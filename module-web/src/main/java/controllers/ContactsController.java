package controllers;

import dto.ContactDTO;
import services.ContactService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/contact-editor")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ContactsController {
    @Path(value = "/getById/{id}")
    @GET
    @Produces("application/json")
    public Response getContact(@PathParam("id") Integer id) {
        ContactDTO dto = new ContactService().getById(id);
        return Response.ok(dto).build();
    }

    @Path(value = "/{id}")
    @DELETE
    public Response deleteContact(@PathParam("id") Integer id) {
        new ContactService().deleteContact(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static void main(String[] args) {
        ContactsController c = new ContactsController();
        Response response = c.getContact(3);

        System.out.println(response.getEntity().toString());
//        c.deleteContact(3);
    }
}
