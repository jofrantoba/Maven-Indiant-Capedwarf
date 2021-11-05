package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Empatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEmpatia {
	private PersistenceManager pm;

	public DaoEmpatia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Empatia mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Empatia)query.mantenimientoReturn(parametro);
	}

	public Empatia getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Empatia)query.getBean(Empatia.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Empatia getBeanByCode(String codeEmpatia) throws UnknownException {
		Query query = pm.newQuery(Empatia.class);
		String declareParameters="String paramCodeEmpatia, String paramEstadoEmpatia";		
		String filter= "codeEmpatia==paramCodeEmpatia && estado==paramEstadoEmpatia";		
		try{
			List<Empatia> lista=new ArrayList<Empatia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeEmpatia);		
			beanObject.add("P");
			lista.addAll((List<Empatia>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Empatia getBeanByCodeTuristas(String correoTurista1,String correoTurista2) throws UnknownException {
		Query query = pm.newQuery(Empatia.class);
		String declareParameters="String paramCorreoTurista1,String paramCorreoTurista2, String paramEstadoEmpatia";		
		String filter= "codeTuristaUno==paramCorreoTurista1 && codeTuristaDos==paramCorreoTurista2 && estado==paramEstadoEmpatia";		
		try{
			List<Empatia> lista=new ArrayList<Empatia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(correoTurista1);
			beanObject.add(correoTurista2);
			beanObject.add("P");
			lista.addAll((List<Empatia>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	public Collection<Empatia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Empatia> listaEmpatia = (Collection<Empatia>) query
				.getListaBean(Empatia.class);
		return listaEmpatia;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Empatia> getListarBeanByEmail(String correoTurista,Long rangoFecha) throws UnknownException {
		Query query = pm.newQuery(Empatia.class);
		String declareParameters="String paramCorreoTurista,Long paramRangoFecha";
		String filter="codeTuristaUno==paramCorreoTurista && version>= paramRangoFecha";		
		try{
		List<Empatia> lista=new ArrayList<Empatia>();
		Querys consulta=new Querys();
		List<Object> beanObject=new ArrayList();
		beanObject.add(correoTurista);
		beanObject.add(rangoFecha);
		lista.addAll((List<Empatia>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));		
		filter="codeTuristaDos==paramCorreoTurista && version>= paramRangoFecha";
		lista.addAll((List<Empatia>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
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
