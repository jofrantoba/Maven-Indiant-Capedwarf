package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class NotificacionTurista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idNotificacionTurista;
	@Persistent
	private String codeNotificacionTurista;	
	@Persistent
	private String visto;		
	@Persistent
	private Long version;
	@Persistent
	private java.util.Date fechaGeneroNotificacion;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private Notificacion beanNotificacion;
	@Persistent
	private String codeNotificacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaRecibeNotificacion;
	@Persistent
	private String codeTuristaRecibeNotificacion;	
	@Persistent
	@Unowned
	private TipoNotificacion beanTipoNotificacion;
	@Persistent
	private String codeTipoNotificacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaGeneraNotificacion;
	@Persistent
	private String codeTuristaGeneraNotificacion;
	@Persistent
	private String nombreTuristaNegocioGeneraNotificacion;
	@Persistent
	private String apellidoTuristaGeneraNotificacion;
	@Persistent
	private String fotoPerfilTuristaNegocioGeneraNotificacion;
	@Persistent
	@Unowned
	private Noticia beanNoticia;
	@Persistent
	private String codeNoticia;
	@Persistent
	@Unowned
	private Novedad beanNovedad;
	@Persistent
	private String codeNovedad;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;
	@Persistent
	@Unowned
	private Miembro beanMiembro;
	@Persistent
	private String codeMiembro;
	@Persistent
	private String codeDivulgacionNoticia;
	@Persistent
	private String codeNoticiaCompartida;
	@Persistent
	@Unowned
	private SolicitudAmistad beanSolicitudAmistad;
	@Persistent
	private String codeSolicitudAmistad;
	@Persistent
	private String codeComentarioNoticia;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaPublica;
	@Persistent
	private String codeTuristaPublica;
	@Persistent
	private String nombreTuristaPublica;
	@Persistent
	private String apellidoTuristaPublica;
	@Persistent
	private String fotoPerfilTuristaPublica;
	@Persistent
	@Unowned
	private Conquista beanConquista;
	@Persistent
	private String codeConquista;
	@Persistent
	@Unowned
	private Destino beanDestino;
	@Persistent
	private String codeDestino;
	@Persistent
	private String nombreDestinoNegocioConquistado;
	@Persistent
	@Unowned
	private OfertaNegocio beanOfertaNegocio;
	@Persistent
	private String codeOfertaNegocio;
	@Persistent
	@Unowned
	private TuristaInteresEmpatia beanTuristaInteresEmpatia;
	@Persistent
	private String codeTuristaInteresEmpatia;
	@Persistent
	@Unowned
	private Interes beanInteres;
	@Persistent
	private String codeInteres;
	@Persistent
	private String nombreInteres;
	@Persistent
	@Unowned
	private SugerenciaColonia beanSugerenciaColonia;
	@Persistent
	private String codeSugerenciaColonia;
	@Persistent
	private String mensajeNotificacion;
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocioGeneraNoticia;
	@Persistent
	private String codeNegocioGeneraNoticia;	
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocioConquistado;
	@Persistent
	private String codeNegocioConquistado;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String tokenFirebase;
	@Persistent
	private String resultTokenFirebase;
	
	public String getIdNotificacionTurista() {
		return idNotificacionTurista;
	}
	public void setIdNotificacionTurista(String idNotificacionTurista) {
		this.idNotificacionTurista=idNotificacionTurista;				
	}
	public String getCodeNotificacionTurista() {
		return codeNotificacionTurista;
	}
	public void setCodeNotificacionTurista(String codeNotificacionTurista) {
		this.codeNotificacionTurista = codeNotificacionTurista;
	}
	public String getVisto() {
		return visto;
	}
	public void setVisto(String visto) {
		this.visto = visto;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public Notificacion getBeanNotificacion() {
		return beanNotificacion;
	}
	public void setBeanNotificacion(Notificacion beanNotificacion) {
		this.beanNotificacion = beanNotificacion;
	}
	public String getCodeNotificacion() {
		return codeNotificacion;
	}		
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	public String getResultTokenFirebase() {
		return resultTokenFirebase;
	}
	public void setResultTokenFirebase(String resultTokenFirebase) {
		this.resultTokenFirebase = resultTokenFirebase;
	}
	public void setCodeNotificacion(String codeNotificacion) {
		this.codeNotificacion = codeNotificacion;
	}
	public UsuarioTurista getBeanTuristaRecibeNotificacion() {
		return beanTuristaRecibeNotificacion;
	}
	public void setBeanTuristaRecibeNotificacion(
			UsuarioTurista beanTuristaRecibeNotificacion) {
		this.beanTuristaRecibeNotificacion = beanTuristaRecibeNotificacion;
	}
	public String getCodeTuristaRecibeNotificacion() {
		return codeTuristaRecibeNotificacion;
	}
	public void setCodeTuristaRecibeNotificacion(
			String codeTuristaRecibeNotificacion) {
		this.codeTuristaRecibeNotificacion = codeTuristaRecibeNotificacion;
	}
	public TipoNotificacion getBeanTipoNotificacion() {
		return beanTipoNotificacion;
	}
	public void setBeanTipoNotificacion(TipoNotificacion beanTipoNotificacion) {
		this.beanTipoNotificacion = beanTipoNotificacion;
	}	
	public java.util.Date getFechaGeneroNotificacion() {
		return fechaGeneroNotificacion;
	}
	public void setFechaGeneroNotificacion(java.util.Date fechaGeneroNotificacion) {
		this.fechaGeneroNotificacion = fechaGeneroNotificacion;
	}
	public String getCodeTipoNotificacion() {
		return codeTipoNotificacion;
	}
	public void setCodeTipoNotificacion(String codeTipoNotificacion) {
		this.codeTipoNotificacion = codeTipoNotificacion;
	}
	public UsuarioTurista getBeanTuristaGeneraNotificacion() {
		return beanTuristaGeneraNotificacion;
	}
	public void setBeanTuristaGeneraNotificacion(
			UsuarioTurista beanTuristaGeneraNotificacion) {
		this.beanTuristaGeneraNotificacion = beanTuristaGeneraNotificacion;
	}
	public String getCodeTuristaGeneraNotificacion() {
		return codeTuristaGeneraNotificacion;
	}
	public void setCodeTuristaGeneraNotificacion(
			String codeTuristaGeneraNotificacion) {
		this.codeTuristaGeneraNotificacion = codeTuristaGeneraNotificacion;
	}
	public String getNombreTuristaNegocioGeneraNotificacion() {
		return nombreTuristaNegocioGeneraNotificacion;
	}
	public void setNombreTuristaNegocioGeneraNotificacion(
			String nombreTuristaNegocioGeneraNotificacion) {
		this.nombreTuristaNegocioGeneraNotificacion = nombreTuristaNegocioGeneraNotificacion;
	}
	public String getApellidoTuristaGeneraNotificacion() {
		return apellidoTuristaGeneraNotificacion;
	}
	public void setApellidoTuristaGeneraNotificacion(
			String apellidoTuristaGeneraNotificacion) {
		this.apellidoTuristaGeneraNotificacion = apellidoTuristaGeneraNotificacion;
	}
	public String getFotoPerfilTuristaNegocioGeneraNotificacion() {
		return fotoPerfilTuristaNegocioGeneraNotificacion;
	}
	public void setFotoPerfilTuristaNegocioGeneraNotificacion(
			String fotoPerfilTuristaNegocioGeneraNotificacion) {
		this.fotoPerfilTuristaNegocioGeneraNotificacion = fotoPerfilTuristaNegocioGeneraNotificacion;
	}
	public Noticia getBeanNoticia() {
		return beanNoticia;
	}
	public void setBeanNoticia(Noticia beanNoticia) {
		this.beanNoticia = beanNoticia;
	}
	public String getCodeNoticia() {
		return codeNoticia;
	}
	public void setCodeNoticia(String codeNoticia) {
		this.codeNoticia = codeNoticia;
	}
	public Novedad getBeanNovedad() {
		return beanNovedad;
	}
	public void setBeanNovedad(Novedad beanNovedad) {
		this.beanNovedad = beanNovedad;
	}
	public String getCodeNovedad() {
		return codeNovedad;
	}
	public void setCodeNovedad(String codeNovedad) {
		this.codeNovedad = codeNovedad;
	}
	public Colonia getBeanColonia() {
		return beanColonia;
	}
	public void setBeanColonia(Colonia beanColonia) {
		this.beanColonia = beanColonia;
	}
	public String getCodeColonia() {
		return codeColonia;
	}
	public void setCodeColonia(String codeColonia) {
		this.codeColonia = codeColonia;
	}
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}	
	public String getCodeDivulgacionNoticia() {
		return codeDivulgacionNoticia;
	}
	public void setCodeDivulgacionNoticia(String codeDivulgacionNoticia) {
		this.codeDivulgacionNoticia = codeDivulgacionNoticia;
	}	
	public String getCodeNoticiaCompartida() {
		return codeNoticiaCompartida;
	}
	public void setCodeNoticiaCompartida(String codeNoticiaCompartida) {
		this.codeNoticiaCompartida = codeNoticiaCompartida;
	}
	public SolicitudAmistad getBeanSolicitudAmistad() {
		return beanSolicitudAmistad;
	}
	public void setBeanSolicitudAmistad(SolicitudAmistad beanSolicitudAmistad) {
		this.beanSolicitudAmistad = beanSolicitudAmistad;
	}
	public String getCodeSolicitudAmistad() {
		return codeSolicitudAmistad;
	}
	public void setCodeSolicitudAmistad(String codeSolicitudAmistad) {
		this.codeSolicitudAmistad = codeSolicitudAmistad;
	}	
	public String getCodeComentarioNoticia() {
		return codeComentarioNoticia;
	}
	public void setCodeComentarioNoticia(String codeComentarioNoticia) {
		this.codeComentarioNoticia = codeComentarioNoticia;
	}
	public UsuarioTurista getBeanTuristaPublica() {
		return beanTuristaPublica;
	}
	public void setBeanTuristaPublica(UsuarioTurista beanTuristaPublica) {
		this.beanTuristaPublica = beanTuristaPublica;
	}
	public String getCodeTuristaPublica() {
		return codeTuristaPublica;
	}
	public void setCodeTuristaPublica(String codeTuristaPublica) {
		this.codeTuristaPublica = codeTuristaPublica;
	}
	public String getNombreTuristaPublica() {
		return nombreTuristaPublica;
	}
	public void setNombreTuristaPublica(String nombreTuristaPublica) {
		this.nombreTuristaPublica = nombreTuristaPublica;
	}
	public String getApellidoTuristaPublica() {
		return apellidoTuristaPublica;
	}
	public void setApellidoTuristaPublica(String apellidoTuristaPublica) {
		this.apellidoTuristaPublica = apellidoTuristaPublica;
	}
	public Conquista getBeanConquista() {
		return beanConquista;
	}
	public void setBeanConquista(Conquista beanConquista) {
		this.beanConquista = beanConquista;
	}
	public String getCodeConquista() {
		return codeConquista;
	}
	public void setCodeConquista(String codeConquista) {
		this.codeConquista = codeConquista;
	}
	public Destino getBeanDestino() {
		return beanDestino;
	}
	public void setBeanDestino(Destino beanDestino) {
		this.beanDestino = beanDestino;
	}
	public String getCodeDestino() {
		return codeDestino;
	}
	public void setCodeDestino(String codeDestino) {
		this.codeDestino = codeDestino;
	}
	public String getNombreDestinoNegocioConquistado() {
		return nombreDestinoNegocioConquistado;
	}
	public void setNombreDestinoNegocioConquistado(
			String nombreDestinoNegocioConquistado) {
		this.nombreDestinoNegocioConquistado = nombreDestinoNegocioConquistado;
	}
	public OfertaNegocio getBeanOfertaNegocio() {
		return beanOfertaNegocio;
	}
	public void setBeanOfertaNegocio(OfertaNegocio beanOfertaNegocio) {
		this.beanOfertaNegocio = beanOfertaNegocio;
	}
	public String getCodeOfertaNegocio() {
		return codeOfertaNegocio;
	}
	public void setCodeOfertaNegocio(String codeOfertaNegocio) {
		this.codeOfertaNegocio = codeOfertaNegocio;
	}
	public TuristaInteresEmpatia getBeanTuristaInteresEmpatia() {
		return beanTuristaInteresEmpatia;
	}
	public void setBeanTuristaInteresEmpatia(
			TuristaInteresEmpatia beanTuristaInteresEmpatia) {
		this.beanTuristaInteresEmpatia = beanTuristaInteresEmpatia;
	}
	public String getCodeTuristaInteresEmpatia() {
		return codeTuristaInteresEmpatia;
	}
	public void setCodeTuristaInteresEmpatia(String codeTuristaInteresEmpatia) {
		this.codeTuristaInteresEmpatia = codeTuristaInteresEmpatia;
	}
	public Interes getBeanInteres() {
		return beanInteres;
	}
	public void setBeanInteres(Interes beanInteres) {
		this.beanInteres = beanInteres;
	}
	public String getCodeInteres() {
		return codeInteres;
	}
	public void setCodeInteres(String codeInteres) {
		this.codeInteres = codeInteres;
	}
	public String getNombreInteres() {
		return nombreInteres;
	}
	public void setNombreInteres(String nombreInteres) {
		this.nombreInteres = nombreInteres;
	}
	public SugerenciaColonia getBeanSugerenciaColonia() {
		return beanSugerenciaColonia;
	}
	public void setBeanSugerenciaColonia(SugerenciaColonia beanSugerenciaColonia) {
		this.beanSugerenciaColonia = beanSugerenciaColonia;
	}
	public String getCodeSugerenciaColonia() {
		return codeSugerenciaColonia;
	}
	public void setCodeSugerenciaColonia(String codeSugerenciaColonia) {
		this.codeSugerenciaColonia = codeSugerenciaColonia;
	}
	public String getMensajeNotificacion() {
		return mensajeNotificacion;
	}
	public void setMensajeNotificacion(String mensajeNotificacion) {
		this.mensajeNotificacion = mensajeNotificacion;
	}
	public UsuarioNegocio getBeanNegocioGeneraNoticia() {
		return beanNegocioGeneraNoticia;
	}
	public void setBeanNegocioGeneraNoticia(UsuarioNegocio beanNegocioGeneraNoticia) {
		this.beanNegocioGeneraNoticia = beanNegocioGeneraNoticia;
	}
	public String getCodeNegocioGeneraNoticia() {
		return codeNegocioGeneraNoticia;
	}
	public void setCodeNegocioGeneraNoticia(String codeNegocioGeneraNoticia) {
		this.codeNegocioGeneraNoticia = codeNegocioGeneraNoticia;
	}
	public UsuarioNegocio getBeanNegocioConquistado() {
		return beanNegocioConquistado;
	}
	public void setBeanNegocioConquistado(UsuarioNegocio beanNegocioConquistado) {
		this.beanNegocioConquistado = beanNegocioConquistado;
	}
	public String getCodeNegocioConquistado() {
		return codeNegocioConquistado;
	}
	public void setCodeNegocioConquistado(String codeNegocioConquistado) {
		this.codeNegocioConquistado = codeNegocioConquistado;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	
	public String getFotoPerfilTuristaPublica() {
		return fotoPerfilTuristaPublica;
	}
	public void setFotoPerfilTuristaPublica(String fotoPerfilTuristaPublica) {
		this.fotoPerfilTuristaPublica = fotoPerfilTuristaPublica;
	}
	
	public Miembro getBeanMiembro() {
		return beanMiembro;
	}
	public void setBeanMiembro(Miembro beanMiembro) {
		this.beanMiembro = beanMiembro;
	}
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeNotificacionTurista == null) ? 0
						: codeNotificacionTurista.hashCode());
		result = prime
				* result
				+ ((idNotificacionTurista == null) ? 0 : idNotificacionTurista
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificacionTurista other = (NotificacionTurista) obj;
		if (codeNotificacionTurista == null) {
			if (other.codeNotificacionTurista != null)
				return false;
		} else if (!codeNotificacionTurista
				.equals(other.codeNotificacionTurista))
			return false;
		if (idNotificacionTurista == null) {
			if (other.idNotificacionTurista != null)
				return false;
		} else if (!idNotificacionTurista.equals(other.idNotificacionTurista))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificacionTurista [idNotificacionTurista=");
		builder.append(idNotificacionTurista);
		builder.append(", codeNotificacionTurista=");
		builder.append(codeNotificacionTurista);
		builder.append(", visto=");
		builder.append(visto);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNotificacion=");
		builder.append(beanNotificacion);
		builder.append(", codeNotificacion=");
		builder.append(codeNotificacion);
		builder.append(", beanTuristaRecibeNotificacion=");
		builder.append(beanTuristaRecibeNotificacion);
		builder.append(", codeTuristaRecibeNotificacion=");
		builder.append(codeTuristaRecibeNotificacion);
		builder.append(", beanTipoNotificacion=");
		builder.append(beanTipoNotificacion);
		builder.append(", codeTipoNotificacion=");
		builder.append(codeTipoNotificacion);
		builder.append(", beanTuristaGeneraNotificacion=");
		builder.append(beanTuristaGeneraNotificacion);
		builder.append(", codeTuristaGeneraNotificacion=");
		builder.append(codeTuristaGeneraNotificacion);
		builder.append(", nombreTuristaNegocioGeneraNotificacion=");
		builder.append(nombreTuristaNegocioGeneraNotificacion);
		builder.append(", apellidoTuristaGeneraNotificacion=");
		builder.append(apellidoTuristaGeneraNotificacion);
		builder.append(", fotoPerfilTuristaNegocioGeneraNotificacion=");
		builder.append(fotoPerfilTuristaNegocioGeneraNotificacion);
		builder.append(", beanNoticia=");
		builder.append(beanNoticia);
		builder.append(", codeNoticia=");
		builder.append(codeNoticia);
		builder.append(", beanNovedad=");
		builder.append(beanNovedad);
		builder.append(", codeNovedad=");
		builder.append(codeNovedad);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
		builder.append(", beanMiembro=");
		builder.append(beanMiembro);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", codeDivulgacionNoticia=");
		builder.append(codeDivulgacionNoticia);
		builder.append(", codeNoticiaCompartida=");
		builder.append(codeNoticiaCompartida);
		builder.append(", beanSolicitudAmistad=");
		builder.append(beanSolicitudAmistad);
		builder.append(", codeSolicitudAmistad=");
		builder.append(codeSolicitudAmistad);
		builder.append(", codeComentarioNoticia=");
		builder.append(codeComentarioNoticia);
		builder.append(", beanTuristaPublica=");
		builder.append(beanTuristaPublica);
		builder.append(", codeTuristaPublica=");
		builder.append(codeTuristaPublica);
		builder.append(", nombreTuristaPublica=");
		builder.append(nombreTuristaPublica);
		builder.append(", apellidoTuristaPublica=");
		builder.append(apellidoTuristaPublica);
		builder.append(", fotoPerfilTuristaPublica=");
		builder.append(fotoPerfilTuristaPublica);
		builder.append(", beanConquista=");
		builder.append(beanConquista);
		builder.append(", codeConquista=");
		builder.append(codeConquista);
		builder.append(", beanDestino=");
		builder.append(beanDestino);
		builder.append(", codeDestino=");
		builder.append(codeDestino);
		builder.append(", nombreDestinoNegocioConquistado=");
		builder.append(nombreDestinoNegocioConquistado);
		builder.append(", beanOfertaNegocio=");
		builder.append(beanOfertaNegocio);
		builder.append(", codeOfertaNegocio=");
		builder.append(codeOfertaNegocio);
		builder.append(", beanTuristaInteresEmpatia=");
		builder.append(beanTuristaInteresEmpatia);
		builder.append(", codeTuristaInteresEmpatia=");
		builder.append(codeTuristaInteresEmpatia);
		builder.append(", beanInteres=");
		builder.append(beanInteres);
		builder.append(", codeInteres=");
		builder.append(codeInteres);
		builder.append(", nombreInteres=");
		builder.append(nombreInteres);
		builder.append(", beanSugerenciaColonia=");
		builder.append(beanSugerenciaColonia);
		builder.append(", codeSugerenciaColonia=");
		builder.append(codeSugerenciaColonia);
		builder.append(", mensajeNotificacion=");
		builder.append(mensajeNotificacion);
		builder.append(", beanNegocioGeneraNoticia=");
		builder.append(beanNegocioGeneraNoticia);
		builder.append(", codeNegocioGeneraNoticia=");
		builder.append(codeNegocioGeneraNoticia);
		builder.append(", beanNegocioConquistado=");
		builder.append(beanNegocioConquistado);
		builder.append(", codeNegocioConquistado=");
		builder.append(codeNegocioConquistado);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
		
}
