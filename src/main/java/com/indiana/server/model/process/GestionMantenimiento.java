package com.indiana.server.model.process;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.server.model.bean.CategoriaDestino;
import com.indiana.server.model.bean.CategoriaNegocio;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.CuentaAdmin;
import com.indiana.server.model.bean.DetalleInteres;
import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.server.model.bean.EstadoMiembro;
import com.indiana.server.model.bean.EstadoSolicAmistad;
import com.indiana.server.model.bean.FormaPago;
import com.indiana.server.model.bean.Idioma;
import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MiembroInteres;
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
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicCasaTarjeta;
import com.indiana.server.model.logic.LogicCategoriaDestino;
import com.indiana.server.model.logic.LogicCategoriaNegocio;
import com.indiana.server.model.logic.LogicColonia;
import com.indiana.server.model.logic.LogicColoniaInteres;
import com.indiana.server.model.logic.LogicCuentaAdmin;
import com.indiana.server.model.logic.LogicDetalleInteres;
import com.indiana.server.model.logic.LogicEntidadFinanciera;
import com.indiana.server.model.logic.LogicEstadoAmistad;
import com.indiana.server.model.logic.LogicEstadoCuenta;
import com.indiana.server.model.logic.LogicEstadoMiembro;
import com.indiana.server.model.logic.LogicEstadoSolicAmistad;
import com.indiana.server.model.logic.LogicFormaPago;
import com.indiana.server.model.logic.LogicIdioma;
import com.indiana.server.model.logic.LogicInteres;
import com.indiana.server.model.logic.LogicLocalidad;
import com.indiana.server.model.logic.LogicMiembro;
import com.indiana.server.model.logic.LogicMiembroInteres;
import com.indiana.server.model.logic.LogicMoneda;
import com.indiana.server.model.logic.LogicPais;
import com.indiana.server.model.logic.LogicPaisMoneda;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicPrivacidad;
import com.indiana.server.model.logic.LogicRedSocial;
import com.indiana.server.model.logic.LogicRegion;
import com.indiana.server.model.logic.LogicTarifario;
import com.indiana.server.model.logic.LogicTipoAnuncio;
import com.indiana.server.model.logic.LogicTipoCambio;
import com.indiana.server.model.logic.LogicTipoEmpatia;
import com.indiana.server.model.logic.LogicTipoInterPublicidad;
import com.indiana.server.model.logic.LogicTipoMovimiento;
import com.indiana.server.model.logic.LogicTipoNotificacion;
import com.indiana.server.model.logic.LogicTipoSuscripcion;
import com.indiana.server.model.logic.LogicTuristaInteres;
import com.indiana.server.model.logic.LogicUsuarioAdmin;
import com.indiana.server.model.logic.LogicUsuarioTurista;
import com.indiana.shared.AESencrypt;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.ListFilterBean;
import com.indiana.shared.P;
import com.indiana.shared.StringHex;
import com.indiana.shared.UnknownException;

public class GestionMantenimiento {
	private static final Logger LOG = Logger.getLogger(GestionMantenimiento.class.getName());

	public static Object insertarObjeto(Object bean) throws UnknownException {

		BeanParametro parametro = new BeanParametro();
		PersistenceManager pm = null;
		Transaction tx = null;
		Boolean resultado = false;
		Boolean valBeanNull = false;
		Boolean valNotInstance = false;
		Boolean valOperAndId = false;
		Boolean valExisteId = false;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			if (bean != null) { // validacion1
				valBeanNull = true;
				if (bean instanceof CategoriaDestino) {/* Categoria Destino */
					// validacion2
					CategoriaDestino obj = (CategoriaDestino) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdCategoriaDestino() != null) {// validacion3
						valOperAndId = true;
						if (getObjeto(CategoriaDestino.class, obj.getIdCategoriaDestino(), pm) == null) {// validacion4
							obj.setIdCategoriaDestino(KeyFactory.keyToString(KeyFactory
									.createKey(CategoriaDestino.class.getSimpleName(), obj.getIdCategoriaDestino())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicCategoriaDestino(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof CategoriaNegocio) {/*
																 * Categoria
																 * Negocio
																 */
					CategoriaNegocio obj = (CategoriaNegocio) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdCategoriaNegocio() != null) {
						valOperAndId = true;
						if (getObjeto(CategoriaNegocio.class, obj.getIdCategoriaNegocio(), pm) == null) {
							obj.setIdCategoriaNegocio(KeyFactory.keyToString(KeyFactory
									.createKey(CategoriaNegocio.class.getSimpleName(), obj.getIdCategoriaNegocio())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicCategoriaNegocio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoAmistad) {/* Estado Amistad */
					valNotInstance = true;
					EstadoAmistad obj = (EstadoAmistad) bean;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdEstadoAmistad() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoAmistad.class, obj.getIdEstadoAmistad(), pm) == null) {
							obj.setIdEstadoAmistad(KeyFactory.keyToString(KeyFactory
									.createKey(EstadoAmistad.class.getSimpleName(), obj.getIdEstadoAmistad())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoAmistad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoCuenta) {/* Estado Cuenta */
					EstadoCuenta obj = (EstadoCuenta) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdEstadoCuenta() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoCuenta.class, obj.getIdEstadoCuenta(), pm) == null) {
							obj.setIdEstadoCuenta(KeyFactory.keyToString(
									KeyFactory.createKey(EstadoCuenta.class.getSimpleName(), obj.getIdEstadoCuenta())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoCuenta(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoMiembro) {/* Estado Miembro */
					EstadoMiembro obj = (EstadoMiembro) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdEstadoMiembro() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoMiembro.class, obj.getIdEstadoMiembro(), pm) == null) {
							obj.setIdEstadoMiembro(KeyFactory.keyToString(KeyFactory
									.createKey(EstadoMiembro.class.getSimpleName(), obj.getIdEstadoMiembro())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoMiembro(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoSolicAmistad) {/*
																 * Estado
																 * Solicitud
																 * Amistad
																 */
					EstadoSolicAmistad obj = (EstadoSolicAmistad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdEstadoSolicAmistad() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoSolicAmistad.class, obj.getIdEstadoSolicAmistad(), pm) == null) {
							obj.setIdEstadoSolicAmistad(KeyFactory.keyToString(KeyFactory.createKey(
									EstadoSolicAmistad.class.getSimpleName(), obj.getIdEstadoSolicAmistad())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoSolicAmistad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Idioma) {/* Idioma */
					Idioma obj = (Idioma) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdIdioma() != null) {
						valOperAndId = true;
						if (getObjeto(Idioma.class, obj.getIdIdioma(), pm) == null) {
							obj.setIdIdioma(KeyFactory.keyToString(
									KeyFactory.createKey(Idioma.class.getSimpleName(), obj.getIdIdioma())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicIdioma(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Localidad) {/* Localidad */
					Localidad obj = (Localidad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdLocalidad() == null) {
						valOperAndId = true;
						String strLocalidad = StringHex.convertStringToHex(String.valueOf(obj.hashCode()));
						obj.setIdLocalidad(strLocalidad);
						obj.setCodeLocalidad(strLocalidad);
						if (getObjeto(Localidad.class, obj.getIdLocalidad(), pm) == null) {
							Region beanRegion = (Region) GestionMantenimiento.getObjeto(Region.class,
									obj.getBeanRegion().getIdRegion(), pm);
							obj.setBeanRegion(beanRegion);
							obj.setCodeRegion(beanRegion.getCodeRegion());
							obj.setNombreRegion(beanRegion.getNombre());
							Pais beanPais = (Pais) GestionMantenimiento.getObjeto(Pais.class,
									obj.getBeanPais().getIdPais(), pm);
							obj.setBeanPais(beanPais);
							obj.setCodePais(beanPais.getCodePais());
							obj.setNombrePais(beanPais.getNombre());
							obj.setIdLocalidad(KeyFactory.keyToString(
									KeyFactory.createKey(Localidad.class.getSimpleName(), obj.getIdLocalidad())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicLocalidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Moneda) {/* Moneda */
					Moneda obj = (Moneda) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdMoneda() == null) {
						valOperAndId = true;
						obj.setIdMoneda(obj.getDescripcion());
						obj.setCodeMoneda(obj.getDescripcion());
						if (getObjeto(Moneda.class, obj.getIdMoneda(), pm) == null) {
							obj.setIdMoneda(KeyFactory.keyToString(
									KeyFactory.createKey(Moneda.class.getSimpleName(), obj.getIdMoneda())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicMoneda(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Pais) {/* Pais */
					valNotInstance = true;
					Pais obj = (Pais) bean;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdPais() == null) {
						valOperAndId = true;
						String strPais = StringHex.convertStringToHex(String.valueOf(obj.hashCode()));
						obj.setIdPais(strPais);						
						obj.setCodePais(strPais);						
							obj.setIdPais(KeyFactory
									.keyToString(KeyFactory.createKey(Pais.class.getSimpleName(), obj.getIdPais())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicPais(pm).mantenimiento(parametro);
							valExisteId = true;						
					}
				} else if (bean instanceof PaisMoneda) {/* Pais Moneda */
					PaisMoneda obj = (PaisMoneda) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdPaisMoneda() == null) {
						valOperAndId = true;
						String strKey = obj.getNombrePais() + ":" + obj.getNombreMoneda();
						obj.setIdPaisMoneda(strKey);
						obj.setCodePaisMoneda(strKey);
						if (getObjeto(PaisMoneda.class, obj.getIdPaisMoneda(), pm) == null) {
							Moneda beanMoneda = (Moneda) GestionMantenimiento.getObjeto(Moneda.class,
									obj.getBeanMoneda().getIdMoneda(), pm);
							obj.setBeanMoneda(beanMoneda);
							obj.setCodeMoneda(beanMoneda.getCodeMoneda());
							obj.setNombreMoneda(beanMoneda.getDescripcion());
							Pais beanPais = (Pais) GestionMantenimiento.getObjeto(Pais.class,
									obj.getBeanPais().getIdPais(), pm);
							obj.setBeanPais(beanPais);
							obj.setCodePais(beanPais.getCodePais());
							obj.setNombrePais(beanPais.getNombre());
							obj.setIdPaisMoneda(KeyFactory.keyToString(
									KeyFactory.createKey(PaisMoneda.class.getSimpleName(), obj.getIdPaisMoneda())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicPaisMoneda(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Privacidad) {/* Privacidad */
					Privacidad obj = (Privacidad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdPrivacidad() != null) {
						valOperAndId = true;
						if (getObjeto(Privacidad.class, obj.getIdPrivacidad(), pm) == null) {
							obj.setIdPrivacidad(KeyFactory.keyToString(
									KeyFactory.createKey(Privacidad.class.getSimpleName(), obj.getIdPrivacidad())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicPrivacidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof RedSocial) {/* Red Social */
					RedSocial obj = (RedSocial) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdRedSocial() != null) {
						valOperAndId = true;
						if (getObjeto(RedSocial.class, obj.getIdRedSocial(), pm) == null) {
							obj.setIdRedSocial(KeyFactory.keyToString(KeyFactory
									.createKey(RedSocial.class.getSimpleName(), obj.getIdRedSocial())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicRedSocial(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Region) {/* Region */
					Region obj = (Region) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdRegion() == null) {
						valOperAndId = true;
						String strRegion = StringHex.convertStringToHex(String.valueOf(obj.hashCode()));
						obj.setIdRegion(strRegion);
						obj.setCodeRegion(strRegion);
						if (getObjeto(Region.class, obj.getIdRegion(), pm) == null) {
							Pais beanPais = (Pais) GestionMantenimiento.getObjeto(Pais.class,
									obj.getBeanPais().getIdPais(), pm);
							obj.setBeanPais(beanPais);
							obj.setCodePais(beanPais.getCodePais());
							obj.setNombrePais(beanPais.getNombre());
							obj.setIdRegion(KeyFactory.keyToString(
									KeyFactory.createKey(Region.class.getSimpleName(), obj.getIdRegion())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicRegion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoAnuncio) {/* Tipo Anuncio */
					TipoAnuncio obj = (TipoAnuncio) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoAnuncio() != null) {
						valOperAndId = true;
						if (getObjeto(Privacidad.class, obj.getIdTipoAnuncio(), pm) == null) {
							obj.setIdTipoAnuncio(KeyFactory.keyToString(
									KeyFactory.createKey(TipoAnuncio.class.getSimpleName(), obj.getIdTipoAnuncio())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoAnuncio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoCambio) {/* Tipo Cambio */
					TipoCambio obj = (TipoCambio) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoCambio() == null) {
						valOperAndId = true;
						String strKey = obj.getCodePaisMonedaOrigen() + "," + obj.getCodePaisMonedaDestino() + ","
								+ new SimpleDateFormat("ddMMyyyy").format(obj.getFecha());
						obj.setIdTipoCambio(strKey);
						obj.setCodeTipoCambio(strKey);
						if (getObjeto(TipoCambio.class, obj.getIdTipoCambio(), pm) == null) {
							/** Verificar Insercion.. **/
							PaisMoneda beanPaisMonedaOrigen = (PaisMoneda) GestionMantenimiento
									.getObjeto(PaisMoneda.class, obj.getBeanPaisMonedaOrigen().getIdPaisMoneda(), pm);
							obj.setBeanPaisMonedaOrigen(beanPaisMonedaOrigen);
							obj.setCodePaisMonedaOrigen(beanPaisMonedaOrigen.getCodePaisMoneda());
							obj.setMonedaOrigen(beanPaisMonedaOrigen.getNombreMoneda());
							obj.setPaisOrigen(beanPaisMonedaOrigen.getNombrePais());
							PaisMoneda beanPaisMonedaDestino = (PaisMoneda) GestionMantenimiento
									.getObjeto(PaisMoneda.class, obj.getBeanPaisMonedaDestino().getIdPaisMoneda(), pm);
							obj.setBeanPaisMonedaDestino(beanPaisMonedaDestino);
							obj.setCodePaisMonedaDestino(beanPaisMonedaDestino.getCodePaisMoneda());
							obj.setMonedaDestino(beanPaisMonedaDestino.getNombreMoneda());
							obj.setPaisDestino(beanPaisMonedaDestino.getNombrePais());
							obj.setIdTipoCambio(KeyFactory.keyToString(
									KeyFactory.createKey(TipoCambio.class.getSimpleName(), obj.getIdTipoCambio())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoCambio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}

				} else if (bean instanceof TipoEmpatia) {/* Tipo Empatia */
					TipoEmpatia obj = (TipoEmpatia) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoEmpatia() != null) {
						valOperAndId = true;
						if (getObjeto(TipoEmpatia.class, obj.getIdTipoEmpatia(), pm) == null) {
							obj.setIdTipoEmpatia(KeyFactory.keyToString(
									KeyFactory.createKey(TipoEmpatia.class.getSimpleName(), obj.getIdTipoEmpatia())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoEmpatia(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoMovimiento) {/*
															 * Tipo Movimiento
															 */
					TipoMovimiento obj = (TipoMovimiento) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoMovimiento() != null) {
						valOperAndId = true;
						if (getObjeto(TipoMovimiento.class, obj.getIdTipoMovimiento(), pm) == null) {
							obj.setIdTipoMovimiento(KeyFactory.keyToString(KeyFactory.createKey(
									TipoMovimiento.class.getSimpleName(), obj.getIdTipoMovimiento())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoMovimiento(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoNotificacion) {/*
																 * Tipo
																 * Notificacion
																 */
					TipoNotificacion obj = (TipoNotificacion) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoNotificacion() != null) {
						valOperAndId = true;
						if (getObjeto(TipoNotificacion.class, obj.getIdTipoNotificacion(), pm) == null) {
							obj.setIdTipoNotificacion(KeyFactory.keyToString(KeyFactory
									.createKey(TipoNotificacion.class.getSimpleName(), obj.getIdTipoNotificacion())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoNotificacion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoSuscripcion) {/*
																 * Tipo
																 * Suscripcion
																 */
					TipoSuscripcion obj = (TipoSuscripcion) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoSuscripcion() != null) {
						valOperAndId = true;
						if (getObjeto(TipoSuscripcion.class, obj.getIdTipoSuscripcion(), pm) == null) {
							obj.setIdTipoSuscripcion(KeyFactory.keyToString(KeyFactory
									.createKey(TipoSuscripcion.class.getSimpleName(), obj.getIdTipoSuscripcion())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoSuscripcion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof CasaTarjeta) {/* Casa Tarjeta */
					CasaTarjeta obj = (CasaTarjeta) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdCasaTarjeta() != null) {
						valOperAndId = true;
						if (getObjeto(CasaTarjeta.class, obj.getIdCasaTarjeta(), pm) == null) {
							obj.setIdCasaTarjeta(KeyFactory.keyToString(
									KeyFactory.createKey(CasaTarjeta.class.getSimpleName(), obj.getIdCasaTarjeta())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicCasaTarjeta(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EntidadFinanciera) {/*
																 * Entidad
																 * Financiera
																 */
					EntidadFinanciera obj = (EntidadFinanciera) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdEntidadFinanciera() != null) {
						valOperAndId = true;
						if (getObjeto(EntidadFinanciera.class, obj.getIdEntidadFinanciera(), pm) == null) {
							obj.getBeanPais().setIdPais(KeyFactory
									.keyToString(KeyFactory.createKey(Pais.class.getSimpleName(), obj.getCodePais())));
							obj.setIdEntidadFinanciera(KeyFactory.keyToString(KeyFactory
									.createKey(EntidadFinanciera.class.getSimpleName(), obj.getIdEntidadFinanciera())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEntidadFinanciera(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof FormaPago) {/* Forma Pago */
					FormaPago obj = (FormaPago) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdFormaPago() != null) {
						valOperAndId = true;
						if (getObjeto(FormaPago.class, obj.getIdFormaPago(), pm) == null) {
							obj.setIdFormaPago(KeyFactory.keyToString(
									KeyFactory.createKey(FormaPago.class.getSimpleName(), obj.getIdFormaPago())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicFormaPago(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof ParametrosSistema) {/*
																 * Parametros
																 * del Sistema
																 */
					ParametrosSistema obj = (ParametrosSistema) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdParametrosSistema() != null) {
						valOperAndId = true;
						if (getObjeto(ParametrosSistema.class, obj.getIdParametrosSistema(), pm) == null) {
							obj.setIdParametrosSistema(KeyFactory.keyToString(KeyFactory
									.createKey(ParametrosSistema.class.getSimpleName(), obj.getIdParametrosSistema())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicParametrosSistema(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Tarifario) {/* Tarifario */
					Tarifario obj = (Tarifario) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTarifario() == null) {
						valOperAndId = true;
						String strTarifario = StringHex.convertStringToHex(UUID.randomUUID().toString());
						obj.setIdTarifario(strTarifario);
						obj.setCodeTarifario(strTarifario);
						LogicTarifario logic = new LogicTarifario(pm);
						if (logic.existeBean(obj.getCodeTipoSuscripcion(),
								obj.getCodePais()/* ,obj.getCodeRegion() */) == 0L) {
							TipoSuscripcion beanTipoSuscripcion = (TipoSuscripcion) GestionMantenimiento.getObjeto(
									TipoSuscripcion.class, obj.getBeanTipoSuscripcion().getIdTipoSuscripcion(), pm);
							obj.setBeanTipoSuscripcion(beanTipoSuscripcion);
							obj.setCodeTipoSuscripcion(beanTipoSuscripcion.getCodeTipoSuscripcion());
							obj.setNombreTipoSuscripcion(beanTipoSuscripcion.getDescripcion());
							Pais beanPais = (Pais) GestionMantenimiento.getObjeto(Pais.class,
									obj.getBeanPais().getIdPais(), pm);
							obj.setBeanPais(beanPais);
							obj.setCodePais(beanPais.getCodePais());
							obj.setNombrePais(beanPais.getNombre());
							obj.setIdTarifario(KeyFactory.keyToString(
									KeyFactory.createKey(Tarifario.class.getSimpleName(), obj.getIdTarifario())));
							obj.setFechaInicial(new java.util.Date());
							obj.setEstado("A");
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = logic.mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoInterPublicidad) {/*
																	 * Tipo
																	 * Interaccion
																	 * Publicidad
																	 */
					TipoInterPublicidad obj = (TipoInterPublicidad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("I") && obj.getIdTipoInterPublicidad() != null) {
						valOperAndId = true;
						if (getObjeto(TipoInterPublicidad.class, obj.getIdTipoInterPublicidad(), pm) == null) {
							obj.setIdTipoInterPublicidad(KeyFactory.keyToString(KeyFactory.createKey(
									TipoInterPublicidad.class.getSimpleName(), obj.getIdTipoInterPublicidad())));
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoInterPublicidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				}
			}
			retornarMensajeError(valBeanNull, valNotInstance, valOperAndId, valExisteId, true);

			if (resultado) {
				tx.commit();
				pm.close();
				return bean;
			} else {
				tx.rollback();
				pm.close();
				return null;
			}
		} catch (Exception ex) {
			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static Object actualizarObjeto(Class<?> nomClase, Object bean, Object idBean) throws UnknownException {
		BeanParametro parametro = new BeanParametro();
		PersistenceManager pm = null;
		Transaction tx = null;
		Boolean resultado = false;
		Boolean valBeanNull = false;
		Boolean valNotInstance = false;
		Boolean valOperAndId = false;
		Boolean valExisteId = false;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			Object beanStore = pm.getObjectById(nomClase, idBean);
			if (beanStore != null) {
				valBeanNull = true;
				if (bean instanceof CategoriaDestino) {/* Categoria Destino */
					CategoriaDestino obj = (CategoriaDestino) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdCategoriaDestino() != null) {
						valOperAndId = true;
						if (getObjeto(CategoriaDestino.class, obj.getIdCategoriaDestino(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicCategoriaDestino(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof CategoriaNegocio) {/*
																 * Categoria
																 * Negocio
																 */
					CategoriaNegocio obj = (CategoriaNegocio) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdCategoriaNegocio() != null) {
						valOperAndId = true;
						if (getObjeto(CategoriaNegocio.class, obj.getIdCategoriaNegocio(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicCategoriaNegocio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoAmistad) {/* Estado Amistad */
					EstadoAmistad obj = (EstadoAmistad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdEstadoAmistad() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoAmistad.class, obj.getIdEstadoAmistad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoAmistad(pm).mantenimiento(parametro);
							valExisteId = true;
						}

					}
				} else if (bean instanceof EstadoCuenta) {/* Estado Cuenta */
					EstadoCuenta obj = (EstadoCuenta) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdEstadoCuenta() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoCuenta.class, obj.getIdEstadoCuenta(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoCuenta(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoMiembro) {/* Estado Miembro */
					EstadoMiembro obj = (EstadoMiembro) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdEstadoMiembro() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoMiembro.class, obj.getIdEstadoMiembro(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoMiembro(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoSolicAmistad) {/*
																 * Estado
																 * Solicitud
																 * Amistad
																 */
					EstadoSolicAmistad obj = (EstadoSolicAmistad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdEstadoSolicAmistad() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoSolicAmistad.class, obj.getIdEstadoSolicAmistad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicEstadoSolicAmistad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Idioma) {/* Idioma */
					Idioma obj = (Idioma) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdIdioma() != null) {
						valOperAndId = true;
						if (getObjeto(Idioma.class, obj.getIdIdioma(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicIdioma(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Localidad) {/* Localidad */
					Localidad obj = (Localidad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdLocalidad() != null) {
						valOperAndId = true;
						if (getObjeto(Localidad.class, obj.getIdLocalidad(), pm) != null) {
							Region beanRegion = (Region) getObjeto(Region.class, obj.getBeanRegion().getIdRegion(), pm);
							Pais beanPais = (Pais) getObjeto(Pais.class, obj.getBeanPais().getIdPais(), pm);
							if (beanRegion != null && beanPais != null) {
								obj.setBeanRegion(beanRegion);
								obj.setCodeRegion(beanRegion.getCodeRegion());
								obj.setNombreRegion(beanRegion.getNombre());
								obj.setBeanPais(beanPais);
								obj.setCodePais(beanPais.getCodePais());
								obj.setNombrePais(beanPais.getNombre());
								parametro.setBean(obj);
								parametro.setTipoOperacion(obj.getOperacion());
								resultado = new LogicLocalidad(pm).mantenimiento(parametro);
								valExisteId = true;
							}
						}
					}

				} else if (bean instanceof Moneda) {/* Moneda */
					Moneda obj = (Moneda) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdMoneda() != null) {
						valOperAndId = true;
						if (getObjeto(Moneda.class, obj.getIdMoneda(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicMoneda(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Pais) {/* Pais */
					Pais obj = (Pais) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdPais() != null) {
						valOperAndId = true;
						if (getObjeto(Pais.class, obj.getIdPais(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicPais(pm).mantenimiento(parametro);
							valExisteId = true;
						}

					}
				} else if (bean instanceof PaisMoneda) {/* Pais Moneda */
					PaisMoneda obj = (PaisMoneda) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdPaisMoneda() != null) {
						valOperAndId = true;
						if (getObjeto(PaisMoneda.class, obj.getIdPaisMoneda(), pm) != null) {
							Pais beanPais = (Pais) getObjeto(Pais.class, obj.getBeanPais().getIdPais(), pm);
							Moneda beanMoneda = (Moneda) getObjeto(Moneda.class, obj.getBeanMoneda().getIdMoneda(), pm);
							if (beanPais != null && beanMoneda != null) {
								obj.setBeanPais(beanPais);
								obj.setCodePais(beanPais.getCodePais());
								obj.setNombrePais(beanPais.getNombre());
								obj.setBeanMoneda(beanMoneda);
								obj.setCodeMoneda(beanMoneda.getCodeMoneda());
								obj.setNombreMoneda(beanMoneda.getDescripcion());
								parametro.setBean(obj);
								parametro.setTipoOperacion(obj.getOperacion());
								resultado = new LogicPaisMoneda(pm).mantenimiento(parametro);
								valExisteId = true;
							}
						}
					}
				} else if (bean instanceof Privacidad) {/* Privacidad */
					Privacidad obj = (Privacidad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdPrivacidad() != null) {
						valOperAndId = true;
						if (getObjeto(Privacidad.class, obj.getIdPrivacidad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicPrivacidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof RedSocial) {/* Red Social Miembro */
					RedSocial obj = (RedSocial) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdRedSocial() != null) {
						valOperAndId = true;
						if (getObjeto(RedSocial.class, obj.getIdRedSocial(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicRedSocial(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Region) {/* Region */
					Region obj = (Region) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdRegion() != null) {
						valOperAndId = true;
						if (getObjeto(Region.class, obj.getIdRegion(), pm) != null) {
							Pais beanPais = (Pais) getObjeto(Pais.class, obj.getBeanPais().getIdPais(), pm);
							if (beanPais != null) {
								obj.setBeanPais(beanPais);
								obj.setCodePais(beanPais.getCodePais());
								obj.setNombre(beanPais.getNombre());
								parametro.setBean(obj);
								parametro.setTipoOperacion(obj.getOperacion());
								resultado = new LogicRegion(pm).mantenimiento(parametro);
								valExisteId = true;
							}
						}
					}
				} else if (bean instanceof TipoAnuncio) {/* Tipo Anuncio */
					TipoAnuncio obj = (TipoAnuncio) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoAnuncio() != null) {
						valOperAndId = true;
						if (getObjeto(TipoAnuncio.class, obj.getIdTipoAnuncio(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoAnuncio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoCambio) {/* Tipo Cambio */
					TipoCambio obj = (TipoCambio) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoCambio() != null) {
						valOperAndId = true;
						if (getObjeto(TipoCambio.class, obj.getIdTipoCambio(), pm) != null) {
							PaisMoneda beanPaisMonedaOrigen = (PaisMoneda) getObjeto(Moneda.class,
									obj.getBeanPaisMonedaOrigen().getIdPaisMoneda(), pm);
							PaisMoneda beanPaisMonedaDestino = (PaisMoneda) getObjeto(Moneda.class,
									obj.getBeanPaisMonedaDestino().getIdPaisMoneda(), pm);
							if (beanPaisMonedaOrigen != null && beanPaisMonedaDestino != null) {
								obj.setBeanPaisMonedaOrigen(beanPaisMonedaOrigen);
								obj.setCodePaisMonedaOrigen(beanPaisMonedaOrigen.getCodePaisMoneda());
								obj.setMonedaOrigen(beanPaisMonedaOrigen.getNombreMoneda());
								obj.setPaisOrigen(beanPaisMonedaOrigen.getNombrePais());
								obj.setBeanPaisMonedaDestino(beanPaisMonedaDestino);
								obj.setCodePaisMonedaDestino(beanPaisMonedaDestino.getCodePaisMoneda());
								obj.setMonedaDestino(beanPaisMonedaDestino.getNombreMoneda());
								obj.setPaisDestino(beanPaisMonedaDestino.getNombrePais());
								parametro.setBean(obj);
								parametro.setTipoOperacion(obj.getOperacion());
								resultado = new LogicTipoCambio(pm).mantenimiento(parametro);
								valExisteId = true;
							}
						}
					}
				} else if (bean instanceof TipoEmpatia) {/* Tipo Empatia */
					TipoEmpatia obj = (TipoEmpatia) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoEmpatia() != null) {
						valOperAndId = true;
						if (getObjeto(TipoEmpatia.class, obj.getIdTipoEmpatia(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoEmpatia(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoMovimiento) {/*
															 * Tipo Movimiento
															 */
					TipoMovimiento obj = (TipoMovimiento) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoMovimiento() != null) {
						valOperAndId = true;
						if (getObjeto(TipoMovimiento.class, obj.getIdTipoMovimiento(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoMovimiento(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoNotificacion) {/*
																 * Tipo
																 * Notificacion
																 */
					TipoNotificacion obj = (TipoNotificacion) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoNotificacion() != null) {
						valOperAndId = true;
						if (getObjeto(TipoNotificacion.class, obj.getIdTipoNotificacion(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoNotificacion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoSuscripcion) {/*
																 * Tipo
																 * Suscripcion
																 */
					TipoSuscripcion obj = (TipoSuscripcion) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoSuscripcion() != null) {
						valOperAndId = true;
						if (getObjeto(TipoSuscripcion.class, obj.getIdTipoSuscripcion(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoSuscripcion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof CasaTarjeta) {/* Casa Tarjeta */
					CasaTarjeta obj = (CasaTarjeta) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdCasaTarjeta() != null) {
						valOperAndId = true;
						if (getObjeto(CasaTarjeta.class, obj.getIdCasaTarjeta(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicCasaTarjeta(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EntidadFinanciera) {/*
																 * Entidad
																 * Financiera
																 */
					EntidadFinanciera obj = (EntidadFinanciera) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdEntidadFinanciera() != null) {
						valOperAndId = true;
						if (getObjeto(EntidadFinanciera.class, obj.getIdEntidadFinanciera(), pm) != null) {
							Pais beanPais = (Pais) getObjeto(Pais.class, obj.getBeanPais().getIdPais(), pm);
							if (beanPais != null) {
								obj.setBeanPais(beanPais);
								obj.setCodePais(beanPais.getCodePais());
								obj.setNombrePais(beanPais.getNombre());
								parametro.setBean(obj);
								parametro.setTipoOperacion(obj.getOperacion());
								resultado = new LogicEntidadFinanciera(pm).mantenimiento(parametro);
								valExisteId = true;
							}
						}
					}
				} else if (bean instanceof FormaPago) {/* Forma Pago */
					FormaPago obj = (FormaPago) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdFormaPago() != null) {
						valOperAndId = true;
						if (getObjeto(FormaPago.class, obj.getIdFormaPago(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicFormaPago(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof ParametrosSistema) {/*
																 * Parametros
																 * del Sistema
																 */
					ParametrosSistema obj = (ParametrosSistema) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdParametrosSistema() != null) {
						valOperAndId = true;
						if (getObjeto(ParametrosSistema.class, obj.getIdParametrosSistema(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicParametrosSistema(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Tarifario) {/* Tarifario */
					Tarifario obj = (Tarifario) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTarifario() != null) {
						valOperAndId = true;
						if (getObjeto(Tarifario.class, obj.getIdTarifario(), pm) != null) {
							Pais beanPais = (Pais) getObjeto(Pais.class, obj.getBeanPais().getIdPais(), pm);
							TipoSuscripcion beanTipoSuscripcion = (TipoSuscripcion) getObjeto(TipoSuscripcion.class,
									obj.getBeanTipoSuscripcion().getIdTipoSuscripcion(), pm);
							if (beanPais != null /* && beanRegion!=null */ && beanTipoSuscripcion != null) {
								obj.setBeanPais(beanPais);
								obj.setCodePais(beanPais.getCodePais());
								obj.setNombrePais(beanPais.getNombre());
								obj.setBeanTipoSuscripcion(beanTipoSuscripcion);
								obj.setCodeTipoSuscripcion(beanTipoSuscripcion.getCodeTipoSuscripcion());
								obj.setNombreTipoSuscripcion(beanTipoSuscripcion.getDescripcion());
								parametro.setBean(obj);
								parametro.setTipoOperacion(obj.getOperacion());
								resultado = new LogicTarifario(pm).mantenimiento(parametro);
								valExisteId = true;
							}
						}
					}
				} else if (bean instanceof TipoInterPublicidad) {/*
																	 * Tipo
																	 * Interaccion
																	 * Publicidad
																	 */
					TipoInterPublicidad obj = (TipoInterPublicidad) bean;
					valNotInstance = true;
					if (obj.getOperacion().equalsIgnoreCase("A") && obj.getIdTipoInterPublicidad() != null) {
						valOperAndId = true;
						if (getObjeto(TipoInterPublicidad.class, obj.getIdTipoInterPublicidad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							resultado = new LogicTipoInterPublicidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				}
			}
			retornarMensajeError(valBeanNull, valNotInstance, valOperAndId, valExisteId, false);
			if (resultado) {
				tx.commit();
				pm.close();
				return bean;
			} else {
				tx.rollback();
				pm.close();
				return null;
			}
		} catch (Exception ex) {

			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());

		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static Boolean eliminarObjeto(Class<?> nomClase, Object idBean) throws UnknownException {

		BeanParametro parametro = new BeanParametro();
		PersistenceManager pm = null;
		Transaction tx = null;
		Boolean resultado = false;
		Boolean valBeanNull = false;
		Boolean valNotInstance = false;
		Boolean valOperAndId = false;
		Boolean valExisteId = false;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			Object bean = pm.getObjectById(nomClase, idBean);
			if (bean != null) {
				valBeanNull = true;
				if (bean instanceof CategoriaDestino) {/* Categoria Destino */
					valNotInstance = true;
					CategoriaDestino obj = (CategoriaDestino) bean;
					if (obj.getIdCategoriaDestino() != null) {
						valOperAndId = true;
						if (getObjeto(CategoriaDestino.class, obj.getIdCategoriaDestino(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdCategoriaDestino());
							resultado = new LogicCategoriaDestino(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof CategoriaNegocio) {/*
																 * Categoria
																 * Negocio
																 */
					valNotInstance = true;
					CategoriaNegocio obj = (CategoriaNegocio) bean;
					if (obj.getIdCategoriaNegocio() != null) {
						valOperAndId = true;
						if (getObjeto(CategoriaNegocio.class, obj.getIdCategoriaNegocio(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdCategoriaNegocio());
							resultado = new LogicCategoriaNegocio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoAmistad) {/* Estado Amistad */
					EstadoAmistad obj = (EstadoAmistad) bean;
					valNotInstance = true;
					if (obj.getIdEstadoAmistad() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoAmistad.class, obj.getIdEstadoAmistad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdEstadoAmistad());
							resultado = new LogicEstadoAmistad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoCuenta) {/* Estado Cuenta */
					EstadoCuenta obj = (EstadoCuenta) bean;
					valNotInstance = true;
					if (obj.getIdEstadoCuenta() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoCuenta.class, obj.getIdEstadoCuenta(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdEstadoCuenta());
							resultado = new LogicEstadoCuenta(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoMiembro) {/* Estado Miembro */
					EstadoMiembro obj = (EstadoMiembro) bean;
					valNotInstance = true;
					if (obj.getIdEstadoMiembro() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoMiembro.class, obj.getIdEstadoMiembro(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E"); // cambio realizado
							parametro.setId(obj.getIdEstadoMiembro());
							resultado = new LogicEstadoMiembro(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EstadoSolicAmistad) {/* EstadoSolicAmistad */
					EstadoSolicAmistad obj = (EstadoSolicAmistad) bean;
					valNotInstance = true;
					if (obj.getIdEstadoSolicAmistad() != null) {
						valOperAndId = true;
						if (getObjeto(EstadoSolicAmistad.class, obj.getIdEstadoSolicAmistad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setId(obj.getIdEstadoSolicAmistad());
							parametro.setTipoOperacion("E");
							resultado = new LogicEstadoSolicAmistad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Idioma) {/* Idioma */
					Idioma obj = (Idioma) bean;
					valNotInstance = true;
					if (obj.getIdIdioma() != null) {
						valOperAndId = true;
						if (getObjeto(Idioma.class, obj.getIdIdioma(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdIdioma());
							resultado = new LogicIdioma(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Localidad) {/* Localidad */
					Localidad obj = (Localidad) bean;
					valNotInstance = true;
					if (obj.getIdLocalidad() != null) {
						valOperAndId = true;
						if (getObjeto(Localidad.class, obj.getIdLocalidad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdLocalidad());
							resultado = new LogicLocalidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Moneda) {/* Moneda */
					Moneda obj = (Moneda) bean;
					valNotInstance = true;
					if (obj.getIdMoneda() != null) {
						valOperAndId = true;
						if (getObjeto(Moneda.class, obj.getIdMoneda(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdMoneda());
							resultado = new LogicMoneda(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Pais) {/* Pais */
					Pais obj = (Pais) bean;
					valNotInstance = true;
					if (obj.getIdPais() != null) {
						valOperAndId = true;
						if (getObjeto(Pais.class, obj.getIdPais(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdPais());
							resultado = new LogicPais(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof PaisMoneda) {/* Pais Moneda */
					PaisMoneda obj = (PaisMoneda) bean;
					valNotInstance = true;
					if (obj.getIdPaisMoneda() != null) {
						valOperAndId = true;
						if (getObjeto(PaisMoneda.class, obj.getIdPaisMoneda(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdPaisMoneda());
							resultado = new LogicPaisMoneda(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Privacidad) {/* Privacidad */
					Privacidad obj = (Privacidad) bean;
					valNotInstance = true;
					if (obj.getIdPrivacidad() != null) {
						valOperAndId = true;
						if (getObjeto(Privacidad.class, obj.getIdPrivacidad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdPrivacidad());
							resultado = new LogicPrivacidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof RedSocial) {/* Red Social Miembro */
					RedSocial obj = (RedSocial) bean;
					valNotInstance = true;
					if (obj.getIdRedSocial() != null) {
						valOperAndId = true;
						if (getObjeto(RedSocial.class, obj.getIdRedSocial(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdRedSocial());
							resultado = new LogicRedSocial(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Region) {/* Region */
					Region obj = (Region) bean;
					valNotInstance = true;
					if (obj.getIdRegion() != null) {
						valOperAndId = true;
						if (getObjeto(Region.class, obj.getIdRegion(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdRegion());
							resultado = new LogicRegion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoAnuncio) {/* Tipo Anuncio */
					TipoAnuncio obj = (TipoAnuncio) bean;
					valNotInstance = true;
					if (obj.getIdTipoAnuncio() != null) {
						valOperAndId = true;
						if (getObjeto(TipoAnuncio.class, obj.getIdTipoAnuncio(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoAnuncio());
							resultado = new LogicTipoAnuncio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoCambio) {/* Tipo Cambio */
					TipoCambio obj = (TipoCambio) bean;
					valNotInstance = true;
					if (obj.getIdTipoCambio() != null) {
						valOperAndId = true;
						if (getObjeto(TipoCambio.class, obj.getIdTipoCambio(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoCambio());
							resultado = new LogicTipoCambio(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoEmpatia) {/* Tipo Empatia */
					TipoEmpatia obj = (TipoEmpatia) bean;
					valNotInstance = true;
					if (obj.getIdTipoEmpatia() != null) {
						valOperAndId = true;
						if (getObjeto(TipoEmpatia.class, obj.getIdTipoEmpatia(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoEmpatia());
							resultado = new LogicTipoEmpatia(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoMovimiento) {/*
															 * Tipo Movimiento
															 */
					TipoMovimiento obj = (TipoMovimiento) bean;
					valNotInstance = true;
					if (obj.getIdTipoMovimiento() != null) {
						valOperAndId = true;
						if (getObjeto(TipoMovimiento.class, obj.getIdTipoMovimiento(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoMovimiento());
							resultado = new LogicTipoMovimiento(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoNotificacion) {/*
																 * Tipo
																 * Notificacion
																 */
					TipoNotificacion obj = (TipoNotificacion) bean;
					valNotInstance = true;
					if (obj.getIdTipoNotificacion() != null) {
						valOperAndId = true;
						if (getObjeto(TipoNotificacion.class, obj.getIdTipoNotificacion(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoNotificacion());
							resultado = new LogicTipoNotificacion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoSuscripcion) {/*
																 * Tipo
																 * Suscripcion
																 */
					TipoSuscripcion obj = (TipoSuscripcion) bean;
					valNotInstance = true;
					if (obj.getIdTipoSuscripcion() != null) {
						valOperAndId = true;
						if (getObjeto(TipoSuscripcion.class, obj.getIdTipoSuscripcion(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoSuscripcion());
							resultado = new LogicTipoSuscripcion(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof CasaTarjeta) {/* Casa Tarjeta */
					CasaTarjeta obj = (CasaTarjeta) bean;
					valNotInstance = true;
					if (obj.getIdCasaTarjeta() != null) {
						valOperAndId = true;
						if (getObjeto(CasaTarjeta.class, obj.getIdCasaTarjeta(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdCasaTarjeta());
							resultado = new LogicCasaTarjeta(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof EntidadFinanciera) {/*
																 * Entidad
																 * Financiera
																 */
					EntidadFinanciera obj = (EntidadFinanciera) bean;
					valNotInstance = true;
					if (obj.getIdEntidadFinanciera() != null) {
						valOperAndId = true;
						if (getObjeto(EntidadFinanciera.class, obj.getIdEntidadFinanciera(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdEntidadFinanciera());
							resultado = new LogicEntidadFinanciera(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof FormaPago) {/* Forma Pago */
					FormaPago obj = (FormaPago) bean;
					valNotInstance = true;
					if (obj.getIdFormaPago() != null) {
						valOperAndId = true;
						if (getObjeto(FormaPago.class, obj.getIdFormaPago(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdFormaPago());
							resultado = new LogicFormaPago(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof ParametrosSistema) {/*
																 * Parametros
																 * del Sistema
																 */
					ParametrosSistema obj = (ParametrosSistema) bean;
					valNotInstance = true;
					if (obj.getIdParametrosSistema() != null) {
						valOperAndId = true;
						if (getObjeto(ParametrosSistema.class, obj.getIdParametrosSistema(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdParametrosSistema());
							resultado = new LogicParametrosSistema(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof Tarifario) {/* Tarifario */
					Tarifario obj = (Tarifario) bean;
					valNotInstance = true;
					if (obj.getIdTarifario() != null) {
						valOperAndId = true;
						if (getObjeto(Tarifario.class, obj.getIdTarifario(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTarifario());
							resultado = new LogicTarifario(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				} else if (bean instanceof TipoInterPublicidad) {/*
																	 * Tipo
																	 * Interaccion
																	 * Publicidad
																	 */
					TipoInterPublicidad obj = (TipoInterPublicidad) bean;
					valNotInstance = true;
					if (obj.getIdTipoInterPublicidad() != null) {
						valOperAndId = true;
						if (getObjeto(TipoInterPublicidad.class, obj.getIdTipoInterPublicidad(), pm) != null) {
							parametro.setBean(obj);
							parametro.setTipoOperacion(obj.getOperacion());
							parametro.setTipoOperacion("E");
							parametro.setId(obj.getIdTipoInterPublicidad());
							resultado = new LogicTipoInterPublicidad(pm).mantenimiento(parametro);
							valExisteId = true;
						}
					}
				}
			}
			retornarMensajeError(valBeanNull, valNotInstance, valOperAndId, valExisteId, false);
			if (resultado) {
				tx.commit();
				pm.close();
				return true;
			} else {
				tx.rollback();
				pm.close();
				return false;
			}
		} catch (Exception ex) {
			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static Object getList(Class<?> nomClase, ListFilterBean filter) throws UnknownException {
		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();

			if (nomClase == UsuarioAdmin.class) {
				PMF.getPMF().getFetchGroup(UsuarioAdmin.class, "UsuarioAdminGroup").addMembers("beanCuentaAdmin");
				PMF.getPMF().getFetchGroup(CuentaAdmin.class, "CuentaAdminGroup").addMembers("estadoCuenta");
				pm.getFetchPlan().addGroup("UsuarioAdminGroup");
				pm.getFetchPlan().addGroup("CuentaAdminGroup");
				pm.getFetchPlan().setMaxFetchDepth(2);
				tx = pm.currentTransaction();
				tx.begin();
				pm.setDetachAllOnCommit(true);
				List<UsuarioAdmin> resultado = (List<UsuarioAdmin>) pm
						.detachCopyAll(new LogicUsuarioAdmin(pm).getListarBean());
				tx.commit();
				return resultado;
			} else if (nomClase == CategoriaDestino.class) {
				return new LogicCategoriaDestino(pm).getListarBean();
			} else if (nomClase == CategoriaNegocio.class) {
				return new LogicCategoriaNegocio(pm).getListarBean();
			} else if (nomClase == EstadoCuenta.class) {
				return new LogicEstadoCuenta(pm).getListarBean();
			} else if (nomClase == EstadoAmistad.class) {
				return new LogicEstadoAmistad(pm).getListarBean();
			} else if (nomClase == EstadoMiembro.class) {
				return new LogicEstadoMiembro(pm).getListarBean();
			} else if (nomClase == EstadoSolicAmistad.class) {
				return new LogicEstadoSolicAmistad(pm).getListarBean();
			} else if (nomClase == Idioma.class) {
				return new LogicIdioma(pm).getListarBean();
			} else if (nomClase == Moneda.class) {
				if (filter == null) {
					return new LogicMoneda(pm).getListarBean();
				} else {
					if (filter.getNameQuery().equalsIgnoreCase(Moneda.LISTARMONEDASACTIVAS)) {
						return new LogicMoneda(pm).getListarBeanActivos();
					}
				}

			} else if (nomClase == PaisMoneda.class) {
				PMF.getPMF().getFetchGroup(PaisMoneda.class, "PaisMonedaGroup").addMembers("beanMoneda", "beanPais");
				pm.getFetchPlan().addGroup("PaisMonedaGroup");
				pm.getFetchPlan().setMaxFetchDepth(1);
				tx = pm.currentTransaction();
				tx.begin();
				pm.setDetachAllOnCommit(true);
				List<PaisMoneda> resultado = (List<PaisMoneda>) pm
						.detachCopyAll(new LogicPaisMoneda(pm).getListarBean());
				tx.commit();
				return resultado;
			} else if (nomClase == Privacidad.class) {
				return new LogicPrivacidad(pm).getListarBean();
			} else if (nomClase == RedSocial.class) {
				return new LogicRedSocial(pm).getListarBean();
			} else if (nomClase == Region.class) {
				if (filter == null) {
					PMF.getPMF().getFetchGroup(Region.class, "RegionGroup").addMember("beanPais");
					pm.getFetchPlan().addGroup("RegionGroup");
					pm.getFetchPlan().setMaxFetchDepth(1);
					tx = pm.currentTransaction();
					tx.begin();
					pm.setDetachAllOnCommit(true);
					List<Region> resultado = (List<Region>) pm.detachCopyAll(new LogicRegion(pm).getListarBean());
					tx.commit();
					return resultado;
				} else {
					if (filter.getNameQuery().equalsIgnoreCase(Region.LISTAREGIONXPAIS)) {
						PMF.getPMF().getFetchGroup(Region.class, "RegionGroup").addMember("beanPais");
						pm.getFetchPlan().addGroup("RegionGroup");
						pm.getFetchPlan().setMaxFetchDepth(1);
						tx = pm.currentTransaction();
						tx.begin();
						pm.setDetachAllOnCommit(true);
						List<Region> resultado = (List<Region>) pm.detachCopyAll(new LogicRegion(pm)
								.getListarBeanByPais(filter.getParamFilter().get("codePais").toString()));
						tx.commit();
						return resultado;
					}
				}
			} else if (nomClase == TipoAnuncio.class) {
				return new LogicTipoAnuncio(pm).getListarBean();
			} else if (nomClase == TipoCambio.class) {
				PMF.getPMF().getFetchGroup(TipoCambio.class, "TipoCambioGroup").addMembers("beanPaisMonedaOrigen",
						"beanPaisMonedaDestino");
				pm.getFetchPlan().addGroup("TipoCambioGroup");
				pm.getFetchPlan().setMaxFetchDepth(1);
				tx = pm.currentTransaction();
				tx.begin();
				pm.setDetachAllOnCommit(true);
				List<TipoCambio> resultado = (List<TipoCambio>) pm
						.detachCopyAll(new LogicTipoCambio(pm).getListarBean());
				tx.commit();
				return resultado;
			} else if (nomClase == TipoEmpatia.class) {
				return new LogicTipoEmpatia(pm).getListarBean();
			} else if (nomClase == TipoMovimiento.class) {
				return new LogicTipoMovimiento(pm).getListarBean();
			} else if (nomClase == TipoNotificacion.class) {
				return new LogicTipoNotificacion(pm).getListarBean();
			} else if (nomClase == TipoSuscripcion.class) {
				if (filter == null) {
					return new LogicTipoSuscripcion(pm).getListarBean();
				} else {
					if (filter.getNameQuery().equalsIgnoreCase(TipoSuscripcion.LISTARTIPOSUSCRIPCIONACTIVA)) {
						return new LogicTipoSuscripcion(pm).getListarBeanActivos();
					}
				}
			} else if (nomClase == CasaTarjeta.class) {
				return new LogicCasaTarjeta(pm).getListarBean();
			} else if (nomClase == EntidadFinanciera.class) {
				PMF.getPMF().getFetchGroup(EntidadFinanciera.class, "EntidadFinancieraGroup").addMember("beanPais");
				pm.getFetchPlan().addGroup("EntidadFinancieraGroup");
				pm.getFetchPlan().setMaxFetchDepth(1);
				tx = pm.currentTransaction();
				tx.begin();
				pm.setDetachAllOnCommit(true);
				List<EntidadFinanciera> resultado = (List<EntidadFinanciera>) pm
						.detachCopyAll(new LogicEntidadFinanciera(pm).getListarBean());
				tx.commit();
				return resultado;
			} else if (nomClase == FormaPago.class) {
				return new LogicFormaPago(pm).getListarBean();
			} else if (nomClase == ParametrosSistema.class) {
				return new LogicParametrosSistema(pm).getListarBean();
			} else if (nomClase == Tarifario.class) {
				PMF.getPMF().getFetchGroup(Tarifario.class, "TarifarioGroup").addMembers("beanPais",
						"beanTipoSuscripcion");
				pm.getFetchPlan().addGroup("TarifarioGroup");
				pm.getFetchPlan().setMaxFetchDepth(1);
				tx = pm.currentTransaction();
				tx.begin();
				pm.setDetachAllOnCommit(true);
				List<Tarifario> resultado = (List<Tarifario>) pm.detachCopyAll(new LogicTarifario(pm).getListarBean());
				tx.commit();
				return resultado;
			} else if (nomClase == TipoInterPublicidad.class) {
				return new LogicTipoInterPublicidad(pm).getListarBean();
			} else if (nomClase == Pais.class) {
				return new LogicPais(pm).getListarBean();
			} else if (nomClase == Localidad.class) {
				if (filter == null) {
					PMF.getPMF().getFetchGroup(Localidad.class, "LocalidadGroup").addMembers("beanRegion", "beanPais");				
					pm.getFetchPlan().addGroup("LocalidadGroup");
					pm.getFetchPlan().setMaxFetchDepth(1);
					tx = pm.currentTransaction();
					tx.begin();
					pm.setDetachAllOnCommit(true);
					List<Localidad> resultado = (List<Localidad>) pm
							.detachCopyAll(new LogicLocalidad(pm).getListarBean());
					tx.commit();					
					return resultado;
				}
			}
		} catch (Exception kngException) {
			throw new UnknownException(kngException.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx != null) {
					if (tx.isActive()) {
						tx.rollback();
					}
				}
				pm.close();
			}
		}

		return null;
	}

	public static UsuarioAdmin logeoUsuarioAdmin(String correo, String clave) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			Key idUsuarioAdmin = KeyFactory.createKey(UsuarioAdmin.class.getSimpleName(), correo);
			String idCuentaAdmin = KeyFactory
					.keyToString(KeyFactory.createKey(idUsuarioAdmin, CuentaAdmin.class.getSimpleName(), correo));			
			CuentaAdmin beanCuentaAdminBD = (CuentaAdmin) getObjeto(CuentaAdmin.class,
					idCuentaAdmin, pm);
			String claveEncriptada = AESencrypt.encrypt(clave);
			if (beanCuentaAdminBD != null && beanCuentaAdminBD.getClave().equalsIgnoreCase(claveEncriptada)) {
				return (UsuarioAdmin) getObjeto(UsuarioAdmin.class, KeyFactory.keyToString(idUsuarioAdmin), pm);
			} else {
				throw new UnknownException("Usuario o clave incorrectos");
			}
		} catch (Exception ex) {
			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				pm.close();
			}
		}
	}

	public static UsuarioAdmin insertarUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) throws UnknownException {
		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			if (existeCuenta(beanUsuarioAdmin.getCorreo(), pm) != null) {
				throw new UnknownException("Existe Cuenta");
			}
			BeanParametro parameter = new BeanParametro();
			CuentaAdmin beanCuentaAdmin = new CuentaAdmin();
			beanCuentaAdmin = beanUsuarioAdmin.getBeanCuentaAdmin();
			Key idUsuarioAdmin = KeyFactory.createKey(UsuarioAdmin.class.getSimpleName(), beanUsuarioAdmin.getCorreo());
			String idCuentaAdmin = KeyFactory.keyToString(KeyFactory.createKey(idUsuarioAdmin,
					CuentaAdmin.class.getSimpleName(), beanUsuarioAdmin.getCorreo()));
			beanCuentaAdmin.setIdCuentaAdmin(idCuentaAdmin);
			beanCuentaAdmin.setCorreo(beanUsuarioAdmin.getCorreo());
			beanCuentaAdmin.setClave(AESencrypt.encrypt(beanCuentaAdmin.getClave()));
			beanCuentaAdmin.setOperacion("I");
			EstadoCuenta beanEstadoCuenta = (EstadoCuenta) getObjeto(EstadoCuenta.class,
					beanCuentaAdmin.getEstadoCuenta().getIdEstadoCuenta(), pm);
			beanCuentaAdmin.setEstadoCuenta(beanEstadoCuenta);
			beanCuentaAdmin.setCodeEstadoCuenta(beanEstadoCuenta.getCodeEstadoCuenta());
			beanCuentaAdmin.setFechaCreacion(new java.util.Date());
			beanCuentaAdmin.setIdUsuarioAdmin(beanUsuarioAdmin.getCorreo());
			beanCuentaAdmin.setIsPersistente(true);
			beanCuentaAdmin.setVersion((new java.util.Date()).getTime());
			beanUsuarioAdmin.setCodeUsuarioAdmin(beanUsuarioAdmin.getCorreo());
			beanUsuarioAdmin.setCorreo(beanUsuarioAdmin.getCorreo());
			beanUsuarioAdmin.setIdUsuarioAdmin(KeyFactory.keyToString(idUsuarioAdmin));
			beanUsuarioAdmin.setBeanCuentaAdmin(beanCuentaAdmin);
			beanCuentaAdmin.setBeanUsuarioAdmin(beanUsuarioAdmin);
			parameter.setBean(beanUsuarioAdmin);
			parameter.setTipoOperacion(beanUsuarioAdmin.getOperacion());
			beanUsuarioAdmin = (UsuarioAdmin) new LogicUsuarioAdmin(pm).mantenimiento(parameter);
			Boolean validarCuenta = enviarMsgValidarCuenta(beanCuentaAdmin.getCorreo(), beanCuentaAdmin.getClave(),
					beanUsuarioAdmin.getNombre(), beanUsuarioAdmin.getApellido());
			if (validarCuenta) {		
				tx.commit();
				pm.close();				
				return beanUsuarioAdmin;
			} else {
				tx.rollback();
				pm.close();
				return null;
			}
		} catch (Exception ex) {
			LOG.warning("Metodo: insertarUsuarioAdmin: "+ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static UsuarioAdmin actualizarUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			UsuarioAdmin beanUsuarioAdminBD = (UsuarioAdmin) getObjeto(UsuarioAdmin.class, beanUsuarioAdmin.getCorreo(),
					pm);
			if (beanUsuarioAdminBD == null) {
				throw new UnknownException("No Existe el Usuario!");
			}
			CuentaAdmin beanCuentaAdmin = existeCuenta(beanUsuarioAdmin.getBeanCuentaAdmin().getCorreo(), pm);
			if (beanCuentaAdmin == null) {
				throw new UnknownException("No Existe Cuenta");
			}
			BeanParametro parameter = new BeanParametro();
			beanUsuarioAdminBD.setNombre(beanUsuarioAdmin.getNombre());
			beanUsuarioAdminBD.setApellido(beanUsuarioAdmin.getApellido());
			beanUsuarioAdminBD.setDni(beanUsuarioAdmin.getDni());
			beanUsuarioAdminBD.setTelefono(beanUsuarioAdmin.getTelefono());
			beanUsuarioAdminBD.setIsPersistente(beanUsuarioAdmin.getIsPersistente());
			EstadoCuenta beanEstadoCuenta = (EstadoCuenta) getObjeto(EstadoCuenta.class,
					beanUsuarioAdmin.getBeanCuentaAdmin().getEstadoCuenta().getIdEstadoCuenta(), pm);
			beanCuentaAdmin.setEstadoCuenta(beanEstadoCuenta);
			beanCuentaAdmin.setCodeEstadoCuenta(beanEstadoCuenta.getCodeEstadoCuenta());
			beanCuentaAdmin.setIsPersistente(beanUsuarioAdmin.getBeanCuentaAdmin().getIsPersistente());
			beanCuentaAdmin.setOperacion(beanUsuarioAdmin.getOperacion());
			beanUsuarioAdminBD.setOperacion(beanUsuarioAdmin.getOperacion());
			beanUsuarioAdminBD.setBeanCuentaAdmin(beanCuentaAdmin);
			parameter.setBean(beanUsuarioAdminBD);
			parameter.setTipoOperacion(beanUsuarioAdminBD.getOperacion());
			new LogicUsuarioAdmin(pm).mantenimiento(parameter);
			Boolean resultado1 = true;
			if (resultado1) {
				Boolean resultado2 = true;
				if (resultado2) {
					tx.commit();
					pm.close();
				}
				return beanUsuarioAdminBD;
			} else {
				tx.rollback();
				pm.close();
				return null;
			}
		} catch (Exception ex) {
			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static Boolean eliminarUsuarioAdmin(String correoUsuarioAdmin) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			BeanParametro parameter = new BeanParametro();
			UsuarioAdmin beanUsuarioAdminBD = (UsuarioAdmin) getObjeto(UsuarioAdmin.class, correoUsuarioAdmin, pm);
			if (beanUsuarioAdminBD == null) {
				throw new UnknownException("No Existe el Usuario!");
			}
			CuentaAdmin beanCuentaAdmin = new CuentaAdmin();
			beanCuentaAdmin = beanUsuarioAdminBD.getBeanCuentaAdmin();
			beanUsuarioAdminBD.setOperacion("E");
			parameter.setBean(beanUsuarioAdminBD);
			parameter.setTipoOperacion(beanUsuarioAdminBD.getOperacion());
			parameter.setId(beanUsuarioAdminBD.getIdUsuarioAdmin());
			new LogicUsuarioAdmin(pm).mantenimiento(parameter);

			Boolean resultado1 = true;
			if (resultado1) {
				beanCuentaAdmin.setOperacion("E");
				parameter.setBean(beanCuentaAdmin);
				parameter.setTipoOperacion(beanCuentaAdmin.getOperacion());
				parameter.setId(beanCuentaAdmin.getIdCuentaAdmin());
				new LogicCuentaAdmin(pm).mantenimiento(parameter);
				Boolean resultado2 = true;
				if (resultado2) {
					tx.commit();
					pm.close();
				}
				return true;
			} else {
				tx.rollback();
				pm.close();
				return false;
			}
		} catch (Exception ex) {
			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static Boolean cambiarClaveUsuarioAdmin(String correoUsuarioAdmin, String passwordInput,
			String passwordNuevo, String confirmarClave) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();

			BeanParametro parameter = new BeanParametro();

			if (!passwordNuevo.equals(confirmarClave)) {
				throw new UnknownException(
						"Las claves no coinciden");
			}
			UsuarioAdmin beanUsuarioAdminBD = (UsuarioAdmin) getObjeto(UsuarioAdmin.class, correoUsuarioAdmin, pm);

			if (beanUsuarioAdminBD == null) {
				throw new UnknownException("No Existe el Usuario!");
			}
			String passwordBD = AESencrypt.decrypt(beanUsuarioAdminBD.getBeanCuentaAdmin().getClave());

			if (!passwordBD
					.equals(passwordInput)) { 
				throw new UnknownException("Las claves no coinciden");
			}
			CuentaAdmin beanCuentaAdmin = new CuentaAdmin();
			beanCuentaAdmin = beanUsuarioAdminBD.getBeanCuentaAdmin();
			beanCuentaAdmin.setClave(AESencrypt.encrypt(passwordNuevo));
			beanCuentaAdmin.setOperacion("A");
			parameter.setBean(beanCuentaAdmin);
			parameter.setTipoOperacion(beanCuentaAdmin.getOperacion());
			parameter.setId(beanCuentaAdmin.getIdCuentaAdmin());
			new LogicCuentaAdmin(pm).mantenimiento(parameter);

			Boolean resultado1 = true;
			if (resultado1) {
				tx.commit();
				pm.close();
				return true;
			} else {
				tx.rollback();
				pm.close();
				return false;
			}
		} catch (Exception ex) {
			UnknownException.trazaConsola(LOG, ex);
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	private static CuentaAdmin existeCuenta(String correoUsuarioAdmin, PersistenceManager pm) throws UnknownException {
		LogicCuentaAdmin logic = new LogicCuentaAdmin(pm);
		Key keyUsuarioAdmin = KeyFactory.createKey(UsuarioAdmin.class.getSimpleName(), correoUsuarioAdmin);
		Key keyCuentaAdmin = KeyFactory.createKey(keyUsuarioAdmin, CuentaAdmin.class.getSimpleName(),
				correoUsuarioAdmin);
		CuentaAdmin beanVerify= (CuentaAdmin) logic.getBean(KeyFactory.keyToString(keyCuentaAdmin));
		return beanVerify ;

	}
	
	public static Boolean enviarMsgValidarCuenta(String correo,String clave,String nombres,String apellidos) throws UnknownException{
		try {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody=buildMensajeActivarCuenta(correo,clave);
		String remitente=P.CORREOREMITENTE;
		Message msg = new MimeMessage(session);
		Multipart mp = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();		
		msg.setFrom(new InternetAddress(remitente.trim().toString(), "Indiant"));
		String usuario="Sr(a). "+nombres+" "+apellidos;
		msg.addRecipient(Message.RecipientType.TO,new InternetAddress(correo.trim().toString(), usuario));	
		msg.setSubject("Bienvenido a Indiant - Active su cuenta");
		htmlPart.setContent(msgBody, "text/html");	
		mp.addBodyPart(htmlPart);
		msg.setContent(mp);
		Transport.send(msg);		
		return true;
		} catch (UnsupportedEncodingException ex) {			
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} catch (MessagingException ex) {			
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}		
	}
	
	private static String buildMensajeActivarCuenta(String correo,String clave) throws UnknownException{
		try {
		String msgBody = "<div id=\"titulo\" style=\"background-color:#009933; color:white; text-align: center; font-weight: bold; font-size:6mm; line-height:12mm\">INDIANT</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Bienvenido a la administracion de Indiant.</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Usuario: "+correo+"</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Contrase&ntilde;a: "+AESencrypt.decrypt(clave)+"</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Valida tu cuenta haciendo click en el siguiente enlace</div>";		
		String encrip=AESencrypt.encrypt(correo);
		String hex=StringHex.convertStringToHex(encrip);
		msgBody = msgBody+"<div style=\"padding:3mm; height:9mm; \"><a style=\"width:100%; height:100%;\"  href='"+P.URL+"/activeaccountadmin.html?encoded="+hex+"'\">Validar Cuenta</a></div>";
		return msgBody;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}

	public static Object getObjeto(Class<?> nomClase, Object id, PersistenceManager pm) throws UnknownException {
		Object objetoOutput = null;
		try {
			String idBean = (String) id;
			if (nomClase == CategoriaDestino.class) {
				objetoOutput = (CategoriaDestino) new LogicCategoriaDestino(pm).getBean(idBean);
			} else if (nomClase == CategoriaNegocio.class) {
				objetoOutput = (CategoriaNegocio) new LogicCategoriaNegocio(pm).getBean(idBean);
			} else if (nomClase == EstadoAmistad.class) {
				objetoOutput = (EstadoAmistad) new LogicEstadoAmistad(pm).getBean(idBean);
			} else if (nomClase == EstadoCuenta.class) {
				objetoOutput = (EstadoCuenta) new LogicEstadoCuenta(pm).getBean(idBean);
			} else if (nomClase == EstadoMiembro.class) {
				objetoOutput = (EstadoMiembro) new LogicEstadoMiembro(pm).getBean(idBean);
			} else if (nomClase == EstadoSolicAmistad.class) {
				objetoOutput = (EstadoSolicAmistad) new LogicEstadoSolicAmistad(pm).getBean(idBean);
			} else if (nomClase == Idioma.class) {
				objetoOutput = (Idioma) new LogicIdioma(pm).getBean(idBean);
			} else if (nomClase == Localidad.class) {
				objetoOutput = (Localidad) new LogicLocalidad(pm).getBean(idBean);
			} else if (nomClase == Moneda.class) {
				objetoOutput = (Moneda) new LogicMoneda(pm).getBean(idBean);
			} else if (nomClase == Pais.class) {
				objetoOutput = (Pais) new LogicPais(pm).getBean(idBean);
			} else if (nomClase == PaisMoneda.class) {
				objetoOutput = (PaisMoneda) new LogicPaisMoneda(pm).getBean(idBean);
			} else if (nomClase == Privacidad.class) {
				objetoOutput = (Privacidad) new LogicPrivacidad(pm).getBean(idBean);
			} else if (nomClase == RedSocial.class) {
				objetoOutput = (RedSocial) new LogicRedSocial(pm).getBean(idBean);
			} else if (nomClase == Region.class) {
				objetoOutput = (Region) new LogicRegion(pm).getBean(idBean);
			} else if (nomClase == TipoAnuncio.class) {
				objetoOutput = (TipoAnuncio) new LogicTipoAnuncio(pm).getBean(idBean);
			} else if (nomClase == TipoCambio.class) {
				objetoOutput = (TipoCambio) new LogicTipoCambio(pm).getBean(idBean);
			} else if (nomClase == TipoEmpatia.class) {
				objetoOutput = (TipoEmpatia) new LogicTipoEmpatia(pm).getBean(idBean);
			} else if (nomClase == TipoMovimiento.class) {
				objetoOutput = (TipoMovimiento) new LogicTipoMovimiento(pm).getBean(idBean);
			} else if (nomClase == TipoNotificacion.class) {
				objetoOutput = (TipoNotificacion) new LogicTipoNotificacion(pm).getBean(idBean);
			} else if (nomClase == TipoSuscripcion.class) {
				objetoOutput = (TipoSuscripcion) new LogicTipoSuscripcion(pm).getBean(idBean);
			} else if (nomClase == UsuarioAdmin.class) {
				objetoOutput = (UsuarioAdmin) new LogicUsuarioAdmin(pm).getBean(idBean);
			} else if (nomClase == Interes.class) {
				objetoOutput = (Interes) new LogicInteres(pm).getBean(idBean);
			} else if (nomClase == Colonia.class) {
				objetoOutput = (Colonia) new LogicColonia(pm).getBean(idBean);
			} else if (nomClase == UsuarioTurista.class) {
				objetoOutput = (UsuarioTurista) new LogicUsuarioTurista(pm).getBean(idBean);
			} else if (nomClase == DetalleInteres.class) {
				objetoOutput = (DetalleInteres) new LogicDetalleInteres(pm).getBean(idBean);
			} else if (nomClase == MiembroInteres.class) {
				objetoOutput = (MiembroInteres) new LogicMiembroInteres(pm).getBean(idBean);
			} else if (nomClase == TuristaInteres.class) {
				objetoOutput = (TuristaInteres) new LogicTuristaInteres(pm).getBean(idBean);
			} else if (nomClase == Miembro.class) {
				objetoOutput = (Miembro) new LogicMiembro(pm).getBean(idBean);
			} else if (nomClase == ColoniaInteres.class) {
				objetoOutput = (ColoniaInteres) new LogicColoniaInteres(pm).getBean(idBean);
			} else if (nomClase == CuentaAdmin.class) {
				objetoOutput = (CuentaAdmin) new LogicCuentaAdmin(pm).getBean(idBean);
			} else if (nomClase == CasaTarjeta.class) {
				objetoOutput = (CasaTarjeta) new LogicCasaTarjeta(pm).getBean(idBean);
			} else if (nomClase == EntidadFinanciera.class) {
				objetoOutput = (EntidadFinanciera) new LogicEntidadFinanciera(pm).getBean(idBean);
			} else if (nomClase == FormaPago.class) {
				objetoOutput = (FormaPago) new LogicFormaPago(pm).getBean(idBean);
			} else if (nomClase == ParametrosSistema.class) {
				objetoOutput = (ParametrosSistema) new LogicParametrosSistema(pm).getBean(idBean);
			} else if (nomClase == Tarifario.class) {
				objetoOutput = (Tarifario) new LogicTarifario(pm).getBean(idBean);
			} else if (nomClase == TipoInterPublicidad.class) {
				objetoOutput = (TipoInterPublicidad) new LogicTipoInterPublicidad(pm).getBean(idBean);
			} 

		} catch (Exception ex) {
			LOG.warning(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
		return objetoOutput;
	}

	private static void retornarMensajeError(Boolean valBeanNull, Boolean valNotInstance, Boolean valOperAndId,
			Boolean valExisteId, Boolean insertar) throws UnknownException {
		if (!valBeanNull) {
			throw new UnknownException("Valor Null");
		} else {
			if (!valNotInstance) {
				throw new UnknownException("Instancia No Identificada!");
			} else {
				if (!valOperAndId) {
					throw new UnknownException("Se esperaba un Id!");
				} else {
					if (!valExisteId) {
						if (insertar) {
							throw new UnknownException("Id Existe en la BD!");
						} else {
							throw new UnknownException("No Existe El Id en la BD");
						}
					}
				}
			}
		}
	}

	public static boolean activarCuentaAdmin(String codigoActivacion) throws UnknownException{
		PersistenceManager pm = null;		
		Transaction tx=null;				
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
			Date fecha=new Date();			
			String emailEncoded=StringHex.convertHexToString(codigoActivacion);												
			String email=AESencrypt.decrypt(emailEncoded);	
			UsuarioAdmin beanUsuarioAdminBD = (UsuarioAdmin) getObjeto(UsuarioAdmin.class, email,pm);
			if (beanUsuarioAdminBD == null || !beanUsuarioAdminBD.getIsPersistente()) {
				throw new UnknownException("No Existe el Usuario!");
			}
			CuentaAdmin beanCuentaAdmin = existeCuenta(email, pm);
			if (beanCuentaAdmin == null || !beanCuentaAdmin.getIsPersistente()) {
				throw new UnknownException("No Existe Cuenta");
			}
			if(!beanCuentaAdmin.getEstadoCuenta().getCodeEstadoCuenta().equalsIgnoreCase("P")){
				return false;
			}			
			BeanParametro parameter = new BeanParametro();	
			EstadoCuenta beanEstadoCuenta = (EstadoCuenta) getObjeto(EstadoCuenta.class,"A", pm);
			beanCuentaAdmin.setEstadoCuenta(beanEstadoCuenta);
			beanCuentaAdmin.setCodeEstadoCuenta(beanEstadoCuenta.getCodeEstadoCuenta());
			beanCuentaAdmin.setVersion(fecha.getTime());
			beanUsuarioAdminBD.setVersion(fecha.getTime());
			beanUsuarioAdminBD.setOperacion("A");
			beanUsuarioAdminBD.setBeanCuentaAdmin(beanCuentaAdmin);
			parameter.setBean(beanUsuarioAdminBD);
			parameter.setTipoOperacion(beanUsuarioAdminBD.getOperacion());
			new LogicUsuarioAdmin(pm).mantenimiento(parameter);
			Boolean resultado = true;
			if (resultado) {
				tx.commit();
				pm.close();
				return true;
			} else {
				tx.rollback();
				pm.close();
				return false;
			}					
			} catch (Exception ex) {
				LOG.warning(ex.getMessage());
				LOG.info(ex.getLocalizedMessage());
				throw new UnknownException(ex.getMessage());
			}finally {
				if (!pm.isClosed()) {
					if(tx!=null){
						tx.rollback();
					}
					pm.close();
				}
			}
	}
}