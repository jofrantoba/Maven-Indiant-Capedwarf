package com.indiana.server.control.servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MailHandlerServlet extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(MailHandlerServlet.class.getName());

  protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
	  Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);
	    try {
	    	new MimeMessage(session, request.getInputStream());
	      LOG.info("Received mail message.");
	    } catch (MessagingException ex) {
	    	LOG.warning(ex.getLocalizedMessage());
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