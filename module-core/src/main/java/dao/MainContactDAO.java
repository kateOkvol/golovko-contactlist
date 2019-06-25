package dao;

import entities.MainContact;

import java.util.List;

public interface MainContactDAO {
    List<MainContact> getAll();

    MainContact getById(Integer id);

    void delete(Integer id);
}
