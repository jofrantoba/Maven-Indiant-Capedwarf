package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ChatMiembro;
import com.indiana.server.model.dao.DaoChatMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicChatMiembro {
	private PersistenceManager pm;

	public LogicChatMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoChatMiembro dao = new DaoChatMiembro(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoChatMiembro dao = new DaoChatMiembro(this.pm);
		return dao.getBean(id);
	}

	public Collection<ChatMiembro> getListarBean() throws UnknownException {
		DaoChatMiembro dao = new DaoChatMiembro(this.pm);
		Collection<ChatMiembro> lista = dao.getListarBean();
		return lista;
	}
}