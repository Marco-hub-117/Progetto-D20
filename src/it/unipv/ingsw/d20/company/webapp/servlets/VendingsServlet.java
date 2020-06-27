package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.VendingMachineInfo;
import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

/**
 * Servlet che gestisce le richieste sul path /d20/selection/vendings/* (visualizzare la tabella dei distributori,
 * monitorare e modificare i settaggi, segnalare guasti).
 *
 */
@SuppressWarnings("serial")
public class VendingsServlet extends WebAppServlet {

	public VendingsServlet(WebAppController controller, WebPagesHandler handler) {
		super(controller, handler);
		setBasicUrl("/d20/selection/vendings/");
	}
	
	//DA CONTROLLARE
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=handler.trimUrl(req.getRequestURI());
		
		if (controller.getLoggedOperator()!=null) {
			if (url.equals(handler.trimUrl(getBasicUrl()))) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllVendingMachines()));
			}
			else if (url.equals("/settings")) {
			VendingPOJO vending=controller.getVendingMachine((req.getParameter("id")));
			VendingMachineInfo temporaryInfo=controller.getVendingMachineInfo((req.getParameter("id")));
			resp.getWriter().write(Rythm.render(handler.getPage(url), vending, temporaryInfo));
			}
			else if (url.equals("/report")) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllOperators()));
			}
			else if (url.equals("/pending_reports")) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getReportList()));
			}
		}
		else {
			resp.sendRedirect(home);
		}
	}

}
