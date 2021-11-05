package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.dao.DaoMiembroInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicMiembroInteres {
	private PersistenceManager pm;

	public LogicMiembroInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Object getBean(MiembroInteres beanMiembroInteres) throws UnknownException {
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		return dao.getBean(beanMiembroInteres);
	}
	
	public Object getBeanByMiembro_Interes(String codeMiembro, String codeInteres) throws UnknownException {
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		return dao.getBeanByMiembro_Interes(codeMiembro, codeInteres);
	}

	public Collection<MiembroInteres> getListarBean() throws UnknownException {
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		Collection<MiembroInteres> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<MiembroInteres> getListarBean(Integer limiteMostrar,Miembro beanMiembro) throws UnknownException {
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		Collection<MiembroInteres> lista = dao.getListarBean(limiteMostrar, beanMiembro);
		return lista;
	}
	
	public List<MiembroInteres> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreColonia,String nacionalidad) throws UnknownException {
		DaoMiembroInteres dao = new DaoMiembroInteres(this.pm);
		return dao.getListarBean(annio, mes, dia, edad, nombreColonia, nacionalidad);		
	}
}
