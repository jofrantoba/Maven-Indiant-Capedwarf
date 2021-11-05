package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Amistad implements Serializable{
	
	private static final long serialVersionUID = 4682238130099045328L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idAmistad;
	@Persistent
	private String codeAmistad;
	@Persistent
	private String codUnicoAmistad;
	@Persistent
	private java.util.Date fechaInicio;
	@Persistent
	private java.util.Date fechaFin;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private EstadoAmistad beanEstadoAmistad;
	@Persistent
	private String codeEstadoAmistad;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaPrincipal;
	@Persistent
	private String codeTuristaPrincipal;
	@Persistent
	private String nombreTuristaPrincipal;
	@Persistent
	private String apellidoTuristaPrincipal;
	@Persistent
	private String fotoPerfilTuristaPrincipal;
	@Persistent
	@Unowned
	private Pais beanPaisNacionalidadPrincipal;
	@Persistent
	private String codePaisNacionalidadPrincipal;
	@Persistent	
	private String nombrePaisPrincipal;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaAmigo;
	@Persistent
	private String codeTuristaAmigo;
	@Persistent
	private String nombreTuristaAmigo;
	@Persistent
	private String apellidoTuristaAmigo;
	@Persistent
	private String fotoPerfilTuristaAmigo;
	@Persistent
	@Unowned
	private Pais beanPaisNacionalidadAmigo;
	@Persistent
	private String codePaisNacionalidadAmigo;
	@Persistent	
	private String nombrePaisAmigo;
	@Persistent
	private String codeRegionAmigo;
	@Persistent	
	private String nombreRegionAmigo;
	@Persistent
	@Unowned
	private SolicitudAmistad beanSolicitudAmistad;
	@Persistent
	private String codeSolicitudAmistad;
	@Persistent
	private Boolean isPersistente;
	@NotPersistent
	private Boolean enComun;
	@NotPersistent
	private Double calculoDistancia;
	
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	public String getIdAmistad() {
		return idAmistad;
	}
	public void setIdAmistad(String idAmistad) {
		this.idAmistad=idAmistad;		
	}
	public String getCodeAmistad() {
		return codeAmistad;
	}
	public void setCodeAmistad(String codeAmistad) {
		this.codeAmistad = codeAmistad;
	}
	public String getCodUnicoAmistad() {
		return codUnicoAmistad;
	}
	public void setCodUnicoAmistad(String codUnicoAmistad) {
		this.codUnicoAmistad = codUnicoAmistad;
	}
	public java.util.Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(java.util.Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public java.util.Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(java.util.Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Long getVersion() {
		return version;
	}	
	public String getCodeRegionAmigo() {
		return codeRegionAmigo;
	}
	
	public Boolean getEnComun() {
		return enComun;
	}
	public void setEnComun(Boolean enComun) {
		this.enComun = enComun;
	}
	public void setCodeRegionAmigo(String codeRegionAmigo) {
		this.codeRegionAmigo = codeRegionAmigo;
	}
	public String getNombreRegionAmigo() {
		return nombreRegionAmigo;
	}
	public void setNombreRegionAmigo(String nombreRegionAmigo) {
		this.nombreRegionAmigo = nombreRegionAmigo;
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
	public EstadoAmistad getBeanEstadoAmistad() {
		return beanEstadoAmistad;
	}
	public void setBeanEstadoAmistad(EstadoAmistad beanEstadoAmistad) {
		this.beanEstadoAmistad = beanEstadoAmistad;
	}
	public String getCodeEstadoAmistad() {
		return codeEstadoAmistad;
	}
	public void setCodeEstadoAmistad(String codeEstadoAmistad) {
		this.codeEstadoAmistad = codeEstadoAmistad;
	}
	public UsuarioTurista getBeanTuristaPrincipal() {
		return beanTuristaPrincipal;
	}
	public void setBeanTuristaPrincipal(UsuarioTurista beanTuristaPrincipal) {
		this.beanTuristaPrincipal = beanTuristaPrincipal;
	}
	public String getCodeTuristaPrincipal() {
		return codeTuristaPrincipal;
	}
	public void setCodeTuristaPrincipal(String codeTuristaPrincipal) {
		this.codeTuristaPrincipal = codeTuristaPrincipal;
	}
	public String getNombreTuristaPrincipal() {
		return nombreTuristaPrincipal;
	}
	public void setNombreTuristaPrincipal(String nombreTuristaPrincipal) {
		this.nombreTuristaPrincipal = nombreTuristaPrincipal;
	}
	public String getApellidoTuristaPrincipal() {
		return apellidoTuristaPrincipal;
	}
	public void setApellidoTuristaPrincipal(String apellidoTuristaPrincipal) {
		this.apellidoTuristaPrincipal = apellidoTuristaPrincipal;
	}
	public String getFotoPerfilTuristaPrincipal() {
		return fotoPerfilTuristaPrincipal;
	}
	public void setFotoPerfilTuristaPrincipal(String fotoPerfilTuristaPrincipal) {
		this.fotoPerfilTuristaPrincipal = fotoPerfilTuristaPrincipal;
	}
	public Pais getBeanPaisNacionalidadPrincipal() {
		return beanPaisNacionalidadPrincipal;
	}
	public void setBeanPaisNacionalidadPrincipal(Pais beanPaisNacionalidadPrincipal) {
		this.beanPaisNacionalidadPrincipal = beanPaisNacionalidadPrincipal;
	}
	public String getCodePaisNacionalidadPrincipal() {
		return codePaisNacionalidadPrincipal;
	}
	public void setCodePaisNacionalidadPrincipal(
			String codePaisNacionalidadPrincipal) {
		this.codePaisNacionalidadPrincipal = codePaisNacionalidadPrincipal;
	}
	public String getNombrePaisPrincipal() {
		return nombrePaisPrincipal;
	}
	public void setNombrePaisPrincipal(String nombrePaisPrincipal) {
		this.nombrePaisPrincipal = nombrePaisPrincipal;
	}
	public UsuarioTurista getBeanTuristaAmigo() {
		return beanTuristaAmigo;
	}
	public void setBeanTuristaAmigo(UsuarioTurista beanTuristaAmigo) {
		this.beanTuristaAmigo = beanTuristaAmigo;
	}
	public String getCodeTuristaAmigo() {
		return codeTuristaAmigo;
	}
	public void setCodeTuristaAmigo(String codeTuristaAmigo) {
		this.codeTuristaAmigo = codeTuristaAmigo;
	}
	public String getNombreTuristaAmigo() {
		return nombreTuristaAmigo;
	}
	public void setNombreTuristaAmigo(String nombreTuristaAmigo) {
		this.nombreTuristaAmigo = nombreTuristaAmigo;
	}
	public String getApellidoTuristaAmigo() {
		return apellidoTuristaAmigo;
	}
	public void setApellidoTuristaAmigo(String apellidoTuristaAmigo) {
		this.apellidoTuristaAmigo = apellidoTuristaAmigo;
	}
	public String getFotoPerfilTuristaAmigo() {
		return fotoPerfilTuristaAmigo;
	}
	public void setFotoPerfilTuristaAmigo(String fotoPerfilTuristaAmigo) {
		this.fotoPerfilTuristaAmigo = fotoPerfilTuristaAmigo;
	}
	public Pais getBeanPaisNacionalidadAmigo() {
		return beanPaisNacionalidadAmigo;
	}
	public void setBeanPaisNacionalidadAmigo(Pais beanPaisNacionalidadAmigo) {
		this.beanPaisNacionalidadAmigo = beanPaisNacionalidadAmigo;
	}
	public String getCodePaisNacionalidadAmigo() {
		return codePaisNacionalidadAmigo;
	}
	public void setCodePaisNacionalidadAmigo(String codePaisNacionalidadAmigo) {
		this.codePaisNacionalidadAmigo = codePaisNacionalidadAmigo;
	}
	public String getNombrePaisAmigo() {
		return nombrePaisAmigo;
	}
	public void setNombrePaisAmigo(String nombrePaisAmigo) {
		this.nombrePaisAmigo = nombrePaisAmigo;
	}
	public SolicitudAmistad getBeanSolicitudAmistad() {
		return beanSolicitudAmistad;
	}
	public void setBeanSolicitudAmistad(SolicitudAmistad beanSolicitudAmistad) {
		this.beanSolicitudAmistad = beanSolicitudAmistad;
	}
	public String getCodeSolicitudAmistad() {
		return codeSolicitudAmistad;
	}
	public void setCodeSolicitudAmistad(String codeSolicitudAmistad) {
		this.codeSolicitudAmistad = codeSolicitudAmistad;
	}		
	public Double getCalculoDistancia() {
		return calculoDistancia;
	}
	public void setCalculoDistancia(Double calculoDistancia) {
		this.calculoDistancia = calculoDistancia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUnicoAmistad == null) ? 0 : codUnicoAmistad.hashCode());
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
		Amistad other = (Amistad) obj;
		if (codUnicoAmistad == null) {
			if (other.codUnicoAmistad != null)
				return false;
		} else if (!codUnicoAmistad.equals(other.codUnicoAmistad))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Amistad [idAmistad=" + idAmistad + ", codeAmistad=" + codeAmistad + ", codUnicoAmistad="
				+ codUnicoAmistad + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", version=" + version
				+ ", beanEstadoAmistad=" + beanEstadoAmistad + ", codeEstadoAmistad=" + codeEstadoAmistad
				+ ", beanTuristaPrincipal=" + beanTuristaPrincipal + ", codeTuristaPrincipal=" + codeTuristaPrincipal
				+ ", nombreTuristaPrincipal=" + nombreTuristaPrincipal + ", apellidoTuristaPrincipal="
				+ apellidoTuristaPrincipal + ", fotoPerfilTuristaPrincipal=" + fotoPerfilTuristaPrincipal
				+ ", beanPaisNacionalidadPrincipal=" + beanPaisNacionalidadPrincipal
				+ ", codePaisNacionalidadPrincipal=" + codePaisNacionalidadPrincipal + ", nombrePaisPrincipal="
				+ nombrePaisPrincipal + ", beanTuristaAmigo=" + beanTuristaAmigo + ", codeTuristaAmigo="
				+ codeTuristaAmigo + ", nombreTuristaAmigo=" + nombreTuristaAmigo + ", apellidoTuristaAmigo="
				+ apellidoTuristaAmigo + ", fotoPerfilTuristaAmigo=" + fotoPerfilTuristaAmigo
				+ ", beanPaisNacionalidadAmigo=" + beanPaisNacionalidadAmigo + ", codePaisNacionalidadAmigo="
				+ codePaisNacionalidadAmigo + ", nombrePaisAmigo=" + nombrePaisAmigo + ", codeRegionAmigo="
				+ codeRegionAmigo + ", nombreRegionAmigo=" + nombreRegionAmigo + ", beanSolicitudAmistad="
				+ beanSolicitudAmistad + ", codeSolicitudAmistad=" + codeSolicitudAmistad + ", isPersistente="
				+ isPersistente + ", enComun=" + enComun + "]";
	}
	
}
