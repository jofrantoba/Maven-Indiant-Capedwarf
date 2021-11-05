package com.indiana.server.model.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.indiana.server.model.bean.VisitaPerfilNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoVisitaPerfilNegocio implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private PersistenceManager pm;

	public DaoVisitaPerfilNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(VisitaPerfilNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<VisitaPerfilNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<VisitaPerfilNegocio> lista = (Collection<VisitaPerfilNegocio>) query
				.getListaBean(VisitaPerfilNegocio.class);
		return lista;
	}
}
