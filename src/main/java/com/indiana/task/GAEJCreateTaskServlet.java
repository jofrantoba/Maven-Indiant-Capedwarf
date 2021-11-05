package com.indiana.task;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.modules.ModulesServiceFactory;
//import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.taskqueue.*;
//import com.google.appengine.api.labs.taskqueue.QueueFactory;
//import com.google.appengine.api.labs.taskqueue.TaskOptions;

@SuppressWarnings("serial")
public class GAEJCreateTaskServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String strCallResult = "";
		resp.setContentType("text/plain");
		try {
                        resp.getWriter().println("MODULO: "+ModulesServiceFactory.getModulesService().getCurrentModule());
			resp.getWriter().println("VERSION: "+ModulesServiceFactory.getModulesService().getCurrentVersion());                        
                        resp.getWriter().println("HOST2: "+ModulesServiceFactory.getModulesService().getVersionHostname(ModulesServiceFactory.getModulesService().getCurrentModule(),ModulesServiceFactory.getModulesService().getCurrentVersion()));
                        resp.getWriter().println("HOST3: "+ModulesServiceFactory.getModulesService().getVersionHostname(ModulesServiceFactory.getModulesService().getCurrentModule(),null));
                        resp.getWriter().println("HOST1: "+ModulesServiceFactory.getModulesService().getVersionHostname(null,null));
                        
			// Extract out the To, Subject and Body of the Email to be sent
			String strEmailId = req.getParameter("emailid");
			// Do validations here. Only basic ones i.e. cannot be null/empty
			if (strEmailId == null)
				throw new Exception("Email Id field cannot be empty.");
			// Trim the stuff
			strEmailId = strEmailId.trim();
			if (strEmailId.length() == 0)
				throw new Exception("Email Id field cannot be empty.");
			// Queue queue = QueueFactory.getDefaultQueue();
			Queue queue = QueueFactory.getQueue("subscription-queue");
			// queue.add(TaskOptions.Builder.url("/gaejsignupsubscriber").param("emailid",
			// strEmailId));
			//queue.add(TaskOptions.Builder.withUrl("/gaejsignupsubscriber").
					//param("emailid", strEmailId));
			//System.out.println("ESTE ES EL NOMBRE: "+ModulesServiceFactory.getModulesService().getVersionHostname("webindiant", "1"));
			queue.add(TaskOptions.Builder
		            .withUrl("/gaejsignupsubscriber")
		            .param("emailid", strEmailId)
		            .header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null,null)));
			strCallResult = "Successfully created a Task in the Queue";
			resp.getWriter().println(strCallResult);			
		} catch (Exception ex) {
			strCallResult = "Fail: " + ex.getMessage();
			resp.getWriter().println(strCallResult);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}