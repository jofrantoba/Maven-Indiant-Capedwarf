package com.indiana.shared;

import com.google.appengine.api.modules.ModulesServiceFactory;

public class P {

	public static final String INSERTAR = "I";
	public static final String ACTUALIZAR = "A";
	public static final String PENDIENTE = "P";
	public static final String EJECUTADO = "E";
	public static final String NO_VISTO = "N";
	public static final String ACTIVO = "A";
	public static final String RETIRADO = "R";
	public static final String EXPULSADO = "E";
	public static final String CHAT_MIEMBRO = "CM";
	public static final String CHAT_COLONIA = "CC";
	public static final String GENERA = "G";
	public static final String RESPONDE = "P";
	public static final String FIREBASE_SERVER_KEY = "AIzaSyCKB1cXore70KWHAswFW7kqRKoF_e_tXng";
	public static final String ABIERTO = "A";
	public static final String PROJECTID = "728034091568";
	public static final String CERRADO = "C";
	public static final String ATENDIDA = "A";
	public static final String CADUCADA = "C";
	public static final String BLOQUEADO = "B";
	public static final String DESACTIVO = "D";        
	public static final String GPS="GPS";
	public static final String ACEPTADO = "A";
	public static final String ELIMINADO = "E";
	public static final String RECHAZADO = "P";
	public static final String REGISTRO = "REGISTRO";
	public static final String OAUTH = "OAUTH";
	public static final String VISTO = "S";
	public static final String URL = "http://kiongo.ddns.net:8080";
	public static final String CORREOREMITENTE = "team@kiongo.com";
	public static final String ERROR_OPERACION = "No se registro la Transaccion";
	public static final String ERROR_EXISTENCIA = "Ya se encuentra en el Registro";
	public static final String ERROR_NO_EXISTENCIA = "No se encuentra en el Registro";
	public static final String CUENTA_NO_ACTIVA = "Su Cuenta no esta Activa";
	public static final String CUENTA_NO_EXISTE = "No existe la Cuenta";
	public static final String PARAMETROS_INVALIDOS = "Parametros Invalidos";
	public static final String CLAVE_NO_COINCIDE = "Las Claves no Coinciden";
	public static final String INTERVALO_NO_PERMITIDO = "Intervalo no Permitido !";
	public static final String FUERA_RANGO= "Fuera del Rango Permitido.";
	public static final String NO_ES_MIEMBRO = "No es Miembro de Colonia";
	public static final String PAISDEFAULT = "PERU";
	public static final String REGIONDEFAULT = "LAMBAYEQUE";
	public static final String LOCALIDADDEFAULT = "CHICLAYO";
	public static final String IDIOMA_ESPANOL = "ES";
	public static final String DESCONOCIDO = "DESCONOCIDO";
	public static final String ENVIO_SOLICITUD_AMISTAD = "ESA";
	public static final String SOLICITUD_AMISTAD_ACEPTADA = "SAA";
	public static final String COMENTARIO_CONQUISTA = "CMC";
	public static final String COMPARTIR_CONQUISTA = "CPC";
	public static final String DIVULGAR_CONQUISTA = "DCO";
	public static final String COMENTARIO_CONQUISTA_RELACIONADO = "CCR";
	public static final String DIVULGACION_CONQUISTA_RELACIONADO = "DCR";
	public static final String COMENTARIO_NOVEDAD = "CMN";
	public static final String COMPARTIR_NOVEDAD = "CPN";
	public static final String DIVULGAR_NOVEDAD = "DNO";
	public static final String DIVULGAR_MI_NOVEDAD = "DMN";
	public static final String COMENTARIO_NOVEDAD_RELACIONADA = "CNR";
	public static final String DIVULGACION_NOVEDAD_RELACIONA = "DNR";
	public static final String DIVULGAR_MI_CONQUISTA = "DMC";
	public static final String UNIRSE_A_COLONIA = "UCO";
	public static final String ABANDONAR_COLONIA = "ACO";
	public static final String SUGERIR_COLONIA = "SCO";
	public static final String SUGERIR_INTERES_A_MIEMBRO = "SIM";
	public static final String GENERAR_EMPATIA_INTERES = "GEI";
	public static final String CONQUISTA = "CNQ";
	public static final String CONQUISTA_NEGOCIO = "CN";
	public static final String CONQUISTA_DESTINO = "CD";
	public static final String CONQUISTA_OFERTA = "CO";
	public static final String USUARIO_QUIERE_CONQUISTAR = "UQC";
	public static final String OFERTA_NEGOCIO = "OFN";
	public static final String CREACION_NUEVO_DESTINO = "CND";
	public static final String CREAR_COLONIA = "CCO";
	public static final String DESCUBIERTO_NUEVO_NEGOCIO = "DNN";
	public static final String DESCUBIERTO_NUEVO_DESTINO = "DND";
	public static final String NEGOCIO="NEGOCIO";
	public static final String DESTINO="DESTINO";
	public static final String EMPATIA_COMUNICACION="EC";
	public static final String EMPATIA_MIEMBROS="EM";
	
	//public static String HOST=ModulesServiceFactory.getModulesService().getVersionHostname(null,null);		
	/*public static final String HOST=ModulesServiceFactory.getModulesService().getVersionHostname(
			ModulesServiceFactory.getModulesService().getCurrentModule(),
			ModulesServiceFactory.getModulesService().getCurrentVersion());*/
        public static final String HOST="http://190.117.201.155:8080";
        /*public static final String HOST=ModulesServiceFactory.getModulesService().getVersionHostname("taskmodule",null);*/
	
	public static String errorNoExistencia(Class<?> otherClass) {
		return ERROR_NO_EXISTENCIA + " " + otherClass.getSimpleName();
	}
}
