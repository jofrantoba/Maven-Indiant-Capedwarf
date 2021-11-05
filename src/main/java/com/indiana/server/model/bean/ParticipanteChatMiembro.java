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
public class ParticipanteChatMiembro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idParticipanteChatMiembro;
	@Persistent
	private String codeParticipanteChatMiembro;
	@Persistent
	private Date fechaUnion;
	@Persistent
	private String estado;	
	@Persistent
	private Long version;	
	@NotPersistent
	private String operacion;
	@Persistent
	@Unowned
	private Miembro beanMiembro;
	@Persistent
	private String codeMiembro;	
	@Persistent
	@Unowned
	private UsuarioTurista beanTuristaParticipante;
	@Persistent
	private String codeTuristaParticipante;
	@Persistent
	private String nombreTuristaParticipante;
	@Persistent
	private String apellidoTuristaParticipante;
	@Persistent
	private String fotoPerfilTuristaParticipante;
	@Persistent
	private String tipoChat;
	@Persistent
	private String tipoParticipante;
	@Persistent
	@Unowned
	private TablonColonia beanTablonColonia;
	@Persistent
	private String codeTablonColonia;
	@Persistent
	@Unowned
	private ChatMiembro beanChatMiembro;
	@Persistent
	private String codeChatMiembro;
	@Persistent
	private String estadoParticipante;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdParticipanteChatMiembro() {
		return idParticipanteChatMiembro;
	}
	public void setIdParticipanteChatMiembro(String idParticipanteChatMiembro) {
		this.idParticipanteChatMiembro=idParticipanteChatMiembro;				
	}
	public String getCodeParticipanteChatMiembro() {
		return codeParticipanteChatMiembro;
	}
	public void setCodeParticipanteChatMiembro(String codeParticipanteChatMiembro) {
		this.codeParticipanteChatMiembro = codeParticipanteChatMiembro;
	}
	public Date getFechaUnion() {
		return fechaUnion;
	}
	public void setFechaUnion(Date fechaUnion) {
		this.fechaUnion = fechaUnion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public UsuarioTurista getBeanTuristaParticipante() {
		return beanTuristaParticipante;
	}
	public void setBeanTuristaParticipante(UsuarioTurista beanTuristaParticipante) {
		this.beanTuristaParticipante = beanTuristaParticipante;
	}
	public String getCodeTuristaParticipante() {
		return codeTuristaParticipante;
	}
	public void setCodeTuristaParticipante(String codeTuristaParticipante) {
		this.codeTuristaParticipante = codeTuristaParticipante;
	}
	public String getNombreTuristaParticipante() {
		return nombreTuristaParticipante;
	}
	public void setNombreTuristaParticipante(String nombreTuristaParticipante) {
		this.nombreTuristaParticipante = nombreTuristaParticipante;
	}
	public String getApellidoTuristaParticipante() {
		return apellidoTuristaParticipante;
	}
	public void setApellidoTuristaParticipante(String apellidoTuristaParticipante) {
		this.apellidoTuristaParticipante = apellidoTuristaParticipante;
	}
	public String getFotoPerfilTuristaParticipante() {
		return fotoPerfilTuristaParticipante;
	}
	public void setFotoPerfilTuristaParticipante(
			String fotoPerfilTuristaParticipante) {
		this.fotoPerfilTuristaParticipante = fotoPerfilTuristaParticipante;
	}
	public String getTipoChat() {
		return tipoChat;
	}
	public void setTipoChat(String tipoChat) {
		this.tipoChat = tipoChat;
	}
	public String getTipoParticipante() {
		return tipoParticipante;
	}
	public void setTipoParticipante(String tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}
	public TablonColonia getBeanTablonColonia() {
		return beanTablonColonia;
	}
	public void setBeanTablonColonia(TablonColonia beanTablonColonia) {
		this.beanTablonColonia = beanTablonColonia;
	}
	public String getCodeTablonColonia() {
		return codeTablonColonia;
	}
	public void setCodeTablonColonia(String codeTablonColonia) {
		this.codeTablonColonia = codeTablonColonia;
	}
	public ChatMiembro getBeanChatMiembro() {
		return beanChatMiembro;
	}
	public void setBeanChatMiembro(ChatMiembro beanChatMiembro) {
		this.beanChatMiembro = beanChatMiembro;
	}
	public String getCodeChatMiembro() {
		return codeChatMiembro;
	}
	public void setCodeChatMiembro(String codeChatMiembro) {
		this.codeChatMiembro = codeChatMiembro;
	}
	public String getEstadoParticipante() {
		return estadoParticipante;
	}
	public void setEstadoParticipante(String estadoParticipante) {
		this.estadoParticipante = estadoParticipante;
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
				+ ((codeParticipanteChatMiembro == null) ? 0
						: codeParticipanteChatMiembro.hashCode());
		result = prime
				* result
				+ ((idParticipanteChatMiembro == null) ? 0
						: idParticipanteChatMiembro.hashCode());
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
		ParticipanteChatMiembro other = (ParticipanteChatMiembro) obj;
		if (codeParticipanteChatMiembro == null) {
			if (other.codeParticipanteChatMiembro != null)
				return false;
		} else if (!codeParticipanteChatMiembro
				.equals(other.codeParticipanteChatMiembro))
			return false;
		if (idParticipanteChatMiembro == null) {
			if (other.idParticipanteChatMiembro != null)
				return false;
		} else if (!idParticipanteChatMiembro
				.equals(other.idParticipanteChatMiembro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParticipanteChatMiembro [idParticipanteChatMiembro=");
		builder.append(idParticipanteChatMiembro);
		builder.append(", codeParticipanteChatMiembro=");
		builder.append(codeParticipanteChatMiembro);
		builder.append(", fechaUnion=");
		builder.append(fechaUnion);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanMiembro=");
		builder.append(beanMiembro);
		builder.append(", codeMiembro=");
		builder.append(codeMiembro);
		builder.append(", beanTuristaParticipante=");
		builder.append(beanTuristaParticipante);
		builder.append(", codeTuristaParticipante=");
		builder.append(codeTuristaParticipante);
		builder.append(", nombreTuristaParticipante=");
		builder.append(nombreTuristaParticipante);
		builder.append(", apellidoTuristaParticipante=");
		builder.append(apellidoTuristaParticipante);
		builder.append(", fotoPerfilTuristaParticipante=");
		builder.append(fotoPerfilTuristaParticipante);
		builder.append(", tipoChat=");
		builder.append(tipoChat);
		builder.append(", tipoParticipante=");
		builder.append(tipoParticipante);
		builder.append(", beanTablonColonia=");
		builder.append(beanTablonColonia);
		builder.append(", codeTablonColonia=");
		builder.append(codeTablonColonia);
		builder.append(", beanChatMiembro=");
		builder.append(beanChatMiembro);
		builder.append(", codeChatMiembro=");
		builder.append(codeChatMiembro);
		builder.append(", estadoParticipante=");
		builder.append(estadoParticipante);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
