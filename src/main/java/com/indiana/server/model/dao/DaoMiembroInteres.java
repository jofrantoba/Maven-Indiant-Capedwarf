package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.logic.LogicInteres;
import com.indiana.server.model.logic.LogicMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoMiembroInteres {
	private PersistenceManager pm;

	public DaoMiembroInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(MiembroInteres.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MiembroInteres getBean(MiembroInteres beanMiembroInteres) throws UnknownException {
		LogicInteres logicInteres= new LogicInteres(pm);
		LogicMiembro logicMiembro= new LogicMiembro(pm);
		Interes beanInteres=(Interes)logicInteres.getBean(beanMiembroInteres.getBeanInteres().getIdInteres());
		Miembro beanMiembro=(Miembro)logicMiembro.getBean(beanMiembroInteres.getBeanMiembro().getIdMiembro());
		Query query = pm.newQuery(MiembroInteres.class);
		String declareParameters="Miembro beanMiembro,Interes beanInteres";
		String filter="this.beanMiembro==beanMiembro && this.beanInteres==beanInteres";		
		try{
			List<MiembroInteres> lista=new ArrayList<MiembroInteres>();
			Querys consulta=new Querys();
			List listBeans=new ArrayList();
			listBeans.add(beanMiembro);
			listBeans.add(beanInteres);
			lista.addAll((List<MiembroInteres>)consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			if(!lista.isEmpty()){
				return lista.get(0);
			}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MiembroInteres getBeanByMiembro_Interes(String codeMiembro, String codeInteres) throws UnknownException {		
		Query query = pm.newQuery(MiembroInteres.class);
		String declareParameters="String paramCodeMiembro, String paramCodeInteres";
		String filter="codeMiembro==paramCodeMiembro && codeInteres==paramCodeInteres";		
		try{
			List<MiembroInteres> lista=new ArrayList<MiembroInteres>();
			Querys consulta=new Querys();
			List listBeans=new ArrayList();
			listBeans.add(codeMiembro);
			listBeans.add(codeInteres);
			lista.addAll((List<MiembroInteres>)consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			if(!lista.isEmpty()){
				return lista.get(0);
			}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<MiembroInteres> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<MiembroInteres> listaLocalidad = (Collection<MiembroInteres>) query
				.getListaBean(MiembroInteres.class);
		return listaLocalidad;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MiembroInteres> getListarBean(Integer limiteMostrar,Miembro beanMiembro) throws UnknownException {
		Query query = pm.newQuery(MiembroInteres.class);
		String declareParameters="Miembro beanMiembro";
		String filter="this.beanMiembro==beanMiembro";		
		try{
			List<MiembroInteres> lista=new ArrayList<MiembroInteres>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(beanMiembro);
			lista.addAll((List<MiembroInteres>)consulta.sendQuery(limiteMostrar, declareParameters, filter, null, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MiembroInteres> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreColonia,String nacionalidad) throws UnknownException {
		Query query = pm.newQuery(MiembroInteres.class);
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
			List<MiembroInteres> listMiembroInteres=new ArrayList<MiembroInteres>();
			listMiembroInteres.addAll(((List<MiembroInteres>)consulta.sendQuery(null, declareParameters, filter,null, listBeans, query)));			
			return listMiembroInteres;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
