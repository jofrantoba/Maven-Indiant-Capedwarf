package com.indiana.view.admin.uitiposuscripcion;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridTipoSuscripcion;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiTipoSuscripcion extends UiFormMantenimiento implements InterUiTipoSuscripcion{
protected GridTipoSuscripcion grid;
	
	public UiTipoSuscripcion(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridTipoSuscripcion();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTipoSuscripcionInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTipoSuscripcionActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTipoSuscripcionEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTipoSuscripcionDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantTipoSuscripcionInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoSuscripcionActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoSuscripcionEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoSuscripcionDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridTipoSuscripcion getGrid() {
		return grid;
	}
	
	
}
