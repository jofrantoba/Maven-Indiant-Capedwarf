package com.indiana.view.admin.uimantcasatarjeta;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.view.admin.uicasatarjeta.UiCasaTarjetaImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantCasaTarjetaImpl extends UiMantCasaTarjeta{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantCasaTarjetaImpl(UiCasaTarjetaImpl uiPadre){
		super(uiPadre);
	}
	
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			CasaTarjeta bean=new CasaTarjeta();
			bean.setIdCasaTarjeta(txtId.getText().trim().toUpperCase());
			bean.setCodeCasaTarjeta(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertCasaTarjeta(bean, new AsyncCallback<CasaTarjeta>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CasaTarjeta result) {
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
			CasaTarjeta bean=new CasaTarjeta();
			bean.setIdCasaTarjeta(this.bean.getIdCasaTarjeta());
			bean.setCodeCasaTarjeta(this.bean.getCodeCasaTarjeta());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateCasaTarjeta(bean, new AsyncCallback<CasaTarjeta>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CasaTarjeta result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiCasaTarjeta();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			CasaTarjeta bean=new CasaTarjeta();
			bean.setIdCasaTarjeta(this.bean.getIdCasaTarjeta());
			bean.setCodeCasaTarjeta(this.bean.getCodeCasaTarjeta());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateCasaTarjeta(bean, new AsyncCallback<CasaTarjeta>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CasaTarjeta result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiCasaTarjeta();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiCasaTarjeta() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(CasaTarjeta bean) {
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
