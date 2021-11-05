package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ChatNegocio;
import com.indiana.server.model.dao.DaoChatNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicChatNegocio {
	private PersistenceManager pm;

	public LogicChatNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoChatNegocio dao = new DaoChatNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoChatNegocio dao = new DaoChatNegocio(this.pm);
		return dao.getBean(id);
	}

	public Collection<ChatNegocio> getListarBean() throws UnknownException {
		DaoChatNegocio dao = new DaoChatNegocio(this.pm);
		Collection<ChatNegocio> lista = dao.getListarBean();
		return lista;
	}
}
