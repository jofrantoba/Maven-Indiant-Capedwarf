package com.indiana.server.control.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indiana.server.model.process.GestionMantenimiento;

public class SrvGestionMantenimiento  extends HttpServlet {
	private static final long serialVersionUID = 2556188587429050683L;
	private static final Logger log = Logger.getLogger(SrvGestionMantenimiento.class
			.getName());

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");
		String url = request.getServletPath();
		if (url.equals("/sessionadmin.html")) {
			redirectAdmin(request, response);
		}else if (url.equals("/activeaccountadmin.html")) {
			activarCuentaAdmin(request, response);
		} 
	}

	private void redirectAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {		
			String urlRedirect=request.getRequestURL().toString().replaceAll(request.getRequestURI(), "/sessionadmin.jsp");			
			PrintWriter out=response.getWriter();
			out.print(urlRedirect);
			out.flush();
			out.close();
		} catch (Exception ex) {
			log.warning(ex.getMessage());			
		}
	}
	
	private void activarCuentaAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String encoded = request.getParameter("encoded");
		try {
			if (GestionMantenimiento.activarCuentaAdmin(encoded)) {
				response.sendRedirect("http://kiongo.ddns.net");				
			} else {

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