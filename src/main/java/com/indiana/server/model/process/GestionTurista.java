package com.indiana.server.model.process;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.indiana.server.model.bean.Amistad;
import com.indiana.server.model.bean.AutenticaOauth;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.bean.ComparteNoticia;
import com.indiana.server.model.bean.ConfigCuenta;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.ControlPosicion;
import com.indiana.server.model.bean.CuentaTurista;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.DivulgacionNoticia;
import com.indiana.server.model.bean.Empatia;
import com.indiana.server.model.bean.EstadoAmistad;
import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.server.model.bean.EstadoSolicAmistad;
import com.indiana.server.model.bean.Idioma;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.NoticiaCompartida;
import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.bean.NotificacionTurista;
import com.indiana.server.model.bean.Novedad;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.bean.Privacidad;
import com.indiana.server.model.bean.RedSocial;
import com.indiana.server.model.bean.Region;
import com.indiana.server.model.bean.SesionTurista;
import com.indiana.server.model.bean.SolicitudAmistad;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicAmistad;
import com.indiana.server.model.logic.LogicAutenticaOauth;
import com.indiana.server.model.logic.LogicColonia;
import com.indiana.server.model.logic.LogicComentarioNoticia;
import com.indiana.server.model.logic.LogicComparteNoticia;
import com.indiana.server.model.logic.LogicConfigCuenta;
import com.indiana.server.model.logic.LogicConquista;
import com.indiana.server.model.logic.LogicControlPosicion;
import com.indiana.server.model.logic.LogicCuentaTurista;
import com.indiana.server.model.logic.LogicDivulgacionNoticia;
import com.indiana.server.model.logic.LogicEmpatia;
import com.indiana.server.model.logic.LogicEstadoAmistad;
import com.indiana.server.model.logic.LogicEstadoSolicAmistad;
import com.indiana.server.model.logic.LogicIdioma;
import com.indiana.server.model.logic.LogicMiembro;
import com.indiana.server.model.logic.LogicMuroNoticia;
import com.indiana.server.model.logic.LogicNoticia;
import com.indiana.server.model.logic.LogicNoticiaCompartida;
import com.indiana.server.model.logic.LogicNotificacion;
import com.indiana.server.model.logic.LogicNotificacionTurista;
import com.indiana.server.model.logic.LogicNovedad;
import com.indiana.server.model.logic.LogicPais;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicPrivacidad;
import com.indiana.server.model.logic.LogicRegion;
import com.indiana.server.model.logic.LogicSesionTurista;
import com.indiana.server.model.logic.LogicSolicitudAmistad;
import com.indiana.server.model.logic.LogicTipoNotificacion;
import com.indiana.server.model.logic.LogicTuristaInteres;
import com.indiana.server.model.logic.LogicUsuarioAdmin;
import com.indiana.server.model.logic.LogicUsuarioTurista;
import com.indiana.shared.AESencrypt;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.P;
import com.indiana.shared.StringHex;
import com.indiana.shared.UnknownException;
import com.jofrantoba.fcm.constants.EnumContentType;
import com.jofrantoba.fcm.entity.AndroidNotificationPayLoad;
import com.jofrantoba.fcm.entity.DataPayLoad;
import com.jofrantoba.fcm.entity.NotificationMessage;
import com.jofrantoba.fcm.http.HttpFcmConection;

public class GestionTurista {
	private static final Logger LOG = Logger.getLogger(GestionTurista.class.getName());
	
	public static Boolean cambiarClaveTurista(String correoTurista,String claveAnterior,String nuevaClave,String tipoAutenticacion) throws UnknownException {
		PersistenceManager pm = null;
		try {		
			pm = PMF.getPMF().getPersistenceManager();	
			CuentaTurista beanCuentaTurista=null;
			LogicCuentaTurista  logicCuentaTurista= new LogicCuentaTurista(pm);
			if(tipoAutenticacion.equals(P.REGISTRO)){
				beanCuentaTurista=verificarClaveTurista(correoTurista, claveAnterior, pm);					
			}else{
				if(tipoAutenticacion.equals(P.OAUTH)){
					beanCuentaTurista=(CuentaTurista) logicCuentaTurista.getBeanByEmail(correoTurista);
					if(!beanCuentaTurista.getTipo().equalsIgnoreCase(P.OAUTH)){
						throw new UnknownException("Usted creo su cuenta por red social");
					}
				}else{
					throw new UnknownException("Tipo Autenticacion No Definido.");
				}				
			}
			if(beanCuentaTurista==null){
				throw new UnknownException(P.CUENTA_NO_EXISTE);
			}									
			beanCuentaTurista.setClave(AESencrypt.encrypt(nuevaClave));				
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanCuentaTurista);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);				
			Boolean rptaCuentaTurista=new LogicCuentaTurista(pm).mantenimiento(beanParametro);
			if(!rptaCuentaTurista){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			return true;
		} catch (Exception ex) {			
			throw new UnknownException(ex.getMessage());		
		} finally {
			GestionShared.closeConnection(pm, null);
		  }
	}
	
	/**
	 * CODIGO: KCYS-77</br>
	 * CUS: CUS_LOGIN_TURISTA - ANS_TURISTA</br>
	 * ESTADO: TESTEADO</br>
	 * DESCRIPCION:
	 * El metodo loginTurista permite la autenticacion del usuario turista en el 
	 * sistema para lo cual tiene que enviar 2 parametros.</br>
	 * @param correoTurista  correo del usuario turista</br>
	 * @param claveTurista clave del usuario turista</br>
	 * @return SesionTurista : entidad que almacena datos de sesion al autenticar
	 * */
	public static SesionTurista loginTurista(String correoTurista, String claveTurista,String tokenFirebase)throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx=null;
		try {					
				pm = PMF.getPMF().getPersistenceManager();				
				tx=pm.currentTransaction();
				tx.begin();				
				CuentaTurista beanCuentaTurista=validarCuentaTurista(correoTurista, claveTurista, pm);			
				if(beanCuentaTurista!=null){													
					UsuarioTurista beanUsuarioTurista=beanCuentaTurista.getBeanUsuarioTurista();
					if(beanUsuarioTurista.getTokenFirebase()==null || beanUsuarioTurista.getTokenFirebase().equalsIgnoreCase(tokenFirebase)){
						beanUsuarioTurista.setTokenFirebase(tokenFirebase);
						beanCuentaTurista.setTokenFirebase(tokenFirebase);
					}
					SesionTurista beanSesionTurista=insertarSesionTurista(beanUsuarioTurista,pm);
						if(beanSesionTurista!=null){
							tx.commit();
							pm.close();
							beanSesionTurista.setCuentaCreadaPor(beanCuentaTurista.getTipo());
							beanSesionTurista.getBeanTurista().getBeanCuentaTurista().setBeanUsuarioTurista(null);
							beanSesionTurista.getBeanTurista().getBeanCuentaTurista().setClave(null);
							return beanSesionTurista;
						}else{
							throw new UnknownException("Error al crear la sesion");
						}														
				}else{
					throw new UnknownException(P.CUENTA_NO_EXISTE);
				}
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());		
		} finally {
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * CODIGO: KCYS-78
	 * CUS: CUS_CREAR_CUENTA_TURISTA - ANS_TURISTA
	 * ESTADO: TESTEADO
	 * */
	public static UsuarioTurista crearCuentaTurista(UsuarioTurista beanUsuarioTurista)throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();			
			String correoCuentaTurista=beanUsuarioTurista.getBeanCuentaTurista().getCorreoTurista();
			String claveCuentaTurista=beanUsuarioTurista.getBeanCuentaTurista().getClave();
			if(existeCuentaTurista(correoCuentaTurista,pm)){
				throw new UnknownException("La Cuenta ya se encuentra Registrada");
			}			
			if(beanUsuarioTurista.getBeanPaisNacimiento()!=null && 
					!beanUsuarioTurista.getBeanPaisNacimiento().getCodePais().isEmpty()){
				Pais beanPais= beanUsuarioTurista.getBeanPaisNacimiento();
				beanPais.setIdPais(KeyFactory.keyToString(KeyFactory.createKey(Pais.class.getSimpleName(),beanPais.getCodePais())));
				beanUsuarioTurista.setCodePaisNacimiento(beanPais.getCodePais());
				beanUsuarioTurista.setNombrePaisNacimiento(beanPais.getNombre());
			}else{
				beanUsuarioTurista.setBeanPaisNacimiento(null);
				beanUsuarioTurista.setCodePaisNacimiento(null);			
				beanUsuarioTurista.setNombrePaisNacimiento(null);
			}				
			Key keyUsuarioTurista = KeyFactory.createKey(UsuarioTurista.class.getSimpleName(), correoCuentaTurista);
			String idUsuarioTurista = KeyFactory.keyToString(keyUsuarioTurista);	
			beanUsuarioTurista.setIdUsuarioTurista(idUsuarioTurista);
			beanUsuarioTurista.setCodeUsuarioTurista(correoCuentaTurista);		
			beanUsuarioTurista.setCorreoTurista(correoCuentaTurista);
			beanUsuarioTurista.setVersion((new java.util.Date()).getTime());
			beanUsuarioTurista.setTotalConquistas(0);
			beanUsuarioTurista.setTotalDescubiertos(0);
			beanUsuarioTurista.setTotalColonias(0);
			beanUsuarioTurista.setEstadoActivacion(P.PENDIENTE);
			beanUsuarioTurista.setIsPersistente(true);
			CuentaTurista beanCuentaTurista=beanUsuarioTurista.getBeanCuentaTurista();			
			String idCuentaTurista=KeyFactory.keyToString( KeyFactory.createKey(keyUsuarioTurista,CuentaTurista.class.getSimpleName(),correoCuentaTurista));
			beanCuentaTurista.setIdCuentaTurista(idCuentaTurista);
			beanCuentaTurista.setClave(AESencrypt.encrypt(beanCuentaTurista.getClave()));
			beanCuentaTurista.setCodeUsuarioTurista(beanUsuarioTurista.getCodeUsuarioTurista());
			beanCuentaTurista.setCorreoTurista(correoCuentaTurista);				
			beanCuentaTurista.setFechaCreacion(new java.util.Date());
			beanCuentaTurista.setIsPersistente(true);
			beanCuentaTurista.setTipo(P.OAUTH);
			beanCuentaTurista.setVersion((new java.util.Date()).getTime());												
			String idEstadoCuenta=KeyFactory.keyToString(KeyFactory.createKey(EstadoCuenta.class.getSimpleName(), P.PENDIENTE));		
			EstadoCuenta beanEstadoCuenta=(EstadoCuenta)GestionMantenimiento.getObjeto(EstadoCuenta.class, idEstadoCuenta, pm);
			beanCuentaTurista.setBeanEstadoCuenta(beanEstadoCuenta);
			beanCuentaTurista.setCodeEstadoCuenta(beanEstadoCuenta.getCodeEstadoCuenta());	
			beanUsuarioTurista.setBeanCuentaTurista(beanCuentaTurista);
			BeanParametro parameter= new BeanParametro();
			parameter.setBean(beanUsuarioTurista);
			parameter.setTipoOperacion(P.INSERTAR);
			Boolean rptaUsuarioTurista=new LogicUsuarioTurista(pm).mantenimiento(parameter);
			if(rptaUsuarioTurista){													
						Boolean validarCuenta = enviarMsgValidarCuentaTurista(correoCuentaTurista,claveCuentaTurista,beanUsuarioTurista.getNombre(),beanUsuarioTurista.getApellido());
						if(validarCuenta){							
							Boolean rptaConfigCuenta= insertarConfiguracionCuenta(beanUsuarioTurista, parameter, pm);
							if(rptaConfigCuenta){
								tx.commit();						
								pm.close();
								beanUsuarioTurista.setBeanCuentaTurista(null);
								return beanUsuarioTurista;
							}	else{
								throw new UnknownException("Error al Configurar la Cuenta");
							}
						}else{
							throw new UnknownException("Error al enviar correo de activacion");
						}					
			}else{
				throw new UnknownException("Error al insertar usuario");
			}
		} catch (Exception ex) {						
			throw new UnknownException(ex.getMessage());			
		} finally {
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * CODIGO: KCYS-79
	 * CUS: CUS_ACTIVAR_CUENTA_TURISTA - ANS_TURISTA
	 * ESTADO: TESTEADO
	 * */
	public static Boolean activarCuentaTurista(String codigoActivacion) throws UnknownException{
		PersistenceManager pm = null;		
		Transaction tx=null;				
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
			Date fecha=new Date();			
			String emailEncoded=StringHex.convertHexToString(codigoActivacion);												
			String email=AESencrypt.decrypt(emailEncoded);	
			LogicUsuarioTurista logicUsuarioTurista=new LogicUsuarioTurista(pm);			
			UsuarioTurista beanUsuarioTuristaBD = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(email);
			if (beanUsuarioTuristaBD == null || !beanUsuarioTuristaBD.getIsPersistente()) {
				throw new UnknownException("No Existe el Usuario!");
			}
			LogicCuentaTurista logicCuentaTurista=new LogicCuentaTurista(pm);
			CuentaTurista beanCuentaTurista = (CuentaTurista)logicCuentaTurista.getBeanByEmail(email);
			if (beanCuentaTurista== null || !beanCuentaTurista.getIsPersistente()) {
				throw new UnknownException("No Existe Cuenta");
			}
			if(!beanCuentaTurista.getBeanEstadoCuenta().getCodeEstadoCuenta().equalsIgnoreCase(P.PENDIENTE)){
				throw new UnknownException("Cuenta "+beanCuentaTurista.getBeanEstadoCuenta().getDescripcion());
			}			
			BeanParametro parameter = new BeanParametro();	
			EstadoCuenta beanEstadoCuenta = (EstadoCuenta) GestionMantenimiento.getObjeto(EstadoCuenta.class,"A", pm);
			beanCuentaTurista.setBeanEstadoCuenta(beanEstadoCuenta);
			beanCuentaTurista.setCodeEstadoCuenta(beanEstadoCuenta.getCodeEstadoCuenta());
			beanCuentaTurista.setVersion(fecha.getTime());
			beanUsuarioTuristaBD.setVersion(fecha.getTime());
			beanUsuarioTuristaBD.setOperacion(P.ACTUALIZAR);
			beanUsuarioTuristaBD.setBeanCuentaTurista(beanCuentaTurista);
			parameter.setBean(beanUsuarioTuristaBD);
			parameter.setTipoOperacion(beanUsuarioTuristaBD.getOperacion());
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
				throw new UnknownException(ex.getMessage());
			}finally {
				GestionShared.closeConnection(pm, tx);
			}
	}
	
	/**
	 * CODIGO: KCYS-80
	 * CUS: CUS_AUTENTICAR_VIA_OAUTH - ANS_TURISTA
	 * ESTADO: TESTEADO
	 * */
	public static SesionTurista autenticarViaOauth(AutenticaOauth beanOauthTurista)throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			String correoCuentaTurista=beanOauthTurista.getBeanCuentaTurista().getCorreoTurista();
			CuentaTurista beanCuentaTurista=beanCuentaTurista(correoCuentaTurista,pm);			
			if(beanCuentaTurista!=null && beanCuentaTurista.getCodeEstadoCuenta().equalsIgnoreCase(P.ELIMINADO)){				
				throw new UnknownException("Cuenta fue eliminada");
			}else{
				RedSocial beanRedSocial=beanOauthTurista.getBeanRedSocial();
				beanRedSocial.setIdRedSocial(KeyFactory.keyToString(KeyFactory.createKey(RedSocial.class.getSimpleName(),beanRedSocial.getCodeRedSocial())));
				String codeAutenticaOauth = StringHex.convertStringToHex(UUID.randomUUID().toString());
				beanOauthTurista.setCodeAutenticaOauth(codeAutenticaOauth);
				beanOauthTurista.setIdAutenticaOauth(KeyFactory.keyToString(KeyFactory.createKey(AutenticaOauth.class.getSimpleName(),codeAutenticaOauth)));
				java.util.Date fecha=new java.util.Date();
				beanOauthTurista.setFecha(fecha);
				beanOauthTurista.setVersion(fecha.getTime());
				beanOauthTurista.setOperacion(P.INSERTAR);
				beanOauthTurista.setIsPersistente(true);
				beanOauthTurista.setBeanRedSocial(beanRedSocial);
				beanOauthTurista.setCodeRedSocial(beanRedSocial.getCodeRedSocial());
				BeanParametro beanParametro= new BeanParametro();				
				if(beanCuentaTurista!=null){
					beanCuentaTurista.setTokenFirebase(beanOauthTurista.getTokenFirebase());
					beanCuentaTurista.getBeanUsuarioTurista().setTokenFirebase(beanOauthTurista.getTokenFirebase());
					beanOauthTurista.setBeanCuentaTurista(beanCuentaTurista);
					beanOauthTurista.setCodeCuentaTurista(beanCuentaTurista.getCorreoTurista());
					LogicAutenticaOauth logicAutenticaOauth=new LogicAutenticaOauth(pm);
					beanParametro.setBean(beanOauthTurista);
					beanParametro.setTipoOperacion(P.INSERTAR);
					AutenticaOauth beanAutenticaOauthBd=logicAutenticaOauth.mantenimiento(beanParametro);
					if(beanAutenticaOauthBd!=null){
						SesionTurista beanSesionTuristaBd=insertarSesionTurista(beanAutenticaOauthBd,pm);
						if(beanSesionTuristaBd!=null){							
								tx.commit();
								pm.close();
								beanSesionTuristaBd.setCuentaCreadaPor(beanCuentaTurista.getTipo());
								beanSesionTuristaBd.getBeanTurista().setBeanCuentaTurista(null);
								beanSesionTuristaBd.getBeanTurista().setListaAmigos(null);
								beanSesionTuristaBd.getBeanTurista().setListaColonias(null);
								beanSesionTuristaBd.getBeanTurista().setListTuristaIntereses(null);
								beanSesionTuristaBd.getBeanTurista().setListNoticias(null);
								beanSesionTuristaBd.getBeanAutenticaOauth().setBeanCuentaTurista(null);
								return beanSesionTuristaBd;													
						}else{
							throw new UnknownException("Error al crear sesion");
						}
					}else{
						throw new UnknownException("Error al guardar AutenticaOauth");
					}
				}else{
					CuentaTurista beanCuentaTuristaBd=insertarCuentaTurista(beanOauthTurista.getBeanCuentaTurista(),pm);
					if(beanCuentaTuristaBd!=null){
						beanOauthTurista.setBeanCuentaTurista(beanCuentaTuristaBd);
						LogicAutenticaOauth logicAutenticaOauth=new LogicAutenticaOauth(pm);
						beanParametro.setBean(beanOauthTurista);
						beanParametro.setTipoOperacion(P.INSERTAR);
						AutenticaOauth beanAutenticaOauthBd=logicAutenticaOauth.mantenimiento(beanParametro);
						if(beanAutenticaOauthBd!=null){
							SesionTurista beanSesionTuristaBd=insertarSesionTurista(beanAutenticaOauthBd,pm);
							if(beanSesionTuristaBd!=null){
								Boolean rptaConfiguracionCuenta=insertarConfiguracionCuenta(beanSesionTuristaBd.getBeanTurista(), beanParametro, pm);
								if(rptaConfiguracionCuenta){
									tx.commit();
									pm.close();
									beanSesionTuristaBd.setCuentaCreadaPor(beanCuentaTuristaBd.getTipo());
									beanSesionTuristaBd.getBeanTurista().getBeanCuentaTurista().setBeanUsuarioTurista(null);
									beanSesionTuristaBd.getBeanTurista().getBeanCuentaTurista().setClave(null);
									beanSesionTuristaBd.getBeanTurista().setListaAmigos(null);
									beanSesionTuristaBd.getBeanTurista().setListaColonias(null);
									beanSesionTuristaBd.getBeanTurista().setListTuristaIntereses(null);
									beanSesionTuristaBd.getBeanTurista().setListNoticias(null);
									beanSesionTuristaBd.getBeanAutenticaOauth().setBeanCuentaTurista(null);
									return beanSesionTuristaBd;
								}else{
									throw new UnknownException("Error en la configuracion");
								}								
							}else{
								throw new UnknownException("Error al crear sesion");
							}
						}else{
							throw new UnknownException("Error al guardar AutenticaOauth");
						}
					}else{
						throw new UnknownException("Error al crear cuenta");
					}
				}
			}
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, tx);
		}		
	}
	
	/**
	 * CODIGO: KCYS-81
	 * CUS: CUS_RECUPERAR_CLAVE_CUENTA_TURISTA - ANS_TURISTA
	 * ESTADO: TESTEADO
	 * */
	public static Boolean recuperarClaveCuentaTurista(String correoCuentaTurista)throws UnknownException{
		PersistenceManager pm = null;						
		try {			
			pm = PMF.getPMF().getPersistenceManager();	
			LogicCuentaTurista logicCuentaTurista= new LogicCuentaTurista(pm);
			CuentaTurista beanCuentaTurista= (CuentaTurista)logicCuentaTurista.getBeanByEmail(correoCuentaTurista);
			if(beanCuentaTurista!=null 
					&& !beanCuentaTurista.getCodeEstadoCuenta().equalsIgnoreCase(P.ELIMINADO)){	
				if(beanCuentaTurista.getTipo().equalsIgnoreCase(P.OAUTH)){
					throw new UnknownException("Usted se registro con una red social");
				}
			UsuarioTurista beanUsuarioTurista= beanCuentaTurista.getBeanUsuarioTurista();			
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);								
			String msgBody = "<div id=\"titulo\" style=\"background-color:#088A08; color:white; text-align: center; font-weight: bold; font-size:6mm; line-height:12mm\">INDIANA</div>";
			msgBody = msgBody+"<div style=\"padding:3mm\">Indiant es una Herramienta que promueve el Turismo.</div>";
			msgBody = msgBody+"<div style=\"padding:3mm\">Usuario: "+correoCuentaTurista+"</div>";
			msgBody = msgBody+"<div style=\"padding:3mm\">Clave: "+AESencrypt.decrypt(beanCuentaTurista.getClave())+"</div>";			
			String encrip=AESencrypt.encrypt(correoCuentaTurista);
			String hex=StringHex.convertStringToHex(encrip);
			msgBody = msgBody+"<div style=\"padding:3mm; height:9mm; \"><a style=\"width:100%; height:100%;\"  href='"+P.URL+"/validarCuenta.html?encoded="+hex+"'\">Volver a Indiana</a></div>";		
			String remitente=P.CORREOREMITENTE;
			Message msg = new MimeMessage(session);
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			msg.setFrom(new InternetAddress(remitente.trim().toString(), "Indiant"));
			String usuario="Sr(a). "+beanUsuarioTurista.getNombre()+" "+beanUsuarioTurista.getApellido();
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(correoCuentaTurista, usuario));
			msg.setSubject("Bienvenido a Indiant - Recuperacion de Clave");
			htmlPart.setContent(msgBody, "text/html");		
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);
			return true;
			}else{
				throw new UnknownException("Create una cuenta");
			}
		} catch (Exception ex) {			
				throw new UnknownException(ex.getMessage());		
		}finally{
			GestionShared.closeConnection(pm, null);	
		}	
	}
	
	/**
	 * CODIGO: KCYS-82
	 * CUS: CUS_VER_MURO_NOTICIAS_TURISTA - ANS_TURISTA
	 * ESTADO: TESTEADO
	 * */
	public static List<MuroNoticia> verMuroNoticiasTurista(Integer limiteInferior,Integer limiteSuperior,String correoUsuarioTurista) throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();				
			LogicMuroNoticia logicMuroNoticia= new LogicMuroNoticia(pm);			
			Set<MuroNoticia> listaMuroNoticia= new HashSet<MuroNoticia>();			
//			NOTDES: Cantidad de Noticias Desconocidas.
						
			String codeParametroSistema="NOTDES";
			LogicParametrosSistema logicParametrosSistema= new LogicParametrosSistema(pm);
			ParametrosSistema beanParametroSistema= (ParametrosSistema)logicParametrosSistema.getBean(codeParametroSistema);
			if(beanParametroSistema==null){
				throw new UnknownException("No existe Parametro ".concat(codeParametroSistema));
			}
			Integer cantidadNoticias= Integer.parseInt(beanParametroSistema.getValor());						
			List<MuroNoticia> listMuroNoticiasDesconocidas= logicMuroNoticia.getListarByNotCorreoTurista(correoUsuarioTurista,cantidadNoticias);
			
			listaMuroNoticia.addAll(listMuroNoticiasDesconocidas);	
                        listaMuroNoticia.addAll(logicMuroNoticia.getListarBean(limiteInferior, limiteSuperior, correoUsuarioTurista));											
			List<MuroNoticia> listaMuroNoticiaReturn= new ArrayList<>(listaMuroNoticia);
			Collections.sort(listaMuroNoticiaReturn,new Comparator<MuroNoticia>(){
				@Override
				public int compare(MuroNoticia beanMuroNoticia, MuroNoticia anotherMuroNoticia) {						
					return new Double(anotherMuroNoticia.getVersion()).compareTo(new Double(beanMuroNoticia.getVersion()));
				}					
			});	
			for(int i=0;i<listaMuroNoticiaReturn.size();i++){
				MuroNoticia beanMuro= listaMuroNoticiaReturn.get(i);
				Noticia beanNoticia=beanMuro.getBeanNoticia();
				Boolean haDivulgado=haDivulgado(correoUsuarioTurista, beanMuro.getCodeNoticia());
				beanMuro.setTotalDivulgado(beanNoticia.getTotalDivulgado());
				beanMuro.setTotalComentario(beanNoticia.getTotalComentario());
				beanMuro.setTotalCompartido(beanNoticia.getTotalCompartido());
				beanMuro.setHaDivulgado(haDivulgado);
				listaMuroNoticiaReturn.set(i, beanMuro);
			}			
			return listaMuroNoticiaReturn;						
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}		
	}	

	public static Boolean haDivulgado(String correoTurista,String codeNoticia) throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();				
			LogicDivulgacionNoticia logicDivulgacionNoticia= new LogicDivulgacionNoticia(pm);
			DivulgacionNoticia beanDivulgacion=logicDivulgacionNoticia.getBean(correoTurista, codeNoticia);
			if(beanDivulgacion==null){
				return false;
			}else{
				return true;
			}					
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}		
	}
	
	public static Boolean haCompartido(String correoTurista,String codeNoticia) throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();							
			LogicComparteNoticia logicComparteNoticia= new LogicComparteNoticia(pm);
			ComparteNoticia beanComparteNoticia= (ComparteNoticia)logicComparteNoticia.getBean(correoTurista, codeNoticia);
			if(beanComparteNoticia==null){
				return false;
			}else{
				return true;
			}			
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}		
	}
	
	public static List<NotificacionTurista> listNotificacionTuristaSolicitudesPendientes(String correoTurista) throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();							
			LogicNotificacionTurista logicNotificacionTurista= new LogicNotificacionTurista(pm);
			List<NotificacionTurista>listNotificacionTurista= logicNotificacionTurista.getListarBean(correoTurista, null);
			LogicSolicitudAmistad logicSolicitudAmistad= new LogicSolicitudAmistad(pm);
			Iterator<NotificacionTurista> iteratorNotificacionTurista=listNotificacionTurista.iterator();
			List<NotificacionTurista> listNotificacionSolicitudesPendientes= new ArrayList<NotificacionTurista>();
			while(iteratorNotificacionTurista.hasNext()){
				NotificacionTurista beanNotificacionTurista= iteratorNotificacionTurista.next();
				if(beanNotificacionTurista.getCodeSolicitudAmistad()!=null){
					SolicitudAmistad beanSolicitudAmistad= (SolicitudAmistad) logicSolicitudAmistad.getBeanByCode(beanNotificacionTurista.getCodeSolicitudAmistad());
					if(beanSolicitudAmistad!=null){
						if(beanSolicitudAmistad.getEstadoTarea().equalsIgnoreCase(P.PENDIENTE)){
							listNotificacionSolicitudesPendientes.add(beanNotificacionTurista);
						}
					}
				}
			}
			return listNotificacionSolicitudesPendientes;		
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}		
	}
	
	/**
	 * CODIGO: KCYS-83
	 * CUS: CUS_DIVULGAR_NOTICIA - ANS_TURISTA
	 * ESTADO: NO TESTEADO
	 * */
	public static Integer divulgarNoticia(String codeTuristaGeneraNotificacion,String codeNoticia)throws UnknownException{
            System.err.println("EMPIEZA A DIVULGAR LA NOTICIA");
		PersistenceManager pm = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicDivulgacionNoticia logicDivulgacionNoticia=new LogicDivulgacionNoticia(pm);
			DivulgacionNoticia beanDivulgacionNoticia=logicDivulgacionNoticia.getBean(codeTuristaGeneraNotificacion,codeNoticia);			
			if(beanDivulgacionNoticia!=null){
				throw new UnknownException("Usted ya divulgo esta noticia!");
			}else{
				LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
				UsuarioTurista beanUsuarioTuristaDivulga= pm.detachCopy(logicUsuarioTurista.getBean(codeTuristaGeneraNotificacion));
				if(beanUsuarioTuristaDivulga==null){
					throw new UnknownException("No existe turista");
				}
				LogicNoticia logicNoticia=new LogicNoticia(pm);
				Noticia beanNoticiaBd=logicNoticia.getBean(codeNoticia);
				if(beanNoticiaBd==null){
					throw new UnknownException("No existe noticia");
				}
				DivulgacionNoticia beanDivulgacionNoticiaBd=insertarDivulgacionNoticia(beanUsuarioTuristaDivulga,beanNoticiaBd,pm);				
				if(beanDivulgacionNoticiaBd!=null){							
					Notificacion beanNotificacion=new Notificacion();
					beanNotificacion.setCodeDivulgacionNoticia(beanDivulgacionNoticiaBd.getCodeDivulgacionNoticia());
					beanNotificacion.setBeanNoticia(beanNoticiaBd);
					beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTuristaDivulga);
					if(beanNoticiaBd.getCodeConquista()!=null && !beanNoticiaBd.getCodeConquista().isEmpty()){
						beanNotificacion.setCodeTipoNotificacion(P.DIVULGAR_CONQUISTA);
					}else if(beanNoticiaBd.getCodeNovedad()!=null && !beanNoticiaBd.getCodeNovedad().isEmpty()){
						beanNotificacion.setCodeTipoNotificacion(P.DIVULGAR_NOVEDAD);
					}
					LogicTipoNotificacion logicTipoNotificacion=new LogicTipoNotificacion(pm);
					List<TipoNotificacion> listaTipoNotificacion=(List<TipoNotificacion>)logicTipoNotificacion.getListarBean();
					if(listaTipoNotificacion!=null && listaTipoNotificacion.size()>0){
						beanNotificacion.setListaTipoNotificacion(listaTipoNotificacion);
						Notificacion beanNotificacionBd=insertarNotificacionDivulgacion(beanNotificacion,pm);
						if(beanNotificacionBd!=null){
                                                        System.err.println("EMPIEZA COLA PARA DIVULGAR LA NOTICIA");
							if(generarNotificacionDivulga(beanNotificacionBd,pm)){
							Queue queue = QueueFactory.getQueue("notificacion-queuepush");
							queue.add(TaskOptions.Builder
						            .withUrl("/taskNotificationDivulgacion")
						            .param("codeNotificacion", beanNotificacionBd.getCodeNotificacion())
                                                                .header("Host", P.HOST));
						            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           								
								pm.close();								
								return 1;
							}else{
								throw new UnknownException("No se pudo divulgar");
							}
						}else{
							throw new UnknownException("No se pudo guardar notificacion");
						}
					}else{
						throw new UnknownException("No hay tipos de notificacion");
					}
				}else{
					throw new UnknownException("No se pudo divulgar");
				}
			}			
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}		
	
	/**
	 * METHOD: COMENTAR NOTICIA 
	 * CODIGO: KCYS-84
	 * CUS: CUS_COMENTAR_NOTICIA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param correoTurista
	 * @param codeNoticia
	 * @param comentario
	 * @return
	 * @throws UnknownException
	 * 
	 */
	public static ComentarioNoticia comentarNoticia(String correoTurista,String codeNoticia,String comentario,Date fechaComentario) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNoticia logicNoticia= new LogicNoticia(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicComentarioNoticia logicComentarioNoticia= new LogicComentarioNoticia(pm);			
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Noticia beanNoticia = (Noticia)logicNoticia.getBeanByCode(codeNoticia);
			if(beanNoticia==null){
				throw new UnknownException(P.errorNoExistencia(Noticia.class));
			}
			if(beanNoticia.getBeanNovedad()!=null){
				if(!GestionColony.existeMiembroEnColonia(beanNoticia.getCodeColonia(), correoTurista, pm)){				
					throw new UnknownException(P.NO_ES_MIEMBRO);	
				}
			}
			ComentarioNoticia beanComentarioNoticia= new ComentarioNoticia();
			String codeComentarioNoticia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanComentarioNoticia.setIdComentarioNoticia(KeyFactory.keyToString(KeyFactory.createKey
					(ComentarioNoticia.class.getSimpleName(), codeComentarioNoticia)));	
			beanComentarioNoticia.setCodeComentarioNoticia(codeComentarioNoticia);
			beanComentarioNoticia.setBeanTuristaComenta(beanUsuarioTurista);
			beanComentarioNoticia.setNombreTuristaComenta(beanUsuarioTurista.getNombre());
			beanComentarioNoticia.setApellidoTuristaComenta(beanUsuarioTurista.getApellido());
			beanComentarioNoticia.setFotoPerfilTuristaComenta(beanUsuarioTurista.getFotoPerfil());
			beanComentarioNoticia.setCodeTuristaComenta(beanUsuarioTurista.getCorreoTurista());
			beanComentarioNoticia.setBeanNoticia(beanNoticia);
			beanComentarioNoticia.setCodeNoticia(beanNoticia.getCodeNoticia());	
			String codeTipoNotificacion=null;
			if(beanNoticia.getBeanConquista()!=null){
				codeTipoNotificacion=P.COMENTARIO_CONQUISTA;
				beanComentarioNoticia.getBeanNoticia().setBeanConquista(beanNoticia.getBeanConquista());
				beanComentarioNoticia.getBeanNoticia().setCodeConquista(beanNoticia.getBeanConquista().getCodeConquista());
				beanComentarioNoticia.getBeanNoticia().setCodeDestinoConquistado(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
				beanComentarioNoticia.getBeanNoticia().setBeanDestinoConquistado(beanNoticia.getBeanDestinoConquistado());
			}
			if(beanNoticia.getBeanNovedad()!=null){
				codeTipoNotificacion=P.COMENTARIO_NOVEDAD;
				beanComentarioNoticia.getBeanNoticia().setBeanNovedad(beanNoticia.getBeanNovedad());
				beanComentarioNoticia.getBeanNoticia().setCodeNovedad(beanNoticia.getCodeNovedad());
				beanComentarioNoticia.getBeanNoticia().setBeanColonia(beanNoticia.getBeanColonia());
				beanComentarioNoticia.getBeanNoticia().setCodeColonia(beanNoticia.getCodeColonia());				
			}								
			beanComentarioNoticia.setComentario(comentario);			
			beanComentarioNoticia.setFechaComentario(fechaComentario);
			beanComentarioNoticia.setFechaPublicacion(new java.util.Date());
			beanComentarioNoticia.setVersion(new java.util.Date().getTime());
			beanComentarioNoticia.setIsPersistente(true);
			BeanParametro beanParametro = new BeanParametro();			
			beanParametro.setBean(beanComentarioNoticia);
			beanParametro.setTipoOperacion(P.INSERTAR);			
			Boolean rptaComentarioNoticia= logicComentarioNoticia.mantenimiento(beanParametro);
			if(rptaComentarioNoticia){				
				beanNoticia.setTotalComentario(beanNoticia.getTotalComentario()+1);
				beanParametro= new BeanParametro();					
				beanParametro.setBean(beanNoticia);
				beanParametro.setTipoOperacion(P.ACTUALIZAR);				
				Boolean rptaNoticia = new LogicNoticia(pm).mantenimiento(beanParametro);
				if(rptaNoticia){					
					Notificacion beanNotificacionBd=generarNotificacionComentario(beanComentarioNoticia, codeTipoNotificacion,beanParametro, pm);
					Queue queue = QueueFactory.getQueue("notificacion-queuepush");
					queue.add(TaskOptions.Builder
				            .withUrl("/taskNotificacionComentario")
				            .param("codeNotificacion", beanNotificacionBd.getCodeNotificacion())				            
                                                .header("Host", P.HOST));
				            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           									           															
						beanNotificacionBd.setEstadoTarea(P.EJECUTADO);
						beanParametro = new BeanParametro();
						beanParametro.setBean(beanNotificacionBd);					
						//tx.commit();
						pm.close();		
						return beanComentarioNoticia;
				}
			}
			return null;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * METHOD: COMPARTIR NOTICIA
	 * CODIGO: KCYS-85
	 * CUS: CUS_COMPARTIR_NOTICIA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeNoticiaOriginal
	 * @param codeColonia
	 * @param correoTurista
	 * @param descripcion
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean compartirNoticia(String codeNoticiaOriginal,
			String codeColonia,String correoTurista,String descripcion) throws UnknownException{
		PersistenceManager pm = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNoticia logicNoticia= new LogicNoticia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicNovedad logicNovedad= new LogicNovedad(pm);
			LogicNoticiaCompartida logicNoticiaCompartida= new LogicNoticiaCompartida(pm);
			
			Noticia beanNoticiaCompartida= logicNoticia.getBeanByCode(codeNoticiaOriginal);
			if(beanNoticiaCompartida== null){
				throw new UnknownException("No es existe la Noticia Compartida");
			}			
			Noticia beanNoticiaOriginal=null;
			if(beanNoticiaCompartida.getCodeNoticiaOriginal()!=null){
				beanNoticiaOriginal=logicNoticia.getBeanByCode(beanNoticiaCompartida.getCodeNoticiaOriginal());//Noticia original..
				if(beanNoticiaOriginal== null){
					throw new UnknownException("No es existe la Noticia Original");
				}
			}
			if(beanNoticiaCompartida.getBeanNovedad()!=null){
				if(!GestionColony.existeMiembroEnColonia(beanNoticiaCompartida.getCodeColonia(), correoTurista, pm)){				
					throw new UnknownException(P.NO_ES_MIEMBRO);	
				}
			}
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista== null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Colonia beanColonia= (Colonia)logicColonia.getBeanByCode(codeColonia);
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Colonia.class));
			}
			Miembro beanMiembro=logicMiembro.verPerfilMiembro(correoTurista, codeColonia);
			if(beanMiembro==null){
				throw new UnknownException(P.errorNoExistencia(Miembro.class));
			}
			
			NoticiaCompartida beanNoticiaComparte= new NoticiaCompartida();
			String codeNoticiaComparte=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanNoticiaComparte.setIdNoticiaCompartida(KeyFactory.keyToString(KeyFactory.createKey
					(NoticiaCompartida.class.getSimpleName(), codeNoticiaComparte)));
			beanNoticiaComparte.setCodeNoticiaCompartida(codeNoticiaComparte);
			beanNoticiaComparte.setBeanColonia(beanColonia);
			beanNoticiaComparte.setCodeColonia(beanColonia.getCodeColonia());
			beanNoticiaComparte.setNombreColonia(beanColonia.getNombreColonia());
			beanNoticiaComparte.setCodeMiembro(beanMiembro.getCodeMiembro());
			beanNoticiaComparte.setNombreTuristaComparte(beanUsuarioTurista.getNombre());
			beanNoticiaComparte.setApellidoTuristaComparte(beanUsuarioTurista.getApellido());
			beanNoticiaComparte.setCodeTurista(beanUsuarioTurista.getCorreoTurista());
			beanNoticiaComparte.setFotoPerfilTuristaComparte(beanUsuarioTurista.getFotoPerfil());
			beanNoticiaComparte.setVersion(new java.util.Date().getTime());
			beanNoticiaComparte.setIsPersistente(true);
			if(beanNoticiaOriginal!=null){
				beanNoticiaComparte.setCodeNoticia(beanNoticiaOriginal.getCodeNoticia());
			}else{
				beanNoticiaComparte.setCodeNoticia(codeNoticiaOriginal);
			}
			
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanNoticiaComparte);
			beanParametro.setTipoOperacion(P.INSERTAR);
			Boolean rptaNoticiaComparte= logicNoticiaCompartida.mantenimiento(beanParametro);
			if(!rptaNoticiaComparte){
				throw new UnknownException("No se guardo la Noticia Compartida");
			}			
			
			String fotoPublicidad=beanNoticiaCompartida.getFotoConquistaPublicidad();
			String hiperLink=beanNoticiaCompartida.getHiperLink();
			
			Novedad beanNovedad = new Novedad();
			String codeNovedad= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanNovedad.setIdNovedad(KeyFactory.keyToString(KeyFactory.createKey(Novedad.class.getSimpleName(), codeNovedad)));	
			beanNovedad.setCodeNovedad(codeNovedad);
			beanNovedad.setBeanMiembroGeneraNovedad(beanMiembro);
			beanNovedad.setCodeMiembroGeneraNovedad(beanMiembro.getCodeMiembro());
			beanNovedad.setBeanTuristaGeneraNovedad(beanUsuarioTurista);
			beanNovedad.setCodeTuristaGeneraNovedad(beanUsuarioTurista.getCodeUsuarioTurista());
			beanNovedad.setApellidoTuristaGeneraNovedad(beanUsuarioTurista.getApellido());
			beanNovedad.setNombreTuristaNegocioGeneraNovedad(beanUsuarioTurista.getNombre());
			beanNovedad.setFotoPerfilTuristaNegocioGeneraNovedad(beanUsuarioTurista.getFotoPerfil());
			beanNovedad.setFotoPublicidad(fotoPublicidad);
			beanNovedad.setBeanColonia(beanColonia);
			beanNovedad.setCodeColonia(beanColonia.getCodeColonia());			
			beanNovedad.setIsPersistente(true);
			beanNovedad.setVersion(new java.util.Date().getTime());
			beanNovedad.setDescripcion(descripcion);
			beanNovedad.setHiperLink(hiperLink);						
			beanNovedad.setFechaPublicacion(new java.util.Date());
			beanNovedad.setEnlace(beanColonia.getNombreColonia());
			if(beanNoticiaOriginal!=null){
				beanNovedad.setBeanNoticiaOriginal(beanNoticiaOriginal);//*
				beanNovedad.setCodeNoticiaOriginal(beanNoticiaOriginal.getCodeNoticia());
			}else{
				beanNovedad.setBeanNoticiaOriginal(beanNoticiaCompartida);//*
				beanNovedad.setCodeNoticiaOriginal(beanNoticiaCompartida.getCodeNoticia());
			}
			beanNovedad.setBeanNoticiaCompartida(beanNoticiaComparte);
			beanNovedad.setCodeNoticiaCompatida(beanNoticiaCompartida.getCodeNoticia());
			
			beanNovedad.setTipoNovedad("NC");				
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanNovedad);
			beanParametro.setTipoOperacion(P.INSERTAR);
			
			Boolean rptaNovedad= logicNovedad.mantenimiento(beanParametro);
			if(!rptaNovedad){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			MuroNoticia beanMuroNoticia= generaNoticiaCompartida(beanNovedad, beanParametro, pm);
			if(beanMuroNoticia==null){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
			queueMuroNoticia.add(TaskOptions.Builder
		            .withUrl("/taskMuroNoticiaCompartirNoticia")
		            .param("codeNoticia", beanMuroNoticia.getCodeNoticia())				            
                                .header("Host", P.HOST));							
		            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           
			
			Notificacion beanNotificacion = generarNotificacionNoticiaCompartida(beanMuroNoticia, beanParametro, pm);			
			if(beanNotificacion==null){
				throw new UnknownException(P.errorNoExistencia(Notificacion.class));
			}
			Queue queue = QueueFactory.getQueue("notificacion-queuepush");
			queue.add(TaskOptions.Builder
		            .withUrl("/taskNotificacionCompartirNoticia")
		            .param("codeNotificacion", beanNotificacion.getCodeNotificacion())				            
                                .header("Host", P.HOST));	
		            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           
			
			if(beanNoticiaOriginal!=null){
				beanNoticiaOriginal.setTotalCompartido(beanNoticiaOriginal.getTotalCompartido()+1);
				beanParametro.setBean(beanNoticiaOriginal);
			}else{
				beanNoticiaCompartida.setTotalCompartido(beanNoticiaCompartida.getTotalCompartido()+1);
				beanParametro.setBean(beanNoticiaCompartida);
			}			
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaNoticia= logicNoticia.mantenimiento(beanParametro);
			if(!rptaNoticia){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			pm.close();		
			return true;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
/**
 * 
 * @param correoTurista
 * @param visto
 * @return
 * @throws UnknownException
 */
	public static  List<NotificacionTurista> verNotificacionTurista(String correoTurista,String visto) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();			
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);					
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			LogicNotificacionTurista logicNotificacionTurista = new LogicNotificacionTurista(pm);
			return logicNotificacionTurista.getListarBean(correoTurista, visto);
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	/**
	 * 
	 * @param correoTurista
	 * @param visto
	 * @return
	 * @throws UnknownException
	 */
	public static  Boolean definirEstadoNotificacionTurista(String codeNotificacionTurista) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNotificacionTurista logicNotificacionTurista = new LogicNotificacionTurista(pm);
			NotificacionTurista beanNotificacionTurista= logicNotificacionTurista.getBeanByCode(codeNotificacionTurista);
			if(beanNotificacionTurista==null){
				throw new UnknownException(P.errorNoExistencia(Notificacion.class));
			}
			beanNotificacionTurista.setVisto(P.VISTO);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanNotificacionTurista);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaNotificacionTurista=logicNotificacionTurista.mantenimiento(beanParametro);
			if(!rptaNotificacionTurista){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			return true;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * Cantidad de Notificacion Pendientes de un Turista..
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static  Integer cantidadNotificacionesPendientes(String correoTurista) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();			
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);					
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			LogicNotificacionTurista logicNotificacionTurista = new LogicNotificacionTurista(pm);
			return logicNotificacionTurista.getCount(correoTurista);
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * CODIGO: KCYS-90
	 * CUS: CUS_CONFIGURACION_PRIVACIDAD_TURISTA - ANS_TURISTA
	 * ESTADO: NO TESTEADO
	 * */
	public static ConfigCuenta getConfiguracionCuenta(String correoTurista)throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicConfigCuenta logicConfigCuenta=new LogicConfigCuenta(pm);																						
			ConfigCuenta beanConfigCuenta = logicConfigCuenta.getBeanByCodeTurista(correoTurista);
			if(beanConfigCuenta==null){
				throw new UnknownException("No se encontro configuracion de cuenta");
			}
			return beanConfigCuenta;						
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * CODIGO: KCYS-90 , KCYS-91
	 * CUS: CUS_CONFIGURACION_PRIVACIDAD_TURISTA - ANS_TURISTA , CUS_CONFIGURAR_IDIOMA - ANS_TURISTA
	 * ESTADO: TESTEADO 
	 * 
	 * @param correoTurista
	 * @param codePrivacidadVerNovedad
	 * @param codePrivacidadInvitarAmigos
	 * @param codePrivacidadBuscarMiPerfil
	 * @param codeIdioma
	 * @return
	 * @throws UnknownException
	 */
	public static ConfigCuenta actualizarConfiguracionCuenta(String correoTurista,
			String codePrivacidadVerNovedad,String codePrivacidadInvitarAmigos,
			String codePrivacidadBuscarMiPerfil,String codeIdioma) throws UnknownException{
		PersistenceManager pm = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();		
			LogicConfigCuenta logicConfCuenta= new LogicConfigCuenta(pm);
			LogicPrivacidad logicPrivacidad= new LogicPrivacidad(pm);
			LogicIdioma logicIdioma= new LogicIdioma(pm);
			
			ConfigCuenta beanConfigCuenta= logicConfCuenta.getBeanByCodeTurista(correoTurista);
			if(beanConfigCuenta==null){
				throw new UnknownException("No se encontro la Configuracion del Turista");
			}
			if(!beanConfigCuenta.getCodePrivacidadNovedad().equals(codePrivacidadVerNovedad)){
				Privacidad beanPrivacidadNovedad= logicPrivacidad.getBeanByCode(codePrivacidadVerNovedad);
				if(beanPrivacidadNovedad==null){
					throw new UnknownException("No se encontro la Privacidad de Novedad");								
				}
				beanConfigCuenta.setBeanPrivacidadNovedad(beanPrivacidadNovedad);
				beanConfigCuenta.setCodePrivacidadNovedad(codePrivacidadVerNovedad);
			}
			
			if(!beanConfigCuenta.getCodePrivacidadInvitacion().equals(codePrivacidadInvitarAmigos)){
				Privacidad beanPrivacidadInvitacion= logicPrivacidad.getBeanByCode(codePrivacidadInvitarAmigos);
				if(beanPrivacidadInvitacion==null){								
					throw new UnknownException("No se encontro la Privacidad de Invitacion");	
				}
				beanConfigCuenta.setBeanPrivacidadInvitacion(beanPrivacidadInvitacion);
				beanConfigCuenta.setCodePrivacidadInvitacion(codePrivacidadInvitarAmigos);
			}

			if(!beanConfigCuenta.getCodePrivacidadPerfil().equals(codePrivacidadBuscarMiPerfil)){
				Privacidad beanPrivacidadPerfil= logicPrivacidad.getBeanByCode(codePrivacidadBuscarMiPerfil);
				if(beanPrivacidadPerfil==null){								
					throw new UnknownException("No se encontro la Privacidad de Perfil");	
				}
				beanConfigCuenta.setBeanPrivacidadPerfil(beanPrivacidadPerfil);
				beanConfigCuenta.setCodePrivacidadPerfil(codePrivacidadBuscarMiPerfil);
			}
			if(!beanConfigCuenta.getCodeIdioma().equals(codeIdioma)){
				Idioma beanIdioma= logicIdioma.getBeanByCode(codeIdioma);
				if(beanIdioma==null){								
					throw new UnknownException("No se encontro la Configuracion de Idioma");	
				}
				beanConfigCuenta.setBeanIdioma(beanIdioma);
				beanConfigCuenta.setCodeIdioma(codeIdioma);
			}
			
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanConfigCuenta);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaConfigCuenta= logicConfCuenta.mantenimiento(beanParametro);
			if(!rptaConfigCuenta){
				throw new UnknownException("No se Proceso la Operacion");	
			}
			return beanConfigCuenta;			
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	
	/**
	 * METHOD: VER PERFIL TURISTA 
	 * CODIGO: KCYS-93
	 * CUS: CUS_MOSTRAR_PERFIL_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static UsuarioTurista verPerfilTurista(String correoTurista) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			GestionShared.closeConnection(pm, null);
			beanUsuarioTurista.setTokenFirebase(null);
			return beanUsuarioTurista;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * METHOD: MOSTRAR GALERIA TURISTA 
	 * CODIGO: KCYS-94
	 * CUS: CUS_MOSTRAR_GALERIA_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param limiteInferior
	 * @param limiteSuperior
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static UsuarioTurista mostrarGaleriaTurista(Integer limiteInferior,Integer limiteSuperior,String correoTurista) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicNoticia logicNoticia= new LogicNoticia(pm);
			UsuarioTurista beanUsuarioTurista=(UsuarioTurista) (logicUsuarioTurista.getBeanByEmail(correoTurista));
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}			
			Set<Noticia>setNoticias= new HashSet<Noticia>();
			setNoticias.addAll(logicNoticia.getListarBeanByTurista(limiteInferior, limiteSuperior, correoTurista,"CD"));	
			setNoticias.addAll((logicNoticia.getListarBeanByTurista(limiteInferior, limiteSuperior, correoTurista,"CN")));		
			pm.close();			
			beanUsuarioTurista.setListNoticias(setNoticias);
			return beanUsuarioTurista;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * METHOD: VER CONQUISTAS
	 * CODIGO: KCYS-96
	 * CUS: CUS_VER_CONQUISTA_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param limiteInferior
	 * @param limiteSuperior
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static UsuarioTurista verConquistas(Integer limiteInferior,Integer limiteSuperior,String correoTurista) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicNoticia logicNoticia= new LogicNoticia(pm);
			UsuarioTurista beanUsuarioTurista=(UsuarioTurista) (logicUsuarioTurista.getBeanByEmail(correoTurista));
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}			
			Set<Noticia>setNoticias= new HashSet<Noticia>();
			setNoticias.addAll(logicNoticia.getListarBeanByTuristaCompleto(limiteInferior, limiteSuperior, correoTurista,"CD"));	
			setNoticias.addAll((logicNoticia.getListarBeanByTuristaCompleto(limiteInferior, limiteSuperior, correoTurista,"CN")));		
			pm.close();			
			beanUsuarioTurista.setListNoticias(setNoticias);
			return beanUsuarioTurista;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}	
	
	/**
	 * METHOD: MOSTRAR NOVEDAD TURISTA
	 * CODIGO: KCYS-97
	 * CUS: CUS_MOSTRAR_NOVEDAD_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param limiteInferior
	 * @param limiteSuperior
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static UsuarioTurista mostrarNovedadTurista(Integer limiteInferior,Integer limiteSuperior,String correoTurista) throws UnknownException{
		return verConquistas(limiteInferior, limiteSuperior, correoTurista);
	}

	
	/**
	 * METHOD: BUSCAR PERSONAS
	 * CODIGO: KCYS-102
	 * CUS: CUS_VER_CONQUISTA_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param patron
	 * @param nombrePais
	 * @param nombreRegion
	 * @param nombreLocalidad
	 * @return
	 * @throws UnknownException
	 */
	public static List<UsuarioTurista> buscarPersonas(String patron,String nombrePais,String nombreRegion,
			String nombreLocalidad) throws UnknownException{
		PersistenceManager pm = null;		
		try {
			pm =PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);						
			List<UsuarioTurista> listUsuarioTurista= logicUsuarioTurista.getListarBeanByConfiguracion(nombrePais, nombreRegion, nombreLocalidad);						
			patron=".*".concat(patron.trim().toUpperCase()).concat(".*");
			List<UsuarioTurista> listaFiltro= new ArrayList<UsuarioTurista>();
			for (int i = 0; i < listUsuarioTurista.size(); i++) {
			    if(listUsuarioTurista.get(i).getNombre().toUpperCase().matches(patron) || listUsuarioTurista.get(i).getApellido().toUpperCase().matches(patron)){
			    	listaFiltro.add(listUsuarioTurista.get(i));continue;
			    }		       
			}
			if(listaFiltro.isEmpty()){
				throw new UnknownException("No se encontraron Resultados, modifique patron de busqueda");
			}
			return listaFiltro;			
		} catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}	
	}	
	
	/**
	 * METHOD: EDITAR AMIGOS TURISTA
	 * CODIGO: KCYS-106
	 * CUS: CUS_EDITAR_AMIGOS_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeTuristaPrincipal
	 * @param codeTuristaAmigo
	 * @param codeEstadoAmistad
	 * @return
	 * @throws UnknownException
	 */
	@SuppressWarnings("unchecked")
	public static EstadoAmistad editarAmigosTurista(
			String codeTuristaPrincipal,String codeTuristaAmigo,String codeEstadoAmistad) throws UnknownException{
		PersistenceManager pm = null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();
			pm =PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);						
			LogicAmistad logicAmistad= new LogicAmistad(pm);
			LogicEstadoAmistad logicEstadoAmistad=  new LogicEstadoAmistad(pm);			
			UsuarioTurista beanTuristaAmigo= (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(codeTuristaAmigo);
			if(beanTuristaAmigo==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}			
			List<Amistad> listAmistad=(List<Amistad>)logicAmistad.verAmistad(codeTuristaPrincipal, codeTuristaAmigo, P.ELIMINADO);
			if(listAmistad==null){
				throw new UnknownException("No existe lazos de Amistad con este Turista.");
			}
			Amistad beanAmistad= listAmistad.get(0);
			EstadoAmistad beanEstadoAmistad= (EstadoAmistad)logicEstadoAmistad.getBeanByCode(codeEstadoAmistad);						
			if(codeEstadoAmistad.equalsIgnoreCase(P.ACTIVO)){
				if(!beanAmistad.getBeanEstadoAmistad().getCodeEstadoAmistad().equalsIgnoreCase(P.BLOQUEADO)){
					throw new UnknownException("Se puede Activar si el Estado esta Bloqueado.");
				}
			}else if(codeEstadoAmistad.equalsIgnoreCase(P.BLOQUEADO)){
				if(!beanAmistad.getBeanEstadoAmistad().getCodeEstadoAmistad().equalsIgnoreCase(P.ACTIVO)){
					throw new UnknownException("Se puede Bloquear si el Estado esta Activo.");										
				}
			}else if(codeEstadoAmistad.equalsIgnoreCase(P.ELIMINADO)){
				if(beanAmistad.getBeanEstadoAmistad().getCodeEstadoAmistad().equalsIgnoreCase(P.ELIMINADO)){
					throw new UnknownException("Se puede Eliminar si el Estado se encuentra Activo o Bloqueado.");
				}
				List<Amistad> listAmistadAmix=(List<Amistad>)logicAmistad.verAmistad(codeTuristaAmigo,codeTuristaPrincipal, P.ELIMINADO);
				if(listAmistadAmix==null){
					throw new UnknownException("No existe lazos de Amistad con este Turista.");
				}
				Amistad beanAmistadAmix= listAmistadAmix.get(0);
				listAmistad.add(beanAmistadAmix);
			}else{
				throw new UnknownException("Estado no Valido.");
			}
			List<BeanParametro> listBeanParametro= new ArrayList<BeanParametro>();
			Iterator<Amistad> listAmistadIterator= listAmistad.iterator();
			while(listAmistadIterator.hasNext()){
				Amistad beanAmistadIterator= listAmistadIterator.next();
				beanAmistadIterator.setBeanEstadoAmistad(beanEstadoAmistad);
				beanAmistadIterator.setCodeEstadoAmistad(codeEstadoAmistad);							
			}
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(listAmistad);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			listBeanParametro.add(beanParametro);
			Boolean rptaAmistad= logicAmistad.mantenimiento(listBeanParametro);
			if(!rptaAmistad){
				throw new UnknownException("No se proceso la Operacion en la Entidad Amistad");	
			}
			pm.close();
			return beanEstadoAmistad;			
		} catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}	
	}
	
	/**
	 * METHOD: EDITAR INFORMACION TURISTA
	 * CODIGO: KCYS-109
	 * CUS: CUS_EDITAR_INFORMACION_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeTurista
	 * @param codePais
	 * @param codeRegion
	 * @param ciudad
	 * @param fechaNacimiento
	 * @param sexo
	 * @return
	 * @throws UnknownException
	 */

	public static UsuarioTurista editarInformacionTurista(
			String codeTurista,String codePais,String codeRegion,String ciudad,
			Date fechaNacimiento,String sexo) throws UnknownException{
		PersistenceManager pm = null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();				
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicPais logicPais= new LogicPais(pm);
			LogicRegion logicRegion= new LogicRegion(pm);
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(codeTurista);			
			if(beanUsuarioTurista==null){
				throw new UnknownException("No se encontro al Turista");
			}
			beanUsuarioTurista.setBeanPaisNacimiento(beanUsuarioTurista.getBeanPaisNacimiento());
			beanUsuarioTurista.setBeanRegionNacimiento(beanUsuarioTurista.getBeanRegionNacimiento());
			String codePaisOriginal= beanUsuarioTurista.getCodePaisNacimiento();
			String codeRegionOriginal= beanUsuarioTurista.getCodeRegionNacimiento();	
			String nombreLocalidadOriginal= beanUsuarioTurista.getNombreLocalidadNacimiento();
			
			if(!codePaisOriginal.equals(codePais)){
				Pais beanPais= logicPais.getBeanByCode(codePais);
				if(beanPais==null){
					throw new UnknownException("No se encontro el Pais");
				}
				beanUsuarioTurista.setBeanPaisNacimiento(beanPais);
				beanUsuarioTurista.setCodePaisNacimiento(codePais);
				beanUsuarioTurista.setNombrePaisNacimiento(beanPais.getNombre());				
			}
			if(!codeRegion.isEmpty()){
				if(!codeRegionOriginal.equals(codeRegion)){
					Region beanRegion= logicRegion.getBeanByCode(codeRegion);
					if(beanRegion==null){
						throw new UnknownException("No se encontro la Region");
					}
					beanUsuarioTurista.setBeanRegionNacimiento(beanRegion);
					beanUsuarioTurista.setCodeRegionNacimiento(codeRegion);
					beanUsuarioTurista.setNombreRegionNacimiento(beanRegion.getNombre());
				}
				if(!ciudad.isEmpty()){
					if(beanUsuarioTurista.getBeanLocalidadNacimiento()==null){
						Localidad beanLocalidadSet= new Localidad();
						beanLocalidadSet.setNombre(ciudad);	
						beanLocalidadSet.setBeanPais(beanUsuarioTurista.getBeanPaisNacimiento());
						beanLocalidadSet.setNombrePais(beanUsuarioTurista.getNombrePaisNacimiento());
						beanLocalidadSet.setBeanRegion(beanUsuarioTurista.getBeanRegionNacimiento());
						beanLocalidadSet.setNombreRegion(beanUsuarioTurista.getNombreRegionNacimiento());
						Localidad beanLocalidad=GestionShared.insertarLocalidad(beanLocalidadSet, pm);	
						if(beanLocalidad==null){
							throw new UnknownException("No se encontro la Localidad");
						}
						beanUsuarioTurista.setBeanLocalidadNacimiento(beanLocalidad);
						beanUsuarioTurista.setCodeLocalidadNacimiento(beanLocalidad.getCodeLocalidad());
						beanUsuarioTurista.setNombreLocalidadNacimiento(ciudad);					
					}else{
						if(!nombreLocalidadOriginal.equalsIgnoreCase(ciudad)){
							Localidad beanLocalidadSet= new Localidad();
							beanLocalidadSet.setNombre(ciudad);	
							beanLocalidadSet.setBeanPais(beanUsuarioTurista.getBeanPaisNacimiento());
							beanLocalidadSet.setNombrePais(beanUsuarioTurista.getNombrePaisNacimiento());
							beanLocalidadSet.setBeanRegion(beanUsuarioTurista.getBeanRegionNacimiento());
							beanLocalidadSet.setNombreRegion(beanUsuarioTurista.getNombreRegionNacimiento());
							Localidad beanLocalidad=GestionShared.insertarLocalidad(beanLocalidadSet, pm);	
							if(beanLocalidad==null){
								throw new UnknownException("No se encontro la Localidad");
							}
							beanUsuarioTurista.setBeanLocalidadNacimiento(beanLocalidad);
							beanUsuarioTurista.setCodeLocalidadNacimiento(beanLocalidad.getCodeLocalidad());
							beanUsuarioTurista.setNombreLocalidadNacimiento(ciudad);	
						}
					}
				}
			}								
			beanUsuarioTurista.setFechaNacimiento(fechaNacimiento);
			beanUsuarioTurista.setGenero(sexo);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanUsuarioTurista);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaUsuarioTurista= logicUsuarioTurista.mantenimiento(beanParametro);
			if(!rptaUsuarioTurista){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			return beanUsuarioTurista;							
		} catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}	
	}

	/**
	 * METHOD: EDITAR INTERESES TURISTA
	 * CODIGO: KCYS-110
	 * CUS: CUS_EDITAR_INTERESES_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeTuristaInteres
	 * @param estadoVisibilidad
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean editarInteresesTurista(
			String codeTuristaInteres,String estadoVisibilidad) throws UnknownException{
		PersistenceManager pm = null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();								
			LogicTuristaInteres logicTuristaInteres= new LogicTuristaInteres(pm);
			TuristaInteres beanTuristaInteres = (TuristaInteres) logicTuristaInteres.getBeanByCode(codeTuristaInteres);							
			if(beanTuristaInteres==null){
				throw new UnknownException("No se encontro el Interes del Turista");
			}			
			beanTuristaInteres.setEstadoVisibilidad(estadoVisibilidad);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanTuristaInteres);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaTuristaInteres= logicTuristaInteres.mantenimiento(beanParametro);
			if(!rptaTuristaInteres){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			return true;							
		} catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}	
	}	

	/**
	 * METHOD: EDITAR COLONIA TURISTA
	 * CODIGO: KCYS-111
	 * CUS: CUS_EDITAR_COLONIAS_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeColonia
	 * @param estadoVisibilidad
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean editarColoniasTurista(
			String codeTurista,String codeColonia,String estadoVisibilidad) throws UnknownException{
		PersistenceManager pm = null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();		
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			Colonia beanColonia= logicColonia.getBeanByCode(codeColonia);												
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Colonia.class));
			}	
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(codeTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Miembro beanMiembro= logicMiembro.verPerfilMiembro(codeTurista, codeColonia);
			if(beanMiembro==null){
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}
			beanMiembro.setEstadoVisibilidad(estadoVisibilidad);								
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanMiembro);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaMiembro= logicMiembro.mantenimiento(beanParametro);
			if(!rptaMiembro){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			return true;							
		} catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}	
	}
	
	
	/**
	 * METHOD: VER MIS NOTICIAS
	 * CODIGO: KCYS-
	 * CUS: 
	 * ESTADO : TESTEADO
	 * 
	 * @param limiteInferior
	 * @param limiteSuperior
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static List<Noticia> verMisNoticias(Integer limiteInferior,Integer limiteSuperior,String correoTurista) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicNoticia logicNoticia= new LogicNoticia(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.ERROR_OPERACION);
			}			
			List<Noticia> listNoticias=(List<Noticia>)(logicNoticia.getListarBeanByTurista(limiteInferior, limiteSuperior, correoTurista));
			pm.close();	
			return listNoticias;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}	
	
	/**
	 * METHOD: MOSTRAR INFORMACION
	 * CODIGO: KCYS-98
	 * CUS: CUS_MOSTRAR_INFORMACION_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param correoTurista
	 * @param codeNoticia
	 * @param comentario
	 * @return
	 * @throws UnknownException
	 * 
	 */
	public static UsuarioTurista mostrarInformacionTurista(String correoTurista) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista)logicUsuarioTurista.getBeanByEmailAtom(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			LogicAmistad logicAmistad= new LogicAmistad(pm);			
			Set<Amistad> listAmistad=logicAmistad.getListarAmigos(correoTurista);	
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			Set<Colonia> listColoniaSet=new HashSet<Colonia>();
			listColoniaSet.addAll(logicColonia.getListarBeanByTuristaCreador(correoTurista));
			List<Miembro> listMiembros= logicMiembro.getListarBean(null, correoTurista, "ALL");
			Iterator<Miembro> listMiembrosIterator= listMiembros.iterator();
			while(listMiembrosIterator.hasNext()){
				Colonia beanColonia= listMiembrosIterator.next().getBeanColonia();				
				beanColonia.setBeanTuristaCreador(null);
				listColoniaSet.add(beanColonia);
			}
			beanUsuarioTurista.setListaAmigos(listAmistad);
			beanUsuarioTurista.setListaColonias(listColoniaSet);
			return beanUsuarioTurista;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * METHOD: VER COLONIAS
	 * CODIGO: KCYS-99
	 * CUS: CUS_VER_COLONIAS_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param correoTuristaPerfil
	 * @param correoTuristaVisita
	 * @return
	 * @throws UnknownException
	 */
	public static List<Colonia> verColonias(String correoTuristaPerfil,String correoTuristaVisita) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTuristaPerfil = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTuristaPerfil);
			if(beanUsuarioTuristaPerfil==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			UsuarioTurista beanUsuarioTuristaVisita = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTuristaVisita);
			if(beanUsuarioTuristaVisita==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			List<Colonia>listColoniasPerfil=GestionColony.misColonias(correoTuristaPerfil);
			List<Colonia>listColoniasVisita=GestionColony.misColonias(correoTuristaVisita);
			for (int i = 0; i < listColoniasPerfil.size(); i++) {
				Colonia beanColoniaPerfil= listColoniasPerfil.get(i);
				beanColoniaPerfil.setEnComun(false);
				listColoniasPerfil.set(i, beanColoniaPerfil);
				for (int j = 0; j < listColoniasVisita.size(); j++) {
					Colonia beanColoniaVisita= listColoniasVisita.get(j);
	                if(beanColoniaPerfil.getCodeColonia().equals(beanColoniaVisita.getCodeColonia())){
	                	beanColoniaPerfil.setEnComun(true);
	                	listColoniasPerfil.set(i, beanColoniaPerfil);break;
	                }
	            }
	        }
			pm.close();
			return listColoniasPerfil;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * METHOD: VER INTERESES TURISTA
	 * CODIGO: KCYS-100
	 * CUS: CUS_VER_INTERESES_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static List<TuristaInteres> verInteresesTurista(String correoTuristaPerfil,String correoTuristaVisita) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTuristaPerfil = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTuristaPerfil);
			if(beanUsuarioTuristaPerfil==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			UsuarioTurista beanUsuarioTuristaVisita = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTuristaVisita);
			if(beanUsuarioTuristaVisita==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			LogicTuristaInteres logicTuristaInteres= new LogicTuristaInteres(pm);			
			List<TuristaInteres> listTuristaInteresPerfil=new ArrayList<TuristaInteres>();
			listTuristaInteresPerfil.addAll(logicTuristaInteres.getListarIntereses(correoTuristaPerfil));	
			List<TuristaInteres> listTuristaInteresVisita= new ArrayList<TuristaInteres>();
			listTuristaInteresVisita.addAll(logicTuristaInteres.getListarIntereses(correoTuristaVisita));
			for (int i = 0; i < listTuristaInteresPerfil.size(); i++) {
				TuristaInteres beanTuristaInteresPerfil= listTuristaInteresPerfil.get(i);
				beanTuristaInteresPerfil.setEnComun(false);
				listTuristaInteresPerfil.set(i, beanTuristaInteresPerfil);
				for (int j = 0; j < listTuristaInteresVisita.size(); j++) {
					TuristaInteres beanTuristaInteresVisita= listTuristaInteresVisita.get(j);
	                if(beanTuristaInteresPerfil.getCodeTuristaInteres().equals(beanTuristaInteresVisita.getCodeTuristaInteres())){
	                	beanTuristaInteresPerfil.setEnComun(true);
	                	listTuristaInteresPerfil.set(i, beanTuristaInteresPerfil);break;
	                }
	            }
	        }
			pm.close();
			return listTuristaInteresPerfil;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	
	/**
	 * METHOD: VER AMIGOS TURISTA
	 * CODIGO: KCYS-101
	 * CUS: CUS_VER_AMIGOS_TURISTA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */
	public static List<Amistad> verAmigosTurista(String correoTuristaPerfil,String correoTuristaVisita) throws UnknownException{
		PersistenceManager pm=null;		
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTuristaPerfil = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTuristaPerfil);
			if(beanUsuarioTuristaPerfil==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			UsuarioTurista beanUsuarioTuristaVisita = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTuristaVisita);
			if(beanUsuarioTuristaVisita==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			LogicAmistad logicAmistad= new LogicAmistad(pm);			
			List<Amistad> listAmistadPerfil=new ArrayList<Amistad>();
			listAmistadPerfil.addAll(logicAmistad.getListarAmigos(correoTuristaPerfil));			
			List<Amistad>listAmistadVisita= new ArrayList<Amistad>();
			listAmistadVisita.addAll(logicAmistad.getListarAmigos(correoTuristaVisita));			
			for (int i = 0; i < listAmistadPerfil.size(); i++) {
				Amistad beanAmistadPerfil= listAmistadPerfil.get(i);
				for (int j = 0; j < listAmistadVisita.size(); j++) {
					Amistad beanAmistadVisita= listAmistadVisita.get(j);
	                if(beanAmistadPerfil.getCodeAmistad().equals(beanAmistadVisita.getCodeAmistad())){
	                	beanAmistadPerfil.setEnComun(true);
	                	listAmistadPerfil.set(i, beanAmistadPerfil);break;
	                }
	            }
	        }
			pm.close();
			return listAmistadPerfil;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * METHOD: ENVIAR SOLICITUD AMISTAD 
	 * CODIGO: KCYS-103
	 * CUS: CUS_INVITAR_PERSONAS - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeTuristaEnvia
	 * @param codeTuristaRecibe
	 * @return
	 * @throws UnknownException
	 */

	@SuppressWarnings("unchecked")
	public static Boolean enviarSolicitudAmistad(String codeTuristaEnvia,String codeTuristaRecibe) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();		
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);			
			LogicEstadoSolicAmistad logicEstadoSolicAmistad= new LogicEstadoSolicAmistad(pm);
			LogicSolicitudAmistad logicSolicitudAmistad= new LogicSolicitudAmistad(pm);		
			LogicAmistad logicAmistad= new LogicAmistad(pm);
			
			EstadoSolicAmistad beanEstadoSolicAmistad=(EstadoSolicAmistad)logicEstadoSolicAmistad.getBeanByCode(P.PENDIENTE);
			if(beanEstadoSolicAmistad==null){
				throw new UnknownException("No existe codigo Estado Solicitud Amistad ");
			}			
			UsuarioTurista beanTuristaEnvia= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(codeTuristaEnvia);
			if(beanTuristaEnvia==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			UsuarioTurista beanTuristaRecibe=(UsuarioTurista)logicUsuarioTurista.getBeanByEmail(codeTuristaRecibe);
			if(beanTuristaRecibe==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			List<Amistad> listAmistad=(List<Amistad>)logicAmistad.verAmistad(codeTuristaEnvia, codeTuristaRecibe, P.ELIMINADO);			
			if(listAmistad!=null && listAmistad.size()>0){
				throw new UnknownException("Ya son amigos");
			}
			SolicitudAmistad beanSolAmistad= (SolicitudAmistad) logicSolicitudAmistad.getBeanByTurista(codeTuristaEnvia, codeTuristaRecibe);						
			if(beanSolAmistad!=null){
					if(beanSolAmistad.getEstadoTarea().equalsIgnoreCase("P") && beanSolAmistad.getCodeEstadoSolicAmistad().equals(P.PENDIENTE)){
						throw new UnknownException("Ya se envio una Solicitud de Amistad");
					}				
			}					
			
			//Validacion de que ya se envio la solicitud de Amistad..
				SolicitudAmistad beanSolicitudAmistad= new SolicitudAmistad();			
				String codeSolicitudAmistad= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanSolicitudAmistad.setIdSolicitudAmistad(KeyFactory.keyToString(KeyFactory.createKey
						(SolicitudAmistad.class.getSimpleName(), codeSolicitudAmistad)));	
				beanSolicitudAmistad.setCodeSolicitudAmistad(codeSolicitudAmistad);
				beanSolicitudAmistad.setBeanTuristaEnvia(beanTuristaEnvia);
				beanSolicitudAmistad.setCodeTuristaEnvia(beanTuristaEnvia.getCodeUsuarioTurista());			
				beanSolicitudAmistad.setBeanTuristaRecibe(beanTuristaRecibe);
				beanSolicitudAmistad.setCodeTuristaRecibe(beanTuristaRecibe.getCodeUsuarioTurista());
				beanSolicitudAmistad.setFecha(new java.util.Date());
				beanSolicitudAmistad.setVersion(new java.util.Date().getTime());
				beanSolicitudAmistad.setIsPersistente(true);
				beanSolicitudAmistad.setEstadoTarea(P.PENDIENTE);
				beanSolicitudAmistad.setBeanEstadoSolicAmistad(beanEstadoSolicAmistad);
				beanSolicitudAmistad.setCodeEstadoSolicAmistad(beanEstadoSolicAmistad.getCodeEstadoSolicAmistad());											
				BeanParametro beanParametro = new BeanParametro();			
				beanParametro.setBean(beanSolicitudAmistad);
				beanParametro.setTipoOperacion(P.INSERTAR);	
				Boolean rptaSolicitudAmistad= logicSolicitudAmistad.mantenimiento(beanParametro);
				if(!rptaSolicitudAmistad){
					throw new UnknownException(P.ERROR_OPERACION);
				}			
				Notificacion beanNotificacion=generarNotificacionSolicitudAmistad(beanSolicitudAmistad, beanParametro, pm);
				LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);																		
				UsuarioTurista beanTuristaGeneraNotificacion= beanNotificacion.getBeanTuristaGeneraNotificacion();				
				StringBuilder title = new StringBuilder();		
				StringBuilder msjTuristaNotificacion = new StringBuilder();		
				StringBuilder body = new StringBuilder();
				if(beanTuristaGeneraNotificacion.getApellido()!=null){
					title.append(beanTuristaGeneraNotificacion.getNombre()+" "+beanTuristaGeneraNotificacion.getApellido());
				}else{
					title.append(beanTuristaGeneraNotificacion.getNombre());
				}				
				msjTuristaNotificacion.append(title.toString());
				TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
				if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.ENVIO_SOLICITUD_AMISTAD)){
					body.append(" te ha enviado una Solicitud de Amistad ");	
					msjTuristaNotificacion.append(body.toString());
				}																								
				UsuarioTurista beanTuristaRecibeNotificacion= beanNotificacion.getBeanSolicitudAmistad().getBeanTuristaRecibe();				        		
				NotificacionTurista beanNotificacionTuristaInteractua= new NotificacionTurista();
				String codeNotificacionTuristaInteractua=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
						(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));					
				beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);																						
				beanNotificacionTuristaInteractua.setIsPersistente(true);
				beanNotificacionTuristaInteractua.setVersion(new java.util.Date().getTime());
				beanNotificacionTuristaInteractua.setBeanTuristaGeneraNotificacion(beanTuristaGeneraNotificacion);
				beanNotificacionTuristaInteractua.setCodeTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getCodeUsuarioTurista());
				beanNotificacionTuristaInteractua.setNombreTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getNombre());
				beanNotificacionTuristaInteractua.setApellidoTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getApellido());
				beanNotificacionTuristaInteractua.setFotoPerfilTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getFotoPerfil());					
				beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacion.toString());						
				beanNotificacionTuristaInteractua.setBeanNotificacion(beanNotificacion);
				beanNotificacionTuristaInteractua.setBeanSolicitudAmistad(beanSolicitudAmistad);
				beanNotificacionTuristaInteractua.setCodeSolicitudAmistad(beanSolicitudAmistad.getCodeSolicitudAmistad());
				beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
				beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
				beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacion.getFechaGeneroNotificacion());
				beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
				beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
				beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
				beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
				beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);																																	
				beanParametro.setBean(beanNotificacionTuristaInteractua);
				beanParametro.setTipoOperacion(P.INSERTAR);					
				Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(beanParametro);
				if(rptaNotificacionTurista){						
					try{
						if(beanNotificacionTuristaInteractua.getTokenFirebase()!=null && 
								!beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()){
						NotificationMessage not=new NotificationMessage();
						not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
						not.setOptionRestrictedPackageName("com.indiant");
				    	not.setOptionPriority(10);  	   
				    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
				        payLoad.setSound("default");
				        DataPayLoad dataPayLoad=new DataPayLoad();
				    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
				    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTipoNotificacion():"");
				    	dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia()!=null?beanNotificacionTuristaInteractua.getCodeNoticia():"");
				    	dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino()!=null?beanNotificacionTuristaInteractua.getCodeDestino():"");
				    	dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia()!=null?beanNotificacionTuristaInteractua.getCodeColonia():"");
				    	dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia()!=null?beanNotificacionTuristaInteractua.getNombreColonia():"");
				    	dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
				    	dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad():"":"");
				    	dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion():"");
				    	dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
				    	payLoad.setTitle("Indiant");
				    	payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());		    			    	
				    	not.setPayLoadData(dataPayLoad.buildPayLoad());
				    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
				    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
				    	cnx.sendNotificationHttp(not);
						}
						}catch(Exception ex){}
					return true;
				}					
				return false;												
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * METHOD: DEFINIR SOLICITUD AMISTAD 
	 * CODIGO: KCYS-104
	 * CUS: CUS_DEFINIR_SOLICITUD_AMISTAD - ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param codeSolicitudAmistad
	 * @param codeEstadoSolicitudAmistad
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean definirSolicitudAmistad(
			String codeSolicitudAmistad,
			String codeEstadoSolicitudAmistad) throws UnknownException{
		PersistenceManager pm=null;
		//Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();						
			LogicEstadoSolicAmistad logicEstadoSolicAmistad= new LogicEstadoSolicAmistad(pm);
			LogicSolicitudAmistad logicSolicitudAmistad= new LogicSolicitudAmistad(pm);
			LogicEstadoAmistad logicEstadoAmistad= new LogicEstadoAmistad(pm);
			LogicAmistad logicAmistad= new LogicAmistad(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicPais logicPais= new LogicPais(pm);						
			BeanParametro beanParametro = new BeanParametro();			
			SolicitudAmistad beanSolicitudAmistad= (SolicitudAmistad)logicSolicitudAmistad.getBeanByCode(codeSolicitudAmistad);
			if(beanSolicitudAmistad==null){
				throw new UnknownException("No existe codigo Solicitud Amistad ");
			}
			if(!beanSolicitudAmistad.getCodeEstadoSolicAmistad().equalsIgnoreCase(P.PENDIENTE)){
				if(beanSolicitudAmistad.getCodeEstadoSolicAmistad().equalsIgnoreCase(P.ACEPTADO)){
					return true;
				}else{
					return false;
				}
			}
			EstadoSolicAmistad beanEstadoSolicAmistad= (EstadoSolicAmistad)logicEstadoSolicAmistad.getBeanByCode(codeEstadoSolicitudAmistad);
			if(beanEstadoSolicAmistad==null){
				throw new UnknownException("No existe codigo Estado Solicitud Amistad ");
			}
			if(codeEstadoSolicitudAmistad.equals(P.PENDIENTE)){
				beanSolicitudAmistad.setBeanEstadoSolicAmistad(beanEstadoSolicAmistad);
				beanSolicitudAmistad.setCodeEstadoSolicAmistad(beanEstadoSolicAmistad.getCodeEstadoSolicAmistad());
				beanSolicitudAmistad.setEstadoTarea(P.ATENDIDA);
				Set<SolicitudAmistad> setSolicitudAmistad= new HashSet<SolicitudAmistad>();
				setSolicitudAmistad.addAll(logicSolicitudAmistad.getListarBeanByTurista(beanSolicitudAmistad.getCodeTuristaEnvia(),
						beanSolicitudAmistad.getCodeTuristaRecibe()));//A--> B
				setSolicitudAmistad.addAll(logicSolicitudAmistad.getListarBeanByTurista(beanSolicitudAmistad.getCodeTuristaRecibe(),
						beanSolicitudAmistad.getCodeTuristaEnvia()));//B-->A
				if(setSolicitudAmistad.contains(beanSolicitudAmistad)){
					setSolicitudAmistad.remove(beanSolicitudAmistad);
				}
				Iterator<SolicitudAmistad> iteratorSolicitudAmistad= setSolicitudAmistad.iterator();
				List<BeanParametro> listBeanParametro= new ArrayList<BeanParametro>();				
				List<SolicitudAmistad> collectionSolicitudAmistad= new ArrayList<SolicitudAmistad>();				
				collectionSolicitudAmistad.add(beanSolicitudAmistad);
				while(iteratorSolicitudAmistad.hasNext()){
					SolicitudAmistad beanSolicitudIterator= iteratorSolicitudAmistad.next();
					beanSolicitudIterator.setEstadoTarea(P.CADUCADA);
					collectionSolicitudAmistad.add(beanSolicitudIterator);
				}
				beanParametro=new BeanParametro();
				beanParametro.setBean(collectionSolicitudAmistad);
				beanParametro.setTipoOperacion(P.ACTUALIZAR);
				listBeanParametro.add(beanParametro);
				Boolean rptaSolicitudAmistad= logicSolicitudAmistad.mantenimiento(listBeanParametro);
				if(rptaSolicitudAmistad){
					//tx.commit();
					pm.close();
					return true;
				}
				return false;
			}	
			EstadoAmistad beanEstadoAmistad= (EstadoAmistad)logicEstadoAmistad.getBeanByCode(P.ACTIVO);
			if(beanEstadoAmistad==null){
				throw new UnknownException("No existe codigo Estado Amistad ");
			}			
			beanSolicitudAmistad.getBeanTuristaEnvia().setListaAmigos(null);
			beanSolicitudAmistad.getBeanTuristaEnvia().setListaColonias(null);
			beanSolicitudAmistad.getBeanTuristaEnvia().setListNoticias(null);
			beanSolicitudAmistad.getBeanTuristaEnvia().setListTuristaIntereses(null);
			beanSolicitudAmistad.getBeanTuristaRecibe().setListaAmigos(null);
			beanSolicitudAmistad.getBeanTuristaRecibe().setListaColonias(null);
			beanSolicitudAmistad.getBeanTuristaRecibe().setListNoticias(null);
			beanSolicitudAmistad.getBeanTuristaRecibe().setListTuristaIntereses(null);
			UsuarioTurista beanTuristaPrincipal= beanSolicitudAmistad.getBeanTuristaEnvia();			
			UsuarioTurista beanTuristaAmigo= beanSolicitudAmistad.getBeanTuristaRecibe();		
			Integer hashEnvia=beanTuristaPrincipal.getCodeUsuarioTurista().hashCode();
			Integer hashRecibe=beanTuristaAmigo.getCodeUsuarioTurista().hashCode();
			String codigoUnicoAmistad=hashEnvia.toString().concat("@").concat(hashRecibe.toString());
			if(hashEnvia>hashRecibe){
				codigoUnicoAmistad=hashRecibe.toString().concat("@").concat(hashEnvia.toString());
			}
			Pais beanPaisTuristaEnvia=null;
			Pais beanPaisTuristaRecibe=null;
			if(beanTuristaPrincipal.getCodePaisNacimiento()!=null){
				beanPaisTuristaEnvia=(Pais)logicPais.getBeanByCode(beanTuristaPrincipal.getCodePaisNacimiento());
			}else{
				Pais beanPais= new Pais();
				beanPais.setNombre(P.PAISDEFAULT);
				beanPaisTuristaEnvia=GestionShared.insertarPais(beanPais, pm);
				beanTuristaPrincipal.setBeanPaisNacimiento(beanPaisTuristaEnvia);
			}			
			if(beanTuristaAmigo.getCodePaisNacimiento()!=null){
				beanPaisTuristaRecibe=(Pais)logicPais.getBeanByCode(beanTuristaAmigo.getCodePaisNacimiento());
			}else{
				Pais beanPais= new Pais();
				beanPais.setNombre(P.PAISDEFAULT);
				beanPaisTuristaRecibe=GestionShared.insertarPais(beanPais, pm);
				beanTuristaAmigo.setBeanPaisNacimiento(beanPaisTuristaRecibe);
			}									
			Amistad beanAmistadTuristaEnvia= new Amistad();
			String codeAmistadTuristaEnvia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanAmistadTuristaEnvia.setIdAmistad(KeyFactory.keyToString(KeyFactory.createKey
					(Amistad.class.getSimpleName(), codeAmistadTuristaEnvia)));	
			beanAmistadTuristaEnvia.setCodeAmistad(codeAmistadTuristaEnvia);
			beanAmistadTuristaEnvia.setBeanTuristaPrincipal(beanTuristaPrincipal);
			beanAmistadTuristaEnvia.setCodeTuristaPrincipal(beanTuristaPrincipal.getCodeUsuarioTurista());		
			beanAmistadTuristaEnvia.setFotoPerfilTuristaPrincipal(beanTuristaPrincipal.getFotoPerfil());
			beanAmistadTuristaEnvia.setNombreTuristaPrincipal(beanTuristaPrincipal.getNombre());
			beanAmistadTuristaEnvia.setApellidoTuristaPrincipal(beanTuristaPrincipal.getApellido());
			beanAmistadTuristaEnvia.setBeanTuristaAmigo(beanTuristaAmigo);
			beanAmistadTuristaEnvia.setCodeTuristaAmigo(beanTuristaAmigo.getCodeUsuarioTurista());
			beanAmistadTuristaEnvia.setFotoPerfilTuristaAmigo(beanTuristaAmigo.getFotoPerfil());
			beanAmistadTuristaEnvia.setNombreTuristaAmigo(beanTuristaAmigo.getNombre());
			beanAmistadTuristaEnvia.setApellidoTuristaAmigo(beanTuristaAmigo.getApellido());
			beanAmistadTuristaEnvia.setBeanSolicitudAmistad(beanSolicitudAmistad);
			beanAmistadTuristaEnvia.setCodeSolicitudAmistad(beanSolicitudAmistad.getCodeSolicitudAmistad());
			beanAmistadTuristaEnvia.setCodUnicoAmistad(codigoUnicoAmistad);
			beanAmistadTuristaEnvia.setBeanPaisNacionalidadAmigo(beanPaisTuristaRecibe);
			beanAmistadTuristaEnvia.setBeanPaisNacionalidadPrincipal(beanPaisTuristaEnvia);
			beanAmistadTuristaEnvia.setBeanEstadoAmistad(beanEstadoAmistad);
			beanAmistadTuristaEnvia.setCodeEstadoAmistad(beanEstadoAmistad.getCodeEstadoAmistad());			
			beanAmistadTuristaEnvia.setNombrePaisAmigo(beanPaisTuristaRecibe.getNombre());
			beanAmistadTuristaEnvia.setCodePaisNacionalidadAmigo(beanTuristaAmigo.getCodePaisNacimiento());
			beanAmistadTuristaEnvia.setNombrePaisPrincipal(beanPaisTuristaEnvia.getNombre());
			beanAmistadTuristaEnvia.setCodePaisNacionalidadPrincipal(beanTuristaPrincipal.getCodePaisNacimiento());
			beanAmistadTuristaEnvia.setFechaInicio(new java.util.Date());
			beanAmistadTuristaEnvia.setFechaFin(new java.util.Date());
			beanAmistadTuristaEnvia.setVersion(new java.util.Date().getTime());	
			beanAmistadTuristaEnvia.setIsPersistente(true);
			beanParametro.setBean(beanAmistadTuristaEnvia);
			beanParametro.setTipoOperacion(P.INSERTAR);	
			Boolean rptaAmistad= logicAmistad.mantenimiento(beanParametro);
			if(!rptaAmistad){
				throw new UnknownException(P.ERROR_OPERACION);
			}										
			beanTuristaPrincipal=beanSolicitudAmistad.getBeanTuristaRecibe();
			beanTuristaAmigo=beanSolicitudAmistad.getBeanTuristaEnvia();
			String nombrePaisPrincipal=beanPaisTuristaRecibe.getNombre();
			String nombrePaisAmigo=beanPaisTuristaEnvia.getNombre();			
			Amistad beanAmistadTuristaRecibe= new Amistad();
			String codeAmistadTuristaRecibe= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanAmistadTuristaRecibe.setIdAmistad(KeyFactory.keyToString(KeyFactory.createKey
					(Amistad.class.getSimpleName(), codeAmistadTuristaRecibe)));	
			beanAmistadTuristaRecibe.setCodeAmistad(codeAmistadTuristaRecibe);
			beanAmistadTuristaRecibe.setBeanTuristaPrincipal(beanTuristaPrincipal);
			beanAmistadTuristaRecibe.setCodeTuristaPrincipal(beanTuristaPrincipal.getCodeUsuarioTurista());
			beanAmistadTuristaRecibe.setFotoPerfilTuristaPrincipal(beanTuristaPrincipal.getFotoPerfil());
			beanAmistadTuristaRecibe.setNombreTuristaPrincipal(beanTuristaPrincipal.getNombre());
			beanAmistadTuristaRecibe.setApellidoTuristaPrincipal(beanTuristaPrincipal.getApellido());
			beanAmistadTuristaRecibe.setBeanTuristaAmigo(beanTuristaAmigo);
			beanAmistadTuristaRecibe.setCodeTuristaAmigo(beanTuristaAmigo.getCodeUsuarioTurista());
			beanAmistadTuristaRecibe.setFotoPerfilTuristaAmigo(beanTuristaAmigo.getFotoPerfil());
			beanAmistadTuristaRecibe.setBeanSolicitudAmistad(beanSolicitudAmistad);
			beanAmistadTuristaRecibe.setCodeSolicitudAmistad(beanSolicitudAmistad.getCodeSolicitudAmistad());
			beanAmistadTuristaRecibe.setNombreTuristaAmigo(beanTuristaAmigo.getNombre());
			beanAmistadTuristaRecibe.setApellidoTuristaAmigo(beanTuristaAmigo.getApellido());
			beanAmistadTuristaRecibe.setCodUnicoAmistad(codigoUnicoAmistad);
			beanAmistadTuristaRecibe.setBeanPaisNacionalidadAmigo(beanPaisTuristaEnvia);
			beanAmistadTuristaRecibe.setBeanPaisNacionalidadPrincipal(beanPaisTuristaRecibe);
			beanAmistadTuristaRecibe.setBeanEstadoAmistad(beanEstadoAmistad);
			beanAmistadTuristaRecibe.setCodeEstadoAmistad(beanEstadoAmistad.getCodeEstadoAmistad());			
			beanAmistadTuristaRecibe.setNombrePaisAmigo(nombrePaisAmigo);
			beanAmistadTuristaRecibe.setCodePaisNacionalidadAmigo(beanTuristaAmigo.getCodePaisNacimiento());
			beanAmistadTuristaRecibe.setNombrePaisPrincipal(nombrePaisPrincipal);
			beanAmistadTuristaRecibe.setCodePaisNacionalidadPrincipal(beanTuristaPrincipal.getCodePaisNacimiento());
			beanAmistadTuristaRecibe.setIsPersistente(true);
			beanAmistadTuristaRecibe.setFechaInicio(new java.util.Date());
			beanAmistadTuristaRecibe.setFechaFin(new java.util.Date());
			beanAmistadTuristaRecibe.setVersion(new java.util.Date().getTime());
			beanParametro.setBean(beanAmistadTuristaRecibe);
			beanParametro.setTipoOperacion(P.INSERTAR);	
			Boolean rptaAmistadRecibe= logicAmistad.mantenimiento(beanParametro);
			if(!rptaAmistadRecibe){
				throw new UnknownException(P.ERROR_OPERACION);
			}			
			List<Amistad>listEnviaronSolicitudAmigo=new ArrayList<Amistad>();
			List<Amistad> amigosDeAmigo=logicAmistad.getBeanByTuristaPrimeraSolicitud(beanAmistadTuristaEnvia.getCodeTuristaAmigo());	
			if(amigosDeAmigo!=null){
				for (int i = 0; i < amigosDeAmigo.size(); i++) {
		            Amistad amigoDeAmigo=amigosDeAmigo.get(i);
		            SolicitudAmistad beanSolicitudAmistadAmigoDeAmigo= (SolicitudAmistad) logicSolicitudAmistad.getBeanByCode(amigoDeAmigo.getCodeSolicitudAmistad());
					if(beanSolicitudAmistadAmigoDeAmigo.getCodeTuristaEnvia().equalsIgnoreCase(amigoDeAmigo.getCodeTuristaAmigo()) &&
							beanSolicitudAmistadAmigoDeAmigo.getCodeTuristaRecibe().equalsIgnoreCase(beanAmistadTuristaEnvia.getCodeTuristaAmigo())){
						listEnviaronSolicitudAmigo.add(amigoDeAmigo);								
					}							
		        }
				if(!listEnviaronSolicitudAmigo.isEmpty()){
					Collections.sort(listEnviaronSolicitudAmigo,new Comparator<Amistad>(){
						@Override
						public int compare(Amistad beanAmistad, Amistad anotherAmistad) {						
							return new Double(beanAmistad.getVersion()).compareTo(new Double(anotherAmistad.getVersion()));
						}					
					});
					if(beanTuristaPrincipal.getCorreoTurista().equalsIgnoreCase(listEnviaronSolicitudAmigo.get(0).getCodeTuristaAmigo())){
						if(beanTuristaAmigo.getNumeroAfiliados()!=null){
							beanTuristaPrincipal.setNumeroAfiliados(beanTuristaPrincipal.getNumeroAfiliados()+1);
						}else{
							beanTuristaPrincipal.setNumeroAfiliados(0);
						}
					}							
				}
			}			
			Notificacion beanNotificacion=generarNotificacionDefinirSolicitud(beanSolicitudAmistad.getBeanTuristaRecibe(), beanParametro, pm);								
			LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);							
			
			UsuarioTurista beanTuristaGeneraNotificacion= beanNotificacion.getBeanTuristaGeneraNotificacion();				
			StringBuilder msjTuristaNotificacion = new StringBuilder();	
			StringBuilder title = new StringBuilder();					
			StringBuilder body = new StringBuilder();
			if(beanSolicitudAmistad.getBeanTuristaRecibe().getApellido()!=null){
				msjTuristaNotificacion.append(beanSolicitudAmistad.getBeanTuristaRecibe().getNombre()+" "+beanSolicitudAmistad.getBeanTuristaRecibe().getApellido());
			}else{
				msjTuristaNotificacion.append(beanSolicitudAmistad.getBeanTuristaRecibe().getNombre());
			}			
			title.append(msjTuristaNotificacion.toString());
			msjTuristaNotificacion.append(" ha aceptado tu Solicitud de Amistad");		
			body.append(" ha aceptado tu Solicitud de Amistad");
			UsuarioTurista beanTuristaRecibeNotificacion= beanSolicitudAmistad.getBeanTuristaEnvia();
			if(beanTuristaRecibeNotificacion.getTokenFirebase()!=null){
				NotificationMessage not=new NotificationMessage();   	    	
		    	not.setOptionRestrictedPackageName("com.indiant");
		    	not.setOptionPriority(10);  	   
		    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
		        payLoad.setSound("default");
		        DataPayLoad dataPayLoad=new DataPayLoad();
		    	dataPayLoad.add("idNotification", beanNotificacion.getIdNotificacion());				        				        	
	        	payLoad.setTitle(title.toString());
		    	payLoad.setBody(body.toString());		    			    	
		    	not.setPayLoadData(dataPayLoad.buildPayLoad());
		    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   
		    	not.setTargetTo(beanTuristaRecibeNotificacion.getTokenFirebase());	    	
		    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
		    	cnx.sendNotificationHttp(not);
			}					
			NotificacionTurista beanNotificacionTuristaInteractua= new NotificacionTurista();
			String codeNotificacionTuristaInteractua=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
					(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));					
			beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);																						
			beanNotificacionTuristaInteractua.setIsPersistente(true);
			beanNotificacionTuristaInteractua.setVersion(new java.util.Date().getTime());
			beanNotificacionTuristaInteractua.setBeanTuristaGeneraNotificacion(beanTuristaGeneraNotificacion);
			beanNotificacionTuristaInteractua.setCodeTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getCodeUsuarioTurista());
			beanNotificacionTuristaInteractua.setNombreTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getNombre());
			beanNotificacionTuristaInteractua.setApellidoTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getApellido());
			beanNotificacionTuristaInteractua.setFotoPerfilTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getFotoPerfil());					
			beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacion.toString());						
			beanNotificacionTuristaInteractua.setBeanNotificacion(beanNotificacion);
			beanNotificacionTuristaInteractua.setBeanSolicitudAmistad(beanSolicitudAmistad);
			beanNotificacionTuristaInteractua.setCodeSolicitudAmistad(beanSolicitudAmistad.getCodeSolicitudAmistad());
			beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
			beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
			beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
			beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
			beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacion.getFechaGeneroNotificacion());
			beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
			beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
			beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);																														
			beanParametro.setBean(beanNotificacionTuristaInteractua);
			beanParametro.setTipoOperacion(P.INSERTAR);					
			Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(beanParametro);
			if(!rptaNotificacionTurista){
				throw new UnknownException("No se proceso la Operacion Notificar a Turista ");
			}
				beanSolicitudAmistad.setBeanEstadoSolicAmistad(beanEstadoSolicAmistad);
				beanSolicitudAmistad.setCodeEstadoSolicAmistad(beanEstadoSolicAmistad.getCodeEstadoSolicAmistad());
				beanSolicitudAmistad.setEstadoTarea(P.ATENDIDA);
				//Atender a todas las solicitudes de Amistad que generaron los participantes.
				Set<SolicitudAmistad> setSolicitudAmistad= new HashSet<SolicitudAmistad>();
				setSolicitudAmistad.addAll(logicSolicitudAmistad.getListarBeanByTurista(beanSolicitudAmistad.getCodeTuristaEnvia(),
						beanSolicitudAmistad.getCodeTuristaRecibe()));//A--> B
				setSolicitudAmistad.addAll(logicSolicitudAmistad.getListarBeanByTurista(beanSolicitudAmistad.getCodeTuristaRecibe(),
						beanSolicitudAmistad.getCodeTuristaEnvia()));//B-->A
				if(setSolicitudAmistad.contains(beanSolicitudAmistad)){
					setSolicitudAmistad.remove(beanSolicitudAmistad);
				}
				Iterator<SolicitudAmistad> iteratorSolicitudAmistad= setSolicitudAmistad.iterator();
				List<BeanParametro> listBeanParametro= new ArrayList<BeanParametro>();				
				List<SolicitudAmistad> collectionSolicitudAmistad= new ArrayList<SolicitudAmistad>();
				
				collectionSolicitudAmistad.add(beanSolicitudAmistad);
				while(iteratorSolicitudAmistad.hasNext()){
					SolicitudAmistad beanSolicitudIterator= iteratorSolicitudAmistad.next();
					beanSolicitudIterator.setEstadoTarea(P.CADUCADA);					
					collectionSolicitudAmistad.add(beanSolicitudIterator);
				}
				beanParametro=new BeanParametro();
				beanParametro.setBean(collectionSolicitudAmistad);
				beanParametro.setTipoOperacion(P.ACTUALIZAR);
				listBeanParametro.add(beanParametro);
				Boolean rptaSolicitudAmistad= logicSolicitudAmistad.mantenimiento(listBeanParametro);
				if(!rptaSolicitudAmistad){
					throw new UnknownException(P.ERROR_OPERACION);
				}
				beanParametro=new BeanParametro();
				beanParametro.setBean(beanTuristaPrincipal);
				beanParametro.setTipoOperacion(P.ACTUALIZAR);
				Boolean rptaUsuarioTurista= logicUsuarioTurista.mantenimiento(beanParametro);
				if(!rptaUsuarioTurista){
					throw new UnknownException("No se proceso la Operacion Actualizar Usuario Turista ");
				}
				pm.close();
				return true;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}

	
	public static List<ComentarioNoticia> listarComentarioNoticia(String codeNoticia)throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicComentarioNoticia logicComentarioNoticia=new LogicComentarioNoticia(pm);																						
			List<ComentarioNoticia> listaComentarioNoticia=logicComentarioNoticia.getListarBean(null, codeNoticia);
			return listaComentarioNoticia;						
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static List<DivulgacionNoticia> listarDivulgacionNoticia(String codeNoticia)throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicDivulgacionNoticia logicDivulgacionNoticia=new LogicDivulgacionNoticia(pm);																						
			List<DivulgacionNoticia> listaDivulgacionNoticia=logicDivulgacionNoticia.getListBean(codeNoticia);
			return listaDivulgacionNoticia;						
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static List<NoticiaCompartida> listarComparteNoticia(String codeNoticia)throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNoticiaCompartida logicComparteNoticia=new LogicNoticiaCompartida(pm);																						
			List<NoticiaCompartida> listaComparteNoticia=logicComparteNoticia.getListBean(codeNoticia);
			return listaComparteNoticia;						
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static Noticia verNoticia(String codeNoticia)throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNoticia logicNoticia=new LogicNoticia(pm);																						
			Noticia beanNoticia=logicNoticia.getBeanByCode(codeNoticia);
			return beanNoticia;						
		} catch (Exception ex) {				
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/*METODOS REUTILIZABLES*/	
	public static Boolean queueNotificationDivulgacion(String codeNotificacion) throws UnknownException{
		PersistenceManager pm=null;	
		try{				
			pm = PMF.getPMF().getPersistenceManager();
			LogicParametrosSistema logicParametroSistema= new LogicParametrosSistema(pm);
			String codeParametroSistema="RF";
			ParametrosSistema beanParametroSistema= new ParametrosSistema();
			beanParametroSistema= (ParametrosSistema)logicParametroSistema.getBean(codeParametroSistema);
			Integer days=-1;
			try{
				days= Integer.parseInt(beanParametroSistema.getValor());
			}catch(Exception ex){
				throw new UnknownException("No se pudo convertir a entero el valor de dias");
			}				
			Calendar beanGregorianCalendar = GregorianCalendar.getInstance();
			beanGregorianCalendar.add(Calendar.DATE, ((-1)*days));
			Long rangoInferior=beanGregorianCalendar.getTime().getTime();
			LogicTipoNotificacion logicTipoNotificacion= new LogicTipoNotificacion(pm);
			LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);
			LogicNotificacion logicNotificacion= new LogicNotificacion(pm);	
			LogicConquista logicConquista= new LogicConquista(pm);			
			Notificacion beanNotificacion= (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);                        
			if(beanNotificacion==null){
				throw new UnknownException("Error en la cola");
			}
			UsuarioTurista beanUsuarioDivulga= beanNotificacion.getBeanTuristaGeneraNotificacion();
			StringBuilder msjTuristaNotificacionAmigo = new StringBuilder();	
			StringBuilder msjTuristaNotificacion = new StringBuilder();				
			 msjTuristaNotificacionAmigo.append(beanUsuarioDivulga.getNombre());
			TipoNotificacion beanTipoNotificacion=null;			
			TipoNotificacion beanTipoNotificacionUqc=null;
			if(beanNotificacion.getCodeTipoNotificacion().equalsIgnoreCase(P.DIVULGAR_CONQUISTA)){
				beanTipoNotificacion= (TipoNotificacion)logicTipoNotificacion.getBean(P.DIVULGACION_CONQUISTA_RELACIONADO);
				if(beanTipoNotificacion!=null){
					beanTipoNotificacionUqc= (TipoNotificacion)logicTipoNotificacion.getBean(P.USUARIO_QUIERE_CONQUISTAR);
					if(beanTipoNotificacionUqc==null){
						throw new UnknownException("No se existe Tipo Notificacion");
					}
				}
			}else if(beanNotificacion.getCodeTipoNotificacion().equalsIgnoreCase(P.DIVULGAR_NOVEDAD)){
				beanTipoNotificacion= (TipoNotificacion)logicTipoNotificacion.getBean(P.DIVULGACION_NOVEDAD_RELACIONA);
			}						
			if(beanTipoNotificacion== null){
				throw new UnknownException("No se existe Tipo Notificacion");
			}
				if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.DIVULGACION_CONQUISTA_RELACIONADO)){
					msjTuristaNotificacionAmigo.append("quiere conquistar ");	
					msjTuristaNotificacionAmigo.append(beanNotificacion.getNombreDestinoNegocioConquistado());
					//msjTuristaNotificacion.append(beanNotificacion.getNombreTuristaNegocioGeneraNotificacion());
					msjTuristaNotificacion.append(beanNotificacion.getNombreTuristaNegocioGeneraNotificacion());
					msjTuristaNotificacion.append(" ha Divulgado la conquista de ");	
				}else if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.DIVULGACION_NOVEDAD_RELACIONA)){
					msjTuristaNotificacion.append(beanNotificacion.getNombreTuristaNegocioGeneraNotificacion());
					msjTuristaNotificacion.append(" ha Divulgado la novedad de ");
				}
			List<NotificacionTurista> listParamNotificacionTurista= new ArrayList<NotificacionTurista>();
			//msjTuristaNotificacion.append(beanNotificacion.getNombreTuristaPublicaNoticia()+" "+beanNotificacion.getApellidoTuristaPublicaNoticia());			
			msjTuristaNotificacion.append(beanNotificacion.getNombreTuristaPublicaNoticia());
			HashMap<String,UsuarioTurista> queueUsuarios= queueNotificationBasic(beanNotificacion.getCodeNoticia(),beanNotificacion.getCodeTuristaPublicaNoticia(),beanNotificacion.getCodeTuristaGeneraNotificacion(),msjTuristaNotificacionAmigo,pm);						
//			Usuarios por Iteraccion en un rango de Fechas.
			queueNotificationRangue(queueUsuarios,beanNotificacion.getCodeTuristaPublicaNoticia(),beanNotificacion.getCodeNoticia(),beanNotificacion.getCodeTuristaGeneraNotificacion(), rangoInferior.toString(), pm);
//			Usuarios por Empatia.
			queueNotificationEmpatia(queueUsuarios,beanNotificacion.getCodeTuristaPublicaNoticia(),beanNotificacion.getCodeTuristaGeneraNotificacion(), rangoInferior.toString(), pm);
			/*USAR PROCESO AL MOMENTO DE LA CONQUISTA DE UN DESTINO O NEGOCIO*/
			if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.DIVULGACION_CONQUISTA_RELACIONADO)){			
				String codeDestinoConquistado= beanNotificacion.getCodeDestinoConquistadoDescubierto();
				List<Conquista> listConquistas= (List<Conquista>)logicConquista.getListarBeanByDestino(beanNotificacion.getCodeTuristaGeneraNotificacion(),codeDestinoConquistado);
				if(listConquistas!=null){
					Iterator<Conquista> listConquistasIterator= listConquistas.iterator();
					while(listConquistasIterator.hasNext()){
						UsuarioTurista beanUsuarioTurista= listConquistasIterator.next().getBeanTuristaConquista();						
						if(!beanUsuarioTurista.getCorreoTurista().equalsIgnoreCase(beanNotificacion.getCodeTuristaPublicaNoticia())
								&& !beanUsuarioTurista.getCorreoTurista().equalsIgnoreCase(beanNotificacion.getCodeTuristaGeneraNotificacion())){
						queueUsuarios.put(beanUsuarioTurista.getCorreoTurista(),beanUsuarioTurista);
						}
					}
				}
			}
			LogicAmistad logicAmistad=new LogicAmistad(pm);
                        System.err.print("Hasta aqui lista amigos");
			Set<Amistad> listaAmistad=logicAmistad.getListarAmigosActivos(beanNotificacion.getCodeTuristaGeneraNotificacion());                        
			Iterator<Amistad> iteradorAmistad=listaAmistad.iterator();
			HashMap<String,UsuarioTurista> mapAmigos=new HashMap<String,UsuarioTurista>();
			while(iteradorAmistad.hasNext()){
				UsuarioTurista beanAmigo=iteradorAmistad.next().getBeanTuristaAmigo();
				mapAmigos.put(beanAmigo.getCorreoTurista(), beanAmigo);
			}
			Iterator<Map.Entry<String,UsuarioTurista>> listQueueUsuarioIterator=queueUsuarios.entrySet().iterator(); 							
                        System.err.print("rev1");
			while(listQueueUsuarioIterator.hasNext()){
				NotificacionTurista beanNotificacionTuristaInteractua= new NotificacionTurista();
				String codeNotificacionTuristaInteractua=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
						(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));
				beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);
				Map.Entry<String,UsuarioTurista> beanMap=listQueueUsuarioIterator.next();
				UsuarioTurista beanUsuarioTuristaRecibe= beanMap.getValue();
				beanNotificacionTuristaInteractua.setBeanTuristaPublica(beanNotificacion.getBeanTuristaPublicaNoticia());				
				beanNotificacionTuristaInteractua.setBeanConquista(beanNotificacion.getBeanConquista());
				beanNotificacionTuristaInteractua.setCodeTuristaPublica(beanNotificacion.getCodeTuristaPublicaNoticia());					
				beanNotificacionTuristaInteractua.setCodeConquista(beanNotificacion.getCodeConquista());		
				beanNotificacionTuristaInteractua.setBeanDestino(beanNotificacion.getBeanDestinoConquistadoDescubierto());
				beanNotificacionTuristaInteractua.setCodeDestino(beanNotificacion.getCodeDestinoConquistadoDescubierto());
				beanNotificacionTuristaInteractua.setIsPersistente(true);
				beanNotificacionTuristaInteractua.setVersion(new java.util.Date().getTime());
				beanNotificacionTuristaInteractua.setBeanTuristaGeneraNotificacion(beanUsuarioDivulga);
				beanNotificacionTuristaInteractua.setCodeTuristaGeneraNotificacion(beanUsuarioDivulga.getCodeUsuarioTurista());
				beanNotificacionTuristaInteractua.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioDivulga.getNombre());
				beanNotificacionTuristaInteractua.setApellidoTuristaGeneraNotificacion(beanUsuarioDivulga.getApellido());
				beanNotificacionTuristaInteractua.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioDivulga.getFotoPerfil());
				beanNotificacionTuristaInteractua.setApellidoTuristaPublica(beanNotificacion.getBeanTuristaPublicaNoticia().getApellido());
				beanNotificacionTuristaInteractua.setNombreTuristaPublica((beanNotificacion.getBeanTuristaPublicaNoticia().getNombre()));
				beanNotificacionTuristaInteractua.setFotoPerfilTuristaPublica((beanNotificacion.getBeanTuristaPublicaNoticia().getFotoPerfil()));
				beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacion.toString());
				beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);
				beanNotificacionTuristaInteractua.setNombreDestinoNegocioConquistado(beanNotificacion.getBeanDestinoConquistadoDescubierto().getNombre());
				beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanUsuarioTuristaRecibe);
				beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanUsuarioTuristaRecibe.getCodeUsuarioTurista());
				beanNotificacionTuristaInteractua.setBeanNoticia(beanNotificacion.getBeanNoticia());
				beanNotificacionTuristaInteractua.setCodeNoticia(beanNotificacion.getBeanNoticia().getCodeNoticia());
				beanNotificacionTuristaInteractua.setBeanNotificacion(beanNotificacion);
				//beanNotificacionTuristaInteractua.setCodeNotificacionTurista(beanNotificacion.getCodeNotificacion());
				beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanTipoNotificacion);				
				beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacion.getFechaGeneroNotificacion());
				beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
				beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacion.toString());
				beanNotificacionTuristaInteractua.setCodeDivulgacionNoticia(beanNotificacion.getCodeDivulgacionNoticia());
				if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.DIVULGACION_CONQUISTA_RELACIONADO)){
					try{
					UsuarioTurista beanTuristaAmigo=mapAmigos.get(beanUsuarioTuristaRecibe.getCorreoTurista());
					beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaAmigo.getTokenFirebase());
					beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaAmigo);
					beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaAmigo.getCodeUsuarioTurista());
					beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanTipoNotificacionUqc);
					beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanTipoNotificacionUqc.getCodeTipoNotificacion());
					beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacionAmigo.toString());
					}catch(Exception ex){
						beanNotificacionTuristaInteractua.setTokenFirebase(beanUsuarioTuristaRecibe.getTokenFirebase());
						beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanUsuarioTuristaRecibe);
						beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanUsuarioTuristaRecibe.getCodeUsuarioTurista());
						beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanTipoNotificacion);
						beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
						beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacion.toString());						
					}
				}
				listParamNotificacionTurista.add(beanNotificacionTuristaInteractua);
                                System.err.print("rev2.0");
				if(beanNotificacionTuristaInteractua.getTokenFirebase()!=null && 
						!beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()){
                                    System.err.print("rev2.1");
				NotificationMessage not=new NotificationMessage();
				not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
				not.setOptionRestrictedPackageName("com.indiant");
		    	not.setOptionPriority(10);  	   
		    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
		        payLoad.setSound("default");
		        DataPayLoad dataPayLoad=new DataPayLoad();
		    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
		    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTipoNotificacion():"");
		    	dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia()!=null?beanNotificacionTuristaInteractua.getCodeNoticia():"");
		    	dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino()!=null?beanNotificacionTuristaInteractua.getCodeDestino():"");
		    	dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia()!=null?beanNotificacionTuristaInteractua.getCodeColonia():"");
		    	dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia()!=null?beanNotificacionTuristaInteractua.getNombreColonia():"");
		    	dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
		    	dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad():"":"");
		    	dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion():"");
		    	dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
		    	payLoad.setTitle("Indiant");
		    	payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());		    			    	
		    	not.setPayLoadData(dataPayLoad.buildPayLoad());
		    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
		    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
		    	cnx.sendNotificationHttp(not);
                        System.err.print("rev2.2");
				}
			}	
                        System.err.print("rev3");
			Collection<BeanParametro> listParametros=new ArrayList<BeanParametro>();
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(listParamNotificacionTurista);
			parametro.setTipoOperacion(P.INSERTAR);
			listParametros.add(parametro);
			Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(listParametros);
			if(rptaNotificacionTurista){		
				beanNotificacion.setEstadoTarea(P.EJECUTADO);
					BeanParametro beanParametro = new BeanParametro();
					beanParametro.setBean(beanNotificacion);
					beanParametro.setTipoOperacion(P.ACTUALIZAR);
					Boolean rptaNotificacion= logicNotificacion.mantenimiento(beanParametro);
					if(!rptaNotificacion){
						throw new UnknownException("No se actualizo la Notificacion");
					}else{
						pm.close();
					}
			}		
			return rptaNotificacionTurista;	
		}catch(Exception ex){                    
                        LOG.warning(ex.getLocalizedMessage());
                         StackTraceElement[] stack=ex.getStackTrace();
                        for(int i=0;i<stack.length;i++){
                            LOG.warning(String.valueOf(stack[i].getLineNumber()));
                        }
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		} 
	}
	
	public static void queueNotificationEmpatia(HashMap<String,UsuarioTurista> queueUsuarios,String codeTuristaPublica,String codeTuristaGenera,String rangoFecha,PersistenceManager pm) throws UnknownException{						
		LogicEmpatia logicEmpatia= new LogicEmpatia(pm);				
		List<Empatia> listEmpatias= (List<Empatia>)logicEmpatia.getListarBeanByEmail(codeTuristaGenera,new Long(rangoFecha));
		if(listEmpatias!=null){
			Iterator<Empatia> listEmpatiaIterator= listEmpatias.iterator();
			while(listEmpatiaIterator.hasNext()){												
				UsuarioTurista beanUsuarioTurista1= listEmpatiaIterator.next().getBeanTuristaUno();
				UsuarioTurista beanUsuarioTurista2= listEmpatiaIterator.next().getBeanTuristaDos();
				if(!beanUsuarioTurista1.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
						&& !beanUsuarioTurista1.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
				queueUsuarios.put(beanUsuarioTurista1.getCorreoTurista(),beanUsuarioTurista1);
				}
				if(!beanUsuarioTurista2.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
						&& !beanUsuarioTurista2.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
				queueUsuarios.put(beanUsuarioTurista2.getCorreoTurista(),beanUsuarioTurista2);
				}
			}
		}						
	}
	
	public static void queueNotificationRangue(HashMap<String,UsuarioTurista> queueUsuarios,String codeTuristaPublica,String codeNoticiaActual,String codeTuristaGenera,String rangoFecha,PersistenceManager pm) throws UnknownException{						
		LogicComentarioNoticia logicComentarioNoticia=new LogicComentarioNoticia(pm);
		LogicComparteNoticia logicComparteNoticia= new LogicComparteNoticia(pm);
		LogicDivulgacionNoticia logicDivulgacionNoticia= new LogicDivulgacionNoticia(pm);
		LogicNoticia logicNoticia= new LogicNoticia(pm);														
//		String [] tiposNoticia={"NC","CD","CN","CO","PN"};      
		List<Noticia> listNoticias=(List<Noticia>)logicNoticia.getListarBeanByRangoFecha(codeTuristaGenera,new Long(rangoFecha));
		  for (int i = 0; i < listNoticias.size(); i++) {
			  String codeNoticia= listNoticias.get(i).getCodeNoticia();
				List<ComentarioNoticia> listComentarioNoticia= (List<ComentarioNoticia>) logicComentarioNoticia.getListBean(codeNoticia);
				if(listComentarioNoticia!=null){
					Iterator<ComentarioNoticia> listComentarioIterator= listComentarioNoticia.iterator();
					while (listComentarioIterator.hasNext()){
						UsuarioTurista beanTuristaComenta=listComentarioIterator.next().getBeanTuristaComenta();
						if(!beanTuristaComenta.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
								&& !beanTuristaComenta.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
						queueUsuarios.put(beanTuristaComenta.getCorreoTurista(),beanTuristaComenta);
						}											
					}			
				}
				List<ComparteNoticia> listComparteNoticia= (List<ComparteNoticia>) logicComparteNoticia.getListBean(codeNoticia);		
				if(listComparteNoticia!=null){
					Iterator<ComparteNoticia> listComparteIterator= listComparteNoticia.iterator();
					while (listComparteIterator.hasNext()){		
						UsuarioTurista beanTuristaComparte=listComparteIterator.next().getBeanTuristaComparte();
						if(!beanTuristaComparte.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
								&& !beanTuristaComparte.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
						queueUsuarios.put(beanTuristaComparte.getCorreoTurista(),beanTuristaComparte);
						}						
					}
				}						
				List<DivulgacionNoticia> listDivulgacionNoticia= (List<DivulgacionNoticia>) logicDivulgacionNoticia.getListBean(codeNoticia);
				if(listDivulgacionNoticia!=null){
					Iterator<DivulgacionNoticia> listDivulgacionIterator= listDivulgacionNoticia.iterator();
					while (listDivulgacionIterator.hasNext()){	
						UsuarioTurista beanTuristaDivulgacion=listDivulgacionIterator.next().getBeanTuristaDivulga();
						if(!beanTuristaDivulgacion.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
								&& !beanTuristaDivulgacion.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
						queueUsuarios.put(beanTuristaDivulgacion.getCorreoTurista(),beanTuristaDivulgacion);
						}						
					}
				}
		  }							
	}
	
	public static HashMap<String,UsuarioTurista> queueNotificationBasic(String codeNoticia,String codeTuristaPublica,String codeTuristaGenera,StringBuilder msj,PersistenceManager pm) throws UnknownException{						
		LogicComentarioNoticia logicComentarioNoticia=new LogicComentarioNoticia(pm);
		LogicComparteNoticia logicComparteNoticia= new LogicComparteNoticia(pm);
		LogicDivulgacionNoticia logicDivulgacionNoticia= new LogicDivulgacionNoticia(pm);							
			HashMap<String,UsuarioTurista>queueUsuarios=new HashMap<String,UsuarioTurista>();																					
			List<ComentarioNoticia> listComentarioNoticia= (List<ComentarioNoticia>) logicComentarioNoticia.getListBean(codeNoticia);
			if(listComentarioNoticia!=null){
				Iterator<ComentarioNoticia> listComentarioIterator= listComentarioNoticia.iterator();
				while (listComentarioIterator.hasNext()){
					UsuarioTurista beanTuristaComenta=listComentarioIterator.next().getBeanTuristaComenta();
					if(!beanTuristaComenta.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
							&& !beanTuristaComenta.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
					queueUsuarios.put(beanTuristaComenta.getCorreoTurista(),beanTuristaComenta);
					}
				}			
			}
			List<ComparteNoticia> listComparteNoticia= (List<ComparteNoticia>) logicComparteNoticia.getListBean(codeNoticia);		
			if(listComparteNoticia!=null){
				Iterator<ComparteNoticia> listComparteIterator= listComparteNoticia.iterator();
				while (listComparteIterator.hasNext()){
					UsuarioTurista beanTuristaComparte=listComparteIterator.next().getBeanTuristaComparte();
					if(!beanTuristaComparte.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
							&& !beanTuristaComparte.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
					queueUsuarios.put(beanTuristaComparte.getCorreoTurista(),beanTuristaComparte);
					}					
				}
			}						
			List<DivulgacionNoticia> listDivulgacionNoticia= (List<DivulgacionNoticia>) logicDivulgacionNoticia.getListBean(codeNoticia);
			if(listDivulgacionNoticia!=null){
				Iterator<DivulgacionNoticia> listDivulgacionIterator= listDivulgacionNoticia.iterator();
				while (listDivulgacionIterator.hasNext()){
					UsuarioTurista beanTuristaDivulgacion=listDivulgacionIterator.next().getBeanTuristaDivulga();
					if(!beanTuristaDivulgacion.getCorreoTurista().equalsIgnoreCase(codeTuristaPublica)
							&& !beanTuristaDivulgacion.getCorreoTurista().equalsIgnoreCase(codeTuristaGenera)){
					queueUsuarios.put(beanTuristaDivulgacion.getCorreoTurista(),beanTuristaDivulgacion);
					}					
				}
			}
			return queueUsuarios;				
}	
	
	public static Boolean generaControlPosicion(UsuarioTurista beanTurista,Double latitud,Double longitud,Double altitud,String nombrePais,String nombreRegion,String nombreLocalidad,PersistenceManager pm) throws UnknownException{																			
		ControlPosicion beanControlPosicion= new ControlPosicion();
		LogicControlPosicion logicControlPosicion= new LogicControlPosicion(pm);		
		String codeControlPosicion=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		beanControlPosicion.setIdControlPosicion(KeyFactory.keyToString(KeyFactory.createKey(ControlPosicion.class.getSimpleName(), codeControlPosicion)));
		beanControlPosicion.setCodeControlPosicion(codeControlPosicion);
		beanControlPosicion.setBeanUsuarioTurista(beanTurista);
		beanControlPosicion.setCodeUsuarioTurista(beanTurista.getCodeUsuarioTurista());						
		beanControlPosicion.setLongitud(longitud);
		beanControlPosicion.setLatitud(latitud);
		beanControlPosicion.setAltitud(altitud);
		beanControlPosicion.setFecha(new java.util.Date());
		beanControlPosicion.setVersion(new java.util.Date().getTime());
		beanControlPosicion.setNombrePais(nombrePais);
		beanControlPosicion.setNombreRegion(nombreRegion);
		beanControlPosicion.setNombreLocalidad(nombreLocalidad);	
		beanControlPosicion.setIsPersistente(true);
		BeanParametro beanParametro=new BeanParametro();
		beanParametro.setBean(beanControlPosicion);
		beanParametro.setTipoOperacion(P.INSERTAR);		
		return logicControlPosicion.mantenimiento(beanParametro);
	}
	
	private static Boolean existeCuentaTurista(String correoUsuarioTurista,PersistenceManager pm){	
		try{
			CuentaTurista bean=(CuentaTurista)new LogicCuentaTurista(pm).getBeanByEmail(correoUsuarioTurista);
			if(bean!=null){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;
		}	
	}
	
	private static CuentaTurista beanCuentaTurista(String correoUsuarioTurista,PersistenceManager pm){	
		try{
			return (CuentaTurista)new LogicCuentaTurista(pm).getBeanByEmail(correoUsuarioTurista);
		}catch(Exception ex){
			return null;
		}	
	}
	
	private static CuentaTurista validarCuentaTurista(String correoTurista, String claveTurista,PersistenceManager pm) throws UnknownException{	
		try{
		LogicCuentaTurista logicCuentaTurista= new LogicCuentaTurista(pm);		
		CuentaTurista beanCuentaTurista= (CuentaTurista)logicCuentaTurista.getBeanByEmail(correoTurista);				
			if(beanCuentaTurista!=null){
				if(beanCuentaTurista.getTipo().trim().equalsIgnoreCase(P.OAUTH) && (beanCuentaTurista.getClave()==null ||beanCuentaTurista.getClave().isEmpty())){
					throw new UnknownException("Usted ingreso por red social, no tiene clave!");
				}
				String claveEncriptada=AESencrypt.encrypt(claveTurista);
				if(beanCuentaTurista.getClave().equals(claveEncriptada)){					
					return beanCuentaTurista;
				}else{
					throw new UnknownException(P.CLAVE_NO_COINCIDE);
				}
			}else{
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}
	}
	
	private static Boolean enviarMsgValidarCuentaTurista(String correo,String clave,String nombres,String apellidos) throws UnknownException{
		try {			
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				String msgBody=buildMensajeActivarCuentaTurista(correo,clave);
				String remitente=P.CORREOREMITENTE;
				Message msg = new MimeMessage(session);
				Multipart mp = new MimeMultipart();
				MimeBodyPart htmlPart = new MimeBodyPart();
				msg.setFrom(new InternetAddress(remitente.trim().toString(), "Indiant"));
				String usuario="Sr(a). "+nombres+" "+apellidos;
				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(correo.trim().toString(), usuario));
				msg.setSubject("Bienvenido a Indiant - Por Favor Active su cuenta");
				htmlPart.setContent(msgBody, "text/html");		
				mp.addBodyPart(htmlPart);
				msg.setContent(mp);
				Transport.send(msg);
		return true;
		} catch (UnsupportedEncodingException ex) {					
			throw new UnknownException(ex.getMessage());		
		} catch (MessagingException ex) {			
			
			throw new UnknownException(ex.getMessage());		
		}
	}
	
	private static String buildMensajeActivarCuentaTurista(String correo,String clave) throws UnknownException{
			try {
					String msgBody = "<div id=\"titulo\" style=\"background-color:#088A08; color:white; text-align: center; font-weight: bold; font-size:6mm; line-height:12mm\">INDIANA</div>";
					msgBody = msgBody+"<div style=\"padding:3mm\">Indiant es una Herramienta que promueve el Turismo.</div>";
					msgBody = msgBody+"<div style=\"padding:3mm\">Usuario: "+correo+"</div>";
					msgBody = msgBody+"<div style=\"padding:3mm\">Clave: "+clave+"</div>";
					msgBody = msgBody+"<div style=\"padding:3mm\">Valida tu cuenta haciendo click en el siguiente enlace</div>";
					String encrip=AESencrypt.encrypt(correo);
					String hex=StringHex.convertStringToHex(encrip);
					msgBody = msgBody+"<div style=\"padding:3mm; height:9mm; \"><a style=\"width:100%; height:100%;\"  href='"+P.URL+"/validarCuenta.html?encoded="+hex+"'\">Validar Cuenta</a></div>";
					return msgBody;
			} catch (Exception ex) {			
					throw new UnknownException(ex.getMessage());		
			}
		}
	
	private static SesionTurista insertarSesionTurista(UsuarioTurista beanUsuarioTurista,PersistenceManager pm)throws UnknownException{
		SesionTurista beanSesion= new SesionTurista();
		String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString(); 
		beanSesion.setIdSesionTurista(KeyFactory.keyToString(KeyFactory.createKey(SesionTurista.class.getSimpleName(), codigoAleatorioGenerico)));
		beanSesion.setCodeSesionTurista(codigoAleatorioGenerico);
		beanSesion.setBeanTurista(beanUsuarioTurista);
		beanSesion.setCorreoTurista(beanUsuarioTurista.getCodeUsuarioTurista());
		beanSesion.setIsPersistente(true);
		beanSesion.setVersion(new java.util.Date().getTime());
		beanSesion.setEstadoSesion(P.ACTIVO);					
		beanSesion.setFechaInicioSession(new java.util.Date());											
		BeanParametro beanParametro= new BeanParametro();
		beanParametro.setBean(beanSesion);
		beanParametro.setTipoOperacion(P.INSERTAR);
		LogicSesionTurista logicSesionTurista=new LogicSesionTurista(pm);
		SesionTurista beanSesionTuristaBd= logicSesionTurista.mantenimiento(beanParametro);
		return beanSesionTuristaBd;
	}
	
	private static SesionTurista insertarSesionTurista(AutenticaOauth beanAutenticaOauth,PersistenceManager pm)throws UnknownException{
		UsuarioTurista beanUsuarioTurista=beanAutenticaOauth.getBeanCuentaTurista().getBeanUsuarioTurista();
		BeanParametro beanParametro= new BeanParametro();
		beanParametro.setBean(beanUsuarioTurista);
		beanParametro.setTipoOperacion(P.ACTUALIZAR);
		LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
		Boolean rptaUsuarioTurista= logicUsuarioTurista.mantenimiento(beanParametro);
		if(!rptaUsuarioTurista){
			throw new UnknownException(P.ERROR_OPERACION);
		}
		SesionTurista beanSesion= new SesionTurista();
		String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString(); 
		beanSesion.setIdSesionTurista(KeyFactory.keyToString(KeyFactory.createKey(SesionTurista.class.getSimpleName(), codigoAleatorioGenerico)));
		beanSesion.setCodeSesionTurista(codigoAleatorioGenerico);
		beanSesion.setBeanTurista(beanUsuarioTurista);
		beanSesion.setCorreoTurista(beanUsuarioTurista.getCodeUsuarioTurista());
		beanSesion.setIsPersistente(true);
		beanSesion.setVersion(new java.util.Date().getTime());
		beanSesion.setEstadoSesion(P.ACTIVO);					
		beanSesion.setFechaInicioSession(new java.util.Date());
		beanSesion.setBeanAutenticaOauth(beanAutenticaOauth);
		beanSesion.setCodeAutenticaOauth(beanAutenticaOauth.getCodeAutenticaOauth());
		beanParametro= new BeanParametro();
		beanParametro.setBean(beanSesion);
		beanParametro.setTipoOperacion(P.INSERTAR);
		LogicSesionTurista logicSesionTurista=new LogicSesionTurista(pm);
		SesionTurista beanSesionTuristaBd= logicSesionTurista.mantenimiento(beanParametro);
		return beanSesionTuristaBd;
	}
	
	private static CuentaTurista insertarCuentaTurista(CuentaTurista beanCuentaTurista,PersistenceManager pm)throws UnknownException{		
		UsuarioTurista beanUsuarioTurista=beanCuentaTurista.getBeanUsuarioTurista();
		if(beanUsuarioTurista.getBeanPaisNacimiento()!=null &&
				!beanUsuarioTurista.getBeanPaisNacimiento().getNombre().isEmpty()){
			Pais beanPais=GestionShared.insertarPais(beanUsuarioTurista.getBeanPaisNacimiento(),pm);
			beanUsuarioTurista.setBeanPaisNacimiento(beanPais);
			beanUsuarioTurista.setCodePaisNacimiento(beanPais.getCodePais());
			beanUsuarioTurista.setNombrePaisNacimiento(beanPais.getNombre());
			if(beanUsuarioTurista.getBeanRegionNacimiento()!=null && 
					!beanUsuarioTurista.getBeanRegionNacimiento().getNombre().isEmpty()){
				beanUsuarioTurista.getBeanRegionNacimiento().setBeanPais(beanPais);
				beanUsuarioTurista.getBeanRegionNacimiento().setNombrePais(beanPais.getNombre());
				beanUsuarioTurista.getBeanRegionNacimiento().setCodePais(beanPais.getCodePais());				
				Region beanRegion=GestionShared.insertarRegion(beanUsuarioTurista.getBeanRegionNacimiento(),pm);
				beanUsuarioTurista.setBeanRegionNacimiento(beanRegion);
				beanUsuarioTurista.setCodeRegionNacimiento(beanRegion.getCodeRegion());
				beanUsuarioTurista.setNombreRegionNacimiento(beanRegion.getNombre());
				if(beanUsuarioTurista.getBeanLocalidadNacimiento()!=null && 
						!beanUsuarioTurista.getBeanLocalidadNacimiento().getNombre().isEmpty()){
					beanUsuarioTurista.getBeanLocalidadNacimiento().setBeanPais(beanPais);
					beanUsuarioTurista.getBeanLocalidadNacimiento().setNombrePais(beanPais.getNombre());
					beanUsuarioTurista.getBeanLocalidadNacimiento().setCodePais(beanPais.getCodePais());
					beanUsuarioTurista.getBeanLocalidadNacimiento().setBeanRegion(beanRegion);
					beanUsuarioTurista.getBeanLocalidadNacimiento().setNombreRegion(beanRegion.getNombre());
					beanUsuarioTurista.getBeanLocalidadNacimiento().setCodeRegion(beanRegion.getCodeRegion());
					Localidad beanLocalidad=GestionShared.insertarLocalidad(beanUsuarioTurista.getBeanLocalidadNacimiento(),pm);
					beanUsuarioTurista.setBeanLocalidadNacimiento(beanLocalidad);
					beanUsuarioTurista.setCodeLocalidadNacimiento(beanLocalidad.getCodeLocalidad());
					beanUsuarioTurista.setNombreLocalidadNacimiento(beanLocalidad.getNombre());
				}else{
					beanUsuarioTurista.setBeanLocalidadNacimiento(null);
					beanUsuarioTurista.setCodeLocalidadNacimiento(null);
				}
			}else{
				beanUsuarioTurista.setBeanRegionNacimiento(null);
				beanUsuarioTurista.setCodeRegionNacimiento(null);
				beanUsuarioTurista.setBeanLocalidadNacimiento(null);
				beanUsuarioTurista.setCodeLocalidadNacimiento(null);
			}
		}else{
			Pais beanPais= new Pais();
			Region beanRegion = new Region();
			beanPais.setNombre(P.PAISDEFAULT);
			Pais beanPaisDefecto=GestionShared.insertarPais(beanPais, pm);
			beanRegion.setNombre(P.LOCALIDADDEFAULT);
			beanRegion.setNombrePais(beanPaisDefecto.getNombre());
			beanRegion.setBeanPais(beanPaisDefecto);
			Region beanRegionDefecto=GestionShared.insertarRegion(beanRegion, pm);			
			beanUsuarioTurista.setBeanPaisNacimiento(beanPaisDefecto);
			beanUsuarioTurista.setCodePaisNacimiento(beanPaisDefecto.getCodePais());	
			beanUsuarioTurista.setBeanRegionNacimiento(beanRegionDefecto);
			beanUsuarioTurista.setCodeRegionNacimiento(beanRegionDefecto.getCodeRegion());
			beanUsuarioTurista.setBeanLocalidadNacimiento(null);
			beanUsuarioTurista.setCodeLocalidadNacimiento(null);
		}				
		Key keyUsuarioTurista = KeyFactory.createKey(UsuarioTurista.class.getSimpleName(), beanCuentaTurista.getCorreoTurista());
		String idUsuarioTurista = KeyFactory.keyToString(keyUsuarioTurista);	
		beanUsuarioTurista.setIdUsuarioTurista(idUsuarioTurista);
		beanUsuarioTurista.setCodeUsuarioTurista(beanCuentaTurista.getCorreoTurista());		
		beanUsuarioTurista.setCorreoTurista(beanCuentaTurista.getCorreoTurista());
		beanUsuarioTurista.setVersion((new java.util.Date()).getTime());
		beanUsuarioTurista.setTotalConquistas(0);
		beanUsuarioTurista.setTotalDescubiertos(0);
		beanUsuarioTurista.setTotalNegociosCreados(0);
		beanUsuarioTurista.setTotalConquistasNegocios(0);
		beanUsuarioTurista.setTotalColonias(0);
		beanUsuarioTurista.setNumeroAfiliados(0);
		beanUsuarioTurista.setNumeroConquistasDistintas(0);
		beanUsuarioTurista.setTotalColoniasCreadas(0);
		beanUsuarioTurista.setEstadoActivacion(P.PENDIENTE);
		beanUsuarioTurista.setIsPersistente(true);			
		String idCuentaTurista=KeyFactory.keyToString( KeyFactory.createKey(keyUsuarioTurista,CuentaTurista.class.getSimpleName(),beanCuentaTurista.getCorreoTurista()));
		beanCuentaTurista.setIdCuentaTurista(idCuentaTurista);		
		beanCuentaTurista.setCodeUsuarioTurista(beanUsuarioTurista.getCodeUsuarioTurista());				
		beanCuentaTurista.setFechaCreacion(new java.util.Date());
		beanCuentaTurista.setIsPersistente(true);
		beanCuentaTurista.setTipo(P.OAUTH);
		beanCuentaTurista.setVersion((new java.util.Date()).getTime());												
		String idEstadoCuenta=KeyFactory.keyToString(KeyFactory.createKey(EstadoCuenta.class.getSimpleName(), P.PENDIENTE));		
		EstadoCuenta beanEstadoCuenta=(EstadoCuenta)GestionMantenimiento.getObjeto(EstadoCuenta.class, idEstadoCuenta, pm);
		beanCuentaTurista.setBeanEstadoCuenta(beanEstadoCuenta);
		beanCuentaTurista.setCodeEstadoCuenta(beanEstadoCuenta.getCodeEstadoCuenta());	
		beanUsuarioTurista.setBeanCuentaTurista(beanCuentaTurista);
		beanUsuarioTurista.setTokenFirebase(beanCuentaTurista.getTokenFirebase());
		BeanParametro parameter= new BeanParametro();
		parameter.setBean(beanUsuarioTurista);
		parameter.setTipoOperacion(P.INSERTAR);
		UsuarioTurista beanUsuarioTuristaBd=new LogicUsuarioTurista(pm).mantenimientoReturn(parameter);
		if(beanUsuarioTuristaBd!=null){
		return beanUsuarioTuristaBd.getBeanCuentaTurista();
		}else{
			throw new UnknownException(P.ERROR_OPERACION);
		}
	}
		
	private static Boolean insertarConfiguracionCuenta(UsuarioTurista beanUsuarioTurista,BeanParametro parameter,PersistenceManager pm) throws UnknownException{
		ConfigCuenta beanConfigCuenta=new ConfigCuenta();
		String aleatorio=java.util.UUID.randomUUID().toString();
		String codeConfigCuenta=StringHex.convertStringToHex(aleatorio);
		beanConfigCuenta.setIdConfCuenta(KeyFactory.keyToString(KeyFactory.createKey(ConfigCuenta.class.getSimpleName(), codeConfigCuenta)));
		beanConfigCuenta.setCodeConfCuenta(codeConfigCuenta);
		String idPrivacidad= KeyFactory.keyToString(KeyFactory.createKey(Privacidad.class.getSimpleName(), "PB"));
		Privacidad beanPrivacidad = (Privacidad)GestionMantenimiento.getObjeto(Privacidad.class, idPrivacidad, pm);
		if(beanPrivacidad==null){
			throw new UnknownException("No existe el tipo de privacidad PB");
		}
		beanConfigCuenta.setBeanPrivacidadInvitacion(beanPrivacidad);
		beanConfigCuenta.setCodePrivacidadInvitacion(beanPrivacidad.getCodePrivacidad());
		beanConfigCuenta.setBeanPrivacidadNovedad(beanPrivacidad);
		beanConfigCuenta.setCodePrivacidadNovedad(beanPrivacidad.getCodePrivacidad());
		beanConfigCuenta.setBeanPrivacidadPerfil(beanPrivacidad);
		beanConfigCuenta.setCodePrivacidadPerfil(beanPrivacidad.getCodePrivacidad());
		beanConfigCuenta.setBeanTurista(beanUsuarioTurista);
		beanConfigCuenta.setCodeTurista(beanUsuarioTurista.getCodeUsuarioTurista());
		String idIdioma=KeyFactory.keyToString(KeyFactory.createKey(Idioma.class.getSimpleName(), P.IDIOMA_ESPANOL));
		Idioma beanIdioma= (Idioma)GestionMantenimiento.getObjeto(Idioma.class, idIdioma, pm);
		if(beanIdioma==null){
			throw new UnknownException("No existe el idioma");
		}
		beanConfigCuenta.setBeanIdioma(beanIdioma);
		beanConfigCuenta.setCodeIdioma(beanIdioma.getCodeIdioma());
		beanConfigCuenta.setVersion(new java.util.Date().getTime());
		beanConfigCuenta.setIsPersistente(true);
		parameter.setBean(beanConfigCuenta);
		parameter.setTipoOperacion(P.INSERTAR);
		return new LogicConfigCuenta(pm).mantenimiento(parameter);
	}
	public static MuroNoticia generaNoticiaCompartida(Novedad beanNovedad,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{																		
		Noticia beanNoticia = new Noticia();		
		String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString();
		String codeNoticia=StringHex.convertStringToHex(codigoAleatorioGenerico);
		Key keyNoticia=KeyFactory.createKey(Noticia.class.getSimpleName(), codeNoticia);
		beanNoticia.setIdNoticia(KeyFactory.keyToString(keyNoticia));
		beanNoticia.setCodeNoticia(codeNoticia);
		beanNoticia.setFechaPublicacion(beanNovedad.getFechaPublicacion());
		beanNoticia.setBeanMiembro(beanNovedad.getBeanMiembroGeneraNovedad());
		beanNoticia.setCodeMiembro(beanNovedad.getBeanMiembroGeneraNovedad().getCodeMiembro());
		beanNoticia.setBeanColonia(beanNovedad.getBeanColonia());
		beanNoticia.setCodeColonia(beanNovedad.getBeanColonia().getCodeColonia());
		beanNoticia.setNombreColoniaNegocioDestino(beanNovedad.getBeanColonia().getNombreColonia());
		
		UsuarioTurista beanUsuarioTurista=beanNovedad.getBeanTuristaGeneraNovedad();				
		beanNoticia.setBeanTuristaGeneraNoticia(beanUsuarioTurista);
		beanNoticia.setCodeTuristaGeneraNoticia(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNoticia.setNombreTuristaNegocioGeneraNoticia(beanUsuarioTurista.getNombre());
		beanNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanUsuarioTurista.getFotoPerfil());
		beanNoticia.setCorreoTuristaGeneraNoticia(beanUsuarioTurista.getCorreoTurista());
		
		beanNoticia.setBeanNoticiaOriginal(beanNovedad.getBeanNoticiaOriginal());//*
		beanNoticia.setCodeNoticiaOriginal(beanNovedad.getBeanNoticiaOriginal().getCodeNoticia());
		beanNoticia.setBeanNovedad(beanNovedad);
		beanNoticia.setCodeNovedad(beanNovedad.getCodeNovedad());
		beanNoticia.setTotalComentario(0);
		beanNoticia.setTotalCompartido(0);
		beanNoticia.setTotalDivulgado(0);		
		beanNoticia.setDescripcion(beanNovedad.getDescripcion());
		beanNoticia.setHiperLink(beanNovedad.getHiperLink());
		beanNoticia.setEnlace(beanNovedad.getEnlace());
		beanNoticia.setTipoNoticia(beanNovedad.getTipoNovedad());	
		beanNoticia.setEstadoTarea(P.PENDIENTE);		
		beanNoticia.setVersion((new java.util.Date()).getTime());
		beanNoticia.setFotoConquistaPublicidad(beanNovedad.getFotoPublicidad());
		beanNoticia.setIsPersistente(true);		
		
		MuroNoticia beanMuroNoticia=new MuroNoticia();
		String codigoAleatorio=java.util.UUID.randomUUID().toString();
		String codeMuroNoticia=StringHex.convertStringToHex(codigoAleatorio);
		String idMuroNoticia=KeyFactory.keyToString( KeyFactory.createKey(keyNoticia,MuroNoticia.class.getSimpleName(),codeMuroNoticia));
		beanMuroNoticia.setIdMuroNoticia(idMuroNoticia);
		beanMuroNoticia.setCodeMuroNoticia(codeMuroNoticia);
		beanMuroNoticia.setBeanTuristaMuro(beanUsuarioTurista);
		beanMuroNoticia.setCodeTuristaMuro(beanUsuarioTurista.getCodeUsuarioTurista());
		beanMuroNoticia.setCorreoTuristaMuro(beanUsuarioTurista.getCorreoTurista());			
		beanMuroNoticia.setBeanNoticia(beanNoticia);
		beanMuroNoticia.setCodeNoticia(codeNoticia);							
		beanMuroNoticia.setBeanTuristaGeneraNoticia(beanUsuarioTurista);
		beanMuroNoticia.setCodeTuristaGeneraNoticia(beanUsuarioTurista.getCodeUsuarioTurista());
		beanMuroNoticia.setNombreTuristaNegocioGeneraNoticia(beanUsuarioTurista.getNombre());
		beanMuroNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanUsuarioTurista.getFotoPerfil());
		beanMuroNoticia.setCorreoTuristaGeneraNoticia(beanUsuarioTurista.getCorreoTurista());
		beanMuroNoticia.setBeanColonia(beanNovedad.getBeanColonia());
		beanMuroNoticia.setBeanMiembro(beanNovedad.getBeanMiembroGeneraNovedad());
		beanMuroNoticia.setCodeColonia(beanNovedad.getCodeColonia());
		beanMuroNoticia.setCodeMiembro(beanNovedad.getCodeMiembroGeneraNovedad());			
		beanMuroNoticia.setHiperLink(beanNovedad.getHiperLink());
		beanMuroNoticia.setEnlace(beanNovedad.getEnlace());
		beanMuroNoticia.setFechaPublicacion(beanNovedad.getFechaPublicacion());
		beanMuroNoticia.setBeanNovedad(beanNovedad);
		beanMuroNoticia.setCodeNovedad(beanNovedad.getCodeNovedad());
		beanMuroNoticia.setTotalComentario(0);
		beanMuroNoticia.setTotalCompartido(0);			
		beanMuroNoticia.setTotalDivulgado(0);		
		beanMuroNoticia.setDescripcion(beanNovedad.getDescripcion());
		beanMuroNoticia.setTipoNoticia(beanNovedad.getTipoNovedad());	
		beanMuroNoticia.setVersion((new java.util.Date()).getTime());
		beanMuroNoticia.setIsPersistente(true);
		beanMuroNoticia.setVisto(P.NO_VISTO);
		beanMuroNoticia.setFotoConquistaPublicidad(beanNovedad.getFotoPublicidad());
		List<MuroNoticia> listMuroNoticia=new ArrayList<MuroNoticia>();
		listMuroNoticia.add(beanMuroNoticia);
		beanNoticia.setListMuroNoticia(listMuroNoticia);
		beanParametro.setBean(beanNoticia);		
		beanParametro.setTipoOperacion(P.INSERTAR);			
		LogicNoticia logicNoticia=new LogicNoticia(pm);
		Noticia beanNoticiabd=logicNoticia.mantenimientoReturn(beanParametro);
		return beanNoticiabd.getListMuroNoticia().get(0);
}

	private static Notificacion generarNotificacionNoticiaCompartida(MuroNoticia beanMuroNoticia,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{										
		LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
		Notificacion beanNotificacion= new Notificacion();
		String codeNotificacion= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
		beanNotificacion.setCodeNotificacion(codeNotificacion);
		
		Novedad beanNovedad = beanMuroNoticia.getBeanNovedad();
		Noticia beanNoticia= beanMuroNoticia.getBeanNoticia();
		
		beanNotificacion.setBeanNovedad(beanNovedad);
		beanNotificacion.setCodeNovedad(beanNovedad.getCodeNovedad());
				
		UsuarioTurista beanUsuarioTurista= beanNovedad.getBeanTuristaGeneraNovedad();																			
		beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
		beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
		beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());				
		beanNotificacion.setNombreColonia(beanNoticia.getNombreColoniaNegocioDestino());
		beanNotificacion.setBeanNoticia(beanNoticia);
		beanNotificacion.setCodeNoticia(beanNoticia.getCodeNoticia());
						
		UsuarioTurista beanUsuarioTuristaPublica=beanNovedad.getBeanTuristaGeneraNovedad();
		beanNotificacion.setBeanTuristaPublicaNoticia(beanUsuarioTuristaPublica);
		beanNotificacion.setCodeTuristaPublicaNoticia(beanUsuarioTuristaPublica.getCodeUsuarioTurista());
		beanNotificacion.setNombreTuristaPublicaNoticia(beanUsuarioTuristaPublica.getNombre());
		beanNotificacion.setApellidoTuristaPublicaNoticia(beanUsuarioTuristaPublica.getApellido());
		beanNotificacion.setFotoPerfilTuristaPublicaNoticia(beanUsuarioTuristaPublica.getFotoPerfil());
		
		beanNotificacion.setCodeColonia(beanNovedad.getCodeColonia());
		beanNotificacion.setCodeMiembro(beanNovedad.getCodeMiembroGeneraNovedad());		
		
		beanNotificacion.setEstadoTarea(P.PENDIENTE);
		beanNotificacion.setIsPersistente(true);
		String codeTipoNotificacion=null;
		if(beanNoticia.getCodeNovedad()!=null){
			codeTipoNotificacion=P.COMPARTIR_NOVEDAD;
		}else{
			if(beanNoticia.getCodeDestinoConquistado()!=null){
				codeTipoNotificacion=P.COMPARTIR_CONQUISTA;
			}else{
				throw new UnknownException("Tipo Notificacion No Definido");
			}
		}
		TipoNotificacion beanTipoNotificacion= new TipoNotificacion();
		beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean(codeTipoNotificacion);
		if(beanTipoNotificacion==null){
			throw new UnknownException("No existe Tipo Notificacion ".concat(codeTipoNotificacion));
		}
		beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
		beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
				
		beanNotificacion.setVersion(new java.util.Date().getTime());					
		beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
		beanParametro = new BeanParametro();											
		beanParametro.setBean(beanNotificacion);
		beanParametro.setTipoOperacion(P.INSERTAR);		
		Notificacion beanNotificacionBd= (Notificacion)logicNotificacion.mantenimientoReturn(beanParametro);
		return pm.detachCopy(beanNotificacionBd);
	}	
	
	public static Boolean queueNotificationNoticiaCompartida(String codeNotificacion) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);
			LogicNotificacion logicNotificacion= new LogicNotificacion(pm);		
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			
			Notificacion beanNotificacion= (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);						
			UsuarioTurista beanTuristaGeneraNotificacion= beanNotificacion.getBeanTuristaGeneraNotificacion();
			StringBuilder msjTuristaPublicaNovedad = new StringBuilder();				
			StringBuilder msjTuristaPublicaNoticiaOriginal= new StringBuilder();
			Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();	
			String codeColonia= beanNotificacion.getCodeColonia();
			UsuarioTurista beanTuristaPublica=beanNotificacion.getBeanTuristaPublicaNoticia();
			List<Miembro> listMiembros= (List<Miembro>)logicMiembro.getListarBeanByColonia(codeColonia);
			queueTuristas.add(beanTuristaPublica);
			if(listMiembros!=null){					
				Iterator<Miembro>listMiembrosIterator=listMiembros.iterator();
				while(listMiembrosIterator.hasNext()){
					queueTuristas.add(listMiembrosIterator.next().getBeanTurista());
				}					
				if(queueTuristas.contains(beanTuristaGeneraNotificacion)){
					queueTuristas.remove(beanTuristaGeneraNotificacion);
				}
				msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre());
				msjTuristaPublicaNoticiaOriginal.append(msjTuristaPublicaNovedad.toString());
				TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
				String concat=null;
				if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMPARTIR_CONQUISTA)){
					 concat="Conquista";							
				}else{
					if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMPARTIR_NOVEDAD)){
						concat="Novedad";
					}else{
						throw new UnknownException("Tipo Notificacion No Definido");
					}
				}
				msjTuristaPublicaNovedad.append(" ha compartido una ".concat(concat));		
				msjTuristaPublicaNoticiaOriginal.append(" ha compartido tu ".concat(concat));
				List<NotificacionTurista> listParamNotificacionTurista= new ArrayList<NotificacionTurista>();
				Iterator<UsuarioTurista> listQueueUsuarioIterator=queueTuristas.iterator(); 	        		        				
				while(listQueueUsuarioIterator.hasNext()){				
					UsuarioTurista beanTuristaRecibeNotificacion= listQueueUsuarioIterator.next();
					NotificacionTurista beanNotificacionTuristaInteractua= new NotificacionTurista();
					String codeNotificacionTuristaInteractua=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
					beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
							(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));
					beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);						
					beanNotificacionTuristaInteractua.setBeanTuristaPublica(beanNotificacion.getBeanTuristaPublicaNoticia());
					//beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);
					beanNotificacionTuristaInteractua.setBeanConquista(beanNotificacion.getBeanConquista());
					beanNotificacionTuristaInteractua.setCodeTuristaPublica(beanNotificacion.getCodeTuristaPublicaNoticia());					
					beanNotificacionTuristaInteractua.setCodeConquista(beanNotificacion.getCodeConquista());		
					beanNotificacionTuristaInteractua.setBeanDestino(beanNotificacion.getBeanDestinoConquistadoDescubierto());
					beanNotificacionTuristaInteractua.setCodeDestino(beanNotificacion.getCodeDestinoConquistadoDescubierto());
					beanNotificacionTuristaInteractua.setIsPersistente(true);				
					beanNotificacionTuristaInteractua.setVersion(new java.util.Date().getTime());
					beanNotificacionTuristaInteractua.setBeanTuristaGeneraNotificacion(beanTuristaGeneraNotificacion);
					beanNotificacionTuristaInteractua.setCodeTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getCodeUsuarioTurista());
					beanNotificacionTuristaInteractua.setNombreTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getNombre());
					beanNotificacionTuristaInteractua.setApellidoTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getApellido());
					beanNotificacionTuristaInteractua.setFotoPerfilTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getFotoPerfil());
					beanNotificacionTuristaInteractua.setApellidoTuristaPublica(beanNotificacion.getBeanTuristaPublicaNoticia().getApellido());
					beanNotificacionTuristaInteractua.setNombreTuristaPublica((beanNotificacion.getBeanTuristaPublicaNoticia().getNombre()));
					beanNotificacionTuristaInteractua.setFotoPerfilTuristaPublica((beanNotificacion.getBeanTuristaPublicaNoticia().getFotoPerfil()));					
					beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaPublicaNovedad.toString());	
					if(beanTuristaRecibeNotificacion.getCorreoTurista().equalsIgnoreCase(beanTuristaPublica.getCorreoTurista())){
						beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaPublicaNoticiaOriginal.toString());
					}
					beanNotificacionTuristaInteractua.setBeanNoticia(beanNotificacion.getBeanNoticia());
					beanNotificacionTuristaInteractua.setCodeComentarioNoticia(beanNotificacion.getCodeComentarioNoticia());
					beanNotificacionTuristaInteractua.setCodeNoticia(beanNotificacion.getCodeNoticia());
					beanNotificacionTuristaInteractua.setBeanNotificacion(beanNotificacion);
					beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
					beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacion.getFechaGeneroNotificacion());
					beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
					beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
					beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
					beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
					beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
					beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);
					beanNotificacionTuristaInteractua.setNombreDestinoNegocioConquistado(beanNotificacion.getBeanDestinoConquistadoDescubierto()!=null?beanNotificacion.getBeanDestinoConquistadoDescubierto().getNombre():null);				
					listParamNotificacionTurista.add(beanNotificacionTuristaInteractua);
					if(beanNotificacionTuristaInteractua.getTokenFirebase()!=null && 
							!beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()){
					NotificationMessage not=new NotificationMessage();
					not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
					not.setOptionRestrictedPackageName("com.indiant");
			    	not.setOptionPriority(10);  	   
			    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
			        payLoad.setSound("default");
			        DataPayLoad dataPayLoad=new DataPayLoad();
			    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
			    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTipoNotificacion():"");
			    	dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia()!=null?beanNotificacionTuristaInteractua.getCodeNoticia():"");
			    	dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino()!=null?beanNotificacionTuristaInteractua.getCodeDestino():"");
			    	dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia()!=null?beanNotificacionTuristaInteractua.getCodeColonia():"");
			    	dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia()!=null?beanNotificacionTuristaInteractua.getNombreColonia():"");
			    	dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
			    	dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad():"":"");
			    	dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion():"");
			    	dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
			    	payLoad.setTitle("Indiant");
			    	payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());		    			    	
			    	not.setPayLoadData(dataPayLoad.buildPayLoad());
			    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
			    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
			    	cnx.sendNotificationHttp(not);
					}
				}
				Collection<BeanParametro> listParametros=new ArrayList<BeanParametro>();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(listParamNotificacionTurista);
				parametro.setTipoOperacion(P.INSERTAR);
				listParametros.add(parametro);
				Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(listParametros);
				if(!rptaNotificacionTurista){
					throw new UnknownException("No se guardo  la Notificacion Turista");
				}				
			}
			beanNotificacion.setEstadoTarea(P.EJECUTADO);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanNotificacion);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaNotificacion=logicNotificacion.mantenimiento(beanParametro);
			if(rptaNotificacion){							
				return true;
			}
			return false;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		} 
	}
	
	private static DivulgacionNoticia insertarDivulgacionNoticia(UsuarioTurista beanUsuarioTuristaDivulga,Noticia beanNoticia,PersistenceManager pm)throws UnknownException{				
		Key keyUsuarioTurista = KeyFactory.createKey(UsuarioTurista.class.getSimpleName(), beanUsuarioTuristaDivulga.getCodeUsuarioTurista());
		String idUsuarioTurista = KeyFactory.keyToString(keyUsuarioTurista);
		beanUsuarioTuristaDivulga.setIdUsuarioTurista(idUsuarioTurista);			
		
		beanNoticia.setTotalDivulgado(beanNoticia.getTotalDivulgado()+1);		
		DivulgacionNoticia beanDivulgacion=new DivulgacionNoticia();
		beanDivulgacion.setBeanTuristaDivulga(beanUsuarioTuristaDivulga);
		beanDivulgacion.setCodeTuristaDivulgacion(beanUsuarioTuristaDivulga.getCodeUsuarioTurista());
		beanDivulgacion.setNombreTuristaDivulgacion(beanUsuarioTuristaDivulga.getNombre());
		beanDivulgacion.setApellidoTuristaDivulgacion(beanUsuarioTuristaDivulga.getApellido());
		beanDivulgacion.setFotoPerfilTuristaDivulga(beanUsuarioTuristaDivulga.getFotoPerfil());				
		beanDivulgacion.setBeanNoticia(beanNoticia);
		beanDivulgacion.setCodeNoticia(beanNoticia.getCodeNoticia());
		beanDivulgacion.setIsPersistente(true);
		beanDivulgacion.setVersion((new java.util.Date()).getTime());
		beanDivulgacion.setFechaDivulgacion(new java.util.Date());
		beanDivulgacion.setOperacion(P.INSERTAR);			
		String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString();
		String codeDivulgacionNoticia=StringHex.convertStringToHex(codigoAleatorioGenerico);
		beanDivulgacion.setCodeDivulgacionNoticia(codeDivulgacionNoticia);
		String idDivulgacionNoticia =KeyFactory.keyToString(KeyFactory.createKey(KeyFactory.stringToKey(beanNoticia.getIdNoticia()), DivulgacionNoticia.class.getSimpleName(), codeDivulgacionNoticia));
		beanDivulgacion.setIdDivulgacionNoticia(idDivulgacionNoticia);
		beanNoticia.getListDivulgacionNoticia().add(beanDivulgacion);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(beanDivulgacion);
		parametro.setTipoOperacion(beanDivulgacion.getOperacion());
		LogicDivulgacionNoticia logicNoticiaDivulgacion=new LogicDivulgacionNoticia(pm);
		return pm.detachCopy(logicNoticiaDivulgacion.mantenimientoReturn(parametro));		
	}
	
	private static Notificacion insertarNotificacionDivulgacion(Notificacion beanNotificacion,PersistenceManager pm)throws UnknownException{
		TipoNotificacion beanTipoNotificacion=obtenerTipoNotificacion(beanNotificacion.getListaTipoNotificacion(),beanNotificacion.getCodeTipoNotificacion());//CODEDIVULGACION DCO(DIVULGACION CONQUISTA) O DNO(DIVULGACION NOVEDAD)
		if(beanTipoNotificacion!=null && (beanTipoNotificacion.getCodeTipoNotificacion().equalsIgnoreCase("DCO")||beanTipoNotificacion.getCodeTipoNotificacion().equalsIgnoreCase("DNO"))){ 
			beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
			beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());			
			UsuarioTurista beanTuristaDivulga=beanNotificacion.getBeanTuristaGeneraNotificacion();
			beanNotificacion.setCodeTuristaGeneraNotificacion(beanTuristaDivulga.getCodeUsuarioTurista());
			beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanTuristaDivulga.getNombre());
			beanNotificacion.setApellidoTuristaGeneraNotificacion(beanTuristaDivulga.getApellido());
			beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanTuristaDivulga.getFotoPerfil());
			Noticia beanNoticia=beanNotificacion.getBeanNoticia();
			beanNotificacion.setBeanNoticia(beanNoticia);
			beanNotificacion.setCodeNoticia(beanNoticia.getCodeNoticia());
			beanNotificacion.setBeanTuristaPublicaNoticia(beanNoticia.getBeanTuristaGeneraNoticia());
			beanNotificacion.setCodeTuristaPublicaNoticia(beanNoticia.getBeanTuristaGeneraNoticia().getCodeUsuarioTurista());
			beanNotificacion.setNombreTuristaPublicaNoticia(beanNoticia.getBeanTuristaGeneraNoticia().getNombre());
			beanNotificacion.setApellidoTuristaPublicaNoticia(beanNoticia.getBeanTuristaGeneraNoticia().getApellido());
			beanNotificacion.setFotoPerfilTuristaPublicaNoticia(beanNoticia.getBeanTuristaGeneraNoticia().getFotoPerfil());
			if(beanTipoNotificacion.getCodeTipoNotificacion().equalsIgnoreCase(P.DIVULGAR_CONQUISTA)){//DIVULGACION CONQUISTA
				beanNotificacion.setBeanConquista(beanNoticia.getBeanConquista());
				beanNotificacion.setCodeConquista(beanNoticia.getBeanConquista().getCodeConquista());
				if(beanNoticia.getTipoNoticia().equalsIgnoreCase(P.CONQUISTA_DESTINO)){ //CONQUISTA DESTINO
					beanNotificacion.setBeanDestinoConquistadoDescubierto(beanNoticia.getBeanDestinoConquistado());
					beanNotificacion.setCodeDestinoConquistadoDescubierto(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
					beanNotificacion.setNombreDestinoNegocioConquistado(beanNoticia.getBeanDestinoConquistado().getNombre());
				}else if(beanNoticia.getTipoNoticia().equalsIgnoreCase(P.CONQUISTA_NEGOCIO) || beanNoticia.getTipoNoticia().equalsIgnoreCase(P.CONQUISTA_OFERTA)){//CONQUISTA NEGOCIO O CONQUISTA OFERTA
					if(beanNoticia.getBeanNegocioConquistado()!=null){
					beanNotificacion.setBeanNegocioConquistado(beanNoticia.getBeanNegocioConquistado());
					beanNotificacion.setCodeNegocioConquistado(beanNoticia.getBeanNegocioConquistado().getCodeUsuarioNegocio());
					beanNotificacion.setNombreDestinoNegocioConquistado(beanNoticia.getBeanNegocioConquistado().getNombreNegocio());
					beanNotificacion.setBeanDestinoConquistadoDescubierto(beanNoticia.getBeanDestinoConquistado());
					beanNotificacion.setCodeDestinoConquistadoDescubierto(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
					beanNotificacion.setNombreDestinoNegocioConquistado(beanNoticia.getBeanDestinoConquistado().getNombre());
					}else{
						beanNotificacion.setBeanDestinoConquistadoDescubierto(beanNoticia.getBeanDestinoConquistado());
						beanNotificacion.setCodeDestinoConquistadoDescubierto(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
						beanNotificacion.setNombreDestinoNegocioConquistado(beanNoticia.getBeanDestinoConquistado().getNombre());
					}
				}
			}else if(beanTipoNotificacion.getCodeTipoNotificacion().equalsIgnoreCase(P.DIVULGAR_NOVEDAD)){//DIVULGACION NOVEDAD
				beanNotificacion.setBeanNovedad(beanNoticia.getBeanNovedad());
				beanNotificacion.setCodeNovedad(beanNoticia.getBeanNovedad().getCodeNovedad());				
				beanNotificacion.setCodeColonia(beanNoticia.getBeanColonia().getCodeColonia());
				beanNotificacion.setNombreColonia(beanNoticia.getBeanColonia().getNombreColonia());
			}
			beanNotificacion.setIsPersistente(true);
			beanNotificacion.setVersion((new java.util.Date()).getTime());
			beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
			beanNotificacion.setOperacion(P.INSERTAR);	
			beanNotificacion.setEstadoTarea(P.PENDIENTE);
			String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString();
			String codeNotificacion=StringHex.convertStringToHex(codigoAleatorioGenerico);
			String  idNotificacion=KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(),codeNotificacion));
			beanNotificacion.setCodeNotificacion(codeNotificacion);
			beanNotificacion.setIdNotificacion(idNotificacion);
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(beanNotificacion);
			parametro.setTipoOperacion(beanNotificacion.getOperacion());
			LogicNotificacion logic=new LogicNotificacion(pm);
			Notificacion beanNotificacionBd=logic.mantenimientoReturn(parametro);
			beanNotificacionBd.setListaTipoNotificacion(beanNotificacion.getListaTipoNotificacion());
			return beanNotificacionBd;
		}else{
			throw new UnknownException("No existe tipo de notificacion o no es el tipo correcto");
		}
	}
	
	private static Boolean generarNotificacionDivulga(Notificacion beanNotificacion,PersistenceManager pm)throws UnknownException{		
		NotificacionTurista beanNotificacionTurista=new NotificacionTurista();					
		UsuarioTurista beanTuristaDivulga=beanNotificacion.getBeanTuristaGeneraNotificacion();
		beanNotificacionTurista.setBeanNotificacion(beanNotificacion);
		beanNotificacionTurista.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
		beanNotificacionTurista.setBeanTuristaGeneraNotificacion(beanTuristaDivulga);
		beanNotificacionTurista.setCodeTuristaGeneraNotificacion(beanTuristaDivulga.getCodeUsuarioTurista());
		beanNotificacionTurista.setNombreTuristaNegocioGeneraNotificacion(beanTuristaDivulga.getNombre());
		beanNotificacionTurista.setApellidoTuristaGeneraNotificacion(beanTuristaDivulga.getApellido());
		beanNotificacionTurista.setFotoPerfilTuristaNegocioGeneraNotificacion(beanTuristaDivulga.getFotoPerfil());
		Noticia beanNoticia=beanNotificacion.getBeanNoticia();
		beanNotificacionTurista.setBeanNoticia(beanNoticia);
		beanNotificacionTurista.setCodeNoticia(beanNoticia.getCodeNoticia());
		beanNotificacionTurista.setBeanTuristaPublica(beanNoticia.getBeanTuristaGeneraNoticia());
		beanNotificacionTurista.setCodeTuristaPublica(beanNoticia.getBeanTuristaGeneraNoticia().getCodeUsuarioTurista());
		beanNotificacionTurista.setNombreTuristaPublica(beanNoticia.getBeanTuristaGeneraNoticia().getNombre());
		beanNotificacionTurista.setApellidoTuristaPublica(beanNoticia.getBeanTuristaGeneraNoticia().getApellido());
		beanNotificacionTurista.setFotoPerfilTuristaPublica(beanNoticia.getBeanTuristaGeneraNoticia().getFotoPerfil());
		if(beanNotificacion.getBeanTipoNotificacion().getCodeTipoNotificacion().equalsIgnoreCase(P.DIVULGAR_CONQUISTA)){//DIVULGACION CONQUISTA			
			TipoNotificacion beanTipoNotificacion=obtenerTipoNotificacion(beanNotificacion.getListaTipoNotificacion(),P.DIVULGAR_MI_CONQUISTA);//DIVULGACION MI CONQUISTA
			if(beanTipoNotificacion!=null){
				beanNotificacionTurista.setBeanTipoNotificacion(beanTipoNotificacion);
				beanNotificacionTurista.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
				beanNotificacionTurista.setBeanTuristaRecibeNotificacion(beanNotificacion.getBeanTuristaPublicaNoticia());
				beanNotificacionTurista.setTokenFirebase(beanNotificacion.getBeanTuristaPublicaNoticia().getTokenFirebase());
				beanNotificacionTurista.setCodeTuristaRecibeNotificacion(beanNotificacion.getBeanTuristaPublicaNoticia().getCodeUsuarioTurista());
				beanNotificacionTurista.setCodeTuristaGeneraNotificacion(beanNotificacion.getCodeTuristaGeneraNotificacion());
				StringBuilder builderMsg = new StringBuilder();
				builderMsg.append(beanTuristaDivulga.getNombre()+" "+beanTuristaDivulga.getApellido());	
				builderMsg.append(" ha divulgado tu conquista ");
				beanNotificacionTurista.setMensajeNotificacion(builderMsg.toString());				
				beanNotificacionTurista.setBeanConquista(beanNoticia.getBeanConquista());
				beanNotificacionTurista.setCodeConquista(beanNoticia.getBeanConquista().getCodeConquista());				
				if(beanNoticia.getTipoNoticia().equalsIgnoreCase(P.CONQUISTA_DESTINO)){ //CONQUISTA DESTINO
					beanNotificacionTurista.setBeanDestino(beanNoticia.getBeanDestinoConquistado());
					beanNotificacionTurista.setCodeDestino(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
					beanNotificacionTurista.setNombreDestinoNegocioConquistado(beanNoticia.getBeanDestinoConquistado().getNombre());
				}else if(beanNoticia.getTipoNoticia().equalsIgnoreCase(P.CONQUISTA_NEGOCIO) || beanNoticia.getTipoNoticia().equalsIgnoreCase(P.CONQUISTA_OFERTA)){//CONQUISTA NEGOCIO O CONQUISTA OFERTA
					if(beanNoticia.getBeanNegocioConquistado()!=null){
					beanNotificacionTurista.setBeanNegocioConquistado(beanNoticia.getBeanNegocioConquistado());
					beanNotificacionTurista.setCodeNegocioConquistado(beanNoticia.getBeanNegocioConquistado().getCodeUsuarioNegocio());
					beanNotificacionTurista.setNombreDestinoNegocioConquistado(beanNoticia.getBeanNegocioConquistado().getNombreNegocio());
					beanNotificacionTurista.setBeanDestino(beanNoticia.getBeanDestinoConquistado());
					beanNotificacionTurista.setCodeDestino(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
					beanNotificacionTurista.setNombreDestinoNegocioConquistado(beanNoticia.getBeanDestinoConquistado().getNombre());
					}else{
						beanNotificacionTurista.setBeanDestino(beanNoticia.getBeanDestinoConquistado());
						beanNotificacionTurista.setCodeDestino(beanNoticia.getBeanDestinoConquistado().getCodeDestino());
						beanNotificacionTurista.setNombreDestinoNegocioConquistado(beanNoticia.getBeanDestinoConquistado().getNombre());
					}
				}
			}else{
				throw new UnknownException("No existe tipo de notificacion");
			}			
		}else if(beanNotificacion.getBeanTipoNotificacion().getCodeTipoNotificacion().equalsIgnoreCase(P.DIVULGAR_NOVEDAD)){//DIVULGACION NOVEDAD			
			TipoNotificacion beanTipoNotificacion=obtenerTipoNotificacion(beanNotificacion.getListaTipoNotificacion(),P.DIVULGAR_MI_NOVEDAD);//DIVULGACION MI NOVEDAD
			beanNotificacionTurista.setBeanTipoNotificacion(beanTipoNotificacion);
			beanNotificacionTurista.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
			beanNotificacionTurista.setBeanTuristaRecibeNotificacion(beanNotificacion.getBeanTuristaPublicaNoticia());
			beanNotificacionTurista.setTokenFirebase(beanNotificacion.getBeanTuristaPublicaNoticia().getTokenFirebase());
			beanNotificacionTurista.setCodeTuristaRecibeNotificacion(beanNotificacion.getBeanTuristaPublicaNoticia().getCodeUsuarioTurista());
			beanNotificacionTurista.setCodeTuristaGeneraNotificacion(beanNotificacion.getCodeTuristaGeneraNotificacion());
			StringBuilder builderMsg = new StringBuilder();
			builderMsg.append(beanTuristaDivulga.getNombre()+" "+beanTuristaDivulga.getApellido());	
			builderMsg.append(" ha divulgado tu novedad");
			beanNotificacionTurista.setMensajeNotificacion(builderMsg.toString());
			beanNotificacionTurista.setBeanNovedad(beanNoticia.getBeanNovedad());
			beanNotificacionTurista.setCodeNovedad(beanNoticia.getBeanNovedad().getCodeNovedad());
			beanNotificacionTurista.setBeanColonia(beanNoticia.getBeanColonia());
			beanNotificacionTurista.setCodeColonia(beanNoticia.getBeanColonia().getCodeColonia());
			beanNotificacionTurista.setNombreColonia(beanNoticia.getBeanColonia().getNombreColonia());
		}
		beanNotificacionTurista.setIsPersistente(true);
		beanNotificacionTurista.setVersion((new java.util.Date()).getTime());
		beanNotificacionTurista.setOperacion(P.INSERTAR);
		beanNotificacionTurista.setVisto(P.NO_VISTO);
		beanNotificacionTurista.setCodeDivulgacionNoticia(beanNotificacion.getCodeDivulgacionNoticia());
		String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString();
		String codeNotificacionTurista=StringHex.convertStringToHex(codigoAleatorioGenerico);
		String  idNotificacionTurista=KeyFactory.keyToString(KeyFactory.createKey(NotificacionTurista.class.getSimpleName(),codeNotificacionTurista));
		beanNotificacionTurista.setIdNotificacionTurista(idNotificacionTurista);
		beanNotificacionTurista.setCodeNotificacionTurista(codeNotificacionTurista);		
		LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);		
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(beanNotificacionTurista);
		parametro.setTipoOperacion(P.INSERTAR);
		boolean valueResult=logicNotificacionTurista.mantenimiento(parametro);
		if(valueResult){
			try{
			if(beanNotificacionTurista.getTokenFirebase()!=null && 
					!beanNotificacionTurista.getTokenFirebase().isEmpty()){
			NotificationMessage not=new NotificationMessage();
			not.setTargetTo(beanNotificacionTurista.getBeanTuristaRecibeNotificacion().getTokenFirebase());
			not.setOptionRestrictedPackageName("com.indiant");
	    	not.setOptionPriority(10);  	   
	    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
	        payLoad.setSound("default");
	        DataPayLoad dataPayLoad=new DataPayLoad();
	    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTurista.getCodeNotificacionTurista());
	    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTurista.getCodeTipoNotificacion()!=null?beanNotificacionTurista.getCodeTipoNotificacion():"");
	    	dataPayLoad.add("codeNoticia", beanNotificacionTurista.getCodeNoticia()!=null?beanNotificacionTurista.getCodeNoticia():"");
	    	dataPayLoad.add("codeDestino", beanNotificacionTurista.getCodeDestino()!=null?beanNotificacionTurista.getCodeDestino():"");
	    	dataPayLoad.add("codeColonia", beanNotificacionTurista.getCodeColonia()!=null?beanNotificacionTurista.getCodeColonia():"");
	    	dataPayLoad.add("nombreColonia", beanNotificacionTurista.getNombreColonia()!=null?beanNotificacionTurista.getNombreColonia():"");
	    	dataPayLoad.add("fotoUsuario", beanNotificacionTurista.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTurista.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
	    	dataPayLoad.add("fotoNoticia", beanNotificacionTurista.getBeanNoticia()!=null?beanNotificacionTurista.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTurista.getBeanNoticia().getFotoConquistaPublicidad():"":"");
	    	dataPayLoad.add("codeTurista", beanNotificacionTurista.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTurista.getCodeTuristaGeneraNotificacion():"");
	    	dataPayLoad.add("tokenFirebase", beanNotificacionTurista.getBeanTuristaRecibeNotificacion().getTokenFirebase());
	    	payLoad.setTitle("Indiant");
	    	payLoad.setBody(beanNotificacionTurista.getMensajeNotificacion());		    			    	
	    	not.setPayLoadData(dataPayLoad.buildPayLoad());
	    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
	    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
	    	cnx.sendNotificationHttp(not);
			}
			}catch(Exception ex){}
		}
		return valueResult;			
	}
	
	private static Notificacion generarNotificacionSolicitudAmistad(SolicitudAmistad beanSolicitudAmistad,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{	
		LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
		Notificacion beanNotificacion= new Notificacion();
		String codeNotificacion= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
		beanNotificacion.setCodeNotificacion(codeNotificacion);
		
		UsuarioTurista beanUsuarioTurista= beanSolicitudAmistad.getBeanTuristaEnvia();																	
		beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
		beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
		beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());				
		
		beanNotificacion.setBeanSolicitudAmistad(beanSolicitudAmistad);
		beanNotificacion.setCodeSolicitudAmistad(beanSolicitudAmistad.getCodeSolicitudAmistad());
		
		beanNotificacion.setEstadoTarea(P.PENDIENTE);
		beanNotificacion.setIsPersistente(true);	
		TipoNotificacion beanTipoNotificacion= new TipoNotificacion();
		beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean(P.ENVIO_SOLICITUD_AMISTAD);
		if(beanTipoNotificacion==null){
			throw new UnknownException("No se encontro el TipoNotificacion ");
		}	
		beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
		beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
				
		beanNotificacion.setVersion(new java.util.Date().getTime());					
		beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
		beanParametro = new BeanParametro();											
		beanParametro.setBean(beanNotificacion);
		beanParametro.setTipoOperacion(P.INSERTAR);		
		Boolean rptaNotificacion=logicNotificacion.mantenimiento(beanParametro);
		if(!rptaNotificacion){
			throw new UnknownException(P.ERROR_OPERACION);
		}
		return beanNotificacion;										
	}
	
	private static Notificacion generarNotificacionDefinirSolicitud(UsuarioTurista beanUsuarioTurista,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{	
		LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
		Notificacion beanNotificacion= new Notificacion();
		String codeNotificacion= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
		beanNotificacion.setCodeNotificacion(codeNotificacion);																		
		beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
		beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
		beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());	
		beanNotificacion.setEstadoTarea(P.PENDIENTE);
		beanNotificacion.setIsPersistente(true);	
		TipoNotificacion beanTipoNotificacion= new TipoNotificacion();
		beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean(P.SOLICITUD_AMISTAD_ACEPTADA);
		if(beanTipoNotificacion==null){
			throw new UnknownException("No se encontro el TipoNotificacion ");
		}	
		beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
		beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());				
		beanNotificacion.setVersion(new java.util.Date().getTime());					
		beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
		beanParametro = new BeanParametro();											
		beanParametro.setBean(beanNotificacion);
		beanParametro.setTipoOperacion(P.INSERTAR);		
		Boolean rptaNotificacion=logicNotificacion.mantenimiento(beanParametro);
		if(!rptaNotificacion){
			throw new UnknownException(P.ERROR_OPERACION);
		}
		return beanNotificacion;										
	}
	
	private static Notificacion generarNotificacionComentario(ComentarioNoticia beanComentarioNoticia,String codeTipoNotificacion,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{										
		LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
		Notificacion beanNotificacion= new Notificacion();
		String codeNotificacion= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
		beanNotificacion.setCodeNotificacion(codeNotificacion);
		
		Noticia beanNoticia= beanComentarioNoticia.getBeanNoticia();
		UsuarioTurista beanUsuarioTurista= beanComentarioNoticia.getBeanTuristaComenta();																				
		beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
		beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
		beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());				
		beanNotificacion.setCodeComentarioNoticia(beanComentarioNoticia.getCodeComentarioNoticia());		
		beanNotificacion.setBeanNoticia(beanNoticia);
		beanNotificacion.setCodeNoticia(beanNoticia.getCodeNoticia());
						
		UsuarioTurista beanUsuarioTuristaPublica= beanNoticia.getBeanTuristaGeneraNoticia();
		beanNotificacion.setBeanTuristaPublicaNoticia(beanUsuarioTuristaPublica);
		beanNotificacion.setCodeTuristaPublicaNoticia(beanUsuarioTuristaPublica.getCodeUsuarioTurista());
		beanNotificacion.setNombreTuristaPublicaNoticia(beanUsuarioTuristaPublica.getNombre());
		beanNotificacion.setApellidoTuristaPublicaNoticia(beanUsuarioTuristaPublica.getApellido());
		beanNotificacion.setFotoPerfilTuristaPublicaNoticia(beanUsuarioTuristaPublica.getFotoPerfil());
		
		
		if(codeTipoNotificacion.equalsIgnoreCase(P.COMENTARIO_CONQUISTA)){
			Conquista beanConquista= beanComentarioNoticia.getBeanNoticia().getBeanConquista();
			beanNotificacion.setBeanConquista(beanConquista);
			beanNotificacion.setCodeConquista(beanConquista.getCodeConquista());
			
			Destino beanDestino= beanComentarioNoticia.getBeanNoticia().getBeanDestinoConquistado();
			beanNotificacion.setBeanDestinoConquistadoDescubierto(beanDestino);
			beanNotificacion.setCodeDestinoConquistadoDescubierto(beanDestino.getCodeDestino());
		}
		if(codeTipoNotificacion.equalsIgnoreCase(P.COMENTARIO_NOVEDAD)){
			Novedad beanNovedad = beanComentarioNoticia.getBeanNoticia().getBeanNovedad();
			beanNotificacion.setBeanNovedad(beanNovedad);
			beanNotificacion.setCodeNovedad(beanNovedad.getCodeNovedad());			
			Colonia beanColonia= beanComentarioNoticia.getBeanNoticia().getBeanColonia();
			beanNotificacion.setCodeColonia(beanColonia.getCodeColonia());
		}
		
		beanNotificacion.setEstadoTarea(P.PENDIENTE);
		beanNotificacion.setIsPersistente(true);			
		TipoNotificacion beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean(codeTipoNotificacion);
		if(beanTipoNotificacion==null){
			throw new UnknownException("No se encontro el TipoNotificacion ");
		}
		beanNotificacion.setComentarioNoticia(beanComentarioNoticia.getComentario());
		beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
		beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());			
		beanNotificacion.setVersion(new java.util.Date().getTime());					
		beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
		beanParametro = new BeanParametro();											
		beanParametro.setBean(beanNotificacion);
		beanParametro.setTipoOperacion(P.INSERTAR);		
		Notificacion beanNotificacionBd= (Notificacion)logicNotificacion.mantenimientoReturn(beanParametro);
		return pm.detachCopy(beanNotificacionBd);
}
	
	public static Boolean queueNotificationComentar(String codeNotificacion) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);
			LogicNotificacion logicNotificacion= new LogicNotificacion(pm);		
			
			Notificacion beanNotificacion= (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);		
			UsuarioTurista beanTuristaPublica=beanNotificacion.getBeanTuristaPublicaNoticia();
			UsuarioTurista beanTuristaGeneraNotificacion= beanNotificacion.getBeanTuristaGeneraNotificacion();
			StringBuilder msjTuristaNoticia = new StringBuilder();
			StringBuilder msjTuristaNotificacion = new StringBuilder();
			StringBuilder msjTuristaPublicaGenera = new StringBuilder();	
			Set<UsuarioTurista> queueUsuarios= new HashSet<UsuarioTurista>();	
			queueUsuarios= queueNotificacionComentario(beanNotificacion, pm);
			msjTuristaNoticia.append(beanTuristaGeneraNotificacion.getNombre());	
			msjTuristaNotificacion.append(beanTuristaGeneraNotificacion.getNombre());
			msjTuristaPublicaGenera.append(beanTuristaGeneraNotificacion.getNombre());
			TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
			if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMENTARIO_CONQUISTA_RELACIONADO)  ||
					beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMENTARIO_CONQUISTA) ||
					beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMENTARIO_NOVEDAD)||
					beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMENTARIO_NOVEDAD_RELACIONADA)){
				String concat="Novedad";
				if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMENTARIO_CONQUISTA)){
					concat="Conquista";
				}
				msjTuristaNoticia.append(" ha comentado tu ".concat(concat));		
				msjTuristaNotificacion.append(" ha comentado la ".concat(concat).concat(" de "));	
				msjTuristaPublicaGenera.append(" ha comentado su ".concat(concat).concat(" "));
			}
			List<NotificacionTurista> listParamNotificacionTurista= new ArrayList<NotificacionTurista>();	
			msjTuristaNotificacion.append(beanNotificacion.getBeanTuristaPublicaNoticia().getNombre());
			Iterator<UsuarioTurista> listQueueUsuarioIterator=queueUsuarios.iterator(); 			
			while(listQueueUsuarioIterator.hasNext()){
				UsuarioTurista beanTuristaRecibeNotificacion= listQueueUsuarioIterator.next();
				NotificacionTurista beanNotificacionTuristaInteractua= new NotificacionTurista();
				String codeNotificacionTuristaInteractua=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
						(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));
				beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);						
				beanNotificacionTuristaInteractua.setBeanTuristaPublica(beanNotificacion.getBeanTuristaPublicaNoticia());
				//beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);				
				beanNotificacionTuristaInteractua.setCodeTuristaPublica(beanNotificacion.getCodeTuristaPublicaNoticia());									
				if(beanNotificacion.getCodeTipoNotificacion().equalsIgnoreCase(P.COMENTARIO_CONQUISTA)){
					beanNotificacionTuristaInteractua.setBeanConquista(beanNotificacion.getBeanConquista());
					beanNotificacionTuristaInteractua.setCodeConquista(beanNotificacion.getCodeConquista());
					beanNotificacionTuristaInteractua.setBeanDestino(beanNotificacion.getBeanDestinoConquistadoDescubierto());
					beanNotificacionTuristaInteractua.setCodeDestino(beanNotificacion.getCodeDestinoConquistadoDescubierto());
					beanNotificacionTuristaInteractua.setNombreDestinoNegocioConquistado(beanNotificacion.getBeanDestinoConquistadoDescubierto().getNombre());
				}
				if(beanNotificacion.getCodeTipoNotificacion().equalsIgnoreCase(P.COMENTARIO_NOVEDAD)){
					beanNotificacionTuristaInteractua.setBeanNovedad(beanNotificacion.getBeanNovedad());
					beanNotificacionTuristaInteractua.setCodeNovedad(beanNotificacion.getCodeNovedad());
					beanNotificacionTuristaInteractua.setCodeColonia(beanNotificacion.getCodeColonia());
					beanNotificacionTuristaInteractua.setBeanColonia(beanNotificacion.getBeanNoticia().getBeanColonia());
				}				
				beanNotificacionTuristaInteractua.setIsPersistente(true);
				beanNotificacionTuristaInteractua.setVersion(new java.util.Date().getTime());
				beanNotificacionTuristaInteractua.setBeanTuristaGeneraNotificacion(beanTuristaGeneraNotificacion);
				beanNotificacionTuristaInteractua.setCodeTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getCodeUsuarioTurista());
				beanNotificacionTuristaInteractua.setNombreTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getNombre());
				beanNotificacionTuristaInteractua.setApellidoTuristaGeneraNotificacion(beanTuristaGeneraNotificacion.getApellido());
				beanNotificacionTuristaInteractua.setFotoPerfilTuristaNegocioGeneraNotificacion(beanTuristaGeneraNotificacion.getFotoPerfil());
				beanNotificacionTuristaInteractua.setApellidoTuristaPublica(beanNotificacion.getBeanTuristaPublicaNoticia().getApellido());
				beanNotificacionTuristaInteractua.setNombreTuristaPublica((beanNotificacion.getBeanTuristaPublicaNoticia().getNombre()));
				beanNotificacionTuristaInteractua.setFotoPerfilTuristaPublica((beanNotificacion.getBeanTuristaPublicaNoticia().getFotoPerfil()));			
				beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNotificacion.toString());			
				if(!beanTuristaGeneraNotificacion.getCodeUsuarioTurista().equals(beanTuristaPublica.getCodeUsuarioTurista())){
					if(beanTuristaRecibeNotificacion.getCodeUsuarioTurista().equals(beanTuristaPublica.getCodeUsuarioTurista())){
						beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaNoticia.toString());										
					}					
				}else{
					beanNotificacionTuristaInteractua.setMensajeNotificacion(msjTuristaPublicaGenera.toString());
				}									
				beanNotificacionTuristaInteractua.setBeanNoticia(beanNotificacion.getBeanNoticia());
				beanNotificacionTuristaInteractua.setCodeComentarioNoticia(beanNotificacion.getCodeComentarioNoticia());
				beanNotificacionTuristaInteractua.setCodeNoticia(beanNotificacion.getCodeNoticia());
				beanNotificacionTuristaInteractua.setBeanNotificacion(beanNotificacion);
				beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
				beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
				beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
				beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
				beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
				beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacion.getFechaGeneroNotificacion());
				beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
				beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);				
				listParamNotificacionTurista.add(beanNotificacionTuristaInteractua);
				if(beanNotificacionTuristaInteractua.getTokenFirebase()!=null && 
						!beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()){
				NotificationMessage not=new NotificationMessage();
				not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
				not.setOptionRestrictedPackageName("com.indiant");
		    	not.setOptionPriority(10);  	   
		    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
		        payLoad.setSound("default");
		        DataPayLoad dataPayLoad=new DataPayLoad();
		    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
		    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTipoNotificacion():"");
		    	dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia()!=null?beanNotificacionTuristaInteractua.getCodeNoticia():"");
		    	dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino()!=null?beanNotificacionTuristaInteractua.getCodeDestino():"");
		    	dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia()!=null?beanNotificacionTuristaInteractua.getCodeColonia():"");
		    	dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia()!=null?beanNotificacionTuristaInteractua.getNombreColonia():"");
		    	dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
		    	dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad():"":"");
		    	dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion():"");
		    	dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
		    	payLoad.setTitle("Indiant");
		    	payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());		    			    	
		    	not.setPayLoadData(dataPayLoad.buildPayLoad());
		    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
		    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
		    	cnx.sendNotificationHttp(not);
				}
			}		
			if(listParamNotificacionTurista.size()>0){
				Collection<BeanParametro> listParametros=new ArrayList<BeanParametro>();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(listParamNotificacionTurista);
				parametro.setTipoOperacion(P.INSERTAR);
				listParametros.add(parametro);
				Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(listParametros);
				pm.close();
				return rptaNotificacionTurista;	
			}
			return true;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		} 
	}	
	
	public static Set<UsuarioTurista> queueNotificacionComentario(Notificacion beanNotificacion,PersistenceManager pm) throws UnknownException{
		LogicComentarioNoticia logicComentarioNoticia=new LogicComentarioNoticia(pm);
		LogicComparteNoticia logicComparteNoticia= new LogicComparteNoticia(pm);
		LogicDivulgacionNoticia logicDivulgacionNoticia= new LogicDivulgacionNoticia(pm);	
		String codeNoticia= beanNotificacion.getCodeNoticia();		
		UsuarioTurista beanUsuarioTuristaPublica= beanNotificacion.getBeanTuristaPublicaNoticia();
		UsuarioTurista beanUsuarioTuristaGeneraNotificacion= beanNotificacion.getBeanTuristaGeneraNotificacion();
		Set<UsuarioTurista>queueUsuarios=new HashSet<UsuarioTurista>();		
		if(!beanUsuarioTuristaGeneraNotificacion.getCodeUsuarioTurista().equals(beanUsuarioTuristaPublica.getCodeUsuarioTurista())){
			queueUsuarios.add(beanUsuarioTuristaPublica);	
		}			
			List<ComentarioNoticia> listComentarioNoticia= (List<ComentarioNoticia>) logicComentarioNoticia.getListBean(codeNoticia);
			if(listComentarioNoticia!=null){
				Iterator<ComentarioNoticia> listComentarioIterator= listComentarioNoticia.iterator();
				while (listComentarioIterator.hasNext()){		
					queueUsuarios.add(listComentarioIterator.next().getBeanTuristaComenta());			
				}			
			}
			List<ComparteNoticia> listComparteNoticia= (List<ComparteNoticia>) logicComparteNoticia.getListBean(codeNoticia);		
			if(listComparteNoticia!=null){
				Iterator<ComparteNoticia> listComparteIterator= listComparteNoticia.iterator();
				while (listComparteIterator.hasNext()){		
					queueUsuarios.add(listComparteIterator.next().getBeanTuristaComparte());			
				}
			}						
			List<DivulgacionNoticia> listDivulgacionNoticia= (List<DivulgacionNoticia>) logicDivulgacionNoticia.getListBean(codeNoticia);
			if(listDivulgacionNoticia!=null){
				Iterator<DivulgacionNoticia> listDivulgacionIterator= listDivulgacionNoticia.iterator();
				while (listDivulgacionIterator.hasNext()){		
					queueUsuarios.add(listDivulgacionIterator.next().getBeanTuristaDivulga());			
				}
			}
			if(queueUsuarios.contains(beanUsuarioTuristaGeneraNotificacion)){
				queueUsuarios.remove(beanUsuarioTuristaGeneraNotificacion);
			}			
		return queueUsuarios;	
	}
	
	private static TipoNotificacion obtenerTipoNotificacion(List<TipoNotificacion> lista,String codeTipoNotificacion){
		java.util.Iterator<TipoNotificacion> iterador=lista.iterator();
		while(iterador.hasNext()){
			TipoNotificacion bean=iterador.next();
			if(bean.getCodeTipoNotificacion().equalsIgnoreCase(codeTipoNotificacion)){
				return bean;
			}
		}
		return null;
	}
	
	private static CuentaTurista verificarClaveTurista(String correoTurista, String claveAnterior,PersistenceManager pm) throws Exception{		
		LogicCuentaTurista logicCuentaTurista= new LogicCuentaTurista(pm);
		CuentaTurista beanCuentaTurista=(CuentaTurista)logicCuentaTurista.getBeanByEmail(correoTurista);
		if(beanCuentaTurista.getTipo().equalsIgnoreCase(P.OAUTH)){
			throw new UnknownException("Usted se registro con una red social");
		}
		if (beanCuentaTurista.getClave().equals(AESencrypt.encrypt(claveAnterior))){
			return beanCuentaTurista;
		}
		return null;
	}
	
	public static List<Pais> listPaisesCuenta(Integer limiteMostrar) throws UnknownException{
		PersistenceManager pm=PMF.getPMF().getPersistenceManager();
		try{
			LogicPais logic=new LogicPais(pm);
			return logic.getListarBean(limiteMostrar, "ADMINISTRADOR");
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());		
		}finally{
			GestionShared.closeConnection(pm, null);
		}	
	}	
}
