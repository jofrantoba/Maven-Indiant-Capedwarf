package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.ParticipanteChatMiembro;
import com.indiana.server.model.dao.DaoParticipanteChatMiembro;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicParticipanteChatMiembro{
	private PersistenceManager pm;

	public LogicParticipanteChatMiembro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoParticipanteChatMiembro dao = new DaoParticipanteChatMiembro(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id){
		try{
		DaoParticipanteChatMiembro dao = new DaoParticipanteChatMiembro(this.pm);
		return dao.getBean(id);
		}catch(Exception ex){
			return null;
		}
	}
	
	public Object getBeanByTuristaTablon(String correoTurista,String codeTablon) throws UnknownException {
		DaoParticipanteChatMiembro dao = new DaoParticipanteChatMiembro(this.pm);
		return dao.getBeanByTuristaTablon(correoTurista, codeTablon);
	}

	public Collection<ParticipanteChatMiembro> getListarBean() throws UnknownException {
		DaoParticipanteChatMiembro dao = new DaoParticipanteChatMiembro(this.pm);
		Collection<ParticipanteChatMiembro> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<ParticipanteChatMiembro> getListarBeanByTablonColonia(String codeTablonColonia) throws UnknownException {
		DaoParticipanteChatMiembro dao = new DaoParticipanteChatMiembro(this.pm);
		Collection<ParticipanteChatMiembro> lista = dao.getListarBeanByTablonColonia(codeTablonColonia);
		return lista;
	}
}