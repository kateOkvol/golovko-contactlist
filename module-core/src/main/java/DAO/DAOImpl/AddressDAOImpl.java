package DAO.DAOImpl;

import DAO.AddressDAO;
import entities.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private Connection connection;

    public AddressDAOImpl() {
    }

    public AddressDAOImpl(Connection connection) {
        this.connection = connection;
    }

    private class PersistAddress extends Address {
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    private List<Address> parseResultSet(ResultSet set) {
        LinkedList<Address> result = new LinkedList<>();

        try {
            while (set.next()) {
                PersistAddress address = new PersistAddress();
                address.setId(set.getInt("id"));
                address.setCountry(set.getString("country"));
                address.setCity(set.getString("city"));
                address.setStreet(set.getString("street"));
                address.setHouse(set.getString("house"));
                address.setFlat(set.getString("flat"));
                address.setZipCode(set.getInt("zip_code"));
                result.add(address);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return result;
    }


    @Override
    public void create(Address address) {
        String sql = "INSERT INTO contacts.address (country, city, street, house, flat, zip_code) \n" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, address);
            statement.setInt(7, address.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAll() {
        List<Address> list = null;
        String sql = "SELECT * FROM contacts.address";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("разраб свою ошибку, getAll адреса");
        }
        return list;
    }

    @Override
    public Address getById(Integer id) {
        Address address = new Address();
        String sql = "SELECT * FROM contacts.address WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            address = parseResultSet(resultSet).get(1);
        } catch (SQLException e) {
            System.out.println("разраб свою ошибку, getbyid адреса");
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE contacts.address SET country = ?, city = ?, street = ?, " +
                     "house = ?, flat = ?, zip_code = ? WHERE id= ?;";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            partOfPrepare(statement, address);
            statement.setInt(7, address.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                //throw new Exception();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Address address) {
        String sql = "DELETE FROM contacts.address WHERE id= ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, address.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("разраб свою ошибку, делит адреса");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void partOfPrepare(PreparedStatement statement, Address address) throws SQLException {
        statement.setString(1, address.getCountry());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getStreet());
        statement.setString(4, address.getHouse());
        statement.setString(5, address.getFlat());
        statement.setInt(6, address.getZipCode());
    }
}
