package com.indiana.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.shared.UnknownException;

@RemoteServiceRelativePath("servicegestionusuario")
public interface ServiceGestionUsuario extends RemoteService {
	List<ColoniaInteres> ieModaInteresTuristaByColoniaInteres(Integer annio,Integer mes,Integer dia,
			Integer edad,String nombreColonia,String nacionalidad) throws UnknownException;
	
	List<TuristaInteres> ieModaInteresTuristaByTuristaInteres(Integer annio, Integer mes, Integer dia, String edad,
			String nombreColonia, String nacionalidad) throws UnknownException;
	
	List<MiembroInteres> ieModaInteresTuristaByMiembroInteres(Integer annio,Integer mes,Integer dia,
			Integer edad,String nombreColonia,String nacionalidad) throws UnknownException;
	
	List<Conquista> ieCantidadConquistas(Integer annio, Integer mes, Integer dia, Integer edad,
			String nombreDestino, String nacionalidad) throws UnknownException;
	
}
