package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.MensajeColoniaPrivado;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoMensajeColoniaPrivado {
	private PersistenceManager pm;

	public DaoMensajeColoniaPrivado(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(MensajeColoniaPrivado.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<MensajeColoniaPrivado> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<MensajeColoniaPrivado> lista = (Collection<MensajeColoniaPrivado>) query
				.getListaBean(MensajeColoniaPrivado.class);
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MensajeColoniaPrivado> getListarBeanByTablon(String codeTablonColonia) throws UnknownException {
		Query query = pm.newQuery(MensajeColoniaPrivado.class);
		String declareParameters="String paramCodeTablonColonia";		
		String filter= "codeTablonColonia ==paramCodeTablonColonia";
		String ordering = "version desc";
		try{
			List<MensajeColoniaPrivado> lista=new ArrayList<MensajeColoniaPrivado>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeTablonColonia);			
			lista.addAll((List<MensajeColoniaPrivado>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
