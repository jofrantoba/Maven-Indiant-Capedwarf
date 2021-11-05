package com.indiana.view.admin.uimanttiponotificacion;

import com.indiana.server.model.bean.TipoNotificacion;

public interface InterUiMantTipoNotificacion {
	void loadFields();
    void cleanForm();
    void goToUiTipoNotificacion();
    boolean isValidData();
    void updateDataGrid(TipoNotificacion bean);
    void clearError();
    boolean isExistInGrid();
}
