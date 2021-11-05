package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Notificacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idNotificacion;	
	@Persistent
	private String codeNotificacion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
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
	private String codeColonia;
	@Persistent
	private String nombreColonia;
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
	private UsuarioTurista beanTuristaPublicaNoticia;
	@Persistent
	private String codeTuristaPublicaNoticia;	
	@Persistent
	private String nombreTuristaPublicaNoticia;
	@Persistent
	private String apellidoTuristaPublicaNoticia;
	@Persistent
	private String fotoPerfilTuristaPublicaNoticia;
	@Persistent
	@Unowned
	private Conquista beanConquista;
	@Persistent
	private String codeConquista;
	@Persistent
	@Unowned
	private Interes beanInteres;
	@Persistent
	private String codeInteres;
	@Persistent
	private String nombreInteres;
	@Persistent
	@Unowned
	private Destino beanDestinoConquistadoDescubierto;
	@Persistent
	private String codeDestinoConquistadoDescubierto;
	@Persistent
	private String nombreDestinoNegocioConquistado;
	@Persistent
	@Unowned
	private OfertaNegocio beanOferta;
	@Persistent
	private String codeOferta;
	@Persistent
	@Unowned
	private TuristaInteresEmpatia beanTuristaInteresEmpatia;
	@Persistent
	private String codeTuristaInteresEmpatia;
	@Persistent
	@Unowned
	private SugerenciaColonia beanSugerenciaColonia;
	@Persistent
	private String codeSugerenciaColonia;
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
	private java.util.Date fechaGeneroNotificacion;
	@Persistent
	private String estadoTarea;
	@Persistent
	private String comentarioNoticia;
	@NotPersistent
	private List<TipoNotificacion> listaTipoNotificacion;
	public String getIdNotificacion() {
		return idNotificacion;
	}
	public void setIdNotificacion(String idNotificacion) {
		this.idNotificacion = idNotificacion;
	}
	public String getCodeNotificacion() {
		return codeNotificacion;
	}
	public String getComentarioNoticia() {
		return comentarioNoticia;
	}
	public void setComentarioNoticia(String comentarioNoticia) {
		this.comentarioNoticia = comentarioNoticia;
	}
	public void setCodeNotificacion(String codeNotificacion) {
		this.codeNotificacion = codeNotificacion;
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
	public TipoNotificacion getBeanTipoNotificacion() {
		return beanTipoNotificacion;
	}
	public void setBeanTipoNotificacion(TipoNotificacion beanTipoNotificacion) {
		this.beanTipoNotificacion = beanTipoNotificacion;
	}
	public String getCodeTipoNotificacion() {
		return codeTipoNotificacion;
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
	public void setCodeTipoNotificacion(String codeTipoNotificacion) {
		this.codeTipoNotificacion = codeTipoNotificacion;
	}
	public UsuarioTurista getBeanTuristaGeneraNotificacion() {
		return beanTuristaGeneraNotificacion;
	}
	public void setBeanTuristaGeneraNotificacion(UsuarioTurista beanTuristaGeneraNotificacion) {
		this.beanTuristaGeneraNotificacion = beanTuristaGeneraNotificacion;
	}
	public String getCodeTuristaGeneraNotificacion() {
		return codeTuristaGeneraNotificacion;
	}
	public void setCodeTuristaGeneraNotificacion(String codeTuristaGeneraNotificacion) {
		this.codeTuristaGeneraNotificacion = codeTuristaGeneraNotificacion;
	}
	public String getNombreTuristaNegocioGeneraNotificacion() {
		return nombreTuristaNegocioGeneraNotificacion;
	}
	public void setNombreTuristaNegocioGeneraNotificacion(String nombreTuristaNegocioGeneraNotificacion) {
		this.nombreTuristaNegocioGeneraNotificacion = nombreTuristaNegocioGeneraNotificacion;
	}
	public String getApellidoTuristaGeneraNotificacion() {
		return apellidoTuristaGeneraNotificacion;
	}
	public void setApellidoTuristaGeneraNotificacion(String apellidoTuristaGeneraNotificacion) {
		this.apellidoTuristaGeneraNotificacion = apellidoTuristaGeneraNotificacion;
	}
	public String getFotoPerfilTuristaNegocioGeneraNotificacion() {
		return fotoPerfilTuristaNegocioGeneraNotificacion;
	}
	public void setFotoPerfilTuristaNegocioGeneraNotificacion(String fotoPerfilTuristaNegocioGeneraNotificacion) {
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
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
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
	public UsuarioTurista getBeanTuristaPublicaNoticia() {
		return beanTuristaPublicaNoticia;
	}
	public void setBeanTuristaPublicaNoticia(UsuarioTurista beanTuristaPublicaNoticia) {
		this.beanTuristaPublicaNoticia = beanTuristaPublicaNoticia;
	}
	public String getCodeTuristaPublicaNoticia() {
		return codeTuristaPublicaNoticia;
	}
	public void setCodeTuristaPublicaNoticia(String codeTuristaPublicaNoticia) {
		this.codeTuristaPublicaNoticia = codeTuristaPublicaNoticia;
	}
	public String getNombreTuristaPublicaNoticia() {
		return nombreTuristaPublicaNoticia;
	}
	public void setNombreTuristaPublicaNoticia(String nombreTuristaPublicaNoticia) {
		this.nombreTuristaPublicaNoticia = nombreTuristaPublicaNoticia;
	}
	public String getApellidoTuristaPublicaNoticia() {
		return apellidoTuristaPublicaNoticia;
	}
	public void setApellidoTuristaPublicaNoticia(String apellidoTuristaPublicaNoticia) {
		this.apellidoTuristaPublicaNoticia = apellidoTuristaPublicaNoticia;
	}
	public String getFotoPerfilTuristaPublicaNoticia() {
		return fotoPerfilTuristaPublicaNoticia;
	}
	public void setFotoPerfilTuristaPublicaNoticia(String fotoPerfilTuristaPublicaNoticia) {
		this.fotoPerfilTuristaPublicaNoticia = fotoPerfilTuristaPublicaNoticia;
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
	public Destino getBeanDestinoConquistadoDescubierto() {
		return beanDestinoConquistadoDescubierto;
	}
	public void setBeanDestinoConquistadoDescubierto(Destino beanDestinoConquistadoDescubierto) {
		this.beanDestinoConquistadoDescubierto = beanDestinoConquistadoDescubierto;
	}
	public String getCodeDestinoConquistadoDescubierto() {
		return codeDestinoConquistadoDescubierto;
	}
	public void setCodeDestinoConquistadoDescubierto(String codeDestinoConquistadoDescubierto) {
		this.codeDestinoConquistadoDescubierto = codeDestinoConquistadoDescubierto;
	}
	public String getNombreDestinoNegocioConquistado() {
		return nombreDestinoNegocioConquistado;
	}
	public void setNombreDestinoNegocioConquistado(String nombreDestinoNegocioConquistado) {
		this.nombreDestinoNegocioConquistado = nombreDestinoNegocioConquistado;
	}
	public OfertaNegocio getBeanOferta() {
		return beanOferta;
	}
	public void setBeanOferta(OfertaNegocio beanOferta) {
		this.beanOferta = beanOferta;
	}
	public String getCodeOferta() {
		return codeOferta;
	}
	public void setCodeOferta(String codeOferta) {
		this.codeOferta = codeOferta;
	}
	public TuristaInteresEmpatia getBeanTuristaInteresEmpatia() {
		return beanTuristaInteresEmpatia;
	}
	public void setBeanTuristaInteresEmpatia(TuristaInteresEmpatia beanTuristaInteresEmpatia) {
		this.beanTuristaInteresEmpatia = beanTuristaInteresEmpatia;
	}
	public String getCodeTuristaInteresEmpatia() {
		return codeTuristaInteresEmpatia;
	}
	public void setCodeTuristaInteresEmpatia(String codeTuristaInteresEmpatia) {
		this.codeTuristaInteresEmpatia = codeTuristaInteresEmpatia;
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
	public java.util.Date getFechaGeneroNotificacion() {
		return fechaGeneroNotificacion;
	}
	public void setFechaGeneroNotificacion(java.util.Date fechaGeneroNotificacion) {
		this.fechaGeneroNotificacion = fechaGeneroNotificacion;
	}
	public String getEstadoTarea() {
		return estadoTarea;
	}
	public void setEstadoTarea(String estadoTarea) {
		this.estadoTarea = estadoTarea;
	}
	public List<TipoNotificacion> getListaTipoNotificacion() {
		return listaTipoNotificacion;
	}
	public void setListaTipoNotificacion(List<TipoNotificacion> listaTipoNotificacion) {
		this.listaTipoNotificacion = listaTipoNotificacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeNotificacion == null) ? 0 : codeNotificacion.hashCode());
		result = prime * result + ((idNotificacion == null) ? 0 : idNotificacion.hashCode());
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
		Notificacion other = (Notificacion) obj;
		if (codeNotificacion == null) {
			if (other.codeNotificacion != null)
				return false;
		} else if (!codeNotificacion.equals(other.codeNotificacion))
			return false;
		if (idNotificacion == null) {
			if (other.idNotificacion != null)
				return false;
		} else if (!idNotificacion.equals(other.idNotificacion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notificacion [idNotificacion=");
		builder.append(idNotificacion);
		builder.append(", codeNotificacion=");
		builder.append(codeNotificacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
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
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
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
		builder.append(", beanTuristaPublicaNoticia=");
		builder.append(beanTuristaPublicaNoticia);
		builder.append(", codeTuristaPublicaNoticia=");
		builder.append(codeTuristaPublicaNoticia);
		builder.append(", nombreTuristaPublicaNoticia=");
		builder.append(nombreTuristaPublicaNoticia);
		builder.append(", apellidoTuristaPublicaNoticia=");
		builder.append(apellidoTuristaPublicaNoticia);
		builder.append(", fotoPerfilTuristaPublicaNoticia=");
		builder.append(fotoPerfilTuristaPublicaNoticia);
		builder.append(", beanConquista=");
		builder.append(beanConquista);
		builder.append(", codeConquista=");
		builder.append(codeConquista);
		builder.append(", beanDestinoConquistadoDescubierto=");
		builder.append(beanDestinoConquistadoDescubierto);
		builder.append(", codeDestinoConquistadoDescubierto=");
		builder.append(codeDestinoConquistadoDescubierto);
		builder.append(", nombreDestinoNegocioConquistado=");
		builder.append(nombreDestinoNegocioConquistado);
		builder.append(", beanOferta=");
		builder.append(beanOferta);
		builder.append(", codeOferta=");
		builder.append(codeOferta);
		builder.append(", beanTuristaInteresEmpatia=");
		builder.append(beanTuristaInteresEmpatia);
		builder.append(", codeTuristaInteresEmpatia=");
		builder.append(codeTuristaInteresEmpatia);
		builder.append(", beanSugerenciaColonia=");
		builder.append(beanSugerenciaColonia);
		builder.append(", codeSugerenciaColonia=");
		builder.append(codeSugerenciaColonia);
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
		builder.append(", fechaGeneroNotificacion=");
		builder.append(fechaGeneroNotificacion);
		builder.append(", estadoTarea=");
		builder.append(estadoTarea);
		builder.append(", listaTipoNotificacion=");
		builder.append(listaTipoNotificacion);
		builder.append("]");
		return builder.toString();
	}
	
	
}
