package com.indiana.view.admin.uitipomovimiento;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridTipoMovimiento;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiTipoMovimiento extends UiFormMantenimiento implements InterUiTipoMovimiento{
protected GridTipoMovimiento grid;
	
	public UiTipoMovimiento(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridTipoMovimiento();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTipoMovimientoInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTipoMovimientoActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTipoMovimientoEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTipoMovimientoDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantTipoMovimientoInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoMovimientoActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoMovimientoEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoMovimientoDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridTipoMovimiento getGrid() {
		return grid;
	}
	
	
}
