package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Moneda;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoMoneda {
	private PersistenceManager pm;

	public DaoMoneda(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Moneda.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Moneda> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Moneda> listaPaises = (Collection<Moneda>) query
				.getListaBean(Moneda.class);
		return listaPaises;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Moneda> getListarBeanActivos() throws UnknownException {
		Query query = pm.newQuery(Moneda.class);
		query.setFilter("estado == paramEstado && isPersistente == paramIsPersistente");		
		query.setOrdering("version desc");
		query.declareParameters("String paramEstado,Boolean paramIsPersistente");
		try {
			List<Moneda> lista = new ArrayList<Moneda>();
			lista.addAll((List<Moneda>) query.execute("A",true));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
