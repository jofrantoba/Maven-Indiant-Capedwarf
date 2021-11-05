package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class RangoEdad implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idRangoEdad;
	@Persistent
	private String codeRangoEdad;
	@Persistent
	private Integer edadInicio;
	@Persistent
	private Integer edadFin;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	public String getIdRangoEdad() {
		return idRangoEdad;
	}
	public void setIdRangoEdad(String idRangoEdad) {
		this.idRangoEdad = idRangoEdad;
	}
	public String getCodeRangoEdad() {
		return codeRangoEdad;
	}
	public void setCodeRangoEdad(String codeRangoEdad) {
		this.codeRangoEdad = codeRangoEdad;
	}
	public Integer getEdadInicio() {
		return edadInicio;
	}
	public void setEdadInicio(Integer edadInicio) {
		this.edadInicio = edadInicio;
	}
	public Integer getEdadFin() {
		return edadFin;
	}
	public void setEdadFin(Integer edadFin) {
		this.edadFin = edadFin;
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
	public String toString() {
		return "RangoEdad [idRangoEdad=" + idRangoEdad + ", codeRangoEdad=" + codeRangoEdad + ", edadInicio="
				+ edadInicio + ", edadFin=" + edadFin + ", version=" + version + ", isPersistente=" + isPersistente
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeRangoEdad == null) ? 0 : codeRangoEdad.hashCode());
		result = prime * result + ((edadFin == null) ? 0 : edadFin.hashCode());
		result = prime * result + ((edadInicio == null) ? 0 : edadInicio.hashCode());
		result = prime * result + ((idRangoEdad == null) ? 0 : idRangoEdad.hashCode());
		result = prime * result + ((isPersistente == null) ? 0 : isPersistente.hashCode());
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
		RangoEdad other = (RangoEdad) obj;
		if (codeRangoEdad == null) {
			if (other.codeRangoEdad != null)
				return false;
		} else if (!codeRangoEdad.equals(other.codeRangoEdad))
			return false;
		if (edadFin == null) {
			if (other.edadFin != null)
				return false;
		} else if (!edadFin.equals(other.edadFin))
			return false;
		if (edadInicio == null) {
			if (other.edadInicio != null)
				return false;
		} else if (!edadInicio.equals(other.edadInicio))
			return false;
		if (idRangoEdad == null) {
			if (other.idRangoEdad != null)
				return false;
		} else if (!idRangoEdad.equals(other.idRangoEdad))
			return false;
		if (isPersistente == null) {
			if (other.isPersistente != null)
				return false;
		} else if (!isPersistente.equals(other.isPersistente))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}	
}