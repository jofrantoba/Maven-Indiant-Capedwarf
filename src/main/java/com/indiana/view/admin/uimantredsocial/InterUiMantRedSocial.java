package com.indiana.view.admin.uimantredsocial;

import com.indiana.server.model.bean.RedSocial;

public interface InterUiMantRedSocial {
	void loadFields();
    void cleanForm();
    void goToUiRedSocial();
    boolean isValidData();
    void updateDataGrid(RedSocial bean);
    void clearError();
    boolean isExistInGrid();
}
