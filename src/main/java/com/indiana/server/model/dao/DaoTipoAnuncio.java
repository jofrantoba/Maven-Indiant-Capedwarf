package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoAnuncio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoAnuncio {
	private PersistenceManager pm;

	public DaoTipoAnuncio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoAnuncio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoAnuncio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoAnuncio> lista = (Collection<TipoAnuncio>) query
				.getListaBean(TipoAnuncio.class);
		return lista;
	}

}
