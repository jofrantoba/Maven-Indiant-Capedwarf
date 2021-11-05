package com.indiana.view.admin.uilocalidad;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridLocalidad;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiLocalidad extends UiFormMantenimiento implements InterUiLocalidad{
protected GridLocalidad grid;
	
	public UiLocalidad(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridLocalidad();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantLocalidadInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantLocalidadActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantLocalidadEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantLocalidadDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantLocalidadInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantLocalidadActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantLocalidadEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantLocalidadDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridLocalidad getGrid() {
		return grid;
	}
	
}
