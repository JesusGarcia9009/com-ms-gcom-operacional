package com.gcom.operacional.token;

public enum KeyClaimsTokenEnum {
	
	ID_USUARIO("idUsuario"),
	FULL_NAME("fullName"),
	MAIL("mail"),
	USERNAME("username"),
	AUTHORITIES("authorities"),
	PROFILE("profile"),
	RUT("rut"),
	USERINPUT("userinput"),
	PERFILES("perfiles"),
	APLICACIONES("aplicaciones");
	
	private String descripcion;
	
	KeyClaimsTokenEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
