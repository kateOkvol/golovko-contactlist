package services;

import dao.DAOImpl.MainNumberDAOImpl;
import db.DataBaseConnection;
import dto.MainNumberDTO;
import entities.MainNumber;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainNumberService {
    private MainNumberDAOImpl dao;

    public MainNumberService() {
        try {
            this.dao = new MainNumberDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the main service");
        }
    }

    public List<MainNumberDTO> findAll(Integer contactId) {
        List<MainNumber> list = this.dao.getAll(contactId);
        List<MainNumberDTO> contactList = new ArrayList<>();
        for (MainNumber contact : list) {
            contactList.add(new MainNumberDTO(contact));
        }
        return contactList;
    }
}
