package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.SolicitudAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoSolicitudAmistad {
	private PersistenceManager pm;

	public DaoSolicitudAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public boolean mantenimiento(List<BeanParametro> parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SolicitudAmistad.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Object getBeanByTurista(String codeTuristaEnvia,String codeTuristaRecibe) throws UnknownException {		
		Query query = pm.newQuery(SolicitudAmistad.class);
		String declareParameters="String paramCodeTuristaEnvia,String paramCodeTuristaRecibe,String paramCodeEstadoAmistad,String paramEstadoTarea";
		String filter="codeTuristaEnvia==paramCodeTuristaEnvia && codeTuristaRecibe==paramCodeTuristaRecibe && "
				+ "codeEstadoSolicAmistad==paramCodeEstadoAmistad && estadoTarea==paramEstadoTarea";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<SolicitudAmistad> listSolicitudAmistad= new ArrayList<SolicitudAmistad>();
			listBeans.add(codeTuristaEnvia);
			listBeans.add(codeTuristaRecibe);	
			listBeans.add("P");
			listBeans.add("P");	
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listSolicitudAmistad.addAll((List<SolicitudAmistad>)object);
			if(!listSolicitudAmistad.isEmpty()){
				return listSolicitudAmistad.get(0);
			}
		}catch(Exception ex){				
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object getBeanByTurista(String codeTuristaEnvia,String codeTuristaRecibe,String codeEstadoSolAmistad) throws UnknownException {		
		Query query = pm.newQuery(SolicitudAmistad.class);
		String declareParameters="String paramCodeTuristaEnvia,String paramCodeTuristaRecibe,String paramCodeEstadoSolAmistad";
		String filter="codeTuristaEnvia==paramCodeTuristaEnvia && codeTuristaRecibe==paramCodeTuristaRecibe && "
				+ "codeEstadoSolicAmistad==paramCodeEstadoSolAmistad";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<SolicitudAmistad> listSolicitudAmistad= new ArrayList<SolicitudAmistad>();
			listBeans.add(codeTuristaEnvia);
			listBeans.add(codeTuristaRecibe);	
			listBeans.add(codeEstadoSolAmistad);	
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listSolicitudAmistad.addAll((List<SolicitudAmistad>)object);
			if(!listSolicitudAmistad.isEmpty()){
				return listSolicitudAmistad.get(0);
			}else{
				listBeans=new ArrayList<Object>();
				listSolicitudAmistad= new ArrayList<SolicitudAmistad>();
				listBeans.add(codeTuristaRecibe);
				listBeans.add(codeTuristaEnvia);	
				listBeans.add(codeEstadoSolAmistad);	
				object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
				listSolicitudAmistad.addAll((List<SolicitudAmistad>)object);
				if(!listSolicitudAmistad.isEmpty()){
					return listSolicitudAmistad.get(0);
				}
			}
		}catch(Exception ex){				
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SolicitudAmistad> getListarBeanByTurista(String codeTuristaEnvia,String codeTuristaRecibe) throws UnknownException {		
		Query query = pm.newQuery(SolicitudAmistad.class);
		String declareParameters="String paramCodeTuristaEnvia,String paramCodeTuristaRecibe,String paramCodeEstadoAmistad,String paramEstadoTarea";
		String filter="codeTuristaEnvia==paramCodeTuristaEnvia && codeTuristaRecibe==paramCodeTuristaRecibe && "
				+ "codeEstadoSolicAmistad==paramCodeEstadoAmistad && estadoTarea==paramEstadoTarea";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<SolicitudAmistad> listSolicitudAmistad= new ArrayList<SolicitudAmistad>();
			listBeans.add(codeTuristaEnvia);
			listBeans.add(codeTuristaRecibe);	
			listBeans.add("P");
			listBeans.add("P");
			Object object=(consulta.sendQuery(null, declareParameters, filter, null, listBeans, query));
			listSolicitudAmistad.addAll((List<SolicitudAmistad>)object);
			return listSolicitudAmistad;
		}catch(Exception ex){				
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Object getBeanByCode(String codeSolicitudAmistad) throws UnknownException {		
		Query query = pm.newQuery(SolicitudAmistad.class);
		String declareParameters="String paramCodeSolicitudAmistad";
		String filter="codeSolicitudAmistad==paramCodeSolicitudAmistad";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<SolicitudAmistad> listSolicitudAmistad= new ArrayList<SolicitudAmistad>();
			listBeans.add(codeSolicitudAmistad);											
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listSolicitudAmistad.addAll((List<SolicitudAmistad>)object);
			if(!listSolicitudAmistad.isEmpty()){
				return listSolicitudAmistad.get(0);
			}
		}catch(Exception ex){				
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<SolicitudAmistad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SolicitudAmistad> listaLocalidad = (Collection<SolicitudAmistad>) query
				.getListaBean(SolicitudAmistad.class);
		return listaLocalidad;
	}
}
