package com.indiana.view.admin.uitarifario;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Tarifario;
import com.indiana.view.admin.uimanttarifario.UiMantTarifario;
import com.indiana.view.admin.uimanttarifario.UiMantTarifarioImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;


public class UiTarifarioImpl extends UiTarifario{
	private UiMantTarifario ui;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT
			.create(ServiceGestionMantenimiento.class);
	
	public UiTarifarioImpl(){
		loadTable();
	}
	
	@Override
    public void loadTable() {
		SERVICE.listTarifario(new AsyncCallback<List<Tarifario>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Tarifario> result) {
				// TODO Auto-generated method stub
				grid.getSelectionModel().clear();
				grid.setData(result);
			}});
	}
	
	public void goToUiMantTarifarioInsertar() {
		// TODO Auto-generated method stub
		if (ui == null) {
			ui = new UiMantTarifarioImpl(this);
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
	public void goToUiMantTarifarioActualizar() {
		// TODO Auto-generated method stub
		Tarifario bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstado().equalsIgnoreCase("A") ) {
			if (ui == null) {
				ui = new UiMantTarifarioImpl(this);
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
			MaterialToast.fireToast("Seleccione un tarifario activo");
		}
	}

	@Override
	public void goToUiMantTarifarioEliminar() {
		// TODO Auto-generated method stub
		Tarifario bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTarifarioImpl(this);
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
	public void goToUiMantTarifarioDetalle() {
		// TODO Auto-generated method stub
		Tarifario bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantTarifarioImpl(this);
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
	
	@Override
	public void gotToUiMantarifarioDesactivar() {
		// TODO Auto-generated method stub
		Tarifario bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstado().equalsIgnoreCase("A")) {
			if (ui == null) {
				ui = new UiMantTarifarioImpl(this);
				ui.setModo(UiTarifario.MODODESACTIVAR);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			} else {
				ui.setModo(UiTarifario.MODODESACTIVAR);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione un Tarifario Activo");
		}
	}
}
