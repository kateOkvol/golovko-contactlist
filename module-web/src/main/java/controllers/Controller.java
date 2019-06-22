package controllers;

import DTO.MainContactDTO;
import services.MainContactService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;


@Path(value = "/contactsList")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class Controller {

    @Path(value = "/getAll")
    @GET
    public Response searchContacts(@QueryParam("fullName") String fullName,
                                   @QueryParam("birthDate") Date birthDate,
                                   @QueryParam("address") String address,
                                   @QueryParam("company") String company) {
        MainContactDTO dto = new MainContactService().findAll();
        return Response.ok(dto).build();
    }
}
