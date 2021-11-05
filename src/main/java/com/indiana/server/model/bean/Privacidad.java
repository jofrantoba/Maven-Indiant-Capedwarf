package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Privacidad implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPrivacidad;
	@Persistent
	private String codePrivacidad;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;

	public String getIdPrivacidad() {
		return idPrivacidad;
	}

	public void setIdPrivacidad(String idPrivacidad) {		
		this.idPrivacidad = idPrivacidad;		
	}
	
	public String getCodePrivacidad() {
		return codePrivacidad;
	}

	public void setCodePrivacidad(String codePrivacidad) {
		this.codePrivacidad = codePrivacidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((idPrivacidad == null) ? 0 : idPrivacidad.hashCode());
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
		Privacidad other = (Privacidad) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idPrivacidad == null) {
			if (other.idPrivacidad != null)
				return false;
		} else if (!idPrivacidad.equals(other.idPrivacidad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Privacidad [idPrivacidad=");
		builder.append(idPrivacidad);
		builder.append(", codePrivacidad=");
		builder.append(codePrivacidad);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}

	
}