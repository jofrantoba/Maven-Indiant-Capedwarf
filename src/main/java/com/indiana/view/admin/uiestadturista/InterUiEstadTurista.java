package com.indiana.view.admin.uiestadturista;

import com.google.gwt.user.client.ui.FlexTable;

public interface InterUiEstadTurista {
	void llenarComboBox();
	void cargarDataGraficaProcedencia(String nacionalidad);
	void cargarDataGraficaRangoEdad(String edad);
	FlexTable getSimpleLayoutPanel();
	void initListener();
	void initStyle();
}
