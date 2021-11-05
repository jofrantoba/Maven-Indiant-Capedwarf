package com.indiana.view.admin.uitipomovimiento;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.view.admin.uimanttipomovimiento.UiMantTipoMovimiento;
import com.indiana.view.admin.uimanttipomovimiento.UiMantTipoMovimientoImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;


public class UiTipoMovimientoImpl extends UiTipoMovimiento{
	private UiMantTipoMovimiento ui;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT
			.create(ServiceGestionMantenimiento.class);
	
	public UiTipoMovimientoImpl(){
		loadTable();
	}
	
	@Override
    public void loadTable() {
		SERVICE.listTipoMovimiento(new AsyncCallback<List<TipoMovimiento>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<TipoMovimiento> result) {
				// TODO Auto-generated method stub
				grid.getSelectionModel().clear();
				grid.setData(result);
			}});
	}
	
	@Override
	public void goToUiMantTipoMovimientoInsertar() {
		// TODO Auto-generated method stub
		if (ui == null) {
			ui = new UiMantTipoMovimientoImpl(this);
			ui.setModo(UiMantenimiento.MODOINSERTAR);
			ui.loadFields();
			ui.show();
		} else {
			ui.setModo(UiMantenimiento.MODOINSERTAR);
			ui.loadFields();
			ui.show();
		}
	}

	@Override
	public void goToUiMantTipoMovimientoActualizar() {
		// TODO Auto-generated method stub
		TipoMovimiento bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTipoMovimientoImpl(this);
				ui.setModo(UiMantenimiento.MODOUPDATE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			} else {
				ui.setModo(UiMantenimiento.MODOUPDATE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione una fila del grid");
		}
	}

	@Override
	public void goToUiMantTipoMovimientoEliminar() {
		// TODO Auto-generated method stub
		TipoMovimiento bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTipoMovimientoImpl(this);
				ui.setModo(UiMantenimiento.MODODELETE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			} else {
				ui.setModo(UiMantenimiento.MODODELETE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione una fila del grid");
		}
	}

	@Override
	public void goToUiMantTipoMovimientoDetalle() {
		// TODO Auto-generated method stub
		TipoMovimiento bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTipoMovimientoImpl(this);
				ui.setModo(UiMantenimiento.MODODETALLE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			} else {
				ui.setModo(UiMantenimiento.MODODETALLE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione una fila del grid");
		}
	}
}

