package dao;

import entities.MainNumber;

import java.util.List;

public interface MainNumberDAO {
    List<MainNumber> getAll(Integer contactId);

    void delete(Integer id);
}
