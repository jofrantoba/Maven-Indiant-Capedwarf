package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.CuentaNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCuentaNegocio {
	private PersistenceManager pm;

	public DaoCuentaNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CuentaNegocio.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CuentaNegocio getBeanByEmail(String correoNegocio) throws UnknownException {
		Query query = pm.newQuery(CuentaNegocio.class);
		String filter = "this.correoNegocio=='" + correoNegocio + "'";
		try {
			List<CuentaNegocio> lista = new ArrayList<CuentaNegocio>();
			Querys consulta = new Querys();
			List beanObject = new ArrayList();
			beanObject.add(correoNegocio);
			lista.addAll((List<CuentaNegocio>) consulta.sendQuery(1, null, filter, null, beanObject, query));
			if (lista.isEmpty()) {
				return null;
			}
			return lista.get(0);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<CuentaNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CuentaNegocio> lista = (Collection<CuentaNegocio>) query.getListaBean(CuentaNegocio.class);
		return lista;
	}
}
