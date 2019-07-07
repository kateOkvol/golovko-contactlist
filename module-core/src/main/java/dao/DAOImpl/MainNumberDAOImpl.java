package dao.DAOImpl;

import dao.MainNumberDAO;
import entities.MainNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MainNumberDAOImpl implements MainNumberDAO {
    private Connection connection;

    public MainNumberDAOImpl() {
    }

    public MainNumberDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<MainNumber> getAll(Integer contactId) {
        List<MainNumber> list = null;
        String sql = "SELECT contact_id, id, concat_ws(' ', country_code, operator_code, numder) " +
                "AS full_number, type::contacts.phone_type, note " +
                "FROM contacts.number " +
                "WHERE contact_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contactId);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.number WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("error main contact");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<MainNumber> parseResultSet(ResultSet set) {
        LinkedList<MainNumber> list = new LinkedList<>();
        try {
            while (set.next()) {
                MainNumber number = new MainNumber();
                number.setId(set.getInt("id"));
                number.setContactId(set.getInt("contact_id"));
                number.setFullNumber(set.getString("full_number"));
                number.setType(set.getString("type"));
                number.setNote(set.getString("note"));
                list.add(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
