package com.indiana.view.admin.uimantentidadfinanciera;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.server.model.bean.Pais;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.grid.GridPais;
import com.indiana.view.uiutil.UiMantenimiento;
import com.indiana.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantEntidadFinanciera extends UiMantenimiento implements InterUiMantEntidadFinanciera {
	
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblNombre;
	protected MaterialTextBox txtNombre;
	private MaterialLabel lblCuenta;
	protected MaterialTextBox txtCuenta;
	private MaterialLabel lblPais;
	protected UiSelectOption lstPais;
	protected EntidadFinanciera bean;
	protected GridPais gridPais;
	protected Pais beanPais;
	
	public UiMantEntidadFinanciera(){
		initComponents();
		initListener();
		reCalcularWindows();
	}
		
	private void initComponents(){
		this.CardContTitulo.setText("Entidad Financiera");			
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblNombre=new MaterialLabel("ENTIDAD FINANCIERA");
		txtNombre=new MaterialTextBox();
		lblCuenta=new MaterialLabel("NRO CUENTA");		
		txtCuenta=new MaterialTextBox();
		lblPais=new MaterialLabel("PAIS");
		gridPais=new GridPais();		
		lstPais=new UiSelectOption(gridPais);
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(2, 0, lblNombre);
		this.addWidget(2, 1, txtNombre);
		this.addWidget(3, 0, lblCuenta);
		this.addWidget(3, 1, txtCuenta);
		this.addWidget(1, 0, lblPais);
		this.addWidget(1, 1, lstPais);
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
				}
			}});
	}
	
	private void reCalcularWindows() {        
        this.pnlScroll.setSize("600px","320px");       
        this.center();
    }
	
	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getCodeEntidadFinanciera());
            txtNombre.setText(this.bean.getNombreEntidadFinanciera());
            txtCuenta.setText(this.bean.getNumeroCuenta());            
            beanPais=this.bean.getBeanPais();
            lstPais.txtInput.setText(beanPais.getNombre());
            txtId.setEnabled(false);
            txtNombre.setEnabled(true);
            txtCuenta.setEnabled(true);
            lstPais.txtInput.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            txtNombre.setFocus(true);  
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getCodeEntidadFinanciera());
            txtNombre.setText(this.bean.getNombreEntidadFinanciera());
            txtCuenta.setText(this.bean.getNumeroCuenta());
            beanPais=this.bean.getBeanPais();
            lstPais.txtInput.setText(beanPais.getNombre());            
            txtId.setEnabled(false);
            txtNombre.setEnabled(false);       
            txtCuenta.setEnabled(false);
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	txtId.setText(this.bean.getCodeEntidadFinanciera());
            txtNombre.setText(this.bean.getNombreEntidadFinanciera());
            txtCuenta.setText(this.bean.getNumeroCuenta());
            beanPais=this.bean.getBeanPais();
            lstPais.txtInput.setText(beanPais.getNombre());
            txtId.setEnabled(false);
            txtNombre.setEnabled(false);       
            txtCuenta.setEnabled(false);
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
            txtId.setEnabled(true);                       
            txtNombre.setFocus(true);
            txtNombre.setEnabled(true);
            txtCuenta.setEnabled(true);
            lstPais.txtBuscar.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            beanPais=null;
            lstPais.txtInput.setText(null);
            lstPais.txtBuscar.setText("");
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
        txtCuenta.clear();
        lstPais.txtInput.setText(null);
        lstPais.txtBuscar.setText("");
        gridPais.getSelectionModel().clear();
	}
	@Override
	public void goToUiEntidadFinanciera() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR) || 
				this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtNombre.getText())){
				txtNombre.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtCuenta.getText())){
				txtCuenta.setError("Campo obligatorio");
				return false;
			}else if(this.beanPais==null){
				MaterialToast.fireToast("Seleccionar un Pais");
				return false;
			}else if(FieldVerifier.isEmpty(lstPais.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Pais");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtNombre.clearErrorOrSuccess();
				txtCuenta.clearErrorOrSuccess();				
				return true;
			}
		}else{
			return true;
		
	}
	}
	@Override
	public void updateDataGrid(EntidadFinanciera bean) {
		// TODO Auto-generated method stub
		
	}
	public void setBean(EntidadFinanciera bean) {
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
			}
		}
		
	};
	
	
	@Override
	public void showSelectOptionPais(){
		loadPais();
		lstPais.txtInput.clear();
		lstPais.txtInput.clearErrorOrSuccess();
		lstPais.grid.setHeight("150px");
		lstPais.grid.setWidth("328px");
		lstPais.simplePopup.setWidth("328px");
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

}
