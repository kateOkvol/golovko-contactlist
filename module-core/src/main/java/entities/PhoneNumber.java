package entities;

import java.util.Objects;

public class PhoneNumber{
    private Integer id;
    private Integer contactID;
    private Integer number;
    private Integer countryCode;
    private Integer operatorCode;
    private String type;
    private String note;

    public PhoneNumber() {
    }

    public PhoneNumber(Integer id, Integer contactID, Integer number) {
        this.id = id;
        this.contactID = contactID;
        this.number = number;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber phoneNumber1 = (PhoneNumber) o;
        return Objects.equals(id, phoneNumber1.id) &&
                Objects.equals(contactID, phoneNumber1.contactID) &&
                Objects.equals(number, phoneNumber1.number) &&
                Objects.equals(countryCode, phoneNumber1.countryCode) &&
                Objects.equals(operatorCode, phoneNumber1.operatorCode) &&
                Objects.equals(type, phoneNumber1.type) &&
                Objects.equals(note, phoneNumber1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactID, number, countryCode, operatorCode, type, note);
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
