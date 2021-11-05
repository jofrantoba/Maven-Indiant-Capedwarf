package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class TipoSuscripcion implements Serializable {
	public static final String LISTARTIPOSUSCRIPCIONACTIVA="LISTARTIPOSUSCRIPCIONACTIVA";

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTipoSuscripcion;
	@Persistent
	private String codeTipoSuscripcion;
	@Persistent
	private String descripcion;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdTipoSuscripcion() {
		return idTipoSuscripcion;
	}
	public void setIdTipoSuscripcion(String idTipoSuscripcion) {		
		this.idTipoSuscripcion = idTipoSuscripcion;		
	}	
	public String getCodeTipoSuscripcion() {
		return codeTipoSuscripcion;
	}
	public void setCodeTipoSuscripcion(String codeTipoSuscripcion) {
		this.codeTipoSuscripcion = codeTipoSuscripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
				+ ((codeTipoSuscripcion == null) ? 0 : codeTipoSuscripcion
						.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((idTipoSuscripcion == null) ? 0 : idTipoSuscripcion
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
		TipoSuscripcion other = (TipoSuscripcion) obj;
		if (codeTipoSuscripcion == null) {
			if (other.codeTipoSuscripcion != null)
				return false;
		} else if (!codeTipoSuscripcion.equals(other.codeTipoSuscripcion))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idTipoSuscripcion == null) {
			if (other.idTipoSuscripcion != null)
				return false;
		} else if (!idTipoSuscripcion.equals(other.idTipoSuscripcion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoSuscripcion [idTipoSuscripcion=");
		builder.append(idTipoSuscripcion);
		builder.append(", codeTipoSuscripcion=");
		builder.append(codeTipoSuscripcion);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", estado=");
		builder.append(estado);
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