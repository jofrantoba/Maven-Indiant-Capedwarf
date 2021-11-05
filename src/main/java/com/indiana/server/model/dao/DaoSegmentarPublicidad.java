package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.SegmentarPublicidad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoSegmentarPublicidad {
	private PersistenceManager pm;

	public DaoSegmentarPublicidad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SegmentarPublicidad.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SegmentarPublicidad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SegmentarPublicidad> listaSegmentarPublicidad = (Collection<SegmentarPublicidad>) query
				.getListaBean(SegmentarPublicidad.class);
		return listaSegmentarPublicidad;
	}

	@SuppressWarnings("unchecked")
	public Collection<SegmentarPublicidad> getListarBeanByPais(String codePais)
			throws UnknownException {
		Query query = pm.newQuery(SegmentarPublicidad.class);
		query.setFilter("codePais == paramCodePais && isPersistente == paramIsPersistente");		
		query.setOrdering("version desc");
		query.declareParameters("String paramCodePais,Boolean paramIsPersistente");
		try {
			List<SegmentarPublicidad> lista = new ArrayList<SegmentarPublicidad>();
			lista.addAll((List<SegmentarPublicidad>) query.execute(codePais,true));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
