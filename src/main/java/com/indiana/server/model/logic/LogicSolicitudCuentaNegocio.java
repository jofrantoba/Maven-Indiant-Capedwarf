package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SolicitudCuentaNegocio;
import com.indiana.server.model.dao.DaoSolicitudCuentaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicSolicitudCuentaNegocio {
	private PersistenceManager pm;

	public LogicSolicitudCuentaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSolicitudCuentaNegocio dao = new DaoSolicitudCuentaNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSolicitudCuentaNegocio dao = new DaoSolicitudCuentaNegocio(this.pm);
		return dao.getBean(id);
	}

	public Collection<SolicitudCuentaNegocio> getListarBean()
			throws UnknownException {
		DaoSolicitudCuentaNegocio dao = new DaoSolicitudCuentaNegocio(this.pm);
		Collection<SolicitudCuentaNegocio> lista = dao.getListarBean();
		return lista;
	}
}
