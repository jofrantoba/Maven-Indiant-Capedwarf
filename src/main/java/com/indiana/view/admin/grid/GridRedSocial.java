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
import com.indiana.server.model.bean.RedSocial;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridRedSocial extends DataGrid<RedSocial> {

	private List<RedSocial> data = new ArrayList<RedSocial>();
	private final SingleSelectionModel<RedSocial> selectionModel = new SingleSelectionModel<RedSocial>();
	private FilteredListDataProvider<RedSocial> dataProvider = new FilteredListDataProvider<RedSocial>(
			new IFilter<RedSocial>() {

				@Override
				public boolean isValid(RedSocial value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombreApp().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridRedSocial() {
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
		this.addColumn(nombreApp, "NOMBRE APP");
		this.addColumn(apiKey, "API KEY");
		this.addColumn(appSecret, "SHARED SECRET");
		this.addColumn(oauthToken, "OAUHT TOKEN");
		this.addColumn(oauthTokenSecret, "OAUTH TOKEN SECRET");
		this.addColumn(apiVersion, "API VERSION");
		this.addColumn(redSocial, "RED SOCIAL");
		this.setColumnWidth(code, 12, Unit.EM);
		this.setColumnWidth(nombreApp, 20, Unit.EM);
		this.setColumnWidth(apiKey, 20, Unit.EM);
		this.setColumnWidth(appSecret, 20, Unit.EM);
		this.setColumnWidth(oauthToken, 30, Unit.EM);
		this.setColumnWidth(oauthTokenSecret, 20, Unit.EM);
		this.setColumnWidth(apiVersion, 20, Unit.EM);
		this.setColumnWidth(redSocial, 20, Unit.EM);
	}

	private Column<RedSocial, String> code = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getCodeRedSocial();
		}

	};
	private Column<RedSocial, String> nombreApp = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getNombreApp();
		}
		

	};
	private Column<RedSocial, String> apiKey = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getApiKey();
		}
		

	};
	private Column<RedSocial, String> appSecret = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getSharedSecret();
		}
		

	};
	private Column<RedSocial, String> oauthToken = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getOauthToken();
		}
		

	};
	private Column<RedSocial, String> oauthTokenSecret = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getOauthTokenSecret();
		}
		

	};
	private Column<RedSocial, String> apiVersion = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getApiVersion();
		}
		

	};
	private Column<RedSocial, String> redSocial = new Column<RedSocial, String>(
			new TextCell()) {

		@Override
		public String getValue(RedSocial object) {
			return object.getRedSocial();
		}
		

	};

	public void setData(List<RedSocial> data) {
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

	public List<RedSocial> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<RedSocial> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<RedSocial> getDataProvider() {
		return dataProvider;
	}
}

