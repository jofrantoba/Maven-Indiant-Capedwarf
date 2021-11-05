package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Region;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoRegion {
	private PersistenceManager pm;

	public DaoRegion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Region mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Region)query.mantenimientoReturn(parametro);
	}

	public Region getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Region)query.getBean(Region.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Region> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Region> listaRegion = (Collection<Region>) query
				.getListaBean(Region.class);
		return listaRegion;
	}

	@SuppressWarnings("unchecked")
	public Region getBeanByCode(String codeRegion) throws UnknownException {
		Query query = pm.newQuery(Region.class);
		query.declareParameters("String paramCodeRegion");
		query.setFilter("codeRegion == paramCodeRegion");						
		try{
			List<Region> lista=new ArrayList<Region>();
			lista.addAll((List<Region>)query.execute(codeRegion));
			if(!lista.isEmpty()){
				return lista.get(0);
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Region> getListarBeanByPais(String codePais)
			throws UnknownException {
		Query query = pm.newQuery(Region.class);
		query.setFilter("codePais == paramCodePais && isPersistente == paramIsPersistente");		
		query.setOrdering("version desc");
		query.declareParameters("String paramCodePais,Boolean paramIsPersistente");
		try {
			List<Region> lista = new ArrayList<Region>();
			lista.addAll((List<Region>) query.execute(codePais,true));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
