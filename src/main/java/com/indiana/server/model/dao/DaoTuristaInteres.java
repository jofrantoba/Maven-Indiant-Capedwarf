package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.query.evaluator.InMemoryQueryResult;

import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTuristaInteres {
	private PersistenceManager pm;

	public DaoTuristaInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TuristaInteres.class, id);
	}	
	
	@SuppressWarnings("unchecked")
	public TuristaInteres getBeanByCode(String codeTuristaInteres) throws UnknownException {
		Query query = pm.newQuery(TuristaInteres.class);
		query.declareParameters("String paramCodeTuristaInteres");
		query.setFilter("codeTuristaInteres == paramCodeTuristaInteres");						
		try{
			List<TuristaInteres> lista=new ArrayList<TuristaInteres>();
			lista.addAll((List<TuristaInteres>)query.execute(codeTuristaInteres));
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
	public Object getBean(UsuarioTurista beanUsuarioTurista, Interes beanInteres) throws UnknownException {		
		Query query = pm.newQuery(TuristaInteres.class);
		String declareParameters="UsuarioTurista beanUsuarioTurista, Interes beanInteres";
		String filter="this.beanTurista==beanUsuarioTurista && this.beanInteres==beanInteres";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<TuristaInteres> listTuristaInteres= new ArrayList<TuristaInteres>();
			listBeans.add(beanUsuarioTurista);
			listBeans.add(beanInteres);												
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listTuristaInteres.addAll((List<TuristaInteres>)object);
			if(!listTuristaInteres.isEmpty()){
				return listTuristaInteres.get(0);
			}
		}catch(Exception ex){	
			System.out.print(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object getBeanByTurista_Interes(String codeTurista, String codeInteres) throws UnknownException {		
		Query query = pm.newQuery(TuristaInteres.class);
		String declareParameters="String paramCodeTurista, String paramCodeInteres";
		String filter="codeTurista==paramCodeTurista && codeInteres==paramCodeInteres";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<TuristaInteres> listTuristaInteres= new ArrayList<TuristaInteres>();
			listBeans.add(codeTurista);
			listBeans.add(codeInteres);												
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listTuristaInteres.addAll((List<TuristaInteres>)object);
			if(!listTuristaInteres.isEmpty()){
				return listTuristaInteres.get(0);
			}
		}catch(Exception ex){	
			System.out.print(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<TuristaInteres> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TuristaInteres> listaLocalidad = (Collection<TuristaInteres>) query
				.getListaBean(TuristaInteres.class);
		return listaLocalidad;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<TuristaInteres> getListarIntereses(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(TuristaInteres.class);
		String declareParameters="String paramCorreoTurista,Boolean paramIsPersistente";
		String filter="codeTurista==paramCorreoTurista && isPersistente==paramIsPersistente";
		String ordering="valorModa desc";		
		try{
		Set<TuristaInteres> lista=new HashSet<TuristaInteres>();
		Querys consulta=new Querys();
		List<Object> beanObject=new ArrayList();
		beanObject.add(correoTurista);	
		beanObject.add(true);
		query.setResult("idTuristaInteres,codeTuristaInteres,nombreInteres,valorModa,isPersistente");
		String declareParamecters="String paramCorreoTurista";			
		query.declareParameters(declareParamecters);			
		InMemoryQueryResult evaluator= (InMemoryQueryResult)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query);
		Iterator<Object> resultIterator= (Iterator<Object>)evaluator.iterator();
		Object[] elements=null;
		List<Object[]> listObject= new ArrayList<Object[]>();
		if(resultIterator.hasNext()){
			listObject.add((Object[]) resultIterator.next());
		}				
		evaluator.close();		
		Iterator<Object[]> listObjectIterator=listObject.iterator();
		while(listObjectIterator.hasNext()){
			elements=listObjectIterator.next();
			TuristaInteres beanTuristaInteres= new TuristaInteres();
			beanTuristaInteres.setIdTuristaInteres(elements[0].toString());
			beanTuristaInteres.setCodeTuristaInteres(elements[1].toString());
			beanTuristaInteres.setNombreInteres(elements[2].toString());
			beanTuristaInteres.setValorModa(Double.parseDouble(elements[3].toString()));
			beanTuristaInteres.setIsPersistente(Boolean.parseBoolean(elements[4].toString()));					
			lista.add(beanTuristaInteres);
		}
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked"})
	public Collection<TuristaInteres> getListarBean(String codeUsuarioTurista,boolean order) throws UnknownException {	    
		Query query = pm.newQuery(TuristaInteres.class);
		String declareParameters="Integer paramCodeUsuarioTurista";
		String filter="hashCodeTurista==paramCodeUsuarioTurista";
		String ordering="valorModa desc";			
		try{			
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			if(order){
				query.setOrdering(ordering);
			}			
			List<TuristaInteres>listTuristaInteres=new ArrayList<TuristaInteres>();
			listTuristaInteres.addAll((List<TuristaInteres>)query.execute(Integer.parseInt(codeUsuarioTurista)));			
			if(listTuristaInteres.size()>0){							
				return listTuristaInteres;
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}
	
	@SuppressWarnings({ "unchecked"})
	public List<TuristaInteres> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreColonia,String nacionalidad) throws UnknownException {	    
		Query query = pm.newQuery(TuristaInteres.class);
		String declareParameters="";
		String filter="";	
		List<String> declareParametersList= new ArrayList<String>();
		List<String> filterList= new ArrayList<String>();
		List<Object>listBeans=new ArrayList<Object>();
		if(annio!=null){			
			declareParametersList.add("Integer paramAnnio");
			filterList.add("annio==paramAnnio");
			listBeans.add(annio);
		}
		if(mes!=null){
			declareParametersList.add("Integer paramMes");
			filterList.add("mes==paramMes");
			listBeans.add(mes);
		}
		if(dia!=null){
			declareParametersList.add("Integer paramDia");
			filterList.add("dia==paramDia");
			listBeans.add(dia);
		}
		if(edad!=null){			
			declareParametersList.add("Integer paramEdad");
			filterList.add("edad==paramEdad");
			listBeans.add(edad);
		}
		if(nombreColonia!=null){			
			declareParametersList.add("String paramNombreColonia");
			filterList.add("nombreColonia==paramNombreColonia");
			listBeans.add(nombreColonia);
		}
		if(nacionalidad!=null){			
			declareParametersList.add("String paramNacionalidad");
			filterList.add("nacionalidad==paramNacionalidad");
			listBeans.add(nacionalidad);
		}	
		if(declareParametersList.isEmpty()){
			declareParameters=null;
			filter=null;
			listBeans=null;			
		}
		for (int i = 0; i < declareParametersList.size(); i++) {         
			declareParameters+=declareParametersList.get(i);
			filter+=filterList.get(i);
			if(i<(declareParametersList.size()-1)){
				declareParameters+=",";
				filter+="&&";
			}
        }		
		try{
			Querys consulta=new Querys();
			List<TuristaInteres> listTuristaInteres=new ArrayList<TuristaInteres>();
			listTuristaInteres.addAll(((List<TuristaInteres>)consulta.sendQuery(null, declareParameters, filter,null, listBeans, query)));			
			return listTuristaInteres;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}
}
