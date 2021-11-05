package com.indiana.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.dao.DaoNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicNoticia {
	private PersistenceManager pm;

	public LogicNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Noticia mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		return dao.mantenimientoReturn(parametro);
	}

	public Noticia getBean(String id) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		return dao.getBean(id);
	}
	
	public Noticia getBeanByCode(String codeNoticia) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		return dao.getBeanByCode(codeNoticia);
	}

	public Collection<Noticia> getListarBean() throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		Collection<Noticia> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Noticia> getListarBeanByRangoFecha(String codeTuristaGeneraNotificacion,Long rangueFecha) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		Collection<Noticia> lista = dao.getListarBeanByRangoFecha(codeTuristaGeneraNotificacion,rangueFecha);
		return lista;
	}
	
	public Collection<Noticia> getListarBeanByDestino(String codeDestino,String tipoNoticia) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		Collection<Noticia> lista = dao.getListarBeanByDestino(codeDestino, tipoNoticia);
		return lista;
	}
	/**
	 * Retorna la lista de Noticias del Tipo Destino y del Tipo Negocio. Metodo para cargar la galeria de Conquistas del Turista.
	 * @param limiteInferior
	 * @param limiteSuperior
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public List<Noticia> getListarBeanByTurista(Integer limiteInferior, Integer limiteSuperior, String correoTurista,String codeTipoNoticia) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		List<Noticia> lista = dao.getListarBeanByTurista(limiteInferior, limiteSuperior, correoTurista,codeTipoNoticia);
		return lista;
	}
	
	public List<Noticia> getListarBeanByTuristaCompleto(Integer limiteInferior, Integer limiteSuperior, String correoTurista,String codeTipoNoticia) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		List<Noticia> lista = dao.getListarBeanByTuristaCompleto(limiteInferior, limiteSuperior, correoTurista,codeTipoNoticia);
		return lista;
	}
	
	public List<Noticia> getListarBeanByTurista(Integer limiteInferior, Integer limiteSuperior, String correoTurista) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		List<Noticia> lista = dao.getListarBeanByTurista(limiteInferior, limiteSuperior, correoTurista);
		return lista;
	}
	public List<Noticia> getListarBeanByColonia(Integer limiteInferior, Integer limiteSuperior, String codeColonia) throws UnknownException {
		DaoNoticia dao = new DaoNoticia(this.pm);
		List<Noticia> lista = dao.getListarBeanByColonia(limiteInferior, limiteSuperior, codeColonia);
		return lista;
	}
}
