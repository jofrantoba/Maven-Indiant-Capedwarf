package com.indiana.server.model.bean;

import java.io.Serializable;


import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class AutenticaOauth implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idAutenticaOauth;
	@Persistent	
	private String codeAutenticaOauth;		
	@Persistent	
	private String token;
	@Persistent	
	private java.util.Date fecha;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent	
	@Unowned
	private RedSocial beanRedSocial;
	@Persistent
	private String codeRedSocial;
	@Persistent	
	@Unowned
	private CuentaTurista beanCuentaTurista;
	@Persistent
	private String codeCuentaTurista;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String tokenFirebase;
	
	
	public String getIdAutenticaOauth() {
		return idAutenticaOauth;
	}
	public String getCodeAutenticaOauth() {
		return codeAutenticaOauth;
	}
	public String getToken() {
		return token;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public Long getVersion() {
		return version;
	}
	public String getOperacion() {
		return operacion;
	}
	public RedSocial getBeanRedSocial() {
		return beanRedSocial;
	}
	public String getCodeRedSocial() {
		return codeRedSocial;
	}
	public CuentaTurista getBeanCuentaTurista() {
		return beanCuentaTurista;
	}
	public String getCodeCuentaTurista() {
		return codeCuentaTurista;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIdAutenticaOauth(String idAutenticaOauth) {
		this.idAutenticaOauth = idAutenticaOauth;
	}
	public void setCodeAutenticaOauth(String codeAutenticaOauth) {
		this.codeAutenticaOauth = codeAutenticaOauth;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public void setBeanRedSocial(RedSocial beanRedSocial) {
		this.beanRedSocial = beanRedSocial;
	}
	public void setCodeRedSocial(String codeRedSocial) {
		this.codeRedSocial = codeRedSocial;
	}
	public void setBeanCuentaTurista(CuentaTurista beanCuentaTurista) {
		this.beanCuentaTurista = beanCuentaTurista;
	}
	public void setCodeCuentaTurista(String codeCuentaTurista) {
		this.codeCuentaTurista = codeCuentaTurista;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeAutenticaOauth == null) ? 0 : codeAutenticaOauth
						.hashCode());
		result = prime
				* result
				+ ((idAutenticaOauth == null) ? 0 : idAutenticaOauth.hashCode());
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
		AutenticaOauth other = (AutenticaOauth) obj;
		if (codeAutenticaOauth == null) {
			if (other.codeAutenticaOauth != null)
				return false;
		} else if (!codeAutenticaOauth.equals(other.codeAutenticaOauth))
			return false;
		if (idAutenticaOauth == null) {
			if (other.idAutenticaOauth != null)
				return false;
		} else if (!idAutenticaOauth.equals(other.idAutenticaOauth))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AutenticaOauth [idAutenticaOauth=");
		builder.append(idAutenticaOauth);
		builder.append(", codeAutenticaOauth=");
		builder.append(codeAutenticaOauth);
		builder.append(", token=");
		builder.append(token);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanRedSocial=");
		builder.append(beanRedSocial);
		builder.append(", codeRedSocial=");
		builder.append(codeRedSocial);
		builder.append(", beanCuentaTurista=");
		builder.append(beanCuentaTurista);
		builder.append(", codeCuentaTurista=");
		builder.append(codeCuentaTurista);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
				
}
