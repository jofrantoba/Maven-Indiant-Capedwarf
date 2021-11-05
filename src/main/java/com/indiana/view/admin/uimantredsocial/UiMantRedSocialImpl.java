package com.indiana.view.admin.uimantredsocial;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.RedSocial;
import com.indiana.view.admin.uiredsocial.UiRedSocialImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantRedSocialImpl extends  UiMantRedSocial{	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantRedSocialImpl(UiRedSocialImpl uiPadre){
		super(uiPadre);
	}

	@Override
	public void processInsertar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			RedSocial bean=new RedSocial();
			bean.setIdRedSocial(txtId.getText().trim().toUpperCase());
			bean.setCodeRedSocial(txtId.getText().trim().toUpperCase());
			bean.setNombreApp(txtNombreApp.getText().trim());
			bean.setApiKey(txtApiKey.getText().trim());
			bean.setSharedSecret(txtSecretApp.getText().trim());
			bean.setOauthToken(txtOauthToken.getText());
			bean.setOauthTokenSecret(txtOauthTokenSecret.getText());
			bean.setApiVersion(txtApiVersion.getText().trim());
			bean.setRedSocial(txtRedSocial.getText().trim().toUpperCase());
			bean.setIsPersistente(true);
			bean.setOperacion("I");
			bean.setVersion((new Date()).getTime());
			SERVICE.insertRedSocial(bean, new AsyncCallback<RedSocial>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(RedSocial result) {
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
			RedSocial bean=new RedSocial();
			bean.setIdRedSocial(this.bean.getIdRedSocial());
			bean.setCodeRedSocial(this.bean.getCodeRedSocial());
			bean.setIsPersistente(this.bean.getIsPersistente());
			bean.setNombreApp(txtNombreApp.getText().trim());	
			bean.setApiKey(txtApiKey.getText().trim());
			bean.setSharedSecret(txtSecretApp.getText().trim());
			bean.setOauthToken(txtOauthToken.getText());
			bean.setOauthTokenSecret(txtOauthTokenSecret.getText());
			bean.setApiVersion(txtApiVersion.getText().trim());
			bean.setRedSocial(txtRedSocial.getText().trim().toUpperCase());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateRedSocial(bean, new AsyncCallback<RedSocial>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(RedSocial result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiRedSocial();
				}
				
			});
		}
	}

	@Override
	public void processEliminar() {
		// TODO Auto-generated method stub
		if (isValidData()) {
			RedSocial bean=new RedSocial();
			bean.setIdRedSocial(this.bean.getIdRedSocial());
			bean.setCodeRedSocial(this.bean.getCodeRedSocial());
			bean.setIsPersistente(false);
			bean.setNombreApp(this.bean.getNombreApp());	
			bean.setSharedSecret(this.bean.getSharedSecret());
			bean.setApiVersion(this.bean.getApiVersion());
			bean.setRedSocial(this.bean.getRedSocial());
			bean.setOperacion("A");
			bean.setVersion((new Date()).getTime());
			SERVICE.updateRedSocial(bean, new AsyncCallback<RedSocial>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(RedSocial result) {
					// TODO Auto-generated method stub					
					MaterialToast.fireToast("Eliminado correctamente");
					updateDataGrid(result);
					goToUiRedSocial();
				}
				
			});
		}
	}

	@Override
	public void processDetalle() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void goToUiRedSocial() {
		// TODO Auto-generated method stub		
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(RedSocial bean) {
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
