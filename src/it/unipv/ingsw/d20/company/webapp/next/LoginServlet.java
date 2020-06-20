package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
public class LoginServlet extends WebAppServlet {
	
	public LoginServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getPathInfo();
		System.out.println("primo:"+url);
		/*if  (url.equals("/login") || url.equals("/wrong_user") || url.equals("/wrong_password")) {
			resp.getWriter().write(Rythm.render(handler.getPage(url)));
		}			
		else {
			resp.getWriter().write(Rythm.render(handler.getPage("/login")));
		}	*/
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {   	
			controller.checkOperatorLogIn(req.getParameter("username"), req.getParameter("inputPassword"));	
		} catch (InvalidUserException eu) {
		   System.out.println("Invalid Operator Username");
		   resp.sendRedirect("/wrong_user");
		} catch (InvalidPasswordException ep) {
		   System.out.println("Invalid Password");
		   resp.sendRedirect("/wrong_password");
		}
		resp.sendRedirect("/select");
	}
	
}
	

