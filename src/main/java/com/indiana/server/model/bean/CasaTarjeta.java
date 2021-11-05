package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class CasaTarjeta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCasaTarjeta;
	@Persistent
	private String codeCasaTarjeta;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdCasaTarjeta() {
		return idCasaTarjeta;
	}
	public void setIdCasaTarjeta(String idCasaTarjeta) {
		this.idCasaTarjeta = idCasaTarjeta;
	}
	public String getCodeCasaTarjeta() {
		return codeCasaTarjeta;
	}
	public void setCodeCasaTarjeta(String codeCasaTarjeta) {
		this.codeCasaTarjeta = codeCasaTarjeta;
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
		result = prime * result
				+ ((codeCasaTarjeta == null) ? 0 : codeCasaTarjeta.hashCode());
		result = prime * result
				+ ((idCasaTarjeta == null) ? 0 : idCasaTarjeta.hashCode());
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
		CasaTarjeta other = (CasaTarjeta) obj;
		if (codeCasaTarjeta == null) {
			if (other.codeCasaTarjeta != null)
				return false;
		} else if (!codeCasaTarjeta.equals(other.codeCasaTarjeta))
			return false;
		if (idCasaTarjeta == null) {
			if (other.idCasaTarjeta != null)
				return false;
		} else if (!idCasaTarjeta.equals(other.idCasaTarjeta))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CasaTarjeta [idCasaTarjeta=");
		builder.append(idCasaTarjeta);
		builder.append(", codeCasaTarjeta=");
		builder.append(codeCasaTarjeta);
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
