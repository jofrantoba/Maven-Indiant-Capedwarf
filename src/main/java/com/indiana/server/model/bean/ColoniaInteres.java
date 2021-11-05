package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class ColoniaInteres implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idColoniaInteres;
	@Persistent
	private String codeColoniaInteres;	
	@Persistent
	private Long version;	
	@NotPersistent
	private String operacion;
	@Persistent
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	@Unowned
	private Interes beanInteres;
	@Persistent
	private String codeInteres;
	@Persistent
	private String nombreInteres;
	@Persistent
	private Double valorModaColoniaInteres;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private Integer annio;
	@Persistent
	private Integer mes;
	@Persistent
	private Integer dia;
	@Persistent
	private Integer edad;
	@Persistent
	private String nombreColonia;
	@Persistent
	private String nacionalidad;
	
	public String getIdColoniaInteres() {
		return idColoniaInteres;
	}
	public void setIdColoniaInteres(String idColoniaInteres) {
		this.idColoniaInteres=idColoniaInteres;
	}
	public String getCodeColoniaInteres() {
		return codeColoniaInteres;
	}
	public void setCodeColoniaInteres(String codeColoniaInteres) {
		this.codeColoniaInteres = codeColoniaInteres;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}	
	public Integer getAnnio() {
		return annio;
	}
	public void setAnnio(Integer annio) {
		this.annio = annio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public Colonia getBeanColonia() {
		return beanColonia;
	}
	public void setBeanColonia(Colonia beanColonia) {
		this.beanColonia = beanColonia;
	}
	public String getCodeColonia() {
		return codeColonia;
	}
	public void setCodeColonia(String codeColonia) {
		this.codeColonia = codeColonia;
	}
	public Interes getBeanInteres() {
		return beanInteres;
	}
	public void setBeanInteres(Interes beanInteres) {
		this.beanInteres = beanInteres;
	}
	public String getCodeInteres() {
		return codeInteres;
	}
	public void setCodeInteres(String codeInteres) {
		this.codeInteres = codeInteres;
	}
	public String getNombreInteres() {
		return nombreInteres;
	}
	public void setNombreInteres(String nombreInteres) {
		this.nombreInteres = nombreInteres;
	}
	public Double getValorModaColoniaInteres() {
		return valorModaColoniaInteres;
	}
	public void setValorModaColoniaInteres(Double valorModaColoniaInteres) {
		this.valorModaColoniaInteres = valorModaColoniaInteres;
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
		result = prime * result + ((codeColonia == null) ? 0 : codeColonia.hashCode());
		result = prime * result + ((codeInteres == null) ? 0 : codeInteres.hashCode());
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
		ColoniaInteres other = (ColoniaInteres) obj;
		if (codeColonia == null) {
			if (other.codeColonia != null)
				return false;
		} else if (!codeColonia.equals(other.codeColonia))
			return false;
		if (codeInteres == null) {
			if (other.codeInteres != null)
				return false;
		} else if (!codeInteres.equals(other.codeInteres))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColoniaInteres [idColoniaInteres=");
		builder.append(idColoniaInteres);
		builder.append(", codeColoniaInteres=");
		builder.append(codeColoniaInteres);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", beanInteres=");
		builder.append(beanInteres);
		builder.append(", codeInteres=");
		builder.append(codeInteres);
		builder.append(", nombreInteres=");
		builder.append(nombreInteres);
		builder.append(", valorModaColoniaInteres=");
		builder.append(valorModaColoniaInteres);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
