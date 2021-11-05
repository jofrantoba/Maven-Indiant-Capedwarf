package com.indiana.view.admin.uimantpaismoneda;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.server.model.bean.Moneda;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.grid.GridPais;
import com.indiana.view.admin.grid.GridMoneda;
import com.indiana.view.uiutil.UiMantenimiento;
import com.indiana.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantPaisMoneda extends UiMantenimiento implements InterUiMantPaisMoneda {
	
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblPais;
	protected UiSelectOption lstPais;
	private MaterialLabel lblMoneda;
	protected UiSelectOption lstMoneda;
	protected PaisMoneda bean;
	protected GridPais gridPais;
	protected Pais beanPais;
	protected GridMoneda gridMoneda;
	protected Moneda beanMoneda;
	
	public UiMantPaisMoneda(){
		initComponents();
		initListener();
	}
		
	private void initComponents(){
		this.CardContTitulo.setText("PaisMoneda");			
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblPais=new MaterialLabel("PAIS");
		gridPais=new GridPais();		
		lstPais=new UiSelectOption(gridPais);
		lblMoneda=new MaterialLabel("MONEDA");
		gridMoneda=new GridMoneda();
		lstMoneda=new UiSelectOption(gridMoneda);
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblPais);
		this.addWidget(1, 1, lstPais);
		this.addWidget(2, 0, lblMoneda);
		this.addWidget(2, 1, lstMoneda);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}
	
	private void initListener(){
		lstPais.btnCombo.addClickHandler(clickKandler);
		lstPais.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionPais();
			}
			
		});
		lstPais.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionPais();
			}

		
		});
		lstPais.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarPais();
			}

		
		});
		gridPais.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanPais=gridPais.getSelectionModel().getSelectedObject();
				if(beanPais!=null){
					lstPais.txtInput.setText(beanPais.getNombre());
					lstPais.simplePopup.hide();
					beanMoneda=null;
					lstMoneda.txtInput.setText(null);
				}
			}});
		

		lstMoneda.btnCombo.addClickHandler(clickKandler);
		lstMoneda.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionMoneda();
			}
			
		});
		lstMoneda.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionMoneda();
			}

		
		});
		lstMoneda.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarPais();
			}

		
		});
		gridMoneda.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanMoneda=gridMoneda.getSelectionModel().getSelectedObject();
				if(beanMoneda!=null){
					lstMoneda.txtInput.setText(beanMoneda.getDescripcion());
					lstMoneda.simplePopup.hide();
				}
			}});
	}
	
	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getCodePaisMoneda());                   
            beanPais=this.bean.getBeanPais();
            beanMoneda=this.bean.getBeanMoneda();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstMoneda.txtInput.setText(beanMoneda.getDescripcion());
            txtId.setEnabled(false);  
            txtId.setVisible(true);
            lblId.setVisible(true);
            lstPais.txtInput.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            lstMoneda.txtInput.setEnabled(true);
            lstMoneda.btnCombo.setEnabled(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getCodePaisMoneda());         
            beanPais=this.bean.getBeanPais();
            beanMoneda=this.bean.getBeanMoneda();
            lstPais.txtInput.setText(beanPais.getNombre());            
            lstMoneda.txtInput.setText(beanMoneda.getDescripcion());
            txtId.setEnabled(false); 
            txtId.setVisible(true);
            lblId.setVisible(true);
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstMoneda.txtInput.setEnabled(false);
            lstMoneda.btnCombo.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	txtId.setText(this.bean.getCodePaisMoneda());            
            beanPais=this.bean.getBeanPais();
            beanMoneda=this.bean.getBeanMoneda();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstMoneda.txtInput.setText(beanMoneda.getDescripcion());
            txtId.setEnabled(false); 
            txtId.setVisible(true);
            lblId.setVisible(true);
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstMoneda.txtInput.setEnabled(false);
            lstMoneda.btnCombo.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
            txtId.setEnabled(true);
            txtId.setVisible(false);
            lblId.setVisible(false);
            lstPais.txtBuscar.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            lstMoneda.txtBuscar.setEnabled(true);
            lstMoneda.btnCombo.setEnabled(true);
            beanPais=null;
            beanMoneda=null;
            lstPais.txtInput.setText(null);
            lstPais.txtBuscar.setText("");
            lstMoneda.txtInput.setText(null);
            lstMoneda.txtBuscar.setText("");
            gridPais.getSelectionModel().clear();
            cleanForm();
            this.btnOperacion.setEnabled(true);
        }
		clearError();
	}
	
	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.clear();                          
        lstPais.txtInput.setText(null);
        lstPais.txtBuscar.setText("");
        gridPais.getSelectionModel().clear();
        lstMoneda.txtInput.setText(null);
        lstMoneda.txtBuscar.setText("");
        gridMoneda.getSelectionModel().clear();
	}
	@Override
	public void goToUiPaisMoneda() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR) || 
				this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){			
			if(this.beanPais==null){
				MaterialToast.fireToast("Seleccionar un Pais");
				return false;
			}else if(FieldVerifier.isEmpty(lstPais.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Pais");
				return false;
			}else if(this.beanMoneda==null){
				MaterialToast.fireToast("Seleccionar una Moneda");
				return false;
			}else if(FieldVerifier.isEmpty(lstMoneda.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar una Moneda");
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
	public void updateDataGrid(PaisMoneda bean) {
		// TODO Auto-generated method stub
		
	}
	public void setBean(PaisMoneda bean) {
		this.bean = bean;
	}
	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadPais() {
		// TODO Auto-generated method stub
		
	}	
	
	ClickHandler clickKandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub			
			if(event.getSource().equals(lstPais.btnCombo)){
				showSelectOptionPais();
			}else if(event.getSource().equals(lstMoneda.btnCombo)){
				showSelectOptionMoneda();
			}
		}
		
	};
	
	
	@Override
	public void showSelectOptionPais(){
		loadPais();
		lstPais.txtInput.clear();
		lstPais.txtInput.clearErrorOrSuccess();
		lstPais.grid.setHeight("150px");
		lstPais.grid.setWidth("217px");
		lstPais.simplePopup.setWidth("217px");
		lstPais.simplePopup.setHeight("220px");
		gridPais.getSelectionModel().clear();
		lstPais.showPopup();
	}

	@Override
	public void buscarPais() {
		// TODO Auto-generated method stub
		gridPais.getDataProvider().setFilter(lstPais.txtBuscar.getText());
		gridPais.getDataProvider().refresh();
	}

	@Override
	public void loadMoneda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSelectOptionMoneda() {
		// TODO Auto-generated method stub
		if(beanPais!=null){
		loadMoneda();
		lstMoneda.txtInput.clear();
		lstMoneda.txtInput.clearErrorOrSuccess();
		lstMoneda.grid.setHeight("150px");
		lstMoneda.grid.setWidth("217px");
		lstMoneda.simplePopup.setWidth("217px");
		lstMoneda.simplePopup.setHeight("220px");
		gridMoneda.getSelectionModel().clear();
		lstMoneda.showPopup();
		}else{
			MaterialToast.fireToast("Seleccione Pais");
		}
	}

	@Override
	public void buscarMoneda() {
		// TODO Auto-generated method stub
		gridPais.getDataProvider().setFilter(lstMoneda.txtBuscar.getText());
		gridPais.getDataProvider().refresh();
	}

}
