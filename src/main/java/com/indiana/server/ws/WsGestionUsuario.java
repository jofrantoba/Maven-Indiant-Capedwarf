package com.indiana.server.ws;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.AutenticaOauth;
import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.bean.ConfigCuenta;
import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.SesionTurista;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.process.GestionTurista;
import com.indiana.shared.ReturnValue;
import com.indiana.shared.UnknownException;
@Api(name = "gestionUsuario", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))

public class WsGestionUsuario {
	
	@ApiMethod(name = "loginTurista",path="loginTurista")
	public ReturnValue loginTurista(@Named("wsCorreoTurista") String correoTurista,@Named("wsClaveTurista") String claveTurista,@Named("wsTokenFirebase") String tokenFirebase) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(SesionTurista.class);						
			valorRetorno.setReturnSesionTurista(GestionTurista.loginTurista(correoTurista,claveTurista,tokenFirebase));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "autenticarViaOauth",path="autenticarViaOauth")
	public ReturnValue autenticarViaOauth(AutenticaOauth beanOauthTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(SesionTurista.class);						
			valorRetorno.setReturnSesionTurista(GestionTurista.autenticarViaOauth(beanOauthTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "crearCuentaTurista",path="crearCuentaTurista")
	public ReturnValue crearCuentaTurista(UsuarioTurista beanUsuarioTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
			valorRetorno.setReturnUsuarioTurista((UsuarioTurista)GestionTurista.crearCuentaTurista(beanUsuarioTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}

		
	@ApiMethod(name = "recuperarClaveCuentaTurista",path="recuperarClaveCuentaTurista")
	public ReturnValue recuperarClaveCuentaTurista(@Named("wsCorreoRecuperacion")String correoCuentaTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionTurista.recuperarClaveCuentaTurista(correoCuentaTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	
	@ApiMethod(name = "divulgarNoticia",path="divulgarNoticia")
	public ReturnValue divulgarNoticia(@Named("wsCodeTuristaDivulga")String codeTuristaDivulga,@Named("wsCodeNoticiaDivulgada")String codeNoticia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Integer.class);
			valorRetorno.setValueReturn(GestionTurista.divulgarNoticia(codeTuristaDivulga,codeNoticia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "getConfiguracionCuenta",path="getConfiguracionCuenta")
	public ReturnValue getConfiguracionCuenta(@Named("wsCorreoCuentaConfig")String wsCorreoCuentaConfig){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(ConfigCuenta.class);						
			valorRetorno.setReturnConfigCuenta(GestionTurista.getConfiguracionCuenta(wsCorreoCuentaConfig));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verMuroNoticiasTurista",path="verMuroNoticiasTurista")
	public ReturnValue verMuroNoticiasTurista(@Named("wsLimiteInferior")Integer wsLimiteInferior,@Named("wsLimiteSuperior")Integer wsLimiteSuperior,@Named("wsCorreoTurista")String wsCorreoUsuarioTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListMuroNoticia(GestionTurista.verMuroNoticiasTurista(wsLimiteInferior, wsLimiteSuperior, wsCorreoUsuarioTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "cambiarClaveTurista",path="cambiarClaveTurista")
	public ReturnValue cambiarClaveTurista(
			@Named("wsCorreoCambiarClave")String correoTurista,
			@Nullable @Named("wsClaveAnterior")String claveAnterior,
			@Named("claveNueva")String claveNueva,
			@Named("cctTipoAutenticacion")String tipoAutenticacion
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionTurista.cambiarClaveTurista(correoTurista, claveAnterior, claveNueva, tipoAutenticacion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listPaisesCuenta",path="listPaisesCuenta")
	public ReturnValue listPaisesCuenta() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListaPais(GestionTurista.listPaisesCuenta(null));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "comentarNoticia",path="comentarNoticia")
	public ReturnValue comentarNoticia(
			@Named("cnCorreoTuristaComenta")String correoTurista,
			@Named("cnCodeNoticiaComenta")String codeNoticia,
			@Named("cnComentario")String comentario,
			@Named("cnFechaComentario")Date fechaComentario) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(ComentarioNoticia.class);
			valorRetorno.setReturnComentarioNoticia(GestionTurista.comentarNoticia(correoTurista, codeNoticia, comentario,fechaComentario));				
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarComentarioNoticia",path="listarComentarioNoticia")
	public ReturnValue listarComentarioNoticia(@Named("lsCodeNoticiaComentada")String codeNoticia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListComentarioNoticia(GestionTurista.listarComentarioNoticia(codeNoticia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarDivulgacionNoticia",path="listarDivulgacionNoticia")
	public ReturnValue listarDivulgacionNoticia(@Named("lsCodeNoticiaDivulgada")String codeNoticia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListDivulgacionNoticia(GestionTurista.listarDivulgacionNoticia(codeNoticia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarNoticiaCompartida",path="listarNoticiaCompartida")
	public ReturnValue listarNoticiaCompartida(@Named("lsCodeNoticiaCompartida")String codeNoticia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListNoticiaCompartida(GestionTurista.listarComparteNoticia(codeNoticia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verNoticia",path="verNoticia")
	public ReturnValue verNoticia(@Named("lsVerCodeNoticia")String codeNoticia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Noticia.class);
			valorRetorno.setReturnNoticia(GestionTurista.verNoticia(codeNoticia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "enviarSolicitudAmistad",path="enviarSolicitudAmistad")
	public ReturnValue enviarSolicitudAmistad(
			@Named("esaCodeTuristaEnvia")String codeTuristaEnvia,
			@Named("esaCodeTuristaRecibe")String codeTuristaRecibe) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionTurista.enviarSolicitudAmistad(codeTuristaEnvia, codeTuristaRecibe));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "definirSolicitudAmistad",path="definirSolicitudAmistad")
	public ReturnValue definirSolicitudAmistad(
			@Named("dsaCodeSolicitudAmistad")String codeSolicitudAmistad,
			@Named("dsaCodeEstadoSolicitudAmistad")String codeEstadoSolicitudAmistad) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setReturnBoolean(GestionTurista.definirSolicitudAmistad(codeSolicitudAmistad,codeEstadoSolicitudAmistad));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verPerfilTurista",path="verPerfilTurista")
	public ReturnValue verPerfilTurista(
			@Named("vptCorreoTurista")String correoTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
		valorRetorno.setReturnUsuarioTurista(GestionTurista.verPerfilTurista(correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "mostrarInformacionTurista",path="mostrarInformacionTurista")
	public ReturnValue mostrarInformacionTurista(
			@Named("mitCorreoTurista")String correoTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
		valorRetorno.setReturnUsuarioTurista(GestionTurista.mostrarInformacionTurista(correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}

	@ApiMethod(name = "mostrarGaleriaTurista",path="mostrarGaleriaTurista")
	public ReturnValue mostrarGaleriaTurista(
			@Named("mgtLimiteInferior")Integer limiteInferior,
			@Named("mgtLimiteSuperior")Integer limiteSuperior,
			@Named("mgtCorreoTurista")String correoTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
			valorRetorno.setReturnUsuarioTurista(GestionTurista.mostrarGaleriaTurista(limiteInferior, limiteSuperior, correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "verConquistas",path="verConquistas")
	public ReturnValue verConquistas(
			@Named("mgtLimiteInferior")Integer limiteInferior,
			@Named("mgtLimiteSuperior")Integer limiteSuperior,
			@Named("mgtCorreoTurista")String correoTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
			valorRetorno.setReturnUsuarioTurista(GestionTurista.verConquistas(limiteInferior, limiteSuperior, correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "mostrarNovedadTurista",path="mostrarNovedadTurista")
	public ReturnValue mostrarNovedadTurista(
			@Named("mgtLimiteInferior")Integer limiteInferior,
			@Named("mgtLimiteSuperior")Integer limiteSuperior,
			@Named("mgtCorreoTurista")String correoTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
			valorRetorno.setReturnUsuarioTurista(GestionTurista.mostrarNovedadTurista(limiteInferior, limiteSuperior, correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verAmigosTurista",path="verAmigosTurista")
	public ReturnValue verAmigosTurista(
			@Named("vatCorreoTuristaPerfil")String correoTuristaPerfil,
			@Named("vatCorreoTuristaVisita")String correoTuristaVisita
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListAmistad(GestionTurista.verAmigosTurista(correoTuristaPerfil,correoTuristaVisita));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verInteresesTurista",path="verInteresesTurista")
	public ReturnValue verInteresesTurista(
			@Named("vitCorreoTuristaPerfil")String correoTuristaPerfil,
			@Named("vitCorreoTuristaVisita")String correoTuristaVisita
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListTuristaInteres(GestionTurista.verInteresesTurista(correoTuristaPerfil,correoTuristaVisita));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verMisNoticias",path="verMisNoticias")
	public ReturnValue verMisNoticias(
			@Named("vmnLimiteInferior")Integer limiteInferior,
			@Named("vmnLimiteSuperior")Integer limiteSuperior,
			@Named("vmnCorreoTurista")String correoTurista
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListNoticias(GestionTurista.verMisNoticias(limiteInferior, limiteSuperior, correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	/**
	 * El parametro visto puede ir en Null, 'S' o 'N', para listar todos , por Vistos o por no Vistos.
	 * @param correoTurista
	 * @param visto
	 * @return
	 */
	@ApiMethod(name = "verNotificacionTurista",path="verNotificacionTurista")
	public ReturnValue verNotificacionTurista(
			@Named("vntCorreoTurista")String correoTurista,
			@Nullable @Named("vntVisto")String visto			
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListNotificacionTurista(GestionTurista.verNotificacionTurista(correoTurista, visto));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}

	@ApiMethod(name = "definirEstadoNotificacionTurista",path="definirEstadoNotificacionTurista")
	public ReturnValue definirEstadoNotificacionTurista(
			@Named("vntCodeNotificacionTurista")String codeNotificacionTurista		
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionTurista.definirEstadoNotificacionTurista(codeNotificacionTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}

	@ApiMethod(name = "cantidadNotificacionesPendientes",path="cantidadNotificacionesPendientes")
	public ReturnValue cantidadNotificacionesPendientes(
			@Named("vntCorreoTurista")String correoTurista		
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Integer.class);
			valorRetorno.setValueReturn(GestionTurista.cantidadNotificacionesPendientes(correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	@ApiMethod(name = "buscarPersonas",path="buscarPersonas")
	public ReturnValue buscarPersonas(
			@Named("bpPatron")String patron,
			@Nullable @Named("bpNombrePais")String nombrePais,
			@Nullable @Named("bpNombreRegion")String nombreRegion,			
			@Nullable @Named("vmnNombreLocalidad")String nombreLocalidad
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListUsuarioTurista(GestionTurista.buscarPersonas(patron, nombrePais, nombreRegion, nombreLocalidad));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "editarAmigosTurista",path="editarAmigosTurista")
	public ReturnValue editarAmigosTurista(
			@Named("eatCorreoTuristaPrincipal")String codeTuristaPrincipal,
			@Named("eatCorreoTuristaAmigo")String codeTuristaAmigo,
			@Named("eatCodeEstadoAmistad")String codeEstadoAmistad) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(EstadoAmistad.class);
			valorRetorno.setReturnEstadoAmistad(GestionTurista.editarAmigosTurista(codeTuristaPrincipal, codeTuristaAmigo, codeEstadoAmistad));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "editarInformacionTurista",path="editarInformacionTurista")
	public ReturnValue editarInformacionTurista(
			@Named("eitCorreoTurista")String codeTurista,
			@Named("eitCodePais")String codePais,
			@Nullable @Named("eitCodeRegion")String codeRegion,
			@Nullable @Named("eitNombreCiudad")String ciudad,
			@Named("eitFechaNacimiento")Date fechaNacimiento,
			@Named("eitSexo")String sexo) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);
			valorRetorno.setReturnUsuarioTurista(GestionTurista.editarInformacionTurista(codeTurista, codePais, codeRegion, ciudad, fechaNacimiento, sexo));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "editarInteresesTurista",path="editarInteresesTurista")
	public ReturnValue editarInteresesTurista(
			@Named("eitCodeTuristaInteres")String codeTuristaInteres,
			@Named("eitEstadoVisibilidad")String estadoVisibilidad) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setReturnBoolean(GestionTurista.editarInteresesTurista(codeTuristaInteres, estadoVisibilidad));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "editarColoniaTurista",path="editarColoniaTurista")
	public ReturnValue editarColoniaTurista(
			@Named("ectCorreoTurista")String codeTurista,
			@Named("ectCodeColonia")String codeColonia,
			@Named("ectEstadoVisibilidad")String estadoVisibilidad) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setReturnBoolean(GestionTurista.editarColoniasTurista(codeTurista,codeColonia, estadoVisibilidad));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "actualizarConfiguracionCuenta",path="actualizarConfiguracionCuenta")
	public ReturnValue actualizarConfiguracionCuenta(
			@Named("accCorreoTurista")String correoTurista,
			@Named("accCodePrivacidadVerNovedad")String codePrivacidadVerNovedad,
			@Named("accCodePrivacidadInvitarAmigos")String codePrivacidadInvitarAmigos,
			@Named("accCodePrivacidadBuscarMiPerfil")String codePrivacidadBuscarMiPerfil,
			@Named("accCodeIdioma")String codeIdioma){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(ConfigCuenta.class);						
			valorRetorno.setReturnConfigCuenta(GestionTurista.actualizarConfiguracionCuenta(correoTurista, codePrivacidadVerNovedad, codePrivacidadInvitarAmigos, codePrivacidadBuscarMiPerfil, codeIdioma));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "compartirNoticia",path="compartirNoticia")
	public ReturnValue compartirNoticia(
			@Named("cnCodeNoticiaOriginal")String codeNoticiaOriginal,
			@Named("cnCodeColoniaCompartir")String codeColonia,
			@Named("cnCorreoTuristaComparte")String correoTurista,
			@Named("cnDescripcionCompartir")String descripcion){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);						
			valorRetorno.setValueReturn(GestionTurista.compartirNoticia(codeNoticiaOriginal, codeColonia, correoTurista, descripcion));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "haDivulgado",path="haDivulgado")
	public ReturnValue haDivulgado(
			@Named("hdCorreoTurista")String correoTurista,
			@Named("hdCodeNoticia")String codeNoticia){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);						
			valorRetorno.setValueReturn(GestionTurista.haDivulgado(correoTurista, codeNoticia));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "haCompartido",path="haCompartido")
	public ReturnValue haCompartido(
			@Named("hcCorreoTurista")String correoTurista,
			@Named("hdCodeNoticia")String codeNoticia){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);						
			valorRetorno.setValueReturn(GestionTurista.haCompartido(correoTurista, codeNoticia));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "listNotificacionTuristaSolicitudesPendientes",path="listNotificacionTuristaSolicitudesPendientes")
	public ReturnValue listNotificacionTuristaSolicitudesPendientes(
			@Named("lntspCorreoTurista")String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListNotificacionTurista(GestionTurista.listNotificacionTuristaSolicitudesPendientes(correoTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getMessage());
		}
		return valorRetorno;
	}	
}
