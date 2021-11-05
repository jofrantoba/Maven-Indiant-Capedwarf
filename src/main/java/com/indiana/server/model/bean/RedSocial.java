package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class RedSocial implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idRedSocial;
	@Persistent
	private String codeRedSocial;	
	@Persistent
	private String nombreApp;
	@Persistent
	private String apiKey;
	@Persistent
	private String sharedSecret;
	@Persistent
	private String oauthToken;
	@Persistent
	private String oauthTokenSecret;
	@Persistent
	private String apiVersion;
	@Persistent	
	private Long version;
	@Persistent
	private String redSocial;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	public String getIdRedSocial() {
		return idRedSocial;
	}
	public void setIdRedSocial(String idRedSocial) {
		this.idRedSocial = idRedSocial;
	}
	public String getCodeRedSocial() {
		return codeRedSocial;
	}
	public void setCodeRedSocial(String codeRedSocial) {
		this.codeRedSocial = codeRedSocial;
	}
	public String getNombreApp() {
		return nombreApp;
	}
	public void setNombreApp(String nombreApp) {
		this.nombreApp = nombreApp;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public String getOauthToken() {
		return oauthToken;
	}
	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}
	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}
	public void setOauthTokenSecret(String oauthTokenSecret) {
		this.oauthTokenSecret = oauthTokenSecret;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getRedSocial() {
		return redSocial;
	}
	public void setRedSocial(String redSocial) {
		this.redSocial = redSocial;
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
		result = prime * result + ((codeRedSocial == null) ? 0 : codeRedSocial.hashCode());
		result = prime * result + ((idRedSocial == null) ? 0 : idRedSocial.hashCode());
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
		RedSocial other = (RedSocial) obj;
		if (codeRedSocial == null) {
			if (other.codeRedSocial != null)
				return false;
		} else if (!codeRedSocial.equals(other.codeRedSocial))
			return false;
		if (idRedSocial == null) {
			if (other.idRedSocial != null)
				return false;
		} else if (!idRedSocial.equals(other.idRedSocial))
			return false;
		return true;
	}
	
	
}