package com.indiana.view.admin.uitipocambio;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridTipoCambio;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiTipoCambio extends UiFormMantenimiento implements InterUiTipoCambio{
protected GridTipoCambio grid;
	
	public UiTipoCambio(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridTipoCambio();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTipoCambioInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTipoCambioActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTipoCambioEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTipoCambioDetalle();
	}

	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	
	
	@Override
	public void goToUiMantTipoCambioInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoCambioActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoCambioEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoCambioDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridTipoCambio getGrid() {
		return grid;
	}
	
	
}
