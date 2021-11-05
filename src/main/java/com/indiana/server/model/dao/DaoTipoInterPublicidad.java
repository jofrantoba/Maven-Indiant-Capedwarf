package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoInterPublicidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoInterPublicidad {
	private PersistenceManager pm;

	public DaoTipoInterPublicidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoInterPublicidad.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoInterPublicidad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoInterPublicidad> lista = (Collection<TipoInterPublicidad>) query
				.getListaBean(TipoInterPublicidad.class);
		return lista;
	}

}
