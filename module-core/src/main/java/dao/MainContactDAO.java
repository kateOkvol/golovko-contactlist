package dao;

import entities.MainContact;

import java.util.List;

public interface MainContactDAO {
    List<MainContact> getAll(int page);
}
