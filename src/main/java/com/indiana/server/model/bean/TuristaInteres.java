package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class TuristaInteres implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8818318628558024098L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTuristaInteres;
	@Persistent
	private String codeTuristaInteres;	
	@Persistent	
	private UsuarioTurista beanTurista;
	@Persistent
	private String codeTurista;
	@Persistent
	@Unowned
	private Interes beanInteres;
	@Persistent
	private String codeInteres;	
	@Persistent
	private String nombreInteres;
	@Persistent
	private Double valorModa;
	@Persistent
	private String estadoVisibilidad;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;	
	@Persistent
	private Boolean enComun;
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
	
	public String getIdTuristaInteres() {
		return idTuristaInteres;
	}
	public void setIdTuristaInteres(String idTuristaInteres) {
		this.idTuristaInteres = idTuristaInteres;				
	}
	public String getCodeTuristaInteres() {
		return codeTuristaInteres;
	}
	public void setCodeTuristaInteres(String codeTuristaInteres) {
		this.codeTuristaInteres = codeTuristaInteres;
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
	public Boolean getEnComun() {
		return enComun;
	}
	public void setEnComun(Boolean enComun) {
		this.enComun = enComun;
	}
	public void setCodeInteres(String codeInteres) {
		this.codeInteres = codeInteres;
	}
	public String getNombreInteres() {
		return nombreInteres;
	}
	public void setNombreInteres(String nombreInteres) {
		this.nombreInteres = nombreInteres;
	}
	public Double getValorModa() {
		return valorModa;
	}
	public void setValorModa(Double valorModa) {
		this.valorModa = valorModa;
	}
	public String getEstadoVisibilidad() {
		return estadoVisibilidad;
	}
	public void setEstadoVisibilidad(String estadoVisibilidad) {
		this.estadoVisibilidad = estadoVisibilidad;
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
		result = prime * result + ((codeInteres == null) ? 0 : codeInteres.hashCode());
		result = prime * result + ((codeTurista == null) ? 0 : codeTurista.hashCode());
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
		TuristaInteres other = (TuristaInteres) obj;
		if (codeInteres == null) {
			if (other.codeInteres != null)
				return false;
		} else if (!codeInteres.equals(other.codeInteres))
			return false;
		if (codeTurista == null) {
			if (other.codeTurista != null)
				return false;
		} else if (!codeTurista.equals(other.codeTurista))
			return false;
		return true;
	}
	public boolean equalsObject(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TuristaInteres other = (TuristaInteres) obj;
		if (beanTurista == null) {
			if (other.getBeanTurista() != null)
				return false;
		} else if (!beanTurista.getCodeUsuarioTurista().equals(other.getBeanTurista().getCodeUsuarioTurista()))
			return false;
		if (beanInteres == null) {
			if (other.getBeanInteres() != null)
				return false;
		} else if (!beanInteres.getNombre().equalsIgnoreCase(other.getBeanInteres().getNombre()))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TuristaInteres [idTuristaInteres=");
		builder.append(idTuristaInteres);
		builder.append(", codeTuristaInteres=");
		builder.append(codeTuristaInteres);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", beanInteres=");
		builder.append(beanInteres);
		builder.append(", codeInteres=");
		builder.append(codeInteres);
		builder.append(", nombreInteres=");
		builder.append(nombreInteres);
		builder.append(", valorModa=");
		builder.append(valorModa);
		builder.append(", estadoVisibilidad=");
		builder.append(estadoVisibilidad);
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
