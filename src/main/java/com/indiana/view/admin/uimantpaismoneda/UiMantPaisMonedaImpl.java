package com.indiana.view.admin.uimantpaismoneda;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Moneda;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.shared.ListFilterBean;
import com.indiana.view.admin.uipaismoneda.UiPaisMonedaImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantPaisMonedaImpl extends UiMantPaisMoneda {
	private UiPaisMonedaImpl uiPadre;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);

	public UiMantPaisMonedaImpl(UiPaisMonedaImpl uiPadre) {
		this.uiPadre = uiPadre;
	}

	@Override
	public void processInsertar() {
		
		if (isValidData()) {
			PaisMoneda bean = new PaisMoneda();
			bean.setBeanPais(this.beanPais);
			bean.setCodePais(this.beanPais.getCodePais());
			bean.setNombrePais(this.beanPais.getNombre());
			bean.setBeanMoneda(this.beanMoneda);
			bean.setCodeMoneda(this.beanMoneda.getCodeMoneda());
			bean.setNombreMoneda(this.beanMoneda.getDescripcion());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertPaisMoneda(bean, new AsyncCallback<PaisMoneda>() {

				@Override
				public void onFailure(Throwable caught) {
					
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(PaisMoneda result) {
					
					MaterialToast.fireToast("Insertado correctamente");
					updateDataGrid(result);
					cleanForm();
				}

			});
		}
	}

	@Override
	public void processActualizar() {
		
		if (isValidData()) {
			PaisMoneda bean = new PaisMoneda();
			bean.setIdPaisMoneda(this.bean.getIdPaisMoneda());
			bean.setCodePaisMoneda(this.bean.getCodePaisMoneda());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setBeanPais(this.beanPais);
			bean.setBeanMoneda(this.beanMoneda);
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updatePaisMoneda(bean, new AsyncCallback<PaisMoneda>() {

				@Override
				public void onFailure(Throwable caught) {
					
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(PaisMoneda result) {
					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiPaisMoneda();
				}

			});
		}
	}

	@Override
	public void processEliminar() {
		
		if (isValidData()) {
			PaisMoneda bean = new PaisMoneda();
			bean.setIdPaisMoneda(this.bean.getIdPaisMoneda());
			bean.setCodePaisMoneda(this.bean.getCodePaisMoneda());
			bean.setIsPersistente(false);
			bean.setBeanPais(this.bean.getBeanPais());
			bean.setBeanMoneda(this.bean.getBeanMoneda());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updatePaisMoneda(bean, new AsyncCallback<PaisMoneda>() {

				@Override
				public void onFailure(Throwable caught) {
					
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(PaisMoneda result) {
					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiPaisMoneda();
				}

			});
		}
	}

	@Override
	public void processDetalle() {
		
	}

	@Override
	public void goToUiPaisMoneda() {
		
		cleanForm();
		this.hide();
	}

	@Override
	public void updateDataGrid(PaisMoneda bean) {
		
		if (this.modo.equalsIgnoreCase(UiMantenimiento.MODOINSERTAR)) {
			uiPadre.getGrid().getData().add(bean);
			uiPadre.getGrid().refreshGrid();
		} else if (this.modo.equalsIgnoreCase(UiMantenimiento.MODOUPDATE)) {
			uiPadre.getGrid().getData().remove(this.bean);
			uiPadre.getGrid().getData().add(bean);
			uiPadre.getGrid().refreshGrid();
		} else if (this.modo.equalsIgnoreCase(UiMantenimiento.MODODELETE)) {
			uiPadre.getGrid().getData().remove(this.bean);
			uiPadre.getGrid().refreshGrid();
		}

	}

	@Override
	public void loadPais() {
		
		SERVICE.listPais(new AsyncCallback<List<Pais>>() {

			@Override
			public void onFailure(Throwable caught) {
				
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Pais> result) {
				
				gridPais.setData(result);
				gridPais.removeColumn(gridPais.code);
				gridPais.removeColumn(gridPais.urlico);
				gridPais.removeColumn(gridPais.generador);
			}

		});
	}

	@Override
	public void loadMoneda() {
		
		ListFilterBean filter = new ListFilterBean();
		filter.setNameQuery(Moneda.LISTARMONEDASACTIVAS);
		SERVICE.listMoneda(filter,new AsyncCallback<List<Moneda>>() {

			@Override
			public void onFailure(Throwable caught) {
				
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Moneda> result) {
				
				gridMoneda.setData(result);
				gridMoneda.removeColumn(gridMoneda.code);
				gridMoneda.removeColumn(gridMoneda.representacion);
				gridMoneda.removeColumn(gridMoneda.estado);
			}

		});

	}
}
