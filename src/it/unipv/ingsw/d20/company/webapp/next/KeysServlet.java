package it.unipv.ingsw.d20.company.webapp.next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import it.unipv.ingsw.d20.company.webapp.WebAppController;
import it.unipv.ingsw.d20.company.webapp.WebPagesHandler;

@SuppressWarnings("serial")
public class KeysServlet extends WebAppServlet {
	
	
	public KeysServlet(WebAppController controller, WebPagesHandler handler){
		super(controller, handler);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url= req.getPathInfo();
				
		if (controller.getLoggedOperator()!=null) {
			if (url==null) { 
				resp.getWriter().write(Rythm.render(handler.getPage("/select"), controller.getLoggedOperator()));
			}
			else if (url.equals("/")) {
				resp.getWriter().write(Rythm.render(handler.getPage("/keys"), controller.getAllKeys()));
			}
			else {
				resp.getWriter().write(Rythm.render(handler.getPage(url)));
			}
		}
		else {
			resp.sendRedirect("/d20/");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getPathInfo().equals("/save_key")) {
			String serialCode=req.getParameter("code");
			int codeNum=Integer.parseInt(serialCode.substring(0, serialCode.length()-1));
			String credit=req.getParameter("credit");
			double creditNum=Double.parseDouble(credit.substring(0, credit.length()-1));	
			System.out.println(":"+codeNum+":"+creditNum);
			
			controller.addKey(codeNum, creditNum);
			resp.sendRedirect("/d20/keys/");
		}
		else if (req.getPathInfo().equals("/deactivate")) {
			controller.deactivateKey(req.getParameter("id"));
		}
	}
}
