package com.indiana.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.indiana.view.negocio.uisesionnegocio.UiSesionNegocio;

public class StartSessionNegocio implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		UiSesionNegocio ui=new UiSesionNegocio();
		RootPanel.get().add(ui);
	}

}
