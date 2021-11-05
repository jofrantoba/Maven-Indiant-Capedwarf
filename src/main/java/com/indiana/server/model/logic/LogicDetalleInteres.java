package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.DetalleInteres;
import com.indiana.server.model.dao.DaoDetalleInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicDetalleInteres {
	private PersistenceManager pm;

	public LogicDetalleInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoDetalleInteres dao = new DaoDetalleInteres(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		DaoDetalleInteres dao = new DaoDetalleInteres(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoDetalleInteres dao = new DaoDetalleInteres(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<DetalleInteres> getListarBean() throws UnknownException {
		DaoDetalleInteres dao = new DaoDetalleInteres(this.pm);
		Collection<DetalleInteres> lista = dao.getListarBean();
		return lista;
	}
}
