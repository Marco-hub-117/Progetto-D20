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
	
	public KeysServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
		setBasicUrl("/d20/selection/operators/");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=controller.trimUrl(req.getRequestURI());
				
		if (controller.getLoggedOperator()!=null && controller.isLimited()==false) {
			resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllKeys()));
		}
		else {
			resp.sendRedirect(home);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getPathInfo().equals("/save_key")) {
			String serialCode=req.getParameter("code");
			int codeNum=Integer.parseInt(serialCode);
			String credit=req.getParameter("credit");
			double creditNum=Double.parseDouble(credit.substring(0, credit.length()-1));	
			
			controller.addKey(codeNum, creditNum);
			resp.sendRedirect(getBasicUrl());
		}
		else if (req.getPathInfo().equals("/deactivate")) {
			controller.deactivateKey(req.getParameter("id"));
			resp.sendRedirect(getBasicUrl());
		}
	}
}
