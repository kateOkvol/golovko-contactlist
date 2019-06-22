package dto;

public class AddressDTO {

    private Integer id;
    private Integer zipCode;
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;

    public AddressDTO() {
    }


    public Integer getId() {
        return id;
    }

    public AddressDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public AddressDTO setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getHouse() {
        return house;
    }

    public AddressDTO setHouse(String house) {
        this.house = house;
        return this;
    }

    public String getFlat() {
        return flat;
    }

    public AddressDTO setFlat(String flat) {
        this.flat = flat;
        return this;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                ", id=" + id +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }
}
