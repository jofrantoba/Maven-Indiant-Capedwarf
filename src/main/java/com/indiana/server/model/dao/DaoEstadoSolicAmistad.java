package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.EstadoSolicAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEstadoSolicAmistad {
	private PersistenceManager pm;

	public DaoEstadoSolicAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(EstadoSolicAmistad.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Object getBeanByCode(String codeEstadoSolAmistad) throws UnknownException {		
		Query query = pm.newQuery(EstadoSolicAmistad.class);
		String declareParameters="String paramCodeEstadoSolicAmistad";
		String filter="codeEstadoSolicAmistad==paramCodeEstadoSolicAmistad";						
		try{
			Querys consulta=new Querys();
			List<Object>listBeans=new ArrayList<Object>();
			ArrayList<EstadoSolicAmistad> listEstadoAmistad= new ArrayList<EstadoSolicAmistad>();
			listBeans.add(codeEstadoSolAmistad);											
			Object object=(consulta.sendQuery(1, declareParameters, filter, null, listBeans, query));
			listEstadoAmistad.addAll((List<EstadoSolicAmistad>)object);
			if(!listEstadoAmistad.isEmpty()){
				return listEstadoAmistad.get(0);
			}
		}catch(Exception ex){				
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<EstadoSolicAmistad> getListarBean()
			throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EstadoSolicAmistad> lista = (Collection<EstadoSolicAmistad>) query
				.getListaBean(EstadoSolicAmistad.class);
		return lista;
	}

}
