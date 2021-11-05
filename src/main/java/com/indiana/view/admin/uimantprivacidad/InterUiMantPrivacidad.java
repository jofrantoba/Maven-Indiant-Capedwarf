package com.indiana.view.admin.uimantprivacidad;

import com.indiana.server.model.bean.Privacidad;

public interface InterUiMantPrivacidad {
	void loadFields();
    void cleanForm();
    void goToUiPrivacidad();
    boolean isValidData();
    void updateDataGrid(Privacidad bean);
    void clearError();
    boolean isExistInGrid();
}
