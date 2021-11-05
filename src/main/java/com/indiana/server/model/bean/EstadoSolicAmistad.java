package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class EstadoSolicAmistad implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEstadoSolicAmistad;
	@Persistent
	private String codeEstadoSolicAmistad;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdEstadoSolicAmistad() {
		return idEstadoSolicAmistad;
	}
	public void setIdEstadoSolicAmistad(String idEstadoSolicAmistad) {		
		this.idEstadoSolicAmistad = idEstadoSolicAmistad;		
	}
	
	public String getCodeEstadoSolicAmistad() {
		return codeEstadoSolicAmistad;
	}
	public void setCodeEstadoSolicAmistad(String codeEstadoSolicAmistad) {
		this.codeEstadoSolicAmistad = codeEstadoSolicAmistad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
				+ ((codeEstadoSolicAmistad == null) ? 0
						: codeEstadoSolicAmistad.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((idEstadoSolicAmistad == null) ? 0 : idEstadoSolicAmistad
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
		EstadoSolicAmistad other = (EstadoSolicAmistad) obj;
		if (codeEstadoSolicAmistad == null) {
			if (other.codeEstadoSolicAmistad != null)
				return false;
		} else if (!codeEstadoSolicAmistad.equals(other.codeEstadoSolicAmistad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idEstadoSolicAmistad == null) {
			if (other.idEstadoSolicAmistad != null)
				return false;
		} else if (!idEstadoSolicAmistad.equals(other.idEstadoSolicAmistad))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoSolicAmistad [idEstadoSolicAmistad=");
		builder.append(idEstadoSolicAmistad);
		builder.append(", codeEstadoSolicAmistad=");
		builder.append(codeEstadoSolicAmistad);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}