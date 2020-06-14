package it.unipv.ingsw.d20.company.webapp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;

public class ApplicationServer {

    private int port;
    private Servlet servlet;
    private Server server;

    public ApplicationServer(int port, Servlet servlet) {
        this.port = port;
        this.servlet = servlet;
    }

    public void start() throws Exception {
    	initTemplateEngine();
        server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(servlet), "/*");
        addStaticFileServing(handler);
        server.setHandler(handler);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
    
    private void addStaticFileServing(ServletContextHandler handler) {
        ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
        holderPwd.setInitParameter("resourceBase", "./webapp-resources/style");
        holderPwd.setInitParameter("dirAllowed","false");
        holderPwd.setInitParameter("pathInfoOnly","true");
        handler.addServlet(holderPwd, "/style/*");
    }
    
    private void initTemplateEngine() {
        Map<String, Object> conf = new HashMap<>();
        //conf.put("home.template", "templates");
        conf.put("home.template", "/Users/luigidelpio/Desktop/ingegneria_del_software/esercitazioni/progetto/Progetto-D20/webapp-resources/pages");
        Rythm.init(conf);
    }
}
