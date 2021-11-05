package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.TablonColonia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoTablonColonia {
	private PersistenceManager pm;

	public DaoTablonColonia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TablonColonia.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TablonColonia getBeanByCode(String codeTablonColonia) throws UnknownException {
		Query query = pm.newQuery(TablonColonia.class);
		String declareParameters="String paramCodeTablonColonia,String paramEstadoTablon";		
		String filter= "codeTablonColonia==paramCodeTablonColonia && estadoTablon==paramEstadoTablon";		
		try{
			List<TablonColonia> lista=new ArrayList<TablonColonia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();			
			beanObject.add(codeTablonColonia);
			beanObject.add("A");
			lista.addAll((List<TablonColonia>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
			if(!lista.isEmpty()){
				return lista.get(0);
			}
		return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	

	@SuppressWarnings("unchecked")
	public Collection<TablonColonia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TablonColonia> lista = (Collection<TablonColonia>) query
				.getListaBean(TablonColonia.class);
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TablonColonia> getListarBeanByColoniaRangoFecha(String codeColonia,String estadoTablon,Long rangoFecha) throws UnknownException {
		Query query = pm.newQuery(TablonColonia.class);
		String declareParameters = "String paramCodeColonia,String paramEstadoTablon,Long paramRangoFecha";
		String filter = "codeColonia==paramCodeColonia && estadoTablon==paramEstadoTablon && version>=paramRangoFecha";	
		String ordering = "version asc";
		try {
			List<TablonColonia> lista = new ArrayList<TablonColonia>();
			Querys consulta = new Querys();
			List<Object> beanObject = new ArrayList<Object>();
			beanObject.add(codeColonia);	
			beanObject.add(estadoTablon);
			beanObject.add(rangoFecha);	
			query.setResult("idTablonColonia,"
					+ "codeTablonColonia,"
					+ "mensaje,"
					+ "linkFotoMensaje,"
					+ "codeTuristaTablon,"
					+ "nombreTuristaTablon,"
					+ "apellidoTuristaTablon,"
					+ "fotoPerfilTuristaTablon,"
					+ "codeColonia,"
					+ "nombreColonia,"
					+ "fechaPublicacionTablon,"
					+ "codeMiembro,"
					+ "version,"
					+ "numeroParticipante,"
					+ "estadoTablon,"
					+ "isPersistente");
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
				TablonColonia beanTablonColonia = new TablonColonia();
				if(elements[0]==null || elements[1]==null){
					//No retorna datos, el id y el code son los atributos principales del bean..
					return new ArrayList<TablonColonia>();
				}
				if (elements[0] != null) {
					beanTablonColonia.setIdTablonColonia(elements[0].toString());
				}
				if (elements[1] != null) {
					beanTablonColonia.setCodeTablonColonia((elements[1].toString()));
				}
				if (elements[2] != null) {
					beanTablonColonia.setMensaje((elements[2].toString()));
				}
				if (elements[3] != null) {
					beanTablonColonia.setLinkFotoMensaje((elements[3].toString()));
				}
				if (elements[4] != null) {
					beanTablonColonia.setCodeTuristaTablon((elements[4].toString()));
				}
				if (elements[5] != null) {
					beanTablonColonia.setNombreTuristaTablon((elements[5].toString()));
				}
				if (elements[6] != null) {
					beanTablonColonia.setApellidoTuristaTablon((elements[6].toString()));
				}
				if (elements[7] != null) {
					beanTablonColonia.setFotoPerfilTuristaTablon((elements[7].toString()));
				}
				if (elements[8] != null) {
					beanTablonColonia.setCodeColonia((elements[8].toString()));
				}
				if (elements[9] != null) {
					beanTablonColonia.setNombreColonia((elements[9].toString()));
				}//La 10 es de la  fecha de Publicacion
				if (elements[11] != null) {
					beanTablonColonia.setCodeMiembro((elements[11].toString()));
				}
				if (elements[12] != null) {
					beanTablonColonia.setVersion(Long.parseLong((elements[12].toString())));
				}
				if (elements[13] != null) {
					beanTablonColonia.setNumeroParticipante(Integer.parseInt((elements[13].toString())));
				}
				if (elements[14] != null) {
					beanTablonColonia.setEstadoTablon((elements[14].toString()));
				}
				if (elements[15] != null) {
					beanTablonColonia.setIsPersistente(Boolean.parseBoolean(elements[15].toString()));
				}	
				beanTablonColonia.setFechaPublicacionTablon(new Date(beanTablonColonia.getVersion()));
				lista.add(beanTablonColonia);
			}
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TablonColonia> getListarBeanByColonia(String codeColonia,String estadoTablon,Integer limiteMostrar) throws UnknownException {
		Query query = pm.newQuery(TablonColonia.class);
		String declareParameters = "String paramCodeColonia,String paramEstadoTablon";
		String filter = "codeColonia==paramCodeColonia && estadoTablon==paramEstadoTablon";		
		String ordering = "version asc";
		try {
			List<TablonColonia> lista = new ArrayList<TablonColonia>();
			Querys consulta = new Querys();
			List<Object> beanObject = new ArrayList<Object>();
			beanObject.add(codeColonia);	
			beanObject.add(estadoTablon);			
			query.setResult("idTablonColonia,"
					+ "codeTablonColonia,"
					+ "mensaje,"
					+ "linkFotoMensaje,"
					+ "codeTuristaTablon,"
					+ "nombreTuristaTablon,"
					+ "apellidoTuristaTablon,"
					+ "fotoPerfilTuristaTablon,"
					+ "codeColonia,"
					+ "nombreColonia,"
					+ "fechaPublicacionTablon,"
					+ "codeMiembro,"
					+ "version,"
					+ "numeroParticipante,"
					+ "estadoTablon,"
					+ "isPersistente");
			List evaluator = ((List) consulta.sendQuery(limiteMostrar, declareParameters, filter, ordering, beanObject, query));
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			List<Object[]> listObject = new ArrayList<Object[]>();
			while (resultIterator.hasNext()) {
				listObject.add((Object[]) resultIterator.next());
			}
			Iterator<Object[]> listObjectIterator = listObject.iterator();
			while (listObjectIterator.hasNext()) {
				elements = listObjectIterator.next();
				TablonColonia beanTablonColonia = new TablonColonia();
				if(elements[0]==null || elements[1]==null){
					//No retorna datos, el id y el code son los atributos principales del bean..
					return new ArrayList<TablonColonia>();
				}
				if (elements[0] != null) {
					beanTablonColonia.setIdTablonColonia(elements[0].toString());
				}
				if (elements[1] != null) {
					beanTablonColonia.setCodeTablonColonia((elements[1].toString()));
				}
				if (elements[2] != null) {
					beanTablonColonia.setMensaje((elements[2].toString()));
				}
				if (elements[3] != null) {
					beanTablonColonia.setLinkFotoMensaje((elements[3].toString()));
				}
				if (elements[4] != null) {
					beanTablonColonia.setCodeTuristaTablon((elements[4].toString()));
				}
				if (elements[5] != null) {
					beanTablonColonia.setNombreTuristaTablon((elements[5].toString()));
				}
				if (elements[6] != null) {
					beanTablonColonia.setApellidoTuristaTablon((elements[6].toString()));
				}
				if (elements[7] != null) {
					beanTablonColonia.setFotoPerfilTuristaTablon((elements[7].toString()));
				}
				if (elements[8] != null) {
					beanTablonColonia.setCodeColonia((elements[8].toString()));
				}
				if (elements[9] != null) {
					beanTablonColonia.setNombreColonia((elements[9].toString()));
				}//La 10 es de la  fecha de Publicacion
				if (elements[11] != null) {
					beanTablonColonia.setCodeMiembro((elements[11].toString()));
				}
				if (elements[12] != null) {
					beanTablonColonia.setVersion(Long.parseLong((elements[12].toString())));
				}
				if (elements[13] != null) {
					beanTablonColonia.setNumeroParticipante(Integer.parseInt((elements[13].toString())));
				}
				if (elements[14] != null) {
					beanTablonColonia.setEstadoTablon((elements[14].toString()));
				}
				if (elements[15] != null) {
					beanTablonColonia.setIsPersistente(Boolean.parseBoolean(elements[15].toString()));
				}	
				beanTablonColonia.setFechaPublicacionTablon(new Date(beanTablonColonia.getVersion()));
				lista.add(beanTablonColonia);
			}
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
