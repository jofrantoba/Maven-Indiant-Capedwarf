package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

/**
 * La entidad UsuarioTurista almacena la informacion personal del 
 * usuario tipo turista.
 * @author Kiongo Inc SAC
 * @version 0.1
 * @since 21/05/2016
 */
@PersistenceCapable(detachable="true")
public class UsuarioTurista implements Serializable {
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3739347446338813206L;
	
	/**
	 * idUsuarioTurista es la clave primaria para la entidad
	 * UsuarioTurista.
	 */
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idUsuarioTurista;
	/**
	 * codeUsuarioTurista es la clave unica que permite la generacion
	 * de la clave primaria idUsuarioTurista.
	 */
	@Persistent
	private String codeUsuarioTurista;
	@Persistent
	private String correoTurista;
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@Persistent
	@Unowned
	private Pais beanPaisNacimiento;
	@Persistent
	private String codePaisNacimiento;
	@Persistent
	private String nombrePaisNacimiento;
	@Persistent
	@Unowned
	private Region beanRegionNacimiento;
	@Persistent
	private String codeRegionNacimiento;
	@Persistent
	private String nombreRegionNacimiento;
	@Persistent
	@Unowned
	private Localidad beanLocalidadNacimiento;
	@Persistent
	private String codeLocalidadNacimiento;
	@Persistent
	private String nombreLocalidadNacimiento;
	@Persistent
	private java.util.Date fechaNacimiento;
	@Persistent
	private String genero;
	@Persistent
	private String fotoPerfil;
	@Persistent
	private String fotoPortada;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
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
	private Integer totalDescubiertos;
	@Persistent
	private Integer totalConquistas;
	@Persistent
	private Integer totalColonias;
	@Persistent
	private CuentaTurista beanCuentaTurista;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String estadoActivacion;
	@Persistent(mappedBy = "beanTurista") 
	private Set<TuristaInteres> listTuristaIntereses;
	@NotPersistent
	private String codeTipoNotificacion;
	@Persistent
	@Unowned
	private Set<Amistad> listaAmigos;
	@Persistent
	@Unowned
	private Set<Colonia> listaColonias;
	@Persistent
	@Unowned
	private Set<Noticia> listNoticias;
	@Persistent
	private String tokenFirebase;
	@Persistent
	private Integer totalColoniasCreadas;
	@NotPersistent
	private Set<Colonia>listColoniasCreadas;
	@NotPersistent
	private Set<Destino>listDestinosCreados;
	@NotPersistent
	private Set<Destino>listConquistasDestinos;
	@NotPersistent
	private Set<Amistad>listAfiliados;
	@NotPersistent
	private Integer numeroAfiliados;
	@NotPersistent
	private Integer numeroConquistasDistintas;
	@Persistent
	private Integer totalNegociosCreados;
	@Persistent
	private Integer totalConquistasNegocios;
	
	public String getIdUsuarioTurista() {
		return idUsuarioTurista;
	}
	public void setIdUsuarioTurista(String idUsuarioTurista) {		
		this.idUsuarioTurista=idUsuarioTurista;
	}
	
	public String getCodeUsuarioTurista() {
		return codeUsuarioTurista;
	}
	public void setCodeUsuarioTurista(String codeUsuarioTurista) {
		this.codeUsuarioTurista = codeUsuarioTurista;
	}
	public String getCorreoTurista() {
		return correoTurista;
	}
	public void setCorreoTurista(String correoTurista) {
		this.correoTurista = correoTurista;
	}	
	public Integer getTotalNegociosCreados() {
		return totalNegociosCreados;
	}
	public void setTotalNegociosCreados(Integer totalNegociosCreados) {
		this.totalNegociosCreados = totalNegociosCreados;
	}
	public Integer getTotalConquistasNegocios() {
		return totalConquistasNegocios;
	}
	public void setTotalConquistasNegocios(Integer totalConquistasNegocios) {
		this.totalConquistasNegocios = totalConquistasNegocios;
	}
	public String getNombre() {
		return nombre;
	}		
	public Integer getNumeroConquistasDistintas() {
		return numeroConquistasDistintas;
	}
	public void setNumeroConquistasDistintas(Integer numeroConquistasDistintas) {
		this.numeroConquistasDistintas = numeroConquistasDistintas;
	}
	public Integer getTotalColoniasCreadas() {
		return totalColoniasCreadas;
	}	
	public Integer getNumeroAfiliados() {
		return numeroAfiliados;
	}
	public void setNumeroAfiliados(Integer numeroAfiliados) {
		this.numeroAfiliados = numeroAfiliados;
	}
	public void setTotalColoniasCreadas(Integer totalColoniasCreadas) {
		this.totalColoniasCreadas = totalColoniasCreadas;
	}
	public Set<Colonia> getListaColonias() {
		return listaColonias;
	}	
	public Set<Noticia> getListNoticias() {
		return listNoticias;
	}
	public void setListNoticias(Set<Noticia> listNoticias) {
		this.listNoticias = listNoticias;
	}
	public void setListaColonias(Set<Colonia> listaColonias) {
		this.listaColonias = listaColonias;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}	
	public Set<Colonia> getListColoniasCreadas() {
		return listColoniasCreadas;
	}
	public void setListColoniasCreadas(Set<Colonia> listColoniasCreadas) {
		this.listColoniasCreadas = listColoniasCreadas;
	}
	public Set<Destino> getListDestinosCreados() {
		return listDestinosCreados;
	}
	public void setListDestinosCreados(Set<Destino> listDestinosCreados) {
		this.listDestinosCreados = listDestinosCreados;
	}
	public Set<Destino> getListConquistasDestinos() {
		return listConquistasDestinos;
	}
	public void setListConquistasDestinos(Set<Destino> listConquistasDestinos) {
		this.listConquistasDestinos = listConquistasDestinos;
	}
	public Set<Amistad> getListAfiliados() {
		return listAfiliados;
	}
	public void setListAfiliados(Set<Amistad> listAfiliados) {
		this.listAfiliados = listAfiliados;
	}
	public Pais getBeanPaisNacimiento() {
		return beanPaisNacimiento;
	}		
	public String getEstadoActivacion() {
		return estadoActivacion;
	}
	public void setEstadoActivacion(String estadoActivacion) {
		this.estadoActivacion = estadoActivacion;
	}
	public void setBeanPaisNacimiento(Pais beanPaisNacimiento) {
		this.beanPaisNacimiento = beanPaisNacimiento;
	}
	public String getCodePaisNacimiento() {
		return codePaisNacimiento;
	}
	public void setCodePaisNacimiento(String codePaisNacimiento) {
		this.codePaisNacimiento = codePaisNacimiento;
	}
	public Region getBeanRegionNacimiento() {
		return beanRegionNacimiento;
	}
	public void setBeanRegionNacimiento(Region beanRegionNacimiento) {
		this.beanRegionNacimiento = beanRegionNacimiento;
	}
	public String getCodeRegionNacimiento() {
		return codeRegionNacimiento;
	}
	public void setCodeRegionNacimiento(String codeRegionNacimiento) {
		this.codeRegionNacimiento = codeRegionNacimiento;
	}
	public Localidad getBeanLocalidadNacimiento() {
		return beanLocalidadNacimiento;
	}
	public void setBeanLocalidadNacimiento(Localidad beanLocalidadNacimiento) {
		this.beanLocalidadNacimiento = beanLocalidadNacimiento;
	}
	public String getCodeLocalidadNacimiento() {
		return codeLocalidadNacimiento;
	}
	public void setCodeLocalidadNacimiento(String codeLocalidadNacimiento) {
		this.codeLocalidadNacimiento = codeLocalidadNacimiento;
	}	
	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}		
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public String getFotoPortada() {
		return fotoPortada;
	}
	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
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
	public Integer getTotalConquistas() {
		return totalConquistas;
	}
	public void setTotalConquistas(Integer totalConquistas) {
		this.totalConquistas = totalConquistas;
	}
	public Integer getTotalColonias() {
		return totalColonias;
	}
	public void setTotalColonias(Integer totalColonias) {
		this.totalColonias = totalColonias;
	}
	public CuentaTurista getBeanCuentaTurista() {
		return beanCuentaTurista;
	}
	public void setBeanCuentaTurista(CuentaTurista beanCuentaTurista) {
		this.beanCuentaTurista = beanCuentaTurista;
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
	
	public Set<TuristaInteres> getListTuristaIntereses() {
		return listTuristaIntereses;
	}
	public void setListTuristaIntereses(Set<TuristaInteres> listTuristaIntereses) {
		this.listTuristaIntereses = listTuristaIntereses;
	}	
	
	public Set<Amistad> getListaAmigos() {
		return listaAmigos;
	}
	public void setListaAmigos(Set<Amistad> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}
	public String getCodeTipoNotificacion() {
		return codeTipoNotificacion;
	}
	public void setCodeTipoNotificacion(String codeTipoNotificacion) {
		this.codeTipoNotificacion = codeTipoNotificacion;
	}
	
	
	public String getNombrePaisNacimiento() {
		return nombrePaisNacimiento;
	}
	public void setNombrePaisNacimiento(String nombrePaisNacimiento) {
		this.nombrePaisNacimiento = nombrePaisNacimiento;
	}
	public String getNombreRegionNacimiento() {
		return nombreRegionNacimiento;
	}
	public void setNombreRegionNacimiento(String nombreRegionNacimiento) {
		this.nombreRegionNacimiento = nombreRegionNacimiento;
	}
	public String getNombreLocalidadNacimiento() {
		return nombreLocalidadNacimiento;
	}
	public void setNombreLocalidadNacimiento(String nombreLocalidadNacimiento) {
		this.nombreLocalidadNacimiento = nombreLocalidadNacimiento;
	}
	
	public Integer getTotalDescubiertos() {
		return totalDescubiertos;
	}
	public void setTotalDescubiertos(Integer totalDescubiertos) {
		this.totalDescubiertos = totalDescubiertos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeUsuarioTurista == null) ? 0 : codeUsuarioTurista
						.hashCode());
		result = prime * result
				+ ((correoTurista == null) ? 0 : correoTurista.hashCode());
		result = prime
				* result
				+ ((idUsuarioTurista == null) ? 0 : idUsuarioTurista.hashCode());
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
		UsuarioTurista other = (UsuarioTurista) obj;
		if (codeUsuarioTurista == null) {
			if (other.codeUsuarioTurista != null)
				return false;
		} else if (!codeUsuarioTurista.equals(other.codeUsuarioTurista))
			return false;
		if (correoTurista == null) {
			if (other.correoTurista != null)
				return false;
		} else if (!correoTurista.equals(other.correoTurista))
			return false;
		if (idUsuarioTurista == null) {
			if (other.idUsuarioTurista != null)
				return false;
		} else if (!idUsuarioTurista.equals(other.idUsuarioTurista))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioTurista [idUsuarioTurista=");
		builder.append(idUsuarioTurista);
		builder.append(", codeUsuarioTurista=");
		builder.append(codeUsuarioTurista);
		builder.append(", correoTurista=");
		builder.append(correoTurista);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", beanPaisNacimiento=");
		builder.append(beanPaisNacimiento);
		builder.append(", codePaisNacimiento=");
		builder.append(codePaisNacimiento);
		builder.append(", nombrePaisNacimiento=");
		builder.append(nombrePaisNacimiento);
		builder.append(", beanRegionNacimiento=");
		builder.append(beanRegionNacimiento);
		builder.append(", codeRegionNacimiento=");
		builder.append(codeRegionNacimiento);
		builder.append(", nombreRegionNacimiento=");
		builder.append(nombreRegionNacimiento);
		builder.append(", beanLocalidadNacimiento=");
		builder.append(beanLocalidadNacimiento);
		builder.append(", codeLocalidadNacimiento=");
		builder.append(codeLocalidadNacimiento);
		builder.append(", nombreLocalidadNacimiento=");
		builder.append(nombreLocalidadNacimiento);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", genero=");
		builder.append(genero);
		builder.append(", fotoPerfil=");
		builder.append(fotoPerfil);
		builder.append(", fotoPortada=");
		builder.append(fotoPortada);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
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
		builder.append(", totalDescubiertos=");
		builder.append(totalDescubiertos);
		builder.append(", totalConquistas=");
		builder.append(totalConquistas);
		builder.append(", totalColonias=");
		builder.append(totalColonias);
		builder.append(", beanCuentaTurista=");
		builder.append(beanCuentaTurista);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", estadoActivacion=");
		builder.append(estadoActivacion);
		builder.append(", listTuristaIntereses=");
		builder.append(listTuristaIntereses);
		builder.append(", codeTipoNotificacion=");
		builder.append(codeTipoNotificacion);
		builder.append(", listaAmigos=");
		builder.append(listaAmigos);
		builder.append(", listaColonias=");
		builder.append(listaColonias);
		builder.append(", listNoticias=");
		builder.append(listNoticias);
		builder.append(", tokenFirebase=");
		builder.append(tokenFirebase);
		builder.append("]");
		return builder.toString();
	}
	
	
	public static String fieldsResult(ArrayList<String> args){
		StringBuilder builder = new StringBuilder();
		int indexFinal=args.size()-1;
		for(int i=0;i<args.size();i++){
			builder.append(args.get(i));
			if(i!=indexFinal){
			builder.append(",");
			}
		}		
		return builder.toString();
	}
	
	public static String fieldIdUsuarioTurista="idUsuarioTurista";
	public static String fieldCodeUsuarioTurista="codeUsuarioTurista";
	public static String fieldCorreoTurista="correoTurista";
	public static String fieldNombre="nombre";
	public static String fieldApellido="apellido";
	public static String fieldBeanPaisNacimiento="beanPaisNacimiento";
	public static String fieldCodePaisNacimiento="codePaisNacimiento";
	public static String fieldNombrePaisNacimiento="nombrePaisNacimiento";
	public static String fieldBeanRegionNacimiento="beanRegionNacimiento";
	public static String fieldCodeRegionNacimiento="codeRegionNacimiento";
	public static String fieldNombreRegionNacimiento="nombreRegionNacimiento";
	public static String fieldBeanLocalidadNacimiento="beanLocalidadNacimiento";
	public static String fieldCodeLocalidadNacimiento="codeLocalidadNacimiento";
	public static String fieldNombreLocalidadNacimiento="nombreLocalidadNacimiento";
	public static String fieldFechaNacimiento="fechaNacimiento";
	public static String fieldGenero="genero";
	public static String fieldFotoPerfil="fotoPerfil";
	public static String fieldFotoPortada="fotoPortada";
	public static String fieldBeanInteresUno="beanInteresUno";
	public static String fieldNombreInteresUno="nombreInteresUno";
	public static String fieldModaInteresUno="modaInteresUno";
	public static String fieldBeanInteresDos="beanInteresDos";
	public static String fieldNombreInteresDos="nombreInteresDos";
	public static String fieldModaInteresDos="modaInteresDos";
	public static String fieldBeanInteresTres="beanInteresTres";
	public static String fieldNombreInteresTres="nombreInteresTres";
	public static String fieldModaInteresTres="modaInteresTres";
	public static String fieldTotalDescubiertos="totalDescubiertos";
	public static String fieldTotalConquistas="totalConquistas";
	public static String fieldTotalColonias="totalColonias";
	public static String fieldBeanCuentaTurista="beanCuentaTurista";
	public static String fieldIsPersistente="isPersistente";
	public static String fieldEstadoActivacion="estadoActivacion";
	public static String fieldListTuristaIntereses="listTuristaIntereses";
	public static String fieldListaAmigos="listaAmigos";
	public static String fieldListaColonias="listaColonias";
	public static String fieldListNoticias="listNoticias";
	public static String fieldTokenFirebase="tokenFirebase";
	public static String fieldTotalColoniasCreadas="totalColoniasCreadas";
	public static String fieldTotalNegociosCreados="totalNegociosCreados";
	public static String fieldTotalConquistasNegocios="totalConquistasNegocios";
	
	
}
