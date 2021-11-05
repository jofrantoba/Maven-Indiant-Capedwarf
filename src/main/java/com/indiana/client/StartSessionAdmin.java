package com.indiana.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.indiana.view.admin.uisesionadmin.UiSesionAdmin;

public class StartSessionAdmin implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		UiSesionAdmin ui=new UiSesionAdmin();
		RootPanel.get().add(ui);
	}

}
