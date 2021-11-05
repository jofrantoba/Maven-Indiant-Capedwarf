package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.dao.DaoComentarioNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicComentarioNoticia {
	private PersistenceManager pm;

	public LogicComentarioNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoComentarioNoticia dao = new DaoComentarioNoticia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoComentarioNoticia dao = new DaoComentarioNoticia(this.pm);
		return dao.getBean(id);
	}

	public Collection<ComentarioNoticia> getListarBean() throws UnknownException {
		DaoComentarioNoticia dao = new DaoComentarioNoticia(this.pm);
		Collection<ComentarioNoticia> lista = dao.getListarBean();
		return lista;
	}
	
	public List<ComentarioNoticia> getListBean(String codeNoticia) throws UnknownException {
		try{
			DaoComentarioNoticia dao = new DaoComentarioNoticia(this.pm);
			return dao.getListBean(codeNoticia);
		}catch(Exception ex){
			return null;
		}
	}
	
	public List<ComentarioNoticia> getListarBean(Integer limiteMostrar,String codeNoticia) throws UnknownException {
		DaoComentarioNoticia dao = new DaoComentarioNoticia(this.pm);
		List<ComentarioNoticia> lista = dao.getListarBean(limiteMostrar, codeNoticia);
		return lista;
	}
}
