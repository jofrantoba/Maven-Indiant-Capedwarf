package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.logic.LogicColonia;
import com.indiana.server.model.logic.LogicInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoColoniaInteres {
	private PersistenceManager pm;

	public DaoColoniaInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ColoniaInteres.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public ColoniaInteres getBean(ColoniaInteres beanColoniaInteres) throws UnknownException {
		LogicColonia logicColonia= new LogicColonia(pm);
		LogicInteres logicInteres= new LogicInteres(pm);
		Colonia beanColonia=(Colonia)logicColonia.getBean(beanColoniaInteres.getBeanColonia().getIdColonia());
		Interes beanInteres =(Interes)logicInteres.getBean(beanColoniaInteres.getBeanInteres());
		Query query = pm.newQuery(ColoniaInteres.class);
		String declareParameters="Colonia beanColonia, Interes beanInteres";
		String filter="this.beanColonia==beanColonia && this.beanInteres==beanInteres";		
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			listBeans.add(beanColonia);		
			listBeans.add(beanInteres);
			List<ColoniaInteres>listColoniaInteres=((List<ColoniaInteres>)consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			if(!listColoniaInteres.isEmpty()){
				return listColoniaInteres.get(0);
			}	
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
		return null;
	}	

	@SuppressWarnings("unchecked")
	public ColoniaInteres getBeanByColonia_Interes(String codeColonia, String codeInteres) throws UnknownException {		
		Query query = pm.newQuery(ColoniaInteres.class);
		String declareParameters="String paramCodeColonia, String paramCodeInteres";
		String filter="codeColonia==paramCodeColonia && codeInteres==paramCodeInteres";		
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			listBeans.add(codeColonia);		
			listBeans.add(codeInteres);
			List<ColoniaInteres>listColoniaInteres=((List<ColoniaInteres>)consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			if(!listColoniaInteres.isEmpty()){
				return listColoniaInteres.get(0);
			}	
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public Collection<ColoniaInteres> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ColoniaInteres> listaLocalidad = (Collection<ColoniaInteres>) query
				.getListaBean(ColoniaInteres.class);
		return listaLocalidad;
	}
	
	@SuppressWarnings("unchecked")
	public List<ColoniaInteres> getListarBean(String codeColonia,boolean order) throws UnknownException {
		Query query = pm.newQuery(ColoniaInteres.class);
		String declareParameters="String paramCodeColonia";
		String filter="codeColonia==paramCodeColonia";
		String ordering="valorModaColoniaInteres descending";			
		try{
			if(!order){
				ordering=null;
			}
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			listBeans.add(codeColonia);
			List<ColoniaInteres> listColoniaInteres=new ArrayList<ColoniaInteres>();
			listColoniaInteres.addAll(((List<ColoniaInteres>)consulta.sendQuery(null, declareParameters, filter, ordering, listBeans, query)));
			if(listColoniaInteres.isEmpty()){
				throw new UnknownException("No se encontraron intereses en la colonia");
			}
			return listColoniaInteres;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<ColoniaInteres> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreColonia,String nacionalidad) throws UnknownException {
		Query query = pm.newQuery(ColoniaInteres.class);
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
		String ordering="valorModaColoniaInteres descending";	
		try{
			Querys consulta=new Querys();
			List<ColoniaInteres> listColoniaInteres=new ArrayList<ColoniaInteres>();
			listColoniaInteres.addAll(((List<ColoniaInteres>)consulta.sendQuery(null, declareParameters, filter,ordering, listBeans, query)));			
			return listColoniaInteres;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}
}
