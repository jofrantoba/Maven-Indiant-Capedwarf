package com.indiana.view.admin.uimantentidadfinanciera;

import com.indiana.server.model.bean.EntidadFinanciera;

public interface InterUiMantEntidadFinanciera {
	void loadFields();
    void cleanForm();
    void goToUiEntidadFinanciera();
    boolean isValidData();
    void updateDataGrid(EntidadFinanciera bean);
    void clearError();
    void loadPais();
    void showSelectOptionPais();
    void buscarPais();
}
