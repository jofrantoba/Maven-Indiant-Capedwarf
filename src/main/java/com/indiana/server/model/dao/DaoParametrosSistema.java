package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoParametrosSistema {
	private PersistenceManager pm;

	public DaoParametrosSistema(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ParametrosSistema.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<ParametrosSistema> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ParametrosSistema> lista = (Collection<ParametrosSistema>) query
				.getListaBean(ParametrosSistema.class);
		return lista;
	}
}
