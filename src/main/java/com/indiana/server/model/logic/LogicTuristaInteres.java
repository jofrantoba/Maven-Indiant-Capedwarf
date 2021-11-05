package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.DaoTuristaInteres;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTuristaInteres{
	private PersistenceManager pm;

	public LogicTuristaInteres(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Object getBean(UsuarioTurista beanUsuarioTurista, Interes beanInteres) throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		return dao.getBean(beanUsuarioTurista, beanInteres);
	}
	public Object getBeanByTurista_Interes(String codeTurista, String codeInteres) throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		return dao.getBeanByTurista_Interes(codeTurista, codeInteres);
	}
	public TuristaInteres getBeanByCode(String codeTuristaInteres) throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		TuristaInteres beanTuristaInteres=dao.getBeanByCode(codeTuristaInteres);
		return beanTuristaInteres;
	}

	public Collection<TuristaInteres> getListarBean() throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		Collection<TuristaInteres> lista = dao.getListarBean();
		return lista;
	}
	/**
	 * Return the list of Interests que sean Persistent of a user tourist
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public Set<TuristaInteres> getListarIntereses(String correoTurista) throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		Set<TuristaInteres> lista = dao.getListarIntereses(correoTurista);
		return lista;
	}
	
	public Collection<TuristaInteres> getListarBean(String codeUsuarioTurista,boolean order) throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		Collection<TuristaInteres> lista = dao.getListarBean(codeUsuarioTurista, order);
		return lista;
	}
	public List<TuristaInteres> getListarBean(Integer annio,Integer mes,Integer dia,Integer edad,
			String nombreColonia,String nacionalidad) throws UnknownException {
		DaoTuristaInteres dao = new DaoTuristaInteres(this.pm);
		return dao.getListarBean(annio, mes, dia, edad, nombreColonia, nacionalidad);
		
	}
}