package controllers;

import dto.ContactDTO;
import services.ContactService;

public class ContactsController {

    public void createContact(ContactDTO contactDTO){
        new ContactService(contactDTO).createContact();
    }

    public void deleteContact(Integer contactId) {
        new ContactService().deleteContact(contactId);
    }

    public ContactDTO getContact(Integer id) {
        return new ContactService().getById(id);
    }


//
//
//    public Response deleteContact(@PathParam("id") Integer id) {
//        new ContactService().deleteContact(id);
//        return Response.status(Response.Status.NO_CONTENT).build();
//    }
//
//    public static void main(String[] args) {
//        ContactsController c = new ContactsController();
//        Response response = c.getContact(3);
//
//        System.out.println(response.getEntity().toString());
////        c.deleteContact(3);
//    }
}
