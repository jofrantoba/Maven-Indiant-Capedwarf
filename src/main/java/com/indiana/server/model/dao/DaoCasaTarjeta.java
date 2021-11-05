package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCasaTarjeta {
	private PersistenceManager pm;

	public DaoCasaTarjeta(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CasaTarjeta.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CasaTarjeta> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CasaTarjeta> lista = (Collection<CasaTarjeta>) query
				.getListaBean(CasaTarjeta.class);
		return lista;
	}

}
