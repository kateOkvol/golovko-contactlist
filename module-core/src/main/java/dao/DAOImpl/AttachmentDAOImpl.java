package dao.DAOImpl;

import dao.AttachmentDAO;
import entities.Attachment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AttachmentDAOImpl implements AttachmentDAO {
    private Connection connection;

    public AttachmentDAOImpl() {
    }

    public AttachmentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Attachment attachment) {
        String sql = "INSERT INTO contacts.attachments (contact_id, path, date,  note)" +
                "VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            partOfPrepare(statement, attachment);
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("contact update exception");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Attachment> getAll(Integer contactId) {
        List<Attachment> list = null;
        String sql = "SELECT id, contact_id, path, date, note " +
                "FROM contacts.attachments " +
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
    public Attachment getById(Integer id) {
        Attachment attachment = new Attachment();
        String sql = "SELECT id, contact_id, path, note, date " +
                "FROM contacts.attachments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            attachment = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public void update(Attachment attachment) {
        String sql = "UPDATE contacts.attachments SET contact_id = ?, path = ?, date = ?, note = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(5, attachment.getId());
            partOfPrepare(statement, attachment);
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
        String sql = "DELETE FROM contacts.attachments WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void partOfPrepare(PreparedStatement statement, Attachment attachment) throws SQLException {
        statement.setInt(1, attachment.getContactId());
        statement.setObject(2, attachment.getPath());
        statement.setObject(3, attachment.getNote());
        statement.setObject(4, attachment.getDate());
    }

    private List<Attachment> parseResultSet(ResultSet set) {
        LinkedList<Attachment> list = new LinkedList<>();
        try {
            while (set.next()) {
                Attachment attachment = new Attachment();
                attachment.setId((Integer) set.getObject("id"));
                attachment.setContactId((Integer) set.getObject("contact_id"));
                attachment.setPath(set.getString("path"));
                attachment.setDate(set.getDate("date"));
                attachment.setNote(set.getString("note"));
                list.add(attachment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
