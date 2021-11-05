package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.process.GestionShared;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoDestino {
	private PersistenceManager pm;

	public DaoDestino(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Destino mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Destino)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Destino.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Destino getBeanByCode(String codeDestino) throws UnknownException {
                String idDestino=GestionShared.keyStringBean(Destino.class.getSimpleName(), codeDestino);
		Query query = pm.newQuery(Destino.class);
		String declareParameters="String paramIdDestino";		
		String filter= "idDestino==paramIdDestino";		
		try{
			List<Destino> lista=new ArrayList<Destino>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();			
			beanObject.add(idDestino);
			lista.addAll((List<Destino>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	public Collection<Destino> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Destino> listaLocalidad = (Collection<Destino>) query
				.getListaBean(Destino.class);
		return listaLocalidad;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Destino> getListarBean(String nombrePais, String nombreRegion,String nombreLocalidad,String tipoNegocioDestino) throws UnknownException {
		Query query = pm.newQuery(Destino.class);
		try {
			String declareParameters = "";
			String filter = "";
			if (nombrePais != null && !nombrePais.isEmpty()) {
				declareParameters = "String paramNombrePais,String paramTipoNegocioDestino";
				filter = "nombrePais==paramNombrePais && tipoNegocioDestino==paramTipoNegocioDestino";
				if (nombreRegion != null && !nombreRegion.isEmpty()) {
					declareParameters += ",String paramNombreRegion";
					filter += " && nombreRegion==paramNombreRegion";
					if(nombreLocalidad!=null && !nombreLocalidad.isEmpty()){
						declareParameters += ",String paramNombreLocalidad";
						filter += " && nombreLocalidad==paramNombreLocalidad";
					}
				}
				query.declareParameters(declareParameters);
				query.setFilter(filter);
			}
			List<Destino> listDestinos = new ArrayList<Destino>();
			Object object = null;
			if (nombrePais != null && !nombrePais.isEmpty()) {
				if (nombreRegion != null && !nombreRegion.isEmpty()) {
					if(nombreLocalidad!=null && !nombreLocalidad.isEmpty()){
						List<Object> beanObject = new ArrayList<Object>();
						beanObject.add(nombrePais);
						beanObject.add(tipoNegocioDestino);
						beanObject.add(nombreRegion);	
						beanObject.add(nombreLocalidad);
						object = query.executeWithArray(beanObject.toArray());
					}else{
						object = query.execute(nombrePais,tipoNegocioDestino, nombreRegion);
					}					
				} else {
					object = query.execute(nombrePais,tipoNegocioDestino);
				}
				listDestinos.addAll((List<Destino>) object);
				return listDestinos;
			}else{
				return null;
			}
							
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Destino> getListarBeanByCreador(String correoTurista) throws UnknownException {
		Query query = pm.newQuery(Destino.class);
		String declareParameters = "String paramCodeTurista";
		String filter = "codeTuristaCreador==paramCodeTurista";		
		try {
			List<Destino> lista = new ArrayList<Destino>();
			Querys consulta = new Querys();			
			List<Object> beanObject = new ArrayList<Object>();
			beanObject.add(correoTurista);
			lista.addAll((List<Destino>) consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
			return (lista);
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Destino> getListarBeanByConfiguracion(String codeCategoria,String nombrePais, String nombreRegion,String nombreLocalidad,String nombreTipoNegocioDestino) throws UnknownException {
		Query query = pm.newQuery(Destino.class);
		try {
			String declareParameters = "";
			String filter = "";
			boolean flagCategoria=false;
			boolean flagPais=false;
			boolean flagRegion=false;
			List<Object> listaParameters=new ArrayList<Object>();
			if(codeCategoria!=null){
				declareParameters += "String paramCodeCategoria";
				filter+="codeCategoriaDestino==paramCodeCategoria";
				flagCategoria=true;
				listaParameters.add(codeCategoria);
			}			
			if(nombrePais!=null){
				if(flagCategoria){
					declareParameters += ",String paramNombrePais";
					filter += "&& nombrePais==paramNombrePais";
				}else{
					declareParameters += "String paramNombrePais";
					filter += "nombrePais==paramNombrePais";
				}
				flagPais=true;
				listaParameters.add(nombrePais);
			}
			if(nombreRegion!=null){
				if((!flagCategoria && !flagPais)){
					declareParameters += "String paramNombreRegion";
					filter += "nombreRegion==paramNombreRegion";
				}else{
					declareParameters += ",String paramNombreRegion";
					filter += " && nombreRegion==paramNombreRegion";
				}
				flagRegion=true;
				listaParameters.add(nombreRegion);
			}
			
			if(nombreLocalidad!=null){
				if(!flagCategoria  && !flagPais && !flagRegion){
					declareParameters += "String paramNombreLocalidad";
					filter += "nombreLocalidad==paramNombreLocalidad";
				}else{
					declareParameters += ",String paramNombreLocalidad";
					filter += " && nombreLocalidad==paramNombreLocalidad";
				}
				listaParameters.add(nombreLocalidad);
			}
			// Agregamos el Tipo...
			declareParameters+=",String paramTipoNegocioDestino";
			filter+="&& tipoNegocioDestino==paramTipoNegocioDestino";
			listaParameters.add(nombreTipoNegocioDestino);
//			-Necesariamente uno de Ellos es Diferente de Null ya que validamos antes de Ingresar a este Metodo
			query.declareParameters(declareParameters);
			query.setFilter(filter);			
			List<Destino> listDestinos = new ArrayList<Destino>();
			Object object = null;
			
			object = query.executeWithArray(listaParameters.toArray());
			listDestinos.addAll((List<Destino>) object);
			if(!listDestinos.isEmpty()){
				return listDestinos;
			}
			return null;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Destino> getListarBeanByLike(String patron,String codeCategoria,String nombrePais, String nombreRegion,String nombreLocalidad) throws UnknownException {
		Query query = pm.newQuery(Destino.class);
//		String declareParameters="Integer paramCodeUsuarioTurista";
//		String filter="InvokeExpression{[PrimaryExpression{nombre}].indexOf(Literal{D})}";
		
		String filter="nombre.matches(\"E*C\")";
//		String filter="nombre.startsWith(\"D.*\")";	
//		(.*)Tutorials(.*)
		try{			
//			query.declareParameters(declareParameters);
			query.setFilter(filter);
			

			List<Destino> listDestinos = new ArrayList<Destino>();				
			listDestinos.addAll((List<Destino>)query.execute());			
			if(listDestinos.size()>0){							
				return listDestinos;
			}
			return null;
							
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
}
