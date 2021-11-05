package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CuentaTurista;
import com.indiana.server.model.dao.DaoCuentaTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCuentaTurista {
	private PersistenceManager pm;

	public LogicCuentaTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCuentaTurista dao = new DaoCuentaTurista(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCuentaTurista dao = new DaoCuentaTurista(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByEmail(String correoCuentaTurista) throws UnknownException {
		DaoCuentaTurista dao = new DaoCuentaTurista(this.pm);
		return dao.getBeanByEmail(correoCuentaTurista);
	}

	public Collection<CuentaTurista> getListarBean() throws UnknownException {
		DaoCuentaTurista dao = new DaoCuentaTurista(this.pm);
		Collection<CuentaTurista> lista = dao.getListarBean();
		return lista;
	}
}
