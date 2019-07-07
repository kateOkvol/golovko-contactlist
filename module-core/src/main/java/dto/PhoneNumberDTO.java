package dto;

import entities.PhoneNumber;

public class PhoneNumberDTO {

    private Integer id;
    private Integer contactID;
    private Integer number;
    private Integer countryCode;
    private Integer operatorCode;
    private String type;
    private String note;

    public PhoneNumberDTO() {
    }

    public PhoneNumberDTO(PhoneNumber phoneNumber) {
        this.id = phoneNumber.getId();
        this.note = phoneNumber.getNote();
        this.number = phoneNumber.getNumber();
        this.type = phoneNumber.getType();
        this.contactID = phoneNumber.getContactID();
        this.countryCode = phoneNumber.getCountryCode();
        this.operatorCode = phoneNumber.getOperatorCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Integer operatorCode) {
        this.operatorCode = operatorCode;
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
        return "PhoneNumberDTO{" +
                "id=" + id +
                ", contactID=" + contactID +
                ", number=" + number +
                ", countryCode=" + countryCode +
                ", operatorCode=" + operatorCode +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
