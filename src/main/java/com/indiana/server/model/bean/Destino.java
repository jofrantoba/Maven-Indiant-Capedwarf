package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Destino implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idDestino;
	@Persistent
	private String codeDestino;
	@Persistent
	private String nombre;
	@Persistent
	private String urlFotoPrincipal;
	@Persistent
	private Double latitud;
	@Persistent
	private Double longitud;
	@Persistent
	private Double radio;
	@Persistent
	private String descripcion;
	@Persistent
	@Unowned
	private Pais beanPais;
	@Persistent
	private String codePais;
	@Persistent
	private String nombrePais;
	@Persistent
	@Unowned
	private Region beanRegion;
	@Persistent
	private String codeRegion;
	@Persistent
	private String nombreRegion;		
	@Persistent
	@Unowned
	private Localidad beanLocalidad;
	@Persistent
	private String codeLocalidad;
	@Persistent
	private String nombreLocalidad;
	@Persistent
	private Date fechaCreacion;	
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
	private String nombreCreador;
	@Persistent
	private String apellidoCreador;
	@Persistent
	private String fotoPerfilCreador;
	@Persistent
	@Unowned
	private CategoriaDestino beanCategoriaDestino;
	@Persistent
	private String codeCategoriaDestino;
	@Persistent
	private String nombreCategoriaDestino;
	@Persistent
	private Boolean isPersistente;
	@NotPersistent
	private Double calculoDistancia;
	@Persistent
	@Unowned
	private List<DestinoCalificaDestino> listaCalificaciones;
	@Persistent
	private Double promedioCalificacion;
	@Persistent
	private Integer totalConquistas;
	@NotPersistent
	private String distanciaTuristaDestino;
	@Persistent
	private String tipoNegocioDestino;
	@Persistent
	private Integer totalDivulgadoNegocio;
	@Persistent
	private Integer totalCompartidoNegocio;
	@Persistent
	private Integer visitasPerfilNegocio;
	
	
	public String getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(String idDestino) {
		this.idDestino=idDestino;		
	}
	public String getCodeDestino() {
		return codeDestino;
	}
	public void setCodeDestino(String codeDestino) {
		this.codeDestino = codeDestino;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getTipoNegocioDestino() {
		return tipoNegocioDestino;
	}
	public void setTipoNegocioDestino(String tipoNegocioDestino) {
		this.tipoNegocioDestino = tipoNegocioDestino;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
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
	public String getNombreRegion() {
		return nombreRegion;
	}
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
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
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
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
	public String getNombreCreador() {
		return nombreCreador;
	}
	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}
	public String getApellidoCreador() {
		return apellidoCreador;
	}
	public void setApellidoCreador(String apellidoCreador) {
		this.apellidoCreador = apellidoCreador;
	}
	public String getFotoPerfilCreador() {
		return fotoPerfilCreador;
	}
	public void setFotoPerfilCreador(String fotoPerfilCreador) {
		this.fotoPerfilCreador = fotoPerfilCreador;
	}
	public CategoriaDestino getBeanCategoriaDestino() {
		return beanCategoriaDestino;
	}
	public void setBeanCategoriaDestino(CategoriaDestino beanCategoriaDestino) {
		this.beanCategoriaDestino = beanCategoriaDestino;
	}
	public String getCodeCategoriaDestino() {
		return codeCategoriaDestino;
	}
	public void setCodeCategoriaDestino(String codeCategoriaDestino) {
		this.codeCategoriaDestino = codeCategoriaDestino;
	}
	public String getNombreCategoriaDestino() {
		return nombreCategoriaDestino;
	}
	public void setNombreCategoriaDestino(String nombreCategoriaDestino) {
		this.nombreCategoriaDestino = nombreCategoriaDestino;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}	
	
	public Double getCalculoDistancia() {
		return calculoDistancia;
	}
	public void setCalculoDistancia(Double calculoDistancia) {
		this.calculoDistancia = calculoDistancia;
	}		
	public List<DestinoCalificaDestino> getListaCalificaciones() {
		return listaCalificaciones;
	}
	public void setListaCalificaciones(List<DestinoCalificaDestino> listaCalificaciones) {
		this.listaCalificaciones = listaCalificaciones;
	}
	public Double getPromedioCalificacion() {
		return promedioCalificacion;
	}
	public void setPromedioCalificacion(Double promedioCalificacion) {
		this.promedioCalificacion = promedioCalificacion;
	}

	public Integer getTotalConquistas() {
		return totalConquistas;
	}
	public void setTotalConquistas(Integer totalConquistas) {
		this.totalConquistas = totalConquistas;
	}	
	
	public String getUrlFotoPrincipal() {
		return urlFotoPrincipal;
	}
	public void setUrlFotoPrincipal(String urlFotoPrincipal) {
		this.urlFotoPrincipal = urlFotoPrincipal;
	}	
	public String getDistanciaTuristaDestino() {
		return distanciaTuristaDestino;
	}
	public void setDistanciaTuristaDestino(String distanciaTuristaDestino) {
		this.distanciaTuristaDestino = distanciaTuristaDestino;
	}		
	public Integer getTotalDivulgadoNegocio() {
		return totalDivulgadoNegocio;
	}
	public void setTotalDivulgadoNegocio(Integer totalDivulgadoNegocio) {
		this.totalDivulgadoNegocio = totalDivulgadoNegocio;
	}
	public Integer getTotalCompartidoNegocio() {
		return totalCompartidoNegocio;
	}
	public void setTotalCompartidoNegocio(Integer totalCompartidoNegocio) {
		this.totalCompartidoNegocio = totalCompartidoNegocio;
	}
	public Integer getVisitasPerfilNegocio() {
		return visitasPerfilNegocio;
	}
	public void setVisitasPerfilNegocio(Integer visitasPerfilNegocio) {
		this.visitasPerfilNegocio = visitasPerfilNegocio;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeDestino == null) ? 0 : codeDestino.hashCode());
		result = prime * result
				+ ((idDestino == null) ? 0 : idDestino.hashCode());
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
		Destino other = (Destino) obj;
		if (codeDestino == null) {
			if (other.codeDestino != null)
				return false;
		} else if (!codeDestino.equals(other.codeDestino))
			return false;
		if (idDestino == null) {
			if (other.idDestino != null)
				return false;
		} else if (!idDestino.equals(other.idDestino))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Destino [idDestino=");
		builder.append(idDestino);
		builder.append(", codeDestino=");
		builder.append(codeDestino);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", radio=");
		builder.append(radio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", beanPais=");
		builder.append(beanPais);
		builder.append(", codePais=");
		builder.append(codePais);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", beanRegion=");
		builder.append(beanRegion);
		builder.append(", codeRegion=");
		builder.append(codeRegion);
		builder.append(", nombreRegion=");
		builder.append(nombreRegion);
		builder.append(", beanLocalidad=");
		builder.append(beanLocalidad);
		builder.append(", codeLocalidad=");
		builder.append(codeLocalidad);
		builder.append(", nombreLocalidad=");
		builder.append(nombreLocalidad);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaCreador=");
		builder.append(beanTuristaCreador);
		builder.append(", codeTuristaCreador=");
		builder.append(codeTuristaCreador);
		builder.append(", nombreCreador=");
		builder.append(nombreCreador);
		builder.append(", apellidoCreador=");
		builder.append(apellidoCreador);
		builder.append(", fotoPerfilCreador=");
		builder.append(fotoPerfilCreador);
		builder.append(", beanCategoriaDestino=");
		builder.append(beanCategoriaDestino);
		builder.append(", codeCategoriaDestino=");
		builder.append(codeCategoriaDestino);
		builder.append(", nombreCategoriaDestino=");
		builder.append(nombreCategoriaDestino);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	public static String fieldIdDestino="idDestino";
	public static String fieldCodeDestino="codeDestino";
	public static String fieldNombre="nombre";
	public static String fieldUrlFotoPrincipal="urlFotoPrincipal";
	public static String fieldLatitud="latitud";
	public static String fieldLongitud="longitud";
	public static String fieldRadio="radio";
	public static String fieldDescripcion="descripcion";
	public static String fieldBeanPais="beanPais";
	public static String fieldCodePais="codePais";
	public static String fieldNombrePais="nombrePais";
	public static String fieldBeanRegion="beanRegion";
	public static String fieldCodeRegion="codeRegion";
	public static String fieldNombreRegion="nombreRegion";
	public static String fieldBeanLocalidad="beanLocalidad";
	public static String fieldCodeLocalidad="codeLocalidad";
	public static String fieldNombreLocalidad="nombreLocalidad";
	public static String fieldFechaCreacion="fechaCreacion";
	public static String fieldVersion="version";
	public static String fieldBeanTuristaCreador="beanTuristaCreador";
	public static String fieldCodeTuristaCreador="codeTuristaCreador";
	public static String fieldNombreCreador="nombreCreador";
	public static String fieldApellidoCreador="apellidoCreador";
	public static String fieldFotoPerfilCreador="fotoPerfilCreador";
	public static String fieldBeanCategoriaDestino="beanCategoriaDestino";
	public static String fieldCodeCategoriaDestino="codeCategoriaDestino";
	public static String fieldNombreCategoriaDestino="nombreCategoriaDestino";
	public static String fieldIsPersistente="isPersistente";
	public static String fieldListaCalificaciones="listaCalificaciones";
	public static String fieldPromedioCalificacion="promedioCalificacion";
	public static String fieldTotalConquistas="totalConquistas";
	public static String fieldTipoNegocioDestino="tipoNegocioDestino";
	
	
	
}
