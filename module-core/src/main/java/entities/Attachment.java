package entities;


import java.sql.Date;
import java.util.Objects;

public class Attachment {
    private Integer contactId;
    private Integer id;
    private String path;
    private Date date;
    private String note;

    public Attachment() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(contactId, that.contactId) &&
                Objects.equals(path, that.path) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, path, date);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "contactId=" + contactId +
                ", path='" + path + '\'' +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }
}
