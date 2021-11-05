package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.DetalleInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoDetalleInteres {
	private PersistenceManager pm;

	public DaoDetalleInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(DetalleInteres.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<DetalleInteres> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<DetalleInteres> lista = (Collection<DetalleInteres>) query
				.getListaBean(DetalleInteres.class);
		return lista;
	}
}
