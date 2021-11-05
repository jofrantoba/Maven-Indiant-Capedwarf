package com.indiana.view.admin.uimantmoneda;

import java.util.Iterator;

import com.indiana.server.model.bean.Moneda;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uimoneda.UiMonedaImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantMoneda extends UiMantenimiento implements InterUiMantMoneda{
	protected UiMonedaImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	private MaterialLabel lblRepresentacion;
	protected MaterialTextBox txtRepresentacion;
	private MaterialLabel lblEstado;
	protected MaterialSwitch swtEstado;
	protected Moneda bean;
	
	public UiMantMoneda(){
		initComponents();	
	}
	
	public UiMantMoneda(UiMonedaImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Moneda");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblDescripcion=new MaterialLabel("DESCRIPCION");
		txtDescripcion=new MaterialTextBox();
		lblRepresentacion=new MaterialLabel("REPRESENTACION");
		txtRepresentacion=new MaterialTextBox();
		lblEstado=new MaterialLabel("ESTADO");
		swtEstado=new MaterialSwitch();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblDescripcion);
		this.addWidget(1, 1, txtDescripcion);
		this.addWidget(2, 0, lblRepresentacion);
		this.addWidget(2, 1, txtRepresentacion);
		this.addWidget(3, 0, lblEstado);
		this.addWidget(3, 1, swtEstado);
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
            txtId.setText(this.bean.getCodeMoneda());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);          
            txtRepresentacion.setText(this.bean.getRepresentacion());
            txtRepresentacion.setEnabled(true);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeMoneda());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);  
            txtRepresentacion.setText(this.bean.getRepresentacion());
            txtRepresentacion.setEnabled(false);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeMoneda().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtRepresentacion.setText(this.bean.getRepresentacion());
            txtRepresentacion.setEnabled(false);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(false);
        	txtId.setVisible(false);
            txtId.setEnabled(false);                       
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);            
            txtRepresentacion.setEnabled(true);            
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
		txtRepresentacion.clear();
	}

	@Override
	public void goToUiMoneda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){			
			if(FieldVerifier.isEmpty(txtDescripcion.getText())){
				txtDescripcion.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtRepresentacion.getText())){
				txtRepresentacion.setError("Campo obligatorio");
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
			}else if(FieldVerifier.isEmpty(txtRepresentacion.getText())){
				txtRepresentacion.setError("Campo obligatorio");
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
	public void updateDataGrid(Moneda bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(Moneda bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.clearErrorOrSuccess();
		txtDescripcion.clearErrorOrSuccess();
		txtRepresentacion.clearErrorOrSuccess();
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<Moneda> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			Moneda bean=iterador.next();
			if(bean.getCodeMoneda().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}
}
