package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class ConfigCuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idConfCuenta;
	@Persistent
	private String codeConfCuenta;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent	
	@Unowned
	private Privacidad beanPrivacidadNovedad;
	@Persistent
	private String codePrivacidadNovedad;
	@Persistent	
	@Unowned
	private Privacidad beanPrivacidadInvitacion;
	@Persistent
	private String codePrivacidadInvitacion;
	@Persistent	
	@Unowned
	private Privacidad beanPrivacidadPerfil;
	@Persistent
	private String codePrivacidadPerfil;
	@Persistent	
	@Unowned
	private Idioma beanIdioma;
	@Persistent
	private String codeIdioma;
	@Persistent	
	@Unowned
	private UsuarioTurista beanTurista;
	@Persistent
	private String codeTurista;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdConfCuenta() {
		return idConfCuenta;
	}
	public void setIdConfCuenta(String idConfCuenta) {
		this.idConfCuenta=idConfCuenta;		
	}
	public String getCodeConfCuenta() {
		return codeConfCuenta;
	}
	public void setCodeConfCuenta(String codeConfCuenta) {
		this.codeConfCuenta = codeConfCuenta;
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
	public Privacidad getBeanPrivacidadNovedad() {
		return beanPrivacidadNovedad;
	}
	public void setBeanPrivacidadNovedad(Privacidad beanPrivacidadNovedad) {
		this.beanPrivacidadNovedad = beanPrivacidadNovedad;
	}
	public String getCodePrivacidadNovedad() {
		return codePrivacidadNovedad;
	}
	public void setCodePrivacidadNovedad(String codePrivacidadNovedad) {
		this.codePrivacidadNovedad = codePrivacidadNovedad;
	}
	public Privacidad getBeanPrivacidadInvitacion() {
		return beanPrivacidadInvitacion;
	}
	public void setBeanPrivacidadInvitacion(Privacidad beanPrivacidadInvitacion) {
		this.beanPrivacidadInvitacion = beanPrivacidadInvitacion;
	}
	public String getCodePrivacidadInvitacion() {
		return codePrivacidadInvitacion;
	}
	public void setCodePrivacidadInvitacion(String codePrivacidadInvitacion) {
		this.codePrivacidadInvitacion = codePrivacidadInvitacion;
	}
	public Privacidad getBeanPrivacidadPerfil() {
		return beanPrivacidadPerfil;
	}
	public void setBeanPrivacidadPerfil(Privacidad beanPrivacidadPerfil) {
		this.beanPrivacidadPerfil = beanPrivacidadPerfil;
	}
	public String getCodePrivacidadPerfil() {
		return codePrivacidadPerfil;
	}
	public void setCodePrivacidadPerfil(String codePrivacidadPerfil) {
		this.codePrivacidadPerfil = codePrivacidadPerfil;
	}
	public Idioma getBeanIdioma() {
		return beanIdioma;
	}
	public void setBeanIdioma(Idioma beanIdioma) {
		this.beanIdioma = beanIdioma;
	}
	public String getCodeIdioma() {
		return codeIdioma;
	}
	public void setCodeIdioma(String codeIdioma) {
		this.codeIdioma = codeIdioma;
	}
	public UsuarioTurista getBeanTurista() {
		return beanTurista;
	}
	public void setBeanTurista(UsuarioTurista beanTurista) {
		this.beanTurista = beanTurista;
	}
	public String getCodeTurista() {
		return codeTurista;
	}
	public void setCodeTurista(String codeTurista) {
		this.codeTurista = codeTurista;
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
		result = prime * result
				+ ((codeConfCuenta == null) ? 0 : codeConfCuenta.hashCode());
		result = prime * result
				+ ((idConfCuenta == null) ? 0 : idConfCuenta.hashCode());
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
		ConfigCuenta other = (ConfigCuenta) obj;
		if (codeConfCuenta == null) {
			if (other.codeConfCuenta != null)
				return false;
		} else if (!codeConfCuenta.equals(other.codeConfCuenta))
			return false;
		if (idConfCuenta == null) {
			if (other.idConfCuenta != null)
				return false;
		} else if (!idConfCuenta.equals(other.idConfCuenta))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigCuenta [idConfCuenta=");
		builder.append(idConfCuenta);
		builder.append(", codeConfCuenta=");
		builder.append(codeConfCuenta);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanPrivacidadNovedad=");
		builder.append(beanPrivacidadNovedad);
		builder.append(", codePrivacidadNovedad=");
		builder.append(codePrivacidadNovedad);
		builder.append(", beanPrivacidadInvitacion=");
		builder.append(beanPrivacidadInvitacion);
		builder.append(", codePrivacidadInvitacion=");
		builder.append(codePrivacidadInvitacion);
		builder.append(", beanPrivacidadPerfil=");
		builder.append(beanPrivacidadPerfil);
		builder.append(", codePrivacidadPerfil=");
		builder.append(codePrivacidadPerfil);
		builder.append(", beanIdioma=");
		builder.append(beanIdioma);
		builder.append(", codeIdioma=");
		builder.append(codeIdioma);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
