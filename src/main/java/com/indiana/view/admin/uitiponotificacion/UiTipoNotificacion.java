package com.indiana.view.admin.uitiponotificacion;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridTipoNotificacion;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiTipoNotificacion extends UiFormMantenimiento implements InterUiTipoNotificacion{
protected GridTipoNotificacion grid;
	
	public UiTipoNotificacion(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridTipoNotificacion();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTipoNotificacionInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTipoNotificacionActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTipoNotificacionEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTipoNotificacionDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	public GridTipoNotificacion getGrid() {
		return grid;
	}

	@Override
	public void goToUiMantTipoNotificacionInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoNotificacionActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoNotificacionEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoNotificacionDetalle() {
		// TODO Auto-generated method stub
		
	}
	
	
}
