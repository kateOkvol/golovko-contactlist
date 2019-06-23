package dao.DAOImpl;

import dao.ContactDAO;
import entities.Contact;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {
    private Connection connection;

    public ContactDAOImpl() {
    }

    public ContactDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Contact contact) {
        String sql = "INSERT INTO contacts.contact (first_name, last_name, middle_name, " +
                "birth_date, gender, citizenship, marital_status, web_site, email, company, address_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, contact);
            statement.setInt(12, contact.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> list = null;
        String sql = "SELECT first_name, last_name, middle_name, " +
                " birth_date, gender, citizenship, marital_status, web_site, email, company, address_id FROM contacts.contact";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("разраб свою ошибку, getall contact");
        }
        return list;
    }

    @Override
    public Contact getById(Integer id) {
        Contact contact = new Contact();
        String sql = "SELECT first_name, last_name, middle_name, " +
                "birth_date, gender, citizenship, marital_status, web_site, email, company, address_id FROM contacts.contact WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            contact = parseResultSet(resultSet).get(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //свой exception
        return contact;
    }

    @Override
    public void update(Contact contact) {
        String sql = "UPDATE contacts.contact SET first_name = ?, last_name = ?, middle_name = ?, " +
                "birth_date = ?, gender = ?, citizenship = ?, marital_status = ?, web_site = ?, " +
                "email = ?, company = ?, address_id = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, contact);
            statement.setInt(12, contact.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("contact update exception");//throw new Exception();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts.contact WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("разраб свою ошибку, делит контакта");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class PersistContact extends Contact {
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    private List<Contact> parseResultSet(ResultSet set) {
        LinkedList<Contact> list = new LinkedList<>();
        try {
            while (set.next()) {
                PersistContact contact = new PersistContact();
                contact.setId(set.getInt("id"));
                contact.setFirstName(set.getString("first_name"));
                contact.setLastName(set.getString("last_name"));
                contact.setMiddleName(set.getString("middle_name"));
                contact.setBirthDate(set.getDate("birth_date"));
                contact.setGender(set.getBoolean("gender"));
                contact.setCitizenship(set.getString("citizenship"));
                contact.setMaritalStatus(set.getString("marital_status"));
                contact.setEmail(set.getString("email"));
                contact.setWebSite(set.getString("web_site"));
                contact.setCompany(set.getString("company"));
                contact.setAddress_id(set.getInt("address_id"));
                list.add(contact);
            }
        } catch (SQLException s) {
            ///////////////////////
        }
        return list;
    }

    private void partOfPrepare(PreparedStatement statement, Contact contact) throws SQLException {
        statement.setString(1, contact.getFirstName());
        statement.setString(2, contact.getLastName());
        statement.setString(3, contact.getMiddleName());
        statement.setDate(4, (Date) contact.getBirthDate());
        statement.setBoolean(5, contact.getGender());
        statement.setString(6, contact.getCitizenship());
        statement.setString(7, contact.getMaritalStatus());
        statement.setString(8, contact.getEmail());
        statement.setString(9, contact.getWebSite());
        statement.setString(10, contact.getCompany());
        statement.setInt(11, contact.getAddress_id());
    }

}
