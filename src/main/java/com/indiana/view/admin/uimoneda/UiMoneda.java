package com.indiana.view.admin.uimoneda;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridMoneda;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiMoneda extends UiFormMantenimiento implements InterUiMoneda{
protected GridMoneda grid;
	
	public UiMoneda(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridMoneda();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantMonedaInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantMonedaActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantMonedaEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantMonedaDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantMonedaInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantMonedaActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantMonedaEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantMonedaDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridMoneda getGrid() {
		return grid;
	}
	
	
}
