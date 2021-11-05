package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Region implements Serializable {
	public static final String LISTAREGIONXPAIS="LISTAREGIONXPAIS";
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey		
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idRegion;
	@Persistent
	private String codeRegion;
	@Persistent
	private String nombre;
	@Persistent
	private String generador;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codePais;
	@Persistent
	private String nombrePais;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(String idRegion) {		
		this.idRegion = idRegion;
	}	
	public String getCodeRegion() {
		return codeRegion;
	}
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
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
	
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
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
		nombrePais=nombrePais.toUpperCase().trim();
		nombre=nombre.toUpperCase().trim();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombrePais == null) ? 0 : nombrePais.hashCode());
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
		Region other = (Region) obj;
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
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Region [idRegion=");
		builder.append(idRegion);
		builder.append(", codeRegion=");
		builder.append(codeRegion);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
