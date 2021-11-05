package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.VisitaPerfilNegocio;
import com.indiana.server.model.dao.DaoVisitaPerfilNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicVisitaPerfilNegocio{
	private PersistenceManager pm;

	public LogicVisitaPerfilNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoVisitaPerfilNegocio dao = new DaoVisitaPerfilNegocio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoVisitaPerfilNegocio dao = new DaoVisitaPerfilNegocio(this.pm);
		return dao.getBean(id);
	}

	public Collection<VisitaPerfilNegocio> getListarBean() throws UnknownException {
		DaoVisitaPerfilNegocio dao = new DaoVisitaPerfilNegocio(this.pm);
		Collection<VisitaPerfilNegocio> lista = dao.getListarBean();
		return lista;
	}
}