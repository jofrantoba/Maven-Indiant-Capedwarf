package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.CuentaTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoCuentaTurista {
	private PersistenceManager pm;

	public DaoCuentaTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CuentaTurista.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CuentaTurista> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CuentaTurista> lista = (Collection<CuentaTurista>) query
				.getListaBean(CuentaTurista.class);
		return lista;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CuentaTurista getBeanByEmail(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(CuentaTurista.class);		
		String filter="this.correoTurista=='"+correoTurista+"'";			
		try{
		List<CuentaTurista> lista=new ArrayList<CuentaTurista>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(correoTurista);
		lista.addAll((List<CuentaTurista>)consulta.sendQuery(1, null, filter, null, beanObject, query));
		if(!lista.isEmpty()){
			return lista.get(0);
		}else{
			throw new UnknownException("Cuenta no existe");
		}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}

}
