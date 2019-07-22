package services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailMessageService {
    public static void sendMessage (String recipient, String message) throws IOException, MessagingException {
        Properties properties = new Properties();
        properties.load(EmailMessageService.class.getClassLoader().getResourceAsStream("email.properties"));
         String user = properties.getProperty("mail.smtps.user");
        String password = properties.getProperty("mail.smtps.password");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(user));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setText(message);

        Transport transport = session.getTransport();
        transport.connect(null, password);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }
}
