package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SugerenciaColonia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoSugerenciaColonia {
	private PersistenceManager pm;

	public DaoSugerenciaColonia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SugerenciaColonia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SugerenciaColonia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SugerenciaColonia> lista = (Collection<SugerenciaColonia>) query
				.getListaBean(SugerenciaColonia.class);
		return lista;
	}
}
