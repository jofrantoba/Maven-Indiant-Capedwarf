package com.indiana.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.indiana.view.negocio.uisesionnegocio.UiSesionNegocio;

public class StartNegocio implements EntryPoint {
	
	public void onModuleLoad() {		
		UiSesionNegocio ui=new UiSesionNegocio();
		RootPanel.get().add(ui);
		}	
		
}