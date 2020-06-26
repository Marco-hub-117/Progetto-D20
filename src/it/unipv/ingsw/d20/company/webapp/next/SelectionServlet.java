package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

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
		String urll=req.getContextPath();
		
		if (controller.getLoggedOperator()!=null && controller.isLimited()==false) {
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
		else if (controller.getLoggedOperator()!=null && controller.isLimited()) {
			if (url==null) { 
				resp.getWriter().write(Rythm.render(handler.getPage("/select_limited"), controller.getLoggedOperator()));
				System.out.println("interpreto qui1 limited");
			}
			else if (url.equals("/")) {
				resp.getWriter().write(Rythm.render(handler.getPage("/select_limited"), controller.getLoggedOperator()));
				System.out.println("giusto");
				System.out.println("interpreto qui2 limited");
			}		
			else {
				resp.getWriter().write(Rythm.render(handler.getPage("/select_limited"), controller.getLoggedOperator()));
				System.out.println("interpreto qui3 limited");
			}	
		}
		
		else {
			resp.sendRedirect("/d20/");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
	

