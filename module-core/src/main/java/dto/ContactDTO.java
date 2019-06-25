package dto;


import entities.Contact;

import java.util.List;

public class ContactDTO {
//
//    private Integer id;
//    private Integer address_id;
//    private String firstName;
//    private String lastName;
//    private String middleName;
//    private Date birthDate;
//    private Boolean gender;
//    private String citizenship;
//    private String maritalStatus;
//    private String webSite;
//    private String email;
//    private String company;


    private List<Contact> contacts;
    private Contact contact;

    public ContactDTO() {
    }

    public ContactDTO(Contact contact) {
        this.contact = contact;
    }

    public ContactDTO(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public ContactDTO setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public ContactDTO setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    @Override
    public String toString() {
        if (contacts == null) {
            return "MainContactDTO{" +
                    "mainContact=" + contact +
                    '}';
        } else return "MainContactDTO{" +
                "mainContacts=" + contacts +
                '}';
    }
}
