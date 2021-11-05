package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ChatMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoChatMiembro {
	private PersistenceManager pm;

	public DaoChatMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ChatMiembro.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<ChatMiembro> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ChatMiembro> lista = (Collection<ChatMiembro>) query
				.getListaBean(ChatMiembro.class);
		return lista;
	}
}
