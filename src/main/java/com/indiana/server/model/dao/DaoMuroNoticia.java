package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoMuroNoticia {
	private PersistenceManager pm;

	public DaoMuroNoticia(PersistenceManager pm) {
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

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(MuroNoticia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<MuroNoticia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<MuroNoticia> lista = (Collection<MuroNoticia>) query
				.getListaBean(MuroNoticia.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<MuroNoticia> getListarBean(Integer limiteInferior,Integer limiteSuperior, String correoUsuarioTurista) throws UnknownException {
		Query query = pm.newQuery(MuroNoticia.class);
		String declareParameters="String paramCorreoTuristaMuro";
		String filter="correoTuristaMuro == paramCorreoTuristaMuro" ;
		try{
			query.declareParameters(declareParameters);
			query.setFilter(filter);
			if(limiteInferior!=null && limiteSuperior!=null){
				if(limiteInferior<=limiteSuperior){
					/*if(limiteInferior>0){
						limiteInferior=limiteInferior-1;
					}*/
					query.setRange(limiteInferior, limiteSuperior);
				}else{
					throw new UnknownException("Rango No Definido");
				}			
			}
			List<MuroNoticia> listMuroNoticia= new ArrayList<MuroNoticia>();
			Object object=query.execute(correoUsuarioTurista);
			if(object==null){
				throw new UnknownException("Error en la Consulta!");
			}
			listMuroNoticia.addAll((List<MuroNoticia>)object);
			return listMuroNoticia;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MuroNoticia> getListarBeanByCorreoTuristaMuro(String correoTuristaMuro,Integer cantidadNoticias) throws UnknownException {
		Query query = pm.newQuery(MuroNoticia.class);
		String declareParameters="String paramCorreoTuristaMuro,Boolean paramIsPersistente,String paramVisto";
		String filter="correoTuristaMuro == paramCorreoTuristaMuro && isPersistente == paramIsPersistente && visto==paramVisto";
		String ordering="fechaPublicacion asc";			
		try{
			List<MuroNoticia> lista=new ArrayList<MuroNoticia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(correoTuristaMuro);
			beanObject.add(true);
			beanObject.add("N");
			lista.addAll((List<MuroNoticia>)consulta.sendQuery(cantidadNoticias, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked"})
	public List<MuroNoticia> getListarByNotCorreoTurista(String correoTuristaMuro,Integer cantidadNoticias) throws UnknownException {
		Query query = pm.newQuery(MuroNoticia.class);
		String declareParameters="String paramCorreoTuristaMuro,Boolean paramIsPersistente,String paramVisto";
		String filter="correoTuristaGeneraNoticia != paramCorreoTuristaMuro && isPersistente == paramIsPersistente && visto==paramVisto";		
		try{
			List<MuroNoticia> lista=new ArrayList<MuroNoticia>();
			Querys consulta=new Querys();
			List<Object> beanObject=new ArrayList<>();
			beanObject.add(correoTuristaMuro);
			beanObject.add(true);
			beanObject.add("N");
			lista.addAll((List<MuroNoticia>)consulta.sendQuery(cantidadNoticias, declareParameters, filter,null, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
