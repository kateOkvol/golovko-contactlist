package services;

import dao.DAOImpl.MainContactDAOImpl;
import db.DataBaseConnection;
import dto.MainContactDTO;
import entities.MainContact;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

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

    public List<MainContactDTO> findAllMatches(int page, String query) {
        List<MainContact> list = this.dao.search(page, query);
        return writeResultAsDTO(list);
    }

    public HashMap<String, String> getPageInfo(String query) {
        HashMap<String, String> map;
        map = pageInfo(String.valueOf(this.dao.countContacts(query)));
        return map;
    }

    private List<MainContactDTO> writeResultAsDTO(List<MainContact> list) {
        List<MainContactDTO> contactList = new ArrayList<>();
        for (MainContact contact : list) {
            contactList.add(new MainContactDTO(contact));
        }
        return contactList;
    }

    private HashMap<String, String> pageInfo(String count) {
        Properties p = new Properties();
        HashMap<String, String> map = new HashMap<>();
        try {
            p.load(MainContactService.class.getClassLoader().getResourceAsStream("config-core.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("count", count);
        map.put("contact_amount", String.valueOf(Integer.parseInt(p.getProperty("contact_amount"))));
        return map;
    }
}

