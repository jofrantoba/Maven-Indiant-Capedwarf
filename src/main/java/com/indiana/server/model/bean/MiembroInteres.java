package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class MiembroInteres implements Serializable{

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMiembroInteres;
	@Persistent
	private String codeMiembroInteres;
	@Persistent
	private String nombreInteres;
	@Persistent
	private Double valorModaMiembroInteres;
	@Persistent
	private String estadoMiembroInteres;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	private Miembro beanMiembro;
	@Persistent
	private String codeMiembro;
	@Persistent
	@Unowned
	private UsuarioTurista beanTurista;
	@Persistent
	private String codeTurista;
	@Persistent
	@Unowned
	private Interes beanInteres;
	@Persistent
	private String codeInteres;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String codeColonia;
	@Persistent
	private Integer annio;
	@Persistent
	private Integer mes;
	@Persistent
	private Integer dia;
	@Persistent
	private Integer edad;
	@Persistent
	private String nombreColonia;
	@Persistent
	private String nacionalidad;
	
	public String getIdMiembroInteres() {
		return idMiembroInteres;
	}
	public void setIdMiembroInteres(String idMiembroInteres) {
		this.idMiembroInteres= idMiembroInteres;				
	}
	public String getCodeMiembroInteres() {
		return codeMiembroInteres;
	}
	public void setCodeMiembroInteres(String codeMiembroInteres) {
		this.codeMiembroInteres = codeMiembroInteres;
	}
	public String getNombreInteres() {
		return nombreInteres;
	}
	public void setNombreInteres(String nombreInteres) {
		this.nombreInteres = nombreInteres;
	}
	public Double getValorModaMiembroInteres() {
		return valorModaMiembroInteres;
	}
	public void setValorModaMiembroInteres(Double valorModaMiembroInteres) {
		this.valorModaMiembroInteres = valorModaMiembroInteres;
	}
	public String getEstadoMiembroInteres() {
		return estadoMiembroInteres;
	}
	public void setEstadoMiembroInteres(String estadoMiembroInteres) {
		this.estadoMiembroInteres = estadoMiembroInteres;
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
	public Integer getAnnio() {
		return annio;
	}
	public void setAnnio(Integer annio) {
		this.annio = annio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Miembro getBeanMiembro() {
		return beanMiembro;
	}
	public void setBeanMiembro(Miembro beanMiembro) {
		this.beanMiembro = beanMiembro;
	}
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
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
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}	
	
	public String getCodeColonia() {
		return codeColonia;
	}
	public void setCodeColonia(String codeColonia) {
		this.codeColonia = codeColonia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeInteres == null) ? 0 : codeInteres.hashCode());
		result = prime * result + ((codeMiembro == null) ? 0 : codeMiembro.hashCode());
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
		MiembroInteres other = (MiembroInteres) obj;
		if (codeInteres == null) {
			if (other.codeInteres != null)
				return false;
		} else if (!codeInteres.equals(other.codeInteres))
			return false;
		if (codeMiembro == null) {
			if (other.codeMiembro != null)
				return false;
		} else if (!codeMiembro.equals(other.codeMiembro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MiembroInteres [idMiembroInteres=");
		builder.append(idMiembroInteres);
		builder.append(", codeMiembroInteres=");
		builder.append(codeMiembroInteres);
		builder.append(", nombreInteres=");
		builder.append(nombreInteres);
		builder.append(", valorModaMiembroInteres=");
		builder.append(valorModaMiembroInteres);
		builder.append(", estadoMiembroInteres=");
		builder.append(estadoMiembroInteres);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanMiembro=");
		builder.append(beanMiembro);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", beanInteres=");
		builder.append(beanInteres);
		builder.append(", codeInteres=");
		builder.append(codeInteres);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append("]");
		return builder.toString();
	}
	
	
}
