package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.ComparteNoticia;
import com.indiana.server.model.bean.Noticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoComparteNoticia {
	private PersistenceManager pm;

	public DaoComparteNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ComparteNoticia.class, id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getBean(String codeTuristaComparte,String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(ComparteNoticia.class);
		String declareParameters="String paramCodeTurista,String paramCodeNoticia,Boolean paramIsPersistente";
		String filter="codeTuristaComparte == paramCodeTurista && codeNoticia==paramCodeNoticia && isPersistente == paramIsPersistente";		
		try{
		List<ComparteNoticia> lista=new ArrayList<ComparteNoticia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeTuristaComparte);
		beanObject.add(codeNoticia);
		beanObject.add(true);
		lista.addAll((List<ComparteNoticia>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
		if(lista.size()==1){
			return lista.get(0);
		}else{
			return null;
		}		
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<ComparteNoticia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ComparteNoticia> listComentarios = (Collection<ComparteNoticia>) query
				.getListaBean(ComparteNoticia.class);
		return listComentarios;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComparteNoticia> getListBean(String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(ComparteNoticia.class);
		String declareParameters="String paramCodeNoticia,Boolean paramIsPersistente";
		String filter="codeNoticia==paramCodeNoticia && isPersistente == paramIsPersistente";		
		try{
		List<ComparteNoticia> lista=new ArrayList<ComparteNoticia>();
		Querys consulta=new Querys();
		List<Object> beanObject=new ArrayList();		
		beanObject.add(codeNoticia);
		beanObject.add(true);
		lista.addAll((List<ComparteNoticia>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
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
	public Collection<ComparteNoticia> getListarBean(Integer limiteMostrar,Noticia beanNoticia) throws UnknownException {
		Query query = pm.newQuery(ComparteNoticia.class);
		String declareParameters="Noticia beanNoticia";		
		String filter= "this.beanNoticia==beanNoticia";		
		try{
		List<ComparteNoticia> lista=new ArrayList<ComparteNoticia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(beanNoticia);
		lista.addAll((List<ComparteNoticia>)consulta.sendQuery(limiteMostrar, declareParameters, filter, null, beanObject, query));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
