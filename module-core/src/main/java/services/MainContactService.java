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

    public List<MainContactDTO> findAll() {
        List<MainContact> list = this.dao.getAll();
        List<MainContactDTO> contactList = new ArrayList<>();
        for (MainContact contact : list) {
            contactList.add(new MainContactDTO(contact));
        }
        return contactList;
    }





//    public static void main(String[] args) {
//        MainContactService service = new MainContactService();
//
//        List<MainContactDTO> dto = service.findAll();
//
//        System.out.println(dto.toString());
//
////        System.out.println(service.getById(3));
//
////        service.deleteContact(3);
//
//    }
//    public MainContactDTO getById(Integer id) {
//        return new MainContactDTO(dao.getById(id));
//    }
}

