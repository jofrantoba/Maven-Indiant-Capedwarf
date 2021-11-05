package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class CalificaDestino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCalificacionDestino;
	@Persistent	
	private String codeCalificacionDestino;			
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
	private Destino beanDestino;
	@Persistent	
	private String codeDestino;
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
	
	public String getIdCalificacionDestino() {
		return idCalificacionDestino;
	}
	public void setIdCalificacionDestino(String idCalificacionDestino) {
		this.idCalificacionDestino=idCalificacionDestino;			
	}
	public String getCodeCalificacionDestino() {
		return codeCalificacionDestino;
	}
	public void setCodeCalificacionDestino(String codeCalificacionDestino) {
		this.codeCalificacionDestino = codeCalificacionDestino;
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
				+ ((codeCalificacionDestino == null) ? 0
						: codeCalificacionDestino.hashCode());
		result = prime
				* result
				+ ((idCalificacionDestino == null) ? 0 : idCalificacionDestino
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
		CalificaDestino other = (CalificaDestino) obj;
		if (codeCalificacionDestino == null) {
			if (other.codeCalificacionDestino != null)
				return false;
		} else if (!codeCalificacionDestino
				.equals(other.codeCalificacionDestino))
			return false;
		if (idCalificacionDestino == null) {
			if (other.idCalificacionDestino != null)
				return false;
		} else if (!idCalificacionDestino.equals(other.idCalificacionDestino))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CalificaDestino [idCalificacionDestino=");
		builder.append(idCalificacionDestino);
		builder.append(", codeCalificacionDestino=");
		builder.append(codeCalificacionDestino);
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
		builder.append(", beanDestino=");
		builder.append(beanDestino);
		builder.append(", codeDestino=");
		builder.append(codeDestino);
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
