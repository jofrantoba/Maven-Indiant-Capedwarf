package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoUsuarioAdmin {
	private PersistenceManager pm;

	public DaoUsuarioAdmin(PersistenceManager pm) {
		this.pm = pm;
	}

	public Object mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(UsuarioAdmin.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<UsuarioAdmin> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<UsuarioAdmin> lista = (Collection<UsuarioAdmin>) query
				.getListaBean(UsuarioAdmin.class);
		return lista;
	}

}
