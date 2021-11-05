package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.server.model.dao.DaoUsuarioAdmin;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicUsuarioAdmin {
	private PersistenceManager pm;

	public LogicUsuarioAdmin(PersistenceManager pm) {
		this.pm = pm;
	}

	public Object mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioAdmin dao = new DaoUsuarioAdmin(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoUsuarioAdmin dao = new DaoUsuarioAdmin(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<UsuarioAdmin> getListarBean() throws UnknownException {
		DaoUsuarioAdmin dao = new DaoUsuarioAdmin(this.pm);
		Collection<UsuarioAdmin> lista = dao.getListarBean();
		return lista;
	}
}
