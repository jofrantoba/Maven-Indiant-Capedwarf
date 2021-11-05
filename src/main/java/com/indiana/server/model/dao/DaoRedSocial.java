package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.RedSocial;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoRedSocial {
	private PersistenceManager pm;

	public DaoRedSocial(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(RedSocial.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<RedSocial> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<RedSocial> lista = (Collection<RedSocial>) query
				.getListaBean(RedSocial.class);
		return lista;
	}

}
