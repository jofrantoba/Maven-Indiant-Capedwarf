package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.DaoColonia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicColonia {
	private PersistenceManager pm;

	public LogicColonia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoColonia dao = new DaoColonia(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Colonia mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoColonia dao = new DaoColonia(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Colonia getBean(String id){
		try{
		DaoColonia dao = new DaoColonia(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}

	public Colonia getBeanByCode(String codeColonia){
		try{
		DaoColonia dao = new DaoColonia(this.pm);
		return dao.getBean(codeColonia);
		}catch(Exception ex){
			return null;
		}
	}
	public Collection<Colonia> getListarBean() throws UnknownException {
		DaoColonia dao = new DaoColonia(this.pm);
		Collection<Colonia> lista = dao.getListarBean();
		return lista;
	}
	public List<Colonia> getListarBeanByTuristaCreador(String codeTuristaCreador) throws UnknownException {
		DaoColonia dao = new DaoColonia(this.pm);
		List<Colonia> lista = dao.getListarBeanByTuristaCreador(codeTuristaCreador);
		return lista;
	}
	
	public Collection<Colonia> getListarBean(Integer limiteMostrar,UsuarioTurista beanUsuarioTurista) throws UnknownException {
		DaoColonia dao = new DaoColonia(this.pm);
		Collection<Colonia> lista = dao.getListarBean(limiteMostrar, beanUsuarioTurista);
		return lista;
	}
	
	public Collection<Colonia> getListarBean(String nombrePais,String nombreRegion,String nombreLocalidadBusqueda) throws UnknownException {
		DaoColonia dao = new DaoColonia(this.pm);
		Collection<Colonia> lista = dao.getListarBean(nombrePais, nombreRegion,nombreLocalidadBusqueda);
		return lista;
	}
}
