package com.indiana.view.negocio.uitoolbarnegocio;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.indiana.client.resource.MyResource;
import com.indiana.view.negocio.uiadminofertasnegocio.UiAdminOfertasNegocio;
import com.indiana.view.negocio.uiadminpublicidadnegocio.UiAdminPublicidadNegocio;
import com.indiana.view.negocio.uichatnegocio.UiChatNegocio;
import com.indiana.view.negocio.uiconquistasnegocio.UiConquistasNegocio;
import com.indiana.view.negocio.uicontactonegocio.UiContactoNegocio;
import com.indiana.view.negocio.uihomesesionnegocio.UiHomeSesionNegocio;
import com.indiana.view.negocio.uisesionnegocio.UiSesionNegocio;
import com.indiana.view.negocio.uitarjetanegocio.UiTarjetaNegocio;
import com.indiana.view.negocio.uivernoticiasnegocio.UiVerNoticiasNegocio;
import com.indiana.view.uiutil.ButtonBar;
import com.indiana.view.uiutil.TabCloseNegocio;

import gwt.material.design.client.ui.MaterialTooltip;

public class UiToolBarNegocio extends Composite {
	private HorizontalPanel pnlContenedor;
	private MaterialTooltip tipHome;
	private MaterialTooltip tipNoticia;
	private MaterialTooltip tipOferta;
	private MaterialTooltip tipPublicidad;
	private MaterialTooltip tipChat;
	private MaterialTooltip tipConquista;
	private MaterialTooltip tipPago;
	private MaterialTooltip tipContactenos;
	private ButtonBar linkHome;
	private ButtonBar linkNoticia;
	private ButtonBar linkOferta;
	private ButtonBar linkPublicidad;
	private ButtonBar linkChat;
	private ButtonBar linkConquista;
	private ButtonBar linkPago;
	private ButtonBar linkContactenos;

	public UiToolBarNegocio() {
		initComponents();
		initStyle();
		initListener();
	}

	private void initComponents() {
		pnlContenedor = new HorizontalPanel();
		linkHome = new ButtonBar(new Image(MyResource.INSTANCE.getImgHome48()));
		tipHome = new MaterialTooltip(linkHome, "Home");
		linkNoticia = new ButtonBar(new Image(MyResource.INSTANCE.getImgNoticia48()));
		tipNoticia = new MaterialTooltip(linkNoticia, "Ver Noticias");
		linkOferta = new ButtonBar(new Image(MyResource.INSTANCE.getImgOferta48()));
		tipOferta = new MaterialTooltip(linkOferta, "Administrar Ofertas");
		linkPublicidad = new ButtonBar(new Image(MyResource.INSTANCE.getImgMegafono48()));
		tipPublicidad = new MaterialTooltip(linkPublicidad, "Administrar publicidad");
		linkChat = new ButtonBar(new Image(MyResource.INSTANCE.getImgChat48()));
		tipChat = new MaterialTooltip(linkChat, "Habla con el turista");
		linkConquista = new ButtonBar(new Image(MyResource.INSTANCE.getImgDestino48()));
		tipConquista = new MaterialTooltip(linkConquista, "Conquistas");
		linkPago = new ButtonBar(new Image(MyResource.INSTANCE.getImgTarjeta48()));
		tipPago = new MaterialTooltip(linkPago, "Agregar Tarjeta");
		linkContactenos = new ButtonBar(new Image(MyResource.INSTANCE.getImgAsistencia48()));
		tipContactenos = new MaterialTooltip(linkContactenos, "Contactenos");

		pnlContenedor.add(tipHome);
		pnlContenedor.add(tipNoticia);
		pnlContenedor.add(tipOferta);
		pnlContenedor.add(tipPublicidad);
		pnlContenedor.add(tipChat);
		pnlContenedor.add(tipConquista);
		pnlContenedor.add(tipPago);
		pnlContenedor.add(tipContactenos);
		this.initWidget(pnlContenedor);
	}

	public void initListener() {
		linkHome.addClickHandler(linkHomeHandler);		
		linkNoticia.addClickHandler(linkNoticiaHandler);
		linkOferta.addClickHandler(linkOfertaHandler);
		linkPublicidad.addClickHandler(linkPublicidadHandler);
		linkChat.addClickHandler(linkChatHandler);
		linkConquista.addClickHandler(linkConquistaHandler);
		linkPago.addClickHandler(linkPagoHandler);
		linkContactenos.addClickHandler(linkContactenosHandler);

	}

	public static ArrayList<TabCloseNegocio> tabs = new ArrayList<TabCloseNegocio>();
	public static TabCloseNegocio tabSelected;

	ClickHandler linkHomeHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			ScrollPanel scrollPanel = UiSesionNegocio.uiHomeNegocioScroll;
			if (scrollPanel == null) {
				scrollPanel = new ScrollPanel();
				FlexTable flexTablePanel = new FlexTable();
				flexTablePanel.setWidget(0, 0, new UiHomeSesionNegocio());
				scrollPanel.add(flexTablePanel);
				flexTablePanel.setWidget(1, 0, UiHomeSesionNegocio.returnFlex());
				UiSesionNegocio.uiHomeNegocioScroll = scrollPanel;
			}
			TabCloseNegocio myTab = new TabCloseNegocio(scrollPanel, "Home");
			UiSesionNegocio.tabPanel.add(scrollPanel, myTab);
			tabs.add(myTab);
			UiSesionNegocio.tabPanel.selectTab(scrollPanel);
			tabSelected = myTab;
			backGroundColor();
		}
	};
	
	ClickHandler linkNoticiaHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			UiVerNoticiasNegocio uiVerNoticias= UiSesionNegocio.uiVerNoticiasNegocio;
			if (uiVerNoticias == null) {                
				uiVerNoticias = new UiVerNoticiasNegocio(); 
				UiSesionNegocio.uiVerNoticiasNegocio= uiVerNoticias;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiVerNoticias, "Ver Noticias");
            UiSesionNegocio.tabPanel.add(uiVerNoticias, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiVerNoticias);
            tabSelected=tab;
            backGroundColor();	
		}
	};


	ClickHandler linkOfertaHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			UiAdminOfertasNegocio uiAdminOfertas= UiSesionNegocio.uiAdministrarOfertasNegocio;
			if (uiAdminOfertas == null) {                
				uiAdminOfertas = new UiAdminOfertasNegocio(); 
				UiSesionNegocio.uiAdministrarOfertasNegocio= uiAdminOfertas;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiAdminOfertas, "Administrar Ofertas");
            UiSesionNegocio.tabPanel.add(uiAdminOfertas, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiAdminOfertas);
            tabSelected=tab;
            backGroundColor();	
		}
	};
	
	
	ClickHandler linkPublicidadHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			UiAdminPublicidadNegocio uiAdminPublicidad= UiSesionNegocio.uiAdminPublicidadNegocio;
			if (uiAdminPublicidad == null) {                
				uiAdminPublicidad = new UiAdminPublicidadNegocio(); 
				UiSesionNegocio.uiAdminPublicidadNegocio= uiAdminPublicidad;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiAdminPublicidad, "Administrar Publicidad");
            UiSesionNegocio.tabPanel.add(uiAdminPublicidad, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiAdminPublicidad);
            tabSelected=tab;
            backGroundColor();	
		}
	};
	
	
	ClickHandler linkChatHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			UiChatNegocio uiChatNegocio= UiSesionNegocio.uiChatNegocio;
			if (uiChatNegocio == null) {                
				uiChatNegocio = new UiChatNegocio(); 
				UiSesionNegocio.uiChatNegocio= uiChatNegocio;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiChatNegocio, "Chat Negocio");
            UiSesionNegocio.tabPanel.add(uiChatNegocio, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiChatNegocio);
            tabSelected=tab;
            backGroundColor();	
		}
	};
	
	ClickHandler linkConquistaHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			UiConquistasNegocio uiConquistarNegocio= UiSesionNegocio.uiConquistasNegocio;
			if (uiConquistarNegocio == null) {                
				uiConquistarNegocio = new UiConquistasNegocio(); 
				UiSesionNegocio.uiConquistasNegocio= uiConquistarNegocio;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiConquistarNegocio, "Conquistas Negocio");
            UiSesionNegocio.tabPanel.add(uiConquistarNegocio, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiConquistarNegocio);
            tabSelected=tab;
            backGroundColor();	
		}
	};
	
	
	
	ClickHandler linkPagoHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			UiTarjetaNegocio uiPagoNegocio= UiSesionNegocio.uiTarjetaNegocio;
			if (uiPagoNegocio == null) {                
				uiPagoNegocio = new UiTarjetaNegocio(); 
				UiSesionNegocio.uiTarjetaNegocio= uiPagoNegocio;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiPagoNegocio, "Pagos Negocio");
            UiSesionNegocio.tabPanel.add(uiPagoNegocio, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiPagoNegocio);
            tabSelected=tab;
            backGroundColor();	
		}
	};
	
	
	ClickHandler linkContactenosHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			UiContactoNegocio uiContactoNegocio= UiSesionNegocio.uiContactoNegocio;
			if (uiContactoNegocio == null) {                
				uiContactoNegocio = new UiContactoNegocio(); 
				UiSesionNegocio.uiContactoNegocio= uiContactoNegocio;
            }
			TabCloseNegocio tab = new TabCloseNegocio(uiContactoNegocio, "Contactanos");
            UiSesionNegocio.tabPanel.add(uiContactoNegocio, tab);                
            tabs.add(tab);
            UiSesionNegocio.tabPanel.selectTab(uiContactoNegocio);
            tabSelected=tab;
            backGroundColor();	
		}
	};
	
	
	public static void backGroundColor() {
		for (int i = 0; i < tabs.size(); i++) {
			TabCloseNegocio tab = tabs.get(i);
			tab.getElement().getStyle().setBackgroundColor("#BDF9BB");
		}
		tabSelected.getElement().getStyle().setBackgroundColor("#F9E96B");
	}

	public static void exitTabDefault() {
		for (int i = 0; i < tabs.size();) {
			tabSelected = tabs.get(i);
			break;
		}
	}

	private void initStyle() {
		MyResource.INSTANCE.getStlUiToolBarAdmin().ensureInjected();
		pnlContenedor.getElement().setId("pnlToolBar");
		linkHome.getElement().setId("btnToolHome");
		linkNoticia.getElement().setId("btnToolNoticia");
		linkOferta.getElement().setId("btnToolOferta");
		linkPublicidad.getElement().setId("btnToolPublicidad");
		linkChat.getElement().setId("btnToolChat");
		linkConquista.getElement().setId("btnToolConquista");
		linkPago.getElement().setId("btnToolPago");
		linkContactenos.getElement().setId("btnToolContactenos");
	}
}
