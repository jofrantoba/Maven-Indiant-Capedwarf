package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CategoriaDestino;
import com.indiana.server.model.dao.DaoCategoriaDestino;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCategoriaDestino{
	private PersistenceManager pm;

	public LogicCategoriaDestino(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCategoriaDestino dao = new DaoCategoriaDestino(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
			DaoCategoriaDestino dao = new DaoCategoriaDestino(this.pm);
			return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<CategoriaDestino> getListarBean() throws UnknownException {
		DaoCategoriaDestino dao = new DaoCategoriaDestino(this.pm);
		Collection<CategoriaDestino> lista = dao.getListarBean();
		return lista;
	}
}