package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Privacidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoPrivacidad {
	private PersistenceManager pm;

	public DaoPrivacidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Privacidad.class, id);
	}

	@SuppressWarnings("unchecked")
	public Privacidad getBeanByCode(String codePrivacidad) throws UnknownException {
		Query query = pm.newQuery(Privacidad.class);
		String declareParameters = "String paramCodePrivacidad";
		String filter = "codePrivacidad==paramCodePrivacidad";
		try {
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<Privacidad> listPrivacidads = new ArrayList<Privacidad>();
			listPrivacidads.addAll((List<Privacidad>) (query.execute(codePrivacidad)));
			if (!listPrivacidads.isEmpty()) {
				return listPrivacidads.get(0);
			}
			return null;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Privacidad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Privacidad> lista = (Collection<Privacidad>) query
				.getListaBean(Privacidad.class);
		return lista;
	}

}
