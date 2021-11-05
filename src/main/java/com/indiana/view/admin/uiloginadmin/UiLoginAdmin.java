package com.indiana.view.admin.uiloginadmin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.indiana.client.resource.MyResource;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.uiutil.BorderLayout;

import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialFooterCopyright;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialTextBox;

public class UiLoginAdmin extends Composite implements InterUiLoginAdmin,ClickHandler{
	private BorderLayout pnlContenedor;	
	private MaterialNavBar navBar;
	private MaterialNavBrand navBrand; 
	private MaterialFooterCopyright footCopy;
	private HTML lblCopy;
	private MaterialCard cardLogin;
	private MaterialCardContent cardContent;
	private MaterialCardTitle cardTitle;
	protected MaterialTextBox txtCorreo;
	protected MaterialTextBox txtClave;
	private MaterialButton btnEntrar;
	
	public UiLoginAdmin(){
		initComponents();
		initListener();
		initStyle();
	}
	
	private void initComponents(){
		pnlContenedor=new BorderLayout();				
		navBar=new MaterialNavBar();	
		navBrand=new MaterialNavBrand();
		navBrand.setText("INDIANT");
		navBar.add(navBrand);
		pnlContenedor.add(BorderLayout.NORTE,navBar);
		footCopy=new MaterialFooterCopyright();
		lblCopy=new HTML("&copy; 2016 Copyright Kiongo Technology");
		footCopy.add(lblCopy);
		cardLogin=new MaterialCard();		
		cardContent=new MaterialCardContent();
		cardTitle=new MaterialCardTitle();
		cardTitle.setText("LOGIN ADMINISTRACION");		
		cardContent.add(cardTitle);
		cardLogin.add(cardContent);
		txtCorreo=new MaterialTextBox();
		txtCorreo.setType(InputType.EMAIL);
		txtCorreo.setPlaceholder("Correo");
		txtClave=new MaterialTextBox();
		txtClave.setType(InputType.PASSWORD);
		txtClave.setPlaceholder("Clave");
		btnEntrar=new MaterialButton();
		btnEntrar.setText("Entrar");	
		cardContent.add(txtCorreo);			
		cardContent.add(txtClave);
		cardContent.add(btnEntrar);		
		pnlContenedor.add(BorderLayout.CENTRO,cardLogin);
		pnlContenedor.add(BorderLayout.SUR,footCopy);
		this.initWidget(pnlContenedor);
	}
	
	private void initListener(){
		btnEntrar.addClickHandler(this);
	}
	
	private void initStyle(){
		MyResource.INSTANCE.getStlUiLoginAdmin().ensureInjected();
		//myResource.ensureInjected();		
		//txtCorreo.getElement().setAttribute("placeholder", "Correo");
		//txtClave.getElement().setAttribute("placeholder", "Clave");
		//txtCorreo.getElement().setClassName(myResource.kngTextBox());
		//txtClave.getElement().setClassName(myResource.kngTextBox());
		btnEntrar.getElement().setId("kngButtonLogin");
		navBar.getElement().setId("kngNavBar");
		cardTitle.getElement().setId("kngCardTitle");
		cardLogin.getElement().setId("kngCardLogin");
		footCopy.getElement().setId("kngFootCopy");
	}

	@Override
	public void loginAdmin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(btnEntrar)){
			if(isValidData()){
				loginAdmin();
			}			
		}
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(FieldVerifier.isEmpty(txtCorreo.getText())){
			txtCorreo.setError("Ingrese correo");
			return false;
		}else if(FieldVerifier.isEmpty(txtClave.getText())){
			txtClave.setError("Ingrese clave");
			return false;
		}else{
			txtCorreo.clearErrorOrSuccess();
			txtClave.clearErrorOrSuccess();
			return true;
		}		
	}

	@Override
	public void goToSession() {
		// TODO Auto-generated method stub
		
	}
}