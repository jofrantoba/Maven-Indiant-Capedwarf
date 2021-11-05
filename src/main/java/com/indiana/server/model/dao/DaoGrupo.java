package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Grupo;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoGrupo {
	private PersistenceManager pm;

	public DaoGrupo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Grupo.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Grupo> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Grupo> lista = (Collection<Grupo>) query
				.getListaBean(Grupo.class);
		return lista;
	}

}
