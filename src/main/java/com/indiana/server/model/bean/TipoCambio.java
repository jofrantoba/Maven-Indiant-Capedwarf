package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class TipoCambio implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent	
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTipoCambio;
	@Persistent
	private String codeTipoCambio;
	@Persistent
	@Unowned
	private PaisMoneda beanPaisMonedaOrigen;
	@Persistent
	@Unowned
	private PaisMoneda beanPaisMonedaDestino;
	@Persistent
	private Double precioVenta;
	@Persistent
	private Double precioCompra;
	@Persistent
	private java.util.Date fecha;
	@Persistent
	private String paisOrigen;
	@Persistent
	private String monedaOrigen;
	@Persistent
	private String paisDestino;
	@Persistent
	private String monedaDestino;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codePaisMonedaOrigen;
	@Persistent
	private String codePaisMonedaDestino;
	@Persistent
	private Boolean isPersistente;
	public String getIdTipoCambio() {
		return idTipoCambio;
	}
	public void setIdTipoCambio(String idTipoCambio) {
		this.idTipoCambio = idTipoCambio;
	}
	public String getCodeTipoCambio() {
		return codeTipoCambio;
	}
	public void setCodeTipoCambio(String codeTipoCambio) {
		this.codeTipoCambio = codeTipoCambio;
	}
	public PaisMoneda getBeanPaisMonedaOrigen() {
		return beanPaisMonedaOrigen;
	}
	public void setBeanPaisMonedaOrigen(PaisMoneda beanPaisMonedaOrigen) {
		this.beanPaisMonedaOrigen = beanPaisMonedaOrigen;
	}
	public PaisMoneda getBeanPaisMonedaDestino() {
		return beanPaisMonedaDestino;
	}
	public void setBeanPaisMonedaDestino(PaisMoneda beanPaisMonedaDestino) {
		this.beanPaisMonedaDestino = beanPaisMonedaDestino;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}
	public String getPaisDestino() {
		return paisDestino;
	}
	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
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
	public String getCodePaisMonedaOrigen() {
		return codePaisMonedaOrigen;
	}
	public void setCodePaisMonedaOrigen(String codePaisMonedaOrigen) {
		this.codePaisMonedaOrigen = codePaisMonedaOrigen;
	}
	public String getCodePaisMonedaDestino() {
		return codePaisMonedaDestino;
	}
	public void setCodePaisMonedaDestino(String codePaisMonedaDestino) {
		this.codePaisMonedaDestino = codePaisMonedaDestino;
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
		result = prime * result + ((codeTipoCambio == null) ? 0 : codeTipoCambio.hashCode());
		result = prime * result + ((idTipoCambio == null) ? 0 : idTipoCambio.hashCode());
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
		TipoCambio other = (TipoCambio) obj;
		if (codeTipoCambio == null) {
			if (other.codeTipoCambio != null)
				return false;
		} else if (!codeTipoCambio.equals(other.codeTipoCambio))
			return false;
		if (idTipoCambio == null) {
			if (other.idTipoCambio != null)
				return false;
		} else if (!idTipoCambio.equals(other.idTipoCambio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoCambio [idTipoCambio=");
		builder.append(idTipoCambio);
		builder.append(", codeTipoCambio=");
		builder.append(codeTipoCambio);
		builder.append(", beanPaisMonedaOrigen=");
		builder.append(beanPaisMonedaOrigen);
		builder.append(", beanPaisMonedaDestino=");
		builder.append(beanPaisMonedaDestino);
		builder.append(", precioVenta=");
		builder.append(precioVenta);
		builder.append(", precioCompra=");
		builder.append(precioCompra);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", paisOrigen=");
		builder.append(paisOrigen);
		builder.append(", monedaOrigen=");
		builder.append(monedaOrigen);
		builder.append(", paisDestino=");
		builder.append(paisDestino);
		builder.append(", monedaDestino=");
		builder.append(monedaDestino);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", codePaisMonedaOrigen=");
		builder.append(codePaisMonedaOrigen);
		builder.append(", codePaisMonedaDestino=");
		builder.append(codePaisMonedaDestino);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}