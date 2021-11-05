package com.indiana.view.admin.uiidioma;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Idioma;
import com.indiana.view.admin.uimantidioma.UiMantIdioma;
import com.indiana.view.admin.uimantidioma.UiMantIdiomaImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;


public class UiIdiomaImpl extends UiIdioma{
	private UiMantIdioma ui;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT
			.create(ServiceGestionMantenimiento.class);
	
	public UiIdiomaImpl(){
		loadTable();
	}
	
	@Override
    public void loadTable() {
		SERVICE.listIdioma(new AsyncCallback<List<Idioma>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Idioma> result) {
				// TODO Auto-generated method stub
				grid.getSelectionModel().clear();
				grid.setData(result);
			}});
	}

	@Override
	public void goToUiMantIdiomaInsertar() {
		// TODO Auto-generated method stub
		if (ui == null) {
			ui = new UiMantIdiomaImpl(this);
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
	public void goToUiMantIdiomaActualizar() {
		// TODO Auto-generated method stub
		Idioma bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantIdiomaImpl(this);
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
	public void goToUiMantIdiomaEliminar() {
		// TODO Auto-generated method stub
		Idioma bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantIdiomaImpl(this);
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
	public void goToUiMantIdiomaDetalle() {
		// TODO Auto-generated method stub
		Idioma bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantIdiomaImpl(this);
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

