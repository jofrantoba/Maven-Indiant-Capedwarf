package com.indiana.view.admin.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.indiana.server.model.bean.TipoCambio;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridTipoCambio extends DataGrid<TipoCambio> {
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	private List<TipoCambio> data = new ArrayList<TipoCambio>();
	private final SingleSelectionModel<TipoCambio> selectionModel = new SingleSelectionModel<TipoCambio>();
	private FilteredListDataProvider<TipoCambio> dataProvider = new FilteredListDataProvider<TipoCambio>(
			new IFilter<TipoCambio>() {

				@Override
				public boolean isValid(TipoCambio value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getMonedaOrigen().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridTipoCambio() {
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
		// MyResource.INSTANCE.getStlGridData().ensureInjected();
		// this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
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
	}

	private void initColumns() {       
        this.addColumn(code, "ID");
        this.addColumn(monedaOrigen, "MONEDA ORIGEN"); 
        this.addColumn(monedaDestino, "MONEDA DESTINO");
        this.addColumn(precioCompra, "PRECIO COMPRA");
        this.addColumn(precioVenta, "PRECIO VENTA");
        this.addColumn(fecha, "FECHA");
        this.setColumnWidth(code, 8, Unit.EM);           
    }	
	
	private Column<TipoCambio, String> code = new Column<TipoCambio, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoCambio object) {
			return object.getCodeTipoCambio();
		}

	};

	private Column<TipoCambio, String> monedaOrigen = new Column<TipoCambio, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoCambio object) {
			return object.getCodePaisMonedaOrigen();
		}

	};

	private Column<TipoCambio, String> monedaDestino = new Column<TipoCambio, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoCambio object) {
			return object.getCodePaisMonedaDestino();
		}
	};

	private Column<TipoCambio, Number> precioCompra = new Column<TipoCambio, Number>(
			new NumberCell()) {

		@Override
		public Number getValue(TipoCambio object) {
			return object.getPrecioCompra();
		}
	};

	private Column<TipoCambio, Number> precioVenta = new Column<TipoCambio, Number>(
			new NumberCell()) {

		@Override
		public Number getValue(TipoCambio object) {
			return object.getPrecioVenta();
		}

	};

	private Column<TipoCambio, Date> fecha = new Column<TipoCambio, Date>(
			new DateCell(dateFormat)){

		@Override
		public Date getValue(TipoCambio object) {
			return object.getFecha();
		}

	};
	
	public void setData(List<TipoCambio> data) {
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

	public List<TipoCambio> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoCambio> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoCambio> getDataProvider() {
		return dataProvider;
	}
}
