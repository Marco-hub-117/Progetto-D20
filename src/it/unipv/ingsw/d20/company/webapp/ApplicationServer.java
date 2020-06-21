package it.unipv.ingsw.d20.company.webapp;

import java.util.List;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ApplicationServer {

    private int port;
    private List<Servlet> servletList;
    private Server server;

    public ApplicationServer(int port, List<Servlet> servletList) {
        this.port = port;
        this.servletList = servletList;
    }

    public void start() throws Exception {
        server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        
        //handler.addServlet(new ServletHolder(servletList.get(0)), "/");
        handler.addServlet(new ServletHolder(servletList.get(0)), "/d20/*");
        handler.addServlet(new ServletHolder(servletList.get(1)), "/d20/selection/*");
        handler.addServlet(new ServletHolder(servletList.get(2)), "/d20/selection/vendings/*");
        handler.addServlet(new ServletHolder(servletList.get(3)), "/d20/selection/operators/*");
        handler.addServlet(new ServletHolder(servletList.get(4)), "/d20/selection/keys/*");
        handler.addServlet(new ServletHolder(servletList.get(5)), "/d20/selection/beverages/*");
        
        addStaticFileServing(handler);
        server.setHandler(handler);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
    
    private void addStaticFileServing(ServletContextHandler handler) {
        ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
        holderPwd.setInitParameter("resourceBase", "./res/webapp/style");
        holderPwd.setInitParameter("dirAllowed","false");
        holderPwd.setInitParameter("pathInfoOnly","true");
        handler.addServlet(holderPwd, "/d20/style/*");
        handler.addServlet(holderPwd, "/d20/selection/style/*");
        handler.addServlet(holderPwd, "/d20/selection/vendings/style/*");
        handler.addServlet(holderPwd, "/d20/selection/operators/style/*");
        handler.addServlet(holderPwd, "/d20/selection/keys/style/*");
        handler.addServlet(holderPwd, "/d20/selection/beverages/style/*");
    }

}
