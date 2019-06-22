package dto;

import entities.MainContact;

import java.util.List;

public class MainContactDTO {
    private List<MainContact> mainContacts;

    public MainContactDTO() {
    }

    public MainContactDTO(List<MainContact> mainContacts){
        this.mainContacts = mainContacts;
    }

    public List<MainContact> getMainContacts() {
        return mainContacts;
    }

    @Override
    public String toString() {
        return "MainContactDTO{" +
                "mainContacts=" + mainContacts.toString() +
                '}';
    }
}