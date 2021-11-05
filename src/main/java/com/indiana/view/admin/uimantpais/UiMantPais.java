package com.indiana.view.admin.uimantpais;

import java.util.Iterator;

import com.indiana.server.model.bean.Pais;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uipais.UiPaisImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantPais extends UiMantenimiento implements InterUiMantPais{
	protected UiPaisImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	private MaterialLabel lblUrlIcono;
	protected MaterialTextBox txtUrlIcono;
	protected Pais bean;
	
	public UiMantPais(){
		initComponents();	
	}
	
	public UiMantPais(UiPaisImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Pais");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblDescripcion=new MaterialLabel("DESCRIPCION");
		txtDescripcion=new MaterialTextBox();
		lblUrlIcono=new MaterialLabel("URL ICONO");
		txtUrlIcono=new MaterialTextBox();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblDescripcion);
		this.addWidget(1, 1, txtDescripcion);
		this.addWidget(2, 0, lblUrlIcono);
		this.addWidget(2, 1, txtUrlIcono);
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
            txtId.setText(this.bean.getCodePais());
            txtDescripcion.setText(this.bean.getNombre());
            txtUrlIcono.setText(this.bean.getUrlicono());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtUrlIcono.setEnabled(true);
            txtDescripcion.setFocus(true);               
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodePais());
            txtDescripcion.setText(this.bean.getNombre());
            txtUrlIcono.setText(this.bean.getUrlicono());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false); 
            txtUrlIcono.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodePais().toString());
            txtDescripcion.setText(this.bean.getNombre());
            txtUrlIcono.setText(this.bean.getUrlicono());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtUrlIcono.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(false);
        	txtId.setVisible(false);
            txtId.setEnabled(false);                       
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
            txtUrlIcono.setEnabled(true);
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
		txtUrlIcono.clear();
	}

	@Override
	public void goToUiPais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){			
			if(FieldVerifier.isEmpty(txtDescripcion.getText())){
				txtDescripcion.setError("Campo obligatorio");
				return false;
			}else if(isExistInGrid()){
				MaterialToast.fireToast("El País existe en BD");
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
	public void updateDataGrid(Pais bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(Pais bean) {
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
		Iterator<Pais> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			Pais bean=iterador.next();
			if(bean.getNombre().equalsIgnoreCase(txtDescripcion.getText().trim())){
				return true;
			}
		}
		return false;
	}
	
}
