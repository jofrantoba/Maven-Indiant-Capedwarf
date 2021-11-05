package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SugerenciaColonia;
import com.indiana.server.model.dao.DaoSugerenciaColonia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicSugerenciaColonia {
	private PersistenceManager pm;

	public LogicSugerenciaColonia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSugerenciaColonia dao = new DaoSugerenciaColonia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSugerenciaColonia dao = new DaoSugerenciaColonia(this.pm);
		return dao.getBean(id);
	}

	public Collection<SugerenciaColonia> getListarBean() throws UnknownException {
		DaoSugerenciaColonia dao = new DaoSugerenciaColonia(this.pm);
		Collection<SugerenciaColonia> lista = dao.getListarBean();
		return lista;
	}
}
