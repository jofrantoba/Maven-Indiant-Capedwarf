package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Conquista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoConquista {
	private PersistenceManager pm;

	public DaoConquista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Conquista mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Conquista)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Conquista.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Conquista getBean(String correoUsuarioTurista,String codeDestino) throws UnknownException {
		Query query = pm.newQuery(Conquista.class);
		String declareParameters="String paramCorreoTurista,String paramCodeDestino";		
		String filter= "codeTuristaConquista==paramCorreoTurista && codeDestino==paramCodeDestino";		
		try{
			List<Conquista> lista=new ArrayList<Conquista>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(correoUsuarioTurista);
			beanObject.add(codeDestino);
			lista.addAll((List<Conquista>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	public Collection<Conquista> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Conquista> listConquistas = (Collection<Conquista>) query
				.getListaBean(Conquista.class);
		return listConquistas;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Conquista> getListarBeanByDestino(String correoTurista,String codeDestino) throws UnknownException {
		Query query = pm.newQuery(Conquista.class);
		String declareParameters="String paramCorreoTurista,String paramCodeDestino";		
		String filter= "codeTuristaConquista!=paramCorreoTurista && codeDestino==paramCodeDestino";		
		try{
			List<Conquista> lista=new ArrayList<Conquista>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(correoTurista);
			beanObject.add(codeDestino);
			lista.addAll((List<Conquista>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
			if(!lista.isEmpty()){
				return lista;
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Conquista> getListarBeanByTurista(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(Conquista.class);
		String declareParameters="String paramCorreoTurista";		
		String filter= "codeTuristaConquista ==paramCorreoTurista";			
		try{
			List<Conquista> lista=new ArrayList<Conquista>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(correoTurista);			
			lista.addAll((List<Conquista>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
			if(!lista.isEmpty()){
				return lista;
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Conquista> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreDestino,String nacionalidad) throws UnknownException {
		Query query = pm.newQuery(Conquista.class);
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
		if(nombreDestino!=null){			
			declareParametersList.add("String paramNombreDestino");
			filterList.add("nombreDestinoNegocio==paramNombreDestino");
			listBeans.add(nombreDestino);
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
			List<Conquista> listColoniaInteres=new ArrayList<Conquista>();
			listColoniaInteres.addAll(((List<Conquista>)consulta.sendQuery(null, declareParameters, filter,null, listBeans, query)));			
			return listColoniaInteres;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}
}
