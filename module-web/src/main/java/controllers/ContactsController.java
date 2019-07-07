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

    public void updateContact(ContactDTO contactDTO){
        new ContactService(contactDTO).updateContact();
    }
}
