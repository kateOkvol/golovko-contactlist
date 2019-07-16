package entities;

import java.util.Objects;

public class PhoneNumber{
    private Integer id;
    private Integer contactId;
    private Integer phone;
    private Integer countryCode;
    private Integer operatorCode;
    private String type;
    private String note;

    public PhoneNumber() {
    }

    public PhoneNumber(Integer id, Integer contactId, Integer phone) {
        this.id = id;
        this.contactId = contactId;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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
                Objects.equals(contactId, phoneNumber1.contactId) &&
                Objects.equals(phone, phoneNumber1.phone) &&
                Objects.equals(countryCode, phoneNumber1.countryCode) &&
                Objects.equals(operatorCode, phoneNumber1.operatorCode) &&
                Objects.equals(type, phoneNumber1.type) &&
                Objects.equals(note, phoneNumber1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactId, phone, countryCode, operatorCode, type, note);
    }

    @Override
    public String toString() {
        return "PhoneNumberDTO{" +
                "id=" + id +
                ", contactId=" + contactId +
                ", phone=" + phone +
                ", countryCode=" + countryCode +
                ", operatorCode=" + operatorCode +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
