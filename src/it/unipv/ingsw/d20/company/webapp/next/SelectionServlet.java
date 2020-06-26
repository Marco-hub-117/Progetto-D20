package it.unipv.ingsw.d20.company.webapp.next;

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
	
	public SelectionServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=controller.trimUrl(req.getRequestURI());
		
		if (controller.getLoggedOperator()!=null && controller.isLimited()){
			url="/selection_limited";
		}
		
		if (controller.getLoggedOperator()!=null) {
			resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getLoggedOperator()));
		}
		else {
			resp.sendRedirect("/d20/");
		}
	}

}
	

