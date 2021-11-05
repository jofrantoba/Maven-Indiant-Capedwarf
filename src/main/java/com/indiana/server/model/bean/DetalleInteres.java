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
public class DetalleInteres implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idDetalleInteres;
	@Persistent
	private String codeDetalleInteres;
	@Persistent
	private String nombreInteres;
	@Persistent
	private Double valor;
	@Persistent
	private String nombreColonia;
	@Persistent
	private Date fechaCreacion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codeTuristaInteres;
	@Persistent
	private String codeMiembroInteres;
	@Persistent
	private String codeColoniaInteres;
	@Persistent	
	@Unowned
	private Interes beanInteres;	
	@Persistent
	private String codeInteres;
	@Persistent	
	@Unowned
	private TipoMovimiento beanTipoMovimiento;
	@Persistent
	private String codeTipoMovimiento;
	@Persistent	
	@Unowned
	private UsuarioTurista beanTurista;
	@Persistent
	private String codeTurista;	
	@Persistent
	private String codeMiembro;
	@Persistent	
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdDetalleInteres() {
		return idDetalleInteres;
	}
	public void setIdDetalleInteres(String idDetalleInteres) {
		this.idDetalleInteres=idDetalleInteres;				
	}
	public String getCodeDetalleInteres() {
		return codeDetalleInteres;
	}
	public void setCodeDetalleInteres(String codeDetalleInteres) {
		this.codeDetalleInteres = codeDetalleInteres;
	}
	public String getNombreInteres() {
		return nombreInteres;
	}
	public void setNombreInteres(String nombreInteres) {
		this.nombreInteres = nombreInteres;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	public String getCodeTuristaInteres() {
		return codeTuristaInteres;
	}
	public void setCodeTuristaInteres(String codeTuristaInteres) {
		this.codeTuristaInteres = codeTuristaInteres;
	}
	public String getCodeMiembroInteres() {
		return codeMiembroInteres;
	}
	public void setCodeMiembroInteres(String codeMiembroInteres) {
		this.codeMiembroInteres = codeMiembroInteres;
	}	
	public String getCodeColoniaInteres() {
		return codeColoniaInteres;
	}
	public void setCodeColoniaInteres(String codeColoniaInteres) {
		this.codeColoniaInteres = codeColoniaInteres;
	}
	public Interes getBeanInteres() {
		return beanInteres;
	}
	public void setBeanInteres(Interes beanInteres) {
		this.beanInteres = beanInteres;
	}
	public String getCodeInteres() {
		return codeInteres;
	}
	public void setCodeInteres(String codeInteres) {
		this.codeInteres = codeInteres;
	}
	public TipoMovimiento getBeanTipoMovimiento() {
		return beanTipoMovimiento;
	}
	public void setBeanTipoMovimiento(TipoMovimiento beanTipoMovimiento) {
		this.beanTipoMovimiento = beanTipoMovimiento;
	}
	public String getCodeTipoMovimiento() {
		return codeTipoMovimiento;
	}
	public void setCodeTipoMovimiento(String codeTipoMovimiento) {
		this.codeTipoMovimiento = codeTipoMovimiento;
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
				+ ((codeDetalleInteres == null) ? 0 : codeDetalleInteres
						.hashCode());
		result = prime
				* result
				+ ((idDetalleInteres == null) ? 0 : idDetalleInteres.hashCode());
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
		DetalleInteres other = (DetalleInteres) obj;
		if (codeDetalleInteres == null) {
			if (other.codeDetalleInteres != null)
				return false;
		} else if (!codeDetalleInteres.equals(other.codeDetalleInteres))
			return false;
		if (idDetalleInteres == null) {
			if (other.idDetalleInteres != null)
				return false;
		} else if (!idDetalleInteres.equals(other.idDetalleInteres))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DetalleInteres [idDetalleInteres=");
		builder.append(idDetalleInteres);
		builder.append(", codeDetalleInteres=");
		builder.append(codeDetalleInteres);
		builder.append(", nombreInteres=");
		builder.append(nombreInteres);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", codeTuristaInteres=");
		builder.append(codeTuristaInteres);
		builder.append(", codeMiembroInteres=");
		builder.append(codeMiembroInteres);
		builder.append(", codeColoniaInteres=");
		builder.append(codeColoniaInteres);
		builder.append(", beanInteres=");
		builder.append(beanInteres);
		builder.append(", codeInteres=");
		builder.append(codeInteres);
		builder.append(", beanTipoMovimiento=");
		builder.append(beanTipoMovimiento);
		builder.append(", codeTipoMovimiento=");
		builder.append(codeTipoMovimiento);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
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
