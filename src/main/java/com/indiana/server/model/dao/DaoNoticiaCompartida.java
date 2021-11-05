package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.NoticiaCompartida;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoNoticiaCompartida {
	private PersistenceManager pm;

	public DaoNoticiaCompartida(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public NoticiaCompartida mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (NoticiaCompartida)query.mantenimientoReturn(parametro);
	}

	public NoticiaCompartida getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (NoticiaCompartida)query.getBean(NoticiaCompartida.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<NoticiaCompartida> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<NoticiaCompartida> listaNoticiaCompartida = (Collection<NoticiaCompartida>) query
				.getListaBean(NoticiaCompartida.class);
		return listaNoticiaCompartida;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<NoticiaCompartida> getListBean(String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(NoticiaCompartida.class);
		String declareParameters="String paramCodeNoticia,Boolean paramIsPersistente";
		String filter="codeNoticia==paramCodeNoticia && isPersistente == paramIsPersistente";		
		try{
		List<NoticiaCompartida> lista=new ArrayList<NoticiaCompartida>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();		
		beanObject.add(codeNoticia);
		beanObject.add(true);
		lista.addAll((List<NoticiaCompartida>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
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
}
