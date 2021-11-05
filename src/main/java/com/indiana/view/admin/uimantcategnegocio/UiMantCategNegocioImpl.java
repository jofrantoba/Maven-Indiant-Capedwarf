package com.indiana.view.admin.uimantcategnegocio;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.CategoriaNegocio;
import com.indiana.view.admin.uicategnegocio.UiCategoriaNegocioImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantCategNegocioImpl extends UiMantCategNegocio{
	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantCategNegocioImpl(UiCategoriaNegocioImpl uiPadre){
		super(uiPadre);
		}
	
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			CategoriaNegocio bean=new CategoriaNegocio();			
			bean.setIdCategoriaNegocio(txtId.getText().trim().toUpperCase());
			bean.setCodeCategoriaNegocio(txtId.getText().trim().toUpperCase());
			bean.setNombre(txtDescripcion.getText().trim().toUpperCase());
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertCategoriaNegocio(bean, new AsyncCallback<CategoriaNegocio>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CategoriaNegocio result) {
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
			CategoriaNegocio bean=new CategoriaNegocio();
			bean.setIdCategoriaNegocio(this.bean.getIdCategoriaNegocio());
			bean.setCodeCategoriaNegocio(this.bean.getCodeCategoriaNegocio());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombre(txtDescripcion.getText().trim().toUpperCase());	
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateCategoriaNegocio(bean, new AsyncCallback<CategoriaNegocio>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CategoriaNegocio result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiCategoriaNegocio();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			CategoriaNegocio bean=new CategoriaNegocio();
			bean.setIdCategoriaNegocio(this.bean.getIdCategoriaNegocio());
			bean.setCodeCategoriaNegocio(this.bean.getCodeCategoriaNegocio());
			bean.setIsPersistente(false);
			bean.setNombre(this.bean.getNombre());	
			bean.setEstado(this.bean.getEstado());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateCategoriaNegocio(bean, new AsyncCallback<CategoriaNegocio>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(CategoriaNegocio result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiCategoriaNegocio();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiCategoriaNegocio() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(CategoriaNegocio bean) {
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
