package com.indiana.view.admin.uimantidioma;

import com.indiana.server.model.bean.Idioma;

public interface InterUiMantIdioma {
	void loadFields();
    void cleanForm();
    void goToUiIdioma();
    boolean isValidData();
    void updateDataGrid(Idioma bean);
    void clearError();
    boolean isExistInGrid();
}
