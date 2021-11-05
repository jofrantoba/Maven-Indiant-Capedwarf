package com.indiana.view.negocio.uihomesesionnegocio;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ToggleButton;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;
import com.indiana.client.resource.MyResource;

import gwt.material.design.client.ui.MaterialLabel;

public class UiHomeSesionNegocio extends FlexTable implements InterUiHomeSesionNegocio, ClickHandler {

	private ToggleButton toggleAhora;
	private ToggleButton toggleDia;
	private ToggleButton toggleSemana;
	private ToggleButton toggleMes;
	private ToggleButton toggleEnCualquierMomento;

	private ToggleButton toggleRangoEdad;
	private ToggleButton toggleInteres;
	private ToggleButton toggleDestinos;

	public UiHomeSesionNegocio() {
		initComponents();
		initListener();
		initialize();
		initStyle();
	}

	private void initComponents() {

		toggleAhora = new ToggleButton("Ahora");
		toggleDia = new ToggleButton("Dia");
		toggleSemana = new ToggleButton("Semana");
		toggleMes = new ToggleButton("Mes");
		toggleEnCualquierMomento = new ToggleButton("En cualquier momento");

		toggleRangoEdad = new ToggleButton("Edad");
		toggleInteres = new ToggleButton("Intereses");
		toggleDestinos = new ToggleButton("Destinos");

		this.setWidget(0, 0, toggleAhora);
		this.setWidget(0, 1, toggleDia);
		this.setWidget(0, 2, toggleSemana);
		this.setWidget(0, 3, toggleMes);
		this.setWidget(0, 4, toggleEnCualquierMomento);

		this.setWidget(1, 0, toggleRangoEdad);
		this.setWidget(2, 0, toggleInteres);
		this.setWidget(3, 0, toggleDestinos);

		FlexCellFormatter cellFormato = new FlexCellFormatter();
		cellFormato.setRowSpan(1, 1, 4);
		cellFormato.setColSpan(1, 1, 4);
		this.setCellFormatter(cellFormato);
		this.setCellPadding(0);
		this.setCellSpacing(0);

	}

	public static FlexTable returnFlex() {
		FlexTable flexDown = new FlexTable();
		flexDown.setWidth("400px");
		flexDown.setWidget(5, 2, new MaterialLabel("Turistas cerca a tu Negocio"));
		flexDown.setWidget(6, 2, new MaterialLabel("Visitas a perfil de Negocio"));
		flexDown.setWidget(7, 2, new MaterialLabel("Conquistas a Negocio"));
		flexDown.setWidget(8, 2, new MaterialLabel("Total Noticias Divulgadas"));
		flexDown.setWidget(9, 2, new MaterialLabel("Total Noticias Compartidas"));

		flexDown.setWidget(5, 3, new MaterialLabel("23"));
		flexDown.setWidget(6, 3, new MaterialLabel("33"));
		flexDown.setWidget(7, 3, new MaterialLabel("43"));
		flexDown.setWidget(8, 3, new MaterialLabel("53"));
		flexDown.setWidget(9, 3, new MaterialLabel("63"));		
		flexDown.getElement().setId("mc_flextable");
		return flexDown;
	}

	private FlexTable getSimpleLayoutPanel() {
		return this;
	}

	private LineChart chart;

	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {
			@Override
			public void run() {	
				chart = new LineChart();
				getSimpleLayoutPanel().setWidget(1, 1, chart);
				draw();
			}
		});
	}

	private void draw() {
		String[] countries = new String[] { 
				"Turista cerca Negocio", "Visita Perfil", "Conquista Negocio", "Noticias Divulgadas", "Noticias Compartidas" };
		
		int[] years = new int[] { 2013, 2014, 2015, 2016, 2017,2018};
		int[][] values = new int[][] { { 3, 3, 3, 3, 3, 3 },
				{ 0, 0, 0, 0, 2, 0 },
				{ 3, 2, 2, 2, 2, 3 },
				{ 2, 3, 3, 3, 3, 2 } };

		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Year");
		for (int i = 0; i < countries.length; i++) {
			dataTable.addColumn(ColumnType.NUMBER, countries[i]);
		}
		
		dataTable.addRows(years.length);
		for (int i = 0; i < years.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(years[i]));
		}
		for (int col = 0; col < values.length; col++) {
			for (int row = 0; row < values[col].length; row++) {
				dataTable.setValue(row, col + 1, values[col][row]);
			}
		}

		LineChartOptions options = LineChartOptions.create();
		options.setFontName("Tahoma");
		options.setTitle("Estadisticas de Negocio");
		options.setHAxis(HAxis.create("Turistas"));
		options.setVAxis(VAxis.create("Intereses"));
		chart.draw(dataTable, options);

	}

	private void initListener() {

	}

	private void initStyle() {
		MyResource.INSTANCE.getStlUiHomeNegocio().ensureInjected();
		this.getElement().setId("mc_flextable");
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
		toggleAhora.addClickHandler(listenerToggleAhora);
		toggleDia.addClickHandler(listenerToggleDia);
		toggleSemana.addClickHandler(listenerToggleSemana);
		toggleMes.addClickHandler(listenerToggleMes);
		toggleEnCualquierMomento.addClickHandler(listenerToggleEnCualquierMomento);

		toggleRangoEdad.addClickHandler(listenerToggleRangoEdad);
		toggleInteres.addClickHandler(listenerToggleInteres);
		toggleDestinos.addClickHandler(listenerToggleDestinos);

	}

	ClickHandler listenerToggleAhora= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
ClickHandler listenerToggleDia= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
ClickHandler listenerToggleSemana= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
ClickHandler listenerToggleMes= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
ClickHandler listenerToggleEnCualquierMomento= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
ClickHandler listenerToggleRangoEdad= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
ClickHandler listenerToggleInteres= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
	
ClickHandler listenerToggleDestinos= new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
}
