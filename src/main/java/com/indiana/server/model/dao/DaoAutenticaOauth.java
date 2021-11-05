package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import com.indiana.server.model.bean.AutenticaOauth;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoAutenticaOauth {
	private PersistenceManager pm;

	public DaoAutenticaOauth(PersistenceManager pm) {
		this.pm = pm;
	}

	public AutenticaOauth mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (AutenticaOauth)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(AutenticaOauth.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<AutenticaOauth> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<AutenticaOauth> lista = (Collection<AutenticaOauth>) query
				.getListaBean(AutenticaOauth.class);
		return lista;
	}
}
