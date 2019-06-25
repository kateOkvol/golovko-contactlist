package controllers;

import dto.MainContactDTO;
import services.MainContactService;


public class MainContactsController {

    public MainContactDTO searchContacts() {
        return new MainContactService().findAll();
    }

//    public Response deleteContact(@PathParam("id") Integer contactId) {
//        new MainContactService().deleteContact(contactId);
//        return Response.status(Response.Status.NO_CONTENT).build();
//    }
//
//    public static void main(String[] args) {
//        MainContactsController c = new MainContactsController();
//        Response response = c.searchContacts();
//
//        System.out.println(response.getEntity().toString());
////        c.deleteContact(3);
//    }
}
