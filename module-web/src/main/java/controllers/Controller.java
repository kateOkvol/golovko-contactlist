package controllers;

import dto.MainContactDTO;
import services.MainContactService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/contactsList")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class Controller {

    @Path(value = "/getAll")
    @GET
    public Response searchContacts() {
        MainContactDTO dto = new MainContactService().findAll();
        return Response.ok(dto).build();
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        Response response = c.searchContacts();
    }
}
