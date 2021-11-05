package com.indiana.server.model.bean;

import java.io.Serializable;


import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
@PersistenceCapable(detachable="true")
public class ChatMiembro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idChatMiembro;
	@Persistent
	private String codeChatMiembro;
	@Persistent
	private java.util.Date fechaCreacion;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaIniciaChat;
	@Persistent
	private String codeTuristaIniciaChat;
	@Persistent
	private String nombreTuristaIniciaChat;
	@Persistent
	private String apellidoTuristaIniciaChat;
	@Persistent
	private String fotoPerfilTuristaIniciaChat;
	@Persistent
	@Unowned
	private Miembro beanMiembroIniciaChat;
	@Persistent
	private String codeMiembro;
	@Persistent
	@Unowned
	private Miembro beanColonia;
	@Persistent
	private String codeColonia;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdChatMiembro() {
		return idChatMiembro;
	}
	public void setIdChatMiembro(String idChatMiembro) {
		this.idChatMiembro=idChatMiembro;				
	}
	public String getCodeChatMiembro() {
		return codeChatMiembro;
	}
	public void setCodeChatMiembro(String codeChatMiembro) {
		this.codeChatMiembro = codeChatMiembro;
	}
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.util.Date fechaCreacion) {
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
	public UsuarioTurista getBeanTuristaIniciaChat() {
		return beanTuristaIniciaChat;
	}
	public void setBeanTuristaIniciaChat(UsuarioTurista beanTuristaIniciaChat) {
		this.beanTuristaIniciaChat = beanTuristaIniciaChat;
	}
	public String getCodeTuristaIniciaChat() {
		return codeTuristaIniciaChat;
	}
	public void setCodeTuristaIniciaChat(String codeTuristaIniciaChat) {
		this.codeTuristaIniciaChat = codeTuristaIniciaChat;
	}
	public String getNombreTuristaIniciaChat() {
		return nombreTuristaIniciaChat;
	}
	public void setNombreTuristaIniciaChat(String nombreTuristaIniciaChat) {
		this.nombreTuristaIniciaChat = nombreTuristaIniciaChat;
	}
	public String getApellidoTuristaIniciaChat() {
		return apellidoTuristaIniciaChat;
	}
	public void setApellidoTuristaIniciaChat(String apellidoTuristaIniciaChat) {
		this.apellidoTuristaIniciaChat = apellidoTuristaIniciaChat;
	}
	public String getFotoPerfilTuristaIniciaChat() {
		return fotoPerfilTuristaIniciaChat;
	}
	public void setFotoPerfilTuristaIniciaChat(String fotoPerfilTuristaIniciaChat) {
		this.fotoPerfilTuristaIniciaChat = fotoPerfilTuristaIniciaChat;
	}
	public Miembro getBeanMiembroIniciaChat() {
		return beanMiembroIniciaChat;
	}
	public void setBeanMiembroIniciaChat(Miembro beanMiembroIniciaChat) {
		this.beanMiembroIniciaChat = beanMiembroIniciaChat;
	}
	public String getCodeMiembro() {
		return codeMiembro;
	}
	public void setCodeMiembro(String codeMiembro) {
		this.codeMiembro = codeMiembro;
	}
	public Miembro getBeanColonia() {
		return beanColonia;
	}
	public void setBeanColonia(Miembro beanColonia) {
		this.beanColonia = beanColonia;
	}
	public String getCodeColonia() {
		return codeColonia;
	}
	public void setCodeColonia(String codeColonia) {
		this.codeColonia = codeColonia;
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
				+ ((codeChatMiembro == null) ? 0 : codeChatMiembro.hashCode());
		result = prime * result
				+ ((idChatMiembro == null) ? 0 : idChatMiembro.hashCode());
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
		ChatMiembro other = (ChatMiembro) obj;
		if (codeChatMiembro == null) {
			if (other.codeChatMiembro != null)
				return false;
		} else if (!codeChatMiembro.equals(other.codeChatMiembro))
			return false;
		if (idChatMiembro == null) {
			if (other.idChatMiembro != null)
				return false;
		} else if (!idChatMiembro.equals(other.idChatMiembro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatMiembro [idChatMiembro=");
		builder.append(idChatMiembro);
		builder.append(", codeChatMiembro=");
		builder.append(codeChatMiembro);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanTuristaIniciaChat=");
		builder.append(beanTuristaIniciaChat);
		builder.append(", codeTuristaIniciaChat=");
		builder.append(codeTuristaIniciaChat);
		builder.append(", nombreTuristaIniciaChat=");
		builder.append(nombreTuristaIniciaChat);
		builder.append(", apellidoTuristaIniciaChat=");
		builder.append(apellidoTuristaIniciaChat);
		builder.append(", fotoPerfilTuristaIniciaChat=");
		builder.append(fotoPerfilTuristaIniciaChat);
		builder.append(", beanMiembroIniciaChat=");
		builder.append(beanMiembroIniciaChat);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", beanColonia=");
		builder.append(beanColonia);
		builder.append(", codeColonia=");
		builder.append(codeColonia);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
