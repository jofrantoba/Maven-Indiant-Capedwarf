package com.indiana.shared;

public class Message {
	public static final String ERROR_OPERACION="No se registro la Transaccion";
	public static final String ERROR_EXISTENCIA="Ya se encuentra en el Registro";
	public static final String ERROR_NO_EXISTENCIA="No se encuentra en el Registro";
	public static final String CUENTA_NO_ACTIVA="Su Cuenta no esta Activa";
	public static final String PARAMETROS_INVALIDOS="Parametros Invalidos";
	public static final String CLAVE_NO_COINCIDE="Las Claves no Coinciden";
	public static final String INTERVALO_NO_PERMITIDO="Intervalo no Permitido !";
	public static final String NO_ES_MIEMBRO="No es Miembro de Colonia";
	
	public static String errorNoExistencia(Class<?> otherClass){
		return ERROR_NO_EXISTENCIA+" "+otherClass.getSimpleName();
	}
}
