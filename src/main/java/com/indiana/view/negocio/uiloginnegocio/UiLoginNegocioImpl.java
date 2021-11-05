package com.indiana.view.negocio.uiloginnegocio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.indiana.client.service.ServiceGestionNegocio;
import com.indiana.client.service.ServiceGestionNegocioAsync;
import com.indiana.server.model.bean.UsuarioNegocio;

import gwt.material.design.client.ui.MaterialToast;

public class UiLoginNegocioImpl extends UiLoginNegocio{
	private static final String URLBASE=GWT.getHostPageBaseURL();
	private final ServiceGestionNegocioAsync SERVICE = GWT
			.create(ServiceGestionNegocio.class);
	@Override
	public void loginNegocio() {
		SERVICE.logeoUsuarioNegocio(txtCorreo.getText(), txtClave.getText(),  new AsyncCallback<UsuarioNegocio>(){

			@Override
			public void onFailure(Throwable caught) {
				MaterialToast.fireToast(caught.getLocalizedMessage());				
			}

			@Override
			public void onSuccess(UsuarioNegocio result) {
				MaterialToast.fireToast("Bienvenido Sr(a) "+result.getDescripcion());
				goToSession();								
			}} );
	}
	
	@Override
	public void goToSession(){
		String url=URLBASE;
		url=url+"sessionNegocio.html";
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
						MaterialToast.fireToast("B"+response.getStatusText());
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
