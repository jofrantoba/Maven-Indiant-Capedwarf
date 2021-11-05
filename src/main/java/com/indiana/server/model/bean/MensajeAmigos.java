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
public class MensajeAmigos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idMensajeAmigos;
	@Persistent
	private String codeMensajeAmigos;		
	@Persistent
	private String codMensajeUnico;			
	@Persistent
	private String codUnicoAmistad;
	@Persistent
	private Date fecha;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent	
	@Unowned
	private Amistad beanAmistad;
	@Persistent
	private String codeAmistad;
	@Persistent	
	@Unowned
	private UsuarioTurista beanTuristaApp;
	@Persistent
	private String codeTuristaApp;	
	@Persistent
	private String mensajeTuristaApp;
	@Persistent	
	@Unowned
	private UsuarioTurista beanTuristaEnvia;
	@Persistent
	private String codeTuristaEnvia;	
	@Persistent
	private String mensajeTuristaEnvia;
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
	private Boolean isPersistente;
	
	
	public String getIdMensajeAmigos() {
		return idMensajeAmigos;
	}
	public String getCodeMensajeAmigos() {
		return codeMensajeAmigos;
	}
	public String getCodMensajeUnico() {
		return codMensajeUnico;
	}
	public String getCodUnicoAmistad() {
		return codUnicoAmistad;
	}
	public Date getFecha() {
		return fecha;
	}
	public Long getVersion() {
		return version;
	}
	public String getOperacion() {
		return operacion;
	}
	public Amistad getBeanAmistad() {
		return beanAmistad;
	}
	public String getCodeAmistad() {
		return codeAmistad;
	}
	public UsuarioTurista getBeanTuristaApp() {
		return beanTuristaApp;
	}
	public String getCodeTuristaApp() {
		return codeTuristaApp;
	}
	public String getMensajeTuristaApp() {
		return mensajeTuristaApp;
	}
	public UsuarioTurista getBeanTuristaEnvia() {
		return beanTuristaEnvia;
	}
	public String getCodeTuristaEnvia() {
		return codeTuristaEnvia;
	}
	public String getMensajeTuristaEnvia() {
		return mensajeTuristaEnvia;
	}
	public Integer getAnno() {
		return anno;
	}
	public Integer getMes() {
		return mes;
	}
	public Integer getDia() {
		return dia;
	}
	public Integer getHora() {
		return hora;
	}
	public Integer getMinuto() {
		return minuto;
	}
	public Integer getSegundo() {
		return segundo;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public String getEstadoVisto() {
		return estadoVisto;
	}
	public Boolean getIsPersistente() {
		return isPersistente;
	}
	public void setIdMensajeAmigos(String idMensajeAmigos) {
		this.idMensajeAmigos = idMensajeAmigos;
	}
	public void setCodeMensajeAmigos(String codeMensajeAmigos) {
		this.codeMensajeAmigos = codeMensajeAmigos;
	}
	public void setCodMensajeUnico(String codMensajeUnico) {
		this.codMensajeUnico = codMensajeUnico;
	}
	public void setCodUnicoAmistad(String codUnicoAmistad) {
		this.codUnicoAmistad = codUnicoAmistad;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public void setBeanAmistad(Amistad beanAmistad) {
		this.beanAmistad = beanAmistad;
	}
	public void setCodeAmistad(String codeAmistad) {
		this.codeAmistad = codeAmistad;
	}
	public void setBeanTuristaApp(UsuarioTurista beanTuristaApp) {
		this.beanTuristaApp = beanTuristaApp;
	}
	public void setCodeTuristaApp(String codeTuristaApp) {
		this.codeTuristaApp = codeTuristaApp;
	}
	public void setMensajeTuristaApp(String mensajeTuristaApp) {
		this.mensajeTuristaApp = mensajeTuristaApp;
	}
	public void setBeanTuristaEnvia(UsuarioTurista beanTuristaEnvia) {
		this.beanTuristaEnvia = beanTuristaEnvia;
	}
	public void setCodeTuristaEnvia(String codeTuristaEnvia) {
		this.codeTuristaEnvia = codeTuristaEnvia;
	}
	public void setMensajeTuristaEnvia(String mensajeTuristaEnvia) {
		this.mensajeTuristaEnvia = mensajeTuristaEnvia;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
	public void setSegundo(Integer segundo) {
		this.segundo = segundo;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public void setEstadoVisto(String estadoVisto) {
		this.estadoVisto = estadoVisto;
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
				+ ((codeMensajeAmigos == null) ? 0 : codeMensajeAmigos
						.hashCode());
		result = prime * result
				+ ((idMensajeAmigos == null) ? 0 : idMensajeAmigos.hashCode());
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
		MensajeAmigos other = (MensajeAmigos) obj;
		if (codeMensajeAmigos == null) {
			if (other.codeMensajeAmigos != null)
				return false;
		} else if (!codeMensajeAmigos.equals(other.codeMensajeAmigos))
			return false;
		if (idMensajeAmigos == null) {
			if (other.idMensajeAmigos != null)
				return false;
		} else if (!idMensajeAmigos.equals(other.idMensajeAmigos))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajeAmigos [idMensajeAmigos=");
		builder.append(idMensajeAmigos);
		builder.append(", codeMensajeAmigos=");
		builder.append(codeMensajeAmigos);
		builder.append(", codMensajeUnico=");
		builder.append(codMensajeUnico);
		builder.append(", codUnicoAmistad=");
		builder.append(codUnicoAmistad);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operacion=");
		builder.append(operacion);
		builder.append(", beanAmistad=");
		builder.append(beanAmistad);
		builder.append(", codeAmistad=");
		builder.append(codeAmistad);
		builder.append(", beanTuristaApp=");
		builder.append(beanTuristaApp);
		builder.append(", codeTuristaApp=");
		builder.append(codeTuristaApp);
		builder.append(", mensajeTuristaApp=");
		builder.append(mensajeTuristaApp);
		builder.append(", beanTuristaEnvia=");
		builder.append(beanTuristaEnvia);
		builder.append(", codeTuristaEnvia=");
		builder.append(codeTuristaEnvia);
		builder.append(", mensajeTuristaEnvia=");
		builder.append(mensajeTuristaEnvia);
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
		builder.append(", isPersistente=");
		builder.append(isPersistente);
		builder.append("]");
		return builder.toString();
	}
	
}
