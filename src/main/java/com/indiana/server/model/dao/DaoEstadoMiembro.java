package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EstadoMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEstadoMiembro {
	private PersistenceManager pm;

	public DaoEstadoMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public EstadoMiembro getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (EstadoMiembro)query.getBean(EstadoMiembro.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<EstadoMiembro> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EstadoMiembro> lista = (Collection<EstadoMiembro>) query
				.getListaBean(EstadoMiembro.class);
		return lista;
	}

}
