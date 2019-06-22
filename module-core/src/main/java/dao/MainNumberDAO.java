package dao;

import entities.MainNumber;

import java.util.List;

public interface MainNumberDAO {
    void create(MainNumber number);

    List<MainNumber> getAll();

    MainNumber getById(Integer id);

    void update(MainNumber object);

    void delete(MainNumber object);


}
