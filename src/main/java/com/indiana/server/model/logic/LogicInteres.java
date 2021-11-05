package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.dao.DaoInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicInteres {
	private PersistenceManager pm;

	public LogicInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoInteres dao = new DaoInteres(this.pm);
		return dao.mantenimiento(parametro);
	}

	public boolean mantenimiento(List<BeanParametro> listBeanParametro)
			throws UnknownException {
		DaoInteres dao = new DaoInteres(this.pm);
		return dao.mantenimiento(listBeanParametro);
	}
	
	public Object getBean(String id){
		try{
		DaoInteres dao = new DaoInteres(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Object getBean(Interes beanInteres) throws UnknownException {
		DaoInteres dao = new DaoInteres(this.pm);
		return dao.getBean(beanInteres);
	}

	
	public Collection<Interes> getListarBean() throws UnknownException {
		DaoInteres dao = new DaoInteres(this.pm);
		Collection<Interes> lista = dao.getListarBean();
		return lista;
	}
	
	
}
