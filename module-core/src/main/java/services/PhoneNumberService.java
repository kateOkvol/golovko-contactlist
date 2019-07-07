package services;

import dao.DAOImpl.PhoneNumberDAOImpl;
import db.DataBaseConnection;
import dto.PhoneNumberDTO;
import entities.PhoneNumber;

import java.sql.SQLException;

public class PhoneNumberService {

    private PhoneNumberDAOImpl dao;
    private PhoneNumberDTO dto;

    public PhoneNumberService() {
        try {
            this.dao = new PhoneNumberDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
    }

    public PhoneNumberService(PhoneNumberDTO dto) {
        try {
            this.dao = new PhoneNumberDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
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
        phoneNumber.setContactID(dto.getContactID());
        phoneNumber.setNumber(dto.getNumber());
        phoneNumber.setCountryCode(dto.getCountryCode());
        phoneNumber.setOperatorCode(dto.getOperatorCode());
        phoneNumber.setNote(dto.getNote());
        phoneNumber.setType(dto.getType());
        return phoneNumber;
    }
}
