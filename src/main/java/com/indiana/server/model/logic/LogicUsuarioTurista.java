package com.indiana.server.model.logic;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.DaoUsuarioTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicUsuarioTurista {
	private PersistenceManager pm;

	public LogicUsuarioTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioTurista dao = new DaoUsuarioTurista(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public UsuarioTurista mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioTurista dao = new DaoUsuarioTurista(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public UsuarioTurista getBean(String id){
		try{
		DaoUsuarioTurista dao = new DaoUsuarioTurista(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public UsuarioTurista getBeanByEmail(String correoTurista) throws UnknownException {
		DaoUsuarioTurista dao = new DaoUsuarioTurista(this.pm);
		return dao.getBeanByEmail(correoTurista);
	}
	
	public UsuarioTurista getBeanByEmailAtom(String correoTurista) throws UnknownException {
		DaoUsuarioTurista dao = new DaoUsuarioTurista(this.pm);
		return dao.getBeanByEmailAtom(correoTurista);
	}
	
	public List<UsuarioTurista> getListarBeanByConfiguracion(String nombrePais, String nombreRegion,String nombreLocalidad) throws UnknownException {
		DaoUsuarioTurista dao = new DaoUsuarioTurista(this.pm);
		List<UsuarioTurista> lista = dao.getListarBeanByConfiguracion(nombrePais, nombreRegion, nombreLocalidad);
		return lista;
	}
	

}
