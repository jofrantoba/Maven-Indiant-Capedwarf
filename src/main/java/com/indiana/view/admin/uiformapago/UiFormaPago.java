package com.indiana.view.admin.uiformapago;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridFormaPago;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiFormaPago extends UiFormMantenimiento implements InterUiFormaPago{
protected GridFormaPago grid;
	
	public UiFormaPago(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridFormaPago();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantFormaPagoInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantFormaPagoActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantFormaPagoEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantFormaPagoDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	
	@Override
	public void goToUiMantFormaPagoInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantFormaPagoActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantFormaPagoEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantFormaPagoDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridFormaPago getGrid() {
		return grid;
	}
	
	

}
