package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class EstadoCuenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEstadoCuenta;
	@Persistent
	private String codeEstadoCuenta;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;

	public String getIdEstadoCuenta() {
		return idEstadoCuenta;
	}

	public void setIdEstadoCuenta(String idEstadoCuenta) {		
		this.idEstadoCuenta = idEstadoCuenta;		
	}
   
	public String getCodeEstadoCuenta() {
		return codeEstadoCuenta;
	}

	public void setCodeEstadoCuenta(String codeEstadoCuenta) {
		this.codeEstadoCuenta = codeEstadoCuenta;
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
				+ ((codeEstadoCuenta == null) ? 0 : codeEstadoCuenta.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((idEstadoCuenta == null) ? 0 : idEstadoCuenta.hashCode());
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
		EstadoCuenta other = (EstadoCuenta) obj;
		if (codeEstadoCuenta == null) {
			if (other.codeEstadoCuenta != null)
				return false;
		} else if (!codeEstadoCuenta.equals(other.codeEstadoCuenta))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idEstadoCuenta == null) {
			if (other.idEstadoCuenta != null)
				return false;
		} else if (!idEstadoCuenta.equals(other.idEstadoCuenta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadoCuenta [idEstadoCuenta=" + idEstadoCuenta + ", codeEstadoCuenta=" + codeEstadoCuenta
				+ ", descripcion=" + descripcion + ", version=" + version + ", operacion=" + operacion
				+ ", isPersistente=" + isPersistente + "]";
	}

	
	
}