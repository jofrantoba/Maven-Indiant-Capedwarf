package com.indiana.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoCambio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTipoCambio {
	private PersistenceManager pm;

	public DaoTipoCambio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoCambio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoCambio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoCambio> lista = (Collection<TipoCambio>) query
				.getListaBean(TipoCambio.class);
		return lista;
	}
	
	
	/*public List<TipoCambio> getListBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		List<Entity> lista = (List<Entity>) query
				.getListBean(TipoCambio.class);
		Iterator<Entity> iterador=lista.iterator();
		List<TipoCambio> array=new ArrayList<TipoCambio>();
		while(iterador.hasNext()){
			Entity entidad=iterador.next();
			TipoCambio obj=new TipoCambio();
			obj.setIdTipoCambio(String.valueOf(entidad.getProperty("idTipoCambio")));
			obj.setCodeTipoCambio(String.valueOf(entidad.getProperty("codeTipoCambio")));
			obj.setMonedaOrigen(String.valueOf(entidad.getProperty("monedaOrigen")));
			obj.setMonedaDestino(String.valueOf(entidad.getProperty("monedaDestino")));
			obj.setPrecioCompra((Double)entidad.getProperty("precioCompra"));
			obj.setPrecioVenta((Double)entidad.getProperty("precioVenta"));
			obj.setFecha((java.util.Date)entidad.getProperty("fecha"));
			array.add(obj);
		}
		return array;
	}*/

}
