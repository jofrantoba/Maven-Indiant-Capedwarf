package com.indiana.view.admin.uimantpais;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Pais;
import com.indiana.view.admin.uipais.UiPaisImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantPaisImpl extends  UiMantPais{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantPaisImpl(UiPaisImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Pais bean=new Pais();
			bean.setCodePais(txtId.getText().trim().toUpperCase());
			bean.setNombre(txtDescripcion.getText().trim().toUpperCase());
			bean.setUrlicono(txtUrlIcono.getText().trim());
			bean.setGenerador("ADMINISTRADOR");
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertPais(bean, new AsyncCallback<Pais>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Pais result) {
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
			Pais bean=new Pais();
			bean.setIdPais(this.bean.getIdPais());
			bean.setCodePais(this.bean.getCodePais());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombre(txtDescripcion.getText().trim().toUpperCase());		
			bean.setUrlicono(txtUrlIcono.getText().trim());
			bean.setGenerador(this.bean.getGenerador());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updatePais(bean, new AsyncCallback<Pais>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Pais result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiPais();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Pais bean=new Pais();
			bean.setIdPais(this.bean.getIdPais());
			bean.setCodePais(this.bean.getCodePais());
			bean.setIsPersistente(false);
			bean.setNombre(this.bean.getNombre());
			bean.setUrlicono(this.bean.getUrlicono());
			bean.setOperacion("A");
			bean.setGenerador(this.bean.getGenerador());
			bean.setVersion((new Date()).getTime());
			SERVICE.updatePais(bean, new AsyncCallback<Pais>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Pais result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiPais();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiPais() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(Pais bean) {
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
