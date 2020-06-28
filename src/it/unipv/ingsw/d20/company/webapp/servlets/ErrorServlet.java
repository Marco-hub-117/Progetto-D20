package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

/**
 * Servlet che visualizza un messaggio d'errore se il path specificato è inesistente.
 *
 */
@SuppressWarnings("serial")
public class ErrorServlet extends WebAppServlet {
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 * @param controller istanza di WebAppController.
	 * @param handler istanza di WebPagesHandler
	 */
	public ErrorServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	/**
	 * Gestisce la richiesta (unica) di tipo GET.
	 * @param req richiesta HTTP
	 * @param resp risposta HTTP
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(Rythm.render(handler.getPage("404")));
	}
	
}
	

