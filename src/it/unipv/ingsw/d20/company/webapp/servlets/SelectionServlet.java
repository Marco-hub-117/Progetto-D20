package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

/**
 * Servlet che gestisce le richieste sul path /d20/selection/* (l'utente seleziona l'operazione desiderata). 
 *
 */
@SuppressWarnings("serial")
public class SelectionServlet extends WebAppServlet {
	private String alternativeUrl;
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 *e imposta l'url alternativo a cui si farà riferimento quando l'operatore non è di tipo remoto e, quindi, ha 
	 *un accesso limitato alle funzionalità.
	 * @param controller istanza di WebAppController
	 * @param handler istanza di WebPagesHandler
	 * 
	 */
	public SelectionServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
		alternativeUrl=WebPagesHandler.selectionServletAlternativeUrl;
	}

	/**
	 * Gestisce le richieste di tipo POST.
	 * @param req richiesta HTTP
	 * @param resp risposta HTTP
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=handler.trimUrl(req.getRequestURI());
		
		if (controller.getLoggedOperator()!=null && controller.operatorIsLimited()){
			//visualizza la pagina di selezione limitata
			url=alternativeUrl;
		}
		
		if (controller.getLoggedOperator()!=null) {
			//visualizza la pagina di selezione
			resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getLoggedOperator()));
		}
		else {
			resp.sendRedirect(home);
		}
	}

}
	

