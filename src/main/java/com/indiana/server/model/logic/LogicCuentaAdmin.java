package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CuentaAdmin;
import com.indiana.server.model.dao.DaoCuentaAdmin;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCuentaAdmin{
	private PersistenceManager pm;

	public LogicCuentaAdmin(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCuentaAdmin dao = new DaoCuentaAdmin(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoCuentaAdmin dao = new DaoCuentaAdmin(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<CuentaAdmin> getListarBean() throws UnknownException {
		DaoCuentaAdmin dao = new DaoCuentaAdmin(this.pm);
		Collection<CuentaAdmin> lista = dao.getListarBean();
		return lista;
	}
}