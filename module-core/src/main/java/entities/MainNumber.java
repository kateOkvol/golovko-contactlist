package entities;

import java.util.Objects;

public class MainNumber {
    private Integer contact_id;
    private Integer number_id;
    private String note;

    public MainNumber() {
    }

    public MainNumber(Integer contact_id, Integer number_id) {
        this.contact_id = contact_id;
        this.number_id = number_id;
    }

    public Integer getContact_id() {
        return contact_id;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    public Integer getNumber_id() {
        return number_id;
    }

    public void setNumber_id(Integer number_id) {
        this.number_id = number_id;
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
        return Objects.equals(contact_id, that.contact_id) &&
                Objects.equals(number_id, that.number_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact_id, number_id);
    }

    @Override
    public String toString() {
        return "MainNumberDTO{" +
                "contact_id=" + contact_id +
                ", number_id=" + number_id +
                ", note='" + note + '\'' +
                '}';
    }
}
