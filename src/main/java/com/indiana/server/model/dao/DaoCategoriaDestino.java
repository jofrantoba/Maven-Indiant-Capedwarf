package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CategoriaDestino;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCategoriaDestino {
	private PersistenceManager pm;

	public DaoCategoriaDestino(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CategoriaDestino.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CategoriaDestino> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CategoriaDestino> listaLocalidad = (Collection<CategoriaDestino>) query
				.getListaBean(CategoriaDestino.class);
		return listaLocalidad;
	}

}
