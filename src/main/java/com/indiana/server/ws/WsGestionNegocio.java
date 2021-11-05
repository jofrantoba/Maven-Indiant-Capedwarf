package com.indiana.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.bean.SesionNegocio;
import com.indiana.server.model.process.GestionBussines;
import com.indiana.server.model.process.GestionDestiny;
import com.indiana.shared.ReturnValue;
import com.indiana.shared.UnknownException;

@Api(name = "gestionNegocio", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))

public class WsGestionNegocio {
		
	@ApiMethod(name = "loginNegocio", path = "loginNegocio")
	public ReturnValue loginNegocio(@Named("wsCorreoNegocio") String correoNegocio,
			@Named("wsClaveNegocio") String claveNegocio) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(SesionNegocio.class);
			valorRetorno.setReturnSesionNegocio(GestionBussines.loginNegocio(correoNegocio, claveNegocio));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}

	@ApiMethod(name = "crearNegocio",path="crearNegocio")
	public ReturnValue crearNegocio(Conquista beanConquista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(MuroNoticia.class);
			valorRetorno.setReturnMuroNoticia(GestionBussines.crearNegocio(beanConquista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "conquistarNegocios",path="conquistarNegocios")
	public ReturnValue conquistarNegocios(
			@Named("cnCorreoTurista")String cdCorreoTurista,
			@Named("cnLatitudTurista")Double cdLatitudTurista,
			@Named("cnLongitudTurista")Double cdLongitudTurista,
			@Named("cnCodeDestino")String cdCodeDestino,
			@Named("cnUrlFotoConquista")String cdUrlFotoConquista,
			@Named("cnDescripcion")String cdDescripcion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(MuroNoticia.class);
			valorRetorno.setReturnMuroNoticia(GestionBussines.conquistarNegocios(cdCorreoTurista, cdLatitudTurista, 
					cdLongitudTurista, cdCodeDestino,cdUrlFotoConquista,cdDescripcion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "buscarDestinos",path="buscarDestinos")
	public ReturnValue buscarDestinos(
			@Nullable @Named("patron")String patron,
			@Nullable @Named("codeCategoria")String codeCategoria,
			@Named("wsNombrePais")String nombrePais,
			@Nullable @Named("wsNombreRegion")String nombreRegion,
			@Nullable @Named("wsNombreLocalidad")String nombreLocalidad) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListDestinos(GestionDestiny.buscarDestinos(patron, codeCategoria, nombrePais, nombreRegion, nombreLocalidad,"NEGOCIO"));				
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	
}
