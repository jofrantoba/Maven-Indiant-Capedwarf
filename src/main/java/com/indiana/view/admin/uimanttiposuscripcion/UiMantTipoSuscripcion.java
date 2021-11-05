package com.indiana.view.admin.uimanttiposuscripcion;

import java.util.Iterator;

import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uitiposuscripcion.UiTipoSuscripcionImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoSuscripcion extends UiMantenimiento implements InterUiMantTipoSuscripcion{
	protected UiTipoSuscripcionImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	private MaterialLabel lblEstado;
	protected MaterialSwitch swtEstado;
	protected TipoSuscripcion bean;
	
	public UiMantTipoSuscripcion(){
		initComponents();	
	}
	
	public UiMantTipoSuscripcion(UiTipoSuscripcionImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Tipo Suscripcion");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblDescripcion=new MaterialLabel("DESCRIPCION");
		txtDescripcion=new MaterialTextBox();
		lblEstado=new MaterialLabel("ESTADO");
		swtEstado=new MaterialSwitch();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblDescripcion);
		this.addWidget(1, 1, txtDescripcion);
		this.addWidget(2, 0, lblEstado);
		this.addWidget(2, 1, swtEstado);
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
            txtId.setText(this.bean.getCodeTipoSuscripcion());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);    
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoSuscripcion());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);            
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoSuscripcion().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            swtEstado.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(true);                       
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
            swtEstado.setValue(false);
            swtEstado.setEnabled(true);
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
	public void goToUiTipoSuscripcion() {
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
	public void updateDataGrid(TipoSuscripcion bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(TipoSuscripcion bean) {
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
		Iterator<TipoSuscripcion> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			TipoSuscripcion bean=iterador.next();
			if(bean.getCodeTipoSuscripcion().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}
}
