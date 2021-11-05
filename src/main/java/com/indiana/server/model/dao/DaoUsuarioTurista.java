package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.query.evaluator.InMemoryQueryResult;

import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoUsuarioTurista {
	private PersistenceManager pm;

	public DaoUsuarioTurista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public UsuarioTurista mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (UsuarioTurista)query.mantenimientoReturn(parametro);
	}

	public UsuarioTurista getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (UsuarioTurista)query.getBean(UsuarioTurista.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<UsuarioTurista> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<UsuarioTurista> lista = (Collection<UsuarioTurista>) query
				.getListaBean(UsuarioTurista.class);
		return lista;
	}	
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UsuarioTurista> getListarBeanByConfiguracion(String nombrePais, String nombreRegion,String nombreLocalidad) throws UnknownException {
		Query query = pm.newQuery(UsuarioTurista.class);
		try {
			String declareParameters ="";
			String filter ="";
			boolean flagCategoria=false;
			boolean flagPais=false;
			boolean flagRegion=false;
			List<Object> listaParameters=new ArrayList<Object>();	
			Querys consulta = new Querys();
			if(nombrePais!=null){
				if(flagCategoria){
					declareParameters += ",String paramNombrePais";
					filter += "&& nombrePaisNacimiento==paramNombrePais";
				}else{
					declareParameters += "String paramNombrePais";
					filter += "nombrePaisNacimiento==paramNombrePais";
				}
				flagPais=true;
				listaParameters.add(nombrePais);
			}
			if(nombreRegion!=null){
				if((!flagCategoria && !flagPais)){
					declareParameters += "String paramNombreRegion";
					filter += "nombreRegionNacimiento==paramNombreRegion";
				}else{
					declareParameters += ",String paramNombreRegion";
					filter += " && nombreRegionNacimiento==paramNombreRegion";
				}
				flagRegion=true;
				listaParameters.add(nombreRegion);
			}			
			if(nombreLocalidad!=null){
				if(!flagCategoria  && !flagPais && !flagRegion){
					declareParameters += "String paramNombreLocalidad";
					filter += "nombreLocalidadNacimiento==paramNombreLocalidad";
				}else{
					declareParameters += ",String paramNombreLocalidad";
					filter += " && nombreLocalidadNacimiento==paramNombreLocalidad";
				}
				listaParameters.add(nombreLocalidad);
			}
			List<UsuarioTurista> listaUsuarioTurista= new ArrayList<UsuarioTurista>();
			query.setResult("idUsuarioTurista,correoTurista,nombre,apellido,nombrePaisNacimiento,nombreRegionNacimiento,"
					+ "nombreLocalidadNacimiento,genero,fotoPerfil,version");
			List evaluator = ((List) consulta.sendQuery(null, declareParameters, filter, null, listaParameters, query));
			Iterator<Object> resultIterator = (Iterator<Object>) evaluator.iterator();
			Object[] elements = null;
			List<Object[]> listObject = new ArrayList<Object[]>();
			while (resultIterator.hasNext()){
				listObject.add((Object[]) resultIterator.next());
			}
			Iterator<Object[]> listObjectIterator = listObject.iterator();
			while (listObjectIterator.hasNext()) {
				elements = listObjectIterator.next();
				UsuarioTurista beanUsuarioTurista = new UsuarioTurista();
				if (elements[0] != null) {
					beanUsuarioTurista.setIdUsuarioTurista(elements[0].toString());
				}
				if (elements[1] != null) {
					beanUsuarioTurista.setCorreoTurista((elements[1].toString()));
				}
				if (elements[2] != null) {
					beanUsuarioTurista.setNombre((elements[2].toString()));
				}
				if (elements[3] != null) {
					beanUsuarioTurista.setApellido((elements[3].toString()));
				}
				if (elements[4] != null) {
					beanUsuarioTurista.setNombrePaisNacimiento((elements[4].toString()));
				}
				if (elements[5] != null) {
					beanUsuarioTurista.setNombreRegionNacimiento((elements[5].toString()));
				}
				if (elements[6] != null) {
					beanUsuarioTurista.setNombreLocalidadNacimiento((elements[6].toString()));
				}
				if (elements[7] != null) {
					beanUsuarioTurista.setGenero((elements[7].toString()));
				}
				if (elements[8] != null) {
					beanUsuarioTurista.setFotoPerfil((elements[8].toString()));
				}
				if (elements[9] != null) {
					beanUsuarioTurista.setVersion(Long.parseLong((elements[9].toString())));
				}	
				listaUsuarioTurista.add(beanUsuarioTurista);				
			}
			return listaUsuarioTurista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioTurista getBeanByEmail(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(UsuarioTurista.class);		
		String filter="this.correoTurista=='"+correoTurista+"'";			
		try{
		List<UsuarioTurista> lista=new ArrayList<UsuarioTurista>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(correoTurista);
		lista.addAll((List<UsuarioTurista>)consulta.sendQuery(1, null, filter, null, beanObject, query));
		if(!lista.isEmpty()){
			return lista.get(0);
		}else{
			throw new UnknownException("Usuario no existe");
		}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioTurista getBeanByEmailAtom(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(UsuarioTurista.class);		
		String filter="this.correoTurista=='"+correoTurista+"'";			
		try{
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(correoTurista);		
		ArrayList<String> atributos=new ArrayList<String>();
		atributos.add(UsuarioTurista.fieldApellido);
		atributos.add(UsuarioTurista.fieldCorreoTurista);
		atributos.add(UsuarioTurista.fieldFechaNacimiento);
		atributos.add(UsuarioTurista.fieldFotoPerfil);
		atributos.add(UsuarioTurista.fieldFotoPortada);
		atributos.add(UsuarioTurista.fieldGenero);
		atributos.add(UsuarioTurista.fieldIdUsuarioTurista);
		atributos.add(UsuarioTurista.fieldModaInteresDos);
		atributos.add(UsuarioTurista.fieldModaInteresTres);
		atributos.add(UsuarioTurista.fieldModaInteresUno);
		atributos.add(UsuarioTurista.fieldNombre);
		atributos.add(UsuarioTurista.fieldNombreInteresDos);
		atributos.add(UsuarioTurista.fieldNombreInteresTres);
		atributos.add(UsuarioTurista.fieldNombreInteresUno);
		atributos.add(UsuarioTurista.fieldNombreLocalidadNacimiento);
		atributos.add(UsuarioTurista.fieldNombrePaisNacimiento);
		atributos.add(UsuarioTurista.fieldNombreRegionNacimiento);
		atributos.add(UsuarioTurista.fieldTokenFirebase);
		atributos.add(UsuarioTurista.fieldTotalColonias);
		atributos.add(UsuarioTurista.fieldTotalColoniasCreadas);
		atributos.add(UsuarioTurista.fieldTotalConquistas);
		atributos.add(UsuarioTurista.fieldTotalConquistasNegocios);
		atributos.add(UsuarioTurista.fieldTotalDescubiertos);
		atributos.add(UsuarioTurista.fieldTotalNegociosCreados);		
		query.setResult(UsuarioTurista.fieldsResult(atributos));				
		InMemoryQueryResult evaluator= (InMemoryQueryResult)consulta.sendQuery(1, null, filter, null, beanObject, query);
		if(evaluator!=null && !evaluator.isEmpty()){
			Iterator<Object> resultIterator= (Iterator<Object>)evaluator.iterator();
			Object[] elements=null;
			List<Object[]> listObject= new ArrayList<Object[]>();
			while(resultIterator.hasNext()){
				listObject.add((Object[]) resultIterator.next());
			}				
			evaluator.close();		
			Iterator<Object[]> listObjectIterator=listObject.iterator();
			UsuarioTurista beanUsuarioTurista= new UsuarioTurista();		
			while(listObjectIterator.hasNext()){
				elements=listObjectIterator.next();			
				beanUsuarioTurista.setApellido(elements[0]!=null?elements[0].toString():null);
				beanUsuarioTurista.setCorreoTurista(elements[1]!=null?elements[1].toString():null);				
				beanUsuarioTurista.setFechaNacimiento(elements[2]!=null?(java.util.Date)elements[2]:null);
				beanUsuarioTurista.setFotoPerfil(elements[3]!=null?elements[3].toString():null);
				beanUsuarioTurista.setFotoPortada(elements[4]!=null?elements[4].toString():null);
				beanUsuarioTurista.setGenero(elements[5]!=null?elements[5].toString():null);				
				beanUsuarioTurista.setIdUsuarioTurista(elements[6]!=null?elements[6].toString():null);
				beanUsuarioTurista.setModaInteresDos(elements[7]!=null?(Double)elements[7]:null);
				beanUsuarioTurista.setModaInteresTres(elements[8]!=null?(Double)elements[8]:null);
				beanUsuarioTurista.setModaInteresUno(elements[9]!=null?(Double)elements[9]:null);
				beanUsuarioTurista.setNombre(elements[10]!=null?elements[10].toString():null);
				beanUsuarioTurista.setNombreInteresDos(elements[11]!=null?elements[11].toString():null);
				beanUsuarioTurista.setNombreInteresTres(elements[12]!=null?elements[12].toString():null);
				beanUsuarioTurista.setNombreInteresUno(elements[13]!=null?elements[13].toString():null);
				beanUsuarioTurista.setNombreLocalidadNacimiento(elements[14]!=null?elements[14].toString():null);
				beanUsuarioTurista.setNombrePaisNacimiento(elements[15]!=null?elements[15].toString():null);
				beanUsuarioTurista.setNombreRegionNacimiento(elements[16]!=null?elements[16].toString():null);
				beanUsuarioTurista.setTokenFirebase(elements[17]!=null?elements[17].toString():null);
				beanUsuarioTurista.setTotalColonias(elements[18]!=null?(Integer)elements[18]:null);
				beanUsuarioTurista.setTotalColoniasCreadas(elements[19]!=null?(Integer)elements[19]:null);
				beanUsuarioTurista.setTotalConquistas(elements[20]!=null?(Integer)elements[20]:null);
				beanUsuarioTurista.setTotalConquistasNegocios(elements[21]!=null?(Integer)elements[21]:null);
				beanUsuarioTurista.setTotalDescubiertos(elements[22]!=null?(Integer)elements[22]:null);
				beanUsuarioTurista.setTotalNegociosCreados(elements[23]!=null?(Integer)elements[23]:null);				
			}
			return beanUsuarioTurista;
		}else{
			throw new UnknownException("Usuario no existe");
		}			
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}

}
