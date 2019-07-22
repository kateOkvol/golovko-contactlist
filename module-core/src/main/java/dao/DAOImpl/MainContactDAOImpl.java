package dao.DAOImpl;

import dao.MainContactDAO;
import entities.MainContact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MainContactDAOImpl implements MainContactDAO {

    private Connection connection;

    public MainContactDAOImpl() {
    }

    public MainContactDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<MainContact> getAll() {
        List<MainContact> list = null;

        String sql = "SELECT id, concat_ws(' ', first_name, middle_name, last_name) " +
                "AS full_name, birth_date, " +
                "concat_ws(', ', country, city) AS address, company " +
                "FROM contacts.contact ;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.number WHERE contact_id = ?;" +
                "DELETE FROM contacts.attachments WHERE contact_id = ?;" +
                "DELETE FROM contacts.contact WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.setObject(2, id);
            statement.setObject(3, id);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("error main contact");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private List<MainContact> parseResultSet(ResultSet set) {
        LinkedList<MainContact> list = new LinkedList<>();
        try {
            while (set.next()) {
                MainContact contact = new MainContact();
                contact.setId(set.getInt("id"));
                contact.setFullName(set.getString("full_name"));
                contact.setBirthDate(set.getDate("birth_date"));
                contact.setAddress(set.getString("address"));
                contact.setCompany(set.getString("company"));
                list.add(contact);
            }
        } catch (SQLException s) {
            ///////////////////////
        }
        return list;
    }
}
