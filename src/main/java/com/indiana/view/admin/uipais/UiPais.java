package com.indiana.view.admin.uipais;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridPais;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiPais extends UiFormMantenimiento implements InterUiPais{
	protected GridPais grid;
	
	public UiPais(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridPais();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantPaisInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantPaisActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantPaisEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantPaisDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	public GridPais getGrid() {
		return grid;
	}

	@Override
	public void goToUiMantPaisInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPaisActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPaisEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPaisDetalle() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
