package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.query.evaluator.InMemoryQueryResult;

import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoColonia {
	private PersistenceManager pm;

	public DaoColonia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Colonia mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Colonia)query.mantenimientoReturn(parametro);
	}

	public Colonia getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Colonia)query.getBean(Colonia.class, id);
	}
	
	public Colonia getBeanByCode(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Colonia)query.getBean(Colonia.class, id);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<Colonia> getListarBean(Integer limiteMostrar,UsuarioTurista beanUsuarioTurista) throws UnknownException {
		Query query = pm.newQuery(Colonia.class);			
		String declareParameters="UsuarioTurista beanUsuarioTurista";
		String filter="this.beanTurista==beanUsuarioTurista";
		try{
		List<Colonia> lista=new ArrayList<Colonia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(beanUsuarioTurista);
		lista.addAll((List<Colonia>)consulta.sendQuery(limiteMostrar, declareParameters, filter, null, beanObject, query));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Colonia> getListarBeanByTuristaCreador(String codeTuristaCreador) throws UnknownException {	
			Query query = pm.newQuery(Colonia.class);
			String declareParameters="String paramCodeTuristaCreador";
			String filter="codeTuristaCreador==paramCodeTuristaCreador";
			try{
			List<Colonia> lista=new ArrayList<Colonia>();
			Querys consulta=new Querys();
			List<Object> beanObject=new ArrayList();
			beanObject.add(codeTuristaCreador);				
			query.setResult("codeColonia,nombreColonia,descripcion,latitud,longitud,radio,"
					+ "cantidadMiembros,nombreInteresUno,modaInteresUno,nombreInteresDos,modaInteresDos,"
					+ "nombreInteresTres,modaInteresTres,idColonia");							
			InMemoryQueryResult evaluator= (InMemoryQueryResult)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query);
			Iterator<Object> resultIterator= (Iterator<Object>)evaluator.iterator();
			Object[] elements=null;
			List<Object[]> listObject= new ArrayList<Object[]>();
			while(resultIterator.hasNext()){
				listObject.add((Object[]) resultIterator.next());
			}				
			evaluator.close();		
			Iterator<Object[]> listObjectIterator=listObject.iterator();
			while(listObjectIterator.hasNext()){
				elements=listObjectIterator.next();
				Colonia beanColonia= new Colonia();			
				beanColonia.setCodeColonia((elements[0].toString()));
				beanColonia.setNombreColonia(elements[1].toString());
				if(elements[2]!=null){
					beanColonia.setDescripcion(elements[2].toString());
				}				
				beanColonia.setLatitud(Double.parseDouble(elements[3].toString()));
				beanColonia.setLongitud(Double.parseDouble(elements[4].toString()));
				beanColonia.setRadio(Double.parseDouble(elements[5].toString()));
				beanColonia.setCantidadMiembros(Integer.parseInt(elements[6].toString()));
				if(elements[7]!=null){
					beanColonia.setNombreInteresUno(elements[7].toString());
				}
				if(elements[8]!=null){
					beanColonia.setModaInteresUno(Double.parseDouble(elements[8].toString()));
				}
				if(elements[9]!=null){
					beanColonia.setNombreInteresDos(elements[9].toString());
				}
				if(elements[10]!=null){
					beanColonia.setModaInteresDos(Double.parseDouble(elements[10].toString()));
				}
				if(elements[11]!=null){
					beanColonia.setNombreInteresTres(elements[11].toString());
				}
				if(elements[12]!=null){
					beanColonia.setModaInteresTres(Double.parseDouble(elements[12].toString()));
				}		
				beanColonia.setIdColonia(elements[13].toString());
				lista.add(beanColonia);
			}
			return lista;
			}catch(Exception ex){			
				throw new UnknownException(ex.getMessage());
			}finally{
				query.closeAll();
			}		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Colonia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Colonia> listColonias = (Collection<Colonia>) query
				.getListaBean(Colonia.class);
		return listColonias;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Colonia> getListarBean(String nombrePais, String nombreRegion,String nombreLocalidad) throws UnknownException {
		Query query = pm.newQuery(Colonia.class);
		try {
			String declareParameters = "";
			String filter = "";
			if (nombrePais != null && !nombrePais.isEmpty()) {
				declareParameters = "String paramNombrePais";
				filter = "nombrePaisColonia==paramNombrePais";
				if (nombreRegion != null && !nombreRegion.isEmpty()) {
					declareParameters += ",String paramNombreRegion";
					filter += " && nombreRegionColonia==paramNombreRegion";
					if(nombreLocalidad!=null && !nombreLocalidad.isEmpty()){
						declareParameters += ",String paramNombreLocalidad";
						filter += " && nombreLocalidadColonia==paramNombreLocalidad";
					}
				}
				query.declareParameters(declareParameters);
				query.setFilter(filter);
			}
			System.out.println(declareParameters);
			System.out.println(filter);
			List<Colonia> listColonias = new ArrayList<Colonia>();
			Object object = null;
			if (nombrePais != null && !nombrePais.isEmpty()) {
				if (nombreRegion != null && !nombreRegion.isEmpty()) {
					if(nombreLocalidad!=null && !nombreLocalidad.isEmpty()){
						object = query.execute(nombrePais, nombreRegion,nombreLocalidad);
					}else{
						object = query.execute(nombrePais, nombreRegion);
					}					
				} else {
					object = query.execute(nombrePais);
				}
				listColonias.addAll((List<Colonia>) object);
				return listColonias;
			}else{
				return null;
			}						
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
	
}
