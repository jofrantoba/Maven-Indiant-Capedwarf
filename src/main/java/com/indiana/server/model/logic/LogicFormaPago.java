package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.FormaPago;
import com.indiana.server.model.dao.DaoFormaPago;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicFormaPago {
	private PersistenceManager pm;

	public LogicFormaPago(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoFormaPago dao = new DaoFormaPago(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoFormaPago dao = new DaoFormaPago(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<FormaPago> getListarBean() throws UnknownException {
		DaoFormaPago dao = new DaoFormaPago(this.pm);
		Collection<FormaPago> lista = dao.getListarBean();
		return lista;
	}
}
