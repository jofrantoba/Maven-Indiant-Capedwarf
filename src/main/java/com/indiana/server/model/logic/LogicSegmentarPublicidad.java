package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.SegmentarPublicidad;
import com.indiana.server.model.dao.DaoSegmentarPublicidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicSegmentarPublicidad {
	private PersistenceManager pm;

	public LogicSegmentarPublicidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSegmentarPublicidad dao = new DaoSegmentarPublicidad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSegmentarPublicidad dao = new DaoSegmentarPublicidad(this.pm);
		return dao.getBean(id);
	}

	public Collection<SegmentarPublicidad> getListarBean() throws UnknownException {
		DaoSegmentarPublicidad dao = new DaoSegmentarPublicidad(this.pm);
		Collection<SegmentarPublicidad> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<SegmentarPublicidad> getListarBeanByPais(String codePais) throws UnknownException {
		DaoSegmentarPublicidad dao = new DaoSegmentarPublicidad(this.pm);
		Collection<SegmentarPublicidad> lista = dao.getListarBeanByPais(codePais);
		return lista;
	}
}
