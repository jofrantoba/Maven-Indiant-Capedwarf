package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EstadoSolicAmistad;
import com.indiana.server.model.dao.DaoEstadoSolicAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEstadoSolicAmistad{
	private PersistenceManager pm;

	public LogicEstadoSolicAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEstadoSolicAmistad dao = new DaoEstadoSolicAmistad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) {
		try{
		DaoEstadoSolicAmistad dao = new DaoEstadoSolicAmistad(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	public Object getBeanByCode(String codeEstadoSolAmistad) throws UnknownException {
		DaoEstadoSolicAmistad dao = new DaoEstadoSolicAmistad(this.pm);
		return dao.getBeanByCode(codeEstadoSolAmistad);
	}

	public Collection<EstadoSolicAmistad> getListarBean() throws UnknownException {
		DaoEstadoSolicAmistad dao = new DaoEstadoSolicAmistad(this.pm);
		Collection<EstadoSolicAmistad> lista = dao.getListarBean();
		return lista;
	}
}