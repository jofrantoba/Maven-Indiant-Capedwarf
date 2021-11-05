package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEstadoAmistad {
	private PersistenceManager pm;

	public DaoEstadoAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(EstadoAmistad.class, id);
	}

	@SuppressWarnings("unchecked")
	public Object getBeanByCode(String codeEstadoAmistad) throws UnknownException {		
		Query query = pm.newQuery(EstadoAmistad.class);
		String declareParameters="String paramCodeEstadoAmistad";
		String filter="codeEstadoAmistad==paramCodeEstadoAmistad";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<EstadoAmistad> listSolicitudAmistad= new ArrayList<EstadoAmistad>();
			listBeans.add(codeEstadoAmistad);											
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listSolicitudAmistad.addAll((List<EstadoAmistad>)object);
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
	public Collection<EstadoAmistad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EstadoAmistad> lista = (Collection<EstadoAmistad>) query
				.getListaBean(EstadoAmistad.class);
		return lista;
	}

}
