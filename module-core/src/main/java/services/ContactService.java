package services;

import dao.DAOImpl.ContactDAOImpl;
import db.DataBaseConnection;

import java.sql.SQLException;

public class ContactService {
    private ContactDAOImpl contactDAO;

    public ContactService() {
        try {
            this.contactDAO = new ContactDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
    }

    public void deleteContact(Integer id){
        this.contactDAO.delete(id);

    }
}
