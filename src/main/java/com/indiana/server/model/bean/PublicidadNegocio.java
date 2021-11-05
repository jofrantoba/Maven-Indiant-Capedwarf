package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class PublicidadNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPublicidadNegocio;
	@Persistent
	private String codePublicidadNegocio;
	@Persistent
	private String descripcionPublicidad;
	@Persistent
	private String fotoPublicidad;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocio;
	@Persistent
	private String codeNegocio;
	@Persistent
	@Unowned
	private TipoAnuncio beanTipoAnuncio;
	@Persistent
	private String codeTipoAnuncio;
	@Persistent
	@Unowned
	private Grupo idGrupo;
	@Persistent
	private String codeGrupo;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdPublicidadNegocio() {
		return idPublicidadNegocio;
	}
	public void setIdPublicidadNegocio(String idPublicidadNegocio) {
		this.idPublicidadNegocio=idPublicidadNegocio;				
	}
	public String getCodePublicidadNegocio() {
		return codePublicidadNegocio;
	}
	public void setCodePublicidadNegocio(String codePublicidadNegocio) {
		this.codePublicidadNegocio = codePublicidadNegocio;
	}
	public String getDescripcionPublicidad() {
		return descripcionPublicidad;
	}
	public void setDescripcionPublicidad(String descripcionPublicidad) {
		this.descripcionPublicidad = descripcionPublicidad;
	}
	public String getFotoPublicidad() {
		return fotoPublicidad;
	}
	public void setFotoPublicidad(String fotoPublicidad) {
		this.fotoPublicidad = fotoPublicidad;
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
	public Grupo getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Grupo idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getCodeGrupo() {
		return codeGrupo;
	}
	public void setCodeGrupo(String codeGrupo) {
		this.codeGrupo = codeGrupo;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public UsuarioNegocio getBeanNegocio() {
		return beanNegocio;
	}
	public void setBeanNegocio(UsuarioNegocio beanNegocio) {
		this.beanNegocio = beanNegocio;
	}
	public String getCodeNegocio() {
		return codeNegocio;
	}
	public void setCodeNegocio(String codeNegocio) {
		this.codeNegocio = codeNegocio;
	}
	public TipoAnuncio getBeanTipoAnuncio() {
		return beanTipoAnuncio;
	}
	public void setBeanTipoAnuncio(TipoAnuncio beanTipoAnuncio) {
		this.beanTipoAnuncio = beanTipoAnuncio;
	}
	public String getCodeTipoAnuncio() {
		return codeTipoAnuncio;
	}
	public void setCodeTipoAnuncio(String codeTipoAnuncio) {
		this.codeTipoAnuncio = codeTipoAnuncio;
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
				+ ((codePublicidadNegocio == null) ? 0 : codePublicidadNegocio
						.hashCode());
		result = prime
				* result
				+ ((idPublicidadNegocio == null) ? 0 : idPublicidadNegocio
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
		PublicidadNegocio other = (PublicidadNegocio) obj;
		if (codePublicidadNegocio == null) {
			if (other.codePublicidadNegocio != null)
				return false;
		} else if (!codePublicidadNegocio.equals(other.codePublicidadNegocio))
			return false;
		if (idPublicidadNegocio == null) {
			if (other.idPublicidadNegocio != null)
				return false;
		} else if (!idPublicidadNegocio.equals(other.idPublicidadNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PublicidadNegocio [idPublicidadNegocio=");
		builder.append(idPublicidadNegocio);
		builder.append(", codePublicidadNegocio=");
		builder.append(codePublicidadNegocio);
		builder.append(", descripcionPublicidad=");
		builder.append(descripcionPublicidad);
		builder.append(", fotoPublicidad=");
		builder.append(fotoPublicidad);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNegocio=");
		builder.append(beanNegocio);
		builder.append(", codeNegocio=");
		builder.append(codeNegocio);
		builder.append(", beanTipoAnuncio=");
		builder.append(beanTipoAnuncio);
		builder.append(", codeTipoAnuncio=");
		builder.append(codeTipoAnuncio);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
