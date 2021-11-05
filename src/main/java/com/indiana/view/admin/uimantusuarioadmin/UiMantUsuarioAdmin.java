package com.indiana.view.admin.uimantusuarioadmin;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.grid.GridEstadoCuenta;
import com.indiana.view.admin.uiusuarioadmin.UiUsuarioAdminImpl;
import com.indiana.view.uiutil.UiMantenimiento;
import com.indiana.view.uiutil.UiSelectOption;

import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantUsuarioAdmin extends UiMantenimiento implements InterUiMantUsuarioAdmin{
	protected UiUsuarioAdminImpl uiPadre;
	private MaterialLabel lblNombre;
	protected MaterialTextBox txtNombre;		
	private MaterialLabel lblApellido;
	protected MaterialTextBox txtApellido;
	private MaterialLabel lblDni;
	protected MaterialTextBox txtDni;
	private MaterialLabel lblTelefono;
	protected MaterialTextBox txtTelefono;
	private MaterialLabel lblCorreo;
	protected MaterialTextBox txtCorreo;
	private MaterialLabel lblClave;
	protected MaterialTextBox txtClave;
	protected UsuarioAdmin bean;
	private MaterialLabel lblEstado;
	protected UiSelectOption lstEstadoCuenta;
	protected GridEstadoCuenta gridEstadoCuenta;
	protected EstadoCuenta beanEstadoCuenta;
	
	public UiMantUsuarioAdmin(){
		initComponents();	
		initListener();
	}
	
	public UiMantUsuarioAdmin(UiUsuarioAdminImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();
		initListener();
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Administradores");				
		lblNombre=new MaterialLabel("NOMBRES");
		txtNombre=new MaterialTextBox();
		lblApellido=new MaterialLabel("APELLIDOS");
		txtApellido=new MaterialTextBox();
		txtDni=new MaterialTextBox();
		lblDni=new MaterialLabel("DNI");
		txtTelefono=new MaterialTextBox();
		lblTelefono=new MaterialLabel("TELEFONO");
		txtCorreo=new MaterialTextBox();
		lblCorreo=new MaterialLabel("CORREO");
		txtCorreo.setType(InputType.EMAIL);
		txtClave=new MaterialTextBox();
		txtClave.setType(InputType.PASSWORD);
		lblClave=new MaterialLabel("CLAVE");
		lblEstado=new MaterialLabel("ESTADO");
		gridEstadoCuenta=new GridEstadoCuenta();
		lstEstadoCuenta=new UiSelectOption(gridEstadoCuenta);
		this.addWidget(0, 0, lblEstado);
		this.addWidget(0, 1, lstEstadoCuenta,3);
		this.addWidget(1, 0, lblNombre);
		this.addWidget(1, 1, txtNombre);
		this.addWidget(1, 2, lblApellido);
		this.addWidget(1, 3, txtApellido);
		this.addWidget(2, 0, lblDni);
		this.addWidget(2, 1, txtDni);
		this.addWidget(2, 2, lblTelefono);
		this.addWidget(2, 3, txtTelefono);
		this.addWidget(3, 0, lblCorreo);
		this.addWidget(3, 1, txtCorreo);
		this.addWidget(3, 2, lblClave);
		this.addWidget(3, 3, txtClave);							
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}	
	
	private void initListener(){
		lstEstadoCuenta.btnCombo.addClickHandler(clickKandler);
		lstEstadoCuenta.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionEstadoCuenta();
			}
			
		});
		lstEstadoCuenta.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				showSelectOptionEstadoCuenta();
			}

		
		});
		lstEstadoCuenta.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				buscarEstadoCuenta();
			}

		
		});
		gridEstadoCuenta.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beanEstadoCuenta=gridEstadoCuenta.getSelectionModel().getSelectedObject();
				if(beanEstadoCuenta!=null){
					lstEstadoCuenta.txtInput.setText(beanEstadoCuenta.getDescripcion());
					lstEstadoCuenta.simplePopup.hide();
				}
			}});
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			beanEstadoCuenta=this.bean.getBeanCuentaAdmin().getEstadoCuenta();
			lstEstadoCuenta.txtInput.setText(beanEstadoCuenta.getDescripcion());
			lstEstadoCuenta.txtInput.setEnabled(true);
			lstEstadoCuenta.btnCombo.setEnabled(true);
			lstEstadoCuenta.setVisible(true);
			lblEstado.setVisible(true);
			txtNombre.setEnabled(true);
            txtNombre.setFocus(true);
        	txtApellido.setVisible(true);
        	txtApellido.setEnabled(true);            
        	txtDni.setVisible(true);
        	txtDni.setEnabled(true);
        	txtTelefono.setEnabled(true);
        	txtCorreo.setVisible(true);
        	txtCorreo.setEnabled(false);
        	txtClave.setVisible(true);
        	txtClave.setEnabled(false);
            txtApellido.setText(this.bean.getApellido());
            txtNombre.setText(this.bean.getNombre());
            txtDni.setText(this.bean.getDni());
            txtTelefono.setText(this.bean.getTelefono());
            txtCorreo.setText(this.bean.getCorreo());
            txtClave.setText(this.bean.getBeanCuentaAdmin().getClave());
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	beanEstadoCuenta=this.bean.getBeanCuentaAdmin().getEstadoCuenta();
			lstEstadoCuenta.txtInput.setText(beanEstadoCuenta.getDescripcion());
			lstEstadoCuenta.txtInput.setEnabled(false);
			lstEstadoCuenta.btnCombo.setEnabled(false);
			lstEstadoCuenta.setVisible(true);
			lblEstado.setVisible(true);
        	txtNombre.setEnabled(false); 
        	txtNombre.setVisible(true);        	
        	txtApellido.setEnabled(false);
        	txtApellido.setVisible(true);
        	txtDni.setEnabled(false);
        	txtDni.setVisible(true);
        	txtTelefono.setEnabled(false);
        	txtTelefono.setVisible(true);
        	txtCorreo.setVisible(true);
        	txtCorreo.setEnabled(false);
        	txtClave.setVisible(true);
        	txtClave.setEnabled(false); 
            txtApellido.setText(this.bean.getApellido());
            txtNombre.setText(this.bean.getNombre());
            txtDni.setText(this.bean.getDni());
            txtTelefono.setText(this.bean.getTelefono());
            txtCorreo.setText(this.bean.getCorreo());
            txtClave.setText(this.bean.getBeanCuentaAdmin().getClave());
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	beanEstadoCuenta=this.bean.getBeanCuentaAdmin().getEstadoCuenta();
			lstEstadoCuenta.txtInput.setText(beanEstadoCuenta.getDescripcion());
			lstEstadoCuenta.txtInput.setEnabled(false);
			lstEstadoCuenta.btnCombo.setEnabled(false);
			lstEstadoCuenta.setVisible(true);
			lblEstado.setVisible(true);
        	txtNombre.setEnabled(false); 
        	txtNombre.setVisible(true);        	
        	txtApellido.setEnabled(false);
        	txtApellido.setVisible(true);
        	txtDni.setEnabled(false);
        	txtDni.setVisible(true);
        	txtTelefono.setEnabled(false);
        	txtTelefono.setVisible(true);
        	txtCorreo.setVisible(true);
        	txtCorreo.setEnabled(false);
        	txtClave.setVisible(true);
        	txtClave.setEnabled(false);
            txtApellido.setText(this.bean.getApellido());
            txtNombre.setText(this.bean.getNombre());
            txtDni.setText(this.bean.getDni());
            txtTelefono.setText(this.bean.getTelefono());
            txtCorreo.setText(this.bean.getCorreo());
            txtClave.setText(this.bean.getBeanCuentaAdmin().getClave());
            this.btnOperacion.setEnabled(false);
        } else {
        	beanEstadoCuenta=null;
        	lstEstadoCuenta.txtInput.setEnabled(false);
			lstEstadoCuenta.btnCombo.setEnabled(false);
			lstEstadoCuenta.setVisible(false);
			lblEstado.setVisible(false);
			txtNombre.setEnabled(true);
            txtNombre.setFocus(true);
        	txtApellido.setVisible(true);
        	txtApellido.setEnabled(true);            
        	txtDni.setVisible(true);
        	txtDni.setEnabled(true);
        	txtTelefono.setEnabled(true);
        	txtCorreo.setVisible(true);
        	txtCorreo.setEnabled(true);
        	txtClave.setVisible(true);
        	txtClave.setEnabled(true);
            cleanForm();
            this.btnOperacion.setEnabled(true);
        }
		clearError();
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtApellido.clear();
		txtNombre.clear();
		txtDni.clear();
		txtTelefono.clear();
		txtCorreo.clear();
		txtClave.clear();
		lstEstadoCuenta.txtInput.setText(null);
		lstEstadoCuenta.txtBuscar.setText("");
        gridEstadoCuenta.getSelectionModel().clear();
	}

	@Override
	public void goToUiUsuarioAdmin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){			
			if(FieldVerifier.isEmpty(txtNombre.getText())){
				txtNombre.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtApellido.getText())){
				txtApellido.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtDni.getText())){
				txtDni.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtTelefono.getText())){
				txtTelefono.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtCorreo.getText())){
				txtCorreo.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtClave.getText())){
				txtClave.setError("Campo obligatorio");
				return false;
			}else if(isExistInGrid()){
				MaterialToast.fireToast("ID existe");
				return false;
			}else{
				clearError();
				return true;
			}
		}else if(this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			if(this.beanEstadoCuenta==null){
				MaterialToast.fireToast("Seleccionar un Estado Cuentax");
				return false;
			}else if(FieldVerifier.isEmpty(lstEstadoCuenta.txtInput.getText())){
				MaterialToast.fireToast("Seleccionar un Estado Cuenta");
				return false;
			}else if(FieldVerifier.isEmpty(txtNombre.getText())){
				txtNombre.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtApellido.getText())){
				txtApellido.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtDni.getText())){
				txtDni.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtTelefono.getText())){
				txtTelefono.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtCorreo.getText())){
				txtCorreo.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtClave.getText())){
				txtClave.setError("Campo obligatorio");
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
	public void updateDataGrid(UsuarioAdmin bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(UsuarioAdmin bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtApellido.clearErrorOrSuccess();
		txtNombre.clearErrorOrSuccess();
		txtDni.clearErrorOrSuccess();
		txtTelefono.clearErrorOrSuccess();
		txtCorreo.clearErrorOrSuccess();
		txtClave.clearErrorOrSuccess();
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<UsuarioAdmin> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			UsuarioAdmin bean=iterador.next();
			if(bean.getCorreo().equalsIgnoreCase(txtCorreo.getText().trim())){
				return true;
			}
		}
		return false;
	}

	@Override
	public void loadEstadoCuenta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSelectOptionEstadoCuenta() {
		// TODO Auto-generated method stub
		loadEstadoCuenta();
		lstEstadoCuenta.txtInput.clear();
		lstEstadoCuenta.txtInput.clearErrorOrSuccess();
		lstEstadoCuenta.grid.setHeight("150px");
		lstEstadoCuenta.grid.setWidth("494px");
		lstEstadoCuenta.simplePopup.setWidth("494px");
		lstEstadoCuenta.simplePopup.setHeight("220px");
		gridEstadoCuenta.getSelectionModel().clear();
		lstEstadoCuenta.showPopup();
	}

	@Override
	public void buscarEstadoCuenta() {
		// TODO Auto-generated method stub
		gridEstadoCuenta.getDataProvider().setFilter(lstEstadoCuenta.txtBuscar.getText());
		gridEstadoCuenta.getDataProvider().refresh();
	}
	
	ClickHandler clickKandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub			
			if(event.getSource().equals(lstEstadoCuenta.btnCombo)){
				showSelectOptionEstadoCuenta();
			}else if(event.getSource().equals(lstEstadoCuenta.btnCombo)){
				showSelectOptionEstadoCuenta();
			}
		}
		
	};
}
