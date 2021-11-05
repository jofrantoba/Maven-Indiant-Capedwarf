package com.indiana.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.shared.UnknownException;

@RemoteServiceRelativePath("servicegestionnegocio")
public interface ServiceGestionNegocio extends RemoteService {
	UsuarioNegocio logeoUsuarioNegocio(String correo, String clave) throws UnknownException;
}
