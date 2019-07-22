package entities;

import java.util.List;
import java.util.Objects;

public class EmailMessage {
    private String message;
    private List<String> recipients;

    public EmailMessage() {
    }

    public EmailMessage(String message, List<String> recipients) {
        this.message = message;
        this.recipients = recipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMessage that = (EmailMessage) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(recipients, that.recipients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, recipients);
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "message='" + message + '\'' +
                ", recipients=" + recipients +
                '}';
    }
}
