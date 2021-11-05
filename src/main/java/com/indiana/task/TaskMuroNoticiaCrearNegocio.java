package com.indiana.task;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indiana.server.model.process.GestionBussines;

@SuppressWarnings("serial")
public class TaskMuroNoticiaCrearNegocio extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(TaskMuroNoticiaCrearNegocio.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strCallResult = "";
		response.setContentType("text/plain");				
		try {		
			String codeNoticia= request.getParameter("codeNoticia");						
			GestionBussines.queueMuroNoticiaCrearNegocio(codeNoticia);
			_logger.info("Exito");		
			strCallResult = "SUCCESS: ENCOLADO";
			_logger.info(strCallResult);
			response.getWriter().println(strCallResult);
		} catch (Exception ex) {
			strCallResult = "FAIL: ERROR ENCOLANDO : " + ex.getMessage();
			_logger.info(strCallResult);
			response.getWriter().println(strCallResult);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}