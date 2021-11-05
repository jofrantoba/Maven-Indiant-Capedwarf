package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Colonia implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;
	@Persistent
	private String descripcion;
	@Persistent
	private Double latitud;
	@Persistent
	private Double longitud;
	@Persistent
	private Double radio;
	@Persistent
	private java.util.Date fechaCreacion;	
	@Persistent
	private Integer cantidadMiembros;
	@Persistent
	@Unowned
	private Pais beanPaisColonia;
	@Persistent
	private String codePaisColonia;
	@Persistent
	private String nombrePaisColonia;
	@Persistent
	@Unowned
	private Region beanRegionColonia;
	@Persistent
	private String codeRegionColonia;
	@Persistent
	private String nombreRegionColonia;		
	@Persistent
	@Unowned
	private Localidad beanLocalidadColonia;
	@Persistent
	private String codeLocalidadColonia;
	@Persistent
	private String nombreLocalidadColonia;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaCreador;
	@Persistent
	private String codeTuristaCreador;	
	@Persistent
	private String nombreCreadorColonia;
	@Persistent
	private String apellidoCreadorColonia;
	@Persistent
	private String fotoPerfilCreadorColonia;
	@Persistent
	@Unowned
	private Interes beanInteresUno;
	@Persistent
	private String nombreInteresUno;
	@Persistent
	private Double modaInteresUno;
	@Persistent
	@Unowned
	private Interes beanInteresDos;
	@Persistent
	private String nombreInteresDos;
	@Persistent
	private Double modaInteresDos;
	@Persistent
	@Unowned
	private Interes beanInteresTres;
	@Persistent
	private String nombreInteresTres;
	@Persistent
	private Double modaInteresTres;
	@Persistent
	private Boolean isPersistente;
	@Persistent(mappedBy = "beanColonia") 
	private Set<ColoniaInteres> listColoniaIntereses;
	@Persistent(mappedBy = "beanColonia")
	private List<Miembro> listMiembro;
	@Persistent(mappedBy = "beanColonia")
	private Set<ProxColoniaDestino>listProxColoniaDestino;	
	@Persistent(mappedBy = "beanColonia")
	private List<DetalleInteres> listDetalleInteres;
	@NotPersistent
	private Double distanciaAlPuntoCreacion;
	@NotPersistent
	private Double calculoDistancia;
	@NotPersistent
	private String distanciaTuristaColonia;
	@NotPersistent
	private String codeTuristaSeUne;
	@NotPersistent
	private Double latitudTuristaSeUne;
	@NotPersistent
	private Double longitudTuristaSeUne;
	@NotPersistent
	private java.util.Date fechaSuscripcion;
	@NotPersistent
	private String codeMiembro;
	@NotPersistent
	private String estadoVisibilidad;
	@Persistent
	private Boolean enComun;
	@NotPersistent
	private Integer indexList;
	
	public String getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(String idColonia) {
		this.idColonia=idColonia;				
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
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}	

	public Boolean getEnComun() {
		return enComun;
	}
	public void setEnComun(Boolean enComun) {
		this.enComun = enComun;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getRadio() {
		return radio;
	}
	public void setRadio(Double radio) {
		this.radio = radio;
	}
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.util.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getCantidadMiembros() {
		return cantidadMiembros;
	}
	public void setCantidadMiembros(Integer cantidadMiembros) {
		this.cantidadMiembros = cantidadMiembros;
	}
	public Pais getBeanPaisColonia() {
		return beanPaisColonia;
	}
	public void setBeanPaisColonia(Pais beanPaisColonia) {
		this.beanPaisColonia = beanPaisColonia;
	}
	public String getCodePaisColonia() {
		return codePaisColonia;
	}
	public void setCodePaisColonia(String codePaisColonia) {
		this.codePaisColonia = codePaisColonia;
	}
	public String getNombrePaisColonia() {
		return nombrePaisColonia;
	}
	public void setNombrePaisColonia(String nombrePaisColonia) {
		this.nombrePaisColonia = nombrePaisColonia;
	}	
	public Set<ProxColoniaDestino> getListProxColoniaDestino() {
		return listProxColoniaDestino;
	}
	public void setListProxColoniaDestino(Set<ProxColoniaDestino> listProxColoniaDestino) {
		this.listProxColoniaDestino = listProxColoniaDestino;
	}
	public Region getBeanRegionColonia() {
		return beanRegionColonia;
	}
	public void setBeanRegionColonia(Region beanRegionColonia) {
		this.beanRegionColonia = beanRegionColonia;
	}
	public String getCodeRegionColonia() {
		return codeRegionColonia;
	}
	public void setCodeRegionColonia(String codeRegionColonia) {
		this.codeRegionColonia = codeRegionColonia;
	}
	public String getNombreRegionColonia() {
		return nombreRegionColonia;
	}
	public void setNombreRegionColonia(String nombreRegionColonia) {
		this.nombreRegionColonia = nombreRegionColonia;
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
	public UsuarioTurista getBeanTuristaCreador() {
		return beanTuristaCreador;
	}
	public void setBeanTuristaCreador(UsuarioTurista beanTuristaCreador) {
		this.beanTuristaCreador = beanTuristaCreador;
	}
	public String getCodeTuristaCreador() {
		return codeTuristaCreador;
	}
	public void setCodeTuristaCreador(String codeTuristaCreador) {
		this.codeTuristaCreador = codeTuristaCreador;
	}	
	public String getNombreCreadorColonia() {
		return nombreCreadorColonia;
	}
	public void setNombreCreadorColonia(String nombreCreadorColonia) {
		this.nombreCreadorColonia = nombreCreadorColonia;
	}
	public String getApellidoCreadorColonia() {
		return apellidoCreadorColonia;
	}
	public void setApellidoCreadorColonia(String apellidoCreadorColonia) {
		this.apellidoCreadorColonia = apellidoCreadorColonia;
	}
	public String getFotoPerfilCreadorColonia() {
		return fotoPerfilCreadorColonia;
	}
	public void setFotoPerfilCreadorColonia(String fotoPerfilCreadorColonia) {
		this.fotoPerfilCreadorColonia = fotoPerfilCreadorColonia;
	}
	public Interes getBeanInteresUno() {
		return beanInteresUno;
	}
	public void setBeanInteresUno(Interes beanInteresUno) {
		this.beanInteresUno = beanInteresUno;
	}
	public String getNombreInteresUno() {
		return nombreInteresUno;
	}
	public void setNombreInteresUno(String nombreInteresUno) {
		this.nombreInteresUno = nombreInteresUno;
	}
	public Interes getBeanInteresDos() {
		return beanInteresDos;
	}
	public void setBeanInteresDos(Interes beanInteresDos) {
		this.beanInteresDos = beanInteresDos;
	}
	public String getNombreInteresDos() {
		return nombreInteresDos;
	}
	public void setNombreInteresDos(String nombreInteresDos) {
		this.nombreInteresDos = nombreInteresDos;
	}
	public Interes getBeanInteresTres() {
		return beanInteresTres;
	}
	public void setBeanInteresTres(Interes beanInteresTres) {
		this.beanInteresTres = beanInteresTres;
	}
	public String getNombreInteresTres() {
		return nombreInteresTres;
	}
	public void setNombreInteresTres(String nombreInteresTres) {
		this.nombreInteresTres = nombreInteresTres;
	}	
	public Localidad getBeanLocalidadColonia() {
		return beanLocalidadColonia;
	}
	public void setBeanLocalidadColonia(Localidad beanLocalidadColonia) {
		this.beanLocalidadColonia = beanLocalidadColonia;
	}
	public String getCodeLocalidadColonia() {
		return codeLocalidadColonia;
	}
	public void setCodeLocalidadColonia(String codeLocalidadColonia) {
		this.codeLocalidadColonia = codeLocalidadColonia;
	}
	public String getNombreLocalidadColonia() {
		return nombreLocalidadColonia;
	}
	public void setNombreLocalidadColonia(String nombreLocalidadColonia) {
		this.nombreLocalidadColonia = nombreLocalidadColonia;
	}	
	public Double getModaInteresUno() {
		return modaInteresUno;
	}
	public void setModaInteresUno(Double modaInteresUno) {
		this.modaInteresUno = modaInteresUno;
	}
	public Double getModaInteresDos() {
		return modaInteresDos;
	}
	public void setModaInteresDos(Double modaInteresDos) {
		this.modaInteresDos = modaInteresDos;
	}
	public Double getModaInteresTres() {
		return modaInteresTres;
	}
	public void setModaInteresTres(Double modaInteresTres) {
		this.modaInteresTres = modaInteresTres;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}
	
	public Set<ColoniaInteres> getListColoniaIntereses() {
		return listColoniaIntereses;
	}
	public void setListColoniaIntereses(Set<ColoniaInteres> listColoniaIntereses) {
		this.listColoniaIntereses = listColoniaIntereses;
	}
	
	public List<Miembro> getListMiembro() {
		return listMiembro;
	}
	public void setListMiembro(List<Miembro> listMiembro) {
		this.listMiembro = listMiembro;
	}			
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<DetalleInteres> getListDetalleInteres() {
		return listDetalleInteres;
	}
	public void setListDetalleInteres(List<DetalleInteres> listDetalleInteres) {
		this.listDetalleInteres = listDetalleInteres;
	}
	public Double getDistanciaAlPuntoCreacion() {
		return distanciaAlPuntoCreacion;
	}
	public void setDistanciaAlPuntoCreacion(Double distanciaAlPuntoCreacion) {
		this.distanciaAlPuntoCreacion = distanciaAlPuntoCreacion;
	}
	
	public Double getCalculoDistancia() {
		return calculoDistancia;
	}
	public void setCalculoDistancia(Double calculoDistancia) {
		this.calculoDistancia = calculoDistancia;
	}
	
	public String getDistanciaTuristaColonia() {
		return distanciaTuristaColonia;
	}
	public void setDistanciaTuristaColonia(String distanciaTuristaColonia) {
		this.distanciaTuristaColonia = distanciaTuristaColonia;
	}	
	public String getCodeTuristaSeUne() {
		return codeTuristaSeUne;
	}
	public void setCodeTuristaSeUne(String codeTuristaSeUne) {
		this.codeTuristaSeUne = codeTuristaSeUne;
	}
	
	public Double getLatitudTuristaSeUne() {
		return latitudTuristaSeUne;
	}
	public void setLatitudTuristaSeUne(Double latitudTuristaSeUne) {
		this.latitudTuristaSeUne = latitudTuristaSeUne;
	}
	public Double getLongitudTuristaSeUne() {
		return longitudTuristaSeUne;
	}
	public void setLongitudTuristaSeUne(Double longitudTuristaSeUne) {
		this.longitudTuristaSeUne = longitudTuristaSeUne;
	}
		
	public java.util.Date getFechaSuscripcion() {
		return fechaSuscripcion;
	}
	public void setFechaSuscripcion(java.util.Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}
	
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
	}
	public String getEstadoVisibilidad() {
		return estadoVisibilidad;
	}
	public void setEstadoVisibilidad(String estadoVisibilidad) {
		this.estadoVisibilidad = estadoVisibilidad;
	}	
	
	public Integer getIndexList() {
		return indexList;
	}
	public void setIndexList(Integer indexList) {
		this.indexList = indexList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeColonia == null) ? 0 : codeColonia.hashCode());
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
		Colonia other = (Colonia) obj;
		if (codeColonia == null) {
			if (other.codeColonia != null)
				return false;
		} else if (!codeColonia.equals(other.codeColonia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Colonia [idColonia=");
		builder.append(idColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", nombreColonia=");
		builder.append(nombreColonia);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", radio=");
		builder.append(radio);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", cantidadMiembros=");
		builder.append(cantidadMiembros);
		builder.append(", beanPaisColonia=");
		builder.append(beanPaisColonia);
		builder.append(", codePaisColonia=");
		builder.append(codePaisColonia);
		builder.append(", nombrePaisColonia=");
		builder.append(nombrePaisColonia);
		builder.append(", beanRegionColonia=");
		builder.append(beanRegionColonia);
		builder.append(", codeRegionColonia=");
		builder.append(codeRegionColonia);
		builder.append(", nombreRegionColonia=");
		builder.append(nombreRegionColonia);
		builder.append(", beanLocalidadColonia=");
		builder.append(beanLocalidadColonia);
		builder.append(", codeLocalidadColonia=");
		builder.append(codeLocalidadColonia);
		builder.append(", nombreLocalidadColonia=");
		builder.append(nombreLocalidadColonia);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaCreador=");
		builder.append(beanTuristaCreador);
		builder.append(", codeTuristaCreador=");
		builder.append(codeTuristaCreador);
		builder.append(", nombreCreadorColonia=");
		builder.append(nombreCreadorColonia);
		builder.append(", apellidoCreadorColonia=");
		builder.append(apellidoCreadorColonia);
		builder.append(", fotoPerfilCreadorColonia=");
		builder.append(fotoPerfilCreadorColonia);
		builder.append(", beanInteresUno=");
		builder.append(beanInteresUno);
		builder.append(", nombreInteresUno=");
		builder.append(nombreInteresUno);
		builder.append(", modaInteresUno=");
		builder.append(modaInteresUno);
		builder.append(", beanInteresDos=");
		builder.append(beanInteresDos);
		builder.append(", nombreInteresDos=");
		builder.append(nombreInteresDos);
		builder.append(", modaInteresDos=");
		builder.append(modaInteresDos);
		builder.append(", beanInteresTres=");
		builder.append(beanInteresTres);
		builder.append(", nombreInteresTres=");
		builder.append(nombreInteresTres);
		builder.append(", modaInteresTres=");
		builder.append(modaInteresTres);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", listColoniaIntereses=");
		builder.append(listColoniaIntereses);
		builder.append(", listMiembro=");
		builder.append(listMiembro);
		builder.append(", listProxColoniaDestino=");
		builder.append(listProxColoniaDestino);
		builder.append(", listDetalleInteres=");
		builder.append(listDetalleInteres);
		builder.append(", distanciaAlPuntoCreacion=");
		builder.append(distanciaAlPuntoCreacion);
		builder.append(", calculoDistancia=");
		builder.append(calculoDistancia);
		builder.append(", distanciaTuristaColonia=");
		builder.append(distanciaTuristaColonia);
		builder.append(", codeTuristaSeUne=");
		builder.append(codeTuristaSeUne);
		builder.append(", latitudTuristaSeUne=");
		builder.append(latitudTuristaSeUne);
		builder.append(", longitudTuristaSeUne=");
		builder.append(longitudTuristaSeUne);
		builder.append(", fechaSuscripcion=");
		builder.append(fechaSuscripcion);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", estadoVisibilidad=");
		builder.append(estadoVisibilidad);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
