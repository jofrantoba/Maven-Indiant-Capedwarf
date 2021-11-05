package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.dao.DaoConquista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicConquista {
	private PersistenceManager pm;

	public LogicConquista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Conquista mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		return dao.getBean(id);
	}

	public Object getBean(String correoUsuarioTurista,String codeDestino) throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		return dao.getBean(correoUsuarioTurista, codeDestino);
	}
	
	public Collection<Conquista> getListarBean() throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		Collection<Conquista> lista = dao.getListarBean();
		return lista;
	}
	
	public List<Conquista> getListarBeanByTurista(String correoTurista) throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		List<Conquista> lista = dao.getListarBeanByTurista(correoTurista);
		return lista;
	}
	
	public Collection<Conquista> getListarBeanByDestino(String correoTurista,String codeDestino) throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		Collection<Conquista> lista = dao.getListarBeanByDestino(correoTurista,codeDestino);
		return lista;
	}
	public List<Conquista> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreDestino,String nacionalidad) throws UnknownException {
		DaoConquista dao = new DaoConquista(this.pm);
		return dao.getListarBean(annio, mes, dia, edad, nombreDestino, nacionalidad);		
	}
}
