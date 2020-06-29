package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.rythmengine.Rythm;
<<<<<<< HEAD

=======
>>>>>>> f66ea4bde5460caeaf35f55c51fe0f8aeda97ed8
import it.unipv.ingsw.d20.company.webapp.ReportPOJO;
import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

/**
 * Servlet che gestisce le richieste sul path /d20/selection/vendings/* (visualizzare la tabella dei distributori,
 * monitorare e modificare i settaggi, segnalare guasti).
 *
 */
@SuppressWarnings("serial")
public class VendingsServlet extends WebAppServlet {
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 *e imposta l'url "di base", a cui si farà riferimento per tornare alla pagina di partenza, 
	 *dopo aver fatto alcune operazioni.
	 * @param controller istanza di WebAppController
	 * @param handler istanza di WebPagesHandler
	 */
	public VendingsServlet(WebAppController controller, WebPagesHandler handler) {
		super(controller, handler);
		setBasicUrl(WebPagesHandler.vendigsServletBasicUrl);
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
		
		if (controller.getLoggedOperator()!=null) {
			if (url.equals(handler.trimUrl(getBasicUrl()))) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllVendingMachines()));
			}
			else if (url.equals(WebPagesHandler.vendingsServletSettings)) {
				VendingPOJO vending=controller.getVendingMachine((req.getParameter("id")));
				String status=controller.getVendingStatus((req.getParameter("id")));
				Double currentAmount= controller.getVendingCurrentAmount((req.getParameter("id")));
				List<String> tanksNames=controller.getTanksNames((req.getParameter("id")));
				List<Double> tanksLevels=controller.getTanksLevels((req.getParameter("id")));
				List<Double> tanksTemps=controller.getTanksTemps((req.getParameter("id")));
				resp.getWriter().write(Rythm.render(handler.getPage(url), vending, status, currentAmount, tanksNames, tanksLevels, tanksTemps));
			}
			else if (url.equals(WebPagesHandler.vendingsServletReport)) {
				
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllOperators(), req.getParameter("id")));
			}
			else if (url.equals(WebPagesHandler.vendingsServletReportConfirmed)) {
				
				resp.getWriter().write(Rythm.render(handler.getPage(url)));
			}
			else if (url.equals(WebPagesHandler.vendingsServletPendingReports)) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getReportList()));
			}
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
		
		if (req.getPathInfo().equals(WebPagesHandler.vendingsServletSendReport)) {
			String vendingID=req.getParameter("id");
			String problem=req.getParameter("problem");
			String details=req.getParameter("details");
			String operatorName=req.getParameter("operator");
			controller.addReport(new ReportPOJO(vendingID, problem, details, operatorName));
			resp.sendRedirect(getBasicUrl()+WebPagesHandler.vendingsServletReportConfirmed);
		}
		else if (req.getPathInfo().equals(WebPagesHandler.vendingsServletRemoveReport)) {
			controller.removeReport(req.getParameter("vendingId"), req.getParameter("problem"));
			resp.sendRedirect(getBasicUrl()+WebPagesHandler.vendingsServletPendingReports);
		}
		else if (req.getPathInfo().equals(WebPagesHandler.vendingsServletSaveSettings)) {
			List<Double> tanksTemps=new LinkedList<>();
			
			for (int i=1; i<IngredientRecipePOJO.maxIngredientsPerVending+1; i++) {
				double temp=Double.parseDouble(req.getParameter(String.valueOf(i)));
				tanksTemps.add(temp);
			}
			controller.updateTanks(req.getParameter("id"), tanksTemps);
		}
		resp.sendRedirect(getBasicUrl());
	}

}
