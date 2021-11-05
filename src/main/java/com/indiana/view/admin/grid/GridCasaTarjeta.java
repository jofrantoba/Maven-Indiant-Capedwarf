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
import com.indiana.server.model.bean.CasaTarjeta;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridCasaTarjeta extends DataGrid<CasaTarjeta> {

    private List<CasaTarjeta> data = new ArrayList<CasaTarjeta>();
    private final SingleSelectionModel<CasaTarjeta> selectionModel = new SingleSelectionModel<CasaTarjeta>();
    private FilteredListDataProvider<CasaTarjeta> dataProvider = new FilteredListDataProvider<CasaTarjeta>(new IFilter<CasaTarjeta>() {

        @Override
        public boolean isValid(CasaTarjeta value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridCasaTarjeta() {
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
              

    private Column<CasaTarjeta, String> code
            = new Column<CasaTarjeta, String>(new TextCell()) {

                @Override
                public String getValue(CasaTarjeta object) {
                    return object.getCodeCasaTarjeta();
                }

            };        
    
    private Column<CasaTarjeta, String> descripcion
            = new Column<CasaTarjeta, String>(new TextCell()) {

                @Override
                public String getValue(CasaTarjeta object) {
                    return object.getDescripcion();
                }

            };                

    public void setData(List<CasaTarjeta> data) {
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

    public List<CasaTarjeta> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<CasaTarjeta> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<CasaTarjeta> getDataProvider() {
        return dataProvider;
    }
}
