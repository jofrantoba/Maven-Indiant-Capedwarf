package com.indiana.view.admin.uiparametros;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridParametrosSistema;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiParametros extends UiFormMantenimiento implements InterUiParametros{
protected GridParametrosSistema grid;
	
	public UiParametros(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridParametrosSistema();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}

	public GridParametrosSistema getGrid() {
		return grid;
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantParametrosInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantParametrosActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantParametrosEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantParametrosDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantParametrosInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantParametrosActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantParametrosEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantParametrosDetalle() {
		// TODO Auto-generated method stub
		
	}
}
