package it.unipv.ingsw.d20.company.webapp.next;

import javax.servlet.http.HttpServlet;
import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

@SuppressWarnings("serial")
public abstract class WebAppServlet extends HttpServlet{
	WebPagesHandler handler;
	WebAppController controller;
	
	public WebAppServlet(WebAppController controller, WebPagesHandler handler){
		this.controller=controller;
		this.handler=handler;
	}

}
