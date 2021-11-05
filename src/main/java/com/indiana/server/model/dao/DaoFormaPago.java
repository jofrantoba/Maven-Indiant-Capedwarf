package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.FormaPago;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoFormaPago {
	private PersistenceManager pm;

	public DaoFormaPago(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(FormaPago.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<FormaPago> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<FormaPago> lista = (Collection<FormaPago>) query
				.getListaBean(FormaPago.class);
		return lista;
	}

}
