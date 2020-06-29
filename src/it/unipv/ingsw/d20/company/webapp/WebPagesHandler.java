package it.unipv.ingsw.d20.company.webapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Presenta attributi statici a cui è associato un url: sono utili nelle Servlet quando si deve decidere l'azione da eseguire sulla
 * base dell'url, perchè in futuro questo, per eseguire una stessa azione, potrebbe essere cambiato in seguito a un refactoring della webapp.
 * Presenta, inoltre, una mappa che permette di associare a una stringa (parte finale dell'url di una richiesta HTTP) il documento HTML corrispondente.
 *
 */
public class WebPagesHandler {
	
	/**
	 * Percorso relativo della cartella in cui sono collocati i file HTML.
	 *
	 */
	private static String folder = "res/webapp/pages/";
	
	//url di base
	public static String homeUrl="/d20/";
	public static String loggedHome="/d20/selection/";
	//url per LoginServlet
	public static String wrongUserUrl="/d20/wrong_user";
	public static String wrongPasswordUrl="/d20/wrong_password";
	public static String logoutReq="/logout";
	public static String logoutUrl="/d20/goodbye";
	//url per SelectionServlet
	public static String selectionServletAlternativeUrl="/selection_limited";
	//url per VendingsServlet
	public static String vendigsServletBasicUrl="/d20/selection/vendings/";
	public static String vendingsServletSettings="/settings";
	public static String vendingsServletReport="/report";
	public static String vendingsServletReportConfirmed="/report_confirmed";
	public static String vendingsServletPendingReports="/pending_reports";
	public static String vendingsServletSendReport="/send_report";
	public static String vendingsServletRemoveReport="/remove_report";
	public static String vendingsServletSaveSettings="/save_settings";
	//url per OperatorsServlet
	public static String operatorsServletBasicUrl="/d20/selection/operators/";
	public static String operatorsServletSaveOperator="/save_operator";
	//url per KeysServlet
	public static String keysServletBasicUrl="/d20/selection/keys/";
	public static String keysServletSaveKey="/save_key";
	public static String keysServletDeactivateKey="/deactivate";
	//url per BeveragesServlet
	public static String beveragesServletBasicUrl="/d20/selection/beverages/";
	public static String beveragesServletSaveBeverage="/save_beverage";
	//stringa per ErrorServlet
	public static String errorString="404";

	
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
    	//pagine di navigazione
    	urlToPage.put("404", "notFound.html");
    	urlToPage.put("/d20", "login.html");
    	urlToPage.put("/login", "login.html");
    	urlToPage.put("/wrong_user", "loginWrongUser.html");
    	urlToPage.put("/wrong_password", "loginWrongPassword.html");
    	urlToPage.put("/selection", "selectOp.html");
    	urlToPage.put("/selection_limited", "selectOpLimited.html");
    	urlToPage.put("/goodbye", "goodbye.html");       	
    	//pagine di VendigsServlet
    	urlToPage.put("/vendings", "vendingsTable.html");
    	urlToPage.put("/pending_reports", "vendingsPendingReports.html");
    	urlToPage.put("/settings", "vendingSettings.html");
    	urlToPage.put("/report", "vendingReport.html");
    	urlToPage.put("/report_confirmed", "reportConfirmed.html");
    	//pagine di OperatorsServlet
    	urlToPage.put("/operators", "operatorsTable.html");
    	urlToPage.put("/add_operator", "operatorForm.html");
    	//pagine di KeysServlet
    	urlToPage.put("/keys", "keysTable.html");
    	urlToPage.put("/add_key", "keyForm.html");
    	//pagine di BeveragesServlet
    	urlToPage.put("/beverages", "beveragesTable.html");
    	urlToPage.put("/beverage_edit", "beverageSettings.html");  	
    }
   
    /**
     * Permette di recuperare la pagina HTML da visualizzare a partire da una stringa.
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
