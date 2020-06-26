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
	protected WebPagesHandler handler;
	protected WebAppController controller;
	protected String home;
	private String basicUrl;
	
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
