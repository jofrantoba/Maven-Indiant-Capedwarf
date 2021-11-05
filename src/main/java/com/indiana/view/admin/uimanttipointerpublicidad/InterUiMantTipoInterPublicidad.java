package com.indiana.view.admin.uimanttipointerpublicidad;

import com.indiana.server.model.bean.TipoInterPublicidad;

public interface InterUiMantTipoInterPublicidad {
	void loadFields();
    void cleanForm();
    void goToUiTipoInterPublicidad();
    boolean isValidData();
    void updateDataGrid(TipoInterPublicidad bean);
    void clearError();
    boolean isExistInGrid();
}
