package com.indiana.server.control.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.mail.BounceNotification;
import com.google.appengine.api.mail.BounceNotificationParser;

@SuppressWarnings("serial")
public class BounceHandlerServlet extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(BounceHandlerServlet.class.getName());
  
  protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
	  BounceNotification bounce;
	try {
		bounce = BounceNotificationParser.parse(request);	
      LOG.warning("Bounced email notification.");      
      bounce.getOriginal().getFrom();
      bounce.getOriginal().getTo();
      bounce.getOriginal().getSubject(); 
      bounce.getOriginal().getText(); 
      bounce.getNotification().getFrom(); 
      bounce.getNotification().getTo(); 
      bounce.getNotification().getSubject(); 
      bounce.getNotification().getText();       
		}catch (MessagingException ex) {		
			LOG.warning(ex.getMessage());
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