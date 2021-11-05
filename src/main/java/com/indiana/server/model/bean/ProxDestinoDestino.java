package com.indiana.server.model.bean;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class ProxDestinoDestino {
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idProxDestinoDestino;
	@Persistent
	private String codeProxDestinoDestino;
	@Persistent
	private Destino beanDestino;
	@Persistent
	private String codeDestino;
	@Persistent
	private Double latitudDestino;
	@Persistent
	private Double longitudDestino;
	@Persistent
	private Double radioDestino;	
	@Persistent
	private String codeDestinoNegocio;
	@Persistent
	private String nombreDestinoNegocio;
	@Persistent
	private String fotoDestinoNegocio;
	@Persistent
	private Double latitudDestinoNegocio;
	@Persistent
	private Double longitudDestinoNegocio;
	@Persistent
	private Double radioDestinoNegocio;
	@Persistent
	private Double distanciaCalculada;
	@Persistent
	private Integer orden;
	@Persistent
	private Long version;
	@Persistent
	private String tipo; //N: NEGOCIO  O D: DESTINO
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdProxDestinoDestino() {
		return idProxDestinoDestino;
	}
	public void setIdProxDestinoDestino(String idProxDestinoDestino) {
		this.idProxDestinoDestino = idProxDestinoDestino;
	}
	public String getCodeProxDestinoDestino() {
		return codeProxDestinoDestino;
	}
	public void setCodeProxDestinoDestino(String codeProxDestinoDestino) {
		this.codeProxDestinoDestino = codeProxDestinoDestino;
	}
	public Destino getBeanDestino() {
		return beanDestino;
	}
	public void setBeanDestino(Destino beanDestino) {
		this.beanDestino = beanDestino;
	}
	public String getCodeDestino() {
		return codeDestino;
	}
	public void setCodeDestino(String codeDestino) {
		this.codeDestino = codeDestino;
	}
	public Double getLatitudDestino() {
		return latitudDestino;
	}
	public void setLatitudDestino(Double latitudDestino) {
		this.latitudDestino = latitudDestino;
	}
	public Double getLongitudDestino() {
		return longitudDestino;
	}
	public void setLongitudDestino(Double longitudDestino) {
		this.longitudDestino = longitudDestino;
	}
	public Double getRadioDestino() {
		return radioDestino;
	}
	public void setRadioDestino(Double radioDestino) {
		this.radioDestino = radioDestino;
	}
	public String getCodeDestinoNegocio() {
		return codeDestinoNegocio;
	}
	public void setCodeDestinoNegocio(String codeDestinoNegocio) {
		this.codeDestinoNegocio = codeDestinoNegocio;
	}
	public String getNombreDestinoNegocio() {
		return nombreDestinoNegocio;
	}
	public void setNombreDestinoNegocio(String nombreDestinoNegocio) {
		this.nombreDestinoNegocio = nombreDestinoNegocio;
	}
	public String getFotoDestinoNegocio() {
		return fotoDestinoNegocio;
	}
	public void setFotoDestinoNegocio(String fotoDestinoNegocio) {
		this.fotoDestinoNegocio = fotoDestinoNegocio;
	}
	public Double getLatitudDestinoNegocio() {
		return latitudDestinoNegocio;
	}
	public void setLatitudDestinoNegocio(Double latitudDestinoNegocio) {
		this.latitudDestinoNegocio = latitudDestinoNegocio;
	}
	public Double getLongitudDestinoNegocio() {
		return longitudDestinoNegocio;
	}
	public void setLongitudDestinoNegocio(Double longitudDestinoNegocio) {
		this.longitudDestinoNegocio = longitudDestinoNegocio;
	}
	public Double getRadioDestinoNegocio() {
		return radioDestinoNegocio;
	}
	public void setRadioDestinoNegocio(Double radioDestinoNegocio) {
		this.radioDestinoNegocio = radioDestinoNegocio;
	}
	public Double getDistanciaCalculada() {
		return distanciaCalculada;
	}
	public void setDistanciaCalculada(Double distanciaCalculada) {
		this.distanciaCalculada = distanciaCalculada;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		result = prime * result + ((codeDestino == null) ? 0 : codeDestino.hashCode());
		result = prime * result + ((codeDestinoNegocio == null) ? 0 : codeDestinoNegocio.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ProxDestinoDestino other = (ProxDestinoDestino) obj;
		if (codeDestino == null) {
			if (other.codeDestino != null)
				return false;
		} else if (!codeDestino.equals(other.codeDestino))
			return false;
		if (codeDestinoNegocio == null) {
			if (other.codeDestinoNegocio != null)
				return false;
		} else if (!codeDestinoNegocio.equals(other.codeDestinoNegocio))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProxDestinoDestino [idProxDestinoDestino=");
		builder.append(idProxDestinoDestino);
		builder.append(", codeProxDestinoDestino=");
		builder.append(codeProxDestinoDestino);
		builder.append(", beanDestino=");
		builder.append(beanDestino);
		builder.append(", codeDestino=");
		builder.append(codeDestino);
		builder.append(", latitudDestino=");
		builder.append(latitudDestino);
		builder.append(", longitudDestino=");
		builder.append(longitudDestino);
		builder.append(", radioDestino=");
		builder.append(radioDestino);
		builder.append(", codeDestinoNegocio=");
		builder.append(codeDestinoNegocio);
		builder.append(", nombreDestinoNegocio=");
		builder.append(nombreDestinoNegocio);
		builder.append(", fotoDestinoNegocio=");
		builder.append(fotoDestinoNegocio);
		builder.append(", latitudDestinoNegocio=");
		builder.append(latitudDestinoNegocio);
		builder.append(", longitudDestinoNegocio=");
		builder.append(longitudDestinoNegocio);
		builder.append(", radioDestinoNegocio=");
		builder.append(radioDestinoNegocio);
		builder.append(", distanciaCalculada=");
		builder.append(distanciaCalculada);
		builder.append(", orden=");
		builder.append(orden);
		builder.append(", version=");
		builder.append(version);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
