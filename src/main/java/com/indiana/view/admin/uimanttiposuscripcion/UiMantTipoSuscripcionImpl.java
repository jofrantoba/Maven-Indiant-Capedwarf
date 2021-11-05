package com.indiana.view.admin.uimanttiposuscripcion;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.view.admin.uitiposuscripcion.UiTipoSuscripcionImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoSuscripcionImpl extends  UiMantTipoSuscripcion{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantTipoSuscripcionImpl(UiTipoSuscripcionImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoSuscripcion bean=new TipoSuscripcion();
			bean.setIdTipoSuscripcion(txtId.getText().trim().toUpperCase());
			bean.setCodeTipoSuscripcion(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertTipoSuscripcion(bean, new AsyncCallback<TipoSuscripcion>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoSuscripcion result) {
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
			TipoSuscripcion bean=new TipoSuscripcion();
			bean.setIdTipoSuscripcion(this.bean.getIdTipoSuscripcion());
			bean.setCodeTipoSuscripcion(this.bean.getCodeTipoSuscripcion());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());		
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoSuscripcion(bean, new AsyncCallback<TipoSuscripcion>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoSuscripcion result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiTipoSuscripcion();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoSuscripcion bean=new TipoSuscripcion();
			bean.setIdTipoSuscripcion(this.bean.getIdTipoSuscripcion());
			bean.setCodeTipoSuscripcion(this.bean.getCodeTipoSuscripcion());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());
			bean.setEstado(this.bean.getEstado());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoSuscripcion(bean, new AsyncCallback<TipoSuscripcion>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoSuscripcion result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiTipoSuscripcion();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiTipoSuscripcion() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(TipoSuscripcion bean) {
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
