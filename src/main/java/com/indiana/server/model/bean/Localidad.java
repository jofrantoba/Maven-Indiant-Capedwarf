package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Localidad implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idLocalidad;
	@Persistent
	private String codeLocalidad;
	@Persistent
	private String nombre;	
	@Persistent
	private Long version;	
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private Region beanRegion;
	@Persistent
	private String codeRegion;
	@Persistent
	private String nombreRegion;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private String codePais;
	@Persistent
	private String nombrePais;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String generador;
	
	
	public String getIdLocalidad() {
		return idLocalidad;
	}
	
	public void setIdLocalidad(String idLocalidad) {		
		this.idLocalidad = idLocalidad;	
	}
	
	public String getCodeLocalidad() {
		return codeLocalidad;
	}

	public void setCodeLocalidad(String codeLocalidad) {
		this.codeLocalidad = codeLocalidad;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Region getBeanRegion() {
		return beanRegion;
	}
	public void setBeanRegion(Region beanRegion) {
		this.beanRegion = beanRegion;
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

	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	public Pais getBeanPais() {
		return beanPais;
	}

	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
	}

	public String getCodePais() {
		return codePais;
	}

	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		nombre=nombre.toUpperCase().trim();
		nombrePais=nombrePais.toUpperCase().trim();
		nombreRegion=nombreRegion.toUpperCase().trim();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombrePais == null) ? 0 : nombrePais.hashCode());
		result = prime * result + ((nombreRegion == null) ? 0 : nombreRegion.hashCode());
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
		Localidad other = (Localidad) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombrePais == null) {
			if (other.nombrePais != null)
				return false;
		} else if (!nombrePais.equals(other.nombrePais))
			return false;
		if (nombreRegion == null) {
			if (other.nombreRegion != null)
				return false;
		} else if (!nombreRegion.equals(other.nombreRegion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Localidad [idLocalidad=");
		builder.append(idLocalidad);
		builder.append(", codeLocalidad=");
		builder.append(codeLocalidad);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanRegion=");
		builder.append(beanRegion);
		builder.append(", codeRegion=");
		builder.append(codeRegion);
		builder.append(", nombreRegion=");
		builder.append(nombreRegion);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", generador=");
		builder.append(generador);
		builder.append("]");
		return builder.toString();
	}

	

}
