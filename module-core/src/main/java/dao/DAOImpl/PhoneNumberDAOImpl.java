package dao.DAOImpl;

import dao.PhoneNumberDAO;
import db.DataBaseConnection;
import entities.PhoneNumber;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {
    private final static Logger logger = Logger.getLogger(PhoneNumberDAOImpl.class);
    public PhoneNumberDAOImpl() {
    }

    @Override
    public void create(PhoneNumber phoneNumber) {
        String sql = "INSERT INTO contacts.number (contact_id, phone, country_code, operator_code, note, type)" +
                "VALUES (?, ?, ?, ?, ?, ?::contacts.phone_type);";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, phoneNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("PhoneNumber DAO, create method:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }

    @Override
    public PhoneNumber getById(Integer id) {
        PhoneNumber phoneNumber = new PhoneNumber();
        String sql = "SELECT id, contact_id, phone, country_code, operator_code, regexp_replace(note, '\\s+$', '') as note, type::contacts.phone_type " +
                "FROM contacts.number WHERE id = ?";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            try {
                while (resultSet.next()) {
                    phoneNumber.setId((Integer) resultSet.getObject("id"));
                    phoneNumber.setContactId((Integer) resultSet.getObject("contact_id"));
                    phoneNumber.setPhone((Integer) resultSet.getObject("phone"));
                    phoneNumber.setCountryCode((Integer) resultSet.getObject("country_code"));
                    phoneNumber.setOperatorCode((Integer) resultSet.getObject("operator_code"));
                    phoneNumber.setNote(resultSet.getString("note"));
                    phoneNumber.setType(resultSet.getString("type"));
                }
            } catch (SQLException e) {
                logger.error("PhoneNumber DAO, getById method, parse ResultSet:\n\t" + Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
        } catch (SQLException e) {
            logger.error("PhoneNumber DAO, getById method:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        return phoneNumber;
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        String sql = "UPDATE contacts.number SET contact_id = ?, phone = ?, country_code = ?, operator_code = ?, " +
                "note = ?, type = ?::contacts.phone_type  WHERE id = ?;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(7, phoneNumber.getId());
            partOfPrepare(statement, phoneNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("PhoneNumber DAO, update method:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.number WHERE id = ?;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("PhoneNumber DAO, delete method:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
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
