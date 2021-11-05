package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.DivulgacionNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoDivulgacionNoticia {
	private PersistenceManager pm;

	public DaoDivulgacionNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public DivulgacionNoticia mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (DivulgacionNoticia)query.mantenimientoReturn(parametro);
	}

	public DivulgacionNoticia getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (DivulgacionNoticia)query.getBean(DivulgacionNoticia.class, id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DivulgacionNoticia getBean(String codeTuristaDivulgacion,String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(DivulgacionNoticia.class);
		String declareParameters="String paramCodeTuristaDivulgacion,String paramCodeNoticia,Boolean paramIsPersistente";
		String filter="codeTuristaDivulgacion == paramCodeTuristaDivulgacion && codeNoticia==paramCodeNoticia && isPersistente == paramIsPersistente";		
		try{
		List<DivulgacionNoticia> lista=new ArrayList<DivulgacionNoticia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeTuristaDivulgacion);
		beanObject.add(codeNoticia);
		beanObject.add(true);
		lista.addAll((List<DivulgacionNoticia>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DivulgacionNoticia> getListBean(String codeNoticia) throws UnknownException {
		Query query = pm.newQuery(DivulgacionNoticia.class);
		String declareParameters="String paramCodeNoticia,Boolean paramIsPersistente";
		String filter="codeNoticia==paramCodeNoticia && isPersistente == paramIsPersistente";		
		try{
		List<DivulgacionNoticia> lista=new ArrayList<DivulgacionNoticia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();		
		beanObject.add(codeNoticia);
		beanObject.add(true);
		lista.addAll((List<DivulgacionNoticia>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
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
	
	

	@SuppressWarnings("unchecked")
	public Collection<DivulgacionNoticia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<DivulgacionNoticia> listaLocalidad = (Collection<DivulgacionNoticia>) query
				.getListaBean(DivulgacionNoticia.class);
		return listaLocalidad;
	}
}
