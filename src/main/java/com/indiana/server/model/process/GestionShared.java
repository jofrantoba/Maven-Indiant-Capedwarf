package com.indiana.server.model.process;

import com.google.appengine.api.datastore.Key;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.KeyFactory;
import com.indiana.server.model.bean.Amistad;
import com.indiana.server.model.bean.ComentarioNoticia;
import com.indiana.server.model.bean.ComparteNoticia;
import com.indiana.server.model.bean.Conquista;
import com.indiana.server.model.bean.DivulgacionNoticia;
import com.indiana.server.model.bean.Empatia;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Miembro;
import com.indiana.server.model.bean.Noticia;
import com.indiana.server.model.bean.Notificacion;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.server.model.bean.Region;
import com.indiana.server.model.bean.UsuarioTurista;
import com.indiana.server.model.logic.LogicAmistad;
import com.indiana.server.model.logic.LogicComentarioNoticia;
import com.indiana.server.model.logic.LogicComparteNoticia;
import com.indiana.server.model.logic.LogicConquista;
import com.indiana.server.model.logic.LogicDivulgacionNoticia;
import com.indiana.server.model.logic.LogicEmpatia;
import com.indiana.server.model.logic.LogicLocalidad;
import com.indiana.server.model.logic.LogicMiembro;
import com.indiana.server.model.logic.LogicNoticia;
import com.indiana.server.model.logic.LogicPais;
import com.indiana.server.model.logic.LogicParametrosSistema;
import com.indiana.server.model.logic.LogicRegion;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.P;
import com.indiana.shared.StringHex;
import com.indiana.shared.UnknownException;

public class GestionShared {
		
	
	public static void closeConnection(PersistenceManager pm, Transaction tx){
		if(!pm.isClosed()){
			if(tx!=null){
				if(tx.isActive()){
					tx.rollback();
				}			
			}
			pm.close();							
		}	
	}	
        
        public static String keyStringBean(String simpleClassName,String code){
                Key key=KeyFactory.createKey(simpleClassName, code);
		String keyString=KeyFactory.keyToString(key);    
                return keyString;
        }
        
	public static Pais insertarPais(Pais beanPais,PersistenceManager pm)throws UnknownException{
		try{
		LogicPais logicPais=new LogicPais(pm);
		int hash=beanPais.hashCode();
		String strPais = StringHex.convertStringToHex(String.valueOf(hash));
		String keyString=KeyFactory.keyToString(KeyFactory.createKey(Pais.class.getSimpleName(),strPais));
		Pais bean=logicPais.getBean(keyString);
		if(bean!=null){
			return bean;
		}else{
			if(!beanPais.getNombre().isEmpty()){
				beanPais.setIdPais(keyString);
				beanPais.setCodePais(strPais);
				beanPais.setGenerador("TURISTA");
				beanPais.setIsPersistente(true);
				beanPais.setOperacion(P.INSERTAR);
				beanPais.setVersion((new java.util.Date()).getTime());
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(beanPais);
				parametro.setTipoOperacion(beanPais.getOperacion());
				return logicPais.mantenimientoReturn(parametro);
			}else{
				return null;
			}
		}
		}catch(Exception ex){
			return null;
		}
	}
	
	public static Region insertarRegion(Region beanRegion,PersistenceManager pm)throws UnknownException{
		try{
		if(!beanRegion.getNombre().isEmpty()){				
		LogicRegion logicRegion=new LogicRegion(pm);
		int hash=beanRegion.hashCode();
		String strRegion = StringHex.convertStringToHex(String.valueOf(hash));
		String keyString=KeyFactory.keyToString(KeyFactory.createKey(Region.class.getSimpleName(),strRegion));
			beanRegion.setIdRegion(keyString);
			beanRegion.setCodeRegion(strRegion);			
			beanRegion.setGenerador("TURISTA");
			beanRegion.setIsPersistente(true);
			beanRegion.setOperacion(P.INSERTAR);
			beanRegion.setVersion((new java.util.Date()).getTime());
			beanRegion.setNombrePais(beanRegion.getBeanPais().getNombre());
			beanRegion.setCodePais(beanRegion.getBeanPais().getCodePais());			
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(beanRegion);
			parametro.setTipoOperacion(beanRegion.getOperacion());
			return logicRegion.mantenimientoReturn(parametro);
		}else{
			return null;
		}
		}catch(Exception ex){
			return null;
		}
	}
	
	public static Localidad insertarLocalidad(Localidad beanLocalidad,PersistenceManager pm)throws UnknownException{
		try{
		if(!beanLocalidad.getNombre().isEmpty()){				
		LogicLocalidad logicLocalidad=new LogicLocalidad(pm);
		int hash=beanLocalidad.hashCode();
		String strLocalidad = StringHex.convertStringToHex(String.valueOf(hash));
		String keyString=KeyFactory.keyToString(KeyFactory.createKey(Localidad.class.getSimpleName(),strLocalidad));
			beanLocalidad.setIdLocalidad(keyString);
			beanLocalidad.setCodeLocalidad(strLocalidad);			
			beanLocalidad.setGenerador("TURISTA");
			beanLocalidad.setIsPersistente(true);
			beanLocalidad.setOperacion(P.INSERTAR);
			beanLocalidad.setVersion((new java.util.Date()).getTime());
			beanLocalidad.setNombrePais(beanLocalidad.getBeanPais().getNombre());
			beanLocalidad.setCodePais(beanLocalidad.getBeanPais().getCodePais());
			beanLocalidad.setNombreRegion(beanLocalidad.getBeanRegion().getNombre());
			beanLocalidad.setCodeRegion(beanLocalidad.getBeanRegion().getCodeRegion());			
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(beanLocalidad);
			parametro.setTipoOperacion(beanLocalidad.getOperacion());
			return logicLocalidad.mantenimientoReturn(parametro);
		}else{
			return null;
		}
		}catch(Exception ex){
			return null;
		}
	}
	
	public static Boolean dentroPerimetroDestinoNegocioColonia(Double latitudInicial,Double longitudInicial,
			Double latitudFinal,Double longitudFinal,Double radioFinal) throws UnknownException{		
		try {																										
			Double calculoDistancia= retornarDistancia(latitudInicial, longitudInicial,latitudFinal, longitudFinal);								
				if(calculoDistancia<=radioFinal){
					return true;
				}else{
					return false;
				}			
		}catch(Exception ex){	
			throw new UnknownException(ex.getLocalizedMessage());		
		}
	}
	
	public static Double calcularDistancia(Double latitudInicial,Double longitudInicial,
			Double latitudFinal,Double longitudFinal,Double radioFinal,
			Double distanciaRequerida) throws UnknownException{
		
		Double distanceCalculada=retornarDistancia(latitudInicial, longitudInicial, latitudFinal, longitudFinal);			
		if(distanciaRequerida>=(distanceCalculada)){
			return distanceCalculada;
		}
		return null;		
	}
	
	public static Double retornarDistancia(Double latitudInicial, Double longitudInicial,Double latitudFinal,Double longitudFinal) throws UnknownException{		
		try {					
			BigDecimal radioTierra=new BigDecimal(6371);			
			BigDecimal exacSinLatitudInicial=BigDecimal.valueOf(Math.sin(Math.toRadians(latitudInicial)));
			BigDecimal exacSinLatitudFinal=BigDecimal.valueOf(Math.sin(Math.toRadians(latitudFinal)));
			BigDecimal exacCosLatitudInicial=BigDecimal.valueOf(Math.cos(Math.toRadians(latitudInicial)));
			BigDecimal exacCosLatitudFinal=BigDecimal.valueOf(Math.cos(Math.toRadians(latitudFinal)));
			BigDecimal exacRadLongitudInicial=BigDecimal.valueOf(Math.toRadians(longitudInicial));
			BigDecimal exacRadLongitudFinal=BigDecimal.valueOf(Math.toRadians(longitudFinal));
			BigDecimal exacRestoFinalLongitud=exacRadLongitudInicial.subtract(exacRadLongitudFinal);
			BigDecimal exacCosRestoFinalLongitud=BigDecimal.valueOf(Math.cos(exacRestoFinalLongitud.doubleValue()));
			BigDecimal exacSinLatIniFin=exacSinLatitudInicial.multiply(exacSinLatitudFinal);
			BigDecimal exacCosLatIniFin=exacCosLatitudInicial.multiply(exacCosLatitudFinal);
			BigDecimal radDistancia=exacCosLatIniFin.multiply(exacCosRestoFinalLongitud).add(exacSinLatIniFin);
			if(radDistancia.compareTo(BigDecimal.ONE)==1){
				radDistancia=BigDecimal.ONE;
			}else if(radDistancia.compareTo(BigDecimal.valueOf(-1))==-1){
				radDistancia=BigDecimal.valueOf(-1);
			}
			BigDecimal acosRadDistancia=BigDecimal.valueOf(Math.acos(Double.parseDouble(radDistancia.toString())));
			BigDecimal calculoDistancia=acosRadDistancia.multiply(radioTierra) ;		
			return Double.parseDouble(calculoDistancia.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());		
		}		
	}
	
	public static Set<UsuarioTurista> busquedaByEmpatias(String codeTuristaPublica,String codeTuristaGenera,String rangoFecha,PersistenceManager pm) throws UnknownException{						
		LogicEmpatia logicEmpatia= new LogicEmpatia(pm);
		Set<UsuarioTurista>queueUsuarios=new HashSet<UsuarioTurista>();
		List<Empatia> listEmpatias= (List<Empatia>)logicEmpatia.getListarBeanByEmail(codeTuristaGenera,new Long(rangoFecha));
		if(listEmpatias!=null){
			Iterator<Empatia> listEmpatiaIterator= listEmpatias.iterator();
			while(listEmpatiaIterator.hasNext()){												
				UsuarioTurista beanUsuarioTurista1= listEmpatiaIterator.next().getBeanTuristaUno();
				UsuarioTurista beanUsuarioTurista2= listEmpatiaIterator.next().getBeanTuristaDos();
				queueUsuarios.add(beanUsuarioTurista1);queueUsuarios.add(beanUsuarioTurista2);
			}
		}	
		return queueUsuarios;
	}
	
	public static Set<UsuarioTurista> busquedaByConquistas(String codeTurista,String codeDestino,PersistenceManager pm) throws UnknownException{						
		LogicConquista  logicConquista= new LogicConquista(pm);
		Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();			
		List<Conquista> listConquistasDestino=(List<Conquista>)logicConquista.getListarBeanByDestino(codeTurista,codeDestino);						
			Iterator<Conquista>listConquistasIterator=listConquistasDestino.iterator();
			while(listConquistasIterator.hasNext()){
				queueTuristas.add(listConquistasIterator.next().getBeanTuristaConquista());
			}					
			return queueTuristas;
	}
	
	public static Set<UsuarioTurista> busquedaByDCS(String codeTuristaPublica,String codeNoticiaActual,String codeTuristaGenera,String rangoFecha,PersistenceManager pm) throws UnknownException{						
		LogicComentarioNoticia logicComentarioNoticia=new LogicComentarioNoticia(pm);
		LogicComparteNoticia logicComparteNoticia= new LogicComparteNoticia(pm);
		LogicDivulgacionNoticia logicDivulgacionNoticia= new LogicDivulgacionNoticia(pm);
		LogicNoticia logicNoticia= new LogicNoticia(pm);	
		Set<UsuarioTurista> queueUsuarios= new HashSet<UsuarioTurista>();
		List<Noticia> listNoticias=(List<Noticia>)logicNoticia.getListarBeanByRangoFecha(codeTuristaGenera,new Long(rangoFecha));
		  for (int i = 0; i < listNoticias.size(); i++) {
			  String codeNoticia= listNoticias.get(i).getCodeNoticia();
				List<ComentarioNoticia> listComentarioNoticia= (List<ComentarioNoticia>) logicComentarioNoticia.getListBean(codeNoticia);
				if(listComentarioNoticia!=null){
					Iterator<ComentarioNoticia> listComentarioIterator= listComentarioNoticia.iterator();
					while (listComentarioIterator.hasNext()){
						UsuarioTurista beanTuristaComenta=listComentarioIterator.next().getBeanTuristaComenta();						
						queueUsuarios.add(beanTuristaComenta);						
					}			
				}
				List<ComparteNoticia> listComparteNoticia= (List<ComparteNoticia>) logicComparteNoticia.getListBean(codeNoticia);		
				if(listComparteNoticia!=null){
					Iterator<ComparteNoticia> listComparteIterator= listComparteNoticia.iterator();
					while (listComparteIterator.hasNext()){		
						UsuarioTurista beanTuristaComparte=listComparteIterator.next().getBeanTuristaComparte();
							queueUsuarios.add(beanTuristaComparte);						
					}
				}						
				List<DivulgacionNoticia> listDivulgacionNoticia= (List<DivulgacionNoticia>) logicDivulgacionNoticia.getListBean(codeNoticia);
				if(listDivulgacionNoticia!=null){
					Iterator<DivulgacionNoticia> listDivulgacionIterator= listDivulgacionNoticia.iterator();
					while (listDivulgacionIterator.hasNext()){	
						UsuarioTurista beanTuristaDivulgacion=listDivulgacionIterator.next().getBeanTuristaDivulga();
							queueUsuarios.add(beanTuristaDivulgacion);						
					}
				}
		  }
		  return queueUsuarios;
	}
	
	public static Set<UsuarioTurista> busquedaByFriends(String codeTuristaGenera,String codeParametroSistema,PersistenceManager pm) throws UnknownException{							
		LogicParametrosSistema logicParametroSistema= new LogicParametrosSistema(pm);		
		Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();				
		ParametrosSistema beanParametroSistema= (ParametrosSistema)logicParametroSistema.getBean(codeParametroSistema);
		if(beanParametroSistema==null){
			throw new UnknownException("No existe el Parametro ".concat(codeParametroSistema));
		}
		Integer cantidadAmigos=-1;
		try{
			cantidadAmigos= Integer.parseInt(beanParametroSistema.getValor());
		}catch(Exception ex){
			throw new UnknownException("No se pudo convertir a entero el valor");
		}
		LogicAmistad logicAmistad=new LogicAmistad(pm);
		Set<Amistad> listFriends=logicAmistad.getListarAmigos(codeTuristaGenera);
		if(listFriends.size()>0){
			Iterator<Amistad> listAmigosIterator= listFriends.iterator();
			List<UsuarioTurista> listAmigosAuxiliar= new ArrayList<UsuarioTurista>();
			while(listAmigosIterator.hasNext()){					
				listAmigosAuxiliar.add(listAmigosIterator.next().getBeanTuristaAmigo());
			}
			if(listAmigosAuxiliar.size()<=cantidadAmigos){
				cantidadAmigos=listFriends.size();//la cantidad de la lista es menor a la cantidad que tengo como  parametro
				queueTuristas.addAll(listAmigosAuxiliar);
			}
			while(queueTuristas.size()<=cantidadAmigos){
				Integer iRandom=(int)(Math.random()*listAmigosAuxiliar.size());//encuentro el indice de manera aleatoria que esta en el rango
				queueTuristas.add(listAmigosAuxiliar.get(iRandom));
				listAmigosAuxiliar.remove(iRandom);
			}				
		}				
		  return queueTuristas;
	}
	public static Set<UsuarioTurista> busquedaByMembers(String codeColonia,PersistenceManager pm) throws UnknownException{										
		Set<UsuarioTurista> queueTuristas= new HashSet<UsuarioTurista>();						
		LogicMiembro logicMiembro= new LogicMiembro(pm);
		List<Miembro> listMiembrosColonia= (List<Miembro>)logicMiembro.getListarBeanByColonia(codeColonia);
		Iterator<Miembro> listMiembrosIterator=listMiembrosColonia.iterator();
		while(listMiembrosIterator.hasNext()){
			queueTuristas.add(listMiembrosIterator.next().getBeanTurista());
		}			
		  return queueTuristas;
	}	
}
