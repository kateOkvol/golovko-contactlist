package services;

import dao.DAOImpl.ContactDAOImpl;
import db.DataBaseConnection;
import dto.EmailMessageDTO;
import entities.Contact;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EmailMessageService {
    public static void sendMessage(String recipient, String message, String topic) throws IOException, MessagingException {
        Properties properties = new Properties();
        properties.load(EmailMessageService.class.getClassLoader().getResourceAsStream("email.properties"));
        String user = properties.getProperty("mail.smtps.user");
        String password = properties.getProperty("mail.smtps.password");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(user));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setText(message, "UTF-8");
        mimeMessage.setSubject(topic, "UTF-8");

        Transport transport = session.getTransport();
        transport.connect(null, password);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }

    public Map<String, String> getTemplatesMap() throws IOException {
        Properties properties = new Properties();
        properties.load(EmailMessageService.class.getClassLoader().getResourceAsStream("templates.properties"));
        Map<String, String> templatesMap = new HashMap<>();
        templatesMap.put("birthday", properties.getProperty("birthday"));
        templatesMap.put("newYear", properties.getProperty("new-year"));
        return templatesMap;
    }

    public void sendEmail(EmailMessageDTO email) throws Exception {
        List<String> recipientsId = email.getRecipients();
        List<String> recipientsEmail = new ArrayList<>();
        List<String> recipientsNames = new ArrayList<>();

        ContactDAOImpl dao = new ContactDAOImpl(DataBaseConnection.getConnection());

        for (String id : recipientsId) {
            Contact contact = dao.getById(Integer.parseInt(id));
            recipientsEmail.add(contact.getEmail());
            recipientsNames.add(contact.getFirstName());
        }
        String text = email.getMessage();
        if (!text.equals("")) {
            int i = 0;
            for (String mail : recipientsEmail) {
                StringTemplate temp = new StringTemplate(text, DefaultTemplateLexer.class);
                temp.setAttribute("name", recipientsNames.get(i));
                if (!mail.equals("")) {
                    sendMessage(mail, temp.toString(), email.getTopic());
                }
                i++;
            }
        }
    }
}
