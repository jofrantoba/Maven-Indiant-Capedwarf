package com.indiana.view.admin.uimanttipocambio;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.server.model.bean.TipoCambio;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.grid.GridPaisMoneda;
import com.indiana.view.uiutil.UiMantenimiento;
import com.indiana.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialDoubleBox;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoCambio extends UiMantenimiento implements InterUiMantTipoCambio {
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblPaisMonedaOrigen;
	protected UiSelectOption lstPaisMonedaOrigen;
	private MaterialLabel lblPaisMonedaDestino;
	protected UiSelectOption lstPaisMonedaDestino;
	protected TipoCambio bean;
	protected GridPaisMoneda gridPaisMonedaOrigen;
	protected PaisMoneda beanPaisMonedaOrigen;
	protected GridPaisMoneda gridPaisMonedaDestino;
	protected PaisMoneda beanPaisMonedaDestino;
	private MaterialLabel lblPrecioCompra;
	protected MaterialDoubleBox txtPrecioCompra;
	private MaterialLabel lblPrecioVenta;
	protected MaterialDoubleBox txtPrecioVenta;
	private MaterialLabel lblFecha;
	protected DateBox dtFecha;
	
	
	public UiMantTipoCambio(){
		initComponents();
		initListener();
		reCalcularWindows();
	}
		
	private void initComponents(){
		this.CardContTitulo.setText("TipoCambio");			
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblFecha=new MaterialLabel("FECHA");
		dtFecha=new DateBox();		
		dtFecha.setFormat(new DateBox.DefaultFormat(dateFormat));
		dtFecha.getDatePicker().setYearArrowsVisible(true);				
		lblPaisMonedaOrigen=new MaterialLabel("PAIS MONEDA ORIGEN");
		gridPaisMonedaOrigen=new GridPaisMoneda();		
		lstPaisMonedaOrigen=new UiSelectOption(gridPaisMonedaOrigen);
		lblPaisMonedaDestino=new MaterialLabel("PAIS MONEDA DESTINO");
		gridPaisMonedaDestino=new GridPaisMoneda();
		lstPaisMonedaDestino=new UiSelectOption(gridPaisMonedaDestino);
		lblPrecioCompra=new MaterialLabel("PRECIO COMPRA");
		txtPrecioCompra=new MaterialDoubleBox();
		lblPrecioVenta=new MaterialLabel("PRECIO VENTA");
		txtPrecioVenta=new MaterialDoubleBox();
		this.addWidget(0, 0, lblFecha);
		this.addWidget(0, 1, dtFecha);
		this.addWidget(1, 0, lblPaisMonedaOrigen);
		this.addWidget(1, 1, lstPaisMonedaOrigen);
		this.addWidget(2, 0, lblPaisMonedaDestino);
		this.addWidget(2, 1, lstPaisMonedaDestino);
		this.addWidget(3, 0, lblPrecioCompra);
		this.addWidget(3, 1, txtPrecioCompra);
		this.addWidget(4, 0, lblPrecioVenta);
		this.addWidget(4, 1, txtPrecioVenta);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}
	
	private void reCalcularWindows() {        
        this.pnlScroll.setSize("600px","300px");       
        this.center();
    }
	
	private void initListener(){
		lstPaisMonedaOrigen.btnCombo.addClickHandler(clickKandler);
		lstPaisMonedaOrigen.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionPaisMonedaOrigen();
			}
			
		});
		lstPaisMonedaOrigen.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionPaisMonedaOrigen();
			}

		
		});
		lstPaisMonedaOrigen.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarPaisMonedaOrigen();
			}

		
		});
		gridPaisMonedaOrigen.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanPaisMonedaOrigen=gridPaisMonedaOrigen.getSelectionModel().getSelectedObject();
				if(beanPaisMonedaOrigen!=null){
					lstPaisMonedaOrigen.txtInput.setText(beanPaisMonedaOrigen.getCodePaisMoneda());
					lstPaisMonedaOrigen.simplePopup.hide();					
				}
			}});
		

		lstPaisMonedaDestino.btnCombo.addClickHandler(clickKandler);
		lstPaisMonedaDestino.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionPaisMonedaDestino();
			}
			
		});
		lstPaisMonedaDestino.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionPaisMonedaDestino();
			}

		
		});
		lstPaisMonedaDestino.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarPaisMonedaDestino();
			}

		
		});
		gridPaisMonedaDestino.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanPaisMonedaDestino=gridPaisMonedaDestino.getSelectionModel().getSelectedObject();
				if(beanPaisMonedaDestino!=null){
					lstPaisMonedaDestino.txtInput.setText(beanPaisMonedaDestino.getCodePaisMoneda());
					lstPaisMonedaDestino.simplePopup.hide();
				}
			}});
	}
	
	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            
			txtId.setText(this.bean.getCodeTipoCambio());  
            txtId.setVisible(true);
            txtId.setEnabled(false);
            lblId.setVisible(true);
            dtFecha.setValue(this.bean.getFecha());
            dtFecha.setVisible(true);
            dtFecha.setEnabled(false);
            beanPaisMonedaOrigen=this.bean.getBeanPaisMonedaOrigen();
            beanPaisMonedaDestino=this.bean.getBeanPaisMonedaDestino();
            lstPaisMonedaOrigen.txtInput.setText(beanPaisMonedaOrigen.getCodePaisMoneda());
            lstPaisMonedaDestino.txtInput.setText(beanPaisMonedaDestino.getCodePaisMoneda());         
            lstPaisMonedaOrigen.txtInput.setEnabled(false);
            lstPaisMonedaOrigen.btnCombo.setEnabled(false);
            lstPaisMonedaDestino.txtInput.setEnabled(false);
            lstPaisMonedaDestino.btnCombo.setEnabled(false); 
            txtPrecioCompra.setValue(this.bean.getPrecioCompra());
            lblPrecioCompra.setVisible(true);
            txtPrecioCompra.setEnabled(true);
            txtPrecioVenta.setValue(this.bean.getPrecioVenta());
            lblPrecioVenta.setVisible(true);
            txtPrecioVenta.setEnabled(true);
            
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getCodeTipoCambio());                     
            beanPaisMonedaOrigen=this.bean.getBeanPaisMonedaOrigen();
            beanPaisMonedaDestino=this.bean.getBeanPaisMonedaDestino();
            lstPaisMonedaOrigen.txtInput.setText(beanPaisMonedaOrigen.getCodePaisMoneda());            
            lstPaisMonedaDestino.txtInput.setText(beanPaisMonedaDestino.getCodePaisMoneda());
            txtId.setEnabled(false);            
            lstPaisMonedaOrigen.txtInput.setEnabled(false);
            lstPaisMonedaOrigen.btnCombo.setEnabled(false);
            lstPaisMonedaDestino.txtInput.setEnabled(false);
            lstPaisMonedaDestino.btnCombo.setEnabled(false);
            txtPrecioCompra.setEnabled(false);
            txtPrecioVenta.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	txtId.setText(this.bean.getCodeTipoCambio());                       
            beanPaisMonedaOrigen=this.bean.getBeanPaisMonedaOrigen();
            beanPaisMonedaDestino=this.bean.getBeanPaisMonedaDestino();
            lstPaisMonedaOrigen.txtInput.setText(beanPaisMonedaOrigen.getCodePaisMoneda());
            lstPaisMonedaDestino.txtInput.setText(beanPaisMonedaDestino.getCodePaisMoneda());
            txtId.setEnabled(false);            
            lstPaisMonedaOrigen.txtInput.setEnabled(false);
            lstPaisMonedaOrigen.btnCombo.setEnabled(false);
            lstPaisMonedaDestino.txtInput.setEnabled(false);
            lstPaisMonedaDestino.btnCombo.setEnabled(false);
            txtPrecioCompra.setEnabled(false);
            txtPrecioVenta.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
            txtId.setEnabled(true);                                          
            lstPaisMonedaOrigen.txtBuscar.setEnabled(true);
            lstPaisMonedaOrigen.btnCombo.setEnabled(true);
            lstPaisMonedaDestino.txtBuscar.setEnabled(true);
            lstPaisMonedaDestino.btnCombo.setEnabled(true);
            beanPaisMonedaOrigen=null;
            beanPaisMonedaDestino=null;
            lstPaisMonedaOrigen.txtInput.setText(null);
            lstPaisMonedaOrigen.txtBuscar.setText("");
            lstPaisMonedaDestino.txtInput.setText(null);
            lstPaisMonedaDestino.txtBuscar.setText("");            
            cleanForm();
            this.btnOperacion.setEnabled(true);
        }
		clearError();
	}
	
	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.clear();                               
        lstPaisMonedaOrigen.txtInput.setText(null);
        lstPaisMonedaOrigen.txtBuscar.setText("");
        gridPaisMonedaOrigen.getSelectionModel().clear();
        lstPaisMonedaDestino.txtInput.setText(null);
        lstPaisMonedaDestino.txtBuscar.setText("");
        gridPaisMonedaDestino.getSelectionModel().clear();
	}
	@Override
	public void goToUiTipoCambio() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR) || 
				this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			if(this.beanPaisMonedaOrigen==null){
				MaterialToast.fireToast("Seleccionar un Pais Moneda Origen");
				return false;
			}else if(FieldVerifier.isEmpty(lstPaisMonedaOrigen.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Pais Moneda Origen");
				return false;
			}else if(this.beanPaisMonedaDestino==null){
				MaterialToast.fireToast("Seleccionar un Pais Moneda Destino");
				return false;
			}else if(FieldVerifier.isEmpty(lstPaisMonedaDestino.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Pais Moneda Destino");
				return false;
			}else if(this.beanPaisMonedaDestino.equals(this.beanPaisMonedaOrigen)){
				MaterialToast.fireToast("No seleccionar el mismo Pais Moneda");
				return false;
			}else{
				txtId.clearErrorOrSuccess();					
				return true;
			}
		}else{
			return true;
		
	}
	}
	@Override
	public void updateDataGrid(TipoCambio bean) {
		// TODO Auto-generated method stub
		
	}
	public void setBean(TipoCambio bean) {
		this.bean = bean;
	}
	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadPaisMonedaOrigen() {
		// TODO Auto-generated method stub
		
	}	
	
	ClickHandler clickKandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub			
			if(event.getSource().equals(lstPaisMonedaOrigen.btnCombo)){
				showSelectOptionPaisMonedaOrigen();
			}else if(event.getSource().equals(lstPaisMonedaDestino.btnCombo)){
				showSelectOptionPaisMonedaDestino();
			}
		}
		
	};
	
	
	@Override
	public void showSelectOptionPaisMonedaOrigen(){
		loadPaisMonedaOrigen();
		lstPaisMonedaOrigen.txtInput.clear();
		lstPaisMonedaOrigen.txtInput.clearErrorOrSuccess();
		lstPaisMonedaOrigen.grid.setHeight("150px");
		lstPaisMonedaOrigen.grid.setWidth("328px");
		lstPaisMonedaOrigen.simplePopup.setWidth("328px");
		lstPaisMonedaOrigen.simplePopup.setHeight("220px");
		gridPaisMonedaOrigen.getSelectionModel().clear();
		lstPaisMonedaOrigen.showPopup();		
	}

	@Override
	public void buscarPaisMonedaOrigen() {
		// TODO Auto-generated method stub
		gridPaisMonedaOrigen.getDataProvider().setFilter(lstPaisMonedaOrigen.txtBuscar.getText());
		gridPaisMonedaOrigen.getDataProvider().refresh();
	}

	@Override
	public void loadPaisMonedaDestino() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSelectOptionPaisMonedaDestino() {
		// TODO Auto-generated method stub		
		loadPaisMonedaDestino();
		lstPaisMonedaDestino.txtInput.clear();
		lstPaisMonedaDestino.txtInput.clearErrorOrSuccess();
		lstPaisMonedaDestino.grid.setHeight("150px");
		lstPaisMonedaDestino.grid.setWidth("328px");
		lstPaisMonedaDestino.simplePopup.setWidth("328px");
		lstPaisMonedaDestino.simplePopup.setHeight("220px");
		gridPaisMonedaDestino.getSelectionModel().clear();
		lstPaisMonedaDestino.showPopup();		
	}

	@Override
	public void buscarPaisMonedaDestino() {
		// TODO Auto-generated method stub
		gridPaisMonedaDestino.getDataProvider().setFilter(lstPaisMonedaDestino.txtBuscar.getText());
		gridPaisMonedaDestino.getDataProvider().refresh();
	}
	
}
