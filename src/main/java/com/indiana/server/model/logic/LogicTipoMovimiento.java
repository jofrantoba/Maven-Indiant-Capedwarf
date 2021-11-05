package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.server.model.dao.DaoTipoMovimiento;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoMovimiento {
	private PersistenceManager pm;

	public LogicTipoMovimiento(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoMovimiento dao = new DaoTipoMovimiento(this.pm);
		return dao.mantenimiento(parametro);
	}

	public TipoMovimiento getBean(String id){
		try{
		DaoTipoMovimiento dao = new DaoTipoMovimiento(this.pm);
		return dao.getBean((id));
		}catch(Exception ex){
			return null;
		}
	}

	public Object getBean(String codeBeanTipoMovimiento,Boolean code) throws UnknownException {
		DaoTipoMovimiento dao = new DaoTipoMovimiento(this.pm);
		return dao.getBean(codeBeanTipoMovimiento, code);
	}
	
	
	public Collection<TipoMovimiento> getListarBean() throws UnknownException {
		DaoTipoMovimiento dao = new DaoTipoMovimiento(this.pm);
		Collection<TipoMovimiento> lista = dao.getListarBean();
		return lista;
	}
}
