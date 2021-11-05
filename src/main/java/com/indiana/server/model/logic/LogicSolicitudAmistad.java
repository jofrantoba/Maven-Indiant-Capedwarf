package com.indiana.server.model.logic;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SolicitudAmistad;
import com.indiana.server.model.dao.DaoSolicitudAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicSolicitudAmistad{
	private PersistenceManager pm;

	public LogicSolicitudAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.mantenimiento(parametro);
	}
	public boolean mantenimiento(List<BeanParametro> parametro)
			throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeSolicitudAmistad) throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.getBeanByCode(codeSolicitudAmistad);
	}
	
	public Object getBeanByTurista(String codeTuristaEnvia,String codeTuristaRecibe) throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.getBeanByTurista(codeTuristaEnvia, codeTuristaRecibe);
	}
	public Object getBeanByTurista(String codeTuristaEnvia,String codeTuristaRecibe,String codeEstadoSolAmistad) throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.getBeanByTurista(codeTuristaEnvia, codeTuristaRecibe,codeEstadoSolAmistad);
	}
	public List<SolicitudAmistad> getListarBeanByTurista(String codeTuristaEnvia,String codeTuristaRecibe) throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		return dao.getListarBeanByTurista(codeTuristaEnvia, codeTuristaRecibe);
	}
	public Collection<SolicitudAmistad> getListarBean() throws UnknownException {
		DaoSolicitudAmistad dao = new DaoSolicitudAmistad(this.pm);
		Collection<SolicitudAmistad> lista = dao.getListarBean();
		return lista;
	}
}