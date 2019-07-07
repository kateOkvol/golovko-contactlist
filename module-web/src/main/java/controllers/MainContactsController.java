package controllers;

import dto.MainContactDTO;
import services.MainContactService;

import java.util.List;


public class MainContactsController {
    public MainContactsController() {
    }

    public List<MainContactDTO> searchContacts() {
        return new MainContactService().findAll();
    }
}
