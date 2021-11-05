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
import com.indiana.server.model.bean.TipoNotificacion;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridTipoNotificacion extends DataGrid<TipoNotificacion> {

	private List<TipoNotificacion> data = new ArrayList<TipoNotificacion>();
	private final SingleSelectionModel<TipoNotificacion> selectionModel = new SingleSelectionModel<TipoNotificacion>();
	private FilteredListDataProvider<TipoNotificacion> dataProvider = new FilteredListDataProvider<TipoNotificacion>(
			new IFilter<TipoNotificacion>() {

				@Override
				public boolean isValid(TipoNotificacion value, String filter) {
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

	public GridTipoNotificacion() {
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
	}

	private void initColumns() {
		this.addColumn(code, "ID");
		this.addColumn(descripcion, "TIPO");
		this.addColumn(mensaje, "MENSAJE");
		this.setColumnWidth(code, 8, Unit.EM);
	}

	private Column<TipoNotificacion, String> code = new Column<TipoNotificacion, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoNotificacion object) {
			return object.getCodeTipoNotificacion();
		}

	};

	private Column<TipoNotificacion, String> descripcion = new Column<TipoNotificacion, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoNotificacion object) {
			return object.getDescripcion();
		}

	};
	
	private Column<TipoNotificacion, String> mensaje = new Column<TipoNotificacion, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoNotificacion object) {
			return object.getMensaje();
		}
	};

	public void setData(List<TipoNotificacion> data) {
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

	public List<TipoNotificacion> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoNotificacion> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoNotificacion> getDataProvider() {
		return dataProvider;
	}
}


