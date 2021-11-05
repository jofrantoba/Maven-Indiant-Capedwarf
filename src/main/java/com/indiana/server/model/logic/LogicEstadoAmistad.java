package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.server.model.dao.DaoEstadoAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEstadoAmistad{
	private PersistenceManager pm;

	public LogicEstadoAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEstadoAmistad dao = new DaoEstadoAmistad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
			DaoEstadoAmistad dao = new DaoEstadoAmistad(this.pm);
			return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}		
	}
	
	public Object getBeanByCode(String codeEstadoAmistad) throws UnknownException {
		DaoEstadoAmistad dao = new DaoEstadoAmistad(this.pm);
		return dao.getBeanByCode(codeEstadoAmistad);
	}

	public Collection<EstadoAmistad> getListarBean() throws UnknownException {
		DaoEstadoAmistad dao = new DaoEstadoAmistad(this.pm);
		Collection<EstadoAmistad> lista = dao.getListarBean();
		return lista;
	}
}