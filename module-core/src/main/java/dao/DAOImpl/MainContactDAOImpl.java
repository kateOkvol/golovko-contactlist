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
        //свой exception
        return list;
    }

    @Override
    public MainContact getById(Integer id) {
        MainContact mainContact = new MainContact();
        String sql = "SELECT id, concat_ws(' ', cont.first_name, cont.middle_name, cont.last_name) " +
                "AS full_name, cont.birth_date, " +
                "concat_ws(',', country, city) AS address, cont.company " +
                "FROM contacts.contact cont " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            mainContact = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("разраб свою ошибку, getbyid main");
            e.printStackTrace();
        }
        return mainContact;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.contact WHERE id = ?;";
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

    private class PersistMainContact extends MainContact {
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    private List<MainContact> parseResultSet(ResultSet set) {
        LinkedList<MainContact> list = new LinkedList<>();
        try {
            while (set.next()) {
                PersistMainContact contact = new PersistMainContact();
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
