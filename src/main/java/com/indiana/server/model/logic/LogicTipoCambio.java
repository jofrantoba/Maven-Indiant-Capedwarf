package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoCambio;
import com.indiana.server.model.dao.DaoTipoCambio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoCambio {
	private PersistenceManager pm;

	public LogicTipoCambio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoCambio dao = new DaoTipoCambio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoTipoCambio dao = new DaoTipoCambio(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<TipoCambio> getListarBean() throws UnknownException {
		DaoTipoCambio dao = new DaoTipoCambio(this.pm);
		Collection<TipoCambio> lista = dao.getListarBean();
		return lista;
	}
	
	/*public List<TipoCambio> getListBean() throws UnknownException {
		DaoTipoCambio dao = new DaoTipoCambio(this.pm);
		List<TipoCambio> lista = dao.getListBean();
		return lista;
	}*/
}

