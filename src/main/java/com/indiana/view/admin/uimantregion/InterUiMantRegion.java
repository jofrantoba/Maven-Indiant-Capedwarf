package com.indiana.view.admin.uimantregion;

import com.indiana.server.model.bean.Region;

public interface InterUiMantRegion {
	void loadFields();
    void cleanForm();
    void goToUiRegion();
    boolean isValidData();
    void updateDataGrid(Region bean);
    void clearError();
    void loadPais();
    void showSelectOptionPais();
    void buscarPais();
}
