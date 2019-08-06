package dao.DAOImpl;

import dao.ContactDAO;
import db.DataBaseConnection;
import entities.Contact;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    public ContactDAOImpl() {
    }

    public String setAvatar(String avatar, int id) {
        if (avatar != null) {
            avatar = id + avatar;
            String sql = "UPDATE contacts.contact SET  avatar = '" + avatar + "' WHERE id = " + id + ";";
            try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return avatar;
    }

    @Override
    public Integer create(Contact contact) {
        Integer id = null;
        String sql = "INSERT INTO contacts.contact (first_name, last_name, middle_name, gender, " +
                "birth_date, citizenship, marital_status, web_site, email, company, country, city, street, house, flat, zip_code) " +
                "VALUES (?, ?, ?, ?::contacts.gender, ?, ?, ?::contacts.marital_status, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING id;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
        String sql = "SELECT id, regexp_replace(first_name, '\\s+$', '') AS first_name, " +
                "regexp_replace(last_name, '\\s+$', '') AS last_name, " +
                "regexp_replace(middle_name, '\\s+$', '') AS middle_name, " +
                "gender::contacts.gender, " +
                "birth_date, " +
                "regexp_replace(citizenship, '\\s+$', '') AS citizenship, " +
                "marital_status::contacts.marital_status, " +
                "regexp_replace(web_site, '\\s+$', '') AS web_site, " +
                "regexp_replace(email, '\\s+$', '') AS email, " +
                "regexp_replace(company, '\\s+$', '') AS company, " +
                "regexp_replace(country, '\\s+$', '') AS country, " +
                "regexp_replace(city, '\\s+$', '') AS city, " +
                "regexp_replace(street, '\\s+$', '') AS street, " +
                "regexp_replace(house, '\\s+$', '') AS house, " +
                "regexp_replace(flat, '\\s+$', '') AS flat, " +
                "zip_code, regexp_replace(avatar, '\\s+$', '') AS avatar " +
                "FROM contacts.contact WHERE id = ?";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            contact = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public List<Contact> getByBirthDate(Date date) {
        List<Contact> list = null;
        String sql = "SELECT first_name, middle_name, last_name " +
                "FROM contacts.contact WHERE birth_date = ?::date;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public void update(Contact contact) {
        String sql = "UPDATE contacts.contact SET first_name = ?, last_name = ?, middle_name = ?, gender = ?::contacts.gender, " +
                "birth_date = ?, citizenship = ?, marital_status = ?::contacts.marital_status, web_site = ?, email = ?, company = ?, " +
                "country = ?, city = ?, street = ?, house = ?, flat = ?, zip_code = ?, avatar = ? WHERE id = ?;";
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, contact);
            statement.setObject(17, contact.getAvatar());
            statement.setInt(18, contact.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("contact update exception");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> delete(Integer id) {
        String sqlAttach = "DELETE FROM contacts.attachments WHERE contact_id = ? RETURNING path;";
        String sqlNumber = "DELETE FROM contacts.number WHERE contact_id = ?;";
        String sqlContact = "DELETE FROM contacts.contact WHERE id = ? RETURNING avatar;";
        LinkedList<String> list = new LinkedList<>();
        fillList(list, sqlAttach, id);
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlNumber)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fillList(list, sqlContact, id);
        return list;
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

                String avatar = set.getString("avatar");
                contact.setAvatar((avatar != null) ? avatar : "no-avatar\\noAva.jpg");
                list.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    private void fillList(List<String> list, String sql, Integer id) {
        try (Connection connection = DataBaseConnection.getInstance().getSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                list.add(set.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
