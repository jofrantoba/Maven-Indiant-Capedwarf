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
import com.indiana.server.model.bean.TipoMovimiento;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridTipoMovimiento extends DataGrid<TipoMovimiento> {

	private List<TipoMovimiento> data = new ArrayList<TipoMovimiento>();
	private final SingleSelectionModel<TipoMovimiento> selectionModel = new SingleSelectionModel<TipoMovimiento>();
	private FilteredListDataProvider<TipoMovimiento> dataProvider = new FilteredListDataProvider<TipoMovimiento>(
			new IFilter<TipoMovimiento>() {

				@Override
				public boolean isValid(TipoMovimiento value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getTipo()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridTipoMovimiento() {
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
		this.addColumn(descripcion, "DESCRIPCION");
		this.addColumn(tipo, "TIPO");
		this.addColumn(estado, "ESTADO");
		this.setColumnWidth(code, 8, Unit.EM);
	}

	private Column<TipoMovimiento, String> code = new Column<TipoMovimiento, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoMovimiento object) {
			return object.getCodeTipoMovimiento();
		}

	};

	private Column<TipoMovimiento, String> descripcion = new Column<TipoMovimiento, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoMovimiento object) {
			return object.getDescripcion();
		}

	};
	private Column<TipoMovimiento, String> tipo = new Column<TipoMovimiento, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoMovimiento object) {
			return object.getTipo();
		}

	};
	private Column<TipoMovimiento, String> estado = new Column<TipoMovimiento, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoMovimiento object) {
			return object.getEstado();
		}

	};
	public void setData(List<TipoMovimiento> data) {
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

	public List<TipoMovimiento> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoMovimiento> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoMovimiento> getDataProvider() {
		return dataProvider;
	}
}

