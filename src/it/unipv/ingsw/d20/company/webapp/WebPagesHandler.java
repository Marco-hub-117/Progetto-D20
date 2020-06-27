package it.unipv.ingsw.d20.company.webapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Permette di associare a una stringa (parte finale di url) il documento html corrispondente.
 *
 */
public class WebPagesHandler {
	
	private static String folder = "res/webapp/pages/";
	
	private final Map<String, String> urlToPage;

    public WebPagesHandler(){	
    	urlToPage = new HashMap<>();
    	//navigation pages
    	urlToPage.put("404", "notFound.html");
    	urlToPage.put("/d20", "login.html");
    	urlToPage.put("/login", "login.html");
    	urlToPage.put("/wrong_user", "loginWrongUser.html");
    	urlToPage.put("/wrong_password", "loginWrongPassword.html");
    	urlToPage.put("/selection", "selectOp.html");
    	urlToPage.put("/selection_limited", "selectOpLimited.html");
    	urlToPage.put("/goodbye", "goodbye.html");       	
    	//vendings pages
    	urlToPage.put("/vendings", "vendingsTable.html");
    	urlToPage.put("/pending_reports", "vendingReports.html");
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
    }
   
    public String getPage(String url) {
    	if (urlToPage.get(url)!=null) {
		return folder + urlToPage.get(url);
    	}
    	else {
    	return folder + urlToPage.get("404");
    	}
	}
    
    //Elabora l'url in modo da avere solo l'ultima parte
  	public String trimUrl(String url) {
  		String[] splittedUrl=url.split("/");
  		return ("/"+splittedUrl[splittedUrl.length-1]);
  	}
    
}
