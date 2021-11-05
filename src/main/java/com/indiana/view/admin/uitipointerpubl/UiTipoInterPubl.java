package com.indiana.view.admin.uitipointerpubl;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridTipoInterPublicidad;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiTipoInterPubl extends UiFormMantenimiento implements InterUiTipoInterPubl{
protected GridTipoInterPublicidad grid;
	
	public UiTipoInterPubl(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridTipoInterPublicidad();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTipoInterPublicidadInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTipoInterPublicidadActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTipoInterPublicidadEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTipoInterPublicidadDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	public GridTipoInterPublicidad getGrid() {
		return grid;
	}

	@Override
	public void goToUiMantTipoInterPublicidadInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoInterPublicidadActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoInterPublicidadEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTipoInterPublicidadDetalle() {
		// TODO Auto-generated method stub
		
	}
	
	
}
