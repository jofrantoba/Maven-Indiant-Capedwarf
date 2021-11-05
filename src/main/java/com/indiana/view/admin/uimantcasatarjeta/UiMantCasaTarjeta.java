package com.indiana.view.admin.uimantcasatarjeta;

import java.util.Iterator;

import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uicasatarjeta.UiCasaTarjetaImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantCasaTarjeta extends UiMantenimiento implements InterUiMantCasaTarjeta{
	protected UiCasaTarjetaImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	protected CasaTarjeta bean;
	
	public UiMantCasaTarjeta(){
		initComponents();
	}
	public UiMantCasaTarjeta(UiCasaTarjetaImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
	}
	private void initComponents(){
		this.CardContTitulo.setText("Casa Tarjeta");
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblDescripcion=new MaterialLabel("DESCRIPCION");
		txtDescripcion=new MaterialTextBox();		
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblDescripcion);
		this.addWidget(1, 1, txtDescripcion);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}
	
	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeCasaTarjeta());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);            
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeCasaTarjeta());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeCasaTarjeta().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(true);                       
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
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
	}

	@Override
	public void goToUiCasaTarjeta() {
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
	public void updateDataGrid(CasaTarjeta bean) {
		// TODO Auto-generated method stub
		
	}

	public void setBean(CasaTarjeta bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.setError(null);
		txtDescripcion.setError(null);
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<CasaTarjeta> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			CasaTarjeta bean=iterador.next();
			if(bean.getCodeCasaTarjeta().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}
	
	
	
}
