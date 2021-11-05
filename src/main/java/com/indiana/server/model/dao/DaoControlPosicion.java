package com.indiana.server.model.dao;

import java.util.Collection;
import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.query.evaluator.InMemoryQueryResult;

import com.indiana.server.model.bean.ControlPosicion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoControlPosicion {
	private PersistenceManager pm;

	public DaoControlPosicion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ControlPosicion.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<ControlPosicion> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ControlPosicion> lista = (Collection<ControlPosicion>) query.getListaBean(ControlPosicion.class);
		return lista;
	}

	@SuppressWarnings({ "unchecked" })
	public ControlPosicion getBeanByTurista(String correoTurista) throws UnknownException {
		Query query = pm.newQuery("SELECT latitud,longitud,nombrePais,nombreRegion,nombreLocalidad,max(version) "
				+ "FROM com.indiana.server.model.bean.ControlPosicion "
				+ "WHERE codeUsuarioTurista ==paramCorreoTurista");

		try {
			String declareParamecters = "String paramCorreoTurista";
			query.declareParameters(declareParamecters);
			InMemoryQueryResult evaluator = (InMemoryQueryResult) query.execute(correoTurista);
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			if (resultIterator.hasNext()) {
				elements = (Object[]) resultIterator.next();
			}
			evaluator.close();		
				ControlPosicion beanControlPosicion = new ControlPosicion();
				if(elements[0]==null || elements[1] == null){
					return null;
				}
				if (elements[0] != null) {
					beanControlPosicion.setLatitud(Double.valueOf(elements[0].toString()));
				}
				if (elements[1] != null) {
					beanControlPosicion.setLongitud(Double.valueOf(elements[1].toString()));
				}
				if (elements[2] != null) {
					beanControlPosicion.setNombrePais(String.valueOf(elements[2]));
				}
				if (elements[3] != null) {
					beanControlPosicion.setNombreRegion(String.valueOf(elements[3]));
				}
				if (elements[4] != null) {
					beanControlPosicion.setNombreLocalidad(String.valueOf(elements[4]));
				}
				return beanControlPosicion;
			
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
