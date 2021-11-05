package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Privacidad;
import com.indiana.server.model.dao.DaoPrivacidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicPrivacidad{
	private PersistenceManager pm;

	public LogicPrivacidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPrivacidad dao = new DaoPrivacidad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoPrivacidad dao = new DaoPrivacidad(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Privacidad getBeanByCode(String codePrivacidad) throws UnknownException {
		DaoPrivacidad dao = new DaoPrivacidad(this.pm);
		return dao.getBeanByCode(codePrivacidad);
	}

	public Collection<Privacidad> getListarBean() throws UnknownException {
		DaoPrivacidad dao = new DaoPrivacidad(this.pm);
		Collection<Privacidad> lista = dao.getListarBean();
		return lista;
	}
	
}