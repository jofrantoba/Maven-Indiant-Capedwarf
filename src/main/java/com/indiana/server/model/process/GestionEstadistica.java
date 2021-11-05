package com.indiana.server.model.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.Amistad;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.SolicitudAmistad;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicAmistad;
import com.indiana.server.model.logic.LogicColonia;
import com.indiana.server.model.logic.LogicColoniaInteres;
import com.indiana.server.model.logic.LogicConquista;
import com.indiana.server.model.logic.LogicDestino;
import com.indiana.server.model.logic.LogicMiembroInteres;
import com.indiana.server.model.logic.LogicSolicitudAmistad;
import com.indiana.server.model.logic.LogicTuristaInteres;
import com.indiana.server.model.logic.LogicUsuarioTurista;
import com.indiana.shared.P;
import com.indiana.shared.UnknownException;
import com.sun.media.Log;

public class GestionEstadistica {
	private static final Logger LOG = Logger.getLogger(GestionEstadistica.class.getName());
	/**
	 * INDICADORES ESTADISTICOS.
	 */

	/**
	 * ESTADO: TERMINADO
	 * 
	 * @param correoTurista
	 * @return
	 * @throws UnknownException
	 */

	private static List<Destino> destinosCreados(String correoTurista, PersistenceManager pm) throws UnknownException {
		try {
			LogicDestino logicDestino = new LogicDestino(pm);
			return (List<Destino>) logicDestino.getListarBeanByCreador(correoTurista);
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		}
	}

	private static List<Colonia> coloniasCreadas(String correoTurista, PersistenceManager pm) throws UnknownException {
		try {
			LogicColonia logicColonia = new LogicColonia(pm);
			return logicColonia.getListarBeanByTuristaCreador(correoTurista);
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		}
	}

	private static Set<Destino> conquistasDestinos(String correoTurista, PersistenceManager pm)
			throws UnknownException {
		try {
			Set<Destino> listConquistasDestinos = new HashSet<Destino>();
			LogicConquista logicConquista = new LogicConquista(pm);
			List<Conquista> listConquistas = logicConquista.getListarBeanByTurista(correoTurista);
			if (listConquistas == null) {
				listConquistas = new ArrayList<Conquista>();
			}
			Iterator<Conquista> iteratorConquistas = listConquistas.iterator();
			while (iteratorConquistas.hasNext()) {
				Conquista beanConquista = iteratorConquistas.next();
				listConquistasDestinos.add(beanConquista.getBeanDestino());
			}
			return listConquistasDestinos;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		}
	}

	private static Set<Amistad> primerosAmigos(String correoTurista, PersistenceManager pm) throws UnknownException {
		try {
			LogicAmistad logicAmistad = new LogicAmistad(pm);
			LogicSolicitudAmistad logicSolicitudAmistad = new LogicSolicitudAmistad(pm);
			Set<Amistad> listAmigos = logicAmistad.getListarAmigos(correoTurista);
			Set<Amistad> listPrimerosAmigos = new HashSet<Amistad>();
			Set<Amistad> listPrimerosAmigosReturn = new HashSet<Amistad>();
			List<Amistad> listEnviaronSolicitudAmigo = new ArrayList<Amistad>();
			Iterator<Amistad> iteratorAmigos = listAmigos.iterator();
			while (iteratorAmigos.hasNext()) {
				Amistad amigo = iteratorAmigos.next();
				List<Amistad> amigosDeAmigo = logicAmistad
						.getBeanByTuristaPrimeraSolicitud(amigo.getCodeTuristaAmigo());
				if (amigosDeAmigo != null) {
					for (int i = 0; i < amigosDeAmigo.size(); i++) {
						Amistad amigoDeAmigo = amigosDeAmigo.get(i);
						SolicitudAmistad beanSolicitudAmistad = (SolicitudAmistad) logicSolicitudAmistad
								.getBeanByCode(amigoDeAmigo.getCodeSolicitudAmistad());
						if (beanSolicitudAmistad.getCodeTuristaEnvia()
								.equalsIgnoreCase(amigoDeAmigo.getCodeTuristaAmigo())
								&& beanSolicitudAmistad.getCodeTuristaRecibe()
										.equalsIgnoreCase(amigo.getCodeTuristaAmigo())) {
							listEnviaronSolicitudAmigo.add(amigoDeAmigo);
						}
					}
					if (!listEnviaronSolicitudAmigo.isEmpty()) {
						Collections.sort(listEnviaronSolicitudAmigo, new Comparator<Amistad>() {
							@Override
							public int compare(Amistad beanAmistad, Amistad anotherAmistad) {
								return new Double(beanAmistad.getVersion())
										.compareTo(new Double(anotherAmistad.getVersion()));
							}
						});
						if (correoTurista.equalsIgnoreCase(listEnviaronSolicitudAmigo.get(0).getCodeTuristaAmigo())) {
							listPrimerosAmigos.add(amigo);
						}
					}
				}
			}
			Iterator<Amistad> iteratorPrimerosAmigos = listPrimerosAmigos.iterator();
			while (iteratorPrimerosAmigos.hasNext()) {
				Amistad primerAmigo = iteratorPrimerosAmigos.next();
				SolicitudAmistad beanSolicitudAmistad = (SolicitudAmistad) logicSolicitudAmistad
						.getBeanByCode(primerAmigo.getCodeSolicitudAmistad());
				if (beanSolicitudAmistad != null) {
					if (beanSolicitudAmistad.getCodeTuristaEnvia().equalsIgnoreCase(correoTurista)
							&& beanSolicitudAmistad.getCodeTuristaRecibe()
									.equalsIgnoreCase(primerAmigo.getCodeTuristaAmigo())) {
						listPrimerosAmigosReturn.add(primerAmigo);
					}
				}
			}
			return listPrimerosAmigosReturn;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		}
	}

	public static UsuarioTurista indicadoresEstadisticos(String correoTurista) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoTurista);
			if (beanUsuarioTurista == null) {
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			beanUsuarioTurista.setListColoniasCreadas(new HashSet<>(coloniasCreadas(correoTurista, pm)));
			beanUsuarioTurista.setListDestinosCreados(new HashSet<>(destinosCreados(correoTurista, pm)));
			beanUsuarioTurista.setListConquistasDestinos(conquistasDestinos(correoTurista, pm));
			beanUsuarioTurista.setListAfiliados(primerosAmigos(correoTurista, pm));
			return beanUsuarioTurista;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioTurista ieColoniasCreadas(String correoTurista) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoTurista);
			if (beanUsuarioTurista == null) {
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			beanUsuarioTurista.setListColoniasCreadas(new HashSet<>(coloniasCreadas(correoTurista, pm)));
			return beanUsuarioTurista;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioTurista ieDestinosCreados(String correoTurista) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoTurista);
			if (beanUsuarioTurista == null) {
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			beanUsuarioTurista.setListDestinosCreados(new HashSet<>(destinosCreados(correoTurista, pm)));
			return beanUsuarioTurista;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioTurista ieConquistasDistintas(String correoTurista) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoTurista);
			if (beanUsuarioTurista == null) {
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			beanUsuarioTurista.setListConquistasDestinos(conquistasDestinos(correoTurista, pm));
			return beanUsuarioTurista;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioTurista iePrimerosAfiliados(String correoTurista) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioTurista logicUsuarioTurista = new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista = (UsuarioTurista) logicUsuarioTurista.getBeanByEmail(correoTurista);
			if (beanUsuarioTurista == null) {
				throw new UnknownException(P.errorNoExistencia(UsuarioTurista.class));
			}
			beanUsuarioTurista.setListAfiliados(primerosAmigos(correoTurista, pm));
			return beanUsuarioTurista;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	
	public static List<ColoniaInteres> ieModaInteresTuristaByColoniaInteres(Integer annio,Integer mes,Integer dia,
			Integer edad,String nombreColonia,String nacionalidad) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicColoniaInteres logicColoniaInteres= new LogicColoniaInteres(pm);
			return logicColoniaInteres.getListarBean(annio, mes, dia, edad, nombreColonia, nacionalidad);						
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	public static List<TuristaInteres> ieModaInteresTuristaByTuristaInteres(Integer annio,Integer mes,Integer dia,
			String edad,String nombreColonia,String nacionalidad) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicTuristaInteres logicTuristaInteres= new LogicTuristaInteres(pm);			
			if(nacionalidad!=null){
				LOG.info("Ingresa aqui.. !");
				LOG.warning("Ingresa aqui ..");
				
				return logicTuristaInteres.getListarBean(null, null, null, null, null, nacionalidad);	
			}
			if(edad!=null){
				StringTokenizer token= new StringTokenizer(edad,"-");
				Integer rangoinferior=Integer.parseInt(token.nextToken());
				Integer rangosuperior=Integer.parseInt(token.nextToken());
				
				List<TuristaInteres> listTuristaInteresRangoEdad= new ArrayList<>();				
				for(int i=rangoinferior; i<=rangosuperior;i++){
					listTuristaInteresRangoEdad.addAll(logicTuristaInteres.getListarBean(null, null, null, i, null, null));	
				}
				return listTuristaInteresRangoEdad;
				
			}
			return null;
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	public static List<MiembroInteres> ieModaInteresTuristaByMiembroInteres(Integer annio,Integer mes,Integer dia,
			Integer edad,String nombreColonia,String nacionalidad) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicMiembroInteres logicMiembroInteres= new LogicMiembroInteres(pm);
			return logicMiembroInteres.getListarBean(annio, mes, dia, edad, nombreColonia, nacionalidad);	
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static List<Conquista> ieCantidadConquistas(Integer annio, Integer mes, Integer dia, Integer edad,
			String nombreDestino, String nacionalidad) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicConquista logicConquista = new LogicConquista(pm);
			return logicConquista.getListarBean(annio, mes, dia, edad, nombreDestino, nacionalidad);
		} catch (UnknownException ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
}