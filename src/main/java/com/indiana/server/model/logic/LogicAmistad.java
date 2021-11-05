package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Amistad;
import com.indiana.server.model.dao.DaoAmistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicAmistad{
	private PersistenceManager pm;

	public LogicAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public boolean mantenimiento(List<BeanParametro> parametro)
			throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		return dao.getBean(id);
	}
	public List<Amistad> getBeanByTuristaPrimeraSolicitud(String correoTurista) throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		return dao.getBeanByTuristaPrimeraSolicitud(correoTurista);
	}

	public Object verAmistad(String codeTuristaEnvia,String codeTuristaRecibe,String codeEstadoAmistad) throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		return dao.verAmistad(codeTuristaEnvia,codeTuristaRecibe,codeEstadoAmistad);
	}
	
	public Collection<Amistad> getListarBean() throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		Collection<Amistad> lista = dao.getListarBean();
		return lista;
	}
	/**
	 * Lista Amigos , la busqueda se realiza solamente por el turista principal, ya que cuando se crea una amistad se guarda de manera doble, asi siempre habra un turista principal y a la ves secundario.
	 * @param codeUsuarioTurista
	 * @return
	 * @throws UnknownException
	 */
	public Set<Amistad> getListarAmigos(String codeUsuarioTurista) throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		Set<Amistad> lista = dao.getListarAmigos(codeUsuarioTurista);
		return lista;
	}
        
        public Set<Amistad> getListarAmigosActivos(String codeUsuarioTurista) throws UnknownException {
		DaoAmistad dao = new DaoAmistad(this.pm);
		Set<Amistad> lista = dao.getListarAmigoActivo(codeUsuarioTurista);
		return lista;
	}
}