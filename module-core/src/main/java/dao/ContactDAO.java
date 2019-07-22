package dao;

import entities.Contact;

import java.sql.Date;
import java.util.List;

public interface ContactDAO {
    Integer create(Contact contact);

    Contact getById(Integer id);

    List<Contact> getByBirthDate(Date date);

    void update(Contact contact);

    List<String> delete(Integer id);
}