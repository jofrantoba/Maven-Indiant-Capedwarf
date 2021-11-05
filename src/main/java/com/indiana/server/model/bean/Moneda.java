package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Moneda implements Serializable {
	public static final String LISTARMONEDASACTIVAS="LISTARMONEDASACTIVAS";

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMoneda;
	@Persistent
	private String codeMoneda;
	@Persistent
	private String descripcion;
	@Persistent
	private String representacion;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(String idMoneda) {		
		this.idMoneda = idMoneda;
	}	
	public String getCodeMoneda() {
		return codeMoneda;
	}
	public void setCodeMoneda(String codeMoneda) {
		this.codeMoneda = codeMoneda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRepresentacion() {
		return representacion;
	}
	public void setRepresentacion(String representacion) {
		this.representacion = representacion;
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
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((idMoneda == null) ? 0 : idMoneda.hashCode());
		result = prime * result
				+ ((representacion == null) ? 0 : representacion.hashCode());
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
		Moneda other = (Moneda) obj;
		if (codeMoneda == null) {
			if (other.codeMoneda != null)
				return false;
		} else if (!codeMoneda.equals(other.codeMoneda))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idMoneda == null) {
			if (other.idMoneda != null)
				return false;
		} else if (!idMoneda.equals(other.idMoneda))
			return false;
		if (representacion == null) {
			if (other.representacion != null)
				return false;
		} else if (!representacion.equals(other.representacion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Moneda [idMoneda=");
		builder.append(idMoneda);
		builder.append(", codeMoneda=");
		builder.append(codeMoneda);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", representacion=");
		builder.append(representacion);
		builder.append(", estado=");
		builder.append(estado);
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