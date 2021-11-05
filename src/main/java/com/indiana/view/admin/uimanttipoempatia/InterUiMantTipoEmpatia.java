package com.indiana.view.admin.uimanttipoempatia;

import com.indiana.server.model.bean.TipoEmpatia;

public interface InterUiMantTipoEmpatia {
	void loadFields();
    void cleanForm();
    void goToUiTipoEmpatia();
    boolean isValidData();
    void updateDataGrid(TipoEmpatia bean);
    void clearError();
    boolean isExistInGrid();
}
