package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable="true")
public class ComentarioNoticia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idComentarioNoticia;
	@Persistent
	private String codeComentarioNoticia;
	@Persistent
	private String comentario;			
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private Noticia beanNoticia;
	@Persistent
	private String codeNoticia;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaComenta;
	@Persistent
	private String codeTuristaComenta;	
	@Persistent
	private String nombreTuristaComenta;
	@Persistent
	private String ApellidoTuristaComenta;
	@Persistent
	private String fotoPerfilTuristaComenta;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private java.util.Date fechaComentario;
	@Persistent
	private java.util.Date fechaPublicacion;
	
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	public String getIdComentarioNoticia() {
		return idComentarioNoticia;
	}
	public void setIdComentarioNoticia(String idComentarioNoticia) {
		this.idComentarioNoticia=idComentarioNoticia;				
	}
	public String getCodeComentarioNoticia() {
		return codeComentarioNoticia;
	}
	public void setCodeComentarioNoticia(String codeComentarioNoticia) {
		this.codeComentarioNoticia = codeComentarioNoticia;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
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
	public UsuarioTurista getBeanTuristaComenta() {
		return beanTuristaComenta;
	}
	public void setBeanTuristaComenta(UsuarioTurista beanTuristaComenta) {
		this.beanTuristaComenta = beanTuristaComenta;
	}
	public String getCodeTuristaComenta() {
		return codeTuristaComenta;
	}
	public void setCodeTuristaComenta(String codeTuristaComenta) {
		this.codeTuristaComenta = codeTuristaComenta;
	}
	public String getNombreTuristaComenta() {
		return nombreTuristaComenta;
	}
	public void setNombreTuristaComenta(String nombreTuristaComenta) {
		this.nombreTuristaComenta = nombreTuristaComenta;
	}
	public String getApellidoTuristaComenta() {
		return ApellidoTuristaComenta;
	}
	public void setApellidoTuristaComenta(String apellidoTuristaComenta) {
		ApellidoTuristaComenta = apellidoTuristaComenta;
	}
	public String getFotoPerfilTuristaComenta() {
		return fotoPerfilTuristaComenta;
	}
	public void setFotoPerfilTuristaComenta(String fotoPerfilTuristaComenta) {
		this.fotoPerfilTuristaComenta = fotoPerfilTuristaComenta;
	}	
	
	public java.util.Date getFechaComentario() {
		return fechaComentario;
	}
	public void setFechaComentario(java.util.Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}	
	
	public java.util.Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(java.util.Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeComentarioNoticia == null) ? 0 : codeComentarioNoticia
						.hashCode());
		result = prime
				* result
				+ ((idComentarioNoticia == null) ? 0 : idComentarioNoticia
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
		ComentarioNoticia other = (ComentarioNoticia) obj;
		if (codeComentarioNoticia == null) {
			if (other.codeComentarioNoticia != null)
				return false;
		} else if (!codeComentarioNoticia.equals(other.codeComentarioNoticia))
			return false;
		if (idComentarioNoticia == null) {
			if (other.idComentarioNoticia != null)
				return false;
		} else if (!idComentarioNoticia.equals(other.idComentarioNoticia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComentarioNoticia [idComentarioNoticia=");
		builder.append(idComentarioNoticia);
		builder.append(", codeComentarioNoticia=");
		builder.append(codeComentarioNoticia);
		builder.append(", comentario=");
		builder.append(comentario);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNoticia=");
		builder.append(beanNoticia);
		builder.append(", codeNoticia=");
		builder.append(codeNoticia);
		builder.append(", beanTuristaComenta=");
		builder.append(beanTuristaComenta);
		builder.append(", codeTuristaComenta=");
		builder.append(codeTuristaComenta);
		builder.append(", nombreTuristaComenta=");
		builder.append(nombreTuristaComenta);
		builder.append(", ApellidoTuristaComenta=");
		builder.append(ApellidoTuristaComenta);
		builder.append(", fotoPerfilTuristaComenta=");
		builder.append(fotoPerfilTuristaComenta);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", fechaComentario=");
		builder.append(fechaComentario);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
