package it.unipv.ingsw.d20.company.webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Gestisce le servlet per le varie pagine e per lo stile grafico.
 *
 */
public class ApplicationServer {

	/**
	 * Porta a cui il Server si mette in ascolto.
	 *
	 */
    private int port;
    
    /**
     * Lista di Servlet per gestire le richieste fatte nell'applicazione web.
     *
     */
    private List<Servlet> servletList;
    
    /**
     * Istanza di Server che gestisce l'applicazione web.
     *
     */
    private Server server;

    /**
   	 * Il costruttore imposta gli attributi della classe.
   	 * @param port Porta su cui si avvia il Server
   	 * @param servletList Lista di Servlet necessarie per gestire le richieste
   	 */
    public ApplicationServer(int port, List<Servlet> servletList) {
        this.port = port;
        this.servletList = servletList;
    }
    
    /**
	 * Crea un'istanza di Server e vi associa un ServletContextHandler, che presenta l'elenco delle Servlet utilizzate.
	 * Avvia il Server che gestisce l'applicazione web.
	 * @throws Exception
	 */
    public void start() throws Exception {
        server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
     
        handler.addServlet(new ServletHolder(servletList.get(0)), "/");
        handler.addServlet(new ServletHolder(servletList.get(1)), "/d20/*");
        handler.addServlet(new ServletHolder(servletList.get(2)), "/d20/selection/*");
        handler.addServlet(new ServletHolder(servletList.get(3)), "/d20/selection/vendings/*");
        handler.addServlet(new ServletHolder(servletList.get(4)), "/d20/selection/operators/*");
        handler.addServlet(new ServletHolder(servletList.get(5)), "/d20/selection/keys/*");
        handler.addServlet(new ServletHolder(servletList.get(6)), "/d20/selection/beverages/*");
        
        addStaticFileServing(handler);
        server.setHandler(handler);
        server.start();
    }
    
    /**
	 * Arresta il Server che gestisce l'applicazione web.
	 * @throws Exception
	 */
    public void stop() throws Exception {
        server.stop();
    }
    
    /**
	 * Aggiunge al ServletContextHandler le Servlet per gestire lo stile grafico delle pagine.
	 * @param handler ServletContextHandler che contiene l'elenco di Servlet
	 */
    private void addStaticFileServing(ServletContextHandler handler) {
        ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
        holderPwd.setInitParameter("resourceBase", "./res/webapp/style");
        holderPwd.setInitParameter("dirAllowed","false");
        holderPwd.setInitParameter("pathInfoOnly","true");
        
        handler.addServlet(holderPwd, "/style/*");
        handler.addServlet(holderPwd, "/d20/style/*");
        handler.addServlet(holderPwd, "/d20/selection/style/*");
        handler.addServlet(holderPwd, "/d20/selection/vendings/style/*");
        handler.addServlet(holderPwd, "/d20/selection/operators/style/*");
        handler.addServlet(holderPwd, "/d20/selection/keys/style/*");
        handler.addServlet(holderPwd, "/d20/selection/beverages/style/*");
    }

}
