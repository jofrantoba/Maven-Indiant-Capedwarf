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
public class DetalleEmpatia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idDetalleEmpatia;
	@Persistent
	private String codeDetalleEmpatia;
	@Persistent
	private Date fechaEmpatia;
	@Persistent
	private String codigoUnicoEmpatia;
	@Persistent
	private String estado;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaEmisor;
	@Persistent
	private String codeTuristaEmisor;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaReceptor;
	@Persistent
	private String codeTuristaReceptor;
	@Persistent
	@Unowned
	private Miembro beanMiembroEmisor;
	@Persistent
	private String codeMiembroEmisor;
	@Persistent
	@Unowned
	private Miembro beanMiembroReceptor;
	@Persistent
	private String codeMiembroReceptor;
	@Persistent
	@Unowned
	private TablonColonia beanTablonColonia;
	@Persistent
	private String codeTablonColonia;
	@Persistent
	@Unowned
	private Empatia beanEmpatia;	
	@Persistent
	private String codeEmpatia;
	@Persistent
	@Unowned
	private TipoEmpatia beanTipoEmpatia;
	@Persistent
	private String codeTipoEmpatia;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdDetalleEmpatia() {
		return idDetalleEmpatia;
	}
	public void setIdDetalleEmpatia(String idDetalleEmpatia) {
		this.idDetalleEmpatia=idDetalleEmpatia;				
	}
	public String getCodeDetalleEmpatia() {
		return codeDetalleEmpatia;
	}
	public void setCodeDetalleEmpatia(String codeDetalleEmpatia) {
		this.codeDetalleEmpatia = codeDetalleEmpatia;
	}
	public Date getFechaEmpatia() {
		return fechaEmpatia;
	}
	public void setFechaEmpatia(Date fechaEmpatia) {
		this.fechaEmpatia = fechaEmpatia;
	}
	public String getCodigoUnicoEmpatia() {
		return codigoUnicoEmpatia;
	}
	public void setCodigoUnicoEmpatia(String codigoUnicoEmpatia) {
		this.codigoUnicoEmpatia = codigoUnicoEmpatia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public UsuarioTurista getBeanTuristaEmisor() {
		return beanTuristaEmisor;
	}
	public void setBeanTuristaEmisor(UsuarioTurista beanTuristaEmisor) {
		this.beanTuristaEmisor = beanTuristaEmisor;
	}
	public String getCodeTuristaEmisor() {
		return codeTuristaEmisor;
	}
	public void setCodeTuristaEmisor(String codeTuristaEmisor) {
		this.codeTuristaEmisor = codeTuristaEmisor;
	}
	public UsuarioTurista getBeanTuristaReceptor() {
		return beanTuristaReceptor;
	}
	public void setBeanTuristaReceptor(UsuarioTurista beanTuristaReceptor) {
		this.beanTuristaReceptor = beanTuristaReceptor;
	}
	public String getCodeTuristaReceptor() {
		return codeTuristaReceptor;
	}
	public void setCodeTuristaReceptor(String codeTuristaReceptor) {
		this.codeTuristaReceptor = codeTuristaReceptor;
	}
	public Miembro getBeanMiembroEmisor() {
		return beanMiembroEmisor;
	}
	public void setBeanMiembroEmisor(Miembro beanMiembroEmisor) {
		this.beanMiembroEmisor = beanMiembroEmisor;
	}
	public String getCodeMiembroEmisor() {
		return codeMiembroEmisor;
	}
	public void setCodeMiembroEmisor(String codeMiembroEmisor) {
		this.codeMiembroEmisor = codeMiembroEmisor;
	}
	public Miembro getBeanMiembroReceptor() {
		return beanMiembroReceptor;
	}
	public void setBeanMiembroReceptor(Miembro beanMiembroReceptor) {
		this.beanMiembroReceptor = beanMiembroReceptor;
	}
	public String getCodeMiembroReceptor() {
		return codeMiembroReceptor;
	}
	public void setCodeMiembroReceptor(String codeMiembroReceptor) {
		this.codeMiembroReceptor = codeMiembroReceptor;
	}
	public TablonColonia getBeanTablonColonia() {
		return beanTablonColonia;
	}
	public void setBeanTablonColonia(TablonColonia beanTablonColonia) {
		this.beanTablonColonia = beanTablonColonia;
	}
	public String getCodeTablonColonia() {
		return codeTablonColonia;
	}
	public void setCodeTablonColonia(String codeTablonColonia) {
		this.codeTablonColonia = codeTablonColonia;
	}
	public Empatia getBeanEmpatia() {
		return beanEmpatia;
	}
	public void setBeanEmpatia(Empatia beanEmpatia) {
		this.beanEmpatia = beanEmpatia;
	}
	public String getCodeEmpatia() {
		return codeEmpatia;
	}
	public void setCodeEmpatia(String codeEmpatia) {
		this.codeEmpatia = codeEmpatia;
	}
	public TipoEmpatia getBeanTipoEmpatia() {
		return beanTipoEmpatia;
	}
	public void setBeanTipoEmpatia(TipoEmpatia beanTipoEmpatia) {
		this.beanTipoEmpatia = beanTipoEmpatia;
	}
	public String getCodeTipoEmpatia() {
		return codeTipoEmpatia;
	}
	public void setCodeTipoEmpatia(String codeTipoEmpatia) {
		this.codeTipoEmpatia = codeTipoEmpatia;
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
				+ ((codeDetalleEmpatia == null) ? 0 : codeDetalleEmpatia
						.hashCode());
		result = prime
				* result
				+ ((idDetalleEmpatia == null) ? 0 : idDetalleEmpatia.hashCode());
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
		DetalleEmpatia other = (DetalleEmpatia) obj;
		if (codeDetalleEmpatia == null) {
			if (other.codeDetalleEmpatia != null)
				return false;
		} else if (!codeDetalleEmpatia.equals(other.codeDetalleEmpatia))
			return false;
		if (idDetalleEmpatia == null) {
			if (other.idDetalleEmpatia != null)
				return false;
		} else if (!idDetalleEmpatia.equals(other.idDetalleEmpatia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DetalleEmpatia [idDetalleEmpatia=");
		builder.append(idDetalleEmpatia);
		builder.append(", codeDetalleEmpatia=");
		builder.append(codeDetalleEmpatia);
		builder.append(", fechaEmpatia=");
		builder.append(fechaEmpatia);
		builder.append(", codigoUnicoEmpatia=");
		builder.append(codigoUnicoEmpatia);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaEmisor=");
		builder.append(beanTuristaEmisor);
		builder.append(", codeTuristaEmisor=");
		builder.append(codeTuristaEmisor);
		builder.append(", beanTuristaReceptor=");
		builder.append(beanTuristaReceptor);
		builder.append(", codeTuristaReceptor=");
		builder.append(codeTuristaReceptor);
		builder.append(", beanMiembroEmisor=");
		builder.append(beanMiembroEmisor);
		builder.append(", codeMiembroEmisor=");
		builder.append(codeMiembroEmisor);
		builder.append(", beanMiembroReceptor=");
		builder.append(beanMiembroReceptor);
		builder.append(", codeMiembroReceptor=");
		builder.append(codeMiembroReceptor);
		builder.append(", beanTablonColonia=");
		builder.append(beanTablonColonia);
		builder.append(", codeTablonColonia=");
		builder.append(codeTablonColonia);
		builder.append(", beanEmpatia=");
		builder.append(beanEmpatia);
		builder.append(", codeEmpatia=");
		builder.append(codeEmpatia);
		builder.append(", beanTipoEmpatia=");
		builder.append(beanTipoEmpatia);
		builder.append(", codeTipoEmpatia=");
		builder.append(codeTipoEmpatia);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
