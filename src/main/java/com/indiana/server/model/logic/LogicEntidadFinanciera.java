package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.server.model.dao.DaoEntidadFinanciera;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEntidadFinanciera {
	private PersistenceManager pm;

	public LogicEntidadFinanciera(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEntidadFinanciera dao = new DaoEntidadFinanciera(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoEntidadFinanciera dao = new DaoEntidadFinanciera(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<EntidadFinanciera> getListarBean() throws UnknownException {
		DaoEntidadFinanciera dao = new DaoEntidadFinanciera(this.pm);
		Collection<EntidadFinanciera> lista = dao.getListarBean();
		return lista;
	}
}
