package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ChatNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoChatNegocio {
	private PersistenceManager pm;

	public DaoChatNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ChatNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<ChatNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ChatNegocio> lista = (Collection<ChatNegocio>) query
				.getListaBean(ChatNegocio.class);
		return lista;
	}
}
