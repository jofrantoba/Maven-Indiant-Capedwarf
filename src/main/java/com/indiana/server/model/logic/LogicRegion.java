package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Region;
import com.indiana.server.model.dao.DaoRegion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicRegion {
	private PersistenceManager pm;

	public LogicRegion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoRegion dao = new DaoRegion(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Region mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoRegion dao = new DaoRegion(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Region getBean(String id){
		try{
			DaoRegion dao = new DaoRegion(this.pm);
			return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
		
	}
	
	public Region getBeanByCode(String codeRegion) throws UnknownException {
		DaoRegion dao = new DaoRegion(this.pm);
		Region beanRegion=dao.getBeanByCode(codeRegion);
		return beanRegion;
	}

	public Collection<Region> getListarBean() throws UnknownException {
		DaoRegion dao = new DaoRegion(this.pm);
		Collection<Region> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Region> getListarBeanByPais(String codePais) throws UnknownException {
		DaoRegion dao = new DaoRegion(this.pm);
		Collection<Region> lista = dao.getListarBeanByPais(codePais);
		return lista;
	}
}
