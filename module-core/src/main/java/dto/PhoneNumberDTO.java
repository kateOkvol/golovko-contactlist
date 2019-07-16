package dto;

import entities.PhoneNumber;

public class PhoneNumberDTO implements DTO{

    private Integer id;
    private Integer contactId;
    private Integer phone;
    private Integer countryCode;
    private Integer operatorCode;
    private String type;
    private String note;

    public PhoneNumberDTO() {
    }

    public PhoneNumberDTO(PhoneNumber phoneNumber) {
        this.id = phoneNumber.getId();
        this.note = phoneNumber.getNote();
        this.phone = phoneNumber.getPhone();
        this.type = phoneNumber.getType();
        this.contactId = phoneNumber.getContactId();
        this.countryCode = phoneNumber.getCountryCode();
        this.operatorCode = phoneNumber.getOperatorCode();
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
