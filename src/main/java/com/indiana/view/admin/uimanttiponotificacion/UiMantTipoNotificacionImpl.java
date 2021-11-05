package com.indiana.view.admin.uimanttiponotificacion;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.view.admin.uitiponotificacion.UiTipoNotificacionImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoNotificacionImpl extends  UiMantTipoNotificacion{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantTipoNotificacionImpl(UiTipoNotificacionImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoNotificacion bean=new TipoNotificacion();
			bean.setIdTipoNotificacion(txtId.getText().trim().toUpperCase());
			bean.setCodeTipoNotificacion(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setMensaje(txtMensaje.getText().trim());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertTipoNotificacion(bean, new AsyncCallback<TipoNotificacion>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoNotificacion result) {
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
			TipoNotificacion bean=new TipoNotificacion();
			bean.setIdTipoNotificacion(this.bean.getIdTipoNotificacion());
			bean.setCodeTipoNotificacion(this.bean.getCodeTipoNotificacion());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());	
			bean.setMensaje(txtMensaje.getText().trim());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoNotificacion(bean, new AsyncCallback<TipoNotificacion>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoNotificacion result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiTipoNotificacion();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoNotificacion bean=new TipoNotificacion();
			bean.setIdTipoNotificacion(this.bean.getIdTipoNotificacion());
			bean.setCodeTipoNotificacion(this.bean.getCodeTipoNotificacion());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());	
			bean.setMensaje(this.bean.getMensaje());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoNotificacion(bean, new AsyncCallback<TipoNotificacion>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoNotificacion result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiTipoNotificacion();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiTipoNotificacion() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(TipoNotificacion bean) {
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
