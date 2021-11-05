package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Moneda;
import com.indiana.server.model.dao.DaoMoneda;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicMoneda {
	private PersistenceManager pm;

	public LogicMoneda(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoMoneda dao = new DaoMoneda(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoMoneda dao = new DaoMoneda(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<Moneda> getListarBean() throws UnknownException {
		DaoMoneda dao = new DaoMoneda(this.pm);
		Collection<Moneda> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Moneda> getListarBeanActivos() throws UnknownException {
		DaoMoneda dao = new DaoMoneda(this.pm);
		Collection<Moneda> lista = dao.getListarBeanActivos();
		return lista;
	}
}