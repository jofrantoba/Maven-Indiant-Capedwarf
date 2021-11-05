package com.indiana.server.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class Noticia implements Serializable {

	private static final long serialVersionUID = 1L;
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idNoticia;
	@Persistent
	private String codeNoticia;	
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaGeneraNoticia;
	@Persistent
	private String codeTuristaGeneraNoticia;
	@Persistent	
	private String correoTuristaGeneraNoticia;	
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocioGeneraNoticia;
	@Persistent
	private String codeNegocioGeneraNoticia;	
	@Persistent
	private String nombreTuristaNegocioGeneraNoticia;
	@Persistent
	private String fotoPerfilTuristaNegocioGeneraNoticia;
	@Persistent
	private String tipoNoticia;
	@Persistent
	private Integer totalComentario;
	@Persistent
	private Integer totalCompartido;
	@Persistent
	private Integer totalDivulgado;
	@Persistent
	private Date fechaPublicacion;
	@Persistent
	@Unowned
	private Conquista beanConquista;
	@Persistent
	private String codeConquista;
	@Persistent
	@Unowned
	private Novedad beanNovedad;
	@Persistent
	private String codeNovedad;
	@Persistent
	@Unowned
	private PublicidadNegocio beanPublicidadNegocio;
	@Persistent
	private String codePublicidadNegocio;
	@Persistent
	@Unowned
	private Colonia beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	@Unowned
	private Destino beanDestinoConquistado;
	@Persistent
	private String codeDestinoConquistado;
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocioConquistado;
	@Persistent
	private String codeNegocioConquistado;
	@Persistent
	private String nombreColoniaNegocioDestino;
	@Persistent
	@Unowned
	private Miembro beanMiembro;
	@Persistent
	private String codeMiembro;
	@Persistent
	private String descripcion;
	@Persistent
	private String fotoConquistaPublicidad;
	@Persistent
	private String telefono;
	@Persistent
	private String enlace;
	@Persistent
	private Double longitud;
	@Persistent
	private Double latitud;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String estadoTarea;
	@Persistent
	@Unowned
	private Noticia beanNoticiaOriginal;
	@Persistent
	private String codeNoticiaOriginal;
	@Persistent
	private Boolean isPersistente;
	@Persistent(mappedBy = "beanNoticia") 
	private List<MuroNoticia> listMuroNoticia;
	@Persistent(mappedBy = "beanNoticia") 
	private Set<DivulgacionNoticia> listDivulgacionNoticia;	
	@Persistent
	private String href;
	@Persistent
	private String hiperLink;
	
	
	public String getIdNoticia() {
		return idNoticia;
	}
	public void setIdNoticia(String idNoticia) {
		this.idNoticia=idNoticia;				
	}
	public String getCodeNoticia() {
		return codeNoticia;
	}
	public void setCodeNoticia(String codeNoticia) {
		this.codeNoticia = codeNoticia;
	}
	public UsuarioTurista getBeanTuristaGeneraNoticia() {
		return beanTuristaGeneraNoticia;
	}
	public void setBeanTuristaGeneraNoticia(UsuarioTurista beanTuristaGeneraNoticia) {
		this.beanTuristaGeneraNoticia = beanTuristaGeneraNoticia;
	}
	public String getCodeTuristaGeneraNoticia() {
		return codeTuristaGeneraNoticia;
	}	
	
	public String getHiperLink() {
		return hiperLink;
	}
	public void setHiperLink(String hiperLink) {
		this.hiperLink = hiperLink;
	}
	public void setCodeTuristaGeneraNoticia(String codeTuristaGeneraNoticia) {
		this.codeTuristaGeneraNoticia = codeTuristaGeneraNoticia;
	}
	public String getCorreoTuristaGeneraNoticia() {
		return correoTuristaGeneraNoticia;
	}
	public void setCorreoTuristaGeneraNoticia(String correoTuristaGeneraNoticia) {
		this.correoTuristaGeneraNoticia = correoTuristaGeneraNoticia;
	}
	public UsuarioNegocio getBeanNegocioGeneraNoticia() {
		return beanNegocioGeneraNoticia;
	}
	public void setBeanNegocioGeneraNoticia(UsuarioNegocio beanNegocioGeneraNoticia) {
		this.beanNegocioGeneraNoticia = beanNegocioGeneraNoticia;
	}
	public String getCodeNegocioGeneraNoticia() {
		return codeNegocioGeneraNoticia;
	}
	public void setCodeNegocioGeneraNoticia(String codeNegocioGeneraNoticia) {
		this.codeNegocioGeneraNoticia = codeNegocioGeneraNoticia;
	}	
	public String getNombreTuristaNegocioGeneraNoticia() {
		return nombreTuristaNegocioGeneraNoticia;
	}
	public void setNombreTuristaNegocioGeneraNoticia(
			String nombreTuristaNegocioGeneraNoticia) {
		this.nombreTuristaNegocioGeneraNoticia = nombreTuristaNegocioGeneraNoticia;
	}
	public String getFotoPerfilTuristaNegocioGeneraNoticia() {
		return fotoPerfilTuristaNegocioGeneraNoticia;
	}
	public void setFotoPerfilTuristaNegocioGeneraNoticia(
			String fotoPerfilTuristaNegocioGeneraNoticia) {
		this.fotoPerfilTuristaNegocioGeneraNoticia = fotoPerfilTuristaNegocioGeneraNoticia;
	}
	public String getTipoNoticia() {
		return tipoNoticia;
	}
	public void setTipoNoticia(String tipoNoticia) {
		this.tipoNoticia = tipoNoticia;
	}
	public Integer getTotalComentario() {
		return totalComentario;
	}
	public void setTotalComentario(Integer totalComentario) {
		this.totalComentario = totalComentario;
	}
	public Integer getTotalCompartido() {
		return totalCompartido;
	}
	public void setTotalCompartido(Integer totalCompartido) {
		this.totalCompartido = totalCompartido;
	}
	public Integer getTotalDivulgado() {
		return totalDivulgado;
	}
	public void setTotalDivulgado(Integer totalDivulgado) {
		this.totalDivulgado = totalDivulgado;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Conquista getBeanConquista() {
		return beanConquista;
	}
	public void setBeanConquista(Conquista beanConquista) {
		this.beanConquista = beanConquista;
	}
	public String getCodeConquista() {
		return codeConquista;
	}
	public void setCodeConquista(String codeConquista) {
		this.codeConquista = codeConquista;
	}
	public Novedad getBeanNovedad() {
		return beanNovedad;
	}
	public void setBeanNovedad(Novedad beanNovedad) {
		this.beanNovedad = beanNovedad;
	}
	public String getCodeNovedad() {
		return codeNovedad;
	}
	public void setCodeNovedad(String codeNovedad) {
		this.codeNovedad = codeNovedad;
	}
	public PublicidadNegocio getBeanPublicidadNegocio() {
		return beanPublicidadNegocio;
	}
	public void setBeanPublicidadNegocio(PublicidadNegocio beanPublicidadNegocio) {
		this.beanPublicidadNegocio = beanPublicidadNegocio;
	}
	public String getCodePublicidadNegocio() {
		return codePublicidadNegocio;
	}
	public void setCodePublicidadNegocio(String codePublicidadNegocio) {
		this.codePublicidadNegocio = codePublicidadNegocio;
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
	public Destino getBeanDestinoConquistado() {
		return beanDestinoConquistado;
	}
	public void setBeanDestinoConquistado(Destino beanDestinoConquistado) {
		this.beanDestinoConquistado = beanDestinoConquistado;
	}
	public String getCodeDestinoConquistado() {
		return codeDestinoConquistado;
	}
	public void setCodeDestinoConquistado(String codeDestinoConquistado) {
		this.codeDestinoConquistado = codeDestinoConquistado;
	}
	public UsuarioNegocio getBeanNegocioConquistado() {
		return beanNegocioConquistado;
	}
	public void setBeanNegocioConquistado(UsuarioNegocio beanNegocioConquistado) {
		this.beanNegocioConquistado = beanNegocioConquistado;
	}
	public String getCodeNegocioConquistado() {
		return codeNegocioConquistado;
	}
	public void setCodeNegocioConquistado(String codeNegocioConquistado) {
		this.codeNegocioConquistado = codeNegocioConquistado;
	}
	public String getNombreColoniaNegocioDestino() {
		return nombreColoniaNegocioDestino;
	}
	public void setNombreColoniaNegocioDestino(String nombreColoniaNegocioDestino) {
		this.nombreColoniaNegocioDestino = nombreColoniaNegocioDestino;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFotoConquistaPublicidad() {
		return fotoConquistaPublicidad;
	}
	public void setFotoConquistaPublicidad(String fotoConquistaPublicidad) {
		this.fotoConquistaPublicidad = fotoConquistaPublicidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
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
	
	public String getEstadoTarea() {
		return estadoTarea;
	}
	public void setEstadoTarea(String estadoTarea) {
		this.estadoTarea = estadoTarea;
	}
	public Noticia getBeanNoticiaOriginal() {
		return beanNoticiaOriginal;
	}
	public void setBeanNoticiaOriginal(Noticia beanNoticiaOriginal) {
		this.beanNoticiaOriginal = beanNoticiaOriginal;
	}
	public String getCodeNoticiaOriginal() {
		return codeNoticiaOriginal;
	}
	public void setCodeNoticiaOriginal(String codeNoticiaOriginal) {
		this.codeNoticiaOriginal = codeNoticiaOriginal;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}	
	public List<MuroNoticia> getListMuroNoticia() {
		return listMuroNoticia;
	}
	public void setListMuroNoticia(List<MuroNoticia> listMuroNoticia) {
		this.listMuroNoticia = listMuroNoticia;
	}
	public Set<DivulgacionNoticia> getListDivulgacionNoticia() {
		return listDivulgacionNoticia;
	}
	public void setListDivulgacionNoticia(Set<DivulgacionNoticia> listDivulgacionNoticia) {
		this.listDivulgacionNoticia = listDivulgacionNoticia;
	}	
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeNoticia == null) ? 0 : codeNoticia.hashCode());
		result = prime * result
				+ ((idNoticia == null) ? 0 : idNoticia.hashCode());
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
		Noticia other = (Noticia) obj;
		if (codeNoticia == null) {
			if (other.codeNoticia != null)
				return false;
		} else if (!codeNoticia.equals(other.codeNoticia))
			return false;
		if (idNoticia == null) {
			if (other.idNoticia != null)
				return false;
		} else if (!idNoticia.equals(other.idNoticia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Noticia [idNoticia=" + idNoticia + ", codeNoticia=" + codeNoticia + ", beanTuristaGeneraNoticia="
				+ beanTuristaGeneraNoticia + ", codeTuristaGeneraNoticia=" + codeTuristaGeneraNoticia
				+ ", correoTuristaGeneraNoticia=" + correoTuristaGeneraNoticia + ", beanNegocioGeneraNoticia="
				+ beanNegocioGeneraNoticia + ", codeNegocioGeneraNoticia=" + codeNegocioGeneraNoticia
				+ ", nombreTuristaNegocioGeneraNoticia=" + nombreTuristaNegocioGeneraNoticia
				+ ", fotoPerfilTuristaNegocioGeneraNoticia=" + fotoPerfilTuristaNegocioGeneraNoticia + ", tipoNoticia="
				+ tipoNoticia + ", totalComentario=" + totalComentario + ", totalCompartido=" + totalCompartido
				+ ", totalDivulgado=" + totalDivulgado + ", fechaPublicacion=" + fechaPublicacion + ", beanConquista="
				+ beanConquista + ", codeConquista=" + codeConquista + ", beanNovedad=" + beanNovedad + ", codeNovedad="
				+ codeNovedad + ", beanPublicidadNegocio=" + beanPublicidadNegocio + ", codePublicidadNegocio="
				+ codePublicidadNegocio + ", beanColonia=" + beanColonia + ", codeColonia=" + codeColonia
				+ ", beanDestinoConquistado=" + beanDestinoConquistado + ", codeDestinoConquistado="
				+ codeDestinoConquistado + ", beanNegocioConquistado=" + beanNegocioConquistado
				+ ", codeNegocioConquistado=" + codeNegocioConquistado + ", nombreColoniaNegocioDestino="
				+ nombreColoniaNegocioDestino + ", beanMiembro=" + beanMiembro + ", codeMiembro=" + codeMiembro
				+ ", descripcion=" + descripcion + ", fotoConquistaPublicidad=" + fotoConquistaPublicidad
				+ ", telefono=" + telefono + ", enlace=" + enlace + ", longitud=" + longitud + ", latitud=" + latitud
				+ ", version=" + version + ", estadoTarea=" + estadoTarea + ", beanNoticiaOriginal="
				+ beanNoticiaOriginal + ", codeNoticiaOriginal=" + codeNoticiaOriginal + ", isPersistente="
				+ isPersistente + ", listMuroNoticia=" + listMuroNoticia + ", listDivulgacionNoticia="
				+ listDivulgacionNoticia + "]";
	}

	
}
