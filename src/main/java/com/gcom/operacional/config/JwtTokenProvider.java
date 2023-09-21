package com.gcom.operacional.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcom.operacional.token.AuthorityDto;
import com.gcom.operacional.token.KeyClaimsTokenEnum;
import com.gcom.operacional.token.ProfileDto;
import com.gcom.operacional.token.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jesus Garcia
 * @version 1.0
 */
@Component
@Slf4j
public class JwtTokenProvider {

	@Value("${gcom.secret-key}")
	private String jwtSecret;

	/**
	 * Funcion que se encarga de crear el token utilizando el caracter secreto para
	 * crear el cuerpo y la cabecera del token
	 * 
	 * @param authentication Objeto que representa las caracteristicas principales
	 *                       del usuario autenticado
	 * @return String que representa la cadena de caracteres creadas para ser el
	 *         token del usuario
	 */
	public String generateToken(Authentication authentication) throws IOException {
		log.info("[generateToken]--> inicio metodo desde libreria");
		ObjectMapper mapper = new ObjectMapper();

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Collection<? extends GrantedAuthority> permisos = authentication.getAuthorities();
		Claims claims = Jwts.claims();

		claims.put(KeyClaimsTokenEnum.AUTHORITIES.getDescripcion(), mapper.writeValueAsString(permisos));
		claims.put(KeyClaimsTokenEnum.PROFILE.getDescripcion(), mapper.writeValueAsString(userPrincipal.getProfile()));
		claims.put(KeyClaimsTokenEnum.ID_USUARIO.getDescripcion(), userPrincipal.getIdUsuario());
		claims.put(KeyClaimsTokenEnum.FULL_NAME.getDescripcion(), userPrincipal.getFullName());
		claims.put(KeyClaimsTokenEnum.MAIL.getDescripcion(), userPrincipal.getMail());
		claims.put(KeyClaimsTokenEnum.USERNAME.getDescripcion(), userPrincipal.getUsername());
		claims.put(KeyClaimsTokenEnum.RUT.getDescripcion(), userPrincipal.getRut());

		Date expiryDate = new Date(System.currentTimeMillis() + 6000000);

		return Jwts.builder().setClaims(claims).setSubject(userPrincipal.getIdUsuario().toString())
				.setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(jwtSecret.getBytes()))
				.compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes())).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public String getFullNameFromJWT(String token) {
		return getDataByKeyClaims(KeyClaimsTokenEnum.FULL_NAME, token);
	}

	public String getUsernameFromJWT(String token) {
		return getDataByKeyClaims(KeyClaimsTokenEnum.USERNAME, token);
	}

	public Collection<? extends GrantedAuthority> getPermisos(String token) throws IOException {
		String permisos = getDataByKeyClaims(KeyClaimsTokenEnum.AUTHORITIES, token);

		final Collection<GrantedAuthority> authorities = new ArrayList<>();

		if (permisos != null && !permisos.isEmpty()) {
			List<AuthorityDto> p = Arrays.asList(new ObjectMapper().readValue(permisos, AuthorityDto[].class));
			p.forEach(x -> authorities.add(new SimpleGrantedAuthority(x.getAuthority())));
		}

		return authorities;
	}

	public ProfileDto getRoles(String token) throws IOException {
		String profileString = getDataByKeyClaims(KeyClaimsTokenEnum.PROFILE, token);
		ProfileDto profile = new ObjectMapper().readValue(profileString.toString().getBytes(), ProfileDto.class);
		return profile;
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes())).parseClaimsJws(token).getBody();
	}

	/**
	 * Genera el objeto UserPrincipal desde el token para el doFilterInternal
	 * 
	 * @param token
	 * @return
	 */
	public UserPrincipal getUserPrincipalFromToken(String token) throws IOException {
		Claims claims = getClaims(token);

		UserPrincipal userPrincipal = new UserPrincipal();
		userPrincipal.setAuthorities(getPermisos(token));
		userPrincipal.setProfile(getRoles(token));
		userPrincipal.setIdUsuario(getUserIdFromJWT(token));
		userPrincipal.setFullName(findKeyClaimsInData(KeyClaimsTokenEnum.FULL_NAME, claims));
		userPrincipal.setMail(findKeyClaimsInData(KeyClaimsTokenEnum.MAIL, claims));
		userPrincipal.setUsername(findKeyClaimsInData(KeyClaimsTokenEnum.USERNAME, claims));
		userPrincipal.setRut(findKeyClaimsInData(KeyClaimsTokenEnum.RUT, claims));

		return userPrincipal;
	}

	/**
	 * Metodo que se encarga de revisar si el token es valido o ya expiro
	 * 
	 * @param authToken Secuencia de caracteres que representa el token recibido
	 * @return <boolean> Que especifica si el token es valido o no true = token
	 *         valido, false = token invalido
	 */
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes())).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}

	private String getDataByKeyClaims(KeyClaimsTokenEnum keyClaimsTokenEnum, String token) {
		Claims claims = getClaims(token);

		return findKeyClaimsInData(keyClaimsTokenEnum, claims);
	}

	private String findKeyClaimsInData(KeyClaimsTokenEnum keyClaimsTokenEnum, Claims claims) {
		return claims.get(keyClaimsTokenEnum.getDescripcion(), String.class);
	}

}
