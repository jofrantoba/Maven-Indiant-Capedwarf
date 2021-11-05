package com.indiana.view.negocio.uihomenegocio;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.indiana.client.resource.MyResource;
import com.indiana.view.negocio.uiloginnegocio.UiLoginNegocio;
import com.indiana.view.negocio.uiloginnegocio.UiLoginNegocioImpl;
import com.indiana.view.uiutil.JHeaderMenu;
import com.indiana.view.uiutil.UiScreenSesion;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialFooterCopyright;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTab;
import gwt.material.design.client.ui.MaterialTabItem;

public class UiHomeNegocio extends UiScreenSesion implements ClickHandler{
	private JHeaderMenu header;
	private MaterialFooterCopyright footCopy;
	private HTML lblCopy;
	private MaterialButton btnRegistrar;
	private MaterialTab tabs;
	private MaterialTabItem tabEmpezar;
	private MaterialLink lnkEmpezar;
	private MaterialTabItem tabVisionGeneral;
	private MaterialLink lnkVisionGeneral;
	private MaterialTabItem tabReclamaPerfil;
	private MaterialLink lnkReclamaPerfil;
	private MaterialTabItem tabBeneficios;
	private MaterialLink lnkBeneficios;
	private MaterialTabItem tabFuncionamiento;
	private MaterialLink lnkFuncionamiento;
	private MaterialTabItem tabCostos;
	private MaterialLink lnkCostos;
	private DeckPanel pnlTabs;
	private UiLoginNegocio uiLoginNegocio;
	
	public UiHomeNegocio(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		header=new JHeaderMenu();
		header.setTitle("INDIANT BUSSINES");
		btnRegistrar=new MaterialButton();
		btnRegistrar.setText("CREAR CUENTA");
		header.addWidgetToNavSection(btnRegistrar);
		tabs=new MaterialTab();
		tabEmpezar=new MaterialTabItem();
		lnkEmpezar=new MaterialLink();
		lnkEmpezar.setText("EMPEZAR");
		tabEmpezar.add(lnkEmpezar);	
		tabs.add(tabEmpezar);
		tabVisionGeneral=new MaterialTabItem();
		lnkVisionGeneral=new MaterialLink();
		lnkVisionGeneral.setText("VISION GENERAL");
		tabVisionGeneral.add(lnkVisionGeneral);	
		tabs.add(tabVisionGeneral);
		tabReclamaPerfil=new MaterialTabItem();
		lnkReclamaPerfil=new MaterialLink();
		lnkReclamaPerfil.setText("RECLAMAR PERFIL");
		tabReclamaPerfil.add(lnkReclamaPerfil);		
		tabs.add(tabReclamaPerfil);		
		tabBeneficios=new MaterialTabItem();
		lnkBeneficios=new MaterialLink();
		lnkBeneficios.setText("BENEFICIOS");
		tabBeneficios.add(lnkBeneficios);	
		tabs.add(tabBeneficios);		
		tabFuncionamiento=new MaterialTabItem();
		lnkFuncionamiento=new MaterialLink();
		lnkFuncionamiento.setText("FUNCIONAMIENTO");
		tabFuncionamiento.add(lnkFuncionamiento);	
		tabs.add(tabFuncionamiento);		
		tabCostos=new MaterialTabItem();
		lnkCostos=new MaterialLink();
		lnkCostos.setText("COSTOS");
		tabCostos.add(lnkCostos);	
		tabs.add(tabCostos);
		pnlTabs=new DeckPanel();
		uiLoginNegocio=new UiLoginNegocioImpl();
		pnlTabs.add(uiLoginNegocio);
		pnlTabs.showWidget(0);
		footCopy=new MaterialFooterCopyright();
		lblCopy=new HTML("&copy; 2016 Copyright Kiongo Technology");
		footCopy.add(lblCopy);
		this.setComponent(UiScreenSesion.TITULO, header);	
		this.setComponent(UiScreenSesion.MENU, tabs);
		this.setComponent(UiScreenSesion.CENTRO, pnlTabs);
		this.setComponent(UiScreenSesion.ESTADO, footCopy);
	}
	
	private void initListener(){
		
	}
	
	private void initStyle(){
		MyResource.INSTANCE.getStlUiHomeNegocio().ensureInjected();
		btnRegistrar.getElement().setId("kngButtonCuenta");	
		pnlTabs.getElement().setId("pnlTabs");
		btnRegistrar.setWaves(WavesType.LIGHT);
		tabEmpezar.setWaves(WavesType.LIGHT);
		tabVisionGeneral.setWaves(WavesType.LIGHT);
		tabReclamaPerfil.setWaves(WavesType.LIGHT);
		tabBeneficios.setWaves(WavesType.LIGHT);
		tabFuncionamiento.setWaves(WavesType.LIGHT);
		tabCostos.setWaves(WavesType.LIGHT);
		lnkEmpezar.setTextColor("black");
		lnkVisionGeneral.setTextColor("black");
		lnkReclamaPerfil.setTextColor("black");
		lnkBeneficios.setTextColor("black");
		lnkFuncionamiento.setTextColor("black");
		lnkCostos.setTextColor("black");
	}
	
	private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 150;
        int ancho = Window.getClientWidth() - 5;
        pnlTabs.setWidth(ancho + "px");
        pnlTabs.setHeight(alto + "px");      
    }

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
