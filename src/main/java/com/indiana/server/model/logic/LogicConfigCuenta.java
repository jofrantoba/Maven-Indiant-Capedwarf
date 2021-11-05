package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ConfigCuenta;
import com.indiana.server.model.dao.DaoConfigCuenta;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicConfigCuenta {
	private PersistenceManager pm;

	public LogicConfigCuenta(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoConfigCuenta dao = new DaoConfigCuenta(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public ConfigCuenta mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoConfigCuenta dao = new DaoConfigCuenta(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoConfigCuenta dao = new DaoConfigCuenta(this.pm);
		return dao.getBean(id);
	}
	
	public ConfigCuenta getBeanByCodeTurista(String codeTurista) throws UnknownException {
		DaoConfigCuenta dao = new DaoConfigCuenta(this.pm);
		return dao.getBeanByCodeTurista(codeTurista);
	}

	public Collection<ConfigCuenta> getListarBean() throws UnknownException {
		DaoConfigCuenta dao = new DaoConfigCuenta(this.pm);
		Collection<ConfigCuenta> lista = dao.getListarBean();
		return lista;
	}
}
