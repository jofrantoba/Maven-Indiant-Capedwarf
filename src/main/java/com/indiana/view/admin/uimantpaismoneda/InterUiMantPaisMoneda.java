package com.indiana.view.admin.uimantpaismoneda;

import com.indiana.server.model.bean.PaisMoneda;

public interface InterUiMantPaisMoneda {
	void loadFields();
    void cleanForm();
    void goToUiPaisMoneda();
    boolean isValidData();
    void updateDataGrid(PaisMoneda bean);
    void clearError();
    void loadPais();
    void showSelectOptionPais();
    void buscarPais();
    void loadMoneda();
    void showSelectOptionMoneda();
    void buscarMoneda();
}
