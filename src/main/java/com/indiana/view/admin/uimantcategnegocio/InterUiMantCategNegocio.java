package com.indiana.view.admin.uimantcategnegocio;

import com.indiana.server.model.bean.CategoriaNegocio;

public interface InterUiMantCategNegocio {
	void loadFields();
    void cleanForm();
    void goToUiCategoriaNegocio();
    boolean isValidData();
    void updateDataGrid(CategoriaNegocio bean);
    void clearError();
    boolean isExistInGrid();
}
