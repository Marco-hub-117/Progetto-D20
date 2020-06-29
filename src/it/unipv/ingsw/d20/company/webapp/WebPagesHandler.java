package it.unipv.ingsw.d20.company.webapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Permette di associare a una stringa (parte finale dell'url di una richiesta HTTP) il documento HTML corrispondente.
 *
 */
public class WebPagesHandler {
	
	/**
	 * Percorso relativo della cartella in cui sono collocati i file HTML.
	 *
	 */
	private static String folder = "res/webapp/pages/";
	
	/**
	 * Mappa che implementa le associazioni url-documento HTMl.
	 *
	 */
	private final Map<String, String> urlToPage;
/**
 * Costruttore della classe WebPagesHandler
 * 
 * */
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
    	urlToPage.put("/pending_reports", "vendingsPendingReports.html");
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
   
    /**
     * Permette di recuperare la pagnia HTML da visualizzare a partire da una stringa.
	 * @param url parte finale dell'url di una richiesta HTTP
	 * @return percorso relativo del file HTML da visualizzare
	 */
    public String getPage(String url) {
    	if (urlToPage.get(url)!=null) {
		return folder + urlToPage.get(url);
    	}
    	else {
    	return folder + urlToPage.get("404");
    	}
	}
    
    /**
     * Taglia l'url passato come parametro per poterne gestire solo l'ultima parte.
	 * @param url url relativo a una richiesta HTTP
	 * @return parte finale dell'url di una richiesta HTTP
	 */
  	public String trimUrl(String url) {
  		String[] splittedUrl=url.split("/");
  		return ("/"+splittedUrl[splittedUrl.length-1]);
  	}
    
}
