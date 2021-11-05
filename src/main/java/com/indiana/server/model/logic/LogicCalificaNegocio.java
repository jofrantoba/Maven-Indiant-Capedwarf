package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CalificaNegocio;
import com.indiana.server.model.dao.DaoCalificaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCalificaNegocio {
	private PersistenceManager pm;

	public LogicCalificaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCalificaNegocio dao = new DaoCalificaNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCalificaNegocio dao = new DaoCalificaNegocio(this.pm);
		return dao.getBean(id);
	}

	public Collection<CalificaNegocio> getListarBean() throws UnknownException {
		DaoCalificaNegocio dao = new DaoCalificaNegocio(this.pm);
		Collection<CalificaNegocio> lista = dao.getListarBean();
		return lista;
	}
}
