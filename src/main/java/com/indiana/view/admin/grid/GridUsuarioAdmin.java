package com.indiana.view.admin.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.indiana.server.model.bean.UsuarioAdmin;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridUsuarioAdmin extends DataGrid<UsuarioAdmin> {

	private List<UsuarioAdmin> data = new ArrayList<UsuarioAdmin>();
	private final SingleSelectionModel<UsuarioAdmin> selectionModel = new SingleSelectionModel<UsuarioAdmin>();
	private FilteredListDataProvider<UsuarioAdmin> dataProvider = new FilteredListDataProvider<UsuarioAdmin>(
			new IFilter<UsuarioAdmin>() {

				@Override
				public boolean isValid(UsuarioAdmin value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombre()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridUsuarioAdmin() {
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
		this.addColumn(nombre, "NOMBRES");
		this.addColumn(apellido, "APELLIDOS");
		this.addColumn(dni, "DNI");
		this.addColumn(telefono, "TELEFONO");
		this.addColumn(correo, "CORREO");		
		this.addColumn(estado, "ESTADO");
	}

	public Column<UsuarioAdmin, String> code = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getCodeUsuarioAdmin();
		}

	};

	public Column<UsuarioAdmin, String> nombre = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getNombre();
		}

	};
	
	public Column<UsuarioAdmin, String> apellido = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getApellido();
		}

	};
	
	public Column<UsuarioAdmin, String> dni = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getDni();
		}

	};
	
	public Column<UsuarioAdmin, String> telefono = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getTelefono();
		}

	};
	
	public Column<UsuarioAdmin, String> correo = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getCorreo();
		}

	};
	
	public Column<UsuarioAdmin, String> estado = new Column<UsuarioAdmin, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioAdmin object) {
			return object.getBeanCuentaAdmin().getEstadoCuenta().getDescripcion();
		}

	};

	public void setData(List<UsuarioAdmin> data) {
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

	public List<UsuarioAdmin> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<UsuarioAdmin> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<UsuarioAdmin> getDataProvider() {
		return dataProvider;
	}
	
}

