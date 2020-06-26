package it.unipv.ingsw.d20.company.webapp;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.Servlet;

import it.unipv.ingsw.d20.company.webapp.next.BeveragesServlet;
import it.unipv.ingsw.d20.company.webapp.next.ErrorServlet;
import it.unipv.ingsw.d20.company.webapp.next.KeysServlet;
import it.unipv.ingsw.d20.company.webapp.next.LoginServlet;
import it.unipv.ingsw.d20.company.webapp.next.OperatorsServlet;
import it.unipv.ingsw.d20.company.webapp.next.SelectionServlet;
import it.unipv.ingsw.d20.company.webapp.next.VendingsServlet;

public class WebAppLauncher {
	
	//Utile per i test.
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
    }
    
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
