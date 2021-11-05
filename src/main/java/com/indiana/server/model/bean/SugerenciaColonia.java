package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class SugerenciaColonia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey		
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idSugerenciaColonia;
	@Persistent
	private String codeSugerenciaColonia;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaEmisor;
	@Persistent
	private String nombreTuristaEmisor;
	@Persistent
	private String apellidoTuristaEmisor;
	@Persistent
	private String fotoPerfilTuristaEmisor;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaReceptor;
	@Persistent
	private String codeTuristaReceptor;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdSugerenciaColonia() {
		return idSugerenciaColonia;
	}
	public void setIdSugerenciaColonia(String idSugerenciaColonia) {
		this.idSugerenciaColonia=idSugerenciaColonia;				
	}
	public String getCodeSugerenciaColonia() {
		return codeSugerenciaColonia;
	}
	public void setCodeSugerenciaColonia(String codeSugerenciaColonia) {
		this.codeSugerenciaColonia = codeSugerenciaColonia;
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
	public UsuarioTurista getBeanTuristaEmisor() {
		return beanTuristaEmisor;
	}
	public void setBeanTuristaEmisor(UsuarioTurista beanTuristaEmisor) {
		this.beanTuristaEmisor = beanTuristaEmisor;
	}
	public String getNombreTuristaEmisor() {
		return nombreTuristaEmisor;
	}
	public void setNombreTuristaEmisor(String nombreTuristaEmisor) {
		this.nombreTuristaEmisor = nombreTuristaEmisor;
	}
	public String getApellidoTuristaEmisor() {
		return apellidoTuristaEmisor;
	}
	public void setApellidoTuristaEmisor(String apellidoTuristaEmisor) {
		this.apellidoTuristaEmisor = apellidoTuristaEmisor;
	}
	public String getFotoPerfilTuristaEmisor() {
		return fotoPerfilTuristaEmisor;
	}
	public void setFotoPerfilTuristaEmisor(String fotoPerfilTuristaEmisor) {
		this.fotoPerfilTuristaEmisor = fotoPerfilTuristaEmisor;
	}
	public UsuarioTurista getBeanTuristaReceptor() {
		return beanTuristaReceptor;
	}
	public void setBeanTuristaReceptor(UsuarioTurista beanTuristaReceptor) {
		this.beanTuristaReceptor = beanTuristaReceptor;
	}
	public String getCodeTuristaReceptor() {
		return codeTuristaReceptor;
	}
	public void setCodeTuristaReceptor(String codeTuristaReceptor) {
		this.codeTuristaReceptor = codeTuristaReceptor;
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
				+ ((codeSugerenciaColonia == null) ? 0 : codeSugerenciaColonia
						.hashCode());
		result = prime
				* result
				+ ((idSugerenciaColonia == null) ? 0 : idSugerenciaColonia
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
		SugerenciaColonia other = (SugerenciaColonia) obj;
		if (codeSugerenciaColonia == null) {
			if (other.codeSugerenciaColonia != null)
				return false;
		} else if (!codeSugerenciaColonia.equals(other.codeSugerenciaColonia))
			return false;
		if (idSugerenciaColonia == null) {
			if (other.idSugerenciaColonia != null)
				return false;
		} else if (!idSugerenciaColonia.equals(other.idSugerenciaColonia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SugerenciaColonia [idSugerenciaColonia=");
		builder.append(idSugerenciaColonia);
		builder.append(", codeSugerenciaColonia=");
		builder.append(codeSugerenciaColonia);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaEmisor=");
		builder.append(beanTuristaEmisor);
		builder.append(", nombreTuristaEmisor=");
		builder.append(nombreTuristaEmisor);
		builder.append(", apellidoTuristaEmisor=");
		builder.append(apellidoTuristaEmisor);
		builder.append(", fotoPerfilTuristaEmisor=");
		builder.append(fotoPerfilTuristaEmisor);
		builder.append(", beanTuristaReceptor=");
		builder.append(beanTuristaReceptor);
		builder.append(", codeTuristaReceptor=");
		builder.append(codeTuristaReceptor);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
