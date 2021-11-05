package com.indiana.view.admin.uitipoempatia;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.TipoEmpatia;
import com.indiana.view.admin.uimanttipoempatia.UiMantTipoEmpatia;
import com.indiana.view.admin.uimanttipoempatia.UiMantTipoEmpatiaImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;


public class UiTipoEmpatiaImpl extends UiTipoEmpatia{
	private UiMantTipoEmpatia ui;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT
			.create(ServiceGestionMantenimiento.class);
	
	public UiTipoEmpatiaImpl(){
		loadTable();
	}
	
	@Override
    public void loadTable() {
		SERVICE.listTipoEmpatia(new AsyncCallback<List<TipoEmpatia>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<TipoEmpatia> result) {
				// TODO Auto-generated method stub
				grid.getSelectionModel().clear();
				grid.setData(result);
			}});
	}
	
	@Override
	public void goToUiMantTipoEmpatiaInsertar() {
		// TODO Auto-generated method stub
		if (ui == null) {
			ui = new UiMantTipoEmpatiaImpl(this);
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
	public void goToUiMantTipoEmpatiaActualizar() {
		// TODO Auto-generated method stub
		TipoEmpatia bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTipoEmpatiaImpl(this);
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
	public void goToUiMantTipoEmpatiaEliminar() {
		// TODO Auto-generated method stub
		TipoEmpatia bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTipoEmpatiaImpl(this);
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
	public void goToUiMantTipoEmpatiaDetalle() {
		// TODO Auto-generated method stub
		TipoEmpatia bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTipoEmpatiaImpl(this);
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

