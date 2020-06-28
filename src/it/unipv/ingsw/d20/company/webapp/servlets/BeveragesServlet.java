package it.unipv.ingsw.d20.company.webapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.BeverageDescriptionPOJO;

/**
 * Servlet che gestisce le richieste sul path /d20/selection/beverages/* (visualizzare la tabella delle bevande,
 * modificare le ricette).
 *
 */
@SuppressWarnings("serial")
public class BeveragesServlet extends WebAppServlet {
	
	/**
	 * Il costruttore chiama il costruttore della superclasse passando le istanze di WebAppController e WebPagesHandler
	 *e imposta l'url "di base", a cui si farà riferimento per tornare alla pagina di partenza, 
	 *dopo aver fatto alcune operazioni.
	 * @param controller istanza di WebAppController
	 * @param handler istanza di WebPagesHandler
	 */
	public BeveragesServlet(WebAppController controller, WebPagesHandler handler) {
		super(controller, handler);
		setBasicUrl("/d20/selection/beverages/");
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
		
		if (controller.getLoggedOperator()!=null && controller.isLimited()==false) {
			if (url.equals(handler.trimUrl(getBasicUrl()))) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllBeverageDescriptions()));
			}
			else {
			BeverageDescriptionPOJO beverage=controller.getBeverageDescription(req.getParameter("bevname"));
			List<String> ingredientsNames=controller.getIngredientsNames(beverage.getIdRecipe());
			List<Double> ingredientsQuantities=controller.getIngredientsQuantities(beverage.getIdRecipe());
			
			resp.getWriter().write(Rythm.render(handler.getPage(url), beverage, ingredientsNames, ingredientsQuantities));
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
	
		if (req.getPathInfo().equals("/save_beverage")) {
			String bevname=req.getParameter("bevname");
			BeverageDescriptionPOJO desc=controller.getBeverageDescription(bevname);
			String idRecipe=desc.getIdRecipe();
			
			int i;
			for (i=1; i<11; i+=2) {
				String ingredientName=req.getParameter((String.valueOf(i)));
				String quantity=req.getParameter(String.valueOf(i+1));
				if (!ingredientName.equals(WebAppController.absenceString)){
				controller.updateIngredients(idRecipe, ingredientName, Double.parseDouble(quantity));
				}
			}
			resp.sendRedirect(getBasicUrl());
		} 
	}
}
