package com.indiana.shared;

import java.io.Serializable;
import java.util.List;

import com.indiana.server.model.bean.Amistad;
import com.indiana.server.model.bean.CalificaDestino;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.bean.ConfigCuenta;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.DivulgacionNoticia;
import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.server.model.bean.MensajeColoniaPrivado;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.NoticiaCompartida;
import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.bean.NotificacionTurista;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.ParticipanteChatMiembro;
import com.indiana.server.model.bean.ProxColoniaDestino;
import com.indiana.server.model.bean.SesionNegocio;
import com.indiana.server.model.bean.SesionTurista;
import com.indiana.server.model.bean.TablonColonia;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioTurista;

public class ReturnValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nameClass;
	private Object valueReturn;
	private SesionTurista returnSesionTurista;
	private SesionNegocio returnSesionNegocio;
	private ConfigCuenta returnConfigCuenta;
	private UsuarioTurista returnUsuarioTurista;
	private List<Pais> returnListaPais;
	private List<MuroNoticia>returnListMuroNoticia;
	private MuroNoticia returnMuroNoticia;
	private Notificacion returnNotificacion;
	private Colonia returnColonia;
	private List<ProxColoniaDestino> returnListProxColoniaDestino;
	private List<Destino> returnListDestinos;
	private CalificaDestino returnCalificaDestino;
	private List<ColoniaInteres> returnListColoniaInteres;
	private Destino returnDestino;
	private List<Colonia> returnListColonias;
	private Miembro returnMiembro;
	private List<Noticia> returnListNoticias;
	private String returnMensajeVerificarPosicion;
	private ComentarioNoticia returnComentarioNoticia;
	private DivulgacionNoticia returnDivulgacionNoticia;
	private List<ComentarioNoticia> returnListComentarioNoticia;
	private List<DivulgacionNoticia> returnListDivulgacionNoticia;
	private List<NoticiaCompartida> returnListNoticiaCompartida;
	private Noticia returnNoticia;
	private Boolean returnBoolean;
	private List<Miembro>returnListMiembros;
	private List<Amistad>returnListAmistad;
	private List<TuristaInteres>returnListTuristaInteres;
	private List<UsuarioTurista>returnListUsuarioTurista;
	private EstadoAmistad returnEstadoAmistad;
	private List<TablonColonia> returnListTablonColonia;
	private List<MiembroInteres>returnListMiembroInteres;
	private List<Conquista> returnListConquistas;
	private List<NotificacionTurista> returnListNotificacionTurista;
	private List<ParticipanteChatMiembro> returnListParticipanteChatMiembro;
	private List<MensajeColoniaPrivado> returnListMensajeColoniaPrivado;
	
	public String getNameClass() {
		return nameClass;
	}
	public void setNameClass(Class<?> ObjectNameClass) {
		this.nameClass = ObjectNameClass.getSimpleName();
	}
	public Object getValueReturn() {
		return valueReturn;
	}
	public void setValueReturn(Object valueReturn) {
		this.valueReturn = valueReturn;
	}
	public List<Miembro> getReturnListMiembros() {
		return returnListMiembros;
	}
	public void setReturnListMiembros(List<Miembro> returnListMiembros) {
		this.returnListMiembros = returnListMiembros;
	}
	public Noticia getReturnNoticia() {
		return returnNoticia;
	}	
	public List<Conquista> getReturnListConquistas() {
		return returnListConquistas;
	}
	public void setReturnListConquistas(List<Conquista> returnListConquistas) {
		this.returnListConquistas = returnListConquistas;
	}	
	public List<NotificacionTurista> getReturnListNotificacionTurista() {
		return returnListNotificacionTurista;
	}
	public void setReturnListNotificacionTurista(List<NotificacionTurista> returnListNotificacionTurista) {
		this.returnListNotificacionTurista = returnListNotificacionTurista;
	}
	public void setReturnNoticia(Noticia returnNoticia) {
		this.returnNoticia = returnNoticia;
	}	
	public List<TablonColonia> getReturnListTablonColonia() {
		return returnListTablonColonia;
	}
	public void setReturnListTablonColonia(List<TablonColonia> returnListTablonColonia) {
		this.returnListTablonColonia = returnListTablonColonia;
	}
	public EstadoAmistad getReturnEstadoAmistad() {
		return returnEstadoAmistad;
	}
	public void setReturnEstadoAmistad(EstadoAmistad returnEstadoAmistad) {
		this.returnEstadoAmistad = returnEstadoAmistad;
	}
	public SesionTurista getReturnSesionTurista() {
		return returnSesionTurista;
	}	
	public List<UsuarioTurista> getReturnListUsuarioTurista() {
		return returnListUsuarioTurista;
	}
	public void setReturnListUsuarioTurista(List<UsuarioTurista> returnListUsuarioTurista) {
		this.returnListUsuarioTurista = returnListUsuarioTurista;
	}
	public List<Amistad> getReturnListAmistad() {
		return returnListAmistad;
	}
	public void setReturnListAmistad(List<Amistad> returnListAmistad) {
		this.returnListAmistad = returnListAmistad;
	}
	public void setReturnSesionTurista(SesionTurista returnSesionTurista) {
		this.returnSesionTurista = returnSesionTurista;
	}
	public List<MiembroInteres> getReturnListMiembroInteres() {
		return returnListMiembroInteres;
	}
	public void setReturnListMiembroInteres(List<MiembroInteres> returnListMiembroInteres) {
		this.returnListMiembroInteres = returnListMiembroInteres;
	}
	public List<Pais> getReturnListaPais() {
		return returnListaPais;
	}	
	public List<TuristaInteres> getReturnListTuristaInteres() {
		return returnListTuristaInteres;
	}
	public void setReturnListTuristaInteres(List<TuristaInteres> returnListTuristaInteres) {
		this.returnListTuristaInteres = returnListTuristaInteres;
	}
	public Boolean getReturnBoolean() {
		return returnBoolean;
	}
	public void setReturnBoolean(Boolean returnBoolean) {
		this.returnBoolean = returnBoolean;
	}
	public void setReturnListaPais(List<Pais> returnListaPais) {
		this.returnListaPais = returnListaPais;
	}

	public UsuarioTurista getReturnUsuarioTurista() {
		return returnUsuarioTurista;
	}

	public void setReturnUsuarioTurista(UsuarioTurista returnUsuarioTurista) {
		this.returnUsuarioTurista = returnUsuarioTurista;
	}

	public ConfigCuenta getReturnConfigCuenta() {
		return returnConfigCuenta;
	}

	public void setReturnConfigCuenta(ConfigCuenta returnConfigCuenta) {
		this.returnConfigCuenta = returnConfigCuenta;
	}	

	public SesionNegocio getReturnSesionNegocio() {
		return returnSesionNegocio;
	}

	public void setReturnSesionNegocio(SesionNegocio returnSesionNegocio) {
		this.returnSesionNegocio = returnSesionNegocio;
	}

	public List<MuroNoticia> getReturnListMuroNoticia() {
		return returnListMuroNoticia;
	}

	public void setReturnListMuroNoticia(List<MuroNoticia> returnListMuroNoticia) {
		this.returnListMuroNoticia = returnListMuroNoticia;
	}

	public MuroNoticia getReturnMuroNoticia() {
		return returnMuroNoticia;
	}

	public void setReturnMuroNoticia(MuroNoticia returnMuroNoticia) {
		this.returnMuroNoticia = returnMuroNoticia;
	}

	public Notificacion getReturnNotificacion() {
		return returnNotificacion;
	}

	public void setReturnNotificacion(Notificacion returnNotificacion) {
		this.returnNotificacion = returnNotificacion;
	}

	public Colonia getReturnColonia() {
		return returnColonia;
	}

	public void setReturnColonia(Colonia returnColonia) {
		this.returnColonia = returnColonia;
	}
	
	public List<ProxColoniaDestino> getReturnListProxColoniaDestino() {
		return returnListProxColoniaDestino;
	}

	public void setReturnListProxColoniaDestino(List<ProxColoniaDestino> returnListProxColoniaDestino) {
		this.returnListProxColoniaDestino = returnListProxColoniaDestino;
	}
	public CalificaDestino getReturnCalificaDestino() {
		return returnCalificaDestino;
	}

	public void setReturnCalificaDestino(CalificaDestino returnCalificaDestino) {
		this.returnCalificaDestino = returnCalificaDestino;
	}

	public List<Destino> getReturnListDestinos() {
		return returnListDestinos;
	}

	public void setReturnListDestinos(List<Destino> returnListDestinos) {
		this.returnListDestinos = returnListDestinos;
	}

	public List<ColoniaInteres> getReturnListColoniaInteres() {
		return returnListColoniaInteres;
	}

	public void setReturnListColoniaInteres(List<ColoniaInteres> returnListColoniaInteres) {
		this.returnListColoniaInteres = returnListColoniaInteres;
	}
	public Destino getReturnDestino() {
		return returnDestino;
	}
	public void setReturnDestino(Destino returnDestino) {
		this.returnDestino = returnDestino;
	}
	public List<Colonia> getReturnListColonias() {
		return returnListColonias;
	}
	public void setReturnListColonias(List<Colonia> returnListColonias) {
		this.returnListColonias = returnListColonias;
	}
	public Miembro getReturnMiembro() {
		return returnMiembro;
	}
	public void setReturnMiembro(Miembro returnMiembro) {
		this.returnMiembro = returnMiembro;
	}
	public List<Noticia> getReturnListNoticias() {
		return returnListNoticias;
	}
	public void setReturnListNoticias(List<Noticia> returnListNoticias) {
		this.returnListNoticias = returnListNoticias;
	}
	public String getReturnMensajeVerificarPosicion() {
		return returnMensajeVerificarPosicion;
	}
	public void setReturnMensajeVerificarPosicion(String returnMensajeVerificarPosicion) {
		this.returnMensajeVerificarPosicion = returnMensajeVerificarPosicion;
	}
	public ComentarioNoticia getReturnComentarioNoticia() {
		return returnComentarioNoticia;
	}
	public void setReturnComentarioNoticia(ComentarioNoticia returnComentarioNoticia) {
		this.returnComentarioNoticia = returnComentarioNoticia;
	}
	public DivulgacionNoticia getReturnDivulgacionNoticia() {
		return returnDivulgacionNoticia;
	}
	public void setReturnDivulgacionNoticia(DivulgacionNoticia returnDivulgacionNoticia) {
		this.returnDivulgacionNoticia = returnDivulgacionNoticia;
	}
	public List<ComentarioNoticia> getReturnListComentarioNoticia() {
		return returnListComentarioNoticia;
	}
	public void setReturnListComentarioNoticia(List<ComentarioNoticia> returnListComentarioNoticia) {
		this.returnListComentarioNoticia = returnListComentarioNoticia;
	}
	public List<DivulgacionNoticia> getReturnListDivulgacionNoticia() {
		return returnListDivulgacionNoticia;
	}
	public void setReturnListDivulgacionNoticia(List<DivulgacionNoticia> returnListDivulgacionNoticia) {
		this.returnListDivulgacionNoticia = returnListDivulgacionNoticia;
	}
	public List<NoticiaCompartida> getReturnListNoticiaCompartida() {
		return returnListNoticiaCompartida;
	}
	public void setReturnListNoticiaCompartida(List<NoticiaCompartida> returnListNoticiaCompartida) {
		this.returnListNoticiaCompartida = returnListNoticiaCompartida;
	}
	public List<ParticipanteChatMiembro> getReturnListParticipanteChatMiembro() {
		return returnListParticipanteChatMiembro;
	}
	public void setReturnListParticipanteChatMiembro(List<ParticipanteChatMiembro> returnListParticipanteChatMiembro) {
		this.returnListParticipanteChatMiembro = returnListParticipanteChatMiembro;
	}
	public List<MensajeColoniaPrivado> getReturnListMensajeColoniaPrivado() {
		return returnListMensajeColoniaPrivado;
	}
	public void setReturnListMensajeColoniaPrivado(List<MensajeColoniaPrivado> returnListMensajeColoniaPrivado) {
		this.returnListMensajeColoniaPrivado = returnListMensajeColoniaPrivado;
	}		
}
