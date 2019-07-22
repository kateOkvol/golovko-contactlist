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
        String sql = "INSERT INTO contacts.attachments (contact_id, path, attach_name, date, note)" +
                "VALUES (?, ?, ?, ?::date, ?);";
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
        String sql = "SELECT id, contact_id, regexp_replace(attach_name, '\\s+$', '') AS attach_name, " +
                "regexp_replace(path, '\\s+$', '') AS path, date, regexp_replace(note, '\\s+$', '') AS note " +
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
        String sql = "SELECT id, contact_id, regexp_replace(attach_name, '\\s+$', '') AS attach_name, " +
                "regexp_replace(path, '\\s+$', '') AS path, date, regexp_replace(note, '\\s+$', '') AS note " +
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
        String sql = "UPDATE contacts.attachments SET contact_id = ?, path = ?, attach_name = ?, date = ?, note = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(6, attachment.getId());
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
    public String delete(Integer id) {
        String sql = "DELETE FROM contacts.attachments WHERE id = ? RETURNING path;";
        String deletePath = "";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            deletePath = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletePath;
    }

    private void partOfPrepare(PreparedStatement statement, Attachment attachment) throws SQLException {
        statement.setInt(1, attachment.getContactId());
        statement.setObject(2, attachment.getPath());
        statement.setObject(3, attachment.getAttachName());
        statement.setDate(4, attachment.getDate());
        statement.setObject(5, attachment.getNote());

    }

    private List<Attachment> parseResultSet(ResultSet set) {
        LinkedList<Attachment> list = new LinkedList<>();
        try {
            while (set.next()) {
                Attachment attachment = new Attachment();
                attachment.setId((Integer) set.getObject("id"));
                attachment.setContactId((Integer) set.getObject("contact_id"));

                String name = (set.getString("attach_name") == null)
                        ? set.getString("path")
                        : set.getString("attach_name");
                attachment.setAttachName(name);

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
