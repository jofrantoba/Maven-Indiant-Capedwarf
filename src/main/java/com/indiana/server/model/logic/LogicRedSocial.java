package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.RedSocial;
import com.indiana.server.model.dao.DaoRedSocial;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicRedSocial{
	private PersistenceManager pm;

	public LogicRedSocial(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoRedSocial dao = new DaoRedSocial(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoRedSocial dao = new DaoRedSocial(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<RedSocial> getListarBean() throws UnknownException {
		DaoRedSocial dao = new DaoRedSocial(this.pm);
		Collection<RedSocial> lista = dao.getListarBean();
		return lista;
	}
	
}