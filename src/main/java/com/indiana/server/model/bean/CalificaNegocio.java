package com.indiana.server.model.bean;

import java.io.Serializable;


import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class CalificaNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCalificacionNegocio;
	@Persistent	
	private String codeCalificacionNegocio;		
	@Persistent	
	private java.util.Date fecha;			
	@Persistent	
	private Integer calificacion;			
	@Persistent	
	private String opinion;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent	
	@Unowned
	private UsuarioNegocio beanNegocio;
	@Persistent
	private String codeNegocio;
	@Persistent	
	@Unowned
	private UsuarioTurista beanTuristaCalifica;
	@Persistent
	private String codeTuristaCalifica;	
	@Persistent
	private String nombreTuristaCalifica;
	@Persistent
	private String apellidoTuristaCalifica;
	@Persistent
	private String fotoPerfilTuristaCalifica;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdCalificacionNegocio() {
		return idCalificacionNegocio;
	}
	public void setIdCalificacionNegocio(String idCalificacionNegocio) {
		this.idCalificacionNegocio=idCalificacionNegocio;				
	}
	public String getCodeCalificacionNegocio() {
		return codeCalificacionNegocio;
	}
	public void setCodeCalificacionNegocio(String codeCalificacionNegocio) {
		this.codeCalificacionNegocio = codeCalificacionNegocio;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public Integer getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	public UsuarioNegocio getBeanNegocio() {
		return beanNegocio;
	}
	public void setBeanNegocio(UsuarioNegocio beanNegocio) {
		this.beanNegocio = beanNegocio;
	}
	public String getCodeNegocio() {
		return codeNegocio;
	}
	public void setCodeNegocio(String codeNegocio) {
		this.codeNegocio = codeNegocio;
	}
	public UsuarioTurista getBeanTuristaCalifica() {
		return beanTuristaCalifica;
	}
	public void setBeanTuristaCalifica(UsuarioTurista beanTuristaCalifica) {
		this.beanTuristaCalifica = beanTuristaCalifica;
	}
	public String getCodeTuristaCalifica() {
		return codeTuristaCalifica;
	}
	public void setCodeTuristaCalifica(String codeTuristaCalifica) {
		this.codeTuristaCalifica = codeTuristaCalifica;
	}	
	public String getNombreTuristaCalifica() {
		return nombreTuristaCalifica;
	}
	public void setNombreTuristaCalifica(String nombreTuristaCalifica) {
		this.nombreTuristaCalifica = nombreTuristaCalifica;
	}
	public String getApellidoTuristaCalifica() {
		return apellidoTuristaCalifica;
	}
	public void setApellidoTuristaCalifica(String apellidoTuristaCalifica) {
		this.apellidoTuristaCalifica = apellidoTuristaCalifica;
	}
	public String getFotoPerfilTuristaCalifica() {
		return fotoPerfilTuristaCalifica;
	}
	public void setFotoPerfilTuristaCalifica(String fotoPerfilTuristaCalifica) {
		this.fotoPerfilTuristaCalifica = fotoPerfilTuristaCalifica;
	}
	
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeCalificacionNegocio == null) ? 0
						: codeCalificacionNegocio.hashCode());
		result = prime
				* result
				+ ((idCalificacionNegocio == null) ? 0 : idCalificacionNegocio
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
		CalificaNegocio other = (CalificaNegocio) obj;
		if (codeCalificacionNegocio == null) {
			if (other.codeCalificacionNegocio != null)
				return false;
		} else if (!codeCalificacionNegocio
				.equals(other.codeCalificacionNegocio))
			return false;
		if (idCalificacionNegocio == null) {
			if (other.idCalificacionNegocio != null)
				return false;
		} else if (!idCalificacionNegocio.equals(other.idCalificacionNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CalificaNegocio [idCalificacionNegocio=");
		builder.append(idCalificacionNegocio);
		builder.append(", codeCalificacionNegocio=");
		builder.append(codeCalificacionNegocio);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", calificacion=");
		builder.append(calificacion);
		builder.append(", opinion=");
		builder.append(opinion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNegocio=");
		builder.append(beanNegocio);
		builder.append(", codeNegocio=");
		builder.append(codeNegocio);
		builder.append(", beanTuristaCalifica=");
		builder.append(beanTuristaCalifica);
		builder.append(", codeTuristaCalifica=");
		builder.append(codeTuristaCalifica);
		builder.append(", nombreTuristaCalifica=");
		builder.append(nombreTuristaCalifica);
		builder.append(", apellidoTuristaCalifica=");
		builder.append(apellidoTuristaCalifica);
		builder.append(", fotoPerfilTuristaCalifica=");
		builder.append(fotoPerfilTuristaCalifica);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
