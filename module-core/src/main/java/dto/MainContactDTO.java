package dto;

import entities.MainContact;

import java.util.List;

public class MainContactDTO {
    private MainContact mainContact;
    private List<MainContact> mainContacts;

    public MainContactDTO() {
    }

    public MainContactDTO(MainContact mainContact) {
        this.mainContact = mainContact;
    }

    public MainContactDTO(List<MainContact> mainContacts) {
        this.mainContacts = mainContacts;
    }

    public MainContact getMainContact() {
        return mainContact;
    }

    public MainContactDTO setMainContact(MainContact mainContact) {
        this.mainContact = mainContact;
        return this;
    }

    public List<MainContact> getMainContacts() {
        return mainContacts;
    }

    @Override
    public String toString() {
        if (mainContacts == null) {
            return "MainContactDTO{" +
                    "mainContact=" + mainContact +
                    '}';
        } else return "MainContactDTO{" +
                "mainContacts=" + mainContacts +
                '}';
    }
}