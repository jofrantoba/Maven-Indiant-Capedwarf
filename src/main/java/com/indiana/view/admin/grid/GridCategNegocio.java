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
import com.indiana.server.model.bean.CategoriaNegocio;
import com.indiana.view.uiutil.FilteredListDataProvider;
import com.indiana.view.uiutil.IFilter;

public class GridCategNegocio extends DataGrid<CategoriaNegocio> {

    private List<CategoriaNegocio> data = new ArrayList<CategoriaNegocio>();
    private final SingleSelectionModel<CategoriaNegocio> selectionModel = new SingleSelectionModel<CategoriaNegocio>();
    private FilteredListDataProvider<CategoriaNegocio> dataProvider = new FilteredListDataProvider<CategoriaNegocio>(new IFilter<CategoriaNegocio>() {

        @Override
        public boolean isValid(CategoriaNegocio value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getNombre().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridCategNegocio() {
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
    
    public void refreshGrid(){
    	this.selectionModel.clear();
    	this.setRowCount(this.data.size(), true);
        this.setRowData(0, this.data);
        this.setPageSize(this.data.size());
        dataProvider.setList(this.data);
        dataProvider.refresh();
    }

    private void initStyle(){           
        //MyResource.INSTANCE.getStlGridData().ensureInjected();	
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);  
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);  
    }

    private void initColumns() {       
        this.addColumn(code, "ID");
        this.addColumn(nombre, "DESCRIPCION"); 
        this.addColumn(estado, "ESTADO");
        this.setColumnWidth(code, 8, Unit.EM);                           
    }    
              

    private Column<CategoriaNegocio, String> code
            = new Column<CategoriaNegocio, String>(new TextCell()) {

                @Override
                public String getValue(CategoriaNegocio object) {
                    return object.getCodeCategoriaNegocio();
                }

            };        
    
    private Column<CategoriaNegocio, String> nombre
            = new Column<CategoriaNegocio, String>(new TextCell()) {

                @Override
                public String getValue(CategoriaNegocio object) {
                    return object.getNombre();
                }

            };    
            private Column<CategoriaNegocio, String> estado
            = new Column<CategoriaNegocio, String>(new TextCell()) {

                @Override
                public String getValue(CategoriaNegocio object) {
                    return object.getEstado();
                }

            }; 

    public void setData(List<CategoriaNegocio> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }
    
    

    public List<CategoriaNegocio> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<CategoriaNegocio> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<CategoriaNegocio> getDataProvider() {
        return dataProvider;
    }
}


