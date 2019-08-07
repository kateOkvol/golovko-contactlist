package services;

import dao.DAOImpl.PhoneNumberDAOImpl;
import dto.PhoneNumberDTO;
import entities.PhoneNumber;
import org.apache.log4j.Logger;

public class PhoneNumberService {
    private static final Logger logger = Logger.getLogger(PhoneNumberService.class);
    private PhoneNumberDAOImpl dao;
    private PhoneNumberDTO dto;

    public PhoneNumberService() {
        this.dao = new PhoneNumberDAOImpl();
    }

    public PhoneNumberService(PhoneNumberDTO dto) {
        this.dao = new PhoneNumberDAOImpl();
        this.dto = dto;
    }

    public PhoneNumberDTO getById(Integer id) {
        return new PhoneNumberDTO(dao.getById(id));
    }

    public void createPhoneNumber() {
        dao.create(setContactFields());
    }

    public void updatePhoneNumber() {
        dao.update(setContactFields());
    }

    public void deletePhoneNumber(Integer id) {
        this.dao.delete(id);

    }

    private PhoneNumber setContactFields() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(dto.getId());
        phoneNumber.setContactId(dto.getContactId());
        phoneNumber.setPhone(dto.getPhone());
        phoneNumber.setCountryCode(dto.getCountryCode());
        phoneNumber.setOperatorCode(dto.getOperatorCode());
        phoneNumber.setNote(dto.getNote());
        phoneNumber.setType(dto.getType());
        return phoneNumber;
    }
}
