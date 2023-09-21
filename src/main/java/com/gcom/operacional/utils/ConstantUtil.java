package com.gcom.operacional.utils;

/**
 * ConstantUtil
 * 
 * @author Jesus Garcia - Open2000
 * @version 0.1
 * @since jdk-11.0.7
 */
public class ConstantUtil {

	private ConstantUtil() {

	}

	/**
	 * Logs
	 */
	public static final String LOG_START = "[INFO :: %s] - START.";
	public static final String LOG_END = "[INFO :: %s] - END.";
	public static final String LOG_START_END = "[INFO :: %s] - START-END.";
	public static final String EXCEPTION_DEPENDENCY = "Content not present.";
	
	/**
	 * Security
	 */
	public static final String[] PATH_ARRAY = new String[] { "/**/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
			"/api/gcom/operacional/login/**", "/configuration/security", "/**/swagger-ui/**", "/swagger-ui.html",
			"/webjars/**" };
	public static final String[] PATH_RESOURCES_ARRAY = new String[] { "/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg",
			"/**/*.html", "/**/*.css", "/**/*.js" };
	
	/**
	 * Errors
	 */
	public static final String ERROR_USER_NOT_FOUND = "Usuario no perfilado, por favor verifique sus credenciales.";
	public static final String EXCEPTION = "[ERROR -> METHOD: %s -> MSG: %s].";
	
	/**
	 * Duplicado
	 */
	public static final String MSG_MAIL_DUPL = "MAIL_DUPL";
	public static final String MSG_PRODUCT_DUPL = "PRODUCT_DUPL";
	public static final String MSG_CLIENT_DUPL = "CLIENT_DUPL";
	public static final String MSG_PROVIDER_DUPL = "PROVIDER_DUPL";
	public static final String MSG_BRAND_DUPL = "BRAND_DUPL";
	public static final String MSG_SOURCE_DUPL = "SOURCE_DUPL";
	public static final String MSG_QUOTATION_DELIVERY_DUPL = "QUOTATION_DELIVERY_DUPL";
	public static final String MSG_INVENTORY_REASON_DUPL = "INVENTORY_REASON_DUPL";
	public static final String MSG_GROUP_DUPL = "GROUP_DUPL";
	public static final String MSG_MODEL_DUPL = "MODEL_DUPL";
	public static final String MSG_PRODUCT_TYPE_DUPL = "PRODUCT_TYPE_DUPL";
	public static final String MSG_DELIVERY_TYPE_DUPL = "DELIVERY_TYPE_DUPL";
	public static final String MSG_PAYMENT_METHOD_DUPL = "PAYMENT_METHOD_DUPL";
	
	/**
	 * Ver
	 */
    public static final String MSG_REF_DUPL = "MSG_REF_DUPL";
    public static final String MSG_RENTA_ACTIVA = "MSG_RENTA_ACTIVA";
    public static final String MSG_CA_ACTIVO = "MSG_CA_ACTIVO";
    public static final String MSG_PERIODO_UTILIZADO = "MSG_PERIODO_UTILIZADO";
    public static final String MSG_NO_RENT_CARG = "MSG_NO_RENT_CARG";
    public static final String MSG_ARR_DUPL = "MSG_ARR_DUPL";

}
