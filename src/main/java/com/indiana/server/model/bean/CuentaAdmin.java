package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class CuentaAdmin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent   
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCuentaAdmin;
	@Persistent
	private String correo;	
	@Persistent
	private String clave;
	@Persistent
	private java.util.Date fechaCreacion;
	@Persistent
	@Unowned
	private EstadoCuenta estadoCuenta;
	@Persistent
	private String codeEstadoCuenta;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String idUsuarioAdmin;
	@Persistent(mappedBy="beanCuentaAdmin")
	private UsuarioAdmin beanUsuarioAdmin;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdCuentaAdmin() {
		return idCuentaAdmin;
	}
	public void setIdCuentaAdmin(String idCuentaAdmin) {
		this.idCuentaAdmin=idCuentaAdmin;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.util.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	public UsuarioAdmin getBeanUsuarioAdmin() {
		return beanUsuarioAdmin;
	}
	public void setBeanUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) {
		this.beanUsuarioAdmin = beanUsuarioAdmin;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public String getIdUsuarioAdmin() {
		return idUsuarioAdmin;
	}
	public void setIdUsuarioAdmin(String idUsuarioAdmin) {
		this.idUsuarioAdmin = idUsuarioAdmin;
	}
	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}
	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}
	public String getCodeEstadoCuenta() {
		return codeEstadoCuenta;
	}
	public void setCodeEstadoCuenta(String codeEstadoCuenta) {
		this.codeEstadoCuenta = codeEstadoCuenta;
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
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result
				+ ((idCuentaAdmin == null) ? 0 : idCuentaAdmin.hashCode());
		result = prime * result
				+ ((idUsuarioAdmin == null) ? 0 : idUsuarioAdmin.hashCode());
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
		CuentaAdmin other = (CuentaAdmin) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (idCuentaAdmin == null) {
			if (other.idCuentaAdmin != null)
				return false;
		} else if (!idCuentaAdmin.equals(other.idCuentaAdmin))
			return false;
		if (idUsuarioAdmin == null) {
			if (other.idUsuarioAdmin != null)
				return false;
		} else if (!idUsuarioAdmin.equals(other.idUsuarioAdmin))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuentaAdmin [idCuentaAdmin=");
		builder.append(idCuentaAdmin);
		builder.append(", correo=");
		builder.append(correo);
		builder.append(", clave=");
		builder.append(clave);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", estadoCuenta=");
		builder.append(estadoCuenta);
		builder.append(", codeEstadoCuenta=");
		builder.append(codeEstadoCuenta);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", idUsuarioAdmin=");
		builder.append(idUsuarioAdmin);
		builder.append(", beanUsuarioAdmin=");
		builder.append(beanUsuarioAdmin);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}