package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.NotificacionTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoNotificacionTurista {
	private PersistenceManager pm;

	public DaoNotificacionTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}	
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public NotificacionTurista mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (NotificacionTurista)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(NotificacionTurista.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public NotificacionTurista getBeanByCode(String codeNotificacionTurista) throws UnknownException {
		Query query = pm.newQuery(NotificacionTurista.class);
		String declareParameters = "String paramCodeNotificacionTurista";
		String filter = "codeNotificacionTurista==paramCodeNotificacionTurista";
		try {						
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			List<NotificacionTurista> listNotificacionTurista = new ArrayList<NotificacionTurista>();
			listNotificacionTurista.addAll((List<NotificacionTurista>) (query.execute(codeNotificacionTurista)));
			if (!listNotificacionTurista.isEmpty()) {
				return listNotificacionTurista.get(0);
			}
			return null;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
	
	public Integer getCount(String correoTurista) throws UnknownException {
		Query query = pm.newQuery("SELECT count(idNotificacionTurista) "
				+ "FROM com.indiana.server.model.bean.NotificacionTurista "
				+ "WHERE codeTuristaRecibeNotificacion==paramCorreoTurista && visto ==paramVisto");

		try {
			String declareParamecters = "String paramCorreoTurista,String paramVisto";
			query.declareParameters(declareParamecters);
			List<Object> beanObject=new ArrayList<>();
			beanObject.add(correoTurista);
			beanObject.add("N");
			Long evaluator = (Long) query.executeWithArray(beanObject.toArray());		
				if(evaluator !=null){
					return Integer.parseInt(evaluator.toString());
				}else{
					return 0;
				}			
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}	
	}

	@SuppressWarnings("unchecked")
	public Collection<NotificacionTurista> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<NotificacionTurista> listaLocalidad = (Collection<NotificacionTurista>) query
				.getListaBean(NotificacionTurista.class);
		return listaLocalidad;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public List<NotificacionTurista> getListarBean(String correoTurista,String visto) throws UnknownException {
		Query query = pm.newQuery(NotificacionTurista.class);
		String declareParameters = "";
		String filter = "";
		String ordering = "version desc";

		List<String> declareParametersList= new ArrayList<String>();
		List<String> filterList= new ArrayList<String>();
		List<Object>listBeans=new ArrayList<Object>();
		if(correoTurista!=null){
			declareParametersList.add("String paramCodeTuristaRecibeNotificacion");
			filterList.add("codeTuristaRecibeNotificacion==paramCodeTuristaRecibeNotificacion");
			listBeans.add(correoTurista);
		}		
		if(visto!=null){			
			declareParametersList.add("String paramVisto");
			filterList.add("visto==paramVisto");
			listBeans.add(visto);
		}		
		if(declareParametersList.isEmpty()){
			declareParameters=null;
			filter=null;
			listBeans=null;			
		}
		for (int i = 0; i < declareParametersList.size(); i++) {         
			declareParameters+=declareParametersList.get(i);
			filter+=filterList.get(i);
			if(i<(declareParametersList.size()-1)){
				declareParameters+=",";
				filter+="&&";
			}
        }	
		try {
			query.setResult(
					"idNotificacionTurista,"
					+ "codeNotificacionTurista,"
					+ "visto,"
					+ "codeNotificacion,"
					+ "codeTipoNotificacion,"
					+ "codeTuristaGeneraNotificacion,"
					+ "nombreTuristaNegocioGeneraNotificacion,"
					+ "apellidoTuristaGeneraNotificacion,"
					+ "fotoPerfilTuristaNegocioGeneraNotificacion,"
					+ "codeNoticia,"
					+ "codeNovedad,"
					+ "codeColonia,"
					+ "nombreColonia,"
					+ "codeMiembro,"
					+ "codeDivulgacionNoticia,"
					+ "codeNoticiaCompartida,"
					+ "codeSolicitudAmistad,"
					+ "codeTuristaPublica,"
					+ "codeConquista,"
					+ "codeDestino,"
					+ "nombreDestinoNegocioConquistado,"
					+ "codeOfertaNegocio,"
					+ "codeTuristaInteresEmpatia,"
					+ "codeInteres,"
					+ "codeSugerenciaColonia,"
					+ "mensajeNotificacion,"
					+ "codeNegocioGeneraNoticia,"
					+ "codeNegocioConquistado,"
					+ "isPersistente,"
					+ "tokenFirebase,"
					+ "resultTokenFirebase,"				
					+ "version");
			Querys consulta=new Querys();
			List evaluator = ((List) consulta.sendQuery(null, declareParameters, filter, ordering, listBeans, query));
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			List<Object[]> listObject = new ArrayList<Object[]>();
			while (resultIterator.hasNext()) {
				listObject.add((Object[]) resultIterator.next());
			}			
			List<NotificacionTurista> lista= new ArrayList<NotificacionTurista>();
			Iterator<Object[]> listObjectIterator = listObject.iterator();
			while (listObjectIterator.hasNext()) {
				elements = listObjectIterator.next();
				NotificacionTurista beanNotificacionTurista = new NotificacionTurista();
				if (elements[0] != null) {
					beanNotificacionTurista.setIdNotificacionTurista(elements[0].toString());
				}else{
					continue;
				}
				if (elements[1] != null) {
					beanNotificacionTurista.setCodeNotificacionTurista((elements[1].toString()));
				}
				if (elements[2] != null) {
					beanNotificacionTurista.setVisto(elements[2].toString());
				}
				if (elements[3] != null) {
					beanNotificacionTurista.setCodeNotificacion(elements[3].toString());
				}
				if (elements[4] != null) {
					beanNotificacionTurista.setCodeTipoNotificacion(elements[4].toString());
				}
				if (elements[5] != null) {
					beanNotificacionTurista.setCodeTuristaGeneraNotificacion(elements[5].toString());
				}
				if (elements[6] != null) {
					beanNotificacionTurista.setNombreTuristaNegocioGeneraNotificacion(elements[6].toString());
				}
				if (elements[7] != null) {
					beanNotificacionTurista.setApellidoTuristaGeneraNotificacion(elements[7].toString());
				}
				if (elements[8] != null) {
					beanNotificacionTurista.setFotoPerfilTuristaNegocioGeneraNotificacion(elements[8].toString());
				}
				if (elements[9] != null) {
					beanNotificacionTurista.setCodeNoticia(elements[9].toString());
				}
				if (elements[10] != null) {
					beanNotificacionTurista.setCodeNovedad(elements[10].toString());
				}
				if (elements[11] != null) {
					beanNotificacionTurista.setCodeColonia(elements[11].toString());
				}
				if (elements[12] != null) {
					beanNotificacionTurista.setNombreColonia(elements[12].toString());
				}
				if (elements[13] != null) {
					beanNotificacionTurista.setCodeMiembro(elements[13].toString());
				}
				if (elements[14] != null) {
					beanNotificacionTurista.setCodeDivulgacionNoticia(elements[14].toString());
				}
				if (elements[15] != null) {
					beanNotificacionTurista.setCodeNoticiaCompartida(elements[15].toString());
				}
				if (elements[16] != null) {
					beanNotificacionTurista.setCodeSolicitudAmistad(elements[16].toString());
				}
				if (elements[17] != null) {
					beanNotificacionTurista.setCodeTuristaPublica(elements[17].toString());
				}
				if (elements[18] != null) {
					beanNotificacionTurista.setCodeConquista(elements[18].toString());
				}
				if (elements[19] != null) {
					beanNotificacionTurista.setCodeDestino(elements[19].toString());
				}
				if (elements[20] != null) {
					beanNotificacionTurista.setNombreDestinoNegocioConquistado(elements[20].toString());
				}
				if (elements[21] != null) {
					beanNotificacionTurista.setCodeOfertaNegocio(elements[21].toString());
				}
				if (elements[22] != null) {
					beanNotificacionTurista.setCodeTuristaInteresEmpatia(elements[22].toString());
				}
				if (elements[23] != null) {
					beanNotificacionTurista.setCodeInteres(elements[23].toString());
				}
				if (elements[24] != null) {
					beanNotificacionTurista.setCodeSugerenciaColonia(elements[24].toString());
				}
				if (elements[25] != null) {
					beanNotificacionTurista.setMensajeNotificacion(elements[25].toString());
				}
				if (elements[26] != null) {
					beanNotificacionTurista.setCodeNegocioGeneraNoticia(elements[26].toString());
				}
				if (elements[27] != null) {
					beanNotificacionTurista.setCodeNegocioConquistado(elements[27].toString());
				}
				if (elements[28] != null) {
					beanNotificacionTurista.setIsPersistente(Boolean.parseBoolean(elements[28].toString()));
				}
				if (elements[29] != null) {
					beanNotificacionTurista.setTokenFirebase(elements[29].toString());
				}
				if (elements[30] != null) {
					beanNotificacionTurista.setResultTokenFirebase(elements[30].toString());
				}
				if (elements[31] != null) {
					beanNotificacionTurista.setFechaGeneroNotificacion(new Date(Long.parseLong((elements[31].toString()))));
				}
				lista.add(beanNotificacionTurista);
			}
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
	
}
