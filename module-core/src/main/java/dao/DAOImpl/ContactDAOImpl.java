package dao.DAOImpl;

import dao.ContactDAO;
import entities.Contact;

import java.sql.Connection;
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
    public Integer create(Contact contact) {
        Integer id = null;
        String sql = "INSERT INTO contacts.contact (first_name, last_name, middle_name, gender, " +
                "birth_date, citizenship, marital_status, web_site, email, company, country, city, street, house, flat, zip_code) " +
                "VALUES (?, ?, ?, ?::contacts.gender, ?, ?, ?::contacts.marital_status, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING id;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, contact);
            ResultSet insertPerson = statement.executeQuery();
            insertPerson.next();
            id = (Integer) insertPerson.getObject(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    @Override
    public Contact getById(Integer id) {
        Contact contact = new Contact();
        String sql = "SELECT id, first_name, last_name, middle_name, gender::contacts.gender, " +
                "birth_date, citizenship, marital_status::contacts.marital_status, web_site, email, company, " +
                "country, city, street, house, flat, zip_code " +
                "FROM contacts.contact WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            contact = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //свой exception
        return contact;
    }

    @Override
    public void update(Contact contact) {
        String sql = "UPDATE contacts.contact SET first_name = ?, last_name = ?, middle_name = ?, gender = ?::contacts.gender, " +
                "birth_date = ?, citizenship = ?, marital_status = ?::contacts.marital_status, web_site = ?, email = ?, company = ?, " +
                "country = ?, city = ?, street = ?, house = ?, flat = ?, zip_code = ?  WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, contact);
            statement.setInt(17, contact.getId());
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
        String sql = "DELETE FROM contacts.number WHERE contact_id = ?;" +
                "DELETE FROM contacts.contact WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.setObject(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Contact> parseResultSet(ResultSet set) {
        LinkedList<Contact> list = new LinkedList<>();
        try {
            while (set.next()) {
                Contact contact = new Contact();
                contact.setId(set.getInt("id"));
                contact.setFirstName(set.getString("first_name"));
                contact.setLastName(set.getString("last_name"));
                contact.setMiddleName(set.getString("middle_name"));
                contact.setGender(set.getString("gender"));
                contact.setBirthDate(set.getDate("birth_date"));
                contact.setCitizenship(set.getString("citizenship"));
                contact.setMaritalStatus(set.getString("marital_status"));
                contact.setEmail(set.getString("email"));
                contact.setWebSite(set.getString("web_site"));
                contact.setCompany(set.getString("company"));
                contact.setCountry(set.getString("country"));
                contact.setCity(set.getString("city"));
                contact.setStreet(set.getString("street"));
                contact.setHouse(set.getString("house"));
                contact.setFlat(set.getString("flat"));
                contact.setZipCode((Integer) set.getObject("zip_code"));
                list.add(contact);
            }
        } catch (SQLException s) {
            ///////////////////////
        }
        return list;
    }

    private void partOfPrepare(PreparedStatement statement, Contact contact) throws SQLException {
        statement.setObject(1, contact.getFirstName());
        statement.setObject(2, contact.getLastName());
        statement.setObject(3, contact.getMiddleName());
        statement.setObject(4, contact.getGender());
        statement.setObject(5, contact.getBirthDate());
        statement.setObject(6, contact.getCitizenship());
        statement.setObject(7, contact.getMaritalStatus());
        statement.setObject(9, contact.getEmail());
        statement.setObject(8, contact.getWebSite());
        statement.setObject(10, contact.getCompany());
        statement.setObject(11, contact.getCountry());
        statement.setObject(12, contact.getCity());
        statement.setObject(13, contact.getStreet());
        statement.setObject(14, contact.getHouse());
        statement.setObject(15, contact.getFlat());
        statement.setObject(16, contact.getZipCode());
    }

}
