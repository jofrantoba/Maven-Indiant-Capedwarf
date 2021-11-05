package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TuristaInteresEmpatia;
import com.indiana.server.model.dao.DaoTuristaInteresEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTuristaInteresEmpatia {
	private PersistenceManager pm;

	public LogicTuristaInteresEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTuristaInteresEmpatia dao = new DaoTuristaInteresEmpatia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTuristaInteresEmpatia dao = new DaoTuristaInteresEmpatia(this.pm);
		return dao.getBean(id);
	}

	public Collection<TuristaInteresEmpatia> getListarBean() throws UnknownException {
		DaoTuristaInteresEmpatia dao = new DaoTuristaInteresEmpatia(this.pm);
		Collection<TuristaInteresEmpatia> lista = dao.getListarBean();
		return lista;
	}
}
