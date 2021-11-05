package com.indiana.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.process.GestionColony;
import com.indiana.server.model.process.GestionShared;
import com.indiana.shared.ReturnValue;
import com.indiana.shared.UnknownException;

@Api(name = "gestionColonia", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))
public class WsGestionColonia {
		
	@ApiMethod(name = "listColoniaInteres",path="listColoniaInteres")
	public ReturnValue listColoniaInteres(@Named("wsCodeColonia")String wsCodeColonia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListColoniaInteres(GestionColony.listColoniaInteres(wsCodeColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}

	@ApiMethod(name = "listDestinosProximos",path="listDestinosProximos")
	public ReturnValue listDestinosProximos(
			@Named("wsLatitudColonia")Double latitudColonia,
			@Named("wsLongitudColonia")Double longitudColonia,
			@Named("wsRadioColonia")Double radioColonia,
			@Named("wsNombrePaisColonia")String nombrePaisColonia,
			@Nullable @Named("wsNombreRegionColonia")String nombreRegionColonia,
			@Nullable @Named("wsNombreLocalidadColonia")String nombreLocalidadColonia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListProxColoniaDestino(GestionColony.listDestinosYNegociosProximos
					(latitudColonia, longitudColonia, radioColonia, nombrePaisColonia, nombreRegionColonia,nombreLocalidadColonia));					
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "distanciaDeTuristaAColonia",path="distanciaDeTuristaAColonia")
	public ReturnValue distanciaDeTuristaAColonia(
			@Named("wsLatitudColonia")Double wsLatitudColonia,
			@Named("wsLongitudColonia")Double wsLongitudColonia,
			@Named("wsLatitudTurista")Double wsLatitudTurista,
			@Named("wsLongitudTurista")Double wsLongitudTurista
			){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Double.class);
			valorRetorno.setValueReturn(GestionShared.retornarDistancia(wsLatitudColonia, wsLongitudColonia, wsLatitudTurista, wsLongitudTurista));
		}catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarColonias",path="listarColonias")
	public ReturnValue listarColonia(
			@Named("wsGps") Boolean gps,
			@Named("wsNombrePais") String nombrePais,
			@Nullable @Named("wsNombreRegion") String nombreRegion,
			@Named("wsNombreLocalidad") @Nullable String nombreLocalidad,
			@Named("wsCorreoTurista") String correoTurista,
			@Named("wsLatitudTurista") @Nullable Double latitudTurista,
			@Named("wsLongitudTurista") @Nullable Double longitudTurista,
			@Named("wsLimiteMostrar") Integer limiteMostrar,
			@Named("wsDistanciaBusqueda") Double distanciaBusqueda
			){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListColonias(GestionColony.listarColonias(gps, nombrePais, nombreRegion, nombreLocalidad, 
					correoTurista, latitudTurista, longitudTurista,limiteMostrar, distanciaBusqueda));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verPerfilColonia",path="verPerfilColonia")
	public ReturnValue verPerfilColonia(@Named("wsCodeColonia") String wsCodeColonia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Colonia.class);
			valorRetorno.setReturnColonia(GestionColony.verPerfilColonia(wsCodeColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "crearColonia",path="crearColonia")
	public ReturnValue crearColonia(Colonia wsbeanColonia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Colonia.class);
			valorRetorno.setReturnColonia(GestionColony.crearColonia(wsbeanColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "unirseColonia",path="unirseColonia")
	public ReturnValue unirseColonia(Colonia wsbeanColonia) {				
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Colonia.class);			
			valorRetorno.setReturnColonia(GestionColony.unirseColonia(wsbeanColonia));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "esMiembroDeColonia",path="esMiembroDeColonia")
	public ReturnValue esMiembroDeColonia(
			@Named("wsCodeColonia") String wsCodeColonia,
			@Named("wsCodeUsuarioTurista") String wsCodeUsuarioTurista) {				
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);			
			valorRetorno.setValueReturn(GestionColony.esMiembroDeColonia(wsCodeColonia, wsCodeUsuarioTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verPerfilMiembro",path="verPerfilMiembro")
	public ReturnValue verPerfilMiembro(
			@Named("wsCodeColonia") String wsCodeColonia,
			@Named("wsCodeUsuarioTurista") String wsCodeUsuarioTurista) {				
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Miembro.class);			
			valorRetorno.setReturnMiembro(GestionColony.verPerfilMiembro(wsCodeColonia, wsCodeUsuarioTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "entrarColonia",path="entrarColonia")
	public ReturnValue entrarColonia(
			@Named("wsCodeColonia") String wsCodeColonia,
			@Named("wsCodeUsuarioTurista") String wsCodeUsuarioTurista,
			@Named("wsLatitudTurista") @Nullable Double wsLatitudTurista,
			@Named("wsLongitudTurista") @Nullable Double wsLongitudTurista,
			@Named("wsNombrePais") @Nullable String wsNombrePais,
			@Named("wsNombreRegion") @Nullable String wsNombreRegion,
			@Named("wsNombreLocalidad") @Nullable String wsNombreLocalidad,
			@Named("wsGps") Boolean wsGps
			) {				
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Colonia.class);			
			valorRetorno.setReturnColonia(GestionColony.entrarColonia(wsCodeColonia, wsCodeUsuarioTurista, wsLatitudTurista, wsLongitudTurista,wsNombrePais,wsNombreRegion,wsNombreLocalidad, wsGps));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "abandonarColonia",path="abandonarColonia")
	public ReturnValue abandonarColonia(
			@Named("wsCodeColonia") String wsCodeColonia,
			@Named("wsCodeUsuarioTurista") String wsCodeUsuarioTurista,
			@Named("wsLatitudTurista") @Nullable Double wsLatitudTurista,
			@Named("wsLongitudTurista") @Nullable Double wsLongitudTurista,
			@Named("wsNombrePais") @Nullable String wsNombrePais,
			@Named("wsNombreRegion") @Nullable String wsNombreRegion,
			@Named("wsNombreLocalidad") @Nullable String wsNombreLocalidad,
			@Named("wsGps") Boolean wsGps
			) {				
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);			
			valorRetorno.setValueReturn(GestionColony.abandonarColonia(wsCodeColonia, wsCodeUsuarioTurista, wsLatitudTurista, wsLongitudTurista,wsNombrePais,wsNombreRegion,wsNombreLocalidad, wsGps));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "misColonias",path="misColonias")
	public ReturnValue misColonia(@Named("wsCorreoTurista") String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListColonias(GestionColony.misColonias(correoTurista));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "verMuroColonia",path="verMuroColonia")
	public ReturnValue verMuroColonia(@Named("wsLimiteInferior")Integer wsLimiteInferior,@Named("wsLimiteSuperior")Integer wsLimiteSuperior,@Named("wsCodeColonia")String wsCodeColonia){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListNoticias(GestionColony.verMuroColonia(wsLimiteInferior, wsLimiteSuperior, wsCodeColonia));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "buscarColonias",path="buscarColonias")
	public ReturnValue buscarColonias(
			@Nullable @Named("bcPatron")String patron,
			@Named("bcNombrePais")String nombrePais,
			@Nullable @Named("bcNombreRegion")String nombreRegion){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListColonias(GestionColony.buscarColonias(patron, nombrePais, nombreRegion));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
}
