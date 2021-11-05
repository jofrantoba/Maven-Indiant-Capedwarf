package com.indiana.view.admin.uimantparametros;

import java.util.Iterator;

import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uiparametros.UiParametrosImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantParametros extends UiMantenimiento implements InterUiMantParametros {
	protected UiParametrosImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;
	private MaterialLabel lblDescripcion;
	protected MaterialTextBox txtDescripcion;
	private MaterialLabel lblValor;
	protected MaterialTextBox txtValor;
	private MaterialLabel lblTipoDato;
	protected MaterialTextBox txtTipoDato;
	private MaterialLabel lblUnidad;
	protected MaterialTextBox txtUnidad;
	private MaterialLabel lblEstado;
	protected MaterialSwitch swtEstado;
	protected ParametrosSistema bean;

	public UiMantParametros() {
		initComponents();
		reCalcularWindows();
	}

	public UiMantParametros(UiParametrosImpl uiPadre) {
		this.uiPadre = uiPadre;
		initComponents();
		reCalcularWindows();
	}

	private void initComponents() {
		this.CardContTitulo.setText("Parametros del Sistema");
		lblId = new MaterialLabel("ID");
		txtId = new MaterialTextBox();
		lblDescripcion = new MaterialLabel("DESCRIPCION");
		txtDescripcion = new MaterialTextBox();
		lblValor = new MaterialLabel("VALOR");
		txtValor = new MaterialTextBox();
		lblTipoDato = new MaterialLabel("TIPO DATO");
		txtTipoDato = new MaterialTextBox();
		lblUnidad = new MaterialLabel("UNIDAD");
		txtUnidad = new MaterialTextBox();
		lblEstado=new MaterialLabel("ESTADO");
		swtEstado=new MaterialSwitch();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblDescripcion);
		this.addWidget(1, 1, txtDescripcion);
		this.addWidget(2, 0, lblTipoDato);
		this.addWidget(2, 1, txtTipoDato);
		this.addWidget(3, 0, lblValor);
		this.addWidget(3, 1, txtValor);
		this.addWidget(4, 0, lblUnidad);
		this.addWidget(4, 1, txtUnidad);
		this.addWidget(5, 0, lblEstado);
		this.addWidget(5, 1, swtEstado);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}
	
	private void reCalcularWindows() {        
        this.pnlScroll.setSize("600px","320px");       
        this.center();
    }

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
			txtId.setVisible(true);
			txtId.setText(this.bean.getCodeParametrosSistema());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(true);
			txtDescripcion.setFocus(true);
			swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(true);
            txtValor.setText(this.bean.getValor());
            txtValor.setEnabled(true);			
			txtTipoDato.setText(this.bean.getTipoDato());
			txtTipoDato.setEnabled(true);	
			txtUnidad.setText(this.bean.getUnidad());
			txtUnidad.setEnabled(true);			
			this.btnOperacion.setEnabled(true);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
			lblId.setVisible(true);
			txtId.setVisible(true);
			txtId.setText(this.bean.getCodeParametrosSistema());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(false);
			swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
            swtEstado.setEnabled(false);
            txtValor.setText(this.bean.getValor());
            txtValor.setEnabled(false);			
			txtTipoDato.setText(this.bean.getTipoDato());
			txtTipoDato.setEnabled(false);		
			txtUnidad.setText(this.bean.getUnidad());
			txtUnidad.setEnabled(false);			
			this.btnOperacion.setEnabled(true);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			lblId.setVisible(true);
			txtId.setVisible(true);
			txtId.setText(this.bean.getCodeParametrosSistema().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(false);
			swtEstado.setValue(this.bean.getEstado().equalsIgnoreCase("A")?true:false);
			swtEstado.setEnabled(false);
			txtValor.setText(this.bean.getValor());
            txtValor.setEnabled(false);			
			txtTipoDato.setText(this.bean.getTipoDato());
			txtTipoDato.setEnabled(false);
			txtUnidad.setText(this.bean.getUnidad());
			txtUnidad.setEnabled(false);			
			this.btnOperacion.setEnabled(false);
		} else {
			lblId.setVisible(true);
			txtId.setVisible(true);
			txtId.setEnabled(true);
			txtDescripcion.setFocus(true);
			txtDescripcion.setEnabled(true);
			swtEstado.setValue(false);
            swtEstado.setEnabled(true);
            txtValor.setEnabled(true);			
			txtTipoDato.setEnabled(true);			
			txtUnidad.setEnabled(true);			
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
		txtValor.clear();
		txtTipoDato.clear();
		txtUnidad.clear();
	}

	@Override
	public void goToUiParametros() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)) {
			if (FieldVerifier.isEmpty(txtId.getText())) {
				txtId.setError("Campo obligatorio");
				return false;
			} else if (FieldVerifier.isEmpty(txtDescripcion.getText())) {
				txtDescripcion.setError("Campo obligatorio");
				return false;
			} else if (FieldVerifier.isEmpty(txtValor.getText())) {
				txtValor.setError("Campo obligatorio");
				return false;
			}else if (FieldVerifier.isEmpty(txtTipoDato.getText())) {
				txtTipoDato.setError("Campo obligatorio");
				return false;
			}else if (isExistInGrid()) {
				MaterialToast.fireToast("ID existe");
				return false;
			} else {
				txtId.clearErrorOrSuccess();
				txtDescripcion.clearErrorOrSuccess();
				return true;
			}
		} else if (this.modo.equals(UiMantenimiento.MODOUPDATE) || this.modo.equals(UiMantenimiento.MODODELETE)) {
			if (FieldVerifier.isEmpty(txtId.getText())) {
				txtId.setError("Campo obligatorio");
				return false;
			} else if (FieldVerifier.isEmpty(txtDescripcion.getText())) {
				txtDescripcion.setError("Campo obligatorio");
				return false;
			}else if (FieldVerifier.isEmpty(txtValor.getText())) {
				txtValor.setError("Campo obligatorio");
				return false;
			}else if (FieldVerifier.isEmpty(txtTipoDato.getText())) {
				txtTipoDato.setError("Campo obligatorio");
				return false;
			} else {
				txtId.clearErrorOrSuccess();
				txtDescripcion.clearErrorOrSuccess();
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void updateDataGrid(ParametrosSistema bean) {
		// TODO Auto-generated method stub

	}

	public void setBean(ParametrosSistema bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.clearErrorOrSuccess();
		txtDescripcion.clearErrorOrSuccess();
		txtValor.clearErrorOrSuccess();
		txtTipoDato.clearErrorOrSuccess();
		txtUnidad.clearErrorOrSuccess();
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<ParametrosSistema> iterador = uiPadre.getGrid().getData().iterator();
		while (iterador.hasNext()) {
			ParametrosSistema bean = iterador.next();
			if (bean.getCodeParametrosSistema().equalsIgnoreCase(txtId.getText())) {
				return true;
			}
		}
		return false;
	}
}
