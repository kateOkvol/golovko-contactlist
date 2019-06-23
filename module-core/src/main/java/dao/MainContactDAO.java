package dao;

import entities.MainContact;

import java.util.List;

public interface MainContactDAO {
    void create(MainContact mainContact);

    List<MainContact> getAll();

    MainContact getById(Integer id);

    void update(MainContact mainContact);

    void delete(Integer id);
}
