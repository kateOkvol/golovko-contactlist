package services;

import dao.DAOImpl.ContactDAOImpl;
import db.DataBaseConnection;
import dto.ContactDTO;

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

//    public ContactDTO findAll() {
//        List<Contact> list = this.dao.getAll();
//        return new ContactDTO(list);
//    }

    public ContactDTO getById(Integer id) {
        return new ContactDTO(dao.getById(id));
    }

    public void deleteContact(Integer id){
        this.dao.delete(id);

    }
}
