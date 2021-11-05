package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.dao.DaoPais;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicPais {
	private PersistenceManager pm;

	public LogicPais(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Pais mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Pais getBean(String id){
		try{
		DaoPais dao = new DaoPais(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	public Pais getBeanByCode(String codePais) throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		Pais beanPais=dao.getBeanByCode(codePais);
		return beanPais;
	}
	
	public Collection<Pais> getListarBean() throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		Collection<Pais> lista = dao.getListarBean();
		return lista;
	}
	
	public List<Pais> getListarBean(Integer limiteMostrar,String generador) throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		List<Pais> lista = dao.getListarBean(limiteMostrar,generador);
		return lista;
	}
}
