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
    public void create(MainContact mainContact) {

    }

    @Override
    public List<MainContact> getAll() {
        List<MainContact> list = null;

        String sql = "SELECT main.id, concat_ws(' ', cont.first_name, cont.middle_name, cont.last_name) " +
                "AS full_name, cont.birth_date, " +
                "concat_ws(',', address.country, address.city) AS address, cont.company " +
                "FROM contacts.main_contact main " +
                "JOIN contacts.contact cont ON main.contact_id=cont.id " +
                "JOIN contacts.address address ON main.address_id=address.id;";
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
        return null;
    }

    @Override
    public void update(MainContact object) {

    }

    @Override
    public void delete(MainContact object) {

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
