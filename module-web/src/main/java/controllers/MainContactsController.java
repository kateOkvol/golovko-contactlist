package controllers;

import dto.MainContactDTO;
import services.MainContactService;

import java.util.List;


public class MainContactsController {

    public List<MainContactDTO> searchContacts() {
        return new MainContactService().findAll();
    }



//
//    public static void main(String[] args) {
//        MainContactsController c = new MainContactsController();
//        Response response = c.searchContacts();
//
//        System.out.println(response.getEntity().toString());
////        c.deleteContact(3);
//    }
}
