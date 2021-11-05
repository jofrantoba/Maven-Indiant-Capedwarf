package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.MensajeColoniaPrivado;
import com.indiana.server.model.dao.DaoMensajeColoniaPrivado;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicMensajeColoniaPrivado {
	private PersistenceManager pm;

	public LogicMensajeColoniaPrivado(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoMensajeColoniaPrivado dao = new DaoMensajeColoniaPrivado(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoMensajeColoniaPrivado dao = new DaoMensajeColoniaPrivado(this.pm);
		return dao.getBean(id);
	}

	public Collection<MensajeColoniaPrivado> getListarBean() throws UnknownException {
		DaoMensajeColoniaPrivado dao = new DaoMensajeColoniaPrivado(this.pm);
		Collection<MensajeColoniaPrivado> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<MensajeColoniaPrivado> getListarBeanByTablon(String codeTablonColonia) throws UnknownException {
		DaoMensajeColoniaPrivado dao = new DaoMensajeColoniaPrivado(this.pm);
		Collection<MensajeColoniaPrivado> lista = dao.getListarBeanByTablon(codeTablonColonia);
		return lista;
	}
}
