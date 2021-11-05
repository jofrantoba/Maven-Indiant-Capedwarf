package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Novedad;
import com.indiana.server.model.dao.DaoNovedad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicNovedad {
	private PersistenceManager pm;

	public LogicNovedad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoNovedad dao = new DaoNovedad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoNovedad dao = new DaoNovedad(this.pm);
		return dao.getBean(id);
	}

	public Collection<Novedad> getListarBean() throws UnknownException {
		DaoNovedad dao = new DaoNovedad(this.pm);
		Collection<Novedad> lista = dao.getListarBean();
		return lista;
	}
}
