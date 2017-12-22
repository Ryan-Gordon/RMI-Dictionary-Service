package ie.gmit.sw;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DictionaryServlet
 */
@WebServlet("/dictionaryServlet")
public class DictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* Declare any shared objects here. For example any of the following can be handled from 
	 * this context by instantiating them at a servlet level:
	 *   1) An Asynchronous Message Facade: declare the IN and OUT queues or MessageQueue
	 *   2) An Chain of Responsibility: declare the initial handler or a full chain object
	 *   1) A Proxy: Declare a shared proxy here and a request proxy inside doGet()
	 */
	private String environmentalVariable = null; //Demo purposes only. Rename this variable to something more appropriate
	private static long jobNumber = 0;
	private QueueManager r;
	private String result;
	
	/* This method is only called once, when the servlet is first started (like a constructor). 
	 * It's the Template Patten in action! Any application-wide variables should be initialised 
	 * here. Note that if you set the xml element <load-on-startup>1</load-on-startup>, this
	 * method will be automatically fired by Tomcat when the web server itself is started.
	 */
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); //The servlet context is the application itself.
		
		//Reads the value from the <context-param> in web.xml. Any application scope variables 
		//defined in the web.xml can be read in as follows:
		environmentalVariable = (String) ctx.getInitParameter("RMI_URL");
		System.out.println("RMI URL from config "+ environmentalVariable);
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response, String res) throws ServletException, IOException {
    		response.getWriter().append(res);
    		response.sendRedirect("index.jsp?result="+res);			
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		r = new QueueManager(jobNumber,request.getParameter("word"));
    		new Thread(r).start(); // start the thread
		try {
			Thread.sleep(5000);					// simulate a wait...?
			String res = r.getResponse();	
			System.out.println("Result" + res);
			doGet(request, response, res);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// try catch
	}

}
