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
public class OfertaNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idOfertaNegocio;
	@Persistent
	private String codeOfertaNegocio;	
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocio;
	@Persistent
	private String codeNegocio;
	@Persistent
	private String nombreNegocio;
	@Persistent
	private String fotoPerfilNegocio;
	@Persistent
	private String descripcion;
	@Persistent
	private String fotoOferta;
	@Persistent
	private Double cantidadOfertada;
	@Persistent
	private Double cantidadOfertaReclamadas;
	@Persistent
	private Date fechaPlaneacion;	
	@Persistent
	private Date fechaLanzamiento;
	@Persistent
	private Date fechaVencimiento;
	@Persistent
	private String estadoOferta;
	@Persistent
	private Date fechaBaja;
	@Persistent
	@Unowned
	private TipoAnuncio beanTipoAnuncio;
	@Persistent
	private String codeTipoAnuncio;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdOfertaNegocio() {
		return idOfertaNegocio;
	}
	public void setIdOfertaNegocio(String idOfertaNegocio) {
		this.idOfertaNegocio=idOfertaNegocio;				
	}
	public String getCodeOfertaNegocio() {
		return codeOfertaNegocio;
	}
	public void setCodeOfertaNegocio(String codeOfertaNegocio) {
		this.codeOfertaNegocio = codeOfertaNegocio;
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
	public String getFotoOferta() {
		return fotoOferta;
	}
	public void setFotoOferta(String fotoOferta) {
		this.fotoOferta = fotoOferta;
	}
	public Double getCantidadOfertada() {
		return cantidadOfertada;
	}
	public void setCantidadOfertada(Double cantidadOfertada) {
		this.cantidadOfertada = cantidadOfertada;
	}
	public Double getCantidadOfertaReclamadas() {
		return cantidadOfertaReclamadas;
	}
	public void setCantidadOfertaReclamadas(Double cantidadOfertaReclamadas) {
		this.cantidadOfertaReclamadas = cantidadOfertaReclamadas;
	}
	public Date getFechaPlaneacion() {
		return fechaPlaneacion;
	}
	public void setFechaPlaneacion(Date fechaPlaneacion) {
		this.fechaPlaneacion = fechaPlaneacion;
	}
	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getEstadoOferta() {
		return estadoOferta;
	}
	public void setEstadoOferta(String estadoOferta) {
		this.estadoOferta = estadoOferta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
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
				+ ((codeOfertaNegocio == null) ? 0 : codeOfertaNegocio
						.hashCode());
		result = prime * result
				+ ((idOfertaNegocio == null) ? 0 : idOfertaNegocio.hashCode());
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
		OfertaNegocio other = (OfertaNegocio) obj;
		if (codeOfertaNegocio == null) {
			if (other.codeOfertaNegocio != null)
				return false;
		} else if (!codeOfertaNegocio.equals(other.codeOfertaNegocio))
			return false;
		if (idOfertaNegocio == null) {
			if (other.idOfertaNegocio != null)
				return false;
		} else if (!idOfertaNegocio.equals(other.idOfertaNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OfertaNegocio [idOfertaNegocio=");
		builder.append(idOfertaNegocio);
		builder.append(", codeOfertaNegocio=");
		builder.append(codeOfertaNegocio);
		builder.append(", beanNegocio=");
		builder.append(beanNegocio);
		builder.append(", codeNegocio=");
		builder.append(codeNegocio);
		builder.append(", nombreNegocio=");
		builder.append(nombreNegocio);
		builder.append(", fotoPerfilNegocio=");
		builder.append(fotoPerfilNegocio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fotoOferta=");
		builder.append(fotoOferta);
		builder.append(", cantidadOfertada=");
		builder.append(cantidadOfertada);
		builder.append(", cantidadOfertaReclamadas=");
		builder.append(cantidadOfertaReclamadas);
		builder.append(", fechaPlaneacion=");
		builder.append(fechaPlaneacion);
		builder.append(", fechaLanzamiento=");
		builder.append(fechaLanzamiento);
		builder.append(", fechaVencimiento=");
		builder.append(fechaVencimiento);
		builder.append(", estadoOferta=");
		builder.append(estadoOferta);
		builder.append(", fechaBaja=");
		builder.append(fechaBaja);
		builder.append(", beanTipoAnuncio=");
		builder.append(beanTipoAnuncio);
		builder.append(", codeTipoAnuncio=");
		builder.append(codeTipoAnuncio);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
