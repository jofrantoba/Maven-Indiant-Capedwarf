package com.indiana.server.model.bean;

import java.io.Serializable;


import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class ChatNegocio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idChatNegocio;
	@Persistent	
	private String codeChatNegocio;	
	@Persistent	
	private java.util.Date fechaApertura;
	@Persistent	
	private java.util.Date fechaCierre;			
	@Persistent	
	private String estadoConversacion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent	
	@Unowned
	private UsuarioNegocio beanNegocio;
	@Persistent
	private String codeNegocio;
	@Persistent	
	@Unowned
	private UsuarioTurista beanTurista;
	@Persistent
	private String codeTurista;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdChatNegocio() {
		return idChatNegocio;
	}
	public void setIdChatNegocio(String idChatNegocio) {
		this.idChatNegocio=idChatNegocio;				
	}
	public String getCodeChatNegocio() {
		return codeChatNegocio;
	}
	public void setCodeChatNegocio(String codeChatNegocio) {
		this.codeChatNegocio = codeChatNegocio;
	}
	public java.util.Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(java.util.Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public java.util.Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(java.util.Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getEstadoConversacion() {
		return estadoConversacion;
	}
	public void setEstadoConversacion(String estadoConversacion) {
		this.estadoConversacion = estadoConversacion;
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
				+ ((codeChatNegocio == null) ? 0 : codeChatNegocio.hashCode());
		result = prime * result
				+ ((idChatNegocio == null) ? 0 : idChatNegocio.hashCode());
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
		ChatNegocio other = (ChatNegocio) obj;
		if (codeChatNegocio == null) {
			if (other.codeChatNegocio != null)
				return false;
		} else if (!codeChatNegocio.equals(other.codeChatNegocio))
			return false;
		if (idChatNegocio == null) {
			if (other.idChatNegocio != null)
				return false;
		} else if (!idChatNegocio.equals(other.idChatNegocio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatNegocio [idChatNegocio=");
		builder.append(idChatNegocio);
		builder.append(", codeChatNegocio=");
		builder.append(codeChatNegocio);
		builder.append(", fechaApertura=");
		builder.append(fechaApertura);
		builder.append(", fechaCierre=");
		builder.append(fechaCierre);
		builder.append(", estadoConversacion=");
		builder.append(estadoConversacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanNegocio=");
		builder.append(beanNegocio);
		builder.append(", codeNegocio=");
		builder.append(codeNegocio);
		builder.append(", beanTurista=");
		builder.append(beanTurista);
		builder.append(", codeTurista=");
		builder.append(codeTurista);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
