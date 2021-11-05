package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.PublicidadNegocio;
import com.indiana.server.model.dao.DaoPublicidadNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicPublicidadNegocio {
	private PersistenceManager pm;

	public LogicPublicidadNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPublicidadNegocio dao = new DaoPublicidadNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoPublicidadNegocio dao = new DaoPublicidadNegocio(this.pm);
		return dao.getBean(id);
	}

	public Collection<PublicidadNegocio> getListarBean() throws UnknownException {
		DaoPublicidadNegocio dao = new DaoPublicidadNegocio(this.pm);
		Collection<PublicidadNegocio> lista = dao.getListarBean();
		return lista;
	}
}
