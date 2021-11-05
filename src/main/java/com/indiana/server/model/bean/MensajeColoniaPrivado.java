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
public class MensajeColoniaPrivado implements Serializable {

	
	private static final long serialVersionUID = 1L;
		
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMensajeColoniaPrivado;
	@Persistent
	private String codeMensajeColoniaPrivado;	
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
	private TablonColonia beanTablonColonia;
	@Persistent
	private String codeTablonColonia;
	@Persistent
	@Unowned
	private ParticipanteChatMiembro beanParticipanteChatMiembro;
	@Persistent
	private String codeParticipanteChatMiembro;
	@Persistent
	private Boolean isPersistente;
	@Persistent
	private String linkFotoMensaje;
	
	
	public String getIdMensajeColoniaPrivado() {
		return idMensajeColoniaPrivado;
	}
	public void setIdMensajeColoniaPrivado(String idMensajeColoniaPrivado) {
		this.idMensajeColoniaPrivado=idMensajeColoniaPrivado;				
	}
	public String getCodeMensajeColoniaPrivado() {
		return codeMensajeColoniaPrivado;
	}
	public void setCodeMensajeColoniaPrivado(String codeMensajeColoniaPrivado) {
		this.codeMensajeColoniaPrivado = codeMensajeColoniaPrivado;
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
	
	public String getLinkFotoMensaje() {
		return linkFotoMensaje;
	}
	public void setLinkFotoMensaje(String linkFotoMensaje) {
		this.linkFotoMensaje = linkFotoMensaje;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeMensajeColoniaPrivado == null) ? 0
						: codeMensajeColoniaPrivado.hashCode());
		result = prime
				* result
				+ ((idMensajeColoniaPrivado == null) ? 0
						: idMensajeColoniaPrivado.hashCode());
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
		MensajeColoniaPrivado other = (MensajeColoniaPrivado) obj;
		if (codeMensajeColoniaPrivado == null) {
			if (other.codeMensajeColoniaPrivado != null)
				return false;
		} else if (!codeMensajeColoniaPrivado
				.equals(other.codeMensajeColoniaPrivado))
			return false;
		if (idMensajeColoniaPrivado == null) {
			if (other.idMensajeColoniaPrivado != null)
				return false;
		} else if (!idMensajeColoniaPrivado
				.equals(other.idMensajeColoniaPrivado))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajeColoniaPrivado [idMensajeColoniaPrivado=");
		builder.append(idMensajeColoniaPrivado);
		builder.append(", codeMensajeColoniaPrivado=");
		builder.append(codeMensajeColoniaPrivado);
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
		builder.append(", beanTablonColonia=");
		builder.append(beanTablonColonia);
		builder.append(", codeTablonColonia=");
		builder.append(codeTablonColonia);
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
