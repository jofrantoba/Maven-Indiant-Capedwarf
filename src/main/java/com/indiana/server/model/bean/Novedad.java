package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Novedad implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idNovedad;
	@Persistent
	private String codeNovedad;
	@Persistent
	private String tipoNovedad;	
	@Persistent
	private String descripcion;		
	@Persistent
	private Date fechaPublicacion;
	@Persistent
	private String enlace;
	@Persistent
	private String hiperLink;
	@Persistent
	private String fotoPublicidad;			
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	@Unowned
	private Miembro beanMiembroGeneraNovedad;
	@Persistent
	private String codeMiembroGeneraNovedad;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaGeneraNovedad;
	@Persistent
	private String codeTuristaGeneraNovedad;
	@Persistent
	private String nombreTuristaNegocioGeneraNovedad;
	@Persistent
	private String apellidoTuristaGeneraNovedad;
	@Persistent
	private String fotoPerfilTuristaNegocioGeneraNovedad;
	@Persistent 
	@Unowned
	private UsuarioNegocio beanNegocioGeneraNovedad;
	@Persistent
	private String codeNegocioGeneraNovedad;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	@Unowned
	private NoticiaCompartida beanNoticiaCompartida;
	@Persistent
	private String codeNoticiaCompatida;
	@Persistent
	@Unowned
	private Noticia beanNoticiaOriginal;
	@Persistent
	private String codeNoticiaOriginal;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String codeDestino;
	@Persistent
	private String nombreDestinoNegocio;
	
	
	public String getHiperLink() {
		return hiperLink;
	}
	public void setHiperLink(String hiperLink) {
		this.hiperLink = hiperLink;
	}
	public String getIdNovedad() {
		return idNovedad;
	}
	public void setIdNovedad(String idNovedad) {
		this.idNovedad=idNovedad;				
	}
	public String getCodeNovedad() {
		return codeNovedad;
	}
	public void setCodeNovedad(String codeNovedad) {
		this.codeNovedad = codeNovedad;
	}
	public String getTipoNovedad() {
		return tipoNovedad;
	}
	public void setTipoNovedad(String tipoNovedad) {
		this.tipoNovedad = tipoNovedad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public String getFotoPublicidad() {
		return fotoPublicidad;
	}
	public void setFotoPublicidad(String fotoPublicidad) {
		this.fotoPublicidad = fotoPublicidad;
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
	public Miembro getBeanMiembroGeneraNovedad() {
		return beanMiembroGeneraNovedad;
	}
	public void setBeanMiembroGeneraNovedad(Miembro beanMiembroGeneraNovedad) {
		this.beanMiembroGeneraNovedad = beanMiembroGeneraNovedad;
	}
	public String getCodeMiembroGeneraNovedad() {
		return codeMiembroGeneraNovedad;
	}
	public void setCodeMiembroGeneraNovedad(String codeMiembroGeneraNovedad) {
		this.codeMiembroGeneraNovedad = codeMiembroGeneraNovedad;
	}
	public UsuarioTurista getBeanTuristaGeneraNovedad() {
		return beanTuristaGeneraNovedad;
	}
	public void setBeanTuristaGeneraNovedad(UsuarioTurista beanTuristaGeneraNovedad) {
		this.beanTuristaGeneraNovedad = beanTuristaGeneraNovedad;
	}
	public String getCodeTuristaGeneraNovedad() {
		return codeTuristaGeneraNovedad;
	}
	public void setCodeTuristaGeneraNovedad(String codeTuristaGeneraNovedad) {
		this.codeTuristaGeneraNovedad = codeTuristaGeneraNovedad;
	}
	public String getNombreTuristaNegocioGeneraNovedad() {
		return nombreTuristaNegocioGeneraNovedad;
	}
	public void setNombreTuristaNegocioGeneraNovedad(
			String nombreTuristaNegocioGeneraNovedad) {
		this.nombreTuristaNegocioGeneraNovedad = nombreTuristaNegocioGeneraNovedad;
	}
	public String getApellidoTuristaGeneraNovedad() {
		return apellidoTuristaGeneraNovedad;
	}
	public void setApellidoTuristaGeneraNovedad(String apellidoTuristaGeneraNovedad) {
		this.apellidoTuristaGeneraNovedad = apellidoTuristaGeneraNovedad;
	}
	public String getFotoPerfilTuristaNegocioGeneraNovedad() {
		return fotoPerfilTuristaNegocioGeneraNovedad;
	}
	public void setFotoPerfilTuristaNegocioGeneraNovedad(
			String fotoPerfilTuristaNegocioGeneraNovedad) {
		this.fotoPerfilTuristaNegocioGeneraNovedad = fotoPerfilTuristaNegocioGeneraNovedad;
	}
	public UsuarioNegocio getBeanNegocioGeneraNovedad() {
		return beanNegocioGeneraNovedad;
	}
	public void setBeanNegocioGeneraNovedad(UsuarioNegocio beanNegocioGeneraNovedad) {
		this.beanNegocioGeneraNovedad = beanNegocioGeneraNovedad;
	}
	public String getCodeNegocioGeneraNovedad() {
		return codeNegocioGeneraNovedad;
	}
	public void setCodeNegocioGeneraNovedad(String codeNegocioGeneraNovedad) {
		this.codeNegocioGeneraNovedad = codeNegocioGeneraNovedad;
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
	public NoticiaCompartida getBeanNoticiaCompartida() {
		return beanNoticiaCompartida;
	}
	public void setBeanNoticiaCompartida(NoticiaCompartida beanNoticiaCompartida) {
		this.beanNoticiaCompartida = beanNoticiaCompartida;
	}
	public String getCodeNoticiaCompatida() {
		return codeNoticiaCompatida;
	}
	public void setCodeNoticiaCompatida(String codeNoticiaCompatida) {
		this.codeNoticiaCompatida = codeNoticiaCompatida;
	}
	public Noticia getBeanNoticiaOriginal() {
		return beanNoticiaOriginal;
	}
	public void setBeanNoticiaOriginal(Noticia beanNoticiaOriginal) {
		this.beanNoticiaOriginal = beanNoticiaOriginal;
	}
	public String getCodeNoticiaOriginal() {
		return codeNoticiaOriginal;
	}
	public void setCodeNoticiaOriginal(String codeNoticiaOriginal) {
		this.codeNoticiaOriginal = codeNoticiaOriginal;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}	
	public String getCodeDestino() {
		return codeDestino;
	}
	public void setCodeDestino(String codeDestino) {
		this.codeDestino = codeDestino;
	}
	public String getNombreDestinoNegocio() {
		return nombreDestinoNegocio;
	}
	public void setNombreDestinoNegocio(String nombreDestinoNegocio) {
		this.nombreDestinoNegocio = nombreDestinoNegocio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeNovedad == null) ? 0 : codeNovedad.hashCode());
		result = prime * result
				+ ((idNovedad == null) ? 0 : idNovedad.hashCode());
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
		Novedad other = (Novedad) obj;
		if (codeNovedad == null) {
			if (other.codeNovedad != null)
				return false;
		} else if (!codeNovedad.equals(other.codeNovedad))
			return false;
		if (idNovedad == null) {
			if (other.idNovedad != null)
				return false;
		} else if (!idNovedad.equals(other.idNovedad))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Novedad [idNovedad=");
		builder.append(idNovedad);
		builder.append(", codeNovedad=");
		builder.append(codeNovedad);
		builder.append(", tipoNovedad=");
		builder.append(tipoNovedad);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fechaPublicacion=");
		builder.append(fechaPublicacion);
		builder.append(", enlace=");
		builder.append(enlace);
		builder.append(", fotoPublicidad=");
		builder.append(fotoPublicidad);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanMiembroGeneraNovedad=");
		builder.append(beanMiembroGeneraNovedad);
		builder.append(", codeMiembroGeneraNovedad=");
		builder.append(codeMiembroGeneraNovedad);
		builder.append(", beanTuristaGeneraNovedad=");
		builder.append(beanTuristaGeneraNovedad);
		builder.append(", codeTuristaGeneraNovedad=");
		builder.append(codeTuristaGeneraNovedad);
		builder.append(", nombreTuristaNegocioGeneraNovedad=");
		builder.append(nombreTuristaNegocioGeneraNovedad);
		builder.append(", apellidoTuristaGeneraNovedad=");
		builder.append(apellidoTuristaGeneraNovedad);
		builder.append(", fotoPerfilTuristaNegocioGeneraNovedad=");
		builder.append(fotoPerfilTuristaNegocioGeneraNovedad);
		builder.append(", beanNegocioGeneraNovedad=");
		builder.append(beanNegocioGeneraNovedad);
		builder.append(", codeNegocioGeneraNovedad=");
		builder.append(codeNegocioGeneraNovedad);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", beanNoticiaCompartida=");
		builder.append(beanNoticiaCompartida);
		builder.append(", codeNoticiaCompatida=");
		builder.append(codeNoticiaCompatida);
		builder.append(", beanNoticiaOriginal=");
		builder.append(beanNoticiaOriginal);
		builder.append(", codeNoticiaOriginal=");
		builder.append(codeNoticiaOriginal);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
