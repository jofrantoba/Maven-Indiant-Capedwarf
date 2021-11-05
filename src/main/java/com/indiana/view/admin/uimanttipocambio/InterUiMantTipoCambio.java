package com.indiana.view.admin.uimanttipocambio;

import com.indiana.server.model.bean.TipoCambio;

public interface InterUiMantTipoCambio {
	void loadFields();
    void cleanForm();
    void goToUiTipoCambio();
    boolean isValidData();
    void updateDataGrid(TipoCambio bean);
    void clearError();
    void loadPaisMonedaOrigen();
    void showSelectOptionPaisMonedaOrigen();
    void buscarPaisMonedaOrigen();
    void loadPaisMonedaDestino();
    void showSelectOptionPaisMonedaDestino();
    void buscarPaisMonedaDestino();
}
