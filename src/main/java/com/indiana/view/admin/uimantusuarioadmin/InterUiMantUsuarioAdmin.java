package com.indiana.view.admin.uimantusuarioadmin;

import com.indiana.server.model.bean.UsuarioAdmin;

public interface InterUiMantUsuarioAdmin {
	void loadFields();
    void cleanForm();
    void goToUiUsuarioAdmin();
    boolean isValidData();
    void updateDataGrid(UsuarioAdmin bean);
    void clearError();
    boolean isExistInGrid();
    void loadEstadoCuenta();
    void showSelectOptionEstadoCuenta();
    void buscarEstadoCuenta();
}
