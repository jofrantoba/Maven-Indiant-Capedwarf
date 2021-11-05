package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Empatia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEmpatia;
	@Persistent	
	private String codeEmpatia;
	@Persistent	
	private String codUnicoEmpatia;
	@Persistent	
	private Integer totalEmpatia;
	@Persistent	
	private String estado;
	@Persistent
	private java.util.Date fechaGenera;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaUno;
	@Persistent	
	private String codeTuristaUno;
	@Persistent
	@Unowned
	private Miembro beanMiembroUno;
	@Persistent	
	private String codeMiembroUno;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaDos;
	@Persistent	
	private String codeTuristaDos;
	@Persistent
	@Unowned
	private Miembro beanMiembroDos;
	@Persistent	
	private String codeMiembroDos;
	@Persistent
	private Boolean isPersistente;
	@Persistent	
	private String codeColonia;
	
	public String getIdEmpatia() {
		return idEmpatia;
	}
	public void setIdEmpatia(String idEmpatia) {
		this.idEmpatia=idEmpatia;				
	}
	public String getCodeEmpatia() {
		return codeEmpatia;
	}
	public void setCodeEmpatia(String codeEmpatia) {
		this.codeEmpatia = codeEmpatia;
	}
	public String getCodUnicoEmpatia() {
		return codUnicoEmpatia;
	}
	public void setCodUnicoEmpatia(String codUnicoEmpatia) {
		this.codUnicoEmpatia = codUnicoEmpatia;
	}
	public Integer getTotalEmpatia() {
		return totalEmpatia;
	}
	public void setTotalEmpatia(Integer totalEmpatia) {
		this.totalEmpatia = totalEmpatia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	public String getCodeColonia() {
		return codeColonia;
	}
	public void setCodeColonia(String codeColonia) {
		this.codeColonia = codeColonia;
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
	public UsuarioTurista getBeanTuristaUno() {
		return beanTuristaUno;
	}
	public void setBeanTuristaUno(UsuarioTurista beanTuristaUno) {
		this.beanTuristaUno = beanTuristaUno;
	}
	public String getCodeTuristaUno() {
		return codeTuristaUno;
	}
	public void setCodeTuristaUno(String codeTuristaUno) {
		this.codeTuristaUno = codeTuristaUno;
	}
	public Miembro getBeanMiembroUno() {
		return beanMiembroUno;
	}
	public void setBeanMiembroUno(Miembro beanMiembroUno) {
		this.beanMiembroUno = beanMiembroUno;
	}
	public String getCodeMiembroUno() {
		return codeMiembroUno;
	}
	public void setCodeMiembroUno(String codeMiembroUno) {
		this.codeMiembroUno = codeMiembroUno;
	}
	public UsuarioTurista getBeanTuristaDos() {
		return beanTuristaDos;
	}
	public void setBeanTuristaDos(UsuarioTurista beanTuristaDos) {
		this.beanTuristaDos = beanTuristaDos;
	}
	public String getCodeTuristaDos() {
		return codeTuristaDos;
	}
	public void setCodeTuristaDos(String codeTuristaDos) {
		this.codeTuristaDos = codeTuristaDos;
	}
	public Miembro getBeanMiembroDos() {
		return beanMiembroDos;
	}
	public void setBeanMiembroDos(Miembro beanMiembroDos) {
		this.beanMiembroDos = beanMiembroDos;
	}
	public String getCodeMiembroDos() {
		return codeMiembroDos;
	}
	public void setCodeMiembroDos(String codeMiembroDos) {
		this.codeMiembroDos = codeMiembroDos;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	public java.util.Date getFechaGenera() {
		return fechaGenera;
	}
	public void setFechaGenera(java.util.Date fechaGenera) {
		this.fechaGenera = fechaGenera;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeEmpatia == null) ? 0 : codeEmpatia.hashCode());
		result = prime * result
				+ ((idEmpatia == null) ? 0 : idEmpatia.hashCode());
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
		Empatia other = (Empatia) obj;
		if (codeEmpatia == null) {
			if (other.codeEmpatia != null)
				return false;
		} else if (!codeEmpatia.equals(other.codeEmpatia))
			return false;
		if (idEmpatia == null) {
			if (other.idEmpatia != null)
				return false;
		} else if (!idEmpatia.equals(other.idEmpatia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empatia [idEmpatia=");
		builder.append(idEmpatia);
		builder.append(", codeEmpatia=");
		builder.append(codeEmpatia);
		builder.append(", codUnicoEmpatia=");
		builder.append(codUnicoEmpatia);
		builder.append(", totalEmpatia=");
		builder.append(totalEmpatia);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", fechaGenera=");
		builder.append(fechaGenera);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaUno=");
		builder.append(beanTuristaUno);
		builder.append(", codeTuristaUno=");
		builder.append(codeTuristaUno);
		builder.append(", beanMiembroUno=");
		builder.append(beanMiembroUno);
		builder.append(", codeMiembroUno=");
		builder.append(codeMiembroUno);
		builder.append(", beanTuristaDos=");
		builder.append(beanTuristaDos);
		builder.append(", codeTuristaDos=");
		builder.append(codeTuristaDos);
		builder.append(", beanMiembroDos=");
		builder.append(beanMiembroDos);
		builder.append(", codeMiembroDos=");
		builder.append(codeMiembroDos);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
