package com.indiana.view.admin.uimanttarifario;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.Tarifario;
import com.indiana.server.model.bean.TipoSuscripcion;
import com.indiana.shared.ListFilterBean;
import com.indiana.view.admin.uitarifario.UiTarifarioImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantTarifarioImpl extends UiMantTarifario{
	private UiTarifarioImpl uiPadre;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantTarifarioImpl(UiTarifarioImpl uiPadre){		
		this.uiPadre = uiPadre;		
		}
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Tarifario bean=new Tarifario();			
			bean.setBeanPais(this.beanPais);
			bean.setCodePais(this.beanPais.getCodePais());
			bean.setNombrePais(this.beanPais.getNombre());
			bean.setBeanTipoSuscripcion(this.beanTipoSuscripcion);
			bean.setCodeTipoSuscripcion(this.beanTipoSuscripcion.getCodeTipoSuscripcion());
			bean.setNombreTipoSuscripcion(this.beanTipoSuscripcion.getDescripcion());
			bean.setRangoInicial(txtRangoInicial.getValue());
			bean.setRangoFinal(txtRangoFinal.getValue());
			bean.setPrecioUnidad(txtPrecioUnitario.getValue());
			bean.setEstado("A");
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new java.util.Date()).getTime());
			SERVICE.insertTarifario(bean, new AsyncCallback<Tarifario>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Tarifario result) {
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
			Tarifario bean=new Tarifario();
			bean.setIdTarifario(this.bean.getIdTarifario());
			bean.setCodeTarifario(this.bean.getCodeTarifario());
			bean.setIsPersistente(this.bean.getIsPersistente());				
			bean.setBeanPais(this.beanPais);
			bean.setBeanTipoSuscripcion(this.beanTipoSuscripcion);
			bean.setRangoInicial(txtRangoInicial.getValue());
			bean.setRangoFinal(txtRangoFinal.getValue());
			bean.setPrecioUnidad(txtPrecioUnitario.getValue());
			bean.setEstado(this.bean.getEstado());
			bean.setFechaInicial(this.bean.getFechaInicial());
			bean.setFechaFinal(this.bean.getFechaFinal());
			bean.setOperacion("A");
			bean.setVersion((new java.util.Date()).getTime());
			SERVICE.updateTarifario(bean, new AsyncCallback<Tarifario>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Tarifario result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiTarifario();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Tarifario bean=new Tarifario();
			bean.setIdTarifario(this.bean.getIdTarifario());
			bean.setCodeTarifario(this.bean.getCodeTarifario());
			bean.setIsPersistente(false);			
			bean.setBeanPais(this.bean.getBeanPais());
			bean.setBeanTipoSuscripcion(this.beanTipoSuscripcion);
			bean.setRangoInicial(this.bean.getRangoInicial());
			bean.setRangoFinal(this.bean.getRangoFinal());
			bean.setPrecioUnidad(this.bean.getPrecioUnidad());
			bean.setEstado(this.bean.getEstado());
			bean.setFechaInicial(this.bean.getFechaInicial());
			bean.setFechaFinal(this.bean.getFechaFinal());
			bean.setOperacion("A");
			bean.setVersion((new java.util.Date()).getTime());
			SERVICE.updateTarifario(bean, new AsyncCallback<Tarifario>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Tarifario result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiTarifario();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			Tarifario bean=new Tarifario();
			bean.setIdTarifario(this.bean.getIdTarifario());
			bean.setCodeTarifario(this.bean.getCodeTarifario());
			bean.setIsPersistente(this.bean.getIsPersistente());			
			bean.setBeanPais(this.bean.getBeanPais());
			bean.setBeanTipoSuscripcion(this.beanTipoSuscripcion);
			bean.setRangoInicial(this.bean.getRangoInicial());
			bean.setRangoFinal(this.bean.getRangoFinal());
			bean.setPrecioUnidad(this.bean.getPrecioUnidad());
			bean.setEstado("D");
			bean.setFechaInicial(this.bean.getFechaInicial());
			bean.setFechaFinal(new java.util.Date());
			bean.setOperacion("A");
			bean.setVersion((new java.util.Date()).getTime());
			SERVICE.updateTarifario(bean, new AsyncCallback<Tarifario>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(Tarifario result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Desactivado correctamente");
					updateDataGrid(result);
					goToUiTarifario();
				}
				
			});
		}
	}
	
	@Override
	public void goToUiTarifario() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(Tarifario bean) {
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
	public void loadTipoSuscripcion() {
		// TODO Auto-generated method stub
		ListFilterBean filter=new ListFilterBean();
		filter.setNameQuery(TipoSuscripcion.LISTARTIPOSUSCRIPCIONACTIVA);
		SERVICE.listTipoSuscripcion(filter,new AsyncCallback<List<TipoSuscripcion>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<TipoSuscripcion> result) {
				// TODO Auto-generated method stub
				gridTipoSuscripcion.setData(result);
				gridTipoSuscripcion.removeColumn(gridTipoSuscripcion.code);
				gridTipoSuscripcion.removeColumn(gridTipoSuscripcion.estado);		
			}
			
		});
	}
}
