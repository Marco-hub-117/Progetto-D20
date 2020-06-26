package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;

@SuppressWarnings("serial")
public class BeveragesServlet extends WebAppServlet {
	
	public BeveragesServlet(WebAppController controller, WebPagesHandler handler) {
		super(controller, handler);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getPathInfo();
		
		if (controller.getLoggedOperator()!=null) {
			if (url.equals("/")) {
				resp.getWriter().write(Rythm.render(handler.getPage("/beverages"), controller.getAllBeverageDescriptions()));
			}
			else {
			BeverageDescriptionPOJO beverage=controller.getBeverageDescription(req.getParameter("bevname"));
			List<String> ingredientsNames=controller.getIngredientsNames(beverage.getIdRecipe());
			List<Double> ingredientsQuantities=controller.getIngredientsQuantities(beverage.getIdRecipe());
			
			resp.getWriter().write(Rythm.render(handler.getPage("/beverage_edit"), beverage, ingredientsNames, ingredientsQuantities));
			}
		}
		else {
			resp.sendRedirect("/d20/");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		if (req.getPathInfo().equals("/save_beverage")) {
			
			String bevname=req.getParameter("bevname"); //dà excepiton
			System.out.println("bevname:"+bevname);
			BeverageDescriptionPOJO desc=controller.getBeverageDescription(bevname);
			String idRecipe=desc.getIdRecipe();
			System.out.println(idRecipe);
			
			String ingredientName=req.getParameter("a1");
			String quantity=req.getParameter("a2");
			
			System.out.println("eccola: "+ingredientName+":"+quantity);
			controller.updateIngredients(idRecipe, ingredientName, Double.parseDouble(quantity));
			resp.sendRedirect("/d20/selection/beverages/");
		} 
	}
}
