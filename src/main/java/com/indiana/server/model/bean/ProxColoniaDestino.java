package com.indiana.server.model.bean;

import java.io.Serializable;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class ProxColoniaDestino implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idProxColoniaDestino;
	@Persistent
	private String codeProxColoniaDestino;
	@Persistent
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private Double latitudColonia;
	@Persistent
	private Double longitudColonia;
	@Persistent
	private Double radioColonia;
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
	
	public String getIdProxColoniaDestino() {
		return idProxColoniaDestino;
	}
	public void setIdProxColoniaDestino(String idProxColoniaDestino) {
		this.idProxColoniaDestino = idProxColoniaDestino;
	}
	public String getCodeProxColoniaDestino() {
		return codeProxColoniaDestino;
	}
	public void setCodeProxColoniaDestino(String codeProxColoniaDestino) {
		this.codeProxColoniaDestino = codeProxColoniaDestino;
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
	public Double getLatitudColonia() {
		return latitudColonia;
	}
	public void setLatitudColonia(Double latitudColonia) {
		this.latitudColonia = latitudColonia;
	}
	public Double getLongitudColonia() {
		return longitudColonia;
	}
	public void setLongitudColonia(Double longitudColonia) {
		this.longitudColonia = longitudColonia;
	}
	public Double getRadioColonia() {
		return radioColonia;
	}
	public void setRadioColonia(Double radioColonia) {
		this.radioColonia = radioColonia;
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
		result = prime * result + ((codeColonia == null) ? 0 : codeColonia.hashCode());
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
		ProxColoniaDestino other = (ProxColoniaDestino) obj;
		if (codeColonia == null) {
			if (other.codeColonia != null)
				return false;
		} else if (!codeColonia.equals(other.codeColonia))
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
		builder.append("ProxColoniaDestino [idProxColoniaDestino=");
		builder.append(idProxColoniaDestino);
		builder.append(", codeProxColoniaDestino=");
		builder.append(codeProxColoniaDestino);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", latitudColonia=");
		builder.append(latitudColonia);
		builder.append(", longitudColonia=");
		builder.append(longitudColonia);
		builder.append(", radioColonia=");
		builder.append(radioColonia);
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
