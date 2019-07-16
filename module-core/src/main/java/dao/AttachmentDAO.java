package dao;

import entities.Attachment;

import java.util.List;

public interface AttachmentDAO {
    void create(Attachment attachment);

    List<Attachment> getAll(Integer contactId);

    Attachment getById(Integer id);

    void update(Attachment object);

    void delete(Integer id);
}
