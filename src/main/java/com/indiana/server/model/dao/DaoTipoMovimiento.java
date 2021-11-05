package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoMovimiento {
	private PersistenceManager pm;

	public DaoTipoMovimiento(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public TipoMovimiento getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (TipoMovimiento)query.getBean(TipoMovimiento.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Object getBean(String codeBeanTipoMovimiento,Boolean code) throws UnknownException {
		Query query = pm.newQuery(TipoMovimiento.class);
		try{		
			Querys consulta=new Querys();			
			String filter="this.codeTipoMovimiento=='".concat(codeBeanTipoMovimiento).concat("'");		
			List<TipoMovimiento> listTipoMovimiento= new ArrayList<TipoMovimiento>();
			listTipoMovimiento.addAll(((List<TipoMovimiento>)consulta.sendQuery(1, null, filter, null, null, query)));						
			if(listTipoMovimiento.size()>0){
				return listTipoMovimiento.get(0);
			}
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<TipoMovimiento> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoMovimiento> lista = (Collection<TipoMovimiento>) query
				.getListaBean(TipoMovimiento.class);
		return lista;
	}

}
