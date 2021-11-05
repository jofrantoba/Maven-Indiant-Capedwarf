package com.indiana.view.admin.uimantregion;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.Region;
import com.indiana.view.admin.uiregion.UiRegionImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantRegionImpl extends UiMantRegion{
	private UiRegionImpl uiPadre;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantRegionImpl(UiRegionImpl uiPadre){		
		this.uiPadre = uiPadre;		
		}
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Region bean=new Region();
			bean.setNombre(txtNombre.getText().trim().toUpperCase());
			bean.setGenerador("ADMINISTRADOR");
			bean.setBeanPais(this.beanPais);
			bean.setCodePais(this.beanPais.getCodePais());
			bean.setNombrePais(this.beanPais.getNombre());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertRegion(bean, new AsyncCallback<Region>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Region result) {
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
			Region bean=new Region();
			bean.setIdRegion(this.bean.getIdRegion());
			bean.setCodeRegion(this.bean.getCodeRegion());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombre(txtNombre.getText().trim().toUpperCase());					
			bean.setBeanPais(this.beanPais);
			bean.setGenerador(this.bean.getGenerador());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateRegion(bean, new AsyncCallback<Region>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Region result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiRegion();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Region bean=new Region();
			bean.setIdRegion(this.bean.getIdRegion());
			bean.setCodeRegion(this.bean.getCodeRegion());
			bean.setIsPersistente(false);
			bean.setNombre(this.bean.getNombre());				
			bean.setBeanPais(this.bean.getBeanPais());
			bean.setGenerador(this.bean.getGenerador());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateRegion(bean, new AsyncCallback<Region>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Region result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiRegion();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiRegion() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(Region bean) {
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
	
	@Override
	public void loadPais() {
		// TODO Auto-generated method stub
		SERVICE.listPais(new AsyncCallback<List<Pais>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Pais> result) {
				// TODO Auto-generated method stub
				gridPais.setData(result);
				gridPais.removeColumn(gridPais.code);
				gridPais.removeColumn(gridPais.urlico);
				gridPais.removeColumn(gridPais.generador);	
			}
			
		});
	}
}
