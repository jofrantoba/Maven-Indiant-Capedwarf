package com.indiana.view.admin.uimantestadomiembro;

import com.indiana.server.model.bean.EstadoMiembro;

public interface InterUiMantEstadoMiembro {
	void loadFields();
    void cleanForm();
    void goToUiEstadoMiembro();
    boolean isValidData();
    void updateDataGrid(EstadoMiembro bean);
    void clearError();
    boolean isExistInGrid();
}
