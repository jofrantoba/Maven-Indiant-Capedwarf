package com.indiana.view.admin.uicategnegocio;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridCategNegocio;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiCategoriaNegocio extends UiFormMantenimiento implements InterUiCategoriaNegocio {
	protected GridCategNegocio grid;

	public UiCategoriaNegocio(){
		initComponents(); 
	}

	private void initComponents(){
		grid= new GridCategNegocio();
		grid.setMinimumTableWidth(1024,Unit.PX);
		this.getPnlTabla().add(grid);
		this.getPnlTabla().add(grid.getPager());
	}
	@Override
	public void showUIOper1() {
		this.goToUiMantCategoriaNegocioInsertar();
	}

	@Override
	public void showUIOper2() {
	this.goToUiMantCategoriaNegocioActualizar();
	}

	@Override
	public void showUIOper3() {
		this.goToUiMantCategoriaNegocioEliminar();
	}

	@Override
	public void showUIOper4() {
		this.goToUiMantCategoriaNegocioDetalle();
		
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	
	
	@Override
	public void goToUiMantCategoriaNegocioInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCategoriaNegocioActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCategoriaNegocioEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCategoriaNegocioDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridCategNegocio getGrid() {
		return grid;
	}


}
