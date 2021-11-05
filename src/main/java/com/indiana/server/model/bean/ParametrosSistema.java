package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class ParametrosSistema implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idParametrosSistema;
	@Persistent
	private String codeParametrosSistema;
	@Persistent
	private String descripcion;
	@Persistent
	private String valor;
	@Persistent
	private String tipoDato;
	@Persistent
	private String unidad;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdParametrosSistema() {
		return idParametrosSistema;
	}
	public void setIdParametrosSistema(String idParametrosSistema) {
		this.idParametrosSistema = idParametrosSistema;
	}
	public String getCodeParametrosSistema() {
		return codeParametrosSistema;
	}
	public void setCodeParametrosSistema(String codeParametrosSistema) {
		this.codeParametrosSistema = codeParametrosSistema;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}	
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
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
		result = prime
				* result
				+ ((codeParametrosSistema == null) ? 0 : codeParametrosSistema
						.hashCode());
		result = prime
				* result
				+ ((idParametrosSistema == null) ? 0 : idParametrosSistema
						.hashCode());
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
		ParametrosSistema other = (ParametrosSistema) obj;
		if (codeParametrosSistema == null) {
			if (other.codeParametrosSistema != null)
				return false;
		} else if (!codeParametrosSistema.equals(other.codeParametrosSistema))
			return false;
		if (idParametrosSistema == null) {
			if (other.idParametrosSistema != null)
				return false;
		} else if (!idParametrosSistema.equals(other.idParametrosSistema))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParametrosSistema [idParametrosSistema=");
		builder.append(idParametrosSistema);
		builder.append(", codeParametrosSistema=");
		builder.append(codeParametrosSistema);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", tipoDato=");
		builder.append(tipoDato);
		builder.append(", unidad=");
		builder.append(unidad);
		builder.append(", estado=");
		builder.append(estado);
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
