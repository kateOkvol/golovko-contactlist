package controllers;

import dto.MainContactDTO;
import services.MainContactService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/contactsList")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class MainContactsController {

    @Path(value = "/getAll")
    @GET
    @Produces("application/json")
    public Response searchContacts() {
        MainContactDTO dto = new MainContactService().findAll();
        return Response.ok(dto).build();
    }

    @Path(value = "/{id}")
    @DELETE
    public Response deleteContact(@PathParam("id") Integer contactId) {
        new MainContactService().deleteContact(contactId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static void main(String[] args) {
        MainContactsController c = new MainContactsController();
//        Response response = c.searchContacts();
//
//        System.out.println(response.getEntity().toString());
        c.deleteContact(3);
    }
}
