package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CuentaAdmin;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCuentaAdmin {
	private PersistenceManager pm;

	public DaoCuentaAdmin(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CuentaAdmin.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CuentaAdmin> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CuentaAdmin> lista = (Collection<CuentaAdmin>) query
				.getListaBean(CuentaAdmin.class);
		return lista;
	}

}
