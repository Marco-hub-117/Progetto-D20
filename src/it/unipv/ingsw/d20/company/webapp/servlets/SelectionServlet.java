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
	
	public SelectionServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
		alternativeUrl="/selection_limited";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=controller.trimUrl(req.getRequestURI());
		
		if (controller.getLoggedOperator()!=null && controller.isLimited()){
			url=alternativeUrl;
		}
		
		if (controller.getLoggedOperator()!=null) {
			resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getLoggedOperator()));
		}
		else {
			resp.sendRedirect(home);
		}
	}

}
	

