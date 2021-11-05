package com.indiana.view.admin.uimenubaradmin;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Pais;
import com.indiana.view.admin.uicasatarjeta.UiCasaTarjetaImpl;
import com.indiana.view.admin.uicategdestino.UiCategoriaDestinoImpl;
import com.indiana.view.admin.uicategnegocio.UiCategoriaNegocioImpl;
import com.indiana.view.admin.uientidadfinanciera.UiEntidadFinancieraImpl;
import com.indiana.view.admin.uiestadoamistad.UiEstadoAmistadImpl;
import com.indiana.view.admin.uiestadocuenta.UiEstadoCuentaImpl;
import com.indiana.view.admin.uiestadomiembro.UiEstadoMiembroImpl;
import com.indiana.view.admin.uiestadosolamistad.UiEstadoSolAmistadImpl;
import com.indiana.view.admin.uiestadturista.UiEstadTurista;
import com.indiana.view.admin.uiestadturista.UiEstadTuristaImpl;
import com.indiana.view.admin.uiformapago.UiFormaPagoImpl;
import com.indiana.view.admin.uiidioma.UiIdiomaImpl;
import com.indiana.view.admin.uilocalidad.UiLocalidadImpl;
import com.indiana.view.admin.uimoneda.UiMonedaImpl;
import com.indiana.view.admin.uipais.UiPaisImpl;
import com.indiana.view.admin.uipaismoneda.UiPaisMonedaImpl;
import com.indiana.view.admin.uiparametros.UiParametrosImpl;
import com.indiana.view.admin.uiprivacidad.UiPrivacidadImpl;
import com.indiana.view.admin.uiredsocial.UiRedSocialImpl;
import com.indiana.view.admin.uiregion.UiRegionImpl;
import com.indiana.view.admin.uisesionadmin.UiSesionAdmin;
import com.indiana.view.admin.uitarifario.UiTarifarioImpl;
import com.indiana.view.admin.uitipocambio.UiTipoCambioImpl;
import com.indiana.view.admin.uitipoempatia.UiTipoEmpatiaImpl;
import com.indiana.view.admin.uitipointerpubl.UiTipoInterPublImpl;
import com.indiana.view.admin.uitipomovimiento.UiTipoMovimientoImpl;
import com.indiana.view.admin.uitiponotificacion.UiTipoNotificacionImpl;
import com.indiana.view.admin.uitiposuscripcion.UiTipoSuscripcionImpl;
import com.indiana.view.admin.uiusuarioadmin.UiUsuarioAdminImpl;
import com.indiana.view.uiutil.TabClose;

public class UiMenuBar extends Composite{
	public static ArrayList<TabClose> tabs = new ArrayList<TabClose>();
	public static TabClose tabSelected;	
	private MenuBar menuRoot;
	private MenuBar mbMantenimiento;
	private MenuBar mbUsuario;
	private MenuBar mbNegocio;
	private MenuBar mbDestino;
	private MenuBar mbSistema;
	private MenuBar mbMonetario;
	private MenuBar mbAdmin;
	private MenuBar mbConfiguracion;
	private MenuBar mbEstadisticas;
	
	private MenuItem miEstadoAmistad;
	private MenuItem miEstadoCuenta;
	private MenuItem miEstadoMiembro;
	private MenuItem miEstadoSolicitudAmistad;
	private MenuItem miPrivacidad;
	private MenuItem miCategoriaDestino;
	private MenuItem miCategoriaNegocio;
	private MenuItem miMoneda;
	private MenuItem miPaisMoneda;
	private MenuItem miTipoCambio;
	private MenuItem miEntidadFinanciera;
	private MenuItem miCasaTarjeta;
	private MenuItem miFormapago;
	private MenuItem miTarifario;
	private MenuItem miUsuarioAdmin;
	private MenuItem miIdioma;
	private MenuItem miPais;
	private MenuItem miRegion;
	private MenuItem miLocalidad;
	private MenuItem miRedSocial;
	private MenuItem miTipoEmpatia;
	private MenuItem miTipoInteraccionPublicidad;
	private MenuItem miTipoMovimiento;
	private MenuItem miTipoNotificacion;
	private MenuItem miTipoSuscripcion;
	private MenuItem miParametrosSistema;
	private MenuItem miEstadTurista;
	private MenuItem miNegocio;
	private MenuItem miDestino;
	private MenuItem miColonia;
	private MenuItem miMiembro;
	
	
	
	
	public UiMenuBar(){
		initComponents();
		loadPaises();
		initStyle();
	}
	
	private void initComponents(){
		menuRoot=new MenuBar();
        menuRoot.setAnimationEnabled(true);
        menuRoot.setAutoOpen(true);
        initMenuMain();
        initMenuMantenimientos();
        initMenuUsuario();
        initMenuNegocio();
        initMenuDestino();
        initMenuSistema();
        initiMenuMonetario();
        initiMenuAdministracion();
        initiMenuConfiguracion();
        initiMenuEstadistica();
        initWidget(menuRoot);
	}
	
	private void initMenuMain(){
		mbMantenimiento=new MenuBar(true);
		mbEstadisticas=new MenuBar(true);
		menuRoot.addItem("Mantenimientos", mbMantenimiento);
		menuRoot.addSeparator();
		menuRoot.addItem("Estadisticas", mbEstadisticas);		
	}
	
	private void initMenuMantenimientos(){
		mbUsuario=new MenuBar(true);
		mbNegocio=new MenuBar(true);
		mbDestino=new MenuBar(true);
		mbSistema=new MenuBar(true);
		mbMantenimiento.addItem("Usuario",mbUsuario);
		mbMantenimiento.addItem("Negocio",mbNegocio);
		mbMantenimiento.addItem("Destino",mbDestino);
		mbMantenimiento.addItem("Sistema",mbSistema);
	}
	
	private void initMenuUsuario(){
		miEstadoAmistad=new MenuItem("Estado Amistad",showUiEstadoAmistad);
		miEstadoCuenta=new MenuItem("Estado Cuenta",showUiEstadoCuenta);
		miEstadoMiembro=new MenuItem("Estado Miembro",showUiEstadoMiembro);
		miEstadoSolicitudAmistad=new MenuItem("Estado Solicitud Amistad",showUiEstadoSolicitudAmistad);
		miPrivacidad=new MenuItem("Privacidad",showUiPrivacidad);
		mbUsuario.addItem(miEstadoAmistad);
		mbUsuario.addItem(miEstadoCuenta);
		mbUsuario.addItem(miEstadoMiembro);
		mbUsuario.addItem(miEstadoSolicitudAmistad);
		mbUsuario.addItem(miPrivacidad);
	}
	
	private void initMenuNegocio(){
		miCategoriaNegocio=new MenuItem("Categoria Negocio",showUiCategoriaNegocio);
		mbNegocio.addItem(miCategoriaNegocio);
	}
	
	private void initMenuDestino(){
		miCategoriaDestino=new MenuItem("Categoria Destino",showUiCategoriaDestino);
		mbDestino.addItem(miCategoriaDestino);
	}
	
	private void initMenuSistema(){
		mbMonetario=new MenuBar(true);
		mbAdmin=new MenuBar(true);
		mbConfiguracion=new MenuBar(true);
		miParametrosSistema=new MenuItem("Parametros Sistema",showUiParametrosSistema);
		mbSistema.addItem("Monetario",mbMonetario);
		mbSistema.addItem("Administracion",mbAdmin);
		mbSistema.addItem("Configuracion",mbConfiguracion);
		mbSistema.addItem(miParametrosSistema);
	}
	
	private void initiMenuMonetario(){
		miMoneda=new MenuItem("Moneda",showUiMoneda);
		miPaisMoneda=new MenuItem("Pais - Moneda",showUiPaisMoneda);
		miTipoCambio=new MenuItem("Tipo Cambio",showUiTipoCambio);
		miEntidadFinanciera=new MenuItem("Entidad Financiera",showUiEntidadFinanciera);
		miCasaTarjeta=new MenuItem("Casa Tarjeta",showUiCasaTarjeta);
		miFormapago=new MenuItem("Forma de Pago",showUiFormaPago);
		miTarifario=new MenuItem("Tarifario", showUiTarifario);
		mbMonetario.addItem(miMoneda);
		mbMonetario.addItem(miPaisMoneda);
		mbMonetario.addItem(miTipoCambio);
		mbMonetario.addItem(miEntidadFinanciera);
		mbMonetario.addItem(miCasaTarjeta);
		mbMonetario.addItem(miFormapago);
		mbMonetario.addItem(miTarifario);
	}
	
	private void initiMenuAdministracion(){
		miUsuarioAdmin= new MenuItem("Usuario Administrador",showUiUsuarioAdmin);
		mbAdmin.addItem(miUsuarioAdmin);
	}
	
	private void initiMenuConfiguracion(){
		miIdioma=new MenuItem("Idioma",showUiIdioma);
		miPais=new MenuItem("Pais",showUiPais);
		miRegion=new MenuItem ("Region",showUiRegion);
		miLocalidad=new MenuItem("Localidad",showUiLocalidad);
		miRedSocial=new MenuItem("Red Social",showUiRedSocial);
		miTipoEmpatia=new MenuItem("Tipo Empatia",showUiTipoEmpatia);
		miTipoInteraccionPublicidad=new MenuItem("Tipo Interaccion Publicidad",showUiTipoInteraccionPublicidad);
		miTipoMovimiento=new MenuItem("Tipo Movimiento",showUiTipoMovimiento);
		miTipoNotificacion=new MenuItem("Tipo Notificacion",showUiTiponotificacion);
		miTipoSuscripcion=new MenuItem("Tipo Suscripcion",showUiTipoSuscripcion);
		mbConfiguracion.addItem(miIdioma);
		mbConfiguracion.addItem(miPais);
		mbConfiguracion.addItem(miRegion);
		mbConfiguracion.addItem(miLocalidad);
		mbConfiguracion.addItem(miRedSocial);
		mbConfiguracion.addItem(miTipoEmpatia);
		mbConfiguracion.addItem(miTipoInteraccionPublicidad);
		mbConfiguracion.addItem(miTipoMovimiento);
		mbConfiguracion.addItem(miTipoNotificacion);
		mbConfiguracion.addItem(miTipoSuscripcion);
		
	}
	private void initiMenuEstadistica(){		
		miEstadTurista= new MenuItem("Estadistica Turista New", showUiEstadTurista);
		miNegocio=new MenuItem("Estadistica Negocio",showUiEstadisticaNegocio);
		miDestino=new MenuItem("Estadistica Destino",showUiEstadisticaDestino);
		miColonia=new MenuItem("Estadistica Colonia",showUiEstadisticaColonia);
		miMiembro=new MenuItem("Estadistica Miembro",showUiEstadisticaMiembro);
		mbEstadisticas.addItem(miEstadTurista);
		mbEstadisticas.addItem(miNegocio);
		mbEstadisticas.addItem(miDestino);
		mbEstadisticas.addItem(miColonia);
		mbEstadisticas.addItem(miMiembro);
	}
	
	Command showUiEstadoAmistad= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiEstadoAmistad == null) {                
				UiSesionAdmin.uiEstadoAmistad = new UiEstadoAmistadImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoAmistad, "Estado Amistad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoAmistad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoAmistad);
                tabSelected=tab;  
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoAmistad, "Estado Amistad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoAmistad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoAmistad);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiEstadoCuenta= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiEstadoCuenta == null) {                
				UiSesionAdmin.uiEstadoCuenta = new UiEstadoCuentaImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoCuenta, "Estado Cuenta");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoCuenta, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoCuenta);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoCuenta, "Estado Cuenta");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoCuenta, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoCuenta);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiEstadoMiembro= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiEstadoMiembro == null) {                
				UiSesionAdmin.uiEstadoMiembro = new UiEstadoMiembroImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoMiembro, "Estado Miembro");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoMiembro, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoMiembro);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoMiembro, "Estado Miembro");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoMiembro, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoMiembro);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiEstadoSolicitudAmistad= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiEstadoSolAmistad == null) {                
				UiSesionAdmin.uiEstadoSolAmistad = new UiEstadoSolAmistadImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoSolAmistad, "Estado Sol. Amistad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoSolAmistad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoSolAmistad);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiEstadoSolAmistad, "Estado .Sol. Amistad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadoSolAmistad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadoSolAmistad);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiPrivacidad= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiPrivacidad == null) {                
				UiSesionAdmin.uiPrivacidad = new UiPrivacidadImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiPrivacidad, "Privacidad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiPrivacidad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiPrivacidad);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiPrivacidad, "Privacidad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiPrivacidad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiPrivacidad);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiCategoriaNegocio= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiCategoriaNegocio == null) {                
				UiSesionAdmin.uiCategoriaNegocio = new UiCategoriaNegocioImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiCategoriaNegocio, "Categ. Negocio");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiCategoriaNegocio, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiCategoriaNegocio);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiCategoriaNegocio, "Categ. Negocio");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiCategoriaNegocio, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiCategoriaNegocio);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiCategoriaDestino= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiCategoriaDestino == null) {                
				UiSesionAdmin.uiCategoriaDestino = new UiCategoriaDestinoImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiCategoriaDestino, "Categ. Destino");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiCategoriaDestino, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiCategoriaDestino);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiCategoriaDestino, "Categ. Destino");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiCategoriaDestino, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiCategoriaDestino);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiParametrosSistema= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiParametrosSistema == null) {                
				UiSesionAdmin.uiParametrosSistema = new UiParametrosImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiParametrosSistema, "Parametros");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiParametrosSistema, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiParametrosSistema);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiParametrosSistema, "Parametros");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiParametrosSistema, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiParametrosSistema);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiMoneda= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiMoneda == null) {                
				UiSesionAdmin.uiMoneda = new UiMonedaImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiMoneda, "Moneda");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiMoneda, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiMoneda);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiMoneda, "Moneda");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiMoneda, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiMoneda);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
		
		
	};
	
	Command showUiPaisMoneda= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiPaisMoneda == null) {                
				UiSesionAdmin.uiPaisMoneda = new UiPaisMonedaImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiPaisMoneda, "Pais-Moneda");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiPaisMoneda, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiPaisMoneda);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiPaisMoneda, "Pais-Moneda");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiPaisMoneda, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiPaisMoneda);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
		
		
	};
	
	Command showUiTipoCambio= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTipoCambio == null) {                
				UiSesionAdmin.uiTipoCambio = new UiTipoCambioImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoCambio, "Tipo Cambio");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoCambio, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoCambio);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoCambio, "Tipo Cambio");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoCambio, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoCambio);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiEntidadFinanciera= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiEntidadFinanciera == null) {                
				UiSesionAdmin.uiEntidadFinanciera = new UiEntidadFinancieraImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiEntidadFinanciera, "Entidad Financiera");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEntidadFinanciera, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEntidadFinanciera);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiEntidadFinanciera, "Entidad Financiera");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEntidadFinanciera, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEntidadFinanciera);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiCasaTarjeta= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiCasaTarjeta == null) {                
				UiSesionAdmin.uiCasaTarjeta = new UiCasaTarjetaImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiCasaTarjeta, "Casa Tarjeta");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiCasaTarjeta, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiCasaTarjeta);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiCasaTarjeta, "Casa Tarjeta");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiCasaTarjeta, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiCasaTarjeta);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiFormaPago= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiFormaPago == null) {                
				UiSesionAdmin.uiFormaPago = new UiFormaPagoImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiFormaPago, "Forma Pago");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiFormaPago, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiFormaPago);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiFormaPago, "Forma Pago");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiFormaPago, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiFormaPago);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiTarifario= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTarifario == null) {                
				UiSesionAdmin.uiTarifario = new UiTarifarioImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTarifario, "Tarifario");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTarifario, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTarifario);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTarifario, "Tarifario");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTarifario, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTarifario);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	
	Command showUiUsuarioAdmin= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiUsuarioAdmin == null) {                
				UiSesionAdmin.uiUsuarioAdmin = new UiUsuarioAdminImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiUsuarioAdmin, "Usuario Administrador");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiUsuarioAdmin, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiUsuarioAdmin);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiUsuarioAdmin, "Usuario Administrador");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiUsuarioAdmin, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiUsuarioAdmin);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiIdioma= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiIdioma == null) {                
				UiSesionAdmin.uiIdioma = new UiIdiomaImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiIdioma, "Idioma");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiIdioma, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiIdioma);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiIdioma, "Idioma");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiIdioma, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiIdioma);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiPais= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiPais == null) {                
				UiSesionAdmin.uiPais = new UiPaisImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiPais, "Pais");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiPais, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiPais);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiPais, "Pais");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiPais, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiPais);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiRegion= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiRegion == null) {                
				UiSesionAdmin.uiRegion = new UiRegionImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiRegion, "Region");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiRegion, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiRegion);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiRegion, "Region");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiRegion, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiRegion);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiLocalidad= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiLocalidad == null) {                
				UiSesionAdmin.uiLocalidad = new UiLocalidadImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiLocalidad, "Localidad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiLocalidad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiLocalidad);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiLocalidad, "Localidad");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiLocalidad, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiLocalidad);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiRedSocial= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiRedSocial == null) {                
				UiSesionAdmin.uiRedSocial = new UiRedSocialImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiRedSocial, "Red Social");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiRedSocial, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiRedSocial);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiRedSocial, "Red Social");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiRedSocial, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiRedSocial);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiTipoEmpatia= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTipoEmpatia == null) {                
				UiSesionAdmin.uiTipoEmpatia = new UiTipoEmpatiaImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoEmpatia, "Tipo Empatia");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoEmpatia, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoEmpatia);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoEmpatia, "Tipo Empatia");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoEmpatia, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoEmpatia);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiTipoInteraccionPublicidad= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTipoInterPubl == null) {                
				UiSesionAdmin.uiTipoInterPubl = new UiTipoInterPublImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoInterPubl, "Tipo Int. Pub.");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoInterPubl, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoInterPubl);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoInterPubl, "Tipo Int. Pub.");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoInterPubl, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoInterPubl);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiTipoMovimiento= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTipoMovimiento == null) {                
				UiSesionAdmin.uiTipoMovimiento = new UiTipoMovimientoImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoMovimiento, "Tipo Movimiento");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoMovimiento, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoMovimiento);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoMovimiento, "Tipo Movimiento");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoMovimiento, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoMovimiento);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiTiponotificacion= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTipoNotificacion == null) {                
				UiSesionAdmin.uiTipoNotificacion = new UiTipoNotificacionImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoNotificacion, "Tipo Notificacion");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoNotificacion, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoNotificacion);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoNotificacion, "Tipo Notificacion");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoNotificacion, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoNotificacion);
                tabSelected=tab;
                backGroundColor();
            }
			}
		
	};
	Command showUiTipoSuscripcion= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			if (UiSesionAdmin.uiTipoSuscripcion == null) {                
				UiSesionAdmin.uiTipoSuscripcion = new UiTipoSuscripcionImpl();                
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoSuscripcion, "Tipo Suscripcion");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoSuscripcion, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoSuscripcion);
                tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiSesionAdmin.uiTipoSuscripcion, "Tipo Suscripcion");
                UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiTipoSuscripcion, tab);                
                tabs.add(tab);
                UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiTipoSuscripcion);
                tabSelected=tab;
                backGroundColor();
            }
			}		
	};
	
Command showUiEstadTurista= new Command() {

		
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if (UiSesionAdmin.uiEstadTurista == null) {                
			UiSesionAdmin.uiEstadTurista = new UiEstadTuristaImpl();            
            TabClose tab = new TabClose(UiSesionAdmin.uiEstadTurista, "Estadistica Turista");
            UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadTurista, tab);                
            tabs.add(tab);
            UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadTurista);
            tabSelected=tab;
            backGroundColor();
        }else{
            TabClose tab = new TabClose(UiSesionAdmin.uiEstadTurista, "Estadistica Turista");
            UiSesionAdmin.tabPanel.add(UiSesionAdmin.uiEstadTurista, tab);                
            tabs.add(tab);
            UiSesionAdmin.tabPanel.selectTab(UiSesionAdmin.uiEstadTurista);
            tabSelected=tab;
            backGroundColor();
        }
		}
		
	};
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);		
	
	public void loadPaises() {
		// TODO Auto-generated method stub
		SERVICE.listPais(new AsyncCallback<List<Pais>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onSuccess(List<Pais> result) {								
				UiEstadTurista.listPaises= result;				
			}
		});
	}
	Command showUiEstadisticaNegocio= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Window.alert("Hola menu");
			}
		
	};
	Command showUiEstadisticaDestino= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Window.alert("Hola menu");
			}
		
	};
	Command showUiEstadisticaColonia= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Window.alert("Hola menu");
			}
		
	};
	Command showUiEstadisticaMiembro= new Command() {

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Window.alert("Hola menu");
			}
		
	};
	private void initStyle(){
		
	}
	
	public static void backGroundColor(){
		for(int i=0;i<tabs.size();i++){
			TabClose tab=tabs.get(i);
			tab.getElement().getStyle().setBackgroundColor("#BDF9BB");
		}
		tabSelected.getElement().getStyle().setBackgroundColor("#F9E96B");
	}
	
	public static void exitTabDefault(){
		for(int i=0;i<tabs.size();){
			tabSelected=tabs.get(i);
			break;
		}
	}
	
	
}
