package com.indiana.view.admin.uimantformapago;

import java.util.Iterator;

import com.indiana.server.model.bean.FormaPago;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uiformapago.UiFormaPagoImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantFormaPago extends UiMantenimiento implements InterUiMantFormaPago{
	protected UiFormaPagoImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblTipo;
	protected MaterialTextBox txtTipo;
	protected FormaPago bean;
	
	public UiMantFormaPago(){
		initComponents();	
	}
	
	public UiMantFormaPago(UiFormaPagoImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Forma de Pago");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblTipo=new MaterialLabel("Tipo");
		txtTipo=new MaterialTextBox();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblTipo);
		this.addWidget(1, 1, txtTipo);
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
            txtId.setText(this.bean.getCodeFormaPago());
            txtTipo.setText(this.bean.getTipo());
            txtId.setEnabled(false);
            txtTipo.setEnabled(true);
            txtTipo.setFocus(true);               
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeFormaPago());
            txtTipo.setText(this.bean.getTipo());
            txtId.setEnabled(false);
            txtTipo.setEnabled(false);            
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeFormaPago().toString());
            txtTipo.setText(this.bean.getTipo());
            txtId.setEnabled(false);
            txtTipo.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(true);                       
            txtTipo.setFocus(true);
            txtTipo.setEnabled(true);
            cleanForm();
            this.btnOperacion.setEnabled(true);
        }
		clearError();
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.clear();
		txtTipo.clear();
	}

	@Override
	public void goToUiFormaPago() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){			
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtTipo.getText())){
				txtTipo.setError("Campo obligatorio");
				return false;
			}else if(isExistInGrid()){
				MaterialToast.fireToast("ID existe");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtTipo.clearErrorOrSuccess();
				return true;
			}
		}else if(this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtTipo.getText())){
				txtTipo.setError("Campo obligatorio");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtTipo.clearErrorOrSuccess();
				return true;
			}
		}else{
			return true;
		}
	}

	@Override
	public void updateDataGrid(FormaPago bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(FormaPago bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.setError(null);
		txtTipo.setError(null);
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<FormaPago> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			FormaPago bean=iterador.next();
			if(bean.getCodeFormaPago().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}
}
