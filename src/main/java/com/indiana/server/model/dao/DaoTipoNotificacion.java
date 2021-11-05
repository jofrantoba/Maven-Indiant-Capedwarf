package com.indiana.server.model.dao;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoNotificacion {
	private PersistenceManager pm;

	public DaoTipoNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public TipoNotificacion getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (TipoNotificacion)query.getBean(TipoNotificacion.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoNotificacion> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		List<TipoNotificacion> lista =  (List<TipoNotificacion>)query.getListaBean(TipoNotificacion.class);
		return lista;
	}

}
