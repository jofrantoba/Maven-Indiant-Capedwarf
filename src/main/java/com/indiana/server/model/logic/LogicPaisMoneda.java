package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.server.model.dao.DaoPaisMoneda;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicPaisMoneda{
	private PersistenceManager pm;

	public LogicPaisMoneda(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPaisMoneda dao = new DaoPaisMoneda(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoPaisMoneda dao = new DaoPaisMoneda(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<PaisMoneda> getListarBean() throws UnknownException {
		DaoPaisMoneda dao = new DaoPaisMoneda(this.pm);
		Collection<PaisMoneda> lista = dao.getListarBean();
		return lista;
	}
}