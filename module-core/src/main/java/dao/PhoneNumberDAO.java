package dao;

import entities.PhoneNumber;

public interface PhoneNumberDAO {
    void create(PhoneNumber phoneNumber);

    PhoneNumber getById(Integer id);

    void update(PhoneNumber phoneNumber);

    void delete(Integer id);
}
