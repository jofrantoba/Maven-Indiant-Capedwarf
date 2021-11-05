package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Idioma;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoIdioma {
	private PersistenceManager pm;

	public DaoIdioma(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Idioma.class, id);
	}

	@SuppressWarnings("unchecked")
	public Idioma getBeanByCode(String codeIdioma) throws UnknownException {
		Query query = pm.newQuery(Idioma.class);
		String declareParameters = "String paramCodeIdioma";
		String filter = "codeIdioma==paramCodeIdioma";
		try {
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<Idioma> listIdiomas = new ArrayList<Idioma>();
			listIdiomas.addAll((List<Idioma>) (query.execute(codeIdioma)));
			if (!listIdiomas.isEmpty()) {
				return listIdiomas.get(0);
			}
			return null;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Idioma> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Idioma> lista = (Collection<Idioma>) query
				.getListaBean(Idioma.class);
		return lista;
	}

}
