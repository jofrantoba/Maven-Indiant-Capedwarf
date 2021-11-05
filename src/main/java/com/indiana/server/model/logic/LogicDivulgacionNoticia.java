package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.DivulgacionNoticia;
import com.indiana.server.model.dao.DaoDivulgacionNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicDivulgacionNoticia {
	private PersistenceManager pm;

	public LogicDivulgacionNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoDivulgacionNoticia dao = new DaoDivulgacionNoticia(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public DivulgacionNoticia mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoDivulgacionNoticia dao = new DaoDivulgacionNoticia(this.pm);
		return (DivulgacionNoticia)dao.mantenimientoReturn(parametro);
	}

	public DivulgacionNoticia getBean(String id){
		try{
			DaoDivulgacionNoticia dao = new DaoDivulgacionNoticia(this.pm);
			return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public DivulgacionNoticia getBean(String codeTuristaDivulgacion,String codeNoticia){
		try{
			DaoDivulgacionNoticia dao = new DaoDivulgacionNoticia(this.pm);
			return dao.getBean(codeTuristaDivulgacion, codeNoticia);
		}catch(Exception ex){
			return null;
		}
	}
	
	public List<DivulgacionNoticia> getListBean(String codeNoticia) throws UnknownException {
		try{
			DaoDivulgacionNoticia dao = new DaoDivulgacionNoticia(this.pm);
			return dao.getListBean(codeNoticia);
		}catch(Exception ex){
			return null;
		}
	}

	public Collection<DivulgacionNoticia> getListarBean() throws UnknownException {
		DaoDivulgacionNoticia dao = new DaoDivulgacionNoticia(this.pm);
		Collection<DivulgacionNoticia> lista = dao.getListarBean();
		return lista;
	}
}
