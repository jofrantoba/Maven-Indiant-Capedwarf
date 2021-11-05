package com.indiana.view.admin.uimantestadosolamistad;

import com.indiana.server.model.bean.EstadoSolicAmistad;

public interface InterUiMantEstadoSolAmistad {
	void loadFields();
    void cleanForm();
    void goToUiEstadoSolicAmistad();
    boolean isValidData();
    void updateDataGrid(EstadoSolicAmistad bean);
    void clearError();
    boolean isExistInGrid();
}
