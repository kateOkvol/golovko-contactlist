package dao.DAOImpl;

import dao.MainNumberDAO;
import db.DataBaseConnection;
import entities.MainNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MainNumberDAOImpl implements MainNumberDAO {
    public MainNumberDAOImpl() {
    }

    @Override
    public List<MainNumber> getAll(Integer contactId) {
        List<MainNumber> list = new LinkedList<>();
        String sql = "SELECT contact_id, id, concat_ws(' ', country_code, operator_code, phone) " +
                "AS full_number, type::contacts.phone_type, regexp_replace(note, '\\s+$', '') as note " +
                "FROM contacts.number " +
                "WHERE contact_id = ?;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contactId);
            ResultSet resultSet = statement.executeQuery();
            try {
                while (resultSet.next()) {
                    MainNumber number = new MainNumber();
                    number.setId(resultSet.getInt("id"));
                    number.setContactId(resultSet.getInt("contact_id"));
                    number.setFullNumber(resultSet.getString("full_number"));
                    number.setType(resultSet.getString("type"));
                    number.setNote(resultSet.getString("note"));
                    list.add(number);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.number WHERE id = ?;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("error main contact");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
