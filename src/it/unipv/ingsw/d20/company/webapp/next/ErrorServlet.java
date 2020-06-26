package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidPasswordException;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidUserException;

@SuppressWarnings("serial")
public class ErrorServlet extends WebAppServlet {
	
	public ErrorServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.getWriter().write(Rythm.render(handler.getPage("404")));
	}
	
}
	

