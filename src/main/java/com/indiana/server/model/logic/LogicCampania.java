package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Campania;
import com.indiana.server.model.dao.DaoCampania;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCampania{
	private PersistenceManager pm;

	public LogicCampania(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCampania dao = new DaoCampania(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCampania dao = new DaoCampania(this.pm);
		return dao.getBean(id);
	}

	public Collection<Campania> getListarBean() throws UnknownException {
		DaoCampania dao = new DaoCampania(this.pm);
		Collection<Campania> lista = dao.getListarBean();
		return lista;
	}
}