package services;

import dao.DAOImpl.ContactDAOImpl;
import db.DataBaseConnection;
import dto.ContactDTO;
import entities.Contact;

import java.sql.SQLException;

public class ContactService {
    private ContactDAOImpl dao;
    private ContactDTO dto;

    public ContactService() {
        try {
            this.dao = new ContactDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
    }

    public ContactService(ContactDTO dto) {
        this.dto = dto;
    }

    public ContactDTO getById(Integer id) {
        return new ContactDTO(dao.getById(id));
    }

    public void createContact() {
        Contact contact = new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setMiddleName(dto.getMiddleName());
        contact.setGender(dto.getGender());
        contact.setBirthDate(dto.getBirthDate());
        contact.setCitizenship(dto.getCitizenship());
        contact.setMaritalStatus(dto.getMaritalStatus());
        contact.setEmail(dto.getEmail());
        contact.setWebSite(dto.getWebSite());
        contact.setCompany(dto.getCompany());
        contact.setCountry(dto.getCountry());
        contact.setCity(dto.getCity());
        contact.setStreet(dto.getStreet());
        contact.setHouse(dto.getHouse());
        contact.setFlat(dto.getFlat());
        contact.setZipCode(dto.getZipCode());

        dao.create(contact);
    }

    public void deleteContact(Integer id) {
        this.dao.delete(id);

    }
}
