package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EnlaceRedSocial;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEnlaceRedSocial {
	private PersistenceManager pm;

	public DaoEnlaceRedSocial(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(EnlaceRedSocial.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<EnlaceRedSocial> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EnlaceRedSocial> lista = (Collection<EnlaceRedSocial>) query
				.getListaBean(EnlaceRedSocial.class);
		return lista;
	}
}
