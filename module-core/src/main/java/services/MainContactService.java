package services;

import DAO.DAOImpl.MainContactDAOImpl;
import DB.DataBaseConnection;
import DTO.MainContactDTO;

import java.sql.SQLException;

public class MainContactService {
    MainContactDAOImpl dao;

    public MainContactService() {
        try {
            this.dao = new MainContactDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the service");
        }
    }

    public MainContactDTO findAll(){
        return new MainContactDTO(dao.getAll());

    }

//    public static void main(String[] args) {
//        System.out.println((new MainContactService()).findAll().getMainContacts());
//    }
}

