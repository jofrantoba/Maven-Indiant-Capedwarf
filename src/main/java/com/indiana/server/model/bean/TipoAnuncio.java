package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class TipoAnuncio implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTipoAnuncio;
	@Persistent
	private String codeTipoAnuncio;
	@Persistent
	private String descripcion;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	
	public String getIdTipoAnuncio() {
		return idTipoAnuncio;
	}
	public void setIdTipoAnuncio(String idTipoAnuncio) {		
		this.idTipoAnuncio = idTipoAnuncio;		
	}	
	public String getCodeTipoAnuncio() {
		return codeTipoAnuncio;
	}
	public void setCodeTipoAnuncio(String codeTipoAnuncio) {
		this.codeTipoAnuncio = codeTipoAnuncio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeTipoAnuncio == null) ? 0 : codeTipoAnuncio.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((idTipoAnuncio == null) ? 0 : idTipoAnuncio.hashCode());
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
		TipoAnuncio other = (TipoAnuncio) obj;
		if (codeTipoAnuncio == null) {
			if (other.codeTipoAnuncio != null)
				return false;
		} else if (!codeTipoAnuncio.equals(other.codeTipoAnuncio))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idTipoAnuncio == null) {
			if (other.idTipoAnuncio != null)
				return false;
		} else if (!idTipoAnuncio.equals(other.idTipoAnuncio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TipoAnuncio [idTipoAnuncio=" + idTipoAnuncio
				+ ", codeTipoAnuncio=" + codeTipoAnuncio + ", descripcion="
				+ descripcion + ", estado=" + estado + ", version=" + version
				+ "]";
	}
	
}