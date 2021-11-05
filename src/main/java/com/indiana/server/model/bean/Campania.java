package com.indiana.server.model.bean;

import java.io.Serializable;


import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Campania implements Serializable{
	
	private static final long serialVersionUID = 4682238130099045328L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCampania;
	@Persistent
	private String codeCampania;
	@Persistent
	private String nombre;
	@Persistent
	private String descripcion;
	@Persistent
	private java.util.Date fechaCreacion;
	@Persistent
	private java.util.Date fechaCierre;
	@Persistent
	private String estado;
	@Persistent
	@Unowned
	private UsuarioNegocio beanUsuarioNegocio;
	@Persistent
	private String codeUsuarioNegocio;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	private Boolean isPersistente;
	public String getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}
	public String getCodeCampania() {
		return codeCampania;
	}
	public void setCodeCampania(String codeCampania) {
		this.codeCampania = codeCampania;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.util.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public java.util.Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(java.util.Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public UsuarioNegocio getBeanUsuarioNegocio() {
		return beanUsuarioNegocio;
	}
	public void setBeanUsuarioNegocio(UsuarioNegocio beanUsuarioNegocio) {
		this.beanUsuarioNegocio = beanUsuarioNegocio;
	}
	public String getCodeUsuarioNegocio() {
		return codeUsuarioNegocio;
	}
	public void setCodeUsuarioNegocio(String codeUsuarioNegocio) {
		this.codeUsuarioNegocio = codeUsuarioNegocio;
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
		result = prime * result + ((codeCampania == null) ? 0 : codeCampania.hashCode());
		result = prime * result + ((idCampania == null) ? 0 : idCampania.hashCode());
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
		Campania other = (Campania) obj;
		if (codeCampania == null) {
			if (other.codeCampania != null)
				return false;
		} else if (!codeCampania.equals(other.codeCampania))
			return false;
		if (idCampania == null) {
			if (other.idCampania != null)
				return false;
		} else if (!idCampania.equals(other.idCampania))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Campania [idCampania=");
		builder.append(idCampania);
		builder.append(", codeCampania=");
		builder.append(codeCampania);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaCierre=");
		builder.append(fechaCierre);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", beanUsuarioNegocio=");
		builder.append(beanUsuarioNegocio);
		builder.append(", codeUsuarioNegocio=");
		builder.append(codeUsuarioNegocio);
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
	