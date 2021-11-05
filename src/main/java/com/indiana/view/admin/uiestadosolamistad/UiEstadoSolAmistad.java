package com.indiana.view.admin.uiestadosolamistad;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridEstadoSolicAmistad;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiEstadoSolAmistad extends UiFormMantenimiento implements InterUiEstadoSolAmistad{
	protected GridEstadoSolicAmistad grid;
	
	public UiEstadoSolAmistad(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridEstadoSolicAmistad();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantEstadoSolAmistadInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantEstadoSolAmistadActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantEstadoSolAmistadEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantEstadoSolAmistadDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}


	public GridEstadoSolicAmistad getGrid() {
		return grid;
	}

	@Override
	public void goToUiMantEstadoSolAmistadInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoSolAmistadActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoSolAmistadEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoSolAmistadDetalle() {
		// TODO Auto-generated method stub
		
	}
	
	
}
