package com.indiana.server.control.rpc;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.indiana.client.service.ServiceGestionUsuario;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.process.GestionEstadistica;
import com.indiana.shared.UnknownException;

public class ServiceGestionUsuarioImpl extends RemoteServiceServlet implements ServiceGestionUsuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2631664790983924802L;

	@Override
	public List<ColoniaInteres> ieModaInteresTuristaByColoniaInteres(Integer annio, Integer mes, Integer dia,
			Integer edad, String nombreColonia, String nacionalidad) throws UnknownException {
		// TODO Auto-generated method stub
		return GestionEstadistica.ieModaInteresTuristaByColoniaInteres(annio, mes, dia, edad, nombreColonia, nacionalidad);
	}

	@Override
	public List<MiembroInteres> ieModaInteresTuristaByMiembroInteres(Integer annio, Integer mes, Integer dia,
			Integer edad, String nombreColonia, String nacionalidad) throws UnknownException {
		// TODO Auto-generated method stub
		return GestionEstadistica.ieModaInteresTuristaByMiembroInteres(annio, mes, dia, edad, nombreColonia, nacionalidad);
	}

	@Override
	public List<Conquista> ieCantidadConquistas(Integer annio, Integer mes, Integer dia, Integer edad,
			String nombreDestino, String nacionalidad) throws UnknownException {
		// TODO Auto-generated method stub
		return GestionEstadistica.ieCantidadConquistas(annio, mes, dia, edad, nombreDestino, nacionalidad);
	}

	@Override
	public List<TuristaInteres> ieModaInteresTuristaByTuristaInteres(Integer annio, Integer mes, Integer dia,
			String edad, String nombreColonia, String nacionalidad) throws UnknownException  {
		// TODO Auto-generated method stub
		return GestionEstadistica.ieModaInteresTuristaByTuristaInteres(annio, mes, dia, edad, nombreColonia, nacionalidad);
	}

}