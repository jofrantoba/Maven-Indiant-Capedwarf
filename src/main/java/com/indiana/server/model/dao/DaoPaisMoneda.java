package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoPaisMoneda {
	private PersistenceManager pm;

	public DaoPaisMoneda(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(PaisMoneda.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<PaisMoneda> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<PaisMoneda> listaMonedaPais = (Collection<PaisMoneda>) query
				.getListaBean(PaisMoneda.class);
		return listaMonedaPais;
	}

	@SuppressWarnings("unchecked")
	public Collection<PaisMoneda> getListarBeanByPais(String idPais)
			throws UnknownException {
		Query query = pm.newQuery(PaisMoneda.class);
		query.setFilter("idPais == paramIdPais");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdPais");
		try {
			List<PaisMoneda> lista = new ArrayList<PaisMoneda>();
			lista.addAll((List<PaisMoneda>) query.execute(idPais));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<PaisMoneda> getListarBeanByMoneda(String idMoneda)
			throws UnknownException {
		Query query = pm.newQuery(PaisMoneda.class);
		query.setFilter("idMoneda == paramIdMoneda");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdMoneda");
		try {
			List<PaisMoneda> lista = new ArrayList<PaisMoneda>();
			lista.addAll((List<PaisMoneda>) query.execute(idMoneda));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}