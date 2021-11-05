package com.indiana.view.admin.uimantestadoamistad;

import com.indiana.server.model.bean.EstadoAmistad;

public interface InterUiMantEstadoAmistad {
	void loadFields();
    void cleanForm();
    void goToUiEstadoAmistad();
    boolean isValidData();
    void updateDataGrid(EstadoAmistad bean);
    void clearError();
    boolean isExistInGrid();
}
