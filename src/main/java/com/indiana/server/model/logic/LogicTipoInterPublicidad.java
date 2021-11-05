package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoInterPublicidad;
import com.indiana.server.model.dao.DaoTipoInterPublicidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoInterPublicidad {
	private PersistenceManager pm;

	public LogicTipoInterPublicidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoInterPublicidad dao = new DaoTipoInterPublicidad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoTipoInterPublicidad dao = new DaoTipoInterPublicidad(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<TipoInterPublicidad> getListarBean() throws UnknownException {
		DaoTipoInterPublicidad dao = new DaoTipoInterPublicidad(this.pm);
		Collection<TipoInterPublicidad> lista = dao.getListarBean();
		return lista;
	}
}
