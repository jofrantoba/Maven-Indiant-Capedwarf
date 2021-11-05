package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CategoriaNegocio;
import com.indiana.server.model.dao.DaoCategoriaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCategoriaNegocio{
	private PersistenceManager pm;

	public LogicCategoriaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCategoriaNegocio dao = new DaoCategoriaNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
			DaoCategoriaNegocio dao = new DaoCategoriaNegocio(this.pm);
			return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}		
	}

	public Collection<CategoriaNegocio> getListarBean() throws UnknownException {
		DaoCategoriaNegocio dao = new DaoCategoriaNegocio(this.pm);
		Collection<CategoriaNegocio> lista = dao.getListarBean();
		return lista;
	}
}