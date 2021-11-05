package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class SolicitudCuentaNegocio implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idSolicitudCuentaNegocio;
	@Persistent
	private String codeSolicitudCuentaNegocio;
	@Persistent
	private String correoPersonal;
	@Persistent
	private String correoCorporativo;
	@Persistent
	private String puestoEmpresa;
	@Persistent
	private String dni;
	@Persistent
	private String telefono;
	@Persistent
	private String estado;
	@Persistent
	private String clave;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	public String getIdSolicitudCuentaNegocio() {
		return idSolicitudCuentaNegocio;
	}
	public void setIdSolicitudCuentaNegocio(String idSolicitudCuentaNegocio) {
		this.idSolicitudCuentaNegocio = idSolicitudCuentaNegocio;
	}
	public String getCodeSolicitudCuentaNegocio() {
		return codeSolicitudCuentaNegocio;
	}
	public void setCodeSolicitudCuentaNegocio(String codeSolicitudCuentaNegocio) {
		this.codeSolicitudCuentaNegocio = codeSolicitudCuentaNegocio;
	}
	public String getCorreoPersonal() {
		return correoPersonal;
	}
	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}
	public String getCorreoCorporativo() {
		return correoCorporativo;
	}
	public void setCorreoCorporativo(String correoCorporativo) {
		this.correoCorporativo = correoCorporativo;
	}
	public String getPuestoEmpresa() {
		return puestoEmpresa;
	}
	public void setPuestoEmpresa(String puestoEmpresa) {
		this.puestoEmpresa = puestoEmpresa;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
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
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime
				* result
				+ ((codeSolicitudCuentaNegocio == null) ? 0
						: codeSolicitudCuentaNegocio.hashCode());
		result = prime
				* result
				+ ((correoCorporativo == null) ? 0 : correoCorporativo
						.hashCode());
		result = prime * result
				+ ((correoPersonal == null) ? 0 : correoPersonal.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime
				* result
				+ ((idSolicitudCuentaNegocio == null) ? 0
						: idSolicitudCuentaNegocio.hashCode());
		result = prime * result
				+ ((isPersistente == null) ? 0 : isPersistente.hashCode());
		result = prime * result
				+ ((puestoEmpresa == null) ? 0 : puestoEmpresa.hashCode());
		result = prime * result
				+ ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		SolicitudCuentaNegocio other = (SolicitudCuentaNegocio) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (codeSolicitudCuentaNegocio == null) {
			if (other.codeSolicitudCuentaNegocio != null)
				return false;
		} else if (!codeSolicitudCuentaNegocio
				.equals(other.codeSolicitudCuentaNegocio))
			return false;
		if (correoCorporativo == null) {
			if (other.correoCorporativo != null)
				return false;
		} else if (!correoCorporativo.equals(other.correoCorporativo))
			return false;
		if (correoPersonal == null) {
			if (other.correoPersonal != null)
				return false;
		} else if (!correoPersonal.equals(other.correoPersonal))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idSolicitudCuentaNegocio == null) {
			if (other.idSolicitudCuentaNegocio != null)
				return false;
		} else if (!idSolicitudCuentaNegocio
				.equals(other.idSolicitudCuentaNegocio))
			return false;
		if (isPersistente == null) {
			if (other.isPersistente != null)
				return false;
		} else if (!isPersistente.equals(other.isPersistente))
			return false;
		if (puestoEmpresa == null) {
			if (other.puestoEmpresa != null)
				return false;
		} else if (!puestoEmpresa.equals(other.puestoEmpresa))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SolicitudCuentaNegocio [idSolicitudCuentaNegocio="
				+ idSolicitudCuentaNegocio + ", codeSolicitudCuentaNegocio="
				+ codeSolicitudCuentaNegocio + ", correoPersonal="
				+ correoPersonal + ", correoCorporativo=" + correoCorporativo
				+ ", puestoEmpresa=" + puestoEmpresa + ", dni=" + dni
				+ ", telefono=" + telefono + ", estado=" + estado + ", clave="
				+ clave + ", version=" + version + ", isPersistente="
				+ isPersistente + "]";
	}
	
}