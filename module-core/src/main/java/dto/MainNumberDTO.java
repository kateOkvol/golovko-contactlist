package dto;

public class MainNumberDTO {
    private Integer contact_id;
    private Integer number_id;
    private String note;

    public MainNumberDTO() {
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
    public String toString() {
        return "MainNumberDTO{" +
                "contact_id=" + contact_id +
                ", number_id=" + number_id +
                ", note='" + note + '\'' +
                '}';
    }
}
