package dao;

import entities.Contact;

public interface ContactDAO {
    Integer create(Contact contact);

    Contact getById(Integer id);

    void update(Contact contact);

    void delete(Integer id);


}