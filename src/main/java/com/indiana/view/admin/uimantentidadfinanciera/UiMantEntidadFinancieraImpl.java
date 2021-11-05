package com.indiana.view.admin.uimantentidadfinanciera;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.server.model.bean.Pais;
import com.indiana.view.admin.uientidadfinanciera.UiEntidadFinancieraImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantEntidadFinancieraImpl extends UiMantEntidadFinanciera{
	private UiEntidadFinancieraImpl uiPadre;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantEntidadFinancieraImpl(UiEntidadFinancieraImpl uiPadre){		
		this.uiPadre = uiPadre;		
		}
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			EntidadFinanciera bean=new EntidadFinanciera();
			bean.setIdEntidadFinanciera(txtId.getText().trim().toUpperCase());
			bean.setCodeEntidadFinanciera(txtId.getText().trim().toUpperCase());
			bean.setNombreEntidadFinanciera(txtNombre.getText().trim().toUpperCase());
			bean.setNumeroCuenta(txtCuenta.getText().trim().toUpperCase());				
			bean.setBeanPais(this.beanPais);
			bean.setCodePais(this.beanPais.getCodePais());
			bean.setNombrePais(this.beanPais.getNombre());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertEntidadFinanciera(bean, new AsyncCallback<EntidadFinanciera>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(EntidadFinanciera result) {
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
			EntidadFinanciera bean=new EntidadFinanciera();
			bean.setIdEntidadFinanciera(this.bean.getIdEntidadFinanciera());
			bean.setCodeEntidadFinanciera(this.bean.getCodeEntidadFinanciera());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombreEntidadFinanciera(txtNombre.getText().trim().toUpperCase());		
			bean.setNumeroCuenta(txtCuenta.getText().trim().toUpperCase());
			bean.setBeanPais(this.beanPais);
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateEntidadFinanciera(bean, new AsyncCallback<EntidadFinanciera>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(EntidadFinanciera result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiEntidadFinanciera();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			EntidadFinanciera bean=new EntidadFinanciera();
			bean.setIdEntidadFinanciera(this.bean.getIdEntidadFinanciera());
			bean.setCodeEntidadFinanciera(this.bean.getCodeEntidadFinanciera());
			bean.setIsPersistente(false);
			bean.setNombreEntidadFinanciera(this.bean.getNombreEntidadFinanciera());	
			bean.setNumeroCuenta(this.bean.getNumeroCuenta());
			bean.setBeanPais(this.bean.getBeanPais());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateEntidadFinanciera(bean, new AsyncCallback<EntidadFinanciera>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(EntidadFinanciera result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiEntidadFinanciera();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiEntidadFinanciera() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(EntidadFinanciera bean) {
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
