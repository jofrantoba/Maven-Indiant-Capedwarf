package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class TuristaInteresEmpatia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey		
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTuristaInteresEmpatia;
	@Persistent
	private String codeTuristaInteresEmpatia;	
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaSugiereInteres1;
	@Persistent
	private String codeTuristaSugiereInteres1;
	@Persistent
	@Unowned
	private Miembro beanMiembroSugiereInteres1;
	@Persistent
	private String codeMiembroSugiereInteres1;
	@Persistent
	@Unowned
	private Interes beanInteresSugerido1;
	@Persistent
	private String codeInteresSugerido1;
	@Persistent
	private Double valorModaInteres1;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaSugiereInteres2;
	@Persistent
	private String codeTuristaSugiereInteres2;
	@Persistent
	@Unowned
	private Miembro beanMiembroSugiereInteres2;
	@Persistent
	private String codeMiembroSugiereInteres2;
	@Persistent
	@Unowned
	private Interes beanInteresSugerido2;
	@Persistent
	private String codeInteresSugerido2;
	@Persistent
	private Double valorModaInteres2;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdTuristaInteresEmpatia() {
		return idTuristaInteresEmpatia;
	}
	public void setIdTuristaInteresEmpatia(String idTuristaInteresEmpatia) {
		this.idTuristaInteresEmpatia = idTuristaInteresEmpatia;				
	}
	public String getCodeTuristaInteresEmpatia() {
		return codeTuristaInteresEmpatia;
	}
	public void setCodeTuristaInteresEmpatia(String codeTuristaInteresEmpatia) {
		this.codeTuristaInteresEmpatia = codeTuristaInteresEmpatia;
	}
	public UsuarioTurista getBeanTuristaSugiereInteres1() {
		return beanTuristaSugiereInteres1;
	}
	public void setBeanTuristaSugiereInteres1(
			UsuarioTurista beanTuristaSugiereInteres1) {
		this.beanTuristaSugiereInteres1 = beanTuristaSugiereInteres1;
	}
	public String getCodeTuristaSugiereInteres1() {
		return codeTuristaSugiereInteres1;
	}
	public void setCodeTuristaSugiereInteres1(String codeTuristaSugiereInteres1) {
		this.codeTuristaSugiereInteres1 = codeTuristaSugiereInteres1;
	}
	public Miembro getBeanMiembroSugiereInteres1() {
		return beanMiembroSugiereInteres1;
	}
	public void setBeanMiembroSugiereInteres1(Miembro beanMiembroSugiereInteres1) {
		this.beanMiembroSugiereInteres1 = beanMiembroSugiereInteres1;
	}
	public String getCodeMiembroSugiereInteres1() {
		return codeMiembroSugiereInteres1;
	}
	public void setCodeMiembroSugiereInteres1(String codeMiembroSugiereInteres1) {
		this.codeMiembroSugiereInteres1 = codeMiembroSugiereInteres1;
	}
	public Interes getBeanInteresSugerido1() {
		return beanInteresSugerido1;
	}
	public void setBeanInteresSugerido1(Interes beanInteresSugerido1) {
		this.beanInteresSugerido1 = beanInteresSugerido1;
	}
	public String getCodeInteresSugerido1() {
		return codeInteresSugerido1;
	}
	public void setCodeInteresSugerido1(String codeInteresSugerido1) {
		this.codeInteresSugerido1 = codeInteresSugerido1;
	}
	public Double getValorModaInteres1() {
		return valorModaInteres1;
	}
	public void setValorModaInteres1(Double valorModaInteres1) {
		this.valorModaInteres1 = valorModaInteres1;
	}
	public UsuarioTurista getBeanTuristaSugiereInteres2() {
		return beanTuristaSugiereInteres2;
	}
	public void setBeanTuristaSugiereInteres2(
			UsuarioTurista beanTuristaSugiereInteres2) {
		this.beanTuristaSugiereInteres2 = beanTuristaSugiereInteres2;
	}
	public String getCodeTuristaSugiereInteres2() {
		return codeTuristaSugiereInteres2;
	}
	public void setCodeTuristaSugiereInteres2(String codeTuristaSugiereInteres2) {
		this.codeTuristaSugiereInteres2 = codeTuristaSugiereInteres2;
	}
	public Miembro getBeanMiembroSugiereInteres2() {
		return beanMiembroSugiereInteres2;
	}
	public void setBeanMiembroSugiereInteres2(Miembro beanMiembroSugiereInteres2) {
		this.beanMiembroSugiereInteres2 = beanMiembroSugiereInteres2;
	}
	public String getCodeMiembroSugiereInteres2() {
		return codeMiembroSugiereInteres2;
	}
	public void setCodeMiembroSugiereInteres2(String codeMiembroSugiereInteres2) {
		this.codeMiembroSugiereInteres2 = codeMiembroSugiereInteres2;
	}
	public Interes getBeanInteresSugerido2() {
		return beanInteresSugerido2;
	}
	public void setBeanInteresSugerido2(Interes beanInteresSugerido2) {
		this.beanInteresSugerido2 = beanInteresSugerido2;
	}
	public String getCodeInteresSugerido2() {
		return codeInteresSugerido2;
	}
	public void setCodeInteresSugerido2(String codeInteresSugerido2) {
		this.codeInteresSugerido2 = codeInteresSugerido2;
	}
	public Double getValorModaInteres2() {
		return valorModaInteres2;
	}
	public void setValorModaInteres2(Double valorModaInteres2) {
		this.valorModaInteres2 = valorModaInteres2;
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
				+ ((codeTuristaInteresEmpatia == null) ? 0
						: codeTuristaInteresEmpatia.hashCode());
		result = prime
				* result
				+ ((idTuristaInteresEmpatia == null) ? 0
						: idTuristaInteresEmpatia.hashCode());
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
		TuristaInteresEmpatia other = (TuristaInteresEmpatia) obj;
		if (codeTuristaInteresEmpatia == null) {
			if (other.codeTuristaInteresEmpatia != null)
				return false;
		} else if (!codeTuristaInteresEmpatia
				.equals(other.codeTuristaInteresEmpatia))
			return false;
		if (idTuristaInteresEmpatia == null) {
			if (other.idTuristaInteresEmpatia != null)
				return false;
		} else if (!idTuristaInteresEmpatia
				.equals(other.idTuristaInteresEmpatia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TuristaInteresEmpatia [idTuristaInteresEmpatia=");
		builder.append(idTuristaInteresEmpatia);
		builder.append(", codeTuristaInteresEmpatia=");
		builder.append(codeTuristaInteresEmpatia);
		builder.append(", beanTuristaSugiereInteres1=");
		builder.append(beanTuristaSugiereInteres1);
		builder.append(", codeTuristaSugiereInteres1=");
		builder.append(codeTuristaSugiereInteres1);
		builder.append(", beanMiembroSugiereInteres1=");
		builder.append(beanMiembroSugiereInteres1);
		builder.append(", codeMiembroSugiereInteres1=");
		builder.append(codeMiembroSugiereInteres1);
		builder.append(", beanInteresSugerido1=");
		builder.append(beanInteresSugerido1);
		builder.append(", codeInteresSugerido1=");
		builder.append(codeInteresSugerido1);
		builder.append(", valorModaInteres1=");
		builder.append(valorModaInteres1);
		builder.append(", beanTuristaSugiereInteres2=");
		builder.append(beanTuristaSugiereInteres2);
		builder.append(", codeTuristaSugiereInteres2=");
		builder.append(codeTuristaSugiereInteres2);
		builder.append(", beanMiembroSugiereInteres2=");
		builder.append(beanMiembroSugiereInteres2);
		builder.append(", codeMiembroSugiereInteres2=");
		builder.append(codeMiembroSugiereInteres2);
		builder.append(", beanInteresSugerido2=");
		builder.append(beanInteresSugerido2);
		builder.append(", codeInteresSugerido2=");
		builder.append(codeInteresSugerido2);
		builder.append(", valorModaInteres2=");
		builder.append(valorModaInteres2);
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
