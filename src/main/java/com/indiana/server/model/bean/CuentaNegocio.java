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
public class CuentaNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true")
	private String idCuentaNegocio;
	@Persistent
	private String codeCuentaNegocio;
	@Persistent
	private String correoNegocio;
	@Persistent
	private String correoCuenta;
	@Persistent
	private String clave;
	@Persistent
	private Date fechaCreacion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private EstadoCuenta beanEstadoCuenta;
	@Persistent
	private String codeEstadoCuenta;	
	@Persistent(mappedBy="beanCuentaNegocio")
	private UsuarioNegocio beanUsuarioNegocio;
	@Persistent
	private String codeUsuarioNegocio;	
	@Persistent
	@Unowned
	private SolicitudCuentaNegocio beanSolicitudCuentaNegocio;
	@Persistent
	private String codeSolicitudCuentaNegocio;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdCuentaNegocio() {
		return idCuentaNegocio;
	}
	public void setIdCuentaNegocio(String idCuentaNegocio) {
		this.idCuentaNegocio=idCuentaNegocio;
	}
	public String getCodeCuentaNegocio() {
		return codeCuentaNegocio;
	}
	public void setCodeCuentaNegocio(String codeCuentaNegocio) {
		this.codeCuentaNegocio = codeCuentaNegocio;
	}
	public String getCorreoNegocio() {
		return correoNegocio;
	}
	public void setCorreoNegocio(String correoNegocio) {
		this.correoNegocio = correoNegocio;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
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
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public EstadoCuenta getBeanEstadoCuenta() {
		return beanEstadoCuenta;
	}
	public void setBeanEstadoCuenta(EstadoCuenta beanEstadoCuenta) {
		this.beanEstadoCuenta = beanEstadoCuenta;
	}	
	public SolicitudCuentaNegocio getBeanSolicitudCuentaNegocio() {
		return beanSolicitudCuentaNegocio;
	}
	public void setBeanSolicitudCuentaNegocio(
			SolicitudCuentaNegocio beanSolicitudCuentaNegocio) {
		this.beanSolicitudCuentaNegocio = beanSolicitudCuentaNegocio;
	}
	public String getCodeSolicitudCuentaNegocio() {
		return codeSolicitudCuentaNegocio;
	}
	public void setCodeSolicitudCuentaNegocio(String codeSolicitudCuentaNegocio) {
		this.codeSolicitudCuentaNegocio = codeSolicitudCuentaNegocio;
	}
	public String getCodeEstadoCuenta() {
		return codeEstadoCuenta;
	}
	public void setCodeEstadoCuenta(String codeEstadoCuenta) {
		this.codeEstadoCuenta = codeEstadoCuenta;
	}	
	public UsuarioNegocio getBeanUsuarioNegocio() {
		return beanUsuarioNegocio;
	}
	public void setBeanUsuarioNegocio(UsuarioNegocio beanUsuarioNegocio) {
		this.beanUsuarioNegocio = beanUsuarioNegocio;
	}
	
	public String getCorreoCuenta() {
		return correoCuenta;
	}
	public void setCorreoCuenta(String correoCuenta) {
		this.correoCuenta = correoCuenta;
	}
	public String getCodeUsuarioNegocio() {
		return codeUsuarioNegocio;
	}
	public void setCodeUsuarioNegocio(String codeUsuarioNegocio) {
		this.codeUsuarioNegocio = codeUsuarioNegocio;
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
				+ ((codeCuentaNegocio == null) ? 0 : codeCuentaNegocio
						.hashCode());
		result = prime * result
				+ ((idCuentaNegocio == null) ? 0 : idCuentaNegocio.hashCode());
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
		CuentaNegocio other = (CuentaNegocio) obj;
		if (codeCuentaNegocio == null) {
			if (other.codeCuentaNegocio != null)
				return false;
		} else if (!codeCuentaNegocio.equals(other.codeCuentaNegocio))
			return false;
		if (idCuentaNegocio == null) {
			if (other.idCuentaNegocio != null)
				return false;
		} else if (!idCuentaNegocio.equals(other.idCuentaNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuentaNegocio [idCuentaNegocio=");
		builder.append(idCuentaNegocio);
		builder.append(", codeCuentaNegocio=");
		builder.append(codeCuentaNegocio);
		builder.append(", correoNegocio=");
		builder.append(correoNegocio);
		builder.append(", correoCuenta=");
		builder.append(correoCuenta);
		builder.append(", clave=");
		builder.append(clave);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanEstadoCuenta=");
		builder.append(beanEstadoCuenta);
		builder.append(", codeEstadoCuenta=");
		builder.append(codeEstadoCuenta);
		builder.append(", beanUsuarioNegocio=");
		builder.append(beanUsuarioNegocio);
		builder.append(", codeUsuarioNegocio=");
		builder.append(codeUsuarioNegocio);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
