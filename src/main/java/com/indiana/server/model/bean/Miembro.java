package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Miembro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMiembro;
	@Persistent
	private String codeMiembro;
	@Persistent
	private Date fechaSuscripcion;
	@Persistent
	private String estadoVisibilidad;		
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTurista;
	@Persistent
	private String codeTurista;
	@Persistent
	private String nombreUsuarioTurista;
	@Persistent
	private String apellidoUsuarioTurista;
	@Persistent
	private String fotoPerfilUsuarioTurista;
	@Persistent	
	private String nombrePais;
	@Persistent	
	private String nombreRegion;
	@Persistent	
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;
	@Persistent
	@Unowned	
	private EstadoMiembro beanEstadoMiembro;
	@Persistent
	private String codeEstadoMiembro;
	@Persistent
	private Boolean isPersistente;
	@Persistent(mappedBy = "beanMiembro") 
	private Set<MiembroInteres> listMiembroIntereses;
	@Persistent
	private Double latitud;
	@Persistent
	private Double longitud;
	@Persistent
	private Double distanciaAlPuntoCreacion;
	@Persistent
	private Integer estadoChatColonia;
	@Persistent
	private String tokenFirebase;
	
	public String getIdMiembro() {
		return idMiembro;
	}
	public void setIdMiembro(String idMiembro) {
		this.idMiembro= idMiembro;				
	}
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
	}
	public Date getFechaSuscripcion() {
		return fechaSuscripcion;
	}
	public void setFechaSuscripcion(Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
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
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
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
	public String getNombreUsuarioTurista() {
		return nombreUsuarioTurista;
	}
	public void setNombreUsuarioTurista(String nombreUsuarioTurista) {
		this.nombreUsuarioTurista = nombreUsuarioTurista;
	}
	public String getApellidoUsuarioTurista() {
		return apellidoUsuarioTurista;
	}
	public void setApellidoUsuarioTurista(String apellidoUsuarioTurista) {
		this.apellidoUsuarioTurista = apellidoUsuarioTurista;
	}
	public String getFotoPerfilUsuarioTurista() {
		return fotoPerfilUsuarioTurista;
	}
	public void setFotoPerfilUsuarioTurista(String fotoPerfilUsuarioTurista) {
		this.fotoPerfilUsuarioTurista = fotoPerfilUsuarioTurista;
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
	public String getNombreColonia() {
		return nombreColonia;
	}
	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}
	public EstadoMiembro getBeanEstadoMiembro() {
		return beanEstadoMiembro;
	}
	public void setBeanEstadoMiembro(EstadoMiembro beanEstadoMiembro) {
		this.beanEstadoMiembro = beanEstadoMiembro;
	}
	public String getCodeEstadoMiembro() {
		return codeEstadoMiembro;
	}
	public void setCodeEstadoMiembro(String codeEstadoMiembro) {
		this.codeEstadoMiembro = codeEstadoMiembro;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	
	public Set<MiembroInteres> getListMiembroIntereses() {
		return listMiembroIntereses;
	}
	public void setListMiembroIntereses(Set<MiembroInteres> listMiembroIntereses) {
		this.listMiembroIntereses = listMiembroIntereses;
	}
	
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getDistanciaAlPuntoCreacion() {
		return distanciaAlPuntoCreacion;
	}
	public void setDistanciaAlPuntoCreacion(Double distanciaAlPuntoCreacion) {
		this.distanciaAlPuntoCreacion = distanciaAlPuntoCreacion;
	}	
	public Integer getEstadoChatColonia() {
		return estadoChatColonia;
	}
	public void setEstadoChatColonia(Integer estadoChatColonia) {
		this.estadoChatColonia = estadoChatColonia;
	}
	
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeMiembro == null) ? 0 : codeMiembro.hashCode());
		result = prime * result
				+ ((idMiembro == null) ? 0 : idMiembro.hashCode());
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
		Miembro other = (Miembro) obj;
		if (codeMiembro == null) {
			if (other.codeMiembro != null)
				return false;
		} else if (!codeMiembro.equals(other.codeMiembro))
			return false;
		if (idMiembro == null) {
			if (other.idMiembro != null)
				return false;
		} else if (!idMiembro.equals(other.idMiembro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Miembro [idMiembro=");
		builder.append(idMiembro);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", fechaSuscripcion=");
		builder.append(fechaSuscripcion);
		builder.append(", estadoVisibilidad=");
		builder.append(estadoVisibilidad);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", nombreUsuarioTurista=");
		builder.append(nombreUsuarioTurista);
		builder.append(", apellidoUsuarioTurista=");
		builder.append(apellidoUsuarioTurista);
		builder.append(", fotoPerfilUsuarioTurista=");
		builder.append(fotoPerfilUsuarioTurista);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", nombreRegion=");
		builder.append(nombreRegion);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
		builder.append(", beanEstadoMiembro=");
		builder.append(beanEstadoMiembro);
		builder.append(", codeEstadoMiembro=");
		builder.append(codeEstadoMiembro);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", listMiembroIntereses=");
		builder.append(listMiembroIntereses);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", distanciaAlPuntoCreacion=");
		builder.append(distanciaAlPuntoCreacion);
		builder.append(", estadoChatColonia=");
		builder.append(estadoChatColonia);
		builder.append(", tokenFirebase=");
		builder.append(tokenFirebase);
		builder.append("]");
		return builder.toString();
	}
	
	
}
