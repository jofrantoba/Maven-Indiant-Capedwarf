package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class DivulgacionNoticia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idDivulgacionNoticia;
	@Persistent
	private String codeDivulgacionNoticia;		
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaDivulga;
	@Persistent
	private String codeTuristaDivulgacion;	
	@Persistent
	private String nombreTuristaDivulgacion;
	@Persistent
	private String apellidoTuristaDivulgacion;
	@Persistent
	private String fotoPerfilTuristaDivulga;
	@Persistent
	private Noticia beanNoticia;
	@Persistent
	private String codeNoticia;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private java.util.Date fechaDivulgacion;	
	
	public String getIdDivulgacionNoticia() {
		return idDivulgacionNoticia;
	}
	public void setIdDivulgacionNoticia(String idDivulgacionNoticia) {
		this.idDivulgacionNoticia=idDivulgacionNoticia;				
	}
	public String getCodeDivulgacionNoticia() {
		return codeDivulgacionNoticia;
	}
	public void setCodeDivulgacionNoticia(String codeDivulgacionNoticia) {
		this.codeDivulgacionNoticia = codeDivulgacionNoticia;
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
	public UsuarioTurista getBeanTuristaDivulga() {
		return beanTuristaDivulga;
	}
	public void setBeanTuristaDivulga(UsuarioTurista beanTuristaDivulga) {
		this.beanTuristaDivulga = beanTuristaDivulga;
	}
	public String getCodeTuristaDivulgacion() {
		return codeTuristaDivulgacion;
	}
	public void setCodeTuristaDivulgacion(String codeTuristaDivulgacion) {
		this.codeTuristaDivulgacion = codeTuristaDivulgacion;
	}	
	public String getNombreTuristaDivulgacion() {
		return nombreTuristaDivulgacion;
	}
	public void setNombreTuristaDivulgacion(String nombreTuristaDivulgacion) {
		this.nombreTuristaDivulgacion = nombreTuristaDivulgacion;
	}
	public String getApellidoTuristaDivulgacion() {
		return apellidoTuristaDivulgacion;
	}
	public void setApellidoTuristaDivulgacion(String apellidoTuristaDivulgacion) {
		this.apellidoTuristaDivulgacion = apellidoTuristaDivulgacion;
	}
	public String getFotoPerfilTuristaDivulga() {
		return fotoPerfilTuristaDivulga;
	}
	public void setFotoPerfilTuristaDivulga(String fotoPerfilTuristaDivulga) {
		this.fotoPerfilTuristaDivulga = fotoPerfilTuristaDivulga;
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
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	
	public java.util.Date getFechaDivulgacion() {
		return fechaDivulgacion;
	}
	public void setFechaDivulgacion(java.util.Date fechaDivulgacion) {
		this.fechaDivulgacion = fechaDivulgacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeDivulgacionNoticia == null) ? 0
						: codeDivulgacionNoticia.hashCode());
		result = prime
				* result
				+ ((idDivulgacionNoticia == null) ? 0 : idDivulgacionNoticia
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
		DivulgacionNoticia other = (DivulgacionNoticia) obj;
		if (codeDivulgacionNoticia == null) {
			if (other.codeDivulgacionNoticia != null)
				return false;
		} else if (!codeDivulgacionNoticia.equals(other.codeDivulgacionNoticia))
			return false;
		if (idDivulgacionNoticia == null) {
			if (other.idDivulgacionNoticia != null)
				return false;
		} else if (!idDivulgacionNoticia.equals(other.idDivulgacionNoticia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DivulgacionNoticia [idDivulgacionNoticia=");
		builder.append(idDivulgacionNoticia);
		builder.append(", codeDivulgacionNoticia=");
		builder.append(codeDivulgacionNoticia);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaDivulga=");
		builder.append(beanTuristaDivulga);
		builder.append(", codeTuristaDivulgacion=");
		builder.append(codeTuristaDivulgacion);
		builder.append(", nombreTuristaDivulgacion=");
		builder.append(nombreTuristaDivulgacion);
		builder.append(", apellidoTuristaDivulgacion=");
		builder.append(apellidoTuristaDivulgacion);
		builder.append(", fotoPerfilTuristaDivulga=");
		builder.append(fotoPerfilTuristaDivulga);
		builder.append(", beanNoticia=");
		builder.append(beanNoticia);
		builder.append(", codeNoticia=");
		builder.append(codeNoticia);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", fechaDivulgacion=");
		builder.append(fechaDivulgacion);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
