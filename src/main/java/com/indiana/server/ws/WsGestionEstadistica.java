package com.indiana.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.process.GestionEstadistica;
import com.indiana.shared.ReturnValue;
import com.indiana.shared.UnknownException;
@Api(name = "gestionEstadistica", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))

public class WsGestionEstadistica {

	@ApiMethod(name = "indicadoresEstadisticos",path="indicadoresEstadisticos")
	public ReturnValue indicadoresEstadisticos(
			@Named("ieCorreoTurista")String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);						
			valorRetorno.setReturnUsuarioTurista(GestionEstadistica.indicadoresEstadisticos(correoTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "ieColoniasCreadas",path="ieColoniasCreadas")
	public ReturnValue ieColoniasCreadas(
			@Named("ieCorreoTurista")String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);						
			valorRetorno.setReturnUsuarioTurista(GestionEstadistica.indicadoresEstadisticos(correoTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	@ApiMethod(name = "ieDestinosCreados",path="ieDestinosCreados")
	public ReturnValue ieDestinosCreados(
			@Named("ieCorreoTurista")String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);						
			valorRetorno.setReturnUsuarioTurista(GestionEstadistica.indicadoresEstadisticos(correoTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	@ApiMethod(name = "ieConquistasDistintas",path="ieConquistasDistintas")
	public ReturnValue ieConquistasDistintas(
			@Named("ieCorreoTurista")String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);						
			valorRetorno.setReturnUsuarioTurista(GestionEstadistica.indicadoresEstadisticos(correoTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "iePrimerosAfiliados",path="iePrimerosAfiliados")
	public ReturnValue iePrimerosAfiliados(
			@Named("ieCorreoTurista")String correoTurista){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioTurista.class);						
			valorRetorno.setReturnUsuarioTurista(GestionEstadistica.indicadoresEstadisticos(correoTurista));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "ieModaInteresTuristaByColoniaInteres",path="ieModaInteresTuristaByColoniaInteres")
	public ReturnValue ieModaInteresTuristaByColoniaInteres(
			@Nullable @Named("iemciAnnio")Integer annio,
			@Nullable @Named("iemciMes")Integer mes,
			@Nullable @Named("iemciDia")Integer dia,
			@Nullable @Named("iemciEdad")Integer edad,
			@Nullable @Named("iemciNombreColonia")String nombreColonia,
			@Nullable @Named("iemciNacionalidad")String nacionalidad){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListColoniaInteres(GestionEstadistica.ieModaInteresTuristaByColoniaInteres(annio, mes, dia, edad, nombreColonia, nacionalidad));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "ieModaInteresTuristaByTuristaInteres",path="ieModaInteresTuristaByTuristaInteres")
	public ReturnValue ieModaInteresTuristaByTuristaInteres(
			@Nullable @Named("iemtiAnnio")Integer annio,
			@Nullable @Named("iemtiMes")Integer mes,
			@Nullable @Named("iemtiDia")Integer dia,
			@Nullable @Named("iemtiRangoEdad")String edad,
			@Nullable @Named("iemtiNombreColonia")String nombreColonia,
			@Nullable @Named("iemtiNacionalidad")String nacionalidad){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListTuristaInteres(GestionEstadistica.ieModaInteresTuristaByTuristaInteres(annio, mes, dia, edad, nombreColonia, nacionalidad));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "ieModaInteresTuristaByMiembroInteres",path="ieModaInteresTuristaByMiembroInteres")
	public ReturnValue ieModaInteresTuristaByMiembroInteres(
			@Nullable @Named("iemmiAnnio")Integer annio,
			@Nullable @Named("iemmiMes")Integer mes,
			@Nullable @Named("iemmiDia")Integer dia,
			@Nullable @Named("iemmiEdad")Integer edad,
			@Nullable @Named("iemmiNombreColonia")String nombreColonia,
			@Nullable @Named("iemmiNacionalidad")String nacionalidad){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListMiembroInteres(GestionEstadistica.ieModaInteresTuristaByMiembroInteres(annio, mes, dia, edad, nombreColonia, nacionalidad));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "ieCantidadConquistas",path="ieCantidadConquistas")
	public ReturnValue ieCantidadConquistas(
			@Nullable @Named("ieccAnnio")Integer annio,
			@Nullable @Named("ieccMes")Integer mes,
			@Nullable @Named("ieccDia")Integer dia,
			@Nullable @Named("ieccEdad")Integer edad,
			@Nullable @Named("ieccNombreDestino")String nombreDestino,
			@Nullable @Named("ieccNacionalidad")String nacionalidad){
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);						
			valorRetorno.setReturnListConquistas(GestionEstadistica.ieCantidadConquistas(annio, mes, dia, edad, nombreDestino, nacionalidad));			
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
}
