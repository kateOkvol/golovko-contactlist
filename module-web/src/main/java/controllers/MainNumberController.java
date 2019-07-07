package controllers;

import dto.MainNumberDTO;
import services.MainNumberService;

import java.util.List;

public class MainNumberController {
    public MainNumberController() {
    }

    public List<MainNumberDTO> searchPhones(Integer contactId) {
        return new MainNumberService().findAll(contactId);
    }
}
