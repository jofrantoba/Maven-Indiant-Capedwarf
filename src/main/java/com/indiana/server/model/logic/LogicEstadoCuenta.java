package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.server.model.dao.DaoEstadoCuenta;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEstadoCuenta{
	private PersistenceManager pm;

	public LogicEstadoCuenta(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEstadoCuenta dao = new DaoEstadoCuenta(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public EstadoCuenta getBean(String idEstadoCuenta){
		try{
			DaoEstadoCuenta dao = new DaoEstadoCuenta(this.pm);
			return (EstadoCuenta)dao.getBean(idEstadoCuenta);	
		}catch(Exception ex){
			return null;
		}		
	}

	public Object getBeanByCode(String codeEstadoCuenta){
		try{
			DaoEstadoCuenta dao = new DaoEstadoCuenta(this.pm);
			return dao.getBeanByCode(codeEstadoCuenta);	
		}catch(Exception ex){
			return null;
		}		
	}

	public Collection<EstadoCuenta> getListarBean() throws UnknownException {
		DaoEstadoCuenta dao = new DaoEstadoCuenta(this.pm);
		Collection<EstadoCuenta> lista = dao.getListarBean();
		return lista;
	}
}