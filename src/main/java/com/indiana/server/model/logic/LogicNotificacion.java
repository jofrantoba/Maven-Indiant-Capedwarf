package com.indiana.server.model.logic;import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.dao.DaoNotificacion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicNotificacion{
	private PersistenceManager pm;

	public LogicNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Notificacion mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeNotificacion) throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.getBeanByCode(codeNotificacion);
	}

	public Collection<Notificacion> getListarBean() throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		Collection<Notificacion> lista = dao.getListarBean();
		return lista;
	}
}