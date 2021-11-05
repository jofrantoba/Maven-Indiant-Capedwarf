package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoAnuncio;
import com.indiana.server.model.dao.DaoTipoAnuncio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoAnuncio {
	private PersistenceManager pm;

	public LogicTipoAnuncio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoAnuncio dao = new DaoTipoAnuncio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoTipoAnuncio dao = new DaoTipoAnuncio(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<TipoAnuncio> getListarBean() throws UnknownException {
		DaoTipoAnuncio dao = new DaoTipoAnuncio(this.pm);
		Collection<TipoAnuncio> lista = dao.getListarBean();
		return lista;
	}
}

