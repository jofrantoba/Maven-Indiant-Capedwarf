package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Interes;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoInteres {
	private PersistenceManager pm;

	public DaoInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public boolean mantenimiento(List<BeanParametro> listBeanParametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(listBeanParametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Interes.class, id);
	}	
	
	@SuppressWarnings("unchecked")
	public Object getBean(Interes beanInteres) throws UnknownException {
		Query query = pm.newQuery(Interes.class);
		try{
			if(beanInteres.getIdInteres()!=null){
				return getBean(beanInteres.getIdInteres());
			}
			if(beanInteres.getNombre()!=null){
				String filter="this.nombre=='"+beanInteres.getNombre()+"'";
				Querys consulta=new Querys();		
				List<Interes>listaInteres= new ArrayList<Interes>();
				listaInteres.addAll((List<Interes>)consulta.sendQuery(1, null, filter, null,null, query));
				if(!listaInteres.isEmpty()){
					return listaInteres.get(0);
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
	public Collection<Interes> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Interes> listIntereses = (Collection<Interes>) query
				.getListaBean(Interes.class);
		return listIntereses;
	}
}
