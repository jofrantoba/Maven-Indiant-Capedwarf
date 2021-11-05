package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.DetalleEmpatia;
import com.indiana.server.model.dao.DaoDetalleEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicDetalleEmpatia {
	private PersistenceManager pm;

	public LogicDetalleEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoDetalleEmpatia dao = new DaoDetalleEmpatia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoDetalleEmpatia dao = new DaoDetalleEmpatia(this.pm);
		return dao.getBean(id);
	}

	public Collection<DetalleEmpatia> getListarBean() throws UnknownException {
		DaoDetalleEmpatia dao = new DaoDetalleEmpatia(this.pm);
		Collection<DetalleEmpatia> lista = dao.getListarBean();
		return lista;
	}
}
