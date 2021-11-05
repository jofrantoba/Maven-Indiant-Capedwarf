package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEntidadFinanciera {
	private PersistenceManager pm;

	public DaoEntidadFinanciera(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(EntidadFinanciera.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<EntidadFinanciera> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EntidadFinanciera> lista = (Collection<EntidadFinanciera>) query
				.getListaBean(EntidadFinanciera.class);
		return lista;
	}
}
