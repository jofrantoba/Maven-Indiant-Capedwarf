package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class UsuarioAdmin implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent   
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idUsuarioAdmin;
	@Persistent
	private String codeUsuarioAdmin ;
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@Persistent
	private String dni;
	@Persistent
	private String telefono;
	@Persistent
	private String correo;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private CuentaAdmin beanCuentaAdmin;
	@NotPersistent
	private String idSession;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdUsuarioAdmin() {
		return idUsuarioAdmin;
	}
	public void setIdUsuarioAdmin(String idUsuarioAdmin) {
		this.idUsuarioAdmin=idUsuarioAdmin;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	public CuentaAdmin getBeanCuentaAdmin() {
		return beanCuentaAdmin;
	}
	public void setBeanCuentaAdmin(CuentaAdmin beanCuentaAdmin) {
		this.beanCuentaAdmin = beanCuentaAdmin;
	}	
	
	public String getCodeUsuarioAdmin() {
		return codeUsuarioAdmin;
	}
	public void setCodeUsuarioAdmin(String codeUsuarioAdmin) {
		this.codeUsuarioAdmin = codeUsuarioAdmin;
	}
	public String getIdSession() {
		return idSession;
	}
	public void setIdSession(String idSession) {
		this.idSession = idSession;
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
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		UsuarioAdmin other = (UsuarioAdmin) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
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
		builder.append("UsuarioAdmin [idUsuarioAdmin=");
		builder.append(idUsuarioAdmin);
		builder.append(", codeUsuarioAdmin=");
		builder.append(codeUsuarioAdmin);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", dni=");
		builder.append(dni);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", correo=");
		builder.append(correo);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanCuentaAdmin=");
		builder.append(beanCuentaAdmin);
		builder.append(", idSession=");
		builder.append(idSession);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}