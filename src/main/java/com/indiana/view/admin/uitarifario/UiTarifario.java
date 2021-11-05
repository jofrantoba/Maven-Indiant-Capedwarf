package com.indiana.view.admin.uitarifario;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.indiana.view.admin.grid.GridTarifario;
import com.indiana.view.uiutil.TableToExcel;
import com.indiana.view.uiutil.UiFormMantenimiento;

import gwt.material.design.client.ui.MaterialButton;

public class UiTarifario extends UiFormMantenimiento implements InterUiTarifario{
	public  static final String MODODESACTIVAR= "DESACTIVAR";
	protected GridTarifario grid;
	private MaterialButton btnDesactivar;

	
	public UiTarifario(){
		initComponents();
		initListener();
	}
	
	private void initComponents(){
		grid=new GridTarifario();
        grid.setMinimumTableWidth(1024, Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager()); 
        this.btnDesactivar=new MaterialButton();
        btnDesactivar.setText("DESACTIVAR");
        this.addComponent(btnDesactivar);
	}
	
	private void initListener(){
		btnDesactivar.addClickHandler(clickHandler);
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantTarifarioInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantTarifarioActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantTarifarioEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantTarifarioDetalle();
	}
	
	@Override
	public void showUIOper5() {
		TableToExcel.save(grid, this.getClass().getSimpleName()+(new Date()).getTime());
	}

	@Override
	public void goToUiMantTarifarioInsertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTarifarioActualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTarifarioEliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiMantTarifarioDetalle() {
		// TODO Auto-generated method stub
		
	}

	public GridTarifario getGrid() {
		return grid;
	}

	@Override
	public void gotToUiMantarifarioDesactivar() {
		// TODO Auto-generated method stub
		
	}
	
	ClickHandler clickHandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource().equals(btnDesactivar)){
				gotToUiMantarifarioDesactivar();
			}
		}
		
	};
}
