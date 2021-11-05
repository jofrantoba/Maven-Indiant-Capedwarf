package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Notificacion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.indiana.server.model.process.GestionShared;

public class DaoNotificacion {
	private PersistenceManager pm;

	public DaoNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Notificacion mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Notificacion)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
                String idNotificacion=GestionShared.keyStringBean(Notificacion.class.getSimpleName(), id);
		return query.getBean(Notificacion.class, idNotificacion);
	}
	
	@SuppressWarnings("unchecked")
	public Object getBeanByCode(String codeNotificacion) throws UnknownException {                
		String idNotificacion=GestionShared.keyStringBean(Notificacion.class.getSimpleName(), codeNotificacion);
		Query query = pm.newQuery(Notificacion.class);
		String declareParameters="String paramIdNotificacion";
		String filter="idNotificacion==paramIdNotificacion";						
		
		try{			
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<Notificacion>listNotificacion=new ArrayList<Notificacion>();
			listNotificacion.addAll((List<Notificacion>)(query.execute(idNotificacion)));			
			if(!listNotificacion.isEmpty()){							
				return listNotificacion.get(0);
			}			
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}

	@SuppressWarnings("unchecked")
	public Collection<Notificacion> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Notificacion> listaLocalidad = (Collection<Notificacion>) query
				.getListaBean(Notificacion.class);
		return listaLocalidad;
	}	
}
