package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import entities.MainContact;

import java.util.Date;

public class MainContactDTO {
    private Integer id;
    private String fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthDate;
    private String address;
    private String company;

    public MainContactDTO() {
    }

    public MainContactDTO(MainContact contact) {
        this.id = contact.getId();
        this.fullName = contact.getFullName();
        this.birthDate = contact.getBirthDate();
        this.address = contact.getAddress();
        this.company = contact.getCompany();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}