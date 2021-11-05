package com.indiana.server.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.TipoEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoEmpatia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PersistenceManager pm;

	public DaoTipoEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoEmpatia.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TipoEmpatia getBeanByCode(String codeTipoEmpatia) throws UnknownException {
		Query query = pm.newQuery(TipoEmpatia.class);
		String declareParameters="String paramCodeTipoEmpatia";		
		String filter= "codeTipoEmpatia==paramCodeTipoEmpatia";		
		try{
			List<TipoEmpatia> lista=new ArrayList<TipoEmpatia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();			
			beanObject.add(codeTipoEmpatia);
			lista.addAll((List<TipoEmpatia>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	public Collection<TipoEmpatia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoEmpatia> lista = (Collection<TipoEmpatia>) query
				.getListaBean(TipoEmpatia.class);
		return lista;
	}

}
