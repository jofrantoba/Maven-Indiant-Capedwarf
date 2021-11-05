package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Novedad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoNovedad {
	private PersistenceManager pm;

	public DaoNovedad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Novedad.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Novedad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Novedad> listNovedads = (Collection<Novedad>) query
				.getListaBean(Novedad.class);
		return listNovedads;
	}
}
