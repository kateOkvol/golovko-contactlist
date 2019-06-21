package entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;


@XmlRootElement
public class MainContact {
    private Integer id;
    private Integer contact_id;
    private Integer address_id;

    public MainContact() {
    }

    public MainContact(Integer id, Integer contact_id, Integer address_id) {
        this.id = id;
        this.contact_id = contact_id;
        this.address_id = address_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContact_id() {
        return contact_id;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainContact that = (MainContact) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(contact_id, that.contact_id) &&
                Objects.equals(address_id, that.address_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contact_id, address_id);
    }

    @Override
    public String toString() {
        return "MainContact{" +
                "id=" + id +
                ", contact_id=" + contact_id +
                ", address_id=" + address_id +
                '}';
    }
}