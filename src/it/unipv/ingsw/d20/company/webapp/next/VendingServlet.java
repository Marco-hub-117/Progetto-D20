package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.InvalidPasswordException;
import it.unipv.ingsw.d20.company.webapp.InvalidUserException;
import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

@SuppressWarnings("serial")
public class VendingServlet extends WebAppServlet {

	public VendingServlet(WebAppController controller, WebPagesHandler handler) {
		super(controller, handler);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getPathInfo();
		
		
		/*if (controller.getLoggedOperator()!=null) {
			if (url.equals("/vendings")) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllVendingMachines()));
			}
			else if (url.equals("/settings")) {
			VendingPOJO vending=controller.getVendingMachine((req.getParameter("id")));
			resp.getWriter().write(Rythm.render(handler.getPage(url), vending, controller.getAllVendingMachines())); //ma l'ultimo param serve??
			}
			else if (url.equals("/report")) {
				resp.getWriter().write(Rythm.render(handler.getPage(url), controller.getAllOperators()));
			}
			else {
				resp.getWriter().write(Rythm.render(handler.getPage(url)));
			}
		}
		else {
			resp.getWriter().write(Rythm.render(handler.getPage("/login")));
		}*/
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		if (req.getPathInfo().equals("/save_vending")) {
			/*TOGLIERE??
			Vendings.add(Integer.parseInt(req.getParameter("id")), req.getParameter("location"), req.getParameter("status"), req.getParameter("type"));
			resp.sendRedirect("/vendings");*/
		} 
		else if (req.getPathInfo().equals("/logout")) {
			controller.setNotLogged();
			resp.sendRedirect("/goodbye");
		} 
	}

}
