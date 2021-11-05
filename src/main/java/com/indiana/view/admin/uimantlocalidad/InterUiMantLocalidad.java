package com.indiana.view.admin.uimantlocalidad;

import com.indiana.server.model.bean.Localidad;

public interface InterUiMantLocalidad {
	void loadFields();
    void cleanForm();
    void goToUiLocalidad();
    boolean isValidData();
    void updateDataGrid(Localidad bean);
    void clearError();
    void loadPais();
    void showSelectOptionPais();
    void buscarPais();
    void loadRegion();
    void showSelectOptionRegion();
    void buscarRegion();
}
