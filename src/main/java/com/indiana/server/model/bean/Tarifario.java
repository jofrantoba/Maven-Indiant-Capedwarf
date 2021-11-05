package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Tarifario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTarifario;
	@Persistent
	private String codeTarifario;
	@Persistent
	private Integer rangoInicial;
	@Persistent
	private Integer rangoFinal;
	@Persistent
	private Double precioUnidad;
	@Persistent
	private String nombreTipoSuscripcion;
	@Persistent
	private String nombrePais;
	@Persistent
	private String estado;
	@Persistent
	private java.util.Date fechaInicial;
	@Persistent
	private java.util.Date fechaFinal;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codeTipoSuscripcion;
	@Persistent
	@Unowned
	private TipoSuscripcion beanTipoSuscripcion;
	@Persistent
	private String codePais;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private Boolean isPersistente;
	public String getIdTarifario() {
		return idTarifario;
	}
	public String getCodeTarifario() {
		return codeTarifario;
	}
	public Integer getRangoInicial() {
		return rangoInicial;
	}
	public Integer getRangoFinal() {
		return rangoFinal;
	}
	public Double getPrecioUnidad() {
		return precioUnidad;
	}
	public String getNombreTipoSuscripcion() {
		return nombreTipoSuscripcion;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public Long getVersion() {
		return version;
	}
	public String getOperacion() {
		return operacion;
	}
	public String getCodeTipoSuscripcion() {
		return codeTipoSuscripcion;
	}
	public TipoSuscripcion getBeanTipoSuscripcion() {
		return beanTipoSuscripcion;
	}
	public String getCodePais() {
		return codePais;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setIdTarifario(String idTarifario) {
		this.idTarifario = idTarifario;
	}
	public void setCodeTarifario(String codeTarifario) {
		this.codeTarifario = codeTarifario;
	}
	public void setRangoInicial(Integer rangoInicial) {
		this.rangoInicial = rangoInicial;
	}
	public void setRangoFinal(Integer rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	public void setNombreTipoSuscripcion(String nombreTipoSuscripcion) {
		this.nombreTipoSuscripcion = nombreTipoSuscripcion;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public void setCodeTipoSuscripcion(String codeTipoSuscripcion) {
		this.codeTipoSuscripcion = codeTipoSuscripcion;
	}
	public void setBeanTipoSuscripcion(TipoSuscripcion beanTipoSuscripcion) {
		this.beanTipoSuscripcion = beanTipoSuscripcion;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
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
	public java.util.Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(java.util.Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public java.util.Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(java.util.Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeTarifario == null) ? 0 : codeTarifario.hashCode());
		result = prime * result + ((idTarifario == null) ? 0 : idTarifario.hashCode());
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
		Tarifario other = (Tarifario) obj;
		if (codeTarifario == null) {
			if (other.codeTarifario != null)
				return false;
		} else if (!codeTarifario.equals(other.codeTarifario))
			return false;
		if (idTarifario == null) {
			if (other.idTarifario != null)
				return false;
		} else if (!idTarifario.equals(other.idTarifario))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tarifario [idTarifario=");
		builder.append(idTarifario);
		builder.append(", codeTarifario=");
		builder.append(codeTarifario);
		builder.append(", rangoInicial=");
		builder.append(rangoInicial);
		builder.append(", rangoFinal=");
		builder.append(rangoFinal);
		builder.append(", precioUnidad=");
		builder.append(precioUnidad);
		builder.append(", nombreTipoSuscripcion=");
		builder.append(nombreTipoSuscripcion);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", fechaInicial=");
		builder.append(fechaInicial);
		builder.append(", fechaFinal=");
		builder.append(fechaFinal);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", codeTipoSuscripcion=");
		builder.append(codeTipoSuscripcion);
		builder.append(", beanTipoSuscripcion=");
		builder.append(beanTipoSuscripcion);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
