package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.dao.DaoMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicMiembro {
	private PersistenceManager pm;

	public LogicMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Miembro mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Object getBean(String id){
		try{
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	
	public Boolean existeMiembro(String codeUsuarioTurista, String codeColonia) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.existeMiembro(codeUsuarioTurista, codeColonia);
	}
	
	public Miembro verPerfilMiembro(String codeUsuarioTurista, String codeColonia) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.verPerfilMiembro(codeUsuarioTurista, codeColonia);
	}	
	
	public Object getBeanByCodeUsuario(String codeUsuarioTurista) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.getBean(codeUsuarioTurista);
	}

	public Object getBeanByCode(String codeMiembro) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		return dao.getBeanByCode(codeMiembro);
	}
	
	public Collection<Miembro> getListarBean() throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		Collection<Miembro> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Miembro> getListarBeanByColonia(String codeColonia) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		Collection<Miembro> lista = dao.getListarBeanByColonia(codeColonia);
		return lista;
	}
	
	public Collection<Miembro> getListarBeanByChatColonia(String codeColonia) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		Collection<Miembro> lista = dao.getListarBeanByChatColonia(codeColonia);
		return lista;
	}
	
	public List<Miembro> getListarBean(Integer limiteMostrar,String codeUsuarioTurista,String estadoVisibilidad) throws UnknownException {
		DaoMiembro dao = new DaoMiembro(this.pm);
		List<Miembro> lista = dao.getListarBean(limiteMostrar, codeUsuarioTurista,estadoVisibilidad);
		return lista;
	}
}
