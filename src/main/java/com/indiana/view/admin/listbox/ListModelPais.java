package com.indiana.view.admin.listbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.indiana.server.model.bean.Pais;

import gwt.material.design.client.ui.MaterialListBox;

public class ListModelPais extends MaterialListBox{	
	 	private Pais selectedItem;	    
		private ArrayList<Pais> data = new ArrayList<>();

	    public ListModelPais() {
	    	super();
	    }


	    public void setData(List<Pais> datos) {
	        if(data.isEmpty()){
	        data.addAll(datos);        
	        }else{
	            clear();
	            data.addAll(datos);            
	        }
	        for (int i = 0; i < data.size(); i++) {
	            insertItem(data.get(i).getNombre(), data.get(i).getIdPais().toString(), i);
	        }
	        if(!data.isEmpty()){
	            selectedItem=data.get(0);
	            setSelectedIndex(0);
	        }
	    }	    

	    @Override
	    public int getItemCount() {
	        try {
	            return data.size();
	        } catch (NullPointerException ex) {
	            return 0;
	        }
	    }

	    public void clean() {
	        data.clear();
	        clear();
	    }

	    public Pais getElement(int index) throws ArrayIndexOutOfBoundsException {
	        try {
	            return data.get(index);
	        } catch (ArrayIndexOutOfBoundsException ex) {
	            throw ex;
	        }
	    }

	    public Pais getElement(String codigo) {        
	        Iterator<Pais> i = data.iterator();
	        while (i.hasNext()) {
	            Pais bean = (Pais) i.next();
	            if (bean.getCodePais().toString().equals(codigo)) {                
	                return bean;
	            }
	        }
	        return null;
	    }

	    @Override
	    public void setSelectedIndex(int index) {        
	        getSelectElement().setSelectedIndex(index);
	        selectedItem=data.get(getSelectElement().getSelectedIndex());
	    }
	        
	    public void setSelectedItem(String code) {  
	        int index=-1;
	        for(int i=0;i<data.size();i++){
	            Pais bean = data.get(i);
	            if (bean.getCodePais().toString().equals(code)) {                
	                index=i;
	            }
	        }
	        getSelectElement().setSelectedIndex(index);
	        selectedItem=data.get(getSelectElement().getSelectedIndex());
	    }
	    
	    
	    public Pais getSelectedItem() {
	        selectedItem=data.get(getSelectElement().getSelectedIndex());
	        return selectedItem;
	    }
	    
}
