package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EnlaceRedSocial;
import com.indiana.server.model.dao.DaoEnlaceRedSocial;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEnlaceRedSocial {
	private PersistenceManager pm;

	public LogicEnlaceRedSocial(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEnlaceRedSocial dao = new DaoEnlaceRedSocial(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoEnlaceRedSocial dao = new DaoEnlaceRedSocial(this.pm);
		return dao.getBean(id);
	}

	public Collection<EnlaceRedSocial> getListarBean() throws UnknownException {
		DaoEnlaceRedSocial dao = new DaoEnlaceRedSocial(this.pm);
		Collection<EnlaceRedSocial> lista = dao.getListarBean();
		return lista;
	}
}
