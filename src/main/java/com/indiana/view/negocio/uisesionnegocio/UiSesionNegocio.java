package com.indiana.view.negocio.uisesionnegocio;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.indiana.view.negocio.uiadminofertasnegocio.UiAdminOfertasNegocio;
import com.indiana.view.negocio.uiadminpublicidadnegocio.UiAdminPublicidadNegocio;
import com.indiana.view.negocio.uichatnegocio.UiChatNegocio;
import com.indiana.view.negocio.uiconquistasnegocio.UiConquistasNegocio;
import com.indiana.view.negocio.uicontactonegocio.UiContactoNegocio;
import com.indiana.view.negocio.uihomenegocio.UiHomeNegocio;
import com.indiana.view.negocio.uihomesesionnegocio.UiHomeSesionNegocio;
import com.indiana.view.negocio.uitarjetanegocio.UiTarjetaNegocio;
import com.indiana.view.negocio.uitoolbarnegocio.UiToolBarNegocio;
import com.indiana.view.negocio.uivernoticiasnegocio.UiVerNoticiasNegocio;
import com.indiana.view.uiutil.JHeaderMenu;
import com.indiana.view.uiutil.UiScreenSesion;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialLink;

public class UiSesionNegocio extends UiScreenSesion implements InterUiSesionNegocio {

	private JHeaderMenu header;
	private MaterialLink linkUser;
	private MaterialLink linkSettings;
	private MaterialLink linkCloseSesion;	
	private UiToolBarNegocio toolBar;
	public static TabLayoutPanel tabPanel;
	public static UiHomeNegocio uiHomeNegocio;
	public static ScrollPanel uiHomeNegocioScroll;
	public static UiHomeSesionNegocio uiHomeSesionNegocio;
	public static UiVerNoticiasNegocio uiVerNoticiasNegocio;
	public static UiAdminOfertasNegocio uiAdministrarOfertasNegocio;
	public static UiAdminPublicidadNegocio uiAdminPublicidadNegocio;
	public static UiChatNegocio uiChatNegocio;
	public static UiConquistasNegocio uiConquistasNegocio;
	public static UiTarjetaNegocio uiTarjetaNegocio;
	public static UiContactoNegocio uiContactoNegocio;
	
	public static ScrollPanel tabPanelScrollable;
	
	public UiSesionNegocio() {
		initComponents();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		header = new JHeaderMenu();
		header.setTitle("ADMINISTRACION NEGOCIO");
		linkUser = new MaterialLink();
		linkUser.setIconType(IconType.BUSINESS);
		linkUser.setText("jonathan@kiongo.com");
		linkUser.setWaves(WavesType.LIGHT);
		linkUser.setTooltip("Ver Perfil");
		linkSettings = new MaterialLink();
		linkSettings.setIconType(IconType.SETTINGS);
		linkSettings.setWaves(WavesType.LIGHT);
		linkSettings.setTooltip("Configuracion");
		linkCloseSesion = new MaterialLink();
		linkCloseSesion.setIconType(IconType.CLOSE);
		linkCloseSesion.setWaves(WavesType.LIGHT);
		linkCloseSesion.setTooltip("Cerrar Sesion");
		header.addWidgetToNavSection(linkUser);
		header.addWidgetToNavSection(linkSettings);
		header.addWidgetToNavSection(linkCloseSesion);
		toolBar = new UiToolBarNegocio();
		tabPanel = new TabLayoutPanel(4, Unit.EM);
		tabPanel.setAnimationDuration(1000);
		
		tabPanelScrollable= new ScrollPanel();
		
		this.setComponent(UiScreenSesion.TITULO, header);
		this.setComponent(UiScreenSesion.BAR, toolBar);
		this.setComponent(UiScreenSesion.TAB, tabPanel);
	}

	private void initStyle() {

	}

	private void reCalcularWindows() {
		int alto = Window.getClientHeight() - 200;
		int ancho = Window.getClientWidth() - 5;
		tabPanel.setWidth(ancho + "px");
		tabPanel.setHeight(alto + "px");
	}

}
