package dao;

import entities.Attachment;

import java.util.List;

public interface AttachmentDAO {
    Integer create(Attachment attachment);

    List<Attachment> getAll(Integer contactId);

    Attachment getById(Integer id);

    void update(Attachment object);

    String delete(Integer id);
}
