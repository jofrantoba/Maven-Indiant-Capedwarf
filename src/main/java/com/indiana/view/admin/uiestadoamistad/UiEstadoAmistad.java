package com.indiana.view.admin.uiestadoamistad;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridEstadoAmistad;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiEstadoAmistad extends UiFormMantenimiento implements InterUiEstadoAmistad{
	protected GridEstadoAmistad grid;
	
	public UiEstadoAmistad(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridEstadoAmistad();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantEstadoAmistadInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantEstadoAmistadActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantEstadoAmistadEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantEstadoAmistadDetalle();
	}
	
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantEstadoAmistadInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoAmistadActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoAmistadEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoAmistadDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridEstadoAmistad getGrid() {
		return grid;
	}
	
	
}
