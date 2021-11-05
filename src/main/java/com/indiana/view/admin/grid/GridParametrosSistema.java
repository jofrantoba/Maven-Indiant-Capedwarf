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
import com.indiana.server.model.bean.ParametrosSistema;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridParametrosSistema extends DataGrid<ParametrosSistema> {

	private List<ParametrosSistema> data = new ArrayList<ParametrosSistema>();
	private final SingleSelectionModel<ParametrosSistema> selectionModel = new SingleSelectionModel<ParametrosSistema>();
	private FilteredListDataProvider<ParametrosSistema> dataProvider = new FilteredListDataProvider<ParametrosSistema>(
			new IFilter<ParametrosSistema>() {

				@Override
				public boolean isValid(ParametrosSistema value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getDescripcion()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridParametrosSistema() {
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

	}

	private void initColumns() {
		this.addColumn(code, "ID");
		this.addColumn(descripcion, "DESCRIPCION");
		this.addColumn(valor, "VALOR");
		this.addColumn(unidad, "UNIDAD");
		this.addColumn(tipoDato, "TIPO DATO");
		this.addColumn(estado, "ESTADO");
		this.setColumnWidth(code, 8, Unit.EM);
	}

	private Column<ParametrosSistema, String> code = new Column<ParametrosSistema, String>(
			new TextCell()) {

		@Override
		public String getValue(ParametrosSistema object) {
			return object.getCodeParametrosSistema();
		}

	};

	private Column<ParametrosSistema, String> descripcion= new Column<ParametrosSistema, String>(
			new TextCell()) {

		@Override
		public String getValue(ParametrosSistema object) {
			return object.getDescripcion();
		}

	};
	private Column<ParametrosSistema, String> tipoDato = new Column<ParametrosSistema, String>(
			new TextCell()) {

		@Override
		public String getValue(ParametrosSistema object) {
			return object.getTipoDato();
		}

	};
	private Column<ParametrosSistema, String> valor = new Column<ParametrosSistema, String>(
			new TextCell()) {

		@Override
		public String getValue(ParametrosSistema object) {
			return object.getValor();
		}

	};
	private Column<ParametrosSistema, String> unidad = new Column<ParametrosSistema, String>(
			new TextCell()) {

		@Override
		public String getValue(ParametrosSistema object) {
			return object.getUnidad();
		}

	};
	private Column<ParametrosSistema, String> estado = new Column<ParametrosSistema, String>(
			new TextCell()) {

		@Override
		public String getValue(ParametrosSistema object) {
			return object.getEstado();
		}

	};

	public void setData(List<ParametrosSistema> data) {
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

	public List<ParametrosSistema> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<ParametrosSistema> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ParametrosSistema> getDataProvider() {
		return dataProvider;
	}
}

