package com.indiana.server.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoSuscripcion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersistenceManager pm;

	public DaoTipoSuscripcion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoSuscripcion.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoSuscripcion> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoSuscripcion> lista = (Collection<TipoSuscripcion>) query
				.getListaBean(TipoSuscripcion.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<TipoSuscripcion> getListarBeanActivos() throws UnknownException {
		Query query = pm.newQuery(TipoSuscripcion.class);
		query.setFilter("estado == paramEstado && isPersistente == paramIsPersistente");		
		query.setOrdering("version desc");
		query.declareParameters("String paramEstado,Boolean paramIsPersistente");
		try {
			List<TipoSuscripcion> lista = new ArrayList<TipoSuscripcion>();
			lista.addAll((List<TipoSuscripcion>) query.execute("A",true));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

}
