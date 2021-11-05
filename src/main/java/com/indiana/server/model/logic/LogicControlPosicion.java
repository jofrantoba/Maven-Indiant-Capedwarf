package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ControlPosicion;
import com.indiana.server.model.dao.DaoControlPosicion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicControlPosicion {
	private PersistenceManager pm;

	public LogicControlPosicion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoControlPosicion dao = new DaoControlPosicion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoControlPosicion dao = new DaoControlPosicion(this.pm);
		return dao.getBean(id);
	}

	public Object getBeanByTurista(String correoTurista) throws UnknownException {
		DaoControlPosicion dao = new DaoControlPosicion(this.pm);
		return dao.getBeanByTurista(correoTurista);
	}

	public Collection<ControlPosicion> getListarBean() throws UnknownException {
		DaoControlPosicion dao = new DaoControlPosicion(this.pm);
		Collection<ControlPosicion> lista = dao.getListarBean();
		return lista;
	}
}
