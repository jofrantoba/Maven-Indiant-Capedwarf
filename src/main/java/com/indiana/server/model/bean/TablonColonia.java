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
public class TablonColonia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey		
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTablonColonia;
	@Persistent
	private String codeTablonColonia;
	@Persistent
	private String mensaje;	
	@Persistent
	private String linkFotoMensaje;	
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaTablon;
	@Persistent
	private String codeTuristaTablon;
	@Persistent
	private String nombreTuristaTablon;
	@Persistent
	private String apellidoTuristaTablon;
	@Persistent
	private String fotoPerfilTuristaTablon;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;
	@Persistent
	private Date fechaPublicacionTablon;
	@Persistent
	@Unowned
	private Miembro beanMiembro;
	@Persistent
	private String codeMiembro;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Integer numeroParticipante;
	@Persistent
	private String estadoTablon;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String tokenFirebase;
	@Persistent
	private String linkVideoMensaje;
	
	public String getIdTablonColonia() {
		return idTablonColonia;
	}
	public void setIdTablonColonia(String idTablonColonia) {
		this.idTablonColonia=idTablonColonia;				
	}
	public String getCodeTablonColonia() {
		return codeTablonColonia;
	}
	public void setCodeTablonColonia(String codeTablonColonia) {
		this.codeTablonColonia = codeTablonColonia;
	}	
	public String getLinkFotoMensaje() {
		return linkFotoMensaje;
	}
	public void setLinkFotoMensaje(String linkFotoMensaje) {
		this.linkFotoMensaje = linkFotoMensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public UsuarioTurista getBeanTuristaTablon() {
		return beanTuristaTablon;
	}
	public void setBeanTuristaTablon(UsuarioTurista beanTuristaTablon) {
		this.beanTuristaTablon = beanTuristaTablon;
	}
	public String getCodeTuristaTablon() {
		return codeTuristaTablon;
	}
	public void setCodeTuristaTablon(String codeTuristaTablon) {
		this.codeTuristaTablon = codeTuristaTablon;
	}
	public String getNombreTuristaTablon() {
		return nombreTuristaTablon;
	}
	public void setNombreTuristaTablon(String nombreTuristaTablon) {
		this.nombreTuristaTablon = nombreTuristaTablon;
	}
	public String getApellidoTuristaTablon() {
		return apellidoTuristaTablon;
	}
	public void setApellidoTuristaTablon(String apellidoTuristaTablon) {
		this.apellidoTuristaTablon = apellidoTuristaTablon;
	}
	public String getFotoPerfilTuristaTablon() {
		return fotoPerfilTuristaTablon;
	}
	public void setFotoPerfilTuristaTablon(String fotoPerfilTuristaTablon) {
		this.fotoPerfilTuristaTablon = fotoPerfilTuristaTablon;
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
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}
	public Date getFechaPublicacionTablon() {
		return fechaPublicacionTablon;
	}
	public void setFechaPublicacionTablon(Date fechaPublicacionTablon) {
		this.fechaPublicacionTablon = fechaPublicacionTablon;
	}
	public Miembro getBeanMiembro() {
		return beanMiembro;
	}
	public void setBeanMiembro(Miembro beanMiembro) {
		this.beanMiembro = beanMiembro;
	}
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
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
	public Integer getNumeroParticipante() {
		return numeroParticipante;
	}
	public void setNumeroParticipante(Integer numeroParticipante) {
		this.numeroParticipante = numeroParticipante;
	}		
	public String getLinkVideoMensaje() {
		return linkVideoMensaje;
	}
	public void setLinkVideoMensaje(String linkVideoMensaje) {
		this.linkVideoMensaje = linkVideoMensaje;
	}
	public String getEstadoTablon() {
		return estadoTablon;
	}
	public void setEstadoTablon(String estadoTablon) {
		this.estadoTablon = estadoTablon;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}		
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeTablonColonia == null) ? 0 : codeTablonColonia
						.hashCode());
		result = prime * result
				+ ((idTablonColonia == null) ? 0 : idTablonColonia.hashCode());
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
		TablonColonia other = (TablonColonia) obj;
		if (codeTablonColonia == null) {
			if (other.codeTablonColonia != null)
				return false;
		} else if (!codeTablonColonia.equals(other.codeTablonColonia))
			return false;
		if (idTablonColonia == null) {
			if (other.idTablonColonia != null)
				return false;
		} else if (!idTablonColonia.equals(other.idTablonColonia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TablonColonia [idTablonColonia=");
		builder.append(idTablonColonia);
		builder.append(", codeTablonColonia=");
		builder.append(codeTablonColonia);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append(", beanTuristaTablon=");
		builder.append(beanTuristaTablon);
		builder.append(", codeTuristaTablon=");
		builder.append(codeTuristaTablon);
		builder.append(", nombreTuristaTablon=");
		builder.append(nombreTuristaTablon);
		builder.append(", apellidoTuristaTablon=");
		builder.append(apellidoTuristaTablon);
		builder.append(", fotoPerfilTuristaTablon=");
		builder.append(fotoPerfilTuristaTablon);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
		builder.append(", fechaPublicacionTablon=");
		builder.append(fechaPublicacionTablon);
		builder.append(", beanMiembro=");
		builder.append(beanMiembro);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", numeroParticipante=");
		builder.append(numeroParticipante);
		builder.append(", estadoTablon=");
		builder.append(estadoTablon);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	}
