package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class EstadoAmistad implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEstadoAmistad;
	@Persistent
	private String codeEstadoAmistad;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdEstadoAmistad() {
		return idEstadoAmistad;
	}
	public void setIdEstadoAmistad(String idEstadoAmistad) {		
		this.idEstadoAmistad = idEstadoAmistad;
	}
	
	public String getCodeEstadoAmistad() {
		return codeEstadoAmistad;
	}
	public void setCodeEstadoAmistad(String codeEstadoAmistad) {
		this.codeEstadoAmistad = codeEstadoAmistad;
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
				+ ((codeEstadoAmistad == null) ? 0 : codeEstadoAmistad
						.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((idEstadoAmistad == null) ? 0 : idEstadoAmistad.hashCode());
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
		EstadoAmistad other = (EstadoAmistad) obj;
		if (codeEstadoAmistad == null) {
			if (other.codeEstadoAmistad != null)
				return false;
		} else if (!codeEstadoAmistad.equals(other.codeEstadoAmistad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idEstadoAmistad == null) {
			if (other.idEstadoAmistad != null)
				return false;
		} else if (!idEstadoAmistad.equals(other.idEstadoAmistad))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoAmistad [idEstadoAmistad=");
		builder.append(idEstadoAmistad);
		builder.append(", codeEstadoAmistad=");
		builder.append(codeEstadoAmistad);
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