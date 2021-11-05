package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SesionNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoSesionNegocio {
	private PersistenceManager pm;

	public DaoSesionNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public SesionNegocio mantenimientoNegocio(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (SesionNegocio)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SesionNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SesionNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SesionNegocio> listaSesionNegocio = (Collection<SesionNegocio>) query
				.getListaBean(SesionNegocio.class);
		return listaSesionNegocio;
	}

}
