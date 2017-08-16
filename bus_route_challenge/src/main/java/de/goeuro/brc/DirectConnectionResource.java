package de.goeuro.brc;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("direct")
public class DirectConnectionResource {

    @GET
    public Response direct(
            @QueryParam("dep_sid") Integer dep_sid,
            @QueryParam("arr_sid") Integer arr_sid) {

        if (dep_sid == null || arr_sid == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("dep_sid or arr_sid is missing").build();
        }

        if (dep_sid.equals(arr_sid)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("dep_sid and arr_sid can not be the same").build();
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
