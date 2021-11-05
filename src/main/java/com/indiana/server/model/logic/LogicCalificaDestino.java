package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CalificaDestino;
import com.indiana.server.model.dao.DaoCalificaDestino;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCalificaDestino {
	private PersistenceManager pm;

	public LogicCalificaDestino(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCalificaDestino dao = new DaoCalificaDestino(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCalificaDestino dao = new DaoCalificaDestino(this.pm);
		return dao.getBean(id);
	}

	public Collection<CalificaDestino> getListarBean() throws UnknownException {
		DaoCalificaDestino dao = new DaoCalificaDestino(this.pm);
		Collection<CalificaDestino> lista = dao.getListarBean();
		return lista;
	}
}
