package com.indiana.view.admin.uimanttiponotificacion;

import java.util.Iterator;

import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uitiponotificacion.UiTipoNotificacionImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoNotificacion extends UiMantenimiento implements InterUiMantTipoNotificacion{
	protected UiTipoNotificacionImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	private MaterialLabel lblMensaje;
	protected MaterialTextArea txtMensaje;
	protected TipoNotificacion bean;
	
	public UiMantTipoNotificacion(){
		initComponents();	
	}
	
	public UiMantTipoNotificacion(UiTipoNotificacionImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
		reCalcularWindows();
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Tipo Notificacion");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblDescripcion=new MaterialLabel("DESCRIPCION");
		txtDescripcion=new MaterialTextBox();
		lblMensaje=new MaterialLabel("MENSAJE");
		txtMensaje=new MaterialTextArea();		
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblDescripcion);
		this.addWidget(1, 1, txtDescripcion);
		this.addWidget(2, 0, lblMensaje);
		this.addWidget(2, 1, txtMensaje);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}	
	
	private void reCalcularWindows() {        
        this.pnlScroll.setSize("600px","300px");       
        this.center();
    }

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoNotificacion());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);  
            txtMensaje.setText(this.bean.getMensaje());
            txtMensaje.setEnabled(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoNotificacion());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtMensaje.setText(this.bean.getMensaje());
            txtMensaje.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoNotificacion().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtMensaje.setText(this.bean.getMensaje());
            txtMensaje.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(true);                       
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);            
            txtMensaje.setEnabled(true);
            cleanForm();
            this.btnOperacion.setEnabled(true);
        }
		clearError();
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.clear();
		txtDescripcion.clear();
		txtMensaje.clear();
	}

	@Override
	public void goToUiTipoNotificacion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){			
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtDescripcion.getText())){
				txtDescripcion.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtMensaje.getText())){
				txtDescripcion.setError("Campo obligatorio");
				return false;
			}else if(isExistInGrid()){
				MaterialToast.fireToast("ID existe");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtDescripcion.clearErrorOrSuccess();
				return true;
			}
		}else if(this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtDescripcion.getText())){
				txtDescripcion.setError("Campo obligatorio");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtDescripcion.clearErrorOrSuccess();
				return true;
			}
		}else{
			return true;
		}
	}

	@Override
	public void updateDataGrid(TipoNotificacion bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(TipoNotificacion bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.clearErrorOrSuccess();
		txtDescripcion.clearErrorOrSuccess();
		txtMensaje.clearErrorOrSuccess();
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<TipoNotificacion> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			TipoNotificacion bean=iterador.next();
			if(bean.getCodeTipoNotificacion().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}
}
