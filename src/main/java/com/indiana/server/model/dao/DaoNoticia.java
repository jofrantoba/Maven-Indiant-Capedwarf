package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.process.GestionShared;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoNoticia {
	private PersistenceManager pm;

	public DaoNoticia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Noticia mantenimientoReturn(BeanParametro parametro) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Noticia) query.mantenimientoReturn(parametro);
	}

	public Noticia getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Noticia) query.getBean(Noticia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Noticia getBeanByCode(String codeNoticia) throws UnknownException {
                String idNoticia=GestionShared.keyStringBean(Noticia.class.getSimpleName(), codeNoticia);
		Query query = pm.newQuery(Noticia.class);
		String declareParameters = "String paramIdNoticia";
		String filter = "idNoticia==paramIdNoticia";
		try {
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<Noticia> listNoticias = new ArrayList<Noticia>();
			listNoticias.addAll((List<Noticia>) (query.execute(idNoticia)));
			if (!listNoticias.isEmpty()) {
				return listNoticias.get(0);
			}
			return null;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Noticia> getListarBeanByTurista(Integer limiteInferior, Integer limiteSuperior, String codeTurista,
			String codeTipoNoticia) throws UnknownException {
		Query query = pm.newQuery(Noticia.class);
		String declareParameters = "String paramCodeTurista,String paramTipoNoticia";
		String filter = "correoTuristaGeneraNoticia==paramCodeTurista && tipoNoticia==paramTipoNoticia";
		String ordering = "fechaPublicacion desc";
		try {
			List<Noticia> lista = new ArrayList<Noticia>();
			Querys consulta = new Querys();
			List<Object> beanObject = new ArrayList<Object>();
			beanObject.add(codeTurista);
			beanObject.add(codeTipoNoticia);// Tipo Conquista Destino
			query.setResult(
					"idNoticia,codeNoticia,tipoNoticia,fechaPublicacion,nombreColoniaNegocioDestino,fotoConquistaPublicidad,enlace");
			if (limiteInferior != null && limiteSuperior != null) {
				if (limiteInferior <= limiteSuperior) {
					query.setRange(limiteInferior, limiteSuperior);
				} else {
					throw new UnknownException("Rango No Definido");
				}
			}
			List evaluator = ((List) consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			List<Object[]> listObject = new ArrayList<Object[]>();
			while (resultIterator.hasNext()) {
				listObject.add((Object[]) resultIterator.next());
			}
			Iterator<Object[]> listObjectIterator = listObject.iterator();
			while (listObjectIterator.hasNext()) {
				elements = listObjectIterator.next();
				Noticia beanNoticia = new Noticia();
				if (elements[0] != null) {
					beanNoticia.setIdNoticia(elements[0].toString());
				}
				if (elements[1] != null) {
					beanNoticia.setCodeNoticia((elements[1].toString()));
				}
				if (elements[2] != null) {
					beanNoticia.setTipoNoticia(elements[2].toString());
				}
				if (elements[4] != null) {
					beanNoticia.setNombreColoniaNegocioDestino(elements[4].toString());
				}
				if (elements[5] != null) {
					beanNoticia.setFotoConquistaPublicidad(elements[5].toString());
				}
				if (elements[6] != null) {
					beanNoticia.setEnlace(elements[6].toString());
				}
				lista.add(beanNoticia);
			}
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Noticia> getListarBeanByTuristaCompleto(Integer limiteInferior, Integer limiteSuperior,
			String codeTurista, String codeTipoNoticia) throws UnknownException {
		Query query = pm.newQuery(Noticia.class);
		String declareParameters = "String paramCodeTurista,String paramTipoNoticia";
		String filter = "correoTuristaGeneraNoticia==paramCodeTurista && tipoNoticia==paramTipoNoticia";
		String ordering = "fechaPublicacion desc";
		try {
			List<Noticia> lista = new ArrayList<Noticia>();
			Querys consulta = new Querys();
			List<Object> beanObject = new ArrayList<Object>();
			beanObject.add(codeTurista);
			beanObject.add(codeTipoNoticia);
			query.setResult("idNoticia,codeNoticia,tipoNoticia,fechaPublicacion,"
					+ "nombreColoniaNegocioDestino,fotoConquistaPublicidad,enlace,"
					+ "totalComentario,totalCompartido,totalDivulgado,"
					+ "descripcion,version,hiperLink,codeColonia,codeDestinoConquistado,fotoPerfilTuristaNegocioGeneraNoticia");
			if (limiteInferior != null && limiteSuperior != null) {
				if (limiteInferior <= limiteSuperior) {
					query.setRange(limiteInferior, limiteSuperior);
				} else {
					throw new UnknownException("Rango No Definido");
				}
			}
			List evaluator = ((List) consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			List<Object[]> listObject = new ArrayList<Object[]>();
			while (resultIterator.hasNext()) {
				listObject.add((Object[]) resultIterator.next());
			}
			Iterator<Object[]> listObjectIterator = listObject.iterator();
			// SimpleDateFormat beanSimpleDateFormat = new
			// SimpleDateFormat("YYYY-MM-dd");
			while (listObjectIterator.hasNext()) {
				elements = listObjectIterator.next();
				Noticia beanNoticia = new Noticia();
				if (elements[0] != null) {
					beanNoticia.setIdNoticia(elements[0].toString());
				}
				if (elements[1] != null) {
					beanNoticia.setCodeNoticia((elements[1].toString()));
				}
				if (elements[2] != null) {
					beanNoticia.setTipoNoticia(elements[2].toString());
				}
				if (elements[4] != null) {
					beanNoticia.setNombreColoniaNegocioDestino(elements[4].toString());
				}
				if (elements[5] != null) {
					beanNoticia.setFotoConquistaPublicidad(elements[5].toString());
				}
				if (elements[6] != null) {
					beanNoticia.setEnlace(elements[6].toString());
				}
				if (elements[7] != null) {
					beanNoticia.setTotalComentario(Integer.parseInt((elements[7].toString())));
				}
				if (elements[8] != null) {
					beanNoticia.setTotalCompartido(Integer.parseInt((elements[8].toString())));
				}
				if (elements[9] != null) {
					beanNoticia.setTotalDivulgado(Integer.parseInt((elements[9].toString())));
				}
				if (elements[10] != null) {
					beanNoticia.setDescripcion((elements[10].toString()));
				}
				if (elements[11] != null) {
					beanNoticia.setVersion(Long.parseLong((elements[11].toString())));
				}
				if (elements[12] != null) {
					beanNoticia.setHiperLink((elements[12].toString()));
				}
				if (elements[13] != null) {
					beanNoticia.setCodeColonia((elements[13].toString()));
				}
				if (elements[14] != null) {
					beanNoticia.setCodeDestinoConquistado((elements[14].toString()));
				}
				if (elements[15] != null) {
					beanNoticia.setFotoPerfilTuristaNegocioGeneraNoticia((elements[15].toString()));
				}
				// beanNoticia.setFechaPublicacion(new
				// Date(beanSimpleDateFormat.format(beanNoticia.getVersion())));
				beanNoticia.setFechaPublicacion(new Date(beanNoticia.getVersion()));
				lista.add(beanNoticia);
			}
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings({ "unchecked" })
	public List<Noticia> getListarBeanByTurista(Integer limiteInferior, Integer limiteSuperior, String codeTurista)
			throws UnknownException {
		Query query = pm.newQuery(Noticia.class);
		String declareParameters = "String paramCodeTurista";
		String filter = "correoTuristaGeneraNoticia==paramCodeTurista";
		String ordering = "fechaPublicacion desc";
		try {
			List<Noticia> lista = new ArrayList<Noticia>();
			Querys consulta = new Querys();
			if (limiteInferior != null && limiteSuperior != null) {
				if (limiteInferior <= limiteSuperior) {
					query.setRange(limiteInferior, limiteSuperior);
				} else {
					throw new UnknownException("Rango No Definido");
				}
			}
			List<Object> beanObject = new ArrayList<Object>();
			beanObject.add(codeTurista);
			lista.addAll(
					(List<Noticia>) consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Noticia> getListarBeanByRangoFecha(String codeTuristaGeneraNotificacion, Long rangueFecha)
			throws UnknownException {
		Query query = pm.newQuery(Noticia.class);
		String declareParameters = "String paramCodeTurista,Long paramRangueFecha";
		String filter = "correoTuristaGeneraNoticia==paramCodeTurista && version>=paramRangueFecha";
		try {
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<Noticia> listNoticias = new ArrayList<Noticia>();
			List<Object> listBeans = new ArrayList<Object>();
			listBeans.add(codeTuristaGeneraNotificacion);
			listBeans.add(rangueFecha);
			listNoticias.addAll((List<Noticia>) (query.executeWithArray(listBeans.toArray())));
			return listNoticias;

		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Noticia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Noticia> listNoticias = (Collection<Noticia>) query.getListaBean(Noticia.class);
		return listNoticias;
	}

	@SuppressWarnings({ "unchecked" })
	public Collection<Noticia> getListarBeanByDestino(String codeDestino, String tipoNoticia) throws UnknownException {
		Query query = pm.newQuery(Noticia.class);
		String declareParameters = "String paramCodeDestino,String paramTipoNoticia";
		String filter = "codeDestinoConquistado==paramCodeDestino && tipoNoticia==paramTipoNoticia";
		try {
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<Noticia> listNoticias = new ArrayList<Noticia>();
			List<Object> listBeans = new ArrayList<Object>();
			listBeans.add(codeDestino);
			listBeans.add(tipoNoticia);
			listNoticias.addAll((List<Noticia>) (query.executeWithArray(listBeans.toArray())));
			if (!listNoticias.isEmpty()) {
				return listNoticias;
			}
			return null;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Noticia> getListarBeanByColonia(Integer limiteInferior, Integer limiteSuperior, String codeColonia)
			throws UnknownException {
		Query query = pm.newQuery(Noticia.class);
		String filter = "codeColonia == '" + codeColonia + "'";
		String ordering = "fechaPublicacion desc";
		try {
			query.setFilter(filter);
			query.setOrdering(ordering);
			if (limiteInferior != null && limiteSuperior != null) {
				if (limiteInferior <= limiteSuperior) {
					query.setRange(limiteInferior, limiteSuperior);
				} else {
					throw new UnknownException("Rango No Definido");
				}
			}
			List<Noticia> listMuroNoticia = new ArrayList<Noticia>();
			Object object = query.execute();
			if (object == null) {
				throw new UnknownException("Error en la Consulta!");
			}
			listMuroNoticia.addAll((List<Noticia>) object);
			return listMuroNoticia;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

}
