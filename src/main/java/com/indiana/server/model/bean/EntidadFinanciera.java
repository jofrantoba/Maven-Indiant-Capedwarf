package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class EntidadFinanciera implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEntidadFinanciera;
	@Persistent
	private String codeEntidadFinanciera;
	@Persistent
	private String nombreEntidadFinanciera;
	@Persistent
	private String numeroCuenta;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codePais;
	@Persistent
	private String nombrePais;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdEntidadFinanciera() {
		return idEntidadFinanciera;
	}
	public void setIdEntidadFinanciera(String idEntidadFinanciera) {
		this.idEntidadFinanciera = idEntidadFinanciera;
	}
	public String getCodeEntidadFinanciera() {
		return codeEntidadFinanciera;
	}
	public void setCodeEntidadFinanciera(String codeEntidadFinanciera) {
		this.codeEntidadFinanciera = codeEntidadFinanciera;
	}
	public String getNombreEntidadFinanciera() {
		return nombreEntidadFinanciera;
	}
	public void setNombreEntidadFinanciera(String nombreEntidadFinanciera) {
		this.nombreEntidadFinanciera = nombreEntidadFinanciera;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
	}
	
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
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
				+ ((codeEntidadFinanciera == null) ? 0 : codeEntidadFinanciera
						.hashCode());
		result = prime
				* result
				+ ((idEntidadFinanciera == null) ? 0 : idEntidadFinanciera
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
		EntidadFinanciera other = (EntidadFinanciera) obj;
		if (codeEntidadFinanciera == null) {
			if (other.codeEntidadFinanciera != null)
				return false;
		} else if (!codeEntidadFinanciera.equals(other.codeEntidadFinanciera))
			return false;
		if (idEntidadFinanciera == null) {
			if (other.idEntidadFinanciera != null)
				return false;
		} else if (!idEntidadFinanciera.equals(other.idEntidadFinanciera))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntidadFinanciera [idEntidadFinanciera=");
		builder.append(idEntidadFinanciera);
		builder.append(", codeEntidadFinanciera=");
		builder.append(codeEntidadFinanciera);
		builder.append(", nombreEntidadFinanciera=");
		builder.append(nombreEntidadFinanciera);
		builder.append(", numeroCuenta=");
		builder.append(numeroCuenta);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	

}
