package com.indiana.server.model.dao;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.query.evaluator.InMemoryQueryResult;

import com.indiana.server.model.bean.ControlPosicion;
import com.indiana.server.model.bean.SesionTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoSesionTurista {
	private PersistenceManager pm;

	public DaoSesionTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public SesionTurista mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (SesionTurista)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SesionTurista.class, id);
	}

	@SuppressWarnings({ "unchecked"})
	public ControlPosicion getBeanByMiembro(String correoTurista,String estadoSesion) throws UnknownException {	    
		Query query = pm.newQuery("SELECT codeSesionTurista,fechaInicioSession,fechaCierreSession,"
				+ "estadoSesion,correoTurista,codeAutenticaOauth,max(version) "
				+ "FROM com.indiana.server.model.bean.SesionTurista "
				+ "WHERE codeUsuarioTurista ==paramCorreoTurista && estadoSesion==paramEstadoSesion");
		
		try{		
			String declareParamecters="String paramCorreoTurista,String paramEstadoSesion";			
			query.declareParameters(declareParamecters);			
			InMemoryQueryResult evaluator= (InMemoryQueryResult)query.execute(correoTurista,estadoSesion);			
			Iterator<Object> resultIterator= (Iterator<Object>)evaluator.iterator();
			Object[] elements=null;
			if(resultIterator.hasNext()){
				elements=(Object[])resultIterator.next();
			}
			evaluator.close();
			if(elements!=null){
				SesionTurista beanSesionTurista= new SesionTurista();
				beanSesionTurista.setCodeSesionTurista(String.valueOf(elements[0]));
				beanSesionTurista.setFechaInicioSession(Date.valueOf(elements[1].toString()));
				if(elements[2]!=null){
					beanSesionTurista.setFechaInicioSession(Date.valueOf(elements[2].toString()));	
				}
				beanSesionTurista.setEstadoSesion(String.valueOf(elements[3]));
//				beanSesionTurista.
				
				ControlPosicion beanControlPosicion= new ControlPosicion();
				beanControlPosicion.setLatitud(Double.valueOf(elements[0].toString()));
				beanControlPosicion.setLongitud(Double.valueOf(elements[1].toString()));
				beanControlPosicion.setNombrePais(String.valueOf(elements[2]));
				beanControlPosicion.setNombreRegion(String.valueOf(elements[3]));
				beanControlPosicion.setNombreLocalidad(String.valueOf(elements[4]));							
				return beanControlPosicion;
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
	public Collection<SesionTurista> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SesionTurista> listaSesionTurista = (Collection<SesionTurista>) query
				.getListaBean(SesionTurista.class);
		return listaSesionTurista;
	}

}
