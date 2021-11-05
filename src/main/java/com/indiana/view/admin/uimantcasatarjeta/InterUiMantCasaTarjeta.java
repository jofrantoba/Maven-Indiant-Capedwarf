package com.indiana.view.admin.uimantcasatarjeta;

import com.indiana.server.model.bean.CasaTarjeta;

public interface InterUiMantCasaTarjeta {
	void loadFields();
    void cleanForm();
    void goToUiCasaTarjeta();
    boolean isValidData();
    void updateDataGrid(CasaTarjeta bean);
    void clearError();
    boolean isExistInGrid();
}
