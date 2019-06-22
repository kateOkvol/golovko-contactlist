package dao;

import entities.Attachments;

import java.util.List;

public interface AttachmentsDAO {
    void create(Attachments attachments);

    List<Attachments> getAll();

    Attachments getById(Integer id);

    void update(Attachments object);

    void delete(Attachments object);
}
