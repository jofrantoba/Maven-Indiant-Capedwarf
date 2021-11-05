package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.dao.DaoMuroNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicMuroNoticia {
	private PersistenceManager pm;

	public LogicMuroNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Object getBean(String id) throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		return dao.getBean(id);
	}

	public Collection<MuroNoticia> getListarBean() throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		Collection<MuroNoticia> lista = dao.getListarBean();
		return lista;
	}

	public List<MuroNoticia> getListarBean(Integer limiteInferior, Integer limiteSuperior, String correoUsuarioTurista) throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		List<MuroNoticia> lista = dao.getListarBean(limiteInferior, limiteSuperior, correoUsuarioTurista);
		return lista;
	}
	
	public List<MuroNoticia> getListarBeanByCorreoTuristaMuro(String correoTuristaMuro, Integer cantidadNoticias) throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		List<MuroNoticia> lista = dao.getListarBeanByCorreoTuristaMuro(correoTuristaMuro, cantidadNoticias);
		return lista;
	}
	
	public List<MuroNoticia> getListarByNotCorreoTurista(String correoTuristaMuro, Integer cantidadNoticias) throws UnknownException {
		DaoMuroNoticia dao = new DaoMuroNoticia(this.pm);
		List<MuroNoticia> lista = dao.getListarByNotCorreoTurista(correoTuristaMuro, cantidadNoticias);
		return lista;
	}
}
