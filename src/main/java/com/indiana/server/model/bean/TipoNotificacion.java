package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class TipoNotificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTipoNotificacion;
	@Persistent
	private String codeTipoNotificacion;
	@Persistent
	private String descripcion;
	@Persistent
	private String mensaje;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdTipoNotificacion() {
		return idTipoNotificacion;
	}
	public void setIdTipoNotificacion(String idTipoNotificacion) {		
		this.idTipoNotificacion = idTipoNotificacion;		
	}	
	public String getCodeTipoNotificacion() {
		return codeTipoNotificacion;
	}
	public void setCodeTipoNotificacion(String codeTipoNotificacion) {
		this.codeTipoNotificacion = codeTipoNotificacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
				+ ((codeTipoNotificacion == null) ? 0 : codeTipoNotificacion
						.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((idTipoNotificacion == null) ? 0 : idTipoNotificacion
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
		TipoNotificacion other = (TipoNotificacion) obj;
		if (codeTipoNotificacion == null) {
			if (other.codeTipoNotificacion != null)
				return false;
		} else if (!codeTipoNotificacion.equals(other.codeTipoNotificacion))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idTipoNotificacion == null) {
			if (other.idTipoNotificacion != null)
				return false;
		} else if (!idTipoNotificacion.equals(other.idTipoNotificacion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoNotificacion [idTipoNotificacion=");
		builder.append(idTipoNotificacion);
		builder.append(", codeTipoNotificacion=");
		builder.append(codeTipoNotificacion);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", mensaje=");
		builder.append(mensaje);
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
