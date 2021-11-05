package com.indiana.view.admin.uiregion;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridRegion;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiRegion extends UiFormMantenimiento implements InterUiRegion{
protected GridRegion grid;
	
	public UiRegion(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridRegion();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantRegionInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantRegionActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantRegionEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantRegionDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantRegionInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantRegionActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantRegionEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantRegionDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridRegion getGrid() {
		return grid;
	}
	
	
}
