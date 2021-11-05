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
public class UsuarioNegocio implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idUsuarioNegocio;
	@Persistent
	private String codeUsuarioNegocio ;
	@Persistent
	private String correoNegocio;
	@Persistent
	private String nombreNegocio;
	@Persistent
	private String fotoPerfilNegocio;	
	@Persistent
	private String descripcion;		
	@Persistent
	private Double longitud;
	@Persistent
	private Double latitud;
	@Persistent
	private Double radio;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private String codePais;
	@Persistent
	private String nombrePais;
	@Persistent
	@Unowned
	private Region beanRegion;
	@Persistent
	private String codeRegion;
	@Persistent
	private String nombreRegion;
	@Persistent
	@Unowned
	private Localidad beanLocalidad;
	@Persistent
	private String codeLocalidad;
	@Persistent
	private String nombreLocalidad;
	@Persistent
	private String telefono;
	@Persistent
	private Date fechaCreacion;
	@Persistent
	@Unowned
	private CategoriaNegocio beanCategoriaNegocio;
	@Persistent
	private String codeCategoriaNegocio;
	@Persistent
	private String nombreCategoriaNegocio;
	@Persistent
	private Integer totalConquistas;
	@Persistent
	private Integer totalOfertas;
	@Persistent
	private Double mediaValoracion;	
	@Persistent
	@Unowned
	private CuentaNegocio beanCuentaNegocio;
	@Persistent
	@Unowned
	private Campania beanCampania;
	@Persistent	
	private String codeCampania;
	@Persistent
	private Boolean isPersistente;
	@NotPersistent
	private String operacion;
	@Persistent
	private Long version;
	@Persistent
	private Double calculoDistancia;
	
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	public Double getCalculoDistancia() {
		return calculoDistancia;
	}
	public void setCalculoDistancia(Double calculoDistancia) {
		this.calculoDistancia = calculoDistancia;
	}
	public String getIdUsuarioNegocio() {
		return idUsuarioNegocio;
	}
	public void setIdUsuarioNegocio(String idUsuarioNegocio) {
		this.idUsuarioNegocio= idUsuarioNegocio;				
	}
	public String getCodeUsuarioNegocio() {
		return codeUsuarioNegocio;
	}
	public void setCodeUsuarioNegocio(String codeUsuarioNegocio) {
		this.codeUsuarioNegocio = codeUsuarioNegocio;
	}
	public String getCorreoNegocio() {
		return correoNegocio;
	}
	public void setCorreoNegocio(String correoNegocio) {
		this.correoNegocio = correoNegocio;
	}		
	public Campania getBeanCampania() {
		return beanCampania;
	}
	public void setBeanCampania(Campania beanCampania) {
		this.beanCampania = beanCampania;
	}
	public String getCodeCampania() {
		return codeCampania;
	}
	public void setCodeCampania(String codeCampania) {
		this.codeCampania = codeCampania;
	}
	public String getNombreNegocio() {
		return nombreNegocio;
	}
	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}
	public String getFotoPerfilNegocio() {
		return fotoPerfilNegocio;
	}
	public void setFotoPerfilNegocio(String fotoPerfilNegocio) {
		this.fotoPerfilNegocio = fotoPerfilNegocio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getRadio() {
		return radio;
	}
	public void setRadio(Double radio) {
		this.radio = radio;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
	}
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public Region getBeanRegion() {
		return beanRegion;
	}
	public void setBeanRegion(Region beanRegion) {
		this.beanRegion = beanRegion;
	}
	public String getCodeRegion() {
		return codeRegion;
	}
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}
	public Localidad getBeanLocalidad() {
		return beanLocalidad;
	}
	public void setBeanLocalidad(Localidad beanLocalidad) {
		this.beanLocalidad = beanLocalidad;
	}
	public String getCodeLocalidad() {
		return codeLocalidad;
	}
	public void setCodeLocalidad(String codeLocalidad) {
		this.codeLocalidad = codeLocalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public CategoriaNegocio getBeanCategoriaNegocio() {
		return beanCategoriaNegocio;
	}
	public void setBeanCategoriaNegocio(CategoriaNegocio beanCategoriaNegocio) {
		this.beanCategoriaNegocio = beanCategoriaNegocio;
	}
	public String getCodeCategoriaNegocio() {
		return codeCategoriaNegocio;
	}
	public void setCodeCategoriaNegocio(String codeCategoriaNegocio) {
		this.codeCategoriaNegocio = codeCategoriaNegocio;
	}
	public String getNombreCategoriaNegocio() {
		return nombreCategoriaNegocio;
	}
	public void setNombreCategoriaNegocio(String nombreCategoriaNegocio) {
		this.nombreCategoriaNegocio = nombreCategoriaNegocio;
	}
	public Integer getTotalConquistas() {
		return totalConquistas;
	}
	public void setTotalConquistas(Integer totalConquistas) {
		this.totalConquistas = totalConquistas;
	}
	public Integer getTotalOfertas() {
		return totalOfertas;
	}
	public void setTotalOfertas(Integer totalOfertas) {
		this.totalOfertas = totalOfertas;
	}
	public Double getMediaValoracion() {
		return mediaValoracion;
	}
	public void setMediaValoracion(Double mediaValoracion) {
		this.mediaValoracion = mediaValoracion;
	}
	
	public CuentaNegocio getBeanCuentaNegocio() {
		return beanCuentaNegocio;
	}
	public void setBeanCuentaNegocio(CuentaNegocio beanCuentaNegocio) {
		this.beanCuentaNegocio = beanCuentaNegocio;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}		
	
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime* result+ ((codeUsuarioNegocio == null) ? 0 : codeUsuarioNegocio.hashCode());
		result = prime * result+ ((correoNegocio == null) ? 0 : correoNegocio.hashCode());
		result = prime* result+ ((idUsuarioNegocio == null) ? 0 : idUsuarioNegocio.hashCode());
		
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
		UsuarioNegocio other = (UsuarioNegocio) obj;
		if (codeUsuarioNegocio == null) {
			if (other.codeUsuarioNegocio != null)
				return false;
		} else if (!codeUsuarioNegocio.equals(other.codeUsuarioNegocio))
			return false;
		if (correoNegocio == null) {
			if (other.correoNegocio != null)
				return false;
		} else if (!correoNegocio.equals(other.correoNegocio))
			return false;
		if (idUsuarioNegocio == null) {
			if (other.idUsuarioNegocio != null)
				return false;
		} else if (!idUsuarioNegocio.equals(other.idUsuarioNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioNegocio [idUsuarioNegocio=");
		builder.append(idUsuarioNegocio);
		builder.append(", codeUsuarioNegocio=");
		builder.append(codeUsuarioNegocio);
		builder.append(", correoNegocio=");
		builder.append(correoNegocio);
		builder.append(", nombreNegocio=");
		builder.append(nombreNegocio);
		builder.append(", fotoPerfilNegocio=");
		builder.append(fotoPerfilNegocio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", radio=");
		builder.append(radio);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", beanRegion=");
		builder.append(beanRegion);
		builder.append(", codeRegion=");
		builder.append(codeRegion);
		builder.append(", beanLocalidad=");
		builder.append(beanLocalidad);
		builder.append(", codeLocalidad=");
		builder.append(codeLocalidad);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", beanCategoriaNegocio=");
		builder.append(beanCategoriaNegocio);
		builder.append(", codeCategoriaNegocio=");
		builder.append(codeCategoriaNegocio);
		builder.append(", nombreCategoriaNegocio=");
		builder.append(nombreCategoriaNegocio);
		builder.append(", totalConquistas=");
		builder.append(totalConquistas);
		builder.append(", totalOfertas=");
		builder.append(totalOfertas);
		builder.append(", mediaValoracion=");
		builder.append(mediaValoracion);
		builder.append(", beanCuentaNegocio=");
		builder.append(beanCuentaNegocio);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
}
