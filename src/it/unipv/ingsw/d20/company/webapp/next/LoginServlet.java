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
public class LoginServlet extends WebAppServlet {
	
	public LoginServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getPathInfo();
		
		if (controller.getLoggedOperator()==null) {
			if (url==null) { 
				resp.getWriter().write(Rythm.render(handler.getPage("/login"))); 
			}
			else if  (url.equals("/wrong_user") || url.equals("/wrong_password") || url.equals("/goodbye")) {
				resp.getWriter().write(Rythm.render(handler.getPage(url)));
			}			
			else {
				resp.getWriter().write(Rythm.render(handler.getPage("/login")));
			}
		}
		else {
			if (controller.isLimited())
				resp.getWriter().write(Rythm.render(handler.getPage("/select_limited"), controller.getLoggedOperator()));
			else
				resp.getWriter().write(Rythm.render(handler.getPage("/select"), controller.getLoggedOperator()));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getPathInfo().equals("/logout")) {
			controller.setNotLogged();
			System.out.println("sloggato da loginServlet");
			resp.sendRedirect("/d20/goodbye");
		}
		else {
			try {   	
				controller.checkOperatorLogIn(req.getParameter("username"), req.getParameter("inputPassword"));	
			} catch (InvalidUserException eu) {
			   System.out.println("Invalid Operator Username");
			   resp.sendRedirect("/d20/wrong_user");
			} catch (InvalidPasswordException ep) {
			   System.out.println("Invalid Password");
			   resp.sendRedirect("/d20/wrong_password");
			}
			resp.sendRedirect("/d20/selection/");
		}
	}
	
}
	

