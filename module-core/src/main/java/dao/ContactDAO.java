package dao;

import entities.Contact;

import java.util.List;

public interface ContactDAO {
    void create(Contact contact);

    List<Contact> getAll();

    Contact getById(Integer id);

    void update(Contact object);

    void delete(Contact object);


}