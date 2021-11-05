package com.indiana.view.admin.uiprivacidad;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridPrivacidad;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiPrivacidad extends UiFormMantenimiento implements InterUiPrivacidad{
	protected GridPrivacidad grid;
	
	public UiPrivacidad(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridPrivacidad();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantPrivacidadInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantPrivacidadActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantPrivacidadEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantPrivacidadDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantPrivacidadInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPrivacidadActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPrivacidadEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantPrivacidadDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridPrivacidad getGrid() {
		return grid;
	}
}
