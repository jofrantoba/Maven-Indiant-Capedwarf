package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class ControlPosicion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idControlPosicion;
	@Persistent	
	private String codeControlPosicion;	
	@Persistent
	@Unowned
	private UsuarioTurista beanUsuarioTurista;
	@Persistent
	private String codeUsuarioTurista;
	@Persistent	
	private Double latitud;	
	@Persistent	
	private Double longitud;	
	@Persistent	
	private Double altitud;	
	@Persistent	
	private String nombrePais;
	@Persistent
	private String nombreRegion;
	@Persistent
	private String nombreLocalidad;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private java.util.Date fecha;
	@Persistent
	private Boolean isPersistente;
	public String getIdControlPosicion() {
		return idControlPosicion;
	}
	public void setIdControlPosicion(String idControlPosicion) {
		this.idControlPosicion = idControlPosicion;
	}
	public String getCodeControlPosicion() {
		return codeControlPosicion;
	}
	public void setCodeControlPosicion(String codeControlPosicion) {
		this.codeControlPosicion = codeControlPosicion;
	}
	public UsuarioTurista getBeanUsuarioTurista() {
		return beanUsuarioTurista;
	}
	public void setBeanUsuarioTurista(UsuarioTurista beanUsuarioTurista) {
		this.beanUsuarioTurista = beanUsuarioTurista;
	}
	public String getCodeUsuarioTurista() {
		return codeUsuarioTurista;
	}
	public void setCodeUsuarioTurista(String codeUsuarioTurista) {
		this.codeUsuarioTurista = codeUsuarioTurista;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getAltitud() {
		return altitud;
	}
	public void setAltitud(Double altitud) {
		this.altitud = altitud;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
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
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
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
		result = prime * result + ((codeControlPosicion == null) ? 0 : codeControlPosicion.hashCode());
		result = prime * result + ((idControlPosicion == null) ? 0 : idControlPosicion.hashCode());
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
		ControlPosicion other = (ControlPosicion) obj;
		if (codeControlPosicion == null) {
			if (other.codeControlPosicion != null)
				return false;
		} else if (!codeControlPosicion.equals(other.codeControlPosicion))
			return false;
		if (idControlPosicion == null) {
			if (other.idControlPosicion != null)
				return false;
		} else if (!idControlPosicion.equals(other.idControlPosicion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ControlPosicion [idControlPosicion=");
		builder.append(idControlPosicion);
		builder.append(", codeControlPosicion=");
		builder.append(codeControlPosicion);
		builder.append(", beanUsuarioTurista=");
		builder.append(beanUsuarioTurista);
		builder.append(", codeUsuarioTurista=");
		builder.append(codeUsuarioTurista);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", altitud=");
		builder.append(altitud);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", nombreRegion=");
		builder.append(nombreRegion);
		builder.append(", nombreLocalidad=");
		builder.append(nombreLocalidad);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
