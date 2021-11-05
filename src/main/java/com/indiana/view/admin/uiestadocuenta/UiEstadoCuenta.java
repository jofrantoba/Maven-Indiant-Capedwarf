package com.indiana.view.admin.uiestadocuenta;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridEstadoCuenta;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiEstadoCuenta extends UiFormMantenimiento implements InterUiEstadoCuenta{
	protected GridEstadoCuenta grid;
	
	public UiEstadoCuenta(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridEstadoCuenta();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantEstadoCuentaInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantEstadoCuentaActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantEstadoCuentaEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantEstadoCuentaDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	

	@Override
	public void goToUiMantEstadoCuentaInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoCuentaActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoCuentaEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoCuentaDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridEstadoCuenta getGrid() {
		return grid;
	}
	
	
}
