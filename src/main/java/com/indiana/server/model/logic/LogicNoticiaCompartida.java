package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.NoticiaCompartida;
import com.indiana.server.model.dao.DaoNoticiaCompartida;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicNoticiaCompartida {
	private PersistenceManager pm;

	public LogicNoticiaCompartida(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoNoticiaCompartida dao = new DaoNoticiaCompartida(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public NoticiaCompartida mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoNoticiaCompartida dao = new DaoNoticiaCompartida(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public NoticiaCompartida getBean(String id){
		try{
			DaoNoticiaCompartida dao = new DaoNoticiaCompartida(this.pm);
			return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
		
	}

	public Collection<NoticiaCompartida> getListarBean() throws UnknownException {
		DaoNoticiaCompartida dao = new DaoNoticiaCompartida(this.pm);
		Collection<NoticiaCompartida> lista = dao.getListarBean();
		return lista;
	}
	
	public List<NoticiaCompartida> getListBean(String codeNoticia) throws UnknownException {
		try{
			DaoNoticiaCompartida dao = new DaoNoticiaCompartida(this.pm);
			return dao.getListBean(codeNoticia);
		}catch(Exception ex){
			return null;
		}
	}
	
}
