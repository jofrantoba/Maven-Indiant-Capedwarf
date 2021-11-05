package com.indiana.view.admin.uimantformapago;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.FormaPago;
import com.indiana.view.admin.uiformapago.UiFormaPagoImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantFormaPagoImpl extends  UiMantFormaPago{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantFormaPagoImpl(UiFormaPagoImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			FormaPago bean=new FormaPago();
			bean.setIdFormaPago(txtId.getText().trim().toUpperCase());
			bean.setCodeFormaPago(txtId.getText().trim().toUpperCase());
			bean.setTipo(txtTipo.getText().trim().toUpperCase());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertFormaPago(bean, new AsyncCallback<FormaPago>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(FormaPago result) {
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
			FormaPago bean=new FormaPago();
			bean.setIdFormaPago(this.bean.getIdFormaPago());
			bean.setCodeFormaPago(this.bean.getCodeFormaPago());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setTipo(txtTipo.getText().trim().toUpperCase());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateFormaPago(bean, new AsyncCallback<FormaPago>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(FormaPago result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiFormaPago();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			FormaPago bean=new FormaPago();
			bean.setIdFormaPago(this.bean.getIdFormaPago());
			bean.setCodeFormaPago(this.bean.getCodeFormaPago());
			bean.setIsPersistente(false);
			bean.setTipo(this.bean.getTipo());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateFormaPago(bean, new AsyncCallback<FormaPago>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(FormaPago result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiFormaPago();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiFormaPago() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(FormaPago bean) {
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
