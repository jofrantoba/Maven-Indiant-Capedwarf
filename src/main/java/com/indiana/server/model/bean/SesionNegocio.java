package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable="true")
public class SesionNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3561603036654280790L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idSesionNegocio;
	@Persistent
	private Date fechaInicioSesion;
	@Persistent
	private Date fechaCierreSesion;
	@Persistent
	@Unowned
	private CuentaNegocio beanCuentaNegocio;
	@Persistent
	@Unowned
	private RedSocial beanRedSocial;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	public String getIdSesionNegocio() {
		return idSesionNegocio;
	}
	public void setIdSesionNegocio(String idSesionNegocio) {
		this.idSesionNegocio = idSesionNegocio;
	}
	public Date getFechaInicioSesion() {
		return fechaInicioSesion;
	}
	public void setFechaInicioSesion(Date fechaInicioSesion) {
		this.fechaInicioSesion = fechaInicioSesion;
	}
	public Date getFechaCierreSesion() {
		return fechaCierreSesion;
	}
	public void setFechaCierreSesion(Date fechaCierreSesion) {
		this.fechaCierreSesion = fechaCierreSesion;
	}
	public CuentaNegocio getBeanCuentaNegocio() {
		return beanCuentaNegocio;
	}
	public void setBeanCuentaNegocio(CuentaNegocio beanCuentaNegocio) {
		this.beanCuentaNegocio = beanCuentaNegocio;
	}
	public RedSocial getBeanRedSocial() {
		return beanRedSocial;
	}
	public void setBeanRedSocial(RedSocial beanRedSocial) {
		this.beanRedSocial = beanRedSocial;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((beanCuentaNegocio == null) ? 0 : beanCuentaNegocio
						.hashCode());
		result = prime * result
				+ ((beanRedSocial == null) ? 0 : beanRedSocial.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime
				* result
				+ ((fechaCierreSesion == null) ? 0 : fechaCierreSesion
						.hashCode());
		result = prime
				* result
				+ ((fechaInicioSesion == null) ? 0 : fechaInicioSesion
						.hashCode());
		result = prime * result
				+ ((idSesionNegocio == null) ? 0 : idSesionNegocio.hashCode());
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
		SesionNegocio other = (SesionNegocio) obj;
		if (beanCuentaNegocio == null) {
			if (other.beanCuentaNegocio != null)
				return false;
		} else if (!beanCuentaNegocio.equals(other.beanCuentaNegocio))
			return false;
		if (beanRedSocial == null) {
			if (other.beanRedSocial != null)
				return false;
		} else if (!beanRedSocial.equals(other.beanRedSocial))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaCierreSesion == null) {
			if (other.fechaCierreSesion != null)
				return false;
		} else if (!fechaCierreSesion.equals(other.fechaCierreSesion))
			return false;
		if (fechaInicioSesion == null) {
			if (other.fechaInicioSesion != null)
				return false;
		} else if (!fechaInicioSesion.equals(other.fechaInicioSesion))
			return false;
		if (idSesionNegocio == null) {
			if (other.idSesionNegocio != null)
				return false;
		} else if (!idSesionNegocio.equals(other.idSesionNegocio))
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
		return "SesionNegocio [idSesionNegocio=" + idSesionNegocio
				+ ", fechaInicioSesion=" + fechaInicioSesion
				+ ", fechaCierreSesion=" + fechaCierreSesion
				+ ", beanCuentaNegocio=" + beanCuentaNegocio
				+ ", beanRedSocial=" + beanRedSocial + ", estado=" + estado
				+ ", version=" + version + "]";
	}
}
