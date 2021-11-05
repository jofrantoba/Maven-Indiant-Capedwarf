package com.indiana.server.model.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.bean.ComparteNoticia;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.CuentaNegocio;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.DestinoCalificaDestino;
import com.indiana.server.model.bean.DivulgacionNoticia;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.MuroNoticia;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.bean.NotificacionTurista;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.bean.Region;
import com.indiana.server.model.bean.SesionNegocio;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicComentarioNoticia;
import com.indiana.server.model.logic.LogicComparteNoticia;
import com.indiana.server.model.logic.LogicConquista;
import com.indiana.server.model.logic.LogicCuentaNegocio;
import com.indiana.server.model.logic.LogicDestino;
import com.indiana.server.model.logic.LogicDivulgacionNoticia;
import com.indiana.server.model.logic.LogicMuroNoticia;
import com.indiana.server.model.logic.LogicNoticia;
import com.indiana.server.model.logic.LogicNotificacion;
import com.indiana.server.model.logic.LogicNotificacionTurista;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicSesionNegocio;
import com.indiana.server.model.logic.LogicTipoNotificacion;
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

public class GestionBussines {

    /**
     * CODIGO: KCYS-165</br>
     * CUS: CUS_LOGIN_NEGOCIO - ANS_NEGOCIO</br>
     * ESTADO: POR TESTEAR</br>
     */
    public static SesionNegocio loginNegocio(String correoNegocio, String claveNegocio) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            CuentaNegocio beanCuentaNegocio = validarCuentaNegocio(correoNegocio, claveNegocio, pm);
            UsuarioNegocio beanUsuarioNegocio = beanCuentaNegocio.getBeanUsuarioNegocio();
            SesionNegocio beanSesionNegocio = insertarSesionNegocio(beanUsuarioNegocio, pm);
            if (beanSesionNegocio != null) {
                throw new UnknownException("Error al crear la sesion");
            }
            tx.commit();
            pm.close();
            return beanSesionNegocio;
        } catch (UnknownException ex) {
            throw ex;
        } finally {
            GestionShared.closeConnection(pm, tx);
        }
    }

    public static MuroNoticia crearNegocio(Conquista beanConquista) throws UnknownException {
        //Titulo, descripcion , radio ..urlfoto, comentario conquista.. 		
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicDestino logicDestino = new LogicDestino(pm);
            LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
            BeanParametro beanParametro = new BeanParametro();
            Destino beanDestino = beanConquista.getBeanDestino();
            if (beanDestino != null
                    && beanDestino.getLatitud() != null
                    && beanDestino.getLongitud() != null
                    && beanDestino.getRadio() > 0
                    && !beanDestino.getNombre().isEmpty()) {
                String codigoAleatorioGenerico = java.util.UUID.randomUUID().toString();
                String strCodeDestino = StringHex.convertStringToHex(String.valueOf(codigoAleatorioGenerico));
                beanDestino.setIdDestino(KeyFactory.keyToString(KeyFactory.createKey(Destino.class.getSimpleName(), strCodeDestino)));
                beanDestino.setCodeDestino(strCodeDestino);
                UsuarioTurista beanUsuarioTuristaConquistador = logicUsuarioTurista.getBean(beanConquista.getBeanTuristaConquista().getCodeUsuarioTurista());
                if (beanUsuarioTuristaConquistador != null) {
                    beanDestino.setBeanTuristaCreador(beanUsuarioTuristaConquistador);
                    beanDestino.setNombreCreador(beanUsuarioTuristaConquistador.getNombre());
                    beanDestino.setFotoPerfilCreador(beanUsuarioTuristaConquistador.getFotoPerfil());
                    beanDestino.setApellidoCreador(beanUsuarioTuristaConquistador.getApellido());
                    beanDestino.setCodeTuristaCreador(beanUsuarioTuristaConquistador.getCodeUsuarioTurista());
                    beanDestino.setTotalConquistas(1);
                    beanDestino.setTipoNegocioDestino(P.NEGOCIO);
                    beanDestino.setFechaCreacion(new java.util.Date());
                    beanDestino.setPromedioCalificacion(0.0);
                    beanDestino.setListaCalificaciones(new ArrayList<DestinoCalificaDestino>());
                    beanDestino.setUrlFotoPrincipal(beanConquista.getFotoConquista());
                    Pais beanPais = GestionShared.insertarPais(beanConquista.getBeanPais(), pm);
                    if (beanPais != null) {
                        beanDestino.setBeanPais(beanPais);
                        beanDestino.setCodePais(beanPais.getCodePais());
                        beanDestino.setNombrePais(beanPais.getNombre());
                        if (beanConquista.getBeanRegion() != null) {
                            beanConquista.getBeanRegion().setBeanPais(beanPais);
                            beanConquista.getBeanRegion().setNombrePais(beanPais.getNombre());
                            beanConquista.getBeanRegion().setCodePais(beanPais.getCodePais());
                        }
                        Region beanRegion = GestionShared.insertarRegion(beanConquista.getBeanRegion(), pm);
                        if (beanRegion != null) {
                            beanDestino.setBeanRegion(beanRegion);
                            beanDestino.setCodeRegion(beanRegion.getCodeRegion());
                            beanDestino.setNombreRegion(beanRegion.getNombre());
                            if (beanConquista.getBeanLocalidad() != null) {
                                beanConquista.getBeanLocalidad().setBeanPais(beanPais);
                                beanConquista.getBeanLocalidad().setNombrePais(beanPais.getNombre());
                                beanConquista.getBeanLocalidad().setCodePais(beanPais.getCodePais());
                                beanConquista.getBeanLocalidad().setBeanRegion(beanRegion);
                                beanConquista.getBeanLocalidad().setNombreRegion(beanRegion.getNombre());
                                beanConquista.getBeanLocalidad().setCodeRegion(beanRegion.getCodeRegion());
                            }
                            Localidad beanLocalidad = GestionShared.insertarLocalidad(beanConquista.getBeanLocalidad(), pm);
                            if (beanLocalidad != null) {
                                beanDestino.setBeanLocalidad(beanLocalidad);
                                beanDestino.setCodeLocalidad(beanLocalidad.getCodeLocalidad());
                                beanDestino.setNombreLocalidad(beanLocalidad.getNombre());
                            } else {
                                beanDestino.setBeanLocalidad(null);
                                beanDestino.setCodeLocalidad(null);
                                beanDestino.setNombreLocalidad(null);
                            }
                        } else {
                            beanDestino.setBeanRegion(null);
                            beanDestino.setCodeRegion(null);
                            beanDestino.setNombreRegion(null);
                            beanDestino.setBeanLocalidad(null);
                            beanDestino.setCodeLocalidad(null);
                            beanDestino.setNombreLocalidad(null);
                        }
                    } else {
                        beanDestino.setBeanPais(null);
                        beanDestino.setCodePais(null);
                        beanDestino.setNombrePais(null);
                        beanDestino.setBeanRegion(null);
                        beanDestino.setCodeRegion(null);
                        beanDestino.setNombreRegion(null);
                        beanDestino.setBeanLocalidad(null);
                        beanDestino.setCodeLocalidad(null);
                        beanDestino.setNombreLocalidad(null);
                    }
                    beanDestino.setVersion(new java.util.Date().getTime());
                    beanDestino.setIsPersistente(true);
                    beanParametro.setBean(beanDestino);
                    beanParametro.setTipoOperacion(P.INSERTAR);
                    Destino beanDestinoBd = pm.detachCopy(logicDestino.mantenimientoReturn(beanParametro));
                    if (beanDestinoBd != null) {
                        beanConquista.setBeanDestino(beanDestinoBd);
                        beanConquista.setCodeDestino(beanDestinoBd.getCodeDestino());
                        Conquista beanConquistaBd = conquistarCrearNegocio(beanConquista, beanParametro, pm);
                        if (beanConquistaBd != null) {
                            beanUsuarioTuristaConquistador.setTotalConquistasNegocios(beanUsuarioTuristaConquistador.getTotalConquistasNegocios() == null ? 1 : beanUsuarioTuristaConquistador.getTotalConquistasNegocios() + 1);
                            beanUsuarioTuristaConquistador.setTotalNegociosCreados(beanUsuarioTuristaConquistador.getTotalNegociosCreados() == null ? 1 : beanUsuarioTuristaConquistador.getTotalNegociosCreados() + 1);
                            beanParametro.setBean(beanUsuarioTuristaConquistador);
                            beanParametro.setTipoOperacion(P.ACTUALIZAR);
                            UsuarioTurista usuarioTuristaBd = logicUsuarioTurista.mantenimientoReturn(beanParametro);
                            if (usuarioTuristaBd != null) {
                                beanConquista.setBeanTuristaConquista(usuarioTuristaBd);
                                MuroNoticia beanMuroNoticiaBd = generaNoticia(beanConquista, beanParametro, pm);
                                if (beanMuroNoticiaBd != null) {
                                    /*Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
									queueMuroNoticia.add(TaskOptions.Builder
								            .withUrl("/taskMuroNoticiaCrearNegocio")
								            .param("codeNoticia", beanMuroNoticiaBd.getCodeNoticia()).header("Host", P.HOST));*/
                                    //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));

                                    Notificacion beanNotificacion = generaNotificacion(beanMuroNoticiaBd.getBeanNoticia(), "DNN", beanParametro, pm);
                                    if (beanNotificacion != null) {
                                        /*Queue queue = QueueFactory.getQueue("notificacion-queuepush");
										queue.add(TaskOptions.Builder
									            .withUrl("/taskNotificacionCrearNegocio")
									            .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));*/
                                        //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));

                                        Boolean rptaControlPosicion = GestionTurista.generaControlPosicion(beanUsuarioTuristaConquistador, beanDestino.getLatitud(), beanDestino.getLongitud(), null, beanDestino.getNombrePais(), beanDestino.getNombreRegion(), beanDestino.getNombreLocalidad(), pm);
                                        if (rptaControlPosicion) {
                                            tx.commit();
                                            pm.close();
                                            Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
                                            queueMuroNoticia.add(TaskOptions.Builder
                                                    .withUrl("/taskMuroNoticiaCrearNegocio")
                                                    .param("codeNoticia", beanMuroNoticiaBd.getCodeNoticia()).header("Host", P.HOST));
                                            Queue queue = QueueFactory.getQueue("notificacion-queuepush");
                                            queue.add(TaskOptions.Builder
                                                    .withUrl("/taskNotificacionCrearNegocio")
                                                    .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));
                                            beanMuroNoticiaBd.setBeanNoticia(null);
                                            return beanMuroNoticiaBd;
                                        } else {
                                            throw new UnknownException("No se pudo guardar posicion del turista");
                                        }
                                    } else {
                                        throw new UnknownException("No se pudo generar notificacion");
                                    }
                                } else {
                                    throw new UnknownException("No se pudo generar noticia");
                                }
                            } else {
                                throw new UnknownException("No se pudo actualizar contador del conquistas del turista");
                            }
                        } else {
                            throw new UnknownException("No se pudo conquistar");
                        }
                    } else {
                        throw new UnknownException("No se pudo crear Negocio");
                    }
                } else {
                    throw new UnknownException("No existe conquistador");
                }
            } else {
                throw new UnknownException("Enviar datos minimos para crear el Negocio");
            }
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, tx);
        }
    }

    private static Conquista conquistarCrearNegocio(Conquista beanConquista, BeanParametro beanParametro, PersistenceManager pm) throws UnknownException {
        LogicConquista logicConquista = new LogicConquista(pm);
        Destino beanDestino = beanConquista.getBeanDestino();
        UsuarioTurista beanUsuarioTurista = beanDestino.getBeanTuristaCreador();
        String codigoAleatorioGenerico = java.util.UUID.randomUUID().toString();
        String codeConquista = StringHex.convertStringToHex(codigoAleatorioGenerico);
        beanConquista.setIdConquista(KeyFactory.keyToString(KeyFactory.createKey(Conquista.class.getSimpleName(), codeConquista)));
        beanConquista.setCodeConquista(codeConquista);
        beanConquista.setNombreDestinoNegocio(beanDestino.getNombre());
        beanConquista.setCodeDestino(beanDestino.getCodeDestino());
        beanConquista.setBeanTuristaConquista(beanUsuarioTurista);
        beanConquista.setApellidoTuristaConquista(beanUsuarioTurista.getApellido());
        beanConquista.setCodeTuristaConquista(beanUsuarioTurista.getCodeUsuarioTurista());
        beanConquista.setNombreTuristaConquista(beanUsuarioTurista.getNombre());
        beanConquista.setFotoPerfilTuristaConquista(beanUsuarioTurista.getFotoPerfil());
        if (beanDestino.getBeanPais() != null) {
            Pais beanPais = beanDestino.getBeanPais();
            beanConquista.setBeanPais(beanPais);
            beanConquista.setCodePais(beanPais.getCodePais());
            if (beanDestino.getBeanRegion() != null) {
                Region beanRegion = beanDestino.getBeanRegion();
                beanConquista.setBeanRegion(beanRegion);
                beanConquista.setCodeRegion(beanRegion.getCodeRegion());
                if (beanDestino.getBeanLocalidad() != null) {
                    Localidad beanLocalidad = beanDestino.getBeanLocalidad();
                    beanConquista.setBeanLocalidad(beanLocalidad);
                    beanConquista.setCodeLocalidad(beanLocalidad.getCodeLocalidad());
                } else {
                    beanConquista.setBeanLocalidad(null);
                    beanConquista.setCodeLocalidad(null);
                }
            } else {
                beanConquista.setBeanRegion(null);
                beanConquista.setCodeRegion(null);
                beanConquista.setBeanLocalidad(null);
                beanConquista.setCodeLocalidad(null);
            }
        } else {
            beanConquista.setBeanPais(null);
            beanConquista.setCodePais(null);
            beanConquista.setBeanRegion(null);
            beanConquista.setCodeRegion(null);
            beanConquista.setBeanLocalidad(null);
            beanConquista.setCodeLocalidad(null);
        }
        beanConquista.setLatitud(beanDestino.getLatitud());
        beanConquista.setLongitud(beanDestino.getLongitud());
        beanConquista.setTipoConquista(P.CONQUISTA_NEGOCIO);//CONQUISTA NEGOCIO	
        beanConquista.setVersion((new java.util.Date()).getTime());
        beanConquista.setIsPersistente(true);
        beanConquista.setFechaConquista(beanDestino.getFechaCreacion());
        beanParametro.setBean(beanConquista);
        beanParametro.setTipoOperacion(P.INSERTAR);
        return logicConquista.mantenimientoReturn(beanParametro);
    }

    public static MuroNoticia conquistarNegocios(String correoTurista, Double latitudTurista, Double longitudTurista,
            String codeDestino, String urlFotoConquista, String descripcion) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            if (correoTurista == null || correoTurista.isEmpty() || latitudTurista == null || longitudTurista == null
                    || codeDestino == null || codeDestino.isEmpty() || urlFotoConquista == null
                    || urlFotoConquista.isEmpty()) {
                throw new UnknownException(P.PARAMETROS_INVALIDOS);
            }
            pm = PMF.getPMF().getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicDestino logicDestino = new LogicDestino(pm);
            LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
            LogicConquista logicConquista = new LogicConquista(pm);

            UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoTurista);
            if (beanUsuarioTurista == null) {
                throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
            }
            Destino beanDestino = (Destino) logicDestino.getBeanByCode(codeDestino);
            if (beanDestino == null) {
                throw new UnknownException(P.ERROR_NO_EXISTENCIA);
            }
            if (!GestionShared.dentroPerimetroDestinoNegocioColonia(latitudTurista, longitudTurista, beanDestino.getLatitud(),
                    beanDestino.getLongitud(), beanDestino.getRadio())) {
                throw new UnknownException(P.FUERA_RANGO);
            }
            Conquista beanConquista = new Conquista();
            String codeConquista = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
            beanConquista.setIdConquista(KeyFactory.keyToString(KeyFactory.createKey(Conquista.class.getSimpleName(), codeConquista)));
            beanConquista.setCodeConquista((codeConquista));
            beanConquista.setBeanDestino(beanDestino);
            beanConquista.setCodeDestino(beanDestino.getCodeDestino());
            beanConquista.setNombreDestinoNegocio(beanDestino.getNombre());
            beanConquista.setLatitud(beanDestino.getLatitud());
            beanConquista.setLongitud(beanDestino.getLongitud());

            beanConquista.setBeanTuristaConquista(beanUsuarioTurista);
            beanConquista.setApellidoTuristaConquista(beanUsuarioTurista.getApellido());
            beanConquista.setCodeTuristaConquista(beanUsuarioTurista.getCodeUsuarioTurista());
            beanConquista.setNombreTuristaConquista(beanUsuarioTurista.getNombre());
            beanConquista.setFotoPerfilTuristaConquista(beanUsuarioTurista.getFotoPerfil());
            if (beanDestino.getBeanPais() != null) {
                Pais beanPais = beanDestino.getBeanPais();
                beanConquista.setBeanPais(beanPais);
                beanConquista.setCodePais(beanPais.getCodePais());
                if (beanDestino.getBeanRegion() != null) {
                    Region beanRegion = beanDestino.getBeanRegion();
                    beanConquista.setBeanRegion(beanRegion);
                    beanConquista.setCodeRegion(beanRegion.getCodeRegion());
                    if (beanDestino.getBeanLocalidad() != null) {
                        Localidad beanLocalidad = beanDestino.getBeanLocalidad();
                        beanConquista.setBeanLocalidad(beanLocalidad);
                        beanConquista.setCodeLocalidad(beanLocalidad.getCodeLocalidad());
                    } else {
                        beanConquista.setBeanLocalidad(null);
                        beanConquista.setCodeLocalidad(null);
                    }
                } else {
                    beanConquista.setBeanRegion(null);
                    beanConquista.setCodeRegion(null);
                    beanConquista.setBeanLocalidad(null);
                    beanConquista.setCodeLocalidad(null);
                }
            } else {
                beanConquista.setBeanPais(null);
                beanConquista.setCodePais(null);
                beanConquista.setBeanRegion(null);
                beanConquista.setCodeRegion(null);
                beanConquista.setBeanLocalidad(null);
                beanConquista.setCodeLocalidad(null);
            }
            beanConquista.setTipoConquista(P.CONQUISTA_NEGOCIO);
            beanConquista.setFechaConquista(new java.util.Date());
            beanConquista.setVersion(new java.util.Date().getTime());
            beanConquista.setFotoConquista(urlFotoConquista);
            beanConquista.setDescripcion(descripcion);
            beanConquista.setIsPersistente(true);
            BeanParametro beanParametro = new BeanParametro();
            beanParametro.setBean(beanConquista);
            beanParametro.setTipoOperacion(P.INSERTAR);
            Boolean rptaConquista = logicConquista.mantenimiento(beanParametro);
            if (!rptaConquista) {
                throw new UnknownException(P.ERROR_OPERACION);
            }

            beanDestino.setTotalConquistas(beanDestino.getTotalConquistas() + 1);
            beanDestino.setUrlFotoPrincipal(beanConquista.getFotoConquista());
            beanParametro.setBean(beanDestino);
            beanParametro.setTipoOperacion(P.ACTUALIZAR);
            Boolean rptaDestino = logicDestino.mantenimiento(beanParametro);
            if (!rptaDestino) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            Conquista beanConquistaCount = (Conquista) logicConquista.getBean(correoTurista, codeDestino);
            if (beanConquistaCount == null) {
                if (beanUsuarioTurista.getNumeroConquistasDistintas() != null) {
                    beanUsuarioTurista.setNumeroConquistasDistintas(beanUsuarioTurista.getNumeroConquistasDistintas() + 1);
                } else {
                    beanUsuarioTurista.setNumeroConquistasDistintas(0);
                }
            }
            if (beanUsuarioTurista.getTotalConquistasNegocios() != null) {
                beanUsuarioTurista.setTotalConquistasNegocios(beanUsuarioTurista.getTotalConquistasNegocios() + 1);
            }
            beanParametro.setBean(beanUsuarioTurista);
            beanParametro.setTipoOperacion(P.ACTUALIZAR);
            Boolean rptaUsuario = logicUsuarioTurista.mantenimiento(beanParametro);
            if (!rptaUsuario) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            MuroNoticia beanMuroNoticia = generaNoticia(beanConquista, beanParametro, pm);
            if (beanMuroNoticia == null) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            /*Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
			queueMuroNoticia.add(TaskOptions.Builder
		            .withUrl("/taskMuroNoticiaCrearNegocio")
		            .param("codeNoticia", beanMuroNoticia.getCodeNoticia()).header("Host", P.HOST));*/
            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));

            Notificacion beanNotificacion = generaNotificacion(beanMuroNoticia.getBeanNoticia(), P.CONQUISTA, beanParametro, pm);
            if (beanNotificacion == null) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            /*Queue queue = QueueFactory.getQueue("notificacion-queuepush");                        
			queue.add(TaskOptions.Builder
		            .withUrl("/taskNotificacionConquistarNegocio")
		            .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));*/
            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));

            Boolean rptaControlPosicion = GestionTurista.generaControlPosicion(beanUsuarioTurista, beanDestino.getLatitud(), beanDestino.getLongitud(), null, beanDestino.getNombrePais(), beanDestino.getNombreRegion(), beanDestino.getNombreLocalidad(), pm);
            if (!rptaControlPosicion) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            tx.commit();
            pm.close();
            System.err.println("task codeNoticia: " + beanMuroNoticia.getCodeNoticia());
            Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
            queueMuroNoticia.add(TaskOptions.Builder
                    .withUrl("/taskMuroNoticiaCrearNegocio")
                    .param("codeNoticia", beanMuroNoticia.getCodeNoticia()).header("Host", P.HOST));
            System.err.println("task codeNotificacion: " + beanNotificacion.getCodeNotificacion());
            Queue queue = QueueFactory.getQueue("notificacion-queuepush");
            queue.add(TaskOptions.Builder
                    .withUrl("/taskNotificacionConquistarNegocio")
                    .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));
            beanMuroNoticia.setBeanNoticia(null);
            return beanMuroNoticia;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, tx);
        }
    }

    public static Notificacion generaNotificacion(Noticia beanNoticia, String codeTipoNotificacion, BeanParametro beanParametro, PersistenceManager pm) throws UnknownException {
        Notificacion beanNotificacion = new Notificacion();
        LogicNotificacion logicNotificacion = new LogicNotificacion(pm);
        LogicTipoNotificacion logicTipoNotificacion = new LogicTipoNotificacion(pm);
        UsuarioTurista beanUsuarioTurista = beanNoticia.getBeanTuristaGeneraNoticia();
        beanNotificacion.setBeanTuristaGeneraNotificacion(beanNoticia.getBeanTuristaGeneraNoticia());
        beanNotificacion.setBeanNoticia(beanNoticia);
        beanNotificacion.setCodeNoticia(beanNoticia.getCodeNoticia());
        Destino beanDestino = beanNoticia.getBeanDestinoConquistado();
        UsuarioNegocio beanUsuarioNegocio = beanNoticia.getBeanNegocioConquistado();
        if (beanDestino != null) {
            beanNotificacion.setBeanDestinoConquistadoDescubierto(beanDestino);
            beanNotificacion.setCodeDestinoConquistadoDescubierto(beanDestino.getCodeDestino());
        }
        if (beanUsuarioNegocio != null) {
            beanNotificacion.setBeanNegocioConquistado(beanUsuarioNegocio);
            beanNotificacion.setCodeDestinoConquistadoDescubierto(beanNoticia.getCodeNegocioConquistado());
        }
        beanNotificacion.setNombreDestinoNegocioConquistado(beanDestino.getNombre());
        beanNotificacion.setBeanConquista(beanNoticia.getBeanConquista());
        beanNotificacion.setCodeConquista(beanNoticia.getCodeConquista());
        beanNotificacion.setBeanTuristaPublicaNoticia(beanUsuarioTurista);
        beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
        beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
        beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
        beanNotificacion.setApellidoTuristaPublicaNoticia(beanUsuarioTurista.getApellido());
        beanNotificacion.setNombreTuristaPublicaNoticia(beanUsuarioTurista.getNombre());
        beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());
        beanNotificacion.setCodeTuristaPublicaNoticia(beanUsuarioTurista.getCodeUsuarioTurista());
        TipoNotificacion beanTipoNotificacion = (TipoNotificacion) logicTipoNotificacion.getBean(codeTipoNotificacion);
        if (beanTipoNotificacion == null) {
            throw new UnknownException(P.ERROR_NO_EXISTENCIA);
        }
        beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
        beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
        beanNotificacion.setIsPersistente(true);
        beanNotificacion.setVersion(new java.util.Date().getTime());
        String codigoAleatorioGenerico = java.util.UUID.randomUUID().toString();
        String codeNotificacion = StringHex.convertStringToHex(codigoAleatorioGenerico);
        beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
        beanNotificacion.setCodeNotificacion(codeNotificacion);
        beanNotificacion.setEstadoTarea(P.PENDIENTE);
        beanParametro.setBean(beanNotificacion);
        beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
        beanParametro.setTipoOperacion(P.INSERTAR);
        Boolean rptaNotificacion = logicNotificacion.mantenimiento(beanParametro);
        if (rptaNotificacion) {
            return beanNotificacion;
        }
        return null;
    }

    public static MuroNoticia generaNoticia(Conquista beanConquista, BeanParametro beanParametro, PersistenceManager pm) throws UnknownException {
        Noticia beanNoticia = new Noticia();
        MuroNoticia beanMuroNoticia = new MuroNoticia();
        String codigoAleatorioGenerico = java.util.UUID.randomUUID().toString();
        String codeNoticia = StringHex.convertStringToHex(codigoAleatorioGenerico);
        Key keyNoticia = KeyFactory.createKey(Noticia.class.getSimpleName(), codeNoticia);
        beanNoticia.setIdNoticia(KeyFactory.keyToString(keyNoticia));
        beanNoticia.setCodeNoticia(codeNoticia);
        beanNoticia.setFechaPublicacion(beanConquista.getFechaConquista());
        beanNoticia.setBeanConquista(beanConquista);
        beanNoticia.setCodeConquista(beanConquista.getCodeConquista());
        if (beanConquista.getBeanDestino() != null) {
            beanNoticia.setBeanDestinoConquistado(beanConquista.getBeanDestino());
            beanNoticia.setCodeDestinoConquistado(beanConquista.getCodeDestino());
            beanMuroNoticia.setBeanDestinoConquistado(beanConquista.getBeanDestino());
            beanMuroNoticia.setCodeDestinoConquistado(beanConquista.getCodeDestino());
        }

        if (beanConquista.getBeanNegocio() != null) {
            beanNoticia.setBeanNegocioConquistado(beanConquista.getBeanNegocio());
            beanNoticia.setCodeNegocioConquistado(beanConquista.getCodeNegocio());
            beanMuroNoticia.setBeanNegocioConquistado(beanConquista.getBeanNegocio());
            beanMuroNoticia.setCodeNegocioConquistado(beanConquista.getCodeNegocio());
        }
        beanNoticia.setNombreColoniaNegocioDestino(beanConquista.getNombreDestinoNegocio());
        beanNoticia.setBeanTuristaGeneraNoticia(beanConquista.getBeanTuristaConquista());
        beanNoticia.setCodeTuristaGeneraNoticia(beanConquista.getCodeTuristaConquista());
        StringBuilder turista = new StringBuilder();
        turista.append(beanConquista.getNombreTuristaConquista() != null ? beanConquista.getNombreTuristaConquista() : "MARCO");
        turista.append(" ");
        turista.append(beanConquista.getApellidoTuristaConquista() != null ? beanConquista.getApellidoTuristaConquista() : "POLO");
        beanNoticia.setNombreTuristaNegocioGeneraNoticia(turista.toString());
        beanNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanConquista.getFotoPerfilTuristaConquista());
        beanNoticia.setCorreoTuristaGeneraNoticia(beanConquista.getBeanTuristaConquista().getCorreoTurista());
        beanNoticia.setLongitud(beanConquista.getLongitud());
        beanNoticia.setLatitud(beanConquista.getLatitud());
        beanNoticia.setFechaPublicacion(beanConquista.getFechaConquista());
        beanNoticia.setTotalComentario(0);
        beanNoticia.setTotalCompartido(0);
        beanNoticia.setTotalDivulgado(0);
        beanNoticia.setEnlace(beanConquista.getNombreDestinoNegocio());
        beanNoticia.setDescripcion(beanConquista.getDescripcion());
        beanNoticia.setTipoNoticia(P.CONQUISTA_NEGOCIO);
        beanNoticia.setEstadoTarea(P.PENDIENTE);
        beanNoticia.setVersion((new java.util.Date()).getTime());
        beanNoticia.setFotoConquistaPublicidad(beanConquista.getFotoConquista());
        beanNoticia.setIsPersistente(true);
        beanNoticia.setFotoConquistaPublicidad(beanConquista.getFotoConquista());
        /*SETEANDO MURO*/

        String codigoAleatorio = java.util.UUID.randomUUID().toString();
        String codeMuroNoticia = StringHex.convertStringToHex(codigoAleatorio);
        String idMuroNoticia = KeyFactory.keyToString(KeyFactory.createKey(keyNoticia, MuroNoticia.class.getSimpleName(), codeMuroNoticia));
        beanMuroNoticia.setIdMuroNoticia(idMuroNoticia);
        beanMuroNoticia.setCodeMuroNoticia(codeMuroNoticia);
        beanMuroNoticia.setBeanTuristaMuro(beanConquista.getBeanTuristaConquista());
        beanMuroNoticia.setCodeTuristaMuro(beanConquista.getBeanTuristaConquista().getCodeUsuarioTurista());
        beanMuroNoticia.setCorreoTuristaMuro(beanConquista.getBeanTuristaConquista().getCorreoTurista());
        beanMuroNoticia.setBeanNoticia(beanNoticia);
        beanMuroNoticia.setCodeNoticia(codeNoticia);
        beanMuroNoticia.setFechaPublicacion(beanConquista.getFechaConquista());
        beanMuroNoticia.setBeanConquista(beanConquista);
        beanMuroNoticia.setCodeConquista(beanConquista.getCodeConquista());

        beanMuroNoticia.setBeanTuristaGeneraNoticia(beanConquista.getBeanTuristaConquista());
        beanMuroNoticia.setCodeTuristaGeneraNoticia(beanConquista.getCodeTuristaConquista());
        beanMuroNoticia.setNombreTuristaNegocioGeneraNoticia(turista.toString());
        beanMuroNoticia.setFotoPerfilTuristaNegocioGeneraNoticia(beanConquista.getFotoPerfilTuristaConquista());
        beanMuroNoticia.setCorreoTuristaGeneraNoticia(beanConquista.getBeanTuristaConquista().getCorreoTurista());
        beanMuroNoticia.setLongitud(beanConquista.getLongitud());
        beanMuroNoticia.setLatitud(beanConquista.getLatitud());
        beanMuroNoticia.setFechaPublicacion(beanConquista.getFechaConquista());
        beanMuroNoticia.setTotalComentario(0);
        beanMuroNoticia.setTotalCompartido(0);
        beanMuroNoticia.setTotalDivulgado(0);
        beanMuroNoticia.setEnlace(beanConquista.getNombreDestinoNegocio());
        beanMuroNoticia.setDescripcion(beanConquista.getDescripcion());
        beanMuroNoticia.setTipoNoticia(P.CONQUISTA_NEGOCIO);
        beanMuroNoticia.setVersion((new java.util.Date()).getTime());
        beanMuroNoticia.setIsPersistente(true);
        beanMuroNoticia.setVisto(P.NO_VISTO);
        beanMuroNoticia.setFotoConquistaPublicidad(beanConquista.getFotoConquista());
        beanMuroNoticia.setNombreColoniaNegocioDestino(beanNoticia.getNombreColoniaNegocioDestino());
        List<MuroNoticia> listMuroNoticia = new ArrayList<MuroNoticia>();
        listMuroNoticia.add(beanMuroNoticia);
        beanNoticia.setListMuroNoticia(listMuroNoticia);
        beanParametro.setBean(beanNoticia);
        beanParametro.setTipoOperacion(P.INSERTAR);
        LogicNoticia logicNoticia = new LogicNoticia(pm);
        Noticia beanNoticiabd = logicNoticia.mantenimientoReturn(beanParametro);
        return beanNoticiabd.getListMuroNoticia().get(0);
    }

    public static Boolean queueMuroNoticiaCrearNegocio(String codeNoticia) throws UnknownException {
        PersistenceManager pm = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            LogicNoticia logicNoticia = new LogicNoticia(pm);
            LogicMuroNoticia logicMuroNoticia = new LogicMuroNoticia(pm);
            Noticia beanNoticia = (Noticia) logicNoticia.getBeanByCode(codeNoticia);
            if (beanNoticia == null) {
                throw new UnknownException(P.ERROR_NO_EXISTENCIA);
            }
            Set<UsuarioTurista> queueTuristas = new HashSet<UsuarioTurista>();
            UsuarioTurista beanTuristaGeneraNoticia = beanNoticia.getBeanTuristaGeneraNoticia();
            String codeNoticiaActual = beanNoticia.getCodeNoticia();
            String codeTuristaPublica = beanTuristaGeneraNoticia.getCodeUsuarioTurista(), codeTuristaGenera = beanTuristaGeneraNoticia.getCodeUsuarioTurista();
            LogicParametrosSistema logicParametroSistema = new LogicParametrosSistema(pm);
            String codeParametroSistema = "RF";
            ParametrosSistema beanParametroSistema = new ParametrosSistema();
            beanParametroSistema = (ParametrosSistema) logicParametroSistema.getBean(codeParametroSistema);
            Integer days = -1;
            try {
                days = Integer.parseInt(beanParametroSistema.getValor());
            } catch (Exception ex) {
                throw new UnknownException("No se pudo convertir a entero el valor de dias");
            }
            Calendar beanGregorianCalendar = GregorianCalendar.getInstance();
            beanGregorianCalendar.add(Calendar.DATE, ((-1) * days));
            Long rangoFecha = beanGregorianCalendar.getTime().getTime();
            queueTuristas.addAll(GestionShared.busquedaByDCS(codeTuristaPublica, codeNoticiaActual, codeTuristaGenera, rangoFecha.toString(), pm));
            queueTuristas.addAll(GestionShared.busquedaByEmpatias(codeTuristaPublica, codeTuristaGenera, rangoFecha.toString(), pm));
            if (beanNoticia.getTipoNoticia().equals(P.CONQUISTA)) {//Cuando es de tipo conquista se realiza la busqueda de las conquistas a ese destino.
                queueTuristas.addAll(GestionShared.busquedaByConquistas(codeTuristaPublica, beanNoticia.getCodeDestinoConquistado(), pm));
            }
            if (queueTuristas.contains(beanTuristaGeneraNoticia)) {
                queueTuristas.remove(beanTuristaGeneraNoticia);
            }
            if (queueTuristas.isEmpty()) {
//			Parametro  CPAN para enviar a usuarios escogidos de manera aleatoria sus muro noticia.
                codeParametroSistema = "CPAN";
                queueTuristas.addAll(GestionShared.busquedaByFriends(codeTuristaGenera, codeParametroSistema, pm));
            }
            List<MuroNoticia> listParamMuroNoticia = new ArrayList<MuroNoticia>();
            Iterator<UsuarioTurista> listQueueUsuarioIterator = queueTuristas.iterator();
            while (listQueueUsuarioIterator.hasNext()) {
                UsuarioTurista beanTuristaMuro = listQueueUsuarioIterator.next();
                MuroNoticia beanMuroNoticia = new MuroNoticia();
                String codeMuroNoticia = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
                Key keyNoticia = KeyFactory.createKey(Noticia.class.getSimpleName(), codeNoticia);
                String idMuroNoticia = KeyFactory.keyToString(KeyFactory.createKey(keyNoticia, MuroNoticia.class.getSimpleName(), codeMuroNoticia));
                beanMuroNoticia.setIdMuroNoticia(idMuroNoticia);
                beanMuroNoticia.setCodeMuroNoticia(codeMuroNoticia);
                beanMuroNoticia.setBeanTuristaMuro(beanTuristaMuro);
                beanMuroNoticia.setCodeTuristaMuro(beanTuristaMuro.getCodeUsuarioTurista());
                beanMuroNoticia.setCorreoTuristaMuro(beanTuristaMuro.getCorreoTurista());
                beanMuroNoticia.setBeanNoticia(beanNoticia);
                beanMuroNoticia.setCodeNoticia(codeNoticia);
                beanMuroNoticia.setFechaPublicacion(beanNoticia.getFechaPublicacion());
                beanMuroNoticia.setBeanConquista(beanNoticia.getBeanConquista());
                beanMuroNoticia.setCodeConquista(beanNoticia.getBeanConquista().getCodeConquista());
                beanMuroNoticia.setBeanDestinoConquistado(beanNoticia.getBeanDestinoConquistado());
                beanMuroNoticia.setCodeDestinoConquistado(beanNoticia.getCodeDestinoConquistado());
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
            Collection<BeanParametro> listParametros = new ArrayList<BeanParametro>();
            BeanParametro parametro = new BeanParametro();
            parametro.setBean(listParamMuroNoticia);
            parametro.setTipoOperacion(P.INSERTAR);
            listParametros.add(parametro);
            Boolean rptaMuroNoticia = logicMuroNoticia.mantenimiento(listParametros);
            if (!rptaMuroNoticia) {
                throw new UnknownException("No se publico en el Muro");
            }
            beanNoticia.setEstadoTarea(P.EJECUTADO);
            BeanParametro beanParametro = new BeanParametro();
            beanParametro.setBean(beanNoticia);
            beanParametro.setTipoOperacion(P.ACTUALIZAR);
            Boolean rptaNoticia = logicNoticia.mantenimiento(beanParametro);
            if (rptaNoticia) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    public static Boolean queueNotificationCrearNegocio(String codeNotificacion) throws UnknownException {
        PersistenceManager pm = null;
        Notificacion beanNotificacion = null;
        LogicNotificacion logicNotificacion = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            logicNotificacion = new LogicNotificacion(pm);
            LogicNotificacionTurista logicNotificacionTurista = new LogicNotificacionTurista(pm);
            LogicNoticia logicNoticia = new LogicNoticia(pm);
            LogicComentarioNoticia logicComentarioNoticia = new LogicComentarioNoticia(pm);
            LogicComparteNoticia logicComparteNoticia = new LogicComparteNoticia(pm);
            LogicDivulgacionNoticia logicDivulgacionNoticia = new LogicDivulgacionNoticia(pm);

            LogicParametrosSistema logicParametroSistema = new LogicParametrosSistema(pm);
            String codeParametroSistema = "RF";
            ParametrosSistema beanParametroSistema = new ParametrosSistema();
            beanParametroSistema = (ParametrosSistema) logicParametroSistema.getBean(codeParametroSistema);
            Integer days = -1;
            try {
                days = Integer.parseInt(beanParametroSistema.getValor());
            } catch (Exception ex) {
                throw new UnknownException("No se pudo convertir a entero el valor de dias");
            }
            Calendar beanGregorianCalendar = GregorianCalendar.getInstance();
            beanGregorianCalendar.add(Calendar.DATE, ((-1) * days));
            Long rangoInferior = beanGregorianCalendar.getTime().getTime();

            beanNotificacion = (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);
            if (beanNotificacion == null) {
                throw new UnknownException(P.ERROR_NO_EXISTENCIA);
            }
            Set<UsuarioTurista> queueTuristas = new HashSet<UsuarioTurista>();
            Destino beanDestino = beanNotificacion.getBeanDestinoConquistadoDescubierto();
            UsuarioTurista beanTuristaGeneraNotificacion = beanNotificacion.getBeanTuristaGeneraNotificacion();
            StringBuilder msjTuristaPublicaNovedad = new StringBuilder();	//enviamos la noticia actual para excluirla
            List<Noticia> listNoticias = (List<Noticia>) logicNoticia.getListarBeanByRangoFecha(beanTuristaGeneraNotificacion.getCodeUsuarioTurista(), rangoInferior);
            if (listNoticias.size() > 0) {
                for (int i = 0; i < listNoticias.size(); i++) {
                    String codeNoticia = listNoticias.get(i).getCodeNoticia();
                    List<ComentarioNoticia> listComentarioNoticia = (List<ComentarioNoticia>) logicComentarioNoticia.getListBean(codeNoticia);
                    if (listComentarioNoticia != null) {
                        Iterator<ComentarioNoticia> listComentarioIterator = listComentarioNoticia.iterator();
                        while (listComentarioIterator.hasNext()) {
                            UsuarioTurista beanTuristaComenta = listComentarioIterator.next().getBeanTuristaComenta();
                            queueTuristas.add(beanTuristaComenta);
                        }
                    }
                    List<ComparteNoticia> listComparteNoticia = (List<ComparteNoticia>) logicComparteNoticia.getListBean(codeNoticia);
                    if (listComparteNoticia != null) {
                        Iterator<ComparteNoticia> listComparteIterator = listComparteNoticia.iterator();
                        while (listComparteIterator.hasNext()) {
                            UsuarioTurista beanTuristaComparte = listComparteIterator.next().getBeanTuristaComparte();
                            queueTuristas.add(beanTuristaComparte);
                        }
                    }
                    List<DivulgacionNoticia> listDivulgacionNoticia = (List<DivulgacionNoticia>) logicDivulgacionNoticia.getListBean(codeNoticia);
                    if (listDivulgacionNoticia != null) {
                        Iterator<DivulgacionNoticia> listDivulgacionIterator = listDivulgacionNoticia.iterator();
                        while (listDivulgacionIterator.hasNext()) {
                            UsuarioTurista beanTuristaDivulgacion = listDivulgacionIterator.next().getBeanTuristaDivulga();
                            queueTuristas.add(beanTuristaDivulgacion);
                        }
                    }
                }
                if (queueTuristas.contains(beanTuristaGeneraNotificacion)) {
                    queueTuristas.remove(beanTuristaGeneraNotificacion);
                }
                msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre());
                TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
                if (beanTipoNotificacion.getCodeTipoNotificacion().equals(P.DESCUBIERTO_NUEVO_NEGOCIO)) {
                    msjTuristaPublicaNovedad.append(" ha Descubierto ".concat(beanDestino.getNombre()));
                }
                List<NotificacionTurista> listParamNotificacionTurista = new ArrayList<NotificacionTurista>();
                Iterator<UsuarioTurista> listQueueUsuarioIterator = queueTuristas.iterator();
                while (listQueueUsuarioIterator.hasNext()) {
                    UsuarioTurista beanTuristaRecibeNotificacion = listQueueUsuarioIterator.next();
                    NotificacionTurista beanNotificacionTuristaInteractua = new NotificacionTurista();
                    String codeNotificacionTuristaInteractua = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
                    beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));
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
                    beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
                    beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
                    beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
                    beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
                    beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());
                    beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);
                    beanNotificacionTuristaInteractua.setNombreDestinoNegocioConquistado(beanNotificacion.getBeanDestinoConquistadoDescubierto().getNombre());
                    beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
                    listParamNotificacionTurista.add(beanNotificacionTuristaInteractua);
                    if (beanNotificacionTuristaInteractua.getTokenFirebase() != null
                            && !beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()) {
                        NotificationMessage not = new NotificationMessage();
                        not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
                        not.setOptionRestrictedPackageName("com.indiant");
                        not.setOptionPriority(10);
                        AndroidNotificationPayLoad payLoad = new AndroidNotificationPayLoad();
                        payLoad.setSound("default");
                        DataPayLoad dataPayLoad = new DataPayLoad();
                        dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
                        dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion() != null ? beanNotificacionTuristaInteractua.getCodeTipoNotificacion() : "");
                        dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia() != null ? beanNotificacionTuristaInteractua.getCodeNoticia() : "");
                        dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino() != null ? beanNotificacionTuristaInteractua.getCodeDestino() : "");
                        dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia() != null ? beanNotificacionTuristaInteractua.getCodeColonia() : "");
                        dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia() != null ? beanNotificacionTuristaInteractua.getNombreColonia() : "");
                        dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion() != null ? beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil() : "");
                        dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia() != null ? beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad() != null ? beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad() : "" : "");
                        dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion() != null ? beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion() : "");
                        dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
                        payLoad.setTitle("Indiant");
                        payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());
                        not.setPayLoadData(dataPayLoad.buildPayLoad());
                        not.setPayLoadNotification(payLoad.buildPayLoadAndroid());
                        HttpFcmConection cnx = new HttpFcmConection(P.FIREBASE_SERVER_KEY, EnumContentType.JSON.getValue());
                        cnx.sendNotificationHttp(not);
                    }
                }
                Collection<BeanParametro> listParametros = new ArrayList<BeanParametro>();
                BeanParametro parametro = new BeanParametro();
                parametro.setBean(listParamNotificacionTurista);
                parametro.setTipoOperacion(P.INSERTAR);
                listParametros.add(parametro);
                Boolean rptaNotificacionTurista = logicNotificacionTurista.mantenimiento(listParametros);
                if (!rptaNotificacionTurista) {
                    throw new UnknownException(P.ERROR_OPERACION);
                }
            }
            beanNotificacion.setEstadoTarea(P.EJECUTADO);
            BeanParametro beanParametro = new BeanParametro();
            beanParametro.setBean(beanNotificacion);
            beanParametro.setTipoOperacion(P.ACTUALIZAR);
            Boolean rptaNotificacion = logicNotificacion.mantenimiento(beanParametro);
            if (rptaNotificacion) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    public static Boolean queueNotificationConquistarNegocio(String codeNotificacion) throws UnknownException {
        PersistenceManager pm = null;
        Notificacion beanNotificacion = null;
        LogicNotificacion logicNotificacion = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            logicNotificacion = new LogicNotificacion(pm);
            LogicConquista logicConquista = new LogicConquista(pm);
            LogicNotificacionTurista logicNotificacionTurista = new LogicNotificacionTurista(pm);

            beanNotificacion = (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);
            if (beanNotificacion == null) {
                throw new UnknownException(P.ERROR_NO_EXISTENCIA);
            }
            Set<UsuarioTurista> queueTuristas = new HashSet<UsuarioTurista>();
            Destino beanDestino = beanNotificacion.getBeanDestinoConquistadoDescubierto();
            UsuarioTurista beanTuristaPublicaNoticia = beanNotificacion.getBeanTuristaPublicaNoticia();
            UsuarioTurista beanTuristaGeneraNotificacion = beanNotificacion.getBeanTuristaGeneraNotificacion();
            StringBuilder msjTuristaPublicaNovedad = new StringBuilder();
            List<Conquista> listConquistasDestino = (List<Conquista>) logicConquista.getListarBeanByDestino(beanTuristaPublicaNoticia.getCorreoTurista(), beanDestino.getCodeDestino());
            if (listConquistasDestino != null) {
                Iterator<Conquista> listConquistasIterator = listConquistasDestino.iterator();
                while (listConquistasIterator.hasNext()) {
                    queueTuristas.add(listConquistasIterator.next().getBeanTuristaConquista());
                }
                if (queueTuristas.contains(beanTuristaGeneraNotificacion)) {
                    queueTuristas.remove(beanTuristaGeneraNotificacion);
                }
                msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre());
                TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
                if (beanTipoNotificacion.getCodeTipoNotificacion().equals(P.CONQUISTA)) {
                    msjTuristaPublicaNovedad.append(" tambien ha Conquistado ".concat(beanDestino.getNombre()));
                }
                List<NotificacionTurista> listParamNotificacionTurista = new ArrayList<NotificacionTurista>();
                Iterator<UsuarioTurista> listQueueUsuarioIterator = queueTuristas.iterator();
                while (listQueueUsuarioIterator.hasNext()) {
                    UsuarioTurista beanTuristaRecibeNotificacion = listQueueUsuarioIterator.next();
                    NotificacionTurista beanNotificacionTuristaInteractua = new NotificacionTurista();
                    String codeNotificacionTuristaInteractua = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
                    beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));
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
                    beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
                    beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
                    beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
                    beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
                    beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());
                    beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);
                    beanNotificacionTuristaInteractua.setNombreDestinoNegocioConquistado(beanNotificacion.getBeanDestinoConquistadoDescubierto().getNombre());
                    beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
                    listParamNotificacionTurista.add(beanNotificacionTuristaInteractua);
                    if (beanNotificacionTuristaInteractua.getTokenFirebase() != null
                            && !beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()) {
                        NotificationMessage not = new NotificationMessage();
                        not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
                        not.setOptionRestrictedPackageName("com.indiant");
                        not.setOptionPriority(10);
                        AndroidNotificationPayLoad payLoad = new AndroidNotificationPayLoad();
                        payLoad.setSound("default");
                        DataPayLoad dataPayLoad = new DataPayLoad();
                        dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
                        dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion() != null ? beanNotificacionTuristaInteractua.getCodeTipoNotificacion() : "");
                        dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia() != null ? beanNotificacionTuristaInteractua.getCodeNoticia() : "");
                        dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino() != null ? beanNotificacionTuristaInteractua.getCodeDestino() : "");
                        dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia() != null ? beanNotificacionTuristaInteractua.getCodeColonia() : "");
                        dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia() != null ? beanNotificacionTuristaInteractua.getNombreColonia() : "");
                        dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion() != null ? beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil() : "");
                        dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia() != null ? beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad() != null ? beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad() : "" : "");
                        dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion() != null ? beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion() : "");
                        dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
                        payLoad.setTitle("Indiant");
                        payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());
                        not.setPayLoadData(dataPayLoad.buildPayLoad());
                        not.setPayLoadNotification(payLoad.buildPayLoadAndroid());
                        HttpFcmConection cnx = new HttpFcmConection(P.FIREBASE_SERVER_KEY, EnumContentType.JSON.getValue());
                        cnx.sendNotificationHttp(not);
                    }
                }
                Collection<BeanParametro> listParametros = new ArrayList<BeanParametro>();
                BeanParametro parametro = new BeanParametro();
                parametro.setBean(listParamNotificacionTurista);
                parametro.setTipoOperacion(P.INSERTAR);
                listParametros.add(parametro);
                Boolean rptaNotificacionTurista = logicNotificacionTurista.mantenimiento(listParametros);
                if (!rptaNotificacionTurista) {
                    throw new UnknownException(P.ERROR_OPERACION);
                }
            }//Algoritmo de Notificacion para Amigos aleatorios CPAN: cantidad de personas a notificar
            LogicNoticia logicNoticia = new LogicNoticia(pm);
            LogicComentarioNoticia logicComentarioNoticia = new LogicComentarioNoticia(pm);
            LogicComparteNoticia logicComparteNoticia = new LogicComparteNoticia(pm);
            LogicDivulgacionNoticia logicDivulgacionNoticia = new LogicDivulgacionNoticia(pm);

            LogicParametrosSistema logicParametroSistema = new LogicParametrosSistema(pm);
            String codeParametroSistema = "RF";
            ParametrosSistema beanParametroSistema = new ParametrosSistema();
            beanParametroSistema = (ParametrosSistema) logicParametroSistema.getBean(codeParametroSistema);
            Integer days = -1;
            try {
                days = Integer.parseInt(beanParametroSistema.getValor());
            } catch (Exception ex) {
                throw new UnknownException("No se pudo convertir a entero el valor de dias");
            }
            Calendar beanGregorianCalendar = GregorianCalendar.getInstance();
            beanGregorianCalendar.add(Calendar.DATE, ((-1) * days));
            Long rangoInferior = beanGregorianCalendar.getTime().getTime();

            beanNotificacion = (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);
            if (beanNotificacion == null) {
                throw new UnknownException(P.ERROR_NO_EXISTENCIA);
            }
            queueTuristas = new HashSet<UsuarioTurista>();
            msjTuristaPublicaNovedad = new StringBuilder();	//enviamos la noticia actual para excluirla
            List<Noticia> listNoticias = (List<Noticia>) logicNoticia.getListarBeanByRangoFecha(beanTuristaGeneraNotificacion.getCodeUsuarioTurista(), rangoInferior);
            if (listNoticias.size() > 0) {
                for (int i = 0; i < listNoticias.size(); i++) {
                    String codeNoticia = listNoticias.get(i).getCodeNoticia();
                    List<ComentarioNoticia> listComentarioNoticia = (List<ComentarioNoticia>) logicComentarioNoticia.getListBean(codeNoticia);
                    if (listComentarioNoticia != null) {
                        Iterator<ComentarioNoticia> listComentarioIterator = listComentarioNoticia.iterator();
                        while (listComentarioIterator.hasNext()) {
                            UsuarioTurista beanTuristaComenta = listComentarioIterator.next().getBeanTuristaComenta();
                            queueTuristas.add(beanTuristaComenta);
                        }
                    }
                    List<ComparteNoticia> listComparteNoticia = (List<ComparteNoticia>) logicComparteNoticia.getListBean(codeNoticia);
                    if (listComparteNoticia != null) {
                        Iterator<ComparteNoticia> listComparteIterator = listComparteNoticia.iterator();
                        while (listComparteIterator.hasNext()) {
                            UsuarioTurista beanTuristaComparte = listComparteIterator.next().getBeanTuristaComparte();
                            queueTuristas.add(beanTuristaComparte);
                        }
                    }
                    List<DivulgacionNoticia> listDivulgacionNoticia = (List<DivulgacionNoticia>) logicDivulgacionNoticia.getListBean(codeNoticia);
                    if (listDivulgacionNoticia != null) {
                        Iterator<DivulgacionNoticia> listDivulgacionIterator = listDivulgacionNoticia.iterator();
                        while (listDivulgacionIterator.hasNext()) {
                            UsuarioTurista beanTuristaDivulgacion = listDivulgacionIterator.next().getBeanTuristaDivulga();
                            queueTuristas.add(beanTuristaDivulgacion);
                        }
                    }
                }
                if (queueTuristas.contains(beanTuristaGeneraNotificacion)) {
                    queueTuristas.remove(beanTuristaGeneraNotificacion);
                }
                msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre());
                TipoNotificacion beanTipoNotificacion = beanNotificacion.getBeanTipoNotificacion();
                if (beanTipoNotificacion.getCodeTipoNotificacion().equals(P.CONQUISTA)) {
                    msjTuristaPublicaNovedad.append(" ha conquistado ".concat(beanDestino.getNombre()));
                }

                List<NotificacionTurista> listParamNotificacionTurista = new ArrayList<NotificacionTurista>();
                Iterator<UsuarioTurista> listQueueUsuarioIterator = queueTuristas.iterator();
                while (listQueueUsuarioIterator.hasNext()) {
                    UsuarioTurista beanTuristaRecibeNotificacion = listQueueUsuarioIterator.next();
                    NotificacionTurista beanNotificacionTuristaInteractua = new NotificacionTurista();
                    String codeNotificacionTuristaInteractua = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
                    beanNotificacionTuristaInteractua.setIdNotificacionTurista(KeyFactory.keyToString(KeyFactory.createKey(NotificacionTurista.class.getSimpleName(), codeNotificacionTuristaInteractua)));
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
                    beanNotificacionTuristaInteractua.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
                    beanNotificacionTuristaInteractua.setTokenFirebase(beanTuristaRecibeNotificacion.getTokenFirebase());
                    beanNotificacionTuristaInteractua.setBeanTipoNotificacion(beanNotificacion.getBeanTipoNotificacion());
                    beanNotificacionTuristaInteractua.setCodeTipoNotificacion(beanNotificacion.getCodeTipoNotificacion());
                    beanNotificacionTuristaInteractua.setBeanTuristaRecibeNotificacion(beanTuristaRecibeNotificacion);
                    beanNotificacionTuristaInteractua.setCodeTuristaRecibeNotificacion(beanTuristaRecibeNotificacion.getCodeUsuarioTurista());
                    beanNotificacionTuristaInteractua.setVisto(P.NO_VISTO);
                    beanNotificacionTuristaInteractua.setNombreDestinoNegocioConquistado(beanNotificacion.getBeanDestinoConquistadoDescubierto().getNombre());
                    listParamNotificacionTurista.add(beanNotificacionTuristaInteractua);
                    if (beanNotificacionTuristaInteractua.getTokenFirebase() != null
                            && !beanNotificacionTuristaInteractua.getTokenFirebase().isEmpty()) {
                        NotificationMessage not = new NotificationMessage();
                        not.setTargetTo(beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
                        not.setOptionRestrictedPackageName("com.indiant");
                        not.setOptionPriority(10);
                        AndroidNotificationPayLoad payLoad = new AndroidNotificationPayLoad();
                        payLoad.setSound("default");
                        DataPayLoad dataPayLoad = new DataPayLoad();
                        dataPayLoad.add("codeNotificacionTurista", beanNotificacionTuristaInteractua.getCodeNotificacionTurista());
                        dataPayLoad.add("codeTipoNotificacion", beanNotificacionTuristaInteractua.getCodeTipoNotificacion() != null ? beanNotificacionTuristaInteractua.getCodeTipoNotificacion() : "");
                        dataPayLoad.add("codeNoticia", beanNotificacionTuristaInteractua.getCodeNoticia() != null ? beanNotificacionTuristaInteractua.getCodeNoticia() : "");
                        dataPayLoad.add("codeDestino", beanNotificacionTuristaInteractua.getCodeDestino() != null ? beanNotificacionTuristaInteractua.getCodeDestino() : "");
                        dataPayLoad.add("codeColonia", beanNotificacionTuristaInteractua.getCodeColonia() != null ? beanNotificacionTuristaInteractua.getCodeColonia() : "");
                        dataPayLoad.add("nombreColonia", beanNotificacionTuristaInteractua.getNombreColonia() != null ? beanNotificacionTuristaInteractua.getNombreColonia() : "");
                        dataPayLoad.add("fotoUsuario", beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion() != null ? beanNotificacionTuristaInteractua.getBeanTuristaGeneraNotificacion().getFotoPerfil() : "");
                        dataPayLoad.add("fotoNoticia", beanNotificacionTuristaInteractua.getBeanNoticia() != null ? beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad() != null ? beanNotificacionTuristaInteractua.getBeanNoticia().getFotoConquistaPublicidad() : "" : "");
                        dataPayLoad.add("codeTurista", beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion() != null ? beanNotificacionTuristaInteractua.getCodeTuristaGeneraNotificacion() : "");
                        dataPayLoad.add("tokenFirebase", beanNotificacionTuristaInteractua.getBeanTuristaRecibeNotificacion().getTokenFirebase());
                        payLoad.setTitle("Indiant");
                        payLoad.setBody(beanNotificacionTuristaInteractua.getMensajeNotificacion());
                        not.setPayLoadData(dataPayLoad.buildPayLoad());
                        not.setPayLoadNotification(payLoad.buildPayLoadAndroid());
                        HttpFcmConection cnx = new HttpFcmConection(P.FIREBASE_SERVER_KEY, EnumContentType.JSON.getValue());
                        cnx.sendNotificationHttp(not);
                    }
                }
                Collection<BeanParametro> listParametros = new ArrayList<BeanParametro>();
                BeanParametro parametro = new BeanParametro();
                parametro.setBean(listParamNotificacionTurista);
                parametro.setTipoOperacion(P.INSERTAR);
                listParametros.add(parametro);
                Boolean rptaNotificacionTurista = logicNotificacionTurista.mantenimiento(listParametros);
                if (!rptaNotificacionTurista) {
                    throw new UnknownException("No se guardo  la Notificacion Turista");
                }
            }
            beanNotificacion.setEstadoTarea(P.EJECUTADO);
            BeanParametro beanParametro = new BeanParametro();
            beanParametro.setBean(beanNotificacion);
            beanParametro.setTipoOperacion(P.ACTUALIZAR);
            Boolean rptaNotificacion = logicNotificacion.mantenimiento(beanParametro);
            if (rptaNotificacion) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    /**
     * CODIGO: KCYS-166</br>
     * CUS: CUS_CREAR_CUENTA_NEGOCIO - ANS_NEGOCIO</br>
     * ESTADO: POR TESTEAR</br>
     * Metodo verificarCuentaNegocio
     */
    public static CuentaNegocio verificarCuentaNegocio(String correoNegocio) throws UnknownException {
        PersistenceManager pm = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            return validarCuentaNegocio(correoNegocio, pm);
        } catch (UnknownException ex) {
            throw ex;
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    /* Datos Estadisticos */
    public static Integer turistaCercaNegocio(String correoNegocio) throws UnknownException {
        PersistenceManager pm = null;
        return 0;
//		try {
//			pm = PMF.getPMF().getPersistenceManager();
//			return 0;
//		} catch (UnknownException ex) {
//			throw ex;
//		} finally {
//			GestionShared.closeConnection(pm,null);
//		}
    }

    public static Integer visitasPerfilNegocio(String correoNegocio) throws UnknownException {
        PersistenceManager pm = null;
        return 0;
//		try {
//			pm = PMF.getPMF().getPersistenceManager();
//			return validarCuentaNegocio(correoNegocio, pm);
//		} catch (UnknownException ex) {
//			throw ex;
//		} finally {
//			GestionShared.closeConnection(pm,null);
//		}
    }

    public static Integer conquistasNegocio(String correoNegocio) throws UnknownException {
        PersistenceManager pm = null;
        return 0;
//		try {
//			pm = PMF.getPMF().getPersistenceManager();
//			return validarCuentaNegocio(correoNegocio, pm);
//		} catch (UnknownException ex) {
//			throw ex;
//		} finally {
//			GestionShared.closeConnection(pm,null);
//		}
    }

    public static Integer noticiasDivulgadas(String correoNegocio) throws UnknownException {
        PersistenceManager pm = null;
        return 0;
//		try {
//			pm = PMF.getPMF().getPersistenceManager();
//			return validarCuentaNegocio(correoNegocio, pm);
//		} catch (UnknownException ex) {
//			throw ex;
//		} finally {
//			GestionShared.closeConnection(pm,null);
//		}
    }

    public static Integer noticiasCompartidas(String correoNegocio) throws UnknownException {
        PersistenceManager pm = null;
        return 0;
//		try {
//			pm = PMF.getPMF().getPersistenceManager();
//			return validarCuentaNegocio(correoNegocio, pm);
//		} catch (UnknownException ex) {
//			throw ex;
//		} finally {
//			GestionShared.closeConnection(pm,null);
//		}
    }

    /* METODOS REUTILIZABLES */
    private static CuentaNegocio validarCuentaNegocio(String correoNegocio, PersistenceManager pm)
            throws UnknownException {
        try {
            LogicCuentaNegocio logicCuentaNegocio = new LogicCuentaNegocio(pm);
            CuentaNegocio beanCuentaNegocio = (CuentaNegocio) logicCuentaNegocio.getBeanByEmail(correoNegocio);
            if (beanCuentaNegocio == null) {
                throw new UnknownException(P.ERROR_NO_EXISTENCIA);
            }
            return beanCuentaNegocio;
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        }
    }

    private static CuentaNegocio validarCuentaNegocio(String correoNegocio, String claveNegocio, PersistenceManager pm)
            throws UnknownException {
        try {
            CuentaNegocio beanCuentaNegocio = validarCuentaNegocio(correoNegocio, pm);
            String claveEncriptada = AESencrypt.encrypt(claveNegocio);
            if (!beanCuentaNegocio.getClave().equals(claveEncriptada)) {
                throw new UnknownException(P.CLAVE_NO_COINCIDE);
            }
            return beanCuentaNegocio;
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        }
    }

    private static SesionNegocio insertarSesionNegocio(UsuarioNegocio beanUsuarioNegocio, PersistenceManager pm)
            throws UnknownException {
        SesionNegocio beanSesion = new SesionNegocio();
        String codigoAleatorioGenerico = java.util.UUID.randomUUID().toString();
        beanSesion.setIdSesionNegocio(KeyFactory
                .keyToString(KeyFactory.createKey(SesionNegocio.class.getSimpleName(), codigoAleatorioGenerico)));
        beanSesion.setVersion(new java.util.Date().getTime());
        beanSesion.setFechaInicioSesion(new java.util.Date());
        BeanParametro beanParametro = new BeanParametro();
        beanParametro.setBean(beanSesion);
        beanParametro.setTipoOperacion(P.INSERTAR);
        LogicSesionNegocio logicSesionNegocio = new LogicSesionNegocio(pm);
        return logicSesionNegocio.mantenimientoNegocio(beanParametro);
    }

}
