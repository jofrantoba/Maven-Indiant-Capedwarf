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
public class MensajeNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMensajeNegocio;	
	@Persistent
	private String codeMensajeNegocio;
	@Persistent
	private String mensajeNegocio;
	@Persistent
	private String mensajeTurista;
	@Persistent
	private Date fecha;
	@Persistent
	private Integer anno;
	@Persistent
	private Integer mes;
	@Persistent
	private Integer dia;
	@Persistent
	private Integer hora;
	@Persistent
	private Integer minuto;
	@Persistent
	private Integer segundo;
	@Persistent
	private String estadoProceso;
	@Persistent
	private String estadoVisto;
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
	private String nombreTurista;
	@Persistent
	private String apellidoTurista;	
	@Persistent
	private String fotoPerfilTurista;
	@Persistent
	@Unowned
	private UsuarioNegocio beanNegocio;
	@Persistent
	private String codeNegocio;
	@Persistent
	private String correoNegocio;
	@Persistent
	private String nombreNegocio;	
	@Persistent
	private String fotoPerfilNegocio;
	@Persistent
	private String tipoMensaje;
	@Persistent
	private Boolean isPersistente;
	@Unowned
	private ChatNegocio beanChatNegocio;
	@Persistent
	private String codeChatNegocio;
	
	public String getIdMensajeNegocio() {
		return idMensajeNegocio;
	}
	public void setIdMensajeNegocio(String idMensajeNegocio) {
		this.idMensajeNegocio= idMensajeNegocio;				
	}
	public String getCodeMensajeNegocio() {
		return codeMensajeNegocio;
	}
	public void setCodeMensajeNegocio(String codeMensajeNegocio) {
		this.codeMensajeNegocio = codeMensajeNegocio;
	}
	public String getMensajeNegocio() {
		return mensajeNegocio;
	}
	
	public ChatNegocio getBeanChatNegocio() {
		return beanChatNegocio;
	}
	public void setBeanChatNegocio(ChatNegocio beanChatNegocio) {
		this.beanChatNegocio = beanChatNegocio;
	}
	public String getCodeChatNegocio() {
		return codeChatNegocio;
	}
	public void setCodeChatNegocio(String codeChatNegocio) {
		this.codeChatNegocio = codeChatNegocio;
	}
	public void setMensajeNegocio(String mensajeNegocio) {
		this.mensajeNegocio = mensajeNegocio;
	}
	public String getMensajeTurista() {
		return mensajeTurista;
	}
	public void setMensajeTurista(String mensajeTurista) {
		this.mensajeTurista = mensajeTurista;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
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
	public Integer getHora() {
		return hora;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	public Integer getMinuto() {
		return minuto;
	}
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
	public Integer getSegundo() {
		return segundo;
	}
	public void setSegundo(Integer segundo) {
		this.segundo = segundo;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public String getEstadoVisto() {
		return estadoVisto;
	}
	public void setEstadoVisto(String estadoVisto) {
		this.estadoVisto = estadoVisto;
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
	public String getNombreTurista() {
		return nombreTurista;
	}
	public void setNombreTurista(String nombreTurista) {
		this.nombreTurista = nombreTurista;
	}
	public String getApellidoTurista() {
		return apellidoTurista;
	}
	public void setApellidoTurista(String apellidoTurista) {
		this.apellidoTurista = apellidoTurista;
	}
	public String getFotoPerfilTurista() {
		return fotoPerfilTurista;
	}
	public void setFotoPerfilTurista(String fotoPerfilTurista) {
		this.fotoPerfilTurista = fotoPerfilTurista;
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
	public String getCorreoNegocio() {
		return correoNegocio;
	}
	public void setCorreoNegocio(String correoNegocio) {
		this.correoNegocio = correoNegocio;
	}
	public String getNombreNegocio() {
		return nombreNegocio;
	}
	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}
	public String getFotoPerfilNegocio() {
		return fotoPerfilNegocio;
	}
	public void setFotoPerfilNegocio(String fotoPerfilNegocio) {
		this.fotoPerfilNegocio = fotoPerfilNegocio;
	}
	public String getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
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
		result = prime
				* result
				+ ((codeMensajeNegocio == null) ? 0 : codeMensajeNegocio
						.hashCode());
		result = prime
				* result
				+ ((idMensajeNegocio == null) ? 0 : idMensajeNegocio.hashCode());
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
		MensajeNegocio other = (MensajeNegocio) obj;
		if (codeMensajeNegocio == null) {
			if (other.codeMensajeNegocio != null)
				return false;
		} else if (!codeMensajeNegocio.equals(other.codeMensajeNegocio))
			return false;
		if (idMensajeNegocio == null) {
			if (other.idMensajeNegocio != null)
				return false;
		} else if (!idMensajeNegocio.equals(other.idMensajeNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajeNegocio [idMensajeNegocio=");
		builder.append(idMensajeNegocio);
		builder.append(", codeMensajeNegocio=");
		builder.append(codeMensajeNegocio);
		builder.append(", mensajeNegocio=");
		builder.append(mensajeNegocio);
		builder.append(", mensajeTurista=");
		builder.append(mensajeTurista);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", anno=");
		builder.append(anno);
		builder.append(", mes=");
		builder.append(mes);
		builder.append(", dia=");
		builder.append(dia);
		builder.append(", hora=");
		builder.append(hora);
		builder.append(", minuto=");
		builder.append(minuto);
		builder.append(", segundo=");
		builder.append(segundo);
		builder.append(", estadoProceso=");
		builder.append(estadoProceso);
		builder.append(", estadoVisto=");
		builder.append(estadoVisto);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", nombreTurista=");
		builder.append(nombreTurista);
		builder.append(", apellidoTurista=");
		builder.append(apellidoTurista);
		builder.append(", fotoPerfilTurista=");
		builder.append(fotoPerfilTurista);
		builder.append(", beanNegocio=");
		builder.append(beanNegocio);
		builder.append(", codeNegocio=");
		builder.append(codeNegocio);
		builder.append(", correoNegocio=");
		builder.append(correoNegocio);
		builder.append(", nombreNegocio=");
		builder.append(nombreNegocio);
		builder.append(", fotoPerfilNegocio=");
		builder.append(fotoPerfilNegocio);
		builder.append(", tipoMensaje=");
		builder.append(tipoMensaje);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
