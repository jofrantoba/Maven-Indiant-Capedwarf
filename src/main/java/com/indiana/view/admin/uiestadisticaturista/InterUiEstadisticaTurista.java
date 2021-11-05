package com.indiana.view.admin.uiestadisticaturista;

import com.google.gwt.user.client.ui.FlexTable;

public interface InterUiEstadisticaTurista {
	
	void llenarComboBox();
	void cargarDataGraficaProcedencia(String nacionalidad);
	void cargarDataGraficaRangoEdad(String edad);
	FlexTable getSimpleLayoutPanel();
	void initListener();
	void initStyle();
}
