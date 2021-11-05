package com.indiana.view.admin.uitipoempatia;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridTipoEmpatia;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiTipoEmpatia extends UiFormMantenimiento implements InterUiTipoEmpatia{
protected GridTipoEmpatia grid;
	
	public UiTipoEmpatia(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridTipoEmpatia();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTipoEmpatiaInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTipoEmpatiaActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTipoEmpatiaEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTipoEmpatiaDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantTipoEmpatiaInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoEmpatiaActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoEmpatiaEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoEmpatiaDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridTipoEmpatia getGrid() {
		return grid;
	}
	
}
