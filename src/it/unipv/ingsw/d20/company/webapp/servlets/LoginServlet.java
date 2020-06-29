package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidPasswordException;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidUserException;

/**
 * Servlet che gestisce le richieste sul path /d20/* (operazioni di login e logout).
 *
 */
@SuppressWarnings("serial")
public class LoginServlet extends WebAppServlet {
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 * @param controller istanza di WebAppController.
	 * @param handler istanza di WebPagesHandler
	 */
	public LoginServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	/**
	 * Gestisce le richieste di tipo GET.
	 * @param req richiesta HTTP
	 * @param resp risposta HTTP
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=handler.trimUrl(req.getRequestURI());
			
		if (controller.getLoggedOperator()==null) {
			resp.getWriter().write(Rythm.render(handler.getPage(url)));
		}
		else { 
			//se l'utente è già loggato, viene rimandato alla pagina di selezione
			resp.sendRedirect(WebPagesHandler.loggedHome);
		}
	}
	
	/**
	 * Gestisce le richieste di tipo POST.
	 * @param req richiesta HTTP
	 * @param resp risposta HTTP
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getPathInfo().equals(WebPagesHandler.logoutReq)) { 
			//esegue il logout
			controller.setNotLogged();
			resp.sendRedirect(WebPagesHandler.logoutUrl);
		}
		else { 
			//prova ad eseguire il login
			try {   	
			  controller.checkOperatorLogIn(req.getParameter("username"), req.getParameter("inputPassword"));	
			} catch (InvalidUserException eu) {
			   System.out.println("Invalid Operator Username");
			   resp.sendRedirect(WebPagesHandler.wrongUserUrl);
			} catch (InvalidPasswordException ep) {
			   System.out.println("Invalid Password");
			   resp.sendRedirect(WebPagesHandler.wrongPasswordUrl);
			}
			resp.sendRedirect(WebPagesHandler.loggedHome);
		}
	}
	
}
	

