package services;

import dao.DAOImpl.MainContactDAOImpl;
import db.DataBaseConnection;
import dto.MainContactDTO;
import entities.MainContact;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainContactService {
    private MainContactDAOImpl dao;

    public MainContactService() {
        try {
            this.dao = new MainContactDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the main service");
        }
    }

    public List<MainContactDTO> findAll(int page) {
        List<MainContact> list = this.dao.getAll(page);
        return writeResultAsDTO(list);
    }

    public List<MainContactDTO> findAllMatches(String query){
        List<MainContact> list = this.dao.search(query);
       return writeResultAsDTO(list);
    }

    private List<MainContactDTO> writeResultAsDTO(List<MainContact> list){
        List<MainContactDTO> contactList = new ArrayList<>();
        for (MainContact contact : list) {
            contactList.add(new MainContactDTO(contact));
        }
        return contactList;
    }
}

