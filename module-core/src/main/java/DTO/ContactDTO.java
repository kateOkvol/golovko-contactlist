package DTO;

import entities.Contact;


import java.util.Date;

public class ContactDTO {

    private Integer id;
    private Integer address_id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private Boolean gender;
    private String citizenship;
    private String maritalStatus;
    private String webSite;
    private String email;
    private String company;

    public ContactDTO() {
    }

    public Integer getId() {
        return id;
    }

    public ContactDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public ContactDTO setAddress_id(Integer address_id) {
        this.address_id = address_id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public ContactDTO setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public ContactDTO setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Boolean getGender() {
        return gender;
    }

    public ContactDTO setGender(Boolean gender) {
        this.gender = gender;
        return this;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public ContactDTO setCitizenship(String citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public ContactDTO setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public String getWebSite() {
        return webSite;
    }

    public ContactDTO setWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public ContactDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + id +
                ", address_id=" + address_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", citizenship='" + citizenship + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", webSite='" + webSite + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
