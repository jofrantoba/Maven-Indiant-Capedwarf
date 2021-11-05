package com.indiana.server.model.process;

import java.text.DecimalFormat;
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
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.indiana.server.model.bean.CalificaDestino;
import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.bean.ComparteNoticia;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.ControlPosicion;
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
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicCalificaDestino;
import com.indiana.server.model.logic.LogicComentarioNoticia;
import com.indiana.server.model.logic.LogicComparteNoticia;
import com.indiana.server.model.logic.LogicConquista;
import com.indiana.server.model.logic.LogicControlPosicion;
import com.indiana.server.model.logic.LogicDestino;
import com.indiana.server.model.logic.LogicDivulgacionNoticia;
import com.indiana.server.model.logic.LogicMuroNoticia;
import com.indiana.server.model.logic.LogicNoticia;
import com.indiana.server.model.logic.LogicNotificacion;
import com.indiana.server.model.logic.LogicNotificacionTurista;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicTipoNotificacion;
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

public class GestionDestiny {

    private static final Logger LOG = Logger.getLogger(GestionDestiny.class.getName());

    /**
     * METHOD: LISTAR DESTINOS CODIGO: KCYS-130 CUS: CUS_BUSCAR_DESTINO -
     * ANS_TURISTA ESTADO : TESTEADO
     *
     * @param gps
     * @param nombrePais
     * @param nombreRegion
     * @param nombreLocalidad
     * @param correoTurista
     * @param latitudTurista
     * @param longitudTurista
     * @param limiteMostrar
     * @param distanciaBusqueda
     * @return
     * @throws UnknownException
     *
     */
    public static List<Destino> listarDestinos(Boolean gps, String nombrePais, String nombreRegion, String nombreLocalidad, String correoTurista,
            Double latitudTurista, Double longitudTurista, Integer limiteMostrar, Double distanciaBusqueda, String tipoNegocioDestino) throws UnknownException {
        PersistenceManager pm = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            LogicControlPosicion logicControPosicion = new LogicControlPosicion(pm);
            LogicDestino logicDestino = new LogicDestino(pm);
            Double latitudBusqueda = latitudTurista;
            Double longitudBusqueda = longitudTurista;
            String nombrePaisBusqueda = nombrePais;
            String nombreRegionBusqueda = nombreRegion;
            String nombreLocalidadBusqueda = nombreLocalidad;
            if (!gps) {
                ControlPosicion beanControlPosicion = (ControlPosicion) logicControPosicion.getBeanByTurista(correoTurista);
                if (beanControlPosicion == null) {
                    throw new UnknownException(P.GPS);
                }
                latitudBusqueda = beanControlPosicion.getLatitud();
                longitudBusqueda = beanControlPosicion.getLongitud();
                if (beanControlPosicion.getNombrePais() != null && !beanControlPosicion.getNombrePais().isEmpty() && !beanControlPosicion.getNombrePais().equalsIgnoreCase(P.DESCONOCIDO)) {
                    nombrePaisBusqueda = beanControlPosicion.getNombrePais();
                    nombreRegionBusqueda = null;
                } else {
                    nombrePaisBusqueda = P.PAISDEFAULT;
                    nombreRegionBusqueda = null;
                }
                //nombreLocalidadBusqueda=beanControlPosicion.getNombreLocalidad();
                nombreLocalidadBusqueda = null;
            } else {
                LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
                UsuarioTurista beanUsuarioTurista = logicUsuarioTurista.getBeanByEmail(correoTurista);
                GestionTurista.generaControlPosicion(beanUsuarioTurista, latitudBusqueda, longitudBusqueda, null, nombrePais, nombreRegion, nombreLocalidad, pm);
                if (nombrePais.equalsIgnoreCase(P.DESCONOCIDO)) {
                    nombrePais = P.PAISDEFAULT;
                }
                nombreRegionBusqueda = null;
                nombreLocalidadBusqueda = null;
            }
            List<Destino> listDestinos = (List<Destino>) logicDestino.getListarBean(nombrePaisBusqueda, nombreRegionBusqueda, nombreLocalidadBusqueda, tipoNegocioDestino);
            if (listDestinos == null) {
                throw new UnknownException("Modifique el Rango de Busqueda");
            }
            List<Destino> listDestinosProximos = new ArrayList<Destino>();
            for (int i = 0; i < listDestinos.size(); i++) {
                Destino beanDestino = listDestinos.get(i);
                Double distancia = GestionShared.calcularDistancia(
                        latitudBusqueda, longitudBusqueda, beanDestino.getLatitud(),
                        beanDestino.getLongitud(), beanDestino.getRadio(), distanciaBusqueda);
                if (distancia != null) {
                    beanDestino.setCalculoDistancia(distancia);
                    beanDestino.setDistanciaTuristaDestino(formatoDistanciaTuristaADestino(distancia));
                    beanDestino.setListaCalificaciones(beanDestino.getListaCalificaciones());
                    listDestinosProximos.add(beanDestino);
                }
            }
            Collections.sort(listDestinosProximos, new Comparator<Destino>() {
                @Override
                public int compare(Destino beanDestino, Destino anotherDestino) {
                    return new Double(beanDestino.getCalculoDistancia()).compareTo(new Double(anotherDestino.getCalculoDistancia()));
                }
            });
            if (listDestinosProximos.size() > limiteMostrar) {
                listDestinosProximos = listDestinosProximos.subList(0, limiteMostrar);
            }
            return listDestinosProximos;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    /**
     * METHOD: BUSCAR DESTINOS CODIGO: KCYS-130 CUS: CUS_BUSCAR_DESTINO -
     * ANS_TURISTA ESTADO : TESTEADO
     *
     * @param patron
     * @param codeCategoria
     * @param nombrePais
     * @param nombreRegion
     * @param nombreLocalidad
     * @return
     * @throws UnknownException
     */
    public static List<Destino> buscarDestinos(String patron, String codeCategoria, String nombrePais, String nombreRegion,
            String nombreLocalidad, String nombreTipoNegocioDestino) throws UnknownException {

        PersistenceManager pm = null;
        try {
            if (nombrePais == null) {
                throw new UnknownException("Debe seleccionar un pais");
            }
            pm = PMF.getPMF().getPersistenceManager();
            LogicDestino logicDestino = new LogicDestino(pm);
            List<Destino> listaDestinos = (List<Destino>) logicDestino.getListarBeanByConfiguracion(codeCategoria, nombrePais, nombreRegion, nombreLocalidad, nombreTipoNegocioDestino);
            if (listaDestinos == null) {
                throw new UnknownException("No se encontraron Resultados, Revise Parametros de Configuracion");
            }
            if (patron == null || patron.trim().isEmpty()) {
                patron = ".*.*";
            } else {
                patron = ".*".concat(patron.trim().toUpperCase()).concat(".*");
            }
            List<Destino> listaFiltro = new ArrayList<Destino>();
            for (int i = 0; i < listaDestinos.size(); i++) {
                if (listaDestinos.get(i).getNombre().toUpperCase().matches(patron)) {
                    listaFiltro.add(listaDestinos.get(i));
                    continue;
                }
            }
            if (listaFiltro.isEmpty()) {
                throw new UnknownException("No se encontraron Resultados, modifique patron de busqueda");
            }
            return listaFiltro;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    /**
     * METHOD: VER MURO DESTINO OBSERVACION: FILTRO POR COORDENADAS CODIGO:
     * KCYS-131 CUS: CUS_VER_MURO_DESTINO - ANS_TURISTA ESTADO : TESTEADO
     *
     * @param gps
     * @param codeDestino
     * @param correoTurista
     * @param latitudTurista
     * @param longitudTurista
     * @param distanciaBusqueda
     * @return
     * @throws UnknownException
     */
    public static Destino verMuroDestino(Boolean gps, String codeDestino, String correoTurista,
            Double latitudTurista, Double longitudTurista, String nombrePais, String nombreRegion, String nombreLocalidad) throws UnknownException {
        PersistenceManager pm = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            LogicControlPosicion logicControPosicion = new LogicControlPosicion(pm);
            LogicDestino logicDestino = new LogicDestino(pm);
            Double latitudBusqueda = latitudTurista;
            Double longitudBusqueda = longitudTurista;

            if (!gps) {
                ControlPosicion beanControlPosicion = (ControlPosicion) logicControPosicion.getBeanByTurista(correoTurista);
                if (beanControlPosicion == null) {
                    throw new UnknownException(P.GPS);
                }
                latitudBusqueda = beanControlPosicion.getLatitud();
                longitudBusqueda = beanControlPosicion.getLongitud();
            } else {
                LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
                UsuarioTurista beanUsuarioTurista = logicUsuarioTurista.getBeanByEmail(correoTurista);
                GestionTurista.generaControlPosicion(beanUsuarioTurista, latitudBusqueda, longitudBusqueda, null, nombrePais, nombreRegion, nombreLocalidad, pm);
            }
            Destino beanDestino = (Destino) logicDestino.getBeanByCode(codeDestino);
            if (beanDestino == null) {
                throw new UnknownException(P.errorNoExistencia(Destino.class));
            }
            Double distancia = GestionShared.retornarDistancia(
                    latitudBusqueda, longitudBusqueda, beanDestino.getLatitud(),
                    beanDestino.getLongitud());
            beanDestino.setCalculoDistancia(distancia);
            beanDestino.setDistanciaTuristaDestino(formatoDistanciaTuristaADestino(distancia));
            beanDestino.setListaCalificaciones(beanDestino.getListaCalificaciones());
            beanDestino.setBeanCategoriaDestino(beanDestino.getBeanCategoriaDestino() == null ? null : beanDestino.getBeanCategoriaDestino());
            beanDestino.setBeanTuristaCreador(beanDestino.getBeanTuristaCreador());
            return beanDestino;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, null);
        }
    }

    /**
     * METHOD: VER MURO DESTINO OBSERVACION: FILTRO EN LISTA DE BUSQUEDA CODIGO:
     * KCYS-131 CUS: CUS_VER_MURO_DESTINO - ANS_TURISTA ESTADO : TESTEADO
     *
     * @param listBusquedaDestinos
     * @param codeDestino
     * @return
     * @throws UnknownException
     */
    public static Destino verMuroDestino(List<Destino> listBusquedaDestinos, String codeDestino) throws UnknownException {
        for (int i = 0; i < listBusquedaDestinos.size(); i++) {
            Destino beanDestino = listBusquedaDestinos.get(i);
            if (beanDestino.getCodeDestino().equals(codeDestino)) {
                return beanDestino;
            }
        }
        throw new UnknownException(P.errorNoExistencia(Destino.class));
    }

    /**
     * METHOD: VALORAR CONQUISTA CODIGO: KCYS-133 CUS: CUS_VALORAR_DESTINO -
     * ANS_TURISTA ESTADO : TESTEADO
     *
     * @param correoUsuarioTurista
     * @param codeDestino
     * @param valorCalificacion
     * @param opinionCalificacion
     * @return
     * @throws UnknownException
     *
     */
    public static CalificaDestino calificarDestinoConquistado(String correoUsuarioTurista, String codeDestino,
            Integer valorCalificacion, String opinionCalificacion) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            if (correoUsuarioTurista == null || correoUsuarioTurista.isEmpty()
                    || codeDestino == null || codeDestino.isEmpty() || valorCalificacion == null) {
                throw new UnknownException(P.PARAMETROS_INVALIDOS);
            }

            LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
            LogicDestino logicDestino = new LogicDestino(pm);
            LogicConquista logicConquista = new LogicConquista(pm);
            LogicCalificaDestino logicCalificaDestino = new LogicCalificaDestino(pm);

            UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoUsuarioTurista);
            if (beanUsuarioTurista == null) {
                throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
            }
            Destino beanDestino = (Destino) logicDestino.getBeanByCode(codeDestino);
            if (beanDestino == null) {
                throw new UnknownException(P.errorNoExistencia(Destino.class));
            }
            Conquista beanConquista = (Conquista) logicConquista.getBean(correoUsuarioTurista, codeDestino);
            if (beanConquista == null) {
                throw new UnknownException("Aun no ha conquistado este Destino");
            }
            if (valorCalificacion < 1 || valorCalificacion > 5) {
                throw new UnknownException("Fuera del rango de Calificacion");
            }
            CalificaDestino beanCalificaDestino = new CalificaDestino();
            String codeCalificaDestino = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
            beanCalificaDestino.setIdCalificacionDestino(KeyFactory.keyToString(KeyFactory.createKey(CalificaDestino.class.getSimpleName(), codeCalificaDestino)));
            beanCalificaDestino.setCodeCalificacionDestino(codeCalificaDestino);
            beanCalificaDestino.setFecha(new java.util.Date());
            beanCalificaDestino.setBeanTuristaCalifica(beanUsuarioTurista);
            beanCalificaDestino.setApellidoTuristaCalifica(beanUsuarioTurista.getApellido());
            beanCalificaDestino.setNombreTuristaCalifica(beanUsuarioTurista.getNombre());
            beanCalificaDestino.setCodeTuristaCalifica(beanUsuarioTurista.getCodeUsuarioTurista());
            beanCalificaDestino.setFotoPerfilTuristaCalifica(beanUsuarioTurista.getFotoPerfil());

            beanCalificaDestino.setBeanDestino(beanDestino);
            beanCalificaDestino.setCodeDestino(beanDestino.getCodeDestino());
            beanCalificaDestino.setCalificacion(valorCalificacion);
            if (opinionCalificacion != null) {
                beanCalificaDestino.setOpinion(opinionCalificacion);
            }
            beanCalificaDestino.setVersion(new java.util.Date().getTime());
            beanCalificaDestino.setIsPersistente(true);

            BeanParametro beanParametro = new BeanParametro();
            beanParametro.setBean(beanCalificaDestino);
            beanParametro.setTipoOperacion(P.INSERTAR);
            Boolean rptaCalificaDestino = logicCalificaDestino.mantenimiento(beanParametro);
            if (!rptaCalificaDestino) {
                throw new UnknownException("No se proceso la Operacion en Bean Califica Destino");
            }
            DestinoCalificaDestino beanDestinoCalificaDestino = new DestinoCalificaDestino();
            String codeDestinoCalificaDestino = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
            beanDestinoCalificaDestino.setIdDestinoCalificaDestino(KeyFactory.keyToString(KeyFactory.createKey(DestinoCalificaDestino.class.getSimpleName(), codeDestinoCalificaDestino)));
            beanDestinoCalificaDestino.setCodeDestinoCalificaDestino(codeDestinoCalificaDestino);
            beanDestinoCalificaDestino.setCodeDestino(beanDestino.getCodeDestino());
            beanDestinoCalificaDestino.setCodeCalificaDestino(codeCalificaDestino);
            beanDestinoCalificaDestino.setCodeTurista(beanUsuarioTurista.getCodeUsuarioTurista());
            beanDestinoCalificaDestino.setNombresTurista(beanUsuarioTurista.getNombre());
            beanDestinoCalificaDestino.setApellidosTurista(beanUsuarioTurista.getApellido());
            beanDestinoCalificaDestino.setFotoPerfilTuristaComenta(beanCalificaDestino.getFotoPerfilTuristaCalifica());
            beanDestinoCalificaDestino.setCalificacion(beanCalificaDestino.getCalificacion());
            beanDestinoCalificaDestino.setOpinion(beanCalificaDestino.getOpinion());
            beanDestinoCalificaDestino.setFechaTuristaComenta(beanCalificaDestino.getFecha());

            beanDestino.getListaCalificaciones().add(beanDestinoCalificaDestino);
            beanDestino.setListaCalificaciones(beanDestino.getListaCalificaciones());
            beanDestino.setPromedioCalificacion(promedioCalificacion(beanDestino.getListaCalificaciones()));
            beanParametro.setBean(beanDestino);
            beanParametro.setTipoOperacion(P.ACTUALIZAR);
            Boolean rptaDestino = logicDestino.mantenimiento(beanParametro);
            if (!rptaDestino) {
                throw new UnknownException("No se proceso la Operacion en Bean Destino");
            }
            tx.commit();
            pm.close();
            return beanCalificaDestino;

        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, tx);
        }
    }

    /**
     * END VALORAR DESTINO
     */

    /**
     * METHOD: VER FOTOS CONQUISTA DESTINO CODIGO: KCYS-134 CUS:
     * CUS_VER_FOTO_DESTINO - ANS_TURISTA ESTADO : TESTEADO
     *
     * @param codeDestino
     * @return
     * @throws UnknownException
     */
    public static List<Noticia> verFotosConquistaDestino(String codeDestino) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            LogicDestino logicDestino = new LogicDestino(pm);
            LogicNoticia logicNoticia = new LogicNoticia(pm);
            String tipoNoticia = P.CONQUISTA_DESTINO;
            Destino beanDestino = (Destino) logicDestino.getBeanByCode(codeDestino);
            if (beanDestino == null) {
                throw new UnknownException("No se encontro el Destino ");
            }
            if (beanDestino.getTipoNegocioDestino() != null) {
                if (beanDestino.getTipoNegocioDestino().equalsIgnoreCase(P.NEGOCIO)) {
                    tipoNoticia = P.CONQUISTA_NEGOCIO;
                }
            }
            List<Noticia> listNoticias = (List<Noticia>) logicNoticia.getListarBeanByDestino(codeDestino, tipoNoticia);
            List<Noticia> listNoticiasReturn = new ArrayList<Noticia>();

            for (int i = 0; i < listNoticias.size(); i++) {
                listNoticiasReturn.add(listNoticias.get(i));
                if (listNoticias.get(i).getBeanConquista() != null) {
                    listNoticiasReturn.get(i).setBeanConquista(listNoticias.get(i).getBeanConquista());
                }
                if (listNoticias.get(i).getBeanTuristaGeneraNoticia() != null) {
                    listNoticiasReturn.get(i).setBeanTuristaGeneraNoticia(listNoticias.get(i).getBeanTuristaGeneraNoticia());
                }
                if (listNoticias.get(i).getBeanDestinoConquistado() != null) {
                    listNoticiasReturn.get(i).setBeanDestinoConquistado(listNoticias.get(i).getBeanDestinoConquistado());
                }
            }
            return listNoticiasReturn;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, tx);
        }
    }

    /**
     * METHOD: CONQUISTAR DESTINOS CODIGO: KCYS-135 CUS: CUS_CONQUISTAR_DESTINO
     * - ANS_TURISTA ESTADO : TESTEADO
     *
     * @param correoTurista
     * @param latitudTurista
     * @param longitudTurista
     * @param codeDestino
     * @param urlFotoConquista
     * @param descripcion
     * @return
     * @throws UnknownException
     *
     */
    public static MuroNoticia conquistarDestinos(String correoTurista, Double latitudTurista, Double longitudTurista,
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
                throw new UnknownException(P.errorNoExistencia(Destino.class));
            }
            if (!GestionShared.dentroPerimetroDestinoNegocioColonia(latitudTurista, longitudTurista, beanDestino.getLatitud(),
                    beanDestino.getLongitud(), beanDestino.getRadio())) {
                throw new UnknownException("No se encuentra Dentro del Rango del Destino");
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
                beanConquista.setBeanPais(beanDestino.getBeanPais());
                beanConquista.setCodePais(beanDestino.getBeanPais().getCodePais());
                if (beanDestino.getBeanRegion() != null) {
                    beanConquista.setBeanRegion(beanDestino.getBeanRegion());
                    beanConquista.setCodeRegion(beanDestino.getBeanRegion().getCodeRegion());
                    if (beanDestino.getBeanLocalidad() != null) {
                        beanConquista.setBeanLocalidad(beanDestino.getBeanLocalidad());
                        beanConquista.setCodeLocalidad(beanDestino.getBeanLocalidad().getCodeLocalidad());
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
            beanConquista.setTipoConquista(P.CONQUISTA_DESTINO);
            beanConquista.setFechaConquista(new java.util.Date());
            beanConquista.setVersion(new java.util.Date().getTime());
            beanConquista.setFotoConquista(urlFotoConquista);
            beanConquista.setDescripcion(descripcion);
            /**
             * 
             */
            Calendar myGregorian = GregorianCalendar.getInstance();
            Integer annio = myGregorian.get(Calendar.YEAR);
            if (beanUsuarioTurista.getFechaNacimiento() != null) {
                Date fechaNacimiento = beanUsuarioTurista.getFechaNacimiento();
                SimpleDateFormat mySimpleFormat = new SimpleDateFormat("YYYY");
                Integer annioTurista = Integer.parseInt(mySimpleFormat.format(fechaNacimiento));
                beanConquista.setEdad(annio - annioTurista);
            }
            beanConquista.setAnnio(annio);
            beanConquista.setMes(myGregorian.get(Calendar.MONTH));
            beanConquista.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
            beanConquista.setNacionalidad(beanUsuarioTurista.getNombrePaisNacimiento());
            /**
             * 
             */
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
            if (beanConquistaCount == null) {//Si no existe la conquista quiere decir que es la primera ves que conquista el destino..
                if (beanUsuarioTurista.getNumeroConquistasDistintas() != null) {
                    beanUsuarioTurista.setNumeroConquistasDistintas(beanUsuarioTurista.getNumeroConquistasDistintas() + 1);
                } else {
                    beanUsuarioTurista.setNumeroConquistasDistintas(0);
                }
            }
            beanUsuarioTurista.setTotalConquistas(beanUsuarioTurista.getTotalConquistas() + 1);
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
		            .withUrl("/taskMuroNoticiaCrearDestinoConquistar")
		            .param("codeNoticia", beanMuroNoticia.getCodeNoticia()).header("Host", P.HOST));*/
            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));

            Notificacion beanNotificacion = generaNotificacion(beanMuroNoticia.getBeanNoticia(), P.CONQUISTA, beanParametro, pm);
            if (beanNotificacion == null) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            /*Queue queue = QueueFactory.getQueue("notificacion-queuepush");
			queue.add(TaskOptions.Builder
		            .withUrl("/taskNotificacionConquistarDestino")
		            .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));*/
            //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));

            Boolean rptaControlPosicion = GestionTurista.generaControlPosicion(beanUsuarioTurista, beanDestino.getLatitud(), beanDestino.getLongitud(), null, beanDestino.getNombrePais(), beanDestino.getNombreRegion(), beanDestino.getNombreLocalidad(), pm);
            if (!rptaControlPosicion) {
                throw new UnknownException(P.ERROR_OPERACION);
            }
            tx.commit();
            pm.close();
            Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
            queueMuroNoticia.add(TaskOptions.Builder
                    .withUrl("/taskMuroNoticiaCrearDestinoConquistar")
                    .param("codeNoticia", beanMuroNoticia.getCodeNoticia()).header("Host", P.HOST));
            Queue queue = QueueFactory.getQueue("notificacion-queuepush");
            queue.add(TaskOptions.Builder
                    .withUrl("/taskNotificacionConquistarDestino")
                    .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));
            beanMuroNoticia.setBeanNoticia(null);
            return beanMuroNoticia;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } finally {
            GestionShared.closeConnection(pm, tx);
        }
    }

    /**
     * CODIGO: KCYS-137 CUS: CUS_CREAR_DESTINO - ANS_TURISTA ESTADO: TESTEADO
	 *
     */
    public static MuroNoticia crearDestino(Conquista beanConquista) throws UnknownException {
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
                    beanDestino.setTipoNegocioDestino(P.DESTINO);
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
                        LOG.warning("creando destino " + beanDestinoBd.getNombre());
                        beanConquista.setBeanDestino(beanDestinoBd);
                        beanConquista.setCodeDestino(beanDestinoBd.getCodeDestino());
                        Conquista beanConquistaBd = conquistarCrearDestino(beanConquista, beanParametro, pm);
                        if (beanConquistaBd != null) {
                            LOG.warning("creando conquista " + beanConquistaBd.getDescripcion());
                            beanUsuarioTuristaConquistador.setTotalConquistas(beanUsuarioTuristaConquistador.getTotalConquistas() + 1);
                            beanUsuarioTuristaConquistador.setTotalDescubiertos(beanUsuarioTuristaConquistador.getTotalDescubiertos() == null ? 1 : beanUsuarioTuristaConquistador.getTotalDescubiertos() + 1);
                            beanUsuarioTuristaConquistador.setNumeroConquistasDistintas(beanUsuarioTuristaConquistador.getNumeroConquistasDistintas() == null ? 1 : (beanUsuarioTuristaConquistador.getNumeroConquistasDistintas() + 1));
                            beanParametro.setBean(beanUsuarioTuristaConquistador);
                            beanParametro.setTipoOperacion(P.ACTUALIZAR);
                            UsuarioTurista usuarioTuristaBd = logicUsuarioTurista.mantenimientoReturn(beanParametro);
                            if (usuarioTuristaBd != null) {
                                LOG.warning("actualizando usuarioturista " + usuarioTuristaBd.getNombre());
                                beanConquista.setBeanTuristaConquista(usuarioTuristaBd);
                                MuroNoticia beanMuroNoticiaBd = generaNoticia(beanConquista, beanParametro, pm);
                                if (beanMuroNoticiaBd != null) {
                                    LOG.warning("generando noticia " + beanMuroNoticiaBd.getCodeNoticia());                                   
                                    LOG.warning("CurrentModule " + ModulesServiceFactory.getModulesService().getCurrentModule());
                                    LOG.warning("CurrentVersion " + ModulesServiceFactory.getModulesService().getCurrentVersion());
                                    //LOG.warning("CurrentInstanceId "+ModulesServiceFactory.getModulesService().getCurrentInstanceId());
                                    LOG.warning("VersionHostname " + ModulesServiceFactory.getModulesService().getVersionHostname(ModulesServiceFactory.getModulesService().getCurrentModule(), ModulesServiceFactory.getModulesService().getCurrentVersion()));
                                    /*Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
                                    queueMuroNoticia.add(TaskOptions.Builder
								           .withUrl("/taskMuroNoticiaCrearDestinoConquistar")
								           .param("codeNoticia", beanMuroNoticiaBd.getCodeNoticia()).header("Host", P.HOST));*/
                                    //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(ModulesServiceFactory.getModulesService().getCurrentModule(),ModulesServiceFactory.getModulesService().getCurrentVersion())));							

                                    Notificacion beanNotificacion = generaNotificacion(beanMuroNoticiaBd.getBeanNoticia(), "DND", beanParametro, pm);
                                    if (beanNotificacion != null) {
                                        LOG.warning("generando notificacion " + beanNotificacion.getCodeNotificacion());
                                        /*Queue queue = QueueFactory.getQueue("notificacion-queuepush");
                                        queue.add(TaskOptions.Builder
                                                .withUrl("/taskNotificacionCrearDestino")
                                                .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));*/
                                        //.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));									           

                                        Boolean rptaControlPosicion = GestionTurista.generaControlPosicion(beanUsuarioTuristaConquistador, beanDestino.getLatitud(), beanDestino.getLongitud(), null, beanDestino.getNombrePais(), beanDestino.getNombreRegion(), beanDestino.getNombreLocalidad(), pm);
                                        if (rptaControlPosicion) {
                                            LOG.warning("Control de posicion " + rptaControlPosicion);
                                            tx.commit();
                                            pm.close();
                                            Queue queueMuroNoticia = QueueFactory.getQueue("muronoticia-queuepush");
                                            queueMuroNoticia.add(TaskOptions.Builder
                                                    .withUrl("/taskMuroNoticiaCrearDestinoConquistar")
                                                    .param("codeNoticia", beanMuroNoticiaBd.getCodeNoticia()).header("Host", P.HOST));
                                            beanMuroNoticiaBd.setBeanNoticia(null);
                                            Queue queue = QueueFactory.getQueue("notificacion-queuepush");
                                        queue.add(TaskOptions.Builder
                                                .withUrl("/taskNotificacionCrearDestino")
                                                .param("codeNotificacion", beanNotificacion.getCodeNotificacion()).header("Host", P.HOST));
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
                        throw new UnknownException("No se pudo crear Destino");
                    }
                } else {
                    throw new UnknownException("No existe conquistador");
                }
            } else {
                throw new UnknownException("Enviar datos minimos para crear el destino");
            }
        } catch (Exception ex) {
            LOG.warning("crearDestino: " + ex.getMessage());
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
            throw new UnknownException("No se encontro el Bean TipoNotificacion");
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
        beanNoticia.setTipoNoticia(P.CONQUISTA_DESTINO);//CONQUISTA DESTINO		
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
        beanMuroNoticia.setTipoNoticia(P.CONQUISTA_DESTINO);//CONQUISTA DESTINO			
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

    private static Conquista conquistarCrearDestino(Conquista beanConquista, BeanParametro beanParametro, PersistenceManager pm) throws UnknownException {
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
        /**
         * 
         */
        Calendar myGregorian = GregorianCalendar.getInstance();
        Integer annio = myGregorian.get(Calendar.YEAR);
        if (beanUsuarioTurista.getFechaNacimiento() != null) {
            Date fechaNacimiento = beanUsuarioTurista.getFechaNacimiento();
            SimpleDateFormat mySimpleFormat = new SimpleDateFormat("YYYY");
            Integer annioTurista = Integer.parseInt(mySimpleFormat.format(fechaNacimiento));
            beanConquista.setEdad(annio - annioTurista);
        }
        beanConquista.setAnnio(annio);
        beanConquista.setMes(myGregorian.get(Calendar.MONTH));
        beanConquista.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
        beanConquista.setNacionalidad(beanUsuarioTurista.getNombrePaisNacimiento());
        /**
         * 
         */
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
        beanConquista.setTipoConquista(P.CONQUISTA_DESTINO);//CONQUISTA DESTINO	
        beanConquista.setVersion((new java.util.Date()).getTime());
        beanConquista.setIsPersistente(true);
        beanConquista.setFechaConquista(beanDestino.getFechaCreacion());
        beanParametro.setBean(beanConquista);
        beanParametro.setTipoOperacion(P.INSERTAR);
        return logicConquista.mantenimientoReturn(beanParametro);
    }

    private static Double promedioCalificacion(List<DestinoCalificaDestino> listCalificaciones) {
        Integer tam = listCalificaciones.size();
        Double cantidad = 0.0;
        for (int i = 0; i < tam; i++) {
            cantidad += listCalificaciones.get(i).getCalificacion();
        }
        return cantidad / tam;
    }

    private static String formatoDistanciaTuristaADestino(Double distanciaCalculada) {
        String unidadMedida = " Kms";
        if (distanciaCalculada < 1.0) {
            unidadMedida = " Mts";
            distanciaCalculada = distanciaCalculada * 1000;
        }
        DecimalFormat beanFormato = new DecimalFormat("0.0");
        String formatDistancia = beanFormato.format(distanciaCalculada);

        return "a ".concat(formatDistancia).concat(unidadMedida);
    }

    public static Boolean queueNotificationCrearDestino(String codeNotificacion) throws UnknownException {
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
                throw new UnknownException("No se existe Notificacion");
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
                if (beanTipoNotificacion.getCodeTipoNotificacion().equals(P.DESCUBIERTO_NUEVO_DESTINO)) {
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

    public static Boolean queueNotificationConquistarDestino(String codeNotificacion) throws UnknownException {
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
                throw new UnknownException("No se existe Notificacion");
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
                if (beanTuristaGeneraNotificacion.getApellido() != null) {
                    msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre() + " " + beanTuristaGeneraNotificacion.getApellido());
                } else {
                    msjTuristaPublicaNovedad.append(beanTuristaGeneraNotificacion.getNombre());
                }
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
                throw new UnknownException("No se existe Notificacion");
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

    public static Boolean queueMuroNoticiaCrearDestinoConquistar(String codeNoticia) throws UnknownException {
        PersistenceManager pm = null;
        try {
            pm = PMF.getPMF().getPersistenceManager();
            LogicNoticia logicNoticia = new LogicNoticia(pm);
            LogicMuroNoticia logicMuroNoticia = new LogicMuroNoticia(pm);
            Noticia beanNoticia = (Noticia) logicNoticia.getBeanByCode(codeNoticia);
            if (beanNoticia == null) {
                throw new UnknownException("No se existe Noticia");
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
                throw new UnknownException("No se publico en el  Muro");
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
}
