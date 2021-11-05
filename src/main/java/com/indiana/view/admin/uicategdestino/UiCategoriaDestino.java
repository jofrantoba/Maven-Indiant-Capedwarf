package com.indiana.view.admin.uicategdestino;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridCategDestino;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiCategoriaDestino extends UiFormMantenimiento implements InterUiCategoriaDestino{
	protected GridCategDestino grid;
	
	public UiCategoriaDestino(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridCategDestino();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}

	@Override
	public void showUIOper1() {
		goToUiMantCategoriaDestinoInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantCategoriaDestinoActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantCategoriaDestinoEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantCategoriaDestinoDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	
	@Override
	public void goToUiMantCategoriaDestinoInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCategoriaDestinoActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCategoriaDestinoEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCategoriaDestinoDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridCategDestino getGrid() {
		return grid;
	}
	
	

}
