package entities;


import java.util.Date;
import java.util.Objects;

public class Attachments {
    private Integer contact_id;
    private String path;
    private Date date;
    private String note;

    public Attachments() {
    }

    public Attachments(Integer contact_id, String path, Date date) {
        this.contact_id = contact_id;
        this.path = path;
        this.date = date;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachments that = (Attachments) o;
        return Objects.equals(contact_id, that.contact_id) &&
                Objects.equals(path, that.path) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact_id, path, date);
    }

    @Override
    public String toString() {
        return "Attachments{" +
                "contact_id=" + contact_id +
                ", path='" + path + '\'' +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }
}
