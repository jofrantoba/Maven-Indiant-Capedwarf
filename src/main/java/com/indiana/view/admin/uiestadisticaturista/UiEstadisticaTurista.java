package com.indiana.view.admin.uiestadisticaturista;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.indiana.client.resource.MyResource;
import com.indiana.client.service.ServiceGestionUsuario;
import com.indiana.client.service.ServiceGestionUsuarioAsync;
import com.indiana.server.model.bean.Pais;
import com.indiana.server.model.bean.TuristaInteres;

import gwt.material.design.client.ui.MaterialLabel;

public class UiEstadisticaTurista extends FlexTable implements InterUiEstadisticaTurista, ClickHandler {
	public final ServiceGestionUsuarioAsync SERVICE = GWT.create(ServiceGestionUsuario.class);	
	private ToggleButton toggleAhora;
	private ToggleButton toggleDia;
	private ToggleButton toggleSemana;
	private ToggleButton toggleMes;
	private ToggleButton toggleEnCualquierMomento;

	private ToggleButton toggleProcedencia;
	private ToggleButton toggleRangoEdad;
	private ToggleButton toggleDestinos;

	public ListBox comboBox;
	public ListBox comboBoxGrupoEtario;
	public MaterialLabel labelBox;

	public static List<Pais> listPaises;

	public FlexTable flexLeftLabel;
	public FlexTable flexLeft3Chart;

	public static TuristaInteres valorModa1 = null;
	public static TuristaInteres valorModa2 = null;
	public static TuristaInteres valorModa3 = null;

	private static final String PAIS_DEFECTO = "PERU";
	private static final String RANGO_EDAD_DEFECTO="18-25";
	public PieChart chart = null;
	
	public UiEstadisticaTurista() {
		initComponents();
		initListener();
		initStyle();
	}

	// Intentar colocar un Horizontal Panel
	private void initComponents() {

		toggleAhora = new ToggleButton("Ahora");
		toggleDia = new ToggleButton("Dia");
		toggleSemana = new ToggleButton("Semana");
		toggleMes = new ToggleButton("Mes");
		toggleEnCualquierMomento = new ToggleButton("En cualquier momento");

		toggleProcedencia = new ToggleButton("Lugar Procedencia");
		toggleRangoEdad = new ToggleButton("Grupo Etario");
		toggleDestinos = new ToggleButton("Destinos");

		comboBox = new ListBox();
		comboBox.setWidth("150px");
		llenarComboBox();
		comboBox.setVisible(true);
		comboBox.getElement().setId("mc_comboboxpais");

		comboBoxGrupoEtario= new ListBox();
		comboBoxGrupoEtario.setWidth("150px");
		comboBoxGrupoEtario.setVisible(true);	
		
		labelBox = new MaterialLabel("Elegir Pais:");

		FlexTable flexTop = new FlexTable();
		flexTop.setWidget(0, 0, toggleAhora);
		flexTop.setWidget(0, 1, toggleDia);
		flexTop.setWidget(0, 2, toggleSemana);
		flexTop.setWidget(0, 3, toggleMes);
		flexTop.setWidget(0, 4, toggleEnCualquierMomento);
		this.setWidget(0, 0, flexTop);

		FlexTable flexLeft0 = new FlexTable();
		flexLeft0.setWidget(0, 0, toggleProcedencia);
		flexLeft0.setWidget(1, 0, toggleRangoEdad);
		flexLeft0.setWidget(2, 0, toggleDestinos);
		this.setWidget(1, 0, flexLeft0);

		flexLeftLabel = new FlexTable();
		flexLeftLabel.setWidget(0, 0, labelBox);
		flexLeftLabel.setWidget(1, 0, comboBox);
		this.setWidget(1, 1, flexLeftLabel);

		flexLeft3Chart = new FlexTable();
		flexLeft3Chart.getFlexCellFormatter().setRowSpan(0, 0, 10);
		this.setWidget(0, 2, flexLeft3Chart);
		
		cargarDataGraficaProcedencia(PAIS_DEFECTO);		
		
		FlexCellFormatter cellFormato = new FlexCellFormatter();
		cellFormato.setColSpan(0, 0, 2);
		cellFormato.setRowSpan(0, 2, 26);
		cellFormato.setColSpan(0, 2, 6);
		cellFormato.setHeight(0, 0, "20px");
		cellFormato.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		cellFormato.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		cellFormato.setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		cellFormato.setVerticalAlignment(0, 5, HasVerticalAlignment.ALIGN_TOP);
		this.setCellFormatter(cellFormato);// solo aplica cuando hacemos// referencia a una clase y no// funciona con un objeto o// instancia
		this.setCellPadding(0);
		this.setCellSpacing(0);
		this.getElement().setId("mc_flextable");

		
	}
	
	@Override
	public FlexTable getSimpleLayoutPanel() {
		return flexLeft3Chart;
	}
	@Override
	public void initListener() {
		comboBox.addChangeHandler(changeHandler);
		comboBoxGrupoEtario.addChangeHandler(changeHandlerRangoEdad);
	}
	@Override
	public void initStyle() {
		MyResource.INSTANCE.getStlUiHomeNegocio().ensureInjected();
		this.getElement().setId("mc_flextable");
		this.getElement().setId("mc_comboboxpais");
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		toggleAhora.addClickHandler(listenerToggleAhora);
		toggleDia.addClickHandler(listenerToggleDia);
		toggleSemana.addClickHandler(listenerToggleSemana);
		toggleMes.addClickHandler(listenerToggleMes);
		toggleEnCualquierMomento.addClickHandler(listenerToggleEnCualquierMomento);
		toggleProcedencia.addClickHandler(listenerToggleProcedencia);
		toggleRangoEdad.addClickHandler(listenerToggleRangoEdad);
		toggleDestinos.addClickHandler(listenerToggleDestinos);
	}

	ClickHandler listenerToggleAhora = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}
	};
	ClickHandler listenerToggleDia = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}
	};
	ClickHandler listenerToggleSemana = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}
	};
	ClickHandler listenerToggleMes = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}
	};
	ClickHandler listenerToggleEnCualquierMomento = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}
	};
	public FlexTable returnConentFlex(){
		return this;
	}
	ClickHandler listenerToggleProcedencia = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub			
			flexLeftLabel.setWidget(0, 0, labelBox);
			flexLeftLabel.setWidget(1, 0, comboBox);
			returnConentFlex().setWidget(1, 1, flexLeftLabel);
			
			flexLeft3Chart.getFlexCellFormatter().setRowSpan(0, 0, 10);
			returnConentFlex().setWidget(0, 2, flexLeft3Chart);
		}
	};
	
	
	ClickHandler listenerToggleRangoEdad = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			labelBox.setText("Elegir Rango Edad");
			flexLeftLabel.setWidget(0, 0, labelBox);
			flexLeftLabel.setWidget(1, 0, comboBoxGrupoEtario);
			returnConentFlex().setWidget(1, 1, flexLeftLabel);	
			
			flexLeft3Chart.getFlexCellFormatter().setRowSpan(0, 0, 10);
			returnConentFlex().setWidget(0, 2, flexLeft3Chart);
			
			cargarDataGraficaRangoEdad(RANGO_EDAD_DEFECTO);
		}
	};

	ClickHandler listenerToggleDestinos = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public void llenarComboBox() {
		// TODO Auto-generated method stub
		comboBox.addItem("Peru");
		comboBox.addItem("Espana");
		comboBox.addItem("Brasil");

		if (listPaises != null) {
			for (int i = 0; i < listPaises.size();) {
				comboBox.addItem(listPaises.get(i).getNombre());
			}
		}
	}

	ChangeHandler changeHandler = new ChangeHandler() {

		@Override
		public void onChange(ChangeEvent event) {
			// TODO Auto-generated method stub
			cargarDataGraficaProcedencia(comboBox.getSelectedValue());
		}
	};

	ChangeHandler changeHandlerRangoEdad = new ChangeHandler() {

		@Override
		public void onChange(ChangeEvent event) {
			// TODO Auto-generated method stub
			cargarDataGraficaRangoEdad(comboBoxGrupoEtario.getSelectedValue());
		}
	};

	
	@Override
	public void cargarDataGraficaProcedencia(String nacionalidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cargarDataGraficaRangoEdad(String edad) {
		// TODO Auto-generated method stub
		
	}
}
