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
public class EnlaceRedSocial implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEnlaceRedSocial;
	@Persistent	
	private String codeEnlaceRedSocial;	
	@Persistent	
	private String token;
	@Persistent	
	private Date fecha;	
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
	@Unowned
	private CuentaNegocio beanCuentaNegocio;
	@Persistent
	private String codeCuentaNegocio;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdEnlaceRedSocial() {
		return idEnlaceRedSocial;
	}
	public void setIdEnlaceRedSocial(String idEnlaceRedSocial) {
		this.idEnlaceRedSocial=idEnlaceRedSocial;				
	}
	public String getCodeEnlaceRedSocial() {
		return codeEnlaceRedSocial;
	}
	public void setCodeEnlaceRedSocial(String codeEnlaceRedSocial) {
		this.codeEnlaceRedSocial = codeEnlaceRedSocial;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	
	public CuentaNegocio getBeanCuentaNegocio() {
		return beanCuentaNegocio;
	}
	public void setBeanCuentaNegocio(CuentaNegocio beanCuentaNegocio) {
		this.beanCuentaNegocio = beanCuentaNegocio;
	}
	public String getCodeCuentaNegocio() {
		return codeCuentaNegocio;
	}
	public void setCodeCuentaNegocio(String codeCuentaNegocio) {
		this.codeCuentaNegocio = codeCuentaNegocio;
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
	public RedSocial getBeanRedSocial() {
		return beanRedSocial;
	}
	public void setBeanRedSocial(RedSocial beanRedSocial) {
		this.beanRedSocial = beanRedSocial;
	}
	public String getCodeRedSocial() {
		return codeRedSocial;
	}
	public void setCodeRedSocial(String codeRedSocial) {
		this.codeRedSocial = codeRedSocial;
	}
	public CuentaTurista getBeanCuentaTurista() {
		return beanCuentaTurista;
	}
	public void setBeanCuentaTurista(CuentaTurista beanCuentaTurista) {
		this.beanCuentaTurista = beanCuentaTurista;
	}
	public String getCodeCuentaTurista() {
		return codeCuentaTurista;
	}
	public void setCodeCuentaTurista(String codeCuentaTurista) {
		this.codeCuentaTurista = codeCuentaTurista;
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
				+ ((codeEnlaceRedSocial == null) ? 0 : codeEnlaceRedSocial
						.hashCode());
		result = prime
				* result
				+ ((idEnlaceRedSocial == null) ? 0 : idEnlaceRedSocial
						.hashCode());
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
		EnlaceRedSocial other = (EnlaceRedSocial) obj;
		if (codeEnlaceRedSocial == null) {
			if (other.codeEnlaceRedSocial != null)
				return false;
		} else if (!codeEnlaceRedSocial.equals(other.codeEnlaceRedSocial))
			return false;
		if (idEnlaceRedSocial == null) {
			if (other.idEnlaceRedSocial != null)
				return false;
		} else if (!idEnlaceRedSocial.equals(other.idEnlaceRedSocial))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnlaceRedSocial [idEnlaceRedSocial=");
		builder.append(idEnlaceRedSocial);
		builder.append(", codeEnlaceRedSocial=");
		builder.append(codeEnlaceRedSocial);
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
