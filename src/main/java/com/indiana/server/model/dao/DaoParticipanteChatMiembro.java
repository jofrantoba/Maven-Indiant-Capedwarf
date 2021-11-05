package com.indiana.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.indiana.server.model.bean.ParticipanteChatMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class DaoParticipanteChatMiembro {
	private PersistenceManager pm;

	public DaoParticipanteChatMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ParticipanteChatMiembro.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ParticipanteChatMiembro getBeanByTuristaTablon(String correoTurista,String codeTablon) throws UnknownException {
		Query query = pm.newQuery(ParticipanteChatMiembro.class);
		String declareParameters="String paramCorreoTurista,String paramCodeTablon, String paramEstadoParticipante";		
		String filter= "codeTuristaParticipante==paramCorreoTurista && codeTablonColonia==paramCodeTablon && estadoParticipante==paramEstadoParticipante";		
		try{
			List<ParticipanteChatMiembro> lista=new ArrayList<ParticipanteChatMiembro>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();			
			beanObject.add(correoTurista);
			beanObject.add(codeTablon);
			beanObject.add("A");
			lista.addAll((List<ParticipanteChatMiembro>)consulta.sendQuery(1, declareParameters, filter, null, beanObject, query));
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
	public Collection<ParticipanteChatMiembro> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ParticipanteChatMiembro> lista = (Collection<ParticipanteChatMiembro>) query
				.getListaBean(ParticipanteChatMiembro.class);
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<ParticipanteChatMiembro> getListarBeanByTablonColonia(String codeTablonColonia) throws UnknownException {
		Query query = pm.newQuery(ParticipanteChatMiembro.class);
		String declareParameters="String paramCodeTablonColonia, String paramEstadoParticipante";		
		String filter= "codeTablonColonia == paramCodeTablonColonia && estadoParticipante == paramEstadoParticipante";		
		try{
			List<ParticipanteChatMiembro> lista=new ArrayList<ParticipanteChatMiembro>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(codeTablonColonia);		
			beanObject.add("A");	
			lista.addAll((List<ParticipanteChatMiembro>)consulta.sendQuery(null, declareParameters, filter, null, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}	
}
