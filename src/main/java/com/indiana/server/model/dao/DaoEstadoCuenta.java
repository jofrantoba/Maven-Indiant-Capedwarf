package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoEstadoCuenta {
	private PersistenceManager pm;

	public DaoEstadoCuenta(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	@SuppressWarnings("unchecked")
	public Collection<EstadoCuenta> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EstadoCuenta> listaEstado = (Collection<EstadoCuenta>) query
				.getListaBean(EstadoCuenta.class);
		return listaEstado;
	}
	
	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(EstadoCuenta.class, id);
	}
	
	@SuppressWarnings({ "unchecked"})
	public EstadoCuenta getBeanByCode(String codeEstadoCuenta) throws UnknownException {	    
		Query query = pm.newQuery(EstadoCuenta.class);
		String declareParameters="String codeEstadoCuenta";
		String filter="this.codeEstadoCuenta==codeEstadoCuenta";		
		try{			
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<EstadoCuenta>listEstadoCuenta=new ArrayList<EstadoCuenta>();
			listEstadoCuenta.addAll((List<EstadoCuenta>)query.execute(codeEstadoCuenta));			
			if(listEstadoCuenta.size()>0){							
				return listEstadoCuenta.get(0);
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}

}