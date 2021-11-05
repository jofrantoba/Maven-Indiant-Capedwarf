package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.PublicidadNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoPublicidadNegocio {
	private PersistenceManager pm;

	public DaoPublicidadNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(PublicidadNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<PublicidadNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<PublicidadNegocio> lista = (Collection<PublicidadNegocio>) query
				.getListaBean(PublicidadNegocio.class);
		return lista;
	}
}
