package com.indiana.server.control.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SrvGestionNegocio  extends HttpServlet {
	private static final long serialVersionUID = 2556188587429050683L;
	private static final Logger log = Logger.getLogger(SrvGestionNegocio.class
			.getName());

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");//
		String url = request.getServletPath();
		if (url.equals("/validarCuentaNegocio.html")) {
			validarCuentaNegocio(request, response);
		}
	}

	@SuppressWarnings("unused")
	private void redirectAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		try {
//			request.getRequestDispatcher(GestionNegocio.url).forward(
//					request, response);			
//		} catch (Exception ex) {
//			log.warning(ex.getMessage());			
//		}
	}
	
	private void validarCuentaNegocio(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try {
			if(request.getParameter("encoded")!=null){
//				String activador= request.getParameter("encoded");
//				Boolean rptaActivarCuenta=GestionNegocioV2.activarCuentaNegocio(activador);
//				if(rptaActivarCuenta){
//					response.sendRedirect(GestionBussines.url);
//				}				
			}
		} catch (Exception ex) {
			log.warning(ex.getMessage());			
		}
	}

	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}