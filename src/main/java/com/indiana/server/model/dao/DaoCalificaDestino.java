package com.indiana.server.model.dao;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.indiana.server.model.bean.CalificaDestino;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCalificaDestino {
	private PersistenceManager pm;

	public DaoCalificaDestino(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CalificaDestino.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CalificaDestino> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CalificaDestino> lista = (Collection<CalificaDestino>) query
				.getListaBean(CalificaDestino.class);
		return lista;
	}
}
