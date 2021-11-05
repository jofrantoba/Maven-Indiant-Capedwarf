package com.indiana.view.admin.uimantestadomiembro;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.EstadoMiembro;
import com.indiana.view.admin.uiestadomiembro.UiEstadoMiembroImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantEstadoMiembroImpl extends UiMantEstadoMiembro{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantEstadoMiembroImpl(UiEstadoMiembroImpl uiPadre){
		super(uiPadre);
	}
	
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			EstadoMiembro bean=new EstadoMiembro();
			bean.setIdEstadoMiembro(txtId.getText().trim().toUpperCase());
			bean.setCodeEstadoMiembro(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertEstadoMiembro(bean, new AsyncCallback<EstadoMiembro>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(EstadoMiembro result) {
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
			EstadoMiembro bean=new EstadoMiembro();
			bean.setIdEstadoMiembro(this.bean.getIdEstadoMiembro());
			bean.setCodeEstadoMiembro(this.bean.getCodeEstadoMiembro());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateEstadoMiembro(bean, new AsyncCallback<EstadoMiembro>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(EstadoMiembro result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiEstadoMiembro();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			EstadoMiembro bean=new EstadoMiembro();
			bean.setIdEstadoMiembro(this.bean.getIdEstadoMiembro());
			bean.setCodeEstadoMiembro(this.bean.getCodeEstadoMiembro());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());			
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateEstadoMiembro(bean, new AsyncCallback<EstadoMiembro>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(EstadoMiembro result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiEstadoMiembro();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiEstadoMiembro() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(EstadoMiembro bean) {
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
