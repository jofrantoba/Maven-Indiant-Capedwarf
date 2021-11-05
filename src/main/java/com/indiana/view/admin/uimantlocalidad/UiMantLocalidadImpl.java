package com.indiana.view.admin.uimantlocalidad;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Localidad;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.Region;
import com.indiana.shared.ListFilterBean;
import com.indiana.view.admin.uilocalidad.UiLocalidadImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantLocalidadImpl extends UiMantLocalidad{
	private UiLocalidadImpl uiPadre;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantLocalidadImpl(UiLocalidadImpl uiPadre){		
		this.uiPadre = uiPadre;		
		}
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Localidad bean=new Localidad();
			//bean.setIdLocalidad(txtId.getText().trim().toUpperCase());
			//bean.setCodeLocalidad(txtId.getText().trim().toUpperCase());
			bean.setNombre(txtNombre.getText().trim().toUpperCase());						
			bean.setBeanPais(this.beanPais);
			bean.setCodePais(this.beanPais.getCodePais());
			bean.setNombrePais(this.beanPais.getNombre());
			bean.setBeanRegion(this.beanRegion);
			bean.setCodeRegion(this.beanRegion.getCodeRegion());
			bean.setNombreRegion(this.beanRegion.getNombre());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertLocalidad(bean, new AsyncCallback<Localidad>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Localidad result) {
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
			Localidad bean=new Localidad();
			bean.setIdLocalidad(this.bean.getIdLocalidad());
			bean.setCodeLocalidad(this.bean.getCodeLocalidad());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombre(txtNombre.getText().trim().toUpperCase());					
			bean.setBeanPais(this.beanPais);
			bean.setBeanRegion(this.beanRegion);
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateLocalidad(bean, new AsyncCallback<Localidad>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Localidad result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiLocalidad();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Localidad bean=new Localidad();
			bean.setIdLocalidad(this.bean.getIdLocalidad());
			bean.setCodeLocalidad(this.bean.getCodeLocalidad());
			bean.setIsPersistente(false);
			bean.setNombre(this.bean.getNombre());				
			bean.setBeanPais(this.bean.getBeanPais());
			bean.setBeanRegion(this.bean.getBeanRegion());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateLocalidad(bean, new AsyncCallback<Localidad>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Localidad result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiLocalidad();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiLocalidad() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(Localidad bean) {
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
	
	@Override
	public void loadRegion() {
		// TODO Auto-generated method stub
		if(this.beanPais!=null){
			ListFilterBean filter=new ListFilterBean();
			filter.setNameQuery(Region.LISTAREGIONXPAIS);
			HashMap<String,Object> paramFilter=new HashMap<String,Object>(); 
			paramFilter.put("codePais", beanPais.getCodePais());
			filter.setParamFilter(paramFilter);
			SERVICE.listRegion(filter,new AsyncCallback<List<Region>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(List<Region> result) {
					// TODO Auto-generated method stub
					gridRegion.setData(result);				
					gridRegion.removeColumn(gridRegion.code);
					gridRegion.removeColumn(gridRegion.pais);	
					gridRegion.removeColumn(gridRegion.generador);	
				}
				
			});
		}else{
			MaterialToast.fireToast("Seleccione pais");
		}
		
	}
}
