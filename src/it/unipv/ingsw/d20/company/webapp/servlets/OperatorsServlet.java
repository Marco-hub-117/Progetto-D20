package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

/**
 * Servlet che gestisce le richieste sul path /d20/selection/operators/* (visualizzare la tabella degli operatori,
 * aggiungere operatori).
 *
 */
@SuppressWarnings("serial")
public class OperatorsServlet extends WebAppServlet {
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 *e imposta l'url "di base", a cui si farà riferimento per tornare alla pagina di partenza, 
	 *dopo aver fatto alcune operazioni.
	 * @param controller istanza di WebAppController
	 * @param handler istanza di WebPagesHandler
	 */
	public OperatorsServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
		setBasicUrl("/d20/selection/operators/");
	}
	
	/**
	 * Gestisce le richieste di tipo GET.
	 * @param req richiesta HTTP
	 * @param resp risposta HTTP
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=handler.trimUrl(req.getRequestURI());
				
		if (controller.getLoggedOperator()!=null && controller.isLimited()==false) {
			resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllOperators()));
		}
		else {
			resp.sendRedirect(home);
		}
	}
	
	/**
	 * Gestisce le richieste di tipo POST.
	 * @param req richiesta HTTP
	 * @param resp risposta HTTP
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getPathInfo().equals("/save_operator")) {
		controller.addOperator(req.getParameter("username"), req.getParameter("first_name")+" "+req.getParameter("last_name"), req.getParameter("password"), req.getParameter("type"));
		resp.sendRedirect(getBasicUrl());
		}
	}
	
}
