package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Idioma;
import com.indiana.server.model.dao.DaoIdioma;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicIdioma{
	private PersistenceManager pm;

	public LogicIdioma(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoIdioma dao = new DaoIdioma(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoIdioma dao = new DaoIdioma(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Idioma getBeanByCode(String codeIdioma) throws UnknownException {
		DaoIdioma dao = new DaoIdioma(this.pm);
		return dao.getBeanByCode(codeIdioma);
	}

	public Collection<Idioma> getListarBean() throws UnknownException {
		DaoIdioma dao = new DaoIdioma(this.pm);
		Collection<Idioma> lista = dao.getListarBean();
		return lista;
	}
}