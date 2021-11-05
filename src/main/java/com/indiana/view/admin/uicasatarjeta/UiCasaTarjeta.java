package com.indiana.view.admin.uicasatarjeta;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridCasaTarjeta;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiCasaTarjeta extends UiFormMantenimiento implements InterUiCasaTarjeta{
	protected GridCasaTarjeta grid;
	
	public UiCasaTarjeta(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridCasaTarjeta();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantCasaTarjetaInsertar();
    }
	
	@Override
    public void showUIOper2() {
		goToUiMantCasaTarjetaActualizar();
    }
	
	@Override
    public void showUIOper3() {
		goToUiMantCasaTarjetaEliminar();
    }
	
	@Override
    public void showUIOper4() {
		goToUiMantCasaTarjetaDetalle();
    }
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantCasaTarjetaInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCasaTarjetaActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCasaTarjetaEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantCasaTarjetaDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridCasaTarjeta getGrid() {
		return grid;
	}
	
	
}
