package com.indiana.server.model.bean;

import java.io.Serializable;


import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class SesionTurista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idSesionTurista;
	@Persistent
	private String codeSesionTurista;		
	@Persistent
	private java.util.Date fechaInicioSession;
	@Persistent
	private java.util.Date fechaCierreSession;
	@Persistent
	private String estadoSesion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTurista;
	@Persistent
	private String correoTurista;
	@NotPersistent
	private String claveTurista;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	@Unowned
	private AutenticaOauth beanAutenticaOauth;
	@Persistent
	private String codeAutenticaOauth;
	@NotPersistent
	private String cuentaCreadaPor;
	
	public String getIdSesionTurista() {
		return idSesionTurista;
	}
	public void setIdSesionTurista(String idSesionTurista) {
		this.idSesionTurista = idSesionTurista;
	}
	public String getCodeSesionTurista() {
		return codeSesionTurista;
	}
	public void setCodeSesionTurista(String codeSesionTurista) {
		this.codeSesionTurista = codeSesionTurista;
	}
	public java.util.Date getFechaInicioSession() {
		return fechaInicioSession;
	}
	public void setFechaInicioSession(java.util.Date fechaInicioSession) {
		this.fechaInicioSession = fechaInicioSession;
	}
	public java.util.Date getFechaCierreSession() {
		return fechaCierreSession;
	}
	public void setFechaCierreSession(java.util.Date fechaCierreSession) {
		this.fechaCierreSession = fechaCierreSession;
	}
	public String getEstadoSesion() {
		return estadoSesion;
	}
	public void setEstadoSesion(String estadoSesion) {
		this.estadoSesion = estadoSesion;
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
	public UsuarioTurista getBeanTurista() {
		return beanTurista;
	}
	public void setBeanTurista(UsuarioTurista beanTurista) {
		this.beanTurista = beanTurista;
	}
	public String getCorreoTurista() {
		return correoTurista;
	}
	public void setCorreoTurista(String correoTurista) {
		this.correoTurista = correoTurista;
	}
	public String getClaveTurista() {
		return claveTurista;
	}
	public void setClaveTurista(String claveTurista) {
		this.claveTurista = claveTurista;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	
	public AutenticaOauth getBeanAutenticaOauth() {
		return beanAutenticaOauth;
	}
	public void setBeanAutenticaOauth(AutenticaOauth beanAutenticaOauth) {
		this.beanAutenticaOauth = beanAutenticaOauth;
	}
	public String getCodeAutenticaOauth() {
		return codeAutenticaOauth;
	}
	public void setCodeAutenticaOauth(String codeAutenticaOauth) {
		this.codeAutenticaOauth = codeAutenticaOauth;
	}		
	
	public String getCuentaCreadaPor() {
		return cuentaCreadaPor;
	}
	public void setCuentaCreadaPor(String cuentaCreadaPor) {
		this.cuentaCreadaPor = cuentaCreadaPor;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SesionTurista [idSesionTurista=");
		builder.append(idSesionTurista);
		builder.append(", codeSesionTurista=");
		builder.append(codeSesionTurista);
		builder.append(", fechaInicioSession=");
		builder.append(fechaInicioSession);
		builder.append(", fechaCierreSession=");
		builder.append(fechaCierreSession);
		builder.append(", estadoSesion=");
		builder.append(estadoSesion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", correoTurista=");
		builder.append(correoTurista);
		builder.append(", claveTurista=");
		builder.append(claveTurista);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", beanAutenticaOauth=");
		builder.append(beanAutenticaOauth);
		builder.append(", codeAutenticaOauth=");
		builder.append(codeAutenticaOauth);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeSesionTurista == null) ? 0 : codeSesionTurista.hashCode());
		result = prime * result + ((idSesionTurista == null) ? 0 : idSesionTurista.hashCode());
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
		SesionTurista other = (SesionTurista) obj;
		if (codeSesionTurista == null) {
			if (other.codeSesionTurista != null)
				return false;
		} else if (!codeSesionTurista.equals(other.codeSesionTurista))
			return false;
		if (idSesionTurista == null) {
			if (other.idSesionTurista != null)
				return false;
		} else if (!idSesionTurista.equals(other.idSesionTurista))
			return false;
		return true;
	}
	
			
					
}
