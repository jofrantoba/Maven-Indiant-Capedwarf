package com.indiana.view.admin.uipaismoneda;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridPaisMoneda;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiPaisMoneda extends UiFormMantenimiento implements InterUiPaisMoneda{
protected GridPaisMoneda grid;
	
	public UiPaisMoneda(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridPaisMoneda();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantPaisMonedaInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantPaisMonedaActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantPaisMonedaEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantPaisMonedaDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantPaisMonedaInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPaisMonedaActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPaisMonedaEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPaisMonedaDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridPaisMoneda getGrid() {
		return grid;
	}
	
	
}
