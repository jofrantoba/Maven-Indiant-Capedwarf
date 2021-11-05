package com.indiana.view.admin.uimantformapago;

import com.indiana.server.model.bean.FormaPago;

public interface InterUiMantFormaPago {
	void loadFields();
    void cleanForm();
    void goToUiFormaPago();
    boolean isValidData();
    void updateDataGrid(FormaPago bean);
    void clearError();
    boolean isExistInGrid();
}
