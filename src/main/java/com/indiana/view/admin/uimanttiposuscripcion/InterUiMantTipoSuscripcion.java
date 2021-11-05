package com.indiana.view.admin.uimanttiposuscripcion;

import com.indiana.server.model.bean.TipoSuscripcion;

public interface InterUiMantTipoSuscripcion {
	void loadFields();
    void cleanForm();
    void goToUiTipoSuscripcion();
    boolean isValidData();
    void updateDataGrid(TipoSuscripcion bean);
    void clearError();
    boolean isExistInGrid();
}
