package com.indiana.view.admin.uimantcategdestino;

import com.indiana.server.model.bean.CategoriaDestino;

public interface InterUiMantCategDestino {
	void loadFields();
    void cleanForm();
    void goToUiCategoriaDestino();
    boolean isValidData();
    void updateDataGrid(CategoriaDestino bean);
    void clearError();
    boolean isExistInGrid();
}
