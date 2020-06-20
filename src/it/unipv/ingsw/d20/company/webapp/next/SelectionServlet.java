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
	
	private static String folder = "res/webapp/pages/"; //da togliere
	
	public SelectionServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url=req.getPathInfo();
		if (url==null) { //succede nel caso in cui si ha /selection senza niente dopo
			//resp.getWriter().write(Rythm.render(folder + "selectOp.html", loggedOperator));
			System.out.println("pagina non valida");
			resp.getWriter().write(Rythm.render(folder + "conferma.html", "pagina non valida")); //solo questo lo renderizza giusto
		}
		else if (url.equals("/")) {
			//resp.getWriter().write(Rythm.render(folder + "selectOp.html", loggedOperator));
			System.out.println("pagina valida");
			resp.getWriter().write(Rythm.render(folder + "conferma.html", "pagina valida"));
		}		
		else {
			//resp.getWriter().write(Rythm.render(handler.getPage("/login")));
			System.out.println("pagina comuqnue valida");
			resp.getWriter().write(Rythm.render(folder + "conferma.html", "pagina comuqnue valida"));
		}
		System.out.println("secondo:"+url);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	}
}
	

