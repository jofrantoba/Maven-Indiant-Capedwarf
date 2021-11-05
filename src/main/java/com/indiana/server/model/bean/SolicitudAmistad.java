package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class SolicitudAmistad implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idSolicitudAmistad;
	@Persistent
	private String codeSolicitudAmistad;
	@Persistent
	private Date fecha;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private EstadoSolicAmistad beanEstadoSolicAmistad;
	@Persistent
	private String codeEstadoSolicAmistad;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaEnvia;
	@Persistent
	private String codeTuristaEnvia;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaRecibe;
	@Persistent
	private String codeTuristaRecibe;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String estadoTarea;
	
	public String getIdSolicitudAmistad() {
		return idSolicitudAmistad;
	}
	public void setIdSolicitudAmistad(String idSolicitudAmistad) {
		this.idSolicitudAmistad=idSolicitudAmistad;				
	}
	public String getCodeSolicitudAmistad() {
		return codeSolicitudAmistad;
	}
	public void setCodeSolicitudAmistad(String codeSolicitudAmistad) {
		this.codeSolicitudAmistad = codeSolicitudAmistad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public EstadoSolicAmistad getBeanEstadoSolicAmistad() {
		return beanEstadoSolicAmistad;
	}
	public void setBeanEstadoSolicAmistad(EstadoSolicAmistad beanEstadoSolicAmistad) {
		this.beanEstadoSolicAmistad = beanEstadoSolicAmistad;
	}
	public String getCodeEstadoSolicAmistad() {
		return codeEstadoSolicAmistad;
	}
	public void setCodeEstadoSolicAmistad(String codeEstadoSolicAmistad) {
		this.codeEstadoSolicAmistad = codeEstadoSolicAmistad;
	}
	public UsuarioTurista getBeanTuristaEnvia() {
		return beanTuristaEnvia;
	}		
	public String getEstadoTarea() {
		return estadoTarea;
	}
	public void setEstadoTarea(String estadoTarea) {
		this.estadoTarea = estadoTarea;
	}
	public void setBeanTuristaEnvia(UsuarioTurista beanTuristaEnvia) {
		this.beanTuristaEnvia = beanTuristaEnvia;
	}
	public String getCodeTuristaEnvia() {
		return codeTuristaEnvia;
	}
	public void setCodeTuristaEnvia(String codeTuristaEnvia) {
		this.codeTuristaEnvia = codeTuristaEnvia;
	}
	public UsuarioTurista getBeanTuristaRecibe() {
		return beanTuristaRecibe;
	}
	public void setBeanTuristaRecibe(UsuarioTurista beanTuristaRecibe) {
		this.beanTuristaRecibe = beanTuristaRecibe;
	}
	public String getCodeTuristaRecibe() {
		return codeTuristaRecibe;
	}
	public void setCodeTuristaRecibe(String codeTuristaRecibe) {
		this.codeTuristaRecibe = codeTuristaRecibe;
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
				+ ((codeSolicitudAmistad == null) ? 0 : codeSolicitudAmistad
						.hashCode());
		result = prime
				* result
				+ ((idSolicitudAmistad == null) ? 0 : idSolicitudAmistad
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
		SolicitudAmistad other = (SolicitudAmistad) obj;
		if (codeSolicitudAmistad == null) {
			if (other.codeSolicitudAmistad != null)
				return false;
		} else if (!codeSolicitudAmistad.equals(other.codeSolicitudAmistad))
			return false;
		if (idSolicitudAmistad == null) {
			if (other.idSolicitudAmistad != null)
				return false;
		} else if (!idSolicitudAmistad.equals(other.idSolicitudAmistad))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SolicitudAmistad [idSolicitudAmistad=");
		builder.append(idSolicitudAmistad);
		builder.append(", codeSolicitudAmistad=");
		builder.append(codeSolicitudAmistad);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanEstadoSolicAmistad=");
		builder.append(beanEstadoSolicAmistad);
		builder.append(", codeEstadoSolicAmistad=");
		builder.append(codeEstadoSolicAmistad);
		builder.append(", beanTuristaEnvia=");
		builder.append(beanTuristaEnvia);
		builder.append(", codeTuristaEnvia=");
		builder.append(codeTuristaEnvia);
		builder.append(", beanTuristaRecibe=");
		builder.append(beanTuristaRecibe);
		builder.append(", codeTuristaRecibe=");
		builder.append(codeTuristaRecibe);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
			
}
