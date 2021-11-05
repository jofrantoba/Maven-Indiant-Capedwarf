package com.indiana.view.admin.uimantestadocuenta;

import com.indiana.server.model.bean.EstadoCuenta;

public interface InterUiMantEstadoCuenta {
	void loadFields();
    void cleanForm();
    void goToUiEstadoCuenta();
    boolean isValidData();
    void updateDataGrid(EstadoCuenta bean);
    void clearError();
    boolean isExistInGrid();    
}
