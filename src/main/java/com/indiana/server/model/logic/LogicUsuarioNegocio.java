package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.server.model.dao.DaoUsuarioNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicUsuarioNegocio {
	private PersistenceManager pm;

	public LogicUsuarioNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioNegocio dao = new DaoUsuarioNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuarioNegocio dao = new DaoUsuarioNegocio(this.pm);
		return dao.getBean(id);
	}

	public Object getBeanByEmail(String correoUsuarioNegocio) throws UnknownException {
		DaoUsuarioNegocio dao = new DaoUsuarioNegocio(this.pm);
		return dao.getBeanByEmail(correoUsuarioNegocio);
	}
	public Collection<UsuarioNegocio> getListarBean(String nombrePais,String nombreRegion,String nombreLocalidad) throws UnknownException {
		DaoUsuarioNegocio dao = new DaoUsuarioNegocio(this.pm);
		Collection<UsuarioNegocio> lista = dao.getListarBean(nombrePais,nombreRegion,nombreLocalidad);
		return lista;
	}
	
	public Collection<UsuarioNegocio> getListarBean() throws UnknownException {
		DaoUsuarioNegocio dao = new DaoUsuarioNegocio(this.pm);
		Collection<UsuarioNegocio> lista = dao.getListarBean();
		return lista;
	}
}
