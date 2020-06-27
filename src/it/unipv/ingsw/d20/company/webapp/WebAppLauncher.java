package it.unipv.ingsw.d20.company.webapp;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.Servlet;

import it.unipv.ingsw.d20.company.webapp.servlets.BeveragesServlet;
import it.unipv.ingsw.d20.company.webapp.servlets.ErrorServlet;
import it.unipv.ingsw.d20.company.webapp.servlets.KeysServlet;
import it.unipv.ingsw.d20.company.webapp.servlets.LoginServlet;
import it.unipv.ingsw.d20.company.webapp.servlets.OperatorsServlet;
import it.unipv.ingsw.d20.company.webapp.servlets.SelectionServlet;
import it.unipv.ingsw.d20.company.webapp.servlets.VendingsServlet;

/**
 * Permette di impostare e avviare l'applicazione web.
 *
 */
public class WebAppLauncher {
	
	/**
	 * Crea un'istanza di WebAppController, una di WebPagesHandler, le servlet e l'ApplicationServer, consentendo
	 * di avviare l'applicazione web.
	 * Utile per i test indipendenti dal resto del sistema.
	 *
	 */
    public static void main(String[] argv) throws Exception {
      	
       WebAppController controller= new WebAppController();       
       WebPagesHandler handler=new WebPagesHandler();
       
       List<Servlet> servletList= new LinkedList<>();
       servletList.add(new ErrorServlet(controller, handler));
       servletList.add(new LoginServlet(controller, handler));
       servletList.add(new SelectionServlet(controller, handler));
       servletList.add(new VendingsServlet(controller, handler));
       servletList.add(new OperatorsServlet(controller, handler));
       servletList.add(new KeysServlet(controller, handler));
       servletList.add(new BeveragesServlet(controller, handler));
       
       new ApplicationServer(8080, servletList).start();
       
       System.out.println(controller.getAllVendingMachineInfo().get("IDN20200627163138").getStatus());
       
    }
    
    /**
     * Crea un'istanza di WebAppController, una di WebPagesHandler, le servlet e l'ApplicationServer, consentendo
     * di avviare l'applicazione web.
     *
     */
    public static void start() throws Exception {
    	
    	WebAppController controller= new WebAppController();       
        WebPagesHandler handler=new WebPagesHandler();
        
        List<Servlet> servletList= new LinkedList<>();
        servletList.add(new ErrorServlet(controller, handler));
        servletList.add(new LoginServlet(controller, handler));
        servletList.add(new SelectionServlet(controller, handler));
        servletList.add(new VendingsServlet(controller, handler));
        servletList.add(new OperatorsServlet(controller, handler));
        servletList.add(new KeysServlet(controller, handler));
        servletList.add(new BeveragesServlet(controller, handler));
        
        new ApplicationServer(8080, servletList).start();
    }
    
}
