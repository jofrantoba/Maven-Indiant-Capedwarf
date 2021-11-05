package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.dao.DaoDestino;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicDestino{
	private PersistenceManager pm;

	public LogicDestino(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Destino mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeDestino) throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		return dao.getBeanByCode(codeDestino);
	}

	public Collection<Destino> getListarBean() throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		Collection<Destino> lista = dao.getListarBean();
		return lista;
	}
	public Collection<Destino> getListarBeanByCreador(String correoTurista) throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		Collection<Destino> lista = dao.getListarBeanByCreador(correoTurista);
		return lista;
	}
	public Collection<Destino> getListarBeanByLike(String patron,String codeCategoria,String nombrePais, String nombreRegion,String nombreLocalidad) throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		Collection<Destino> lista = dao.getListarBeanByLike(patron, codeCategoria, nombrePais, nombreRegion, nombreLocalidad);
		return lista;
	}
	public Collection<Destino> getListarBeanByConfiguracion(String codeCategoria,String nombrePais, String nombreRegion,String nombreLocalidad,String nombreTipoNegocioDestino) throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		Collection<Destino> lista = dao.getListarBeanByConfiguracion(codeCategoria, nombrePais, nombreRegion, nombreLocalidad,nombreTipoNegocioDestino);
		return lista;
	}
	public Collection<Destino> getListarBean(String nombrePais,String nombreRegion,String nombreLocalidadBusqueda,String tipoNegocioDestino) throws UnknownException {
		DaoDestino dao = new DaoDestino(this.pm);
		Collection<Destino> lista = dao.getListarBean(nombrePais, nombreRegion,nombreLocalidadBusqueda,tipoNegocioDestino);
		return lista;
	}
}