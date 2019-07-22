package dto;

import java.util.List;

public class EmailMessageDTO implements DTO {
    private String message;
    private List<String> recipients;

    public EmailMessageDTO() {
    }

    public EmailMessageDTO(String message, List<String> recipients) {
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
    public String toString() {
        return "EmailMessageDTO{" +
                "message='" + message + '\'' +
                ", recipients=" + recipients +
                '}';
    }
}
