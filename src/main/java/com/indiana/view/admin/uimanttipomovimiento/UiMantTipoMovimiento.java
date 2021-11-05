package com.indiana.view.admin.uimanttipomovimiento;

import java.util.Iterator;

import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uitipomovimiento.UiTipoMovimientoImpl;
import com.indiana.view.uiutil.JComboBox;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoMovimiento extends UiMantenimiento implements InterUiMantTipoMovimiento{
	protected UiTipoMovimientoImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblTipo;
	protected JComboBox lstTipo;
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	private MaterialLabel lblEstado;
	protected MaterialSwitch swtEstado;
	protected TipoMovimiento bean;
	
	public UiMantTipoMovimiento(){
		initComponents();	
	}
	
	public UiMantTipoMovimiento(UiTipoMovimientoImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("Tipo Movimiento");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblTipo=new MaterialLabel("TIPO");
		lstTipo=new JComboBox();		
		lblDescripcion=new MaterialLabel("DESCRIPCION");
		txtDescripcion=new MaterialTextBox();
		lblEstado=new MaterialLabel("ESTADO");
		swtEstado=new MaterialSwitch();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblTipo);
		this.addWidget(1, 1, lstTipo);
		this.addWidget(2, 0, lblDescripcion);
		this.addWidget(2, 1, txtDescripcion);
		this.addWidget(3, 0, lblEstado);
		this.addWidget(3, 1, swtEstado);		
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		loadTipo();
	}	

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoMovimiento());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);     
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);            
            swtEstado.setEnabled(true);  
            lstTipo.listBox.setSelectedIndex(this.bean.getTipo().equalsIgnoreCase("N")?0:this.bean.getTipo().equalsIgnoreCase("E")?1:2);
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).removeAttribute("disabled");
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).setAttribute("value", lstTipo.listBox.getSelectedItemText());
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoMovimiento());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);  
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);    
            lstTipo.listBox.setSelectedIndex(this.bean.getTipo().equalsIgnoreCase("N")?0:this.bean.getTipo().equalsIgnoreCase("E")?1:2);
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).setAttribute("disabled", "disabled");
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).setAttribute("value", lstTipo.listBox.getSelectedItemText());
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeTipoMovimiento().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);   
            lstTipo.listBox.setSelectedIndex(this.bean.getTipo().equalsIgnoreCase("N")?0:this.bean.getTipo().equalsIgnoreCase("E")?1:2);
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).setAttribute("disabled", "disabled");
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).setAttribute("value", lstTipo.listBox.getSelectedItemText());
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(true);                       
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
            swtEstado.setValue(false);
            swtEstado.setEnabled(true);    
            ((com.google.gwt.user.client.Element)lstTipo.getElement().getChild(0).getChild(1)).removeAttribute("disabled");;
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
	public void goToUiTipoMovimiento() {
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
	public void updateDataGrid(TipoMovimiento bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(TipoMovimiento bean) {
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
		Iterator<TipoMovimiento> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			TipoMovimiento bean=iterador.next();
			if(bean.getCodeTipoMovimiento().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}

	@Override
	public void loadTipo() {
		// TODO Auto-generated method stub
		lstTipo.addItem("NEUTRAL", "N");
		lstTipo.addItem("ENTRADA", "E");
		lstTipo.addItem("SALIDA", "S");
	}
}
