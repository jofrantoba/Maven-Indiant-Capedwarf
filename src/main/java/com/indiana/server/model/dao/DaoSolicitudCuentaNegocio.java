package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SolicitudCuentaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoSolicitudCuentaNegocio {
	private PersistenceManager pm;

	public DaoSolicitudCuentaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SolicitudCuentaNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SolicitudCuentaNegocio> getListarBean()
			throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SolicitudCuentaNegocio> lista = (Collection<SolicitudCuentaNegocio>) query
				.getListaBean(SolicitudCuentaNegocio.class);
		return lista;
	}
}
