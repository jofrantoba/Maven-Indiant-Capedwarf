package com.indiana.view.admin.uimanttipocambio;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.PaisMoneda;
import com.indiana.server.model.bean.TipoCambio;
import com.indiana.view.admin.uitipocambio.UiTipoCambioImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantTipoCambioImpl extends UiMantTipoCambio{
	private UiTipoCambioImpl uiPadre;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantTipoCambioImpl(UiTipoCambioImpl uiPadre){		
		this.uiPadre = uiPadre;		
		}
	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoCambio bean=new TipoCambio();							
			bean.setBeanPaisMonedaOrigen(this.beanPaisMonedaOrigen);
			bean.setCodePaisMonedaOrigen(this.beanPaisMonedaOrigen.getCodePaisMoneda());
			bean.setPaisOrigen(this.beanPaisMonedaOrigen.getNombrePais());
			bean.setBeanPaisMonedaDestino(this.beanPaisMonedaDestino);
			bean.setCodePaisMonedaDestino(this.beanPaisMonedaDestino.getCodePaisMoneda());
			bean.setPaisDestino(this.beanPaisMonedaDestino.getNombrePais());
			bean.setFecha(dtFecha.getValue());
			bean.setPrecioCompra(txtPrecioCompra.getValue());
			bean.setPrecioVenta(txtPrecioVenta.getValue());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertTipoCambio(bean, new AsyncCallback<TipoCambio>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoCambio result) {
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
			TipoCambio bean=new TipoCambio();
			bean.setIdTipoCambio(this.bean.getIdTipoCambio());
			bean.setCodeTipoCambio(this.bean.getCodeTipoCambio());
			bean.setIsPersistente(this.bean.getIsPersistente());							
			bean.setBeanPaisMonedaOrigen(this.beanPaisMonedaOrigen);
			bean.setBeanPaisMonedaDestino(this.beanPaisMonedaDestino);
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoCambio(bean, new AsyncCallback<TipoCambio>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoCambio result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiTipoCambio();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			TipoCambio bean=new TipoCambio();
			bean.setIdTipoCambio(this.bean.getIdTipoCambio());
			bean.setCodeTipoCambio(this.bean.getCodeTipoCambio());
			bean.setIsPersistente(false);					
			bean.setBeanPaisMonedaOrigen(this.bean.getBeanPaisMonedaOrigen());
			bean.setBeanPaisMonedaDestino(this.bean.getBeanPaisMonedaDestino());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateTipoCambio(bean, new AsyncCallback<TipoCambio>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(TipoCambio result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiTipoCambio();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiTipoCambio() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(TipoCambio bean) {
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
	public void loadPaisMonedaOrigen() {
		// TODO Auto-generated method stub
		SERVICE.listPaisMoneda(new AsyncCallback<List<PaisMoneda>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<PaisMoneda> result) {
				// TODO Auto-generated method stub
				gridPaisMonedaOrigen.setData(result);
				gridPaisMonedaOrigen.removeColumn(gridPaisMonedaOrigen.pais);
				gridPaisMonedaOrigen.removeColumn(gridPaisMonedaOrigen.moneda);		
			}
			
		});
	}
	
	@Override
	public void loadPaisMonedaDestino() {
		// TODO Auto-generated method stub
					
			SERVICE.listPaisMoneda(new AsyncCallback<List<PaisMoneda>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(List<PaisMoneda> result) {
					// TODO Auto-generated method stub
					gridPaisMonedaDestino.setData(result);				
					gridPaisMonedaDestino.removeColumn(gridPaisMonedaDestino.pais);
					gridPaisMonedaDestino.removeColumn(gridPaisMonedaDestino.moneda);
				}
				
			});
		
		
	}
}
