package com.indiana.view.admin.uiestadturista;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.indiana.server.model.bean.TuristaInteres;

import gwt.material.design.client.ui.MaterialToast;

public class UiEstadTuristaImpl extends UiEstadTurista{

	public UiEstadTuristaImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void cargarDataGraficaProcedencia(String nacionalidad) {		
		// TODO Auto-generated method stub		
		SERVICE.ieModaInteresTuristaByTuristaInteres(null, null, null, null, null, nacionalidad.toUpperCase(), new AsyncCallback<List<TuristaInteres>>() {			
			@Override
			public void onSuccess(List<TuristaInteres> result) {								
				if(result!=null){					
					if(result.size()>=3){
						valorModa1= result.get(0);
						valorModa2=result.get(1);
						valorModa3=result.get(2);							
						initialize();		
					}
				}					
			}			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MaterialToast.fireToast(caught.getLocalizedMessage());				
			}
		});		
	}
	
 	@Override

 	public void cargarDataGraficaRangoEdad(String edad) {		
 		SERVICE.ieModaInteresTuristaByTuristaInteres(null, null, null, edad, null, null, new AsyncCallback<List<TuristaInteres>>() {			
 			@Override
 			public void onSuccess(List<TuristaInteres> result) { 				
 				if(result!=null){
 					if(result.size()>=3){ 						
 						valorModa1= result.get(0);
 						valorModa2=result.get(1);
 						valorModa3=result.get(2);						
 						initialize();		
 					}
 				}					
 			}			
 			@Override
 			public void onFailure(Throwable caught) { 				
 				MaterialToast.fireToast(caught.getLocalizedMessage());				
 			}
 		});		
 	}
	
	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {
			@Override
			public void run() {
				chart = new PieChart();
				getSimpleLayoutPanel().setWidget(0, 0, chart);				
				draw(valorModa1,valorModa2,valorModa3);
			}
		});
	}

	private void draw(TuristaInteres valorModa1, TuristaInteres valorModa2, TuristaInteres valorModa3) {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Task");
		dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
		dataTable.addRows(3);
		dataTable.setValue(0, 0, valorModa1.getNombreInteres());
		dataTable.setValue(0, 1, valorModa1.getValorModa());
		dataTable.setValue(1, 0, valorModa2.getNombreInteres());
		dataTable.setValue(1, 1, valorModa2.getValorModa());
		dataTable.setValue(2, 0, valorModa3.getNombreInteres());
		dataTable.setValue(2, 1, valorModa3.getValorModa());
		PieChartOptions options = PieChartOptions.create();
		options.setFontName("Tahoma");
		options.setIs3D(false);
		options.setPieResidueSliceColor("#000000");
		options.setPieResidueSliceLabel("Otros");
		options.setWidth(500);
		options.setHeight(500);
		options.setSliceVisibilityThreshold(0.1);
		chart.draw(dataTable, options);

	}
}
