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
public class CuentaTurista implements Serializable {

	private static final long serialVersionUID = -3284528998930688911L;
	
	@PrimaryKey	
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCuentaTurista;		
	@Persistent
	private String correoTurista;		
	@Persistent
	private String clave;		
	@Persistent
	private Date fechaCreacion;
	@Persistent
	private Long version;
	@Persistent
	private String tipo;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private EstadoCuenta beanEstadoCuenta;
	@Persistent
	private String codeEstadoCuenta;		
	@Persistent(mappedBy="beanCuentaTurista")
	private UsuarioTurista beanUsuarioTurista;
	@Persistent
	private String codeUsuarioTurista;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String tokenFirebase;	
	
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	public String getIdCuentaTurista() {
		return idCuentaTurista;
	}
	public void setIdCuentaTurista(String idCuentaTurista) {
		this.idCuentaTurista=idCuentaTurista;
	}
	public String getCorreoTurista() {
		return correoTurista;
	}
	public void setCorreoTurista(String correoTurista) {
		this.correoTurista = correoTurista;
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
	public String getCodeEstadoCuenta() {
		return codeEstadoCuenta;
	}
	public void setCodeEstadoCuenta(String codeEstadoCuenta) {
		this.codeEstadoCuenta = codeEstadoCuenta;
	}
	public UsuarioTurista getBeanUsuarioTurista() {
		return beanUsuarioTurista;
	}
	public void setBeanUsuarioTurista(UsuarioTurista beanUsuarioTurista) {
		this.beanUsuarioTurista = beanUsuarioTurista;
	}
	public String getCodeUsuarioTurista() {
		return codeUsuarioTurista;
	}
	public void setCodeUsuarioTurista(String codeUsuarioTurista) {
		this.codeUsuarioTurista = codeUsuarioTurista;
	}	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
				+ ((codeUsuarioTurista == null) ? 0 : codeUsuarioTurista
						.hashCode());
		result = prime * result
				+ ((correoTurista == null) ? 0 : correoTurista.hashCode());
		result = prime * result
				+ ((idCuentaTurista == null) ? 0 : idCuentaTurista.hashCode());
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
		CuentaTurista other = (CuentaTurista) obj;
		if (codeUsuarioTurista == null) {
			if (other.codeUsuarioTurista != null)
				return false;
		} else if (!codeUsuarioTurista.equals(other.codeUsuarioTurista))
			return false;
		if (correoTurista == null) {
			if (other.correoTurista != null)
				return false;
		} else if (!correoTurista.equals(other.correoTurista))
			return false;
		if (idCuentaTurista == null) {
			if (other.idCuentaTurista != null)
				return false;
		} else if (!idCuentaTurista.equals(other.idCuentaTurista))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuentaTurista [idCuentaTurista=");
		builder.append(idCuentaTurista);
		builder.append(", correoTurista=");
		builder.append(correoTurista);
		builder.append(", clave=");
		builder.append(clave);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanEstadoCuenta=");
		builder.append(beanEstadoCuenta);
		builder.append(", codeEstadoCuenta=");
		builder.append(codeEstadoCuenta);
		builder.append(", beanUsuarioTurista=");
		builder.append(beanUsuarioTurista);
		builder.append(", codeUsuarioTurista=");
		builder.append(codeUsuarioTurista);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
}
