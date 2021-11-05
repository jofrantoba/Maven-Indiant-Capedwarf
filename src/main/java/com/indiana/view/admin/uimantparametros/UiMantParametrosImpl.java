package com.indiana.view.admin.uimantparametros;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.view.admin.uiparametros.UiParametrosImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantParametrosImpl extends  UiMantParametros{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantParametrosImpl(UiParametrosImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			ParametrosSistema bean=new ParametrosSistema();
			bean.setIdParametrosSistema(txtId.getText().trim().toUpperCase());
			bean.setCodeParametrosSistema(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setTipoDato(txtTipoDato.getText().trim());
			bean.setValor(txtValor.getText().trim());
			bean.setUnidad(txtUnidad.getText().trim().toUpperCase());
			bean.setEstado(swtEstado.getValue()?"A":"D");			
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertParametrosSistema(bean, new AsyncCallback<ParametrosSistema>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(ParametrosSistema result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Insertado correctamente");
					updateDataGrid(result);
					cleanForm();
				}
				
			});
		}
	}

	@Override
	public void processActualizar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			ParametrosSistema bean=new ParametrosSistema();
			bean.setIdParametrosSistema(this.bean.getIdParametrosSistema());
			bean.setCodeParametrosSistema(this.bean.getCodeParametrosSistema());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());	
			bean.setTipoDato(txtTipoDato.getText().trim());
			bean.setValor(txtValor.getText().trim());
			bean.setUnidad(txtUnidad.getText().trim().toUpperCase());
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateParametrosSistema(bean, new AsyncCallback<ParametrosSistema>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(ParametrosSistema result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiParametros();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			ParametrosSistema bean=new ParametrosSistema();
			bean.setIdParametrosSistema(this.bean.getIdParametrosSistema());
			bean.setCodeParametrosSistema(this.bean.getCodeParametrosSistema());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());	
			bean.setTipoDato(this.bean.getTipoDato());
			bean.setValor(this.bean.getValor());
			bean.setUnidad(this.bean.getUnidad());
			bean.setEstado(this.bean.getEstado());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateParametrosSistema(bean, new AsyncCallback<ParametrosSistema>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(ParametrosSistema result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiParametros();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiParametros() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(ParametrosSistema bean) {
		// TODO Auto-generated method stub
		if(this.modo.equalsIgnoreCase(UiMantenimiento.MODOINSERTAR)){
			uiPadre.getGrid().getData().add(bean);
			uiPadre.getGrid().refreshGrid();
		}else if(this.modo.equalsIgnoreCase(UiMantenimiento.MODOUPDATE)){
			uiPadre.getGrid().getData().remove(this.bean);
			uiPadre.getGrid().getData().add(bean);
			uiPadre.getGrid().refreshGrid();
		}else if(this.modo.equalsIgnoreCase(UiMantenimiento.MODODELETE)){
			uiPadre.getGrid().getData().remove(this.bean);
			uiPadre.getGrid().refreshGrid();
		}
		
	}

}
