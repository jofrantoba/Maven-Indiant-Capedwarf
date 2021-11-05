package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.OfertaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoOfertaNegocio {
	private PersistenceManager pm;

	public DaoOfertaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(OfertaNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<OfertaNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<OfertaNegocio> lista = (Collection<OfertaNegocio>) query
				.getListaBean(OfertaNegocio.class);
		return lista;
	}
}
