package dto;

import java.util.Date;

public class AttachmentsDTO {

    private Integer contact_id;
    private String path;
    private String note;
    private Date date;

    public AttachmentsDTO() {
    }

    public Integer getContact_id() {
        return contact_id;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        return "AttachmentsDTO{" +
                ", contact_id=" + contact_id +
                ", path='" + path + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                '}';
    }
}
