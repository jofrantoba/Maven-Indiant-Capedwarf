package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Grupo implements Serializable{
	
	private static final long serialVersionUID = 4682238130099045328L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idGrupo;
	@Persistent
	private String codeGrupo;
	@Persistent
	private String nombre;
	@Persistent
	private Date descripcion;
	@Persistent
	private Date fechaCreacion;
	@Persistent
	private Date fechaCierre;
	@Persistent
	private String estado;
	@Persistent
	@Unowned
	private Campania beanCampania;
	@Persistent
	private String codeCampania;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	private Boolean isPersistente;
	public String getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getCodeGrupo() {
		return codeGrupo;
	}
	public void setCodeGrupo(String codeGrupo) {
		this.codeGrupo = codeGrupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(Date descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Campania getBeanCampania() {
		return beanCampania;
	}
	public void setBeanCampania(Campania beanCampania) {
		this.beanCampania = beanCampania;
	}	
	public String getCodeCampania() {
		return codeCampania;
	}
	public void setCodeCampania(String codeCampania) {
		this.codeCampania = codeCampania;
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
		result = prime * result + ((beanCampania == null) ? 0 : beanCampania.hashCode());
		result = prime * result + ((codeCampania == null) ? 0 : codeCampania.hashCode());
		result = prime * result + ((codeGrupo == null) ? 0 : codeGrupo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaCierre == null) ? 0 : fechaCierre.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		result = prime * result + ((isPersistente == null) ? 0 : isPersistente.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		Grupo other = (Grupo) obj;
		if (beanCampania == null) {
			if (other.beanCampania != null)
				return false;
		} else if (!beanCampania.equals(other.beanCampania))
			return false;
		if (codeCampania == null) {
			if (other.codeCampania != null)
				return false;
		} else if (!codeCampania.equals(other.codeCampania))
			return false;
		if (codeGrupo == null) {
			if (other.codeGrupo != null)
				return false;
		} else if (!codeGrupo.equals(other.codeGrupo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaCierre == null) {
			if (other.fechaCierre != null)
				return false;
		} else if (!fechaCierre.equals(other.fechaCierre))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		if (isPersistente == null) {
			if (other.isPersistente != null)
				return false;
		} else if (!isPersistente.equals(other.isPersistente))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grupo [idGrupo=" + idGrupo + ", codeGrupo=" + codeGrupo + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", fechaCreacion=" + fechaCreacion + ", fechaCierre=" + fechaCierre + ", estado="
				+ estado + ", beanCampania=" + beanCampania + ", codeCampania=" + codeCampania + ", version=" + version
				+ ", isPersistente=" + isPersistente + "]";
	}		
}