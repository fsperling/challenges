package de.goeuro.brc;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author felix
 */
public class DirectBusConnectionService {
    private static BusNetwork busnetwork;

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("You need to supply a filename.");
        } else {
            busnetwork = new BusNetwork(args[0]);
            launchServlet();
        }
    }

    private static void launchServlet() throws Exception {
        String contextPath = "";
        String appBase = ".";
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8088";
        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);
        Context context = tomcat.addWebapp(contextPath, appBase);

        Tomcat.addServlet(context, "jersey-container-servlet",
                new ServletContainer(resourceConfig()));
        context.addServletMapping("/api/*", "jersey-container-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    public static BusNetwork getBusNetwork() {
        return busnetwork;
    }

    private static ResourceConfig resourceConfig() {
        return new JerseyConfiguration();
    }

}
