package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Pais;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoPais {
	private PersistenceManager pm;

	public DaoPais(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Pais mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Pais)query.mantenimientoReturn(parametro);
	}

	public Pais getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Pais)query.getBean(Pais.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Pais getBeanByCode(String codePais) throws UnknownException {
		Query query = pm.newQuery(Pais.class);
		query.declareParameters("String paramCodePais");
		query.setFilter("codePais == paramCodePais");						
		try{
			List<Pais> lista=new ArrayList<Pais>();
			lista.addAll((List<Pais>)query.execute(codePais));
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
	public Collection<Pais> getListarBean() throws UnknownException {
		Query query = pm.newQuery(Pais.class);		
		query.setFilter("isPersistente == paramIsPersistente");		
		query.setOrdering("nombre asc");
		query.declareParameters("Boolean paramIsPersistente");		
		try{
		List<Pais> lista=new ArrayList<Pais>();
		lista.addAll((List<Pais>)query.execute(true));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Pais> getListarBean(Integer limiteMostrar,String generador) throws UnknownException {
		Query query = pm.newQuery(Pais.class);
		String declareParameters="String paramGenerador,Boolean paramIsPersistente";
		String filter="generador == paramGenerador && isPersistente == paramIsPersistente";
		String ordering="nombre asc";			
		try{
		List<Pais> lista=new ArrayList<Pais>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(generador);
		beanObject.add(true);
		lista.addAll((List<Pais>)consulta.sendQuery(limiteMostrar, declareParameters, filter, ordering, beanObject, query));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
}
