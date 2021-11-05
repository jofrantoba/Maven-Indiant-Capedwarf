package com.indiana.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
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

public interface ServiceGestionMantenimientoAsync {

	void logeoUsuarioAdmin(String correo, String clave, AsyncCallback<UsuarioAdmin> callback);

	void listCategoriaDestino(AsyncCallback<List<CategoriaDestino>> callback);

	void listCasaTarjeta(AsyncCallback<List<CasaTarjeta>> callback);

	void listEntidadFinanciera(AsyncCallback<List<EntidadFinanciera>> callback);

	void listFormaPago(AsyncCallback<List<FormaPago>> callback);

	void listParametrosSistema(AsyncCallback<List<ParametrosSistema>> callback);

	void listTarifario(AsyncCallback<List<Tarifario>> callback);

	void listTipoInterPublicidad(AsyncCallback<List<TipoInterPublicidad>> callback);

	void listCategoriaNegocio(AsyncCallback<List<CategoriaNegocio>> callback);

	void listUsuarioAdmin(AsyncCallback<List<UsuarioAdmin>> callback);

	void listEstadoAmistad(AsyncCallback<List<EstadoAmistad>> callback);

	void listEstadoCuenta(AsyncCallback<List<EstadoCuenta>> callback);

	void listEstadoMiembro(AsyncCallback<List<EstadoMiembro>> callback);

	void listEstadoSolicAmistad(AsyncCallback<List<EstadoSolicAmistad>> callback);

	void listLocalidad(AsyncCallback<List<Localidad>> callback);

	void listIdioma(AsyncCallback<List<Idioma>> callback);

	void listMoneda(ListFilterBean filter,AsyncCallback<List<Moneda>> callback);

	void listPais(AsyncCallback<List<Pais>> callback);

	void listPaisMoneda(AsyncCallback<List<PaisMoneda>> callback);

	void listPrivacidad(AsyncCallback<List<Privacidad>> callback);

	void listRegion(ListFilterBean filter,AsyncCallback<List<Region>> callback);

	void listRedSocial(AsyncCallback<List<RedSocial>> callback);

	void listTipoCambio(AsyncCallback<List<TipoCambio>> callback);

	void listTipoEmpatia(AsyncCallback<List<TipoEmpatia>> callback);

	void listTipoMovimiento(AsyncCallback<List<TipoMovimiento>> callback);

	void listTipoNotificacion(AsyncCallback<List<TipoNotificacion>> callback);

	void listTipoSuscripcion(ListFilterBean filter,AsyncCallback<List<TipoSuscripcion>> callback);

	void insertEstadoCuenta(EstadoCuenta estadocuenta, AsyncCallback<EstadoCuenta> callback);

	void updateEstadoCuenta(EstadoCuenta estadocuenta, AsyncCallback<EstadoCuenta> callback);

	void removeEstadoCuenta(String idEstadoCuenta, AsyncCallback<Boolean> callback);

	void updateCasaTarjeta(CasaTarjeta estadocuenta, AsyncCallback<CasaTarjeta> callback);

	void insertCasaTarjeta(CasaTarjeta estadocuenta, AsyncCallback<CasaTarjeta> callback);

	void removeCasaTarjeta(String idCasaTarjeta, AsyncCallback<Boolean> callback);

	void insertCategoriaDestino(CategoriaDestino categoriaDestino, AsyncCallback<CategoriaDestino> callback);

	void updateCategoriaDestino(CategoriaDestino categoriaDestino, AsyncCallback<CategoriaDestino> callback);

	void removeCategoriaDestino(String idCategoriaDestino, AsyncCallback<Boolean> callback);

	void insertCategoriaNegocio(CategoriaNegocio categoriaNegocio, AsyncCallback<CategoriaNegocio> callback);

	void updateCategoriaNegocio(CategoriaNegocio categoriaNegocio, AsyncCallback<CategoriaNegocio> callback);

	void removeCategoriaNegocio(String idCategoriaNegocio, AsyncCallback<Boolean> callback);

	void insertEntidadFinanciera(EntidadFinanciera entidadFinanciera, AsyncCallback<EntidadFinanciera> callback);

	void updateEntidadFinanciera(EntidadFinanciera entidadFinanciera, AsyncCallback<EntidadFinanciera> callback);

	void removeEntidadFinanciera(String idEntidadFinanciera, AsyncCallback<Boolean> callback);

	void insertEstadoAmistad(EstadoAmistad estadoAmistad, AsyncCallback<EstadoAmistad> callback);

	void updateEstadoAmistad(EstadoAmistad estadoAmistad, AsyncCallback<EstadoAmistad> callback);

	void removeEstadoAmistad(String idEstadoAmistad, AsyncCallback<Boolean> callback);

	void insertEstadoMiembro(EstadoMiembro estadoMiembro, AsyncCallback<EstadoMiembro> callback);

	void updateEstadoMiembro(EstadoMiembro estadoMiembro, AsyncCallback<EstadoMiembro> callback);

	void removeEstadoMiembro(String idEstadoMiembro, AsyncCallback<Boolean> callback);

	void insertEstadoSolicAmistad(EstadoSolicAmistad estadoSolicAmistad, AsyncCallback<EstadoSolicAmistad> callback);

	void updateEstadoSolicAmistad(EstadoSolicAmistad estadoSolicAmistad, AsyncCallback<EstadoSolicAmistad> callback);

	void removeEstadoSolicAmistad(String idEstadoSolicAmistad, AsyncCallback<Boolean> callback);

	void insertFormaPago(FormaPago formaPago, AsyncCallback<FormaPago> callback);

	void updateFormaPago(FormaPago formaPago, AsyncCallback<FormaPago> callback);

	void removeFormaPago(String idFormaPago, AsyncCallback<Boolean> callback);

	void insertIdioma(Idioma idioma, AsyncCallback<Idioma> callback);

	void updateIdioma(Idioma idioma, AsyncCallback<Idioma> callback);

	void removeIdioma(String idIdioma, AsyncCallback<Boolean> callback);

	void insertLocalidad(Localidad localidad, AsyncCallback<Localidad> callback);

	void updateLocalidad(Localidad localidad, AsyncCallback<Localidad> callback);

	void removeLocalidad(String idLocalidad, AsyncCallback<Boolean> callback);

	void insertMoneda(Moneda moneda, AsyncCallback<Moneda> callback);

	void updateMoneda(Moneda moneda, AsyncCallback<Moneda> callback);

	void removeMoneda(String idMoneda, AsyncCallback<Boolean> callback);

	void insertPais(Pais pais, AsyncCallback<Pais> callback);

	void updatePais(Pais pais, AsyncCallback<Pais> callback);

	void removePais(String idPais, AsyncCallback<Boolean> callback);

	void insertPaisMoneda(PaisMoneda paisMoneda, AsyncCallback<PaisMoneda> callback);

	void updatePaisMoneda(PaisMoneda paisMoneda, AsyncCallback<PaisMoneda> callback);

	void removePaisMoneda(String idPaisMoneda, AsyncCallback<Boolean> callback);

	void insertParametrosSistema(ParametrosSistema parametrosSistema, AsyncCallback<ParametrosSistema> callback);

	void updateParametrosSistema(ParametrosSistema parametrosSistema, AsyncCallback<ParametrosSistema> callback);

	void removeParametrosSistema(String idParametrosSistema, AsyncCallback<Boolean> callback);

	void insertPrivacidad(Privacidad privacidad, AsyncCallback<Privacidad> callback);

	void updatePrivacidad(Privacidad privacidad, AsyncCallback<Privacidad> callback);

	void removePrivacidad(String idPrivacidad, AsyncCallback<Boolean> callback);

	void insertRedSocial(RedSocial redSocial, AsyncCallback<RedSocial> callback);

	void updateRedSocial(RedSocial redSocial, AsyncCallback<RedSocial> callback);

	void removeRedSocial(String idRedSocial, AsyncCallback<Boolean> callback);

	void insertRegion(Region region, AsyncCallback<Region> callback);

	void updateRegion(Region region, AsyncCallback<Region> callback);

	void removeRegion(String idRegion, AsyncCallback<Boolean> callback);

	void insertTarifario(Tarifario tarifario, AsyncCallback<Tarifario> callback);

	void updateTarifario(Tarifario tarifario, AsyncCallback<Tarifario> callback);

	void removeTarifario(String idTarifario, AsyncCallback<Boolean> callback);

	void insertTipoCambio(TipoCambio tipoCambio, AsyncCallback<TipoCambio> callback);

	void updateTipoCambio(TipoCambio tipoCambio, AsyncCallback<TipoCambio> callback);

	void removeTipoCambio(String idTipoCambio, AsyncCallback<Boolean> callback);

	void insertTipoEmpatia(TipoEmpatia tipoEmpatia, AsyncCallback<TipoEmpatia> callback);

	void updateTipoEmpatia(TipoEmpatia tipoEmpatia, AsyncCallback<TipoEmpatia> callback);

	void removeTipoEmpatia(String idTipoEmpatia, AsyncCallback<Boolean> callback);

	void insertTipoInterPublicidad(TipoInterPublicidad tipoInterPublicidad,
			AsyncCallback<TipoInterPublicidad> callback);

	void updateTipoInterPublicidad(TipoInterPublicidad tipoInterPublicidad,
			AsyncCallback<TipoInterPublicidad> callback);

	void removeTipoInterPublicidad(String idTipoInterPublicidad, AsyncCallback<Boolean> callback);

	void insertTipoMovimiento(TipoMovimiento tipoMovimiento, AsyncCallback<TipoMovimiento> callback);

	void updateTipoMovimiento(TipoMovimiento tipoMovimiento, AsyncCallback<TipoMovimiento> callback);

	void removeTipoMovimiento(String idTipoMovimiento, AsyncCallback<Boolean> callback);

	void insertTipoNotificacion(TipoNotificacion tipoNotificacion, AsyncCallback<TipoNotificacion> callback);

	void updateTipoNotificacion(TipoNotificacion tipoNotificacion, AsyncCallback<TipoNotificacion> callback);

	void removeTipoNotificacion(String idTipoNotificacion, AsyncCallback<Boolean> callback);

	void insertTipoSuscripcion(TipoSuscripcion tipoSuscripcion, AsyncCallback<TipoSuscripcion> callback);

	void updateTipoSuscripcion(TipoSuscripcion tipoSuscripcion, AsyncCallback<TipoSuscripcion> callback);

	void removeTipoSuscripcion(String idTipoSuscripcion, AsyncCallback<Boolean> callback);

	void insertUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin, AsyncCallback<UsuarioAdmin> callback);

	void updateUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin, AsyncCallback<UsuarioAdmin> callback);

	void removeUsuarioAdmin(String correoUsuarioAdmin, AsyncCallback<Boolean> callback);

}
