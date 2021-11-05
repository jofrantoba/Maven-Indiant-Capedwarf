package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable="true")
public class NoticiaCompartida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idNoticiaCompartida;
	@Persistent
	private String codeNoticiaCompartida;	
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
	private UsuarioTurista beanTuristaComparte;
	@Persistent
	private String codeTurista;
	@Persistent
	private String nombreTuristaComparte;
	@Persistent
	private String apellidoTuristaComparte;
	@Persistent
	private String fotoPerfilTuristaComparte;	
	@Persistent
	private String codeMiembro;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdNoticiaCompartida() {
		return idNoticiaCompartida;
	}
	public void setIdNoticiaCompartida(String idNoticiaCompartida) {
		this.idNoticiaCompartida=idNoticiaCompartida;				
	}
	public String getCodeNoticiaCompartida() {
		return codeNoticiaCompartida;
	}
	public void setCodeNoticiaCompartida(String codeNoticiaCompartida) {
		this.codeNoticiaCompartida = codeNoticiaCompartida;
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
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
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
	public UsuarioTurista getBeanTuristaComparte() {
		return beanTuristaComparte;
	}
	public void setBeanTuristaComparte(UsuarioTurista beanTuristaComparte) {
		this.beanTuristaComparte = beanTuristaComparte;
	}
	public String getCodeTurista() {
		return codeTurista;
	}
	public void setCodeTurista(String codeTurista) {
		this.codeTurista = codeTurista;
	}
	public String getNombreTuristaComparte() {
		return nombreTuristaComparte;
	}
	public void setNombreTuristaComparte(String nombreTuristaComparte) {
		this.nombreTuristaComparte = nombreTuristaComparte;
	}
	public String getApellidoTuristaComparte() {
		return apellidoTuristaComparte;
	}
	public void setApellidoTuristaComparte(String apellidoTuristaComparte) {
		this.apellidoTuristaComparte = apellidoTuristaComparte;
	}
	public String getFotoPerfilTuristaComparte() {
		return fotoPerfilTuristaComparte;
	}
	public void setFotoPerfilTuristaComparte(String fotoPerfilTuristaComparte) {
		this.fotoPerfilTuristaComparte = fotoPerfilTuristaComparte;
	}	
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
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
				+ ((codeNoticiaCompartida == null) ? 0 : codeNoticiaCompartida
						.hashCode());
		result = prime
				* result
				+ ((idNoticiaCompartida == null) ? 0 : idNoticiaCompartida
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
		NoticiaCompartida other = (NoticiaCompartida) obj;
		if (codeNoticiaCompartida == null) {
			if (other.codeNoticiaCompartida != null)
				return false;
		} else if (!codeNoticiaCompartida.equals(other.codeNoticiaCompartida))
			return false;
		if (idNoticiaCompartida == null) {
			if (other.idNoticiaCompartida != null)
				return false;
		} else if (!idNoticiaCompartida.equals(other.idNoticiaCompartida))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoticiaCompartida [idNoticiaCompartida=");
		builder.append(idNoticiaCompartida);
		builder.append(", codeNoticiaCompartida=");
		builder.append(codeNoticiaCompartida);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNoticia=");
		builder.append(beanNoticia);
		builder.append(", codeNoticia=");
		builder.append(codeNoticia);
		builder.append(", beanTuristaComparte=");
		builder.append(beanTuristaComparte);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", nombreTuristaComparte=");
		builder.append(nombreTuristaComparte);
		builder.append(", apellidoTuristaComparte=");
		builder.append(apellidoTuristaComparte);
		builder.append(", fotoPerfilTuristaComparte=");
		builder.append(fotoPerfilTuristaComparte);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
