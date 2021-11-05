package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Tarifario;
import com.indiana.server.model.dao.DaoTarifario;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTarifario {
	private PersistenceManager pm;

	public LogicTarifario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTarifario dao = new DaoTarifario(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoTarifario dao = new DaoTarifario(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Long existeBean(String codeTipoSuscripcion,String codePais) throws UnknownException {
		DaoTarifario dao = new DaoTarifario(this.pm);
		if(dao.existeBean(codeTipoSuscripcion,codePais)!=0L){
			throw new UnknownException("Configuracion de tarifario existe y esta activa");
		}else{
			return 0L;
		}
	}

	public Collection<Tarifario> getListarBean() throws UnknownException {
		DaoTarifario dao = new DaoTarifario(this.pm);
		Collection<Tarifario> lista = dao.getListarBean();
		return lista;
	}
	

}
