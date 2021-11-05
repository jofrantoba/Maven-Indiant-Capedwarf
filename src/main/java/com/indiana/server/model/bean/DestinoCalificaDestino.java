package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(detachable="true")
public class DestinoCalificaDestino implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idDestinoCalificaDestino;
	@Persistent	
	private String codeDestinoCalificaDestino;	
	@Persistent	
	private String codeDestino;
	@Persistent	
	private String codeCalificaDestino;
	@Persistent	
	private String codeTurista;	
	@Persistent
	private String nombresTurista;
	@Persistent
	private String apellidosTurista;
	@Persistent
	private String fotoPerfilTuristaComenta;
	@Persistent
	private java.util.Date fechaTuristaComenta;
	@Persistent	
	private Integer calificacion;
	@Persistent	
	private String opinion;		
	@Persistent
	private Long version;
	
	public String getIdDestinoCalificaDestino() {
		return idDestinoCalificaDestino;
	}
	public void setIdDestinoCalificaDestino(String idDestinoCalificaDestino) {
		this.idDestinoCalificaDestino = idDestinoCalificaDestino;
	}
	public String getCodeDestinoCalificaDestino() {
		return codeDestinoCalificaDestino;
	}
	public void setCodeDestinoCalificaDestino(String codeDestinoCalificaDestino) {
		this.codeDestinoCalificaDestino = codeDestinoCalificaDestino;
	}
	public String getCodeDestino() {
		return codeDestino;
	}
	public void setCodeDestino(String codeDestino) {
		this.codeDestino = codeDestino;
	}
	public String getCodeCalificaDestino() {
		return codeCalificaDestino;
	}		
	public String getFotoPerfilTuristaComenta() {
		return fotoPerfilTuristaComenta;
	}
	public void setFotoPerfilTuristaComenta(String fotoPerfilTuristaComenta) {
		this.fotoPerfilTuristaComenta = fotoPerfilTuristaComenta;
	}
	public java.util.Date getFechaTuristaComenta() {
		return fechaTuristaComenta;
	}
	public void setFechaTuristaComenta(java.util.Date fechaTuristaComenta) {
		this.fechaTuristaComenta = fechaTuristaComenta;
	}
	public void setCodeCalificaDestino(String codeCalificaDestino) {
		this.codeCalificaDestino = codeCalificaDestino;
	}
	public String getCodeTurista() {
		return codeTurista;
	}
	public void setCodeTurista(String codeTurista) {
		this.codeTurista = codeTurista;
	}
	public String getNombresTurista() {
		return nombresTurista;
	}
	public void setNombresTurista(String nombresTurista) {
		this.nombresTurista = nombresTurista;
	}
	public String getApellidosTurista() {
		return apellidosTurista;
	}
	public void setApellidosTurista(String apellidosTurista) {
		this.apellidosTurista = apellidosTurista;
	}
	public Integer getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	
}