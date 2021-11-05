package com.indiana.view.admin.uiestadomiembro;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.indiana.view.admin.grid.GridEstadoMiembro;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

public class UiEstadoMiembro extends UiFormMantenimiento implements InterUiEstadoMiembro{
	protected GridEstadoMiembro grid;
	
	public UiEstadoMiembro(){
		initComponents();
	}
	
	private void initComponents(){
		grid=new GridEstadoMiembro();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());        
	}
	@Override
	public void showUIOper1() {
		goToUiMantEstadoMiembroInsertar();
    }
	
	@Override
    public void showUIOper2() {
		goToUiMantEstadoMiembroActualizar();
    }
	
	@Override
    public void showUIOper3() {
		goToUiMantEstadoMiembroEliminar();
    }
	
	@Override
    public void showUIOper4() {
		goToUiMantEstadoMiembroDetalle();
    }
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}
	
	
	@Override
	public void goToUiMantEstadoMiembroInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoMiembroActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoMiembroEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantEstadoMiembroDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridEstadoMiembro getGrid() {
		return grid;
	}


}
