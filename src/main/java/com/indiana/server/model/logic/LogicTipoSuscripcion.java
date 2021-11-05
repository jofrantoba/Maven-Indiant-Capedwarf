package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.server.model.dao.DaoTipoSuscripcion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoSuscripcion {
	private PersistenceManager pm;

	public LogicTipoSuscripcion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoSuscripcion dao = new DaoTipoSuscripcion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoTipoSuscripcion dao = new DaoTipoSuscripcion(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<TipoSuscripcion> getListarBean() throws UnknownException {
		DaoTipoSuscripcion dao = new DaoTipoSuscripcion(this.pm);
		Collection<TipoSuscripcion> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<TipoSuscripcion> getListarBeanActivos() throws UnknownException {
		DaoTipoSuscripcion dao = new DaoTipoSuscripcion(this.pm);
		Collection<TipoSuscripcion> lista = dao.getListarBeanActivos();
		return lista;
	}
}
