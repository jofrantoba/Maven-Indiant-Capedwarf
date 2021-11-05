package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class EstadoMiembro implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEstadoMiembro;
	@Persistent
	private String codeEstadoMiembro;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdEstadoMiembro() {
		return idEstadoMiembro;
	}
	public void setIdEstadoMiembro(String idEstadoMiembro) {	
		this.idEstadoMiembro = idEstadoMiembro;
	}
	
	public String getCodeEstadoMiembro() {
		return codeEstadoMiembro;
	}
	public void setCodeEstadoMiembro(String codeEstadoMiembro) {
		this.codeEstadoMiembro = codeEstadoMiembro;
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
				+ ((codeEstadoMiembro == null) ? 0 : codeEstadoMiembro
						.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((idEstadoMiembro == null) ? 0 : idEstadoMiembro.hashCode());
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
		EstadoMiembro other = (EstadoMiembro) obj;
		if (codeEstadoMiembro == null) {
			if (other.codeEstadoMiembro != null)
				return false;
		} else if (!codeEstadoMiembro.equals(other.codeEstadoMiembro))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idEstadoMiembro == null) {
			if (other.idEstadoMiembro != null)
				return false;
		} else if (!idEstadoMiembro.equals(other.idEstadoMiembro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoMiembro [idEstadoMiembro=");
		builder.append(idEstadoMiembro);
		builder.append(", codeEstadoMiembro=");
		builder.append(codeEstadoMiembro);
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