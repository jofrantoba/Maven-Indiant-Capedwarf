package com.indiana.view.admin.uimanttipomovimiento;

import com.indiana.server.model.bean.TipoMovimiento;

public interface InterUiMantTipoMovimiento {
	void loadFields();
    void cleanForm();
    void goToUiTipoMovimiento();
    boolean isValidData();
    void updateDataGrid(TipoMovimiento bean);
    void clearError();
    boolean isExistInGrid();
    void loadTipo();
}
