package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Empatia;
import com.indiana.server.model.dao.DaoEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicEmpatia {
	private PersistenceManager pm;

	public LogicEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEmpatia dao = new DaoEmpatia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoEmpatia dao = new DaoEmpatia(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeEmpatia) throws UnknownException {
		DaoEmpatia dao = new DaoEmpatia(this.pm);
		return dao.getBeanByCode(codeEmpatia);
	}

	public Object getBeanByCodeTuristas(String correoTurista1,String correoTurista2) throws UnknownException {
		DaoEmpatia dao = new DaoEmpatia(this.pm);
		return dao.getBeanByCodeTuristas(correoTurista1, correoTurista2);
	}
	
	public Collection<Empatia> getListarBean() throws UnknownException {
		DaoEmpatia dao = new DaoEmpatia(this.pm);
		Collection<Empatia> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Empatia> getListarBeanByEmail(String correoTurista,Long rangoFecha) throws UnknownException {
		DaoEmpatia dao = new DaoEmpatia(this.pm);
		Collection<Empatia> lista = dao.getListarBeanByEmail(correoTurista,rangoFecha);
		return lista;
	}
}
