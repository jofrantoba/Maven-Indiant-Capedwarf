package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Tarifario;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTarifario {
	private PersistenceManager pm;

	public DaoTarifario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Tarifario.class, id);
	}
	
	public Long existeBean(String codeTipoSuscripcion,String codePais) throws UnknownException {
		Query query = pm.newQuery(Tarifario.class);
		query.setFilter("estado == paramEstado && isPersistente == paramIsPersistente && codeTipoSuscripcion==paramCodeTipoSuscripcion && codePais==paramCodePais");		
		query.declareParameters("String paramEstado,Boolean paramIsPersistente,String paramCodeTipoSuscripcion,String paramCodePais");		
		query.setResult("count(codeTarifario)");
		try {			
			Long count =(Long)query.executeWithArray("A",true,codeTipoSuscripcion,codePais);			
			return count;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Tarifario> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Tarifario> lista = (Collection<Tarifario>) query
				.getListaBean(Tarifario.class);
		return lista;
	}
	

}
