package services;

import dao.DAOImpl.AttachmentDAOImpl;
import dto.AttachmentDTO;
import entities.Attachment;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AttachmentService {
    private static final Logger logger = Logger.getLogger(AttachmentService.class);
    private AttachmentDAOImpl dao;
    private AttachmentDTO dto;

    public AttachmentService() {
        this.dao = new AttachmentDAOImpl();
    }

    public AttachmentService(AttachmentDTO dto) {
        this.dao = new AttachmentDAOImpl();
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

    public Integer createAttach() {
        return dao.create(setContactFields());
    }

    public String setPath(String path, int id){
        return dao.setPath(path, id);
    }

    public void updateAttach() {
        dao.update(setContactFields());
    }

    public String deleteAttach(Integer id) {
        return this.dao.delete(id);
    }

    private Attachment setContactFields() {
        Attachment attachment = new Attachment();
        attachment.setId(dto.getId());
        attachment.setContactId(dto.getContactId());
        attachment.setPath(dto.getPath());
        attachment.setAttachName(dto.getAttachName());
        attachment.setDate(dto.getDate());
        attachment.setNote(dto.getNote());
        return attachment;
    }
}
