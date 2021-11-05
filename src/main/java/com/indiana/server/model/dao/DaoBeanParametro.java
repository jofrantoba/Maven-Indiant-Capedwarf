package com.indiana.server.model.dao;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoBeanParametro {
	private PersistenceManager pm;

	public DaoBeanParametro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(Collection<BeanParametro> parametros)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametros);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TuristaInteres.class, id);
	}	
	
}
