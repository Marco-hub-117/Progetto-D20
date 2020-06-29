package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

/**
 * Servlet che gestisce le richieste sul path /d20/selection/keys/* (visualizzare la tabella delle chiavette,
 * aggiungere chiavette, disattivare chiavette).
 *
 */
@SuppressWarnings("serial")
public class KeysServlet extends WebAppServlet {
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 *e imposta l'url "di base", a cui si farà riferimento per tornare alla pagina di partenza, 
	 *dopo aver fatto alcune operazioni.
	 * @param controller istanza di WebAppController
	 * @param handler istanza di WebPagesHandler
	 */
	public KeysServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
		setBasicUrl(WebPagesHandler.keysServletBasicUrl);
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
				
		if (controller.getLoggedOperator()!=null && controller.operatorIsLimited()==false) { 
			//visualizza la tabella di tutte le chiavette
			resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllKeys()));
		}
		else {
			resp.sendRedirect(home);
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
		
		if (req.getPathInfo().equals(WebPagesHandler.keysServletSaveKey)) {
			//registra una nuova chiavetta
			String serialCode=req.getParameter("code");
			int codeNum=Integer.parseInt(serialCode);
			String credit=req.getParameter("credit");
			double creditNum=Double.parseDouble(credit.substring(0, credit.length()-1));	
			
			controller.addKey(codeNum, creditNum);
			resp.sendRedirect(getBasicUrl());
		}
		else if (req.getPathInfo().equals(WebPagesHandler.keysServletDeactivateKey)) {
			//disattiva una chiavetta
			controller.deactivateKey(req.getParameter("id"));
			resp.sendRedirect(getBasicUrl());
		}
	}
}
