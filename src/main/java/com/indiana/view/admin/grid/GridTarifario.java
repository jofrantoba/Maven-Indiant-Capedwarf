package com.indiana.view.admin.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.indiana.server.model.bean.Tarifario;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridTarifario extends DataGrid<Tarifario> {
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	private List<Tarifario> data = new ArrayList<Tarifario>();
	private final SingleSelectionModel<Tarifario> selectionModel = new SingleSelectionModel<Tarifario>();
	private FilteredListDataProvider<Tarifario> dataProvider = new FilteredListDataProvider<Tarifario>(
			new IFilter<Tarifario>() {

				@Override
				public boolean isValid(Tarifario value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombreTipoSuscripcion()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridTarifario() {
		initComponents();
		initStyle();
	}

	private void initComponents() {
		this.setWidth("100%");
		this.setHeight("90%");
		initColumns();
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		this.setVisible(true);
		this.setSelectionModel(selectionModel);
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(SimplePager.TextLocation.CENTER,
				pagerResources, false, 0, true);
		pager.setDisplay(this);
		pager.setVisible(true);
	}

	private void initStyle() {

		this.getColumn(0).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(1).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(2).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(3).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(4).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(5).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(6).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(7).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
	
	}

	private void initColumns() {       
    
        this.addColumn(tipoSuscripcion, "TIPO SUSCRIPCION"); 
        this.addColumn(rangoInicial, "RANGO INICIAL");
        this.addColumn(rangoFinal, "RANGO FINAL");
        this.addColumn(precioUnitario, "P. UNITARIO");
        this.addColumn(pais, "PAIS");
        this.addColumn(estado, "ESTADO");
        this.addColumn(fechaInicial, "F. ACTIVACION");
        this.addColumn(fechaFinal, "F. DESACTIVACION");
    }	
	private Column<Tarifario, String> tipoSuscripcion = new Column<Tarifario, String>(
			new TextCell()) {

		@Override
		public String getValue(Tarifario object) {
			return object.getNombreTipoSuscripcion();
		}

	};
	private Column<Tarifario, Number> rangoInicial = new Column<Tarifario, Number>(
			new NumberCell()) {

		@Override
		public Number getValue(Tarifario object) {
			return object.getRangoInicial();
		}

	};
	private Column<Tarifario, Number> rangoFinal = new Column<Tarifario, Number>(
			new NumberCell()) {

		@Override
		public Number getValue(Tarifario object) {
			return object.getRangoFinal();
		}

	};
	private Column<Tarifario, Number> precioUnitario = new Column<Tarifario, Number>(
			new NumberCell()) {

		@Override
		public Number getValue(Tarifario object) {
			return object.getPrecioUnidad();
		}

	};

	private Column<Tarifario, String> pais = new Column<Tarifario, String>(
			new TextCell()) {

		@Override
		public String getValue(Tarifario object) {
			return object.getNombrePais();
		}
	};
	private Column<Tarifario, String> estado = new Column<Tarifario, String>(
			new TextCell()) {

		@Override
		public String getValue(Tarifario object) {
			return object.getEstado();
		}
	};
	private Column<Tarifario, java.util.Date> fechaInicial = new Column<Tarifario, java.util.Date>(
			new DateCell(dateFormat)) {

		@Override
		public java.util.Date getValue(Tarifario object) {
			return object.getFechaInicial();
		}
	};
	private Column<Tarifario, java.util.Date> fechaFinal = new Column<Tarifario, java.util.Date>(
			new DateCell(dateFormat)) {

		@Override
		public java.util.Date getValue(Tarifario object) {
			return object.getFechaFinal();
		}
	};

	public void setData(List<Tarifario> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		this.setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.refresh();
	}
    public void refreshGrid(){
    	this.selectionModel.clear();
    	this.setRowCount(this.data.size(), true);
        this.setRowData(0, this.data);
        this.setPageSize(this.data.size());
        dataProvider.setList(this.data);
        dataProvider.refresh();
    }

	public List<Tarifario> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<Tarifario> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<Tarifario> getDataProvider() {
		return dataProvider;
	}
}
