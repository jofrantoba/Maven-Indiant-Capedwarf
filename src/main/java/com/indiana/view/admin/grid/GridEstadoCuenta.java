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
import com.indiana.server.model.bean.EstadoCuenta;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridEstadoCuenta extends DataGrid<EstadoCuenta> {

    private List<EstadoCuenta> data = new ArrayList<EstadoCuenta>();
    private final SingleSelectionModel<EstadoCuenta> selectionModel = new SingleSelectionModel<EstadoCuenta>();
    private FilteredListDataProvider<EstadoCuenta> dataProvider = new FilteredListDataProvider<EstadoCuenta>(new IFilter<EstadoCuenta>() {

        @Override
        public boolean isValid(EstadoCuenta value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridEstadoCuenta() {
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
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);
    }
    
    private void initStyle(){           
        //MyResource.INSTANCE.getStlGridData().ensureInjected();	
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);      
    }

    private void initColumns() {       
        this.addColumn(code, "ID");
        this.addColumn(descripcion, "DESCRIPCION");                                
        this.setColumnWidth(code, 8, Unit.EM);                           
    }    
              

    public Column<EstadoCuenta, String> code
            = new Column<EstadoCuenta, String>(new TextCell()) {

                @Override
                public String getValue(EstadoCuenta object) {
                    return object.getCodeEstadoCuenta();
                }

            };        
    
    public Column<EstadoCuenta, String> descripcion
            = new Column<EstadoCuenta, String>(new TextCell()) {

                @Override
                public String getValue(EstadoCuenta object) {
                    return object.getDescripcion();
                }

            };           

    public void setData(List<EstadoCuenta> data) {
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

    public List<EstadoCuenta> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<EstadoCuenta> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<EstadoCuenta> getDataProvider() {
        return dataProvider;
    }
}
