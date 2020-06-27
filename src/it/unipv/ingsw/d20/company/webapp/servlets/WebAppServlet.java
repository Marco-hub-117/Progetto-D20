package it.unipv.ingsw.d20.company.webapp.servlets;

import javax.servlet.http.HttpServlet;
import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

/**
 * Servlet astratta che definisce gli attributi che saranno necessari nelle servlet concrete. 
 * 
 */
@SuppressWarnings("serial")
public abstract class WebAppServlet extends HttpServlet{
	/**
	 * Istanza di WebPagesHandler a cui si fa riferimento per associare a una richiesta HTTP il file HTML 
	 * corretto da visualizzare.
	 * 
	 */
	protected WebPagesHandler handler;
	
	/**
	 * Istanza di WebAppController a cui si delegano tutte le operazioni da effetturare.
	 * 
	 */
	protected WebAppController controller;
	
	/**
	 * Percorso di base dell'applicazione web. 
	 * 
	 */
	protected String home;
	
	/**
	 * Percorso di base specifico di ogni Servlet. 
	 * 
	 */
	private String basicUrl;
	
	/**
	 * Il costruttore assegna agli attributi relativi le istanze di WebAppController e WebPagesHandler
	 *e imposta l'url di base dell'applicazione web.
	 * @param controller istanza di WebAppController
	 * @param handler istanza di WebPagesHandler
	 */
	public WebAppServlet(WebAppController controller, WebPagesHandler handler){
		this.controller=controller;
		this.handler=handler;
		home="/d20/";
	}

	public String getBasicUrl() {
		return basicUrl;
	}

	public void setBasicUrl(String basicUrl) {
		this.basicUrl = basicUrl;
	}

}
