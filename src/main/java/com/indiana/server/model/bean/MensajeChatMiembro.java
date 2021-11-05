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
public class MensajeChatMiembro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMensajeChatMiembro;
	@Persistent
	private String codeMensajeChatMiembro;	
	@Persistent
	private String mensaje;
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
	private Long version;	
	@NotPersistent
	private String operacion;
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
	@Unowned
	private Miembro beanMiembroParticipante;
	@Persistent
	private String codeMiembroParticipante;
	@Persistent
	@Unowned
	private ChatMiembro beanChatMiembro;
	@Persistent
	private String codeChatMiembro;
	@Persistent
	@Unowned
	private ParticipanteChatMiembro beanParticipanteChatMiembro;
	@Persistent
	private String codeParticipanteChatMiembro;
	@Persistent
	private Boolean isPersistente;
	
	public String getIdMensajeChatMiembro() {
		return idMensajeChatMiembro;
	}
	public void setIdMensajeChatMiembro(String idMensajeChatMiembro) {
		this.idMensajeChatMiembro=idMensajeChatMiembro;				
	}
	public String getCodeMensajeChatMiembro() {
		return codeMensajeChatMiembro;
	}
	public void setCodeMensajeChatMiembro(String codeMensajeChatMiembro) {
		this.codeMensajeChatMiembro = codeMensajeChatMiembro;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	public Miembro getBeanMiembroParticipante() {
		return beanMiembroParticipante;
	}
	public void setBeanMiembroParticipante(Miembro beanMiembroParticipante) {
		this.beanMiembroParticipante = beanMiembroParticipante;
	}
	public String getCodeMiembroParticipante() {
		return codeMiembroParticipante;
	}
	public void setCodeMiembroParticipante(String codeMiembroParticipante) {
		this.codeMiembroParticipante = codeMiembroParticipante;
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
	public ParticipanteChatMiembro getBeanParticipanteChatMiembro() {
		return beanParticipanteChatMiembro;
	}
	public void setBeanParticipanteChatMiembro(
			ParticipanteChatMiembro beanParticipanteChatMiembro) {
		this.beanParticipanteChatMiembro = beanParticipanteChatMiembro;
	}
	public String getCodeParticipanteChatMiembro() {
		return codeParticipanteChatMiembro;
	}
	public void setCodeParticipanteChatMiembro(String codeParticipanteChatMiembro) {
		this.codeParticipanteChatMiembro = codeParticipanteChatMiembro;
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
				+ ((codeMensajeChatMiembro == null) ? 0
						: codeMensajeChatMiembro.hashCode());
		result = prime
				* result
				+ ((idMensajeChatMiembro == null) ? 0 : idMensajeChatMiembro
						.hashCode());
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
		MensajeChatMiembro other = (MensajeChatMiembro) obj;
		if (codeMensajeChatMiembro == null) {
			if (other.codeMensajeChatMiembro != null)
				return false;
		} else if (!codeMensajeChatMiembro.equals(other.codeMensajeChatMiembro))
			return false;
		if (idMensajeChatMiembro == null) {
			if (other.idMensajeChatMiembro != null)
				return false;
		} else if (!idMensajeChatMiembro.equals(other.idMensajeChatMiembro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajeChatMiembro [idMensajeChatMiembro=");
		builder.append(idMensajeChatMiembro);
		builder.append(", codeMensajeChatMiembro=");
		builder.append(codeMensajeChatMiembro);
		builder.append(", mensaje=");
		builder.append(mensaje);
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
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
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
		builder.append(", beanMiembroParticipante=");
		builder.append(beanMiembroParticipante);
		builder.append(", codeMiembroParticipante=");
		builder.append(codeMiembroParticipante);
		builder.append(", beanChatMiembro=");
		builder.append(beanChatMiembro);
		builder.append(", codeChatMiembro=");
		builder.append(codeChatMiembro);
		builder.append(", beanParticipanteChatMiembro=");
		builder.append(beanParticipanteChatMiembro);
		builder.append(", codeParticipanteChatMiembro=");
		builder.append(codeParticipanteChatMiembro);
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
	
}
