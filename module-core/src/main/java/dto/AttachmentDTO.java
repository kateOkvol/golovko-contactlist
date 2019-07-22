package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import entities.Attachment;

import java.sql.Date;

public class AttachmentDTO implements DTO {
    private Integer contactId;
    private Integer id;
    private String path;
    private String attachName;
    private String note;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    public AttachmentDTO() {
    }

    public AttachmentDTO(Attachment attachment) {
        this.contactId = attachment.getContactId();
        this.id = attachment.getId();
        this.path = attachment.getPath();
        this.attachName = attachment.getAttachName();
        this.note = attachment.getNote();
        this.date = attachment.getDate();
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "AttachmentDTO{" +
                "contactId=" + contactId +
                ", id=" + id +
                ", path='" + path + '\'' +
                ", attachName='" + attachName + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                '}';
    }
}
