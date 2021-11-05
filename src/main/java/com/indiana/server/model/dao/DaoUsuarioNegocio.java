package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoUsuarioNegocio {
	private PersistenceManager pm;

	public DaoUsuarioNegocio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(UsuarioNegocio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<UsuarioNegocio> getListarBean(String nombrePais, String nombreRegion,String nombreLocalidad) throws UnknownException {
		Query query = pm.newQuery(UsuarioNegocio.class);
		try {
			String declareParameters = "";
			String filter = "";
			if (nombrePais != null) {
				declareParameters = "String paramNombrePais";
				filter = "nombrePais==paramNombrePais";
				if (nombreRegion != null) {
					declareParameters += ",String paramNombreRegion";
					filter += " && nombreRegion==paramNombreRegion";
					if(nombreLocalidad!=null){
						declareParameters += ",String paramNombreLocalidad";
						filter += " && nombreLocalidad==paramNombreLocalidad";
					}
				}
				query.declareParameters(declareParameters);
				query.setFilter(filter);
			}
			List<UsuarioNegocio> listNegocios= new ArrayList<UsuarioNegocio>();
			Object object = null;
			if (nombrePais != null) {
				if (nombreRegion != null) {
					if(nombreLocalidad!=null){
						object = query.execute(nombrePais, nombreRegion,nombreLocalidad);
					}else{
						object = query.execute(nombrePais, nombreRegion);
					}
				} else {
					object = query.execute(nombrePais);
				}
				listNegocios.addAll((List<UsuarioNegocio>) object);
				return listNegocios;
			}else{
				return null;
			}
			
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioNegocio getBeanByEmail(String correoNegocio) throws UnknownException {
		Query query = pm.newQuery(UsuarioNegocio.class);		
		String filter="this.correoNegocio=='"+correoNegocio+"'";			
		try{
		List<UsuarioNegocio> lista=new ArrayList<UsuarioNegocio>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(correoNegocio);
		lista.addAll((List<UsuarioNegocio>)consulta.sendQuery(1, null, filter, null, beanObject, query));
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
	
	@SuppressWarnings("unchecked")
	public Collection<UsuarioNegocio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<UsuarioNegocio> lista = (Collection<UsuarioNegocio>) query
				.getListaBean(UsuarioNegocio.class);
		return lista;
	}

}
