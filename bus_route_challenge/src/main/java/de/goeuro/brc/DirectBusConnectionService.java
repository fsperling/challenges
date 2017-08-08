package de.goeuro.brc;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;


/**
 *
 * @author felix
 */
public class DirectBusConnectionService {
    
    public static void main(String[] args) throws Exception {
        
        if (args.length < 1) {
            System.out.println("You need to supply a filename.");
        } else {
                 
        BusRoute busroutes = new BusRoute(args[0]);
        Map<Integer, HashSet> connections = busroutes.getRoutes();
        System.out.println(busroutes.toString());
        launchServlet();
    
        }
    }

  

    
    
    
    private static void launchServlet() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/api/");

        Server jettyServer = new Server(8088);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                TestResource.class.getCanonicalName());
        
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}