package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SesionTurista;
import com.indiana.server.model.dao.DaoSesionTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicSesionTurista{
	private PersistenceManager pm;

	public LogicSesionTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public SesionTurista mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSesionTurista dao = new DaoSesionTurista(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSesionTurista dao = new DaoSesionTurista(this.pm);
		return dao.getBean((id));
	}
	
	public Object getBeanByMiembro(String correoTurista,String estadoSesion) throws UnknownException {
		DaoSesionTurista dao = new DaoSesionTurista(this.pm);
		return dao.getBeanByMiembro(correoTurista,estadoSesion);
	}

	public Collection<SesionTurista> getListarBean() throws UnknownException {
		DaoSesionTurista dao = new DaoSesionTurista(this.pm);
		Collection<SesionTurista> lista = dao.getListarBean();
		return lista;
	}
}