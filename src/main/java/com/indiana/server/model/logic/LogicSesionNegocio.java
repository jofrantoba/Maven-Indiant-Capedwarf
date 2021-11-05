package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SesionNegocio;
//import com.indiana.server.model.bean.SesionTurista;
import com.indiana.server.model.dao.DaoSesionNegocio;
//import com.indiana.server.model.dao.DaoSesionTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicSesionNegocio{
	private PersistenceManager pm;

	public LogicSesionNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSesionNegocio dao = new DaoSesionNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public SesionNegocio mantenimientoNegocio(BeanParametro parametro)
			throws UnknownException {
		DaoSesionNegocio dao = new DaoSesionNegocio(this.pm);
		return dao.mantenimientoNegocio(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSesionNegocio dao = new DaoSesionNegocio(this.pm);
		return dao.getBean((id));
	}

	public Collection<SesionNegocio> getListarBean() throws UnknownException {
		DaoSesionNegocio dao = new DaoSesionNegocio(this.pm);
		Collection<SesionNegocio> lista = dao.getListarBean();
		return lista;
	}
}