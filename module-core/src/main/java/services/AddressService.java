package services;

import dao.DAOImpl.AddressDAOImpl;
import db.DataBaseConnection;

import java.sql.SQLException;

public class AddressService {
    private AddressDAOImpl addressDAO;

    public AddressService() {
        try {
            this.addressDAO = new AddressDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
    }

    public void deleteContact(Integer id){
        this.addressDAO.delete(id);

    }
}
