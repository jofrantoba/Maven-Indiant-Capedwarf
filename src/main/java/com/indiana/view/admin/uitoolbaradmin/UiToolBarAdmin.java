package com.indiana.view.admin.uitoolbaradmin;

import gwt.material.design.client.ui.MaterialTooltip;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.indiana.client.resource.MyResource;
import com.indiana.view.uiutil.ButtonBar;

public class UiToolBarAdmin extends Composite{
	private HorizontalPanel pnlContenedor;
	private MaterialTooltip tipHome;
	private MaterialTooltip tipTurista;
	private MaterialTooltip tipNegocio;
	private MaterialTooltip tipDestino;
	private MaterialTooltip tipColonia;
	private MaterialTooltip tipMiembro;
	private ButtonBar linkHome;
	private ButtonBar linkTurista;
	private ButtonBar linkNegocio;
	private ButtonBar linkDestino;
	private ButtonBar linkColonia;
	private ButtonBar linkMiembro;
	
	public UiToolBarAdmin(){
		initComponents();
		initStyle();
	}
	
	private void initComponents(){
		pnlContenedor=new HorizontalPanel();
		linkHome=new ButtonBar(new Image(MyResource.INSTANCE.getImgHome48()));
		tipHome=new MaterialTooltip(linkHome,"Home");		
		linkTurista=new ButtonBar(new Image(MyResource.INSTANCE.getImgTurista48()));
		tipTurista=new MaterialTooltip(linkTurista,"Estadistica Turista");		
		linkNegocio=new ButtonBar(new Image(MyResource.INSTANCE.getImgNegocio48()));
		tipNegocio=new MaterialTooltip(linkNegocio,"Estadistica Negocio");		
		linkDestino=new ButtonBar(new Image(MyResource.INSTANCE.getImgDestino48()));
		tipDestino=new MaterialTooltip(linkDestino,"Estadistica Destino");		
		linkColonia=new ButtonBar(new Image(MyResource.INSTANCE.getImgColonia48()));
		tipColonia=new MaterialTooltip(linkColonia,"Estadistica Colonia");		
		linkMiembro=new ButtonBar(new Image(MyResource.INSTANCE.getImgMiembro48()));
		tipMiembro=new MaterialTooltip(linkMiembro,"Estadistica Miembro");		
		pnlContenedor.add(tipHome);
		pnlContenedor.add(tipTurista);
		pnlContenedor.add(tipNegocio);
		pnlContenedor.add(tipDestino);
		pnlContenedor.add(tipColonia);
		pnlContenedor.add(tipMiembro);
		this.initWidget(pnlContenedor);
	}
	
	private void initStyle(){
		MyResource.INSTANCE.getStlUiToolBarAdmin().ensureInjected();
		pnlContenedor.getElement().setId("pnlToolBar");
		linkHome.getElement().setId("btnToolHome");
		linkTurista.getElement().setId("btnToolTurista");
		linkNegocio.getElement().setId("btnToolNegocio");
		linkDestino.getElement().setId("btnToolDestino");
		linkColonia.getElement().setId("btnToolColonia");
		linkMiembro.getElement().setId("btnToolMiembro");
	}
	
}
