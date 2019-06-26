package services;

import dao.DAOImpl.MainContactDAOImpl;
import db.DataBaseConnection;
import dto.MainContactDTO;
import entities.MainContact;

import java.sql.SQLException;
import java.util.List;

public class MainContactService {
    private MainContactDAOImpl dao;
    private MainContactDTO dto;

    public MainContactService() {
        try {
            this.dao = new MainContactDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the main service");
        }
    }

    public MainContactDTO findAll() {
        List<MainContact> list = this.dao.getAll();
        return new MainContactDTO(list);
    }

//    public MainContactDTO getById(Integer id) {
//        return new MainContactDTO(dao.getById(id));
//    }

    public void deleteContact(Integer id) {
        this.dao.delete(id);
        new ContactService().deleteContact(id);
    }

    public static void main(String[] args) {
        MainContactService service = new MainContactService();

        MainContactDTO dto = service.findAll();

        System.out.println(dto.toString());

//        System.out.println(service.getById(3));

//        service.deleteContact(3);
    }

}

