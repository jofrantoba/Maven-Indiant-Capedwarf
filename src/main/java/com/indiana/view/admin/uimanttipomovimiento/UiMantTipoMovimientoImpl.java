package com.indiana.view.admin.uimanttipomovimiento;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.view.admin.uitipomovimiento.UiTipoMovimientoImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoMovimientoImpl extends  UiMantTipoMovimiento{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantTipoMovimientoImpl(UiTipoMovimientoImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoMovimiento bean=new TipoMovimiento();
			bean.setIdTipoMovimiento(txtId.getText().trim().toUpperCase());
			bean.setCodeTipoMovimiento(txtId.getText().trim().toUpperCase());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setTipo(lstTipo.getSelectedValue());
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertTipoMovimiento(bean, new AsyncCallback<TipoMovimiento>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoMovimiento result) {
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
			TipoMovimiento bean=new TipoMovimiento();
			bean.setIdTipoMovimiento(this.bean.getIdTipoMovimiento());
			bean.setCodeTipoMovimiento(this.bean.getCodeTipoMovimiento());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
			bean.setTipo(lstTipo.getSelectedValue());
			bean.setEstado(swtEstado.getValue()?"A":"D");
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoMovimiento(bean, new AsyncCallback<TipoMovimiento>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoMovimiento result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiTipoMovimiento();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoMovimiento bean=new TipoMovimiento();
			bean.setIdTipoMovimiento(this.bean.getIdTipoMovimiento());
			bean.setCodeTipoMovimiento(this.bean.getCodeTipoMovimiento());
			bean.setIsPersistente(false);
			bean.setDescripcion(this.bean.getDescripcion());
			bean.setTipo(this.bean.getTipo());
			bean.setEstado(this.bean.getEstado());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoMovimiento(bean, new AsyncCallback<TipoMovimiento>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoMovimiento result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiTipoMovimiento();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiTipoMovimiento() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(TipoMovimiento bean) {
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
