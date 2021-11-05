package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable="true")
public class ComparteNoticia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idComparteNoticia;
	@Persistent
	private String codeComparteNoticia;
	@Persistent
	private String mensaje;			
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private Noticia beanNoticia;
	@Persistent
	private String codeNoticia;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaComparte;
	@Persistent
	private String codeTuristaComparte;	
	@Persistent
	private String nombreTuristaComparte;
	@Persistent
	private String ApellidoTuristaComparte;
	@Persistent
	private String fotoPerfilTuristaComparte;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdComparteNoticia() {
		return idComparteNoticia;
	}
	public void setIdComparteNoticia(String idComparteNoticia) {
		this.idComparteNoticia = idComparteNoticia;
	}
	public String getCodeComparteNoticia() {
		return codeComparteNoticia;
	}
	public void setCodeComparteNoticia(String codeComparteNoticia) {
		this.codeComparteNoticia = codeComparteNoticia;
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
	public Noticia getBeanNoticia() {
		return beanNoticia;
	}
	public void setBeanNoticia(Noticia beanNoticia) {
		this.beanNoticia = beanNoticia;
	}
	public String getCodeNoticia() {
		return codeNoticia;
	}
	public void setCodeNoticia(String codeNoticia) {
		this.codeNoticia = codeNoticia;
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
	public UsuarioTurista getBeanTuristaComparte() {
		return beanTuristaComparte;
	}
	public void setBeanTuristaComparte(UsuarioTurista beanTuristaComparte) {
		this.beanTuristaComparte = beanTuristaComparte;
	}
	public String getCodeTuristaComparte() {
		return codeTuristaComparte;
	}
	public void setCodeTuristaComparte(String codeTuristaComparte) {
		this.codeTuristaComparte = codeTuristaComparte;
	}
	public String getNombreTuristaComparte() {
		return nombreTuristaComparte;
	}
	public void setNombreTuristaComparte(String nombreTuristaComparte) {
		this.nombreTuristaComparte = nombreTuristaComparte;
	}
	public String getApellidoTuristaComparte() {
		return ApellidoTuristaComparte;
	}
	public void setApellidoTuristaComparte(String apellidoTuristaComparte) {
		ApellidoTuristaComparte = apellidoTuristaComparte;
	}
	public String getFotoPerfilTuristaComparte() {
		return fotoPerfilTuristaComparte;
	}
	public void setFotoPerfilTuristaComparte(String fotoPerfilTuristaComparte) {
		this.fotoPerfilTuristaComparte = fotoPerfilTuristaComparte;
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
		result = prime * result + ((codeComparteNoticia == null) ? 0 : codeComparteNoticia.hashCode());
		result = prime * result + ((idComparteNoticia == null) ? 0 : idComparteNoticia.hashCode());
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
		ComparteNoticia other = (ComparteNoticia) obj;
		if (codeComparteNoticia == null) {
			if (other.codeComparteNoticia != null)
				return false;
		} else if (!codeComparteNoticia.equals(other.codeComparteNoticia))
			return false;
		if (idComparteNoticia == null) {
			if (other.idComparteNoticia != null)
				return false;
		} else if (!idComparteNoticia.equals(other.idComparteNoticia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComparteNoticia [idComparteNoticia=");
		builder.append(idComparteNoticia);
		builder.append(", codeComparteNoticia=");
		builder.append(codeComparteNoticia);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNoticia=");
		builder.append(beanNoticia);
		builder.append(", codeNoticia=");
		builder.append(codeNoticia);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", beanTuristaComparte=");
		builder.append(beanTuristaComparte);
		builder.append(", codeTuristaComparte=");
		builder.append(codeTuristaComparte);
		builder.append(", nombreTuristaComparte=");
		builder.append(nombreTuristaComparte);
		builder.append(", ApellidoTuristaComparte=");
		builder.append(ApellidoTuristaComparte);
		builder.append(", fotoPerfilTuristaComparte=");
		builder.append(fotoPerfilTuristaComparte);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
