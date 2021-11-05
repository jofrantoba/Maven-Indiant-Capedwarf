package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.OfertaNegocio;
import com.indiana.server.model.dao.DaoOfertaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicOfertaNegocio {
	private PersistenceManager pm;

	public LogicOfertaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoOfertaNegocio dao = new DaoOfertaNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoOfertaNegocio dao = new DaoOfertaNegocio(this.pm);
		return dao.getBean(id);
	}

	public Collection<OfertaNegocio> getListarBean() throws UnknownException {
		DaoOfertaNegocio dao = new DaoOfertaNegocio(this.pm);
		Collection<OfertaNegocio> lista = dao.getListarBean();
		return lista;
	}
}
