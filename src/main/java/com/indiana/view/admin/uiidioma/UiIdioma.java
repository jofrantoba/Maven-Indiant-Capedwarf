package com.indiana.view.admin.uiidioma;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridIdioma;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiIdioma extends UiFormMantenimiento implements InterUiIdioma{
protected GridIdioma grid;
	
	public UiIdioma(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridIdioma();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	@Override
	public void showUIOper1() {
		goToUiMantIdiomaInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantIdiomaActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantIdiomaEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantIdiomaDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantIdiomaInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantIdiomaActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantIdiomaEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantIdiomaDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridIdioma getGrid() {
		return grid;
	}
	
	
}

