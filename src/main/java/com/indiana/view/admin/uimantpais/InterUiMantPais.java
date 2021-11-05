package com.indiana.view.admin.uimantpais;

import com.indiana.server.model.bean.Pais;

public interface InterUiMantPais {
	void loadFields();
    void cleanForm();
    void goToUiPais();
    boolean isValidData();
    void updateDataGrid(Pais bean);
    void clearError();
    boolean isExistInGrid();
}
