package com.indiana.view.admin.uiusuarioadmin;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridUsuarioAdmin;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiUsuarioAdmin extends UiFormMantenimiento implements InterUiUsuarioAdmin{
protected GridUsuarioAdmin grid;
	
	public UiUsuarioAdmin(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridUsuarioAdmin();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantUsuarioAdminInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantUsuarioAdminActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantUsuarioAdminEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantUsuarioAdminDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantUsuarioAdminInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantUsuarioAdminActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantUsuarioAdminEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantUsuarioAdminDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridUsuarioAdmin getGrid() {
		return grid;
	}
	
}
