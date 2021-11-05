package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.query.evaluator.InMemoryQueryResult;

import com.indiana.server.model.bean.Amistad;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;
import java.util.logging.Logger;

public class DaoAmistad {
    private static final Logger LOG = Logger.getLogger(DaoAmistad.class.getName());
	private PersistenceManager pm;

	public DaoAmistad(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	public boolean mantenimiento(List<BeanParametro> listParametros)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(listParametros);
	}
	
	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Amistad.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object verAmistad(String codeTuristaEnvia,String codeTuristaRecibe,String codeEstadoAmistad) throws UnknownException {
		Query query = pm.newQuery(Amistad.class);
		String declareParameters="String paramCodeTuristaEnvia,String paramCodeTuristaRecibe,String paramCodeEstadoAmistad";
		String filter="codeEstadoAmistad!=paramCodeEstadoAmistad && codeTuristaPrincipal==paramCodeTuristaEnvia && codeTuristaAmigo==paramCodeTuristaRecibe";				
		try{			
			query.declareParameters(declareParameters);
			query.setFilter(filter);		
			List<Amistad>listAmigos=new ArrayList<Amistad>();
			List<Object> beanObject=new ArrayList();
			beanObject.add(codeTuristaEnvia);
			beanObject.add(codeTuristaRecibe);
			beanObject.add(codeEstadoAmistad);
			listAmigos.addAll((List<Amistad>)query.executeWithArray(beanObject.toArray()));			
			if(listAmigos.size()>0){							
				return listAmigos;
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Amistad> getListarAmigoActivo(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(Amistad.class);
		String declareParameters="String paramCodeUsuarioTurista,String paramCodeEstadoAmistad";
		String filter="codeTuristaPrincipal==paramCodeUsuarioTurista && codeEstadoAmistad!=paramCodeEstadoAmistad";
		try{
		Set<Amistad> lista=new HashSet<Amistad>();
		Querys consulta=new Querys();
		List<Object> beanObject=new ArrayList();                
                LOG.info(correoTurista);
		beanObject.add(correoTurista);	
		beanObject.add("E");		
		List<Amistad>listAmistades=((List<Amistad>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));				
                lista.addAll(listAmistades);
		return lista;
		}catch(Exception ex){	
                    System.err.println("error"+ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Amistad> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Amistad> listaAmistad = (Collection<Amistad>) query
				.getListaBean(Amistad.class);
		return listaAmistad;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Amistad> getListarAmigos(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(Amistad.class);
		String declareParameters="String paramCodeUsuarioTurista,String paramCodeEstadoAmistad";
		String filter="codeTuristaPrincipal==paramCodeUsuarioTurista && codeEstadoAmistad!=paramCodeEstadoAmistad";
		try{
		Set<Amistad> lista=new HashSet<Amistad>();
		Querys consulta=new Querys();
		List<Object> beanObject=new ArrayList();                
                LOG.info(correoTurista);
		beanObject.add(correoTurista);	
		beanObject.add("E");
		query.setResult("codeAmistad,codUnicoAmistad,version,codeEstadoAmistad,codeTuristaAmigo,"
				+ "nombreTuristaAmigo,nombrePaisAmigo,idAmistad,fotoPerfilTuristaAmigo,"
				+ "apellidoTuristaAmigo,codeSolicitudAmistad");			
		InMemoryQueryResult evaluator= (InMemoryQueryResult)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query);
		Iterator<Object> resultIterator= (Iterator<Object>)evaluator.iterator();
		Object[] elements=null;
		List<Object[]> listObject= new ArrayList<Object[]>();
		while(resultIterator.hasNext()){
			listObject.add((Object[]) resultIterator.next());
		}				
		evaluator.close();		
		Iterator<Object[]> listObjectIterator=listObject.iterator();   
                LOG.info(String.valueOf(listObject.size()));
		while(listObjectIterator.hasNext()){
			elements=listObjectIterator.next();
			Amistad beanAmistad= new Amistad();			
			beanAmistad.setCodeAmistad((elements[0].toString()));
			beanAmistad.setCodUnicoAmistad(elements[1].toString());
			beanAmistad.setVersion(Long.parseLong(elements[2].toString()));
			beanAmistad.setCodeEstadoAmistad(elements[3].toString());
			beanAmistad.setCodeTuristaAmigo(elements[4].toString());
			if(elements[5]!=null){
				beanAmistad.setNombreTuristaAmigo(elements[5].toString());
			}
			if(elements[6]!=null){
				beanAmistad.setNombrePaisAmigo(elements[6].toString());
			}
			if(elements[7]!=null){
				beanAmistad.setIdAmistad(elements[7].toString());
			}
			if(elements[8]!=null){
				beanAmistad.setFotoPerfilTuristaAmigo(elements[8].toString());
			}
			if(elements[9]!=null){
				beanAmistad.setApellidoTuristaAmigo(elements[9].toString());
			}
			if(elements[10]!=null){
				beanAmistad.setCodeSolicitudAmistad(elements[10].toString());
			}
			beanAmistad.setEnComun(false);
			lista.add(beanAmistad);
                        LOG.info(elements[10].toString());
		}
                System.err.println("Apunto de retornar la lista: "+lista.size());
		return lista;
		}catch(Exception ex){	
                    System.err.println("error"+ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Amistad> getBeanByTuristaPrimeraSolicitud(String correoTurista) throws UnknownException {
		Query query = pm.newQuery("SELECT "
				+ "version,"
				+ "idAmistad,"
				+ "codeAmistad,"
				+ "codUnicoAmistad,"
				+ "codeEstadoAmistad,"
				+ "codeTuristaAmigo,"
				+ "nombreTuristaAmigo,"
				+ "apellidoTuristaAmigo,"
				+ "fotoPerfilTuristaAmigo,"
				+ "codePaisNacionalidadAmigo,"
				+ "nombrePaisAmigo,"
				+ "codeRegionAmigo,"
				+ "nombreRegionAmigo,"
				+ "codeSolicitudAmistad,"
				+ "isPersistente"
				+ " "
				+ "FROM com.indiana.server.model.bean.Amistad "
				+ "WHERE codeTuristaPrincipal ==paramCorreoTurista");
		try {
			String declareParamecters = "String paramCorreoTurista";
			query.declareParameters(declareParamecters);
			InMemoryQueryResult evaluator = (InMemoryQueryResult) query.execute(correoTurista);
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			List<Amistad> lista=new ArrayList<Amistad>();
			List<Object[]> listObject= new ArrayList<Object[]>();
			while(resultIterator.hasNext()){
				listObject.add((Object[]) resultIterator.next());
			}	
			evaluator.close();		
			Iterator<Object[]> listObjectIterator=listObject.iterator();
			while(listObjectIterator.hasNext()){
				elements=listObjectIterator.next();
				Amistad beanAmistad = new Amistad();
				if(elements[1]==null || elements[2] == null){
					return null;
				}
				if (elements[1] != null) {
					beanAmistad.setIdAmistad(elements[1].toString());
				}
				if (elements[2] != null) {
					beanAmistad.setCodeAmistad(elements[2].toString());
				}
				if (elements[3] != null) {
					beanAmistad.setCodUnicoAmistad(elements[3].toString());
				}
				if (elements[4] != null) {
					beanAmistad.setCodeEstadoAmistad(elements[4].toString());
				}
				if (elements[5] != null) {
					beanAmistad.setCodeTuristaAmigo(elements[5].toString());
				}
				if (elements[6] != null) {
					beanAmistad.setNombreTuristaAmigo(elements[6].toString());
				}
				if (elements[7] != null) {
					beanAmistad.setApellidoTuristaAmigo(elements[7].toString());
				}
				if (elements[8] != null) {
					beanAmistad.setFotoPerfilTuristaAmigo(elements[8].toString());
				}
				if (elements[9] != null) {
					beanAmistad.setCodePaisNacionalidadAmigo(elements[9].toString());
				}
				if (elements[10] != null) {
					beanAmistad.setNombrePaisAmigo(elements[10].toString());
				}
				if (elements[11] != null) {
					beanAmistad.setCodeRegionAmigo(elements[11].toString());
				}
				if (elements[12] != null) {
					beanAmistad.setNombreRegionAmigo(elements[12].toString());
				}
				if (elements[13] != null) {
					beanAmistad.setCodeSolicitudAmistad(elements[13].toString());
				}
				if (elements[14] != null) {
					beanAmistad.setIsPersistente(Boolean.valueOf(elements[14].toString()));
				}
				if (elements[0] != null) {
					beanAmistad.setVersion(Long.valueOf(elements[0].toString()));
				}
				lista.add(beanAmistad);
			}
			return lista;			
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
