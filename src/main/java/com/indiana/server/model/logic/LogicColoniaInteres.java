package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.dao.DaoColoniaInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicColoniaInteres {
	private PersistenceManager pm;

	public LogicColoniaInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Object getBean(ColoniaInteres beanColoniaInteres) throws UnknownException {
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		return dao.getBean(beanColoniaInteres);
	}
	
	public Object getBeanByColonia_Interes(String codeColonia, String codeInteres) throws UnknownException {
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		return dao.getBeanByColonia_Interes(codeColonia, codeInteres);
	}
	
	public Collection<ColoniaInteres> getListarBean() throws UnknownException {
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		Collection<ColoniaInteres> lista = dao.getListarBean();
		return lista;
	}
	
	public List<ColoniaInteres> getListarBean(String codeColonia,boolean order) throws UnknownException {
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		List<ColoniaInteres> lista = dao.getListarBean(codeColonia, order);
		return lista;
	}
	
	public List<ColoniaInteres> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreColonia,String nacionalidad) throws UnknownException {
		DaoColoniaInteres dao = new DaoColoniaInteres(this.pm);
		List<ColoniaInteres> lista = dao.getListarBean(annio, mes, dia, edad, nombreColonia, nacionalidad);
		return lista;
	}
}
