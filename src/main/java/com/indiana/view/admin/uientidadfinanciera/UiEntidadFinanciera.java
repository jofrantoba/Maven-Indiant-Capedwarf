package com.indiana.view.admin.uientidadfinanciera;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridEntidadFinanciera;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiEntidadFinanciera extends UiFormMantenimiento implements InterUiEntidadFinanciera{
protected GridEntidadFinanciera grid;
	
	public UiEntidadFinanciera(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridEntidadFinanciera();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());                
	}
	@Override
	public void showUIOper1() {
		goToUiMantEntidadFinancieraInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantEntidadFinancieraActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantEntidadFinancieraEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantEntidadFinancieraDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	
	
	@Override
	public void goToUiMantEntidadFinancieraInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEntidadFinancieraActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEntidadFinancieraEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEntidadFinancieraDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridEntidadFinanciera getGrid() {
		return grid;
	}

}
