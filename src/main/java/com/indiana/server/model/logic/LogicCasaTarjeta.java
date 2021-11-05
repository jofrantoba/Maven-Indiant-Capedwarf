package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.server.model.dao.DaoCasaTarjeta;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicCasaTarjeta {
	private PersistenceManager pm;

	public LogicCasaTarjeta(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCasaTarjeta dao = new DaoCasaTarjeta(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoCasaTarjeta dao = new DaoCasaTarjeta(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<CasaTarjeta> getListarBean() throws UnknownException {
		DaoCasaTarjeta dao = new DaoCasaTarjeta(this.pm);
		Collection<CasaTarjeta> lista = dao.getListarBean();
		return lista;
	}
}