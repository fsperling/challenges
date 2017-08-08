package de.goeuro.brc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("direct")
public class TestResource {

    @GET
    public String sessions() {
      return "hello world";
      
    }
}
