package it.unipv.ingsw.d20.company.webapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletResponse;
import org.rythmengine.Rythm;

public class WebPagesHandler {
	
	private static String folder = "res/webapp/pages/";
	
	private final Map<String, String> urlToPage;
	
	private final Map<String, Callable<Object>> urlToMethod;

    public WebPagesHandler(){
    	WebAppController controller = new WebAppController ();
    	
    	urlToPage = new HashMap<>();
    	//navigation pages
    	urlToPage.put("/login", "login.html");
    	urlToPage.put("/wrong_user", "loginWrongUser.html");
    	urlToPage.put("/wrong_password", "loginWrongPassword.html");
    	urlToPage.put("/select", "selectOp.html");
    	urlToPage.put("/goodbye", "goodbye.html");       	
    	//vendings pages
    	urlToPage.put("/vendings", "vendingsTable.html");
    	urlToPage.put("/add_vending", "vendingForm.html");
    	urlToPage.put("/settings", "vendingSettings.html");
    	urlToPage.put("/report", "vendingReport.html");
    	urlToPage.put("/report_confirmed", "reportConfirmed.html");
    	//operators pages
    	urlToPage.put("/operators", "operatorsTable.html");
    	urlToPage.put("/add_operator", "operatorForm.html");
    	//keys pages
    	urlToPage.put("/keys", "keysTable.html");
    	urlToPage.put("/add_key", "keyForm.html");
    	//beverages pages
    	urlToPage.put("/beverages", "beveragesTable.html");
    	urlToPage.put("/beverage_edit", "beverageSettings.html");
    
    	urlToMethod = new HashMap<>();
    	//navigation pages
    	urlToMethod.put("/login", () -> controller.doNothing());
    	urlToMethod.put("/wrong_user", () -> controller.doNothing());
    	urlToMethod.put("/wrong_password", () -> controller.doNothing());
    	urlToMethod.put("/select", () -> controller.getLoggedOperator());
    	urlToMethod.put("/goodbye", () -> controller.doNothing());       	
    	//vendings pages
    	urlToMethod.put("/vendings", () -> controller.getAllVendingMachines());
    	urlToMethod.put("/add_vending", () -> controller.doNothing());
    	////urlToMethod.put("/settings", () -> controller.getVendingMachine(id));
    	urlToMethod.put("/report", () -> controller.doNothing());
    	urlToMethod.put("/report_confirmed", () -> controller.doNothing());
    	//operators pages
    	urlToMethod.put("/operators", () -> controller.getAllOperators());
    	urlToMethod.put("/add_operator", () -> controller.doNothing());
    	//keys pages
    	urlToMethod.put("/keys", () -> controller.getAllKeys());
    	urlToMethod.put("/add_key", () -> controller.doNothing());
    	//beverages pages
    	urlToMethod.put("/beverages", () -> controller.getAllBeverageDescriptions());
    	urlToMethod.put("/beverage_edit", () -> controller.doNothing());
    }
   
    public String getPage(String url) {
		return folder + urlToPage.get(url);
	}
    
    //ho dubbi che funzioni
    public Callable<Object> getMethod(String url) {
		return urlToMethod.get(url);
	}
    
    public void renderPage(String url, HttpServletResponse resp) {
    	try {
    		 Object obj=getMethod(url).call();
			resp.getWriter().write(Rythm.render(folder + getPage(url), obj));
		} catch (IOException e) {
			System.out.println("A problem occured while loading web page");
		} catch (Exception ex) {
    		
    	}
    }
    
}
