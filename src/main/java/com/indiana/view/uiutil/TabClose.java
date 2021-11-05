package com.indiana.view.uiutil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.indiana.client.resource.MyResource;
import com.indiana.view.admin.uimenubaradmin.UiMenuBar;
import com.indiana.view.admin.uisesionadmin.UiSesionAdmin;

public class TabClose extends Composite implements ClickHandler {

    private Label lblTitulo;
    private ButtonBar btnClose;
    private HorizontalPanel pnlHor;
    private Widget ui;
    private String titulo;    
    
    public TabClose(Widget ui, String titulo) {
        this.ui = ui;
        this.titulo = titulo;
        initComponents();
        initListener();
        initStyle();
    }
    
    private void initComponents() {
        pnlHor = new HorizontalPanel();
        lblTitulo = new Label(titulo);
        btnClose = new ButtonBar(new Image(MyResource.INSTANCE.getImgCerrar16()));
        pnlHor.add(lblTitulo);
        pnlHor.add(btnClose);
        pnlHor.setTitle(titulo);
        initWidget(pnlHor);
    }
    
    private void initStyle() {   
        MyResource.INSTANCE.getStlTabClose().ensureInjected();		
        btnClose.addStyleName(MyResource.INSTANCE.getStlTabClose().btnClose());
        lblTitulo.addStyleName(MyResource.INSTANCE.getStlTabClose().lblTitulo());
        pnlHor.addStyleName(MyResource.INSTANCE.getStlTabClose().pnlHor());
    }
    
    private void initListener(){      
        btnClose.addClickHandler(this);
        pnlHor.addDomHandler(this,ClickEvent.getType());
    }

    @Override
    public void onClick(ClickEvent event) {
        if(event.getSource().equals(btnClose)){               
           UiSesionAdmin.tabPanel.remove(ui);
           UiMenuBar.tabs.remove(this);
           UiMenuBar.exitTabDefault();
           UiMenuBar.backGroundColor();
        }else if(event.getSource().equals(pnlHor)){
        	UiMenuBar.tabSelected=this;
            UiMenuBar.backGroundColor();
        }
        
    }
              
    
}

