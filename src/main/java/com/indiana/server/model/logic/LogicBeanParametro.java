package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.dao.DaoBeanParametro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicBeanParametro {
	private PersistenceManager pm;

	public LogicBeanParametro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(Collection<BeanParametro> listBeanParametro)
			throws UnknownException {
		DaoBeanParametro dao = new DaoBeanParametro(this.pm);
		return dao.mantenimiento(listBeanParametro);
	}

	public Object getBean(String id){
		try{
			DaoBeanParametro dao = new DaoBeanParametro(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
}
