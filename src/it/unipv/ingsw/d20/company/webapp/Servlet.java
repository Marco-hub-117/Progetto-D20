package it.unipv.ingsw.d20.company.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {
	
	private Operator loggedOperator;
	 WebPagesHandler handler;
	
	public Servlet(){
		handler=new WebPagesHandler();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (loggedOperator!=null) {
			handler.renderPage(req.getPathInfo(), resp);
		}
		else {	
			
		}
		
	}
}
