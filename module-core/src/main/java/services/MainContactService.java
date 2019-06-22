package services;

import dao.DAOImpl.MainContactDAOImpl;
import db.DataBaseConnection;
import dto.MainContactDTO;
import entities.MainContact;

import java.sql.SQLException;
import java.util.List;

public class MainContactService {
    MainContactDAOImpl dao;
    MainContactDTO dto;

    public MainContactService() {
        try {
            this.dao = new MainContactDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the service");
        }
    }

    public MainContactDTO findAll() {
        List<MainContact> list = this.dao.getAll();
        this.dto = new MainContactDTO(list);
        return this.dto;
    }

//    public static void main(String[] args) {
//        MainContactService service = new MainContactService();
//        MainContactDTO dto = service.findAll();
//
//        System.out.println(dto.toString());
//    }
}

