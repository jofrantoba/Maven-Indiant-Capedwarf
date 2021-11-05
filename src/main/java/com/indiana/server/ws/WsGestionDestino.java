package com.indiana.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.process.GestionDestiny;
import com.indiana.shared.ReturnValue;
import com.indiana.shared.UnknownException;	

@Api(name = "gestionDestino", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))
public class WsGestionDestino {
	
	@ApiMethod(name = "crearDestino",path="crearDestino")
	public ReturnValue crearDestino(Conquista beanConquista) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(MuroNoticia.class);
			valorRetorno.setReturnMuroNoticia(GestionDestiny.crearDestino(beanConquista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	/*
	@ApiMethod(name = "verificarPosTuristaDestino",path="verificarPosTuristaDestino")
	public ReturnValue verificarPosTuristaDestino(
			@Named("vpLatitudTurista")Double latitudTurista,
			@Named("vpLongitudTurista")Double longitudTurista,
			@Named("vpLatitudDestino")Double latitudDestino,
			@Named("vpLongitudDestino")Double longitudDestino,
			@Named("vpRadioDestino")Double radioDestino) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(String.class);
			valorRetorno.setReturnMensajeVerificarPosicion(GestionDestiny.verificarPosTuristaDestino(latitudTurista, longitudTurista, latitudDestino, longitudDestino, radioDestino));				
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}*/
	
	@ApiMethod(name = "conquistarDestinos",path="conquistarDestinos")
	public ReturnValue conquistarDestinos(
			@Named("cdCorreoTurista")String cdCorreoTurista,
			@Named("cdLatitudTurista")Double cdLatitudTurista,
			@Named("cdLongitudTurista")Double cdLongitudTurista,
			@Named("cdCodeDestino")String cdCodeDestino,
			@Named("cdUrlFotoConquista")String cdUrlFotoConquista,
			@Named("cdDescripcion")String cdDescripcion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(MuroNoticia.class);
			valorRetorno.setReturnMuroNoticia(GestionDestiny.conquistarDestinos(cdCorreoTurista, cdLatitudTurista, 
					cdLongitudTurista, cdCodeDestino,cdUrlFotoConquista,cdDescripcion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verFotosConquistaDestino",path="verFotosConquistaDestino")
	public ReturnValue verFotosConquistaDestino(@Named("cdCodeDestino")String cdCodeDestino) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListNoticias(GestionDestiny.verFotosConquistaDestino(cdCodeDestino));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "valorarDestino",path="valorarDestino")
	public ReturnValue valorarDestino(
			@Named("wsCorreoTurista")String wsCorreoTurista,
			@Named("wsCodeDestino")String wsCodeDestino,
			@Named("wsValorCalificacion")Integer wsValorCalificacion,
			@Nullable @Named("wsOpinionCalificacion")String wsOpinionCalificacion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setReturnCalificaDestino(GestionDestiny.calificarDestinoConquistado
					(wsCorreoTurista, wsCodeDestino, wsValorCalificacion, wsOpinionCalificacion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verMuroDestino",path="verMuroDestino")
	public ReturnValue verMuroDestino(
			@Named("gps")Boolean gps,
			@Named("codeDestino")String codeDestino,
			@Named("correoTurista")String correoTurista,
			@Nullable @Named("latitudTurista")Double latitudTurista,
			@Nullable @Named("longitudTurista")Double longitudTurista,
			@Nullable @Named("nombrePais")String nombrePais,
			@Nullable @Named("nombreRegion")String nombreRegion,
			@Nullable @Named("nombreLocalidad")String nombreLocalidad) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Destino.class);
			valorRetorno.setReturnDestino(GestionDestiny.verMuroDestino(gps, codeDestino, correoTurista, latitudTurista, longitudTurista, nombrePais,nombreRegion,nombreLocalidad));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarDestinos",path="listarDestinos")
	public ReturnValue listarDestinos(
			@Named("wsGps")Boolean gps,
			@Named("wsCorreoTurista")String correoTurista,
			@Nullable @Named("wsNombrePais")String nombrePais,
			@Nullable @Named("wsNombreRegion")String nombreRegion,
			@Nullable @Named("wsNombreLocalidad")String nombreLocalidad,
			@Nullable @Named("wsLatitudTurista")Double latitudTurista,
			@Nullable @Named("wsLongitudTurista")Double longitudTurista,
			@Named("wsLimiteMostrar")Integer limiteMostrar,
			@Named("wsDistanciaBusqueda")Double distanciaBusqueda,
			@Named("wsTipoNegocioDestino")String tipoNegocioDestino ) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListDestinos(GestionDestiny.listarDestinos(gps, nombrePais, nombreRegion, nombreLocalidad, 
					correoTurista, latitudTurista, longitudTurista,limiteMostrar, distanciaBusqueda,tipoNegocioDestino));
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
			valorRetorno.setReturnListDestinos(GestionDestiny.buscarDestinos(patron, codeCategoria, nombrePais, nombreRegion, nombreLocalidad,"DESTINO"));				
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}			
}
