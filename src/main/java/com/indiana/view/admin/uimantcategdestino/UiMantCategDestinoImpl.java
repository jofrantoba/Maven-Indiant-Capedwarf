package com.indiana.view.admin.uimantcategdestino;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.CategoriaDestino;
import com.indiana.view.admin.uicategdestino.UiCategoriaDestinoImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantCategDestinoImpl  extends UiMantCategDestino{	
	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantCategDestinoImpl(UiCategoriaDestinoImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			CategoriaDestino bean=new CategoriaDestino();
			bean.setIdCategoriaDestino(txtId.getText().trim().toUpperCase());
			bean.setCodeCategoriaDestino(txtId.getText().trim().toUpperCase());
			bean.setNombre(txtDescripcion.getText().trim().toUpperCase());
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertCategoriaDestino(bean, new AsyncCallback<CategoriaDestino>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CategoriaDestino result) {
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
			CategoriaDestino bean=new CategoriaDestino();
			bean.setIdCategoriaDestino(this.bean.getIdCategoriaDestino());
			bean.setCodeCategoriaDestino(this.bean.getCodeCategoriaDestino());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombre(txtDescripcion.getText().trim().toUpperCase());	
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateCategoriaDestino(bean, new AsyncCallback<CategoriaDestino>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CategoriaDestino result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiCategoriaDestino();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			CategoriaDestino bean=new CategoriaDestino();
			bean.setIdCategoriaDestino(this.bean.getIdCategoriaDestino());
			bean.setCodeCategoriaDestino(this.bean.getCodeCategoriaDestino());
			bean.setIsPersistente(false);
			bean.setNombre(this.bean.getNombre());	
			bean.setEstado(this.bean.getEstado());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateCategoriaDestino(bean, new AsyncCallback<CategoriaDestino>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CategoriaDestino result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiCategoriaDestino();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiCategoriaDestino() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(CategoriaDestino bean) {
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
