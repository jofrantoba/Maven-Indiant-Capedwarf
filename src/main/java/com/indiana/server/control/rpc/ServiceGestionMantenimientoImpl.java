package com.indiana.server.control.rpc;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.indiana.client.service.ServiceGestionMantenimiento;
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
import com.indiana.server.model.process.GestionMantenimiento;
import com.indiana.shared.ListFilterBean;
import com.indiana.shared.UnknownException;

public class ServiceGestionMantenimientoImpl extends RemoteServiceServlet implements ServiceGestionMantenimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9071049936109262193L;

	@Override
	public UsuarioAdmin logeoUsuarioAdmin(String correo, String clave)
			throws UnknownException {
		// TODO Auto-generated method stub
		UsuarioAdmin beanUsuarioAdmin=GestionMantenimiento.logeoUsuarioAdmin(correo, clave);
		if(beanUsuarioAdmin!=null){
			HttpSession session= this.getThreadLocalRequest().getSession(true);
			beanUsuarioAdmin.setIdSession(session.getId());			
			session.setAttribute("idsession", session.getId());					
			return beanUsuarioAdmin;
		}else{
			throw new UnknownException("Error al crear sesion");
		}		 
	}
	
	/** INICIO MANTENIMIENTO CUENTA ADMINISTRADOR */
	@Override
	public UsuarioAdmin insertUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) throws UnknownException {
		UsuarioAdmin bean=GestionMantenimiento.insertarUsuarioAdmin(beanUsuarioAdmin);
		bean.getBeanCuentaAdmin().setFechaCreacion(new java.util.Date(bean.getBeanCuentaAdmin().getFechaCreacion().getTime()));
	  return bean; 
	}

	@Override
	public UsuarioAdmin updateUsuarioAdmin(UsuarioAdmin beanUsuarioAdmin) throws UnknownException {
		UsuarioAdmin bean=GestionMantenimiento.actualizarUsuarioAdmin(beanUsuarioAdmin);
		bean.getBeanCuentaAdmin().setFechaCreacion(new java.util.Date(bean.getBeanCuentaAdmin().getFechaCreacion().getTime()));
	  return bean;
	}

	@Override
	public Boolean removeUsuarioAdmin(String correoUsuarioAdmin) throws UnknownException {		
	return GestionMantenimiento.eliminarUsuarioAdmin(correoUsuarioAdmin);	    
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioAdmin> listUsuarioAdmin()
	  throws UnknownException {
	// TODO Auto-generated method stub
		List<UsuarioAdmin> listUsuarioAdmin= (List<UsuarioAdmin>) GestionMantenimiento.getList(UsuarioAdmin.class,null);
	return listUsuarioAdmin;
	}

	/** FIN MANTENIMIENTO CUENTA ADMINISTRADOR*/
	
	/** INICIO MANTENIMIENTO CASA TARJETA */
	@Override
	public CasaTarjeta insertCasaTarjeta(CasaTarjeta casaTarjeta) throws UnknownException {		
			return (CasaTarjeta) GestionMantenimiento
					.insertarObjeto(casaTarjeta);
	}
	
	@Override
	public CasaTarjeta updateCasaTarjeta(CasaTarjeta casaTarjeta) throws UnknownException {		
			return (CasaTarjeta) GestionMantenimiento
					.actualizarObjeto(CasaTarjeta.class,casaTarjeta,casaTarjeta.getIdCasaTarjeta());
	}
	
	@Override
	public Boolean removeCasaTarjeta(String idCasaTarjeta) throws UnknownException {		
		return GestionMantenimiento
				.eliminarObjeto(CasaTarjeta.class, idCasaTarjeta);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CasaTarjeta> listCasaTarjeta() throws UnknownException {
		// TODO Auto-generated method stub
		return (List<CasaTarjeta>) GestionMantenimiento.getList(CasaTarjeta.class,null);
	}
	
	/** FIN MANTENIMIENTO CASA TARJETA */
	
	
	/** INICIO MANTENIMIENTO CATEGORIA DESTINO*/
	@Override
	public CategoriaDestino insertCategoriaDestino(CategoriaDestino categoriaDestino) throws UnknownException {		
			return (CategoriaDestino) GestionMantenimiento
					.insertarObjeto(categoriaDestino);
	}
	
	@Override
	public CategoriaDestino updateCategoriaDestino(CategoriaDestino categoriaDestino) throws UnknownException {		
			return (CategoriaDestino) GestionMantenimiento
					.actualizarObjeto(CategoriaDestino.class,categoriaDestino,categoriaDestino.getIdCategoriaDestino());
	}
	
	@Override
	public Boolean removeCategoriaDestino(String idCategoriaDestino) throws UnknownException {		
		return GestionMantenimiento
				.eliminarObjeto(CategoriaDestino.class, idCategoriaDestino);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaDestino> listCategoriaDestino()
			throws UnknownException{
		return (List<CategoriaDestino>) GestionMantenimiento.getList(CategoriaDestino.class,null);
	}
	
	/** FIN MANTENIMIENTO CATEGORIA DESTINO*/
	

	/** INICIO MANTENIMIENTO CATEGORIA NEGOCIO*/
	@Override
	public CategoriaNegocio insertCategoriaNegocio(CategoriaNegocio categoriaNegocio) throws UnknownException {		
			return (CategoriaNegocio) GestionMantenimiento
					.insertarObjeto(categoriaNegocio);
	}
	
	@Override
	public CategoriaNegocio updateCategoriaNegocio(CategoriaNegocio categoriaNegocio) throws UnknownException {		
			return (CategoriaNegocio) GestionMantenimiento
					.actualizarObjeto(CategoriaNegocio.class,categoriaNegocio,categoriaNegocio.getIdCategoriaNegocio());
	}
	
	@Override
	public Boolean removeCategoriaNegocio(String idCategoriaNegocio) throws UnknownException {		
		return GestionMantenimiento
				.eliminarObjeto(CategoriaNegocio.class, idCategoriaNegocio);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaNegocio> listCategoriaNegocio()
			throws UnknownException {
		// TODO Auto-generated method stub
		return (List<CategoriaNegocio>) GestionMantenimiento.getList(CategoriaNegocio.class,null);
	}
	/** FIN MANTENIMIENTO CATEGORIA NEGOCIO*/
	
	
	/** INICIO MANTENIMIENTO ENTIDAD FINANCIERA*/
	@Override
	public EntidadFinanciera insertEntidadFinanciera(EntidadFinanciera entidadFinanciera) throws UnknownException {		
			return (EntidadFinanciera) GestionMantenimiento
					.insertarObjeto(entidadFinanciera);
	}
	
	@Override
	public EntidadFinanciera updateEntidadFinanciera(EntidadFinanciera entidadFinanciera) throws UnknownException {		
			return (EntidadFinanciera) GestionMantenimiento
					.actualizarObjeto(EntidadFinanciera.class,entidadFinanciera,entidadFinanciera.getIdEntidadFinanciera());
	}
	
	@Override
	public Boolean removeEntidadFinanciera(String idEntidadFinanciera) throws UnknownException {		
		return GestionMantenimiento
				.eliminarObjeto(EntidadFinanciera.class, idEntidadFinanciera);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadFinanciera> listEntidadFinanciera()
			throws UnknownException {
		// TODO Auto-generated method stub
		return (List<EntidadFinanciera>) GestionMantenimiento.getList(EntidadFinanciera.class,null);
	}
	
	/** FIN MANTENIMIENTO ENTIDAD FINANCIERA*/
	
	
	@	/** INICIO MANTENIMIENTO ESTADO AMISTAD*/
	Override
	public EstadoAmistad insertEstadoAmistad(EstadoAmistad estadoAmistad) throws UnknownException {		
			return (EstadoAmistad) GestionMantenimiento
					.insertarObjeto(estadoAmistad);
	}
	
	@Override
	public EstadoAmistad updateEstadoAmistad(EstadoAmistad estadoAmistad) throws UnknownException {		
			return (EstadoAmistad) GestionMantenimiento
					.actualizarObjeto(EstadoAmistad.class,estadoAmistad,estadoAmistad.getIdEstadoAmistad());
	}
	
	@Override
	public Boolean removeEstadoAmistad(String idEstadoAmistad) throws UnknownException {		
		return GestionMantenimiento
				.eliminarObjeto(EstadoAmistad.class, idEstadoAmistad);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoAmistad> listEstadoAmistad()
			throws UnknownException {
		// TODO Auto-generated method stub
		return (List<EstadoAmistad>) GestionMantenimiento.getList(EstadoAmistad.class,null);
	}
	
	/** FIN MANTENIMIENTO ESTADO AMISTAD*/
	
	
		/** INICIO MANTENIMIENTO ESTADO CUENTA*/
	@Override
	public EstadoCuenta insertEstadoCuenta(EstadoCuenta estadoCuenta) throws UnknownException {		
			return (EstadoCuenta) GestionMantenimiento
					.insertarObjeto(estadoCuenta);
	}
	
	@Override
	public EstadoCuenta updateEstadoCuenta(EstadoCuenta estadoCuenta) throws UnknownException {		
			return (EstadoCuenta) GestionMantenimiento
					.actualizarObjeto(EstadoCuenta.class,estadoCuenta,estadoCuenta.getIdEstadoCuenta());
	}
	
	@Override
	public Boolean removeEstadoCuenta(String idEstadoCuenta) throws UnknownException {		
		return GestionMantenimiento
				.eliminarObjeto(EstadoCuenta.class, idEstadoCuenta);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoCuenta> listEstadoCuenta()
			throws UnknownException {
		// TODO Auto-generated method stub
		return (List<EstadoCuenta>) GestionMantenimiento.getList(EstadoCuenta.class,null);
	}
	
	/** FIN MANTENIMIENTO ESTADO CUENTA*/
	
	/** INICIO MANTENIMIENTO ESTADO MIEMBRO*/
	@Override
	public EstadoMiembro insertEstadoMiembro(EstadoMiembro estadoMiembro) throws UnknownException {		
	  return (EstadoMiembro) GestionMantenimiento
	      .insertarObjeto(estadoMiembro);
	}

	@Override
	public EstadoMiembro updateEstadoMiembro(EstadoMiembro estadoMiembro) throws UnknownException {		
	  return (EstadoMiembro) GestionMantenimiento
	      .actualizarObjeto(EstadoMiembro.class,estadoMiembro,estadoMiembro.getIdEstadoMiembro());
	}

	@Override
	public Boolean removeEstadoMiembro(String idEstadoMiembro) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(EstadoMiembro.class, idEstadoMiembro);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoMiembro> listEstadoMiembro()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<EstadoMiembro>) GestionMantenimiento.getList(EstadoMiembro.class,null);
	}

	/** FIN MANTENIMIENTO ESTADO MIEMBRO*/
	
	/** INICIO MANTENIMIENTO ESTADO SOLICITUD AMISTAD*/
	@Override
	public EstadoSolicAmistad insertEstadoSolicAmistad(EstadoSolicAmistad estadoSolicAmistad) throws UnknownException {		
	  return (EstadoSolicAmistad) GestionMantenimiento
	      .insertarObjeto(estadoSolicAmistad);
	}

	@Override
	public EstadoSolicAmistad updateEstadoSolicAmistad(EstadoSolicAmistad estadoSolicAmistad) throws UnknownException {		
	  return (EstadoSolicAmistad) GestionMantenimiento
	      .actualizarObjeto(EstadoSolicAmistad.class,estadoSolicAmistad,estadoSolicAmistad.getIdEstadoSolicAmistad());
	}

	@Override
	public Boolean removeEstadoSolicAmistad(String idEstadoSolicAmistad) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(EstadoSolicAmistad.class, idEstadoSolicAmistad);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoSolicAmistad> listEstadoSolicAmistad()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<EstadoSolicAmistad>) GestionMantenimiento.getList(EstadoSolicAmistad.class,null);
	}

	/** FIN MANTENIMIENTO ESTADO SOLICITUD AMISTAD*/
	
	/** INICIO MANTENIMIENTO FORMA PAGO*/
	@Override
	public FormaPago insertFormaPago(FormaPago formaPago) throws UnknownException {		
	  return (FormaPago) GestionMantenimiento
	      .insertarObjeto(formaPago);
	}

	@Override
	public FormaPago updateFormaPago(FormaPago formaPago) throws UnknownException {		
	  return (FormaPago) GestionMantenimiento
	      .actualizarObjeto(FormaPago.class,formaPago,formaPago.getIdFormaPago());
	}

	@Override
	public Boolean removeFormaPago(String idFormaPago) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(FormaPago.class, idFormaPago);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPago> listFormaPago()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<FormaPago>) GestionMantenimiento.getList(FormaPago.class,null);
	}

	/** FIN MANTENIMIENTO FORMA PAGO*/
	
	/** INICIO MANTENIMIENTO IDIOMA */
	@Override
	public Idioma insertIdioma(Idioma idioma) throws UnknownException {		
	  return (Idioma) GestionMantenimiento
	      .insertarObjeto(idioma);
	}

	@Override
	public Idioma updateIdioma(Idioma idioma) throws UnknownException {		
	  return (Idioma) GestionMantenimiento
	      .actualizarObjeto(Idioma.class,idioma,idioma.getIdIdioma());
	}

	@Override
	public Boolean removeIdioma(String idIdioma) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Idioma.class, idIdioma);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Idioma> listIdioma()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Idioma>) GestionMantenimiento.getList(Idioma.class,null);
	}

	/** FIN MANTENIMIENTO IDIOMA*/
	
	/** INICIO MANTENIMIENTO LOCALIDAD */
	@Override
	public Localidad insertLocalidad(Localidad localidad) throws UnknownException {		
	  return (Localidad) GestionMantenimiento
	      .insertarObjeto(localidad);
	}

	@Override
	public Localidad updateLocalidad(Localidad localidad) throws UnknownException {		
	  return (Localidad) GestionMantenimiento
	      .actualizarObjeto(Localidad.class,localidad,localidad.getIdLocalidad());
	}

	@Override
	public Boolean removeLocalidad(String idLocalidad) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Localidad.class, idLocalidad);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Localidad> listLocalidad()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Localidad>) GestionMantenimiento.getList(Localidad.class,null);
	}

	/** FIN MANTENIMIENTO LOCALIDAD*/
	
	/** INICIO MANTENIMIENTO MONEDA */
	@Override
	public Moneda insertMoneda(Moneda moneda) throws UnknownException {		
	  return (Moneda) GestionMantenimiento
	      .insertarObjeto(moneda);
	}

	@Override
	public Moneda updateMoneda(Moneda moneda) throws UnknownException {		
	  return (Moneda) GestionMantenimiento
	      .actualizarObjeto(Moneda.class,moneda,moneda.getIdMoneda());
	}

	@Override
	public Boolean removeMoneda(String idMoneda) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Moneda.class, idMoneda);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Moneda> listMoneda(ListFilterBean filter)
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Moneda>) GestionMantenimiento.getList(Moneda.class,filter);
	}

	/** FIN MANTENIMIENTO MONEDA*/
	
	/** INICIO MANTENIMIENTO PAIS */
	@Override
	public Pais insertPais(Pais pais) throws UnknownException {		
	  return (Pais) GestionMantenimiento
	      .insertarObjeto(pais);
	}

	@Override
	public Pais updatePais(Pais pais) throws UnknownException {		
	  return (Pais) GestionMantenimiento
	      .actualizarObjeto(Pais.class,pais,pais.getIdPais());
	}

	@Override
	public Boolean removePais(String idPais) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Pais.class, idPais);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pais> listPais()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Pais>) GestionMantenimiento.getList(Pais.class,null);
	}

	/** FIN MANTENIMIENTO PAIS*/
	 
	/** INICIO MANTENIMIENTO PAIS MONEDA */
	@Override
	public PaisMoneda insertPaisMoneda(PaisMoneda paisMoneda) throws UnknownException {		
	  return (PaisMoneda) GestionMantenimiento
	      .insertarObjeto(paisMoneda);
	}

	@Override
	public PaisMoneda updatePaisMoneda(PaisMoneda paisMoneda) throws UnknownException {		
	  return (PaisMoneda) GestionMantenimiento
	      .actualizarObjeto(PaisMoneda.class,paisMoneda,paisMoneda.getIdPaisMoneda());
	}

	@Override
	public Boolean removePaisMoneda(String idPaisMoneda) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(PaisMoneda.class, idPaisMoneda);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PaisMoneda> listPaisMoneda()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<PaisMoneda>) GestionMantenimiento.getList(PaisMoneda.class,null);
	}

	/** FIN MANTENIMIENTO PAIS MONEDA*/
	
	/** INICIO MANTENIMIENTO PARAMETROS DEL SISTEMA */
	@Override
	public ParametrosSistema insertParametrosSistema(ParametrosSistema parametrosSistema) throws UnknownException {		
	  return (ParametrosSistema) GestionMantenimiento
	      .insertarObjeto(parametrosSistema);
	}

	@Override
	public ParametrosSistema updateParametrosSistema(ParametrosSistema parametrosSistema) throws UnknownException {		
	  return (ParametrosSistema) GestionMantenimiento
	      .actualizarObjeto(ParametrosSistema.class,parametrosSistema,parametrosSistema.getIdParametrosSistema());
	}

	@Override
	public Boolean removeParametrosSistema(String idParametrosSistema) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(ParametrosSistema.class, idParametrosSistema);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ParametrosSistema> listParametrosSistema()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<ParametrosSistema>) GestionMantenimiento.getList(ParametrosSistema.class,null);
	}

	/** FIN MANTENIMIENTO PARAMETROS DEL SISTEMA*/
	
	/** INICIO MANTENIMIENTO PRIVACIDAD */
	@Override
	public Privacidad insertPrivacidad(Privacidad privacidad) throws UnknownException {		
	  return (Privacidad) GestionMantenimiento
	      .insertarObjeto(privacidad);
	}

	@Override
	public Privacidad updatePrivacidad(Privacidad privacidad) throws UnknownException {		
	  return (Privacidad) GestionMantenimiento
	      .actualizarObjeto(Privacidad.class,privacidad,privacidad.getIdPrivacidad());
	}

	@Override
	public Boolean removePrivacidad(String idPrivacidad) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Privacidad.class, idPrivacidad);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Privacidad> listPrivacidad()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Privacidad>) GestionMantenimiento.getList(Privacidad.class,null);
	}

	/** FIN MANTENIMIENTO PRIVACIDAD*/
	
	/** INICIO MANTENIMIENTO RED SOCIAL */
	@Override
	public RedSocial insertRedSocial(RedSocial redSocial) throws UnknownException {		
	  return (RedSocial) GestionMantenimiento
	      .insertarObjeto(redSocial);
	}

	@Override
	public RedSocial updateRedSocial(RedSocial redSocial) throws UnknownException {		
	  return (RedSocial) GestionMantenimiento
	      .actualizarObjeto(RedSocial.class,redSocial,redSocial.getIdRedSocial());
	}

	@Override
	public Boolean removeRedSocial(String idRedSocial) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(RedSocial.class, idRedSocial);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RedSocial> listRedSocial()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<RedSocial>) GestionMantenimiento.getList(RedSocial.class,null);
	}

	/** FIN MANTENIMIENTO RED SOCIAL*/
	
	/** INICIO MANTENIMIENTO REGION */
	@Override
	public Region insertRegion(Region region) throws UnknownException {		
	  return (Region) GestionMantenimiento
	      .insertarObjeto(region);
	}

	@Override
	public Region updateRegion(Region region) throws UnknownException {		
	  return (Region) GestionMantenimiento
	      .actualizarObjeto(Region.class,region,region.getIdRegion());
	}

	@Override
	public Boolean removeRegion(String idRegion) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Region.class, idRegion);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> listRegion(ListFilterBean filter)
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Region>) GestionMantenimiento.getList(Region.class,filter);
	}

	/** FIN MANTENIMIENTO REGION*/
	
	/** INICIO MANTENIMIENTO TARIFARIO */
	@Override
	public Tarifario insertTarifario(Tarifario tarifario) throws UnknownException {		
	  Tarifario bean=(Tarifario) GestionMantenimiento
	      .insertarObjeto(tarifario);
	  bean.setFechaInicial(new java.util.Date(bean.getFechaInicial().getTime()));
	  bean.setFechaFinal(null);
	  return bean;
	}

	@Override
	public Tarifario updateTarifario(Tarifario tarifario) throws UnknownException {		
		Tarifario bean=(Tarifario) GestionMantenimiento
				.actualizarObjeto(Tarifario.class,tarifario,tarifario.getIdTarifario());
			  bean.setFechaInicial(new java.util.Date(bean.getFechaInicial().getTime()));
			  bean.setFechaFinal(bean.getFechaFinal()!=null?new java.util.Date(bean.getFechaFinal().getTime()):null);
	  return bean;
	}

	@Override
	public Boolean removeTarifario(String idTarifario) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(Tarifario.class, idTarifario);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tarifario> listTarifario()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<Tarifario>) GestionMantenimiento.getList(Tarifario.class,null);
	}

	/** FIN MANTENIMIENTO TARIFARIO*/
	
	/** INICIO MANTENIMIENTO TIPO DE CAMBIO */
	@Override
	public TipoCambio insertTipoCambio(TipoCambio tipoCambio) throws UnknownException {
		TipoCambio bean=(TipoCambio) GestionMantenimiento
	      .insertarObjeto(tipoCambio);
		bean.setFecha(new java.util.Date(bean.getFecha().getTime()));
	  return bean;
	}

	@Override
	public TipoCambio updateTipoCambio(TipoCambio tipoCambio) throws UnknownException {		
	  return (TipoCambio) GestionMantenimiento
	      .actualizarObjeto(TipoCambio.class,tipoCambio,tipoCambio.getIdTipoCambio());
	}

	@Override
	public Boolean removeTipoCambio(String idTipoCambio) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(TipoCambio.class, idTipoCambio);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoCambio> listTipoCambio()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<TipoCambio>) GestionMantenimiento.getList(TipoCambio.class,null);
	}

	/** FIN MANTENIMIENTO TIPO DE CAMBIO*/
	
	/** INICIO MANTENIMIENTO TIPO EMPATIA */
	@Override
	public TipoEmpatia insertTipoEmpatia(TipoEmpatia tipoEmpatia) throws UnknownException {		
	  return (TipoEmpatia) GestionMantenimiento
	      .insertarObjeto(tipoEmpatia);
	}

	@Override
	public TipoEmpatia updateTipoEmpatia(TipoEmpatia tipoEmpatia) throws UnknownException {		
	  return (TipoEmpatia) GestionMantenimiento
	      .actualizarObjeto(TipoEmpatia.class,tipoEmpatia,tipoEmpatia.getIdTipoEmpatia());
	}

	@Override
	public Boolean removeTipoEmpatia(String idTipoEmpatia) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(TipoEmpatia.class, idTipoEmpatia);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoEmpatia> listTipoEmpatia()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<TipoEmpatia>) GestionMantenimiento.getList(TipoEmpatia.class,null);
	}

	/** FIN MANTENIMIENTO TIPO EMPATIA*/
	
	/** INICIO MANTENIMIENTO TIPO INTERACCION PUBLICIDAD */
	@Override
	public TipoInterPublicidad insertTipoInterPublicidad(TipoInterPublicidad tipoInterPublicidad) throws UnknownException {		
	  return (TipoInterPublicidad) GestionMantenimiento
	      .insertarObjeto(tipoInterPublicidad);
	}

	@Override
	public TipoInterPublicidad updateTipoInterPublicidad(TipoInterPublicidad tipoInterPublicidad) throws UnknownException {		
	  return (TipoInterPublicidad) GestionMantenimiento
	      .actualizarObjeto(TipoInterPublicidad.class,tipoInterPublicidad,tipoInterPublicidad.getIdTipoInterPublicidad());
	}

	@Override
	public Boolean removeTipoInterPublicidad(String idTipoInterPublicidad) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(TipoInterPublicidad.class, idTipoInterPublicidad);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoInterPublicidad> listTipoInterPublicidad()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<TipoInterPublicidad>) GestionMantenimiento.getList(TipoInterPublicidad.class,null);
	}

	/** FIN MANTENIMIENTO TIPO INTERACCION PUBLICIDAD*/
	
	/** INICIO MANTENIMIENTO TIPO MOVIMIENTO */
	@Override
	public TipoMovimiento insertTipoMovimiento(TipoMovimiento tipoMovimiento) throws UnknownException {		
	  return (TipoMovimiento) GestionMantenimiento
	      .insertarObjeto(tipoMovimiento);
	}

	@Override
	public TipoMovimiento updateTipoMovimiento(TipoMovimiento tipoMovimiento) throws UnknownException {		
	  return (TipoMovimiento) GestionMantenimiento
	      .actualizarObjeto(TipoMovimiento.class,tipoMovimiento,tipoMovimiento.getIdTipoMovimiento());
	}

	@Override
	public Boolean removeTipoMovimiento(String idTipoMovimiento) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(TipoMovimiento.class, idTipoMovimiento);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoMovimiento> listTipoMovimiento()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<TipoMovimiento>) GestionMantenimiento.getList(TipoMovimiento.class,null);
	}

	/** FIN MANTENIMIENTO TIPO MOVIMIENTO*/
	
	/** INICIO MANTENIMIENTO TIPO NOTIFICACION */
	@Override
	public TipoNotificacion insertTipoNotificacion(TipoNotificacion tipoNotificacion) throws UnknownException {		
	  return (TipoNotificacion) GestionMantenimiento
	      .insertarObjeto(tipoNotificacion);
	}

	@Override
	public TipoNotificacion updateTipoNotificacion(TipoNotificacion tipoNotificacion) throws UnknownException {		
	  return (TipoNotificacion) GestionMantenimiento
	      .actualizarObjeto(TipoNotificacion.class,tipoNotificacion,tipoNotificacion.getIdTipoNotificacion());
	}

	@Override
	public Boolean removeTipoNotificacion(String idTipoNotificacion) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(TipoNotificacion.class, idTipoNotificacion);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoNotificacion> listTipoNotificacion()
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<TipoNotificacion>) GestionMantenimiento.getList(TipoNotificacion.class,null);
	}

	/** FIN MANTENIMIENTO TIPO NOTIFICACION*/
	
	/** INICIO MANTENIMIENTO TIPO SUSCRIPCION */
	@Override
	public TipoSuscripcion insertTipoSuscripcion(TipoSuscripcion tipoSuscripcion) throws UnknownException {		
	  return (TipoSuscripcion) GestionMantenimiento
	      .insertarObjeto(tipoSuscripcion);
	}

	@Override
	public TipoSuscripcion updateTipoSuscripcion(TipoSuscripcion tipoSuscripcion) throws UnknownException {		
	  return (TipoSuscripcion) GestionMantenimiento
	      .actualizarObjeto(TipoSuscripcion.class,tipoSuscripcion,tipoSuscripcion.getIdTipoSuscripcion());
	}

	@Override
	public Boolean removeTipoSuscripcion(String idTipoSuscripcion) throws UnknownException {		
	return GestionMantenimiento
	    .eliminarObjeto(TipoSuscripcion.class, idTipoSuscripcion);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoSuscripcion> listTipoSuscripcion(ListFilterBean filter)
	  throws UnknownException {
	// TODO Auto-generated method stub
	return (List<TipoSuscripcion>) GestionMantenimiento.getList(TipoSuscripcion.class,filter);
	}

	/** FIN MANTENIMIENTO TIPO SUSCRIPCION*/
}
