package com.indiana.view.admin.uimantlocalidad;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.Region;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.grid.GridPais;
import com.indiana.view.admin.grid.GridRegion;
import com.indiana.view.uiutil.UiMantenimiento;
import com.indiana.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantLocalidad extends UiMantenimiento implements InterUiMantLocalidad {
	
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblNombre;
	protected MaterialTextBox txtNombre;
	private MaterialLabel lblPais;
	protected UiSelectOption lstPais;
	private MaterialLabel lblRegion;
	protected UiSelectOption lstRegion;
	protected Localidad bean;
	protected GridPais gridPais;
	protected Pais beanPais;
	protected GridRegion gridRegion;
	protected Region beanRegion;
	
	public UiMantLocalidad(){
		initComponents();
		initListener();
	}
		
	private void initComponents(){
		this.CardContTitulo.setText("Localidad");			
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblNombre=new MaterialLabel("NOMBRE");
		txtNombre=new MaterialTextBox();
		lblPais=new MaterialLabel("PAIS");
		gridPais=new GridPais();		
		lstPais=new UiSelectOption(gridPais);
		lblRegion=new MaterialLabel("REGION");
		gridRegion=new GridRegion();
		lstRegion=new UiSelectOption(gridRegion);
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(3, 0, lblNombre);
		this.addWidget(3, 1, txtNombre);
		this.addWidget(1, 0, lblPais);
		this.addWidget(1, 1, lstPais);
		this.addWidget(2, 0, lblRegion);
		this.addWidget(2, 1, lstRegion);
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
					beanRegion=null;
					lstRegion.txtInput.setText(null);
				}
			}});
		

		lstRegion.btnCombo.addClickHandler(clickKandler);
		lstRegion.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionRegion();
			}
			
		});
		lstRegion.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionRegion();
			}

		
		});
		lstRegion.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarRegion();
			}

		
		});
		gridRegion.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanRegion=gridRegion.getSelectionModel().getSelectedObject();
				if(beanRegion!=null){
					lstRegion.txtInput.setText(beanRegion.getNombre());
					lstRegion.simplePopup.hide();
				}
			}});
	}
	
	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(false);
            txtId.setText(this.bean.getCodeLocalidad());
            txtNombre.setText(this.bean.getNombre());                     
            beanPais=this.bean.getBeanPais();
            beanRegion=this.bean.getBeanRegion();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstRegion.txtInput.setText(beanRegion.getNombre());
            txtId.setEnabled(false);
            txtNombre.setEnabled(true);            
            lstPais.txtInput.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            lstRegion.txtInput.setEnabled(true);
            lstRegion.btnCombo.setEnabled(true);
            txtNombre.setFocus(true);  
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(false);
            txtId.setText(this.bean.getCodeLocalidad());
            txtNombre.setText(this.bean.getNombre());            
            beanPais=this.bean.getBeanPais();
            beanRegion=this.bean.getBeanRegion();
            lstPais.txtInput.setText(beanPais.getNombre());            
            lstRegion.txtInput.setText(beanRegion.getNombre());
            txtId.setEnabled(false);
            txtNombre.setEnabled(false);                   
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstRegion.txtInput.setEnabled(false);
            lstRegion.btnCombo.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(false);
        	txtId.setText(this.bean.getCodeLocalidad());
            txtNombre.setText(this.bean.getNombre());            
            beanPais=this.bean.getBeanPais();
            beanRegion=this.bean.getBeanRegion();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstRegion.txtInput.setText(beanRegion.getNombre());
            txtId.setEnabled(false);
            txtNombre.setEnabled(false);                   
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstRegion.txtInput.setEnabled(false);
            lstRegion.btnCombo.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(false);
        	txtId.setVisible(false);
            txtId.setEnabled(false);                       
            txtNombre.setFocus(true);
            txtNombre.setEnabled(true);            
            lstPais.txtBuscar.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            lstRegion.txtBuscar.setEnabled(true);
            lstRegion.btnCombo.setEnabled(true);
            beanPais=null;
            beanRegion=null;
            lstPais.txtInput.setText(null);
            lstPais.txtBuscar.setText("");
            lstRegion.txtInput.setText(null);
            lstRegion.txtBuscar.setText("");
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
        txtNombre.clear();        
        lstPais.txtInput.setText(null);
        lstPais.txtBuscar.setText("");
        gridPais.getSelectionModel().clear();
        lstRegion.txtInput.setText(null);
        lstRegion.txtBuscar.setText("");
        gridRegion.getSelectionModel().clear();
	}
	@Override
	public void goToUiLocalidad() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR) || 
				this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			/*if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else */if(FieldVerifier.isEmpty(txtNombre.getText())){
				txtNombre.setError("Campo obligatorio");
				return false;
			}else if(this.beanPais==null){
				MaterialToast.fireToast("Seleccionar un Pais");
				return false;
			}else if(FieldVerifier.isEmpty(lstPais.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Pais");
				return false;
			}else if(this.beanRegion==null){
				MaterialToast.fireToast("Seleccionar una Region");
				return false;
			}else if(FieldVerifier.isEmpty(lstRegion.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar una Region");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtNombre.clearErrorOrSuccess();						
				return true;
			}
		}else{
			return true;
		
	}
	}
	@Override
	public void updateDataGrid(Localidad bean) {
		// TODO Auto-generated method stub
		
	}
	public void setBean(Localidad bean) {
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
			}else if(event.getSource().equals(lstRegion.btnCombo)){
				showSelectOptionRegion();
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
	public void loadRegion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSelectOptionRegion() {
		// TODO Auto-generated method stub
		if(beanPais!=null){
		loadRegion();
		lstRegion.txtInput.clear();
		lstRegion.txtInput.clearErrorOrSuccess();
		lstRegion.grid.setHeight("150px");
		lstRegion.grid.setWidth("217px");
		lstRegion.simplePopup.setWidth("217px");
		lstRegion.simplePopup.setHeight("220px");
		gridRegion.getSelectionModel().clear();
		lstRegion.showPopup();
		}else{
			MaterialToast.fireToast("Seleccione Pais");
		}
	}

	@Override
	public void buscarRegion() {
		// TODO Auto-generated method stub
		gridRegion.getDataProvider().setFilter(lstRegion.txtBuscar.getText());
		gridRegion.getDataProvider().refresh();
	}

}
