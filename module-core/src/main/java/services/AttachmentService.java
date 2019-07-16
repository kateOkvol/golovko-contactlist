package services;

import dao.DAOImpl.AttachmentDAOImpl;
import db.DataBaseConnection;
import dto.AttachmentDTO;
import entities.Attachment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentService {
    private static final String PATH = "C:\\Users\\okvol\\Desktop\\iTechArt\\attachments\\";

    private AttachmentDAOImpl dao;
    private AttachmentDTO dto;

    public AttachmentService() {
        try {
            this.dao = new AttachmentDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
    }

    public AttachmentService(AttachmentDTO dto) {
        try {
            this.dao = new AttachmentDAOImpl(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("no connection to start the address service");
        }
        this.dto = dto;
    }

    public List<AttachmentDTO> findAll(Integer contactId) {
        List<Attachment> list = this.dao.getAll(contactId);
        List<AttachmentDTO> contactList = new ArrayList<>();
        for (Attachment attachment : list) {
            contactList.add(new AttachmentDTO(attachment));
        }
        return contactList;
    }

    public AttachmentDTO getById(Integer id) {
        return new AttachmentDTO(dao.getById(id));
    }

    public void createAttach() {
        dao.create(setContactFields());
    }

    public void updateAttach() {
        dao.update(setContactFields());
    }

    public void deleteAttach(Integer id) {
        this.dao.delete(id);

    }

    private Attachment setContactFields() {
        Attachment attachment = new Attachment();
        attachment.setId(dto.getId());
        attachment.setContactId(dto.getContactId());
        if (!dto.getPath().isEmpty()) {
            attachment.setPath(PATH + dto.getPath());
        }
        attachment.setDate(dto.getDate());
        attachment.setNote(dto.getNote());
        return attachment;
    }
}
