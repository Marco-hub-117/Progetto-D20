package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.InvalidPasswordException;
import it.unipv.ingsw.d20.company.webapp.InvalidUserException;
import it.unipv.ingsw.d20.company.webapp.Operator;
import it.unipv.ingsw.d20.company.webapp.Operators;
import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

@SuppressWarnings("serial")
public class SelectionServlet extends WebAppServlet {
	
	public SelectionServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getPathInfo();
		
		if (controller.getLoggedOperator()!=null) {
			if (url==null) { 
				resp.getWriter().write(Rythm.render(handler.getPage("/select"), controller.getLoggedOperator()));
				System.out.println("interpreto qui1");
			}
			else if (url.equals("/")) {
				resp.getWriter().write(Rythm.render(handler.getPage("/select"), controller.getLoggedOperator()));
				System.out.println("giusto");
				System.out.println("interpreto qui2");
			}		
			else {
				resp.getWriter().write(Rythm.render(handler.getPage("/select"), controller.getLoggedOperator()));
				System.out.println("interpreto qui3");
			}
		//si possono anche togliere i vari if else, per ora li lascio x eventuale debug
		}
		else {
			resp.sendRedirect("/d20/");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
	

