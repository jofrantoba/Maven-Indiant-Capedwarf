package com.indiana.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.TuristaInteres;

public interface ServiceGestionUsuarioAsync {

	void ieModaInteresTuristaByColoniaInteres(Integer annio, Integer mes, Integer dia, Integer edad,
			String nombreColonia, String nacionalidad, AsyncCallback<List<ColoniaInteres>> callback);

	void ieCantidadConquistas(Integer annio, Integer mes, Integer dia, Integer edad, String nombreDestino,
			String nacionalidad, AsyncCallback<List<Conquista>> callback);

	void ieModaInteresTuristaByMiembroInteres(Integer annio, Integer mes, Integer dia, Integer edad,
			String nombreColonia, String nacionalidad, AsyncCallback<List<MiembroInteres>> callback);

	void ieModaInteresTuristaByTuristaInteres(Integer annio, Integer mes, Integer dia, String edad,
			String nombreColonia, String nacionalidad, AsyncCallback<List<TuristaInteres>> callback);

}
