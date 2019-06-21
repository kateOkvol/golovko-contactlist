package DAO.DAOImpl;

import entities.PhoneNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PhoneNumberDAOImpl {
//    public PhoneNumberDAOImpl(Connection connection) {
//        super(connection);
//    }
//
//    @Override
//    public String getSelectQuery() {
//        return "SELECT * FROM contacts.number";
//    }
//
//    @Override
//    public String getCreateQuery() {
//        return "INSERT INTO contacts.number (contact_id, number, country_code, " +
//                "operator_code, type, note) VALUES (?, ?, ?, ?, ?, ?);";
//    }
//
//    @Override
//    public String getUpdateQuery() {
//        return "UPDATE SET contacts.number contact_id = ? number = ? country_code = ? " +
//                "operator_code = ? type = ? note = ? WHERE id = ?;";
//    }
//
//    @Override
//    public String getDeleteQuery() {
//        return "DELETE FROM contacts.number WHERE id = ?;";
//    }
//
//    @Override
//    public List<PhoneNumberDTO> parseResultSet(ResultSet set) {
//        LinkedList<PhoneNumberDTO> list = new LinkedList<>();
//        try {
//            while (set.next()) {
//                PersistNumber number = new PersistNumber();
//                number.setId(set.getInt("id"));
//                number.setId(set.getInt("contact_id"));
//                number.setId(set.getInt("number"));
//                number.setId(set.getInt("country_code"));
//                number.setId(set.getInt("operator_code"));
//                number.setType(set.getString("type"));
//                number.setNote(set.getString("note"));
//                list.add(number);
//            }
//        } catch (SQLException s) {
//            ///////////////////////
//        }
//        return list;
//    }
//
//    @Override
//    public void prepareForInsert(PreparedStatement statement, PhoneNumberDTO number) {
//        try {
//            partOfPrepare(statement,number);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void prepareForUpdate(PreparedStatement statement, PhoneNumberDTO number) {
//        try {
//            partOfPrepare(statement,number);
//            statement.setInt(7, number.getId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public PhoneNumberDTO create() {
//
//        PhoneNumberDTO number = new PhoneNumberDTO();
//        return persist(number);
//    }
//
//    private void partOfPrepare(PreparedStatement statement, PhoneNumberDTO number) throws SQLException {
//        statement.setInt(1, number.getContactID());
//        statement.setInt(2, number.getNumber());
//        statement.setInt(3, number.getCountryCode());
//        statement.setInt(4, number.getOperatorCode());
//        statement.setString(5, number.getType());
//        statement.setString(6, number.getNote());
//    }
//
//    private class PersistNumber extends PhoneNumberDTO{
//        public void setId(Integer id){
//            super.setId(id);
//        }
//    }
}
