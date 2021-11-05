package com.indiana.view.admin.uimanttarifario;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.Tarifario;
import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.grid.GridPais;
import com.indiana.view.admin.grid.GridTipoSuscripcion;
import com.indiana.view.admin.uitarifario.UiTarifario;
import com.indiana.view.uiutil.UiMantenimiento;
import com.indiana.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialDoubleBox;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantTarifario extends UiMantenimiento implements InterUiMantTarifario {
	
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblRangoInicial;
	protected MaterialIntegerBox txtRangoInicial;
	private MaterialLabel lblRangoFinal;
	protected MaterialIntegerBox txtRangoFinal;
	private MaterialLabel lblPrecioUnitario;
	protected MaterialDoubleBox txtPrecioUnitario;
	private MaterialLabel lblPais;
	protected UiSelectOption lstPais;
	private MaterialLabel lblTipoSuscripcion;
	protected UiSelectOption lstTipoSuscripcion;
	private MaterialLabel lblEstado;
	protected MaterialSwitch swtEstado;
	private MaterialLabel lblFechaInicial;
	protected MaterialDatePicker dateFechaInicial;
	private MaterialLabel lblFechaFinal;
	protected MaterialDatePicker dateFechaFinal;
	protected Tarifario bean;
	protected GridPais gridPais;
	protected Pais beanPais;
	protected GridTipoSuscripcion gridTipoSuscripcion;
	protected TipoSuscripcion beanTipoSuscripcion;
	
	public UiMantTarifario(){
		initComponents();
		initListener();
		reCalcularWindows();
	}
		
	private void initComponents(){
		this.CardContTitulo.setText("Tarifario");			
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblPais=new MaterialLabel("PAIS");
		gridPais=new GridPais();		
		lstPais=new UiSelectOption(gridPais);
		lblTipoSuscripcion=new MaterialLabel("TIPO SUSCRIPCION");
		gridTipoSuscripcion=new GridTipoSuscripcion();
		lstTipoSuscripcion=new UiSelectOption(gridTipoSuscripcion);
		lblRangoInicial=new MaterialLabel("RANGO INICIAL");
		txtRangoInicial=new MaterialIntegerBox();
		lblRangoFinal=new MaterialLabel("RANGO FINAL");
		txtRangoFinal=new MaterialIntegerBox();
		lblPrecioUnitario=new MaterialLabel("PRECIO UNITARIO");
		txtPrecioUnitario=new MaterialDoubleBox();
		lblEstado=new MaterialLabel("ESTADO");
		swtEstado=new MaterialSwitch();
		lblFechaInicial= new MaterialLabel("FECHA ACTIVACION");
		
		lblFechaFinal= new MaterialLabel("FECHA DESACTIVACION");
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblPais);
		this.addWidget(1, 1, lstPais);
		this.addWidget(2, 0, lblTipoSuscripcion);
		this.addWidget(2, 1, lstTipoSuscripcion);
		this.addWidget(3, 0, lblRangoInicial);
		this.addWidget(3, 1, txtRangoInicial);
		this.addWidget(4, 0, lblRangoFinal);
		this.addWidget(4, 1, txtRangoFinal);
		this.addWidget(5, 0, lblPrecioUnitario);
		this.addWidget(5, 1, txtPrecioUnitario);
		this.addWidget(6, 0, lblEstado);
		this.addWidget(6, 1, swtEstado);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}
	
	private void reCalcularWindows() {        
        this.pnlScroll.setSize("600px","300px");       
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
		

				
		lstTipoSuscripcion.btnCombo.addClickHandler(clickKandler);
		lstTipoSuscripcion.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionTipoSuscripcion();
			}
			
		});
		lstTipoSuscripcion.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionTipoSuscripcion();
			}

		
		});
		lstTipoSuscripcion.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarTipoSuscripcion();
			}

		
		});
		gridTipoSuscripcion.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanTipoSuscripcion=gridTipoSuscripcion.getSelectionModel().getSelectedObject();
				if(beanTipoSuscripcion!=null){
					lstTipoSuscripcion.txtInput.setText(beanTipoSuscripcion.getDescripcion());
					lstTipoSuscripcion.simplePopup.hide();
				}
			}});
	}
	
	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTarifario());                    
            beanPais=this.bean.getBeanPais();
            beanTipoSuscripcion=this.bean.getBeanTipoSuscripcion();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstTipoSuscripcion.txtInput.setText(beanTipoSuscripcion.getDescripcion());
            txtId.setEnabled(false);     
            lstPais.txtInput.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            lstTipoSuscripcion.txtInput.setEnabled(true);
            lstTipoSuscripcion.btnCombo.setEnabled(true);
            txtRangoInicial.setEnabled(true);
            txtRangoFinal.setEnabled(true);
            txtPrecioUnitario.setEnabled(true);
            txtRangoInicial.setValue(this.bean.getRangoInicial());
            txtRangoFinal.setValue(this.bean.getRangoFinal());
            txtPrecioUnitario.setValue(this.bean.getPrecioUnidad());
            lblEstado.setVisible(true);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            swtEstado.setVisible(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTarifario());          
            beanPais=this.bean.getBeanPais();
            beanTipoSuscripcion=this.bean.getBeanTipoSuscripcion();
            lstPais.txtInput.setText(beanPais.getNombre());            
            lstTipoSuscripcion.txtInput.setText(beanTipoSuscripcion.getDescripcion());
            txtId.setEnabled(false);         
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstTipoSuscripcion.txtInput.setEnabled(false);
            lstTipoSuscripcion.btnCombo.setEnabled(false);
            txtRangoInicial.setEnabled(false);
            txtRangoFinal.setEnabled(false);
            txtPrecioUnitario.setEnabled(false);
            txtRangoInicial.setValue(this.bean.getRangoInicial());
            txtRangoFinal.setValue(this.bean.getRangoFinal());
            txtPrecioUnitario.setValue(this.bean.getPrecioUnidad());
            lblEstado.setVisible(true);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            swtEstado.setVisible(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
        	txtId.setText(this.bean.getCodeTarifario());         
            beanPais=this.bean.getBeanPais();
            beanTipoSuscripcion=this.bean.getBeanTipoSuscripcion();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstTipoSuscripcion.txtInput.setText(beanTipoSuscripcion.getDescripcion());
            txtId.setEnabled(false);                  
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstTipoSuscripcion.txtInput.setEnabled(false);
            lstTipoSuscripcion.btnCombo.setEnabled(false);
            txtRangoInicial.setEnabled(false);
            txtRangoFinal.setEnabled(false);
            txtPrecioUnitario.setEnabled(false);
            txtRangoInicial.setValue(this.bean.getRangoInicial());
            txtRangoFinal.setValue(this.bean.getRangoFinal());
            txtPrecioUnitario.setValue(this.bean.getPrecioUnidad());
            lblEstado.setVisible(true);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            swtEstado.setVisible(true);
            this.btnOperacion.setEnabled(false);
        }else if (this.modo.equals(UiTarifario.MODODESACTIVAR)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
        	txtId.setText(this.bean.getCodeTarifario());         
            beanPais=this.bean.getBeanPais();
            beanTipoSuscripcion=this.bean.getBeanTipoSuscripcion();
            lstPais.txtInput.setText(beanPais.getNombre());
            lstTipoSuscripcion.txtInput.setText(beanTipoSuscripcion.getDescripcion());
            txtId.setEnabled(false);                  
            lstPais.txtInput.setEnabled(false);
            lstPais.btnCombo.setEnabled(false);
            lstTipoSuscripcion.txtInput.setEnabled(false);
            lstTipoSuscripcion.btnCombo.setEnabled(false);
            txtRangoInicial.setEnabled(false);
            txtRangoFinal.setEnabled(false);
            txtPrecioUnitario.setEnabled(false);
            txtRangoInicial.setValue(this.bean.getRangoInicial());
            txtRangoFinal.setValue(this.bean.getRangoFinal());
            txtPrecioUnitario.setValue(this.bean.getPrecioUnidad());
            lblEstado.setVisible(true);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            swtEstado.setVisible(true);
            this.btnOperacion.setEnabled(true);
        } else {
        	lblId.setVisible(false);
        	txtId.setVisible(false);
            txtId.setEnabled(true);                
            lstPais.txtInput.setEnabled(true);
            lstPais.btnCombo.setEnabled(true);
            lstTipoSuscripcion.txtInput.setEnabled(true);
            lstTipoSuscripcion.btnCombo.setEnabled(true);
            txtRangoInicial.setEnabled(true);
            txtRangoFinal.setEnabled(true);
            txtPrecioUnitario.setEnabled(true);
            lblEstado.setVisible(false);
            swtEstado.setValue(true);
            swtEstado.setEnabled(false);
            swtEstado.setVisible(false);
            beanPais=null;
            beanTipoSuscripcion=null;                        
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
        lstTipoSuscripcion.txtInput.setText(null);
        lstTipoSuscripcion.txtBuscar.setText("");
        gridTipoSuscripcion.getSelectionModel().clear();
        txtRangoInicial.clear();
        txtRangoFinal.clear();
        txtPrecioUnitario.clear();
	}
	@Override
	public void goToUiTarifario() {
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
			}else if(this.beanTipoSuscripcion==null){
				MaterialToast.fireToast("Seleccionar un Tipo Suscripcion");
				return false;
			}else if(FieldVerifier.isEmpty(lstTipoSuscripcion.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Tipo Suscripcion");
				return false;
			}else if(FieldVerifier.isEmpty(txtRangoInicial.getText())){
				txtRangoInicial.setError("Ingrese valor entero");
				return false;
			}else if(FieldVerifier.isEmpty(txtRangoFinal.getText())){
				txtRangoFinal.setError("Ingrese valor entero");
				return false;
			}else if(FieldVerifier.isEmpty(txtPrecioUnitario.getText())){
				txtPrecioUnitario.setError("Ingrese valor decimal");
				return false;
			}else if(FieldVerifier.notIsInteger(txtRangoInicial.getText())){
				txtRangoInicial.setError("Ingrese valor entero");
				return false;
			}else if(FieldVerifier.notIsInteger(txtRangoFinal.getText())){
				txtRangoFinal.setError("Ingrese valor entero");
				return false;
			}else if(FieldVerifier.notIsDouble(txtPrecioUnitario.getText())){
				txtPrecioUnitario.setError("Ingrese valor decimal");
				return false;
			}else if(txtRangoFinal.getValue() < txtRangoInicial.getValue()){
				MaterialToast.fireToast("Rango Final debe ser igual o mayor a Rango Inicial");
				return false;
			}else{
				clearError();			
				return true;
			}
		}else{
			return true;
		
	}
	}
	@Override
	public void updateDataGrid(Tarifario bean) {
		// TODO Auto-generated method stub
		
	}
	public void setBean(Tarifario bean) {
		this.bean = bean;
	}
	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.clearErrorOrSuccess();		
		txtRangoInicial.clearErrorOrSuccess();
		txtRangoFinal.clearErrorOrSuccess();
		txtPrecioUnitario.clearErrorOrSuccess();		
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
			}/*else if(event.getSource().equals(lstRegion.btnCombo)){
				showSelectOptionRegion();
			}*/else if(event.getSource().equals(lstTipoSuscripcion.btnCombo)){
				showSelectOptionTipoSuscripcion();
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

	@Override
	public void loadTipoSuscripcion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSelectOptionTipoSuscripcion() {
		// TODO Auto-generated method stub
		loadTipoSuscripcion();
		lstTipoSuscripcion.txtInput.clear();
		lstTipoSuscripcion.txtInput.clearErrorOrSuccess();
		lstTipoSuscripcion.grid.setHeight("150px");
		lstTipoSuscripcion.grid.setWidth("328px");
		lstTipoSuscripcion.simplePopup.setWidth("328px");
		lstTipoSuscripcion.simplePopup.setHeight("220px");
		gridTipoSuscripcion.getSelectionModel().clear();
		lstTipoSuscripcion.showPopup();
	}

	@Override
	public void buscarTipoSuscripcion() {
		// TODO Auto-generated method stub
		gridTipoSuscripcion.getDataProvider().setFilter(lstTipoSuscripcion.txtBuscar.getText());
		gridTipoSuscripcion.getDataProvider().refresh();
	}

	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource().equals(btnCerrar)){
			CerrarForm();
		}else if(event.getSource().equals(btnOperacion)){
			if(this.modo.equalsIgnoreCase(MODOINSERTAR)){
				processInsertar();
			}else if(this.modo.equalsIgnoreCase(MODOUPDATE)){
				processActualizar();
			}else if(this.modo.equalsIgnoreCase(MODODELETE)){
				processEliminar();
			}else if(this.modo.equalsIgnoreCase(MODODETALLE)){
				processDetalle();
			}else if(this.modo.equalsIgnoreCase(UiTarifario.MODODESACTIVAR)){
				processDesactivar();
			}
		}
	}

}
