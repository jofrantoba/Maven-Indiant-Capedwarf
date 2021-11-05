package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.dao.DaoTipoNotificacion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoNotificacion {
	private PersistenceManager pm;

	public LogicTipoNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoNotificacion dao = new DaoTipoNotificacion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public TipoNotificacion getBean(String id){
		try{
		DaoTipoNotificacion dao = new DaoTipoNotificacion(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<TipoNotificacion> getListarBean(){
		try{
		DaoTipoNotificacion dao = new DaoTipoNotificacion(this.pm);
		List<TipoNotificacion> lista = dao.getListarBean();
		return lista;
		}catch(Exception ex){
			return null;
		}
	}
}
