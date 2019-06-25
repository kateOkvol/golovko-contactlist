package dao;

import entities.Contact;

public interface ContactDAO {
    void create(Contact contact);

    Contact getById(Integer id);

    void update(Contact object);

    void delete(Integer id);


}