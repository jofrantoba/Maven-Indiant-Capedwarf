package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class CategoriaNegocio implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCategoriaNegocio;
	@Persistent
	private String codeCategoriaNegocio;
	@Persistent
	private String nombre;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdCategoriaNegocio() {
		return idCategoriaNegocio;
	}
	public void setIdCategoriaNegocio(String idCategoriaNegocio) {		
		this.idCategoriaNegocio = idCategoriaNegocio;
	}
	
	public String getCodeCategoriaNegocio() {
		return codeCategoriaNegocio;
	}
	public void setCodeCategoriaNegocio(String codeCategoriaNegocio) {
		this.codeCategoriaNegocio = codeCategoriaNegocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeCategoriaNegocio == null) ? 0 : codeCategoriaNegocio
						.hashCode());
		result = prime
				* result
				+ ((idCategoriaNegocio == null) ? 0 : idCategoriaNegocio
						.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		CategoriaNegocio other = (CategoriaNegocio) obj;
		if (codeCategoriaNegocio == null) {
			if (other.codeCategoriaNegocio != null)
				return false;
		} else if (!codeCategoriaNegocio.equals(other.codeCategoriaNegocio))
			return false;
		if (idCategoriaNegocio == null) {
			if (other.idCategoriaNegocio != null)
				return false;
		} else if (!idCategoriaNegocio.equals(other.idCategoriaNegocio))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoriaNegocio [idCategoriaNegocio=");
		builder.append(idCategoriaNegocio);
		builder.append(", codeCategoriaNegocio=");
		builder.append(codeCategoriaNegocio);
		builder.append(", nombre=");
		builder.append(nombre);
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
