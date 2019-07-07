package controllers;

import dto.PhoneNumberDTO;
import services.PhoneNumberService;

public class NumberController {

    public NumberController() {
    }

    public void createPhone(PhoneNumberDTO contactDTO) {
        new PhoneNumberService(contactDTO).createPhoneNumber();
    }

    public void deletePhone(Integer contactId) {
        new PhoneNumberService().deletePhoneNumber(contactId);
    }

    public PhoneNumberDTO getPhone(Integer id) {
        return new PhoneNumberService().getById(id);
    }

    public void updatePhone(PhoneNumberDTO contactDTO) {
        new PhoneNumberService(contactDTO).updatePhoneNumber();
    }
}
