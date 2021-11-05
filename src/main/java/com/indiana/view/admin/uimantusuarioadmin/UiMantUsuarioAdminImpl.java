package com.indiana.view.admin.uimantusuarioadmin;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.CuentaAdmin;
import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.view.admin.uiusuarioadmin.UiUsuarioAdminImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantUsuarioAdminImpl extends  UiMantUsuarioAdmin{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantUsuarioAdminImpl(UiUsuarioAdminImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			UsuarioAdmin bean=new UsuarioAdmin();
			bean.setNombre(txtNombre.getText().trim().toUpperCase());
			bean.setApellido(txtApellido.getText().trim().toUpperCase());
			bean.setDni(txtDni.getText().trim().toUpperCase());
			bean.setTelefono(txtTelefono.getText().trim().toUpperCase());
			bean.setCorreo(txtCorreo.getText().trim());
			CuentaAdmin beanCuentaAdmin=new CuentaAdmin(); 
			beanCuentaAdmin.setClave(txtClave.getText().trim());
			bean.setBeanCuentaAdmin(beanCuentaAdmin);
			EstadoCuenta beanEstadoCuenta=new EstadoCuenta();
			beanEstadoCuenta.setIdEstadoCuenta("P");
			bean.getBeanCuentaAdmin().setEstadoCuenta(beanEstadoCuenta);
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertUsuarioAdmin(bean, new AsyncCallback<UsuarioAdmin>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(UsuarioAdmin result) {
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
			UsuarioAdmin bean=new UsuarioAdmin();
			bean.setIdUsuarioAdmin(this.bean.getIdUsuarioAdmin());
			bean.setCodeUsuarioAdmin(this.bean.getCodeUsuarioAdmin());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombre(txtNombre.getText().trim().toUpperCase());
			bean.setApellido(txtApellido.getText().trim().toUpperCase());
			bean.setDni(txtDni.getText().trim().toUpperCase());
			bean.setTelefono(txtTelefono.getText().trim().toUpperCase());
			bean.setCorreo(this.bean.getCorreo());
			bean.setBeanCuentaAdmin(this.bean.getBeanCuentaAdmin());
			bean.getBeanCuentaAdmin().setEstadoCuenta(this.beanEstadoCuenta);
			bean.setIsPersistente(true);
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateUsuarioAdmin(bean, new AsyncCallback<UsuarioAdmin>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(UsuarioAdmin result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiUsuarioAdmin();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			UsuarioAdmin bean=new UsuarioAdmin();
			bean.setIdUsuarioAdmin(this.bean.getIdUsuarioAdmin());
			bean.setCodeUsuarioAdmin(this.bean.getCodeUsuarioAdmin());
			bean.setNombre(this.bean.getNombre());
			bean.setApellido(this.bean.getApellido());
			bean.setDni(this.bean.getDni());
			bean.setTelefono(this.bean.getTelefono());
			bean.setCorreo(this.bean.getCorreo());
			bean.setBeanCuentaAdmin(this.bean.getBeanCuentaAdmin());			
			bean.getBeanCuentaAdmin().setEstadoCuenta(this.bean.getBeanCuentaAdmin().getEstadoCuenta());
			bean.getBeanCuentaAdmin().setIsPersistente(false);
			bean.setIsPersistente(false);
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateUsuarioAdmin(bean, new AsyncCallback<UsuarioAdmin>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(UsuarioAdmin result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiUsuarioAdmin();
				}
				
			});
		}
	}
	
	@Override
	public void loadEstadoCuenta() {
		// TODO Auto-generated method stub
		SERVICE.listEstadoCuenta(new AsyncCallback<List<EstadoCuenta>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<EstadoCuenta> result) {
				// TODO Auto-generated method stub
				gridEstadoCuenta.setData(result);
				gridEstadoCuenta.removeColumn(gridEstadoCuenta.code);				
			}

		});
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiUsuarioAdmin() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(UsuarioAdmin bean) {
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
