package com.indiana.server.model.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.indiana.server.model.bean.Colonia;
import com.indiana.server.model.bean.ColoniaInteres;
import com.indiana.server.model.bean.ControlPosicion;
import com.indiana.server.model.bean.Destino;
import com.indiana.server.model.bean.DetalleInteres;
import com.indiana.server.model.bean.EstadoMiembro;
import com.indiana.server.model.bean.Interes;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.MiembroInteres;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.bean.ProxColoniaDestino;
import com.indiana.server.model.bean.Region;
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.server.model.bean.TuristaInteres;
import com.indiana.server.model.bean.UsuarioNegocio;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.dao.PMF;
import com.indiana.server.model.logic.LogicColonia;
import com.indiana.server.model.logic.LogicColoniaInteres;
import com.indiana.server.model.logic.LogicControlPosicion;
import com.indiana.server.model.logic.LogicDestino;
import com.indiana.server.model.logic.LogicEstadoMiembro;
import com.indiana.server.model.logic.LogicInteres;
import com.indiana.server.model.logic.LogicMiembro;
import com.indiana.server.model.logic.LogicNoticia;
import com.indiana.server.model.logic.LogicNotificacion;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicTipoMovimiento;
import com.indiana.server.model.logic.LogicTipoNotificacion;
import com.indiana.server.model.logic.LogicUsuarioNegocio;
import com.indiana.server.model.logic.LogicUsuarioTurista;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.P;
import com.indiana.shared.StringHex;
import com.indiana.shared.UnknownException;
import java.util.logging.Logger;

public class GestionColony {
    private static final Logger LOG = Logger.getLogger(GestionColony.class.getName());
	
	/**
	 * CODIGO: KCYS-145
	 * CUS: CUS_CREAR_COLONIA - ANS_TURISTA
	 * ESTADO: TESTEADO
	 * */
	public static Colonia crearColonia(Colonia bean)throws UnknownException{
		PersistenceManager pm=null;
		try{			
			pm = PMF.getPMF().getPersistenceManager();					
			if(bean.getListColoniaIntereses()==null || bean.getListColoniaIntereses().size()==0){
				throw new UnknownException("Defina al menos un interes");
			}
			Set<Interes> intereses=new HashSet<Interes>();		
			Iterator<ColoniaInteres> iterador=bean.getListColoniaIntereses().iterator();
			while(iterador.hasNext()){
				ColoniaInteres beanColoniaInteres=iterador.next();
				Interes beanInteres=new Interes();
				beanInteres.setNombre(beanColoniaInteres.getNombreInteres().toUpperCase());
				intereses.add(beanInteres);
			}
			if(intereses.size()==0){
				throw new UnknownException("Ingrese al menos un interes");
			}else{
				List<Interes> interesesBd=insertarIntereses(intereses,pm);
				if(interesesBd.size()==0){
					throw new UnknownException("Error al insertar intereses");
				}
				Colonia beanColoniaBd=insertarColonia(bean,interesesBd,pm);
				if(beanColoniaBd==null){
					throw new UnknownException("Error al crear colonia");
				}else{					
					pm.close();
					beanColoniaBd.setListMiembro(null);
					beanColoniaBd.setListColoniaIntereses(null);
					beanColoniaBd.setListDetalleInteres(null);
					beanColoniaBd.setListProxColoniaDestino(null);		
					beanColoniaBd.setBeanTuristaCreador(null);
					beanColoniaBd.setBeanInteresDos(null);
					beanColoniaBd.setBeanInteresTres(null);
					beanColoniaBd.setBeanInteresUno(null);
					beanColoniaBd.setBeanLocalidadColonia(null);
					beanColoniaBd.setBeanPaisColonia(null);
					beanColoniaBd.setBeanRegionColonia(null);
					beanColoniaBd.setBeanTuristaCreador(null);
					return beanColoniaBd;
				}
			}
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm,null);
		}
	}
	
	/**
	 * CODIGO: KCYS-143
	 * CUS: CUS_UNIRSE_COLONIA - ANS_TURISTA
	 * ESTADO: NO TESTEADO
	 * */
	public static Colonia unirseColonia(Colonia bean)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			Boolean esMiembro=existeMiembroEnColonia(bean.getCodeColonia(),bean.getCodeTuristaSeUne(),pm);
			if(esMiembro){
				throw new UnknownException("Turista ya es miembro");
			}				
			if(bean.getListColoniaIntereses()==null || bean.getListColoniaIntereses().size()==0){
				throw new UnknownException("Defina al menos un interes");
			}
			Set<Interes> intereses=new HashSet<Interes>();		
			Iterator<ColoniaInteres> iterador=bean.getListColoniaIntereses().iterator();
			while(iterador.hasNext()){
				ColoniaInteres beanColoniaInteres=iterador.next();
				Interes beanInteres=new Interes();
				beanInteres.setNombre(beanColoniaInteres.getNombreInteres().toUpperCase());
				intereses.add(beanInteres);
			}
			if(intereses.size()==0){
				throw new UnknownException("Ingrese al menos un interes");
			}else{
				List<Interes> interesesBd=insertarIntereses(intereses,pm);
				if(interesesBd.size()==0){
					throw new UnknownException("Error al insertar intereses");
				}
				Colonia beanColoniaBd=updateColoniaUnirse(bean,interesesBd,pm);
				if(beanColoniaBd==null){
					throw new UnknownException("Error al crear colonia");
				}else{							
					pm.close();					
					beanColoniaBd.setListMiembro(null);
					beanColoniaBd.setListColoniaIntereses(null);
					beanColoniaBd.setListDetalleInteres(null);
					beanColoniaBd.setListProxColoniaDestino(null);		
					beanColoniaBd.setBeanTuristaCreador(null);
					beanColoniaBd.setBeanInteresDos(null);
					beanColoniaBd.setBeanInteresTres(null);
					beanColoniaBd.setBeanInteresUno(null);
					beanColoniaBd.setBeanLocalidadColonia(null);
					beanColoniaBd.setBeanPaisColonia(null);
					beanColoniaBd.setBeanRegionColonia(null);
					beanColoniaBd.setBeanTuristaCreador(null);
					return beanColoniaBd;					
				}				
			}						
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}		
	}
	
	public static Colonia entrarColonia(String codeColonia,String codeTurista,Double latitudTurista,Double longitudTurista,String nombrePais,String nombreRegion,String nombreLocalidad,Boolean gps)throws UnknownException{
		PersistenceManager pm=null;
		try{
                    LOG.info("codeColonia: "+codeColonia);
			pm = PMF.getPMF().getPersistenceManager();
			LogicMiembro logic=new LogicMiembro(pm);	
			Miembro  beanMiembro=logic.verPerfilMiembro(codeTurista, codeColonia);
			if(beanMiembro==null){
				throw new UnknownException(P.NO_ES_MIEMBRO);
			}
			if(beanMiembro.getCodeEstadoMiembro().equalsIgnoreCase("D")){
				throw new UnknownException("Vuelva a unirse a colonia");
			}
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista= logicUsuarioTurista.getBean(codeTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException("No existe turista");
			}
			LogicColonia logicColonia=new LogicColonia(pm);
			Colonia beanColoniaBd=logicColonia.getBean(codeColonia);
			if(beanColoniaBd==null){
				throw new UnknownException("No se encontro colonia");
			}
			if(!gps){
				LogicControlPosicion logicControPosicion= new LogicControlPosicion(pm);
				ControlPosicion beanControlPosicion = (ControlPosicion)logicControPosicion.getBeanByTurista(codeTurista);
				if(beanControlPosicion==null){
					throw new UnknownException("GPS");	
				}
				latitudTurista=beanControlPosicion.getLatitud();
				longitudTurista=beanControlPosicion.getLongitud();
				nombrePais=beanControlPosicion.getNombrePais();
				nombreRegion=beanControlPosicion.getNombreRegion();
				nombreLocalidad=beanControlPosicion.getNombreLocalidad();
			}else{
				Pais beanPais=null;
				Region beanRegion=null;
				Localidad beanLocalidad=null;
				if(nombrePais!=null && !nombrePais.isEmpty()){
					beanPais=new Pais();
					beanPais.setNombre(nombrePais);
					beanPais=pm.detachCopy(GestionShared.insertarPais(beanPais, pm));
					if(beanPais!=null && nombreRegion!=null && !nombreRegion.isEmpty()){
						beanRegion=new Region();
						beanRegion.setBeanPais(beanPais);
						beanRegion.setCodePais(beanPais.getCodePais());
						beanRegion.setNombrePais(beanPais.getNombre());
						beanRegion.setNombre(nombreRegion);
						beanRegion=pm.detachCopy(GestionShared.insertarRegion(beanRegion, pm));
						if(beanRegion!=null && nombreLocalidad!=null && !nombreLocalidad.isEmpty()){
							beanLocalidad=new Localidad();
							beanLocalidad.setCodeRegion(beanRegion.getCodeRegion());
							beanLocalidad.setNombreRegion(beanRegion.getNombre());
							beanLocalidad.setBeanRegion(beanRegion);
							beanLocalidad.setCodePais(beanPais.getCodePais());
							beanLocalidad.setNombrePais(beanPais.getNombre());
							beanLocalidad.setBeanPais(beanPais);
							beanLocalidad.setNombre(nombreLocalidad);
							beanLocalidad=pm.detachCopy(GestionShared.insertarLocalidad(beanLocalidad, pm));
						}
					}
				}				
			}
			Boolean estaDentroDePerimetro=GestionShared.dentroPerimetroDestinoNegocioColonia(latitudTurista, longitudTurista, beanColoniaBd.getLatitud(), beanColoniaBd.getLongitud(), beanColoniaBd.getRadio());
			if(estaDentroDePerimetro){
				if(beanMiembro.getCodeEstadoMiembro().equalsIgnoreCase(P.PENDIENTE)){
					beanColoniaBd=activarMiembroDeColonia(beanUsuarioTurista,beanColoniaBd,beanMiembro,pm);
				}
			}else{
				if(beanMiembro.getCodeEstadoMiembro().equalsIgnoreCase(P.ACTIVO)){
					beanColoniaBd=retirarMiembroDeColonia(beanUsuarioTurista,beanColoniaBd,beanMiembro,pm);
				}
			}
			if(beanColoniaBd==null){
				throw new UnknownException("Error al activar o retirar miembro");
			}
			beanUsuarioTurista=pm.detachCopy(beanUsuarioTurista);
			GestionTurista.generaControlPosicion(beanUsuarioTurista,latitudTurista,longitudTurista,null,nombrePais,nombreRegion,nombreLocalidad, pm);
			pm.close();
			beanColoniaBd.setListMiembro(null);
			beanColoniaBd.setListColoniaIntereses(null);
			beanColoniaBd.setListDetalleInteres(null);
			beanColoniaBd.setListProxColoniaDestino(null);		
			beanColoniaBd.setBeanTuristaCreador(null);
			beanColoniaBd.setBeanInteresDos(null);
			beanColoniaBd.setBeanInteresTres(null);
			beanColoniaBd.setBeanInteresUno(null);
			beanColoniaBd.setBeanLocalidadColonia(null);
			beanColoniaBd.setBeanPaisColonia(null);
			beanColoniaBd.setBeanRegionColonia(null);
			beanColoniaBd.setBeanTuristaCreador(null);
			return beanColoniaBd;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}	
	
	public static Colonia retirarMiembroDeColonia(UsuarioTurista beanUsuarioTurista,Colonia beanColonia,Miembro beanMiembro,PersistenceManager pm)throws UnknownException{
		LogicEstadoMiembro logicEstadoMiembro=new LogicEstadoMiembro(pm);
		EstadoMiembro beanEstadoMiembro=pm.detachCopy(logicEstadoMiembro.getBean(P.RETIRADO));		
		if(beanEstadoMiembro==null || beanEstadoMiembro.getIdEstadoMiembro()==null){
			throw new UnknownException("Estado R de miembro no existe");
		}
		Set<ColoniaInteres> interesesColonia=beanColonia.getListColoniaIntereses();
		Set<MiembroInteres> interesesMiembro=beanMiembro.getListMiembroIntereses();
			HashMap<String,ColoniaInteres> mapInteresesColonia=new HashMap<String,ColoniaInteres>();
			Iterator<ColoniaInteres> iterador=interesesColonia.iterator();	
			while(iterador.hasNext()){				
				ColoniaInteres bean=iterador.next();
				mapInteresesColonia.put(bean.getCodeInteres(), bean);
			}
			Iterator<MiembroInteres> iterarInteresMiembroUpdate=interesesMiembro.iterator();
			ColoniaInteres beanColoniaInteresExiste;
			double valorModaActual;
			String codigoAleatorioDetalleInteres;
			String codeDetalleInteres;
			String idDetalleInteres;
			LogicTipoMovimiento logicTipoMovimiento=new LogicTipoMovimiento(pm);
			TipoMovimiento beanTipoMovimiento=pm.detachCopy(logicTipoMovimiento.getBean("RC"));
			if(beanTipoMovimiento==null){
				throw new UnknownException("No existe tipo movimiento RC");
			}
			while(iterarInteresMiembroUpdate.hasNext()){
				MiembroInteres beanMiembroInteres=iterarInteresMiembroUpdate.next();
				try{
					beanColoniaInteresExiste=mapInteresesColonia.get(beanMiembroInteres.getCodeInteres());
					valorModaActual=beanColoniaInteresExiste.getValorModaColoniaInteres();
					beanColoniaInteresExiste.setValorModaColoniaInteres(valorModaActual-beanMiembroInteres.getValorModaMiembroInteres());
					beanMiembroInteres.setEstadoMiembroInteres("D");
					
					DetalleInteres beanDetalleInteres=new DetalleInteres();
					codigoAleatorioDetalleInteres=java.util.UUID.randomUUID().toString();
					codeDetalleInteres=StringHex.convertStringToHex(codigoAleatorioDetalleInteres);
					idDetalleInteres=KeyFactory.keyToString(KeyFactory.createKey(KeyFactory.stringToKey(beanColonia.getIdColonia()),DetalleInteres.class.getSimpleName(), codeDetalleInteres));
					beanDetalleInteres.setIdDetalleInteres(idDetalleInteres);
					beanDetalleInteres.setCodeDetalleInteres(codeDetalleInteres);
					beanDetalleInteres.setBeanInteres(beanMiembroInteres.getBeanInteres());
					beanDetalleInteres.setCodeInteres(beanMiembroInteres.getBeanInteres().getCodeInteres());
					beanDetalleInteres.setNombreInteres(beanMiembroInteres.getBeanInteres().getNombre());
					beanDetalleInteres.setValor(beanMiembroInteres.getValorModaMiembroInteres()*-1);
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
					beanDetalleInteres.setCodeColoniaInteres(beanColoniaInteresExiste.getCodeColoniaInteres());			
					beanDetalleInteres.setCodeMiembroInteres(null);			
					beanDetalleInteres.setCodeTuristaInteres(null);
					beanDetalleInteres.setVersion(new java.util.Date().getTime());
					beanColonia.getListDetalleInteres().add(beanDetalleInteres);
					
				}catch(Exception ex){
					throw new UnknownException("Interes de miembro no existe en colonia");
				}				
			}		
		beanColonia=ordenYSetInteresAnColonia(beanColonia);
		beanMiembro.setBeanColonia(beanColonia);
		beanMiembro.setBeanEstadoMiembro(beanEstadoMiembro);
		beanMiembro.setCodeEstadoMiembro(beanEstadoMiembro.getCodeEstadoMiembro());
		LogicMiembro logicMiembro=new LogicMiembro(pm);
		BeanParametro parametro=new BeanParametro();
		parametro.setBean(beanMiembro);
		parametro.setTipoOperacion("A");
		Miembro beanMiembroBd=logicMiembro.mantenimientoReturn(parametro);
		return pm.detachCopy(beanMiembroBd.getBeanColonia());
	}
	
	public static Colonia activarMiembroDeColonia(UsuarioTurista beanUsuarioTurista,Colonia beanColonia,Miembro beanMiembro,PersistenceManager pm)throws UnknownException{
		LogicEstadoMiembro logicEstadoMiembro=new LogicEstadoMiembro(pm);
		EstadoMiembro beanEstadoMiembro=pm.detachCopy(logicEstadoMiembro.getBean("A"));		
		if(beanEstadoMiembro==null || beanEstadoMiembro.getIdEstadoMiembro()==null){
			throw new UnknownException("Estado A de miembro no existe");
		}
		Set<ColoniaInteres> interesesColonia=beanColonia.getListColoniaIntereses();
		Set<MiembroInteres> interesesMiembro=beanMiembro.getListMiembroIntereses();
			HashMap<String,ColoniaInteres> mapInteresesColonia=new HashMap<String,ColoniaInteres>();
			Iterator<ColoniaInteres> iterador=interesesColonia.iterator();	
			while(iterador.hasNext()){				
				ColoniaInteres bean=iterador.next();
				mapInteresesColonia.put(bean.getCodeInteres(), bean);
			}
			Iterator<MiembroInteres> iterarInteresMiembroUpdate=interesesMiembro.iterator();
			ColoniaInteres beanColoniaInteresExiste;
			double valorModaActual;
			String codigoAleatorioDetalleInteres;
			String codeDetalleInteres;
			String idDetalleInteres;
			LogicTipoMovimiento logicTipoMovimiento=new LogicTipoMovimiento(pm);
			TipoMovimiento beanTipoMovimiento=pm.detachCopy(logicTipoMovimiento.getBean("EC"));
			if(beanTipoMovimiento==null){
				throw new UnknownException("No existe tipo movimiento EC");
			}
			while(iterarInteresMiembroUpdate.hasNext()){
				MiembroInteres beanMiembroInteres=iterarInteresMiembroUpdate.next();
				try{
					beanColoniaInteresExiste=mapInteresesColonia.get(beanMiembroInteres.getCodeInteres());
					valorModaActual=beanColoniaInteresExiste.getValorModaColoniaInteres();
					beanColoniaInteresExiste.setValorModaColoniaInteres(valorModaActual+beanMiembroInteres.getValorModaMiembroInteres());
					beanMiembroInteres.setEstadoMiembroInteres("A");
					
					DetalleInteres beanDetalleInteres=new DetalleInteres();
					codigoAleatorioDetalleInteres=java.util.UUID.randomUUID().toString();
					codeDetalleInteres=StringHex.convertStringToHex(codigoAleatorioDetalleInteres);
					idDetalleInteres=KeyFactory.keyToString(KeyFactory.createKey(KeyFactory.stringToKey(beanColonia.getIdColonia()),DetalleInteres.class.getSimpleName(), codeDetalleInteres));
					beanDetalleInteres.setIdDetalleInteres(idDetalleInteres);
					beanDetalleInteres.setCodeDetalleInteres(codeDetalleInteres);
					beanDetalleInteres.setBeanInteres(beanMiembroInteres.getBeanInteres());
					beanDetalleInteres.setCodeInteres(beanMiembroInteres.getBeanInteres().getCodeInteres());
					beanDetalleInteres.setNombreInteres(beanMiembroInteres.getBeanInteres().getNombre());
					beanDetalleInteres.setValor(beanMiembroInteres.getValorModaMiembroInteres());
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
					beanDetalleInteres.setCodeColoniaInteres(beanColoniaInteresExiste.getCodeColoniaInteres());			
					beanDetalleInteres.setCodeMiembroInteres(null);			
					beanDetalleInteres.setCodeTuristaInteres(null);
					beanDetalleInteres.setVersion(new java.util.Date().getTime());
					beanColonia.getListDetalleInteres().add(beanDetalleInteres);
					
				}catch(Exception ex){
					throw new UnknownException("Interes de miembro no existe en colonia");
				}				
			}		
		beanColonia=ordenYSetInteresAnColonia(beanColonia);		
		beanMiembro.setBeanColonia(beanColonia);
		beanMiembro.setBeanEstadoMiembro(beanEstadoMiembro);
		beanMiembro.setCodeEstadoMiembro(beanEstadoMiembro.getCodeEstadoMiembro());
		LogicMiembro logicMiembro=new LogicMiembro(pm);
		BeanParametro parametro=new BeanParametro();
		parametro.setBean(beanMiembro);
		parametro.setTipoOperacion(P.ACTUALIZAR);
		Miembro beanMiembroBd=logicMiembro.mantenimientoReturn(parametro);
		return pm.detachCopy(beanMiembroBd.getBeanColonia());
	}
	
	public static Boolean abandonarColonia(String codeColonia,String codeTurista,Double latitudTurista,Double longitudTurista,String nombrePais,String nombreRegion,String nombreLocalidad,Boolean gps)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicMiembro logic=new LogicMiembro(pm);	
			Miembro  beanMiembro=logic.verPerfilMiembro(codeTurista, codeColonia);
			if(beanMiembro==null){
				throw new UnknownException("No es miembro de colonia");
			}
			if(beanMiembro.getCodeEstadoMiembro().equalsIgnoreCase("D")){
				throw new UnknownException("Vuelva a unirse a colonia");
			}
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			UsuarioTurista beanUsuarioTurista= logicUsuarioTurista.getBean(codeTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException("No existe turista");
			}
			LogicColonia logicColonia=new LogicColonia(pm);
			Colonia beanColoniaBd=logicColonia.getBean(codeColonia);
			if(beanColoniaBd==null){
				throw new UnknownException("No se encontro colonia");
			}
			LogicEstadoMiembro logicEstadoMiembro=new LogicEstadoMiembro(pm);
			EstadoMiembro beanEstadoMiembro=pm.detachCopy(logicEstadoMiembro.getBean("D"));		
			if(beanEstadoMiembro==null || beanEstadoMiembro.getIdEstadoMiembro()==null){
				throw new UnknownException("Estado D de miembro no existe");
			}
			if(!gps){
				LogicControlPosicion logicControPosicion= new LogicControlPosicion(pm);
				ControlPosicion beanControlPosicion = (ControlPosicion)logicControPosicion.getBeanByTurista(codeTurista);
				if(beanControlPosicion==null){
					throw new UnknownException("Activar GPS para verificar su estado de miembro");	
				}
				latitudTurista=beanControlPosicion.getLatitud();
				longitudTurista=beanControlPosicion.getLongitud();
			}else{
				Pais beanPais=null;
				Region beanRegion=null;
				Localidad beanLocalidad=null;
				if(nombrePais!=null && !nombrePais.isEmpty()){
					beanPais=new Pais();
					beanPais.setNombre(nombrePais);
					beanPais=pm.detachCopy(GestionShared.insertarPais(beanPais, pm));
					if(beanPais!=null && nombreRegion!=null && !nombreRegion.isEmpty()){
						beanRegion=new Region();
						beanRegion.setBeanPais(beanPais);
						beanRegion.setCodePais(beanPais.getCodePais());
						beanRegion.setNombrePais(beanPais.getNombre());
						beanRegion.setNombre(nombreRegion);
						beanRegion=pm.detachCopy(GestionShared.insertarRegion(beanRegion, pm));
						if(beanRegion!=null && nombreLocalidad!=null && !nombreLocalidad.isEmpty()){
							beanLocalidad=new Localidad();
							beanLocalidad.setCodeRegion(beanRegion.getCodeRegion());
							beanLocalidad.setNombreRegion(beanRegion.getNombre());
							beanLocalidad.setBeanRegion(beanRegion);
							beanLocalidad.setCodePais(beanPais.getCodePais());
							beanLocalidad.setNombrePais(beanPais.getNombre());
							beanLocalidad.setBeanPais(beanPais);
							beanLocalidad.setNombre(nombreLocalidad);
							beanLocalidad=pm.detachCopy(GestionShared.insertarLocalidad(beanLocalidad, pm));
						}
					}
				}				
			}
			Set<ColoniaInteres> interesesColonia=beanColoniaBd.getListColoniaIntereses();
			Set<MiembroInteres> interesesMiembro=beanMiembro.getListMiembroIntereses();
			Set<TuristaInteres> interesesTurista=beanUsuarioTurista.getListTuristaIntereses();
				HashMap<String,ColoniaInteres> mapInteresesColonia=new HashMap<String,ColoniaInteres>();
				Iterator<ColoniaInteres> iterador=interesesColonia.iterator();	
				while(iterador.hasNext()){				
					ColoniaInteres bean=iterador.next();
					mapInteresesColonia.put(bean.getCodeInteres(), bean);
				}
				HashMap<String,TuristaInteres> mapInteresesTurista=new HashMap<String,TuristaInteres>();
				Iterator<TuristaInteres> iteradorIntTurista=interesesTurista.iterator();	
				while(iteradorIntTurista.hasNext()){				
					TuristaInteres bean=iteradorIntTurista.next();
					mapInteresesTurista.put(bean.getCodeInteres(), bean);
				}
				Iterator<MiembroInteres> iterarInteresMiembroUpdate=interesesMiembro.iterator();
				ColoniaInteres beanColoniaInteresExiste;
				TuristaInteres beanTuristaInteresExiste;
				double valorModaActualInteresColonia;
				double valorModaActualInteresTurista;
				String codigoAleatorioDetalleInteres;
				String codeDetalleInteres;
				String idDetalleInteres;
				LogicTipoMovimiento logicTipoMovimiento=new LogicTipoMovimiento(pm);
				TipoMovimiento beanTipoMovimiento=pm.detachCopy(logicTipoMovimiento.getBean("AC"));
				if(beanTipoMovimiento==null){
					throw new UnknownException("No existe tipo movimiento AC");
				}
				while(iterarInteresMiembroUpdate.hasNext()){
					MiembroInteres beanMiembroInteres=iterarInteresMiembroUpdate.next();
					try{																							
						DetalleInteres beanDetalleInteres=new DetalleInteres();
						codigoAleatorioDetalleInteres=java.util.UUID.randomUUID().toString();
						codeDetalleInteres=StringHex.convertStringToHex(codigoAleatorioDetalleInteres);
						idDetalleInteres=KeyFactory.keyToString(KeyFactory.createKey(KeyFactory.stringToKey(beanColoniaBd.getIdColonia()),DetalleInteres.class.getSimpleName(), codeDetalleInteres));
						beanDetalleInteres.setIdDetalleInteres(idDetalleInteres);
						beanDetalleInteres.setCodeDetalleInteres(codeDetalleInteres);
						beanDetalleInteres.setBeanInteres(beanMiembroInteres.getBeanInteres());
						beanDetalleInteres.setCodeInteres(beanMiembroInteres.getBeanInteres().getCodeInteres());
						beanDetalleInteres.setNombreInteres(beanMiembroInteres.getBeanInteres().getNombre());
						beanDetalleInteres.setValor(beanMiembroInteres.getValorModaMiembroInteres()*-1);
						beanDetalleInteres.setBeanColonia(beanColoniaBd);
						beanDetalleInteres.setCodeColonia(beanColoniaBd.getCodeColonia());			
						beanDetalleInteres.setNombreColonia(beanColoniaBd.getNombreColonia());
						beanDetalleInteres.setFechaCreacion(new java.util.Date());
						beanDetalleInteres.setIsPersistente(true);
						beanDetalleInteres.setBeanTipoMovimiento(beanTipoMovimiento);
						beanDetalleInteres.setCodeTipoMovimiento(beanTipoMovimiento.getCodeTipoMovimiento());
						beanDetalleInteres.setBeanTurista(beanUsuarioTurista);
						beanDetalleInteres.setCodeTurista(beanUsuarioTurista.getCodeUsuarioTurista());			
						beanDetalleInteres.setCodeMiembro(beanMiembro.getCodeMiembro());											
						beanDetalleInteres.setCodeMiembroInteres(null);									
						beanDetalleInteres.setVersion(new java.util.Date().getTime());
						beanColoniaBd.getListDetalleInteres().add(beanDetalleInteres);						
						if(beanMiembro.getCodeEstadoMiembro().equalsIgnoreCase(P.ACTIVO)){
							beanColoniaInteresExiste=mapInteresesColonia.get(beanMiembroInteres.getCodeInteres());
							valorModaActualInteresColonia=beanColoniaInteresExiste.getValorModaColoniaInteres();
							beanColoniaInteresExiste.setValorModaColoniaInteres(valorModaActualInteresColonia-beanMiembroInteres.getValorModaMiembroInteres());
							beanMiembroInteres.setEstadoMiembroInteres(P.DESACTIVO);
							beanDetalleInteres.setCodeColoniaInteres(beanColoniaInteresExiste.getCodeColoniaInteres());
						}else{
							beanDetalleInteres.setCodeColoniaInteres(null);
						}
						beanTuristaInteresExiste=mapInteresesTurista.get(beanMiembroInteres.getCodeInteres());
						valorModaActualInteresTurista=beanTuristaInteresExiste.getValorModa();
						beanTuristaInteresExiste.setValorModa(valorModaActualInteresTurista-beanMiembroInteres.getValorModaMiembroInteres());
						beanDetalleInteres.setCodeTuristaInteres(beanTuristaInteresExiste.getCodeTuristaInteres());
					}catch(Exception ex){
						throw new UnknownException("Interes de miembro no existe en colonia");
					}				
				}		
			beanUsuarioTurista=ordenYSetInteresAnTurista(beanUsuarioTurista);
			if(beanUsuarioTurista.getTotalColonias()!=null && beanUsuarioTurista.getTotalColonias()>=0){
				beanUsuarioTurista.setTotalColonias(beanUsuarioTurista.getTotalColonias()-1);
			}					
			BeanParametro parametro=new BeanParametro();
			parametro.setBean(beanUsuarioTurista);
			parametro.setTipoOperacion(P.ACTUALIZAR);
			beanUsuarioTurista=pm.detachCopy(logicUsuarioTurista.mantenimientoReturn(parametro));
			GestionTurista.generaControlPosicion(beanUsuarioTurista,latitudTurista,longitudTurista,null,nombrePais,nombreRegion,nombreLocalidad, pm);
			beanColoniaBd=ordenYSetInteresAnColonia(beanColoniaBd);
			beanColoniaBd.setCantidadMiembros(beanColoniaBd.getCantidadMiembros()-1);
			beanMiembro.setBeanColonia(beanColoniaBd);
			beanMiembro.setBeanEstadoMiembro(beanEstadoMiembro);
			beanMiembro.setCodeEstadoMiembro(beanEstadoMiembro.getCodeEstadoMiembro());
			LogicMiembro logicMiembro=new LogicMiembro(pm);			
			parametro.setBean(beanMiembro);
			parametro.setTipoOperacion(P.ACTUALIZAR);			
			Boolean val=logicMiembro.mantenimiento(parametro);
			pm.close();
			return val;	
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * METHOD: BUSCAR COLONIAS
	 * CODIGO: KCYS-141
	 * CUS: CUS_BUSCAR_COLONIA- ANS_TURISTA
	 * ESTADO : TESTEADO
	 * 
	 * @param patron
	 * @param nombrePais
	 * @param nombreRegion
	 * @return
	 * @throws UnknownException
	 */
	public static List<Colonia> buscarColonias(
			String patron,String nombrePais,String nombreRegion)throws UnknownException{
		PersistenceManager pm=null;
		try{
			if(nombrePais==null && nombreRegion==null){
				throw new UnknownException("Configurar Parametros de Busqueda");
			}
			pm = PMF.getPMF().getPersistenceManager();
			LogicColonia logicColonia=new LogicColonia(pm);
			List<Colonia> listColonias= (List<Colonia>) logicColonia.getListarBean(nombrePais, nombreRegion, null);
			if(listColonias== null){
				throw new UnknownException("No se encontraron Resultados, Revise Parametros de Configuracion");
			}
			if(patron==null || patron.trim().isEmpty()){
				patron=".*.*";
			}else{						
				patron=".*".concat(patron.trim().toUpperCase()).concat(".*");				
			}			
			List<Colonia> listaFiltro= new ArrayList<Colonia>();
			for (int i = 0; i < listColonias.size(); i++) {
			    if(listColonias.get(i).getNombreColonia().toUpperCase().matches(patron)){
			    	listaFiltro.add(listColonias.get(i));continue;
			    }		       
			}		
			if(listaFiltro.isEmpty()){
				throw new UnknownException("No se encontraron Resultados, modifique patron de busqueda");
			}
			return listaFiltro;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}	
	/**
	 * CODIGO: KCYS-142
	 * CUS: CUS_VER_PERFIL_COLONIA - ANS_TURISTA
	 * ESTADO: NO TESTEADO
	 * */
	public static Colonia verPerfilColonia(String codeColonia)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicColonia logicColonia=new LogicColonia(pm);
			Colonia beanColonia=new Colonia();
			Colonia beanColoniaBd=logicColonia.getBean(codeColonia);			
			Iterator<Miembro> iterador=beanColoniaBd.getListMiembro().iterator();
			List<Miembro> listaMiembros=new ArrayList<Miembro>();
			while(iterador.hasNext()){
				Miembro bean=iterador.next();
				if(!bean.getCodeEstadoMiembro().equalsIgnoreCase(P.DESACTIVO)){
					listaMiembros.add(bean);
				}				
				if(listaMiembros.size()>5){
					break;
				}
			}
			beanColonia=pm.detachCopy(beanColoniaBd);
			beanColonia.setListMiembro(listaMiembros);			
			beanColonia.setListColoniaIntereses(null);
			beanColonia.setListDetalleInteres(null);
			beanColonia.setListProxColoniaDestino(null);		
			beanColonia.setBeanTuristaCreador(null);
			beanColonia.setBeanInteresDos(null);
			beanColonia.setBeanInteresTres(null);
			beanColonia.setBeanInteresUno(null);
			beanColonia.setBeanLocalidadColonia(null);
			beanColonia.setBeanPaisColonia(null);
			beanColonia.setBeanRegionColonia(null);
			beanColonia.setBeanTuristaCreador(null);
			return beanColonia;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}	
	
	public static List<Colonia> listarColonias(
			Boolean gps,
			String nombrePais,
			String nombreRegion,
			String nombreLocalidad,
			String correoTurista,
			Double latitudTurista,
			Double longitudTurista,
			Integer limiteMostrar,
			Double distanciaBusqueda) throws UnknownException{
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicControlPosicion logicControPosicion= new LogicControlPosicion(pm);
			LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
			LogicColonia logicColonia= new LogicColonia(pm);
			Double latitudBusqueda= latitudTurista;
			Double longitudBusqueda= longitudTurista;
			String nombrePaisBusqueda= nombrePais;
			String nombreRegionBusqueda= nombreRegion;	
			String nombreLocalidadBusqueda=nombreLocalidad;
			UsuarioTurista beanUsuarioTurista= logicUsuarioTurista.getBeanByEmail(correoTurista);
			if(beanUsuarioTurista==null){
				throw new UnknownException("No existe el Turista");	
			}
			if(!gps){
				ControlPosicion beanControlPosicion = (ControlPosicion)logicControPosicion.getBeanByTurista(correoTurista);
				if(beanControlPosicion==null){
					throw new UnknownException(P.GPS);	
				}
				 latitudBusqueda= beanControlPosicion.getLatitud();
				 longitudBusqueda= beanControlPosicion.getLongitud();
				 if(beanControlPosicion.getNombrePais()!=null && !beanControlPosicion.getNombrePais().isEmpty() && !beanControlPosicion.getNombrePais().equalsIgnoreCase(P.DESCONOCIDO)){
					 nombrePaisBusqueda= beanControlPosicion.getNombrePais();
					 nombreRegionBusqueda= null;
				 }else{
					 nombrePaisBusqueda=P.PAISDEFAULT;
					 nombreRegionBusqueda= null;
				 }								 				 
				 //nombreLocalidadBusqueda=beanControlPosicion.getNombreLocalidad();
				 nombreLocalidadBusqueda=null;			
			}else{								
				GestionTurista.generaControlPosicion(beanUsuarioTurista,latitudBusqueda,longitudBusqueda,null, nombrePais,nombreRegion,nombreLocalidad, pm);
				if(nombrePais.equalsIgnoreCase(P.DESCONOCIDO)){
					nombrePais=P.PAISDEFAULT;					
				}
				nombreRegionBusqueda= null;
				nombreLocalidadBusqueda=null;
			}				
			List<Colonia> listColonias=(List<Colonia>) pm.detachCopyAll(logicColonia.getListarBean(nombrePaisBusqueda, nombreRegionBusqueda,nombreLocalidadBusqueda));
			if(listColonias==null){
				throw new UnknownException("Modifique el Rango de Busqueda");	
			}
			List<Colonia> listColoniasProximos= new ArrayList<Colonia>();
			for(int i=0; i<listColonias.size();i++){
				Colonia beanColonia= listColonias.get(i);			
				Double distancia= GestionShared.calcularDistancia(
						latitudBusqueda,longitudBusqueda, beanColonia.getLatitud(),
						beanColonia.getLongitud(),beanColonia.getRadio(),distanciaBusqueda);
				if(distancia!=null){
					beanColonia.setCalculoDistancia(distancia);
					beanColonia.setDistanciaTuristaColonia(distancia.toString());	
					beanColonia.setListMiembro(null);
					beanColonia.setListColoniaIntereses(null);
					beanColonia.setListDetalleInteres(null);
					beanColonia.setListProxColoniaDestino(null);		
					beanColonia.setBeanTuristaCreador(null);
					beanColonia.setBeanInteresDos(null);
					beanColonia.setBeanInteresTres(null);
					beanColonia.setBeanInteresUno(null);
					beanColonia.setBeanLocalidadColonia(null);
					beanColonia.setBeanPaisColonia(null);
					beanColonia.setBeanRegionColonia(null);
					beanColonia.setBeanTuristaCreador(null);
					listColoniasProximos.add(beanColonia);
				}
			}				
			Collections.sort(listColoniasProximos,new Comparator<Colonia>(){
				@Override
				public int compare(Colonia beanColonia, Colonia anotherColonia) {						
					return new Double(beanColonia.getCalculoDistancia()).compareTo(new Double(anotherColonia.getCalculoDistancia()));
				}					
			});												
			if(listColoniasProximos.size()>limiteMostrar){
				listColoniasProximos= listColoniasProximos.subList(0, limiteMostrar);
			}					
			return listColoniasProximos;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}		
	}
	
	public static List<ColoniaInteres> listColoniaInteres(String codeColonia)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicColoniaInteres logicColoniaInteres=new LogicColoniaInteres(pm);
			return logicColoniaInteres.getListarBean(codeColonia, true);
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
		
	public static List<ProxColoniaDestino> listDestinosYNegociosProximos(Double latitudColonia,Double longitudColonia,Double radioColonia,
			String nombrePaisColonia,String nombreRegionColonia,String nombreLocalidad) throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			List<ProxColoniaDestino> list= new ArrayList<ProxColoniaDestino>(); 			
			List<Destino> listDestinos= listDestinosProximos(latitudColonia, longitudColonia, nombrePaisColonia, nombreRegionColonia,nombreLocalidad, pm);
			java.util.Iterator<Destino> iterator= listDestinos.iterator();
			int c=0;
			while(iterator.hasNext()){
				Destino beanDestino = (Destino)iterator.next();
				ProxColoniaDestino beanProxColoniaDestino= new ProxColoniaDestino();
				beanProxColoniaDestino.setLatitudColonia(latitudColonia);
				beanProxColoniaDestino.setLongitudColonia(longitudColonia);
				beanProxColoniaDestino.setLatitudDestinoNegocio(beanDestino.getLatitud());
				beanProxColoniaDestino.setLongitudDestinoNegocio(beanDestino.getLongitud());
				beanProxColoniaDestino.setCodeDestinoNegocio(beanDestino.getCodeDestino());
				beanProxColoniaDestino.setDistanciaCalculada(beanDestino.getCalculoDistancia());
				beanProxColoniaDestino.setTipo(P.DESTINO);
				beanProxColoniaDestino.setNombreDestinoNegocio(beanDestino.getNombre());
				beanProxColoniaDestino.setRadioColonia(radioColonia);
				beanProxColoniaDestino.setRadioDestinoNegocio(beanDestino.getRadio());
				beanProxColoniaDestino.setVersion(new java.util.Date().getTime());
				beanProxColoniaDestino.setOperacion(P.INSERTAR);
				beanProxColoniaDestino.setOrden(c);
				c++;
				list.add(beanProxColoniaDestino);
			}
			
			List<UsuarioNegocio> listNegocios= listUsuarioNegociosProximos(latitudColonia, longitudColonia, nombrePaisColonia, nombreRegionColonia,nombreLocalidad, pm);
			java.util.Iterator<UsuarioNegocio>iteratorNegocios= listNegocios.iterator();
			c=0;
			while(iteratorNegocios.hasNext()){
				UsuarioNegocio beanNegocio = (UsuarioNegocio)iteratorNegocios.next();
				ProxColoniaDestino beanProxColoniaDestino= new ProxColoniaDestino();
				beanProxColoniaDestino.setLatitudColonia(latitudColonia);
				beanProxColoniaDestino.setLongitudColonia(longitudColonia);
				beanProxColoniaDestino.setLatitudDestinoNegocio(beanNegocio.getLatitud());
				beanProxColoniaDestino.setLongitudDestinoNegocio(beanNegocio.getLongitud());
				beanProxColoniaDestino.setCodeDestinoNegocio(beanNegocio.getCodeUsuarioNegocio());
				beanProxColoniaDestino.setDistanciaCalculada(beanNegocio.getCalculoDistancia());
				beanProxColoniaDestino.setTipo(P.NEGOCIO);
				beanProxColoniaDestino.setNombreDestinoNegocio(beanNegocio.getNombreNegocio());
				beanProxColoniaDestino.setRadioColonia(radioColonia);
				beanProxColoniaDestino.setRadioDestinoNegocio(beanNegocio.getRadio());
				beanProxColoniaDestino.setFotoDestinoNegocio(beanNegocio.getFotoPerfilNegocio());
				beanProxColoniaDestino.setVersion(new java.util.Date().getTime());
				beanProxColoniaDestino.setOperacion(P.INSERTAR);
				beanProxColoniaDestino.setOrden(c);
				c++;
				list.add(beanProxColoniaDestino);
			}
			if(list.size()==0){
				throw new UnknownException("No se Encontraron Destinos Proximos");
			}else{
				return list;
			}			
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static Miembro verPerfilMiembro(String codeColonia,String codeUsuarioTurista)throws UnknownException{		
		PersistenceManager pm=null;
		try {		
			pm = PMF.getPMF().getPersistenceManager();
			LogicMiembro logic=new LogicMiembro(pm);	
			Miembro  bean=logic.verPerfilMiembro(codeUsuarioTurista, codeColonia);
			return bean;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static Boolean existeMiembroEnColonia(String codeColonia,String codeUsuarioTurista,PersistenceManager pm)throws UnknownException{		
		try {				
			LogicMiembro logic=new LogicMiembro(pm);	
			return logic.existeMiembro(codeUsuarioTurista, codeColonia);						
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}		
	}
	
	public static Boolean esMiembroDeColonia(String codeColonia,String codeUsuarioTurista)throws UnknownException{		
		PersistenceManager pm=null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicMiembro logic=new LogicMiembro(pm);	
			return logic.existeMiembro(codeUsuarioTurista, codeColonia);						
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}		
	}
	
	public static List<Colonia> misColonias(String codeTurista)throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx=null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Miembro.class, "MiembroGroup").addMembers("beanColonia");
			pm.getFetchPlan().addGroup("MiembroGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicMiembro logic=new LogicMiembro(pm);	
			List<Miembro> listaMiembro=(List<Miembro>)pm.detachCopyAll(logic.getListarBean(null,codeTurista,"ALL"));
			tx.commit();
			List<Colonia> listaColonias=new ArrayList<Colonia>();
			Iterator<Miembro> iterador=listaMiembro.iterator();
			while(iterador.hasNext()){
				Miembro bean=iterador.next();
				Colonia beanColonia=new Colonia();				
				beanColonia.setCodeMiembro(bean.getCodeMiembro());
				beanColonia.setEstadoVisibilidad(bean.getEstadoVisibilidad());
				beanColonia.setRadio(bean.getBeanColonia().getRadio());
				beanColonia.setNombreColonia(bean.getBeanColonia().getNombreColonia());
				beanColonia.setNombreInteresUno(bean.getBeanColonia().getNombreInteresUno());
				beanColonia.setModaInteresUno(bean.getBeanColonia().getModaInteresUno());
				beanColonia.setNombreInteresDos(bean.getBeanColonia().getNombreInteresDos());
				beanColonia.setModaInteresDos(bean.getBeanColonia().getModaInteresDos());
				beanColonia.setNombreInteresTres(bean.getBeanColonia().getNombreInteresTres());
				beanColonia.setModaInteresTres(bean.getBeanColonia().getModaInteresTres());
				beanColonia.setIdColonia(bean.getBeanColonia().getIdColonia());
				beanColonia.setCodeColonia(bean.getBeanColonia().getCodeColonia());
				beanColonia.setCantidadMiembros(bean.getBeanColonia().getCantidadMiembros());
				beanColonia.setDescripcion(bean.getBeanColonia().getDescripcion());
				beanColonia.setLatitud(bean.getBeanColonia().getLatitud());
				beanColonia.setLongitud(bean.getBeanColonia().getLongitud());
				beanColonia.setNombrePaisColonia(bean.getBeanColonia().getNombrePaisColonia());
				beanColonia.setNombreRegionColonia(bean.getBeanColonia().getNombreRegionColonia());				
				listaColonias.add(beanColonia);
			}
			return listaColonias;
		}catch(Exception ex){
			throw new UnknownException(ex.getLocalizedMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	public static List<Noticia> verMuroColonia(Integer limiteInferior,Integer limiteSuperior,String codeColonia) throws UnknownException{
		PersistenceManager pm = null;		
		try{
			pm = PMF.getPMF().getPersistenceManager();	
			LogicNoticia logicNoticia= new LogicNoticia(pm);																						
			List<Noticia> listaNoticia = logicNoticia.getListarBeanByColonia(limiteInferior, limiteSuperior, codeColonia);					
			return listaNoticia;						
		} catch (Exception ex) {							
			throw new UnknownException(ex.getMessage());
		}finally {
			GestionShared.closeConnection(pm, null);
		}		
	}
	/*METODOS REUTILIZABLES*/
	private static List<UsuarioNegocio> listUsuarioNegociosProximos(Double latitudColonia,Double longitudColonia,
			String nombrePaisColonia,String nombreRegionColonia,String nombreLocalidadColonia,PersistenceManager pm) throws UnknownException{
		try{			
				LogicUsuarioNegocio logicUsuarioNegocio= new LogicUsuarioNegocio(pm);				
				LogicParametrosSistema logicParametrosSistema= new LogicParametrosSistema(pm);
				
//				codigo: DDP -> DISTANCIA NEGOCIO PROXIMOS
				String codeParametro="DNP";
				String idParametro= KeyFactory.keyToString(KeyFactory.createKey(ParametrosSistema.class.getSimpleName(),codeParametro));
				ParametrosSistema beanParametroSistema= (ParametrosSistema)logicParametrosSistema.getBean(idParametro);
				if(beanParametroSistema==null){
					throw new UnknownException("No se encontró el parametro del sistema con codigo ".concat(codeParametro));		
				}
				Double distanciaRequerida=-1.0;
				try{
					distanciaRequerida= Double.parseDouble(beanParametroSistema.getValor());
				}catch(Exception ex){
					throw new UnknownException("No es una distancia valida, verificar parametro del sistema");		
				}
//				LNPC : LIMITE NEGOCIOS PROXIMOS A COLONIA
				codeParametro="LNPC"; 
				idParametro= KeyFactory.keyToString(KeyFactory.createKey(ParametrosSistema.class.getSimpleName(),codeParametro));
				beanParametroSistema= (ParametrosSistema)logicParametrosSistema.getBean(idParametro);
				Integer limiteMostrar=-1;
				if(beanParametroSistema==null){
					throw new UnknownException("No se encontró el parametro del sistema con codigo ".concat(codeParametro));		
				}			
				try{
					limiteMostrar= Integer.parseInt(beanParametroSistema.getValor());
				}catch(Exception ex){
					throw new UnknownException("No es un limite valido, verificar parametro del sistema");		
				}
//				Filtro de los UsuarioNegocios por su pais y region si es que estuvieran los codigos de los mismos..
				List<UsuarioNegocio> listUsuarioNegocios=(List<UsuarioNegocio>) logicUsuarioNegocio.getListarBean
						(nombrePaisColonia, nombreRegionColonia,nombreLocalidadColonia);
				if(listUsuarioNegocios==null || listUsuarioNegocios.size()==0){
					//throw new UnknownException("No se Encontraron UsuarioNegocios Proximos");
					return listUsuarioNegocios;
				}
				List<UsuarioNegocio> listNegociosProximos= new ArrayList<UsuarioNegocio>();
				for(int i=0; i<listUsuarioNegocios.size();i++){
					UsuarioNegocio beanNegocio= listUsuarioNegocios.get(i);			
					Double distancia= GestionShared.calcularDistancia(latitudColonia,longitudColonia,
							beanNegocio.getLatitud(),beanNegocio.getLongitud(),beanNegocio.getRadio(), distanciaRequerida);
					if(distancia!=null){
						beanNegocio.setCalculoDistancia(distancia);
						listNegociosProximos.add(beanNegocio);
					}
				}				
				Collections.sort(listNegociosProximos,new Comparator<UsuarioNegocio>(){
					@Override
					public int compare(UsuarioNegocio beanUsuarioNegocio, UsuarioNegocio anotherUsuarioNegocio) {						
						return new Double(beanUsuarioNegocio.getCalculoDistancia()).compareTo(new Double(anotherUsuarioNegocio.getCalculoDistancia()));
					}					
				});												
//				limieteMostrar: 5 es la cantidad a mostrar de UsuarioNegocios asi como la de negocios, podria ser un parametro del sistema
//				solamente compara cuando es mayor a la cantidad a mostrar 
				if(listNegociosProximos.size()>limiteMostrar){
					listNegociosProximos= listNegociosProximos.subList(0, limiteMostrar);
				}										
				return listNegociosProximos;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());	
		}
	}
	
	private static List<Destino> listDestinosProximos(Double latitudColonia,Double longitudColonia,
			String nombrePaisColonia,String nombreRegionColonia,String nombreLocalidad,PersistenceManager pm) throws UnknownException{
		try{
			// LDPC limite mostrar proximos a colonia..
				
				LogicDestino logicDestino= new LogicDestino(pm);				
				LogicParametrosSistema logicParametrosSistema= new LogicParametrosSistema(pm);
				
//				codigo: DDP -> DISTANCIA DESTINOS PROXIMOS
				String codeParametro="DDP";
				String idParametro= KeyFactory.keyToString(KeyFactory.createKey(ParametrosSistema.class.getSimpleName(),codeParametro));
				ParametrosSistema beanParametroSistema= (ParametrosSistema)logicParametrosSistema.getBean(idParametro);
				if(beanParametroSistema==null){
					throw new UnknownException("No se encontró el parametro del sistema con codigo ".concat(codeParametro));		
				}
				Double distanciaRequerida=-1.0;
				try{
					distanciaRequerida= Double.parseDouble(beanParametroSistema.getValor());
				}catch(Exception ex){
					throw new UnknownException("No es una distancia valida, verificar parametro del sistema");		
				}
				
				codeParametro="LDPC";
				idParametro= KeyFactory.keyToString(KeyFactory.createKey(ParametrosSistema.class.getSimpleName(),codeParametro));
				beanParametroSistema= (ParametrosSistema)logicParametrosSistema.getBean(idParametro);
				Integer limiteMostrar=-1;
				if(beanParametroSistema==null){
					throw new UnknownException("No se encontró el parametro del sistema con codigo ".concat(codeParametro));		
				}			
				try{
					limiteMostrar= Integer.parseInt(beanParametroSistema.getValor());
				}catch(Exception ex){
					throw new UnknownException("No es un limite valido, verificar parametro del sistema");		
				}
//				Filtro de los destinos por su pais y region si es que estuvieran los codigos de los mismos..
				List<Destino> listDestinos=(List<Destino>) logicDestino.getListarBean(nombrePaisColonia, nombreRegionColonia,nombreLocalidad,"DESTINO");
				if(listDestinos==null || listDestinos.size()==0){
					//throw new UnknownException("No se Encontraron Destinos Proximos");
					return listDestinos;
				}
				List<Destino> listDestinosProximos= new ArrayList<Destino>();
				for(int i=0; i<listDestinos.size();i++){
					Destino beanDestino= listDestinos.get(i);			
					Double distancia= GestionShared.calcularDistancia(
							latitudColonia,longitudColonia, beanDestino.getLatitud(),
							beanDestino.getLongitud(),beanDestino.getRadio(),distanciaRequerida);
					if(distancia!=null){
						beanDestino.setCalculoDistancia(distancia);
						listDestinosProximos.add(beanDestino);
					}
				}				
				Collections.sort(listDestinosProximos,new Comparator<Destino>(){
					@Override
					public int compare(Destino beanDestino, Destino anotherDestino) {						
						return new Double(beanDestino.getCalculoDistancia()).compareTo(new Double(anotherDestino.getCalculoDistancia()));
					}					
				});												
//				limiteMostrar: 5 es la cantidad a mostrar de destinos asi como la de negocios, podria ser un parametro del sistema
//				solamente compara cuando es mayor a la cantidad a mostrar 
				if(listDestinosProximos.size()>limiteMostrar){
					listDestinosProximos= listDestinosProximos.subList(0, limiteMostrar);
				}										
				return listDestinosProximos;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());	
		}		
	}		
	
	private static Colonia updateColoniaUnirse(Colonia bean,List<Interes> intereses,PersistenceManager pm)throws UnknownException{		
		LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
		UsuarioTurista beanUsuarioTuristaSeUne= logicUsuarioTurista.getBean(bean.getCodeTuristaSeUne());
		if(beanUsuarioTuristaSeUne==null){
			throw new UnknownException("No existe turista");
		}
		LogicEstadoMiembro logicEstadoMiembro=new LogicEstadoMiembro(pm);
		EstadoMiembro beanEstadoMiembro=pm.detachCopy(logicEstadoMiembro.getBean(P.ACTIVO));
		if(beanEstadoMiembro==null){
			throw new UnknownException("No existe configuracion de estado miembro");
		}
		LogicTipoMovimiento logicTipoMovimiento=new LogicTipoMovimiento(pm);
		TipoMovimiento beanTipoMovimiento=pm.detachCopy(logicTipoMovimiento.getBean("UC"));
		if(beanTipoMovimiento==null){
			throw new UnknownException("No existe tipo movimiento UC");
		}
		Key keyTurista=KeyFactory.stringToKey(beanUsuarioTuristaSeUne.getIdUsuarioTurista());
		LogicColonia logicColonia=new LogicColonia(pm);
		Colonia beanColoniaBd=logicColonia.getBean(bean.getCodeColonia());
		if(beanColoniaBd==null){
			throw new UnknownException("No se encontro colonia");
		}
		Double distancia=GestionShared.retornarDistancia(bean.getLatitudTuristaSeUne(), bean.getLongitudTuristaSeUne(),beanColoniaBd.getLatitud(), beanColoniaBd.getLongitud());
		if(distancia>beanColoniaBd.getRadio()){
			throw new UnknownException("Usted no puede unirse, esta fuera del perimetro de la colonia");
		}
		Key keyColonia=KeyFactory.stringToKey(beanColoniaBd.getIdColonia());
		String codigoAleatorioMiembro=java.util.UUID.randomUUID().toString();
		String codeMiembro=StringHex.convertStringToHex(codigoAleatorioMiembro);
		Key keyMiembro=KeyFactory.createKey(keyColonia,Miembro.class.getSimpleName(), codeMiembro);
		Miembro beanMiembro=new Miembro();
		beanMiembro.setIdMiembro(KeyFactory.keyToString(keyMiembro));
		beanMiembro.setCodeMiembro(codeMiembro);
		beanMiembro.setFechaSuscripcion(bean.getFechaSuscripcion());
		beanMiembro.setEstadoVisibilidad(P.VISTO);
		beanMiembro.setVersion(new java.util.Date().getTime());		
		beanMiembro.setBeanColonia(beanColoniaBd);
		beanMiembro.setCodeColonia(beanColoniaBd.getCodeColonia());
		beanMiembro.setNombreColonia(beanColoniaBd.getNombreColonia());
		beanMiembro.setNombrePais(beanUsuarioTuristaSeUne.getBeanPaisNacimiento()==null?null:beanUsuarioTuristaSeUne.getBeanPaisNacimiento().getNombre());
		beanMiembro.setNombreRegion(beanUsuarioTuristaSeUne.getBeanRegionNacimiento()==null?null:beanUsuarioTuristaSeUne.getBeanRegionNacimiento().getNombre());
		beanMiembro.setIsPersistente(true);				
		beanMiembro.setBeanEstadoMiembro(beanEstadoMiembro);
		beanMiembro.setCodeEstadoMiembro(beanEstadoMiembro.getCodeEstadoMiembro());
		beanMiembro.setDistanciaAlPuntoCreacion(distancia);
		Set<ColoniaInteres> hashColoniaInteres=new HashSet<ColoniaInteres>();
		Set<MiembroInteres> hashMiembroInteres=new HashSet<MiembroInteres>();
		Set<TuristaInteres> hashTuristaInteres=new HashSet<TuristaInteres>();		
		String codeColoniaInteres;
		String idColoniaInteres;
		String codeMiembroInteres;
		String idMiembroInteres;
		String codeTuristaInteres;
		String idTuristaInteres;
		String codigoAleatorioDetalleInteres;
		String codeDetalleInteres;
		String idDetalleInteres;
		for(int i=0;i<intereses.size();i++){			
			ColoniaInteres beanColoniaInteres=new ColoniaInteres();
			codeColoniaInteres=intereses.get(i).getCodeInteres();
			idColoniaInteres=KeyFactory.keyToString(KeyFactory.createKey(keyColonia,ColoniaInteres.class.getSimpleName(), codeColoniaInteres));
			beanColoniaInteres.setIdColoniaInteres(idColoniaInteres);
			beanColoniaInteres.setCodeColoniaInteres(codeColoniaInteres);
			beanColoniaInteres.setVersion(new java.util.Date().getTime());
			beanColoniaInteres.setBeanColonia(beanColoniaBd);
			beanColoniaInteres.setCodeColonia(beanColoniaBd.getCodeColonia());	
			beanColoniaInteres.setBeanInteres(intereses.get(i));
			beanColoniaInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanColoniaInteres.setNombreInteres(intereses.get(i).getNombre());
			beanColoniaInteres.setValorModaColoniaInteres(1.0);
			beanColoniaInteres.setIsPersistente(true);
			/***/
			Calendar myGregorian = GregorianCalendar.getInstance();
			Integer annio= myGregorian.get(Calendar.YEAR);
			if(beanUsuarioTuristaSeUne.getFechaNacimiento()!=null){
				Date fechaNacimiento= beanUsuarioTuristaSeUne.getFechaNacimiento();
				SimpleDateFormat mySimpleFormat = new SimpleDateFormat("YYYY");				
		        Integer annioTurista= Integer.parseInt(mySimpleFormat.format(fechaNacimiento));
				beanColoniaInteres.setEdad(annio-annioTurista);
			}			
			beanColoniaInteres.setAnnio(annio);
			beanColoniaInteres.setMes(myGregorian.get(Calendar.MONTH));
			beanColoniaInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
			beanColoniaInteres.setNacionalidad(beanUsuarioTuristaSeUne.getNombrePaisNacimiento());
			beanColoniaInteres.setNombreColonia(bean.getNombreColonia());
			/***/
			hashColoniaInteres.add(beanColoniaInteres);
			MiembroInteres beanMiembroInteres=new MiembroInteres();
			codeMiembroInteres=intereses.get(i).getCodeInteres();
			idMiembroInteres=KeyFactory.keyToString(KeyFactory.createKey(keyMiembro,MiembroInteres.class.getSimpleName(), codeMiembroInteres));
			beanMiembroInteres.setIdMiembroInteres(idMiembroInteres);
			beanMiembroInteres.setCodeMiembroInteres(codeMiembroInteres);
			beanMiembroInteres.setBeanInteres(intereses.get(i));
			beanMiembroInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanMiembroInteres.setNombreInteres(intereses.get(i).getNombre());
			beanMiembroInteres.setValorModaMiembroInteres(1.0);
			beanMiembroInteres.setEstadoMiembroInteres(P.ACTIVO);
			beanMiembroInteres.setVersion(new java.util.Date().getTime());
			beanMiembroInteres.setBeanMiembro(beanMiembro);
			beanMiembroInteres.setCodeMiembro(beanMiembro.getCodeMiembro());
			/***/
			beanMiembroInteres.setEdad(beanColoniaInteres.getEdad());
			beanMiembroInteres.setAnnio(beanColoniaInteres.getAnnio());
			beanMiembroInteres.setMes(myGregorian.get(Calendar.MONTH));
			beanMiembroInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
			beanMiembroInteres.setNacionalidad(beanUsuarioTuristaSeUne.getNombrePaisNacimiento());
			beanMiembroInteres.setNombreColonia(bean.getNombreColonia());
			/***/
			beanMiembroInteres.setBeanTurista(beanUsuarioTuristaSeUne);
			beanMiembroInteres.setCodeTurista(beanUsuarioTuristaSeUne.getCodeUsuarioTurista());
			beanMiembroInteres.setIsPersistente(true);
			beanMiembroInteres.setCodeColonia(beanColoniaBd.getCodeColonia());
			hashMiembroInteres.add(beanMiembroInteres);
			TuristaInteres beanTuristaInteres=new TuristaInteres();
			codeTuristaInteres=intereses.get(i).getCodeInteres();
			idTuristaInteres=KeyFactory.keyToString(KeyFactory.createKey(keyTurista,TuristaInteres.class.getSimpleName(), codeTuristaInteres));
			beanTuristaInteres.setIdTuristaInteres(idTuristaInteres);
			beanTuristaInteres.setCodeTuristaInteres(codeTuristaInteres);
			beanTuristaInteres.setBeanInteres(intereses.get(i));
			beanTuristaInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanTuristaInteres.setNombreInteres(intereses.get(i).getNombre());
			beanTuristaInteres.setEstadoVisibilidad(P.VISTO);
			beanTuristaInteres.setValorModa(1.0);
			/***/
			beanMiembroInteres.setEdad(beanColoniaInteres.getEdad());
			beanMiembroInteres.setAnnio(beanColoniaInteres.getAnnio());
			beanMiembroInteres.setMes(myGregorian.get(Calendar.MONTH));
			beanMiembroInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
			beanMiembroInteres.setNacionalidad(beanUsuarioTuristaSeUne.getNombrePaisNacimiento());
			beanMiembroInteres.setNombreColonia(bean.getNombreColonia());
			/***/
			beanTuristaInteres.setVersion(new java.util.Date().getTime());			
			beanTuristaInteres.setBeanTurista(beanUsuarioTuristaSeUne);
			beanTuristaInteres.setCodeTurista(beanUsuarioTuristaSeUne.getCodeUsuarioTurista());
			beanTuristaInteres.setIsPersistente(true);
			hashTuristaInteres.add(beanTuristaInteres);
			DetalleInteres beanDetalleInteres=new DetalleInteres();
			codigoAleatorioDetalleInteres=java.util.UUID.randomUUID().toString();
			codeDetalleInteres=StringHex.convertStringToHex(codigoAleatorioDetalleInteres);
			idDetalleInteres=KeyFactory.keyToString(KeyFactory.createKey(keyColonia,DetalleInteres.class.getSimpleName(), codeDetalleInteres));
			beanDetalleInteres.setIdDetalleInteres(idDetalleInteres);
			beanDetalleInteres.setCodeDetalleInteres(codeDetalleInteres);
			beanDetalleInteres.setBeanInteres(intereses.get(i));
			beanDetalleInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanDetalleInteres.setNombreInteres(intereses.get(i).getNombre());
			beanDetalleInteres.setValor(1.0);
			beanDetalleInteres.setBeanColonia(beanColoniaBd);
			beanDetalleInteres.setCodeColonia(beanColoniaBd.getCodeColonia());			
			beanDetalleInteres.setNombreColonia(beanColoniaBd.getNombreColonia());
			beanDetalleInteres.setFechaCreacion(new java.util.Date());
			beanDetalleInteres.setIsPersistente(true);
			beanDetalleInteres.setBeanTipoMovimiento(beanTipoMovimiento);
			beanDetalleInteres.setCodeTipoMovimiento(beanTipoMovimiento.getCodeTipoMovimiento());
			beanDetalleInteres.setBeanTurista(beanUsuarioTuristaSeUne);
			beanDetalleInteres.setCodeTurista(beanUsuarioTuristaSeUne.getCodeUsuarioTurista());			
			beanDetalleInteres.setCodeMiembro(beanMiembro.getCodeMiembro());			
			beanDetalleInteres.setCodeColoniaInteres(beanColoniaInteres.getCodeColoniaInteres());			
			beanDetalleInteres.setCodeMiembroInteres(beanMiembroInteres.getCodeMiembroInteres());			
			beanDetalleInteres.setCodeTuristaInteres(beanTuristaInteres.getCodeTuristaInteres());
			beanDetalleInteres.setVersion(new java.util.Date().getTime());
			beanColoniaBd.getListDetalleInteres().add(beanDetalleInteres);
		}
		if(hashColoniaInteres.size()==0){
			throw new UnknownException("Colonia debe tener interes");
		}		
		if(hashMiembroInteres.size()==0){
			throw new UnknownException("Miembro debe tener interes");
		}				
		if(hashTuristaInteres.size()==0){
			throw new UnknownException("Turista debe tener interes");
		}		
		UsuarioTurista beanUsuarioTuristaSeUneBd=pm.detachCopy(updateTuristaInteres(beanUsuarioTuristaSeUne,hashTuristaInteres,pm));	
		if(beanUsuarioTuristaSeUneBd==null){
			throw new UnknownException("Error al actualizar intereses del turista");
		}
		beanColoniaBd=updateColoniaInteres(beanColoniaBd,hashColoniaInteres);		
		beanMiembro.setListMiembroIntereses(hashMiembroInteres);
		beanMiembro.setBeanTurista(beanUsuarioTuristaSeUneBd);
		beanMiembro.setCodeTurista(beanUsuarioTuristaSeUneBd.getCodeUsuarioTurista());
		beanMiembro.setNombreUsuarioTurista(beanUsuarioTuristaSeUneBd.getNombre());
		beanMiembro.setApellidoUsuarioTurista(beanUsuarioTuristaSeUneBd.getApellido());
		beanMiembro.setFotoPerfilUsuarioTurista(beanUsuarioTuristaSeUneBd.getFotoPerfil());
		beanMiembro.setLatitud(bean.getLatitudTuristaSeUne());
		beanMiembro.setLongitud(bean.getLongitudTuristaSeUne());		
		beanColoniaBd.getListMiembro().add(beanMiembro);
		beanColoniaBd.setCantidadMiembros(beanColoniaBd.getCantidadMiembros()+1);
		BeanParametro parametro=new BeanParametro();
		parametro.setBean(beanColoniaBd);
		parametro.setTipoOperacion(P.INSERTAR);
		//tx.begin();
		pm.setDetachAllOnCommit(true);
		Colonia beanColoniaUpdate=(Colonia)pm.detachCopy(logicColonia.mantenimientoReturn(parametro));
		//tx.commit();
		if(beanColoniaUpdate==null){
			throw new UnknownException("Error al crear la colonia");
		}else{
				Boolean generoNotificacion=generarNotificacion(beanColoniaUpdate,beanMiembro,beanUsuarioTuristaSeUneBd,"UCO",pm);
				if(generoNotificacion){
					Boolean rptaControlPosicion=GestionTurista.generaControlPosicion(beanUsuarioTuristaSeUneBd,bean.getLatitudTuristaSeUne(),bean.getLongitudTuristaSeUne(),null, beanColoniaUpdate.getNombrePaisColonia(), beanColoniaUpdate.getNombreRegionColonia(),beanColoniaUpdate.getNombreLocalidadColonia(), pm);
					if(rptaControlPosicion){
						return beanColoniaUpdate;
					}else{
						throw new UnknownException("Error al guardar posicion del turista");
					}
				}else{
					throw new UnknownException("Error al generar notificacion");
				}		
		}
	}
	
	private static Colonia insertarColonia(Colonia bean,List<Interes> intereses,PersistenceManager pm)throws UnknownException{		
		LogicUsuarioTurista logicUsuarioTurista= new LogicUsuarioTurista(pm);
		UsuarioTurista beanUsuarioTuristaCreador= logicUsuarioTurista.getBean(bean.getCodeTuristaCreador());		
		if(beanUsuarioTuristaCreador==null){
			throw new UnknownException("No existe turista");
		}
		LogicEstadoMiembro logicEstadoMiembro=new LogicEstadoMiembro(pm);
		EstadoMiembro beanEstadoMiembro=pm.detachCopy(logicEstadoMiembro.getBean("A"));
		if(beanEstadoMiembro==null){
			throw new UnknownException("No existe configuracion de estado miembro");
		}
		LogicTipoMovimiento logicTipoMovimiento=new LogicTipoMovimiento(pm);
		TipoMovimiento beanTipoMovimiento=pm.detachCopy(logicTipoMovimiento.getBean("CC"));
		if(beanTipoMovimiento==null){
			throw new UnknownException("No existe tipo movimiento CC");
		}		
		Key keyTurista=KeyFactory.stringToKey(beanUsuarioTuristaCreador.getIdUsuarioTurista());
		String codigoAleatorioColonia=java.util.UUID.randomUUID().toString();
		String codeColonia=StringHex.convertStringToHex(codigoAleatorioColonia);
		Key keyColonia=KeyFactory.createKey(Colonia.class.getSimpleName(), codeColonia);
		bean.setIdColonia(KeyFactory.keyToString(keyColonia));
		bean.setCodeColonia(codeColonia);
		bean.setIsPersistente(true);
		bean.setCantidadMiembros(1);
		bean.setVersion(new java.util.Date().getTime());				
		String codigoAleatorioMiembro=java.util.UUID.randomUUID().toString();
		String codeMiembro=StringHex.convertStringToHex(codigoAleatorioMiembro);
		Key keyMiembro=KeyFactory.createKey(keyColonia,Miembro.class.getSimpleName(), codeMiembro);
		Miembro beanMiembro=new Miembro();
		beanMiembro.setIdMiembro(KeyFactory.keyToString(keyMiembro));
		beanMiembro.setCodeMiembro(codeMiembro);
		beanMiembro.setFechaSuscripcion(bean.getFechaCreacion());
		beanMiembro.setEstadoVisibilidad("S");
		beanMiembro.setVersion(new java.util.Date().getTime());		
		beanMiembro.setBeanColonia(bean);
		beanMiembro.setCodeColonia(bean.getCodeColonia());
		beanMiembro.setNombreColonia(bean.getNombreColonia());
		beanMiembro.setNombrePais(beanUsuarioTuristaCreador.getBeanPaisNacimiento()==null?null:beanUsuarioTuristaCreador.getBeanPaisNacimiento().getNombre());
		beanMiembro.setNombreRegion(beanUsuarioTuristaCreador.getBeanRegionNacimiento()==null?null:beanUsuarioTuristaCreador.getBeanRegionNacimiento().getNombre());
		beanMiembro.setIsPersistente(true);				
		beanMiembro.setBeanEstadoMiembro(beanEstadoMiembro);
		beanMiembro.setCodeEstadoMiembro(beanEstadoMiembro.getCodeEstadoMiembro());		
		Set<ColoniaInteres> hashColoniaInteres=new HashSet<ColoniaInteres>();
		Set<MiembroInteres> hashMiembroInteres=new HashSet<MiembroInteres>();
		Set<TuristaInteres> hashTuristaInteres=new HashSet<TuristaInteres>();
		List<DetalleInteres> listaDetalleInteres=new ArrayList<DetalleInteres>();
		String codeColoniaInteres;
		String idColoniaInteres;
		String codeMiembroInteres;
		String idMiembroInteres;
		String codeTuristaInteres;
		String idTuristaInteres;
		String codigoAleatorioDetalleInteres;
		String codeDetalleInteres;
		String idDetalleInteres;
		for(int i=0;i<intereses.size();i++){
			switch(i){
			case 0: 
				bean.setBeanInteresUno(intereses.get(i));
				bean.setNombreInteresUno(intereses.get(i).getNombre());
				bean.setModaInteresUno(1.0);
				break;
			case 1: 
				bean.setBeanInteresDos(intereses.get(i));
				bean.setNombreInteresDos(intereses.get(i).getNombre());
				bean.setModaInteresDos(1.0);
				break;
			case 2: 
				bean.setBeanInteresTres(intereses.get(i));
				bean.setNombreInteresTres(intereses.get(i).getNombre());
				bean.setModaInteresTres(1.0);
				break;
			default:
				break;
			}
			ColoniaInteres beanColoniaInteres=new ColoniaInteres();
			codeColoniaInteres=intereses.get(i).getCodeInteres();
			idColoniaInteres=KeyFactory.keyToString(KeyFactory.createKey(keyColonia,ColoniaInteres.class.getSimpleName(), codeColoniaInteres));
			beanColoniaInteres.setIdColoniaInteres(idColoniaInteres);
			beanColoniaInteres.setCodeColoniaInteres(codeColoniaInteres);
			beanColoniaInteres.setVersion(new java.util.Date().getTime());
			beanColoniaInteres.setBeanColonia(bean);
			beanColoniaInteres.setCodeColonia(codeColonia);	
			beanColoniaInteres.setBeanInteres(intereses.get(i));
			beanColoniaInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanColoniaInteres.setNombreInteres(intereses.get(i).getNombre());
			beanColoniaInteres.setValorModaColoniaInteres(1.0);
			beanColoniaInteres.setIsPersistente(true);
			/***/
			Calendar myGregorian = GregorianCalendar.getInstance();
			Integer annio= myGregorian.get(Calendar.YEAR);
			if(beanUsuarioTuristaCreador.getFechaNacimiento()!=null){
				Date fechaNacimiento= beanUsuarioTuristaCreador.getFechaNacimiento();
				SimpleDateFormat mySimpleFormat = new SimpleDateFormat("YYYY");				
		        Integer annioTurista= Integer.parseInt(mySimpleFormat.format(fechaNacimiento));
				beanColoniaInteres.setEdad(annio-annioTurista);
			}			
			beanColoniaInteres.setAnnio(annio);
			beanColoniaInteres.setMes(myGregorian.get(Calendar.MONTH));
			beanColoniaInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
			beanColoniaInteres.setNacionalidad(beanUsuarioTuristaCreador.getNombrePaisNacimiento());
			beanColoniaInteres.setNombreColonia(bean.getNombreColonia());
			/***/
			hashColoniaInteres.add(beanColoniaInteres);
			MiembroInteres beanMiembroInteres=new MiembroInteres();
			codeMiembroInteres=intereses.get(i).getCodeInteres();
			idMiembroInteres=KeyFactory.keyToString(KeyFactory.createKey(keyMiembro,MiembroInteres.class.getSimpleName(), codeMiembroInteres));
			beanMiembroInteres.setIdMiembroInteres(idMiembroInteres);
			beanMiembroInteres.setCodeMiembroInteres(codeMiembroInteres);
			beanMiembroInteres.setBeanInteres(intereses.get(i));
			beanMiembroInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanMiembroInteres.setNombreInteres(intereses.get(i).getNombre());
			beanMiembroInteres.setValorModaMiembroInteres(1.0);
			beanMiembroInteres.setEstadoMiembroInteres(P.ACTIVO);
			beanMiembroInteres.setVersion(new java.util.Date().getTime());
			beanMiembroInteres.setBeanMiembro(beanMiembro);
			/***/
			beanMiembroInteres.setEdad(beanColoniaInteres.getEdad());
			beanMiembroInteres.setAnnio(beanColoniaInteres.getAnnio());
			beanMiembroInteres.setMes(myGregorian.get(Calendar.MONTH));
			beanMiembroInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
			beanMiembroInteres.setNacionalidad(beanUsuarioTuristaCreador.getNombrePaisNacimiento());
			beanMiembroInteres.setNombreColonia(bean.getNombreColonia());
			/***/
			beanMiembroInteres.setCodeMiembro(beanMiembro.getCodeMiembro());
			beanMiembroInteres.setBeanTurista(beanUsuarioTuristaCreador);
			beanMiembroInteres.setCodeTurista(beanUsuarioTuristaCreador.getCodeUsuarioTurista());
			beanMiembroInteres.setIsPersistente(true);
			beanMiembroInteres.setCodeColonia(codeColonia);
			hashMiembroInteres.add(beanMiembroInteres);
			TuristaInteres beanTuristaInteres=new TuristaInteres();
			codeTuristaInteres=intereses.get(i).getCodeInteres();
			idTuristaInteres=KeyFactory.keyToString(KeyFactory.createKey(keyTurista,TuristaInteres.class.getSimpleName(), codeTuristaInteres));
			beanTuristaInteres.setIdTuristaInteres(idTuristaInteres);
			beanTuristaInteres.setCodeTuristaInteres(codeTuristaInteres);
			beanTuristaInteres.setBeanInteres(intereses.get(i));
			beanTuristaInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanTuristaInteres.setNombreInteres(intereses.get(i).getNombre());
			beanTuristaInteres.setEstadoVisibilidad(P.VISTO);
			beanTuristaInteres.setValorModa(1.0);
			/***/
			beanTuristaInteres.setEdad(beanColoniaInteres.getEdad());
			beanTuristaInteres.setAnnio(beanColoniaInteres.getAnnio());
			beanTuristaInteres.setMes(myGregorian.get(Calendar.MONTH));
			beanTuristaInteres.setDia(myGregorian.get(Calendar.DAY_OF_MONTH));
			beanTuristaInteres.setNacionalidad(beanUsuarioTuristaCreador.getNombrePaisNacimiento());
			beanTuristaInteres.setNombreColonia(bean.getNombreColonia());
			/***/
			beanTuristaInteres.setVersion(new java.util.Date().getTime());			
			beanTuristaInteres.setBeanTurista(beanUsuarioTuristaCreador);
			beanTuristaInteres.setCodeTurista(beanUsuarioTuristaCreador.getCodeUsuarioTurista());
			beanTuristaInteres.setIsPersistente(true);
			hashTuristaInteres.add(beanTuristaInteres);
			DetalleInteres beanDetalleInteres=new DetalleInteres();
			codigoAleatorioDetalleInteres=java.util.UUID.randomUUID().toString();
			codeDetalleInteres=StringHex.convertStringToHex(codigoAleatorioDetalleInteres);
			idDetalleInteres=KeyFactory.keyToString(KeyFactory.createKey(keyColonia,DetalleInteres.class.getSimpleName(), codeDetalleInteres));
			beanDetalleInteres.setIdDetalleInteres(idDetalleInteres);
			beanDetalleInteres.setCodeDetalleInteres(codeDetalleInteres);
			beanDetalleInteres.setBeanInteres(intereses.get(i));
			beanDetalleInteres.setCodeInteres(intereses.get(i).getCodeInteres());
			beanDetalleInteres.setNombreInteres(intereses.get(i).getNombre());
			beanDetalleInteres.setValor(1.0);
			beanDetalleInteres.setBeanColonia(bean);
			beanDetalleInteres.setCodeColonia(bean.getCodeColonia());			
			beanDetalleInteres.setNombreColonia(bean.getNombreColonia());
			beanDetalleInteres.setFechaCreacion(new java.util.Date());
			beanDetalleInteres.setIsPersistente(true);
			beanDetalleInteres.setBeanTipoMovimiento(beanTipoMovimiento);
			beanDetalleInteres.setCodeTipoMovimiento(beanTipoMovimiento.getCodeTipoMovimiento());
			beanDetalleInteres.setBeanTurista(beanUsuarioTuristaCreador);
			beanDetalleInteres.setCodeTurista(beanUsuarioTuristaCreador.getCodeUsuarioTurista());			
			beanDetalleInteres.setCodeMiembro(beanMiembro.getCodeMiembro());			
			beanDetalleInteres.setCodeColoniaInteres(beanColoniaInteres.getCodeColoniaInteres());			
			beanDetalleInteres.setCodeMiembroInteres(beanMiembroInteres.getCodeMiembroInteres());			
			beanDetalleInteres.setCodeTuristaInteres(beanTuristaInteres.getCodeTuristaInteres());
			beanDetalleInteres.setVersion(new java.util.Date().getTime());
			listaDetalleInteres.add(beanDetalleInteres);
		}	
		if(hashColoniaInteres.size()==0){
			throw new UnknownException("Colonia debe tener interes");
		}		
		if(hashMiembroInteres.size()==0){
			throw new UnknownException("Miembro debe tener interes");
		}				
		if(hashTuristaInteres.size()==0){
			throw new UnknownException("Turista debe tener interes");
		}
		if(listaDetalleInteres.size()==0){
			throw new UnknownException("No existe movimiento de entrada por creacion de colonia");
		}		
		bean.setListDetalleInteres(listaDetalleInteres);				
		UsuarioTurista beanUsuarioTuristaCreadorBd=pm.detachCopy(updateTuristaInteres(beanUsuarioTuristaCreador,hashTuristaInteres,pm));	
		if(beanUsuarioTuristaCreadorBd==null){
			throw new UnknownException("Error al actualizar intereses del turista");
		}
		bean.setListColoniaIntereses(hashColoniaInteres);
		beanMiembro.setListMiembroIntereses(hashMiembroInteres);
		List<Miembro> listaMiembros=new ArrayList<Miembro>();
		listaMiembros.add(beanMiembro);
		bean.setListMiembro(listaMiembros);
		bean.setBeanTuristaCreador(beanUsuarioTuristaCreadorBd);				
		bean.setNombreCreadorColonia(beanUsuarioTuristaCreadorBd.getNombre());
		bean.setFotoPerfilCreadorColonia(beanUsuarioTuristaCreadorBd.getFotoPerfil());
		bean.setApellidoCreadorColonia(beanUsuarioTuristaCreadorBd.getApellido());
		bean.setCodeTuristaCreador(beanUsuarioTuristaCreadorBd.getCodeUsuarioTurista());		
		beanMiembro.setBeanTurista(beanUsuarioTuristaCreadorBd);
		beanMiembro.setCodeTurista(beanUsuarioTuristaCreadorBd.getCodeUsuarioTurista());
		beanMiembro.setNombreUsuarioTurista(beanUsuarioTuristaCreadorBd.getNombre());
		beanMiembro.setApellidoUsuarioTurista(beanUsuarioTuristaCreadorBd.getApellido());
		beanMiembro.setFotoPerfilUsuarioTurista(beanUsuarioTuristaCreadorBd.getFotoPerfil());
		beanMiembro.setLatitud(bean.getLatitud());
		beanMiembro.setLongitud(bean.getLongitud());
		beanMiembro.setDistanciaAlPuntoCreacion(0.0);
		Pais beanPais=pm.detachCopy(GestionShared.insertarPais(bean.getBeanPaisColonia(), pm));
		if(beanPais!=null){						
			bean.setBeanPaisColonia(beanPais);
			bean.setCodePaisColonia(beanPais.getCodePais());
			bean.setNombrePaisColonia(beanPais.getNombre());
			Region beanRegion;
			if(bean.getBeanRegionColonia()!=null){
			bean.getBeanRegionColonia().setBeanPais(beanPais);
			bean.getBeanRegionColonia().setNombrePais(beanPais.getNombre());
			bean.getBeanRegionColonia().setCodePais(beanPais.getCodePais());
			beanRegion=pm.detachCopy(GestionShared.insertarRegion(bean.getBeanRegionColonia(), pm));
			}else{
				beanRegion=null;
			}
			if(beanRegion!=null){							
				bean.setBeanRegionColonia(beanRegion);
				bean.setCodeRegionColonia(beanRegion.getCodeRegion());
				bean.setNombreRegionColonia(beanRegion.getNombre());
				Localidad beanLocalidad;
				if(bean.getBeanLocalidadColonia()!=null){
				bean.getBeanLocalidadColonia().setBeanPais(beanPais);
				bean.getBeanLocalidadColonia().setNombrePais(beanPais.getNombre());
				bean.getBeanLocalidadColonia().setCodePais(beanPais.getCodePais());
				bean.getBeanLocalidadColonia().setBeanRegion(beanRegion);
				bean.getBeanLocalidadColonia().setNombreRegion(beanRegion.getNombre());
				bean.getBeanLocalidadColonia().setCodeRegion(beanRegion.getCodeRegion());
				beanLocalidad=pm.detachCopy(GestionShared.insertarLocalidad(bean.getBeanLocalidadColonia(), pm));
				}else{
					beanLocalidad=null;
				}
				if(beanLocalidad!=null){								
					bean.setBeanLocalidadColonia(beanLocalidad);
					bean.setCodeLocalidadColonia(beanLocalidad.getCodeLocalidad());
					bean.setNombreLocalidadColonia(beanLocalidad.getNombre());
				}else{
					bean.setBeanLocalidadColonia(null);
					bean.setCodeLocalidadColonia(null);
					bean.setNombreLocalidadColonia(null);
				}
			}else{
				bean.setBeanRegionColonia(null);
				bean.setCodeRegionColonia(null);
				bean.setNombreRegionColonia(null);
				bean.setBeanLocalidadColonia(null);
				bean.setCodeLocalidadColonia(null);
				bean.setNombreLocalidadColonia(null);
			}
		}else{
			bean.setBeanPaisColonia(null);
			bean.setCodePaisColonia(null);
			bean.setNombrePaisColonia(null);
			bean.setBeanRegionColonia(null);
			bean.setCodeRegionColonia(null);
			bean.setNombreRegionColonia(null);
			bean.setBeanLocalidadColonia(null);
			bean.setCodeLocalidadColonia(null);
			bean.setNombreLocalidadColonia(null);
		}					
		Set<ProxColoniaDestino> listaDestinosProximos=bean.getListProxColoniaDestino();			
		if(listaDestinosProximos!=null && listaDestinosProximos.size()>0){			
			Iterator<ProxColoniaDestino> iter=listaDestinosProximos.iterator();
			String codeProxColoniaDestino;
			String idProxColoniaDestino;			
			while(iter.hasNext()){
				ProxColoniaDestino beanDestinoProx=iter.next();
				beanDestinoProx.setBeanColonia(bean);
				beanDestinoProx.setCodeColonia(bean.getCodeColonia());
				beanDestinoProx.setLatitudColonia(bean.getLatitud());
				beanDestinoProx.setLongitudColonia(bean.getLongitud());
				beanDestinoProx.setRadioColonia(bean.getRadio());
				codeProxColoniaDestino=beanDestinoProx.getTipo()+beanDestinoProx.getCodeDestinoNegocio();
				idProxColoniaDestino=KeyFactory.keyToString(KeyFactory.createKey(keyColonia, ProxColoniaDestino.class.getSimpleName(),codeProxColoniaDestino));
				beanDestinoProx.setIdProxColoniaDestino(idProxColoniaDestino);
				beanDestinoProx.setCodeProxColoniaDestino(codeProxColoniaDestino);
				beanDestinoProx.setVersion(new java.util.Date().getTime());
				beanDestinoProx.setIsPersistente(true);
			}
			bean.setListProxColoniaDestino(listaDestinosProximos);
		}
		LogicColonia logicColonia=new LogicColonia(pm);
		BeanParametro parametro=new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(P.INSERTAR);
		Colonia beanColoniaBd=logicColonia.mantenimientoReturn(parametro);
		if(beanColoniaBd==null){
			throw new UnknownException(P.ERROR_OPERACION);
		}else{
			beanColoniaBd=pm.detachCopy(beanColoniaBd);
				Boolean generoNotificacion=generarNotificacion(beanColoniaBd,beanMiembro,beanUsuarioTuristaCreadorBd,"CCO",pm);
				if(generoNotificacion){
					Boolean rptaControlPosicion=GestionTurista.generaControlPosicion(beanUsuarioTuristaCreadorBd,beanColoniaBd.getLatitud(),beanColoniaBd.getLongitud(),null, beanColoniaBd.getNombrePaisColonia(), beanColoniaBd.getNombreRegionColonia(),beanColoniaBd.getNombreLocalidadColonia(), pm);
					if(rptaControlPosicion){
						return beanColoniaBd;
					}else{
						throw new UnknownException("Error al guardar posicion del turista");
					}
				}else{
					throw new UnknownException("Error al generar notificacion");
				}		
		}
				
	}
	
	private static Boolean generarNotificacion(Colonia beanColonia,Miembro beanMiembro,UsuarioTurista beanUsuarioTurista,String codeTipoNotifiacion,PersistenceManager pm)throws UnknownException{
		beanMiembro=pm.detachCopy(beanMiembro);
		Notificacion beanNotificacion = new Notificacion();
		LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
		LogicTipoNotificacion logicTipoNotificacion= new LogicTipoNotificacion(pm);		
		beanNotificacion.setCodeColonia(beanColonia.getCodeColonia());
		beanNotificacion.setNombreColonia(beanColonia.getNombreColonia());
		beanNotificacion.setCodeMiembro(beanMiembro.getCodeMiembro());
		beanNotificacion.setBeanTuristaGeneraNotificacion(beanUsuarioTurista);
		beanNotificacion.setCodeTuristaGeneraNotificacion(beanUsuarioTurista.getCodeUsuarioTurista());
		beanNotificacion.setNombreTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getNombre());
		beanNotificacion.setApellidoTuristaGeneraNotificacion(beanUsuarioTurista.getApellido());
		beanNotificacion.setFotoPerfilTuristaNegocioGeneraNotificacion(beanUsuarioTurista.getFotoPerfil());
		beanNotificacion.setVersion(new java.util.Date().getTime());		
		TipoNotificacion beanTipoNotificacion= (TipoNotificacion)logicTipoNotificacion.getBean(codeTipoNotifiacion);
		beanNotificacion.setBeanTipoNotificacion(beanTipoNotificacion);
		beanNotificacion.setCodeTipoNotificacion(beanTipoNotificacion.getCodeTipoNotificacion());
		beanNotificacion.setIsPersistente(true);
		beanNotificacion.setEstadoTarea(P.PENDIENTE);
		String codigoAleatorioGenerico=java.util.UUID.randomUUID().toString();
		String codeNotificacion=StringHex.convertStringToHex(codigoAleatorioGenerico);
		beanNotificacion.setIdNotificacion(KeyFactory.keyToString(KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));
		beanNotificacion.setCodeNotificacion(codeNotificacion);
		beanNotificacion.setFechaGeneroNotificacion(new java.util.Date());
		BeanParametro beanParametro=new BeanParametro();
		beanParametro.setBean(beanNotificacion);
		beanParametro.setTipoOperacion(P.INSERTAR);
		return logicNotificacion.mantenimiento(beanParametro);
	}
	
	private static Colonia updateColoniaInteres(Colonia beanColonia,Set<ColoniaInteres> nuevosInteres)throws UnknownException{
		Set<ColoniaInteres> interesesColonia=beanColonia.getListColoniaIntereses();
		if(interesesColonia.size()==0){
			beanColonia.setListColoniaIntereses(nuevosInteres);
		}else{
			HashMap<String,ColoniaInteres> mapInteresesColonia=new HashMap<String,ColoniaInteres>();
			Iterator<ColoniaInteres> iterador=interesesColonia.iterator();	
			while(iterador.hasNext()){				
				ColoniaInteres bean=iterador.next();
				mapInteresesColonia.put(bean.getCodeInteres(), bean);
			}
			Iterator<ColoniaInteres> iterarNuevosInteres=nuevosInteres.iterator();
			ColoniaInteres beanColoniaInteresExiste;
			double valorModaActual;
			while(iterarNuevosInteres.hasNext()){
				ColoniaInteres beanNuevoInteres=iterarNuevosInteres.next();
				try{
					beanColoniaInteresExiste=mapInteresesColonia.get(beanNuevoInteres.getCodeInteres());
					valorModaActual=beanColoniaInteresExiste.getValorModaColoniaInteres();
					beanColoniaInteresExiste.setValorModaColoniaInteres(valorModaActual+1);
				}catch(Exception ex){
					beanColonia.getListColoniaIntereses().add(beanNuevoInteres);
				}				
			}
		}	
		beanColonia=ordenYSetInteresAnColonia(beanColonia);
		return beanColonia;
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
	private static UsuarioTurista updateTuristaInteres(UsuarioTurista beanUsuarioTurista,Set<TuristaInteres> nuevosInteres,PersistenceManager pm)throws UnknownException{
		Set<TuristaInteres> interesesTurista=beanUsuarioTurista.getListTuristaIntereses();
		if(interesesTurista.size()==0){
			beanUsuarioTurista.setListTuristaIntereses(nuevosInteres);
		}else{
			HashMap<String,TuristaInteres> mapInteresesTurista=new HashMap<String,TuristaInteres>();
			Iterator<TuristaInteres> iterador=interesesTurista.iterator();	
			while(iterador.hasNext()){				
				TuristaInteres bean=iterador.next();
				mapInteresesTurista.put(bean.getCodeInteres(), bean);
			}
			Iterator<TuristaInteres> iterarNuevosInteres=nuevosInteres.iterator();
			TuristaInteres beanTuristaInteresExiste;
			double valorModaActual;
			while(iterarNuevosInteres.hasNext()){
				TuristaInteres beanNuevoInteres=iterarNuevosInteres.next();
				try{
					beanTuristaInteresExiste=mapInteresesTurista.get(beanNuevoInteres.getCodeInteres());
					valorModaActual=beanTuristaInteresExiste.getValorModa();
					beanTuristaInteresExiste.setValorModa(valorModaActual+1);
				}catch(Exception ex){
					beanUsuarioTurista.getListTuristaIntereses().add(beanNuevoInteres);
				}				
			}
		}
		beanUsuarioTurista=ordenYSetInteresAnTurista(beanUsuarioTurista);			
		if(beanUsuarioTurista.getTotalColonias()!=null && beanUsuarioTurista.getTotalColonias()>=0){
			beanUsuarioTurista.setTotalColonias(beanUsuarioTurista.getTotalColonias()+1);
		}	
		if(beanUsuarioTurista.getTotalColoniasCreadas()!=null){
			beanUsuarioTurista.setTotalColoniasCreadas(beanUsuarioTurista.getTotalColoniasCreadas()+1);
		}else{
			beanUsuarioTurista.setTotalColoniasCreadas(0);
		}
		LogicUsuarioTurista logic=new LogicUsuarioTurista(pm);
		BeanParametro parametro=new BeanParametro();
		parametro.setBean(beanUsuarioTurista);
		parametro.setTipoOperacion(P.ACTUALIZAR);
		UsuarioTurista beanUsuarioTuristaBd=logic.mantenimientoReturn(parametro);
		return beanUsuarioTuristaBd;
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
	
	private static List<Interes> insertarIntereses(Set<Interes> listaIntereses,PersistenceManager pm)throws UnknownException{		
		Iterator<Interes> iterador=listaIntereses.iterator();
		int hash;
		String codeInteres;
		String idInteres;
		List<Interes> colection=new ArrayList<Interes>();
		while(iterador.hasNext()){
			Interes bean=iterador.next();
			hash=bean.hashCode();
			codeInteres= StringHex.convertStringToHex(String.valueOf(hash));
			idInteres=KeyFactory.keyToString(KeyFactory.createKey(Interes.class.getSimpleName(),codeInteres));
			bean.setIdInteres(idInteres);
			bean.setCodeInteres(codeInteres);			
			bean.setIsPersistente(true);
			bean.setVersion(new java.util.Date().getTime());	
			colection.add(bean);
		}
		LogicInteres logic=new LogicInteres(pm);
		List<BeanParametro> listBeanParametro=new ArrayList<BeanParametro>();
		BeanParametro beanParametro=new BeanParametro();
		beanParametro.setBean(colection);
		beanParametro.setTipoOperacion(P.INSERTAR);
		listBeanParametro.add(beanParametro);
		logic.mantenimiento(listBeanParametro);		
		return colection;
	}
	
}
