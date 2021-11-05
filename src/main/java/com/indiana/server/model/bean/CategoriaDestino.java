package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class CategoriaDestino implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCategoriaDestino;
	@Persistent
	private String codeCategoriaDestino;
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
	
	public String getIdCategoriaDestino() {		
		return idCategoriaDestino;
	}
	public void setIdCategoriaDestino(String idCategoriaDestino) {
		this.idCategoriaDestino = idCategoriaDestino;
	}
	
	public String getCodeCategoriaDestino() {
		return codeCategoriaDestino;
	}
	public void setCodeCategoriaDestino(String codeCategoriaDestino) {
		this.codeCategoriaDestino = codeCategoriaDestino;
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
				+ ((codeCategoriaDestino == null) ? 0 : codeCategoriaDestino
						.hashCode());
		result = prime
				* result
				+ ((idCategoriaDestino == null) ? 0 : idCategoriaDestino
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
		CategoriaDestino other = (CategoriaDestino) obj;
		if (codeCategoriaDestino == null) {
			if (other.codeCategoriaDestino != null)
				return false;
		} else if (!codeCategoriaDestino.equals(other.codeCategoriaDestino))
			return false;
		if (idCategoriaDestino == null) {
			if (other.idCategoriaDestino != null)
				return false;
		} else if (!idCategoriaDestino.equals(other.idCategoriaDestino))
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
		builder.append("CategoriaDestino [idCategoriaDestino=");
		builder.append(idCategoriaDestino);
		builder.append(", codeCategoriaDestino=");
		builder.append(codeCategoriaDestino);
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
