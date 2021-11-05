package com.indiana.server.control.rpc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.indiana.client.service.ServiceGestionNegocio;
import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.shared.UnknownException;

public class ServiceGestionNegocioImpl extends RemoteServiceServlet implements ServiceGestionNegocio{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1533406727958622583L;

	@Override
	public UsuarioNegocio logeoUsuarioNegocio(String correo, String clave) throws UnknownException {
		// TODO Auto-generated method stub
		return null;
	}

}
