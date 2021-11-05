package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.AutenticaOauth;
import com.indiana.server.model.dao.DaoAutenticaOauth;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicAutenticaOauth {
	private PersistenceManager pm;

	public LogicAutenticaOauth(PersistenceManager pm) {
		this.pm = pm;
	}

	public AutenticaOauth mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoAutenticaOauth dao = new DaoAutenticaOauth(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoAutenticaOauth dao = new DaoAutenticaOauth(this.pm);
		return dao.getBean(id);
	}

	public Collection<AutenticaOauth> getListarBean() throws UnknownException {
		DaoAutenticaOauth dao = new DaoAutenticaOauth(this.pm);
		Collection<AutenticaOauth> lista = dao.getListarBean();
		return lista;
	}
}
