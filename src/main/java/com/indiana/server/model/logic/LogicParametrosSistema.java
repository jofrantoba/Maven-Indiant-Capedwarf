package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.dao.DaoParametrosSistema;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicParametrosSistema {
	private PersistenceManager pm;

	public LogicParametrosSistema(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoParametrosSistema dao = new DaoParametrosSistema(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoParametrosSistema dao = new DaoParametrosSistema(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<ParametrosSistema> getListarBean() throws UnknownException {
		DaoParametrosSistema dao = new DaoParametrosSistema(this.pm);
		Collection<ParametrosSistema> lista = dao.getListarBean();
		return lista;
	}

}
