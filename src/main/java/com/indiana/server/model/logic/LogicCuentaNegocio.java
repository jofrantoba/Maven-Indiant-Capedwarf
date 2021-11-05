package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CuentaNegocio;
import com.indiana.server.model.dao.DaoCuentaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCuentaNegocio {
	private PersistenceManager pm;

	public LogicCuentaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCuentaNegocio dao = new DaoCuentaNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCuentaNegocio dao = new DaoCuentaNegocio(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByEmail(String correoCuentaNegocio) throws UnknownException {
		DaoCuentaNegocio dao = new DaoCuentaNegocio(this.pm);
		return dao.getBeanByEmail(correoCuentaNegocio);
	}

	public Collection<CuentaNegocio> getListarBean() throws UnknownException {
		DaoCuentaNegocio dao = new DaoCuentaNegocio(this.pm);
		Collection<CuentaNegocio> lista = dao.getListarBean();
		return lista;
	}
}
