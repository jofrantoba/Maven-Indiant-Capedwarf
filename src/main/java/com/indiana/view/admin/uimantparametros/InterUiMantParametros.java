package com.indiana.view.admin.uimantparametros;

import com.indiana.server.model.bean.ParametrosSistema;

public interface InterUiMantParametros {
	void loadFields();
    void cleanForm();
    void goToUiParametros();
    boolean isValidData();
    void updateDataGrid(ParametrosSistema bean);
    void clearError();
    boolean isExistInGrid();
}
