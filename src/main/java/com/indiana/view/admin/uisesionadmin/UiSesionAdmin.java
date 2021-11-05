package com.indiana.view.admin.uisesionadmin;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.indiana.view.admin.uicasatarjeta.UiCasaTarjeta;
import com.indiana.view.admin.uicategdestino.UiCategoriaDestino;
import com.indiana.view.admin.uicategnegocio.UiCategoriaNegocio;
import com.indiana.view.admin.uientidadfinanciera.UiEntidadFinanciera;
import com.indiana.view.admin.uiestadoamistad.UiEstadoAmistad;
import com.indiana.view.admin.uiestadocuenta.UiEstadoCuenta;
import com.indiana.view.admin.uiestadomiembro.UiEstadoMiembro;
import com.indiana.view.admin.uiestadosolamistad.UiEstadoSolAmistad;
import com.indiana.view.admin.uiestadturista.UiEstadTurista;
import com.indiana.view.admin.uiformapago.UiFormaPago;
import com.indiana.view.admin.uiidioma.UiIdioma;
import com.indiana.view.admin.uilocalidad.UiLocalidad;
import com.indiana.view.admin.uimenubaradmin.UiMenuBar;
import com.indiana.view.admin.uimoneda.UiMoneda;
import com.indiana.view.admin.uipais.UiPais;
import com.indiana.view.admin.uipaismoneda.UiPaisMoneda;
import com.indiana.view.admin.uiparametros.UiParametros;
import com.indiana.view.admin.uiprivacidad.UiPrivacidad;
import com.indiana.view.admin.uiredsocial.UiRedSocial;
import com.indiana.view.admin.uiregion.UiRegion;
import com.indiana.view.admin.uitarifario.UiTarifario;
import com.indiana.view.admin.uitipocambio.UiTipoCambio;
import com.indiana.view.admin.uitipoempatia.UiTipoEmpatia;
import com.indiana.view.admin.uitipointerpubl.UiTipoInterPubl;
import com.indiana.view.admin.uitipomovimiento.UiTipoMovimiento;
import com.indiana.view.admin.uitiponotificacion.UiTipoNotificacion;
import com.indiana.view.admin.uitiposuscripcion.UiTipoSuscripcion;
import com.indiana.view.admin.uitoolbaradmin.UiToolBarAdmin;
import com.indiana.view.admin.uiusuarioadmin.UiUsuarioAdmin;
import com.indiana.view.uiutil.JHeaderMenu;
import com.indiana.view.uiutil.UiScreenSesion;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialLink;

public class UiSesionAdmin extends UiScreenSesion implements InterUiSesionAdmin{
	private JHeaderMenu header;
	private MaterialLink linkUser;
	private MaterialLink linkSettings;
	private MaterialLink linkCloseSesion;
	private UiMenuBar menuBar;
	private UiToolBarAdmin toolBar;
	public static TabLayoutPanel tabPanel;
	public static UiEstadoAmistad uiEstadoAmistad;
	public static UiEstadoCuenta uiEstadoCuenta;
	public static UiEstadoMiembro uiEstadoMiembro;
	public static UiEstadoSolAmistad uiEstadoSolAmistad;
	public static UiPrivacidad uiPrivacidad;
	public static UiCategoriaNegocio uiCategoriaNegocio;
	public static UiCategoriaDestino uiCategoriaDestino;
	public static UiMoneda uiMoneda;
	public static UiPaisMoneda uiPaisMoneda;
	public static UiTipoCambio uiTipoCambio;
	public static UiEntidadFinanciera uiEntidadFinanciera;
	public static UiCasaTarjeta uiCasaTarjeta;
	public static UiFormaPago uiFormaPago;
	public static UiTarifario uiTarifario;
	public static UiUsuarioAdmin uiUsuarioAdmin;
	public static UiIdioma uiIdioma;
	public static UiPais uiPais;
	public static UiRegion uiRegion;
	public static UiLocalidad uiLocalidad;
	public static UiRedSocial uiRedSocial;
	public static UiTipoEmpatia uiTipoEmpatia;
	public static UiTipoInterPubl uiTipoInterPubl;
	public static UiTipoMovimiento uiTipoMovimiento;
	public static UiTipoNotificacion uiTipoNotificacion;
	public static UiTipoSuscripcion uiTipoSuscripcion;
	public static UiParametros uiParametrosSistema;
	public static UiEstadTurista uiEstadTurista;
	
	
	public UiSesionAdmin(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		header=new JHeaderMenu();
		header.setTitle("ADMINISTRACION INDIANT");
		linkUser=new MaterialLink();
		linkUser.setIconType(IconType.ACCOUNT_CIRCLE);
		linkUser.setText("jonathan@kiongo.com");
		linkUser.setWaves(WavesType.LIGHT);
		linkUser.setTooltip("Ver Perfil");
		linkSettings=new MaterialLink();
		linkSettings.setIconType(IconType.SETTINGS);
		linkSettings.setWaves(WavesType.LIGHT);
		linkSettings.setTooltip("Configuracion");
		linkCloseSesion=new MaterialLink();
		linkCloseSesion.setIconType(IconType.CLOSE);
		linkCloseSesion.setWaves(WavesType.LIGHT);
		linkCloseSesion.setTooltip("Cerrar Sesion");
		header.addWidgetToNavSection(linkUser);
		header.addWidgetToNavSection(linkSettings);
		header.addWidgetToNavSection(linkCloseSesion);
		menuBar=new UiMenuBar();
		toolBar=new UiToolBarAdmin();
        tabPanel = new TabLayoutPanel(4, Unit.EM);
        tabPanel.setAnimationDuration(1000);
        this.setComponent(UiScreenSesion.TITULO, header);
        this.setComponent(UiScreenSesion.MENU, menuBar);
        this.setComponent(UiScreenSesion.BAR, toolBar);
        this.setComponent(UiScreenSesion.TAB, tabPanel); 
	}
	
	private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 200;
        int ancho = Window.getClientWidth() - 5;
        tabPanel.setWidth(ancho + "px");
        tabPanel.setHeight(alto + "px");      
    }
	
	private void initListener(){
		
	}
	
	private void initStyle(){
		
	}
}
