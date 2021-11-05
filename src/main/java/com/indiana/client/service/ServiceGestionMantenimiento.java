package com.indiana.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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
import com.indiana.server.model.bean.TipoCambio;
import com.indiana.server.model.bean.TipoEmpatia;
import com.indiana.server.model.bean.TipoInterPublicidad;
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.shared.ListFilterBean;
import com.indiana.shared.UnknownException;

@RemoteServiceRelativePath("servicegestionmantenimiento")
public interface ServiceGestionMantenimiento extends RemoteService {

	UsuarioAdmin logeoUsuarioAdmin(String correo, String clave) throws UnknownException;

	UsuarioAdmin insertUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) throws UnknownException;

	UsuarioAdmin updateUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) throws UnknownException;

	Boolean removeUsuarioAdmin(String correoUsuarioAdmin) throws UnknownException;

	List<UsuarioAdmin> listUsuarioAdmin() throws UnknownException;
	
	CasaTarjeta insertCasaTarjeta(CasaTarjeta casaTarjeta) throws UnknownException;

	CasaTarjeta updateCasaTarjeta(CasaTarjeta casaTarjeta) throws UnknownException;

	Boolean removeCasaTarjeta(String idCasaTarjeta) throws UnknownException;

	List<CasaTarjeta> listCasaTarjeta() throws UnknownException;

	CategoriaDestino insertCategoriaDestino(CategoriaDestino categoriaDestino) throws UnknownException;

	CategoriaDestino updateCategoriaDestino(CategoriaDestino categoriaDestino) throws UnknownException;

	Boolean removeCategoriaDestino(String idCategoriaDestino) throws UnknownException;

	List<CategoriaDestino> listCategoriaDestino() throws UnknownException;

	CategoriaNegocio insertCategoriaNegocio(CategoriaNegocio categoriaNegocio) throws UnknownException;

	CategoriaNegocio updateCategoriaNegocio(CategoriaNegocio categoriaNegocio) throws UnknownException;

	List<CategoriaNegocio> listCategoriaNegocio() throws UnknownException;

	Boolean removeCategoriaNegocio(String idCategoriaNegocio) throws UnknownException;

	EntidadFinanciera insertEntidadFinanciera(EntidadFinanciera entidadFinanciera) throws UnknownException;

	EntidadFinanciera updateEntidadFinanciera(EntidadFinanciera entidadFinanciera) throws UnknownException;

	Boolean removeEntidadFinanciera(String idEntidadFinanciera) throws UnknownException;

	List<EntidadFinanciera> listEntidadFinanciera() throws UnknownException;

	EstadoAmistad insertEstadoAmistad(EstadoAmistad estadoAmistad) throws UnknownException;

	EstadoAmistad updateEstadoAmistad(EstadoAmistad estadoAmistad) throws UnknownException;

	Boolean removeEstadoAmistad(String idEstadoAmistad) throws UnknownException;

	List<EstadoAmistad> listEstadoAmistad() throws UnknownException;

	EstadoCuenta insertEstadoCuenta(EstadoCuenta estadoCuenta) throws UnknownException;

	EstadoCuenta updateEstadoCuenta(EstadoCuenta estadoCuenta) throws UnknownException;

	Boolean removeEstadoCuenta(String idEstadoCuenta) throws UnknownException;

	List<EstadoCuenta> listEstadoCuenta() throws UnknownException;

	EstadoMiembro insertEstadoMiembro(EstadoMiembro estadoMiembro) throws UnknownException;

	EstadoMiembro updateEstadoMiembro(EstadoMiembro estadoMiembro) throws UnknownException;

	Boolean removeEstadoMiembro(String idEstadoMiembro) throws UnknownException;

	List<EstadoMiembro> listEstadoMiembro() throws UnknownException;

	EstadoSolicAmistad insertEstadoSolicAmistad(EstadoSolicAmistad estadoSolicAmistad) throws UnknownException;

	EstadoSolicAmistad updateEstadoSolicAmistad(EstadoSolicAmistad estadoSolicAmistad) throws UnknownException;

	Boolean removeEstadoSolicAmistad(String idEstadoSolicAmistad) throws UnknownException;

	List<EstadoSolicAmistad> listEstadoSolicAmistad() throws UnknownException;

	FormaPago insertFormaPago(FormaPago formaPago) throws UnknownException;

	FormaPago updateFormaPago(FormaPago formaPago) throws UnknownException;

	Boolean removeFormaPago(String idFormaPago) throws UnknownException;

	List<FormaPago> listFormaPago() throws UnknownException;

	Idioma insertIdioma(Idioma idioma) throws UnknownException;

	Idioma updateIdioma(Idioma idioma) throws UnknownException;

	Boolean removeIdioma(String idIdioma) throws UnknownException;

	List<Idioma> listIdioma() throws UnknownException;

	Localidad insertLocalidad(Localidad localidad) throws UnknownException;

	Localidad updateLocalidad(Localidad localidad) throws UnknownException;

	Boolean removeLocalidad(String idLocalidad) throws UnknownException;

	List<Localidad> listLocalidad() throws UnknownException;

	Moneda insertMoneda(Moneda moneda) throws UnknownException;

	Moneda updateMoneda(Moneda moneda) throws UnknownException;

	Boolean removeMoneda(String idMoneda) throws UnknownException;

	List<Moneda> listMoneda(ListFilterBean filter) throws UnknownException;

	Pais insertPais(Pais pais) throws UnknownException;

	Pais updatePais(Pais pais) throws UnknownException;

	Boolean removePais(String idPais) throws UnknownException;

	List<Pais> listPais() throws UnknownException;

	PaisMoneda insertPaisMoneda(PaisMoneda paisMoneda) throws UnknownException;

	PaisMoneda updatePaisMoneda(PaisMoneda paisMoneda) throws UnknownException;

	Boolean removePaisMoneda(String idPaisMoneda) throws UnknownException;

	List<PaisMoneda> listPaisMoneda() throws UnknownException;

	ParametrosSistema insertParametrosSistema(ParametrosSistema parametrosSistema) throws UnknownException;

	ParametrosSistema updateParametrosSistema(ParametrosSistema parametrosSistema) throws UnknownException;

	Boolean removeParametrosSistema(String idParametrosSistema) throws UnknownException;

	List<ParametrosSistema> listParametrosSistema() throws UnknownException;

	Privacidad insertPrivacidad(Privacidad privacidad) throws UnknownException;

	Privacidad updatePrivacidad(Privacidad privacidad) throws UnknownException;

	Boolean removePrivacidad(String idPrivacidad) throws UnknownException;

	List<Privacidad> listPrivacidad() throws UnknownException;

	RedSocial insertRedSocial(RedSocial redSocial) throws UnknownException;

	RedSocial updateRedSocial(RedSocial redSocial) throws UnknownException;

	Boolean removeRedSocial(String idRedSocial) throws UnknownException;

	List<RedSocial> listRedSocial() throws UnknownException;

	Region insertRegion(Region region) throws UnknownException;

	Region updateRegion(Region region) throws UnknownException;

	Boolean removeRegion(String idRegion) throws UnknownException;

	List<Region> listRegion(ListFilterBean filter) throws UnknownException;

	Tarifario insertTarifario(Tarifario tarifario) throws UnknownException;

	Tarifario updateTarifario(Tarifario tarifario) throws UnknownException;

	Boolean removeTarifario(String idTarifario) throws UnknownException;

	List<Tarifario> listTarifario() throws UnknownException;

	TipoCambio insertTipoCambio(TipoCambio tipoCambio) throws UnknownException;

	TipoCambio updateTipoCambio(TipoCambio tipoCambio) throws UnknownException;

	Boolean removeTipoCambio(String idTipoCambio) throws UnknownException;

	List<TipoCambio> listTipoCambio() throws UnknownException;

	TipoEmpatia insertTipoEmpatia(TipoEmpatia tipoEmpatia) throws UnknownException;

	TipoEmpatia updateTipoEmpatia(TipoEmpatia tipoEmpatia) throws UnknownException;

	Boolean removeTipoEmpatia(String idTipoEmpatia) throws UnknownException;

	List<TipoEmpatia> listTipoEmpatia() throws UnknownException;

	TipoInterPublicidad insertTipoInterPublicidad(TipoInterPublicidad tipoInterPublicidad) throws UnknownException;

	TipoInterPublicidad updateTipoInterPublicidad(TipoInterPublicidad tipoInterPublicidad) throws UnknownException;

	Boolean removeTipoInterPublicidad(String idTipoInterPublicidad) throws UnknownException;

	List<TipoInterPublicidad> listTipoInterPublicidad() throws UnknownException;

	TipoMovimiento insertTipoMovimiento(TipoMovimiento tipoMovimiento) throws UnknownException;

	TipoMovimiento updateTipoMovimiento(TipoMovimiento tipoMovimiento) throws UnknownException;

	Boolean removeTipoMovimiento(String idTipoMovimiento) throws UnknownException;

	List<TipoMovimiento> listTipoMovimiento() throws UnknownException;

	TipoNotificacion insertTipoNotificacion(TipoNotificacion tipoNotificacion) throws UnknownException;

	TipoNotificacion updateTipoNotificacion(TipoNotificacion tipoNotificacion) throws UnknownException;

	Boolean removeTipoNotificacion(String idTipoNotificacion) throws UnknownException;

	List<TipoNotificacion> listTipoNotificacion() throws UnknownException;

	TipoSuscripcion insertTipoSuscripcion(TipoSuscripcion tipoSuscripcion) throws UnknownException;

	TipoSuscripcion updateTipoSuscripcion(TipoSuscripcion tipoSuscripcion) throws UnknownException;

	Boolean removeTipoSuscripcion(String idTipoSuscripcion) throws UnknownException;

	List<TipoSuscripcion> listTipoSuscripcion(ListFilterBean filter) throws UnknownException;
}
