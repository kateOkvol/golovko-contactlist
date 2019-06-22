package dao;

import entities.Address;

import java.util.List;

public interface AddressDAO {
    void create(Address address);

    List<Address> getAll();

    Address getById(Integer id);

    void update(Address object);

    void delete(Address object);
}
