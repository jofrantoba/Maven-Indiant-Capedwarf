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
public class MuroNoticia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1720217275861470776L;
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMuroNoticia;
	@Persistent
	private String codeMuroNoticia;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaMuro;
	@Persistent
	private String codeTuristaMuro;
	@Persistent
	private String correoTuristaMuro;
	@Persistent
	private Noticia beanNoticia;
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
	private String hiperLink;
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
	private Boolean isPersistente;
	@Persistent
	private String visto;	
	@Persistent
	private String href;
	@Persistent
	private Boolean haDivulgado;
	
	public String getIdMuroNoticia() {
		return idMuroNoticia;
	}
	public void setIdMuroNoticia(String idMuroNoticia) {
		this.idMuroNoticia=idMuroNoticia;				
	}
	public String getCodeMuroNoticia() {
		return codeMuroNoticia;
	}
	public void setCodeMuroNoticia(String codeMuroNoticia) {
		this.codeMuroNoticia = codeMuroNoticia;
	}
	public UsuarioTurista getBeanTuristaMuro() {
		return beanTuristaMuro;
	}
	public void setBeanTuristaMuro(UsuarioTurista beanTuristaMuro) {
		this.beanTuristaMuro = beanTuristaMuro;
	}
	
	public String getHiperLink() {
		return hiperLink;
	}
	public void setHiperLink(String hiperLink) {
		this.hiperLink = hiperLink;
	}
	public String getCodeTuristaMuro() {
		return codeTuristaMuro;
	}
	public void setCodeTuristaMuro(String codeTuristaMuro) {
		this.codeTuristaMuro = codeTuristaMuro;
	}
	public String getCorreoTuristaMuro() {
		return correoTuristaMuro;
	}
	public void setCorreoTuristaMuro(String correoTuristaMuro) {
		this.correoTuristaMuro = correoTuristaMuro;
	}
	public Noticia getBeanNoticia() {
		return beanNoticia;
	}
	public void setBeanNoticia(Noticia beanNoticia) {
		this.beanNoticia = beanNoticia;
	}
	public String getCodeNoticia() {
		return codeNoticia;
	}
	public void setCodeNoticia(String codeNoticia) {
		this.codeNoticia = codeNoticia;
	}	
	public Boolean getHaDivulgado() {
		return haDivulgado;
	}
	public void setHaDivulgado(Boolean haDivulgado) {
		this.haDivulgado = haDivulgado;
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
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIsPersistente(Boolean isPersistente) {
		this.isPersistente = isPersistente;
	}	
	public String getVisto() {
		return visto;
	}
	public void setVisto(String visto) {
		this.visto = visto;
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
		MuroNoticia other = (MuroNoticia) obj;
		if (codeNoticia == null) {
			if (other.codeNoticia != null)
				return false;
		} else if (!codeNoticia.equals(other.codeNoticia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MuroNoticia [idMuroNoticia=");
		builder.append(idMuroNoticia);
		builder.append(", codeMuroNoticia=");
		builder.append(codeMuroNoticia);
		builder.append(", beanTuristaMuro=");
		builder.append(beanTuristaMuro);
		builder.append(", codeTuristaMuro=");
		builder.append(codeTuristaMuro);
		builder.append(", correoTuristaMuro=");
		builder.append(correoTuristaMuro);
		builder.append(", beanNoticia=");
		builder.append(beanNoticia);
		builder.append(", codeNoticia=");
		builder.append(codeNoticia);
		builder.append(", beanTuristaGeneraNoticia=");
		builder.append(beanTuristaGeneraNoticia);
		builder.append(", codeTuristaGeneraNoticia=");
		builder.append(codeTuristaGeneraNoticia);
		builder.append(", correoTuristaGeneraNoticia=");
		builder.append(correoTuristaGeneraNoticia);
		builder.append(", beanNegocioGeneraNoticia=");
		builder.append(beanNegocioGeneraNoticia);
		builder.append(", codeNegocioGeneraNoticia=");
		builder.append(codeNegocioGeneraNoticia);
		builder.append(", nombreTuristaNegocioGeneraNoticia=");
		builder.append(nombreTuristaNegocioGeneraNoticia);
		builder.append(", fotoPerfilTuristaNegocioGeneraNoticia=");
		builder.append(fotoPerfilTuristaNegocioGeneraNoticia);
		builder.append(", tipoNoticia=");
		builder.append(tipoNoticia);
		builder.append(", totalComentario=");
		builder.append(totalComentario);
		builder.append(", totalCompartido=");
		builder.append(totalCompartido);
		builder.append(", totalDivulgado=");
		builder.append(totalDivulgado);
		builder.append(", fechaPublicacion=");
		builder.append(fechaPublicacion);
		builder.append(", beanConquista=");
		builder.append(beanConquista);
		builder.append(", codeConquista=");
		builder.append(codeConquista);
		builder.append(", beanNovedad=");
		builder.append(beanNovedad);
		builder.append(", codeNovedad=");
		builder.append(codeNovedad);
		builder.append(", beanPublicidadNegocio=");
		builder.append(beanPublicidadNegocio);
		builder.append(", codePublicidadNegocio=");
		builder.append(codePublicidadNegocio);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", beanDestinoConquistado=");
		builder.append(beanDestinoConquistado);
		builder.append(", codeDestinoConquistado=");
		builder.append(codeDestinoConquistado);
		builder.append(", beanNegocioConquistado=");
		builder.append(beanNegocioConquistado);
		builder.append(", codeNegocioConquistado=");
		builder.append(codeNegocioConquistado);
		builder.append(", nombreColoniaNegocioDestino=");
		builder.append(nombreColoniaNegocioDestino);
		builder.append(", beanMiembro=");
		builder.append(beanMiembro);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fotoConquistaPublicidad=");
		builder.append(fotoConquistaPublicidad);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", enlace=");
		builder.append(enlace);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append(", visto=");
		builder.append(visto);
		builder.append("]");
		return builder.toString();
	}
	
	
}
