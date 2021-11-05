package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Grupo;
import com.indiana.server.model.dao.DaoGrupo;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicGrupo {
	private PersistenceManager pm;

	public LogicGrupo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoGrupo dao = new DaoGrupo(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoGrupo dao = new DaoGrupo(this.pm);
		return dao.getBean(id);
	}

	public Collection<Grupo> getListarBean() throws UnknownException {
		DaoGrupo dao = new DaoGrupo(this.pm);
		Collection<Grupo> lista = dao.getListarBean();
		return lista;
	}
}
