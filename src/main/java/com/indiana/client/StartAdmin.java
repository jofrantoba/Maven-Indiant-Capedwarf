package com.indiana.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.indiana.view.admin.uiloginadmin.UiLoginAdminImpl;
import com.indiana.view.admin.uisesionadmin.UiSesionAdminImpl;



public class StartAdmin implements EntryPoint {
//	
	public void onModuleLoad() {		
                UiSesionAdminImpl ui=new UiSesionAdminImpl();
		//UiLoginAdminImpl ui=new UiLoginAdminImpl();
		RootPanel.get().add(ui);
		}

}
