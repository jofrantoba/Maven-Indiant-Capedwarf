package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.DetalleEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoDetalleEmpatia {
	private PersistenceManager pm;

	public DaoDetalleEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(DetalleEmpatia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<DetalleEmpatia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<DetalleEmpatia> lista = (Collection<DetalleEmpatia>) query
				.getListaBean(DetalleEmpatia.class);
		return lista;
	}
}
