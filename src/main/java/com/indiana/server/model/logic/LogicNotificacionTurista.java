package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.NotificacionTurista;
import com.indiana.server.model.dao.DaoNotificacionTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicNotificacionTurista {
	private PersistenceManager pm;

	public LogicNotificacionTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public NotificacionTurista mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		return dao.getBean(id);
	}
	public NotificacionTurista getBeanByCode(String codeNotificacionTurista) throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		return dao.getBeanByCode(codeNotificacionTurista);
	}
	
	public Integer getCount(String correoTurista) throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		return dao.getCount(correoTurista);
	}
	public Collection<NotificacionTurista> getListarBean() throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		Collection<NotificacionTurista> lista = dao.getListarBean();
		return lista;
	}
	
	public List<NotificacionTurista> getListarBean(String correoTurista,String visto) throws UnknownException {
		DaoNotificacionTurista dao = new DaoNotificacionTurista(this.pm);
		List<NotificacionTurista> lista = dao.getListarBean(correoTurista, visto);
		return lista;
	}
}
