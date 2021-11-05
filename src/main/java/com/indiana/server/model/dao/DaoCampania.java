package com.indiana.server.model.dao;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.indiana.server.model.bean.Campania;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCampania {
	private PersistenceManager pm;

	public DaoCampania(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Campania.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Campania> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Campania> listaCampania = (Collection<Campania>) query
				.getListaBean(Campania.class);
		return listaCampania;
	}
}
