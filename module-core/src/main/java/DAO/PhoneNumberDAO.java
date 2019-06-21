package DAO;

import entities.PhoneNumber;

import java.util.List;

public interface PhoneNumberDAO {
    void create(PhoneNumber phoneNumber);

    List<PhoneNumber> getAll();

    PhoneNumber getById(Integer id);

    void update(PhoneNumber object);

    void delete(PhoneNumber object);


}
