package com.gcom.operacional.token;

/**
 * JwtUsuario 
 * 
 * @author Open2000
 * @version 0.1
 * @since jdk-11.0.7
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface JwtUsuario {

}
