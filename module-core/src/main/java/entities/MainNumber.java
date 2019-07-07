package entities;

import java.util.Objects;

public class MainNumber {
    private Integer contactId;
    private Integer id;
    private String fullNumber;
    private String type;
    private String note;

    public MainNumber() {
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

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        MainNumber that = (MainNumber) o;
        return Objects.equals(contactId, that.contactId) &&
                Objects.equals(id, that.id) &&
                Objects.equals(fullNumber, that.fullNumber) &&
                Objects.equals(type, that.type) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, id, fullNumber, type, note);
    }

    @Override
    public String toString() {
        return "MainNumber{" +
                "contactId=" + contactId +
                ", id=" + id +
                ", fullNumber=" + fullNumber +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
