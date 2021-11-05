package com.indiana.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.server.model.bean.UsuarioNegocio;

public interface ServiceGestionNegocioAsync {

	void logeoUsuarioNegocio(String correo, String clave, AsyncCallback<UsuarioNegocio> callback);

}
