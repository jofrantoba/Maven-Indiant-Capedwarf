package com.indiana.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.process.GestionMiembro;
import com.indiana.shared.ReturnValue;
import com.indiana.shared.UnknownException;
@Api(name = "gestionMiembro", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))
public class WsGestionMiembro {
	
	@ApiMethod(name = "changeEstadoChatColonia",path="changeEstadoChatColonia")
	public ReturnValue changeEstadoChatColonia(
			@Named("ceccCodeColonia")String codeColonia,
			@Named("ceccCorreoTurista")String correoTurista,
			@Named("ceccEstadoChatColonia")Integer estadoChatColonia,
			@Named("ceccTokenFirebase")String tokenFirebase,
			@Named("ceccNombrePais")String nombrePais,
			@Named("ceccNombreRegion")String nombreRegion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Integer.class);
			valorRetorno.setValueReturn(GestionMiembro.changeEstadoChatColonia(codeColonia, correoTurista, estadoChatColonia,tokenFirebase, nombrePais,nombreRegion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "publicarNovedadColonia",path="publicarNovedadColonia")
	public ReturnValue publicarNovedadColonia(
			@Named("pcCodeColonia")String codeColonia,
			@Named("pcCorreoTurista")String correoTurista,
			@Nullable @Named("pcFotoPublicidad")String fotoPublicidad,
			@Nullable @Named("pcHiperLink")String hiperLink,
			@Named("pcDescripcion")String descripcion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Noticia.class);
			valorRetorno.setReturnNoticia(GestionMiembro.publicarNovedadColonia(codeColonia, correoTurista, fotoPublicidad,hiperLink, descripcion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verMiembrosColonia",path="verMiembrosColonia")
	public ReturnValue verMiembrosColonia(
			@Named("vmcCodeColonia")String codeColonia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListMiembros(GestionMiembro.verMiembrosColonia(codeColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "listarTablonesColonia",path="listarTablonesColonia")
	public ReturnValue listarTablonesColonia(
			@Named("ltcCodeColonia")String codeColonia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListTablonColonia(GestionMiembro.listarTablonesColonia(codeColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "cargarSesionUsuarioTurista",path="cargarSesionUsuarioTurista")
	public ReturnValue cargarSesionUsuarioTurista(
			@Named("csutCodeColonia")String codeColonia,
			@Named("csutCorreoTurista")String correoTurista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListMiembros(GestionMiembro.cargarSesionUsuarioTurista(codeColonia, correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "actualizarEstadoSesionTuristaColonia",path="actualizarEstadoSesionTuristaColonia")
	public ReturnValue actualizarEstadoSesionTuristaColonia(
			@Named("aestCodeColonia")String codeColonia,
			@Named("aestCorreoTurista")String correoTurista,
			@Named("aestConectado")Boolean conectado) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionMiembro.actualizarEstadoSesionTuristaColonia(codeColonia, correoTurista, conectado));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "lanzarTablonColonia",path="lanzarTablonColonia")
	public ReturnValue lanzarTablonColonia(
			@Named("ltcCodeColonia")String codeColonia,
			@Named("ltcCorreoTurista")String correoTurista,
			@Named("ltcMensaje")String mensaje,		
			@Named("ltcTokenFirebase")String tokenFirebase,
			@Nullable @Named("ltcLinkFotoMensaje")String linkFotoMensaje,
			@Nullable @Named("ltcLinkVideoMensaje")String linkVideoMensaje
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionMiembro.lanzarTablonColonia(codeColonia, correoTurista, mensaje, linkFotoMensaje,linkVideoMensaje,tokenFirebase));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "entrarChatTablonColonia",path="entrarChatTablonColonia")
	public ReturnValue entrarChatTablonColonia(
			@Named("ectcCodeColonia")String codeColonia,
			@Named("ectcCorreoTurista")String correoTurista,
			@Named("ectcCodeTablonColonia")String codeTablonColonia
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionMiembro.entrarChatTablonColonia(codeColonia, correoTurista, codeTablonColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "actualizarEstadoParticipanteTablon",path="actualizarEstadoParticipanteTablon")
	public ReturnValue actualizarEstadoParticipanteTablon(
			@Named("aeptCorreoTurista")String correoTurista,
			@Named("aeptCodeTablonColonia")String codeTablonColonia,
			@Named("aeptEstadoParticipante")String estadoParticipante
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionMiembro.actualizarEstadoParticipanteTablon(correoTurista, codeTablonColonia, estadoParticipante));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarParticipantesTablonColonia",path="listarParticipantesTablonColonia")
	public ReturnValue listarParticipantesTablonColonia(
			@Named("lptcCodeColonia")String codeColonia,
			@Named("lptcCorreoTurista")String correoTurista,
			@Named("lptcCodeTablonColonia")String codeTablonColonia
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListParticipanteChatMiembro(GestionMiembro.listarParticipantesTablonColonia(codeColonia, correoTurista, codeTablonColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "responderTablonColonia",path="responderTablonColonia")
	public ReturnValue responderTablonColonia(
			@Named("rtcCorreoTurista")String correoTurista,
			@Named("rtcCodeTablonColonia")String codeTablonColonia,
			@Named("rtcMensaje")String mensaje,
			@Nullable @Named("rtcLinkFotoMensaje")String linkFotoMensaje
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setValueReturn(GestionMiembro.responderTablonColonia(correoTurista, codeTablonColonia, mensaje, linkFotoMensaje));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarMensajesTablonColonia",path="listarMensajesTablonColonia")
	public ReturnValue listarMensajesTablonColonia(			
			@Named("lmtcCodeTablonColonia")String codeTablonColonia			
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListMensajeColoniaPrivado(GestionMiembro.listarMensajesTablonColonia(codeTablonColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarAmigosCercanosColonia",path="listarAmigosCercanosColonia")
	public ReturnValue listarAmigosCercanosColonia(
			@Named("laccCorreoTurista")String correoTurista, 
			@Named("laccCodeColonia")String codeColonia, 
			@Named("laccDistanciaBusqueda")Double distanciaBusqueda
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListAmistad(GestionMiembro.listarAmigosCercanosColonia(correoTurista, codeColonia, distanciaBusqueda));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "sugerirColonia",path="sugerirColonia")
	public ReturnValue sugerirColonia(
			@Named("scListaCorreoTuristasAmigos")List<String> listCodeTuristasSeleccionados,
			@Named("scCorreoTurista")String codeTurista,
			@Named("scCodeColonia")String codeColonia
			) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionMiembro.sugerirColonia(listCodeTuristasSeleccionados, codeTurista, codeColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "aceptarSugerenciaInteres",path="aceptarSugerenciaInteres")
	public ReturnValue aceptarSugerenciaInteres(
			@Named("asiCorreoTurista")String codeTurista,
			@Named("asiCodeTurista")String codeInteres,
			@Named("asiCodeColonia")String codeColonia
			){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionMiembro.aceptarSugerenciaInteres(codeTurista, codeInteres, codeColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}		
}
