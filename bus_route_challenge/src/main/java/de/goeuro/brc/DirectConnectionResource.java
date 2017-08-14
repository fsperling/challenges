package de.goeuro.brc;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("direct")
public class DirectConnectionResource {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response direct(
            @QueryParam("dep_sid") Integer dep_sid, 
            @QueryParam("arr_sid") Integer arr_sid) {
        
      System.out.println("dep_sid: " + dep_sid);
      System.out.println("arr_sid: " + arr_sid);
      
      if (dep_sid == null || arr_sid == null) {
          return Response.status(Response.Status.BAD_REQUEST).build();
      }
      
      if (dep_sid.equals(arr_sid)) {
             System.out.println("start and stop are the same");
             return Response.status(Response.Status.BAD_REQUEST).build();

      }
      
      boolean directConnection = DirectBusConnectionService.getBusNetwork().hasDirectConnection(dep_sid, arr_sid);
      
      JsonObject value = Json.createObjectBuilder()
        .add("dep_sid", dep_sid)
        .add("arr_sid", arr_sid)
        .add("direct_bus_route", directConnection)
        .build();

      return Response.ok(value.toString(), MediaType.APPLICATION_JSON).build();      
    }
                
            
            
}
