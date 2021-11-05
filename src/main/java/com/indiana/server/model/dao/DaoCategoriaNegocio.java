package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CategoriaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCategoriaNegocio {
	private PersistenceManager pm;

	public DaoCategoriaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CategoriaNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CategoriaNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CategoriaNegocio> lista = (Collection<CategoriaNegocio>) query
				.getListaBean(CategoriaNegocio.class);
		return lista;
	}

}
