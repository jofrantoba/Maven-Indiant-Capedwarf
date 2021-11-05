package com.indiana.view.admin.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.indiana.server.model.bean.Localidad;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridLocalidad extends DataGrid<Localidad> {

	private List<Localidad> data = new ArrayList<Localidad>();
	private final SingleSelectionModel<Localidad> selectionModel = new SingleSelectionModel<Localidad>();
	private FilteredListDataProvider<Localidad> dataProvider = new FilteredListDataProvider<Localidad>(
			new IFilter<Localidad>() {

				@Override
				public boolean isValid(Localidad value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombreRegion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridLocalidad() {
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
	}

	private void initColumns() {
		this.addColumn(code, "ID");
		this.addColumn(nombre, "LOCALIDAD");
		this.addColumn(region, "REGION");
		this.addColumn(pais, "PAIS");
		this.setColumnWidth(code, 8, Unit.EM);
	}

	private Column<Localidad, String> code = new Column<Localidad, String>(
			new TextCell()) {

		@Override
		public String getValue(Localidad object) {
			return object.getCodeLocalidad();
		}

	};

	private Column<Localidad, String> nombre = new Column<Localidad, String>(
			new TextCell()) {

		@Override
		public String getValue(Localidad object) {
			return object.getNombre();
		}

	};

	private Column<Localidad, String> region = new Column<Localidad, String>(
			new TextCell()) {

		@Override
		public String getValue(Localidad object) {
			return object.getNombreRegion();
		}

	};
	private Column<Localidad, String> pais = new Column<Localidad, String>(
			new TextCell()) {

		@Override
		public String getValue(Localidad object) {
			return object.getNombrePais();
		}

	};
	
	public void setData(List<Localidad> data) {
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

	public List<Localidad> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<Localidad> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<Localidad> getDataProvider() {
		return dataProvider;
	}
}
