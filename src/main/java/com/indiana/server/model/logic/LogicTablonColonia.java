package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TablonColonia;
import com.indiana.server.model.dao.DaoTablonColonia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTablonColonia {
	private PersistenceManager pm;

	public LogicTablonColonia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTablonColonia dao = new DaoTablonColonia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTablonColonia dao = new DaoTablonColonia(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeTablonColonia) throws UnknownException {
		DaoTablonColonia dao = new DaoTablonColonia(this.pm);
		return dao.getBeanByCode(codeTablonColonia);
	}
	
	public Collection<TablonColonia> getListarBean() throws UnknownException {
		DaoTablonColonia dao = new DaoTablonColonia(this.pm);
		Collection<TablonColonia> lista = dao.getListarBean();
		return lista;
	}
	
	public  List<TablonColonia> getListarBeanByColonia(String codeColonia,String estadoTablon,Integer limiteMostrar) throws UnknownException {
		DaoTablonColonia dao = new DaoTablonColonia(this.pm);
		return dao.getListarBeanByColonia(codeColonia,estadoTablon,limiteMostrar);
	}
	
	public  List<TablonColonia> getListarBeanByColoniaRangoFecha(String codeColonia,String estadoTablon,Long rangoFecha) throws UnknownException {
		DaoTablonColonia dao = new DaoTablonColonia(this.pm);
		return dao.getListarBeanByColoniaRangoFecha(codeColonia,estadoTablon,rangoFecha);
	}
}
