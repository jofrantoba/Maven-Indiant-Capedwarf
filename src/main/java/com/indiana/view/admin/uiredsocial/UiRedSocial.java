package com.indiana.view.admin.uiredsocial;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridRedSocial;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiRedSocial extends UiFormMantenimiento implements InterUiRedSocial{
protected GridRedSocial grid;
	
	public UiRedSocial(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridRedSocial();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantRedSocialInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantRedSocialActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantRedSocialEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantRedSocialDetalle();
	}
		
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantRedSocialInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantRedSocialActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantRedSocialEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantRedSocialDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridRedSocial getGrid() {
		return grid;
	}
	
	

}
