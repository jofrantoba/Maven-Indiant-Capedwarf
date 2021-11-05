package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.dao.DaoLocalidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicLocalidad{
	private PersistenceManager pm;

	public LogicLocalidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoLocalidad dao = new DaoLocalidad(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Localidad mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoLocalidad dao = new DaoLocalidad(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Localidad getBean(String id){
		try{
			DaoLocalidad dao = new DaoLocalidad(this.pm);
			return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}		
	}

	public Collection<Localidad> getListarBean() throws UnknownException {
		DaoLocalidad dao = new DaoLocalidad(this.pm);
		Collection<Localidad> lista = dao.getListarBean();
		return lista;
	}
}