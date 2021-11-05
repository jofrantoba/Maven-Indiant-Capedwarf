package com.indiana.view.admin.uimanttarifario;

import com.indiana.server.model.bean.Tarifario;

public interface InterUiMantTarifario {
	void loadFields();
    void cleanForm();
    void goToUiTarifario();
    boolean isValidData();
    void updateDataGrid(Tarifario bean);
    void clearError();
    void loadPais();
    void showSelectOptionPais();
    void buscarPais();
    void loadTipoSuscripcion();
    void showSelectOptionTipoSuscripcion();
    void buscarTipoSuscripcion();
    void processDesactivar();
}
