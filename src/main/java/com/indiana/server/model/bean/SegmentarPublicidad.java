package com.indiana.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class SegmentarPublicidad implements Serializable {

	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idSegmentarPublicidad;
	@Persistent
	private String codeSegmentarPublicidad;	
	@Persistent
	@Unowned
	private UsuarioNegocio beanUsuarioNegocio;
	@Persistent
	private String codeUsuarioNegocio;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private String nombreColonia;
	@Persistent
	@Unowned
	private Destino beanDestino;
	@Persistent
	private String codeDestino;
	@Persistent
	private String nombreDestino;
	@Persistent
	@Unowned
	private RangoEdad beanRangoEdad;
	@Persistent
	private String codeRangoEdad;
	@Persistent
	private String sexo;	
	@Persistent
	@Unowned
	private Interes beanInteres;	
	@Persistent
	private String nombreInteres;
	@Persistent
	private Pais beanPais;	
	@Persistent
	private String codePais;
	@Persistent
	private String nombrePais;
	@Persistent
	private Region beanRegion;
	@Persistent
	private String codeRegion;
	@Persistent
	private String nombreRegion;
	@Persistent
	private Localidad beanLocalidad;
	@Persistent
	private String codeLocalidad;
	@Persistent
	private String nombreLocalidad;
	@Persistent
	private String tipoSegmento;
	@Persistent	
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Boolean isPersistente;
	public String getIdSegmentarPublicidad() {
		return idSegmentarPublicidad;
	}
	public void setIdSegmentarPublicidad(String idSegmentarPublicidad) {
		this.idSegmentarPublicidad = idSegmentarPublicidad;
	}
	public String getCodeSegmentarPublicidad() {
		return codeSegmentarPublicidad;
	}
	public void setCodeSegmentarPublicidad(String codeSegmentarPublicidad) {
		this.codeSegmentarPublicidad = codeSegmentarPublicidad;
	}
	public UsuarioNegocio getBeanUsuarioNegocio() {
		return beanUsuarioNegocio;
	}
	public void setBeanUsuarioNegocio(UsuarioNegocio beanUsuarioNegocio) {
		this.beanUsuarioNegocio = beanUsuarioNegocio;
	}
	public String getCodeUsuarioNegocio() {
		return codeUsuarioNegocio;
	}
	public void setCodeUsuarioNegocio(String codeUsuarioNegocio) {
		this.codeUsuarioNegocio = codeUsuarioNegocio;
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
	public String getNombreDestino() {
		return nombreDestino;
	}
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	public RangoEdad getBeanRangoEdad() {
		return beanRangoEdad;
	}
	public void setBeanRangoEdad(RangoEdad beanRangoEdad) {
		this.beanRangoEdad = beanRangoEdad;
	}
	public String getCodeRangoEdad() {
		return codeRangoEdad;
	}
	public void setCodeRangoEdad(String codeRangoEdad) {
		this.codeRangoEdad = codeRangoEdad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Interes getBeanInteres() {
		return beanInteres;
	}
	public void setBeanInteres(Interes beanInteres) {
		this.beanInteres = beanInteres;
	}
	public String getNombreInteres() {
		return nombreInteres;
	}
	public void setNombreInteres(String nombreInteres) {
		this.nombreInteres = nombreInteres;
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
	public String getTipoSegmento() {
		return tipoSegmento;
	}
	public void setTipoSegmento(String tipoSegmento) {
		this.tipoSegmento = tipoSegmento;
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
	public String toString() {
		return "SegmentarPublicidad [idSegmentarPublicidad=" + idSegmentarPublicidad + ", codeSegmentarPublicidad="
				+ codeSegmentarPublicidad + ", beanUsuarioNegocio=" + beanUsuarioNegocio + ", codeUsuarioNegocio="
				+ codeUsuarioNegocio + ", beanColonia=" + beanColonia + ", codeColonia=" + codeColonia
				+ ", nombreColonia=" + nombreColonia + ", beanDestino=" + beanDestino + ", codeDestino=" + codeDestino
				+ ", nombreDestino=" + nombreDestino + ", beanRangoEdad=" + beanRangoEdad + ", codeRangoEdad="
				+ codeRangoEdad + ", sexo=" + sexo + ", beanInteres=" + beanInteres + ", nombreInteres=" + nombreInteres
				+ ", beanPais=" + beanPais + ", codePais=" + codePais + ", nombrePais=" + nombrePais + ", beanRegion="
				+ beanRegion + ", codeRegion=" + codeRegion + ", nombreRegion=" + nombreRegion + ", beanLocalidad="
				+ beanLocalidad + ", codeLocalidad=" + codeLocalidad + ", nombreLocalidad=" + nombreLocalidad
				+ ", tipoSegmento=" + tipoSegmento + ", version=" + version + ", isPersistente=" + isPersistente + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanColonia == null) ? 0 : beanColonia.hashCode());
		result = prime * result + ((beanDestino == null) ? 0 : beanDestino.hashCode());
		result = prime * result + ((beanInteres == null) ? 0 : beanInteres.hashCode());
		result = prime * result + ((beanLocalidad == null) ? 0 : beanLocalidad.hashCode());
		result = prime * result + ((beanPais == null) ? 0 : beanPais.hashCode());
		result = prime * result + ((beanRangoEdad == null) ? 0 : beanRangoEdad.hashCode());
		result = prime * result + ((beanRegion == null) ? 0 : beanRegion.hashCode());
		result = prime * result + ((beanUsuarioNegocio == null) ? 0 : beanUsuarioNegocio.hashCode());
		result = prime * result + ((codeColonia == null) ? 0 : codeColonia.hashCode());
		result = prime * result + ((codeDestino == null) ? 0 : codeDestino.hashCode());
		result = prime * result + ((codeLocalidad == null) ? 0 : codeLocalidad.hashCode());
		result = prime * result + ((codePais == null) ? 0 : codePais.hashCode());
		result = prime * result + ((codeRangoEdad == null) ? 0 : codeRangoEdad.hashCode());
		result = prime * result + ((codeRegion == null) ? 0 : codeRegion.hashCode());
		result = prime * result + ((codeSegmentarPublicidad == null) ? 0 : codeSegmentarPublicidad.hashCode());
		result = prime * result + ((codeUsuarioNegocio == null) ? 0 : codeUsuarioNegocio.hashCode());
		result = prime * result + ((idSegmentarPublicidad == null) ? 0 : idSegmentarPublicidad.hashCode());
		result = prime * result + ((isPersistente == null) ? 0 : isPersistente.hashCode());
		result = prime * result + ((nombreColonia == null) ? 0 : nombreColonia.hashCode());
		result = prime * result + ((nombreDestino == null) ? 0 : nombreDestino.hashCode());
		result = prime * result + ((nombreInteres == null) ? 0 : nombreInteres.hashCode());
		result = prime * result + ((nombreLocalidad == null) ? 0 : nombreLocalidad.hashCode());
		result = prime * result + ((nombrePais == null) ? 0 : nombrePais.hashCode());
		result = prime * result + ((nombreRegion == null) ? 0 : nombreRegion.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tipoSegmento == null) ? 0 : tipoSegmento.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		SegmentarPublicidad other = (SegmentarPublicidad) obj;
		if (beanColonia == null) {
			if (other.beanColonia != null)
				return false;
		} else if (!beanColonia.equals(other.beanColonia))
			return false;
		if (beanDestino == null) {
			if (other.beanDestino != null)
				return false;
		} else if (!beanDestino.equals(other.beanDestino))
			return false;
		if (beanInteres == null) {
			if (other.beanInteres != null)
				return false;
		} else if (!beanInteres.equals(other.beanInteres))
			return false;
		if (beanLocalidad == null) {
			if (other.beanLocalidad != null)
				return false;
		} else if (!beanLocalidad.equals(other.beanLocalidad))
			return false;
		if (beanPais == null) {
			if (other.beanPais != null)
				return false;
		} else if (!beanPais.equals(other.beanPais))
			return false;
		if (beanRangoEdad == null) {
			if (other.beanRangoEdad != null)
				return false;
		} else if (!beanRangoEdad.equals(other.beanRangoEdad))
			return false;
		if (beanRegion == null) {
			if (other.beanRegion != null)
				return false;
		} else if (!beanRegion.equals(other.beanRegion))
			return false;
		if (beanUsuarioNegocio == null) {
			if (other.beanUsuarioNegocio != null)
				return false;
		} else if (!beanUsuarioNegocio.equals(other.beanUsuarioNegocio))
			return false;
		if (codeColonia == null) {
			if (other.codeColonia != null)
				return false;
		} else if (!codeColonia.equals(other.codeColonia))
			return false;
		if (codeDestino == null) {
			if (other.codeDestino != null)
				return false;
		} else if (!codeDestino.equals(other.codeDestino))
			return false;
		if (codeLocalidad == null) {
			if (other.codeLocalidad != null)
				return false;
		} else if (!codeLocalidad.equals(other.codeLocalidad))
			return false;
		if (codePais == null) {
			if (other.codePais != null)
				return false;
		} else if (!codePais.equals(other.codePais))
			return false;
		if (codeRangoEdad == null) {
			if (other.codeRangoEdad != null)
				return false;
		} else if (!codeRangoEdad.equals(other.codeRangoEdad))
			return false;
		if (codeRegion == null) {
			if (other.codeRegion != null)
				return false;
		} else if (!codeRegion.equals(other.codeRegion))
			return false;
		if (codeSegmentarPublicidad == null) {
			if (other.codeSegmentarPublicidad != null)
				return false;
		} else if (!codeSegmentarPublicidad.equals(other.codeSegmentarPublicidad))
			return false;
		if (codeUsuarioNegocio == null) {
			if (other.codeUsuarioNegocio != null)
				return false;
		} else if (!codeUsuarioNegocio.equals(other.codeUsuarioNegocio))
			return false;
		if (idSegmentarPublicidad == null) {
			if (other.idSegmentarPublicidad != null)
				return false;
		} else if (!idSegmentarPublicidad.equals(other.idSegmentarPublicidad))
			return false;
		if (isPersistente == null) {
			if (other.isPersistente != null)
				return false;
		} else if (!isPersistente.equals(other.isPersistente))
			return false;
		if (nombreColonia == null) {
			if (other.nombreColonia != null)
				return false;
		} else if (!nombreColonia.equals(other.nombreColonia))
			return false;
		if (nombreDestino == null) {
			if (other.nombreDestino != null)
				return false;
		} else if (!nombreDestino.equals(other.nombreDestino))
			return false;
		if (nombreInteres == null) {
			if (other.nombreInteres != null)
				return false;
		} else if (!nombreInteres.equals(other.nombreInteres))
			return false;
		if (nombreLocalidad == null) {
			if (other.nombreLocalidad != null)
				return false;
		} else if (!nombreLocalidad.equals(other.nombreLocalidad))
			return false;
		if (nombrePais == null) {
			if (other.nombrePais != null)
				return false;
		} else if (!nombrePais.equals(other.nombrePais))
			return false;
		if (nombreRegion == null) {
			if (other.nombreRegion != null)
				return false;
		} else if (!nombreRegion.equals(other.nombreRegion))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tipoSegmento == null) {
			if (other.tipoSegmento != null)
				return false;
		} else if (!tipoSegmento.equals(other.tipoSegmento))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}		
	
}