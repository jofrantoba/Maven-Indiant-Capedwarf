package com.indiana.view.admin.uimantredsocial;

import java.util.Iterator;

import com.indiana.server.model.bean.RedSocial;
import com.indiana.shared.FieldVerifier;
import com.indiana.view.admin.uiredsocial.UiRedSocialImpl;
import com.indiana.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantRedSocial extends UiMantenimiento implements InterUiMantRedSocial{
	protected UiRedSocialImpl uiPadre;
	private MaterialLabel lblId;
	protected MaterialTextBox txtId;		
	private MaterialLabel lblNombreApp;
	protected MaterialTextBox txtNombreApp;
	private MaterialLabel lblApiKey;
	protected MaterialTextBox txtApiKey;
	private MaterialLabel lblSecretApp;
	protected MaterialTextBox txtSecretApp;
	private MaterialLabel lblOauthToken;
	protected MaterialTextBox txtOauthToken;
	private MaterialLabel lblOauthTokenSecret;
	protected MaterialTextBox txtOauthTokenSecret;
	private MaterialLabel lblApiVersion;
	protected MaterialTextBox txtApiVersion;
	private MaterialLabel lblRedSocial;
	protected MaterialTextBox txtRedSocial;
	protected RedSocial bean;
	
	public UiMantRedSocial(){
		initComponents();	
		reCalcularWindows();
	}
	
	public UiMantRedSocial(UiRedSocialImpl uiPadre){
		this.uiPadre=uiPadre;
		initComponents();	
		reCalcularWindows();
	}
	
	private void initComponents(){		
		this.CardContTitulo.setText("App Red Social");		
		lblId=new MaterialLabel("ID");
		txtId=new MaterialTextBox();
		lblNombreApp=new MaterialLabel("NOMBRE APP");
		txtNombreApp=new MaterialTextBox();
		lblApiKey=new MaterialLabel("API KEY");
		txtApiKey=new MaterialTextBox();
		lblSecretApp=new MaterialLabel("SHARED SECRET");
		txtSecretApp=new MaterialTextBox();
		lblOauthToken=new MaterialLabel("OAUTH TOKEN");
		txtOauthToken=new MaterialTextBox();
		lblOauthTokenSecret=new MaterialLabel("OAUTH TOKEN SECRET");
		txtOauthTokenSecret=new MaterialTextBox();
		lblApiVersion=new MaterialLabel("API VERSION");
		txtApiVersion=new MaterialTextBox();
		lblRedSocial=new MaterialLabel("RED SOCIAL");
		txtRedSocial=new MaterialTextBox();
		this.addWidget(0, 0, lblId);
		this.addWidget(0, 1, txtId);
		this.addWidget(1, 0, lblRedSocial);
		this.addWidget(1, 1, txtRedSocial);
		this.addWidget(2, 0, lblNombreApp);
		this.addWidget(2, 1, txtNombreApp);
		this.addWidget(3, 0, lblApiKey);
		this.addWidget(3, 1, txtApiKey);
		this.addWidget(4, 0, lblSecretApp);
		this.addWidget(4, 1, txtSecretApp);
		this.addWidget(5, 0, lblOauthToken);
		this.addWidget(5, 1, txtOauthToken);
		this.addWidget(6, 0, lblOauthTokenSecret);
		this.addWidget(6, 1, txtOauthTokenSecret);
		this.addWidget(7, 0, lblApiVersion);
		this.addWidget(7, 1, txtApiVersion);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}	
	
	private void reCalcularWindows() {        
        this.pnlScroll.setSize("600px","320px");       
        this.center();
    }

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeRedSocial());
            txtNombreApp.setText(this.bean.getNombreApp());
            txtRedSocial.setText(this.bean.getRedSocial());
            txtApiKey.setText(this.bean.getApiKey());
            txtSecretApp.setText(this.bean.getSharedSecret());
            txtOauthToken.setText(this.bean.getOauthToken()==null?null:this.bean.getOauthToken());
            txtOauthTokenSecret.setText(this.bean.getOauthTokenSecret()==null?null:this.bean.getOauthTokenSecret());
            txtApiVersion.setText(this.bean.getApiVersion());
            txtId.setEnabled(false);
            txtNombreApp.setFocus(true);            
            txtRedSocial.setEnabled(true);
            txtApiKey.setEnabled(true);
            txtSecretApp.setEnabled(true);
            txtOauthToken.setEnabled(true);
            txtOauthTokenSecret.setEnabled(true);
            txtApiVersion.setEnabled(true);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeRedSocial());
            txtNombreApp.setText(this.bean.getNombreApp());
            txtRedSocial.setText(this.bean.getRedSocial());
            txtApiKey.setText(this.bean.getApiKey());
            txtSecretApp.setText(this.bean.getSharedSecret());
            txtOauthToken.setText(this.bean.getOauthToken()==null?null:this.bean.getOauthToken());
            txtOauthTokenSecret.setText(this.bean.getOauthTokenSecret()==null?null:this.bean.getOauthTokenSecret());
            txtApiVersion.setText(this.bean.getApiVersion());
            txtId.setEnabled(false);
            txtNombreApp.setFocus(false);            
            txtRedSocial.setEnabled(false);
            txtApiKey.setEnabled(false);
            txtSecretApp.setEnabled(false);
            txtOauthToken.setEnabled(false);
            txtOauthTokenSecret.setEnabled(false);
            txtApiVersion.setEnabled(false);
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setText(this.bean.getCodeRedSocial().toString());
            txtNombreApp.setText(this.bean.getNombreApp());
            txtRedSocial.setText(this.bean.getRedSocial());
            txtApiKey.setText(this.bean.getApiKey());
            txtSecretApp.setText(this.bean.getSharedSecret());
            txtOauthToken.setText(this.bean.getOauthToken()==null?null:this.bean.getOauthToken());
            txtOauthTokenSecret.setText(this.bean.getOauthTokenSecret()==null?null:this.bean.getOauthTokenSecret());
            txtApiVersion.setText(this.bean.getApiVersion());
            txtId.setEnabled(false);
            txtNombreApp.setFocus(false);            
            txtRedSocial.setEnabled(false);
            txtApiKey.setEnabled(false);
            txtSecretApp.setEnabled(false);
            txtOauthToken.setEnabled(false);
            txtOauthTokenSecret.setEnabled(false);
            txtApiVersion.setEnabled(false);
            this.btnOperacion.setEnabled(false);
        } else {
        	lblId.setVisible(true);
        	txtId.setVisible(true);
            txtId.setEnabled(true);                       
            txtNombreApp.setFocus(true);            
            txtRedSocial.setEnabled(true);
            txtApiKey.setEnabled(true);
            txtSecretApp.setEnabled(true);
            txtOauthToken.setEnabled(true);
            txtOauthTokenSecret.setEnabled(true);
            txtApiVersion.setEnabled(true);
            cleanForm();
            this.btnOperacion.setEnabled(true);
        }
		clearError();
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.clear();
		txtNombreApp.clear();
		txtRedSocial.clear();
		txtApiKey.clear();
        txtSecretApp.clear();
        txtOauthToken.clear();
        txtOauthTokenSecret.clear();
        txtApiVersion.clear();
	}

	@Override
	public void goToUiRedSocial() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){			
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtNombreApp.getText())){
				txtNombreApp.setError("Campo obligatorio");
				return false;
			}else if(isExistInGrid()){
				MaterialToast.fireToast("ID existe");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtNombreApp.clearErrorOrSuccess();
				return true;
			}
		}else if(this.modo.equals(UiMantenimiento.MODOUPDATE) || 
				this.modo.equals(UiMantenimiento.MODODELETE)){
			if(FieldVerifier.isEmpty(txtId.getText())){
				txtId.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtNombreApp.getText())){
				txtNombreApp.setError("Campo obligatorio");
				return false;
			}else{
				txtId.clearErrorOrSuccess();
				txtNombreApp.clearErrorOrSuccess();
				return true;
			}
		}else{
			return true;
		}
	}

	@Override
	public void updateDataGrid(RedSocial bean) {
		// TODO Auto-generated method stub
		
	}	

	public void setBean(RedSocial bean) {
		this.bean = bean;
	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub
		txtId.setError(null);
		txtNombreApp.setError(null);
	}

	@Override
	public boolean isExistInGrid() {
		// TODO Auto-generated method stub
		Iterator<RedSocial> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			RedSocial bean=iterador.next();
			if(bean.getCodeRedSocial().equalsIgnoreCase(txtId.getText())){
				return true;
			}
		}
		return false;
	}
}
