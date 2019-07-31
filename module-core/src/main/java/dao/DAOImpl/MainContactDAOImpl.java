package dao.DAOImpl;

import dao.MainContactDAO;
import entities.MainContact;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class MainContactDAOImpl implements MainContactDAO {

    private Connection connection;

    public MainContactDAOImpl() {
    }

    public MainContactDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Integer countContacts() {
        String sql = "SELECT count(*) FROM contacts.contact ";
        Integer result = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MainContact> getAll(int page) {
        Properties properties = new Properties();
        try {
            properties.load(MainContactDAOImpl.class.getClassLoader().getResourceAsStream("config-core.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int amount = Integer.parseInt(properties.getProperty("contact_amount"));
        String sql = "SELECT id, concat_ws(' ', regexp_replace(first_name, '\\s+$', ''), " +
                "regexp_replace(last_name, '\\s+$', ''), " +
                "regexp_replace(middle_name, '\\s+$', '')) " +
                "AS full_name, birth_date, " +
                "concat_ws(', ', regexp_replace(country, '\\s+$', ''), " +
                "regexp_replace(city, '\\s+$', '')) AS address, company " +
                "FROM contacts.contact limit " + amount + " offset " + amount * (page - 1) + ";";
        return processGetReq(sql);
    }

    public List<MainContact> search(String query) {
        String sql = "SELECT id, concat_ws(' ', regexp_replace(first_name, '\\s+$', ''), " +
                "regexp_replace(last_name, '\\s+$', ''), " +
                "regexp_replace(middle_name, '\\s+$', '')) " +
                "AS full_name, birth_date, " +
                "concat_ws(', ', regexp_replace(country, '\\s+$', ''), " +
                "regexp_replace(city, '\\s+$', '')) AS address, company " +
                "FROM contacts.contact WHERE " + query + ";";
        return processGetReq(sql);
    }

    private List<MainContact> processGetReq(String sql) {
        List<MainContact> list = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<MainContact> parseResultSet(ResultSet set) {
        LinkedList<MainContact> list = new LinkedList<>();
        try {
            while (set.next()) {
                MainContact contact = new MainContact();
                contact.setId(set.getInt("id"));
                contact.setFullName(set.getString("full_name"));
                contact.setBirthDate(set.getDate("birth_date"));
                String address = set.getString("address");
                if (address.equals(", ")) address = null;
                contact.setAddress(address);
                contact.setCompany(set.getString("company"));
                list.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
