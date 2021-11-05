package com.indiana.view.admin.uimantmoneda;

import com.indiana.server.model.bean.Moneda;

public interface InterUiMantMoneda {
	void loadFields();
    void cleanForm();
    void goToUiMoneda();
    boolean isValidData();
    void updateDataGrid(Moneda bean);
    void clearError();
    boolean isExistInGrid();
}
