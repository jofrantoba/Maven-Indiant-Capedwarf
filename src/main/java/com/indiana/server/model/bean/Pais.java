package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Pais implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3271622424872679129L;
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPais;
	@Persistent
	private String codePais;
	@Persistent
	private String nombre;
	@Persistent
	private String urlicono;
	@Persistent
	private String generador;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	@NotPersistent
	private Integer indexList;
	
	
	public String getIdPais() {
		return idPais;
	}
	public void setIdPais(String idPais) {		
		this.idPais = idPais;
	}
	
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUrlicono() {
		return urlicono;
	}
	public void setUrlicono(String urlicono) {
		this.urlicono = urlicono;
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
	public String getGenerador() {
		return generador;
	}
	public void setGenerador(String generador) {
		this.generador = generador;
	}	
	public Integer getIndexList() {
		return indexList;
	}
	public void setIndexList(Integer indexList) {
		this.indexList = indexList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		nombre=nombre.toUpperCase().trim();
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
		Pais other = (Pais) obj;
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
		builder.append("Pais [idPais=");
		builder.append(idPais);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", urlicono=");
		builder.append(urlicono);
		builder.append(", generador=");
		builder.append(generador);
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
