package com.indiana.view.admin.uiloginadmin;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionMantenimiento;
import com.indiana.client.service.ServiceGestionMantenimientoAsync;
import com.indiana.server.model.bean.UsuarioAdmin;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

import gwt.material.design.client.ui.MaterialToast;

public class UiLoginAdminImpl extends UiLoginAdmin{
	private static final String URLBASE=GWT.getHostPageBaseURL();
	private final ServiceGestionMantenimientoAsync SERVICE = GWT
			.create(ServiceGestionMantenimiento.class);
	@Override
	public void loginAdmin() {
		SERVICE.logeoUsuarioAdmin(txtCorreo.getText(), txtClave.getText(),  new AsyncCallback<UsuarioAdmin>(){

			@Override
			public void onFailure(Throwable caught) {
				MaterialToast.fireToast(caught.getLocalizedMessage());				
			}

			@Override
			public void onSuccess(UsuarioAdmin result) {
				MaterialToast.fireToast("Bienvenido Sr(a) "+result.getNombre());
				goToSession();								
			}} );
	}
	
	@Override
	public void goToSession(){
		String url=URLBASE;
		url=url+"sessionadmin.html";
		MaterialToast.fireToast(url);
		RequestBuilder builder=new RequestBuilder(RequestBuilder.GET,url);
		try{
			builder.sendRequest(null,new RequestCallback(){

				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					if(response.getStatusCode()==200){
						Window.Location.assign(response.getText());
					}else{
						MaterialToast.fireToast(response.getStatusText());
					}
				}

				@Override
				public void onError(Request request, Throwable ex) {
					// TODO Auto-generated method stub
					MaterialToast.fireToast(ex.getLocalizedMessage());
				}});			
		}catch(RequestException ex){
			MaterialToast.fireToast(ex.getLocalizedMessage());
		}
	}
	
}
