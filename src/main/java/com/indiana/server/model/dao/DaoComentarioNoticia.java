package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoComentarioNoticia {
	private PersistenceManager pm;

	public DaoComentarioNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ComentarioNoticia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<ComentarioNoticia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ComentarioNoticia> listComentarios = (Collection<ComentarioNoticia>) query
				.getListaBean(ComentarioNoticia.class);
		return listComentarios;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ComentarioNoticia> getListBean(String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(ComentarioNoticia.class);
		String declareParameters="String paramCodeNoticia,Boolean paramIsPersistente";
		String filter="codeNoticia==paramCodeNoticia && isPersistente == paramIsPersistente";		
		try{
		List<ComentarioNoticia> lista=new ArrayList<ComentarioNoticia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();		
		beanObject.add(codeNoticia);
		beanObject.add(true);
		lista.addAll((List<ComentarioNoticia>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
		if(lista.size()>0){
			return lista;
		}else{
			return null;
		}		
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComentarioNoticia> getListarBean(Integer limiteMostrar,String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(ComentarioNoticia.class);
		String declareParameters="String paramCodeNoticia";		
		String filter= "this.codeNoticia==paramCodeNoticia";	
		String ordering="version desc";
		try{
			List<ComentarioNoticia> lista=new ArrayList<ComentarioNoticia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeNoticia);
			lista.addAll((List<ComentarioNoticia>)consulta.sendQuery(limiteMostrar, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}

}
