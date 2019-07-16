package dto;

import entities.MainNumber;

public class MainNumberDTO implements DTO{
    private Integer contactId;
    private Integer id;
    private String fullNumber;
    private String type;
    private String note;

    public MainNumberDTO() {
    }

    public MainNumberDTO(MainNumber mainNumber) {
        this.contactId = mainNumber.getContactId();
        this.id = mainNumber.getId();
        this.fullNumber = mainNumber.getFullNumber();
        this.note = mainNumber.getNote();
        this.type = mainNumber.getType();
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getNumber_id() {
        return id;
    }

    public MainNumberDTO setNumber_id(Integer id) {
        this.id = id;
        return this;
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
    public String toString() {
        return "MainNumberDTO{" +
                "contactId=" + contactId +
                ", number_id=" + id +
                ", fullNumber=" + fullNumber +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
