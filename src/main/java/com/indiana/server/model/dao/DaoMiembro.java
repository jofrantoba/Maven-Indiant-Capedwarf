package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.Miembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoMiembro {
	private PersistenceManager pm;

	public DaoMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	
	public Miembro mantenimientoReturn(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Miembro)query.mantenimientoReturn(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Miembro.class, id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean getBeanByCode(String codeMiembro) throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeMiembro, String paramCodeEstadoMiembro";
		String filter="codeMiembro==paramCodeMiembro && codeEstadoMiembro!=paramCodeEstadoMiembro";
		try{		
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		String codeEstadoMiembro="D";
		beanObject.add(codeMiembro);		
		beanObject.add(codeEstadoMiembro);
		List<Miembro> listaResult=(List<Miembro>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query);
		Integer count=listaResult.size();
		if(count==0){
			return false;
		}else if(count==1){
			return true;
		}else{
			throw new UnknownException("Turista tiene "+count+" configuraciones de miembro en colonia");
		}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean existeMiembro(String codeUsuarioTurista, String codeColonia) throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeColonia, String  paramCodeTurista, String paramCodeEstadoMiembro";
		String filter="this.codeColonia==paramCodeColonia && this.codeTurista==paramCodeTurista && this.codeEstadoMiembro!=paramCodeEstadoMiembro";
		try{		
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		String codeEstadoMiembro="D";
		beanObject.add(codeColonia);
		beanObject.add(codeUsuarioTurista);
		beanObject.add(codeEstadoMiembro);
		List<Miembro> listaResult=(List<Miembro>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query);
		Integer count=listaResult.size();
		if(count==0){
			return false;
		}else if(count==1){
			return true;
		}else{
			throw new UnknownException("Turista tiene "+count+" configuraciones de miembro en colonia");
		}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Miembro verPerfilMiembro(String codeUsuarioTurista, String codeColonia) throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeColonia, String  paramCodeTurista, String paramCodeEstadoMiembro";
		String filter="this.codeColonia==paramCodeColonia && this.codeTurista==paramCodeTurista && this.codeEstadoMiembro!=paramCodeEstadoMiembro";
		try{		
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		String codeEstadoMiembro="D";
		beanObject.add(codeColonia);
		beanObject.add(codeUsuarioTurista);
		beanObject.add(codeEstadoMiembro);
		List<Miembro> listaResult=(List<Miembro>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query);
		Integer count=listaResult.size();
		if(count==0){
			throw new UnknownException("No existe miembro");
		}else if(count==1){
			return listaResult.get(0);
		}else{
			throw new UnknownException("Turista tiene "+count+" configuraciones de miembro en colonia");
		}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getBeanByCodeUsuario(String codeUsuarioTurista) throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeTurista";
		String filter="this.codeTurista==paramCodeTurista && this.estadoVisibilidad=='VISIBLE'";					
		try{
			List<Miembro> lista=new ArrayList<Miembro>();
			Querys consulta=new Querys();
			List listaBeans=new ArrayList();		
			listaBeans.add(codeUsuarioTurista);
			lista.addAll((List<Miembro>)consulta.sendQuery(1, declareParameters, filter, null, listaBeans, query));
			if(!lista.isEmpty()){
				return lista.get(0);
			}
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Miembro> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Miembro> listaLocalidad = (Collection<Miembro>) query
				.getListaBean(Miembro.class);
		return listaLocalidad;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Miembro> getListarBean(Integer limiteMostrar,String codeUsuarioTurista,String estadoVisibilidad) throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeTurista,String paramEstadoMiembro";
		if(!estadoVisibilidad.equalsIgnoreCase("ALL")){
			declareParameters=declareParameters+",String paramEstadoVisibilidad";
		}
		String filter="this.codeTurista==paramCodeTurista && this.codeEstadoMiembro!=paramEstadoMiembro";
		if(!estadoVisibilidad.equalsIgnoreCase("ALL")){
			filter=filter+"&& this.estadoVisibilidad==paramEstadoVisibilidad";
		}
		try{
			List<Miembro> lista=new ArrayList<Miembro>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeUsuarioTurista);								
			beanObject.add("D");
			if(!estadoVisibilidad.equalsIgnoreCase("ALL")){
				beanObject.add(estadoVisibilidad);
			}
			lista.addAll((List<Miembro>)consulta.sendQuery(limiteMostrar, declareParameters, filter, null, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Miembro> getListarBeanByColonia(String codeColonia)throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeColonia,String paramEstadoMiembro";		
		String filter= "codeColonia==paramCodeColonia && this.codeEstadoMiembro!=paramEstadoMiembro";		
		try{
			List<Miembro> lista=new ArrayList<Miembro>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeColonia);
			beanObject.add("D");
			lista.addAll((List<Miembro>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
			if(!lista.isEmpty()){
				return lista;
			}
			return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Miembro> getListarBeanByChatColonia(String codeColonia)throws UnknownException {
		Query query = pm.newQuery(Miembro.class);
		String declareParameters="String paramCodeColonia,String paramEstadoMiembro,Integer paramEstadoChatColonia";		
		String filter= "codeColonia==paramCodeColonia && this.codeEstadoMiembro!=paramEstadoMiembro && this.estadoChatColonia=paramEstadoChatColonia";		
		try{
			List<Miembro> lista=new ArrayList<Miembro>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeColonia);
			beanObject.add("D");
			beanObject.add(1);
			lista.addAll((List<Miembro>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
}
