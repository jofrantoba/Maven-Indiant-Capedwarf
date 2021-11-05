package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TuristaInteresEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTuristaInteresEmpatia {
	private PersistenceManager pm;

	public DaoTuristaInteresEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TuristaInteresEmpatia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TuristaInteresEmpatia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TuristaInteresEmpatia> lista = (Collection<TuristaInteresEmpatia>) query
				.getListaBean(TuristaInteresEmpatia.class);
		return lista;
	}
}
