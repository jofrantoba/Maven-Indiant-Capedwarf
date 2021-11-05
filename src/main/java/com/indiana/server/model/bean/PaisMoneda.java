package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class PaisMoneda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPaisMoneda;
	@Persistent
	private String codePaisMoneda;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codePais;
	@Persistent
	private String codeMoneda;
	@Persistent
	private String nombrePais;
	@Persistent
	private String nombreMoneda;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	@Unowned
	private Moneda beanMoneda;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdPaisMoneda() {
		return idPaisMoneda;
	}
	public void setIdPaisMoneda(String idPaisMoneda) {		
		this.idPaisMoneda = idPaisMoneda;		
	}	
	public String getCodePaisMoneda() {
		return codePaisMoneda;
	}
	public void setCodePaisMoneda(String codePaisMoneda) {
		this.codePaisMoneda = codePaisMoneda;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
	}
	public Moneda getBeanMoneda() {
		return beanMoneda;
	}
	public void setBeanMoneda(Moneda beanMoneda) {
		this.beanMoneda = beanMoneda;
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
	public String getCodeMoneda() {
		return codeMoneda;
	}
	public void setCodeMoneda(String codeMoneda) {
		this.codeMoneda = codeMoneda;
	}
	
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
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
		result = prime * result
				+ ((codeMoneda == null) ? 0 : codeMoneda.hashCode());
		result = prime * result + ((codePais == null) ? 0 : codePais.hashCode());
		result = prime * result
				+ ((idPaisMoneda == null) ? 0 : idPaisMoneda.hashCode());
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
		PaisMoneda other = (PaisMoneda) obj;
		if (codeMoneda == null) {
			if (other.codeMoneda != null)
				return false;
		} else if (!codeMoneda.equals(other.codeMoneda))
			return false;
		if (codePais == null) {
			if (other.codePais != null)
				return false;
		} else if (!codePais.equals(other.codePais))
			return false;
		if (idPaisMoneda == null) {
			if (other.idPaisMoneda != null)
				return false;
		} else if (!idPaisMoneda.equals(other.idPaisMoneda))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaisMoneda [idPaisMoneda=");
		builder.append(idPaisMoneda);
		builder.append(", codePaisMoneda=");
		builder.append(codePaisMoneda);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", codeMoneda=");
		builder.append(codeMoneda);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", nombreMoneda=");
		builder.append(nombreMoneda);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", beanMoneda=");
		builder.append(beanMoneda);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}