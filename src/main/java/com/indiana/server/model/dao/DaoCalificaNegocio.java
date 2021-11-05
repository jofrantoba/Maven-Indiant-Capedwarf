package com.indiana.server.model.dao;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.indiana.server.model.bean.CalificaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCalificaNegocio {
	private PersistenceManager pm;

	public DaoCalificaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CalificaNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CalificaNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CalificaNegocio> lista = (Collection<CalificaNegocio>) query
				.getListaBean(CalificaNegocio.class);
		return lista;
	}
}
