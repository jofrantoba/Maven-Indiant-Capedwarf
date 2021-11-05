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
public class Conquista implements Serializable{

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idConquista;	
	@Persistent
	private String codeConquista;
	@Persistent
	private String descripcion;
	@Persistent
	private String fotoConquista;
	@Persistent
	private Double longitud;
	@Persistent
	private Double latitud;
	@Persistent
	private Date fechaConquista;
	@Persistent
	private String tipoConquista;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaConquista;
	@Persistent
	private String codeTuristaConquista;
	@Persistent
	private String nombreTuristaConquista;
	@Persistent
	private String apellidoTuristaConquista;
	@Persistent
	private String fotoPerfilTuristaConquista;
	@Persistent
	@Unowned
	private Destino beanDestino;
	@Persistent
	private String codeDestino;
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocio;
	@Persistent
	private String codeNegocio;
	@Persistent
	private String nombreDestinoNegocio;
	@Persistent
	@Unowned
	private OfertaNegocio beanOferta;
	@Persistent
	private String codeOferta;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private String codePais;
	@Persistent
	@Unowned
	private Region beanRegion;
	@Persistent
	private String codeRegion;
	@Persistent
	@Unowned
	private Localidad beanLocalidad;
	@Persistent
	private String codeLocalidad;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private Integer annio;
	@Persistent
	private Integer mes;
	@Persistent
	private Integer dia;
	@Persistent
	private Integer edad;	
	@Persistent
	private String nacionalidad;
	
	public String getIdConquista() {
		return idConquista;
	}
	public void setIdConquista(String idConquista) {
		this.idConquista=idConquista;			
	}
	public String getCodeConquista() {
		return codeConquista;
	}
	public void setCodeConquista(String codeConquista) {
		this.codeConquista = codeConquista;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFotoConquista() {
		return fotoConquista;
	}
	public void setFotoConquista(String fotoConquista) {
		this.fotoConquista = fotoConquista;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
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
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Date getFechaConquista() {
		return fechaConquista;
	}
	public void setFechaConquista(Date fechaConquista) {
		this.fechaConquista = fechaConquista;
	}
	public String getTipoConquista() {
		return tipoConquista;
	}
	public void setTipoConquista(String tipoConquista) {
		this.tipoConquista = tipoConquista;
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
	public UsuarioTurista getBeanTuristaConquista() {
		return beanTuristaConquista;
	}
	public void setBeanTuristaConquista(UsuarioTurista beanTuristaConquista) {
		this.beanTuristaConquista = beanTuristaConquista;
	}
	public String getCodeTuristaConquista() {
		return codeTuristaConquista;
	}
	public void setCodeTuristaConquista(String codeTuristaConquista) {
		this.codeTuristaConquista = codeTuristaConquista;
	}
	public String getNombreTuristaConquista() {
		return nombreTuristaConquista;
	}
	public void setNombreTuristaConquista(String nombreTuristaConquista) {
		this.nombreTuristaConquista = nombreTuristaConquista;
	}
	public String getApellidoTuristaConquista() {
		return apellidoTuristaConquista;
	}
	public void setApellidoTuristaConquista(String apellidoTuristaConquista) {
		this.apellidoTuristaConquista = apellidoTuristaConquista;
	}
	public String getFotoPerfilTuristaConquista() {
		return fotoPerfilTuristaConquista;
	}
	public void setFotoPerfilTuristaConquista(String fotoPerfilTuristaConquista) {
		this.fotoPerfilTuristaConquista = fotoPerfilTuristaConquista;
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
	public String getNombreDestinoNegocio() {
		return nombreDestinoNegocio;
	}
	public void setNombreDestinoNegocio(String nombreDestinoNegocio) {
		this.nombreDestinoNegocio = nombreDestinoNegocio;
	}
	public OfertaNegocio getBeanOferta() {
		return beanOferta;
	}
	public void setBeanOferta(OfertaNegocio beanOferta) {
		this.beanOferta = beanOferta;
	}
	public String getCodeOferta() {
		return codeOferta;
	}
	public void setCodeOferta(String codeOferta) {
		this.codeOferta = codeOferta;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
	}
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public Region getBeanRegion() {
		return beanRegion;
	}
	public void setBeanRegion(Region beanRegion) {
		this.beanRegion = beanRegion;
	}
	public String getCodeRegion() {
		return codeRegion;
	}
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}	
	public Localidad getBeanLocalidad() {
		return beanLocalidad;
	}
	public void setBeanLocalidad(Localidad beanLocalidad) {
		this.beanLocalidad = beanLocalidad;
	}
	public String getCodeLocalidad() {
		return codeLocalidad;
	}
	public void setCodeLocalidad(String codeLocalidad) {
		this.codeLocalidad = codeLocalidad;
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
		result = prime * result
				+ ((codeConquista == null) ? 0 : codeConquista.hashCode());
		result = prime * result
				+ ((idConquista == null) ? 0 : idConquista.hashCode());
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
		Conquista other = (Conquista) obj;
		if (codeConquista == null) {
			if (other.codeConquista != null)
				return false;
		} else if (!codeConquista.equals(other.codeConquista))
			return false;
		if (idConquista == null) {
			if (other.idConquista != null)
				return false;
		} else if (!idConquista.equals(other.idConquista))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conquista [idConquista=");
		builder.append(idConquista);
		builder.append(", codeConquista=");
		builder.append(codeConquista);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fotoConquista=");
		builder.append(fotoConquista);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", fechaConquista=");
		builder.append(fechaConquista);
		builder.append(", tipoConquista=");
		builder.append(tipoConquista);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaConquista=");
		builder.append(beanTuristaConquista);
		builder.append(", codeTuristaConquista=");
		builder.append(codeTuristaConquista);
		builder.append(", nombreTuristaConquista=");
		builder.append(nombreTuristaConquista);
		builder.append(", apellidoTuristaConquista=");
		builder.append(apellidoTuristaConquista);
		builder.append(", fotoPerfilTuristaConquista=");
		builder.append(fotoPerfilTuristaConquista);
		builder.append(", beanDestino=");
		builder.append(beanDestino);
		builder.append(", codeDestino=");
		builder.append(codeDestino);
		builder.append(", beanNegocio=");
		builder.append(beanNegocio);
		builder.append(", codeNegocio=");
		builder.append(codeNegocio);
		builder.append(", nombreDestinoNegocio=");
		builder.append(nombreDestinoNegocio);
		builder.append(", beanOferta=");
		builder.append(beanOferta);
		builder.append(", codeOferta=");
		builder.append(codeOferta);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", beanRegion=");
		builder.append(beanRegion);
		builder.append(", codeRegion=");
		builder.append(codeRegion);
		builder.append(", beanLocalidad=");
		builder.append(beanLocalidad);
		builder.append(", codeLocalidad=");
		builder.append(codeLocalidad);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
