package dao;

import entities.Contact;

import java.util.List;

public interface ContactDAO {
    Integer create(Contact contact);

    Contact getById(Integer id);

    void update(Contact contact);

    List<String> delete(Integer id);
}