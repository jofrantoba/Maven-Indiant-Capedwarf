package com.indiana.view.admin.uimanttipointerpublicidad;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.TipoInterPublicidad;
import com.indiana.view.admin.uitipointerpubl.UiTipoInterPublImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoInterPublicidadImpl extends  UiMantTipoInterPublicidad{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantTipoInterPublicidadImpl(UiTipoInterPublImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoInterPublicidad bean=new TipoInterPublicidad();
			bean.setIdTipoInterPublicidad(txtId.getText().trim().toUpperCase());
			bean.setCodeTipoInterPublicidad(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertTipoInterPublicidad(bean, new AsyncCallback<TipoInterPublicidad>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoInterPublicidad result) {
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
			TipoInterPublicidad bean=new TipoInterPublicidad();
			bean.setIdTipoInterPublicidad(this.bean.getIdTipoInterPublicidad());
			bean.setCodeTipoInterPublicidad(this.bean.getCodeTipoInterPublicidad());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoInterPublicidad(bean, new AsyncCallback<TipoInterPublicidad>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoInterPublicidad result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiTipoInterPublicidad();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoInterPublicidad bean=new TipoInterPublicidad();
			bean.setIdTipoInterPublicidad(this.bean.getIdTipoInterPublicidad());
			bean.setCodeTipoInterPublicidad(this.bean.getCodeTipoInterPublicidad());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoInterPublicidad(bean, new AsyncCallback<TipoInterPublicidad>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoInterPublicidad result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiTipoInterPublicidad();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiTipoInterPublicidad() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(TipoInterPublicidad bean) {
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
