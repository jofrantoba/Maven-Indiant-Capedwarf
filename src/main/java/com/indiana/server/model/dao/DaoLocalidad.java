package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Localidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoLocalidad {
	private PersistenceManager pm;

	public DaoLocalidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Localidad mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Localidad)query.mantenimientoReturn(parametro);
	}

	public Localidad getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Localidad)query.getBean(Localidad.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Localidad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Localidad> listaLocalidad = (Collection<Localidad>) query
				.getListaBean(Localidad.class);
		return listaLocalidad;
	}

}
