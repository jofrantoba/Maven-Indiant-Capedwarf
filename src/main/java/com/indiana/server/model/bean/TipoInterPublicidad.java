package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class TipoInterPublicidad implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTipoInterPublicidad;
	@Persistent
	private String codeTipoInterPublicidad;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdTipoInterPublicidad() {
		return idTipoInterPublicidad;
	}
	public void setIdTipoInterPublicidad(String idTipoInterPublicidad) {
		this.idTipoInterPublicidad = idTipoInterPublicidad;
	}
	public String getCodeTipoInterPublicidad() {
		return codeTipoInterPublicidad;
	}
	public void setCodeTipoInterPublicidad(String codeTipoInterPublicidad) {
		this.codeTipoInterPublicidad = codeTipoInterPublicidad;
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
				+ ((codeTipoInterPublicidad == null) ? 0
						: codeTipoInterPublicidad.hashCode());
		result = prime
				* result
				+ ((idTipoInterPublicidad == null) ? 0 : idTipoInterPublicidad
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
		TipoInterPublicidad other = (TipoInterPublicidad) obj;
		if (codeTipoInterPublicidad == null) {
			if (other.codeTipoInterPublicidad != null)
				return false;
		} else if (!codeTipoInterPublicidad
				.equals(other.codeTipoInterPublicidad))
			return false;
		if (idTipoInterPublicidad == null) {
			if (other.idTipoInterPublicidad != null)
				return false;
		} else if (!idTipoInterPublicidad.equals(other.idTipoInterPublicidad))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoInterPublicidad [idTipoInterPublicidad=");
		builder.append(idTipoInterPublicidad);
		builder.append(", codeTipoInterPublicidad=");
		builder.append(codeTipoInterPublicidad);
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
