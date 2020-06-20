package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

@SuppressWarnings("serial")
public class BeveragesServlet extends WebAppServlet {
	
	
	public BeveragesServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (loggedOperator!=null) {
			handler.renderPage(req.getPathInfo(), resp);
		}
		else {	
			
		}
		
	}*/
}
