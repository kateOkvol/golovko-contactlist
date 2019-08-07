package services;

import dao.DAOImpl.MainNumberDAOImpl;
import dto.MainNumberDTO;
import entities.MainNumber;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainNumberService {
    private static final Logger logger = Logger.getLogger(MainNumberService.class);

    private MainNumberDAOImpl dao;

    public MainNumberService() {
            this.dao = new MainNumberDAOImpl();
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
