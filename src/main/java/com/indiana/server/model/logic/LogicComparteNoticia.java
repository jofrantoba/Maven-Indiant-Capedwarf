package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ComparteNoticia;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.dao.DaoComparteNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicComparteNoticia {
	private PersistenceManager pm;

	public LogicComparteNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoComparteNoticia dao = new DaoComparteNoticia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoComparteNoticia dao = new DaoComparteNoticia(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBean(String codeTuristaComparte,String codeNoticia) throws UnknownException {
		DaoComparteNoticia dao = new DaoComparteNoticia(this.pm);
		return dao.getBean(codeTuristaComparte, codeNoticia);
	}

	public Collection<ComparteNoticia> getListarBean() throws UnknownException {
		DaoComparteNoticia dao = new DaoComparteNoticia(this.pm);
		Collection<ComparteNoticia> lista = dao.getListarBean();
		return lista;
	}
	
	public List<ComparteNoticia> getListBean(String codeNoticia) throws UnknownException {
		try{
			DaoComparteNoticia dao = new DaoComparteNoticia(this.pm);
			return dao.getListBean(codeNoticia);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Collection<ComparteNoticia> getListarBean(Integer limiteMostrar,Noticia beanNoticia) throws UnknownException {
		DaoComparteNoticia dao = new DaoComparteNoticia(this.pm);
		Collection<ComparteNoticia> lista = dao.getListarBean(limiteMostrar, beanNoticia);
		return lista;
	}
}
