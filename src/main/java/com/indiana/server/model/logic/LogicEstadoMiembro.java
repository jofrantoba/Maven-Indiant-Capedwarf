package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EstadoMiembro;
import com.indiana.server.model.dao.DaoEstadoMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEstadoMiembro{
	private PersistenceManager pm;

	public LogicEstadoMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEstadoMiembro dao = new DaoEstadoMiembro(this.pm);
		return dao.mantenimiento(parametro);
	}

	public EstadoMiembro getBean(String id){
		try{
		DaoEstadoMiembro dao = new DaoEstadoMiembro(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<EstadoMiembro> getListarBean() throws UnknownException {
		DaoEstadoMiembro dao = new DaoEstadoMiembro(this.pm);
		Collection<EstadoMiembro> lista = dao.getListarBean();
		return lista;
	}
}