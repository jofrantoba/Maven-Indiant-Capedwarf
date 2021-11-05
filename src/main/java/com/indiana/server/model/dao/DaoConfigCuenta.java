package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.ConfigCuenta;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoConfigCuenta {
	private PersistenceManager pm;

	public DaoConfigCuenta(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public ConfigCuenta mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (ConfigCuenta)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ConfigCuenta.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public ConfigCuenta getBeanByCodeTurista(String codeTurista) throws UnknownException {
		Query query = pm.newQuery(ConfigCuenta.class);		
		query.setFilter("isPersistente == paramIsPersistente && codeTurista==paramCodeTurista");				
		query.declareParameters("Boolean paramIsPersistente,String paramCodeTurista");		
		try{
		List<ConfigCuenta> lista=new ArrayList<ConfigCuenta>();
		lista.addAll((List<ConfigCuenta>)query.execute(true,codeTurista));
		if(lista.size()==1){
			return lista.get(0);
		}else if(lista.size()==0){
			throw new UnknownException("Configuracion no existe");
		}else{
			throw new UnknownException("Hay mas de una configuracion de cuenta para un usuario");
		}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<ConfigCuenta> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ConfigCuenta> lista = (Collection<ConfigCuenta>) query
				.getListaBean(ConfigCuenta.class);
		return lista;
	}
}
