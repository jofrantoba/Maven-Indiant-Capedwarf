package com.indiana.server.ws;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.server.model.bean.CategoriaDestino;
import com.indiana.server.model.bean.CategoriaNegocio;
import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.server.model.bean.EstadoMiembro;
import com.indiana.server.model.bean.EstadoSolicAmistad;
import com.indiana.server.model.bean.FormaPago;
import com.indiana.server.model.bean.Idioma;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Moneda;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.bean.Privacidad;
import com.indiana.server.model.bean.RedSocial;
import com.indiana.server.model.bean.Region;
import com.indiana.server.model.bean.Tarifario;
import com.indiana.server.model.bean.TipoAnuncio;
import com.indiana.server.model.bean.TipoCambio;
import com.indiana.server.model.bean.TipoEmpatia;
import com.indiana.server.model.bean.TipoInterPublicidad;
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.server.model.process.GestionMantenimiento;
import com.indiana.shared.UnknownException;

@Api(name = "gestionMantenimiento", namespace = @ApiNamespace(ownerDomain = "indiana.com", ownerName = "indiana.com", packagePath = "server.ws"))
public class WsGestionMantenimiento {
	
	/************************ Gestion Casa Tarjeta ************************************/

	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listCasaTarjeta",path="listCasaTarjeta")
	public List<CasaTarjeta> listCasaTarjeta() throws UnknownException {		
		return (List<CasaTarjeta>) GestionMantenimiento.getList(CasaTarjeta.class,null);
	}
	/************************ Gestion Entidad Financiera ************************************/
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listEntidadFinanciera",path="listEntidadFinanciera")
	public List<EntidadFinanciera> listEntidadFinanaciera() throws UnknownException {		
		return (List<EntidadFinanciera>) GestionMantenimiento.getList(EntidadFinanciera.class,null);
	}
	
	/************************ Gestion Forma de Pago ************************************/
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listFormaPago",path="listFormaPago")
	public List<FormaPago> listFormaPago() throws UnknownException {		
		return (List<FormaPago>) GestionMantenimiento.getList(FormaPago.class,null);
	}
	
	/************************ Gestion Parametros del Sistema ************************************/
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listParametrosSistema",path="listParametrosSistema")
	public List<ParametrosSistema> listParametrosSistema() throws UnknownException {		
		return (List<ParametrosSistema>) GestionMantenimiento.getList(ParametrosSistema.class,null);
	}
	
	/************************ Gestion Tarifario ************************************/
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listTarifario",path="listTarifario")
	public List<Tarifario> listTarifario() throws UnknownException {		
		return (List<Tarifario>) GestionMantenimiento.getList(Tarifario.class,null);
	}
	
	/************************ Gestion Tipo Interaccion Publicidad ************************************/	
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listTipoInterPublicidad",path="listTipoInterPublicidad")
	public List<TipoInterPublicidad> listTipoInterPublicidad() throws UnknownException {		
		return (List<TipoInterPublicidad>) GestionMantenimiento.getList(TipoInterPublicidad.class,null);
	}
	
	
	
	
	/************************ Gestion Categoria Destino ************************************/	
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "listCategoriaDestino",path="listCategoriaDestino")
	public List<CategoriaDestino> listCategoriaDestino() throws UnknownException {		
		return (List<CategoriaDestino>) GestionMantenimiento.getList(CategoriaDestino.class,null);
	}

	/************************ Fin Gestión Categoría Destino ************************************/

	/************************ Gestion Categoria Negocio ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listCategoriaNegocio",path="listCategoriaNegocio")
	public List<CategoriaNegocio> listCategoriaNegocio() throws UnknownException {
		return (List<CategoriaNegocio>)GestionMantenimiento.getList(CategoriaNegocio.class,null);
	}

	/************************ Fin Gestion Categoria Negocio ************************************/

	

	/************************ Gestion Estado Amistad ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listEstadoAmistad",path="listEstadoAmistad")
	public List<EstadoAmistad> listEstadoAmistad() throws UnknownException {
		return (List<EstadoAmistad>)GestionMantenimiento.getList(EstadoAmistad.class,null);
	}

	/************************ Fin Gestion Estado Amistad ************************************/

	/************************ Gestion Estado Cuenta ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listEstadoCuenta",path="listEstadoCuenta")
	public List<EstadoCuenta> listEstadoCuenta() throws UnknownException {
		return (List<EstadoCuenta>)GestionMantenimiento.getList(EstadoCuenta.class,null);
	}

	/************************ Fin Gestion Estado Cuenta ************************************/

	/************************ Gestion Estado Miembro ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listEstadoMiembro",path="listEstadoMiembro")
	public List<EstadoMiembro> listEstadoMiembro() throws UnknownException {
		return (List<EstadoMiembro>)GestionMantenimiento.getList(EstadoMiembro.class,null);
	}

	/************************ Fin Gestion Estado Miembro ************************************/

	/************************ Gestion Estado Solicitud Amistad ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listEstadoSolicAmistad",path="listEstadoSolicAmistad")
	public List<EstadoSolicAmistad> listEstadoSolicAmistad() throws UnknownException {
		return (List<EstadoSolicAmistad>)GestionMantenimiento.getList(EstadoSolicAmistad.class,null);
	}

	/************************ Fin Gestion Solic Amistad ************************************/

	/************************ Gestion Idioma ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listIdioma",path="listIdioma")
	public List<Idioma> listIdioma() throws UnknownException {
		return (List<Idioma>)GestionMantenimiento.getList(Idioma.class,null);
	}

	/************************ Gestion Idioma ************************************/

	/************************ Gestion Localidad ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listLocalidad",path="listLocalidad")
	public List<Localidad> listLocalidad() throws UnknownException {
		return (List<Localidad>)GestionMantenimiento.getList(Localidad.class,null);
	}

	/************************ Fin Gestion Localidad ************************************/

	/************************ Gestion Moneda ************************************/
	@SuppressWarnings({"unchecked"})
	@ApiMethod(name = "listMoneda",path="listMoneda")
	public List<Moneda> listMoneda() throws UnknownException {
		return (List<Moneda>) GestionMantenimiento.getList(Moneda.class,null);
	}

	/************************ Fin Gestion Moneda ************************************/

	/************************ Gestion Pais ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listPais",path="listPais")
	public List<Pais> listPais() throws UnknownException {
		return (List<Pais>)GestionMantenimiento.getList(Pais.class,null);
	}

	/************************ Fin Gestion Pais ************************************/

	/************************ Gestion Pais Moneda ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listPaisMoneda",path="listPaisMoneda")
	public List<PaisMoneda> listPaisMoneda() throws UnknownException {
		return (List<PaisMoneda>)GestionMantenimiento.getList(PaisMoneda.class,null);
	}

	/************************ Gestion Pais Moneda ************************************/

	/************************ Gestion Privacidad ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listPrivacidad",path="listPrivacidad")
	public List<Privacidad> listPrivacidad() throws UnknownException {
		return (List<Privacidad>)GestionMantenimiento.getList(Privacidad.class,null);
	}

	/************************ Fin Gestion Privacidad ************************************/

	/************************ Gestion Red Social ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listRedSocial",path="listRedSocial")
	public List<RedSocial> listRedSocial() throws UnknownException {
		return (List<RedSocial>)GestionMantenimiento.getList(RedSocial.class,null);
	}

	/************************ Gestion Red Social ************************************/

	/************************ Gestion Region ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listRegion",path="listRegion")
	public List<Region> listRegion() throws UnknownException {
	return (List<Region>)GestionMantenimiento.getList(Region.class,null);	
	}

	/************************ Fin Gestion Region ************************************/

	/************************ Gestion Tipo Anuncio ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listTipoAnuncio",path="listTipoAnuncio")
	public List<TipoAnuncio> listTipoAnuncio() throws UnknownException {
		return (List<TipoAnuncio>)GestionMantenimiento.getList(TipoAnuncio.class,null);
	}

	/************************ Fin Gestion Tipo Anuncio ************************************/

	/************************ Gestion Tipo Cambio ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listTipoCambio",path="listTipoCambio")
	public List<TipoCambio> listTipoCambio() throws UnknownException {
		return (List<TipoCambio>)GestionMantenimiento.getList(TipoCambio.class,null);
	}

	/************************ Fin Gestion Tipo Cambio ************************************/

	/************************ Gestion Tipo Empatia ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listTipoEmpatia",path="listTipoEmpatia")
	public List<TipoEmpatia> listTipoEmpatia() throws UnknownException {
		return (List<TipoEmpatia>)GestionMantenimiento.getList(TipoEmpatia.class,null);
	}

	/************************ Fin Gestion Tipo Empatia ************************************/

	/************************ Gestion Tipo Movimiento ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listTipoMovimiento",path="listTipoMovimiento")
	public List<TipoMovimiento> listTipoMovimiento() throws UnknownException {
		return (List<TipoMovimiento>)GestionMantenimiento.getList(TipoMovimiento.class,null);
	}

	/************************ Fin Gestion Tipo Movimiento ************************************/

	/************************ Gestion Tipo Notificacion  ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listTipoNotificacion",path="listTipoNotificacion")
	public List<TipoNotificacion> listTipoNotificacion() throws UnknownException {
		return (List<TipoNotificacion>) GestionMantenimiento.getList(TipoNotificacion.class,null);
	}

	/************************ Fin Gestion Tipo Notificacion ************************************/

	/************************ Gestion Tipo Suscripcion ************************************/
	@SuppressWarnings({ "unchecked"})
	@ApiMethod(name = "listTipoSuscripcion",path="listTipoSuscripcion")
	public List<TipoSuscripcion> listTipoSuscripcion() throws UnknownException {
		return (List<TipoSuscripcion>)GestionMantenimiento.getList(TipoSuscripcion.class,null);
	}

	/************************ Fin Gestion Tipo Suscripcion ************************************/

}
