package dao.DAOImpl;

import dao.PhoneNumberDAO;
import entities.PhoneNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {
    private Connection connection;

    public PhoneNumberDAOImpl() {
    }

    public PhoneNumberDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PhoneNumber phoneNumber) {
        String sql = "INSERT INTO contacts.number (contact_id, phone, country_code, operator_code, note, type)" +
                "VALUES (?, ?, ?, ?, ?, ?::contacts.phone_type);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, phoneNumber);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("contact update exception");//throw new Exception();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PhoneNumber getById(Integer id) {
        PhoneNumber phoneNumber = new PhoneNumber();
        String sql = "SELECT id, contact_id, phone, country_code, operator_code, regexp_replace(note, '\\s+$', '') as note, type::contacts.phone_type " +
                "FROM contacts.number WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            phoneNumber = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        String sql = "UPDATE contacts.number SET contact_id = ?, phone = ?, country_code = ?, operator_code = ?, " +
                "note = ?, type = ?::contacts.phone_type  WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(7, phoneNumber.getId());
            partOfPrepare(statement, phoneNumber);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("contact update exception");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.number WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<PhoneNumber> parseResultSet(ResultSet set) {
        LinkedList<PhoneNumber> list = new LinkedList<>();
        try {
            while (set.next()) {
                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setId((Integer) set.getObject("id"));
                phoneNumber.setContactId((Integer) set.getObject("contact_id"));
                phoneNumber.setPhone((Integer) set.getObject("phone"));
                phoneNumber.setCountryCode((Integer) set.getObject("country_code"));
                phoneNumber.setOperatorCode((Integer) set.getObject("operator_code"));
                phoneNumber.setNote(set.getString("note"));
                phoneNumber.setType(set.getString("type"));
                list.add(phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void partOfPrepare(PreparedStatement statement, PhoneNumber number) throws SQLException {
        statement.setInt(1, number.getContactId());
        statement.setInt(2, number.getPhone());
        statement.setObject(3, number.getCountryCode());
        statement.setObject(4, number.getOperatorCode());
        statement.setObject(5, number.getNote());
        statement.setObject(6, number.getType());
    }
}
