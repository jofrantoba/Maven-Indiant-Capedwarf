package com.indiana.server.model.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.indiana.server.model.bean.Amistad;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.ControlPosicion;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.DetalleEmpatia;
import com.indiana.server.model.bean.DetalleInteres;
import com.indiana.server.model.bean.Empatia;
import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.bean.MensajeColoniaPrivado;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.bean.NotificacionTurista;
import com.indiana.server.model.bean.Novedad;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.bean.ParticipanteChatMiembro;
import com.indiana.server.model.bean.SugerenciaColonia;
import com.indiana.server.model.bean.TablonColonia;
import com.indiana.server.model.bean.TipoEmpatia;
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.TuristaInteresEmpatia;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicAmistad;
import com.indiana.server.model.logic.LogicColonia;
import com.indiana.server.model.logic.LogicColoniaInteres;
import com.indiana.server.model.logic.LogicControlPosicion;
import com.indiana.server.model.logic.LogicDetalleEmpatia;
import com.indiana.server.model.logic.LogicDetalleInteres;
import com.indiana.server.model.logic.LogicEmpatia;
import com.indiana.server.model.logic.LogicInteres;
import com.indiana.server.model.logic.LogicMensajeColoniaPrivado;
import com.indiana.server.model.logic.LogicMiembro;
import com.indiana.server.model.logic.LogicMiembroInteres;
import com.indiana.server.model.logic.LogicMuroNoticia;
import com.indiana.server.model.logic.LogicNoticia;
import com.indiana.server.model.logic.LogicNotificacion;
import com.indiana.server.model.logic.LogicNotificacionTurista;
import com.indiana.server.model.logic.LogicNovedad;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicParticipanteChatMiembro;
import com.indiana.server.model.logic.LogicSugerenciaColonia;
import com.indiana.server.model.logic.LogicTablonColonia;
import com.indiana.server.model.logic.LogicTipoEmpatia;
import com.indiana.server.model.logic.LogicTipoMovimiento;
import com.indiana.server.model.logic.LogicTipoNotificacion;
import com.indiana.server.model.logic.LogicTuristaInteres;
import com.indiana.server.model.logic.LogicTuristaInteresEmpatia;
import com.indiana.server.model.logic.LogicUsuarioTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.P;
import com.indiana.shared.StringHex;
import com.indiana.shared.UnknownException;
import com.jofrantoba.fcm.constants.EnumContentType;
import com.jofrantoba.fcm.entity.AndroidNotificationPayLoad;
import com.jofrantoba.fcm.entity.DataPayLoad;
import com.jofrantoba.fcm.entity.NotificationMessage;
import com.jofrantoba.fcm.http.HttpFcmConection;
import com.jofrantoba.fcm.xmpp.XmppFcmNotification;

public class GestionMiembro {
	
	public static Boolean queueNotificationChatColonia(String codeTablonColonia){
		PersistenceManager pm = null;
		try {					
			pm = PMF.getPMF().getPersistenceManager();
			LogicTablonColonia logic=new LogicTablonColonia(pm);
			TablonColonia bean=(TablonColonia)logic.getBean(codeTablonColonia);
			List<Miembro> lista=chatMiembros(bean.getCodeColonia());
			Iterator<Miembro> iterador=lista.iterator();
			XmppFcmNotification xmpp=new XmppFcmNotification(P.PROJECTID,P.FIREBASE_SERVER_KEY);
			xmpp.initXmppProduction(false);
			while(iterador.hasNext()){
				Miembro miembro=iterador.next();
				if(miembro.getTokenFirebase().equals(bean.getTokenFirebase()) || miembro.getTokenFirebase().isEmpty() ||miembro.getTokenFirebase()==null ){
					continue;
				}
				NotificationMessage not=new NotificationMessage();				
				not.setTargetTo(miembro.getTokenFirebase());
				not.setOptionMessageId(java.util.UUID.randomUUID().toString());
				not.setOptionRestrictedPackageName("com.indiant");
		    	not.setOptionPriority(10);    	
		    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();
		    	payLoad.setTitle("Indiant");
		    	payLoad.setBody(bean.getMensaje());
		    	payLoad.setColor("#ffffff");
		    	payLoad.setSound("default");
		    	DataPayLoad dataPayLoad=new DataPayLoad();
		    	dataPayLoad.add("codeTablonColonia", bean.getCodeTablonColonia());
		    	dataPayLoad.add("mensaje", bean.getMensaje());
		    	dataPayLoad.add("linkFotoMensaje", bean.getLinkFotoMensaje());
		    	dataPayLoad.add("codeMiembro", bean.getCodeMiembro());
		    	dataPayLoad.add("codeTuristaTablon", bean.getCodeTuristaTablon());
		    	dataPayLoad.add("nombreTuristaTablon", bean.getNombreTuristaTablon());
		    	dataPayLoad.add("apellidoTuristaTablon", bean.getApellidoTuristaTablon());
		    	dataPayLoad.add("fotoPerfilTuristaTablon", bean.getFotoPerfilTuristaTablon());
		    	dataPayLoad.add("codeColonia", bean.getCodeColonia());
		    	dataPayLoad.add("nombreColonia", bean.getNombreColonia());
		    	dataPayLoad.add("fechaPublicacionTablon", bean.getFechaPublicacionTablon());
		    	dataPayLoad.add("estadoTablon", bean.getEstadoTablon());
		    	dataPayLoad.add("numeroParticipante", bean.getNumeroParticipante());
		    	not.setPayLoadData(dataPayLoad.buildPayLoad());
		    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());		    								    	
		    	xmpp.sendNotificationXmpp(not);
			}			
		return true;
		}catch(Exception ex){		
			return false;
		}finally{
			GestionShared.closeConnection(pm, null);
		} 
	}
	
	public static Integer changeEstadoChatColonia(String codeColonia,String correoTurista,Integer estadoChatColonia,String tokenFirebase,String nombrePais,String nombreRegion) throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx=null;
		try {					
				pm = PMF.getPMF().getPersistenceManager();				
				tx=pm.currentTransaction();
				tx.begin();
				LogicMiembro logicMiembro= new LogicMiembro(pm);
		Miembro beanMiembro=logicMiembro.verPerfilMiembro(correoTurista, codeColonia);
		if(beanMiembro==null){
			throw new UnknownException(P.NO_ES_MIEMBRO);
		}
		beanMiembro.setEstadoChatColonia(estadoChatColonia);
		beanMiembro.setNombrePais(nombrePais);
		beanMiembro.setNombreRegion(nombreRegion);
		beanMiembro.setTokenFirebase(tokenFirebase);
		BeanParametro beanParametro=new BeanParametro();
		beanParametro.setTipoOperacion("I");
		beanParametro.setBean(beanMiembro);
		logicMiembro.mantenimientoReturn(beanParametro);
		tx.commit();
		pm.close();	
		return estadoChatColonia;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		} 
		
	}
	/**
	 * METHOD: PUBLICAR NOVEDAD COLONIA
	 * CODIGO: KCYS-154
	 * CUS: CUS_PUBLICAR_NOVEDAD_COLONIA - ANS_TURISTA
	 * ESTADO : TESTEADO
	 *
	 * @param codeColonia
	 * @param correoTurista
	 * @param fotoPublicidad
	 * @param enlace
	 * @param descripcion
	 * @return
	 * @throws UnknownException
	 */
	public static Noticia publicarNovedadColonia(String codeColonia,String correoTurista,String fotoPublicidad,
			String hiperLink,String descripcion) throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx=null;
		try {					
				pm = PMF.getPMF().getPersistenceManager();				
				tx=pm.currentTransaction();
				tx.begin();	
				LogicMiembro logicMiembro= new LogicMiembro(pm);
				LogicColonia logicColonia= new LogicColonia(pm);
				LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
				LogicNovedad logicNovedad= new LogicNovedad(pm);
				
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
					throw new UnknownException(P.NO_ES_MIEMBRO);
				}				
				Novedad beanNovedad = new Novedad();
				String codeNovedad= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanNovedad.setIdNovedad(KeyFactory.keyToString(KeyFactory.createKey(Novedad.class.getSimpleName(), codeNovedad)));	
				beanNovedad.setCodeNovedad(codeNovedad);
				beanNovedad.setBeanMiembroGeneraNovedad(beanMiembro);
				beanNovedad.setCodeMiembroGeneraNovedad(beanMiembro.getCodeMiembro());
				beanNovedad.setBeanTuristaGeneraNovedad(beanUsuarioTurista);
				beanNovedad.setCodeTuristaGeneraNovedad(beanUsuarioTurista.getCodeUsuarioTurista());
				beanNovedad.setApellidoTuristaGeneraNovedad(beanUsuarioTurista.getApellido());
				StringBuilder turista=new StringBuilder();
				turista.append(beanUsuarioTurista.getNombre()!=null?beanUsuarioTurista.getNombre():"MARCO");
				turista.append(" ");
				turista.append(beanUsuarioTurista.getApellido()!=null?beanUsuarioTurista.getApellido():"POLO");
				beanNovedad.setNombreTuristaNegocioGeneraNovedad(turista.toString());
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
				beanNovedad.setTipoNovedad("NC");				
				BeanParametro beanParametro= new BeanParametro();
				beanParametro.setBean(beanNovedad);
				beanParametro.setTipoOperacion(P.INSERTAR);
				
				Boolean rptaNovedad= logicNovedad.mantenimiento(beanParametro);
				if(!rptaNovedad){
					throw new UnknownException("No se guardo la Novedad ");
				}
					MuroNoticia beanMuroNoticia= generaNoticia(beanNovedad, beanParametro, pm);
					if(beanMuroNoticia==null){
						throw new UnknownException("No se guardo la Noticia ");
					}
					Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
					queueMuroNoticia.add(TaskOptions.Builder
				            .withUrl("/taskMuroNoticiaNovedadColonia")
				            .param("codeNoticia", beanMuroNoticia.getCodeNoticia()).header("Host", P.HOST));				            
				            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           							
					
					Notificacion beanNotificacion = generarNotificacionNovedad(beanMuroNoticia, beanParametro, pm);
					
					if(beanNotificacion==null){
						throw new UnknownException("No se guardo la Notificacion ");
					}
					Queue queue = QueueFactory.getQueue("notificacion-queuepush");
					queue.add(TaskOptions.Builder
				            .withUrl("/taskNotificacionNovedadColonia")
				            .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));				            
				            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           							
					tx.commit();
					pm.close();		
					Noticia beanNoticia=beanMuroNoticia.getBeanNoticia();
					beanNoticia.setBeanColonia(null);
					beanNoticia.setBeanMiembro(null);					
					beanNoticia.setListMuroNoticia(null);					
					beanNoticia.setEnlace(beanColonia.getNombreColonia());
					beanNoticia.setNombreColoniaNegocioDestino(beanColonia.getNombreColonia());
					beanNoticia.setListDivulgacionNoticia(null);
					beanNoticia.getBeanTuristaGeneraNoticia().setListTuristaIntereses(null);
					beanNoticia.setBeanTuristaGeneraNoticia(null);					
					beanNoticia.setBeanNovedad(null);
					return beanNoticia;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		} 
	}
	
	/**
	 * METHOD: VER MIEMBROS COLONIA 
	 * CODIGO: KCYS-157
	 * CUS: CUS_VER_MIEMBROS_COLONIA - ANS_TURISTA
	 * ESTADO : POR TESTEAR
	 * 
	 * @param correoTurista
	 * @param codeNoticia
	 * @param comentario
	 * @return
	 * @throws UnknownException
	 * 
	 */
	
	public static List<Miembro> verMiembrosColonia(String codeColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();							
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			Colonia beanColonia= (Colonia) logicColonia.getBeanByCode(codeColonia);
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Colonia.class));
			}
			List<Miembro> listMiembros= (List<Miembro>) logicMiembro.getListarBeanByColonia(codeColonia);
			return listMiembros;						
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 *  Sin Terminar.
	 * METHOD: VER MIEMBROS COLONIA 
	 * CODIGO: KCYS-157
	 * CUS: CUS_VER_MIEMBROS_COLONIA - ANS_TURISTA
	 * ESTADO : SIN TERMINAR.
	 * 
	 * @param correoTurista
	 * @param codeNoticia
	 * @param comentario
	 * @return
	 * @throws UnknownException
	 * 
	 */
	
	public static List<Miembro> chatMiembros(String codeColonia) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			List<Miembro> listMiembros= (List<Miembro>)logicMiembro.getListarBeanByChatColonia(codeColonia);			
			return listMiembros;						
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * 
	 * @param codeColonia
	 * @return
	 * @throws UnknownException
	 */
	public static List<TablonColonia> listarTablonesColonia(String codeColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicTablonColonia logicTablonColonia= new LogicTablonColonia(pm);		
			LogicParametrosSistema logicParamatroSistema= new LogicParametrosSistema(pm);
			//FHM	> FILTRO HORA TABLON COLONIA ; 24 horas
			String codeParametro= "FHTC";
			ParametrosSistema  beanParametroSistema= (ParametrosSistema) logicParamatroSistema.getBean(codeParametro);
			if(beanParametroSistema==null){
				throw new UnknownException("No se encontro el parametro  ".concat(codeParametro));
			}
			Integer hours=-1;
			try{
				hours= Integer.parseInt(beanParametroSistema.getValor());
			}catch(Exception ex){
				throw new UnknownException("No se pudo convertir a entero el valor de horas");
			}				
			Calendar beanGregorianCalendar = GregorianCalendar.getInstance();
			beanGregorianCalendar.add(Calendar.HOUR, ((-1)*hours));
			Long rangoFecha=beanGregorianCalendar.getTime().getTime();
			List<TablonColonia>listMensajesColonia= logicTablonColonia.getListarBeanByColoniaRangoFecha(codeColonia,P.ABIERTO,rangoFecha);
			if(listMensajesColonia.isEmpty()){
//				Si esta vacio mostramos los 10 ultimos mensajes que se encuentren activos en el tablon de la colonia.
				listMensajesColonia=logicTablonColonia.getListarBeanByColonia(codeColonia,P.ABIERTO, 10);// el 10 deberia ser como parametro sistema.
			}
			return listMensajesColonia;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	
	/**
	 * Descripcion: Lista a los Miembros Conectados( Miembros que se encuentran dentro del Tab Colonia)
	 * @param codeColonia -- Codigo de la Colonia para listar Miembros Conectados.
	 * @param correoTurista -- Permite tener el correo del Turista que entra a su colonia para no tener en cuenta en la lista de conectados para el.
	 * @return
	 * @throws UnknownException
	 */
	public static List<Miembro> cargarSesionUsuarioTurista(String codeColonia,String correoTurista) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();	
			
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			
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
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}			
			List<Miembro> listMiembrosConectados= (List<Miembro>) logicMiembro.getListarBeanByChatColonia(codeColonia);			
			if(listMiembrosConectados.contains(beanMiembro)){
				listMiembrosConectados.remove(beanMiembro);
			}
			return listMiembrosConectados;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * Descripcion: Actualiza el estado de Conexion del Miembro de la Colonia.
	 * @param codeColonia
	 * @param correoTurista
	 * @param conectado
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean actualizarEstadoSesionTuristaColonia(String codeColonia,String correoTurista,Boolean conectado) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();		
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
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
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}						
			beanMiembro.setEstadoChatColonia((conectado)?1:0);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanMiembro);
			beanParametro.setTipoOperacion(P.INSERTAR);
			Boolean rptaMiembro= logicMiembro.mantenimiento(beanParametro);
			if(!rptaMiembro){
				return false;
			}
			tx.commit();
			pm.close();
			return true;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * KCYS-160: DCS_CUS_INCIAR_CHAT_COLONIA
	 * ESTADO: ?
	 * 
	 * @param codeColonia
	 * @param correoTurista
	 * @param mensaje
	 * @param linkFotoMensaje
	 * @param tokenFirebase : Revisar este parametro
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean lanzarTablonColonia(String codeColonia,String correoTurista,String mensaje,
			String linkFotoMensaje,String linkVideoMensaje,String tokenFirebase) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();
			LogicTablonColonia logicTablonColonia= new LogicTablonColonia(pm);			
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicNovedad logicNovedad= new LogicNovedad(pm);
			
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
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}
			TablonColonia beanTablonColonia= new TablonColonia();
			String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString();
			String codeTablon=StringHex.convertStringToHex(codigoAleatorioGenerico);
			Key keyNoticia=KeyFactory.createKey(TablonColonia.class.getSimpleName(), codeTablon);
			beanTablonColonia.setIdTablonColonia(KeyFactory.keyToString(keyNoticia));
			beanTablonColonia.setCodeTablonColonia(codeTablon);
			beanTablonColonia.setBeanMiembro(beanMiembro);
			beanTablonColonia.setCodeMiembro(beanMiembro.getCodeMiembro());
			beanTablonColonia.setBeanColonia(beanColonia);
			beanTablonColonia.setCodeColonia(beanColonia.getCodeColonia());
			beanTablonColonia.setBeanTuristaTablon(beanUsuarioTurista);
			beanTablonColonia.setApellidoTuristaTablon(beanUsuarioTurista.getApellido());
			beanTablonColonia.setNombreColonia(beanColonia.getNombreColonia());
			beanTablonColonia.setNombreTuristaTablon(beanUsuarioTurista.getNombre());
			beanTablonColonia.setFotoPerfilTuristaTablon(beanUsuarioTurista.getFotoPerfil());
			beanTablonColonia.setMensaje(mensaje);
			beanTablonColonia.setFechaPublicacionTablon(new java.util.Date());
			beanTablonColonia.setIsPersistente(true);
			beanTablonColonia.setVersion(new java.util.Date().getTime());
			beanTablonColonia.setEstadoTablon(P.ABIERTO);
			beanTablonColonia.setNumeroParticipante(0);
			beanTablonColonia.setTokenFirebase(tokenFirebase);
			beanTablonColonia.setLinkFotoMensaje(linkFotoMensaje);
			beanTablonColonia.setLinkVideoMensaje(linkVideoMensaje);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanTablonColonia);
			beanParametro.setTipoOperacion(P.INSERTAR);
			Boolean rptaTabloColonia= logicTablonColonia.mantenimiento(beanParametro);
			if(!rptaTabloColonia){
				return false;
			}	
			
			Novedad beanNovedad = new Novedad();
			String codeNovedad= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanNovedad.setIdNovedad(KeyFactory.keyToString(KeyFactory.createKey(Novedad.class.getSimpleName(), codeNovedad)));	
			beanNovedad.setCodeNovedad(codeNovedad);
			beanNovedad.setBeanMiembroGeneraNovedad(beanMiembro);
			beanNovedad.setCodeMiembroGeneraNovedad(beanMiembro.getCodeMiembro());
			beanNovedad.setBeanTuristaGeneraNovedad(beanUsuarioTurista);
			beanNovedad.setCodeTuristaGeneraNovedad(beanUsuarioTurista.getCodeUsuarioTurista());
			beanNovedad.setApellidoTuristaGeneraNovedad(beanUsuarioTurista.getApellido());
			StringBuilder turista=new StringBuilder();
			turista.append(beanUsuarioTurista.getNombre()!=null?beanUsuarioTurista.getNombre():"MARCO");
			turista.append(" ");
			turista.append(beanUsuarioTurista.getApellido()!=null?beanUsuarioTurista.getApellido():"POLO");
			beanNovedad.setNombreTuristaNegocioGeneraNovedad(turista.toString());
			beanNovedad.setFotoPerfilTuristaNegocioGeneraNovedad(beanUsuarioTurista.getFotoPerfil());
			beanNovedad.setFotoPublicidad(linkFotoMensaje);
			beanNovedad.setBeanColonia(beanColonia);
			beanNovedad.setCodeColonia(beanColonia.getCodeColonia());
			beanNovedad.setIsPersistente(true);
			beanNovedad.setVersion(new java.util.Date().getTime());
			beanNovedad.setDescripcion(mensaje);
			beanNovedad.setHiperLink(linkVideoMensaje);						
			beanNovedad.setFechaPublicacion(new java.util.Date());
			beanNovedad.setEnlace(beanColonia.getNombreColonia());
			beanNovedad.setTipoNovedad("NC");				
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanNovedad);
			beanParametro.setTipoOperacion(P.INSERTAR);
			
			Boolean rptaNovedad= logicNovedad.mantenimiento(beanParametro);
			if(!rptaNovedad){
				throw new UnknownException("No se guardo la Novedad ");
			}
				MuroNoticia beanMuroNoticia= generaNoticia(beanNovedad, beanParametro, pm);
				if(beanMuroNoticia==null){
					throw new UnknownException("No se guardo la Noticia ");
				}
			
			Queue queueMuroNoticia = QueueFactory.getQueue("tabloncolonia-queuepush");
			queueMuroNoticia.add(TaskOptions.Builder
		            .withUrl("/TaskMuroNoticiaTablonColonia")
		            .param("codeNoticia", beanMuroNoticia.getCodeNoticia()).header("Host", P.HOST));				            
		            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           	
			
			Queue queue = QueueFactory.getQueue("chatcolonia-queuepush");
			queue.add(TaskOptions.Builder
		            .withUrl("/taskNotificacionChatColonia")
		            .param("codeTablonColonia", beanTablonColonia.getCodeTablonColonia()).header("Host", P.HOST));
		            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           								
			
			tx.commit();
			pm.close();
			return true;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * Descripcion: Este metodo se ejecuta cuando el Miembro le da click en Responder del Tablon. Se convierte en Participante.
	 * @param codeColonia
	 * @param correoTurista
	 * @param codeTablonColonia
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean entrarChatTablonColonia(
			String codeColonia,
			String correoTurista,
			String codeTablonColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();				
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicTablonColonia logicTablonColonia= new LogicTablonColonia(pm);
			LogicParticipanteChatMiembro logicParticipanteChatMiembro= new LogicParticipanteChatMiembro(pm);
			LogicEmpatia logicEmpatia= new LogicEmpatia(pm);
			LogicTipoEmpatia logicTipoEmpatia= new LogicTipoEmpatia(pm);
			LogicDetalleEmpatia logicDetalleEmpatia= new LogicDetalleEmpatia(pm);
			
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			
			if(beanUsuarioTurista== null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Colonia beanColonia= (Colonia)logicColonia.getBeanByCode(codeColonia);
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Destino.class));
			}
			Miembro beanMiembro=logicMiembro.verPerfilMiembro(correoTurista, codeColonia);
			if(beanMiembro==null){
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}					
			TablonColonia beanTablonColonia= (TablonColonia)logicTablonColonia.getBeanByCode(codeTablonColonia);
			if(beanTablonColonia==null){
				throw new UnknownException(P.errorNoExistencia(Destino.class));
			}	
			
			UsuarioTurista beanUsuarioTuristaLanzaTablon= logicUsuarioTurista.getBeanByEmail(beanTablonColonia.getCodeTuristaTablon());
			if(beanUsuarioTuristaLanzaTablon==null){
				throw new UnknownException("No existe Usuario creador de Tablon");
			}
			
			Miembro beanMiembroLanzaTablon= (Miembro) logicMiembro.verPerfilMiembro(
					beanUsuarioTuristaLanzaTablon.getCorreoTurista(), codeColonia);
			if(beanMiembroLanzaTablon==null){
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}	
			
			BeanParametro beanParametro= new BeanParametro();
			Boolean noEsParticipante=true;
			Integer cantidadParticipante=0;			
			if(beanTablonColonia.getNumeroParticipante()==0){				
				// como no existe ninguno entonces se agrega en este caso al que lanza el tablon
				ParticipanteChatMiembro beanParticipanteChatMiembroLanzaTablon = new ParticipanteChatMiembro();
				String codeParticipanteChatMiembro= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());		
				beanParticipanteChatMiembroLanzaTablon.setIdParticipanteChatMiembro(KeyFactory.keyToString(KeyFactory.createKey(ParticipanteChatMiembro.class.getSimpleName(), codeParticipanteChatMiembro)));
				beanParticipanteChatMiembroLanzaTablon.setCodeParticipanteChatMiembro(codeParticipanteChatMiembro);			
				beanParticipanteChatMiembroLanzaTablon.setApellidoTuristaParticipante(beanUsuarioTuristaLanzaTablon.getApellido());				
				beanParticipanteChatMiembroLanzaTablon.setCodeMiembro(beanMiembroLanzaTablon.getCodeMiembro());			
				beanParticipanteChatMiembroLanzaTablon.setCodeTablonColonia(codeTablonColonia);
				beanParticipanteChatMiembroLanzaTablon.setCodeTuristaParticipante(beanUsuarioTuristaLanzaTablon.getCorreoTurista());
				beanParticipanteChatMiembroLanzaTablon.setEstado(P.ACTIVO);
				beanParticipanteChatMiembroLanzaTablon.setEstadoParticipante(P.ACTIVO);
				beanParticipanteChatMiembroLanzaTablon.setFechaUnion(new java.util.Date());
				beanParticipanteChatMiembroLanzaTablon.setFotoPerfilTuristaParticipante(beanUsuarioTuristaLanzaTablon.getFotoPerfil());			
				beanParticipanteChatMiembroLanzaTablon.setIsPersistente(true);
				beanParticipanteChatMiembroLanzaTablon.setNombreTuristaParticipante(beanUsuarioTuristaLanzaTablon.getNombre());
				beanParticipanteChatMiembroLanzaTablon.setTipoChat(P.CHAT_COLONIA);
				beanParticipanteChatMiembroLanzaTablon.setTipoParticipante(P.GENERA);
				beanParticipanteChatMiembroLanzaTablon.setVersion(new java.util.Date().getTime());
				cantidadParticipante++;
				beanParametro= new BeanParametro();
				beanParametro.setBean(beanParticipanteChatMiembroLanzaTablon);
				beanParametro.setTipoOperacion(P.INSERTAR);
				
				Boolean rptParticipanteChatMiembroLanzaTablon= logicParticipanteChatMiembro.mantenimiento(beanParametro);
				if(!rptParticipanteChatMiembroLanzaTablon){
					throw new UnknownException(P.ERROR_OPERACION);
				}				
				
			}else{
				ParticipanteChatMiembro beanParticipanteChatMiembroVerificar= (ParticipanteChatMiembro) 
						logicParticipanteChatMiembro.getBeanByTuristaTablon(correoTurista, codeTablonColonia);	
				// En este caso verifico si el miembro ya era participante de la sala. por tanto si es null quiere decir
				//que noEsParticipante.
				if(beanParticipanteChatMiembroVerificar!=null){
					noEsParticipante=false;
				}
			}
			// Solo en la primera ves se va a ejecutar de manera independiente, ya que la variable noEsParticipante no ingresa
			// al else y no se modifica ya que como hay 0 participantes se tiene que agregar dos el que genera y el que responde
			//
			if(noEsParticipante){
				ParticipanteChatMiembro beanParticipanteChatMiembro = new ParticipanteChatMiembro();
				String codeParticipanteChatMiembro= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());		
				beanParticipanteChatMiembro.setIdParticipanteChatMiembro(KeyFactory.keyToString(KeyFactory.createKey(ParticipanteChatMiembro.class.getSimpleName(), codeParticipanteChatMiembro)));
				beanParticipanteChatMiembro.setCodeParticipanteChatMiembro(codeParticipanteChatMiembro);			
				beanParticipanteChatMiembro.setApellidoTuristaParticipante(beanUsuarioTurista.getApellido());				
				beanParticipanteChatMiembro.setCodeMiembro(beanMiembro.getCodeMiembro());			
				beanParticipanteChatMiembro.setCodeTablonColonia(codeTablonColonia);
				beanParticipanteChatMiembro.setCodeTuristaParticipante(beanUsuarioTurista.getCorreoTurista());
				beanParticipanteChatMiembro.setEstado(P.ACTIVO);
				beanParticipanteChatMiembro.setEstadoParticipante(P.ACTIVO);
				beanParticipanteChatMiembro.setFechaUnion(new java.util.Date());
				beanParticipanteChatMiembro.setFotoPerfilTuristaParticipante(beanUsuarioTurista.getFotoPerfil());			
				beanParticipanteChatMiembro.setIsPersistente(true);
				beanParticipanteChatMiembro.setNombreTuristaParticipante(beanUsuarioTurista.getNombre());
				beanParticipanteChatMiembro.setTipoChat(P.CHAT_COLONIA);
				beanParticipanteChatMiembro.setTipoParticipante(P.RESPONDE);
				beanParticipanteChatMiembro.setVersion(new java.util.Date().getTime());
				cantidadParticipante++;
				beanParametro= new BeanParametro();
				beanParametro.setBean(beanParticipanteChatMiembro);
				beanParametro.setTipoOperacion(P.INSERTAR);
				
				Boolean rptParticipanteChatMiembro= logicParticipanteChatMiembro.mantenimiento(beanParametro);
				if(!rptParticipanteChatMiembro){
					throw new UnknownException(P.ERROR_OPERACION);
				}	
				beanParametro= new BeanParametro();
				Integer hashTuristaUno=beanUsuarioTuristaLanzaTablon.getCorreoTurista().hashCode();
				Integer hashTuristaDos=beanUsuarioTurista.getCorreoTurista().hashCode();
				String codigoUnicoEmpatia=hashTuristaUno.toString().concat("@").concat(hashTuristaDos.toString());
				/* --Existe empatia en ellos?? */
				
				Empatia beanEmpatia= (Empatia) logicEmpatia.getBeanByCodeTuristas(
						beanUsuarioTuristaLanzaTablon.getCorreoTurista(), beanUsuarioTurista.getCorreoTurista());
				
				if(beanEmpatia==null){
					beanEmpatia = new Empatia();
					String codeEmpatia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());		
					beanEmpatia.setIdEmpatia(KeyFactory.keyToString(KeyFactory.createKey(Empatia.class.getSimpleName(), codeEmpatia)));											    
					beanEmpatia.setCodeEmpatia(codeEmpatia);
					beanEmpatia.setCodeMiembroDos(beanMiembro.getCodeMiembro());
					beanEmpatia.setCodeMiembroUno(beanMiembroLanzaTablon.getCodeMiembro());
					beanEmpatia.setCodeTuristaDos(beanUsuarioTurista.getCorreoTurista());
					beanEmpatia.setCodeTuristaUno(beanUsuarioTuristaLanzaTablon.getCorreoTurista());
					beanEmpatia.setCodUnicoEmpatia(codigoUnicoEmpatia);
					beanEmpatia.setCodeColonia(codeColonia);
					beanEmpatia.setEstado(P.PENDIENTE);
					beanEmpatia.setFechaGenera(new java.util.Date());						
					beanEmpatia.setIsPersistente(true);					
					beanEmpatia.setTotalEmpatia(1);
					beanEmpatia.setVersion(new java.util.Date().getTime());															
					beanParametro.setBean(beanEmpatia);
					beanParametro.setTipoOperacion(P.INSERTAR);																			
				}else{
					beanEmpatia.setTotalEmpatia(beanEmpatia.getTotalEmpatia()+1);
					if(beanEmpatia.getTotalEmpatia()==5){
						beanEmpatia.setEstado(P.EJECUTADO);
						Queue queue = QueueFactory.getQueue("empatia-queuepush");
						queue.add(TaskOptions.Builder
					            .withUrl("/taskNotificacionEmpatia")
					            .param("codeEmpatia", beanEmpatia.getCodeEmpatia()).header("Host", P.HOST));				            
					            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           
									           
					}					
					beanParametro.setTipoOperacion(P.ACTUALIZAR);						
				}				
				Boolean rptaEmpatia=logicEmpatia.mantenimiento(beanParametro);
				if(!rptaEmpatia){
					throw new UnknownException(P.ERROR_OPERACION);
				}	
				
				TipoEmpatia beanTipoEmpatia= (TipoEmpatia) logicTipoEmpatia.getBeanByCode(P.EMPATIA_COMUNICACION);
				if(beanTipoEmpatia==null){
					throw new UnknownException("No se encontro el codigo de TipoEmpatia");
				}	
				
				DetalleEmpatia beanDetalleEmpatia= new DetalleEmpatia();
				String codeDetalleEmpatia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());		
				beanDetalleEmpatia.setIdDetalleEmpatia(KeyFactory.keyToString(KeyFactory.createKey(DetalleEmpatia.class.getSimpleName(), codeDetalleEmpatia)));											    					
				beanDetalleEmpatia.setCodeDetalleEmpatia(codeDetalleEmpatia);
				beanDetalleEmpatia.setCodeEmpatia(beanEmpatia.getCodeEmpatia());
				beanDetalleEmpatia.setCodeMiembroEmisor(beanMiembroLanzaTablon.getCodeMiembro());
				beanDetalleEmpatia.setCodeMiembroReceptor(beanMiembro.getCodeMiembro());
				beanDetalleEmpatia.setCodeTablonColonia(codeTablonColonia);
				beanDetalleEmpatia.setCodeTipoEmpatia(beanTipoEmpatia.getCodeTipoEmpatia());
				beanDetalleEmpatia.setCodeTuristaEmisor(beanUsuarioTuristaLanzaTablon.getCorreoTurista());
				beanDetalleEmpatia.setCodeTuristaReceptor(beanUsuarioTurista.getCorreoTurista());
				beanDetalleEmpatia.setCodigoUnicoEmpatia(codigoUnicoEmpatia);
				beanDetalleEmpatia.setEstado(beanEmpatia.getEstado());
				beanDetalleEmpatia.setFechaEmpatia(new java.util.Date());				
				beanDetalleEmpatia.setIsPersistente(true);					
				beanDetalleEmpatia.setVersion(new java.util.Date().getTime());
				
				beanParametro= new BeanParametro();
				beanParametro.setBean(beanDetalleEmpatia);
				beanParametro.setTipoOperacion(P.INSERTAR);
				
				Boolean rptaDetalleEmpatia=logicDetalleEmpatia.mantenimiento(beanParametro);
				if(!rptaDetalleEmpatia){
					throw new UnknownException(P.ERROR_OPERACION);
				}
			}
			
			Boolean rptaTablonColonia=logicTablonColonia.mantenimiento(beanParametro);
			if(!rptaTablonColonia){
				throw new UnknownException(P.ERROR_OPERACION);
			}												
			return true;								
					
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}	
	
	/**
	 * Descripcion: Actualiza el Estado del Participante del Tablon. Lo Retira o lo Expulsa.
	 * @param correoTurista
	 * @param codeTablonColonia
	 * @param estadoParticipante
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean actualizarEstadoParticipanteTablon(
			String correoTurista,
			String codeTablonColonia,
			String estadoParticipante) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();		
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicTablonColonia logicTablonColonia= new LogicTablonColonia(pm);
			LogicParticipanteChatMiembro logicParticipanteChatMiembro= new LogicParticipanteChatMiembro(pm);			
			
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			
			if(beanUsuarioTurista== null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}			
			
			TablonColonia beanTablonColonia= (TablonColonia)logicTablonColonia.getBeanByCode(codeTablonColonia);
			if(beanTablonColonia==null){
				throw new UnknownException(P.errorNoExistencia(TablonColonia.class));
			}							
			
			ParticipanteChatMiembro beanParticipanteChatMiembroVerificar= (ParticipanteChatMiembro) 
					logicParticipanteChatMiembro.getBeanByTuristaTablon(correoTurista, codeTablonColonia);	
			
			if(beanParticipanteChatMiembroVerificar==null){
				throw new UnknownException("Usuario no es Participante del Chat Grupal");
			}						
			/* --Verificamos el estado para actualizar segun sea el caso. */
			if(estadoParticipante.equalsIgnoreCase(P.ACTIVO)){
				if(beanParticipanteChatMiembroVerificar.getEstadoParticipante().equalsIgnoreCase(P.RETIRADO)){
					beanParticipanteChatMiembroVerificar.setEstadoParticipante(estadoParticipante);
				}
			}else{
				if(estadoParticipante.equalsIgnoreCase(P.RETIRADO)){
					if(beanParticipanteChatMiembroVerificar.getEstadoParticipante().equalsIgnoreCase(P.ACTIVO)){
						beanParticipanteChatMiembroVerificar.setEstadoParticipante(estadoParticipante);
					}
				}else{
					/* --Si el estado a actualizar es expulsado, para ello el estado del participante tiene que estar en modo Activo. */
					if(beanParticipanteChatMiembroVerificar.getEstadoParticipante().equalsIgnoreCase(P.ACTIVO)){
						beanParticipanteChatMiembroVerificar.setEstadoParticipante(estadoParticipante);
					}
				}
			}
			BeanParametro beanParametro= new BeanParametro();
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanParticipanteChatMiembroVerificar);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			
			Boolean rptParticipanteChatMiembroVerificar= logicParticipanteChatMiembro.mantenimiento(beanParametro);
			if(!rptParticipanteChatMiembroVerificar){
				throw new UnknownException(P.ERROR_OPERACION);
			}			
																				
			tx.commit();
			pm.close();
			return true;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
		
	/**
	 * Descricion: Lista los participantes activos en el Tablon. 
	 * @param codeColonia
	 * @param correoTurista
	 * @param codeTablonColonia
	 * @return
	 * @throws UnknownException
	 */
	public static List<ParticipanteChatMiembro> listarParticipantesTablonColonia(
			String codeColonia,String correoTurista,String codeTablonColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();		
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicTablonColonia logicTablonColonia= new LogicTablonColonia(pm);
			LogicParticipanteChatMiembro logicParticipanteChatMiembro= new LogicParticipanteChatMiembro(pm);			
			
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
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}					
			TablonColonia beanTablonColonia= (TablonColonia)logicTablonColonia.getBeanByCode(codeTablonColonia);
			if(beanTablonColonia==null){
				throw new UnknownException(P.errorNoExistencia(TablonColonia.class));
			}		
			List<ParticipanteChatMiembro> listParticipantesTablonColonia= (List<ParticipanteChatMiembro>) logicParticipanteChatMiembro.getListarBeanByTablonColonia(codeTablonColonia);
			return listParticipantesTablonColonia;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}	
	
	/**
	 * Descripcion: Metodo que guarda el mensaje que posteriormente se enviara al tablon. 
	 * @param correoTurista
	 * @param codeTablonColonia
	 * @param mensaje
	 * @param linkFotoMensaje
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean responderTablonColonia(String correoTurista,String codeTablonColonia,
			String mensaje,String linkFotoMensaje) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();		
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicParticipanteChatMiembro logicParticipanteChatMiembro= new LogicParticipanteChatMiembro(pm);
			LogicMensajeColoniaPrivado logicMensajeColoniaPrivado= new LogicMensajeColoniaPrivado(pm);			
			
			UsuarioTurista beanUsuarioTurista= (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista== null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}	
						
			ParticipanteChatMiembro beanParticipanteChatMiembroVerificar= (ParticipanteChatMiembro) 
					logicParticipanteChatMiembro.getBeanByTuristaTablon(correoTurista, codeTablonColonia);	
			
			if(beanParticipanteChatMiembroVerificar==null){
				throw new UnknownException("Usuario no es Participante del Chat Grupal");
			}						
			
			Calendar myCalendar = GregorianCalendar.getInstance();							
			MensajeColoniaPrivado beanMensajeColoniaPrivado= new MensajeColoniaPrivado();
			String codeMensajeColoniaPrivado= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());		
			beanMensajeColoniaPrivado.setIdMensajeColoniaPrivado(KeyFactory.keyToString(KeyFactory.createKey(MensajeColoniaPrivado.class.getSimpleName(), codeMensajeColoniaPrivado)));
			beanMensajeColoniaPrivado.setCodeMensajeColoniaPrivado(codeMensajeColoniaPrivado);			
			beanMensajeColoniaPrivado.setAnno(myCalendar.get(Calendar.YEAR));
			beanMensajeColoniaPrivado.setApellidoTuristaParticipante(beanUsuarioTurista.getApellido());		
			beanMensajeColoniaPrivado.setCodeMiembroParticipante(beanParticipanteChatMiembroVerificar.getCodeMiembro());
			beanMensajeColoniaPrivado.setCodeParticipanteChatMiembro(beanParticipanteChatMiembroVerificar.getCodeParticipanteChatMiembro());
			beanMensajeColoniaPrivado.setCodeTablonColonia(codeTablonColonia);
			beanMensajeColoniaPrivado.setCodeTuristaParticipante(beanUsuarioTurista.getCodeUsuarioTurista());
			beanMensajeColoniaPrivado.setDia(myCalendar.get(Calendar.DAY_OF_MONTH));
			beanMensajeColoniaPrivado.setFecha(new java.util.Date());
			beanMensajeColoniaPrivado.setFotoPerfilTuristaParticipante(beanUsuarioTurista.getFotoPerfil());
			beanMensajeColoniaPrivado.setHora(myCalendar.get(Calendar.HOUR_OF_DAY));			
			beanMensajeColoniaPrivado.setIsPersistente(true);
			beanMensajeColoniaPrivado.setMensaje(mensaje);
			beanMensajeColoniaPrivado.setLinkFotoMensaje(linkFotoMensaje);
			beanMensajeColoniaPrivado.setMes(myCalendar.get(Calendar.MONTH));
			beanMensajeColoniaPrivado.setMinuto(myCalendar.get(Calendar.MINUTE));
			beanMensajeColoniaPrivado.setNombreTuristaParticipante(beanUsuarioTurista.getNombre());			
			beanMensajeColoniaPrivado.setSegundo(myCalendar.get(Calendar.SECOND));
			beanMensajeColoniaPrivado.setVersion(new java.util.Date().getTime());
			
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanMensajeColoniaPrivado);
			beanParametro.setTipoOperacion(P.INSERTAR);
			Boolean rptaMensajeColoniaPrivado=logicMensajeColoniaPrivado.mantenimiento(beanParametro);
			if(!rptaMensajeColoniaPrivado){
				return false;
			}
			tx.commit();
			pm.close();
			return true;								
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * Descripcion: Retorna la lista de los mensajes que interactuan en la ventana del Tablon.
	 * @param codeTablonColonia
	 * @return
	 * @throws UnknownException
	 */
	public static List<MensajeColoniaPrivado> listarMensajesTablonColonia(String codeTablonColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();				
			LogicMensajeColoniaPrivado logicMensajeColoniaPrivado= new LogicMensajeColoniaPrivado(pm);				
			List<MensajeColoniaPrivado> listMensajesTablon= (List<MensajeColoniaPrivado>) logicMensajeColoniaPrivado.getListarBeanByTablon(codeTablonColonia);					
			return listMensajesTablon;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * 
	 * @param correoTurista
	 * @param codeColonia
	 * @param distanciaBusqueda
	 * @return
	 * @throws UnknownException
	 */
	public static List<Amistad> listarAmigosCercanosColonia(String correoTurista, 
			String codeColonia, Double distanciaBusqueda) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();							
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicAmistad logicAmistad= new LogicAmistad(pm);
			LogicControlPosicion logicControlPosicion= new LogicControlPosicion(pm);
						
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Colonia beanColonia = logicColonia.getBeanByCode(codeColonia);
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Colonia.class));
			}
			Miembro beanMiembro= (Miembro) logicMiembro.verPerfilMiembro(correoTurista, codeColonia);
			if(beanMiembro==null){
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}						
			/* Es miembro de colonia..*/			
			Set<Amistad> listAmigosTurista=  logicAmistad.getListarAmigos(correoTurista);
			List<Amistad> listAmigosCercanos= new ArrayList<Amistad>();
			/* Para amigos cercanos usar la ultima posicion */
			Iterator<Amistad> listAmigosTuristaIterator= listAmigosTurista.iterator();
			List<Amistad> listAmigosNoMiembrosColonia= new ArrayList<Amistad>();
			while(listAmigosTuristaIterator.hasNext()){
				Amistad beanAmistadIterator = listAmigosTuristaIterator.next();	
				ControlPosicion beanControlPosicion=(ControlPosicion) logicControlPosicion.getBeanByTurista(beanAmistadIterator.getCodeTuristaAmigo());
					Double distancia= GestionShared.calcularDistancia(
							beanControlPosicion.getLatitud(),beanControlPosicion.getLongitud(), beanColonia.getLatitud(),
							beanColonia.getLongitud(),beanColonia.getRadio(),distanciaBusqueda);
					if(distancia!=null){
						beanAmistadIterator.setCalculoDistancia(distancia);
						listAmigosCercanos.add(beanAmistadIterator);
					}				
			}				
				Collections.sort(listAmigosCercanos,new Comparator<Amistad>(){
					@Override
					public int compare(Amistad beanAmistad, Amistad anotherAmistad) {						
						return new Double(beanAmistad.getCalculoDistancia()).compareTo(new Double(anotherAmistad.getCalculoDistancia()));
					}					
				});												
				
				Iterator<Amistad> listAmigosCercanosIterator= listAmigosCercanos.iterator();
				while (listAmigosCercanosIterator.hasNext()){
					Amistad beanAmistadIterator = listAmigosTuristaIterator.next();	
					if(!logicMiembro.existeMiembro(beanAmistadIterator.getCodeTuristaAmigo(), codeColonia)){
						listAmigosNoMiembrosColonia.add(beanAmistadIterator);
					}
				}
				/* Si no es miembro de colonia entonces agregamos a la lista de amigos a mostrar..*/
							
			return listAmigosNoMiembrosColonia;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	/**
	 * 
	 * @param codeTablonColonia
	 * @return
	 * @throws UnknownException
	 */
	public static Boolean sugerirColonia(List<String> listCodeTuristasSeleccionados,String codeTurista,String codeColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();			
			LogicSugerenciaColonia logicSugerenciaColonia= new LogicSugerenciaColonia(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicNotificacionTurista logicNotificacionTurista= new LogicNotificacionTurista(pm);			
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(codeTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Colonia beanColonia = logicColonia.getBeanByCode(codeColonia);
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Colonia.class));
			}						
			Iterator<String> listCodeTuristasSeleccionadosIterator= listCodeTuristasSeleccionados.iterator();
			while(listCodeTuristasSeleccionadosIterator.hasNext()){
				String codeTuristaAmigo= listCodeTuristasSeleccionadosIterator.next();				
				SugerenciaColonia beanSugerirColonia= new SugerenciaColonia();
				String codeSugerenciaColonia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());						
				beanSugerirColonia.setIdSugerenciaColonia(KeyFactory.keyToString(KeyFactory.createKey(SugerenciaColonia.class.getSimpleName(), codeSugerenciaColonia)));
				beanSugerirColonia.setCodeSugerenciaColonia(codeSugerenciaColonia);
				beanSugerirColonia.setBeanTuristaEmisor(beanUsuarioTurista);
				beanSugerirColonia.setApellidoTuristaEmisor(beanUsuarioTurista.getApellido());
				beanSugerirColonia.setCodeColonia(codeColonia);				
				beanSugerirColonia.setCodeTuristaReceptor(codeTuristaAmigo);
				beanSugerirColonia.setFotoPerfilTuristaEmisor(beanUsuarioTurista.getFotoPerfil());				
				beanSugerirColonia.setIsPersistente(true);
				beanSugerirColonia.setNombreColonia(beanColonia.getNombreColonia());
				beanSugerirColonia.setNombreTuristaEmisor(beanUsuarioTurista.getNombre());				
				beanSugerirColonia.setVersion(new java.util.Date().getTime());
				
				BeanParametro beanParametro = new BeanParametro();
				beanParametro.setBean(beanSugerirColonia);
				beanParametro.setTipoOperacion(P.INSERTAR);
				
				Boolean rptaSugerenciaColonia= logicSugerenciaColonia.mantenimiento(beanParametro);
				if(!rptaSugerenciaColonia){
					throw new UnknownException(P.ERROR_OPERACION);
				}				
				Notificacion beanNotificacion= generarNotificacionSugerenciaColonia(beanSugerirColonia, beanParametro, pm);																							
				UsuarioTurista beanTuristaGeneraNotificacion= beanUsuarioTurista;			
				StringBuilder title = new StringBuilder();		
				StringBuilder msjTuristaNotificacion = new StringBuilder();		
				StringBuilder body = new StringBuilder();				
				title.append(beanTuristaGeneraNotificacion.getNombre());							
				msjTuristaNotificacion.append(title.toString());						
				body.append(" te sugiere unirte a la Colonia ".concat(beanColonia.getNombreColonia()));	
				msjTuristaNotificacion.append(body.toString());																										
				UsuarioTurista beanTuristaRecibeNotificacion= logicUsuarioTurista.getBeanByEmail(codeTuristaAmigo);
								        		
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
				beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
				beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
				beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacion.getFechaGeneroNotificacion());				
				beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
				beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
				beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
				beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);																																	
				beanParametro.setBean(beanNotificacionTuristaInteractua);
				beanParametro.setTipoOperacion(P.INSERTAR);					
				Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(beanParametro);
				if(!rptaNotificacionTurista){																		
					throw new UnknownException(P.ERROR_OPERACION);
				}
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
				}catch(Exception ex){									
				}						
			}									
			return true;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}	
	
	public static Boolean aceptarSugerenciaInteres(String codeTurista,String codeInteres,String codeColonia) throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();					
			LogicColonia logicColonia= new LogicColonia(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);					
			LogicColoniaInteres logicColoniaInteres= new LogicColoniaInteres(pm);
			LogicTuristaInteres logicTuristaInteres= new LogicTuristaInteres(pm);
			LogicMiembroInteres logicMiembroInteres= new LogicMiembroInteres(pm);
			LogicInteres logicInteres= new LogicInteres(pm);
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			LogicDetalleInteres logicDetalleInteres= new LogicDetalleInteres(pm);
			
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista)logicUsuarioTurista.getBeanByEmail(codeTurista);						
			if(beanUsuarioTurista==null){
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			Colonia beanColonia = logicColonia.getBeanByCode(codeColonia);
			if(beanColonia==null){
				throw new UnknownException(P.errorNoExistencia(Colonia.class));
			}		
			Interes beanInteres= (Interes) logicInteres.getBean(codeInteres);
			if(beanInteres==null){
				throw new UnknownException(P.errorNoExistencia(Interes.class));
			}			
			Calendar myGregorian = GregorianCalendar.getInstance();
			Integer annio= myGregorian.get(Calendar.YEAR);
			Set<ColoniaInteres> listColoniaInteresSet= beanColonia.getListColoniaIntereses();
			ColoniaInteres beanColoniaInteres= (ColoniaInteres) logicColoniaInteres.getBeanByColonia_Interes(codeColonia, codeInteres);
			if(beanColoniaInteres==null){
				beanColoniaInteres=new ColoniaInteres();
				String codeColoniaInteres=codeInteres;
				Key keyColonia=KeyFactory.createKey(Colonia.class.getSimpleName(), codeColonia);
				beanColoniaInteres.setIdColoniaInteres(KeyFactory.keyToString(KeyFactory.createKey(keyColonia,ColoniaInteres.class.getSimpleName(), codeColoniaInteres)));
				beanColoniaInteres.setCodeColoniaInteres(codeColoniaInteres);
				beanColoniaInteres.setVersion(new java.util.Date().getTime());
				beanColoniaInteres.setBeanColonia(beanColonia);
				beanColoniaInteres.setCodeColonia(codeColonia);	
				beanColoniaInteres.setBeanInteres(beanInteres);
				beanColoniaInteres.setCodeInteres(beanInteres.getCodeInteres());
				beanColoniaInteres.setNombreInteres(beanInteres.getNombre());
				beanColoniaInteres.setValorModaColoniaInteres(1.0);
				beanColoniaInteres.setIsPersistente(true);
				/***/
				
				if(beanUsuarioTurista.getFechaNacimiento()!=null){
					Date fechaNacimiento= beanUsuarioTurista.getFechaNacimiento();
					SimpleDateFormat mySimpleFormat = new SimpleDateFormat("YYYY");				
			        Integer annioTurista= Integer.parseInt(mySimpleFormat.format(fechaNacimiento));
					beanColoniaInteres.setEdad(annio-annioTurista);
				}			
				beanColoniaInteres.setAnnio(annio);
				beanColoniaInteres.setMes(myGregorian.get(Calendar.MONTH));
				beanColoniaInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
				beanColoniaInteres.setNacionalidad(beanUsuarioTurista.getNombrePaisNacimiento());
				beanColoniaInteres.setNombreColonia(beanColonia.getNombreColonia());		
				listColoniaInteresSet.add(beanColoniaInteres);
			}else{
				
				beanColoniaInteres.setValorModaColoniaInteres(beanColoniaInteres.getValorModaColoniaInteres()+1);
				if(!listColoniaInteresSet.isEmpty()){
					if(listColoniaInteresSet.contains(beanColoniaInteres)){
						listColoniaInteresSet.remove(beanColoniaInteres);												
					}
					listColoniaInteresSet.add(beanColoniaInteres);	
				}
			}			
			BeanParametro beanParametro= new BeanParametro();				
			beanParametro.setBean(beanColoniaInteres);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			
			Boolean rptaColoniaInteres = logicColoniaInteres.mantenimiento(beanParametro);
			if(!rptaColoniaInteres){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			
			beanColonia.setListColoniaIntereses(listColoniaInteresSet);
			beanColonia=ordenYSetInteresAnColonia(beanColonia);
			
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanColonia);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			
			Boolean rptaColonia= logicColonia.mantenimiento(beanParametro);
			if(!rptaColonia){
				throw new UnknownException(P.ERROR_OPERACION);
			}			
			Miembro beanMiembro= logicMiembro.verPerfilMiembro(codeTurista, codeColonia);
			Set<MiembroInteres>listMiembrosInteresesSet= beanMiembro.getListMiembroIntereses();
			MiembroInteres beanMiembroInteres= (MiembroInteres) logicMiembroInteres.getBeanByMiembro_Interes(beanMiembro.getCodeMiembro(), codeInteres);
			if(beanMiembroInteres==null){
				beanMiembroInteres=new MiembroInteres();
				String codeMiembroInteres=codeInteres;	
				Key keyColonia=KeyFactory.createKey(Colonia.class.getSimpleName(), codeColonia);
				Key keyMiembro=KeyFactory.createKey(keyColonia,Miembro.class.getSimpleName(), beanMiembro.getCodeMiembro());
				beanMiembroInteres.setIdMiembroInteres(KeyFactory.keyToString(KeyFactory.createKey(keyMiembro,MiembroInteres.class.getSimpleName(), codeMiembroInteres)));
				beanMiembroInteres.setCodeMiembroInteres(codeMiembroInteres);
				beanMiembroInteres.setBeanInteres(beanInteres);
				beanMiembroInteres.setCodeInteres(beanInteres.getCodeInteres());
				beanMiembroInteres.setNombreInteres(beanInteres.getNombre());
				beanMiembroInteres.setValorModaMiembroInteres(1.0);
				beanMiembroInteres.setEstadoMiembroInteres(P.ACTIVO);
				beanMiembroInteres.setVersion(new java.util.Date().getTime());
				beanMiembroInteres.setBeanMiembro(beanMiembro);
				/***/
				beanMiembroInteres.setEdad(beanColoniaInteres.getEdad());
				beanMiembroInteres.setAnnio(beanColoniaInteres.getAnnio());
				beanMiembroInteres.setMes(myGregorian.get(Calendar.MONTH));
				beanMiembroInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
				beanMiembroInteres.setNacionalidad(beanUsuarioTurista.getNombrePaisNacimiento());
				beanMiembroInteres.setNombreColonia(beanColonia.getNombreColonia());
				/***/
				beanMiembroInteres.setCodeMiembro(beanMiembro.getCodeMiembro());
				beanMiembroInteres.setBeanTurista(beanUsuarioTurista);
				beanMiembroInteres.setCodeTurista(beanUsuarioTurista.getCodeUsuarioTurista());
				beanMiembroInteres.setIsPersistente(true);
				beanMiembroInteres.setCodeColonia(codeColonia);
				listMiembrosInteresesSet.add(beanMiembroInteres);
			}else{
				beanMiembroInteres.setValorModaMiembroInteres(beanMiembroInteres.getValorModaMiembroInteres()+1);
				if(!listMiembrosInteresesSet.isEmpty()){
					if(listMiembrosInteresesSet.contains(beanMiembroInteres)){
						listMiembrosInteresesSet.remove(beanMiembroInteres);												
					}
					listMiembrosInteresesSet.add(beanMiembroInteres);	
				}
			}
			
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanMiembroInteres);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			
			Boolean rptaMiembroInteres= logicMiembroInteres.mantenimiento(beanParametro);
			if(!rptaMiembroInteres){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			beanMiembro.setListMiembroIntereses(listMiembrosInteresesSet);
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanMiembro);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			
			Boolean rptaMiembro= logicMiembro.mantenimiento(beanParametro);
			if(!rptaMiembro){
				throw new UnknownException(P.ERROR_OPERACION);
			}
			
			Set<TuristaInteres> listTuristaInteresSet= beanUsuarioTurista.getListTuristaIntereses();
			TuristaInteres beanTuristaInteres= (TuristaInteres) logicTuristaInteres.getBeanByTurista_Interes(codeTurista, codeInteres);
			if(beanTuristaInteres==null){
				beanTuristaInteres=new TuristaInteres();
				Key keyTurista=KeyFactory.stringToKey(beanUsuarioTurista.getIdUsuarioTurista());
				String codeTuristaInteres=codeInteres;				
				beanTuristaInteres.setIdTuristaInteres(KeyFactory.keyToString(KeyFactory.createKey(keyTurista,TuristaInteres.class.getSimpleName(), codeTuristaInteres)));
				beanTuristaInteres.setCodeTuristaInteres(codeTuristaInteres);
				beanTuristaInteres.setBeanInteres(beanInteres);
				beanTuristaInteres.setCodeInteres(beanInteres.getCodeInteres());
				beanTuristaInteres.setNombreInteres(beanInteres.getNombre());
				beanTuristaInteres.setEstadoVisibilidad(P.VISTO);
				beanTuristaInteres.setValorModa(1.0);
				/***/
				beanTuristaInteres.setEdad(beanColoniaInteres.getEdad());
				beanTuristaInteres.setAnnio(beanColoniaInteres.getAnnio());
				beanTuristaInteres.setMes(myGregorian.get(Calendar.MONTH));
				beanTuristaInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
				beanTuristaInteres.setNacionalidad(beanUsuarioTurista.getNombrePaisNacimiento());
				beanTuristaInteres.setNombreColonia(beanColonia.getNombreColonia());
				/***/
				beanTuristaInteres.setVersion(new java.util.Date().getTime());			
				beanTuristaInteres.setBeanTurista(beanUsuarioTurista);
				beanTuristaInteres.setCodeTurista(beanUsuarioTurista.getCodeUsuarioTurista());
				beanTuristaInteres.setIsPersistente(true);
				listTuristaInteresSet.add(beanTuristaInteres);
			}else{
				beanTuristaInteres.setValorModa(beanTuristaInteres.getValorModa()+1);
				if(!listTuristaInteresSet.isEmpty()){
					if(listTuristaInteresSet.contains(beanTuristaInteres)){
						listTuristaInteresSet.remove(beanTuristaInteres);												
					}
					listTuristaInteresSet.add(beanTuristaInteres);	
				}
			}			
			Boolean rptaTuristaInteres = logicTuristaInteres.mantenimiento(beanParametro);
			if(!rptaTuristaInteres){
				throw new UnknownException(P.ERROR_OPERACION);			
			}			
			beanUsuarioTurista.setListTuristaIntereses(listTuristaInteresSet);
			beanUsuarioTurista=ordenYSetInteresAnTurista(beanUsuarioTurista);
			
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanUsuarioTurista);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			
			Boolean rptaUsuarioTurista= logicUsuarioTurista.mantenimiento(beanParametro);
			if(!rptaUsuarioTurista){
				throw new UnknownException(P.ERROR_OPERACION);			
			}
			LogicTipoMovimiento logicTipoMovimiento=new LogicTipoMovimiento(pm);
			TipoMovimiento beanTipoMovimiento=pm.detachCopy(logicTipoMovimiento.getBean(P.EMPATIA_MIEMBROS));
			if(beanTipoMovimiento==null){
				throw new UnknownException(P.errorNoExistencia(TipoMovimiento.class));
			}				
			DetalleInteres beanDetalleInteres=new DetalleInteres();			
			String codeDetalleInteres=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			Key keyColonia=KeyFactory.createKey(Colonia.class.getSimpleName(), codeColonia);			
			beanDetalleInteres.setIdDetalleInteres(KeyFactory.keyToString(KeyFactory.createKey(keyColonia,DetalleInteres.class.getSimpleName(), codeDetalleInteres)));
			beanDetalleInteres.setCodeDetalleInteres(codeDetalleInteres);
			beanDetalleInteres.setBeanInteres(beanInteres);
			beanDetalleInteres.setCodeInteres(beanInteres.getCodeInteres());
			beanDetalleInteres.setNombreInteres(beanInteres.getNombre());
			beanDetalleInteres.setValor(1.0);
			beanDetalleInteres.setBeanColonia(beanColonia);
			beanDetalleInteres.setCodeColonia(beanColonia.getCodeColonia());			
			beanDetalleInteres.setNombreColonia(beanColonia.getNombreColonia());
			beanDetalleInteres.setFechaCreacion(new java.util.Date());
			beanDetalleInteres.setIsPersistente(true);
			beanDetalleInteres.setBeanTipoMovimiento(beanTipoMovimiento);
			beanDetalleInteres.setCodeTipoMovimiento(beanTipoMovimiento.getCodeTipoMovimiento());
			beanDetalleInteres.setBeanTurista(beanUsuarioTurista);
			beanDetalleInteres.setCodeTurista(beanUsuarioTurista.getCodeUsuarioTurista());			
			beanDetalleInteres.setCodeMiembro(beanMiembro.getCodeMiembro());			
			beanDetalleInteres.setCodeColoniaInteres(beanColoniaInteres.getCodeColoniaInteres());			
			beanDetalleInteres.setCodeMiembroInteres(beanMiembroInteres.getCodeMiembroInteres());			
			beanDetalleInteres.setCodeTuristaInteres(beanTuristaInteres.getCodeTuristaInteres());
			beanDetalleInteres.setVersion(new java.util.Date().getTime());
			
			beanParametro = new BeanParametro();
			beanParametro.setBean(beanDetalleInteres);
			beanParametro.setTipoOperacion(P.INSERTAR);
			
			Boolean rptaDetalleInteres = logicDetalleInteres.mantenimiento(beanParametro);
			if(!rptaDetalleInteres){
				throw new UnknownException(P.ERROR_OPERACION);
			}			
			return true;									
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}	
	
	private static Colonia ordenYSetInteresAnColonia(Colonia beanColonia){		
		List<ColoniaInteres> arrayOrdenado=new ArrayList<ColoniaInteres>();		
		arrayOrdenado.addAll(beanColonia.getListColoniaIntereses());
		Collections.sort(arrayOrdenado,new Comparator<ColoniaInteres>(){
			@Override
			public int compare(ColoniaInteres beanColoniaInteres, ColoniaInteres anotherColoniaInteres) {						
				return new Double(anotherColoniaInteres.getValorModaColoniaInteres())
						.compareTo(new Double(beanColoniaInteres.getValorModaColoniaInteres()));
			}					
		});
		if(arrayOrdenado.size()>=1){
			Interes beanInteres= arrayOrdenado.get(0).getBeanInteres();						
			beanColonia.setBeanInteresUno(beanInteres);
			beanColonia.setModaInteresUno(arrayOrdenado.get(0).getValorModaColoniaInteres());
			beanColonia.setNombreInteresUno(beanInteres.getNombre());									
		}
		if(arrayOrdenado.size()>=2){
			Interes beanInteres= arrayOrdenado.get(1).getBeanInteres();						
			beanColonia.setBeanInteresDos(beanInteres);
			beanColonia.setModaInteresDos(arrayOrdenado.get(1).getValorModaColoniaInteres());
			beanColonia.setNombreInteresDos(beanInteres.getNombre());																																				
		}		
		if(arrayOrdenado.size()>=3){
			Interes beanInteres= arrayOrdenado.get(2).getBeanInteres();
			beanColonia.setBeanInteresTres(beanInteres);
			beanColonia.setModaInteresTres(arrayOrdenado.get(2).getValorModaColoniaInteres());
			beanColonia.setNombreInteresTres(beanInteres.getNombre());					
		}	
		return beanColonia;
	}
	

	private static UsuarioTurista ordenYSetInteresAnTurista(UsuarioTurista beanUsuarioTurista){
		
		List<TuristaInteres> arrayOrdenado=new ArrayList<TuristaInteres>();		
		arrayOrdenado.addAll(beanUsuarioTurista.getListTuristaIntereses());
		Collections.sort(arrayOrdenado,new Comparator<TuristaInteres>(){
			@Override
			public int compare(TuristaInteres beanTuristaInteres, TuristaInteres anotherTuristaInteres) {						
				return new Double(anotherTuristaInteres.getValorModa()).compareTo(new Double(beanTuristaInteres.getValorModa()));
			}					
		});
		if(arrayOrdenado.size()>=1){
			Interes beanInteres= arrayOrdenado.get(0).getBeanInteres();						
			beanUsuarioTurista.setBeanInteresUno(beanInteres);
			beanUsuarioTurista.setModaInteresUno(arrayOrdenado.get(0).getValorModa());
			beanUsuarioTurista.setNombreInteresUno(beanInteres.getNombre());									
		}
		if(arrayOrdenado.size()>=2){
			Interes beanInteres= arrayOrdenado.get(1).getBeanInteres();						
			beanUsuarioTurista.setBeanInteresDos(beanInteres);
			beanUsuarioTurista.setModaInteresDos(arrayOrdenado.get(1).getValorModa());
			beanUsuarioTurista.setNombreInteresDos(beanInteres.getNombre());																																				
		}
		
		if(arrayOrdenado.size()>=3){
			Interes beanInteres= arrayOrdenado.get(2).getBeanInteres();
			beanUsuarioTurista.setBeanInteresTres(beanInteres);
			beanUsuarioTurista.setModaInteresTres(arrayOrdenado.get(2).getValorModa());
			beanUsuarioTurista.setNombreInteresTres(beanInteres.getNombre());					
		}	
		return beanUsuarioTurista;
	}
	
	public static MuroNoticia generaNoticia(Novedad beanNovedad,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{																		
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
		StringBuilder turista=new StringBuilder();
		turista.append(beanUsuarioTurista.getNombre()!=null?beanUsuarioTurista.getNombre():"MARCO");
		turista.append(" ");
		turista.append(beanUsuarioTurista.getApellido()!=null?beanUsuarioTurista.getApellido():"POLO");
		beanNoticia.setNombreTuristaNegocioGeneraNoticia(turista.toString());
		beanNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanUsuarioTurista.getFotoPerfil());
		beanNoticia.setCorreoTuristaGeneraNoticia(beanUsuarioTurista.getCorreoTurista());
		
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
			beanMuroNoticia.setNombreTuristaNegocioGeneraNoticia(turista.toString());
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
                        beanMuroNoticia.setNombreColoniaNegocioDestino(beanNoticia.getNombreColoniaNegocioDestino());
			List<MuroNoticia> listMuroNoticia=new ArrayList<MuroNoticia>();
			listMuroNoticia.add(beanMuroNoticia);
			beanNoticia.setListMuroNoticia(listMuroNoticia);
			beanParametro.setBean(beanNoticia);		
			beanParametro.setTipoOperacion(P.INSERTAR);			
			LogicNoticia logicNoticia=new LogicNoticia(pm);
			Noticia beanNoticiabd=logicNoticia.mantenimientoReturn(beanParametro);
			return beanNoticiabd.getListMuroNoticia().get(0);
}
	
	private static Notificacion generarNotificacionNovedad(MuroNoticia beanMuroNoticia,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{										
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
		StringBuilder turista=new StringBuilder();
		turista.append(beanUsuarioTurista.getNombre()!=null?beanUsuarioTurista.getNombre():"MARCO");
		turista.append(" ");
		//turista.append(beanUsuarioTurista.getApellido()!=null?beanUsuarioTurista.getApellido():"POLO");
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(turista.toString());
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
		TipoNotificacion beanTipoNotificacion= new TipoNotificacion();
		beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean(P.COMENTARIO_NOVEDAD);
		if(beanTipoNotificacion==null){
			throw new UnknownException("No existe Tipo Notificacion CMN");
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
	
	public static Boolean queueNotificationNovedadColonia(String codeNotificacion) throws UnknownException{
		PersistenceManager pm=null;
		try{		
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);
			LogicNotificacion logicNotificacion= new LogicNotificacion(pm);		
			LogicMiembro logicMiembro= new LogicMiembro(pm);
			
			Notificacion beanNotificacion= (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);						
			UsuarioTurista beanTuristaGeneraNotificacion= beanNotificacion.getBeanTuristaGeneraNotificacion();
			StringBuilder msjTuristaPublicaNovedad = new StringBuilder();				
			Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();	
			String codeColonia= beanNotificacion.getCodeColonia();
			
			List<Miembro> listMiembros= (List<Miembro>)logicMiembro.getListarBeanByColonia(codeColonia);
			if(listMiembros!=null){					
				Iterator<Miembro>listMiembrosIterator=listMiembros.iterator();
				while(listMiembrosIterator.hasNext()){
					queueTuristas.add(listMiembrosIterator.next().getBeanTurista());
				}					
				if(queueTuristas.contains(beanTuristaGeneraNotificacion)){
					queueTuristas.remove(beanTuristaGeneraNotificacion);
				}				
				msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre());
				TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
				if(beanTipoNotificacion.getCodeTipoNotificacion().equals(P.COMENTARIO_NOVEDAD)){
					String concat="Novedad";
					msjTuristaPublicaNovedad.append(" ha publicado una ".concat(concat));						
				}						
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
					beanNotificacionTuristaInteractua.setCodeNotificacionTurista(codeNotificacionTuristaInteractua);
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
					beanNotificacionTuristaInteractua.setBeanNoticia(beanNotificacion.getBeanNoticia());
					beanNotificacionTuristaInteractua.setCodeComentarioNoticia(beanNotificacion.getCodeComentarioNoticia());
					beanNotificacionTuristaInteractua.setCodeNoticia(beanNotificacion.getCodeNoticia());
					beanNotificacionTuristaInteractua.setBeanNotificacion(beanNotificacion);
					beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
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
	
	public static Boolean queueMuroNoticiaNovedadColonia(String codeNoticia) throws UnknownException{						
		PersistenceManager pm=null;				
		try{		
		pm = PMF.getPMF().getPersistenceManager();			
		LogicNoticia logicNoticia= new LogicNoticia(pm);
		LogicMuroNoticia logicMuroNoticia= new LogicMuroNoticia(pm);	
		Noticia beanNoticia= (Noticia)logicNoticia.getBeanByCode(codeNoticia);					
		if(beanNoticia== null){
			throw new UnknownException("No se existe Noticia");
		}
		Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();					
		UsuarioTurista beanTuristaGeneraNoticia= beanNoticia.getBeanTuristaGeneraNoticia();		
		String codeNoticiaActual= beanNoticia.getCodeNoticia();
		String codeTuristaPublica = beanTuristaGeneraNoticia.getCodeUsuarioTurista(),codeTuristaGenera= beanTuristaGeneraNoticia.getCodeUsuarioTurista();
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
		Long rangoFecha=beanGregorianCalendar.getTime().getTime();		
		queueTuristas.addAll(GestionShared.busquedaByDCS(codeTuristaPublica, codeNoticiaActual, codeTuristaGenera, rangoFecha.toString(), pm));
		queueTuristas.addAll(GestionShared.busquedaByEmpatias(codeTuristaPublica, codeTuristaGenera, rangoFecha.toString(), pm));				
		if(queueTuristas.isEmpty()){			
			codeParametroSistema="CPAN";
			queueTuristas.addAll(GestionShared.busquedaByFriends(codeTuristaGenera, codeParametroSistema, pm));
			if(queueTuristas.isEmpty()){ // Si No hay usuarios a notificar se le mostrara la noticia a los miembros de la colonia..				
				queueTuristas.addAll(GestionShared.busquedaByMembers(beanNoticia.getCodeColonia(), pm));
			}
		}
		if(queueTuristas.contains(beanTuristaGeneraNoticia)){
			queueTuristas.remove(beanTuristaGeneraNoticia);
		}
		List<MuroNoticia> listParamMuroNoticia=new ArrayList<MuroNoticia>();
		Iterator<UsuarioTurista> listQueueUsuarioIterator= queueTuristas.iterator();		
			while(listQueueUsuarioIterator.hasNext()){
				UsuarioTurista beanTuristaMuro= listQueueUsuarioIterator.next();
				MuroNoticia beanMuroNoticia= new MuroNoticia();
				String codeMuroNoticia=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());	
				Key keyNoticia=KeyFactory.createKey(Noticia.class.getSimpleName(), codeNoticia);
				String idMuroNoticia=KeyFactory.keyToString( KeyFactory.createKey(keyNoticia,MuroNoticia.class.getSimpleName(),codeMuroNoticia));
				beanMuroNoticia.setIdMuroNoticia(idMuroNoticia);
				beanMuroNoticia.setCodeMuroNoticia(codeMuroNoticia);
				beanMuroNoticia.setBeanTuristaMuro(beanTuristaMuro);
				beanMuroNoticia.setCodeTuristaMuro(beanTuristaMuro.getCodeUsuarioTurista());
				beanMuroNoticia.setCorreoTuristaMuro(beanTuristaMuro.getCorreoTurista());							
				beanMuroNoticia.setBeanNoticia(beanNoticia);
				beanMuroNoticia.setCodeNoticia(codeNoticia);
				beanMuroNoticia.setBeanColonia(beanNoticia.getBeanColonia());
				beanMuroNoticia.setCodeColonia(beanNoticia.getCodeColonia());
				beanMuroNoticia.setBeanMiembro(beanNoticia.getBeanMiembro());
				beanMuroNoticia.setCodeMiembro(beanNoticia.getCodeMiembro());
				beanMuroNoticia.setFechaPublicacion(beanNoticia.getFechaPublicacion());
				beanMuroNoticia.setBeanTuristaGeneraNoticia(beanTuristaGeneraNoticia);
				beanMuroNoticia.setCodeTuristaGeneraNoticia(beanTuristaGeneraNoticia.getCodeUsuarioTurista());
				beanMuroNoticia.setNombreTuristaNegocioGeneraNoticia(beanTuristaGeneraNoticia.getNombre());
				beanMuroNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanTuristaGeneraNoticia.getFotoPerfil());
				beanMuroNoticia.setCorreoTuristaGeneraNoticia(beanTuristaGeneraNoticia.getCorreoTurista());
				beanMuroNoticia.setLongitud(beanNoticia.getLongitud());
				beanMuroNoticia.setLatitud(beanNoticia.getLatitud());
				beanMuroNoticia.setTotalComentario(0);
				beanMuroNoticia.setTotalCompartido(0);
				beanMuroNoticia.setTotalDivulgado(0);
				beanMuroNoticia.setEnlace(beanNoticia.getEnlace());
				beanMuroNoticia.setDescripcion(beanNoticia.getDescripcion());
				beanMuroNoticia.setTipoNoticia(beanNoticia.getTipoNoticia());			
				beanMuroNoticia.setVersion((new java.util.Date()).getTime());
				beanMuroNoticia.setIsPersistente(true);
				beanMuroNoticia.setVisto(P.NO_VISTO);		
				beanMuroNoticia.setFotoConquistaPublicidad(beanNoticia.getFotoConquistaPublicidad());				
				listParamMuroNoticia.add(beanMuroNoticia);
			}	
			Collection<BeanParametro> listParametros=new ArrayList<BeanParametro>();
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(listParamMuroNoticia);
			parametro.setTipoOperacion(P.INSERTAR);
			listParametros.add(parametro);
			Boolean rptaMuroNoticia= logicMuroNoticia.mantenimiento(listParametros);				
			if(!rptaMuroNoticia){
				throw new UnknownException("No se publico en el Muro");
			}					
			beanNoticia.setEstadoTarea(P.EJECUTADO);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanNoticia);
			beanParametro.setTipoOperacion(P.ACTUALIZAR);
			Boolean rptaNoticia= logicNoticia.mantenimiento(beanParametro);			
			if(rptaNoticia){							
				return true;
			}
			return false;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{						
			GestionShared.closeConnection(pm, null);
		} 
	}

	public static Boolean queueNotificationEmpatia(String codeEmpatia) throws UnknownException{						
		PersistenceManager pm=null;				
		try{		
			/**/
		pm = PMF.getPMF().getPersistenceManager();		
		LogicEmpatia logicEmpatia= new LogicEmpatia(pm);
		LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
		LogicTuristaInteres logicTuristaInteres= new LogicTuristaInteres(pm);
		LogicTuristaInteresEmpatia logicTuristaInteresEmpatia= new LogicTuristaInteresEmpatia(pm);		
		LogicNotificacionTurista logicNotificacionTurista=new LogicNotificacionTurista(pm);				
		LogicTipoNotificacion logicTipoNotificacion= new LogicTipoNotificacion(pm);
		LogicMiembro logicMiembro= new LogicMiembro(pm);
		Empatia beanEmpatia= (Empatia) logicEmpatia.getBeanByCode(codeEmpatia);
		if(beanEmpatia== null){
			throw new UnknownException(P.errorNoExistencia(Empatia.class));
		}		
		/* --Listado por orden descendente. el Valor mayor moda se posiciona arriba.. */
		List<TuristaInteres> listInteresTuristaUno= (List<TuristaInteres>) logicTuristaInteres.getListarBean(beanEmpatia.getCodeTuristaUno(), true);
		
		List<TuristaInteres> listInteresTuristaDos= (List<TuristaInteres>) logicTuristaInteres.getListarBean(beanEmpatia.getCodeTuristaDos(), true);
		
		/* --Lista de Intereses Comunes para ambos turistas, ver analisis en Drive.*/
		List<TuristaInteres> listInteresesComunesTuristaUno= new ArrayList<TuristaInteres>();
		List<TuristaInteres> listInteresesComunesTuristaDos= new ArrayList<TuristaInteres>();
		for(int i=0 ; i< listInteresTuristaUno.size();i++){
			for(int j=0 ; j< listInteresTuristaDos.size();j++){
				if(listInteresTuristaUno.get(i).getBeanInteres().equals(listInteresTuristaDos.get(j).getBeanInteres())){
					listInteresesComunesTuristaUno.add(listInteresTuristaUno.get(i));
					continue;
				}
			}
		}
		Collections.sort(listInteresesComunesTuristaUno,new Comparator<TuristaInteres>(){
			@Override
			public int compare(TuristaInteres beanTuristaInteres, TuristaInteres anotherTuristaInteres) {						
				return new Double(beanTuristaInteres.getValorModa()).compareTo(new Double(anotherTuristaInteres.getValorModa()));
			}					
		});			
		for(int i=0 ; i< listInteresTuristaDos.size();i++){
			for(int j=0 ; j< listInteresTuristaUno.size();j++){
				if(listInteresTuristaUno.get(j).getBeanInteres().equals(listInteresTuristaDos.get(i).getBeanInteres())){
					listInteresesComunesTuristaDos.add(listInteresTuristaUno.get(j));
					continue;
				}
			}
		}
		Collections.sort(listInteresesComunesTuristaDos,new Comparator<TuristaInteres>(){
			@Override
			public int compare(TuristaInteres beanTuristaInteres, TuristaInteres anotherTuristaInteres) {						
				return new Double(beanTuristaInteres.getValorModa()).compareTo(new Double(anotherTuristaInteres.getValorModa()));
			}					
		});	
		
		/* Se obtuvo las listas comunes ordenadas para cada turista. */
		
		if(!listInteresesComunesTuristaUno.isEmpty()){
			
			TuristaInteres beanInteresTuristaUno= listInteresesComunesTuristaUno.get(0);
			TuristaInteres beanInteresTuristaDos= listInteresesComunesTuristaDos.get(0);
			
			TuristaInteresEmpatia beanTuristaInteresEmpatia = new TuristaInteresEmpatia();			
			String codeTuristaInteresEmpatia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());		
			beanTuristaInteresEmpatia.setIdTuristaInteresEmpatia(KeyFactory.keyToString(KeyFactory.createKey(TuristaInteresEmpatia.class.getSimpleName(), codeTuristaInteresEmpatia)));				
			beanTuristaInteresEmpatia.setCodeTuristaInteresEmpatia(codeTuristaInteresEmpatia);
			beanTuristaInteresEmpatia.setCodeInteresSugerido1(beanInteresTuristaUno.getCodeInteres());
			beanTuristaInteresEmpatia.setCodeInteresSugerido2(beanInteresTuristaDos.getCodeInteres());
			beanTuristaInteresEmpatia.setCodeMiembroSugiereInteres1(beanEmpatia.getCodeMiembroUno());
			beanTuristaInteresEmpatia.setCodeMiembroSugiereInteres2(beanEmpatia.getCodeMiembroDos());		
			beanTuristaInteresEmpatia.setCodeTuristaSugiereInteres1(beanEmpatia.getCodeTuristaUno());
			beanTuristaInteresEmpatia.setCodeTuristaSugiereInteres2(beanEmpatia.getCodeTuristaDos());		
			beanTuristaInteresEmpatia.setIsPersistente(true);
			beanTuristaInteresEmpatia.setValorModaInteres1(beanInteresTuristaUno.getValorModa());
			beanTuristaInteresEmpatia.setValorModaInteres2(beanInteresTuristaDos.getValorModa());
			beanTuristaInteresEmpatia.setVersion((new java.util.Date()).getTime());
			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setBean(beanTuristaInteresEmpatia);
			beanParametro.setTipoOperacion(P.INSERTAR);
			Boolean rptaTuristaInteresEmpatia= logicTuristaInteresEmpatia.mantenimiento(beanParametro);
			if(!rptaTuristaInteresEmpatia){
				throw new UnknownException(P.ERROR_OPERACION);
			}			
			Notificacion beanNotificacionTuristaUno = generarNotificacionSugerenciaEmpatia(beanTuristaInteresEmpatia,
					beanTuristaInteresEmpatia.getCodeInteresSugerido1(),P.SUGERIR_INTERES_A_MIEMBRO, beanParametro, pm);																	
			UsuarioTurista beanTuristaGeneraNotificacion= logicUsuarioTurista.getBeanByEmail(beanEmpatia.getCodeTuristaUno());			
			StringBuilder title = new StringBuilder();		
			StringBuilder msjTuristaNotificacion = new StringBuilder();		
			StringBuilder body = new StringBuilder();
			if(beanTuristaGeneraNotificacion.getApellido()!=null){
				title.append(beanTuristaGeneraNotificacion.getNombre()+" "+beanTuristaGeneraNotificacion.getApellido());
			}else{
				title.append(beanTuristaGeneraNotificacion.getNombre());
			}					
			msjTuristaNotificacion.append(title.toString());
			TipoNotificacion beanTipoNotificacion = logicTipoNotificacion.getBean(P.SUGERIR_INTERES_A_MIEMBRO);			
			body.append(" te sugiere el siguiente interes "+beanTuristaInteresEmpatia.getCodeInteresSugerido1());
			msjTuristaNotificacion.append(body.toString());																			
			UsuarioTurista beanTuristaRecibeNotificacion= logicUsuarioTurista.getBeanByEmail(beanEmpatia.getCodeTuristaDos());			
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
			beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacionTuristaUno.getCodeNotificacion());
			beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
			beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacionTuristaUno.getFechaGeneroNotificacion());
			beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacionTuristaUno.getBeanTipoNotificacion());
			beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
			beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
			beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
			beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);																																	
			beanParametro.setBean(beanNotificacionTuristaInteractua);
			beanParametro.setTipoOperacion(P.INSERTAR);					
			Boolean rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(beanParametro);
			if(!rptaNotificacionTurista){																		
				throw new UnknownException(P.ERROR_OPERACION);
			}
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
		}catch(Exception ex){														
		}
			Notificacion beanNotificacionTuristaDos = generarNotificacionSugerenciaEmpatia(beanTuristaInteresEmpatia,
					beanTuristaInteresEmpatia.getCodeInteresSugerido2(),P.SUGERIR_INTERES_A_MIEMBRO, beanParametro, pm);																	
			beanTuristaGeneraNotificacion= beanTuristaRecibeNotificacion;			
			if(beanTuristaGeneraNotificacion.getApellido()!=null){
				title.append(beanTuristaGeneraNotificacion.getNombre()+" "+beanTuristaGeneraNotificacion.getApellido());
			}else{
				title.append(beanTuristaGeneraNotificacion.getNombre());
			}	
			body = new StringBuilder();
			body.append(" te sugiere el siguiente interes "+beanTuristaInteresEmpatia.getCodeInteresSugerido2());
			msjTuristaNotificacion.append(body.toString());																											
			beanTuristaRecibeNotificacion= logicUsuarioTurista.getBeanByEmail(beanEmpatia.getCodeTuristaUno());
							        		
			beanNotificacionTuristaInteractua= new NotificacionTurista();
			codeNotificacionTuristaInteractua=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
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
			beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacionTuristaDos.getCodeNotificacion());
			beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
			beanNotificacionTuristaInteractua.setFechaGeneroNotificacion(beanNotificacionTuristaDos.getFechaGeneroNotificacion());
			beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacionTuristaDos.getBeanTipoNotificacion());
			beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
			beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
			beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());	
			beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);																																	
			beanParametro.setBean(beanNotificacionTuristaInteractua);
			beanParametro.setTipoOperacion(P.INSERTAR);					
			rptaNotificacionTurista= logicNotificacionTurista.mantenimiento(beanParametro);
			if(!rptaNotificacionTurista){																		
				throw new UnknownException(P.ERROR_OPERACION);
			}
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
			}catch(Exception ex){												
			}
			Notificacion beanNotificacionTuristaTres = generarNotificacionSugerenciaEmpatia(beanTuristaInteresEmpatia,
					beanTuristaInteresEmpatia.getCodeInteresSugerido1(),P.GENERAR_EMPATIA_INTERES, beanParametro, pm);
			
			Notificacion beanNotificacionTuristaCuatro = generarNotificacionSugerenciaEmpatia(beanTuristaInteresEmpatia,
					beanTuristaInteresEmpatia.getCodeInteresSugerido2(),P.GENERAR_EMPATIA_INTERES, beanParametro, pm);
			
			Miembro beanMiembroUno= (Miembro) logicMiembro.getBeanByCode(beanEmpatia.getCodeMiembroUno());
			Miembro beanMiembroDos=(Miembro) logicMiembro.getBeanByCode(beanEmpatia.getCodeMiembroDos());
			List<Miembro> listMiembros= (List<Miembro>) logicMiembro.getListarBeanByColonia(beanMiembroUno.getCodeColonia());
			
			if(listMiembros.contains(beanMiembroUno)){
				listMiembros.remove(beanMiembroUno);
			}
			if(listMiembros.contains(beanMiembroDos)){
				listMiembros.remove(beanMiembroDos);				
			}
			
			Iterator<Miembro> listMiembrosIterator= listMiembros.iterator();
			List<UsuarioTurista> listTuristasNotificacion= new ArrayList<>();
			while(listMiembrosIterator.hasNext()){
				Miembro beanMiembroIterator= listMiembrosIterator.next();
				UsuarioTurista beanUsuarioTuristaNotificacion= logicUsuarioTurista.getBeanByEmail(beanMiembroIterator.getCodeTurista());
				listTuristasNotificacion.add(beanUsuarioTuristaNotificacion);
				
			}
			
			StringBuilder tituloUno = new StringBuilder();		
			StringBuilder tituloDos = new StringBuilder();		
			StringBuilder msjTuristaNotificacionGEI_Uno = new StringBuilder();		
			StringBuilder msjTuristaNotificacionGEI_Dos = new StringBuilder();		
			
			tituloUno.append(beanTuristaGeneraNotificacion.getNombre()+" "+beanTuristaGeneraNotificacion.getApellido());
			tituloDos.append(beanTuristaRecibeNotificacion.getNombre()+" "+beanTuristaRecibeNotificacion.getApellido());
			msjTuristaNotificacionGEI_Uno.append("La colonia te sugiere el siguiente interes "+beanInteresTuristaUno.getNombreInteres());
			msjTuristaNotificacionGEI_Dos.append("La colonia te sugiere el siguiente interes "+beanInteresTuristaDos.getNombreInteres());
			TipoNotificacion beanTipoNotificacionGEI = logicTipoNotificacion.getBean(P.GENERAR_EMPATIA_INTERES);	
			
			UsuarioTurista beanUsuarioTuristaNotificacionUno = beanTuristaGeneraNotificacion;
			UsuarioTurista beanUsuarioTuristaNotificacionDos= beanTuristaRecibeNotificacion;
			
			Iterator<UsuarioTurista> listTuristasNotificacionIterator= listTuristasNotificacion.iterator();
			while(listTuristasNotificacionIterator.hasNext()){
				UsuarioTurista beanUsuarioTuristaNotificacion= listTuristasNotificacionIterator.next();
				
				NotificacionTurista beanNotificacionTuristaInteractuaUno= new NotificacionTurista();
				String codeNotificacionTuristaInteractuaUno=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanNotificacionTuristaInteractuaUno.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
						(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));					
				beanNotificacionTuristaInteractuaUno.setCodeNotificacionTurista(codeNotificacionTuristaInteractuaUno);																						
				beanNotificacionTuristaInteractuaUno.setIsPersistente(true);
				beanNotificacionTuristaInteractuaUno.setVersion(new java.util.Date().getTime());
				beanNotificacionTuristaInteractuaUno.setBeanTuristaGeneraNotificacion(beanUsuarioTuristaNotificacionUno);
				beanNotificacionTuristaInteractuaUno.setCodeTuristaGeneraNotificacion(beanUsuarioTuristaNotificacionUno.getCodeUsuarioTurista());
				beanNotificacionTuristaInteractuaUno.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTuristaNotificacionUno.getNombre());
				beanNotificacionTuristaInteractuaUno.setApellidoTuristaGeneraNotificacion(beanUsuarioTuristaNotificacionUno.getApellido());
				beanNotificacionTuristaInteractuaUno.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTuristaNotificacionUno.getFotoPerfil());					
				beanNotificacionTuristaInteractuaUno.setMensajeNotificacion(msjTuristaNotificacionGEI_Uno.toString());						
				beanNotificacionTuristaInteractuaUno.setCodeNotificacion(beanNotificacionTuristaTres.getCodeNotificacion());
				beanNotificacionTuristaInteractuaUno.setTokenFirebase(beanUsuarioTuristaNotificacion.getTokenFirebase());
				beanNotificacionTuristaInteractuaUno.setFechaGeneroNotificacion(beanNotificacionTuristaUno.getFechaGeneroNotificacion());
				beanNotificacionTuristaInteractuaUno.setBeanTipoNotificacion(beanTipoNotificacionGEI);
				beanNotificacionTuristaInteractuaUno.setCodeTipoNotificacion(beanTipoNotificacionGEI.getCodeTipoNotificacion());
				beanNotificacionTuristaInteractuaUno.setBeanTuristaRecibeNotificacion(beanUsuarioTuristaNotificacion);
				beanNotificacionTuristaInteractuaUno.setCodeTuristaRecibeNotificacion(beanUsuarioTuristaNotificacion.getCodeUsuarioTurista());	
				beanNotificacionTuristaInteractuaUno.setVisto(P.NO_VISTO);																																	
				beanParametro.setBean(beanNotificacionTuristaInteractuaUno);
				beanParametro.setTipoOperacion(P.INSERTAR);					
				Boolean rptaNotificacionTuristaUno= logicNotificacionTurista.mantenimiento(beanParametro);
				if(!rptaNotificacionTuristaUno){																		
					throw new UnknownException(P.ERROR_OPERACION);
				}
				
				try{
					if(beanNotificacionTuristaInteractuaUno.getTokenFirebase()!=null && 
							!beanNotificacionTuristaInteractuaUno.getTokenFirebase().isEmpty()){
						NotificationMessage not=new NotificationMessage();
						not.setTargetTo(beanNotificacionTuristaInteractuaUno.getBeanTuristaRecibeNotificacion().getTokenFirebase());
						not.setOptionRestrictedPackageName("com.indiant");
				    	not.setOptionPriority(10);  	   
				    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
				        payLoad.setSound("default");
				        DataPayLoad dataPayLoad=new DataPayLoad();
				    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractuaUno.getCodeNotificacionTurista());
				    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractuaUno.getCodeTipoNotificacion()!=null?beanNotificacionTuristaInteractuaUno.getCodeTipoNotificacion():"");
				    	dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractuaUno.getCodeNoticia()!=null?beanNotificacionTuristaInteractuaUno.getCodeNoticia():"");
				    	dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractuaUno.getCodeDestino()!=null?beanNotificacionTuristaInteractuaUno.getCodeDestino():"");
				    	dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractuaUno.getCodeColonia()!=null?beanNotificacionTuristaInteractuaUno.getCodeColonia():"");
				    	dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractuaUno.getNombreColonia()!=null?beanNotificacionTuristaInteractuaUno.getNombreColonia():"");
				    	dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractuaUno.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractuaUno.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
				    	dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractuaUno.getBeanNoticia()!=null?beanNotificacionTuristaInteractuaUno.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTuristaInteractuaUno.getBeanNoticia().getFotoConquistaPublicidad():"":"");
				    	dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractuaUno.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractuaUno.getCodeTuristaGeneraNotificacion():"");
				    	dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractuaUno.getBeanTuristaRecibeNotificacion().getTokenFirebase());
				    	payLoad.setTitle("Indiant");
				    	payLoad.setBody(beanNotificacionTuristaInteractuaUno.getMensajeNotificacion());		    			    	
				    	not.setPayLoadData(dataPayLoad.buildPayLoad());
				    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
				    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
				    	cnx.sendNotificationHttp(not);				
					}						
				}catch(Exception ex){																	
				}
				
				NotificacionTurista beanNotificacionTuristaInteractuaDos= new NotificacionTurista();
				String codeNotificacionTuristaInteractuaDos=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanNotificacionTuristaInteractuaDos.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey
						(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));					
				beanNotificacionTuristaInteractuaDos.setCodeNotificacionTurista(codeNotificacionTuristaInteractuaDos);																						
				beanNotificacionTuristaInteractuaDos.setIsPersistente(true);
				beanNotificacionTuristaInteractuaDos.setVersion(new java.util.Date().getTime());
				beanNotificacionTuristaInteractuaDos.setBeanTuristaGeneraNotificacion(beanUsuarioTuristaNotificacionDos);
				beanNotificacionTuristaInteractuaDos.setCodeTuristaGeneraNotificacion(beanUsuarioTuristaNotificacionDos.getCodeUsuarioTurista());
				beanNotificacionTuristaInteractuaDos.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTuristaNotificacionDos.getNombre());
				beanNotificacionTuristaInteractuaDos.setApellidoTuristaGeneraNotificacion(beanUsuarioTuristaNotificacionDos.getApellido());
				beanNotificacionTuristaInteractuaDos.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTuristaNotificacionDos.getFotoPerfil());					
				beanNotificacionTuristaInteractuaDos.setMensajeNotificacion(msjTuristaNotificacionGEI_Dos.toString());						
				beanNotificacionTuristaInteractuaDos.setCodeNotificacion(beanNotificacionTuristaCuatro.getCodeNotificacion());
				beanNotificacionTuristaInteractuaDos.setTokenFirebase(beanUsuarioTuristaNotificacion.getTokenFirebase());
				beanNotificacionTuristaInteractuaDos.setFechaGeneroNotificacion(beanNotificacionTuristaUno.getFechaGeneroNotificacion());
				beanNotificacionTuristaInteractuaDos.setBeanTipoNotificacion(beanTipoNotificacionGEI);
				beanNotificacionTuristaInteractuaDos.setCodeTipoNotificacion(beanTipoNotificacionGEI.getCodeTipoNotificacion());
				beanNotificacionTuristaInteractuaDos.setBeanTuristaRecibeNotificacion(beanUsuarioTuristaNotificacion);
				beanNotificacionTuristaInteractuaDos.setCodeTuristaRecibeNotificacion(beanUsuarioTuristaNotificacion.getCodeUsuarioTurista());	
				beanNotificacionTuristaInteractuaDos.setVisto(P.NO_VISTO);																																	
				beanParametro.setBean(beanNotificacionTuristaInteractuaDos);
				beanParametro.setTipoOperacion(P.INSERTAR);					
				Boolean rptaNotificacionTuristaDos= logicNotificacionTurista.mantenimiento(beanParametro);
				if(!rptaNotificacionTuristaDos){																		
					throw new UnknownException(P.ERROR_OPERACION);
				}				
				try{
					if(beanNotificacionTuristaInteractuaDos.getTokenFirebase()!=null && 
							!beanNotificacionTuristaInteractuaDos.getTokenFirebase().isEmpty()){
						NotificationMessage not=new NotificationMessage();
						not.setTargetTo(beanNotificacionTuristaInteractuaDos.getBeanTuristaRecibeNotificacion().getTokenFirebase());
						not.setOptionRestrictedPackageName("com.indiant");
				    	not.setOptionPriority(10);  	   
				    	AndroidNotificationPayLoad payLoad=new AndroidNotificationPayLoad();	    	
				        payLoad.setSound("default");
				        DataPayLoad dataPayLoad=new DataPayLoad();
				    	dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractuaDos.getCodeNotificacionTurista());
				    	dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractuaDos.getCodeTipoNotificacion()!=null?beanNotificacionTuristaInteractuaDos.getCodeTipoNotificacion():"");
				    	dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractuaDos.getCodeNoticia()!=null?beanNotificacionTuristaInteractuaDos.getCodeNoticia():"");
				    	dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractuaDos.getCodeDestino()!=null?beanNotificacionTuristaInteractuaDos.getCodeDestino():"");
				    	dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractuaDos.getCodeColonia()!=null?beanNotificacionTuristaInteractuaDos.getCodeColonia():"");
				    	dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractuaDos.getNombreColonia()!=null?beanNotificacionTuristaInteractuaDos.getNombreColonia():"");
				    	dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractuaDos.getBeanTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractuaDos.getBeanTuristaGeneraNotificacion().getFotoPerfil():"");
				    	dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractuaDos.getBeanNoticia()!=null?beanNotificacionTuristaInteractuaDos.getBeanNoticia().getFotoConquistaPublicidad()!=null?beanNotificacionTuristaInteractuaDos.getBeanNoticia().getFotoConquistaPublicidad():"":"");
				    	dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractuaDos.getCodeTuristaGeneraNotificacion()!=null?beanNotificacionTuristaInteractuaDos.getCodeTuristaGeneraNotificacion():"");
				    	dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractuaDos.getBeanTuristaRecibeNotificacion().getTokenFirebase());
				    	payLoad.setTitle("Indiant");
				    	payLoad.setBody(beanNotificacionTuristaInteractuaDos.getMensajeNotificacion());		    			    	
				    	not.setPayLoadData(dataPayLoad.buildPayLoad());
				    	not.setPayLoadNotification(payLoad.buildPayLoadAndroid());   		    	
				    	HttpFcmConection cnx=new HttpFcmConection(P.FIREBASE_SERVER_KEY,EnumContentType.JSON.getValue());
				    	cnx.sendNotificationHttp(not);				
					}						
				}catch(Exception ex){																	
				}
				
			}																		
		}
		
		/* NOTIFICAR A TODOS LOS MIEMBROS DE LA COLONIA CON GENERAR EMPATIA MIEMBROS DONDE LE LLEGA LOS DOS INTERESES ELEGIDOS..
		 * ANALIZAR.. */
			return false;
	}catch(Exception ex){
		throw new UnknownException(ex.getLocalizedMessage());
	}finally{						
		GestionShared.closeConnection(pm, null);
	}		
		}
	
	
	
	private static Notificacion generarNotificacionSugerenciaColonia(SugerenciaColonia beanSugerenciaColonia,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{	
		LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
		/* -- Pendiente de analisis.. */
		Notificacion beanNotificacion= new Notificacion();
		String codeNotificacion= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
		beanNotificacion.setCodeNotificacion(codeNotificacion);
		
		UsuarioTurista beanUsuarioTurista= beanSugerenciaColonia.getBeanTuristaEmisor();																
		beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
		beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
		beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());				
	
		TipoNotificacion beanTipoNotificacion= new TipoNotificacion();
		beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean("SCO");
		if(beanTipoNotificacion==null){
			throw new UnknownException("No se encontro el TipoNotificacion ");
		}	
		
		beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
		beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());			
		beanNotificacion.setCodeSugerenciaColonia(beanSugerenciaColonia.getCodeSugerenciaColonia());				
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
		
		private static Notificacion generarNotificacionSugerenciaEmpatia(TuristaInteresEmpatia beanTuristaInteresEmpatia,String codeInteres,String codeTipoNotificacion,BeanParametro beanParametro,PersistenceManager pm) throws UnknownException{	
			LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
			Notificacion beanNotificacion= new Notificacion();
			String codeNotificacion= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
			beanNotificacion.setCodeNotificacion(codeNotificacion);
			
			UsuarioTurista beanUsuarioTurista= beanTuristaInteresEmpatia.getBeanTuristaSugiereInteres1();/*verificar si es el turist1*/																				
			beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
			beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
			beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
			beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
			beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());									
			beanNotificacion.setCodeTuristaInteresEmpatia(beanTuristaInteresEmpatia.getCodeTuristaInteresEmpatia());	
			beanNotificacion.setCodeInteres(codeInteres);
			beanNotificacion.setNombreInteres(codeInteres);
			
			beanNotificacion.setEstadoTarea(P.PENDIENTE);
			beanNotificacion.setIsPersistente(true);	
			TipoNotificacion beanTipoNotificacion= new TipoNotificacion();
			beanTipoNotificacion= (TipoNotificacion)new LogicTipoNotificacion(pm).getBean(codeTipoNotificacion);
			if(beanTipoNotificacion==null){
				throw new UnknownException("No se encontro el TipoNotificacion ");
			}				
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
		
	public static Boolean queueMuroNoticiaTablonColonia(String codeNoticia) throws UnknownException{							
			/* --De aca tenemos que obtener los usuarios o miembros que son parte de la colonia del turista.
			 * Analizar el algoritmo del metodo lanzarNovedadColonia() ..*/
			PersistenceManager pm=null;				
			try{
			pm = PMF.getPMF().getPersistenceManager();			
			LogicNoticia logicNoticia= new LogicNoticia(pm);
			LogicMuroNoticia logicMuroNoticia= new LogicMuroNoticia(pm);	
			Noticia beanNoticia= (Noticia)logicNoticia.getBeanByCode(codeNoticia);					
			if(beanNoticia== null){
				throw new UnknownException("No se existe Noticia");
			}
			Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();					
			UsuarioTurista beanTuristaGeneraNoticia= beanNoticia.getBeanTuristaGeneraNoticia();		
			String codeNoticiaActual= beanNoticia.getCodeNoticia();
			String codeTuristaPublica = beanTuristaGeneraNoticia.getCodeUsuarioTurista(),codeTuristaGenera= beanTuristaGeneraNoticia.getCodeUsuarioTurista();
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
			Long rangoFecha=beanGregorianCalendar.getTime().getTime();		
			queueTuristas.addAll(GestionShared.busquedaByDCS(codeTuristaPublica, codeNoticiaActual, codeTuristaGenera, rangoFecha.toString(), pm));
			queueTuristas.addAll(GestionShared.busquedaByEmpatias(codeTuristaPublica, codeTuristaGenera, rangoFecha.toString(), pm));				
			if(queueTuristas.isEmpty()){			
				codeParametroSistema="CPAN";
				queueTuristas.addAll(GestionShared.busquedaByFriends(codeTuristaGenera, codeParametroSistema, pm));
				if(queueTuristas.isEmpty()){ // Si No hay usuarios a notificar se le mostrara la noticia a los miembros de la colonia..				
					queueTuristas.addAll(GestionShared.busquedaByMembers(beanNoticia.getCodeColonia(), pm));
				}
			}
			if(queueTuristas.contains(beanTuristaGeneraNoticia)){
				queueTuristas.remove(beanTuristaGeneraNoticia);
			}
			List<MuroNoticia> listParamMuroNoticia=new ArrayList<MuroNoticia>();
			Iterator<UsuarioTurista> listQueueUsuarioIterator= queueTuristas.iterator();		
				while(listQueueUsuarioIterator.hasNext()){
					UsuarioTurista beanTuristaMuro= listQueueUsuarioIterator.next();
					MuroNoticia beanMuroNoticia= new MuroNoticia();
					String codeMuroNoticia=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());	
					Key keyNoticia=KeyFactory.createKey(Noticia.class.getSimpleName(), codeNoticia);
					String idMuroNoticia=KeyFactory.keyToString( KeyFactory.createKey(keyNoticia,MuroNoticia.class.getSimpleName(),codeMuroNoticia));
					beanMuroNoticia.setIdMuroNoticia(idMuroNoticia);
					beanMuroNoticia.setCodeMuroNoticia(codeMuroNoticia);
					beanMuroNoticia.setBeanTuristaMuro(beanTuristaMuro);
					beanMuroNoticia.setCodeTuristaMuro(beanTuristaMuro.getCodeUsuarioTurista());
					beanMuroNoticia.setCorreoTuristaMuro(beanTuristaMuro.getCorreoTurista());							
					beanMuroNoticia.setBeanNoticia(beanNoticia);
					beanMuroNoticia.setCodeNoticia(codeNoticia);
					beanMuroNoticia.setBeanColonia(beanNoticia.getBeanColonia());
					beanMuroNoticia.setCodeColonia(beanNoticia.getCodeColonia());
					beanMuroNoticia.setBeanMiembro(beanNoticia.getBeanMiembro());
					beanMuroNoticia.setCodeMiembro(beanNoticia.getCodeMiembro());
					beanMuroNoticia.setFechaPublicacion(beanNoticia.getFechaPublicacion());
					beanMuroNoticia.setBeanTuristaGeneraNoticia(beanTuristaGeneraNoticia);
					beanMuroNoticia.setCodeTuristaGeneraNoticia(beanTuristaGeneraNoticia.getCodeUsuarioTurista());
					beanMuroNoticia.setNombreTuristaNegocioGeneraNoticia(beanTuristaGeneraNoticia.getNombre());
					beanMuroNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanTuristaGeneraNoticia.getFotoPerfil());
					beanMuroNoticia.setCorreoTuristaGeneraNoticia(beanTuristaGeneraNoticia.getCorreoTurista());
					beanMuroNoticia.setLongitud(beanNoticia.getLongitud());
					beanMuroNoticia.setLatitud(beanNoticia.getLatitud());
					beanMuroNoticia.setTotalComentario(0);
					beanMuroNoticia.setTotalCompartido(0);
					beanMuroNoticia.setTotalDivulgado(0);
					beanMuroNoticia.setEnlace(beanNoticia.getEnlace());
					beanMuroNoticia.setDescripcion(beanNoticia.getDescripcion());
					beanMuroNoticia.setTipoNoticia(beanNoticia.getTipoNoticia());			
					beanMuroNoticia.setVersion((new java.util.Date()).getTime());
					beanMuroNoticia.setIsPersistente(true);
					beanMuroNoticia.setVisto(P.NO_VISTO);		
					beanMuroNoticia.setFotoConquistaPublicidad(beanNoticia.getFotoConquistaPublicidad());				
					listParamMuroNoticia.add(beanMuroNoticia);
				}	
				Collection<BeanParametro> listParametros=new ArrayList<BeanParametro>();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(listParamMuroNoticia);
				parametro.setTipoOperacion(P.INSERTAR);
				listParametros.add(parametro);
				Boolean rptaMuroNoticia= logicMuroNoticia.mantenimiento(listParametros);				
				if(!rptaMuroNoticia){
					throw new UnknownException("No se publico en el Muro");
				}					
				beanNoticia.setEstadoTarea(P.EJECUTADO);
				BeanParametro beanParametro= new BeanParametro();
				beanParametro.setBean(beanNoticia);
				beanParametro.setTipoOperacion(P.ACTUALIZAR);
				Boolean rptaNoticia= logicNoticia.mantenimiento(beanParametro);			
				if(rptaNoticia){							
					return true;
				}
				return false;
			}catch(Exception ex){
				throw new UnknownException(ex.getLocalizedMessage());
			}finally{						
				GestionShared.closeConnection(pm, null);
			}  
	}
}