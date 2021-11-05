package com.indiana.view.admin.uientidadfinanciera;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.view.admin.uimantentidadfinanciera.UiMantEntidadFinanciera;
import com.indiana.view.admin.uimantentidadfinanciera.UiMantEntidadFinancieraImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;


public class UiEntidadFinancieraImpl extends UiEntidadFinanciera{
	private UiMantEntidadFinanciera ui;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT
			.create(ServiceGestionMantenimiento.class);

	public UiEntidadFinancieraImpl(){
		loadTable();
	}
	
	@Override
    public void loadTable() {
		SERVICE.listEntidadFinanciera(new AsyncCallback<List<EntidadFinanciera>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<EntidadFinanciera> result) {
				// TODO Auto-generated method stub
				grid.getSelectionModel().clear();
				grid.setData(result);
			}});
	}
	public void goToUiMantEntidadFinancieraInsertar() {
		// TODO Auto-generated method stub
		if (ui == null) {
			ui = new UiMantEntidadFinancieraImpl(this);
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
	public void goToUiMantEntidadFinancieraActualizar() {
		// TODO Auto-generated method stub
		EntidadFinanciera bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantEntidadFinancieraImpl(this);
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
	public void goToUiMantEntidadFinancieraEliminar() {
		// TODO Auto-generated method stub
		EntidadFinanciera bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantEntidadFinancieraImpl(this);
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
	public void goToUiMantEntidadFinancieraDetalle() {
		// TODO Auto-generated method stub
		EntidadFinanciera bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantEntidadFinancieraImpl(this);
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
