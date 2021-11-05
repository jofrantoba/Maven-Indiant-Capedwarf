package com.indiana.view.uiutil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.indiana.client.resource.MyResource;

import gwt.material.design.client.ui.MaterialButton;

public class UiFormMantenimiento extends Composite implements ClickHandler {

    public static final int BOTONOPER0 = 0;
    public static final int BOTONOPER1 = 1;
    public static final int BOTONOPER2 = 2;
    public static final int BOTONOPER3 = 3;
    public static final int BOTONOPER4 = 4;
    public static final int BOTONOPER5 = 5;
    protected MaterialButton btnOper1;
    protected MaterialButton btnOper2;
    protected MaterialButton btnOper3;
    protected MaterialButton btnOper4;
    protected MaterialButton btnOper5;
    protected MaterialButton btnOper0;

    private FlowPanel pnlContenedor;
    private FlowPanel pnlBotones;
    private FlowPanel pnlBusqueda;
    private FlowPanel pnlTabla;
    private FlowPanel pnlUnion;

    public UiFormMantenimiento() {
        initComponents();
        Estilo();
        initWidget(pnlContenedor);
    }

    private void initComponents() {

        pnlContenedor = new FlowPanel();
        pnlBotones = new FlowPanel();
        pnlBusqueda = new FlowPanel();
        pnlTabla = new FlowPanel();
        pnlUnion = new FlowPanel();

        btnOper1 = new MaterialButton();
        btnOper1.setText("Agregar");
        btnOper2 = new MaterialButton();
        btnOper2.setText("Modificar");
        btnOper3 = new MaterialButton();
        btnOper3.setText("Eliminar");
        btnOper4 = new MaterialButton();
        btnOper4.setText("Detalle");
        btnOper5 = new MaterialButton();
        btnOper5.setText("Exportar");
        btnOper0 = new MaterialButton();
        btnOper0.setText("Actualizar");

        pnlBotones.add(btnOper1);
        pnlBotones.add(btnOper2);
        pnlBotones.add(btnOper3);
        pnlBotones.add(btnOper4);
        pnlBotones.add(btnOper5);
        pnlBotones.add(btnOper0);

        pnlUnion.add(pnlBusqueda);
        pnlUnion.add(pnlTabla);

        pnlContenedor.add(pnlBotones);
        pnlContenedor.add(pnlUnion);
        btnOper1.addClickHandler(this);
        btnOper2.addClickHandler(this);
        btnOper3.addClickHandler(this);
        btnOper4.addClickHandler(this);
        btnOper5.addClickHandler(this);
        btnOper0.addClickHandler(this);          
    }

    private void Estilo() {
        pnlBotones.getElement().setId("pnlBotones");
        pnlBusqueda.getElement().setId("pnlBusqueda");
        pnlTabla.getElement().setId("pnlTabla");
        pnlContenedor.getElement().setId("pnlForm");
        pnlUnion.getElement().setId("pnlUnion");
        btnOper0.getElement().setId("btnActualizar");
        MyResource.INSTANCE.getStlUiFormMantenimiento().ensureInjected();
    }

    public FlowPanel getPnlTabla() {
        return pnlTabla;
    }

    public FlowPanel getPnlBusqueda() {
        return pnlBusqueda;
    }

    public FlowPanel getPnlBotones() {
        return pnlBotones;
    }

    public FlowPanel getPnlUnion() {
        return pnlUnion;
    }
    
    

    @Override
    public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub
        if (event.getSource().equals(btnOper1)) {
            showUIOper1();
        } else if (event.getSource().equals(btnOper2)) {
            showUIOper2();
        } else if (event.getSource().equals(btnOper3)) {
            showUIOper3();
        } else if (event.getSource().equals(btnOper4)) {
            showUIOper4();
        } else if (event.getSource().equals(btnOper5)) {
            showUIOper5();
        } else if (event.getSource().equals(btnOper0)) {
            showUIOper0();
        }
    }

    public void addComponent(Widget Component) {
        pnlBotones.add(Component);
    }

    public void setRenderizar(int component, String titulo, String toolTip) {
        switch (component) {
            case 0:
                btnOper0.setTitle(toolTip);
                btnOper0.setText(titulo);
                break;
            case 1:
                btnOper1.setTitle(toolTip);
                btnOper1.setText(titulo);
                break;
            case 2:
                btnOper2.setTitle(toolTip);
                btnOper2.setText(titulo);
                break;
            case 3:
                btnOper3.setTitle(toolTip);
                btnOper3.setText(titulo);
                break;
            case 4:
                btnOper4.setTitle(toolTip);
                btnOper4.setText(titulo);
                break;
        }

    }

    public void setVisible(int component, boolean value) {
        switch (component) {
            case 0:
                btnOper0.setVisible(value);
                break;
            case 1:
                btnOper1.setVisible(value);
                break;
            case 2:
                btnOper2.setVisible(value);
                break;
            case 3:
                btnOper3.setVisible(value);
                break;
            case 4:
                btnOper4.setVisible(value);
                break;
        }

    }

    public void showUIOper1() {

    }

    public void showUIOper2() {

    }

    public void showUIOper3() {

    }

    public void showUIOper4() {
        
    }
    
    public void showUIOper5() {
        
    }

    public void showUIOper0() {        
        loadTable();
    }

    public void loadTable() {

    }

}
