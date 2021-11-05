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
import com.indiana.server.model.bean.EntidadFinanciera;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridEntidadFinanciera extends DataGrid<EntidadFinanciera> {

	private List<EntidadFinanciera> data = new ArrayList<EntidadFinanciera>();
	private final SingleSelectionModel<EntidadFinanciera> selectionModel = new SingleSelectionModel<EntidadFinanciera>();
	private FilteredListDataProvider<EntidadFinanciera> dataProvider = new FilteredListDataProvider<EntidadFinanciera>(
			new IFilter<EntidadFinanciera>() {

				@Override
				public boolean isValid(EntidadFinanciera value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombreEntidadFinanciera()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridEntidadFinanciera() {
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
		this.addColumn(nombreEntidadFinanciera, "NOMBRE");
		this.addColumn(pais, "PAIS");
		this.addColumn(numeroCuenta, "Nro CUENTA");
		this.setColumnWidth(code, 8, Unit.EM);
	}

	private Column<EntidadFinanciera, String> code = new Column<EntidadFinanciera, String>(
			new TextCell()) {

		@Override
		public String getValue(EntidadFinanciera object) {
			return object.getCodeEntidadFinanciera();
		}

	};

	private Column<EntidadFinanciera, String> pais = new Column<EntidadFinanciera, String>(
			new TextCell()) {

		@Override
		public String getValue(EntidadFinanciera object) {
			return object.getNombrePais();
		}

	};
	
	private Column<EntidadFinanciera, String> nombreEntidadFinanciera = new Column<EntidadFinanciera, String>(
			new TextCell()) {

		@Override
		public String getValue(EntidadFinanciera object) {
			return object.getNombreEntidadFinanciera();
		}

	};
	private Column<EntidadFinanciera, String> numeroCuenta = new Column<EntidadFinanciera, String>(
			new TextCell()) {

		@Override
		public String getValue(EntidadFinanciera object) {
			return object.getNumeroCuenta();
		}

	};

	public void setData(List<EntidadFinanciera> data) {
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
	public List<EntidadFinanciera> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<EntidadFinanciera> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<EntidadFinanciera> getDataProvider() {
		return dataProvider;
	}
}
